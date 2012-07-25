// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.fileplayer;

import android.app.*;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.*;
import android.text.SpannableStringBuilder;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.MtvFile;
import com.samsung.sec.mtv.provider.MtvFileManager;
import com.samsung.sec.mtv.ui.bml.IMtvUiBmlActivity;
import com.samsung.sec.mtv.ui.bml.MtvUiBmlSurfaceView;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.ui.common.MtvUiSleepTimerDialogFrag;
import com.samsung.sec.mtv.utility.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class MtvUiFilePlayer extends Activity
    implements IMtvAppPlaybackStateListener, IMtvUiBmlActivity, com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener
{
    class ChannelGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            boolean flag;
            flag = false;
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("GestrueDetectorUiPlayer : onFling called ").append(f).toString());
            if(lockImage.getVisibility() != 0) goto _L2; else goto _L1
_L1:
            MtvUtilDebug.Low("MtvUiFilePlayer", "File Player in Lock Mode onFling cannot be processed...");
            flag = true;
_L4:
            return flag;
_L2:
            float f2 = Math.abs(f);
            float f3 = Math.abs(f1);
            int i = 0;
            if(motionevent1 != null && motionevent != null)
                i = (int)(motionevent1.getX() - motionevent.getX());
            if(Math.abs(i) < 100 || f3 > f2)
                MtvUtilDebug.Low("MtvUiFilePlayer", "Up/Down fling...no channel change");
            else
            if(MtvUiFilePlayer.mMtvFile.getFileFormat() == 2 || isPrevNextEnabled())
                if(f > 0.0F)
                    onFragEvent(282, null);
                else
                if(f < 0.0F)
                    onFragEvent(281, null);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            if(fragHandler == null || fragHandler.isPhoneLocked()) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            i = MtvUiFilePlayer.mMtvFile.getFileFormat();
            j = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
            if(2 != i) goto _L4; else goto _L3
_L3:
            if(fragHandler.isFragPresent(8))
                fragHandler.removeFrag(8);
            if(!fragHandler.isFragPresent(15))
            {
                fragHandler.addFrag(15, 5000L, false, new int[0]);
                mLocalUiMsgHandler.sendEmptyMessage(500);
                fragHandler.addFrag(13, 5000L, false, new int[0]);
            } else
            {
                fragHandler.removeFrag(15);
                fragHandler.removeFrag(13);
            }
_L2:
            return false;
_L4:
            if(!fragHandler.isFragPresent(15))
            {
                if(j == 1)
                    fragHandler.addFrag(15, 5000L, false, new int[0]);
                else
                    fragHandler.addFrag(15, -1L, false, new int[0]);
                mLocalUiMsgHandler.sendEmptyMessage(500);
            } else
            if(j == 1)
                fragHandler.removeFrag(15);
            if(fragHandler.isFragPresent(14))
                break; /* Loop/switch isn't completed */
            if(j == 1)
            {
                fragHandler.addFrag(14, 5000L, false, new int[0]);
            } else
            {
                fragHandler.addFrag(14, -1L, false, new int[0]);
                mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
                mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
            }
_L6:
            if(fragHandler.isFragPresent(12) && j == 1)
                fragHandler.removeFrag(12);
            if(true) goto _L2; else goto _L5
_L5:
            if(j == 1)
                fragHandler.removeFrag(14);
            else
            if(MtvUiFilePlayer.TrickMode == 0)
                fragHandler.onUpdate(118, null, 14);
              goto _L6
            if(true) goto _L2; else goto _L7
_L7:
        }

        final MtvUiFilePlayer this$0;

        ChannelGestureListener()
        {
            this$0 = MtvUiFilePlayer.this;
            super();
        }
    }

    class ControlAnimationRunnable
        implements Runnable
    {

        private void controlAnimation()
        {
            mAnimationDrawable = (AnimationDrawable)mAnimationImage.getBackground();
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("controlAnimation: called :").append(isEnable).toString());
            if(isEnable)
            {
                mAnimationlayout.setVisibility(0);
                mAnimationImage.setVisibility(0);
                if(!mAnimationDrawable.isRunning())
                    mAnimationDrawable.start();
                else
                    mAnimationDrawable.start();
                if(mStrAnimation != null)
                    mTxtAnimation.setText(mStrAnimation);
            } else
            {
                if(mAnimationDrawable.isRunning())
                {
                    mAnimationDrawable.stop();
                    mTxtAnimation.setText("");
                    mAnimationlayout.setVisibility(4);
                    mAnimationImage.setVisibility(4);
                }
                mAnimationImage.setVisibility(4);
            }
        }

        public void postAnimationToRunInUIThread()
        {
            mAnimationImage.post(this);
        }

        public void run()
        {
            controlAnimation();
        }

        public void setAnimationEnable(boolean flag)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationEnable :").append(flag).toString());
            isEnable = flag;
        }

        public void setAnimationText(String s)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationText :").append(s).toString());
            mStrAnimation = s;
        }

        private boolean isEnable;
        private AnimationDrawable mAnimationDrawable;
        private String mStrAnimation;
        final MtvUiFilePlayer this$0;

        public ControlAnimationRunnable()
        {
            this$0 = MtvUiFilePlayer.this;
            super();
            isEnable = false;
            mStrAnimation = null;
            MtvUtilDebug.Low("MtvUiFilePlayer", "ControlAnimationRunnable...");
        }
    }


    public MtvUiFilePlayer()
    {
        mImgViewerScreen = null;
        mVidViewerScreen = null;
        mCaptionView = null;
        mMtvAppPlaybackContext = null;
        mMtvPlayerOneSeg = null;
        mMtvFileList = null;
        mMtvAudMgr = null;
        isRotated = false;
        mCurrentInSecond = 0;
        mbIsInTrickMode = false;
        mbIsTrickResume = false;
        timeCalculatedForTrick = -1;
        mRunnableUpdateSeekBarTime = new Runnable() ;
        mRunnableHideProgressBarInPortrait = new Runnable() {

            public void run()
            {
                if(fragHandler != null)
                    fragHandler.onUpdate(122, null, 14);
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mRunnableUpdateFragsBasedOnLockState = new Runnable() {

            public void run()
            {
                updateFragsBasedOnLockState();
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(listener);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mService.unregisterListener(listener);
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onMtvAppAndroidServiceNotify called: what=").append(i).toString());
                i;
                JVM INSTR lookupswitch 2: default 52
            //                           2: 81
            //                           9: 53;
                   goto _L1 _L2 _L3
_L1:
                return;
_L3:
                mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(512, obj));
                continue; /* Loop/switch isn't completed */
_L2:
                mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(515, obj));
                if(true) goto _L1; else goto _L4
_L4:
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                mLocalUiMsgHandler.removeCallbacks(null);
                mLocalUiMsgHandler.removeCallbacksAndMessages(null);
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        finish();
                    }

                    final _cls5 this$1;

                        _cls1()
                        {
                            this$1 = _cls5.this;
                            super();
                        }
                }

                mLocalUiMsgHandler.post(new _cls1());
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mLocalUiMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                MtvUtilDebug.Mid("MtvUiFilePlayer", (new StringBuilder()).append("handleMessage : ").append(message.what).toString());
                message.what;
                JVM INSTR lookupswitch 13: default 144
            //                           112: 1353
            //                           500: 248
            //                           501: 282
            //                           502: 362
            //                           503: 442
            //                           508: 479
            //                           509: 503
            //                           510: 686
            //                           511: 145
            //                           512: 1058
            //                           513: 1030
            //                           514: 1010
            //                           515: 1236;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
                return;
_L10:
                String s = null;
                if(message.obj != null)
                    s = (String)message.obj;
                if(mControlAnimationRunnable == null)
                    mControlAnimationRunnable = new ControlAnimationRunnable();
                if(s != null)
                {
                    mControlAnimationRunnable.setAnimationEnable(true);
                    mControlAnimationRunnable.setAnimationText(s);
                } else
                {
                    mControlAnimationRunnable.setAnimationEnable(false);
                }
                mControlAnimationRunnable.postAnimationToRunInUIThread();
                continue; /* Loop/switch isn't completed */
_L3:
                if(fragHandler != null)
                    fragHandler.onUpdate(101, getStatusBarText(), 15);
                continue; /* Loop/switch isn't completed */
_L4:
                invalidateViews();
                loadNextFile();
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(101, getStatusBarText(), 15);
                    if(MtvUiFilePlayer.mMtvFile.getFileFormat() != 2)
                        fragHandler.onUpdate(114, null, 14);
                }
                loadCurrentFileUI();
                continue; /* Loop/switch isn't completed */
