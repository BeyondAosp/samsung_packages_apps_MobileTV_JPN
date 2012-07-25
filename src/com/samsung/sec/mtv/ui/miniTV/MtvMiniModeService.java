// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.miniTV;

import android.app.ActivityManager;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.os.*;
import android.telephony.TelephonyManager;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.MtvAppAndroidService;
import com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer;
import com.samsung.sec.mtv.utility.*;
import com.sec.android.app.minimode.MainView;
import com.sec.android.app.minimode.MiniModeService;
import com.sec.android.hardware.SecHardwareInterface;
import java.util.List;

// Referenced classes of package com.samsung.sec.mtv.ui.miniTV:
//            MtvUiMiniModeSurface, MtvMiniModeTvOut

public class MtvMiniModeService extends MiniModeService
    implements IMtvAppPlaybackStateListener
{
    class OnExitTouchListener extends com.sec.android.app.minimode.MiniModeService.TouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            super.onTouch(view, motionevent);
            motionevent.getAction();
            JVM INSTR tableswitch 0 1: default 32
        //                       0 34
        //                       1 44;
               goto _L1 _L2 _L3
_L1:
            return true;
_L2:
            MtvUtilDebug.Low("MtvMiniModeService", "EXIT BUTTON : ACTION_DOWN");
            continue; /* Loop/switch isn't completed */
_L3:
            MtvUtilDebug.Low("MtvMiniModeService", "EXIT BUTTON : ACTION_DOWN");
            if(mExitLayout != null)
                mExitLayout.setVisibility(8);
            closeOneseg();
            stopSelf();
            if(true) goto _L1; else goto _L4
_L4:
        }

        final MtvMiniModeService this$0;

        OnExitTouchListener()
        {
            this$0 = MtvMiniModeService.this;
            super(MtvMiniModeService.this);
        }
    }

    class miniModeSurfaceOnTouchListener extends com.sec.android.app.minimode.MiniModeService.TouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            int i;
            super.onTouch(view, motionevent);
            i = motionevent.getAction();
            motionevent.getEventTime() - motionevent.getDownTime();
            i;
            JVM INSTR tableswitch 0 2: default 52
        //                       0 54
        //                       1 208
        //                       2 105;
               goto _L1 _L2 _L3 _L4
_L1:
            return true;
_L2:
            MtvUtilDebug.Low("MtvMiniModeService", "ACTION_DOWN");
            mDownTime = (int)motionevent.getEventTime();
            mXdown = motionevent.getRawX();
            mYdown = motionevent.getRawY();
            mHandler.sendEmptyMessageDelayed(11, 500L);
            continue; /* Loop/switch isn't completed */
_L4:
            MtvUtilDebug.Low("MtvMiniModeService", "ACTION_MOVE");
            float f2 = Math.abs(mXdown - motionevent.getRawX());
            float f3 = Math.abs(mYdown - motionevent.getRawY());
            if((long)(int)(motionevent.getEventTime() - (long)mDownTime) > 500L && true)
                if(f2 < (float)ViewConfiguration.getTouchSlop() && f3 < (float)ViewConfiguration.getTouchSlop())
                    MtvUtilDebug.Low("MtvMiniModeService", "showExitDialog");
                else
                    mHandler.removeMessages(11);
            continue; /* Loop/switch isn't completed */
_L3:
            MtvUtilDebug.Low("MtvMiniModeService", "ACTION_UP");
            mXup = motionevent.getRawX();
            mYup = motionevent.getRawY();
            float f = Math.abs(mXdown - mXup);
            float f1 = Math.abs(mYdown - mYup);
            int j = (int)(motionevent.getEventTime() - (long)mDownTime);
            if(f < (float)ViewConfiguration.getTouchSlop() && f1 < (float)ViewConfiguration.getTouchSlop() && (long)j <= 500L)
            {
                MtvUtilDebug.Low("MtvMiniModeService", "ACTION_UP TouchListener : Short click!!");
                if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    mHandler.removeMessages(11);
                    if(mExitLayout != null)
                        mExitLayout.setVisibility(8);
                    siwitchToNormalMode();
                } else
                if(checkIsCalling(mContext))
                    Toast.makeText(mContext, 0x7f07009e, 0).show();
                else
                    playMiniMode();
            }
            if(true) goto _L1; else goto _L5
