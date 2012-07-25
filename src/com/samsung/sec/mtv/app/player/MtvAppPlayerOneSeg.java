// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.player;

import android.broadcast.IMtvOneSegServiceListener;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegChannel;
import android.broadcast.helper.types.MtvOneSegTVLink;
import android.broadcast.oneseg.MtvOneSegFactory;
import android.broadcast.oneseg.MtvOneSegService;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.*;
import android.text.SpannableStringBuilder;
import com.samsung.sec.mtv.app.context.*;

// Referenced classes of package com.samsung.sec.mtv.app.player:
//            IMtvAppPlayerOneSeg

public class MtvAppPlayerOneSeg
    implements IMtvOneSegServiceListener, IMtvAppPlayerOneSeg
{
    private class MtvAppPlayerPendingParams
    {

        private Context mAppContext;
        private int mCmdExtraInfo;
        private int mCmdStatus;
        private MtvAppPlaybackContext mNewContext;
        private MtvURI mNewURI;
        private int mPendingCmd;
        private boolean mcmdServiced;
        final MtvAppPlayerOneSeg this$0;




/*
        static MtvURI access$1002(MtvAppPlayerPendingParams mtvappplayerpendingparams, MtvURI mtvuri)
        {
            mtvappplayerpendingparams.mNewURI = mtvuri;
            return mtvuri;
        }

*/


/*
        static Context access$102(MtvAppPlayerPendingParams mtvappplayerpendingparams, Context context)
        {
            mtvappplayerpendingparams.mAppContext = context;
            return context;
        }

*/



/*
        static int access$2002(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            mtvappplayerpendingparams.mPendingCmd = i;
            return i;
        }

*/



/*
        static boolean access$2302(MtvAppPlayerPendingParams mtvappplayerpendingparams, boolean flag)
        {
            mtvappplayerpendingparams.mcmdServiced = flag;
            return flag;
        }

*/



/*
        static int access$2402(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            mtvappplayerpendingparams.mCmdStatus = i;
            return i;
        }

*/



/*
        static int access$2502(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            mtvappplayerpendingparams.mCmdExtraInfo = i;
            return i;
        }

*/



/*
        static MtvAppPlaybackContext access$402(MtvAppPlayerPendingParams mtvappplayerpendingparams, MtvAppPlaybackContext mtvappplaybackcontext)
        {
            mtvappplayerpendingparams.mNewContext = mtvappplaybackcontext;
            return mtvappplaybackcontext;
        }

*/

        private MtvAppPlayerPendingParams()
        {
            this$0 = MtvAppPlayerOneSeg.this;
            super();
            mcmdServiced = false;
            mPendingCmd = 0;
            mCmdStatus = 0;
            mCmdExtraInfo = 0;
            mAppContext = null;
            mNewURI = null;
            mNewContext = null;
        }

    }

    private class MtvAppPlayerSeekParams
    {

        private int mCurTimeStamp;
        private boolean mSeekFail;
        private int mSeekStatus;
        private int mSeekedTimeStamp;
        private int mTrickMode;
        final MtvAppPlayerOneSeg this$0;



/*
        static int access$2602(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            mtvappplayerseekparams.mTrickMode = i;
            return i;
        }

*/



/*
        static int access$2702(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            mtvappplayerseekparams.mCurTimeStamp = i;
            return i;
        }

*/



/*
        static boolean access$2902(MtvAppPlayerSeekParams mtvappplayerseekparams, boolean flag)
        {
            mtvappplayerseekparams.mSeekFail = flag;
            return flag;
        }

*/



/*
        static int access$3002(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            mtvappplayerseekparams.mSeekStatus = i;
            return i;
        }

*/



/*
        static int access$3102(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            mtvappplayerseekparams.mSeekedTimeStamp = i;
            return i;
        }

*/

        private MtvAppPlayerSeekParams()
        {
            this$0 = MtvAppPlayerOneSeg.this;
            super();
            mCurTimeStamp = 0;
            mTrickMode = 0;
            mSeekFail = false;
            mSeekStatus = 0;
            mSeekedTimeStamp = 0;
        }

    }


    private MtvAppPlayerOneSeg()
    {
        mPlayerHdlr = null;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Constructor Called");
        HandlerThread handlerthread = new HandlerThread("MtvAppPlayerOneSeg");
        handlerthread.start();
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Starting the thread");
        mPendingParam = new MtvAppPlayerPendingParams();
        mPlayerHdlr = new Handler(handlerthread.getLooper(), mPlayerHdlrCB);
        MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully Instantiated Constrcutor");
    }

    private void flushPlayerThreadMsg()
    {
        if(mPlayerHdlr != null)
        {
            for(int i = 20480; i < 20496; i++)
            {
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Flushing the message [").append(i).append("] from PlayerOneSeg ThreadQ ").toString());
                mPlayerHdlr.removeMessages(i);
            }

        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Thread Handler. No point of flushing the messages from PlayerOneSeg ThreadQ!");
        }
    }

    public static IMtvAppPlayerOneSeg getInstance()
    {
        if(mPlayer == null)
        {
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "PlayerOneSeg Instance is null, Instantiating the constructor");
            mPlayer = new MtvAppPlayerOneSeg();
        }
        return mPlayer;
    }

    private boolean getPendingOpStatus()
    {
        return mOpStatus;
    }

    private void handleBuffering(int i, int j, int k)
    {
        if(mCurContext == null)
            break MISSING_BLOCK_LABEL_106;
        MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Buffering Status [").append(j).append("]").toString());
        j;
        JVM INSTR tableswitch 204 206: default 64
    //                   204 77
    //                   205 84
    //                   206 91;
           goto _L1 _L2 _L3 _L4
_L1:
        mCurContext.getState().triggerNotification(i, j, k);
_L5:
        return;
_L2:
        j = 24582;
          goto _L1
_L3:
        j = 24583;
          goto _L1
_L4:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Got Buffering End from OneSegService ");
        j = 24584;
          goto _L1
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "App is still running - Dnt Crash Yet?!?!?!");
          goto _L5
    }

    private boolean handleClose(boolean flag)
    {
        boolean flag1 = true;
        if(mCurContext == null || mPendingParam == null) goto _L2; else goto _L1
_L1:
        if(flag != flag1) goto _L4; else goto _L3
_L3:
        handleLowSignal(false);
        handleSignalUpdate(false);
        handleRetryChnlSearch(false);
        if(mOneSegServ == null) goto _L4; else goto _L5
_L5:
        boolean flag2 = mOneSegServ.close();
_L8:
        if(!flag2)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully closed the playback session ");
            mPendingParam.mcmdServiced = false;
            mCurContext.getAttribute().reset();
            mCurContext.getComponents().getCaption().reset();
            setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED, 20494, 24581, 0, flag1);
            handlePendingCommand(mCurContext.getState().getOp());
        } else
        {
            flag1 = flag2;
        }
_L6:
        return flag1;
_L2:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters mCurContext [").append(mCurContext).append(" ] mPendingParam [").append(mPendingParam).append("] Cannot close the session!").toString());
        flag1 = false;
        if(true) goto _L6; else goto _L4
