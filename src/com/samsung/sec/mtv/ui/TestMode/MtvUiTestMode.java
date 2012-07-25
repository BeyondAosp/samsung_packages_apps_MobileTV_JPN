// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.TestMode;

import android.app.Activity;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegSignal;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.ui.configration.MtvUiConfigration;
import com.samsung.sec.mtv.utility.*;
import java.util.ArrayList;
import java.util.List;

public class MtvUiTestMode extends Activity
    implements android.widget.AdapterView.OnItemClickListener, IMtvAppPlaybackStateListener, IMtvAppProgramAttributeListener
{
    public class ChannelAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = mInflater.inflate(mLayoutId, null);
            int j = ((Integer)mContainer.get(i)).intValue();
            view1.setTag(Integer.valueOf(j));
            ((TextView)view1.findViewById(0x7f0a010b)).setText((new StringBuilder()).append("Channel ").append(j).toString());
            return view1;
        }

        private final List mContainer;
        private final LayoutInflater mInflater;
        private final int mLayoutId;
        final MtvUiTestMode this$0;

        public ChannelAdapter(Context context, int i, List list)
        {
            this$0 = MtvUiTestMode.this;
            super(context, i, list);
            mContainer = list;
            mLayoutId = i;
            mInflater = (LayoutInflater)getSystemService("layout_inflater");
        }
    }

    private final class RunnableUpdateText
        implements Runnable
    {

        public void run()
        {
            mView.setText(mString);
        }

        private final String mString;
        private final TextView mView;
        final MtvUiTestMode this$0;

        public RunnableUpdateText(TextView textview, String s)
        {
            this$0 = MtvUiTestMode.this;
            super();
            mView = textview;
            mString = s;
        }
    }


    public MtvUiTestMode()
    {
        mVideoSurfaceView = null;
        mChannelInfoTextView = null;
        mSignalInfoTextView = null;
        mSignalDetailsTextView = null;
        mTestmodeChannelListView = null;
        mTestmodeChannelListAdapter = null;
        bFromFactoryTest = false;
        bFactoryTestSuccess = false;
        mSavedVolumeLevel = 0;
        mChannelNumber = 27;
        mDebugSetting = null;
        mMtvPlayerOneSeg = null;
        mMtvAppPlaybackContext = null;
        mMtvAudMgr = null;
        haveToFinish = false;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                if(message.what != 0) goto _L2; else goto _L1
_L1:
                if(!MtvUiTestMode.mShowSignalState) goto _L4; else goto _L3
_L3:
                MtvOneSegSignal mtvonesegsignal = getSignalValue();
                MtvUtilDebug.Mid("MtvUiTestMode", (new StringBuilder()).append("handleMessage getSignalValue ").append(mtvonesegsignal).toString());
                View view = findViewById(0x7f0a0032);
                if(view != null)
                {
                    int i;
                    if(mtvonesegsignal != null)
                    {
                        if((long)mtvonesegsignal.getRSSI() >= -88L)
                            view.setBackgroundColor(0xff0000ff);
                        else
                            view.setBackgroundColor(0xffff0000);
                        i = mtvonesegsignal.getSignalQuality();
                        mSignalDetailsTextView.setText(getSignalValue(mtvonesegsignal));
                    } else
                    {
                        view.setBackgroundColor(0xff444444);
                        i = 0;
                        mSignalDetailsTextView.setText(" ");
                    }
                    if(mSignalInfoTextView != null)
                        mSignalInfoTextView.setText((new StringBuilder()).append("Signal[0.. 4]=").append(i).toString());
                } else
                {
                    MtvUtilDebug.Mid("MtvUiTestMode", "handleMessage : TEST_MODE_MSG_UPDATE_SIGNAL signalLayout invalid ");
                }
_L2:
                return;
_L4:
                if(mSignalDetailsTextView != null)
                    mSignalDetailsTextView.setText(" ");
                if(true) goto _L2; else goto _L5
_L5:
            }

            final MtvUiTestMode this$0;

            
            {
                this$0 = MtvUiTestMode.this;
                super();
            }
        };
    }

    private void changeLayout(boolean flag)
    {
        mCurrentLayoutState = flag;
        if(flag)
        {
            setRequestedOrientation(0);
            setContentView(0x7f030008);
            mChannelInfoTextView = (TextView)findViewById(0x7f0a0033);
            mSignalInfoTextView = (TextView)findViewById(0x7f0a0034);
            mSignalDetailsTextView = (TextView)findViewById(0x7f0a0035);
            mVideoSurfaceView = (SurfaceView)findViewById(0x7f0a0031);
            mTestmodeChannelListView = null;
        } else
        {
            setRequestedOrientation(1);
            setContentView(0x7f030007);
            ArrayList arraylist = new ArrayList();
            mChannelInfoTextView = (TextView)findViewById(0x7f0a0033);
            mSignalInfoTextView = (TextView)findViewById(0x7f0a0034);
            mSignalDetailsTextView = (TextView)findViewById(0x7f0a0035);
            mVideoSurfaceView = (SurfaceView)findViewById(0x7f0a0031);
            mTestmodeChannelListView = (ListView)findViewById(0x7f0a0036);
            mTestmodeChannelListAdapter = new ChannelAdapter(this, 0x7f03002a, arraylist);
            for(int i = 13; i <= 62; i++)
                arraylist.add(Integer.valueOf(i));

            mTestmodeChannelListView.setOnItemClickListener(this);
            mTestmodeChannelListView.setAdapter(mTestmodeChannelListAdapter);
            mTestmodeChannelListView.setSelection(14);
        }
        runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel[").append(mChannelNumber).append("]").toString()));
    }

    private MtvOneSegSignal getSignalValue()
    {
        MtvOneSegSignal mtvonesegsignal;
        if(mMtvAppPlaybackContext != null)
            mtvonesegsignal = mMtvAppPlaybackContext.getAttribute().getSignalStatistics();
        else
            mtvonesegsignal = null;
        return mtvonesegsignal;
    }

    private String getSignalValue(MtvOneSegSignal mtvonesegsignal)
    {
        return "CN[".concat(Long.toString(mtvonesegsignal.getCN())).concat("] RSSI[").concat(Long.toString(mtvonesegsignal.getRSSI())).concat("] BER[").concat(Long.toString(mtvonesegsignal.getBER())).concat("]");
    }

    private void invalidate()
    {
        View view = findViewById(0x7f0a0032);
        if(view != null)
            view.setBackgroundColor(0xff444444);
        runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, ""));
        mSignalInfoTextView.setText("");
        mSignalDetailsTextView.setText("");
        mVideoSurfaceView.setVisibility(4);
        mVideoSurfaceView.setVisibility(0);
    }

    private void releaseMtvPlayer()
    {
        if(mMtvAppPlaybackContext != null)
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext != null)
        {
            MtvAppPlaybackContextManager.getInstance().reset();
            mMtvPlayerOneSeg = null;
            mMtvAppPlaybackContext = null;
        }
    }

    private void showConfigrationSettings()
    {
        Intent intent = new Intent();
        intent.setClass(this, com/samsung/sec/mtv/ui/configration/MtvUiConfigration);
        MtvUtilDebug.Low("MtvUiTestMode", "Start Activity - MtvUiConfigration");
        startActivity(intent);
    }

    private void showSignal(boolean flag)
    {
        mHandler.sendEmptyMessage(0);
    }

    private void stopOneSegPlayer()
    {
    }

    public void onBackPressed()
    {
        MtvUtilDebug.Low("MtvUiTestMode", "onBackPressed");
        if(bFromFactoryTest)
            if(bFactoryTestSuccess)
                setResult(-1);
            else
                setResult(0);
        MtvUtilDebug.Mid("MtvUiTestMode", (new StringBuilder()).append("bFactoryTestSuccess -").append(bFactoryTestSuccess).append(" bFromFactoryTest - ").append(bFromFactoryTest).toString());
        if(bFromFactoryTest)
            mMtvAudMgr.setVolumeLevel(mSavedVolumeLevel);
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        mMtvAppPlaybackContext.getState().registerListener(this);
        if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE || haveToFinish) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Mid("MtvUiTestMode", (new StringBuilder()).append("Exiting - OneSeg already initialized so deleting OneSeg and waiting till delete callback  ").append(mMtvAppPlaybackContext.getState().getState()).append("  ").append(mMtvAppPlaybackContext.getState().getOp()).toString());
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        if(mMtvAppPlaybackContext == null || mMtvAppPlaybackContext.getState().getOp() != 20495) goto _L4; else goto _L3
_L3:
        MtvUtilDebug.Low("MtvUiTestMode", "cannot issue delete as already one delete operation is going on...");
