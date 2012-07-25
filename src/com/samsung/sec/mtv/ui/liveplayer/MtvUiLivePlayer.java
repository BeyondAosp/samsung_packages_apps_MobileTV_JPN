// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.liveplayer;

import android.app.*;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.*;
import android.content.*;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.*;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.reciever.MtvBroadcastReceiver;
import com.samsung.sec.mtv.ui.bml.IMtvUiBmlActivity;
import com.samsung.sec.mtv.ui.bml.MtvUiBmlSurfaceView;
import com.samsung.sec.mtv.ui.channelguide.MtvUiChannelGuide;
import com.samsung.sec.mtv.ui.common.*;
import com.samsung.sec.mtv.utility.*;
import java.util.*;

public class MtvUiLivePlayer extends Activity
    implements IMtvAppPlaybackStateListener, IMtvAppProgramAttributeListener, IMtvUiBmlActivity, com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener
{
    class ChannelGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            boolean flag;
            float f2;
            float f3;
            int i;
            flag = true;
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("GestrueDetectorUiPlayer : onFling called ").append(f).toString());
            f2 = Math.abs(f);
            f3 = Math.abs(f1);
            i = 0;
            if(lockImage.getVisibility() != 0) goto _L2; else goto _L1
_L1:
            MtvUtilDebug.Low("MtvUiLivePlayer", "player locked , ignoring fling...");
_L4:
            return flag;
_L2:
            if(mMtvAppPlaybackContext.getState().getOp() != 20487)
                flag = false;
            if(mMtvAppPlaybackContext == null || mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE || fragHandler == null || flag)
                break; /* Loop/switch isn't completed */
            if(motionevent1 != null && motionevent != null)
                i = (int)(motionevent1.getX() - motionevent.getX());
            if(Math.abs(i) < 100 || f3 > f2)
            {
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low("MtvUiLivePlayer", "Up/Down fling...no channel change");
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(fragHandler != null)
            {
                if(f > 0.0F)
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", "onFling: Channel prev");
                    mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(314));
                } else
                if(f < 0.0F)
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", "onFling: Channel next ");
                    mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(313));
                }
                fragHandler.removeFrag(12);
                fragHandler.addFrag(15, -1L, false, new int[0]);
                fragHandler.addFrag(0, -1L, false, new int[0]);
            }
_L5:
            flag = super.onFling(motionevent, motionevent1, f, f1);
            if(true) goto _L4; else goto _L3
_L3:
            MtvUtilDebug.Low("MtvUiLivePlayer", "onFling: ignored ..... still starting DTV");
              goto _L5
            if(true) goto _L4; else goto _L6
_L6:
        }

        public void onLongPress(MotionEvent motionevent)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onLongPress:").append(motionevent.toString()).toString());
            Bundle bundle = new Bundle();
            if(mMtvAppPlaybackContext != null && mMtvPreferences != null && (fragHandler == null || !fragHandler.isFragPresent(5)))
            {
                MtvProgram mtvprogram = MtvProgramManager.getCurrentProgramByPhCh(getApplicationContext(), mMtvPreferences.getLatestPChannelFromVChannel(), mMtvAppPlaybackContext.getAttribute().getTot());
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("pgm:").append(mtvprogram).toString());
                if(mtvprogram != null)
                {
                    bundle.putInt("pgmPch", mtvprogram.mPch);
                    MtvChannel mtvchannel = MtvChannelManager.findByPChannel(getApplicationContext(), mtvprogram.mPch);
                    if(mtvchannel != null)
                        bundle.putString("channelName", mtvchannel.mChannelName);
                    bundle.putLong("startTime", mtvprogram.mTimeStart);
                    bundle.putLong("endtime", mtvprogram.mTimeEnd);
                    bundle.putString("programName", mtvprogram.mPgmName);
                    MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append(" pgm.mPgmDetail :").append(mtvprogram.mPgmDetail).toString());
                    bundle.putString("programDesc", mtvprogram.mPgmDetail);
                    MtvUiFragHandler.addUnManagedFrag("ProgInfoFrag", bundle, 0x7f0a0019, MtvUiLivePlayer.this);
                }
                super.onLongPress(motionevent);
            }
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            boolean flag;
            boolean flag1;
            if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState().compareTo(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED) >= 0)
                flag = true;
            else
                flag = false;
            if(lockImage.getVisibility() != 0) goto _L2; else goto _L1
_L1:
            MtvUtilDebug.Low("MtvUiLivePlayer", "player locked... ignoring singleTap...");
            flag1 = true;
_L4:
            return flag1;
_L2:
            if(fragHandler != null && flag)
            {
                if(fragHandler.isFragPresent(12))
                    fragHandler.removeFrag(12);
                if(fragHandler.isFragPresent(1))
                    break; /* Loop/switch isn't completed */
                if(!fragHandler.isFragPresent(15))
                {
                    if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                    {
                        fragHandler.addFrag(15, 3000L, false, new int[0]);
                        fragHandler.addFrag(0, 3000L, false, new int[0]);
                    } else
                    {
                        fragHandler.addFrag(15, -1L, false, new int[0]);
                        fragHandler.addFrag(0, -1L, false, new int[0]);
                    }
                    mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                    mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                } else
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                {
                    fragHandler.removeFrag(15);
                    fragHandler.removeFrag(0);
                }
            }
_L5:
            flag1 = true;
            if(true) goto _L4; else goto _L3
_L3:
            MtvUtilDebug.Low("MtvUiLivePlayer", "Tap on Record fragment");
            if(!fragHandler.isFragPresent(15))
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "status bar not present, adding status bar and showing record components");
                fragHandler.addFrag(15, -1L, false, new int[0]);
                mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                fragHandler.onUpdate(111, Boolean.valueOf(true), 1);
                mLiveUiMsgHandler.removeCallbacks(mRunnableUpdateRecordComponents);
                mLiveUiMsgHandler.postDelayed(mRunnableUpdateRecordComponents, 3000L);
            } else
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "status bar present, removing status bar and hiding record components");
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                {
                    fragHandler.removeFrag(15);
                    fragHandler.onUpdate(111, Boolean.valueOf(false), 1);
                }
            }
              goto _L5
            if(true) goto _L4; else goto _L6
_L6:
        }

        final MtvUiLivePlayer this$0;

        ChannelGestureListener()
        {
            this$0 = MtvUiLivePlayer.this;
            super();
        }
    }

    class ControlAnimationRunnable
        implements Runnable
    {

        private void controlAnimation()
        {
            mAnimationDrawable = (AnimationDrawable)mAnimationImage.getBackground();
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("controlAnimation: called :").append(isEnable).toString());
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
                }
                mAnimationImage.setVisibility(4);
                mAnimationImage.invalidate();
                mAnimationlayout.invalidate();
            }
        }

        private void postAnimationToRunInUIThread()
        {
            mAnimationImage.removeCallbacks(this);
            mAnimationImage.post(this);
        }

        private void setAnimationEnable(boolean flag)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationEnable :").append(flag).toString());
            isEnable = flag;
        }

        private void setAnimationText(String s)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("ControlAnimationRunnable: setAnimationText :").append(s).toString());
            mStrAnimation = s;
        }

        public void run()
        {
            controlAnimation();
        }

        private boolean isEnable;
        private AnimationDrawable mAnimationDrawable;
        private String mStrAnimation;
        final MtvUiLivePlayer this$0;




        private ControlAnimationRunnable()
        {
            this$0 = MtvUiLivePlayer.this;
            super();
            isEnable = false;
            mStrAnimation = null;
            MtvUtilDebug.Low("MtvUiLivePlayer", "ControlAnimationRunnable...");
        }

    }

    public static class ErrorDialogFragment extends DialogFragment
    {

        public static ErrorDialogFragment newInstance(int i)
        {
            ErrorDialogFragment errordialogfragment = new ErrorDialogFragment();
            errordialogfragment.myStringId = i;
            return errordialogfragment;
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            if(bundle != null)
                myStringId = bundle.getInt("myStringId");
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final ErrorDialogFragment this$0;

                _cls1()
                {
                    this$0 = ErrorDialogFragment.this;
                    super();
                }
            }

            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700d5).setIcon(0x7f02008e).setMessage(getString(myStringId)).setPositiveButton(0x7f070034, new _cls1()).create();
            alertdialog.getWindow().addFlags(1024);
            return alertdialog;
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("myStringId", myStringId);
            super.onSaveInstanceState(bundle);
        }

        private int myStringId;

        public ErrorDialogFragment()
        {
        }
    }

    private class MtvUiLiveServiceListener
        implements onMtvAppAndroidServiceListener
    {

        public void onMtvAppAndroidServiceNotify(int i, Object obj)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onMtvAppAndroidServiceNotify called: what=").append(i).toString());
            i;
            JVM INSTR tableswitch 1 14: default 96
        //                       1 105
        //                       2 133
        //                       3 161
        //                       4 260
        //                       5 96
        //                       6 96
        //                       7 189
        //                       8 217
        //                       9 288
        //                       10 316
        //                       11 344
        //                       12 372
        //                       13 96
        //                       14 97;
               goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L6 _L7 _L8 _L9 _L10 _L11 _L1 _L12
_L1:
            return;
_L12:
            setRequestedOrientation(5);
_L2:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(302, obj));
            continue; /* Loop/switch isn't completed */
_L3:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(303, obj));
            continue; /* Loop/switch isn't completed */
_L4:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(304, obj));
            continue; /* Loop/switch isn't completed */
_L6:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(312, obj));
            continue; /* Loop/switch isn't completed */
_L7:
            checkIsReservationAndEnd(2);
            MtvUtilDebug.Low("MtvUiLivePlayer", "MTVAPP_ANDROID_SERVICE_APP_FINISH_FOREGROUND notified to LivePlayer");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, obj));
            continue; /* Loop/switch isn't completed */
_L5:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(305, obj));
            continue; /* Loop/switch isn't completed */
_L8:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(316, obj));
            continue; /* Loop/switch isn't completed */
_L9:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(322, obj));
            continue; /* Loop/switch isn't completed */
_L10:
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, obj));
            continue; /* Loop/switch isn't completed */
_L11:
            runOnUiThread(RunnableReservationEndPopupExpire);
            if(true) goto _L1; else goto _L13
_L13:
        }

        public void onMtvAppFinishNotify(Object obj)
        {
            Intent intent = (Intent)obj;
            checkIsReservationAndEnd(2);
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && mMtvAppPlaybackContext.getState().getOp() == 20487)
                stopRecording();
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            mMtvAppPlaybackContext.getState().registerListener(MtvUiLivePlayer.this);
            if(intent.getAction().equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"))
            {
                MtvUtilDebug.Mid("MtvUiLivePlayer", "com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE");
                MtvUtilDebug.Mid("MtvUiLivePlayer", "looks like something already went wrong in middleware... just finishing liveplayer without doing anything...");
                mExitProgressDialog = new ProgressDialog(MtvUiLivePlayer.this);
                hideNotification();
            } else
            if(intent.getAction().equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND"))
            {
                mExitProgressDialog = new ProgressDialog(MtvUiLivePlayer.this);
                MtvUtilDebug.Mid("MtvUiLivePlayer", "com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND");
                hideNotification();
            } else
            {
                mExitProgressDialog = null;
                MtvUtilDebug.Mid("MtvUiLivePlayer", "com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_FOREGROUND");
            }
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)));
        }

        final MtvUiLivePlayer this$0;

        private MtvUiLiveServiceListener()
        {
            this$0 = MtvUiLivePlayer.this;
            super();
        }

    }

    private class RunnableShowSignalAlertDialog
        implements Runnable
    {

        public void run()
        {
            showSignalAlertDialog(mType);
        }

        private final int mType;
        final MtvUiLivePlayer this$0;

        public RunnableShowSignalAlertDialog(int i)
        {
            this$0 = MtvUiLivePlayer.this;
            super();
            mType = i;
        }
    }


    public MtvUiLivePlayer()
    {
        mExitProgressDialog = null;
        mExitAppDialog = null;
        mNoSignalAlertDialog = null;
        mReservationEndDialog = null;
        mCaptionView = null;
        mMtvAudMgr = null;
        restrictLaunch = false;
        orientation = -1;
        mySavedInstanceState = null;
        mMtvUiLiveServiceListener = null;
        epgChNo = -1;
        epgServiceId = -1;
        mMtvPlayerOneSeg = null;
        mMtvAppPlaybackContext = null;
        shownToastforOperationNA = false;
        mAudioManager = null;
        mRunnableUpdateRecordComponents = new Runnable() {

            public void run()
            {
                if(fragHandler != null && fragHandler.isFragPresent(15) && MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                {
                    fragHandler.removeFrag(15);
                    fragHandler.onUpdate(111, Boolean.valueOf(false), 1);
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        mLiveUiMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("handleMessage : ").append(message.what).toString());
                message.what;
                JVM INSTR tableswitch 300 326: default 152
            //                           300 153
            //                           301 257
            //                           302 285
            //                           303 313
            //                           304 981
            //                           305 441
            //                           306 465
            //                           307 842
            //                           308 862
            //                           309 884
            //                           310 910
            //                           311 152
            //                           312 931
            //                           313 1065
            //                           314 1065
            //                           315 1037
            //                           316 1416
            //                           317 1598
            //                           318 1640
            //                           319 1687
            //                           320 1734
            //                           321 1251
            //                           322 1752
            //                           323 152
            //                           324 1762
            //                           325 1880
            //                           326 1977;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L1 _L13 _L14 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L1 _L23 _L24 _L25
_L1:
                return;
_L2:
                String s1 = null;
                if(message.obj != null)
                    s1 = (String)message.obj;
                if(mControlAnimationRunnable == null)
                    mControlAnimationRunnable = new ControlAnimationRunnable();
                if(s1 != null)
                {
                    mControlAnimationRunnable.setAnimationEnable(true);
                    mControlAnimationRunnable.setAnimationText(s1);
                } else
                {
                    mControlAnimationRunnable.setAnimationEnable(false);
                }
                mControlAnimationRunnable.postAnimationToRunInUIThread();
                continue; /* Loop/switch isn't completed */
_L3:
                if(MtvUiLivePlayer.showStatusNotification)
                    showNotification(true);
                else
                    MtvUtilDebug.Mid("MtvUiLivePlayer", "showStatusNotification is false");
                continue; /* Loop/switch isn't completed */
_L4:
                if(fragHandler != null)
                    fragHandler.onUpdate(103, null, 15);
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                {
                    Intent intent2 = (Intent)message.obj;
                    boolean flag2;
                    int k;
                    int l;
                    if(intent2.getIntExtra("status", 1) == 2)
                        flag2 = true;
                    else
                        flag2 = false;
                    k = intent2.getIntExtra("scale", 100);
                    l = intent2.getIntExtra("level", k);
                    MtvBatteryInfo.setBatteryChargeStatus(flag2);
                    if(l < 15 && !flag2)
                    {
                        mLiveUiMsgHandler.sendEmptyMessage(304);
                    } else
                    {
                        MtvBatteryInfo.updateBatteryLevel(l, k);
                        if(fragHandler != null)
                            fragHandler.onUpdate(102, null, 15);
                    }
                }
                continue; /* Loop/switch isn't completed */
_L7:
                hideNotification();
                mLiveUiMsgHandler.sendEmptyMessage(306);
                continue; /* Loop/switch isn't completed */
_L8:
                boolean flag1;
                if(message.obj != null)
                    flag1 = ((Boolean)message.obj).booleanValue();
                else
                    flag1 = false;
                if(MtvUtilAppService.getRotation(getApplicationContext()) == 1)
                    setRequestedOrientation(0);
                else
                if(MtvUtilAppService.getRotation(getApplicationContext()) == 3)
                    setRequestedOrientation(8);
                else
                    setRequestedOrientation(1);
                hideNotification();
                if(!isResumed() && !flag1)
                {
                    MtvUtilDebug.Error("MtvUiLivePlayer", (new StringBuilder()).append("isResumed?: ").append(isResumed()).append(" isBackgroundFinishAllowed?: ").append(flag1).toString());
                    MtvUtilDebug.Error("MtvUiLivePlayer", "skipping finish as LivePlayer activity is not the topActivity - !!");
                } else
                {
                    if(mExitProgressDialog == null)
                        showExitingAppDialog();
                    MtvUtilDebug.Low("MtvUiLivePlayer", "inside306");
                    isScheduledReservationProgram();
                    mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
                    if(mMtvAppPlaybackContext != null)
                    {
                        mMtvAppPlaybackContext.getState().registerListener(MtvUiLivePlayer.this);
                        if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
                        {
                            MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("Exiting - OneSeg already initialized so deleting OneSeg and waiting till delete callback  ").append(mMtvAppPlaybackContext.getState().getState()).append("  ").append(mMtvAppPlaybackContext.getState().getOp()).toString());
                            mLiveUiMsgHandler.removeCallbacks(RunnableNextPreviousChannel);
                            deleteMtvPlayer();
                        } else
                        if(mMtvAppPlaybackContext.getState().getOp() == 20481)
                            MtvUtilDebug.Mid("MtvUiLivePlayer", "Exiting - Create in Progress, so will be finish on Create callback");
                    } else
                    {
                        MtvUtilDebug.Mid("MtvUiLivePlayer", "Exiting - Not initilized and also create is not in progress so finish");
                        finish();
                    }
                }
                continue; /* Loop/switch isn't completed */
_L9:
                setScreenRatio(((Integer)message.obj).intValue());
                continue; /* Loop/switch isn't completed */
_L10:
                if(MtvUiLivePlayer.mBmlSurface != null)
                    MtvUiLivePlayer.mBmlSurface.updateBMLSurfaceView();
                setCaptionViewVisibility();
                continue; /* Loop/switch isn't completed */
_L11:
                mCaptionView.setText("");
                mCaptionView.invalidate();
                continue; /* Loop/switch isn't completed */
_L12:
                SpannableStringBuilder spannablestringbuilder = (SpannableStringBuilder)message.obj;
                setCaptionText(spannablestringbuilder);
                continue; /* Loop/switch isn't completed */
_L13:
                if(!isFinishing())
                {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.samsung.sec.mtv.ACTION_MTV_POP_UP");
                    intent1.putExtra("popup_type", 1);
                    startActivity(intent1);
                }
                continue; /* Loop/switch isn't completed */
_L6:
                if(!MtvUiPopUpActivity.isBatteryLowPopupAvailable() && !isFinishing())
                {
                    Intent intent = new Intent();
                    intent.setAction("com.samsung.sec.mtv.ACTION_MTV_POP_UP");
                    intent.putExtra("popup_type", 0);
                    startActivity(intent);
                }
                continue; /* Loop/switch isn't completed */
_L15:
                if(fragHandler != null)
                    fragHandler.onUpdate(104, null, 15);
                continue; /* Loop/switch isn't completed */
_L14:
                StringBuilder stringbuilder = (new StringBuilder()).append("changing cannel - ");
                String s;
                boolean flag;
                if(message.what == 313)
                    s = "next";
                else
                    s = "prev";
                MtvUtilDebug.Mid("MtvUiLivePlayer", stringbuilder.append(s).toString());
                if(message.what == 313)
                    flag = true;
                else
                    flag = false;
                checkIsReservationAndEnd(1);
                if(mMtvPlayerOneSeg != null)
                {
                    getNextPreviousChannelNumber(flag);
                    mLiveUiMsgHandler.removeCallbacks(RunnableNextPreviousChannel);
                    mLiveUiMsgHandler.postDelayed(RunnableNextPreviousChannel, calculateDelayForStartChannel());
                    mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070087)));
                }
                invalidateLiveViews();
                continue; /* Loop/switch isn't completed */