_L4:
        flag2 = false;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private boolean handleLocalPlayback(int i, int j, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, int k)
    {
        int l = mCurContext.getState().getOp();
        l;
        JVM INSTR tableswitch 20489 20492: default 44
    //                   20489 78
    //                   20490 78
    //                   20491 195
    //                   20492 195;
           goto _L1 _L2 _L2 _L3 _L3
_L1:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Command [").append(l).append("]. Failure in handling commands in local playback").toString());
_L5:
        return false;
_L2:
        if(j == 201)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Success in [").append(l).append("] session").toString());
            mCurContext.getState().setOp(20480);
            setContextState(state, l, 24581, k, true);
        } else
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Failure in [").append(l).append("] session").toString());
            setContextParams(20480, l, 24580, k, true);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        handleSeek(l, j, mSeekParam.mTrickMode, mSeekParam.mCurTimeStamp, i, k);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void handleLowSignal(boolean flag)
    {
        if(mPlayerHdlr != null)
        {
            if(flag)
            {
                mPlayerHdlr.removeCallbacks(mRunnableUpdateSignal);
                mPlayerHdlr.postDelayed(mRunnableUpdateSignal, 10000L);
                mPlayerHdlr.postDelayed(mRunnableLowSignal, 10000L);
            } else
            {
                mPlayerHdlr.removeCallbacks(mRunnableLowSignal);
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Player Handler -- Error in handleSignalUpdate()");
        }
    }

    private boolean handleNewCommand(int i, int j, int k, MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri, Context context)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        if(mCurContext == null) goto _L2; else goto _L1
_L1:
        if(mCurContext.getState().getOp() != 20480) goto _L4; else goto _L3
_L3:
        if(mPendingParam.mNewURI != null)
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session [URI: ").append(mPendingParam.mNewURI).append("] [chnl Num: ").append(mPendingParam.mNewURI.chnlNum()).append("] [fileIndex: ").append(mPendingParam.mNewURI.fileIndex()).append("] is going on, Got a request for session change:: Posting CLOSE_CMD to end the current session").toString());
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Setting the pending operation as cmd [").append(i).append("] ").toString());
        flag = setPendingOperation(false, i, 0, 0, mtvappplaybackcontext, context, mtvuri);
        if(!flag) goto _L6; else goto _L5
_L5:
        if(mCurContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED) goto _L8; else goto _L7
_L7:
        handlePendingCommand(20480);
_L2:
        return flag;
_L8:
        flag = sendPlayerThreadMsg(20494, 0, 0, null);
        if(!flag)
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Reverting the pending parameters");
            setPendingOperation(false, 20480, 0, 0, mCurContext, null, null);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Reverting the pending parameters");
        setPendingOperation(false, 20480, 0, 0, mCurContext, null, null);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mCurContext.getState().getOp() == 20482 && mRetryCnt > 0)
        {
            flag = setPendingOperation(true, i, 0, 0, mtvappplaybackcontext, context, mtvuri);
            if(flag)
            {
                flag = sendPlayerThreadMsg(17, 0, 0, null);
                if(!flag)
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "CHNL_RETRY going on, Failed in stopping that -- No way the other commands get serviced!!!");
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Reverting the pending parameters");
                    setPendingOperation(false, 20480, 0, 0, mCurContext, null, null);
                }
            }
        } else
        {
            if(mPendingParam.mNewURI != null)
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current Session is in the middle. CurCmd[").append(mCurContext.getState().getOp()).append("] URI [").append(mPendingParam.mNewURI).append("] ChnlNum [").append(mPendingParam.mNewURI.chnlNum()).append("] [fileIndex: ").append(mPendingParam.mNewURI.fileIndex()).append("]").toString());
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Cannot issue CLOSE immediately.. Setting Pending CMD [").append(i).append("] with appropriate parameters").toString());
            if(i != 20486)
                flag1 = true;
            flag = setPendingOperation(flag1, i, 0, 0, mtvappplaybackcontext, context, mtvuri);
        }
        if(true) goto _L2; else goto _L9
_L9:
    }

    private boolean handlePendingCommand(int i)
    {
        boolean flag = false;
        if(mCurContext != null)
        {
            int j = mCurContext.getState().getOp();
            if(mPendingParam != null && mPendingParam.mPendingCmd != 0)
            {
                if(j == i)
                {
                    if(!mPendingParam.mcmdServiced)
                    {
                        MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Pending CMD [").append(mPendingParam.mPendingCmd).append("] will be serviced now").toString());
                        setPendingOpStatus(true);
                        if(mPendingParam.mNewContext != null && mPendingParam.mNewContext != mCurContext)
                        {
                            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Context Differs -- Setting the NewContext and henceforth all the CBs will be triggered to the new context ");
                            mCurContext = mPendingParam.mNewContext;
                            mCurContext.getState().setState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED);
                            MtvAppPlaybackContextManager.getInstance().setCurrentContext(mCurContext);
                        }
                        sendPlayerThreadMsg(mPendingParam.mPendingCmd, mPendingParam.mCmdStatus, mPendingParam.mCmdExtraInfo, null);
                        mPendingParam.mCmdExtraInfo = 0;
                        mPendingParam.mCmdStatus = 0;
                        mPendingParam.mPendingCmd = 0;
                    } else
                    {
                        MtvUtilDebug.High("MtvAppPlayerOneSeg", "Closing the current on-going session");
                        if(mOneSegServ != null)
                        {
                            mCurContext.getState().setOp(20494);
                            handleClose(true);
                        }
                    }
                } else
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("The CB/NewCmd [").append(i).append("] is different from the current operation [").append(j).append("] going on -- Doing Nothing").toString());
                }
                flag = true;
            } else
            if(mPendingParam == null)
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "PendingCmd Queue is NULL --- Get Ready to take ADB Dump!!!");
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "mContext is NULL - Take ForceDump Please!!!");
        }
        return flag;
    }

    private void handleRetryChnlSearch(boolean flag)
    {
        if(mPlayerHdlr != null)
        {
            if(flag)
            {
                mRetryCnt = 1 + mRetryCnt;
                if(mRetryCnt == 5)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Retry Cnt [").append(mRetryCnt).append("].. Max times searched, Got Fedup - Leave it Yaar - Why do you want to play TV when there is no Siganl!!!").toString());
                    mPlayerHdlr.removeCallbacks(mRetryChnlSearch);
                    mRetryCnt = 0;
                    setContextParams(20480, 20482, 24580, mPendingParam.mNewURI.chnlNum(), true);
                } else
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Retry Cnt [").append(mRetryCnt).append("].. Retrying for Channel:: Sleeping for 5sec").toString());
                    mPlayerHdlr.removeCallbacks(mRetryChnlSearch);
                    mPlayerHdlr.postDelayed(mRetryChnlSearch, 5000L);
                }
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Resetting RetryChnlSearch() ");
                mPlayerHdlr.removeCallbacks(mRetryChnlSearch);
                mRetryCnt = 0;
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Player Handler -- Error in handleRetryChnlSearch()");
        }
    }

    private boolean handleSeek(int i, int j, int k, int l, int i1, int j1)
    {
        char c = '\u6004';
        if(mCurContext == null) goto _L2; else goto _L1
_L1:
        i1;
        JVM INSTR lookupswitch 5: default 64
    //                   0: 165
    //                   1: 231
    //                   16: 463
    //                   256: 570
    //                   4096: 552;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid seek status [").append(i1).append("]. Failure in Seek(), Don't know which event to send!!! ").toString());
_L10:
        boolean flag1 = false;
_L11:
        boolean flag;
        if(!flag1)
        {
            mSeekParam = null;
            if(i == 20491)
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in trickModePlay() ");
            else
            if(i == 20492)
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in repositionPlay() ");
            setContextParams(20480, i, c, 0, true);
        }
        if(mCurContext.getState().getOp() == 20480)
        {
            handlePendingCommand(20480);
            flag = flag1;
        } else
        {
            flag = flag1;
        }
_L15:
        return flag;
_L4:
        if(mSeekParam == null)
        {
            mSeekParam = new MtvAppPlayerSeekParams();
            if(mSeekParam != null)
            {
                mSeekParam.mCurTimeStamp = l;
                mSeekParam.mTrickMode = k;
                mSeekParam.mSeekFail = false;
            }
        }
        if(mOneSegServ == null) goto _L10; else goto _L9
_L9:
        flag1 = mOneSegServ.pause();
          goto _L11
_L5:
        if(j != 201) goto _L10; else goto _L12
_L12:
        MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - Pause Done");
        mCurContext.getState().setState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED);
        if(mSeekParam == null || mOneSegServ == null) goto _L10; else goto _L13