_L6:
        return;
_L4:
        if(mMtvPlayerOneSeg != null)
            mMtvPlayerOneSeg.delete(mMtvAppPlaybackContext);
        continue; /* Loop/switch isn't completed */
_L2:
        if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getOp() == 20481)
        {
            MtvUtilDebug.Low("MtvUiTestMode", "Create in Progress..Cannot finish...");
            haveToFinish = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiTestMode", "finish()-ed");
            finish();
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        changeLayout(mCurrentLayoutState);
        mDebugSetting = MtvUtilDebug.getDebugInstance();
        mDebugSetting.loadDbgInfoFromFile();
        MtvUtilDebug.Low("MtvUiTestMode", "onCreate");
        bFromFactoryTest = getIntent().getBooleanExtra("fromFactoryTest", false);
        bFactoryTestSuccess = false;
        mConfigSettingControl = new MtvUtilConfigSettingControl();
        mConfigSetting = mConfigSettingControl.getConfigFromFile();
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
        mMtvAppPlaybackContext.getState().registerListener(this);
        if(bundle == null && mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
        {
            boolean flag = mMtvPlayerOneSeg.create(mMtvAppPlaybackContext, null);
            MtvUtilDebug.Low("MtvUiTestMode", (new StringBuilder()).append("onCreate first create : ").append(flag).toString());
            if(!flag)
            {
                boolean flag1 = mMtvPlayerOneSeg.create(mMtvAppPlaybackContext, null);
                MtvUtilDebug.Low("MtvUiTestMode", (new StringBuilder()).append("onCreate second create : ").append(flag1).toString());
            }
        } else
        {
            mMtvAppPlaybackContext.getComponents().getVideo().enable();
            mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface().registerSurface(mVideoSurfaceView);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
        }
        if(bFromFactoryTest)
        {
            mSavedVolumeLevel = mMtvAudMgr.getVolumeLevel();
            mMtvAudMgr.setVolumeLevel(15);
        }
        getWindow().addFlags(8192);
    }

    protected void onDestroy()
    {
        MtvUtilDebug.Low("MtvUiTestMode", "onDestroy");
        super.onDestroy();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        mChannelNumber = ((Integer)view.getTag()).intValue();
        MtvUtilDebug.Low("MtvUiTestMode", (new StringBuilder()).append("onItemClick pos=").append(i).append(", channel=").append(mChannelNumber).toString());
        MtvURI mtvuri = new MtvURI(4, mChannelNumber);
        invalidate();
        runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel [").append(mChannelNumber).append("]").append(" searching started").toString()));
        if(mMtvPlayerOneSeg != null)
            mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        MtvUtilDebug.Mid("MtvUiTestMode", (new StringBuilder()).append("onMenuItemSelected : ").append(menuitem.getItemId()).toString());
        menuitem.getItemId();
        JVM INSTR tableswitch 0 4: default 72
    //                   0 79
    //                   1 86
    //                   2 113
    //                   3 135
    //                   4 190;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return super.onMenuItemSelected(i, menuitem);
_L2:
        stopOneSegPlayer();
        continue; /* Loop/switch isn't completed */
_L3:
        if(mShowSignalState)
            mShowSignalState = false;
        else
            mShowSignalState = true;
        showSignal(mShowSignalState);
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mCurrentLayoutState)
            changeLayout(true);
        else
            changeLayout(false);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mConfigSetting.iTsFileSimul)
            mConfigSetting.iTsFileSimul = false;
        else
            mConfigSetting.iTsFileSimul = true;
        if(!mConfigSettingControl.setConfigToFile(mConfigSetting))
            MtvUtilDebug.Mid("MtvUiTestMode", "setConfigToFile false!");
        continue; /* Loop/switch isn't completed */