_L21:
                int j = ((Integer)message.obj).intValue();
                if(fragHandler != null)
                {
                    fragHandler.removeFrag(6);
                    MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("changing channel to - ").append(j).toString());
                    if(mMtvPlayerOneSeg != null)
                    {
                        setLatestChannelsByPChannel(j);
                        mLiveUiMsgHandler.removeCallbacks(RunnableNextPreviousChannel);
                        mLiveUiMsgHandler.postDelayed(RunnableNextPreviousChannel, calculateDelayForStartChannel());
                        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070087)));
                    }
                    invalidateLiveViews();
                }
                continue; /* Loop/switch isn't completed */
_L16:
                closeOptionsMenu();
                if(message.obj != null)
                {
                    int i = ((Intent)message.obj).getIntExtra("state", 0);
                    if(i == 0)
                    {
                        if(mMtvPreferences == null)
                            mMtvPreferences = new MtvPreferences(getApplicationContext());
                        mMtvPreferences.setAudio51Enabled(false);
                        if(fragHandler != null && fragHandler.isFragPresent(15))
                            fragHandler.onUpdate(104, Integer.valueOf(i), 15);
                        if(fragHandler != null && fragHandler.isFragPresent(2))
                            fragHandler.onUpdate(109, Integer.valueOf(i), 2);
                    }
                }
                postDelayed(postHeadsetUpdate, 300L);
                continue; /* Loop/switch isn't completed */
_L17:
                if(fragHandler != null)
                    fragHandler.onUpdate(110, mMtvAppPlaybackContext.getComponents().getVideo().getCaptFrame(), 4);
                continue; /* Loop/switch isn't completed */
_L18:
                if(optionsMenu != null)
                {
                    closeOptionsMenu();
                    optionsMenu = null;
                }
                showToast(0x7f0700d0);
                checkIsReservationAndStart();
                continue; /* Loop/switch isn't completed */
_L19:
                if(fragHandler != null)
                {
                    MtvUiFragHandler mtvuifraghandler = fragHandler;
                    int ai[] = new int[1];
                    ai[0] = 0x7f0a0019;
                    mtvuifraghandler.addFrag(6, -1L, false, ai);
                }
                continue; /* Loop/switch isn't completed */
_L20:
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        MtvOneSegProgram amtvonesegprogram[];
                        int i1;
                        amtvonesegprogram = getProgram();
                        if(mMtvPreferences == null)
                            mMtvPreferences = new MtvPreferences(getApplicationContext());
                        i1 = mMtvPreferences.getLatestVChannel();
                        MtvProgramManager.delete(_fld0, null);
                        if(amtvonesegprogram == null || amtvonesegprogram.length <= 0) goto _L2; else goto _L1
_L1:
                        int j1 = 0;
_L5:
                        if(j1 < amtvonesegprogram.length && amtvonesegprogram[j1] != null) goto _L3; else goto _L2
_L2:
                        return;
_L3:
                        MtvProgram mtvprogram = new MtvProgram(amtvonesegprogram[j1], i1);
                        if(mtvprogram != null)
                            MtvProgramManager.updateOrInsert(_fld0, mtvprogram);
                        j1++;
                        if(true) goto _L5; else goto _L4