_L13:
        if(i == 20491)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("SEEK - Calling Trickmode with Mode [").append(mSeekParam.mTrickMode).append("] newTimeStamp [").append(mSeekParam.mCurTimeStamp).append("]").toString());
            if(mSeekParam.mTrickMode == 0)
                mSeekParam.mSeekStatus = 4096;
            flag1 = mOneSegServ.trickmodePlay(mSeekParam.mTrickMode, mSeekParam.mCurTimeStamp);
        } else
        if(i == 20492)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("SEEK - Calling Reposition with newTimeStamp [").append(mSeekParam.mCurTimeStamp).append("]").toString());
            flag1 = mOneSegServ.repositionPlay(mSeekParam.mCurTimeStamp);
        } else
        {
            flag1 = false;
        }
        if(!flag1)
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Resuming the play as there is failure in seek() operation");
            mSeekParam.mSeekFail = true;
            if(mOneSegServ != null)
                flag1 = mOneSegServ.resume();
        }
          goto _L11
_L6:
        if(j == 201)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - SEEK Done");
            if(mSeekParam != null)
                mSeekParam.mSeekedTimeStamp = j1;
        } else
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Failure in [").append(i).append("] session. resuming the playback").toString());
            mSeekParam.mSeekFail = true;
        }
        if(mOneSegServ == null) goto _L10; else goto _L14
_L14:
        flag1 = mOneSegServ.resume();
          goto _L11
_L8:
        MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - got onTrickModeEnd() Callback -- Post the Event now");
        mSeekParam.mSeekStatus = 4096;
_L7:
        if(mSeekParam.mSeekStatus != 4096)
        {
            if(j == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - Resume Done");
                flag1 = true;
            } else
            {
                mSeekParam.mSeekFail = true;
                mSeekParam.mSeekedTimeStamp = 0;
                flag1 = false;
            }
        } else
        {
            flag1 = true;
        }
        if(i == 20492 || i == 20491 && mSeekParam.mSeekStatus == 4096)
        {
            if(flag1 && mSeekParam != null)
            {
                if(!mSeekParam.mSeekFail)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Success in [").append(i).append("] for the playback ").toString());
                    c = '\u6005';
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Failure in [").append(i).append("] for the playback ").toString());
                    c = '\u6004';
                }
                mCurContext.getState().setOp(20480);
                setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, i, c, mSeekParam.mSeekedTimeStamp, true);
                mSeekParam = null;
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current Command = [").append(i).append("] SeekStatus = [").append(mSeekParam.mSeekStatus).append("]. May be still under processing !!").toString());
        }
          goto _L11
