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
import android.util.Log;
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$OnExitTouchListener;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
            super.onTouch(view, motionevent);
            motionevent.getAction();
            JVM INSTR tableswitch 0 1: default 40
        //                       0 42
        //                       1 52;
               goto _L1 _L2 _L3
_L1:
            return true;
_L2:
            MtvUtilDebug.Low("MtvMiniModeService", "EXIT BUTTON : ACTION_DOWN");
            continue; /* Loop/switch isn't completed */
_L3:
            MtvUtilDebug.Low("MtvMiniModeService", "EXIT BUTTON : ACTION_DOWN");
            if(Log.d(MtvMiniModeService.this) != null)
                Log.d(MtvMiniModeService.this).setVisibility(8);
            closeOneseg();
            stopSelf();
            if(true) goto _L1; else goto _L4
_L4:
        }

        final MtvMiniModeService this$0;

        OnExitTouchListener()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$OnExitTouchListener;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)V");
            this$0 = MtvMiniModeService.this;
            super(MtvMiniModeService.this);
        }
    }

    class miniModeSurfaceOnTouchListener extends com.sec.android.app.minimode.MiniModeService.TouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            int i;
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$miniModeSurfaceOnTouchListener;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
            super.onTouch(view, motionevent);
            i = motionevent.getAction();
            motionevent.getEventTime() - motionevent.getDownTime();
            i;
            JVM INSTR tableswitch 0 2: default 60
        //                       0 62
        //                       1 216
        //                       2 113;
               goto _L1 _L2 _L3 _L4
_L1:
            return true;
_L2:
            MtvUtilDebug.Low("MtvMiniModeService", "ACTION_DOWN");
            mDownTime = (int)motionevent.getEventTime();
            mXdown = motionevent.getRawX();
            mYdown = motionevent.getRawY();
            Log.d(MtvMiniModeService.this).sendEmptyMessageDelayed(11, 500L);
            continue; /* Loop/switch isn't completed */
_L4:
            MtvUtilDebug.Low("MtvMiniModeService", "ACTION_MOVE");
            float f2 = Math.abs(mXdown - motionevent.getRawX());
            float f3 = Math.abs(mYdown - motionevent.getRawY());
            if((long)(int)(motionevent.getEventTime() - (long)mDownTime) > 500L && true)
                if(f2 < (float)ViewConfiguration.getTouchSlop() && f3 < (float)ViewConfiguration.getTouchSlop())
                    MtvUtilDebug.Low("MtvMiniModeService", "showExitDialog");
                else
                    Log.d(MtvMiniModeService.this).removeMessages(11);
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
                if(Log.d(MtvMiniModeService.this).getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    Log.d(MtvMiniModeService.this).removeMessages(11);
                    if(Log.d(MtvMiniModeService.this) != null)
                        Log.d(MtvMiniModeService.this).setVisibility(8);
                    siwitchToNormalMode();
                } else
                if(Log.d(MtvMiniModeService.this, Log.d(MtvMiniModeService.this)))
                    Toast.makeText(Log.d(MtvMiniModeService.this), 0x7f07009e, 0).show();
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$miniModeSurfaceOnTouchListener;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;-><init>()V");
        super();
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$1;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V");
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
                        Toast.makeText(Log.d(MtvMiniModeService.this), 0x7f0700a1, 0).show();
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$1;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)V");
                this$0 = MtvMiniModeService.this;
                super();
            }
        };
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$2;->handleMessage(Landroid/os/Message;)V");
                message.what;
                JVM INSTR tableswitch 11 12: default 36
            //                           11 37
            //                           12 71;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                removeMessages(11);
                Log.d(MtvMiniModeService.this).setVisibility(0);
                sendMessageDelayed(obtainMessage(12), 10000L);
                continue; /* Loop/switch isn't completed */
_L3:
                Log.d(MtvMiniModeService.this).setVisibility(8);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvMiniModeService this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService$2;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)V");
                this$0 = MtvMiniModeService.this;
                super();
            }
        };
    }

    private boolean checkIsCalling(Context context)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->checkIsCalling(Landroid/content/Context;)Z");
        flag = true;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->registerIntents()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->setScreenRatio()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->unRegisterIntents()V");
        MtvUtilDebug.Low("MtvMiniModeService", "unregister()");
        unregisterReceiver(mReceiver);
    }

    public void closeOneseg()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->closeOneseg()V");
        mMtvAudMgr.removeCallStateListener();
        MtvUtilAppService.forceresetMtvVisibiltySettings();
        stopService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService));
        MtvAppPlayerOneSeg.getInstance().delete(MtvAppPlaybackContextManager.getInstance().getCurrentContext());
    }

    protected boolean onClose(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onClose(I)Z");
        i;
        JVM INSTR lookupswitch 2: default 36
    //                   1: 40
    //                   6: 40;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onCreate()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onDestroy()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onPlayerNotification(III)V");
        i;
        JVM INSTR lookupswitch 3: default 44
    //                   20483: 59
    //                   20484: 45
    //                   20495: 94;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onStartCommand(Landroid/content/Intent;II)I");
        MtvUtilDebug.Low("MtvMiniModeService", "onStartCommand: entered ");
        classname = intent.getStringExtra("class");
        SecHardwareInterface.setmDNIeUIMode(1);
        playMiniMode();
        return super.onStartCommand(intent, i, j);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->onStateChanged(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;)V");
    }

    public void playMiniMode()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->playMiniMode()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->siwitchToNormalMode()V");
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

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;-><clinit>()V");
    }


/*
    static Context access$000(MtvMiniModeService mtvminimodeservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->access$000(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)Landroid/content/Context;");
        return mtvminimodeservice.mContext;
    }

*/


/*
    static RelativeLayout access$100(MtvMiniModeService mtvminimodeservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->access$100(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)Landroid/widget/RelativeLayout;");
        return mtvminimodeservice.mExitLayout;
    }

*/


/*
    static Handler access$200(MtvMiniModeService mtvminimodeservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->access$200(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)Landroid/os/Handler;");
        return mtvminimodeservice.mHandler;
    }

*/


/*
    static MtvAppPlaybackContext access$300(MtvMiniModeService mtvminimodeservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->access$300(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvminimodeservice.mMtvAppPlaybackContext;
    }

*/


/*
    static boolean access$400(MtvMiniModeService mtvminimodeservice, Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;->access$400(Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeService;Landroid/content/Context;)Z");
        return mtvminimodeservice.checkIsCalling(context);
    }

*/
}