_L4:
                    }

                    final _cls2 this$1;

                        _cls1()
                        {
                            this$1 = _cls2.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
                continue; /* Loop/switch isn't completed */
_L22:
                endReservationProgramAndShowAlert();
                continue; /* Loop/switch isn't completed */
_L23:
                if(optionsMenu != null)
                {
                    closeOptionsMenu();
                    optionsMenu = null;
                }
                cancelRecording();
                if(isResumed())
                {
                    if(24595 == message.arg1)
                    {
                        showErrorDialog(0x7f0700d6);
                        CheckIsreservationAndUpdatefailureReason(5);
                    } else
                    {
                        showErrorDialog(0x7f0700d8);
                        CheckIsreservationAndUpdatefailureReason(2);
                    }
                } else
                {
                    MtvUiLivePlayer.mPendingPlayerNotification = obtainMessage(message.what, message.arg1, message.arg2);
                }
                continue; /* Loop/switch isn't completed */
_L24:
                if(optionsMenu != null)
                {
                    closeOptionsMenu();
                    optionsMenu = null;
                }
                stopRecording();
                if(isResumed())
                {
                    if(24594 == message.arg1)
                    {
                        showErrorDialog(0x7f0700d4);
                        CheckIsReservationPartialOrNone(5);
                    }
                } else
                {
                    MtvUiLivePlayer.mPendingPlayerNotification = obtainMessage(message.what, message.arg1, message.arg2);
                }
                continue; /* Loop/switch isn't completed */
_L25:
                if(fragHandler != null)
                    fragHandler.onUpdate(111, Boolean.valueOf(true), 1);
                if(true) goto _L1; else goto _L26
_L26:
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        postHeadsetUpdate = new Runnable() {

            public void run()
            {
                if(fragHandler != null)
                {
                    fragHandler.onUpdate(108, null, 12);
                    fragHandler.onUpdate(108, null, 0);
                    fragHandler.onUpdate(108, null, 1);
                    MtvUtilAudioManager.getInstance(getApplicationContext()).updateCurrentAudioEffectAndMode();
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        mRunableScreenOff = new Runnable() {

            public void run()
            {
                if(mLiveVideoSurfaceView != null)
                    mLiveVideoSurfaceView.setKeepScreenOn(false);
                if(mHiddenSurfaceView != null)
                    mHiddenSurfaceView.setKeepScreenOn(false);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        mRunableScreenON = new Runnable() {

            public void run()
            {
                if(mLiveVideoSurfaceView != null)
                    mLiveVideoSurfaceView.setKeepScreenOn(true);
                if(mHiddenSurfaceView != null)
                    mHiddenSurfaceView.setKeepScreenOn(true);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(mMtvUiLiveServiceListener = new MtvUiLiveServiceListener());
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onServiceConnected...");
                mService.unregisterListener(mMtvUiLiveServiceListener);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
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
                    mLiveUiMsgHandler.postDelayed(runnableVolumeEscalating, 100L);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
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
                    mLiveUiMsgHandler.postDelayed(runnableVolumeDecreasing, 100L);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        runnableAbnormalFinishTV = new Runnable() {

            public void run()
            {
                MtvUtilDebug.Error("MtvUiLivePlayer", "MTV_ERROR_SERVICE_UNKONWN : Abnormal termination NOW");
                mLiveUiMsgHandler.sendEmptyMessage(306);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        runnableShowNotification = new Runnable() {

            public void run()
            {
                showNotification();
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableNextPreviousChannel = new Runnable() {

            public void run()
            {
                if(mMtvPlayerOneSeg != null)
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", "RunnableNextPreviousChannel dispatched from Queue -> Gonna Open !");
                    MtvURI mtvuri = new MtvURI(2, mMtvPreferences.getLatestPChannelFromVChannel());
                    mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
                    class _cls1
                        implements Runnable
                    {

                        public void run()
                        {
                            if(MtvUiLivePlayer.mBmlSurface != null)
                                MtvUiLivePlayer.mBmlSurface.setVisibility(4);
                        }

                        final _cls20 this$1;

                        _cls1()
                        {
                            this$1 = _cls20.this;
                            super();
                        }
                    }

                    runOnUiThread(new _cls1());
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableUpdateProgramChannelInfo = new Runnable() {

            public void run()
            {
                if(fragHandler != null && mMtvPreferences != null)
                {
                    if(fragHandler.isFragPresent(15))
                    {
                        MtvUtilDebug.Low("MtvUiLivePlayer", "RunnableUpdateProgramChannelInfo");
                        fragHandler.onUpdate(101, getCurrentChannelProgramTitle(false, true), 15);
                    }
                    if(fragHandler.isFragPresent(0))
                    {
                        MtvUtilDebug.Low("MtvUiLivePlayer", "RunnableUpdateProgramChannelInfo");
                        fragHandler.onUpdate(101, getCurrentChannelProgramTitle(false, false), 0);
                    }
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableUpdateSignalInfo = new Runnable() {

            public void run()
            {
                int i = 0;
                MtvOneSegSignal mtvonesegsignal = getSignalValue();
                if(mtvonesegsignal != null)
                    i = mtvonesegsignal.getSignalQuality();
                MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("RunnableUpdateSignalInfo - signalValue: ").append(i).toString());
                if(fragHandler != null && fragHandler.isFragPresent(15))
                    fragHandler.onUpdate(100, Integer.valueOf(i), 15);
                if(i > 0)
                    mLiveUiMsgHandler.post(RunnableRemoveWeakSignalDialogue);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableRemoveWeakSignalDialogue = new Runnable() {

            public void run()
            {
                if(getFragmentManager().findFragmentByTag("signal_alert_retry_exit") != null && isResumed())
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", "RunnableRemoveWeakSignalDialogue... removed");
                    MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_retry_exit");
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableReservationEndPopupExpire = new Runnable() {

            public void run()
            {
                mLiveUiMsgHandler.removeCallbacks(RunnableReservationEndPopupExpire);
                DialogFragment dialogfragment = (DialogFragment)getFragmentManager().findFragmentByTag("reserve_end_dialog");
                if(dialogfragment != null)
                {
                    endReservationProgram(dialogfragment.getArguments());
                    dialogfragment.dismiss();
                }
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableresetComingReservationID = new Runnable() {

            public void run()
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "reseting ComingReservationID ..");
                mLiveUiMsgHandler.removeCallbacks(RunnableresetComingReservationID);
                mMtvPreferences.setComingReservationID(-1);
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
        RunnableCheckIsReservationAndStart = new Runnable() {

            public void run()
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "RunnableCheckIsReservationAndStart...");
                checkIsReservationAndStart();
            }

            final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
        };
    }

    private void CheckIsReservationPartialOrNone(int i)
    {
        int j;
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        j = mMtvPreferences.getReservationAlertID();
        if(j == -1) goto _L2; else goto _L1
_L1:
        MtvReservation mtvreservation = MtvReservationManager.find(getApplicationContext(), j);
        if(mtvreservation == null) goto _L2; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 4 5: default 72
    //                   4 82
    //                   5 140;
           goto _L4 _L5 _L6
_L4:
        MtvUtilDebug.Low("MtvUiLivePlayer", "No Above");
_L2:
        return;
_L5:
        MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsReservationPartialOrNone    :STATUS_FAIL_SIGNAL_ERROR");
        if(mtvreservation.mPgmStatus == 6)
        {
            if(mtvreservation.mPgmType == 1)
                CheckIsreservationAndUpdatefailureReason(1);
            else
                CheckIsreservationAndUpdatefailureReason(8);
        } else
        if(mtvreservation.mPgmStatus == 0)
            CheckIsreservationAndUpdatefailureReason(4);
        continue; /* Loop/switch isn't completed */
_L6:
        MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsReservationPartialOrNone    :STATUS_FAIL_MEMORY_ERROR");
        if(mtvreservation.mPgmStatus == 6)
        {
            if(mtvreservation.mPgmType == 1)
                CheckIsreservationAndUpdatefailureReason(1);
            else
                CheckIsreservationAndUpdatefailureReason(9);
        } else
        if(mtvreservation.mPgmStatus == 0)
            CheckIsreservationAndUpdatefailureReason(5);
        if(true) goto _L2; else goto _L7
_L7:
    }

    private void CheckIsreservationAndUpdatefailureReason(int i)
    {
        MtvReservation mtvreservation;
        MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsreservationAndUpdatefailureReason");
        mtvreservation = MtvReservationManager.find(this, mMtvPreferences.getReservationAlertID());
        if(mtvreservation != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsreservationAndUpdatefailureReason() : invalid reservation");
_L4:
        return;
_L2:
        boolean flag;
        String s;
        int j;
        Bundle bundle;
        if(mMtvPreferences.getReserveAlertFrom() == 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsreservationAndUpdatefailureReason() : OUT_OF_APP");
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "CheckIsreservationAndUpdatefailureReason() : not OUT_OF_APP");
            flag = false;
        }
        if(mtvreservation.mPgmType != 0)
            break; /* Loop/switch isn't completed */
        if(flag)
            s = getString(0x7f0702b8);
        else
            s = getString(0x7f0702bb);
_L5:
        j = mtvreservation.mPgmType;
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("FailStatusAndreason").append(i).toString());
        checkIsReservationAndEnd(i);
        bundle = new Bundle();
        bundle.putInt("dialogType", 7);
        bundle.putBoolean("reserve_end_exit", flag);
        bundle.putString("dialog_msg", s);
        bundle.putInt("reserve_type", j);
        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_retry_exit");
        MtvUtilDebug.Low("MtvUiLivePlayer", "startReservationProgram :NoSignal popup closed");
        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_terminate");
        MtvUtilDebug.Low("MtvUiLivePlayer", "startReservationProgram :mNoSignalTerminate popup closed");
        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "exit_confirmation");
        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "save_exit_confirmation");
        endFailReservationProgram(bundle);
        if(true) goto _L4; else goto _L3
_L3:
        if(flag)
            s = getString(0x7f0702b8);
        else
            s = getString(0x7f0702ba);
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private void LaunchChannelGuideActivity(int i)
    {
        if(fragHandler != null && fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        Intent intent = new Intent(getApplicationContext(), com/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide);
        intent.putExtra("initialize", true);
        intent.putExtra("tab", i);
        (new Bundle()).putParcelable("intent", intent);
        startActivityForResult(intent, 0);
    }

    private boolean blockUiEventsForReservation()
    {
        boolean flag = false;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("").append(mMtvPreferences.isReservationProgram()).append("   :::   ").append(mMtvAppPlaybackContext).toString());
        if(mMtvPreferences.isReservationProgram())
            if(mMtvAppPlaybackContext != null)
            {
                if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                    flag = true;
            } else
            {
                flag = true;
            }
        return flag;
    }

    private void cancelRecording()
    {
        if(fragHandler != null)
        {
            fragHandler.removeFrag(15);
            fragHandler.removeFrag(1);
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
            {
                fragHandler.addFrag(15, -1L, false, new int[0]);
                mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                fragHandler.addFrag(0, -1L, false, new int[0]);
            }
        }
        mMtvPlayerOneSeg.cancelRecord(mMtvAppPlaybackContext);
    }

    private boolean checkIsCalling(boolean flag)
    {
        boolean flag1;
        if(((TelephonyManager)getSystemService("phone")).getCallState() == 2)
        {
            if(flag)
            {
                if(mExitToast == null)
                    mExitToast = Toast.makeText(this, "", 0);
                else
                    mExitToast.cancel();
                mExitToast.setText(0x7f07009e);
                mExitToast.setDuration(1000);
                mExitToast.show();
                flag1 = true;
            } else
            {
                flag1 = true;
            }
        } else
        {
            mExitToast = null;
            flag1 = false;
        }
        MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("checkIsCalling() + result = ").append(flag1).toString());
        return flag1;
    }

    private boolean checkIsMassStorageUSBEnabled()
    {
        boolean flag = false;
        if(Environment.getExternalStorageState().equals("mounted")) goto _L2; else goto _L1
_L1:
        if(mExitToast == null)
            mExitToast = Toast.makeText(this, "", 0);
        else
            mExitToast.cancel();
        mExitToast.setText(0x7f07009d);
        mExitToast.setDuration(0);
        mExitToast.show();
        flag = true;
_L4:
        MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("checkIsMassStorageUSBEnabled() + result = ").append(flag).toString());
        return flag;
_L2:
        if(mExitToast != null)
        {
            mExitToast.cancel();
            mExitToast = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean checkIsRelaunched()
    {
        boolean flag = false;
        if(!isTaskRoot())
        {
            Intent intent = getIntent();
            String s = intent.getAction();
            if(intent.hasCategory("android.intent.category.LAUNCHER") && s != null && s.equals("android.intent.action.MAIN"))
                flag = true;
        }
        MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("checkIsRelaunched() + result = ").append(flag).toString());
        return flag;
    }

    private void checkIsReservationAndEnd(int i)
    {
        shownToastforOperationNA = false;
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        int j = mMtvPreferences.getReservationAlertID();
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("checkIsReservationAndEnd() newstatus:").append(i).append("  reserve_id : ").append(j).toString());
        if(mMtvPreferences.isReservationProgram() && j > -1)
        {
            MtvReservation mtvreservation = MtvReservationManager.find(this, j);
            if(mtvreservation != null)
            {
                if(i == 1 && !MtvReservationManager.isImmediateReservation(this, mtvreservation.mTimeEnd))
                {
                    Intent intent = getIntent();
                    intent.removeExtra("dbId");
                    intent.removeExtra("youNeedToShowAlert");
                }
                if(mMtvPreferences.getComingReservationID() == j)
                    mMtvPreferences.setComingReservationID(-1);
                MtvReservationManager.UpdateStatus(this, mtvreservation, i);
            }
        }
    }

    private boolean checkIsReservationAndStart()
    {
        boolean flag;
        Bundle bundle;
        flag = false;
        MtvUtilDebug.Low("MtvUiLivePlayer", "checkIsReservationAndStart()");
        bundle = getIntent().getExtras();
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append(" checkIsReservationAndStart() : ").append(getIntent().toString()).append(" ").append(getIntent().hasExtra("dbId")).toString());
        if(bundle == null || isFinishing()) goto _L2; else goto _L1
_L1:
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER) goto _L4; else goto _L3
_L3:
        MtvUtilDebug.High("MtvUiLivePlayer", "current context is scan.. will wait until scan is cancelled and then start reservation...");
_L6:
        return flag;
_L4:
        boolean flag1;
        if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
            break; /* Loop/switch isn't completed */
        int i = bundle.getInt("dbId", -1);
        MtvReservation mtvreservation = MtvReservationManager.find(this, i);
        boolean flag2 = bundle.getBoolean("youNeedToShowAlert", false);
        if(mtvreservation == null || mtvreservation.mPgmStatus != 0 || mtvreservation.mTimeEnd <= System.currentTimeMillis())
            break MISSING_BLOCK_LABEL_534;
        if(MtvAreaManager.getCount(this) == 0)
        {
            checkIsReservationAndEnd(2);
            int j = 0x7f0702e0;
            if(mtvreservation.mPgmType == 0)
                j = 0x7f0702e1;
            else
            if(mtvreservation.mPgmType == 1)
                j = 0x7f0702e0;
            if(!mLiveUiMsgHandler.hasMessages(306))
                mLiveUiMsgHandler.sendEmptyMessage(306);
            Toast.makeText(getApplicationContext(), j, 2000).show();
            continue; /* Loop/switch isn't completed */
        }
        if(!flag2)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "checkIsReservation : this may be a valid reservation,lets check it");
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && mMtvAppPlaybackContext.getState().getOp() == 20487)
                stopRecording();
            else
            if(mMtvPreferences.getReservationAlertID() != -1 && mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && mMtvAppPlaybackContext.getState().getOp() != 20487 && mMtvPreferences.getReservationAlertID() != i)
            {
                MtvReservation mtvreservation1 = MtvReservationManager.find(getApplicationContext(), mMtvPreferences.getReservationAlertID());
                if(mtvreservation1 != null)
                    if(mtvreservation1.mPgmStatus == 6)
                    {
                        if(mtvreservation1.mPgmType == 1)
                            checkIsReservationAndEnd(1);
                    } else
                    if(mtvreservation1.mPgmStatus == 0)
                        checkIsReservationAndEnd(2);
                mMtvPreferences.setReservationAlertID(i);
                startReservationProgram(mtvreservation);
            } else
            {
                mMtvPreferences.setReservationAlertID(i);
                startReservationProgram(mtvreservation);
            }
        } else
        if(flag2)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "checkIsReservation : showing reservation alert to user");
            showReservationAlert();
        }
        flag1 = true;
_L7:
        flag = flag1;
        if(true) goto _L6; else goto _L5
_L5:
        MtvUtilDebug.Low("MtvUiLivePlayer", "checkIsReservation() : MW not initilized yet!!! ");
        flag1 = false;
          goto _L7
_L2:
        MtvUtilDebug.Low("MtvUiLivePlayer", "checkIsReservation() : no current reservation");
        flag1 = false;
          goto _L7
    }

    private void checkRecordingTime()
    {
        if(fragHandler != null)
            fragHandler.onUpdate(116, null, 1);
    }

    private void deleteMtvPlayer()
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "inside deleteMtvPlayer...");
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getOp() == 20495)
            MtvUtilDebug.Low("MtvUiLivePlayer", "cannot issue delete as already one delete operation is going on...");
        else
        if(mMtvPlayerOneSeg != null)
            mMtvPlayerOneSeg.delete(mMtvAppPlaybackContext);
        else
            MtvUtilDebug.Low("MtvUiLivePlayer", "mMtvPlayerOneSeg is null ");
    }

    private void endFailReservationProgram(Bundle bundle)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram");
        if(bundle != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram -bundle null");
_L4:
        return;
_L2:
        int i = bundle.getInt("reserve_type", -1);
        boolean flag = bundle.getBoolean("reserve_end_exit", false);
        if(i == 0)
        {
            if(flag)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram Exiting App after Stopping Record .... ");
                if(!mLiveUiMsgHandler.hasMessages(306))
                    mLiveUiMsgHandler.sendMessageDelayed(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)), 2000L);
            }
        } else
        if(flag)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram Exiting App.... ");
            if(!mLiveUiMsgHandler.hasMessages(306))
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void endReservationProgram(Bundle bundle)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram");
        if(bundle != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram -bundle null");
_L4:
        return;
_L2:
        int i = bundle.getInt("reserve_type", -1);
        boolean flag = bundle.getBoolean("reserve_end_exit", false);
        if(i == 0)
        {
            stopRecording();
            if(flag)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram Exiting App after Stopping Record .... ");
                mLiveUiMsgHandler.sendMessageDelayed(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)), 2000L);
            }
        } else
        if(flag)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgram Exiting App.... ");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void endReservationProgramAndShowAlert()
    {
        boolean flag;
        MtvReservation mtvreservation;
        flag = false;
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert");
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        mtvreservation = MtvReservationManager.find(this, mMtvPreferences.getReservationAlertID());
        if(mtvreservation != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert() : invalid reservation");
_L7:
        return;
_L2:
        if(mMtvPreferences.getReserveAlertFrom() == 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert() : OUT_OF_APP");
            boolean flag1;
            String s;
            int i;
            Bundle bundle;
            ActivityManager activitymanager;
            String s1;
            Exception exception;
            if(MtvReservationManager.isImmediateReservation(this, mtvreservation.mTimeEnd))
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert() : OUT_OF_APP BUT CONTINEOUS");
                flag1 = false;
            } else
            {
                flag1 = true;
            }
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert() : not OUT_OF_APP");
            flag1 = false;
        }
        if(mtvreservation.mPgmType == 0)
        {
            if(flag1)
                s = getString(0x7f0702b9);
            else
                s = getString(0x7f0702bb);
        } else
        if(flag1)
            s = getString(0x7f0702b8);
        else
            s = getString(0x7f0702ba);
        i = mtvreservation.mPgmType;
        if(MtvReservationManager.isImmediateReservation(this, mtvreservation.mTimeEnd)) goto _L4; else goto _L3
_L3:
        if(mtvreservation.mPgmStatus != 6) goto _L6; else goto _L5
_L5:
        if(mtvreservation.mPgmType == 1)
            checkIsReservationAndEnd(1);
_L8:
        bundle = new Bundle();
        bundle.putInt("dialogType", 7);
        bundle.putBoolean("reserve_end_exit", flag1);
        bundle.putString("dialog_msg", s);
        bundle.putInt("reserve_type", i);
        activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
        if(activitymanager != null)
            s1 = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        else
            s1 = null;
        if(s1 != null && s1.equalsIgnoreCase("com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"))
            flag = true;
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("  isResumed()  :").append(isResumed()).append("    isTopActivity  :").append(flag).toString());
        if(isResumed() || flag)
        {
            try
            {
                mReservationEndDialog = MtvUiDialogsFrag.newInstance(bundle);
                mReservationEndDialog.show(getFragmentManager(), "reserve_end_dialog");
                mLiveUiMsgHandler.postDelayed(RunnableReservationEndPopupExpire, 5000L);
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception)
            {
                exception.printStackTrace();
                MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert: inside catch block");
                endReservationProgram(bundle);
            }
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "endReservationProgramAndShowAlert: isResumed() - false");
            endReservationProgram(bundle);
        }
_L4:
        if(true) goto _L7; else goto _L6