_L2:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Context. Cannot handle trickModePlay()/repositionPlay() ");
        flag = false;
          goto _L15
    }

    private void handleSignalUpdate(boolean flag)
    {
        if(mPlayerHdlr != null)
        {
            if(flag)
            {
                mPlayerHdlr.postDelayed(mRunnableUpdateSignal, 4000L);
            } else
            {
                mLowSigCnt = 0;
                mPlayerHdlr.removeCallbacks(mRunnableUpdateSignal);
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Player Handler -- Error in handleSignalUpdate()");
        }
    }

    private void resetApp()
    {
        if(mOneSegServ != null)
        {
            mOneSegServ.unregisterListener(this);
            mOneSegServ = null;
        }
        handleRetryChnlSearch(false);
        handleSignalUpdate(false);
        handleLowSignal(false);
        setPendingOpStatus(false);
        mCurContext.getComponents().getAudio().disable();
        mCurContext.getComponents().getVideo().disable();
        mCurContext = null;
        mPlayer = null;
        mPlayerHdlr = null;
        mPendingParam = null;
        MtvUtilDebug.High("MtvAppPlayerOneSeg", "Reset all the data");
    }

    private boolean sendPlayerThreadMsg(int i, int j, int k, Object obj)
    {
        boolean flag = false;
        if(mPlayerHdlr != null)
        {
            if(j == 215 || i == 20487)
                flag = mPlayerHdlr.sendMessageAtFrontOfQueue(mPlayerHdlr.obtainMessage(i, j, k, obj));
            else
            if(i == 20495)
            {
                handleRetryChnlSearch(false);
                flushPlayerThreadMsg();
                flag = mPlayerHdlr.sendMessage(mPlayerHdlr.obtainMessage(i, j, k, obj));
            } else
            {
                flag = mPlayerHdlr.sendMessage(mPlayerHdlr.obtainMessage(i, j, k, obj));
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Thread Handler. Failure in posting messages to thread");
        }
        return flag;
    }

    private void setContextParams(int i, int j, int k, int l, boolean flag)
    {
        if(mCurContext != null)
        {
            mCurContext.getState().setOp(i);
            mCurContext.getState().triggerNotification(j, k, l, flag);
        }
    }

    private void setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, int i, int j, int k, boolean flag)
    {
        if(mCurContext != null)
        {
            mCurContext.getState().setState(state);
            mCurContext.getState().triggerNotification(i, j, k, flag);
        }
    }

    private void setPendingOpStatus(boolean flag)
    {
        mOpStatus = flag;
    }

    private boolean setPendingOperation(boolean flag, int i, int j, int k, MtvAppPlaybackContext mtvappplaybackcontext, Context context, MtvURI mtvuri)
    {
        boolean flag1;
        flag1 = false;
        if(mPendingParam == null)
            break MISSING_BLOCK_LABEL_94;
        MtvAppPlayerPendingParams mtvappplayerpendingparams = mPendingParam;
        mtvappplayerpendingparams;
        JVM INSTR monitorenter ;
        mPendingParam.mcmdServiced = flag;
        mPendingParam.mPendingCmd = i;
        mPendingParam.mCmdStatus = j;
        mPendingParam.mCmdExtraInfo = k;
        mPendingParam.mNewContext = mtvappplaybackcontext;
        mPendingParam.mAppContext = context;
        mPendingParam.mNewURI = mtvuri;
        flag1 = true;
        break MISSING_BLOCK_LABEL_102;
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Handler:: Cannot Store parameters for next session -- Cannot continue operation");
        return flag1;
    }

    public boolean cancelRecord(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered cancelRecord()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20487)
                flag = sendPlayerThreadMsg(20487, 2, 0, null);
            else
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Recording is not going on curState [").append(state).append("] curCmd [").append(i).append("] -- Cannot Cancel Recording").toString());
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append("] newContext [").append(mtvappplaybackcontext).append("]. Cannot Cancel Recording ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting cancelRecord()");
        return flag;
    }

    public boolean cancelScanChannels(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered cancelScanChannels()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && i == 20485)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("], curCmd [").append(i).append("] -- Checking whether the new command [CANCEL SCAN] can be serviced or not").toString());
                flag = handleNewCommand(20486, 0, 0, mtvappplaybackcontext, null, null);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] curCommand [").append(i).append("]. Failure in cancelScanChannels() ").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append(" newContext [").append(mtvappplaybackcontext).append("]. Failure in cancelScanChannels()").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting cancelScanChannels()");
        return flag;
    }

    public boolean captFrame(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered captFrame()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20480)
            {
                flag = sendPlayerThreadMsg(20488, 0, 0, null);
                if(flag)
                    mtvappplaybackcontext.getState().triggerNotification(20488, 24577, 0);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State curState [").append(state).append("] curCmd [").append(i).append("] -- Cannot Capture Frame").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append("] newContext [").append(mtvappplaybackcontext).append("]. Cannot Capture Frame ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting captFrame()");
        return flag;
    }

    public boolean create(MtvAppPlaybackContext mtvappplaybackcontext, Context context)
    {
        boolean flag = false;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered create()");
        if(mCurContext == null)
        {
            if(mtvappplaybackcontext != null)
            {
                if(mtvappplaybackcontext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
                {
                    if(mPendingParam != null)
                    {
                        setPendingOperation(false, 0, 0, 0, mtvappplaybackcontext, context, null);
                        if(mtvappplaybackcontext.getComponents() != null)
                        {
                            if(mtvappplaybackcontext.getComponents().getAudio() != null)
                            {
                                mtvappplaybackcontext.getComponents().getAudio().setControlInterface(MtvOneSegFactory.getAudioControl());
                                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Enabling Audio Comp");
                                mtvappplaybackcontext.getComponents().getAudio().enable();
                            }
                            if(mtvappplaybackcontext.getComponents().getVideo() != null)
                            {
                                mtvappplaybackcontext.getComponents().getVideo().setControlInterface(MtvOneSegFactory.getVideoControl());
                                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Enabling Video Comp");
                                mtvappplaybackcontext.getComponents().getVideo().enable();
                            }
                            if(mtvappplaybackcontext.getComponents().getBml() != null)
                                mtvappplaybackcontext.getComponents().getBml().setControlInterface(MtvOneSegFactory.getBMLViewControl());
                        }
                        flag = sendPlayerThreadMsg(20481, 0, 0, null);
                        if(flag)
                            mtvappplaybackcontext.getState().triggerNotification(20481, 24577, 0);
                    } else
                    {
                        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Cannot Set Params required to start CREATE Operation.. Failure in Create() ");
                    }
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(mtvappplaybackcontext.getState().getState()).append("]. Cannot create playback session").toString());
                }
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Context. Cannot create playback session");
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "******Valid Context Before Create!!!. Cannot create playback session******");
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting create()");
        return flag;
    }

    public void delete(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered delete()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            if(mtvappplaybackcontext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
            {
                flag = sendPlayerThreadMsg(20495, 0, 0, null);
                if(flag)
                    mtvappplaybackcontext.getState().triggerNotification(20495, 24577, 0);
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append(" newContext [").append(mtvappplaybackcontext).append("]. Cannot delete playback session").toString());
        }
        if(!flag)
        {
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Invalid State in Delete():: Trying to exit gracefully after that its your fate !!");
            setContextParams(20480, 20495, 24581, 0, true);
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting delete()");
    }

    public boolean deleteTVFile(MtvAppPlaybackContext mtvappplaybackcontext, int i)
    {
        boolean flag = false;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered deleteTVFile()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
            flag = sendPlayerThreadMsg(16, i, 0, null);
        else
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Input Parameters pbContext [").append(mtvappplaybackcontext).append("] mCurrentContext [").append(mCurContext).append("]").toString());
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting deleteTVFile()");
        return flag;
    }

    public void onServiceNotify(int i, int j, int k, Object obj)
    {
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Callback from backend, command [").append(i).append("], status [").append(j).append("]").toString());
        sendPlayerThreadMsg(i, j, k, obj);
    }

    public boolean open(MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered open()");
        if(mtvappplaybackcontext != null && mtvuri != null && mCurContext != null)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(mtvappplaybackcontext.getComponents().getBml() != null)
                mtvappplaybackcontext.getComponents().getBml().setControlInterface(MtvOneSegFactory.getBMLViewControl());
            if(mCurContext == mtvappplaybackcontext)
            {
                if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && i == 20480)
                {
                    if(mPendingParam != null)
                    {
                        setPendingOperation(false, 0, 0, 0, mCurContext, mPendingParam.mAppContext, mtvuri);
                        flag = sendPlayerThreadMsg(20482, 0, 0, null);
                        if(flag)
                            mtvappplaybackcontext.getState().triggerNotification(20482, 24577, 0);
                    } else
                    {
                        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Cannot Set params required to start OPEN Operation -- Failure in open() ");
                    }
                } else
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("] curCmd [").append(i).append("] -- Checking whether the new command [OPEN] [newChnl: ").append(mtvuri.chnlNum()).append("]  [new fileIndex: ").append(mtvuri.fileIndex()).append("] can be serviced or not").toString());
                    flag = handleNewCommand(20482, 0, 0, mtvappplaybackcontext, mtvuri, mPendingParam.mAppContext);
                }
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("] curCmd [").append(i).append("] -- Checking whether the new command [OPEN] [newChnl: ").append(mtvuri.chnlNum()).append("]  [new fileIndex: ").append(mtvuri.fileIndex()).append("] can be serviced or not").toString());
                flag = handleNewCommand(20482, 0, 0, mtvappplaybackcontext, mtvuri, null);
            }
        } else
        if(mtvappplaybackcontext == null)
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Context - null. Cannot start playback session");
        else
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid openURI - null. Cannot start playback session");
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting open()");
        return flag;
    }

    public boolean open(MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri, Context context)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered open()");
        if(mtvappplaybackcontext != null && mtvuri != null && context != null && mCurContext != null)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(mCurContext == mtvappplaybackcontext)
            {
                if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && i == 20480)
                {
                    if(mPendingParam != null)
                    {
                        setPendingOperation(false, 0, 0, 0, mCurContext, context, mtvuri);
                        flag = sendPlayerThreadMsg(20482, 0, 0, null);
                        if(flag)
                            mtvappplaybackcontext.getState().triggerNotification(20482, 24577, 0);
                    } else
                    {
                        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Cannot Set params required to start OPEN Operation -- Failure in open() ");
                    }
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("]. Cannot open playback session").toString());
                }
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("] curCmd [").append(i).append("] -- Checking whether the new command [OPEN] [newChnl: ").append(mtvuri.chnlNum()).append("]  [new fileIndex: ").append(mtvuri.fileIndex()).append("] can be serviced or not").toString());
                flag = handleNewCommand(20482, 0, 0, mtvappplaybackcontext, mtvuri, context);
            }
        } else
        if(mtvappplaybackcontext == null)
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Context - null. Cannot start playback session");
        else
        if(mtvuri == null)
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid openURI - null. Cannot start playback session");
        else
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid bmlCB - null. Cannot start playback session");
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting open()");
        return flag;
    }

    public boolean pause(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered pause()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20480)
            {
                flag = sendPlayerThreadMsg(20489, 0, 0, null);
                if(flag)
                    mCurContext.getState().triggerNotification(20489, 24577, 0);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] curCommand [").append(i).append("]. Failure in pause()").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("]. Failure in pause() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting pause()");
        return flag;
    }

    public boolean reposition(MtvAppPlaybackContext mtvappplaybackcontext, int i)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Entered reposition() rePositionTimeStamp [").append(i).append("]").toString());
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int j = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && j == 20480)
            {
                flag = sendPlayerThreadMsg(20492, i, 0, null);
                if(flag)
                    mCurContext.getState().triggerNotification(20492, 24577, 0);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] currentCommand [").append(j).append("]. Failure in reposition() ").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("]. Failure in reposition() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting reposition()");
        return flag;
    }

    public boolean resume(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered resume()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED && i == 20480)
            {
                flag = sendPlayerThreadMsg(20490, 0, 0, null);
                if(flag)
                    mCurContext.getState().triggerNotification(20490, 24577, 0);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] curCommand [").append(i).append("]. Failure in resume()").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("]. Failure in pause() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting resume()");
        return flag;
    }

    public boolean scanChannels(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered scanChannels()");
        if(mtvappplaybackcontext != null && mCurContext != mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && i == 20480)
            {
                flag = handleNewCommand(20485, 0, 0, mtvappplaybackcontext, null, null);
                if(flag)
                    mtvappplaybackcontext.getState().triggerNotification(20485, 24577, 0);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("], curCmd [").append(i).append("] -- Checking whether the new command [SCAN] can be serviced or not").toString());
                handleNewCommand(20485, 0, 0, mtvappplaybackcontext, null, null);
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("No Change in Context {curContext = [").append(mCurContext).append("] newContext = ").append(mtvappplaybackcontext).append("] }. Cannot issue SCAN, Failure in scanChannels()").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting scanChannels()");
        return flag;
    }

    public boolean startRecord(MtvAppPlaybackContext mtvappplaybackcontext, String s, int i)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered startRecord()");
        if(mtvappplaybackcontext != null && s != null)
        {
            if(mCurContext == mtvappplaybackcontext)
            {
                com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
                int j = mCurContext.getState().getOp();
                if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && j == 20480)
                {
                    flag = sendPlayerThreadMsg(20487, 1, i, s);
                    if(flag)
                        mtvappplaybackcontext.getState().triggerNotification(20487, 24577, 0);
                } else
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session is going on curState [").append(state).append("] curCmd [").append(j).append("] -- Cannot Start Recording").toString());
                }
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append("] newContext [").append(mtvappplaybackcontext).append("]. Cannot Start Recording ").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Input parameters pbContext [").append(mtvappplaybackcontext).append("] recURI [").append(s).append("] availMemSize [").append(i).append("]. Cannot Start Recording").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting startRecord()");
        return flag;
    }

    public boolean startTVLink(MtvAppPlaybackContext mtvappplaybackcontext, MtvOneSegTVLink mtvonesegtvlink, Context context)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered startTVLink()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext && mtvonesegtvlink != null)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(mtvappplaybackcontext.getComponents().getBml() != null)
                mtvappplaybackcontext.getComponents().getBml().setControlInterface(MtvOneSegFactory.getBMLViewControl());
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20480)
            {
                flag = sendPlayerThreadMsg(20493, 1, 0, mtvonesegtvlink);
                if(flag)
                    mCurContext.getState().triggerNotification(20493, 24577, 0);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] currentCommand [").append(i).append("]. Failure in startTVLink() ").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("] tvLinkURI = [").append(mtvonesegtvlink).append("]. Failure in startTVLink() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting startTVLink()");
        return flag;
    }

    public boolean stopRecord(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered stopRecord()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20487)
                flag = sendPlayerThreadMsg(20487, 3, 0, null);
            else
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Recording is not going on curState [").append(state).append("] curCmd [").append(i).append("] -- Cannot Stop Recording").toString());
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Mismatch in context curContext [").append(mCurContext).append("] newContext [").append(mtvappplaybackcontext).append("]. Cannot Stop Recording ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting stopRecord()");
        return flag;
    }

    public boolean stopTVLink(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered stopTVLink()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int i = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && i == 20493)
                flag = sendPlayerThreadMsg(20493, 2, 0, null);
            else
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] currentCommand [").append(i).append("]. Failure in stopTVLink() ").toString());
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("]. Failure in stopTVLink() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting stopTVLink()");
        return flag;
    }

    public boolean trickmode(MtvAppPlaybackContext mtvappplaybackcontext, int i, int j)
    {
        boolean flag = false;
        com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Entered trickmode()");
        if(mtvappplaybackcontext != null && mCurContext == mtvappplaybackcontext)
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = mCurContext.getState().getState();
            int k = mCurContext.getState().getOp();
            if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING && k == 20480)
            {
                flag = sendPlayerThreadMsg(20491, i, j, null);
                if(flag)
                    mCurContext.getState().triggerNotification(20491, 24577, 0);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid State [").append(state).append("] currentCommand [").append(k).append("]. Failure in trickmode() ").toString());
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters pbContext = [").append(mtvappplaybackcontext).append("] curContext = [").append(mCurContext).append("]. Failure in trickmode() ").toString());
        }
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Exiting trickmode()");
        return flag;
    }

    private static MtvAppPlaybackContext mCurContext = null;
    private static int mLowSigCnt = 0;
    private static MtvOneSegService mOneSegServ = null;
    private static boolean mOpStatus = false;
    private static MtvAppPlayerPendingParams mPendingParam = null;
    private static MtvAppPlayerOneSeg mPlayer = null;
    private static int mRetryCnt = 0;
    private static MtvAppPlayerSeekParams mSeekParam = null;
    private Handler mPlayerHdlr;
    private final android.os.Handler.Callback mPlayerHdlrCB = new android.os.Handler.Callback() {

        public boolean handleMessage(Message message)
        {
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Command[").append(message.what).append("] in thread").toString());
            if(MtvAppPlayerOneSeg.mCurContext == null)
                if(MtvAppPlayerOneSeg.mPendingParam != null)
                {
                    MtvAppPlayerOneSeg.mCurContext = MtvAppPlayerOneSeg.mPendingParam.mNewContext;
                    MtvAppPlaybackContextManager.getInstance().setCurrentContext(MtvAppPlayerOneSeg.mCurContext);
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Problem in receiving message, cannot set context:: Cannont continue operation");
                }
            if(MtvAppPlayerOneSeg.mCurContext == null) goto _L2; else goto _L1
_L1:
            message.what;
            JVM INSTR lookupswitch 32: default 340
        //                       16: 1894
        //                       17: 1866
        //                       101: 1914
        //                       102: 2042
        //                       103: 2652
        //                       105: 2762
        //                       106: 2815
        //                       107: 3009
        //                       108: 3220
        //                       109: 3718
        //                       110: 3738
        //                       111: 3796
        //                       112: 3898
        //                       113: 3258
        //                       114: 2377
        //                       115: 3955
        //                       116: 4021
        //                       117: 4089
        //                       118: 4101
        //                       20481: 384
        //                       20482: 525
        //                       20485: 1071
        //                       20486: 1181
        //                       20487: 877
        //                       20488: 817
        //                       20489: 1284
        //                       20490: 1347
        //                       20491: 1410
        //                       20492: 1456
        //                       20493: 1524
        //                       20494: 1669
        //                       20495: 1701;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L3:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Unknown Command [").append(message.what).append("] Ignoring...").toString());
_L56:
            return true;
_L23:
            boolean flag10 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Create Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20481);
            if(getPendingOpStatus())
            {
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20481, 24577, 0);
                setPendingOpStatus(false);
            }
            MtvAppPlayerOneSeg.mOneSegServ = MtvOneSegFactory.getService();
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
            {
                flag10 = MtvAppPlayerOneSeg.mOneSegServ.registerListener(MtvAppPlayerOneSeg.this);
                if(flag10 && MtvAppPlayerOneSeg.mPendingParam != null)
                    flag10 = MtvAppPlayerOneSeg.mOneSegServ.create(MtvAppPlayerOneSeg.mPendingParam.mAppContext);
            }
            if(!flag10)
            {
                setContextParams(20480, 20481, 24580, 0, true);
                resetApp();
            }
            continue; /* Loop/switch isn't completed */
