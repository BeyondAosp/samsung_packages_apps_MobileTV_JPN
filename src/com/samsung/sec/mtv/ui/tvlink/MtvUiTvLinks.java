// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.tvlink;

import android.app.Activity;
import android.app.ActivityManager;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegTVLink;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.provider.MtvBMLManager;
import com.samsung.sec.mtv.provider.MtvCProBMInfo;
import com.samsung.sec.mtv.ui.bml.IMtvUiBmlActivity;
import com.samsung.sec.mtv.ui.bml.MtvUiBmlSurfaceView;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.utility.MtvPreferences;
import java.util.List;

public class MtvUiTvLinks extends Activity
    implements IMtvAppPlaybackStateListener, IMtvAppProgramAttributeListener, IMtvUiBmlActivity, com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener
{

    public MtvUiTvLinks()
    {
        fragHandler = null;
        mLinks = null;
        mLink = null;
        mMtvAppPlaybackContext = null;
        mMtvPlayerOneSeg = null;
        mIndex = 0;
        mMtvPreferences = null;
    }

    public boolean isBmlFullView()
    {
        boolean flag;
        if(mBMLSurfaceView != null)
            flag = mBMLSurfaceView.IsBMLFullView();
        else
            flag = false;
        return flag;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(getLayoutInflater().inflate(0x7f030009, null));
        getWindow().addFlags(128);
        mBMLSurfaceView = (MtvUiBmlSurfaceView)findViewById(0x7f0a000a);
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        mMtvAppPlaybackContext.getState().registerListener(this);
        mMtvAppPlaybackContext.getAttribute().registerListener(this);
        mIndex = getIntent().getExtras().getInt("MtvSelectLink");
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
        mMtvAppPlaybackContext.getComponents().getBml().enable();
        fragHandler = new MtvUiFragHandler(getFragmentManager(), 2, 0x7f0a0026);
        if(bundle != null)
            fragHandler.fillFragHandlerData(bundle);
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onCreate(mMtvAppPlaybackContext, fragHandler);
        mMtvAppPlaybackContext.getState().registerListener(this);
        mMtvAppPlaybackContext.getAttribute().registerListener(this);
    }

    protected void onDestroy()
    {
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onDestroy();
        if(mMtvPlayerOneSeg != null)
        {
            mMtvPlayerOneSeg.stopTVLink(mMtvAppPlaybackContext);
            MtvURI mtvuri = new MtvURI(2, mMtvPreferences.getLatestPChannelFromVChannel());
            mMtvAppPlaybackContext.getState().setOp(20480);
            mMtvPlayerOneSeg.open(mMtvAppPlaybackContext, mtvuri);
        }
        mMtvAppPlaybackContext = null;
        super.onDestroy();
    }

    public void onFragEvent(int i, Object obj)
    {
    }

    public void onPause()
    {
        if(!isFinishing()) goto _L2; else goto _L1
_L1:
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onDestroy();
_L4:
        super.onPause();
        return;
_L2:
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onPause();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        j;
        JVM INSTR lookupswitch 3: default 36
    //                   24577: 36
    //                   24578: 36
    //                   24593: 37;
           goto _L1 _L1 _L1 _L2
_L1:
        return;
_L2:
        MtvUtilDebug.Error(TAG, "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
        if(!MtvUtilDebug.isReleaseMode())
        {
            Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
            toast.setGravity(119, 0, 0);
            toast.show();
        }
        sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onProgramAttributeUpdated(int i)
    {
    }

    protected void onResume()
    {
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        if(mBMLSurfaceView != null)
        {
            mBMLSurfaceView.onResume(mMtvAppPlaybackContext);
            mBMLSurfaceView.setBMLFullView(true);
        }
        if(mMtvTVlinkURI == null)
            mMtvTVlinkURI = new MtvOneSegTVLink();
        mLinks = MtvBMLManager.getAvailableCProBMInfoAll();
        if(mLinks != null)
            mLink = mLinks[mIndex];
        if(mLink != null && mMtvTVlinkURI != null)
        {
            mMtvTVlinkURI.origNWID = mLink.getOriginalNetworkID();
            mMtvTVlinkURI.transStreamID = mLink.getTransportStreamID();
            mMtvTVlinkURI.servID = mLink.getServiceID();
            mMtvTVlinkURI.compTag = 128;
            mMtvTVlinkURI.destURI = mLink.getDstURI();
            mMtvTVlinkURI.affilID = mLink.getAffiliationID();
        }
        mMtvAppPlaybackContext.getComponents().getBml().enable();
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        if(layoutparams.screenBrightness == 0.0F)
            layoutparams.screenBrightness = 0.1F;
        getWindow().setAttributes(layoutparams);
        mMtvPlayerOneSeg.startTVLink(mMtvAppPlaybackContext, mMtvTVlinkURI, this);
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onStart(fragHandler);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    public void onStop()
    {
        String s = null;
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onStop();
        ActivityManager activitymanager = (ActivityManager)getSystemService("activity");
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        if(s != null)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low(TAG, (new StringBuilder()).append("topActivityName = ").append(s).toString());
            if(s.contains("com.samsung.sec.mtv"));
        }
        super.onStop();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        MtvUtilDebug.Low(TAG, "onTouchEvent:entered");
        if(mBMLSurfaceView != null)
            mBMLSurfaceView.onTouchEvent(motionevent);
        return true;
    }

    public static String TAG = "MtvUiLinkBML";
    private static MtvUiBmlSurfaceView mBMLSurfaceView = null;
    private static MtvOneSegTVLink mMtvTVlinkURI = null;
    private MtvUiFragHandler fragHandler;
    private int mIndex;
    private MtvCProBMInfo mLink;
    private MtvCProBMInfo mLinks[];
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private IMtvAppPlayerOneSeg mMtvPlayerOneSeg;
    private MtvPreferences mMtvPreferences;

}