_L6:
        if(mtvreservation.mPgmStatus == 0)
            checkIsReservationAndEnd(2);
          goto _L8
    }

    private SpannableStringBuilder getCaptionText()
    {
        SpannableStringBuilder spannablestringbuilder = null;
        if(mMtvAppPlaybackContext != null)
            spannablestringbuilder = mMtvAppPlaybackContext.getComponents().getCaption().getBuffer();
        return spannablestringbuilder;
    }

    private String getCurrentChannelProgramTitle(boolean flag, boolean flag1)
    {
        MtvOneSegProgram amtvonesegprogram[] = null;
        String s = "";
        String s1 = "";
        MtvOneSegChannel mtvonesegchannel;
        if(!flag)
        {
            mtvonesegchannel = getChannel();
            amtvonesegprogram = getProgram();
        } else
        {
            mtvonesegchannel = null;
        }
        if(mtvonesegchannel != null)
            s = mtvonesegchannel.getServName();
        else
        if(mMtvPreferences != null)
            s = mMtvPreferences.getLatestChannelNameForDisplay(flag1);
        if(amtvonesegprogram != null)
        {
            MtvOneSegProgram mtvonesegprogram = getCurrentProgramDetails(amtvonesegprogram);
            if(mtvonesegprogram != null)
                s1 = mtvonesegprogram.getProgName();
        } else
        {
            s1 = "";
        }
        return (new StringBuilder()).append("").append(s).append("\n").append(s1).toString();
    }

    private SurfaceView getLiveVideoSurfaceView(boolean flag)
    {
        SurfaceView surfaceview;
        if(!flag)
            mLiveVideoSurfaceView.setVisibility(8);
        else
        if(!mBmlSurface.IsBMLFullView())
            mLiveVideoSurfaceView.setVisibility(0);
        else
            mLiveVideoSurfaceView.setVisibility(8);
        if(flag)
            surfaceview = mLiveVideoSurfaceView;
        else
            surfaceview = mHiddenSurfaceView;
        return surfaceview;
    }

    private int getNextPreviousChannelNumber(boolean flag)
    {
        int i;
        int j;
        int k;
        int l;
        i = 62;
        j = -1;
        k = mMtvPreferences.getLatestVChannel();
        l = mMtvPreferences.getLatestPChannelFromVChannel();
        if(MtvChannelManager.getCount(this) >= 1) goto _L2; else goto _L1
_L1:
        if(l >= 13 && l <= i) goto _L4; else goto _L3
_L3:
        i = 13;
_L6:
        mMtvPreferences.setLatestPChannel(i);
        mMtvPreferences.setLatestVChannel(j);
        return i;
_L4:
        if(l != 13 || flag)
            if(l == i && flag)
                i = 13;
            else
            if(flag)
                i = l + 1;
            else
                i = l - 1;
        continue; /* Loop/switch isn't completed */
_L2:
        if(MtvChannelManager.getCount(this) == 1)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "getNextPreviousChannelNumber : only one channel on Air ,better to handle this before you reach here.");
            i = 13;
        } else
        {
            MtvChannel mtvchannel;
            if(k > 0)
            {
                if(flag)
                    mtvchannel = MtvChannelManager.getNext(this, k);
                else
                    mtvchannel = MtvChannelManager.getPrevious(this, k);
            } else
            {
                mtvchannel = MtvChannelManager.getFirstOnAir(this);
            }
            if(mtvchannel != null)
            {
                i = mtvchannel.mPhysicalNum;
                j = mtvchannel.mVirtualNum;
            } else
            {
                i = 13;
            }
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private String getNotificationContent()
    {
        return getCurrentChannelProgramTitle(false, true);
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

    private void hideNotification()
    {
        if(mNotificationManager != null)
            mNotificationManager.cancel(0x7f020113);
    }

    private void initMtvPlayer()
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "initMtvPlayer...");
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        int i = MtvAreaManager.getCount(getApplicationContext());
        if(mMtvAppPlaybackContext == null)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("setting AutoPowerOff Time after ").append(mMtvPreferences.getAutoPowerOffTime()).toString());
            MtvUiSleepTimerDialogFrag.setAlarm(getApplicationContext(), mMtvPreferences.getAutoPowerOffTime(), true);
            if(i > 0)
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070087)));
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            mMtvPlayerOneSeg.create(mMtvAppPlaybackContext, this);
            mMtvAppPlaybackContext.getComponents().getBml().enable();
        } else
        if(mMtvAppPlaybackContext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "current context is scanner... waiting for cancel  scan_complete..");
            mMtvPlayerOneSeg.cancelScanChannels(mMtvAppPlaybackContext);
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
        } else
        if(mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV)
        {
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            if(mMtvPlayerOneSeg != null)
            {
                MtvURI mtvuri = new MtvURI(2, mMtvPreferences.getLatestPChannelFromVChannel());
                mMtvPlayerOneSeg.open(mMtvAppPlaybackContext, mtvuri);
            }
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "already in live mode .");
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
        }
        if(MtvAreaManager.getCount(getApplicationContext()) > 0 && mMtvAppPlaybackContext != null)
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
            {
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, null));
                MtvOneSegSignal mtvonesegsignal = mMtvAppPlaybackContext.getAttribute().getSignalStatistics();
                int j = 0;
                if(mtvonesegsignal != null)
                    j = mtvonesegsignal.getSignalQuality();
                if(j != 0)
                    mLiveUiMsgHandler.post(RunnableRemoveWeakSignalDialogue);
            } else
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED)
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            else
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070087)));
        if(i > 0)
        {
            registerVideoSurfaceView(true);
            if(getCaptionText() != null)
                mCaptionView.setText(getCaptionText());
        } else
        {
            MtvUtilAudioManager.getInstance(this).setAudioMute(true);
        }
    }

    private void initViewControl()
    {
        setContentView(0x7f030004);
        lockImage = (ImageView)findViewById(0x7f0a0025);
        mAnimationlayout = (RelativeLayout)findViewById(0x7f0a0021);
        mAnimationImage = (ImageView)findViewById(0x7f0a0022);
        mTxtAnimation = (TextView)findViewById(0x7f0a0023);
        mLiveVideoSurfaceView = (SurfaceView)findViewById(0x7f0a001d);
        mHiddenSurfaceView = (SurfaceView)findViewById(0x7f0a001e);
        mBmlSurface = (MtvUiBmlSurfaceView)findViewById(0x7f0a000a);
        mCaptionView = (TextView)findViewById(0x7f0a0020);
        mCaptionView.setOnClickListener(null);
        mImgViewNoChannel = (ImageView)findViewById(0x7f0a001f);
        mImgViewNoChannel.setVisibility(4);
        mChannelGestureDetector = new GestureDetector(this, new ChannelGestureListener());
        showStatusNotification = false;
        setCaptionViewVisibility();
    }

    private void invalidateLiveViews()
    {
        if(!mBmlSurface.IsBMLFullView()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mLiveVideoSurfaceView.setVisibility(4);
        mLiveVideoSurfaceView.setVisibility(0);
        mLiveUiMsgHandler.sendEmptyMessage(309);
        if(fragHandler != null)
        {
            if(fragHandler.isFragPresent(15))
            {
                fragHandler.onUpdate(101, getCurrentChannelProgramTitle(true, true), 15);
                fragHandler.onUpdate(100, null, 15);
            }
            if(fragHandler.isFragPresent(0))
                fragHandler.onUpdate(101, getCurrentChannelProgramTitle(true, false), 0);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void latestChannelValidity()
    {
        if(mMtvPreferences.getLatestPChannel() < 0 && mMtvPreferences.getLatestVChannel() < 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "Lost Track of last Channel,may be its first time run , reseting to  Default Channel");
            mMtvPreferences.setLatestPChannel(13);
            mMtvPreferences.setLatestVChannel(-1);
        }
    }

    private void registerMediaButtonReceiver()
    {
        mAudioManager.registerMediaButtonEventReceiver(new ComponentName(getPackageName(), com/samsung/sec/mtv/reciever/MtvBroadcastReceiver.getName()));
    }

    private void registerVideoSurfaceView(boolean flag)
    {
        if(mMtvAppPlaybackContext != null)
        {
            mMtvAppPlaybackContext.getComponents().getVideo().enable();
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("registerVideoSurfaceView :which").append(flag).toString());
            IMtvOneSegVideoControl imtvonesegvideocontrol = mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface();
            if(imtvonesegvideocontrol != null)
            {
                imtvonesegvideocontrol.registerSurface(getLiveVideoSurfaceView(flag));
                if(mMtvPreferences == null)
                    mMtvPreferences = new MtvPreferences(getApplicationContext());
                if(mMtvPreferences.getComingReservationID() != -1)
                {
                    MtvReservation mtvreservation = MtvReservationManager.find(this, mMtvPreferences.getComingReservationID());
                    if(mtvreservation != null && mtvreservation.mPgmType == 0)
                    {
                        MtvUtilDebug.High("MtvUiLivePlayer", "registerVideoSurfaceView off screen while recording reservation");
                        mLiveUiMsgHandler.postDelayed(mRunableScreenOff, 1000L);
                    } else
                    {
                        MtvUtilDebug.High("MtvUiLivePlayer", "registerVideoSurfaceView on screen while watch reservation");
                        mLiveUiMsgHandler.postDelayed(mRunableScreenON, 1000L);
                    }
                } else
                {
                    mLiveUiMsgHandler.postDelayed(mRunableScreenON, 500L);
                }
            }
        }
    }

    private void releaseMtvPlayer()
    {
        if(mMtvAppPlaybackContext != null)
        {
            if(mBmlSurface != null)
                mBmlSurface.onDestroy();
            registerVideoSurfaceView(false);
            MtvAppPlaybackContextManager.getInstance().reset();
            mMtvPlayerOneSeg = null;
            mMtvAppPlaybackContext = null;
        }
    }

    private void removeFragments()
    {
        if(fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        if(fragHandler.isFragPresent(15))
            fragHandler.removeFrag(15);
        if(fragHandler.isFragPresent(0))
            fragHandler.removeFrag(0);
    }

    private boolean requestSystemKeyEvent(int i, boolean flag)
    {
        IWindowManager iwindowmanager;
        iwindowmanager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        MtvUtilDebug.Low("MtvUiLivePlayer", "requestSystemKeyEvent");
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

    private void saveTVFileThumbnail()
    {
        boolean flag = false;
        if(mMtvAppPlaybackContext == null)
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        android.graphics.Bitmap bitmap = mMtvAppPlaybackContext.getComponents().getVideo().getCaptFrame();
        String s = mMtvAppPlaybackContext.getComponents().getVideo().getCaptFrameName();
        MtvUtilDebug.Low("MtvUiLivePlayer", "saveTVFileThumbnail");
        boolean flag1;
        if(bitmap != null)
            flag1 = true;
        else
            flag1 = false;
        if(s != null)
            flag = true;
        if(flag & flag1)
        {
            MtvFile mtvfile = new MtvFile();
            mtvfile.setFileFormat(1);
            mtvfile.setCreationTime(new Date());
            mtvfile.setFilePath(s);
            MtvFileManager.saveFile(mMtvPreferences.getSaveToStorage(), bitmap, mtvfile);
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
    }

    private void setCaptionText(SpannableStringBuilder spannablestringbuilder)
    {
        mCaptionView.setText(spannablestringbuilder);
    }

    private void setCaptionViewVisibility()
    {
        if(mMtvPreferences.isCaptionEnabled() && !isBmlFullView())
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
            mLiveVideoSurfaceView.setVisibility(8);
            mImgViewNoChannel.setVisibility(0);
            mAnimationImage.setVisibility(4);
        } else
        {
            mLiveVideoSurfaceView.setVisibility(0);
            mImgViewNoChannel.setVisibility(8);
        }
    }

    private void setLatestChannelsByPChannel(int i)
    {
        int j = -1;
        MtvChannel mtvchannel = MtvChannelManager.findByPChannel(this, i);
        if(mtvchannel != null)
            j = mtvchannel.mVirtualNum;
        mMtvPreferences.setLatestPChannel(i);
        mMtvPreferences.setLatestVChannel(j);
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
            MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio landscape");
            j = (int)MtvUtilConfigSetting.convertDpToPixel(360F, context);
            k = (int)MtvUtilConfigSetting.convertDpToPixel(640F, context);
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio portrait");
            j = (int)MtvUtilConfigSetting.convertDpToPixel(203F, context);
            k = (int)MtvUtilConfigSetting.convertDpToPixel(360F, context);
        }
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("setScreenRatio LCD_HEIGHT:").append(j).append("LCD_WIDTH:").append(k).toString());
        if(1.777778F == 1.777778F)
        {
            if(i == 0)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio 16:9 NORMAL");
                l = 9 * (k / 16);
                i1 = k;
            } else
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio 16:9 ENLARGE ");
                l = j;
                i1 = k;
            }
        } else
        if(i == 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio 4:3 NORMAL ");
            i1 = 4 * (k / 3);
            l = j;
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "setScreenRatio 4:3 ENLARGE ");
            l = j;
            i1 = k;
        }
        j1 = (k - i1) / 2;
        k1 = (j - l) / 2;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("setScreenRatio x = ").append(j1).append(" y = ").append(k1).append(" w = ").append(i1).append(" h = ").append(l).toString());
        layoutparams = new android.widget.AbsoluteLayout.LayoutParams(i1, l, j1, k1);
        if(mLiveVideoSurfaceView == null)
            mLiveVideoSurfaceView = (SurfaceView)findViewById(0x7f0a001d);
        if(mLiveVideoSurfaceView != null)
            mLiveVideoSurfaceView.setLayoutParams(layoutparams);
    }

    private void showErrorDialog(int i)
    {
        ErrorDialogFragment.newInstance(i).show(getFragmentManager(), "ErrorDialogFragment");
    }

    private void showNotification()
    {
        if(!MtvUtilAppService.isAppExiting())
        {
            Notification notification = new Notification();
            notification.icon = 0x7f020113;
            notification.when = 0L;
            notification.flags = 0x22 | notification.flags;
            Intent intent = new Intent();
            intent.setFlags(0x100000);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(getComponentName());
            PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
            if(mMtvAppPlaybackContext == null)
                mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            if(mMtvAppPlaybackContext != null && 20487 != mMtvAppPlaybackContext.getState().getOp())
                notification.setLatestEventInfo(this, getString(0x7f07009b), getNotificationContent(), pendingintent);
            else
                notification.setLatestEventInfo(this, getString(0x7f07009c), getNotificationContent(), pendingintent);
            if(mNotificationManager != null)
                mNotificationManager.notify(0x7f020113, notification);
            else
                MtvUtilDebug.Error("MtvUiLivePlayer", "mNotificationManager is null");
        }
    }

    private void showNotification(boolean flag)
    {
        MtvUtilDebug.High("MtvUiLivePlayer", (new StringBuilder()).append("showNotification() called.. showTicker").append(flag).toString());
        hideNotification();
        if(mMtvAppPlaybackContext != null)
        {
            MtvUtilDebug.High("MtvUiLivePlayer", "showNotification() called.. inside");
            Notification notification = new Notification();
            notification.icon = 0x7f020113;
            notification.when = 0L;
            notification.flags = 2 | notification.flags;
            if(!flag)
                notification.flags = 0x20 | notification.flags;
            Intent intent = new Intent();
            intent.setFlags(0x100000);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(getComponentName());
            PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
            notification.setLatestEventInfo(this, getString(0x7f070000), getNotificationContent(), pendingintent);
            if(mMtvAppPlaybackContext == null)
                mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            if(mNotificationManager != null)
            {
                if(flag)
                {
                    if(20487 != mMtvAppPlaybackContext.getState().getOp())
                    {
                        notification.setLatestEventInfo(this, getString(0x7f07009b), getNotificationContent(), pendingintent);
                        notification.tickerText = getString(0x7f07009b);
                        mNotificationManager.notify(0x7f020113, notification);
                        hideNotification();
                        notification.tickerText = getNotificationContent();
                        mNotificationManager.notify(0x7f020113, notification);
                    } else
                    {
                        notification.setLatestEventInfo(this, getString(0x7f07009c), getNotificationContent(), pendingintent);
                        notification.tickerText = getString(0x7f07009c);
                        mNotificationManager.notify(0x7f020113, notification);
                        hideNotification();
                        notification.tickerText = getNotificationContent();
                        mNotificationManager.notify(0x7f020113, notification);
                    }
                } else
                {
                    mNotificationManager.notify(0x7f020113, notification);
                }
            } else
            {
                MtvUtilDebug.Error("MtvUiLivePlayer", "mNotificationManager is null ");
            }
        }
    }

    private void showReservationAlert()
    {
        if(!isResumed())
        {
            MtvUtilDebug.High("MtvUiLivePlayer", "Live Player not resumed continuing with recording without showing popup...");
        } else
        {
            Bundle bundle = getIntent().getExtras();
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiPopUpActivity"));
            intent.putExtra("dbId", bundle.getInt("dbId", -1));
            intent.putExtra("popup_type", 2);
            intent.setFlags(0x10000000);
            MtvUtilDebug.High("MtvUiLivePlayer", "Mobile Tv - Reminder -user Alert");
            startActivity(intent);
        }
    }

    private void showToast(int i)
    {
        ActivityManager activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
        String s;
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        else
            s = null;
        if(s == null) goto _L2; else goto _L1
_L1:
        if(!s.equalsIgnoreCase("com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer")) goto _L4; else goto _L3
_L3:
        gen_toast = Toast.makeText(this, i, 0);
        gen_toast.show();
_L2:
        return;
_L4:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("showToast: Activity on top -> ").append(s).toString());
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void showToastforNoOperation(int i)
    {
        if(shownToastforOperationNA) goto _L2; else goto _L1
_L1:
        ActivityManager activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
        String s;
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        else
            s = null;
        if(s == null) goto _L2; else goto _L3
_L3:
        if(!s.equalsIgnoreCase("com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer")) goto _L5; else goto _L4
_L4:
        gen_toast = Toast.makeText(this, i, 1);
        gen_toast.show();
        shownToastforOperationNA = true;
_L2:
        return;
_L5:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("showToast: Activity on top -> ").append(s).toString());
        if(true) goto _L2; else goto _L6
