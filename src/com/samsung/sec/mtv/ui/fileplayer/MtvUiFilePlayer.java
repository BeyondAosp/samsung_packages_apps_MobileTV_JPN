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
import android.util.Log;
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ChannelGestureListener;->onFling(Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z");
            flag = false;
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("GestrueDetectorUiPlayer : onFling called ").append(f).toString());
            if(Log.d(MtvUiFilePlayer.this).getVisibility() != 0) goto _L2; else goto _L1
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
            if(Log.d().getFileFormat() == 2 || Log.d(MtvUiFilePlayer.this))
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ChannelGestureListener;->onSingleTapConfirmed(Landroid/view/MotionEvent;)Z");
            if(Log.d(MtvUiFilePlayer.this) == null || Log.d(MtvUiFilePlayer.this).isPhoneLocked()) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            i = Log.d().getFileFormat();
            j = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
            if(2 != i) goto _L4; else goto _L3
_L3:
            if(Log.d(MtvUiFilePlayer.this).isFragPresent(8))
                Log.d(MtvUiFilePlayer.this).removeFrag(8);
            if(!Log.d(MtvUiFilePlayer.this).isFragPresent(15))
            {
                Log.d(MtvUiFilePlayer.this).addFrag(15, 5000L, false, new int[0]);
                Log.d(MtvUiFilePlayer.this).sendEmptyMessage(500);
                Log.d(MtvUiFilePlayer.this).addFrag(13, 5000L, false, new int[0]);
            } else
            {
                Log.d(MtvUiFilePlayer.this).removeFrag(15);
                Log.d(MtvUiFilePlayer.this).removeFrag(13);
            }
_L2:
            return false;
_L4:
            if(!Log.d(MtvUiFilePlayer.this).isFragPresent(15))
            {
                if(j == 1)
                    Log.d(MtvUiFilePlayer.this).addFrag(15, 5000L, false, new int[0]);
                else
                    Log.d(MtvUiFilePlayer.this).addFrag(15, -1L, false, new int[0]);
                Log.d(MtvUiFilePlayer.this).sendEmptyMessage(500);
            } else
            if(j == 1)
                Log.d(MtvUiFilePlayer.this).removeFrag(15);
            if(Log.d(MtvUiFilePlayer.this).isFragPresent(14))
                break; /* Loop/switch isn't completed */
            if(j == 1)
            {
                Log.d(MtvUiFilePlayer.this).addFrag(14, 5000L, false, new int[0]);
            } else
            {
                Log.d(MtvUiFilePlayer.this).addFrag(14, -1L, false, new int[0]);
                Log.d(MtvUiFilePlayer.this).removeCallbacks(Log.d(MtvUiFilePlayer.this));
                Log.d(MtvUiFilePlayer.this).postDelayed(Log.d(MtvUiFilePlayer.this), 5000L);
            }
_L6:
            if(Log.d(MtvUiFilePlayer.this).isFragPresent(12) && j == 1)
                Log.d(MtvUiFilePlayer.this).removeFrag(12);
            if(true) goto _L2; else goto _L5
_L5:
            if(j == 1)
                Log.d(MtvUiFilePlayer.this).removeFrag(14);
            else
            if(Log.d() == 0)
                Log.d(MtvUiFilePlayer.this).onUpdate(118, null, 14);
              goto _L6
            if(true) goto _L2; else goto _L7