_L24:
            boolean flag9 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Open Operation Started ChnlNum [").append(MtvAppPlayerOneSeg.mPendingParam.mNewURI.chnlNum()).append("] fileIndex [").append(MtvAppPlayerOneSeg.mPendingParam.mNewURI.fileIndex()).append("]").toString());
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20482);
            if(getPendingOpStatus())
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Posting OPEN_START to UiPlayer ");
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20482, 24577, 0);
                setPendingOpStatus(false);
            }
            if(MtvAppPlayerOneSeg.mOneSegServ != null && MtvAppPlayerOneSeg.mPendingParam != null)
            {
                if(MtvAppPlayerOneSeg.mPendingParam.mNewURI.pbType() == 2 || MtvAppPlayerOneSeg.mPendingParam.mNewURI.pbType() == 4)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "As it is live/testmode, starting SignalQty Runnable ");
                    handleSignalUpdate(true);
                }
                if(MtvAppPlayerOneSeg.mCurContext.getComponents() != null)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Enabling Captions ");
                    if(MtvAppPlayerOneSeg.mCurContext.getComponents().getCaption() != null)
                    {
                        MtvAppPlayerOneSeg.mCurContext.getComponents().getCaption().setControlInterface(MtvOneSegFactory.getCaptionControl());
                        MtvAppPlayerOneSeg.mCurContext.getComponents().getCaption().enable();
                    }
                }
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Calling OneSegService.Open() ");
                flag9 = MtvAppPlayerOneSeg.mOneSegServ.open(MtvAppPlayerOneSeg.mPendingParam.mNewURI);
            } else
            if(MtvAppPlayerOneSeg.mPendingParam == null)
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "OpenURI is not set -- Cannot start playback session");
            else
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Handler: OneSegServiceHdl - null. Cannot start playback session");
            if(!flag9)
                setContextParams(20480, 20482, 24580, 0, true);
            continue; /* Loop/switch isn't completed */