_L6:
        showConfigrationSettings();
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   20481: 61
    //                   20482: 301
    //                   20483: 1023
    //                   20484: 580
    //                   20494: 919
    //                   20495: 988;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L9:
        return;
_L2:
        MtvUtilDebug.Low("MtvUiTestMode", "onPlayerNotification ...:CMD_CREATE");
        switch(j)
        {
        case 24580: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("OneSeg Error [").append(j).append("]").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = false;
            break;

        case 24581: 
            if(haveToFinish)
            {
                mHandler.post(new Runnable() {

                    public void run()
                    {
                        if(mMtvPlayerOneSeg != null)
                            mMtvPlayerOneSeg.delete(mMtvAppPlaybackContext);
                    }

                    final MtvUiTestMode this$0;

            
            {
                this$0 = MtvUiTestMode.this;
                super();
            }
                });
            } else
            {
                mMtvAppPlaybackContext.getComponents().getVideo().enable();
                mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface().registerSurface(mVideoSurfaceView);
                mMtvAppPlaybackContext.getAttribute().registerListener(this);
                MtvURI mtvuri = new MtvURI(4, mChannelNumber);
                runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel[").append(mChannelNumber).append("] searching started").toString()));
                mMtvPlayerOneSeg.open(mMtvAppPlaybackContext, mtvuri);
            }
            break;
        }
_L1:
        if(true) goto _L9; else goto _L8
_L8:
_L3:
        MtvUtilDebug.Low("MtvUiTestMode", "onPlayerNotification ...:CMD_OPEN");
        switch(j)
        {
        case 24580: 
            MtvUtilDebug.Low("MtvUiTestMode", "onPlayerNotification ...:CMD_OPEN - STAT_FAILURE");
            MtvUtilDebug.Low("MtvUiTestMode", "Channel searching failed...");
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel [").append(k).append("] searching failed").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = false;
            break;

        case 24581: 
            MtvUtilDebug.Low("MtvUiTestMode", "***oneseg open completed");
            mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface().registerSurface(mVideoSurfaceView);
            break;

        case 24583: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel searching ").append(k).append("%").toString()));
            break;

        case 24584: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, "Channel searching done"));
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel [").append(mChannelNumber).append("]").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = true;
            break;
        }
        if(true) goto _L9; else goto _L10