_L7:
        }

        final MtvUiFilePlayer this$0;

        ChannelGestureListener()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ChannelGestureListener;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
            this$0 = MtvUiFilePlayer.this;
            super();
        }
    }

    class ControlAnimationRunnable
        implements Runnable
    {

        private void controlAnimation()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;->controlAnimation()V");
            mAnimationDrawable = (AnimationDrawable)Log.d(MtvUiFilePlayer.this).getBackground();
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("controlAnimation: called :").append(isEnable).toString());
            if(isEnable)
            {
                Log.d(MtvUiFilePlayer.this).setVisibility(0);
                Log.d(MtvUiFilePlayer.this).setVisibility(0);
                if(!mAnimationDrawable.isRunning())
                    mAnimationDrawable.start();
                else
                    mAnimationDrawable.start();
                if(mStrAnimation != null)
                    Log.d(MtvUiFilePlayer.this).setText(mStrAnimation);
            } else
            {
                if(mAnimationDrawable.isRunning())
                {
                    mAnimationDrawable.stop();
                    Log.d(MtvUiFilePlayer.this).setText("");
                    Log.d(MtvUiFilePlayer.this).setVisibility(4);
                    Log.d(MtvUiFilePlayer.this).setVisibility(4);
                }
                Log.d(MtvUiFilePlayer.this).setVisibility(4);
            }
        }

        public void postAnimationToRunInUIThread()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;->postAnimationToRunInUIThread()V");
            Log.d(MtvUiFilePlayer.this).post(this);
        }

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;->run()V");
            controlAnimation();
        }

        public void setAnimationEnable(boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;->setAnimationEnable(Z)V");
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationEnable :").append(flag).toString());
            isEnable = flag;
        }

        public void setAnimationText(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;->setAnimationText(Ljava/lang/String;)V");
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationText :").append(s).toString());
            mStrAnimation = s;
        }

        private boolean isEnable;
        private AnimationDrawable mAnimationDrawable;
        private String mStrAnimation;
        final MtvUiFilePlayer this$0;

        public ControlAnimationRunnable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
            this$0 = MtvUiFilePlayer.this;
            super();
            isEnable = false;
            mStrAnimation = null;
            MtvUtilDebug.Low("MtvUiFilePlayer", "ControlAnimationRunnable...");
        }
    }


    public MtvUiFilePlayer()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;-><init>()V");
        super();
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
        mRunnableUpdateSeekBarTime = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$1;->run()V");
                if(Log.d(MtvUiFilePlayer.this) == null) goto _L2; else goto _L1
_L1:
                if(Log.d(MtvUiFilePlayer.this) || getPlayerCommand() == 20492 || getPlaybackState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING) goto _L4; else goto _L3
_L3:
                Log.d(MtvUiFilePlayer.this).onUpdate(505, Integer.valueOf(Log.d(MtvUiFilePlayer.this)), 14);
_L2:
                Log.d(MtvUiFilePlayer.this).postDelayed(this, 1000L);
                return;
_L4:
                if(!Log.d(MtvUiFilePlayer.this) && getPlayerCommand() != 20492 && getPlaybackState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED)
                    Log.d(MtvUiFilePlayer.this).onUpdate(505, Integer.valueOf(Log.d(MtvUiFilePlayer.this)), 14);
                if(true) goto _L2; else goto _L5
_L5:
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$1;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mRunnableHideProgressBarInPortrait = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$2;->run()V");
                if(Log.d(MtvUiFilePlayer.this) != null)
                    Log.d(MtvUiFilePlayer.this).onUpdate(122, null, 14);
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$2;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mRunnableUpdateFragsBasedOnLockState = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$3;->run()V");
                Log.d(MtvUiFilePlayer.this);
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$3;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$4;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low("MtvUiFilePlayer", "onServiceConnected...");
                Log.d(MtvUiFilePlayer.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiFilePlayer.this).registerListener(Log.d(MtvUiFilePlayer.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$4;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiFilePlayer.this).unregisterListener(Log.d(MtvUiFilePlayer.this));
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$4;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onMtvAppAndroidServiceNotify called: what=").append(i).toString());
                i;
                JVM INSTR lookupswitch 2: default 60
            //                           2: 89
            //                           9: 61;
                   goto _L1 _L2 _L3
_L1:
                return;
_L3:
                Log.d(MtvUiFilePlayer.this).sendMessage(Log.d(MtvUiFilePlayer.this).obtainMessage(512, obj));
                continue; /* Loop/switch isn't completed */
_L2:
                Log.d(MtvUiFilePlayer.this).sendMessage(Log.d(MtvUiFilePlayer.this).obtainMessage(515, obj));
                if(true) goto _L1; else goto _L4
_L4:
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                Log.d(MtvUiFilePlayer.this).removeCallbacks(null);
                Log.d(MtvUiFilePlayer.this).removeCallbacksAndMessages(null);
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5$1;->run()V");
                        finish();
                    }

                    final _cls5 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5$1;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5;)V");
                            this$1 = _cls5.this;
                            super();
                        }
                }

                Log.d(MtvUiFilePlayer.this).post(new _cls1());
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$5;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mLocalUiMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$6;->handleMessage(Landroid/os/Message;)V");
                MtvUtilDebug.Mid("MtvUiFilePlayer", (new StringBuilder()).append("handleMessage : ").append(message.what).toString());
                message.what;
                JVM INSTR lookupswitch 13: default 152
            //                           112: 1365
            //                           500: 256
            //                           501: 290
            //                           502: 370
            //                           503: 450
            //                           508: 487
            //                           509: 511
            //                           510: 694
            //                           511: 153
            //                           512: 1066
            //                           513: 1038
            //                           514: 1018
            //                           515: 1244;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
                return;