_L6:
    }

    private int startRecording()
    {
        String s = null;
        String s1 = null;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        long l;
        int j;
        if(1 == mMtvPreferences.getSaveToStorage())
            l = MtvUtilMemoryStatus.getAvailableInternalMemorySize();
        else
            l = MtvUtilMemoryStatus.getAvailableExternalMemorySize();
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("availMemSize :").append(l).toString());
        if(l < 0x204000L)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "memory already full.. cannot start recording...");
            showErrorDialog(0x7f0700d6);
            j = 1;
        } else
        {
            byte byte0;
            String s2;
            int i;
            MtvOneSegChannel mtvonesegchannel;
            MtvOneSegProgram amtvonesegprogram[];
            String s3;
            if(mMtvPreferences.getBroadcastSetRecordingMode() == 0)
                byte0 = 2;
            else
                byte0 = 1;
            if(1 == mMtvPreferences.getSaveToStorage())
            {
                s2 = "/sdcard/video/MyTvFiles/";
                i = 1;
            } else
            {
                s2 = "/mnt/extSdCard/video/MyTvFiles/";
                i = 0;
            }
            mtvonesegchannel = getChannel();
            if(mtvonesegchannel != null)
                s = mtvonesegchannel.getServName();
            amtvonesegprogram = getProgram();
            if(amtvonesegprogram != null)
            {
                MtvOneSegProgram mtvonesegprogram = getCurrentProgramDetails(amtvonesegprogram);
                if(mtvonesegprogram != null)
                    s1 = mtvonesegprogram.getProgName();
            }
            s3 = (new MtvOneSegPlayer(byte0, s2, i, s, s1, new Date(date.getTime() - (long)calendar.get(15) - (long)calendar.get(16)))).getRecordURI();
            if(!mMtvPlayerOneSeg.startRecord(mMtvAppPlaybackContext, s3, (int)l))
            {
                MtvUtilDebug.High("MtvUiLivePlayer", "startRecording() failed");
                showToast(0x7f0700d8);
                j = -1;
            } else
            {
                MtvUtilDebug.High("MtvUiLivePlayer", "startRecording() start");
                j = 0;
            }
        }
        return j;
    }

    private void startReservationProgram(MtvReservation mtvreservation)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append(" startReservationProgram() : !!! Starting from ").append(mMtvPreferences.getReserveAlertFrom()).append("Reservation ID ").append(mMtvPreferences.getReservationAlertID()).toString());
        if(mMtvPlayerOneSeg != null && mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : error - MW not initilized yet!!! ");
_L4:
        return;
_L2:
        if(mtvreservation == null || mtvreservation.mPgmStatus != 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : error - not a valid reservation");
        } else
        {
            MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_retry_exit");
            MtvUtilDebug.Low("MtvUiLivePlayer", "startReservationProgram :NoSignal popup closed");
            MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_terminate");
            MtvUtilDebug.Low("MtvUiLivePlayer", "startReservationProgram :mNoSignalTerminate popup closed");
            MtvUiDialogsFrag.removeDialog(getFragmentManager(), "exit_confirmation");
            MtvUiDialogsFrag.removeDialog(getFragmentManager(), "save_exit_confirmation");
            if(mtvreservation.mPgmType == 0)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : !!!TYPE_REC");
                if(mMtvAppPlaybackContext.getState().getOp() != 20487)
                {
                    int j = mMtvPreferences.getLatestPChannelFromVChannel();
                    if(j > 0 && j != mtvreservation.mPch)
                    {
                        MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : recording : - not playing");
                        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(321, Integer.valueOf(mtvreservation.mPch)));
                        mMtvPreferences.setIsReservationProgram(true);
                    } else
                    if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                        mMtvPreferences.setIsReservationProgram(true);
                    else
                    if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                    {
                        MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : : recording : - already playing");
                        int k = startRecording();
                        if(k == 1)
                        {
                            mMtvPreferences.setIsReservationProgram(true);
                            CheckIsreservationAndUpdatefailureReason(5);
                        } else
                        if(k == -1)
                        {
                            mMtvPreferences.setIsReservationProgram(true);
                            CheckIsreservationAndUpdatefailureReason(2);
                        } else
                        {
                            if(mMtvPreferences.getReserveAlertFrom() == 0)
                            {
                                mLiveVideoSurfaceView.setKeepScreenOn(false);
                                mCpuWakeLock = ((PowerManager)getApplicationContext().getSystemService("power")).newWakeLock(0x10000001, "MobileTV");
                                mCpuWakeLock.acquire();
                            }
                            MtvReservationManager.UpdateStatus(this, mtvreservation, 6);
                            mLiveUiMsgHandler.postDelayed(RunnableresetComingReservationID, 5000L);
                        }
                    }
                } else
                if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : : recording : - already recording");
                    stopRecording();
                    mMtvPreferences.setIsReservationProgram(true);
                }
            } else
            if(mtvreservation.mPgmType == 1)
                if(mMtvAppPlaybackContext.getState().getOp() != 20487)
                {
                    int i = mMtvPreferences.getLatestPChannelFromVChannel();
                    if(i > 0 && (i != mtvreservation.mPch || mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING))
                    {
                        MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() : watch : - not playing same channel");
                        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(321, Integer.valueOf(mtvreservation.mPch)));
                    }
                    MtvReservationManager.UpdateStatus(this, mtvreservation, 6);
                } else
                if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    MtvUtilDebug.Low("MtvUiLivePlayer", " startReservationProgram() :: watch : - recording going on");
                    stopRecording();
                    mMtvPreferences.setIsReservationProgram(true);
                }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void startVolumeControlBar()
    {
        if(fragHandler != null && !fragHandler.isFragPresent(1))
        {
            if(!fragHandler.isFragPresent(15))
            {
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low("MtvUiLivePlayer", "onTouchEvent, adding status fragment");
                MtvUiFragHandler mtvuifraghandler;
                int ai[];
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                    fragHandler.addFrag(15, 4000L, false, new int[0]);
                else
                    fragHandler.addFrag(15, -1L, false, new int[0]);
                mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
            } else
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                fragHandler.resetTimer(15, 4000L);
            if(!fragHandler.isFragPresent(0))
            {
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low("MtvUiLivePlayer", "onTouchEvent, adding main fragment");
                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                    fragHandler.addFrag(0, 4000L, false, new int[0]);
                else
                    fragHandler.addFrag(0, -1L, false, new int[0]);
            } else
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                fragHandler.resetTimer(0, 4000L);
        }
        if(fragHandler != null && !fragHandler.isFragPresent(12))
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiLivePlayer", "onTouchEvent, adding main fragment");
            mtvuifraghandler = fragHandler;
            ai = new int[1];
            ai[0] = 0x7f0a0027;
            mtvuifraghandler.addFrag(12, 3000L, false, ai);
        } else
        {
            fragHandler.resetTimer(12, 3000L);
        }
    }

    private void stopRecording()
    {
        int i = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
        MtvUtilDebug.Mid("MtvUiLivePlayer", "stopRecording in LivePlayer");
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvPlayerOneSeg == null)
            mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvPlayerOneSeg.stopRecord(mMtvAppPlaybackContext);
        if(fragHandler != null)
        {
            fragHandler.removeFrag(15);
            fragHandler.removeFrag(1);
            fragHandler.removeFrag(12);
            if(i == 0)
            {
                fragHandler.addFrag(15, -1L, false, new int[0]);
                mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                fragHandler.addFrag(0, -1L, false, new int[0]);
            }
            StringBuilder stringbuilder = (new StringBuilder()).append("Fragments removed normally? ");
            Exception exception;
            android.app.Fragment fragment;
            android.app.Fragment fragment1;
            FragmentTransaction fragmenttransaction;
            boolean flag;
            if(!fragHandler.isFragPresent(1))
                flag = true;
            else
                flag = false;
            MtvUtilDebug.Low("MtvUiLivePlayer", stringbuilder.append(flag).toString());
        }
        MtvUtilDebug.Low("MtvUiLivePlayer", "if fragHandler has failed to remove fragments attempting  via fragment Manager...");
        fragment = getFragmentManager().findFragmentByTag("RecordFrag");
        fragment1 = getFragmentManager().findFragmentByTag("StatusBarFrag");
        fragmenttransaction = getFragmentManager().beginTransaction();
        if(fragmenttransaction == null) goto _L2; else goto _L1
_L1:
        if(fragment != null)
            fragmenttransaction.remove(fragment);
        else
            MtvUtilDebug.Low("MtvUiLivePlayer", "recFrag is null as fragHandler already removed it");
        if(fragment1 == null) goto _L4; else goto _L3
_L3:
        fragmenttransaction.remove(fragment1);
_L10:
        fragmenttransaction.commit();
          goto _L5
        exception;
        MtvUtilDebug.Low("MtvUiLivePlayer", "Exception occured while trying to delete fragments... :( Hidinig them from user...\n the fragments will be removed as soon as the Activity resumes...");
        if(fragHandler == null) goto _L7; else goto _L6
_L6:
        fragHandler.onUpdate(327, null, 1);
        fragHandler.onUpdate(327, null, 15);
_L8:
        exception.printStackTrace();
        break; /* Loop/switch isn't completed */
_L4:
        MtvUtilDebug.Low("MtvUiLivePlayer", "statusFrag is null as fragHandler already removed it");
        continue; /* Loop/switch isn't completed */
_L2:
        MtvUtilDebug.Low("MtvUiLivePlayer", "fTrans is null");
        break; /* Loop/switch isn't completed */
_L7:
        fragHandler = new MtvUiFragHandler(getFragmentManager(), 0, 0x7f0a0026);
        if(!isResumed())
            fragHandler.setEnabled(false);
        if(i == 0)
        {
            fragHandler.addFrag(15, -1L, false, new int[0]);
            mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
            mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
            fragHandler.addFrag(0, -1L, false, new int[0]);
        }
        if(true) goto _L8; else goto _L5
_L5:
        return;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private void unRegisterMediaButtonReceiver()
    {
        if(mAudioManager != null)
            mAudioManager.unregisterMediaButtonEventReceiver(new ComponentName(getPackageName(), com/samsung/sec/mtv/reciever/MtvBroadcastReceiver.getName()));
    }

    private void updateFragmentsOnLock()
    {
        boolean flag;
        MtvUtilDebug.Low("MtvUiLivePlayer", "inside updateFragmentsOnLock...");
        if(mMtvAppPlaybackContext != null)
        {
            if(mMtvAppPlaybackContext.getState().getOp() == 20487)
                flag = true;
            else
                flag = false;
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "updateFragmentsOnLock mMtvAppPlaybackContext is null");
            flag = false;
        }
        if(orientation != 0) goto _L2; else goto _L1
_L1:
        if(lockImage.getVisibility() == 0)
        {
            if(!flag)
                fragHandler.onUpdate(112, Boolean.valueOf(false), 0);
            else
                fragHandler.onUpdate(112, Boolean.valueOf(false), 1);
        } else
        if(!flag)
            fragHandler.onUpdate(112, Boolean.valueOf(true), 0);
        else
            fragHandler.onUpdate(112, Boolean.valueOf(true), 1);
_L4:
        if(fragHandler.isFragPresent(12))
            fragHandler.removeFrag(12);
        return;
_L2:
        if(lockImage.getVisibility() == 0)
        {
            if(!flag)
                fragHandler.removeFrag(0);
            else
                fragHandler.onUpdate(112, Boolean.valueOf(false), 1);
            fragHandler.removeFrag(15);
        } else
        if(flag)
            fragHandler.onUpdate(112, Boolean.valueOf(true), 1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateFragmentsOnStateChange()
    {
        boolean flag;
        MtvUtilDebug.Low("MtvUiLivePlayer", "inside updateFragmentsOnStateChange...");
        orientation = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
        flag = false;
        if(mMtvAppPlaybackContext != null)
            if(mMtvAppPlaybackContext.getState().getOp() == 20487)
                flag = true;
            else
                flag = false;
        if(orientation != 0) goto _L2; else goto _L1
_L1:
        if(!flag)
        {
            if(!fragHandler.isFragPresent(0))
                fragHandler.addFrag(0, -1L, false, new int[0]);
            else
                fragHandler.resetTimer(0, -1L);
            fragHandler.removeFrag(1);
        } else
        {
            fragHandler.removeFrag(0);
        }
        if(!fragHandler.isFragPresent(15))
            fragHandler.addFrag(15, -1L, false, new int[0]);
        else
            fragHandler.resetTimer(15, -1L);
        mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
        mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
_L4:
        return;
_L2:
        if(!flag)
        {
            if(fragHandler.isFragPresent(0))
                fragHandler.resetTimer(0, 3000L);
            fragHandler.removeFrag(1);
        } else
        {
            fragHandler.removeFrag(0);
        }
        if(fragHandler.isFragPresent(15))
        {
            mLiveUiMsgHandler.removeCallbacks(mRunnableUpdateRecordComponents);
            mLiveUiMsgHandler.postDelayed(mRunnableUpdateRecordComponents, 3000L);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateLiveViews()
    {
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(310, getCaptionText()));
        if(fragHandler != null)
        {
            if(fragHandler.isFragPresent(15))
                fragHandler.onUpdate(101, getCurrentChannelProgramTitle(false, true), 15);
            if(fragHandler.isFragPresent(0))
                fragHandler.onUpdate(101, getCurrentChannelProgramTitle(false, false), 0);
        }
    }

    public boolean HomeKeyPresses(Context context)
    {
        String s;
        s = null;
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        if(s == null) goto _L2; else goto _L1
_L1:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("HomeKeyPresses :topActivityName = ").append(s).toString());
        if(s.contains("com.samsung.sec.mtv")) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    void bindLiveUItoService()
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "bindLiveUItoService called");
        Intent intent = new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService);
        startService(intent);
        bindService(intent, mConnection, 1);
    }

    long calculateDelayForStartChannel()
    {
        long l = System.currentTimeMillis();
        long l1 = l - mPreviousChannelChangeTime;
        long l2;
        if(l1 > 2500L)
            l2 = 0L;
        else
            l2 = 2500L - l1;
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("DelayForStart:").append(l2).append(": prev:").append(mPreviousChannelChangeTime).append(": current:").append(mPreviousChannelChangeTime).toString());
        mPreviousChannelChangeTime = l;
        return l2;
    }

    public MtvOneSegChannel getChannel()
    {
        MtvOneSegChannel mtvonesegchannel;
        if(mMtvAppPlaybackContext != null)
            mtvonesegchannel = mMtvAppPlaybackContext.getAttribute().getChannel();
        else
            mtvonesegchannel = null;
        return mtvonesegchannel;
    }

    public MtvOneSegProgram getCurrentProgramDetails(MtvOneSegProgram amtvonesegprogram[])
    {
        MtvOneSegProgram mtvonesegprogram = null;
        long l = getStreamTime();
        int i = amtvonesegprogram.length;
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    MtvOneSegProgram mtvonesegprogram1 = amtvonesegprogram[j];
                    if(mtvonesegprogram1 == null || l <= mtvonesegprogram1.getStartTime().getTime() || l >= mtvonesegprogram1.getEndTime().getTime())
                        break label0;
                    mtvonesegprogram = mtvonesegprogram1;
                }
                return mtvonesegprogram;
            }
            j++;
        } while(true);
    }

    public MtvOneSegProgram[] getProgram()
    {
        MtvOneSegProgram amtvonesegprogram[];
        if(mMtvAppPlaybackContext != null)
            amtvonesegprogram = mMtvAppPlaybackContext.getAttribute().getProgram();
        else
            amtvonesegprogram = null;
        return amtvonesegprogram;
    }

    public long getStreamTime()
    {
        long l;
        if(mMtvAppPlaybackContext != null)
            l = mMtvAppPlaybackContext.getAttribute().getTot();
        else
            l = 0L;
        return l;
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

    protected void isScheduledReservationProgram()
    {
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("isScheduledReservationProgram: mMtvPreferences: ").append(mMtvPreferences).append(" mMtvPreferences.isReservationProgram(): ").append(mMtvPreferences.isReservationProgram()).append(" mMtvPreferences.getReservationAlertID(): ").append(mMtvPreferences.getReservationAlertID()).toString());
        if(!mMtvPreferences.isReservationProgram() || mMtvPreferences.getReservationAlertID() <= -1) goto _L2; else goto _L1
_L1:
        MtvReservation mtvreservation;
        mtvreservation = MtvReservationManager.find(getApplicationContext(), mMtvPreferences.getReservationAlertID());
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("isScheduledReservationProgram: CurrentReservation: ").append(mtvreservation).toString());
        if(mtvreservation == null) goto _L2; else goto _L3
_L3:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("isScheduledReservationProgram: CurrentReservation.mPgmStatus: ").append(mtvreservation.mPgmStatus).append(" CurrentReservation.mPgmType: ").append(mtvreservation.mPgmType).toString());
        if(mtvreservation.mPgmStatus != 6) goto _L5; else goto _L4
_L4:
        if(mtvreservation.mPgmType == 1)
            checkIsReservationAndEnd(1);
_L2:
        return;
_L5:
        if(mtvreservation.mPgmStatus == 0)
            checkIsReservationAndEnd(2);
        if(true) goto _L2; else goto _L6
