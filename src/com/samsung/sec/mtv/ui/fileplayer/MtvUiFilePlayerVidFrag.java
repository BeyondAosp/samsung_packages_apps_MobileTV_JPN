// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.fileplayer;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.utility.*;

// Referenced classes of package com.samsung.sec.mtv.ui.fileplayer:
//            MtvUiFilePlayer

public class MtvUiFilePlayerVidFrag extends MtvUiFrag
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{

    public MtvUiFilePlayerVidFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;-><init>()V");
        super();
        mProgressBarArea = null;
        mPlayerSeekBar = null;
        mVolumeButton = null;
        mVolumeMuteButton = null;
        mVolumeText = null;
        mMtvAudMgr = null;
        mPlayPauseButton = null;
        mPrevButton = null;
        mNextButton = null;
        mTotalDurationInSecond = 0;
        TRICKMODESPEED_NONE = 0;
        TRICKMODESPEED_2x = 1;
        TRICKMODESPEED_10x = 2;
        mbIsTouching = false;
        isLocked = false;
        mPlayerSeekBarChangeListener = new _cls1();
    }

    public MtvUiFilePlayerVidFrag(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;-><init>(I)V");
        boolean flag = true;
        this();
        if(i != flag)
            flag = false;
        isLocked = flag;
    }

    private int getDurationInSeconds(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->getDurationInSeconds(I)I");
        int j;
        if(i < 1000)
            j = 1;
        else
            j = (i + 900) / 1000;
        return j;
    }

    private void initializeUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->initializeUI()V");
        int i = mFilePlayer.getFileTotalTime();
        int j = mFilePlayer.getCurrentFileTime();
        int k = getDurationInSeconds(i);
        mTotalDurationInSecond = k;
        setTextFileEndtime(k);
        if(j > 0)
            setTextFilePlayingtime(j);
        else
            setTextFilePlayingtime(0);
        mPlayerSeekBar.setMax(mTotalDurationInSecond);
        mPlayerSeekBar.setEnabled(false);
        mPlayerSeekBar.setProgress(j);
        mPlayerSeekBar.bringToFront();
        if(mVolumeButton != null)
        {
            mVolumeButton.setOnClickListener(this);
            mVolumeButton.setOnLongClickListener(this);
        }
        if(mVolumeMuteButton != null)
        {
            mVolumeMuteButton.setOnClickListener(this);
            mVolumeMuteButton.setOnLongClickListener(this);
        }
        if(mVolumeText != null)
        {
            mVolumeText.setOnClickListener(this);
            mVolumeText.setOnLongClickListener(this);
        }
        mPrevButton.setOnClickListener(this);
        mPrevButton.setOnLongClickListener(this);
        mNextButton.setOnClickListener(this);
        mNextButton.setOnLongClickListener(this);
        mPlayPauseButton.setOnClickListener(this);
        mPlayPauseButton.setEnabled(false);
        mPlayPauseButton.setBackgroundResource(0x7f0200d5);
        setMainControlByPlayback(false);
        toggleVolumeButton();
        mListener.onFragEvent(201, Integer.valueOf(mPreferences.getDisplaySize()));
    }

    private void setInitialMainControl()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->setInitialMainControl()V");
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mFilePlayer.getPlaybackState();
        mPrevButton.setEnabled(false);
        mNextButton.setEnabled(false);
        if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state)
        {
            mPlayPauseButton.setEnabled(false);
            mPlayPauseButton.setBackgroundResource(0x7f0200d5);
        }
        if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state)
        {
            mPlayPauseButton.setEnabled(false);
            mPlayPauseButton.setBackgroundResource(0x7f0200db);
        }
        int i = getDurationInSeconds(mFilePlayer.getFileTotalTime());
        mTotalDurationInSecond = i;
        setTextFileEndtime(i);
        setTextFilePlayingtime(0);
        mPlayerSeekBar.setMax(mTotalDurationInSecond);
        mPlayerSeekBar.setEnabled(false);
        mPlayerSeekBar.setProgress(0);
    }

    private void setMainControlByPlayback(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->setMainControlByPlayback(Z)V");
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mFilePlayer.getPlaybackState();
        int i = mFilePlayer.getPlayerCommand();
        boolean flag1 = true;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        MtvUtilDebug.Low("MtvUiFilePlayerVidFrag", (new StringBuilder()).append("setMainControlByPlayback: playbackState[").append(state).append("] playerCommand[").append(i).append("]").toString());
        if(!flag)
        {
            if(20480 == i)
            {
                if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state)
                    flag1 = true;
                else
                if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state)
                {
                    flag2 = true;
                    flag4 = true;
                }
            } else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state && 20489 == i)
            {
                flag1 = true;
                flag3 = true;
            } else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state && 20490 == i)
            {
                flag2 = true;
                flag3 = true;
            } else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state && 20491 == i)
            {
                flag2 = true;
                flag4 = true;
            }
            mPrevButton.setEnabled(true);
            mNextButton.setEnabled(true);
            if(flag4)
                mPlayerSeekBar.setEnabled(false);
            else
                mPlayerSeekBar.setEnabled(true);
            if(flag1)
                if(flag3)
                {
                    mPlayPauseButton.setEnabled(false);
                    mPlayPauseButton.setBackgroundResource(0x7f0200d5);
                } else
                {
                    mPlayPauseButton.setEnabled(true);
                    mPlayPauseButton.setBackgroundResource(0x7f0200d5);
                    if(flag4)
                        mPlayerSeekBar.setEnabled(false);
                    else
                        mPlayerSeekBar.setEnabled(true);
                }
            if(flag2)
                if(flag3)
                {
                    mPlayPauseButton.setEnabled(false);
                    mPlayPauseButton.setBackgroundResource(0x7f0200db);
                } else
                {
                    mPlayPauseButton.setEnabled(true);
                    mPlayPauseButton.setBackgroundResource(0x7f0200db);
                    if(flag4)
                        mPlayerSeekBar.setEnabled(false);
                    else
                        mPlayerSeekBar.setEnabled(true);
                }
        } else
        {
            mPlayPauseButton.setBackgroundResource(0x7f0200db);
            if(mPlayerSeekBar != null)
                mPlayerSeekBar.setEnabled(false);
        }
    }

    private void setTextFileEndtime(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->setTextFileEndtime(I)V");
        if(i != -1)
        {
            if(i < 3600)
            {
                TextView textview1 = mPlayingTotalTimeTextView;
                Object aobj1[] = new Object[3];
                aobj1[0] = Integer.valueOf(i / 3600);
                aobj1[1] = Integer.valueOf(i / 60);
                aobj1[2] = Integer.valueOf(i % 60);
                textview1.setText(String.format("%02d:%02d:%02d", aobj1));
            } else
            {
                int j = i / 60;
                TextView textview = mPlayingTotalTimeTextView;
                Object aobj[] = new Object[3];
                aobj[0] = Integer.valueOf(i / 3600);
                aobj[1] = Integer.valueOf(j % 60);
                aobj[2] = Integer.valueOf(i % 60);
                textview.setText(String.format("%02d:%02d:%02d", aobj));
            }
            mPlayingTotalTimeTextView.invalidate();
        }
    }

    private void setTextFilePlayingtime(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->setTextFilePlayingtime(I)V");
        if(mPlayingTimeTextView == null) goto _L2; else goto _L1
_L1:
        if(i >= 3600 || i > mTotalDurationInSecond) goto _L4; else goto _L3
_L3:
        TextView textview1 = mPlayingTimeTextView;
        Object aobj1[] = new Object[3];
        aobj1[0] = Integer.valueOf(i / 3600);
        aobj1[1] = Integer.valueOf(i / 60);
        aobj1[2] = Integer.valueOf(i % 60);
        textview1.setText(String.format("%02d:%02d:%02d", aobj1));
_L6:
        mPlayingTimeTextView.invalidate();
_L2:
        return;
_L4:
        int j = i / 60;
        if(i <= mTotalDurationInSecond)
        {
            TextView textview = mPlayingTimeTextView;
            Object aobj[] = new Object[3];
            aobj[0] = Integer.valueOf(i / 3600);
            aobj[1] = Integer.valueOf(j % 60);
            aobj[2] = Integer.valueOf(i % 60);
            textview.setText(String.format("%02d:%02d:%02d", aobj));
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void toggleVolumeButton()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->toggleVolumeButton()V");
        if(mMtvAudMgr != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(mMtvAudMgr.getVolumeLevel() != 0) goto _L4; else goto _L3
_L3:
        if(mVolumeMuteButton != null)
            mVolumeMuteButton.setVisibility(0);
        if(mVolumeButton != null)
            mVolumeButton.setVisibility(8);
_L5:
        if(mVolumeText != null)
            if(mMtvAudMgr.getVolumeLevel() == 0)
                mVolumeText.setText(null);
            else
            if(mMtvAudMgr.getVolumeLevel() < 10)
                mVolumeText.setText((new StringBuilder()).append("0").append(mMtvAudMgr.getVolumeLevel()).toString());
            else
                mVolumeText.setText((new StringBuilder()).append("").append(mMtvAudMgr.getVolumeLevel()).toString());
        if(true) goto _L1; else goto _L4
_L4:
        if(mVolumeMuteButton != null)
            mVolumeMuteButton.setVisibility(8);
        if(mVolumeButton != null)
            mVolumeButton.setVisibility(0);
          goto _L5
    }

    private void updateFragmentsBasedOnLockState(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->updateFragmentsBasedOnLockState(Z)V");
        int i;
        if(flag)
            i = 0;
        else
            i = 4;
        if(!mFilePlayer.isBmlFullView())
            mProgressBarArea.setVisibility(i);
        else
            mProgressBarArea.setVisibility(4);
        mVolumeButton.setEnabled(flag);
        mVolumeMuteButton.setEnabled(flag);
        mVolumeText.setEnabled(flag);
        mPrevButton.setEnabled(flag);
        mNextButton.setEnabled(flag);
        mPlayPauseButton.setEnabled(flag);
        onUpdate(113, Boolean.valueOf(false));
    }

    private void updateTrickSeekBar(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->updateTrickSeekBar(I)V");
        if(mProgressBarArea != null)
            if(i == TRICKMODESPEED_NONE)
            {
                mProgressBarArea.setVisibility(0);
                mListener.onFragEvent(290, null);
            } else
            {
                mProgressBarArea.setVisibility(0);
                mListener.onFragEvent(291, null);
            }
    }

    private void updateTrickSpeed(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->updateTrickSpeed(I)V");
        if(mTrickModeText != null)
            if(i == TRICKMODESPEED_2x)
            {
                mTrickModeText.setVisibility(0);
                mTrickModeText.setText("2x");
            } else
            if(i == TRICKMODESPEED_10x)
            {
                mTrickModeText.setVisibility(0);
                mTrickModeText.setText("10x");
            } else
            {
                mTrickModeText.setVisibility(8);
            }
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onAttach(Landroid/app/Activity;)V");
        super.onAttach(activity);
        try
        {
            mListener = (com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener)activity;
            return;
        }
        catch(ClassCastException classcastexception)
        {
            throw new ClassCastException((new StringBuilder()).append(activity.toString()).append(" must implement IMtvFragEventListener").toString());
        }
    }

    public void onClick(View view)
    {
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state;
        int i;
        int j;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onClick(Landroid/view/View;)V");
        state = mFilePlayer.getPlaybackState();
        i = mFilePlayer.getPlayerCommand();
        j = mFilePlayer.getTrickMode();
        if(!mFilePlayer.isPhoneLocked()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch(view.getId())
        {
        case 2131361911: 
        case 2131361912: 
        case 2131361913: 
            toggleVolumeButton();
            mListener.onFragEvent(227, null);
            break;

        case 2131362023: 
            MtvUtilDebug.Low("MtvUiFilePlayerVidFrag", (new StringBuilder()).append("onClick - VideoPlayerBtnPause: command[").append(i).append("] state[").append(state).append("]").toString());
            if(j != 0)
                mListener.onFragEvent(289, null);
            else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state)
                mListener.onFragEvent(283, null);
            else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state)
                mListener.onFragEvent(284, null);
            break;

        case 2131362022: 
            mListener.onFragEvent(282, null);
            break;

        case 2131362024: 
            mListener.onFragEvent(281, null);
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        mFilePlayer = (MtvUiFilePlayer)getActivity();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mContext = getActivity().getApplicationContext();
        mLayoutView = layoutinflater.inflate(0x7f030027, null);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(mContext);
        mProgressBarArea = (RelativeLayout)mLayoutView.findViewById(0x7f0a00e0);
        mPlayerSeekBar = (SeekBar)mLayoutView.findViewById(0x7f0a00e3);
        mPlayerSeekBar.setOnSeekBarChangeListener(mPlayerSeekBarChangeListener);
        mVolumeButton = (ImageButton)mLayoutView.findViewById(0x7f0a0077);
        mVolumeMuteButton = (ImageButton)mLayoutView.findViewById(0x7f0a0078);
        mVolumeText = (TextView)mLayoutView.findViewById(0x7f0a0079);
        mPlayPauseButton = (ImageButton)mLayoutView.findViewById(0x7f0a00e7);
        mPrevButton = (ImageButton)mLayoutView.findViewById(0x7f0a00e6);
        mNextButton = (ImageButton)mLayoutView.findViewById(0x7f0a00e8);
        mPlayingTimeTextView = (TextView)mLayoutView.findViewById(0x7f0a00e2);
        mPlayingTotalTimeTextView = (TextView)mLayoutView.findViewById(0x7f0a00e4);
        mTrickModeText = (TextView)mLayoutView.findViewById(0x7f0a00df);
        mPreferences = new MtvPreferences(mContext);
        return mLayoutView;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onDestroy()V");
        super.onDestroy();
    }

    public boolean onLongClick(View view)
    {
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state;
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onLongClick(Landroid/view/View;)Z");
        state = mFilePlayer.getPlaybackState();
        i = mFilePlayer.getPlayerCommand();
        view.getId();
        JVM INSTR lookupswitch 6: default 88
    //                   2131361911: 94
    //                   2131361912: 94
    //                   2131361913: 94
    //                   2131362022: 124
    //                   2131362023: 243
    //                   2131362024: 124;
           goto _L1 _L2 _L2 _L2 _L3 _L4 _L3
_L4:
        break MISSING_BLOCK_LABEL_243;
_L1:
        boolean flag = false;
_L5:
        return flag;
_L2:
        mMtvAudMgr.volumeMute();
        toggleVolumeButton();
        mListener.onFragEvent(226, null);
        flag = true;
          goto _L5
_L3:
        if(state != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && (state != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED || i != 20491)) goto _L1; else goto _L6
_L6:
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setBackgroundResource(0x7f0200db);
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setOnClickListener(this);
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setOnLongClickListener(this);
        if(0x7f0a00e8 == view.getId())
            mListener.onFragEvent(287, null);
        else
            mListener.onFragEvent(288, null);
        flag = true;
          goto _L5
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setBackgroundResource(0x7f0200d5);
        flag = true;
          goto _L5
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onResume()V");
        super.onResume();
        initializeUI();
        updateTrickSpeed(mFilePlayer.getTrickModeClick());
        boolean flag;
        if(!isLocked)
            flag = true;
        else
            flag = false;
        updateFragmentsBasedOnLockState(flag);
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiFilePlayerVidFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        if(i == 112 || !isLocked) goto _L2; else goto _L1
_L1:
        return;
_L2:
        i;
        JVM INSTR lookupswitch 18: default 208
    //                   106: 271
    //                   107: 271
    //                   108: 271
    //                   112: 217
    //                   113: 278
    //                   114: 403
    //                   117: 427
    //                   118: 482
    //                   119: 594
    //                   120: 613
    //                   121: 631
    //                   122: 548
    //                   123: 645
    //                   124: 659
    //                   125: 670
    //                   505: 368
    //                   506: 292
    //                   507: 410;
           goto _L3 _L4 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19
_L16:
        break MISSING_BLOCK_LABEL_670;
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        break; /* Loop/switch isn't completed */
_L21:
        super.onUpdate(i, obj);
        if(true) goto _L1; else goto _L20
_L20:
        if(obj != null)
        {
            boolean flag;
            if(!((Boolean)obj).booleanValue())
                flag = true;
            else
                flag = false;
            isLocked = flag;
            updateFragmentsBasedOnLockState(((Boolean)obj).booleanValue());
        } else
        {
            MtvUtilDebug.Low("MtvUiFilePlayerVidFrag", "UPDATE_COMPONENTS_ON_LOCK : Value passed is null ; can't update ");
        }
          goto _L21
_L4:
        toggleVolumeButton();
          goto _L21
_L6:
        setMainControlByPlayback(((Boolean)obj).booleanValue());
          goto _L21
_L18:
        if(obj != null)
        {
            Bundle bundle = (Bundle)obj;
            if(bundle != null && mFilePlayer.getCurrentFileIndex() == bundle.getInt("fileIndex"))
            {
                int k = bundle.getInt("fileUpdateTime");
                if(k < 0)
                    k = 0;
                setTextFilePlayingtime(k);
                if(mPlayerSeekBar != null)
                    mPlayerSeekBar.setProgress(k);
            }
        }
          goto _L21
_L17:
        if(!mbIsTouching)
        {
            setTextFilePlayingtime(((Integer)obj).intValue());
            mPlayerSeekBar.setProgress(((Integer)obj).intValue());
        }
          goto _L21
_L7:
        setInitialMainControl();
          goto _L21
_L19:
        mPlayerSeekBar.setEnabled(((Boolean)obj).booleanValue());
          goto _L21
_L8:
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setBackgroundResource(0x7f0200db);
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setOnClickListener(this);
        ((ImageButton)mLayoutView.findViewById(0x7f0a00e7)).setOnLongClickListener(this);
          goto _L21
_L9:
        if(mProgressBarArea != null)
            if(mProgressBarArea.getVisibility() == 0)
            {
                mProgressBarArea.setVisibility(8);
                mListener.onFragEvent(291, null);
            } else
            {
                mProgressBarArea.setVisibility(0);
                mListener.onFragEvent(290, null);
            }
          goto _L21
_L13:
        if(mProgressBarArea != null)
        {
            int j = MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext());
            if(mProgressBarArea.getVisibility() == 0 && j == 0)
                mProgressBarArea.setVisibility(8);
        }
          goto _L21
_L10:
        if(mProgressBarArea != null)
            mProgressBarArea.setVisibility(8);
          goto _L21
_L11:
        if(mProgressBarArea != null)
            mProgressBarArea.setVisibility(0);
          goto _L21
_L12:
        updateTrickSpeed(((Integer)obj).intValue());
          goto _L21
_L14:
        updateTrickSeekBar(((Integer)obj).intValue());
          goto _L21
_L15:
        mProgressBarArea.setVisibility(0);
          goto _L21
        initializeUI();
          goto _L21
    }

    private int TRICKMODESPEED_10x;
    private int TRICKMODESPEED_2x;
    private int TRICKMODESPEED_NONE;
    private boolean isLocked;
    private Context mContext;
    private MtvUiFilePlayer mFilePlayer;
    private View mLayoutView;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;
    private MtvUtilAudioManager mMtvAudMgr;
    private ImageButton mNextButton;
    private ImageButton mPlayPauseButton;
    private SeekBar mPlayerSeekBar;
    private android.widget.SeekBar.OnSeekBarChangeListener mPlayerSeekBarChangeListener;
    private TextView mPlayingTimeTextView;
    private TextView mPlayingTotalTimeTextView;
    private MtvPreferences mPreferences;
    private ImageButton mPrevButton;
    private RelativeLayout mProgressBarArea;
    private int mTotalDurationInSecond;
    private TextView mTrickModeText;
    private ImageButton mVolumeButton;
    private ImageButton mVolumeMuteButton;
    private TextView mVolumeText;
    private boolean mbIsTouching;


/*
    static boolean access$002(MtvUiFilePlayerVidFrag mtvuifileplayervidfrag, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->access$002(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;Z)Z");
        mtvuifileplayervidfrag.mbIsTouching = flag;
        return flag;
    }

*/


/*
    static com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener access$100(MtvUiFilePlayerVidFrag mtvuifileplayervidfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;->access$100(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuifileplayervidfrag.mListener;
    }

*/

    private class _cls1
        implements android.widget.SeekBar.OnSeekBarChangeListener
    {

        public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag$1;->onProgressChanged(Landroid/widget/SeekBar;IZ)V");
        }

        public void onStartTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag$1;->onStartTrackingTouch(Landroid/widget/SeekBar;)V");
            Log.d(MtvUiFilePlayerVidFrag.this, 1);
            Log.d(MtvUiFilePlayerVidFrag.this).onFragEvent(286, Integer.valueOf(-1));
        }

        public void onStopTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag$1;->onStopTrackingTouch(Landroid/widget/SeekBar;)V");
            Log.d(MtvUiFilePlayerVidFrag.this, 0);
            Log.d(MtvUiFilePlayerVidFrag.this).onFragEvent(285, Integer.valueOf(seekbar.getProgress()));
            onUpdate(505, Integer.valueOf(seekbar.getProgress()));
            if(MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) == 1)
                Log.d(MtvUiFilePlayerVidFrag.this).onFragEvent(286, Integer.valueOf(5000));
        }

        final MtvUiFilePlayerVidFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerVidFrag;)V");
            this$0 = MtvUiFilePlayerVidFrag.this;
            super();
        }
    }

}