_L10:
                String s = null;
                if(message.obj != null)
                    s = (String)message.obj;
                if(Log.d(MtvUiFilePlayer.this) == null)
                    Log.d(MtvUiFilePlayer.this, new ControlAnimationRunnable());
                if(s != null)
                {
                    Log.d(MtvUiFilePlayer.this).setAnimationEnable(true);
                    Log.d(MtvUiFilePlayer.this).setAnimationText(s);
                } else
                {
                    Log.d(MtvUiFilePlayer.this).setAnimationEnable(false);
                }
                Log.d(MtvUiFilePlayer.this).postAnimationToRunInUIThread();
                continue; /* Loop/switch isn't completed */
_L3:
                if(Log.d(MtvUiFilePlayer.this) != null)
                    Log.d(MtvUiFilePlayer.this).onUpdate(101, Log.d(MtvUiFilePlayer.this), 15);
                continue; /* Loop/switch isn't completed */
_L4:
                Log.d(MtvUiFilePlayer.this);
                Log.d(MtvUiFilePlayer.this);
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(101, Log.d(MtvUiFilePlayer.this), 15);
                    if(Log.d().getFileFormat() != 2)
                        Log.d(MtvUiFilePlayer.this).onUpdate(114, null, 14);
                }
                Log.d(MtvUiFilePlayer.this);
                continue; /* Loop/switch isn't completed */
_L5:
                Log.d(MtvUiFilePlayer.this);
                Log.d(MtvUiFilePlayer.this);
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(101, Log.d(MtvUiFilePlayer.this), 15);
                    if(Log.d().getFileFormat() != 2)
                        Log.d(MtvUiFilePlayer.this).onUpdate(114, null, 14);
                }
                Log.d(MtvUiFilePlayer.this);
                continue; /* Loop/switch isn't completed */
_L6:
                if(Log.d(MtvUiFilePlayer.this) != null)
                    Log.d(MtvUiFilePlayer.this).onUpdate(113, Boolean.valueOf(Log.d(MtvUiFilePlayer.this)), 14);
                continue; /* Loop/switch isn't completed */
_L7:
                SpannableStringBuilder spannablestringbuilder = (SpannableStringBuilder)message.obj;
                Log.d(MtvUiFilePlayer.this).setText(spannablestringbuilder);
                continue; /* Loop/switch isn't completed */
_L8:
                MtvUtilDebug.Low("MtvUiFilePlayer", "Stopping trick mode");
                Log.d(0);
                Log.d(0);
                Log.d(MtvUiFilePlayer.this, 0);
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                    Log.d(MtvUiFilePlayer.this, 5000);
                else
                    Log.d(MtvUiFilePlayer.this, -1);
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                    Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                }
                Log.d(MtvUiFilePlayer.this).setAudioMute(false);
                Log.d(MtvUiFilePlayer.this).trickmode(Log.d(MtvUiFilePlayer.this), 0, -1);
                if(Log.d(MtvUiFilePlayer.this) != null)
                    Log.d(MtvUiFilePlayer.this).onUpdate(113, Boolean.valueOf(Log.d(MtvUiFilePlayer.this)), 14);
                continue; /* Loop/switch isn't completed */
_L9:
                MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("UI_FILE_PLAYER_CHECK_TRICK TrickMode ").append(Log.d()).append(" mbIsTrickResume ").append(Log.d(MtvUiFilePlayer.this)).toString());
                if(Log.d() != 0)
                {
                    final Integer updateTime = (Integer)message.obj;
                    if(updateTime.intValue() >= 0)
                    {
                        if(1 + updateTime.intValue() == Log.d(MtvUiFilePlayer.this))
                        {
                            Log.d(MtvUiFilePlayer.this, updateTime.intValue());
                            class _cls1
                                implements Runnable
                            {

                                public void run()
                                {
                                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$6$1;->run()V");
                                    Log.d(_fld0, updateTime.intValue());
                                }

                                final _cls6 this$1;
                                final Integer val$updateTime;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$6$1;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$6;Ljava/lang/Integer;)V");
                            this$1 = _cls6.this;
                            updateTime = integer;
                            super();
                        }
                            }

                            runOnUiThread(new _cls1());
                        }
                        if(!Log.d(MtvUiFilePlayer.this))
                        {
                            Log.d(MtvUiFilePlayer.this).post(Log.d(MtvUiFilePlayer.this));
                        } else
                        {
                            MtvUtilDebug.High("MtvUiFilePlayer", "Resume Pressed -- Posting FILE_PLAYER_STOP_TRICK ");
                            Log.d(MtvUiFilePlayer.this, 0);
                            Log.d(MtvUiFilePlayer.this).sendEmptyMessage(509);
                        }
                    } else
                    {
                        Log.d(0);
                        Log.d(0);
                        Log.d(MtvUiFilePlayer.this, 0);
                        if(Log.d(MtvUiFilePlayer.this) != null)
                        {
                            Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                            Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                        }
                    }
                } else
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(113, Boolean.valueOf(Log.d(MtvUiFilePlayer.this)), 14);
                    Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                    Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                }
                continue; /* Loop/switch isn't completed */