_L5:
        }

        int mDownTime;
        float mXdown;
        float mXup;
        float mYdown;
        float mYup;
        final MtvMiniModeService this$0;

        miniModeSurfaceOnTouchListener()
        {
            this$0 = MtvMiniModeService.this;
            super(MtvMiniModeService.this);
            mDownTime = 0;
            mXdown = 0.0F;
            mYdown = 0.0F;
            mXup = 0.0F;
            mYup = 0.0F;
        }
    }


    public MtvMiniModeService()
    {
        miniMainView = null;
        mContext = null;
        mMtvAppPlaybackContext = null;
        mMiniTvSurface = null;
        mMtvPreferences = null;
        tvoutService = null;
        mMtvAudMgr = null;
        mExitLayout = null;
        mReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                if(!"android.intent.action.SCREEN_ON".equals(intent.getAction())) goto _L2; else goto _L1
_L1:
                MtvUtilDebug.Low("MtvMiniModeService", "ACTION_SCREEN_ON");
_L4:
                return;
_L2:
                if("android.intent.action.SCREEN_OFF".equals(intent.getAction()))
                    MtvUtilDebug.Low("MtvMiniModeService", "ACTION_SCREEN_OFF");
                else
                if(!"android.intent.action.PALM_DOWN".equals(intent.getAction()))
                    if("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction()))
                        MtvUtilDebug.Low("MtvMiniModeService", "mReceiver - receive ACTION_AUDIO_BECOMING_NOISY");
                    else
                    if(intent.getAction().equals("android.intent.action.BATTERY_LOW"))
                    {
                        Toast.makeText(mContext, 0x7f0700a1, 0).show();
                        closeOneseg();
                    } else
                    if("intent.stop.app-in-app".equals(intent.getAction()))
                    {
                        String s = null;
                        ActivityManager activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
                        if(activitymanager != null)
                            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
                        if(s != null && !s.equalsIgnoreCase("com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"))
                            closeOneseg();
                    } else
                    if(intent.getAction().equals("android.intent.action.HDMI_PLUGGED"))
                        if(intent.getBooleanExtra("state", false))
                            MtvMiniModeTvOut.suspendTvOut();
                        else
                            MtvMiniModeTvOut.resumeTvOut();
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MtvMiniModeService this$0;

            
            {
                this$0 = MtvMiniModeService.this;
                super();
            }
        };
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 11 12: default 28
            //                           11 29
            //                           12 63;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                removeMessages(11);
                mExitLayout.setVisibility(0);
                sendMessageDelayed(obtainMessage(12), 10000L);
                continue; /* Loop/switch isn't completed */
_L3:
                mExitLayout.setVisibility(8);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvMiniModeService this$0;

            
            {
                this$0 = MtvMiniModeService.this;
                super();
            }
        };
    }

    private boolean checkIsCalling(Context context)
    {
        boolean flag = true;
        IVoIPInterface ivoipinterface = android.os.IVoIPInterface.Stub.asInterface(ServiceManager.checkService("voip"));
        if(!ivoipinterface.isVoIPDialing() && !ivoipinterface.isVoIPActivated()) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvMiniModeService", "VOIP checkIsCalling");
_L4:
        return flag;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
_L2:
        if(((TelephonyManager)context.getSystemService("phone")).getCallState() == 0)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L2
    }

    private void registerIntents()
    {
        MtvUtilDebug.Low("MtvMiniModeService", "registerIntents: entered ");
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SCREEN_ON");
        intentfilter.addAction("android.intent.action.BATTERY_LOW");
        intentfilter.addAction("intent.stop.app-in-app");
        intentfilter.addAction("android.intent.action.SCREEN_OFF");
        intentfilter.addAction("android.intent.action.PALM_DOWN");
        intentfilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        intentfilter.addAction("android.intent.action.HDMI_PLUGGED");
        intentfilter.addAction("intent.finished.app-in-app");
        registerReceiver(mReceiver, intentfilter);
    }

    private void setScreenRatio()
    {
        MtvUtilDebug.Low("MtvMiniModeService", "setScreenRatio landscape");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvMiniModeService", (new StringBuilder()).append("setScreenRatio LCD_HEIGHT:").append(293).append("LCD_WIDTH:").append(520).toString());
        int i;
        int j;
        int k;
        int l;
        android.widget.RelativeLayout.LayoutParams layoutparams;
        android.view.WindowManager.LayoutParams layoutparams1;
        if(1.774744F == 1.777778F)
        {
            MtvUtilDebug.Low("MtvMiniModeService", "setScreenRatio 16:9 NORMAL");
            j = 520;
            int _tmp = 520 / 16;
            i = 9 * 32;
        } else
        {
            MtvUtilDebug.Low("MtvMiniModeService", "setScreenRatio 4:3 NORMAL ");
            i = 293;
            int _tmp1 = 520 / 3;
            j = 4 * 173;
        }
        k = (520 - j) / 2;
        l = (293 - i) / 2;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvMiniModeService", (new StringBuilder()).append("setScreenRatio x = ").append(k).append(" y = ").append(l).append(" w = ").append(j).append(" h = ").append(i).toString());
        layoutparams = new android.widget.RelativeLayout.LayoutParams(j, i);
        mMiniTvSurface.setLayoutParams(layoutparams);
        layoutparams1 = getAttributes();
        layoutparams1.x = 60;
        layoutparams1.y = 60;
        layoutparams1.gravity = 51;
        layoutparams1.flags = 8 | layoutparams1.flags;
        layoutparams1.flags = 0x2000 | layoutparams1.flags;
        setAttributes(layoutparams1);
    }

    private void unRegisterIntents()
    {
        MtvUtilDebug.Low("MtvMiniModeService", "unregister()");
        unregisterReceiver(mReceiver);
    }

    public void closeOneseg()
    {
        mMtvAudMgr.removeCallStateListener();
        MtvUtilAppService.forceresetMtvVisibiltySettings();
        stopService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService));
        MtvAppPlayerOneSeg.getInstance().delete(MtvAppPlaybackContextManager.getInstance().getCurrentContext());
    }

    protected boolean onClose(int i)
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   1: 32
    //                   6: 32;
           goto _L1 _L2 _L2
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCreate()
    {
        MtvUtilDebug.Low("MtvMiniModeService", "onCreate: entered ");
        super.onCreate();
        mContext = getApplicationContext();
        miniMainView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f03002b, null);
        setContentView(miniMainView, 0x7f0a010e, 0x7f0a010c);
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        mMiniTvSurface = (MtvUiMiniModeSurface)miniMainView.findViewById(0x7f0a010d);
        mMiniTvSurface.setOnTouchListener(new miniModeSurfaceOnTouchListener());
        mMainView.setBackgroundDrawable(null);
        mExitLayout = (RelativeLayout)miniMainView.findViewById(0x7f0a010e);
        mExitLayout.setVisibility(8);
        mExitLayout.setOnTouchListener(new OnExitTouchListener());
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        tvoutService = android.os.ITvoutService.Stub.asInterface(ServiceManager.getService("tvoutservice"));
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
    }

    public void onDestroy()
    {
        MtvUtilDebug.Low("MtvMiniModeService", "onDestroy: entered ");
        SecHardwareInterface.setmDNIeUIMode(0);
        try
        {
            if(tvoutService.TvoutGetSuspendStatus())
                tvoutService.TvoutSetSuspendStatus(false);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        sendBroadcast(new Intent("intent.finished.app-in-app"));
        unRegisterIntents();
        super.onDestroy();
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   20483: 51
    //                   20484: 37
    //                   20495: 86;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        return;
_L3:
        if(24592 == j)
            stopSelf();
        break; /* Loop/switch isn't completed */
_L2:
        switch(j)
        {
        case 24588: 
            closeOneseg();
            stopSelf();
            break;
        }
        if(false)
            ;
        continue; /* Loop/switch isn't completed */
_L4:
        if(j == 24581)
        {
            MtvAppPlaybackContextManager.getInstance().reset();
            stopSelf();
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        MtvUtilDebug.Low("MtvMiniModeService", "onStartCommand: entered ");
        classname = intent.getStringExtra("class");
        SecHardwareInterface.setmDNIeUIMode(1);
        playMiniMode();
        return super.onStartCommand(intent, i, j);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    public void playMiniMode()
    {
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext != null)
        {
            mMtvAppPlaybackContext.getComponents().getVideo().enable();
            IMtvOneSegVideoControl imtvonesegvideocontrol = mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface();
            if(imtvonesegvideocontrol != null)
                imtvonesegvideocontrol.registerSurface(mMiniTvSurface);
            mMtvAppPlaybackContext.getState().registerListener(this);
        }
        try
        {
            if(tvoutService.TvoutGetStatus() && !tvoutService.TvoutGetSuspendStatus())
                tvoutService.TvoutSetSuspendStatus(true);
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        setScreenRatio();
        registerIntents();
        if(mMtvAudMgr != null)
            mMtvAudMgr.setAvStreaming(true);
    }

    public void siwitchToNormalMode()
    {
        Intent intent = new Intent(mContext, com/samsung/sec/mtv/ui/liveplayer/MtvUiLivePlayer);
        MtvUtilDebug.Low("MtvMiniModeService", "Start Activity");
        intent.setFlags(0x14000000);
        mContext.startActivity(intent);
        stopSelf();
    }

    public static String classname = null;
    private Context mContext;
    private RelativeLayout mExitLayout;
    private Handler mHandler;
    private MtvUiMiniModeSurface mMiniTvSurface;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvUtilAudioManager mMtvAudMgr;
    private MtvPreferences mMtvPreferences;
    private BroadcastReceiver mReceiver;
    private View miniMainView;
    ITvoutService tvoutService;






}