_L10:
_L5:
        MtvUtilDebug.Low("MtvUiTestMode", "onPlayerNotification ...:CMD_PLAY");
        switch(j)
        {
        case 24580: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel Play Error [").append(j).append("]").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = false;
            break;

        case 24582: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel[").append(mChannelNumber).append("] buffering start").toString()));
            break;

        case 24583: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel[").append(mChannelNumber).append("] buffering ").append(k).append("%").toString()));
            break;

        case 24584: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel[").append(mChannelNumber).append("]").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = true;
            break;

        case 24593: 
            MtvUtilDebug.Error("MtvUiTestMode", "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
            if(!MtvUtilDebug.isReleaseMode())
            {
                Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
                toast.setGravity(119, 0, 0);
                toast.show();
                mHandler.post(new Runnable() {

                    public void run()
                    {
                        if(mMtvPlayerOneSeg != null)
                            mMtvPlayerOneSeg.delete(mMtvAppPlaybackContext);
                    }

                    final MtvUiTestMode this$0;

            
            {
                this$0 = MtvUiTestMode.this;
                super();
            }
                });
            }
            break;
        }
        if(true) goto _L9; else goto _L11
_L11:
_L6:
        MtvUtilDebug.Low("MtvUiTestMode", (new StringBuilder()).append("onPlayerNotification ...:CMD_CLOSE: Status-").append(j).toString());
        switch(j)
        {
        case 24581: 
            if(mMtvAppPlaybackContext != null)
                mMtvAppPlaybackContext.getAttribute().registerListener(this);
            break;
        }
        if(true) goto _L9; else goto _L12
_L12:
_L7:
        MtvUtilDebug.Low("MtvUiTestMode", "onPlayerNotification ...:CMD_DELETE");
        if(j == 24581)
            mHandler.post(new Runnable() {

                public void run()
                {
                    releaseMtvPlayer();
                    finish();
                }

                final MtvUiTestMode this$0;

            
            {
                this$0 = MtvUiTestMode.this;
                super();
            }
            });
          goto _L9
_L4:
        MtvUtilDebug.Low("MtvUiTestMode", (new StringBuilder()).append("onPlayerNotification ...:CMD_STATUS_UPDT Status-").append(j).toString());
        switch(j)
        {
        case 24588: 
            runOnUiThread(new RunnableUpdateText(mChannelInfoTextView, (new StringBuilder()).append("Channel searching failed [").append(j).append("]").toString()));
            if(bFromFactoryTest)
                bFactoryTestSuccess = false;
            break;
        }
        if(true) goto _L9; else goto _L13
_L13:
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.clear();
        menu.add(0, 0, 0, "stop");
        if(mShowSignalState)
            menu.add(0, 1, 0, "Hide OneSeg Signal");
        else
            menu.add(0, 1, 0, "Show OneSeg Signal");
        if(!mCurrentLayoutState)
            menu.add(0, 2, 0, "Horizontal");
        else
            menu.add(0, 2, 0, "Vertical");
        if(mConfigSetting.iTsFileSimul)
            menu.add(0, 3, 0, "Simul mode Off");
        else
            menu.add(0, 3, 0, "Simul mode On");
        menu.add(0, 4, 0, "Configration");
        return super.onPrepareOptionsMenu(menu);
    }

    public void onProgramAttributeUpdated(int i)
    {
        i;
        JVM INSTR tableswitch 16 16: default 20
    //                   16 21;
           goto _L1 _L2
_L1:
        return;
_L2:
        showSignal(mShowSignalState);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    private static boolean mCurrentLayoutState = false;
    private static boolean mShowSignalState = true;
    private boolean bFactoryTestSuccess;
    private boolean bFromFactoryTest;
    private boolean haveToFinish;
    private TextView mChannelInfoTextView;
    private int mChannelNumber;
    private MtvUtilConfigSetting mConfigSetting;
    private MtvUtilConfigSettingControl mConfigSettingControl;
    private MtvUtilDebug mDebugSetting;
    private Handler mHandler;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvUtilAudioManager mMtvAudMgr;
    private IMtvAppPlayerOneSeg mMtvPlayerOneSeg;
    private int mSavedVolumeLevel;
    private TextView mSignalDetailsTextView;
    private TextView mSignalInfoTextView;
    private ListAdapter mTestmodeChannelListAdapter;
    private ListView mTestmodeChannelListView;
    private SurfaceView mVideoSurfaceView;









}