_L6:
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onActivityResult: requestCode=").append(i).append(", resultCode").append(j).toString());
        if(i == 0 && j == -1)
        {
            if(intent.getBooleanExtra("channelinput", false))
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onActivityResult for Channel input");
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(319));
            }
            if(intent.getBooleanExtra("noAreaSet", false))
                mLiveUiMsgHandler.sendEmptyMessage(306);
        } else
        {
            super.onActivityResult(i, j, intent);
        }
    }

    public void onBackPressed()
    {
        if(!MtvUiFragHandler.removeUnManagedFrag("ProgInfoFrag", this)) goto _L2; else goto _L1
_L1:
        if(fragHandler != null)
            fragHandler.onUpdate(108, null, 0);
_L4:
        return;
_L2:
        if(fragHandler != null)
            if(fragHandler.isFragPresent(2))
            {
                fragHandler.removeFrag(2);
                fragHandler.onUpdate(108, null, 0);
            } else
            if(fragHandler.isFragPresent(6))
            {
                fragHandler.removeFrag(6);
                fragHandler.onUpdate(108, null, 0);
            } else
            {
                showExitApplicationDialog();
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCreate(Bundle bundle)
    {
        mExitToast = null;
        MtvUtilDebug.Low("MtvUiLivePlayer", "onCreate...");
        super.onCreate(bundle);
        orientation = MtvUtilAppService.getCurrentOrientation(getApplicationContext());
        if(!checkIsCalling(true) || bundle != null) goto _L2; else goto _L1
_L1:
        finish();
_L4:
        return;
_L2:
        if(checkIsRelaunched() || checkIsMassStorageUSBEnabled())
        {
            restrictLaunch = true;
            finish();
            continue; /* Loop/switch isn't completed */
        }
        MtvUtilMemoryStatus.setAppComponents(getApplicationContext());
        if(mAudioManager == null)
            mAudioManager = (AudioManager)getSystemService("audio");
        registerMediaButtonReceiver();
        if(bundle == null && !MtvUtilAppService.isIntentAvailable(getApplicationContext(), new Intent("ACTION_EPGAPP_VIEW")))
            MtvUtilConfigSetting.EPGAPP_ENABLED = false;
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        mDebugSetting = MtvUtilDebug.getDebugInstance();
        mDebugSetting.loadDbgInfoFromFile();
        mMtvPreferences = new MtvPreferences(getApplicationContext());
        latestChannelValidity();
        bindLiveUItoService();
        setActivityProperty();
        initViewControl();
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
        mNotificationManager = (NotificationManager)getSystemService("notification");
        fragHandler = new MtvUiFragHandler(getFragmentManager(), 0, 0x7f0a0026);
        fragHandler.setEnabled(true);
        if(bundle != null)
        {
            fragHandler.fillFragHandlerData(bundle);
            lockImage.setVisibility(bundle.getInt("lockVisibility"));
            updateFragmentsOnStateChange();
            showNotification();
        }
        if(MtvUtilConfigSetting.EPGAPP_ENABLED)
        {
            Intent intent = getIntent();
            if(intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_VIEW"))
            {
                MtvUtilDebug.Mid("MtvUiLivePlayer", "OnCreate EPG -ACTION_DTV_VIEW received");
                epgChNo = intent.getIntExtra("EXTRA_VIEW_CHANNEL_NO", -1);
                epgServiceId = intent.getIntExtra("EXTRA_VIEW_SERVICE_ID", -1);
                if(MtvAreaManager.getCount(getApplicationContext()) != 0)
                    break; /* Loop/switch isn't completed */
                com.samsung.sec.mtv.ui.common.MtvUiDialogsFrag.EPGErrorDialogFragment.newInstance(0x7f0702d1, 0x7f070293).show(getFragmentManager(), "EPGErrorDialogFragment");
            }
        }
_L5:
        getWindow().addFlags(8192);
        if(true) goto _L4; else goto _L3
_L3:
        MtvChannel mtvchannel = MtvChannelManager.findByServiceId(getApplicationContext(), epgServiceId);
        if(mtvchannel != null)
        {
            MtvUtilDebug.Error("MtvUiLivePlayer", (new StringBuilder()).append("Actual pch : [").append(epgChNo).append("] from EPG and pch[").append(mtvchannel.mPhysicalNum).append("] obtained from mapping").toString());
            if(mtvchannel.mPhysicalNum != -1)
                setLatestChannelsByPChannel(mtvchannel.mPhysicalNum);
            else
                MtvUtilDebug.Error("MtvUiLivePlayer", "pch is -1 ! Cross verify the Area Station info ...");
        } else
        {
            showToast(0x7f07028e);
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, null));
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    protected void onDestroy()
    {
        super.onDestroy();
        MtvUtilDebug.Low("MtvUiLivePlayer", "...onDestroy()");
        if(mLiveUiMsgHandler.hasMessages(306))
            mLiveUiMsgHandler.removeMessages(306);
        if(fragHandler != null)
            fragHandler.setEnabled(false);
        if(mService != null)
        {
            unbindService(mConnection);
            mService = null;
        }
        if(restrictLaunch)
        {
            restrictLaunch = false;
        } else
        {
            unRegisterMediaButtonReceiver();
            if(isFinishing())
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onDestroy called: isFinishing...");
                if(mMtvPreferences == null)
                    mMtvPreferences = new MtvPreferences(getApplicationContext());
                mMtvPreferences.setComingReservationID(-1);
                if(mMtvAudMgr != null)
                    mMtvAudMgr.removeCallStateListener();
                if(fragHandler != null)
                    fragHandler.reset();
                MtvUtilAppService.forceresetMtvVisibiltySettings();
                stopService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService));
                mService = null;
                if(mBmlSurface != null)
                    mBmlSurface.onDestroy();
                releaseMtvPlayer();
                if(mExitProgressDialog != null)
                {
                    mExitProgressDialog.dismiss();
                    mExitProgressDialog = null;
                }
                mPendingPlayerNotification = null;
                MtvUtilAppService.setAppExiting(false);
                MtvUtilAppService.setAbnormalExit(false);
                mLiveUiMsgHandler.removeCallbacks(RunnableNextPreviousChannel);
                RunnableNextPreviousChannel = null;
                if(mNotificationManager != null)
                    mNotificationManager.cancel(0x7f020113);
                mNotificationManager = null;
                mChannelGestureDetector = null;
                mControlAnimationRunnable = null;
                mLiveUiMsgHandler.removeCallbacksAndMessages(null);
            }
            if(mLiveUiMsgHandler != null)
            {
                mLiveUiMsgHandler.removeCallbacks(RunnableUpdateProgramChannelInfo);
                RunnableUpdateProgramChannelInfo = null;
                mLiveUiMsgHandler.removeCallbacks(RunnableUpdateSignalInfo);
                RunnableUpdateSignalInfo = null;
                mLiveUiMsgHandler.removeCallbacks(runnableVolumeDecreasing);
                runnableVolumeDecreasing = null;
                mLiveUiMsgHandler.removeCallbacks(runnableVolumeEscalating);
                runnableVolumeEscalating = null;
            }
            MtvUtilAppService.unbindDrawables(findViewById(0x7f0a0018));
            System.gc();
            hideNotification();
            if(mCpuWakeLock != null && mCpuWakeLock.isHeld())
                mCpuWakeLock.release();
            MtvUtilDebug.Low("MtvUiLivePlayer", "...onDestroy() Completed");
        }
    }

    public void onFragEvent(int i, Object obj)
    {
        boolean flag;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onFragEvent: event[").append(i).append("]").toString());
        if(mMtvAppPlaybackContext != null && com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == mMtvAppPlaybackContext.getState().getState() && 20487 == mMtvAppPlaybackContext.getState().getOp())
            flag = true;
        else
            flag = false;
        i;
        JVM INSTR lookupswitch 27: default 308
    //                   201: 724
    //                   202: 749
    //                   203: 770
    //                   211: 328
    //                   212: 314
    //                   213: 343
    //                   220: 350
    //                   223: 514
    //                   224: 559
    //                   225: 591
    //                   226: 683
    //                   227: 449
    //                   228: 1282
    //                   230: 647
    //                   231: 669
    //                   241: 791
    //                   242: 826
    //                   243: 308
    //                   244: 861
    //                   251: 949
    //                   252: 967
    //                   253: 1115
    //                   261: 991
    //                   274: 1104
    //                   276: 1195
    //                   277: 1177
    //                   278: 1217;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L1 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27
_L1:
        return;
_L6:
        if(mBmlSurface != null)
            setBMLLayoutFullView(false);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mBmlSurface != null)
            mBmlSurface.openBMLHomePage();
        continue; /* Loop/switch isn't completed */
_L7:
        showExitApplicationDialog();
        continue; /* Loop/switch isn't completed */
_L8:
        if(fragHandler != null)
        {
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            {
                fragHandler.resetTimer(0, 4000L);
                fragHandler.resetTimer(15, 4000L);
            }
            if(!fragHandler.isFragPresent(12))
            {
                MtvUiFragHandler mtvuifraghandler1 = fragHandler;
                int ai1[] = new int[1];
                ai1[0] = 0x7f0a0027;
                mtvuifraghandler1.addFrag(12, 3000L, false, ai1);
            } else
            {
                fragHandler.removeFrag(12);
            }
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if(fragHandler != null)
            if(!fragHandler.isFragPresent(12))
            {
                MtvUiFragHandler mtvuifraghandler = fragHandler;
                int ai[] = new int[1];
                ai[0] = 0x7f0a0027;
                mtvuifraghandler.addFrag(12, 3000L, false, ai);
            } else
            {
                fragHandler.removeFrag(12);
            }
        continue; /* Loop/switch isn't completed */
_L9:
        if(fragHandler != null)
        {
            fragHandler.resetTimer(0, -1L);
            fragHandler.resetTimer(15, -1L);
            fragHandler.resetTimer(12, -1L);
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if(fragHandler != null)
        {
            fragHandler.onUpdate(108, null, 0);
            fragHandler.onUpdate(108, null, 1);
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if(fragHandler != null)
        {
            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            {
                fragHandler.resetTimer(0, 4000L);
                fragHandler.resetTimer(15, 4000L);
            }
            fragHandler.resetTimer(12, 3000L);
        }
        continue; /* Loop/switch isn't completed */
_L15:
        if(fragHandler != null)
            fragHandler.onUpdate(105, null, 15);
        continue; /* Loop/switch isn't completed */
_L16:
        mLiveUiMsgHandler.sendEmptyMessage(306);
        continue; /* Loop/switch isn't completed */
_L12:
        if(fragHandler != null)
        {
            fragHandler.onUpdate(108, null, 1);
            fragHandler.onUpdate(108, null, 0);
            fragHandler.removeFrag(12);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(307, (Integer)obj));
        continue; /* Loop/switch isn't completed */
_L3:
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(308));
        continue; /* Loop/switch isn't completed */
_L4:
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(315));
        continue; /* Loop/switch isn't completed */
_L17:
        if(blockUiEventsForReservation() || flag)
            showToastforNoOperation(0x7f0702d3);
        else
            mLiveUiMsgHandler.sendEmptyMessage(313);
        continue; /* Loop/switch isn't completed */
_L18:
        if(blockUiEventsForReservation() || flag)
            showToastforNoOperation(0x7f0702d3);
        else
            mLiveUiMsgHandler.sendEmptyMessage(314);
        continue; /* Loop/switch isn't completed */
_L19:
        if(blockUiEventsForReservation() || flag)
        {
            showToastforNoOperation(0x7f0702d3);
        } else
        {
            ActivityManager activitymanager = (ActivityManager)getApplicationContext().getSystemService("activity");
            int j;
            MtvURI mtvuri;
            int k;
            MtvChannel mtvchannel;
            String s;
            if(activitymanager != null)
                s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
            else
                s = null;
            if(s != null && !s.equalsIgnoreCase("com.samsung.sec.mtv.ui.channelguide.MtvUiChannelGuide"))
                LaunchChannelGuideActivity(0);
        }
        continue; /* Loop/switch isn't completed */
_L20:
        if(fragHandler != null)
            fragHandler.removeFrag(4);
        continue; /* Loop/switch isn't completed */
_L21:
        MtvUtilDebug.Low("MtvUiLivePlayer", "MTV_UPDATE_FRAG_CMD_RECORD_DESTROY... enable the audio mute");
        mMtvAudMgr.setAudioMute(false);
        stopRecording();
        continue; /* Loop/switch isn't completed */
_L23:
        checkIsReservationAndEnd(1);
        k = ((Integer)obj).intValue();
        if(k > 0 && k < 13)
        {
            mtvchannel = MtvChannelManager.findByVChannel(getApplicationContext(), k);
            if(mtvchannel != null && mtvchannel.mPhysicalNum > 0)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "found a virtual channel mapping the channel input given...");
                mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(321, Integer.valueOf(mtvchannel.mPhysicalNum)));
                continue; /* Loop/switch isn't completed */
            }
        }
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(321, (Integer)obj));
        continue; /* Loop/switch isn't completed */
_L24:
        endReservationProgram((Bundle)obj);
        continue; /* Loop/switch isn't completed */
_L22:
        if(fragHandler != null && !fragHandler.isFragPresent(15))
        {
            fragHandler.addFrag(15, -1L, false, new int[0]);
            mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
            mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
        }
        continue; /* Loop/switch isn't completed */
_L26:
        stopRecording();
        mLiveUiMsgHandler.sendEmptyMessage(306);
        continue; /* Loop/switch isn't completed */
_L25:
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, obj));
        continue; /* Loop/switch isn't completed */
_L27:
        MtvUtilDebug.Low("MtvUiLivePlayer", "onFragEvent MTV_UPDATE_FRAG_CMD_DIALOGS_SIGNAL_ALERT_RETRY_OK");
        if(mLiveUiMsgHandler != null)
        {
            j = mMtvPreferences.getLatestPChannelFromVChannel();
            if(mMtvPlayerOneSeg != null)
            {
                mtvuri = new MtvURI(2, j);
                mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
            }
        }
        continue; /* Loop/switch isn't completed */
_L14:
        fragHandler.removeFrag(12);
        if(true) goto _L1; else goto _L28
_L28:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        boolean flag1 = true;
        if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState().compareTo(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED) >= 0)
            flag = flag1;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", "onKeyDown");
        if(fragHandler != null)
            if(fragHandler.isPhoneLocked() == flag1 && i != 26 && i != 24 && i != 25)
                MtvUtilDebug.Low("MtvUiLivePlayer", "Locked Key not supported");
            else
            if(i == 25 || i == 24)
            {
                if(fragHandler.isPhoneLocked() == flag1 || fragHandler.isFragPresent(2) || fragHandler.isFragPresent(6) || mBmlSurface.IsBMLFullView() || !flag)
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
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "Super called for key");
                flag1 = super.onKeyDown(i, keyevent);
            }
        return flag1;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(i == 24)
            mLiveUiMsgHandler.postDelayed(runnableVolumeEscalating, 10L);
        else
        if(i == 25)
            mLiveUiMsgHandler.postDelayed(runnableVolumeDecreasing, 10L);
        else
            flag = super.onKeyLongPress(i, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag = true;
        if(mMtvAppPlaybackContext.getState().getState().compareTo(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED) >= 0)
            flag1 = flag;
        else
            flag1 = false;
        if(mMtvAppPlaybackContext.getState().getState().compareTo(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED) >= 0)
            flag2 = flag;
        else
            flag2 = false;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiLivePlayer", "onKeyUp");
        if(fragHandler != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(fragHandler.isPhoneLocked() && i != 26 && i != 24 && i != 25)
            MtvUtilDebug.Low("MtvUiLivePlayer", "Locked Key not supported");
        else
        if(i == 26 && !keyevent.isLongPress() && flag1)
        {
            if(lockImage.getVisibility() != 0)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onKeyUp, adding touch lock fragment");
                lockImage.setVisibility(0);
                fragHandler.addFrag(5, -1L, false, new int[0]);
                updateFragmentsOnStateChange();
                updateFragmentsOnLock();
                mBmlSurface.setPhoneLockedState(flag);
                android.app.Fragment fragment1 = getFragmentManager().findFragmentByTag("ProgInfoFrag");
                if(fragment1 != null)
                {
                    FragmentTransaction fragmenttransaction1 = getFragmentManager().beginTransaction();
                    fragmenttransaction1.hide(fragment1);
                    fragmenttransaction1.commit();
                }
                requestSystemKeyEvent(3, flag);
            } else
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onKeyUp, removing touch lock fragment");
                lockImage.setVisibility(8);
                fragHandler.removeFrag(5);
                updateFragmentsOnStateChange();
                updateFragmentsOnLock();
                mBmlSurface.setPhoneLockedState(false);
                android.app.Fragment fragment = getFragmentManager().findFragmentByTag("ProgInfoFrag");
                if(fragment != null)
                {
                    FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
                    fragmenttransaction.show(fragment);
                    fragmenttransaction.commit();
                }
                requestSystemKeyEvent(3, false);
            }
        } else
        if(i == 24 || i == 25)
        {
            if(mMtvAudMgr != null)
                mMtvAudMgr.setAudioMute(false);
            if(flag2 && !mBmlSurface.IsBMLFullView() && (!fragHandler.isPhoneLocked() || !fragHandler.isFragPresent(2)))
                if(i == 24)
                {
                    mLiveUiMsgHandler.removeCallbacks(runnableVolumeEscalating);
                    fragHandler.onUpdate(106, null, 12);
                } else
                {
                    mLiveUiMsgHandler.removeCallbacks(runnableVolumeDecreasing);
                    fragHandler.onUpdate(107, null, 12);
                }
            if(fragHandler.isPhoneLocked())
                fragHandler.onUpdate(108, null, 0);
        } else
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "Super called for key");
            flag = super.onKeyUp(i, keyevent);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        MtvUtilDebug.Low("MtvUiLivePlayer", "onNewIntent()...");
        if(!checkIsCalling(true)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(MtvUtilConfigSetting.EPGAPP_ENABLED)
        {
            if(intent != null && intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_VIEW"))
            {
                epgChNo = intent.getIntExtra("EXTRA_VIEW_CHANNEL_NO", -1);
                epgServiceId = intent.getIntExtra("EXTRA_VIEW_SERVICE_ID", -1);
                MtvUtilDebug.Mid("MtvUiLivePlayer", (new StringBuilder()).append("onNewIntent EPG -ACTION_DTV_VIEW received channel: ").append(epgChNo).toString());
                if(MtvAreaManager.getCount(getApplicationContext()) == 0)
                {
                    com.samsung.sec.mtv.ui.common.MtvUiDialogsFrag.EPGErrorDialogFragment.newInstance(0x7f0702d1, 0x7f070293).show(getFragmentManager(), "EPGErrorDialogFragment");
                } else
                {
                    MtvChannel mtvchannel = MtvChannelManager.findByServiceId(getApplicationContext(), epgServiceId);
                    if(mtvchannel != null)
                    {
                        MtvUtilDebug.Error("MtvUiLivePlayer", (new StringBuilder()).append("Actual pch : [").append(epgChNo).append("] from EPG and pch[").append(mtvchannel.mPhysicalNum).append("] obtained from mapping").toString());
                        if(mtvchannel.mPhysicalNum != -1)
                        {
                            setLatestChannelsByPChannel(mtvchannel.mPhysicalNum);
                            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(321, Integer.valueOf(mtvchannel.mPhysicalNum)));
                        } else
                        {
                            MtvUtilDebug.Error("MtvUiLivePlayer", "pch is -1 ! Cross verify the Area Station info ...");
                        }
                    } else
                    {
                        showToast(0x7f07028e);
                        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, null));
                    }
                }
            } else
            {
                setIntent(intent);
            }
        } else
        if(intent != null)
            setIntent(intent);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag = false;
        menuitem.getItemId();
        JVM INSTR tableswitch 0 23: default 120
    //                   0 122
    //                   1 129
    //                   2 249
    //                   3 257
    //                   4 300
    //                   5 323
    //                   6 346
    //                   7 354
    //                   8 120
    //                   9 559
    //                   10 590
    //                   11 605
    //                   12 615
    //                   13 120
    //                   14 120
    //                   15 120
    //                   16 625
    //                   17 120
    //                   18 408
    //                   19 362
    //                   20 505
    //                   21 120
    //                   22 454
    //                   23 525;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L1 _L10 _L11 _L12 _L13 _L1 _L1 _L1 _L14 _L1 _L15 _L16 _L17 _L1 _L18 _L19