_L5:
                invalidateViews();
                loadPrevFile();
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(101, getStatusBarText(), 15);
                    if(MtvUiFilePlayer.mMtvFile.getFileFormat() != 2)
                        fragHandler.onUpdate(114, null, 14);
                }
                loadCurrentFileUI();
                continue; /* Loop/switch isn't completed */
_L6:
                if(fragHandler != null)
                    fragHandler.onUpdate(113, Boolean.valueOf(mbIsInTrickMode), 14);
                continue; /* Loop/switch isn't completed */
_L7:
                SpannableStringBuilder spannablestringbuilder = (SpannableStringBuilder)message.obj;
                mCaptionView.setText(spannablestringbuilder);
                continue; /* Loop/switch isn't completed */
_L8:
                MtvUtilDebug.Low("MtvUiFilePlayer", "Stopping trick mode");
                MtvUiFilePlayer.TrickMode = 0;
                MtvUiFilePlayer.TrickModeClick = 0;
                mbIsInTrickMode = false;
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                    resetVideoFragments(5000);
                else
                    resetVideoFragments(-1);
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                    fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                }
                mMtvAudMgr.setAudioMute(false);
                mMtvPlayerOneSeg.trickmode(mMtvAppPlaybackContext, 0, -1);
                if(fragHandler != null)
                    fragHandler.onUpdate(113, Boolean.valueOf(mbIsInTrickMode), 14);
                continue; /* Loop/switch isn't completed */
_L9:
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("UI_FILE_PLAYER_CHECK_TRICK TrickMode ").append(MtvUiFilePlayer.TrickMode).append(" mbIsTrickResume ").append(mbIsTrickResume).toString());
                if(MtvUiFilePlayer.TrickMode != 0)
                {
                    final Integer updateTime = (Integer)message.obj;
                    if(updateTime.intValue() >= 0)
                    {
                        if(1 + updateTime.intValue() == timeCalculatedForTrick)
                        {
                            mCurrentInSecond = updateTime.intValue();
                            class _cls1
                                implements Runnable
                            {

                                public void run()
                                {
                                    updateVidFragCurrTime(updateTime.intValue());
                                }

                                final _cls6 this$1;
                                final Integer val$updateTime;

                        _cls1()
                        {
                            this$1 = _cls6.this;
                            updateTime = integer;
                            super();
                        }
                            }

                            runOnUiThread(new _cls1());
                        }
                        if(!mbIsTrickResume)
                        {
                            mLocalUiMsgHandler.post(mRunnableTrickModeNew);
                        } else
                        {
                            MtvUtilDebug.High("MtvUiFilePlayer", "Resume Pressed -- Posting FILE_PLAYER_STOP_TRICK ");
                            mbIsTrickResume = false;
                            mLocalUiMsgHandler.sendEmptyMessage(509);
                        }
                    } else
                    {
                        MtvUiFilePlayer.TrickMode = 0;
                        MtvUiFilePlayer.TrickModeClick = 0;
                        mbIsInTrickMode = false;
                        if(fragHandler != null)
                        {
                            fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                            fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                        }
                    }
                } else
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(113, Boolean.valueOf(mbIsInTrickMode), 14);
                    fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                    fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                }
                continue; /* Loop/switch isn't completed */
_L13:
                setScreenRatio(((Integer)message.obj).intValue());
                continue; /* Loop/switch isn't completed */
_L12:
                if(fragHandler != null)
                    fragHandler.onUpdate(104, null, 15);
                continue; /* Loop/switch isn't completed */
_L11:
                closeOptionsMenu();
                if(message.obj != null)
                {
                    int k = ((Intent)message.obj).getIntExtra("state", 0);
                    if(k == 0)
                    {
                        if(mPreferences == null)
                            mPreferences = new MtvPreferences(getApplicationContext());
                        mPreferences.setAudio51Enabled(false);
                        if(fragHandler != null && fragHandler.isFragPresent(15))
                            fragHandler.onUpdate(104, null, 15);
                        if(fragHandler != null && fragHandler.isFragPresent(2))
                            fragHandler.onUpdate(109, Integer.valueOf(k), 2);
                    }
                }
                postDelayed(postHeadsetUpdate, 300L);
                continue; /* Loop/switch isn't completed */
_L14:
                if(message.obj != null)
                {
                    Intent intent = (Intent)message.obj;
                    boolean flag1;
                    int i;
                    int j;
                    if(intent.getIntExtra("status", 1) == 2)
                        flag1 = true;
                    else
                        flag1 = false;
                    i = intent.getIntExtra("scale", 100);
                    j = intent.getIntExtra("level", i);
                    MtvBatteryInfo.setBatteryChargeStatus(flag1);
                    if(j < 15 && !flag1)
                        finish();
                    MtvBatteryInfo.updateBatteryLevel(j, i);
                    if(fragHandler != null)
                        fragHandler.onUpdate(102, null, 15);
                }
                continue; /* Loop/switch isn't completed */
_L2:
                if(message.obj != null)
                {
                    boolean flag;
                    if(lockImage.getVisibility() != 0)
                        flag = true;
                    else
                        flag = false;
                    if(fragHandler != null)
                        fragHandler.onUpdate(112, Boolean.valueOf(flag), ((Integer)message.obj).intValue());
                }
                if(true) goto _L1; else goto _L15