_L28:
            boolean flag8 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "CAPTURE Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20488);
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                flag8 = MtvAppPlayerOneSeg.mOneSegServ.captureFrame();
            if(!flag8)
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20488, 24580, 0);
            continue; /* Loop/switch isn't completed */
_L27:
            boolean flag7;
            flag7 = false;
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20487);
            if(MtvAppPlayerOneSeg.mOneSegServ == null) goto _L37; else goto _L36
_L36:
            message.arg1;
            JVM INSTR tableswitch 1 3: default 928
        //                       1 996
        //                       2 1025
        //                       3 1043;
               goto _L38 _L39 _L40 _L41
_L38:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("] with REC Command. Cannot service it").toString());
_L42:
            if(!flag7)
            {
                MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20487, 24580, 0);
            }
            continue; /* Loop/switch isn't completed */
_L39:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "startRecord() Operation Started");
            flag7 = MtvAppPlayerOneSeg.mOneSegServ.startRecord((String)message.obj, message.arg2);
            continue; /* Loop/switch isn't completed */
_L40:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "cancelRecord() Operation Started");
            flag7 = MtvAppPlayerOneSeg.mOneSegServ.cancelRecord();
            continue; /* Loop/switch isn't completed */
_L41:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "stopRecord() Operation Started");
            flag7 = MtvAppPlayerOneSeg.mOneSegServ.stopRecord();
            continue; /* Loop/switch isn't completed */
_L37:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Service Handle. Cannot Continue Recording");
            if(true) goto _L42; else goto _L25
_L25:
            boolean flag6 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "SCAN Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED);
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20485);
            if(getPendingOpStatus())
            {
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20485, 24577, 0);
                setPendingOpStatus(false);
            }
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                flag6 = MtvAppPlayerOneSeg.mOneSegServ.scanChannels();
            if(!flag6)
                setContextParams(20480, 20485, 24580, 0, true);
            continue; /* Loop/switch isn't completed */
_L26:
            boolean flag5 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("CANCEL SCAN Operation Started LastSetScanned Chnl [").append(MtvAppPlayerOneSeg.mCurContext.getAttribute().getLastScannedChannel()).append("]").toString());
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20486);
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                flag5 = MtvAppPlayerOneSeg.mOneSegServ.cancelScanChannels();
            if(!flag5)
            {
                MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20486, 24580, 0);
            }
            continue; /* Loop/switch isn't completed */
_L29:
            boolean flag4 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Pause Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20489);
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                flag4 = MtvAppPlayerOneSeg.mOneSegServ.pause();
            if(!flag4)
                setContextParams(20480, 20489, 24580, 0, true);
            continue; /* Loop/switch isn't completed */
_L30:
            boolean flag3 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Resume Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20490);
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                flag3 = MtvAppPlayerOneSeg.mOneSegServ.resume();
            if(!flag3)
                setContextParams(20480, 20490, 24580, 0, true);
            continue; /* Loop/switch isn't completed */
_L31:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "TrcikMode Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20491);
            handleSeek(message.what, 0, message.arg1, message.arg2, 0, 0);
            continue; /* Loop/switch isn't completed */
_L32:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Reposition Operation Started repositionTimeStamp [").append(message.arg1).append("]").toString());
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20492);
            handleSeek(message.what, 0, 0, message.arg1, 0, 0);
            continue; /* Loop/switch isn't completed */
_L33:
            boolean flag2 = false;
            if(MtvAppPlayerOneSeg.mOneSegServ == null) goto _L44; else goto _L43
_L43:
            message.arg1;
            JVM INSTR tableswitch 1 2: default 1560
        //                       1 1620
        //                       2 1658;
               goto _L45 _L46 _L47