_L1:
        return flag;
_L2:
        showExitApplicationDialog();
        continue; /* Loop/switch isn't completed */
_L3:
        long l;
        if(1 == mMtvPreferences.getSaveToStorage())
            l = MtvUtilMemoryStatus.getAvailableInternalMemorySize();
        else
            l = MtvUtilMemoryStatus.getAvailableExternalMemorySize();
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("availMemSize :").append(l).toString());
        if(l < 0x204000L)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "memory already full.. cannot start capture...");
            showErrorDialog(0x7f070049);
            flag = true;
        } else
        {
            mMtvPlayerOneSeg.captFrame(mMtvAppPlaybackContext);
            if(fragHandler != null)
                fragHandler.addFrag(4, 2000L, false, new int[0]);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        startRecording();
        continue; /* Loop/switch isn't completed */
_L5:
        if(MtvUtilConfigSetting.EPGAPP_ENABLED)
        {
            Intent intent1 = new Intent();
            intent1.setAction("ACTION_EPGAPP_VIEW");
            intent1.putExtra("EXTRA_SERVICE_ID", 0);
            startActivity(intent1);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            removeFragments();
        LaunchChannelGuideActivity(2);
        continue; /* Loop/switch isn't completed */
_L7:
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
            removeFragments();
        LaunchChannelGuideActivity(0);
        continue; /* Loop/switch isn't completed */
_L8:
        LaunchChannelGuideActivity(1);
        continue; /* Loop/switch isn't completed */
_L9:
        LaunchChannelGuideActivity(3);
        continue; /* Loop/switch isn't completed */
_L16:
        if(fragHandler != null)
        {
            MtvUiFragHandler mtvuifraghandler1 = fragHandler;
            int ai1[] = new int[2];
            ai1[0] = 0x7f0a0019;
            ai1[1] = 19;
            mtvuifraghandler1.addFrag(2, -1L, false, ai1);
        }
        continue; /* Loop/switch isn't completed */
_L15:
        if(fragHandler != null)
        {
            MtvUiFragHandler mtvuifraghandler = fragHandler;
            int ai[] = new int[2];
            ai[0] = 0x7f0a0019;
            ai[1] = 18;
            mtvuifraghandler.addFrag(2, -1L, false, ai);
        }
        continue; /* Loop/switch isn't completed */
_L18:
        Bundle bundle = new Bundle();
        bundle.putBoolean("expire", false);
        bundle.putInt("remainTime", mMtvPreferences.getAutoPowerOffTime());
        MtvUiSleepTimerDialogFrag.newInstance(bundle).show(getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L17:
        (new com.samsung.sec.mtv.ui.common.MtvUiSettingsFrag.SaveToDialogFragment()).show(getFragmentManager(), "SaveToDialogFragment");
        continue; /* Loop/switch isn't completed */
_L19:
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiSettingsBroadcast"));
        startActivity(intent);
        continue; /* Loop/switch isn't completed */
_L10:
        if(mBmlSurface != null)
            if(!mBmlSurface.IsBMLFullView())
                setBMLLayoutFullView(true);
            else
                setBMLLayoutFullView(false);
        continue; /* Loop/switch isn't completed */
_L11:
        if(mBmlSurface != null)
            mBmlSurface.openBMLHomePage();
        continue; /* Loop/switch isn't completed */
_L12:
        mMtvAudMgr.transferToBT();
        continue; /* Loop/switch isn't completed */
_L13:
        mMtvAudMgr.transferToPhone();
        continue; /* Loop/switch isn't completed */
_L14:
        MtvUtilDebug.Low("MtvUiLivePlayer", "MENU_RECORD_STOP_RECORDING... enable the auiod mute");
        mMtvAudMgr.setAudioMute(false);
        checkRecordingTime();
        if(true) goto _L1; else goto _L20
_L20:
    }

    public void onOptionsMenuClosed(Menu menu)
    {
        optionsMenu = null;
        super.onOptionsMenuClosed(menu);
    }

    protected void onPause()
    {
        super.onPause();
        unRegisterMediaButtonReceiver();
        if(mBmlSurface != null)
            mBmlSurface.onPause();
        registerVideoSurfaceView(false);
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:command : ").append(i).append(" status : ").append(j).toString());
        if(mMtvAudMgr == null)
            mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        i;
        JVM INSTR tableswitch 20481 20495: default 152
    //                   20481 153
    //                   20482 464
    //                   20483 1211
    //                   20484 743
    //                   20485 152
    //                   20486 1643
    //                   20487 1352
    //                   20488 1560
    //                   20489 152
    //                   20490 152
    //                   20491 152
    //                   20492 152
    //                   20493 152
    //                   20494 396
    //                   20495 1176;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7 _L8 _L1 _L1 _L1 _L1 _L1 _L9 _L10
_L12:
        return;
_L2:
        if(j == 24581)
        {
            if(mExitProgressDialog != null && mExitProgressDialog.isShowing())
            {
                MtvUtilDebug.Mid("MtvUiLivePlayer", "onPlayerNotification - CMD_CREATE - exiting");
                if(!mLiveUiMsgHandler.hasMessages(306))
                    mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)));
            } else
            {
                mExitProgressDialog = null;
                if(mBmlSurface != null)
                    runOnUiThread(new Runnable() {

                        public void run()
                        {
                            MtvUiLivePlayer.mBmlSurface.onResume(mMtvAppPlaybackContext);
                        }

                        final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
                    });
                mMtvAudMgr.setAudioEnable(true);
                runOnUiThread(runnableShowNotification);
                if(MtvAreaManager.getCount(getApplicationContext()) == 0)
                {
                    runOnUiThread(new Runnable() {

                        public void run()
                        {
                            Intent intent = getIntent();
                            boolean flag1;
                            if(intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_VIEW"))
                                flag1 = true;
                            else
                                flag1 = false;
                            if(!isFinishing() && !flag1)
                                LaunchChannelGuideActivity(0);
                        }

                        final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
                    });
                } else
                {
                    runOnUiThread(new Runnable() {

                        public void run()
                        {
                            registerVideoSurfaceView(true);
                        }

                        final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
                    });
                    MtvURI mtvuri = new MtvURI(2, mMtvPreferences.getLatestPChannelFromVChannel());
                    runOnUiThread(new Runnable() {

                        public void run()
                        {
                            if(fragHandler != null && !fragHandler.isPhoneLocked())
                            {
                                if(!fragHandler.isFragPresent(15))
                                {
                                    MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification, adding Status fragment in CMD_CREATE, STAT_COMPLETED");
                                    fragHandler.addFrag(15, -1L, false, new int[0]);
                                    mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                                    mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                                }
                                if(!fragHandler.isFragPresent(0))
                                {
                                    MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification, adding Main fragment in CMD_CREATE, STAT_COMPLETED");
                                    fragHandler.addFrag(0, -1L, false, new int[0]);
                                }
                            }
                        }

                        final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
                    });
                    runOnUiThread(RunnableCheckIsReservationAndStart);
                    if(mMtvPlayerOneSeg != null)
                        mMtvPlayerOneSeg.open(mMtvAppPlaybackContext, mtvuri);
                }
            }
        } else
        if(j == 24580)
        {
            MtvUtilDebug.Error("MtvUiLivePlayer", "onPlayerNotification - CMD_CREATE - exiting");
            mMtvPlayerOneSeg.delete(mMtvAppPlaybackContext);
        }
          goto _L1
_L9:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:CMD_CLOSE: Status-").append(j).toString());
        switch(j)
        {
        case 24581: 
            if(mMtvAppPlaybackContext != null)
                mMtvAppPlaybackContext.getAttribute().registerListener(this);
            break;
        }
          goto _L1
_L3:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:CMD_OPEN: Status-").append(j).toString());
        switch(j)
        {
        case 24577: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_OPEN:STAT_STARTED");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070087)));
            runOnUiThread(runnableShowNotification);
            break;

        case 24582: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_OPEN:STAT_BUFFERING_BEGIN");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            break;

        case 24583: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_OPEN:STAT_BUFFERING_PROGRESS");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            break;

        case 24584: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_OPEN:STAT_BUFFERING_END");
            mLiveUiMsgHandler.removeMessages(300);
            mLiveUiMsgHandler.sendMessageDelayed(mLiveUiMsgHandler.obtainMessage(300, null), 500L);
            break;

        case 24580: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "STATUS_FAIL_SIGNAL_ERROR");
            CheckIsReservationPartialOrNone(4);
            if(k == mMtvPreferences.getLatestPChannel())
                runOnUiThread(new RunnableShowSignalAlertDialog(0));
            break;
        }
          goto _L1
_L5:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:CMD_PLAY Status-").append(j).toString());
        switch(j)
        {
        case 24577: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_PLAY:STAT_STARTED");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            break;

        case 24581: 
            if(mMtvAudMgr != null)
                mMtvAudMgr.updateCurrentAudioEffectAndMode();
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null && !fragHandler.isPhoneLocked())
                    {
                        if(fragHandler.isFragPresent(12))
                        {
                            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification, removing Volume fragment");
                            if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                                fragHandler.removeFrag(12);
                        }
                        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                        {
                            if(fragHandler.isFragPresent(15))
                            {
                                MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification, removing status fragment");
                                fragHandler.removeFrag(15);
                            }
                            if(fragHandler.isFragPresent(0))
                            {
                                MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification, removing main fragment");
                                if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 1)
                                    fragHandler.removeFrag(0);
                            }
                        }
                    }
                }

                final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
            });
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(optionsMenu != null)
                    {
                        closeOptionsMenu();
                        optionsMenu = null;
                    }
                    MtvUiLivePlayer.mBmlSurface.setVisibility(0);
                }

                final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
            });
            runOnUiThread(RunnableCheckIsReservationAndStart);
            break;

        case 24582: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_PLAY:STAT_BUFFERING_BEGIN");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            break;

        case 24583: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_PLAY:STAT_BUFFERING_PROGRESS");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, getString(0x7f070086)));
            break;

        case 24584: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...CMD_PLAY:STAT_BUFFERING_END");
            mLiveUiMsgHandler.removeMessages(300);
            mLiveUiMsgHandler.sendMessageDelayed(mLiveUiMsgHandler.obtainMessage(300, null), 500L);
            mLiveUiMsgHandler.post(RunnableRemoveWeakSignalDialogue);
            break;

        case 24580: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "STATUS_FAIL_SIGNAL_ERROR");
            CheckIsReservationPartialOrNone(4);
            runOnUiThread(new RunnableShowSignalAlertDialog(0));
            break;

        case 24593: 
            MtvUtilDebug.Error("MtvUiLivePlayer", "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
            boolean flag;
            if(SystemProperties.getInt("ro.debuggable", 0) == 1)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
                toast.setGravity(119, 0, 0);
                toast.show();
                mLiveUiMsgHandler.postDelayed(runnableAbnormalFinishTV, 3000L);
            } else
            {
                mLiveUiMsgHandler.sendEmptyMessage(306);
            }
            break;
        }
_L1:
        if(true) goto _L12; else goto _L11
_L11:
_L10:
        MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification ...:CMD_DELETE");
        if(j == 24581)
            mLiveUiMsgHandler.post(new Runnable() {

                public void run()
                {
                    finish();
                }

                final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
            });
          goto _L12
_L4:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:CMD_STATUS_UPDT Status-").append(j).toString());
        switch(j)
        {
        case 24588: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "STATUS_FAIL_SIGNAL_ERROR");
            CheckIsReservationPartialOrNone(4);
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(325, 24588, -1));
            if(k == mMtvPreferences.getLatestPChannel())
                runOnUiThread(new RunnableShowSignalAlertDialog(0));
            break;

        case 24589: 
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(310, getCaptionText()));
            break;
        }
        if(true) goto _L12; else goto _L13
_L13:
_L7:
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onPlayerNotification ...:CMD_RECORD Status-").append(j).toString());
        switch(j)
        {
        case 24577: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(fragHandler != null)
                    {
                        fragHandler.addFrag(15, -1L, false, new int[0]);
                        mLiveUiMsgHandler.post(RunnableUpdateProgramChannelInfo);
                        mLiveUiMsgHandler.post(RunnableUpdateSignalInfo);
                        if(MtvUiLivePlayer.mBmlSurface != null && MtvUiLivePlayer.mBmlSurface.IsBMLFullView())
                            setBMLLayoutFullView(false);
                        fragHandler.addFrag(1, -1L, false, new int[0]);
                        if(fragHandler.isFragPresent(0))
                            fragHandler.removeFrag(0);
                    }
                }

                final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
            });
            showNotification();
            break;

        case 24581: 
            saveTVFileThumbnail();
            checkIsReservationAndEnd(1);
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(318));
            showNotification();
            break;

        case 24580: 
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("Got CMD_REC with STAT_FAILURE and extra = [").append(k).append("]").toString());
            char c = '\u0145';
            if(k == 24595)
                c = '\u0144';
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(c, k, -1));
            showNotification();
            break;
        }
        if(true) goto _L12; else goto _L14
_L14:
_L8:
        switch(j)
        {
        case 24580: 
            Toast.makeText(this, 0x7f0700d1, 0).show();
            break;

        case 24581: 
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPlayerNotification: Capture received!!!");
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(317));
            break;
        }
        if(true) goto _L12; else goto _L15
_L15:
_L6:
        switch(j)
        {
        case 24581: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
                    mMtvAppPlaybackContext.getState().registerListener(MtvUiLivePlayer.this);
                    mMtvAppPlaybackContext.getAttribute().registerListener(MtvUiLivePlayer.this);
                    checkIsReservationAndStart();
                }

                final MtvUiLivePlayer this$0;

            
            {
                this$0 = MtvUiLivePlayer.this;
                super();
            }
            });
            break;
        }
        if(true) goto _L12; else goto _L16