_L15:
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        postHeadsetUpdate = new Runnable() {

            public void run()
            {
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(108, null, 12);
                    fragHandler.onUpdate(108, null, 14);
                    MtvUtilAudioManager.getInstance(getApplicationContext()).updateCurrentAudioEffectAndMode();
                }
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mRunnableTrickModeNew = new Runnable() {

            public void run()
            {
                int i;
                int j;
                boolean flag = true;
                i = 1000 * calculateSeekToValue();
                j = getFileTotalTime();
                boolean flag1;
                boolean flag2;
                boolean flag3;
                if(MtvUiFilePlayer.TrickMode != 0)
                    flag1 = flag;
                else
                    flag1 = false;
                if(i > 0)
                    flag2 = flag;
                else
                    flag2 = false;
                flag3 = flag2 & flag1;
                if(i >= j)
                    flag = false;
                if(!(flag & flag3)) goto _L2; else goto _L1
_L1:
                mMtvPlayerOneSeg.trickmode(mMtvAppPlaybackContext, 2, i);
_L4:
                return;
_L2:
                if(i >= j)
                    onFragEvent(281, null);
                else
                if(i <= 0)
                    onFragEvent(282, null);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        runnableVolumeEscalating = new Runnable() {

            public void run()
            {
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(106, null, 0);
                    fragHandler.onUpdate(106, null, 12);
                    fragHandler.onUpdate(106, null, 1);
                }
                if(mMtvAudMgr != null && mMtvAudMgr.getVolumeLevel() != 15)
                    mLocalUiMsgHandler.postDelayed(runnableVolumeEscalating, 100L);
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        runnableVolumeDecreasing = new Runnable() {

            public void run()
            {
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(107, null, 0);
                    fragHandler.onUpdate(107, null, 12);
                    fragHandler.onUpdate(107, null, 1);
                }
                if(mMtvAudMgr != null && mMtvAudMgr.getVolumeLevel() != 0)
                    mLocalUiMsgHandler.postDelayed(runnableVolumeDecreasing, 100L);
            }

            final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
    }

    private int calculateSeekToValue()
    {
        int i = getCurrentFileTime();
        if(TrickMode != 1) goto _L2; else goto _L1
_L1:
        if(TrickModeClick == 1)
            i += 3;
        else
            i += 11;
_L4:
        timeCalculatedForTrick = i;
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("calculateSeekToValue: currentTime[").append(getCurrentFileTime()).append("] ").append("calculatedTime[").append(i).append("]").toString());
        return i;
_L2:
        if(TrickMode == 2)
            if(TrickModeClick == 1)
                i--;
            else
                i -= 9;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void clearCaptionView()
    {
        MtvUtilDebug.Low("MtvUiFilePlayer", "Inside Clear caption");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        if(mPreferences.isCaptionEnabled() && !isBmlFullView())
        {
            mCaptionView.setText("");
            mCaptionView.invalidate();
        }
    }

    private void clearNotification()
    {
        MtvUtilDebug.Low("MtvUiFilePlayer", "clearNotification...");
        mNotificationManager.cancel(0x7f020113);
    }

    private void drawImageFile()
    {
        FileInputStream fileinputstream;
        ByteBuffer bytebuffer;
        fileinputstream = null;
        bytebuffer = null;
        FileInputStream fileinputstream1 = new FileInputStream(mMtvFile.getFilePath());
        MtvUtilCrypto mtvutilcrypto = new MtvUtilCrypto(2);
        int i;
        int j;
        i = 0;
        j = 0;
        int k = mtvutilcrypto.getOutputSize(2, (int)fileinputstream1.getChannel().size());
        i = k;
_L3:
        long l = fileinputstream1.getChannel().size();
        j = (int)l;
_L5:
        if(i <= 0 || j <= 0) goto _L2; else goto _L1
_L1:
        ByteBuffer bytebuffer1 = ByteBuffer.allocate(i);
        ByteBuffer bytebuffer2 = ByteBuffer.allocate((int)fileinputstream1.getChannel().size());
        bytebuffer = bytebuffer2;
_L6:
        Exception exception;
        FileNotFoundException filenotfoundexception;
        IOException ioexception2;
        IOException ioexception3;
        try
        {
            fileinputstream1.read(bytebuffer.array());
        }
        catch(IOException ioexception6)
        {
            ioexception6.printStackTrace();
        }
        mtvutilcrypto.decryptData(bytebuffer, bytebuffer1);
        mImage.setImageBitmap(BitmapFactory.decodeByteArray(bytebuffer1.array(), 0, bytebuffer1.array().length));
        if(mPreferences != null)
            mPreferences.setLatestFileIndex(mMtvFileIndex);
_L2:
        fileinputstream1.close();
_L4:
        return;
        ioexception2;
        ioexception2.printStackTrace();
          goto _L3
        filenotfoundexception;
        fileinputstream = fileinputstream1;
_L8:
        filenotfoundexception.printStackTrace();
        try
        {
            fileinputstream.close();
        }
        catch(NullPointerException nullpointerexception1)
        {
            nullpointerexception1.printStackTrace();
        }
        catch(IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
          goto _L4
        ioexception3;
        ioexception3.printStackTrace();
          goto _L5
        exception;
        fileinputstream = fileinputstream1;
_L7:
        IOException ioexception4;
        NullPointerException nullpointerexception2;
        IOException ioexception5;
        try
        {
            fileinputstream.close();
        }
        catch(NullPointerException nullpointerexception)
        {
            nullpointerexception.printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        throw exception;
        ioexception5;
        ioexception5.printStackTrace();
          goto _L6
        nullpointerexception2;
        nullpointerexception2.printStackTrace();
          goto _L4
        ioexception4;
        ioexception4.printStackTrace();
          goto _L4
        exception;
          goto _L7
        exception;
        fileinputstream = fileinputstream1;
          goto _L7
        filenotfoundexception;
          goto _L8
        filenotfoundexception;
        fileinputstream = fileinputstream1;
          goto _L8
    }

    private SpannableStringBuilder getCaptionText()
    {
        SpannableStringBuilder spannablestringbuilder = null;
        if(mMtvAppPlaybackContext != null)
            spannablestringbuilder = mMtvAppPlaybackContext.getComponents().getCaption().getBuffer();
        return spannablestringbuilder;
    }

    private SurfaceView getLocalVideoSurfaceView(boolean flag)
    {
        SurfaceView surfaceview;
        if(!flag)
            mLocalVideoSurfaceView.setVisibility(8);
        else
        if(!mBmlSurface.IsBMLFullView())
            mLocalVideoSurfaceView.setVisibility(0);
        else
            mLocalVideoSurfaceView.setVisibility(8);
        if(flag)
            surfaceview = mLocalVideoSurfaceView;
        else
            surfaceview = mHiddenSurfaceView;
        return surfaceview;
    }

    private String getStatusBarText()
    {
        String s;
        if(mMtvFile.getProgramName() == null || mMtvFile.getProgramName().equals("null"))
            s = getResources().getString(0x7f0700ce);
        else
            s = mMtvFile.getProgramName();
        return (new StringBuilder()).append(mMtvFile.getChannelName()).append("\n").append(s).toString();
    }

    private void initMtvFilePlayer()
    {
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext == null || mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LOCALTV)
        {
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(511, getString(0x7f070087)));
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLocalTV();
        }
        mMtvAppPlaybackContext.getState().registerListener(this);
        mMtvAppPlaybackContext.getComponents().getBml().enable();
        registerVideoSurfaceView(true);
        if(getCaptionText() != null)
            mCaptionView.setText(getCaptionText());
        if(mMtvFile.getFileFormat() != 2) goto _L2; else goto _L1
_L1:
        loadImageFile();
_L4:
        return;
_L2:
        if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(511, null));
        else
        if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED)
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(511, getString(0x7f070086)));
        else
        if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED)
            loadVideoFile();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initViewControl()
    {
        setContentView(0x7f030004);
        lockImage = (ImageView)findViewById(0x7f0a0025);
        mAnimationlayout = (RelativeLayout)findViewById(0x7f0a0021);
        mAnimationImage = (ImageView)findViewById(0x7f0a0022);
        mTxtAnimation = (TextView)findViewById(0x7f0a0023);
        mAnimationlayout.setVisibility(8);
        mLocalVideoSurfaceView = (SurfaceView)findViewById(0x7f0a001d);
        mHiddenSurfaceView = (SurfaceView)findViewById(0x7f0a001e);
        mImgViewerScreen = (AbsoluteLayout)findViewById(0x7f0a001a);
        mVidViewerScreen = (AbsoluteLayout)findViewById(0x7f0a001c);
        mBmlSurface = (MtvUiBmlSurfaceView)findViewById(0x7f0a000a);
        mCaptionView = (TextView)findViewById(0x7f0a0020);
        mImgViewNoChannel = (ImageView)findViewById(0x7f0a001f);
        mImage = (ImageView)findViewById(0x7f0a001b);
        mChannelGestureDetector = new GestureDetector(this, new ChannelGestureListener());
        setCaptionViewVisibility();
        mCaptionView.setOnClickListener(null);
        setImageViewDuringNoChannel(false);
        setScreen();
    }

    private void invalidateViews()
    {
        mLocalVideoSurfaceView.setVisibility(4);
        mLocalVideoSurfaceView.setVisibility(0);
        clearCaptionView();
        if(mBmlSurface.IsBMLFullView())
            setBMLLayoutFullView(false);
        if(optionsMenu != null)
        {
            closeOptionsMenu();
            optionsMenu = null;
        }
    }

    private boolean isPrevNextEnabled()
    {
        boolean flag;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state;
        int i;
        flag = false;
        state = getPlaybackState();
        i = getPlayerCommand();
        if(20480 != i) goto _L2; else goto _L1
_L1:
        if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED == state || com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state || com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state)
            flag = true;
_L4:
        return flag;