_L13:
                Log.d(MtvUiFilePlayer.this, ((Integer)message.obj).intValue());
                continue; /* Loop/switch isn't completed */
_L12:
                if(Log.d(MtvUiFilePlayer.this) != null)
                    Log.d(MtvUiFilePlayer.this).onUpdate(104, null, 15);
                continue; /* Loop/switch isn't completed */
_L11:
                closeOptionsMenu();
                if(message.obj != null)
                {
                    int k = ((Intent)message.obj).getIntExtra("state", 0);
                    if(k == 0)
                    {
                        if(Log.d(MtvUiFilePlayer.this) == null)
                            Log.d(MtvUiFilePlayer.this, new MtvPreferences(getApplicationContext()));
                        Log.d(MtvUiFilePlayer.this).setAudio51Enabled(false);
                        if(Log.d(MtvUiFilePlayer.this) != null && Log.d(MtvUiFilePlayer.this).isFragPresent(15))
                            Log.d(MtvUiFilePlayer.this).onUpdate(104, null, 15);
                        if(Log.d(MtvUiFilePlayer.this) != null && Log.d(MtvUiFilePlayer.this).isFragPresent(2))
                            Log.d(MtvUiFilePlayer.this).onUpdate(109, Integer.valueOf(k), 2);
                    }
                }
                postDelayed(Log.d(MtvUiFilePlayer.this), 300L);
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
                    if(Log.d(MtvUiFilePlayer.this) != null)
                        Log.d(MtvUiFilePlayer.this).onUpdate(102, null, 15);
                }
                continue; /* Loop/switch isn't completed */
_L2:
                if(message.obj != null)
                {
                    boolean flag;
                    if(Log.d(MtvUiFilePlayer.this).getVisibility() != 0)
                        flag = true;
                    else
                        flag = false;
                    if(Log.d(MtvUiFilePlayer.this) != null)
                        Log.d(MtvUiFilePlayer.this).onUpdate(112, Boolean.valueOf(flag), ((Integer)message.obj).intValue());
                }
                if(true) goto _L1; else goto _L15
