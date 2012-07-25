// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.utility.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiStatusBarFrag extends MtvUiFrag
{

    public MtvUiStatusBarFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;-><init>()V");
        super();
        mViewStatusBar = null;
        mMainBarLayout = null;
        mImageViewSignalLevel = null;
        mImageViewBattery = null;
        mImageViewSleepTimer = null;
        mImageView5_1Channel = null;
        mTextViewClock = null;
        mCalendar = null;
        mTextViewChannelName = null;
        mTextViewProgramName = null;
        mProgramChannelDetail = "";
        mProgramName = "";
        mChannelName = "";
        mMtvPreferences = null;
    }

    public MtvUiStatusBarFrag(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;-><init>(I)V");
        super();
        mViewStatusBar = null;
        mMainBarLayout = null;
        mImageViewSignalLevel = null;
        mImageViewBattery = null;
        mImageViewSleepTimer = null;
        mImageView5_1Channel = null;
        mTextViewClock = null;
        mCalendar = null;
        mTextViewChannelName = null;
        mTextViewProgramName = null;
        mProgramChannelDetail = "";
        mProgramName = "";
        mChannelName = "";
        mMtvPreferences = null;
        fragment_mode = i;
    }

    private int getSignalQuality()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->getSignalQuality()I");
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        int i;
        if(mtvappplaybackcontext != null)
            i = mtvappplaybackcontext.getAttribute().getSignalLevel();
        else
            i = 0;
        return i;
    }

    private final CharSequence getSmallTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->getSmallTime()Ljava/lang/CharSequence;");
        SimpleDateFormat simpledateformat;
        if(DateFormat.is24HourFormat(getActivity().getApplicationContext()))
            simpledateformat = new SimpleDateFormat("H:mm");
        else
            simpledateformat = (SimpleDateFormat)SimpleDateFormat.getTimeInstance(3);
        return simpledateformat.format(mCalendar.getTime());
    }

    private void initializeUI(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->initializeUI(Landroid/view/View;)V");
        mMainBarLayout = (RelativeLayout)view.findViewById(0x7f0a00d7);
        mMainBarLayout.setBackgroundColor(0x7f060008);
        mImageViewSignalLevel = (ImageView)view.findViewById(0x7f0a00d9);
        mImageViewBattery = (ImageView)view.findViewById(0x7f0a00da);
        mImageViewSleepTimer = (ImageView)view.findViewById(0x7f0a00de);
        mImageView5_1Channel = (ImageView)view.findViewById(0x7f0a00dd);
        if(!MtvUtilAudioManager.getInstance(getActivity().getApplicationContext()).isHeadsetConnected())
        {
            mImageView5_1Channel.setVisibility(8);
            mMtvPreferences.setAudio51Enabled(false);
        }
        mTextViewClock = (TextView)view.findViewById(0x7f0a00db);
        mTextViewChannelName = (TextView)view.findViewById(0x7f0a00dc);
        mTextViewProgramName = (TextView)view.findViewById(0x7f0a00d8);
        onUpdate(100, Integer.valueOf(getSignalQuality()));
        setProgramChannelDetails(mProgramChannelDetail);
        update5_1Channel();
        updateSleepTimer();
        updateBattery();
        updateClock();
    }

    private void setProgramChannelDetails(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->setProgramChannelDetails(Ljava/lang/String;)V");
        String s1 = null;
        String s2 = null;
        if(s != null)
        {
            s.concat("\n");
            StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
            if(stringtokenizer.hasMoreTokens())
                s1 = stringtokenizer.nextToken();
            if(stringtokenizer.hasMoreTokens())
                s2 = stringtokenizer.nextToken();
            setStatusBarProgramDetails(s2);
            setStatusBarChannelDetails(s1);
        } else
        {
            setStatusBarProgramDetails(null);
            setStatusBarChannelDetails(null);
        }
    }

    private void setStatusBarChannelDetails(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->setStatusBarChannelDetails(Ljava/lang/String;)V");
        if(s != null)
            mChannelName = s;
        else
            mChannelName = "";
        if(mTextViewChannelName != null)
            mTextViewChannelName.setText(mChannelName);
    }

    private void setStatusBarProgramDetails(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->setStatusBarProgramDetails(Ljava/lang/String;)V");
        if(s != null)
            mProgramName = s;
        else
            mProgramName = "";
        if(mTextViewProgramName != null)
            mTextViewProgramName.setText(mProgramName);
    }

    private void update5_1Channel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->update5_1Channel()V");
        if(mImageView5_1Channel != null)
        {
            ImageView imageview = mImageView5_1Channel;
            int i;
            if(mMtvPreferences.isAudio51Enabled())
                i = 0;
            else
                i = 8;
            imageview.setVisibility(i);
        }
    }

    private void updateBattery()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->updateBattery()V");
        if(mImageViewBattery != null)
        {
            mImageViewBattery.setVisibility(0);
            mImageViewBattery.setBackgroundResource(MtvBatteryInfo.getBatteryDrawableResourceID());
        }
    }

    private void updateClock()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->updateClock()V");
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        if(mTextViewClock != null)
            mTextViewClock.setText(getSmallTime());
    }

    private void updateSignalLevel(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->updateSignalLevel(I)V");
        if(i < 0)
            i = 0;
        if(mImageViewSignalLevel != null)
        {
            mImageViewSignalLevel.setBackgroundResource(STATUS_BAR_RSRC_SIGNAL_LEVEL[i]);
            if(fragment_mode == 0)
                mImageViewSignalLevel.setVisibility(0);
            else
                mImageViewSignalLevel.setVisibility(8);
        }
    }

    private void updateSleepTimer()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->updateSleepTimer()V");
        int i = 0;
        boolean flag;
        if(mMtvPreferences.getAutoPowerOffTime() > 0)
            flag = true;
        else
            flag = false;
        if(mImageViewSleepTimer != null)
        {
            ImageView imageview = mImageViewSleepTimer;
            if(!flag)
                i = 8;
            imageview.setVisibility(i);
        }
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onAttach(Landroid/app/Activity;)V");
        MtvUtilDebug.Low("TAG", "onAttach");
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onCreate(Landroid/os/Bundle;)V");
        MtvUtilDebug.Low("MtvUiStatusBarFrag", "onCreate");
        super.onCreate(bundle);
        setRetainInstance(true);
        mCalendar = Calendar.getInstance(TimeZone.getDefault());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        MtvUtilDebug.Low("MtvUiStatusBarFrag", "onCreateView");
        mMtvPreferences = new MtvPreferences(getActivity());
        mViewStatusBar = layoutinflater.inflate(0x7f030025, viewgroup, false);
        mViewStatusBar.setOnTouchListener(new _cls1());
        initializeUI(mViewStatusBar);
        return mViewStatusBar;
    }

    public void onDestroyView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onDestroyView()V");
        MtvUtilDebug.Low("MtvUiStatusBarFrag", "onDestroyView");
        mMtvPreferences = null;
        super.onDestroyView();
    }

    public void onDetach()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onDetach()V");
        MtvUtilDebug.Low("MtvUiStatusBarFrag", "onDetach");
        super.onDetach();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiStatusBarFrag", (new StringBuilder()).append("onUpdate : what = ").append(i).toString());
        i;
        JVM INSTR lookupswitch 7: default 104
    //                   100: 105
    //                   101: 157
    //                   102: 218
    //                   103: 250
    //                   104: 257
    //                   105: 264
    //                   327: 271;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return;