_L2:
        if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state && 20489 == i || com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state && 20490 == i || com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state && 20491 == i)
            flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void loadCurrentFileUI()
    {
        setScreen();
        showControl();
        updateFragsBasedOnLockState();
        mCurrentInSecond = 0;
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        if(mMtvFile.getFileFormat() == 2)
            loadImageFile();
        else
            loadVideoFile();
    }

    private void loadImageFile()
    {
        if(mPreferences != null)
            mPreferences.setIsFileFormatImage(true);
        mBmlSurface.stopBmlContrlFragment();
        if(mMtvFile != null)
        {
            MtvURI mtvuri = new MtvURI(5, null, mMtvFile.getIndex());
            mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLocalTV(), mtvuri);
        }
        drawImageFile();
    }

    private void loadNextFile()
    {
        if(mMtvFileIndex == -1 + mMtvFileList.length)
            mMtvFileIndex = 0;
        else
            mMtvFileIndex = 1 + mMtvFileIndex;
        mMtvFile = mMtvFileList[mMtvFileIndex];
    }

    private void loadPrevFile()
    {
        if(mMtvFileIndex == 0)
            mMtvFileIndex = -1 + mMtvFileList.length;
        else
            mMtvFileIndex = -1 + mMtvFileIndex;
        mMtvFile = mMtvFileList[mMtvFileIndex];
    }

    private void loadVideoFile()
    {
        if(mPreferences != null)
            mPreferences.setIsFileFormatImage(false);
        mImage.setImageBitmap(null);
        mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(511, getString(0x7f070086)));
        if(mMtvFile != null)
        {
            MtvURI mtvuri = new MtvURI(1, null, mMtvFile.getIndex());
            mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLocalTV(), mtvuri);
        }
        if(mPreferences != null)
            mPreferences.setLatestFileIndex(mMtvFileIndex);
    }

    private void postLockUpdate(int i)
    {
        mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(112, Integer.valueOf(i)));
    }

    private void registerVideoSurfaceView(boolean flag)
    {
        if(mMtvAppPlaybackContext != null)
        {
            mMtvAppPlaybackContext.getComponents().getVideo().enable();
            IMtvOneSegVideoControl imtvonesegvideocontrol = mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface();
            if(imtvonesegvideocontrol != null)
                imtvonesegvideocontrol.registerSurface(getLocalVideoSurfaceView(flag));
        }
    }

    private boolean requestSystemKeyEvent(int i, boolean flag)
    {
        IWindowManager iwindowmanager;
        iwindowmanager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", "requestSystemKeyEvent");
        boolean flag2 = iwindowmanager.requestSystemKeyEvent(i, getComponentName(), flag);
        boolean flag1 = flag2;
_L2:
        return flag1;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        flag1 = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void resetVideoFragments(int i)
    {
        if(fragHandler != null)
        {
            if(fragHandler.isFragPresent(15))
                fragHandler.resetTimer(15, i);
            if(fragHandler.isFragPresent(14))
            {
                fragHandler.resetTimer(14, i);
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
                {
                    mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
                    mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
                }
            }
        }
    }

    private void setActivityProperty()
    {
        requestWindowFeature(1);
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
            getWindow().clearFlags(1024);
        else
            getWindow().addFlags(1024);
        setVolumeControlStream(3);
        getWindow().addFlags(128);
    }

    private void setCaptionViewVisibility()
    {
        if(mPreferences.isCaptionEnabled() && !isBmlFullView())
        {
            mCaptionView.setVisibility(0);
            if(getCaptionText() != null)
                mCaptionView.setText(getCaptionText());
        } else
        {
            mCaptionView.setVisibility(8);
        }
    }

    private void setImageViewDuringNoChannel(boolean flag)
    {
        if(flag)
        {
            mLocalVideoSurfaceView.setVisibility(8);
            mImgViewNoChannel.setVisibility(0);
        } else
        {
            mLocalVideoSurfaceView.setVisibility(0);
            mImgViewNoChannel.setVisibility(8);
        }
    }

    private void setScreen()
    {
        if(mMtvFile.getFileFormat() == 2)
        {
            mImgViewerScreen.setVisibility(0);
            mImage.setVisibility(0);
            mVidViewerScreen.setVisibility(8);
        } else
        {
            mImgViewerScreen.setVisibility(8);
            mImage.setVisibility(8);
            mVidViewerScreen.setVisibility(0);
        }
    }

    private void setScreenRatio(int i)
    {
        Context context = getApplicationContext();
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        android.widget.AbsoluteLayout.LayoutParams layoutparams;
        if(MtvUtilAppService.getCurrentOrientation(context) == 1)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio landscape");
            j = (int)MtvUtilConfigSetting.convertDpToPixel(360F, context);
            k = (int)MtvUtilConfigSetting.convertDpToPixel(640F, context);
        } else
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio portrait");
            j = (int)MtvUtilConfigSetting.convertDpToPixel(203F, context);
            k = (int)MtvUtilConfigSetting.convertDpToPixel(360F, context);
        }
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("setScreenRatio LCD_HEIGHT:").append(j).append("LCD_WIDTH:").append(k).toString());
        if(1.777778F == 1.777778F)
        {
            if(i == 0)
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio 16:9 NORMAL");
                l = 9 * (k / 16);
                i1 = k;
            } else
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio 16:9 ENLARGE ");
                l = j;
                i1 = k;
            }
        } else
        if(i == 0)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio 4:3 NORMAL ");
            i1 = 4 * (k / 3);
            l = j;
        } else
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "setScreenRatio 4:3 ENLARGE ");
            l = j;
            i1 = k;
        }
        j1 = (k - i1) / 2;
        k1 = (j - l) / 2;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("setScreenRatio x = ").append(j1).append(" y = ").append(k1).append(" w = ").append(i1).append(" h = ").append(l).toString());
        layoutparams = new android.widget.AbsoluteLayout.LayoutParams(i1, l, j1, k1);
        mLocalVideoSurfaceView.setLayoutParams(layoutparams);
    }

    private void showControl()
    {
        long l;
        int i;
        l = -1L;
        i = mMtvFile.getFileFormat();
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            l = 5000L;
        if(fragHandler == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = fragHandler.areFragmentsHidden();
        if(fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        if(2 != i) goto _L4; else goto _L3
_L3:
        if(fragHandler.isFragPresent(8))
            fragHandler.removeFrag(8);
        if(fragHandler.isFragPresent(14))
        {
            fragHandler.removeFrag(14);
            mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
        }
        if(!fragHandler.isFragPresent(15) && !flag)
        {
            fragHandler.addFrag(15, l, false, new int[0]);
            mLocalUiMsgHandler.sendEmptyMessage(500);
        } else
        {
            fragHandler.resetTimer(15, l);
            mLocalUiMsgHandler.sendEmptyMessage(500);
        }
        if(!fragHandler.isFragPresent(13) && !flag)
            fragHandler.addFrag(13, l, false, new int[0]);
        else
            fragHandler.resetTimer(13, l);
_L2:
        return;
_L4:
        if(fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        if(fragHandler.isFragPresent(13))
            fragHandler.removeFrag(13);
        if(!fragHandler.isFragPresent(15) && !flag)
        {
            fragHandler.addFrag(15, l, false, new int[0]);
            mLocalUiMsgHandler.sendEmptyMessage(500);
        } else
        {
            fragHandler.resetTimer(15, l);
            mLocalUiMsgHandler.sendEmptyMessage(500);
        }
        if(!fragHandler.isFragPresent(14) && !flag)
            fragHandler.addFrag(14, l, false, new int[0]);
        else
        if(!mBmlSurface.IsBMLFullView())
        {
            fragHandler.onUpdate(124, null, 14);
            fragHandler.resetTimer(14, l);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void startVolumeControlBar()
    {
        int i = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
        if(!fragHandler.isFragPresent(15))
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "onTouchEvent, adding status fragment");
            fragHandler.addFrag(15, 6000L, false, new int[0]);
            mLocalUiMsgHandler.sendEmptyMessage(500);
            fragHandler.addFrag(14, 5000L, false, new int[0]);
        } else
        if(i == 1)
        {
            fragHandler.resetTimer(15, 6000L);
            fragHandler.resetTimer(14, 6000L);
        }
        if(!fragHandler.isFragPresent(12))
        {
            MtvUiFragHandler mtvuifraghandler = fragHandler;
            int ai[] = new int[1];
            ai[0] = 0x7f0a0027;
            mtvuifraghandler.addFrag(12, 5000L, false, ai);
        } else
        {
            fragHandler.resetTimer(12, 5000L);
        }
    }

    private void updateFragsBasedOnLockState()
    {
        int i;
        boolean flag;
        byte byte0;
        MtvUtilDebug.Low("MtvUiFilePlayer", "updateFragsBasedOnLockState...");
        i = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
        if(lockImage.getVisibility() == 0)
            flag = true;
        else
            flag = false;
        if(mMtvFile.getFileFormat() == 2)
            byte0 = 13;
        else
            byte0 = 14;
        if(fragHandler != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.High("MtvUiFilePlayer", "updateFragsBasedOnLockState: fragHandler is null ");
_L4:
        return;
_L2:
        if(i != 0)
            break; /* Loop/switch isn't completed */
        if(flag)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "FilePlayer in lock mode...disable all listeners");
            if(!fragHandler.isFragPresent(15))
            {
                MtvUiFragHandler mtvuifraghandler3 = fragHandler;
                int ai3[] = new int[2];
                ai3[0] = 0x7f0a0026;
                ai3[1] = 1;
                mtvuifraghandler3.addFrag(15, -1L, false, ai3);
                mLocalUiMsgHandler.sendEmptyMessage(500);
            }
            if(!fragHandler.isFragPresent(byte0))
            {
                MtvUiFragHandler mtvuifraghandler2 = fragHandler;
                int ai2[] = new int[2];
                ai2[0] = 0x7f0a0026;
                ai2[1] = 1;
                mtvuifraghandler2.addFrag(byte0, -1L, false, ai2);
            } else
            {
                postLockUpdate(byte0);
            }
        } else
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "FilePlayer not in lock mode...enable back listeners");
            if(!fragHandler.isFragPresent(15))
            {
                MtvUiFragHandler mtvuifraghandler1 = fragHandler;
                int ai1[] = new int[2];
                ai1[0] = 0x7f0a0026;
                ai1[1] = 0;
                mtvuifraghandler1.addFrag(15, -1L, false, ai1);
                mLocalUiMsgHandler.sendEmptyMessage(500);
            }
            if(!fragHandler.isFragPresent(byte0))
            {
                MtvUiFragHandler mtvuifraghandler = fragHandler;
                int ai[] = new int[2];
                ai[0] = 0x7f0a0026;
                ai[1] = 0;
                mtvuifraghandler.addFrag(byte0, -1L, false, ai);
            } else
            {
                postLockUpdate(byte0);
            }
            mLocalUiMsgHandler.sendEmptyMessage(503);
        }
_L5:
        if(fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        if(true) goto _L4; else goto _L3
_L3:
        if(flag)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "FilePlayer in lock mode...disable all listeners");
            if(fragHandler.isFragPresent(15))
                fragHandler.removeFrag(15);
            if(fragHandler.isFragPresent(byte0))
                fragHandler.removeFrag(byte0);
        } else
        {
            mLocalUiMsgHandler.sendEmptyMessage(503);
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private void updateVidFragCurrTime(int i)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("fileUpdateTime", i);
        bundle.putInt("fileIndex", mMtvFile.getIndex());
        if(fragHandler != null)
            fragHandler.onUpdate(506, bundle, 14);
    }

    public int getCurrentFileIndex()
    {
        return mMtvFile.getIndex();
    }

    public int getCurrentFileTime()
    {
        return mCurrentInSecond;
    }

    public int getFileFormat()
    {
        int i;
        if(mMtvFile != null)
            i = mMtvFile.getFileFormat();
        else
            i = -1;
        return i;
    }

    public int getFileTotalTime()
    {
        int i;
        if(mMtvFile != null)
            i = mMtvFile.getDurationOfRecording();
        else
            i = -1;
        return i;
    }

    public com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State getPlaybackState()
    {
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state;
        if(mMtvAppPlaybackContext == null)
            state = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        else
            state = mMtvAppPlaybackContext.getState().getState();
        return state;
    }

    public int getPlayerCommand()
    {
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        int i;
        if(mMtvAppPlaybackContext == null)
            i = 20480;
        else
            i = mMtvAppPlaybackContext.getState().getOp();
        return i;
    }

    public int getTrickMode()
    {
        return TrickMode;
    }

    public int getTrickModeClick()
    {
        return TrickModeClick;
    }

    public boolean isBmlFullView()
    {
        boolean flag;
        if(mBmlSurface != null)
            flag = mBmlSurface.IsBMLFullView();
        else
            flag = false;
        return flag;
    }

    public boolean isPhoneLocked()
    {
        boolean flag;
        if(fragHandler != null)
            flag = fragHandler.isPhoneLocked();
        else
            flag = false;
        return flag;
    }

    public void onBackPressed()
    {
        if(fragHandler != null)
            if(fragHandler.isFragPresent(2))
            {
                fragHandler.removeFrag(2);
                fragHandler.onUpdate(108, null, 14);
            } else
            {
                super.onBackPressed();
            }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        MtvUtilDebug.Low("MtvUiFilePlayer", "onCreate...");
        mPreferences = new MtvPreferences(getApplicationContext());
        setActivityProperty();
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        fragHandler = new MtvUiFragHandler(getFragmentManager(), 1, 0x7f0a0026);
        mNotificationManager = (NotificationManager)getSystemService("notification");
        clearNotification();
        if(bundle == null) goto _L2; else goto _L1
_L1:
        Object aobj[];
        mCurrentInSecond = bundle.getInt("fileUpdateTime");
        TrickMode = bundle.getInt("trickMode");
        TrickModeClick = bundle.getInt("trickModeClick");
        aobj = (Object[])(Object[])bundle.getSerializable("tvFilesList");
        if(aobj == null) goto _L4; else goto _L3
_L3:
        mMtvFileList = (MtvFile[])Arrays.copyOf(aobj, aobj.length, [Lcom/samsung/sec/mtv/provider/MtvFile;);
        mMtvFile = mMtvFileList[mMtvFileIndex];
_L6:
        if(mMtvFile.getFileFormat() != 2 && mCurrentInSecond >= 0)
        {
            mLocalUiMsgHandler.removeCallbacks(mRunnableUpdateSeekBarTime);
            mLocalUiMsgHandler.postDelayed(mRunnableUpdateSeekBarTime, 0L);
        }
        fragHandler.fillFragHandlerData(bundle);
        isRotated = true;
        if(TrickMode != 0)
        {
            mbIsTrickResume = bundle.getBoolean("trickModeResume");
            mbIsInTrickMode = bundle.getBoolean("trickModeEnabled");
            mLocalUiMsgHandler.post(mRunnableTrickModeNew);
        }
_L10:
        initViewControl();
        if(bundle != null)
            lockImage.setVisibility(bundle.getInt("lockVisibility"));
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext == null || mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LOCALTV)
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLocalTV();
        mMtvAppPlaybackContext.getComponents().getBml().enable();
        if(mBmlSurface != null)
            mBmlSurface.onCreate(mMtvAppPlaybackContext, fragHandler);
        getWindow().addFlags(8192);
        return;
_L4:
        mMtvFile = (MtvFile)getIntent().getSerializableExtra("MtvFile");
        mMtvFileList = MtvFileManager.getAvailableTVRecFilesEx();
        if(mMtvFileList == null || mMtvFileList.length <= 0) goto _L6; else goto _L5
_L5:
        int j = 0;
_L7:
        if(j < mMtvFileList.length)
        {
label0:
            {
                if(mMtvFile.getCreationTime().compareTo(mMtvFileList[j].getCreationTime()) != 0)
                    break label0;
                mMtvFileIndex = j;
            }
        }
          goto _L6
        j++;
          goto _L7
_L2:
        mMtvFile = (MtvFile)getIntent().getSerializableExtra("MtvFile");
        mMtvFileList = MtvFileManager.getAvailableTVRecFilesEx();
        if(mMtvFileList == null || mMtvFileList.length <= 0) goto _L9; else goto _L8
_L8:
        int i = 0;
_L11:
        if(i < mMtvFileList.length)
        {
            if(mMtvFile.getCreationTime().compareTo(mMtvFileList[i].getCreationTime()) != 0)
                break MISSING_BLOCK_LABEL_564;
            mMtvFileIndex = i;
        }
_L9:
        isRotated = false;
          goto _L10
        i++;
          goto _L11
    }

    protected void onDestroy()
    {
        super.onDestroy();
        MtvUtilDebug.Low("MtvUiFilePlayer", "...onDestroy()");
        mLocalUiMsgHandler.removeCallbacks(null);
        mLocalUiMsgHandler.removeCallbacksAndMessages(null);
        if(mPreferences != null)
            mPreferences.setIsFileFormatImage(false);
        mPreferences = null;
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        if(isFinishing())
        {
            TrickMode = 0;
            TrickModeClick = 0;
        }
        fragHandler.reset();
        fragHandler = null;
        mMtvAppPlaybackContext = null;
        mChannelGestureDetector = null;
        mControlAnimationRunnable = null;
        optionsMenu = null;
        MtvUtilAppService.unbindDrawables(findViewById(0x7f0a0018));
        System.gc();
    }

    public void onFragEvent(int i, Object obj)
    {
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onFragEvent: event[").append(i).append("]").toString());
        i;
        JVM INSTR lookupswitch 22: default 224
    //                   200: 225
    //                   201: 1111
    //                   202: 1058
    //                   203: 1179
    //                   223: 390
    //                   224: 447
    //                   225: 469
    //                   226: 359
    //                   227: 259
    //                   228: 1077
    //                   230: 1089
    //                   281: 551
    //                   282: 607
    //                   283: 663
    //                   284: 684
    //                   285: 705
    //                   286: 759
    //                   287: 832
    //                   288: 941
    //                   289: 1050
    //                   290: 1136
    //                   291: 1165;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23
_L1:
        return;
_L2:
        if(fragHandler != null)
        {
            lockImage.setVisibility(8);
            fragHandler.removeFrag(5);
            requestSystemKeyEvent(3, false);
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if(fragHandler != null)
        {
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            {
                fragHandler.resetTimer(14, 6000L);
                fragHandler.resetTimer(15, 6000L);
            }
            if(!fragHandler.isFragPresent(12))
            {
                MtvUiFragHandler mtvuifraghandler = fragHandler;
                int ai[] = new int[1];
                ai[0] = 0x7f0a0027;
                mtvuifraghandler.addFrag(12, 5000L, false, ai);
            } else
            {
                fragHandler.removeFrag(12);
            }
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if(fragHandler != null)
        {
            fragHandler.onUpdate(108, null, 14);
            fragHandler.removeFrag(12);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(fragHandler != null)
        {
            fragHandler.resetTimer(14, -1L);
            mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
            fragHandler.resetTimer(15, -1L);
            fragHandler.resetTimer(12, -1L);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(fragHandler != null)
            fragHandler.onUpdate(108, null, 14);
        continue; /* Loop/switch isn't completed */
_L8:
        if(fragHandler != null)
        {
            fragHandler.resetTimer(14, 6000L);
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
            {
                mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
                mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
            }
            fragHandler.resetTimer(15, 6000L);
            fragHandler.resetTimer(12, 5000L);
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if(TrickMode != 0)
        {
            TrickMode = 0;
            TrickModeClick = 0;
            mbIsInTrickMode = false;
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null)
                    {
                        fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                        fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
        }
        mLocalUiMsgHandler.removeCallbacks(mRunnableUpdateSeekBarTime);
        mLocalUiMsgHandler.sendEmptyMessage(501);
        continue; /* Loop/switch isn't completed */
_L14:
        if(TrickMode != 0)
        {
            TrickMode = 0;
            TrickModeClick = 0;
            mbIsInTrickMode = false;
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null)
                    {
                        fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                        fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
        }
        mLocalUiMsgHandler.removeCallbacks(mRunnableUpdateSeekBarTime);
        mLocalUiMsgHandler.sendEmptyMessage(502);
        continue; /* Loop/switch isn't completed */
_L15:
        mMtvPlayerOneSeg.pause(mMtvAppPlaybackContext);
        showControl();
        continue; /* Loop/switch isn't completed */
_L16:
        mMtvPlayerOneSeg.resume(mMtvAppPlaybackContext);
        showControl();
        continue; /* Loop/switch isn't completed */
_L17:
        if(mMtvPlayerOneSeg.reposition(mMtvAppPlaybackContext, 1000 * ((Integer)obj).intValue()))
        {
            mCurrentInSecond = ((Integer)obj).intValue();
            MtvUtilDebug.Low("MtvUiFilePlayer", "Clear caption in case seek start");
            clearCaptionView();
        }
        continue; /* Loop/switch isn't completed */
_L18:
        fragHandler.resetTimer(14, ((Integer)obj).intValue());
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
        {
            mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
            mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
        }
        fragHandler.resetTimer(15, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L19:
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("Starting trick mode FF: ").append(TrickModeClick).toString());
        mMtvAudMgr.setAudioMute(true);
        if(TrickMode == 2)
        {
            TrickMode = 0;
            TrickModeClick = 0;
        }
        if(2 != TrickModeClick)
            TrickModeClick = 1 + TrickModeClick;
        TrickMode = 1;
        mbIsInTrickMode = true;
        resetVideoFragments(-1);
        clearCaptionView();
        mMtvAudMgr.setAudioEnable(false);
        mLocalUiMsgHandler.post(mRunnableTrickModeNew);
        continue; /* Loop/switch isn't completed */
_L20:
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("Starting trick mode REW: ").append(TrickModeClick).toString());
        mMtvAudMgr.setAudioMute(true);
        if(TrickMode == 1)
        {
            TrickMode = 0;
            TrickModeClick = 0;
        }
        if(2 != TrickModeClick)
            TrickModeClick = 1 + TrickModeClick;
        TrickMode = 2;
        mbIsInTrickMode = true;
        resetVideoFragments(-1);
        clearCaptionView();
        mMtvAudMgr.setAudioEnable(false);
        mLocalUiMsgHandler.post(mRunnableTrickModeNew);
        continue; /* Loop/switch isn't completed */
_L21:
        mbIsTrickResume = true;
        continue; /* Loop/switch isn't completed */
_L4:
        if(mBmlSurface != null)
            mBmlSurface.updateBMLSurfaceView();
        setCaptionViewVisibility();
        continue; /* Loop/switch isn't completed */
_L11:
        fragHandler.removeFrag(12);
        continue; /* Loop/switch isn't completed */
_L12:
        if(fragHandler != null)
            fragHandler.onUpdate(105, null, 15);
        continue; /* Loop/switch isn't completed */
_L3:
        mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(514, (Integer)obj));
        continue; /* Loop/switch isn't completed */
_L22:
        mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
        mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
        continue; /* Loop/switch isn't completed */
_L23:
        mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
        continue; /* Loop/switch isn't completed */
_L5:
        mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(513));
        if(true) goto _L1; else goto _L24
_L24:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        flag = true;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", "onKeyDown");
        if(isPhoneLocked() != flag || i == 26 || i == 24 || i == 25) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiFilePlayer", "Locked Key not supported");
_L4:
        return flag;
_L2:
        if(i == 25 || i == 24)
        {
            if(isPhoneLocked() || 2 == mMtvFile.getFileFormat() || mBmlSurface.IsBMLFullView() || fragHandler.isFragPresent(2))
            {
                if(i == 25)
                    mMtvAudMgr.volumeDown();
                else
                    mMtvAudMgr.volumeUp();
            } else
            {
                startVolumeControlBar();
                keyevent.startTracking();
            }
        } else
        if(i == 79 || i == 85)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = getPlaybackState();
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == state)
                onFragEvent(283, null);
            else
            if(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED == state)
                onFragEvent(284, null);
        } else
        if(i == 87 || i == 88)
        {
            if(getFileFormat() == 2 || isPrevNextEnabled())
                if(i == 87)
                    onFragEvent(281, null);
                else
                    onFragEvent(282, null);
        } else
        if(i == 89 || i == 90)
        {
            if(getPlaybackState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && fragHandler != null)
            {
                fragHandler.onUpdate(117, null, 14);
                if(90 == i)
                    onFragEvent(287, null);
                else
                    onFragEvent(288, null);
            }
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(i == 24)
            mLocalUiMsgHandler.postDelayed(runnableVolumeEscalating, 10L);
        else
        if(i == 25)
            mLocalUiMsgHandler.postDelayed(runnableVolumeDecreasing, 10L);
        else
            flag = super.onKeyLongPress(i, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", "onKeyUp");
        boolean flag;
        if(fragHandler == null)
            flag = true;
        else
        if(isPhoneLocked() && i != 26 && i != 24 && i != 25)
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "Locked Key not supported");
            flag = true;
        } else
        if(i == 26 && !keyevent.isLongPress() && isResumed())
        {
            if(!fragHandler.isFragPresent(5))
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", "onKeyUp, adding touch lock fragment");
                lockImage.setVisibility(0);
                mLocalUiMsgHandler.post(mRunnableUpdateFragsBasedOnLockState);
                fragHandler.addFrag(5, -1L, false, new int[0]);
                mBmlSurface.setPhoneLockedState(true);
                requestSystemKeyEvent(3, true);
            } else
            {
                MtvUtilDebug.Low("MtvUiFilePlayer", "onKeyUp, removing touch lock fragment");
                lockImage.setVisibility(8);
                fragHandler.onUpdate(108, null, 14);
                mLocalUiMsgHandler.post(mRunnableUpdateFragsBasedOnLockState);
                fragHandler.removeFrag(5);
                mBmlSurface.setPhoneLockedState(false);
                requestSystemKeyEvent(3, false);
                showControl();
            }
            flag = true;
        } else
        if(i == 24 || i == 25)
        {
            if(!fragHandler.isPhoneLocked() && 2 != mMtvFile.getFileFormat() && !mBmlSurface.IsBMLFullView() && !fragHandler.isFragPresent(2))
                if(i == 24)
                {
                    mLocalUiMsgHandler.removeCallbacks(runnableVolumeEscalating);
                    fragHandler.onUpdate(106, null, 12);
                } else
                {
                    mLocalUiMsgHandler.removeCallbacks(runnableVolumeDecreasing);
                    fragHandler.onUpdate(107, null, 12);
                }
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiFilePlayer", "Super called for key");
            flag = super.onKeyUp(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 9 23: default 80
    //                   9 117
    //                   10 102
    //                   11 92
    //                   12 82
    //                   13 80
    //                   14 148
    //                   15 80
    //                   16 80
    //                   17 80
    //                   18 208
    //                   19 162
    //                   20 80
    //                   21 80
    //                   22 254
    //                   23 305;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L1 _L1 _L1 _L7 _L8 _L1 _L1 _L9 _L10
_L1:
        return true;
_L5:
        mMtvAudMgr.transferToPhone();
        continue; /* Loop/switch isn't completed */
_L4:
        mMtvAudMgr.transferToBT();
        continue; /* Loop/switch isn't completed */
_L3:
        if(mBmlSurface != null)
            mBmlSurface.openBMLHomePage();
        continue; /* Loop/switch isn't completed */
_L2:
        if(mBmlSurface != null)
            if(!mBmlSurface.IsBMLFullView())
                setBMLLayoutFullView(true);
            else
                setBMLLayoutFullView(false);
        continue; /* Loop/switch isn't completed */
_L6:
        if(mBmlSurface != null)
            setBMLLayoutFullView(false);
        continue; /* Loop/switch isn't completed */
_L8:
        if(fragHandler != null)
        {
            MtvUiFragHandler mtvuifraghandler1 = fragHandler;
            int ai1[] = new int[2];
            ai1[0] = 0x7f0a0019;
            ai1[1] = 19;
            mtvuifraghandler1.addFrag(2, -1L, true, ai1);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(fragHandler != null)
        {
            MtvUiFragHandler mtvuifraghandler = fragHandler;
            int ai[] = new int[2];
            ai[0] = 0x7f0a0019;
            ai[1] = 18;
            mtvuifraghandler.addFrag(2, -1L, true, ai);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        Bundle bundle = new Bundle();
        bundle.putBoolean("expire", false);
        bundle.putInt("remainTime", mPreferences.getAutoPowerOffTime());
        MtvUiSleepTimerDialogFrag.newInstance(bundle).show(getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L10:
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiSettingsBroadcast"));
        startActivity(intent);
        if(true) goto _L1; else goto _L11
_L11:
    }

    public void onOptionsMenuClosed(Menu menu)
    {
        optionsMenu = null;
        super.onOptionsMenuClosed(menu);
    }

    protected void onPause()
    {
        String s = null;
        ActivityManager activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        super.onPause();
        if(mMtvFile != null)
        {
            if(mBmlSurface != null)
                mBmlSurface.onPause();
            registerVideoSurfaceView(false);
        }
        if(s != null)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onPause() :topActivityName = ").append(s).toString());
            if(!s.contains("com.samsung.sec.mtv") && !isFinishing() && com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == getPlaybackState())
                onFragEvent(283, null);
        }
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onPlayerNotification ...:command : ").append(i).append(" status : ").append(j).toString());
        i;
        JVM INSTR tableswitch 20482 20492: default 96
    //                   20482 97
    //                   20483 383
    //                   20484 132
    //                   20485 96
    //                   20486 96
    //                   20487 96
    //                   20488 96
    //                   20489 362
    //                   20490 362
    //                   20491 488
    //                   20492 415;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L1 _L1 _L5 _L5 _L6 _L7
_L1:
        return;
_L2:
        if(24577 == j)
            mLocalUiMsgHandler.sendMessageAtFrontOfQueue(mLocalUiMsgHandler.obtainMessage(511, getString(0x7f070086)));
        continue; /* Loop/switch isn't completed */
_L4:
        if(24581 == j)
        {
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiFilePlayer.mMtvFile.getFileFormat() != 2 && fragHandler != null)
                        fragHandler.onUpdate(125, null, 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
            if(!mbIsInTrickMode)
                MtvUtilAudioManager.getInstance(getApplicationContext()).setAudioMute(false);
            mMtvAudMgr.updateCurrentAudioEffectAndMode();
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(!MtvUiFilePlayer.mBmlSurface.IsBMLFullView())
                    {
                        showControl();
                        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
                        {
                            mLocalUiMsgHandler.removeCallbacks(mRunnableHideProgressBarInPortrait);
                            mLocalUiMsgHandler.postDelayed(mRunnableHideProgressBarInPortrait, 5000L);
                        }
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
            mLocalUiMsgHandler.sendEmptyMessage(503);
            mLocalUiMsgHandler.removeCallbacks(mRunnableUpdateSeekBarTime);
            mLocalUiMsgHandler.postDelayed(mRunnableUpdateSeekBarTime, 0L);
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(511, null));
        } else
        if(24592 == j)
        {
            if(TrickMode != 0)
                mLocalUiMsgHandler.sendEmptyMessage(509);
            runOnUiThread(new Runnable() {

                public void run()
                {
                    onFragEvent(281, null);
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
        } else
        if(24580 == j)
            finish();
        else
        if(24593 == j)
        {
            MtvUtilDebug.Error("MtvUiFilePlayer", "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
            if(!MtvUtilDebug.isReleaseMode())
            {
                Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
                toast.setGravity(119, 0, 0);
                toast.show();
            }
            sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"));
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(24581 == j)
            mLocalUiMsgHandler.sendEmptyMessage(503);
        continue; /* Loop/switch isn't completed */
_L3:
        if(j == 24589)
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(508, getCaptionText()));
        continue; /* Loop/switch isn't completed */
_L7:
        if(j == 24577)
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null)
                        fragHandler.onUpdate(507, Boolean.valueOf(false), 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
        else
        if(j == 24581)
        {
            if(k >= 0)
            {
                final Integer updateTime = Integer.valueOf(k);
                mCurrentInSecond = k;
                runOnUiThread(new Runnable() {

                    public void run()
                    {
                        updateVidFragCurrTime(updateTime.intValue());
                    }

                    final MtvUiFilePlayer this$0;
                    final Integer val$updateTime;

            
            {
                this$0 = MtvUiFilePlayer.this;
                updateTime = integer;
                super();
            }
                });
            }
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null)
                        fragHandler.onUpdate(507, Boolean.valueOf(true), 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
        }
        if(true) goto _L1; else goto _L6
_L6:
        switch(j)
        {
        case 24577: 
            mLocalUiMsgHandler.sendEmptyMessage(503);
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null && !isBmlFullView())
                    {
                        fragHandler.onUpdate(121, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                        fragHandler.onUpdate(123, Integer.valueOf(MtvUiFilePlayer.TrickModeClick), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                this$0 = MtvUiFilePlayer.this;
                super();
            }
            });
            break;

        case 24581: 
            mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(510, Integer.valueOf(k)));
            break;
        }
        if(true) goto _L1; else goto _L8
_L8:
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean flag = true;
        boolean flag1 = isBmlFullView();
        int i = mMtvFile.getFileFormat();
        optionsMenu = menu;
        menu.clear();
        if(!fragHandler.isFragPresent(2))
        {
            if(i != 2)
            {
                if(!mMtvAudMgr.isHeadsetConnected())
                    if(mMtvAudMgr.isBTConnected() == flag)
                        menu.add(0, 12, 0, 0x7f070098).setIcon(0x7f020108);
                    else
                        menu.add(0, 11, 0, 0x7f070099).setIcon(0x7f020108);
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0 || flag1)
                {
                    menu.add(0, 10, 0, 0x7f070097).setIcon(0x7f020107);
                    SubMenu submenu;
                    if(!flag1)
                        menu.add(0, 9, 0, 0x7f070096).setIcon(0x7f0200fb);
                    else
                        menu.add(0, 14, 0, 0x7f070037).setIcon(0x7f0200fc);
                }
            }
            if(i == 2 || i != 2 && !flag1)
            {
                submenu = menu.addSubMenu(0, 8, 0, 0x7f07008e).setIcon(0x7f020105);
                submenu.add(0, 19, 0, 0x7f07008f);
                submenu.add(0, 18, 0, 0x7f070090);
                submenu.add(0, 20, 0, 0x7f070091).setEnabled(false);
                submenu.add(0, 22, 0, 0x7f070092);
                submenu.add(0, 23, 0, 0x7f070093);
            }
            flag = super.onPrepareOptionsMenu(menu);
        }
        return flag;
    }

    protected void onResume()
    {
        super.onResume();
        MtvUtilDebug.Low("MtvUiFilePlayer", "onResume...");
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        fragHandler.setEnabled(true);
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiFilePlayer", "isExiting...");
        finish();
_L4:
        return;
_L2:
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        initMtvFilePlayer();
        if(mMtvFile != null && mBmlSurface != null)
        {
            int i = mMtvFile.getIndex();
            MtvURI mtvuri;
            if(mMtvFile.getFileFormat() == 2)
            {
                mtvuri = new MtvURI(5, null, i);
                mPreferences.setIsFileFormatImage(true);
            } else
            {
                mtvuri = new MtvURI(1, null, i);
                mPreferences.setIsFileFormatImage(false);
            }
            mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLocalTV(), mtvuri, this);
            mBmlSurface.onResume(mMtvAppPlaybackContext);
        }
        if(!isRotated)
            showControl();
        mLocalUiMsgHandler.post(mRunnableUpdateFragsBasedOnLockState);
        requestSystemKeyEvent(26, true);
        if(mMtvAudMgr != null)
        {
            if(TrickMode == 0)
                mMtvAudMgr.stopOtherSound();
            mMtvAudMgr.setAvStreaming(true);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        MtvUtilDebug.Low("MtvUiFilePlayer", "onSaveInstanceState");
        if(!isFinishing())
        {
            fragHandler.setEnabled(false);
            fragHandler.putFragHandlerData(bundle);
            bundle.putInt("lockVisibility", lockImage.getVisibility());
            bundle.putInt("fileUpdateTime", mCurrentInSecond);
            bundle.putInt("trickMode", TrickMode);
            bundle.putInt("trickModeClick", TrickModeClick);
            bundle.putBoolean("trickModeEnabled", mbIsInTrickMode);
            bundle.putBoolean("trickModeResume", mbIsTrickResume);
            bundle.putInt("fileIndex", mMtvFileIndex);
            bundle.putSerializable("tvFilesList", mMtvFileList);
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        if(mMtvFile != null && mBmlSurface != null)
            mBmlSurface.onStart(fragHandler);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    protected void onStop()
    {
        super.onStop();
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        if(mMtvFile != null && mBmlSurface != null)
            mBmlSurface.onStop();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(!mBmlSurface.IsBMLFullView() && mChannelGestureDetector.onTouchEvent(motionevent))
            flag = true;
        return flag;
    }

    public void onWindowFocusChanged(boolean flag)
    {
        if(flag)
        {
            requestSystemKeyEvent(26, true);
            if(fragHandler != null)
                fragHandler.onUpdate(108, null, 14);
        } else
        {
            requestSystemKeyEvent(26, false);
        }
    }

    public void setBMLLayoutFullView(boolean flag)
    {
        View view = findViewById(0x7f0a0028);
        if(view == null || mBmlSurface == null) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        fragHandler.onUpdate(119, null, 14);
        view.setVisibility(8);
        mBmlSurface.setBMLFullView(true);
        setRequestedOrientation(1);
        registerVideoSurfaceView(false);
        mCaptionView.setVisibility(8);
_L2:
        return;
_L4:
        fragHandler.onUpdate(120, null, 14);
        view.setVisibility(0);
        mBmlSurface.setBMLFullView(false);
        setRequestedOrientation(4);
        registerVideoSurfaceView(true);
        if(mPreferences.isCaptionEnabled())
        {
            mCaptionView.setVisibility(0);
            if(getCaptionText() != null)
                mCaptionView.setText(getCaptionText());
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static int TrickMode = 0;
    private static int TrickModeClick = 0;
    private static MtvUiBmlSurfaceView mBmlSurface = null;
    private static MtvFile mMtvFile = null;
    private static int mMtvFileIndex = 0;
    private static NotificationManager mNotificationManager = null;
    private MtvUiFragHandler fragHandler;
    private boolean isRotated;
    private onMtvAppAndroidServiceListener listener;
    private ImageView lockImage;
    private ImageView mAnimationImage;
    private RelativeLayout mAnimationlayout;
    private TextView mCaptionView;
    private GestureDetector mChannelGestureDetector;
    private ServiceConnection mConnection;
    private ControlAnimationRunnable mControlAnimationRunnable;
    private int mCurrentInSecond;
    private SurfaceView mHiddenSurfaceView;
    private ImageView mImage;
    private ImageView mImgViewNoChannel;
    private AbsoluteLayout mImgViewerScreen;
    private Handler mLocalUiMsgHandler;
    private SurfaceView mLocalVideoSurfaceView;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvUtilAudioManager mMtvAudMgr;
    private MtvFile mMtvFileList[];
    private IMtvAppPlayerOneSeg mMtvPlayerOneSeg;
    private MtvPreferences mPreferences;
    private Runnable mRunnableHideProgressBarInPortrait;
    private Runnable mRunnableTrickModeNew;
    private Runnable mRunnableUpdateFragsBasedOnLockState;
    private Runnable mRunnableUpdateSeekBarTime;
    private MtvAppAndroidService mService;
    private TextView mTxtAnimation;
    private AbsoluteLayout mVidViewerScreen;
    private boolean mbIsInTrickMode;
    private boolean mbIsTrickResume;
    private Menu optionsMenu;
    private Runnable postHeadsetUpdate;
    private Runnable runnableVolumeDecreasing;
    private Runnable runnableVolumeEscalating;
    private int timeCalculatedForTrick;






/*
    static boolean access$102(MtvUiFilePlayer mtvuifileplayer, boolean flag)
    {
        mtvuifileplayer.mbIsInTrickMode = flag;
        return flag;
    }

*/







/*
    static int access$1502(int i)
    {
        TrickMode = i;
        return i;
    }

*/



/*
    static int access$1602(int i)
    {
        TrickModeClick = i;
        return i;
    }

*/







/*
    static int access$202(MtvUiFilePlayer mtvuifileplayer, int i)
    {
        mtvuifileplayer.mCurrentInSecond = i;
        return i;
    }

*/


/*
    static int access$204(MtvUiFilePlayer mtvuifileplayer)
    {
        int i = 1 + mtvuifileplayer.mCurrentInSecond;
        mtvuifileplayer.mCurrentInSecond = i;
        return i;
    }

*/



/*
    static boolean access$2102(MtvUiFilePlayer mtvuifileplayer, boolean flag)
    {
        mtvuifileplayer.mbIsTrickResume = flag;
        return flag;
    }

*/







/*
    static MtvPreferences access$2602(MtvUiFilePlayer mtvuifileplayer, MtvPreferences mtvpreferences)
    {
        mtvuifileplayer.mPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/

















/*
    static MtvAppAndroidService access$502(MtvUiFilePlayer mtvuifileplayer, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuifileplayer.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/




/*
    static ControlAnimationRunnable access$702(MtvUiFilePlayer mtvuifileplayer, ControlAnimationRunnable controlanimationrunnable)
    {
        mtvuifileplayer.mControlAnimationRunnable = controlanimationrunnable;
        return controlanimationrunnable;
    }

*/


}