_L15:
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$6;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        postHeadsetUpdate = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$7;->run()V");
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(108, null, 12);
                    Log.d(MtvUiFilePlayer.this).onUpdate(108, null, 14);
                    MtvUtilAudioManager.getInstance(getApplicationContext()).updateCurrentAudioEffectAndMode();
                }
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$7;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        mRunnableTrickModeNew = new Runnable() {

            public void run()
            {
                int i;
                int j;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$8;->run()V");
                boolean flag = true;
                i = 1000 * Log.d(MtvUiFilePlayer.this);
                j = getFileTotalTime();
                boolean flag1;
                boolean flag2;
                boolean flag3;
                if(Log.d() != 0)
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
                Log.d(MtvUiFilePlayer.this).trickmode(Log.d(MtvUiFilePlayer.this), 2, i);
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$8;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        runnableVolumeEscalating = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$18;->run()V");
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(106, null, 0);
                    Log.d(MtvUiFilePlayer.this).onUpdate(106, null, 12);
                    Log.d(MtvUiFilePlayer.this).onUpdate(106, null, 1);
                }
                if(Log.d(MtvUiFilePlayer.this) != null && Log.d(MtvUiFilePlayer.this).getVolumeLevel() != 15)
                    Log.d(MtvUiFilePlayer.this).postDelayed(Log.d(MtvUiFilePlayer.this), 100L);
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$18;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
        runnableVolumeDecreasing = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$19;->run()V");
                if(Log.d(MtvUiFilePlayer.this) != null)
                {
                    Log.d(MtvUiFilePlayer.this).onUpdate(107, null, 0);
                    Log.d(MtvUiFilePlayer.this).onUpdate(107, null, 12);
                    Log.d(MtvUiFilePlayer.this).onUpdate(107, null, 1);
                }
                if(Log.d(MtvUiFilePlayer.this) != null && Log.d(MtvUiFilePlayer.this).getVolumeLevel() != 0)
                    Log.d(MtvUiFilePlayer.this).postDelayed(Log.d(MtvUiFilePlayer.this), 100L);
            }

            final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$19;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
                this$0 = MtvUiFilePlayer.this;
                super();
            }
        };
    }

    private int calculateSeekToValue()
    {
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->calculateSeekToValue()I");
        i = getCurrentFileTime();
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->clearCaptionView()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->clearNotification()V");
        MtvUtilDebug.Low("MtvUiFilePlayer", "clearNotification...");
        mNotificationManager.cancel(0x7f020113);
    }

    private void drawImageFile()
    {
        FileInputStream fileinputstream;
        ByteBuffer bytebuffer;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->drawImageFile()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getCaptionText()Landroid/text/SpannableStringBuilder;");
        SpannableStringBuilder spannablestringbuilder = null;
        if(mMtvAppPlaybackContext != null)
            spannablestringbuilder = mMtvAppPlaybackContext.getComponents().getCaption().getBuffer();
        return spannablestringbuilder;
    }

    private SurfaceView getLocalVideoSurfaceView(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getLocalVideoSurfaceView(Z)Landroid/view/SurfaceView;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getStatusBarText()Ljava/lang/String;");
        String s;
        if(mMtvFile.getProgramName() == null || mMtvFile.getProgramName().equals("null"))
            s = getResources().getString(0x7f0700ce);
        else
            s = mMtvFile.getProgramName();
        return (new StringBuilder()).append(mMtvFile.getChannelName()).append("\n").append(s).toString();
    }

    private void initMtvFilePlayer()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->initMtvFilePlayer()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->initViewControl()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->invalidateViews()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->isPrevNextEnabled()Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->loadCurrentFileUI()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->loadImageFile()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->loadNextFile()V");
        if(mMtvFileIndex == -1 + mMtvFileList.length)
            mMtvFileIndex = 0;
        else
            mMtvFileIndex = 1 + mMtvFileIndex;
        mMtvFile = mMtvFileList[mMtvFileIndex];
    }

    private void loadPrevFile()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->loadPrevFile()V");
        if(mMtvFileIndex == 0)
            mMtvFileIndex = -1 + mMtvFileList.length;
        else
            mMtvFileIndex = -1 + mMtvFileIndex;
        mMtvFile = mMtvFileList[mMtvFileIndex];
    }

    private void loadVideoFile()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->loadVideoFile()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->postLockUpdate(I)V");
        mLocalUiMsgHandler.sendMessage(mLocalUiMsgHandler.obtainMessage(112, Integer.valueOf(i)));
    }

    private void registerVideoSurfaceView(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->registerVideoSurfaceView(Z)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->requestSystemKeyEvent(IZ)Z");
        boolean flag1 = true;
        android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        if(!MtvUtilDebug.isReleaseMode())
        {
            flag1 = "requestSystemKeyEvent";
            MtvUtilDebug.Low("MtvUiFilePlayer", flag1);
        }
        try
        {
            getComponentName();
            return flag1;
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        return false;
    }

    private void resetVideoFragments(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->resetVideoFragments(I)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setActivityProperty()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setCaptionViewVisibility()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setImageViewDuringNoChannel(Z)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setScreen()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setScreenRatio(I)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->showControl()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->startVolumeControlBar()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->updateFragsBasedOnLockState()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->updateVidFragCurrTime(I)V");
        Bundle bundle = new Bundle();
        bundle.putInt("fileUpdateTime", i);
        bundle.putInt("fileIndex", mMtvFile.getIndex());
        if(fragHandler != null)
            fragHandler.onUpdate(506, bundle, 14);
    }

    public int getCurrentFileIndex()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getCurrentFileIndex()I");
        return mMtvFile.getIndex();
    }

    public int getCurrentFileTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getCurrentFileTime()I");
        return mCurrentInSecond;
    }

    public int getFileFormat()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getFileFormat()I");
        int i;
        if(mMtvFile != null)
            i = mMtvFile.getFileFormat();
        else
            i = -1;
        return i;
    }

    public int getFileTotalTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getFileTotalTime()I");
        int i;
        if(mMtvFile != null)
            i = mMtvFile.getDurationOfRecording();
        else
            i = -1;
        return i;
    }

    public com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State getPlaybackState()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getPlaybackState()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getPlayerCommand()I");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getTrickMode()I");
        return TrickMode;
    }

    public int getTrickModeClick()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->getTrickModeClick()I");
        return TrickModeClick;
    }

    public boolean isBmlFullView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->isBmlFullView()Z");
        boolean flag;
        if(mBmlSurface != null)
            flag = mBmlSurface.IsBMLFullView();
        else
            flag = false;
        return flag;
    }

    public boolean isPhoneLocked()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->isPhoneLocked()Z");
        boolean flag;
        if(fragHandler != null)
            flag = fragHandler.isPhoneLocked();
        else
            flag = false;
        return flag;
    }

    public void onBackPressed()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onBackPressed()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onCreate(Landroid/os/Bundle;)V");
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
                break MISSING_BLOCK_LABEL_577;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onDestroy()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onFragEvent(ILjava/lang/Object;)V");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onFragEvent: event[").append(i).append("]").toString());
        i;
        JVM INSTR lookupswitch 22: default 236
    //                   200: 237
    //                   201: 1123
    //                   202: 1070
    //                   203: 1191
    //                   223: 402
    //                   224: 459
    //                   225: 481
    //                   226: 371
    //                   227: 271
    //                   228: 1089
    //                   230: 1101
    //                   281: 563
    //                   282: 619
    //                   283: 675
    //                   284: 696
    //                   285: 717
    //                   286: 771
    //                   287: 844
    //                   288: 953
    //                   289: 1062
    //                   290: 1148
    //                   291: 1177;
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$9;->run()V");
                    if(Log.d(MtvUiFilePlayer.this) != null)
                    {
                        Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                        Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$9;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$10;->run()V");
                    if(Log.d(MtvUiFilePlayer.this) != null)
                    {
                        Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                        Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$10;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onKeyDown(ILandroid/view/KeyEvent;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onKeyLongPress(ILandroid/view/KeyEvent;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onKeyUp(ILandroid/view/KeyEvent;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onOptionsItemSelected(Landroid/view/MenuItem;)Z");
        menuitem.getItemId();
        JVM INSTR tableswitch 9 23: default 88
    //                   9 125
    //                   10 110
    //                   11 100
    //                   12 90
    //                   13 88
    //                   14 156
    //                   15 88
    //                   16 88
    //                   17 88
    //                   18 216
    //                   19 170
    //                   20 88
    //                   21 88
    //                   22 262
    //                   23 313;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onOptionsMenuClosed(Landroid/view/Menu;)V");
        optionsMenu = null;
        super.onOptionsMenuClosed(menu);
    }

    protected void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onPause()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onPlayerNotification(III)V");
        MtvUtilDebug.Low("MtvUiFilePlayer", (new StringBuilder()).append("onPlayerNotification ...:command : ").append(i).append(" status : ").append(j).toString());
        i;
        JVM INSTR tableswitch 20482 20492: default 104
    //                   20482 105
    //                   20483 391
    //                   20484 140
    //                   20485 104
    //                   20486 104
    //                   20487 104
    //                   20488 104
    //                   20489 370
    //                   20490 370
    //                   20491 496
    //                   20492 423;
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$11;->run()V");
                    if(Log.d().getFileFormat() != 2 && Log.d(MtvUiFilePlayer.this) != null)
                        Log.d(MtvUiFilePlayer.this).onUpdate(125, null, 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$11;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$12;->run()V");
                    if(!Log.d().IsBMLFullView())
                    {
                        Log.d(MtvUiFilePlayer.this);
                        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
                        {
                            Log.d(MtvUiFilePlayer.this).removeCallbacks(Log.d(MtvUiFilePlayer.this));
                            Log.d(MtvUiFilePlayer.this).postDelayed(Log.d(MtvUiFilePlayer.this), 5000L);
                        }
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$12;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$13;->run()V");
                    onFragEvent(281, null);
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$13;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$14;->run()V");
                    if(Log.d(MtvUiFilePlayer.this) != null)
                        Log.d(MtvUiFilePlayer.this).onUpdate(507, Boolean.valueOf(false), 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$14;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$15;->run()V");
                        Log.d(MtvUiFilePlayer.this, updateTime.intValue());
                    }

                    final MtvUiFilePlayer this$0;
                    final Integer val$updateTime;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$15;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Ljava/lang/Integer;)V");
                this$0 = MtvUiFilePlayer.this;
                updateTime = integer;
                super();
            }
                });
            }
            runOnUiThread(new Runnable() {

                public void run()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$16;->run()V");
                    if(Log.d(MtvUiFilePlayer.this) != null)
                        Log.d(MtvUiFilePlayer.this).onUpdate(507, Boolean.valueOf(true), 14);
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$16;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$17;->run()V");
                    if(Log.d(MtvUiFilePlayer.this) != null && !isBmlFullView())
                    {
                        Log.d(MtvUiFilePlayer.this).onUpdate(121, Integer.valueOf(Log.d()), 14);
                        Log.d(MtvUiFilePlayer.this).onUpdate(123, Integer.valueOf(Log.d()), 14);
                    }
                }

                final MtvUiFilePlayer this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$17;-><init>(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onPrepareOptionsMenu(Landroid/view/Menu;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onResume()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onSaveInstanceState(Landroid/os/Bundle;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onStart()V");
        super.onStart();
        if(mMtvFile != null && mBmlSurface != null)
            mBmlSurface.onStart(fragHandler);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onStateChanged(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;)V");
    }

    protected void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onStop()V");
        super.onStop();
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        if(mMtvFile != null && mBmlSurface != null)
            mBmlSurface.onStop();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onTouchEvent(Landroid/view/MotionEvent;)Z");
        flag = false;
        break MISSING_BLOCK_LABEL_11;
        if(!mBmlSurface.IsBMLFullView() && mChannelGestureDetector.onTouchEvent(motionevent))
            flag = true;
        return flag;
    }

    public void onWindowFocusChanged(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->onWindowFocusChanged(Z)V");
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
        View view;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->setBMLLayoutFullView(Z)V");
        view = findViewById(0x7f0a0028);
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

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;-><clinit>()V");
    }


/*
    static MtvUiFragHandler access$000(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$000(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;");
        return mtvuifileplayer.fragHandler;
    }

*/


/*
    static boolean access$100(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$100(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Z");
        return mtvuifileplayer.mbIsInTrickMode;
    }

*/


/*
    static void access$1000(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1000(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.loadNextFile();
        return;
    }

*/


/*
    static boolean access$102(MtvUiFilePlayer mtvuifileplayer, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$102(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Z)Z");
        mtvuifileplayer.mbIsInTrickMode = flag;
        return flag;
    }

*/


/*
    static MtvFile access$1100()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1100()Lcom/samsung/sec/mtv/provider/MtvFile;");
        return mMtvFile;
    }

*/


/*
    static void access$1200(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1200(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.loadCurrentFileUI();
        return;
    }

*/


/*
    static void access$1300(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1300(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.loadPrevFile();
        return;
    }

*/


/*
    static TextView access$1400(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1400(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/widget/TextView;");
        return mtvuifileplayer.mCaptionView;
    }

*/


/*
    static int access$1500()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1500()I");
        return TrickMode;
    }

*/


/*
    static int access$1502(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1502(I)I");
        TrickMode = i;
        return i;
    }

*/


/*
    static int access$1600()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1600()I");
        return TrickModeClick;
    }

*/


/*
    static int access$1602(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1602(I)I");
        TrickModeClick = i;
        return i;
    }

*/


/*
    static void access$1700(MtvUiFilePlayer mtvuifileplayer, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1700(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;I)V");
        mtvuifileplayer.resetVideoFragments(i);
        return;
    }

*/


/*
    static MtvUtilAudioManager access$1800(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1800(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/utility/MtvUtilAudioManager;");
        return mtvuifileplayer.mMtvAudMgr;
    }

*/


/*
    static MtvAppPlaybackContext access$1900(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$1900(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvuifileplayer.mMtvAppPlaybackContext;
    }

*/


/*
    static int access$200(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$200(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)I");
        return mtvuifileplayer.mCurrentInSecond;
    }

*/


/*
    static IMtvAppPlayerOneSeg access$2000(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2000(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/app/player/IMtvAppPlayerOneSeg;");
        return mtvuifileplayer.mMtvPlayerOneSeg;
    }

*/


/*
    static int access$202(MtvUiFilePlayer mtvuifileplayer, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$202(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;I)I");
        mtvuifileplayer.mCurrentInSecond = i;
        return i;
    }

*/


/*
    static int access$204(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$204(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)I");
        int i = 1 + mtvuifileplayer.mCurrentInSecond;
        mtvuifileplayer.mCurrentInSecond = i;
        return i;
    }

*/


/*
    static boolean access$2100(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2100(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Z");
        return mtvuifileplayer.mbIsTrickResume;
    }

*/


/*
    static boolean access$2102(MtvUiFilePlayer mtvuifileplayer, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2102(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Z)Z");
        mtvuifileplayer.mbIsTrickResume = flag;
        return flag;
    }

*/


/*
    static int access$2200(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2200(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)I");
        return mtvuifileplayer.timeCalculatedForTrick;
    }

*/


/*
    static void access$2300(MtvUiFilePlayer mtvuifileplayer, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2300(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;I)V");
        mtvuifileplayer.updateVidFragCurrTime(i);
        return;
    }

*/


/*
    static Runnable access$2400(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2400(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/Runnable;");
        return mtvuifileplayer.mRunnableTrickModeNew;
    }

*/


/*
    static void access$2500(MtvUiFilePlayer mtvuifileplayer, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2500(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;I)V");
        mtvuifileplayer.setScreenRatio(i);
        return;
    }

*/


/*
    static MtvPreferences access$2600(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2600(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuifileplayer.mPreferences;
    }

*/


/*
    static MtvPreferences access$2602(MtvUiFilePlayer mtvuifileplayer, MtvPreferences mtvpreferences)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2602(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Lcom/samsung/sec/mtv/utility/MtvPreferences;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        mtvuifileplayer.mPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/


/*
    static Runnable access$2700(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2700(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/Runnable;");
        return mtvuifileplayer.postHeadsetUpdate;
    }

*/


/*
    static ImageView access$2800(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2800(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/widget/ImageView;");
        return mtvuifileplayer.lockImage;
    }

*/


/*
    static int access$2900(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$2900(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)I");
        return mtvuifileplayer.calculateSeekToValue();
    }

*/


/*
    static Handler access$300(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$300(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/os/Handler;");
        return mtvuifileplayer.mLocalUiMsgHandler;
    }

*/


/*
    static Runnable access$3000(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3000(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/Runnable;");
        return mtvuifileplayer.mRunnableHideProgressBarInPortrait;
    }

*/


/*
    static boolean access$3100(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3100(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Z");
        return mtvuifileplayer.isPrevNextEnabled();
    }

*/


/*
    static MtvUiBmlSurfaceView access$3200()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3200()Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;");
        return mBmlSurface;
    }

*/


/*
    static void access$3300(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3300(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.showControl();
        return;
    }

*/


/*
    static Runnable access$3400(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3400(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/Runnable;");
        return mtvuifileplayer.runnableVolumeEscalating;
    }

*/


/*
    static Runnable access$3500(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3500(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/Runnable;");
        return mtvuifileplayer.runnableVolumeDecreasing;
    }

*/


/*
    static ImageView access$3600(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3600(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/widget/ImageView;");
        return mtvuifileplayer.mAnimationImage;
    }

*/


/*
    static RelativeLayout access$3700(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3700(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/widget/RelativeLayout;");
        return mtvuifileplayer.mAnimationlayout;
    }

*/


/*
    static TextView access$3800(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$3800(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Landroid/widget/TextView;");
        return mtvuifileplayer.mTxtAnimation;
    }

*/


/*
    static void access$400(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$400(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.updateFragsBasedOnLockState();
        return;
    }

*/


/*
    static MtvAppAndroidService access$500(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$500(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuifileplayer.mService;
    }

*/


/*
    static MtvAppAndroidService access$502(MtvUiFilePlayer mtvuifileplayer, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$502(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuifileplayer.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$600(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$600(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuifileplayer.listener;
    }

*/


/*
    static ControlAnimationRunnable access$700(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$700(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;");
        return mtvuifileplayer.mControlAnimationRunnable;
    }

*/


/*
    static ControlAnimationRunnable access$702(MtvUiFilePlayer mtvuifileplayer, ControlAnimationRunnable controlanimationrunnable)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$702(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;)Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer$ControlAnimationRunnable;");
        mtvuifileplayer.mControlAnimationRunnable = controlanimationrunnable;
        return controlanimationrunnable;
    }

*/


/*
    static String access$800(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$800(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)Ljava/lang/String;");
        return mtvuifileplayer.getStatusBarText();
    }

*/


/*
    static void access$900(MtvUiFilePlayer mtvuifileplayer)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;->access$900(Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer;)V");
        mtvuifileplayer.invalidateViews();
        return;
    }

*/
}