_L45:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid command [").append(message.arg1).append("] in TVLink Operation. Failure in TVLInk Operation()").toString());
_L44:
            if(!flag2)
                setContextParams(20480, 20493, 24580, 0, true);
            continue; /* Loop/switch isn't completed */
_L46:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "TVLink Operation Started ");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20493);
            flag2 = MtvAppPlayerOneSeg.mOneSegServ.startTVLink((MtvOneSegTVLink)message.obj);
            continue; /* Loop/switch isn't completed */
_L47:
            flag2 = MtvAppPlayerOneSeg.mOneSegServ.stopTVLink();
            if(true) goto _L44; else goto _L34
_L34:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Close Operation Started");
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20494);
            handleClose(true);
            continue; /* Loop/switch isn't completed */
_L35:
            boolean flag1;
            flag1 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Delete Operation Started");
            if(MtvAppPlayerOneSeg.mCurContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && MtvAppPlayerOneSeg.mCurContext.getState().getOp() != 20485) goto _L49; else goto _L48
_L48:
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
            {
                setPendingOperation(false, 20495, 0, 0, MtvAppPlayerOneSeg.mCurContext, null, null);
                flag1 = handleClose(true);
            }
_L50:
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20495);
            if(!flag1)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully deleted the playback session ");
                setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, 20495, 24581, 0, true);
                resetApp();
            }
            continue; /* Loop/switch isn't completed */
_L49:
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
            {
                setPendingOperation(false, 20495, 0, 0, MtvAppPlayerOneSeg.mCurContext, null, null);
                flag1 = MtvAppPlayerOneSeg.mOneSegServ.delete();
            }
            if(true) goto _L50; else goto _L5
_L5:
            handleRetryChnlSearch(false);
            handlePendingCommand(MtvAppPlayerOneSeg.mCurContext.getState().getOp());
            continue; /* Loop/switch isn't completed */
_L4:
            if(MtvAppPlayerOneSeg.mOneSegServ != null)
                MtvAppPlayerOneSeg.mOneSegServ.deleteTVFile(message.arg1);
            continue; /* Loop/switch isn't completed */
_L6:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully created the playback session");
                MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
                setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED, 20481, 24581, 0, true);
                if(MtvAppPlayerOneSeg.mOneSegServ != null)
                {
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setSignalStatistics(MtvAppPlayerOneSeg.mOneSegServ.getSignalStatistics());
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setSignalLevel(MtvAppPlayerOneSeg.mOneSegServ.getSignalQuality());
                    mRunnableUpdateSignal.run();
                }
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Failure in creating the playback session ");
                setContextParams(20480, 20481, 24580, 0, true);
            }
            continue; /* Loop/switch isn't completed */
_L7:
            boolean flag = handlePendingCommand(20482);
            if(!flag)
                switch(message.arg1)
                {
                case 202: 
                default:
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("With OPEN command from backend.. Verify the commands once again!").toString());
                    break;

                case 201: 
                    handleRetryChnlSearch(false);
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully opened the playback session ");
                    setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED, 20482, 24581, 0, true);
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setChannel((MtvOneSegChannel)message.obj);
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Play Operation Started");
                    setContextParams(20484, 20484, 24577, 0, true);
                    int l = MtvAppPlayerOneSeg.mPendingParam.mNewURI.pbType();
                    if(l != 5 && l != 3)
                    {
                        if(MtvAppPlayerOneSeg.mOneSegServ != null)
                            flag = MtvAppPlayerOneSeg.mOneSegServ.start((MtvOneSegChannel)message.obj);
                        if(!flag)
                        {
                            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in starting player.. Govinda Govinda !!");
                            setContextParams(20480, 20484, 24580, 0, true);
                        }
                    }
                    break;

                case 204: 
                case 205: 
                case 206: 
                    handleBuffering(20482, message.arg1, message.arg2);
                    break;

                case 203: 
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Failure in opening the playback session ");
                    if(MtvAppPlayerOneSeg.mPendingParam.mNewURI.pbType() == 2 || MtvAppPlayerOneSeg.mPendingParam.mNewURI.pbType() == 4)
                        handleRetryChnlSearch(true);
                    else
                        setContextParams(20480, 20482, 24580, 0, true);
                    break;
                }
            continue; /* Loop/switch isn't completed */
_L18:
            if(message.arg1 == 215)
                setContextParams(20480, 20484, 24593, 0, true);
            else
            if(!handlePendingCommand(20484))
            {
                switch(message.arg1)
                {
                case 202: 
                case 207: 
                case 208: 
                case 209: 
                case 210: 
                case 211: 
                case 212: 
                case 213: 
                default:
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("With PLAY command from backend.. Verify the commands once again!").toString());
                    break;

                case 201: 
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully started the playback session ");
                    MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
                    setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, 20484, 24581, 0, true);
                    break;

                case 204: 
                case 205: 
                case 206: 
                    handleBuffering(20484, message.arg1, message.arg2);
                    break;

                case 214: 
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "End of file reached");
                    setContextParams(20480, 20484, 24592, 0, true);
                    break;

                case 203: 
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in starting the playback session ");
                    setContextParams(20480, 20484, 24580, 0, true);
                    break;
                }
                if(false)
                    ;
            }
            continue; /* Loop/switch isn't completed */
_L8:
            char c4;
            int k;
            c4 = '\u6004';
            k = 0;
            message.arg1;
            JVM INSTR lookupswitch 3: default 2700
        //                       201: 2719
        //                       216: 2746
        //                       217: 2754;
               goto _L51 _L52 _L53 _L54
_L51:
            MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20487, c4, k);
            continue; /* Loop/switch isn't completed */
_L52:
            MtvAppPlayerOneSeg.mCurContext.getComponents().getVideo().setCaptFrameName((String)message.obj);
            c4 = '\u6002';
            continue; /* Loop/switch isn't completed */
_L53:
            k = 24594;
            continue; /* Loop/switch isn't completed */
_L54:
            k = 24595;
            if(true) goto _L51; else goto _L9
_L9:
            char c3 = '\u6004';
            if(message.arg1 == 201)
                c3 = '\u6005';
            MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
            MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20487, c3, message.arg2);
            continue; /* Loop/switch isn't completed */
_L10:
            int j = MtvAppPlayerOneSeg.mCurContext.getState().getOp();
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Got the captured frame");
            if(j == 20488)
            {
                MtvAppPlayerOneSeg.mCurContext.getState().setOp(20480);
                if(message.arg1 == 201)
                {
                    MtvAppPlayerOneSeg.mCurContext.getComponents().getVideo().setCaptFrame((Bitmap)message.obj);
                    MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(j, 24581, 0);
                } else
                {
                    MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(j, 24580, 0);
                }
            } else
            if(j == 20487)
            {
                if(message.arg1 == 201)
                {
                    MtvAppPlayerOneSeg.mCurContext.getComponents().getVideo().setCaptFrame((Bitmap)message.obj);
                    MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(j, 24596, 0);
                }
            } else
            {
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid curCmd [").append(j).append("]. CaptureFrame CB Cannot be handled!").toString());
            }
            continue; /* Loop/switch isn't completed */