_L16:
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean flag = true;
        if(MtvAreaManager.getCount(getApplicationContext()) != 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        boolean flag1;
        menu.clear();
        if(MtvUtilAppService.isAbnormalExit())
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "onPrepareOptionsMenu : AbnormalExit Happening... cannot repare options menu");
            continue; /* Loop/switch isn't completed */
        }
        if(getFragmentManager().findFragmentByTag("ProgInfoFrag") != null || fragHandler != null && fragHandler.isFragPresent(6) || fragHandler.isFragPresent(2))
            continue; /* Loop/switch isn't completed */
        if(mMtvAppPlaybackContext != null && com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING == mMtvAppPlaybackContext.getState().getState() && 20480 == mMtvAppPlaybackContext.getState().getOp())
            flag1 = flag;
        else
            flag1 = false;
        optionsMenu = menu;
        if(fragHandler != null && !fragHandler.isPhoneLocked())
        {
            if(fragHandler.isFragPresent(12))
                fragHandler.removeFrag(12);
            if(!fragHandler.isFragPresent(flag))
                break; /* Loop/switch isn't completed */
            menu.add(0, 16, 0, 0x7f0700d2).setIcon(0x7f020106);
            SubMenu submenu1 = menu.addSubMenu(0, 8, 0, 0x7f07008e).setIcon(0x7f020105);
            submenu1.add(0, 19, 0, 0x7f07008f);
            submenu1.add(0, 18, 0, 0x7f070090);
            submenu1.add(0, 20, 0, 0x7f070091).setEnabled(false);
            submenu1.add(0, 22, 0, 0x7f070092);
            submenu1.add(0, 23, 0, 0x7f070093);
        }
_L4:
        flag = super.onPrepareOptionsMenu(menu);
        if(true) goto _L1; else goto _L3
_L3:
        menu.add(0, 0, 0, 0x7f070088).setIcon(0x7f02010b).setEnabled(flag);
        menu.add(0, 2, 0, 0x7f07008a).setIcon(0x7f02010c).setEnabled(flag1);
        menu.add(0, flag, 0, 0x7f070089).setIcon(0x7f02010a).setEnabled(flag1);
        menu.add(0, 3, 0, 0x7f07008b).setIcon(0x7f020102);
        menu.add(0, 4, 0, 0x7f070094).setIcon(0x7f020109);
        SubMenu submenu = menu.addSubMenu(0, 8, 0, 0x7f07008e).setIcon(0x7f020105);
        submenu.add(0, 19, 0, 0x7f07008f);
        submenu.add(0, 18, 0, 0x7f070090);
        submenu.add(0, 20, 0, 0x7f070091);
        submenu.add(0, 22, 0, 0x7f070092);
        submenu.add(0, 23, 0, 0x7f070093);
        if(!mMtvAudMgr.isHeadsetConnected())
            if(!mMtvAudMgr.isBTConnected())
                menu.add(0, 11, 0, 0x7f070099).setIcon(0x7f020108);
            else
                menu.add(0, 12, 0, 0x7f070098).setIcon(0x7f020108);
        if(MtvUtilAppService.getCurrentOrientation(getApplicationContext()) == 0)
        {
            menu.add(0, 9, 0, 0x7f070096).setIcon(0x7f0200fb).setEnabled(flag1);
            menu.add(0, 10, 0, 0x7f070097).setIcon(0x7f020107).setEnabled(flag1);
        }
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onProgramAttributeUpdated(int i)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onProgramAttributeUpdated: ").append(i).toString());
        i;
        JVM INSTR lookupswitch 3: default 60
    //                   2: 72
    //                   3: 61
    //                   16: 109;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L3:
        runOnUiThread(RunnableUpdateProgramChannelInfo);
        continue; /* Loop/switch isn't completed */
_L2:
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(320));
        runOnUiThread(runnableShowNotification);
        runOnUiThread(RunnableUpdateProgramChannelInfo);
        continue; /* Loop/switch isn't completed */
_L4:
        runOnUiThread(RunnableUpdateSignalInfo);
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onResume()
    {
        super.onResume();
        fragHandler.setEnabled(true);
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        if(!MtvUtilAppService.isAbnormalExit()) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Mid("MtvUiLivePlayer", "looks like something already went wrong in middleware... just finishing liveplayer without doing anything...");
        fragHandler.removeFrag(0);
        fragHandler.removeFrag(15);
        mLiveUiMsgHandler.removeMessages(300);
        mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(300, null));
_L4:
        return;
_L2:
        if(MtvUtilAppService.isAppExiting())
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "isExiting...");
            fragHandler.removeFrag(0);
            Intent intent;
            int i;
            android.view.WindowManager.LayoutParams layoutparams;
            KeyguardManager keyguardmanager;
            boolean flag;
            MtvReservation mtvreservation;
            boolean flag1;
            if(mMtvAppPlaybackContext != null)
            {
                if(mMtvAppPlaybackContext.getState().getOp() == 20487)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            if(flag1)
                stopRecording();
            mLiveUiMsgHandler.sendMessage(mLiveUiMsgHandler.obtainMessage(306, Boolean.valueOf(true)));
            continue; /* Loop/switch isn't completed */
        }
        if(mMtvPreferences.getLaunchAntennaAlert())
        {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiPopUpActivity"));
            intent.putExtra("popup_type", 3);
            intent.setFlags(0x10000000);
            MtvUtilDebug.High("MtvUiLivePlayer", "Mobile Tv Lanuch antenna");
            startActivity(intent);
            mMtvPreferences.setLaunchAntennaAlert(false);
        }
        i = MtvAreaManager.getCount(getApplicationContext());
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onResume...areaCount:").append(i).toString());
        if(i > 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", "updateFragmentsOnStateChange called");
            updateFragmentsOnStateChange();
            updateFragmentsOnLock();
        }
        if(fragHandler.isFragPresent(4))
            fragHandler.removeFrag(4);
        if(isFinishing() || restrictLaunch)
            continue; /* Loop/switch isn't completed */
        layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mMtvPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        if(i == 0)
            setImageViewDuringNoChannel(true);
        else
            setImageViewDuringNoChannel(false);
        initMtvPlayer();
        updateLiveViews();
        if(mPendingPlayerNotification != null)
            mLiveUiMsgHandler.sendMessage(mPendingPlayerNotification);
        if(mMtvAppPlaybackContext != null && mBmlSurface != null && mMtvPlayerOneSeg != null)
            if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
                mBmlSurface.onResume(mMtvAppPlaybackContext);
            else
                mBmlSurface.onCreate(mMtvAppPlaybackContext, fragHandler);
        keyguardmanager = (KeyguardManager)getApplicationContext().getSystemService("keyguard");
        flag = keyguardmanager.isKeyguardLocked();
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("mKeyguardManager ").append(flag).toString());
        if(mMtvAudMgr != null && !checkIsCalling(false) && i > 0)
        {
            MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onResume: reservation getReservationDuringRemainder()").append(mMtvPreferences.getComingReservationID()).toString());
            if(mMtvPreferences.getComingReservationID() == -1)
                break; /* Loop/switch isn't completed */
            mtvreservation = MtvReservationManager.find(this, mMtvPreferences.getComingReservationID());
            if(mtvreservation != null && mtvreservation.mPgmType == 0)
            {
                MtvUtilDebug.Low("MtvUiLivePlayer", "onResume: reservation recording muting nexplaye audio");
                mMtvAudMgr.setAudioMute(true);
            } else
            {
                mMtvAudMgr.stopOtherSound();
            }
        }
        checkIsReservationAndStart();
        showStatusNotification = false;
        MtvUtilDebug.Low("MtvUiLivePlayer", (new StringBuilder()).append("onResume: isScreenOn: ").append(MtvUtilAppService.isScreenOn(getApplicationContext())).append("isKeyguardLocked :").append(keyguardmanager.isKeyguardLocked()).toString());
        if(!MtvUtilAppService.isScreenOn(getApplicationContext()))
            mLiveVideoSurfaceView.setKeepScreenOn(true);
        MtvUtilAppService.releaseCPUPartialWakeLock();
        if(!flag)
            requestSystemKeyEvent(26, true);
        if(mMtvAudMgr != null)
            mMtvAudMgr.setAvStreaming(true);
        if(mAudioManager == null)
            mAudioManager = (AudioManager)getSystemService("audio");
        registerMediaButtonReceiver();
        if(true) goto _L4; else goto _L3
_L3:
        if(flag || fragHandler.isFragPresent(5))
            break MISSING_BLOCK_LABEL_651;
        MtvUtilDebug.Low("MtvUiLivePlayer", "onResume: not in reservation so resume the audio");
        mMtvAudMgr.stopOtherSound();
        break MISSING_BLOCK_LABEL_651;
        if(true) goto _L4; else goto _L5
_L5:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        MtvUtilDebug.Low("MtvUiLivePlayer", "onSaveInstanceState");
        if(!isFinishing())
        {
            fragHandler.setEnabled(false);
            fragHandler.putFragHandlerData(bundle);
            bundle.putInt("lockVisibility", lockImage.getVisibility());
        }
        mySavedInstanceState = bundle;
        super.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        if(mBmlSurface != null)
            mBmlSurface.onStart(fragHandler);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    protected void onStop()
    {
        if(MtvUtilAppService.isScreenOn(getApplicationContext()) && !isFinishing())
            if(showStatusNotification && HomeKeyPresses(getApplicationContext()))
                showNotification(true);
            else
                MtvUtilDebug.Mid("MtvUiLivePlayer", "showStatusNotification is false");
        super.onStop();
        if(mBmlSurface != null)
            mBmlSurface.onStop();
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
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

    protected void onUserLeaveHint()
    {
        MtvUtilDebug.Mid("MtvUiLivePlayer", "onUserLeaveHint");
        showStatusNotification = true;
        super.onUserLeaveHint();
    }

    public void onWindowFocusChanged(boolean flag)
    {
        if(flag)
        {
            requestSystemKeyEvent(26, true);
            if(fragHandler != null)
                fragHandler.onUpdate(108, null, 0);
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
        view.setVisibility(8);
        mBmlSurface.setBMLFullView(true);
        setRequestedOrientation(1);
        registerVideoSurfaceView(false);
        mCaptionView.setVisibility(8);
_L2:
        return;
_L4:
        view.setVisibility(0);
        mBmlSurface.setBMLFullView(false);
        setRequestedOrientation(4);
        registerVideoSurfaceView(true);
        if(mMtvPreferences.isCaptionEnabled())
        {
            mCaptionView.setVisibility(0);
            if(getCaptionText() != null)
                mCaptionView.setText(getCaptionText());
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void showExitApplicationDialog()
    {
        Bundle bundle = new Bundle();
        if(fragHandler != null && !fragHandler.isFragPresent(1))
        {
            bundle.putInt("dialogType", 9);
            mExitAppDialog = MtvUiDialogsFrag.newInstance(bundle);
            mExitAppDialog.show(getFragmentManager(), "exit_confirmation");
        } else
        {
            bundle.putInt("dialogType", 10);
            mExitAppDialog = MtvUiDialogsFrag.newInstance(bundle);
            mExitAppDialog.show(getFragmentManager(), "save_exit_confirmation");
        }
    }

    public void showExitingAppDialog()
    {
        mExitProgressDialog = new ProgressDialog(this);
        mExitProgressDialog.setTitle(0x7f07009f);
        mExitProgressDialog.setMessage(getString(0x7f0700a0));
        mExitProgressDialog.setIndeterminate(true);
        mExitProgressDialog.setCancelable(false);
        mExitProgressDialog.show();
    }

    public void showSignalAlertDialog(int i)
    {
        if(getFragmentManager().findFragmentByTag("signal_alert_retry_exit") == null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("dialogType", 11);
            mNoSignalAlertDialog = MtvUiDialogsFrag.newInstance(bundle);
            mNoSignalAlertDialog.show(getFragmentManager(), "signal_alert_retry_exit");
        }
_L1:
        return;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        MtvUtilDebug.Mid("MtvUiLivePlayer", "Cannot Show SignalAlertDialog after onSaveInstance");
        illegalstateexception.printStackTrace();
          goto _L1
    }

    private static MtvUiBmlSurfaceView mBmlSurface = null;
    private static Toast mExitToast = null;
    private static NotificationManager mNotificationManager = null;
    private static Message mPendingPlayerNotification = null;
    private static boolean showStatusNotification = false;
    private Runnable RunnableCheckIsReservationAndStart;
    private Runnable RunnableNextPreviousChannel;
    private Runnable RunnableRemoveWeakSignalDialogue;
    private Runnable RunnableReservationEndPopupExpire;
    private Runnable RunnableUpdateProgramChannelInfo;
    private Runnable RunnableUpdateSignalInfo;
    private Runnable RunnableresetComingReservationID;
    private int epgChNo;
    private int epgServiceId;
    private MtvUiFragHandler fragHandler;
    private Toast gen_toast;
    private ImageView lockImage;
    private ImageView mAnimationImage;
    private RelativeLayout mAnimationlayout;
    private AudioManager mAudioManager;
    private TextView mCaptionView;
    private GestureDetector mChannelGestureDetector;
    private ServiceConnection mConnection;
    private ControlAnimationRunnable mControlAnimationRunnable;
    private android.os.PowerManager.WakeLock mCpuWakeLock;
    private MtvUtilDebug mDebugSetting;
    private DialogFragment mExitAppDialog;
    private ProgressDialog mExitProgressDialog;
    private SurfaceView mHiddenSurfaceView;
    private ImageView mImgViewNoChannel;
    private Handler mLiveUiMsgHandler;
    private SurfaceView mLiveVideoSurfaceView;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvUtilAudioManager mMtvAudMgr;
    private IMtvAppPlayerOneSeg mMtvPlayerOneSeg;
    private MtvPreferences mMtvPreferences;
    private MtvUiLiveServiceListener mMtvUiLiveServiceListener;
    private DialogFragment mNoSignalAlertDialog;
    private long mPreviousChannelChangeTime;
    private DialogFragment mReservationEndDialog;
    private Runnable mRunableScreenON;
    private Runnable mRunableScreenOff;
    private Runnable mRunnableUpdateRecordComponents;
    private MtvAppAndroidService mService;
    private TextView mTxtAnimation;
    private Bundle mySavedInstanceState;
    private Menu optionsMenu;
    int orientation;
    private Runnable postHeadsetUpdate;
    private boolean restrictLaunch;
    private Runnable runnableAbnormalFinishTV;
    private Runnable runnableShowNotification;
    private Runnable runnableVolumeDecreasing;
    private Runnable runnableVolumeEscalating;
    private boolean shownToastforOperationNA;






/*
    static ProgressDialog access$1002(MtvUiLivePlayer mtvuiliveplayer, ProgressDialog progressdialog)
    {
        mtvuiliveplayer.mExitProgressDialog = progressdialog;
        return progressdialog;
    }

*/


/*
    static ControlAnimationRunnable access$102(MtvUiLivePlayer mtvuiliveplayer, ControlAnimationRunnable controlanimationrunnable)
    {
        mtvuiliveplayer.mControlAnimationRunnable = controlanimationrunnable;
        return controlanimationrunnable;
    }

*/



/*
    static MtvAppPlaybackContext access$1102(MtvUiLivePlayer mtvuiliveplayer, MtvAppPlaybackContext mtvappplaybackcontext)
    {
        mtvuiliveplayer.mMtvAppPlaybackContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/















/*
    static MtvPreferences access$2402(MtvUiLivePlayer mtvuiliveplayer, MtvPreferences mtvpreferences)
    {
        mtvuiliveplayer.mMtvPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/




/*
    static Menu access$2602(MtvUiLivePlayer mtvuiliveplayer, Menu menu)
    {
        mtvuiliveplayer.optionsMenu = menu;
        return menu;
    }

*/








/*
    static Message access$3302(Message message)
    {
        mPendingPlayerNotification = message;
        return message;
    }

*/











/*
    static MtvAppAndroidService access$4202(MtvUiLivePlayer mtvuiliveplayer, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuiliveplayer.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/



/*
    static MtvUiLiveServiceListener access$4302(MtvUiLivePlayer mtvuiliveplayer, MtvUiLiveServiceListener mtvuiliveservicelistener)
    {
        mtvuiliveplayer.mMtvUiLiveServiceListener = mtvuiliveservicelistener;
        return mtvuiliveservicelistener;
    }

*/



















}