_L2:
        int j = 0;
        if(obj != null)
            j = ((Integer)obj).intValue();
        MtvUtilDebug.Low("MtvUiStatusBarFrag", (new StringBuilder()).append("onUpdate: UPDATE_ANNOUNCE_SIGNAL : level= ").append(j).toString());
        updateSignalLevel(j);
        continue; /* Loop/switch isn't completed */
_L3:
        if(obj != null)
            mProgramChannelDetail = (String)obj;
        else
            mProgramChannelDetail = "";
        MtvUtilDebug.Low("MtvUiStatusBarFrag", (new StringBuilder()).append("onUpdate: UPDATE_NOW_PROGRAM : mProgramChannelDetail= ").append(mProgramChannelDetail).toString());
        setProgramChannelDetails(mProgramChannelDetail);
        continue; /* Loop/switch isn't completed */
_L4:
        if(getActivity().getResources().getConfiguration().orientation == 2)
        {
            updateBattery();
            updateSignalLevel(getSignalQuality());
        }
        continue; /* Loop/switch isn't completed */
_L5:
        updateClock();
        continue; /* Loop/switch isn't completed */
_L6:
        update5_1Channel();
        continue; /* Loop/switch isn't completed */
_L7:
        updateSleepTimer();
        continue; /* Loop/switch isn't completed */
_L8:
        if(mViewStatusBar != null)
            mViewStatusBar.setVisibility(8);
        else
            MtvUtilDebug.Low("MtvUiStatusBarFrag", "mLayoutView is null");
        if(true) goto _L1; else goto _L9
_L9:
    }

    private static final int STATUS_BAR_RSRC_SIGNAL_LEVEL[];
    private int fragment_mode;
    private Calendar mCalendar;
    private String mChannelName;
    private ImageView mImageView5_1Channel;
    private ImageView mImageViewBattery;
    private ImageView mImageViewSignalLevel;
    private ImageView mImageViewSleepTimer;
    private RelativeLayout mMainBarLayout;
    private MtvPreferences mMtvPreferences;
    private String mProgramChannelDetail;
    private String mProgramName;
    private TextView mTextViewChannelName;
    private TextView mTextViewClock;
    private TextView mTextViewProgramName;
    private View mViewStatusBar;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;-><clinit>()V");
        int ai[] = new int[5];
        ai[0] = 0x7f020126;
        ai[1] = 0x7f020127;
        ai[2] = 0x7f020128;
        ai[3] = 0x7f020129;
        ai[4] = 0x7f02012a;
        STATUS_BAR_RSRC_SIGNAL_LEVEL = ai;
    }

    private class _cls1
        implements android.view.View.OnTouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag$1;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
            return true;
        }

        final MtvUiStatusBarFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiStatusBarFrag;)V");
            this$0 = MtvUiStatusBarFrag.this;
            super();
        }
    }

}