_L11:
            if(!handlePendingCommand(20485) && MtvAppPlayerOneSeg.mCurContext.getState().getOp() == 20485)
            {
                switch(message.arg1)
                {
                default:
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("With SCAN command from backend.. Verify the commands once again!").toString());
                    break;

                case 202: 
                    char c2 = '\u6016';
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setLastScannedChannel(message.arg2);
                    if(message.obj != null)
                    {
                        MtvAppPlayerOneSeg.mCurContext.getAttribute().setChannel((MtvOneSegChannel)message.obj);
                        c2 = '\u6015';
                    }
                    MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20485, c2, message.arg2);
                    break;

                case 201: 
                case 203: 
                    char c1 = '\u6004';
                    if(message.arg1 == 201)
                        c1 = '\u6005';
                    setContextParams(20480, 20485, c1, 0, true);
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().reset();
                    break;
                }
                if(false)
                    ;
            }
            continue; /* Loop/switch isn't completed */
_L12:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CANCEL_SCAN from Service");
            setContextParams(20480, 20486, 24581, 0, true);
            MtvAppPlayerOneSeg.mCurContext.getAttribute().reset();
            continue; /* Loop/switch isn't completed */
_L17:
            switch(message.arg1)
            {
            case 214: 
            case 215: 
            case 216: 
            case 217: 
            default:
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("] with STATUS_UPDT from Service. Dont know, what to do!?").toString());
                break;

            case 208: 
                if(MtvAppPlayerOneSeg.mCurContext.getState().getOp() != 20494)
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setProgram((android.broadcast.helper.types.MtvOneSegProgram[])(android.broadcast.helper.types.MtvOneSegProgram[])message.obj);
                break;

            case 209: 
                if(MtvAppPlayerOneSeg.mCurContext.getState().getOp() != 20494)
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setTot(((Long)message.obj).longValue());
                break;

            case 210: 
                if(MtvAppPlayerOneSeg.mPendingParam.mPendingCmd == 0)
                {
                    if(message.arg2 == 2)
                        handleLowSignal(true);
                    else
                        handleLowSignal(false);
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("As there are pending commands [").append(MtvAppPlayerOneSeg.mPendingParam.mPendingCmd).append("] to be serviced, ignoring LOW_SIGNAL from Player").toString());
                }
                break;

            case 211: 
                int i = MtvAppPlayerOneSeg.mCurContext.getState().getOp();
                if(i != 20494 && i != 20491 && i != 20492)
                {
                    MtvAppPlayerOneSeg.mCurContext.getComponents().getCaption().setBuffer((SpannableStringBuilder)message.obj);
                    MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24589, 0);
                }
                break;

            case 212: 
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Time Update:: New time is [").append(message.arg2).append("]").toString());
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24590, message.arg2);
                break;

            case 213: 
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24591, 0);
                break;

            case 218: 
                char c = '\u6004';
                if(message.arg2 == 201)
                    c = '\u6003';
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24599, c);
                break;

            case 207: 
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24585, 0);
                break;
            }
            continue; /* Loop/switch isn't completed */
_L13:
            handleLocalPlayback(1, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED, 0);
            continue; /* Loop/switch isn't completed */
_L14:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Resume SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
            handleLocalPlayback(256, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, message.arg2);
            continue; /* Loop/switch isn't completed */
_L15:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Trickmode SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
                handleLocalPlayback(16, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            } else
            if(message.arg1 == 202)
                handleLocalPlayback(4096, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            continue; /* Loop/switch isn't completed */
_L16:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Reposition SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
            handleLocalPlayback(16, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            continue; /* Loop/switch isn't completed */
_L19:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CMD_TVLINK_START SUCCESS from Service:");
                MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20493, 24578, 0);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CMD_TVLINK_START FAILURE!!! from Service:");
                setContextParams(20480, 20493, 24580, 0, true);
            }
            continue; /* Loop/switch isn't completed */
_L20:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "CMD_TVLINK_STOP:TVLink Operation Completed ");
                setContextParams(20480, 20493, 24581, 0, true);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "CMD_TVLINK_STOP FAILURE!!!! ");
                setContextParams(20480, 20493, 24580, 0, true);
            }
            continue; /* Loop/switch isn't completed */
_L21:
            handleClose(false);
            continue; /* Loop/switch isn't completed */
_L22:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully deleted the playback session ");
            setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, 20495, 24581, 0, true);
            resetApp();
            continue; /* Loop/switch isn't completed */
_L2:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "******Invalid Context. Cannot Handle commands in PlayerThread******");
            if(true) goto _L56; else goto _L55
_L55:
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRetryChnlSearch = new Runnable() {

        public void run()
        {
            if(MtvAppPlayerOneSeg.mCurContext != null && MtvAppPlayerOneSeg.mCurContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && MtvAppPlayerOneSeg.mPendingParam != null)
            {
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Posting OPEN from Retry Runnable ");
                sendPlayerThreadMsg(20482, 0, 0, MtvAppPlayerOneSeg.mPendingParam.mNewURI);
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters mCurContext [").append(MtvAppPlayerOneSeg.mCurContext).append(" ] mPendingParam [").append(MtvAppPlayerOneSeg.mPendingParam).append("] Cannot retry channel search").toString());
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRunnableLowSignal = new Runnable() {

        public void run()
        {
            if(mPlayerHdlr != null && MtvAppPlayerOneSeg.mCurContext != null)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Low- Signal:: Go to good signal area else TV will be stopped immediately!!!");
                if(MtvAppPlayerOneSeg.mCurContext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV)
                {
                    if(MtvAppPlayerOneSeg.mPendingParam != null && MtvAppPlayerOneSeg.mPendingParam.mNewURI != null)
                        MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24588, MtvAppPlayerOneSeg.mPendingParam.mNewURI.chnlNum());
                    else
                        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Pending Parameters.. May be nobody interested in receiving the notification !!");
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Live is not going on! How come low-signal now -- Anyways ignore it!!");
                }
                handleLowSignal(false);
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRunnableUpdateSignal = new Runnable() {

        public void run()
        {
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
            if(mPlayerHdlr != null && MtvAppPlayerOneSeg.mCurContext != null)
            {
                com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = MtvAppPlayerOneSeg.mCurContext.getState().getState();
                if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED || state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED || state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setSignalStatistics(MtvAppPlayerOneSeg.mOneSegServ.getSignalStatistics());
                    int i = MtvAppPlayerOneSeg.mOneSegServ.getSignalQuality();
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current SignalQuality [").append(i).append("]").toString());
                    MtvAppPlayerOneSeg.mCurContext.getAttribute().setSignalLevel(i);
                    if(i > 0);
                    if(MtvAppPlayerOneSeg.mLowSigCnt == 3)
                    {
                        MtvUtilDebug.High("MtvAppPlayerOneSeg", "Low- Signal:: Go to good signal area else TV will be stopped immediately!!!");
                        MtvAppPlayerOneSeg.mCurContext.getState().triggerNotification(20483, 24588, 0);
                        handleSignalUpdate(false);
                    } else
                    {
                        handleSignalUpdate(true);
                    }
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid State, not extracting signal quality!");
                }
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };













/*
    static MtvAppPlaybackContext access$202(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        mCurContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/











/*
    static MtvOneSegService access$702(MtvOneSegService mtvonesegservice)
    {
        mOneSegServ = mtvonesegservice;
        return mtvonesegservice;
    }

*/


}
