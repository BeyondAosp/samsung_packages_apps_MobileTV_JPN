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
import android.util.Log;
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
        static Context access$100(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$100(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)Landroid/content/Context;");
            return mtvappplayerpendingparams.mAppContext;
        }

*/


/*
        static MtvURI access$1000(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$1000(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)Landroid/broadcast/helper/MtvURI;");
            return mtvappplayerpendingparams.mNewURI;
        }

*/


/*
        static MtvURI access$1002(MtvAppPlayerPendingParams mtvappplayerpendingparams, MtvURI mtvuri)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$1002(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;Landroid/broadcast/helper/MtvURI;)Landroid/broadcast/helper/MtvURI;");
            mtvappplayerpendingparams.mNewURI = mtvuri;
            return mtvuri;
        }

*/


/*
        static Context access$102(MtvAppPlayerPendingParams mtvappplayerpendingparams, Context context)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$102(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;Landroid/content/Context;)Landroid/content/Context;");
            mtvappplayerpendingparams.mAppContext = context;
            return context;
        }

*/


/*
        static int access$2000(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2000(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)I");
            return mtvappplayerpendingparams.mPendingCmd;
        }

*/


/*
        static int access$2002(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2002(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;I)I");
            mtvappplayerpendingparams.mPendingCmd = i;
            return i;
        }

*/


/*
        static boolean access$2300(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2300(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)Z");
            return mtvappplayerpendingparams.mcmdServiced;
        }

*/


/*
        static boolean access$2302(MtvAppPlayerPendingParams mtvappplayerpendingparams, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2302(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;Z)Z");
            mtvappplayerpendingparams.mcmdServiced = flag;
            return flag;
        }

*/


/*
        static int access$2400(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2400(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)I");
            return mtvappplayerpendingparams.mCmdStatus;
        }

*/


/*
        static int access$2402(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2402(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;I)I");
            mtvappplayerpendingparams.mCmdStatus = i;
            return i;
        }

*/


/*
        static int access$2500(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2500(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)I");
            return mtvappplayerpendingparams.mCmdExtraInfo;
        }

*/


/*
        static int access$2502(MtvAppPlayerPendingParams mtvappplayerpendingparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$2502(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;I)I");
            mtvappplayerpendingparams.mCmdExtraInfo = i;
            return i;
        }

*/


/*
        static MtvAppPlaybackContext access$400(MtvAppPlayerPendingParams mtvappplayerpendingparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$400(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
            return mtvappplayerpendingparams.mNewContext;
        }

*/


/*
        static MtvAppPlaybackContext access$402(MtvAppPlayerPendingParams mtvappplayerpendingparams, MtvAppPlaybackContext mtvappplaybackcontext)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;->access$402(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
            mtvappplayerpendingparams.mNewContext = mtvappplaybackcontext;
            return mtvappplaybackcontext;
        }

*/

        private MtvAppPlayerPendingParams()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
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

        MtvAppPlayerPendingParams(_cls1 _pcls1)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$1;)V");
            this();
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
        static int access$2600(MtvAppPlayerSeekParams mtvappplayerseekparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2600(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;)I");
            return mtvappplayerseekparams.mTrickMode;
        }

*/


/*
        static int access$2602(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2602(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;I)I");
            mtvappplayerseekparams.mTrickMode = i;
            return i;
        }

*/


/*
        static int access$2700(MtvAppPlayerSeekParams mtvappplayerseekparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2700(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;)I");
            return mtvappplayerseekparams.mCurTimeStamp;
        }

*/


/*
        static int access$2702(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2702(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;I)I");
            mtvappplayerseekparams.mCurTimeStamp = i;
            return i;
        }

*/


/*
        static boolean access$2900(MtvAppPlayerSeekParams mtvappplayerseekparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2900(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;)Z");
            return mtvappplayerseekparams.mSeekFail;
        }

*/


/*
        static boolean access$2902(MtvAppPlayerSeekParams mtvappplayerseekparams, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$2902(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;Z)Z");
            mtvappplayerseekparams.mSeekFail = flag;
            return flag;
        }

*/


/*
        static int access$3000(MtvAppPlayerSeekParams mtvappplayerseekparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$3000(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;)I");
            return mtvappplayerseekparams.mSeekStatus;
        }

*/


/*
        static int access$3002(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$3002(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;I)I");
            mtvappplayerseekparams.mSeekStatus = i;
            return i;
        }

*/


/*
        static int access$3100(MtvAppPlayerSeekParams mtvappplayerseekparams)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$3100(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;)I");
            return mtvappplayerseekparams.mSeekedTimeStamp;
        }

*/


/*
        static int access$3102(MtvAppPlayerSeekParams mtvappplayerseekparams, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;->access$3102(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;I)I");
            mtvappplayerseekparams.mSeekedTimeStamp = i;
            return i;
        }

*/

        private MtvAppPlayerSeekParams()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
            this$0 = MtvAppPlayerOneSeg.this;
            super();
            mCurTimeStamp = 0;
            mTrickMode = 0;
            mSeekFail = false;
            mSeekStatus = 0;
            mSeekedTimeStamp = 0;
        }

        MtvAppPlayerSeekParams(_cls1 _pcls1)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerSeekParams;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$1;)V");
            this();
        }
    }


    private MtvAppPlayerOneSeg()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;-><init>()V");
        super();
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->flushPlayerThreadMsg()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->getInstance()Lcom/samsung/sec/mtv/app/player/IMtvAppPlayerOneSeg;");
        if(mPlayer == null)
        {
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "PlayerOneSeg Instance is null, Instantiating the constructor");
            mPlayer = new MtvAppPlayerOneSeg();
        }
        return mPlayer;
    }

    private boolean getPendingOpStatus()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->getPendingOpStatus()Z");
        return mOpStatus;
    }

    private void handleBuffering(int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleBuffering(III)V");
        if(mCurContext == null)
            break MISSING_BLOCK_LABEL_114;
        MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Buffering Status [").append(j).append("]").toString());
        j;
        JVM INSTR tableswitch 204 206: default 72
    //                   204 85
    //                   205 92
    //                   206 99;
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
        boolean flag1;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleClose(Z)Z");
        flag1 = true;
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
            Log.d(mPendingParam, 0);
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
        int l;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleLocalPlayback(IILcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;I)Z");
        l = mCurContext.getState().getOp();
        l;
        JVM INSTR tableswitch 20489 20492: default 52
    //                   20489 86
    //                   20490 86
    //                   20491 203
    //                   20492 203;
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
        handleSeek(l, j, Log.d(mSeekParam), Log.d(mSeekParam), i, k);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void handleLowSignal(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleLowSignal(Z)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleNewCommand(IIILcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/broadcast/helper/MtvURI;Landroid/content/Context;)Z");
        flag = false;
        flag1 = false;
        if(mCurContext == null) goto _L2; else goto _L1
_L1:
        if(mCurContext.getState().getOp() != 20480) goto _L4; else goto _L3
_L3:
        if(Log.d(mPendingParam) != null)
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Already one session [URI: ").append(Log.d(mPendingParam)).append("] [chnl Num: ").append(Log.d(mPendingParam).chnlNum()).append("] [fileIndex: ").append(Log.d(mPendingParam).fileIndex()).append("] is going on, Got a request for session change:: Posting CLOSE_CMD to end the current session").toString());
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
            if(Log.d(mPendingParam) != null)
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current Session is in the middle. CurCmd[").append(mCurContext.getState().getOp()).append("] URI [").append(Log.d(mPendingParam)).append("] ChnlNum [").append(Log.d(mPendingParam).chnlNum()).append("] [fileIndex: ").append(Log.d(mPendingParam).fileIndex()).append("]").toString());
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handlePendingCommand(I)Z");
        boolean flag = false;
        if(mCurContext != null)
        {
            int j = mCurContext.getState().getOp();
            if(mPendingParam != null && Log.d(mPendingParam) != 0)
            {
                if(j == i)
                {
                    if(!Log.d(mPendingParam))
                    {
                        MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Pending CMD [").append(Log.d(mPendingParam)).append("] will be serviced now").toString());
                        setPendingOpStatus(true);
                        if(Log.d(mPendingParam) != null && Log.d(mPendingParam) != mCurContext)
                        {
                            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Context Differs -- Setting the NewContext and henceforth all the CBs will be triggered to the new context ");
                            mCurContext = Log.d(mPendingParam);
                            mCurContext.getState().setState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED);
                            MtvAppPlaybackContextManager.getInstance().setCurrentContext(mCurContext);
                        }
                        sendPlayerThreadMsg(Log.d(mPendingParam), Log.d(mPendingParam), Log.d(mPendingParam), null);
                        Log.d(mPendingParam, 0);
                        Log.d(mPendingParam, 0);
                        Log.d(mPendingParam, 0);
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleRetryChnlSearch(Z)V");
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
                    setContextParams(20480, 20482, 24580, Log.d(mPendingParam).chnlNum(), true);
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
        char c;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleSeek(IIIIII)Z");
        c = '\u6004';
        if(mCurContext == null) goto _L2; else goto _L1
_L1:
        i1;
        JVM INSTR lookupswitch 5: default 72
    //                   0: 173
    //                   1: 239
    //                   16: 473
    //                   256: 580
    //                   4096: 562;
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
                Log.d(mSeekParam, l);
                Log.d(mSeekParam, k);
                Log.d(mSeekParam, 0);
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
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("SEEK - Calling Trickmode with Mode [").append(Log.d(mSeekParam)).append("] newTimeStamp [").append(Log.d(mSeekParam)).append("]").toString());
            if(Log.d(mSeekParam) == 0)
                Log.d(mSeekParam, 4096);
            flag1 = mOneSegServ.trickmodePlay(Log.d(mSeekParam), Log.d(mSeekParam));
        } else
        if(i == 20492)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("SEEK - Calling Reposition with newTimeStamp [").append(Log.d(mSeekParam)).append("]").toString());
            flag1 = mOneSegServ.repositionPlay(Log.d(mSeekParam));
        } else
        {
            flag1 = false;
        }
        if(!flag1)
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Resuming the play as there is failure in seek() operation");
            Log.d(mSeekParam, 1);
            if(mOneSegServ != null)
                flag1 = mOneSegServ.resume();
        }
          goto _L11
_L6:
        if(j == 201)
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - SEEK Done");
            if(mSeekParam != null)
                Log.d(mSeekParam, j1);
        } else
        {
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Failure in [").append(i).append("] session. resuming the playback").toString());
            Log.d(mSeekParam, 1);
        }
        if(mOneSegServ == null) goto _L10; else goto _L14
_L14:
        flag1 = mOneSegServ.resume();
          goto _L11
_L8:
        MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - got onTrickModeEnd() Callback -- Post the Event now");
        Log.d(mSeekParam, 4096);
_L7:
        if(Log.d(mSeekParam) != 4096)
        {
            if(j == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "SEEK - Resume Done");
                flag1 = true;
            } else
            {
                Log.d(mSeekParam, 1);
                Log.d(mSeekParam, 0);
                flag1 = false;
            }
        } else
        {
            flag1 = true;
        }
        if(i == 20492 || i == 20491 && Log.d(mSeekParam) == 4096)
        {
            if(flag1 && mSeekParam != null)
            {
                if(!Log.d(mSeekParam))
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Success in [").append(i).append("] for the playback ").toString());
                    c = '\u6005';
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Failure in [").append(i).append("] for the playback ").toString());
                    c = '\u6004';
                }
                mCurContext.getState().setOp(20480);
                setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, i, c, Log.d(mSeekParam), true);
                mSeekParam = null;
            }
        } else
        {
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current Command = [").append(i).append("] SeekStatus = [").append(Log.d(mSeekParam)).append("]. May be still under processing !!").toString());
        }
          goto _L11
_L2:
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Context. Cannot handle trickModePlay()/repositionPlay() ");
        flag = false;
          goto _L15
    }

    private void handleSignalUpdate(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->handleSignalUpdate(Z)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->resetApp()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->sendPlayerThreadMsg(IIILjava/lang/Object;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->setContextParams(IIIIZ)V");
        if(mCurContext != null)
        {
            mCurContext.getState().setOp(i);
            mCurContext.getState().triggerNotification(j, k, l, flag);
        }
    }

    private void setContextState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, int i, int j, int k, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->setContextState(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;IIIZ)V");
        if(mCurContext != null)
        {
            mCurContext.getState().setState(state);
            mCurContext.getState().triggerNotification(i, j, k, flag);
        }
    }

    private void setPendingOpStatus(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->setPendingOpStatus(Z)V");
        mOpStatus = flag;
    }

    private boolean setPendingOperation(boolean flag, int i, int j, int k, MtvAppPlaybackContext mtvappplaybackcontext, Context context, MtvURI mtvuri)
    {
        boolean flag1;
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->setPendingOperation(ZIIILcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/content/Context;Landroid/broadcast/helper/MtvURI;)Z");
        flag1 = false;
        if(mPendingParam == null)
            break MISSING_BLOCK_LABEL_103;
        MtvAppPlayerPendingParams mtvappplayerpendingparams = mPendingParam;
        mtvappplayerpendingparams;
        JVM INSTR monitorenter ;
        Log.d(mPendingParam, flag);
        Log.d(mPendingParam, i);
        Log.d(mPendingParam, j);
        Log.d(mPendingParam, k);
        Log.d(mPendingParam, mtvappplaybackcontext);
        Log.d(mPendingParam, context);
        Log.d(mPendingParam, mtvuri);
        flag1 = true;
        break MISSING_BLOCK_LABEL_111;
        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Handler:: Cannot Store parameters for next session -- Cannot continue operation");
        return flag1;
    }

    public boolean cancelRecord(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->cancelRecord(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->cancelScanChannels(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->captFrame(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->create(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/content/Context;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->delete(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->deleteTVFile(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;I)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->onServiceNotify(IIILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Callback from backend, command [").append(i).append("], status [").append(j).append("]").toString());
        sendPlayerThreadMsg(i, j, k, obj);
    }

    public boolean open(MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->open(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/broadcast/helper/MtvURI;)Z");
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
                        setPendingOperation(false, 0, 0, 0, mCurContext, Log.d(mPendingParam), mtvuri);
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
                    flag = handleNewCommand(20482, 0, 0, mtvappplaybackcontext, mtvuri, Log.d(mPendingParam));
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->open(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/broadcast/helper/MtvURI;Landroid/content/Context;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->pause(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->reposition(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;I)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->resume(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->scanChannels(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->startRecord(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Ljava/lang/String;I)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->startTVLink(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/broadcast/helper/types/MtvOneSegTVLink;Landroid/content/Context;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->stopRecord(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->stopTVLink(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Z");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->trickmode(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;II)Z");
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
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$1;->handleMessage(Landroid/os/Message;)Z");
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Command[").append(message.what).append("] in thread").toString());
            if(Log.d() == null)
                if(Log.d() != null)
                {
                    Log.d(Log.d(Log.d()));
                    MtvAppPlaybackContextManager.getInstance().setCurrentContext(Log.d());
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Problem in receiving message, cannot set context:: Cannont continue operation");
                }
            if(Log.d() == null) goto _L2; else goto _L1
_L1:
            message.what;
            JVM INSTR lookupswitch 32: default 348
        //                       16: 1906
        //                       17: 1878
        //                       101: 1926
        //                       102: 2054
        //                       103: 2664
        //                       105: 2774
        //                       106: 2827
        //                       107: 3021
        //                       108: 3232
        //                       109: 3730
        //                       110: 3750
        //                       111: 3808
        //                       112: 3910
        //                       113: 3270
        //                       114: 2389
        //                       115: 3967
        //                       116: 4033
        //                       117: 4101
        //                       118: 4113
        //                       20481: 392
        //                       20482: 533
        //                       20485: 1082
        //                       20486: 1192
        //                       20487: 885
        //                       20488: 825
        //                       20489: 1295
        //                       20490: 1358
        //                       20491: 1421
        //                       20492: 1467
        //                       20493: 1535
        //                       20494: 1681
        //                       20495: 1713;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L3:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Unknown Command [").append(message.what).append("] Ignoring...").toString());
_L56:
            return true;
_L23:
            boolean flag10 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Create Operation Started");
            Log.d().getState().setOp(20481);
            if(Log.d(MtvAppPlayerOneSeg.this))
            {
                Log.d().getState().triggerNotification(20481, 24577, 0);
                Log.d(MtvAppPlayerOneSeg.this, 0);
            }
            Log.d(MtvOneSegFactory.getService());
            if(Log.d() != null)
            {
                flag10 = Log.d().registerListener(MtvAppPlayerOneSeg.this);
                if(flag10 && Log.d() != null)
                    flag10 = Log.d().create(Log.d(Log.d()));
            }
            if(!flag10)
            {
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20481, 24580, 0, 1);
                Log.d(MtvAppPlayerOneSeg.this);
            }
            continue; /* Loop/switch isn't completed */
_L24:
            boolean flag9 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Open Operation Started ChnlNum [").append(Log.d(Log.d()).chnlNum()).append("] fileIndex [").append(Log.d(Log.d()).fileIndex()).append("]").toString());
            Log.d().getState().setOp(20482);
            if(Log.d(MtvAppPlayerOneSeg.this))
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Posting OPEN_START to UiPlayer ");
                Log.d().getState().triggerNotification(20482, 24577, 0);
                Log.d(MtvAppPlayerOneSeg.this, 0);
            }
            if(Log.d() != null && Log.d() != null)
            {
                if(Log.d(Log.d()).pbType() == 2 || Log.d(Log.d()).pbType() == 4)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "As it is live/testmode, starting SignalQty Runnable ");
                    Log.d(MtvAppPlayerOneSeg.this, 1);
                }
                if(Log.d().getComponents() != null)
                {
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Enabling Captions ");
                    if(Log.d().getComponents().getCaption() != null)
                    {
                        Log.d().getComponents().getCaption().setControlInterface(MtvOneSegFactory.getCaptionControl());
                        Log.d().getComponents().getCaption().enable();
                    }
                }
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Calling OneSegService.Open() ");
                flag9 = Log.d().open(Log.d(Log.d()));
            } else
            if(Log.d() == null)
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "OpenURI is not set -- Cannot start playback session");
            else
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Handler: OneSegServiceHdl - null. Cannot start playback session");
            if(!flag9)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20482, 24580, 0, 1);
            continue; /* Loop/switch isn't completed */
_L28:
            boolean flag8 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "CAPTURE Operation Started");
            Log.d().getState().setOp(20488);
            if(Log.d() != null)
                flag8 = Log.d().captureFrame();
            if(!flag8)
                Log.d().getState().triggerNotification(20488, 24580, 0);
            continue; /* Loop/switch isn't completed */
_L27:
            boolean flag7;
            flag7 = false;
            Log.d().getState().setOp(20487);
            if(Log.d() == null) goto _L37; else goto _L36
_L36:
            message.arg1;
            JVM INSTR tableswitch 1 3: default 936
        //                       1 1004
        //                       2 1033
        //                       3 1052;
               goto _L38 _L39 _L40 _L41
_L38:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("] with REC Command. Cannot service it").toString());
_L42:
            if(!flag7)
            {
                Log.d().getState().setOp(20480);
                Log.d().getState().triggerNotification(20487, 24580, 0);
            }
            continue; /* Loop/switch isn't completed */
_L39:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "startRecord() Operation Started");
            flag7 = Log.d().startRecord((String)message.obj, message.arg2);
            continue; /* Loop/switch isn't completed */
_L40:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "cancelRecord() Operation Started");
            flag7 = Log.d().cancelRecord();
            continue; /* Loop/switch isn't completed */
_L41:
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "stopRecord() Operation Started");
            flag7 = Log.d().stopRecord();
            continue; /* Loop/switch isn't completed */
_L37:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Service Handle. Cannot Continue Recording");
            if(true) goto _L42; else goto _L25
_L25:
            boolean flag6 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "SCAN Operation Started");
            Log.d().getState().setState(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED);
            Log.d().getState().setOp(20485);
            if(Log.d(MtvAppPlayerOneSeg.this))
            {
                Log.d().getState().triggerNotification(20485, 24577, 0);
                Log.d(MtvAppPlayerOneSeg.this, 0);
            }
            if(Log.d() != null)
                flag6 = Log.d().scanChannels();
            if(!flag6)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20485, 24580, 0, 1);
            continue; /* Loop/switch isn't completed */
_L26:
            boolean flag5 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("CANCEL SCAN Operation Started LastSetScanned Chnl [").append(Log.d().getAttribute().getLastScannedChannel()).append("]").toString());
            Log.d().getState().setOp(20486);
            if(Log.d() != null)
                flag5 = Log.d().cancelScanChannels();
            if(!flag5)
            {
                Log.d().getState().setOp(20480);
                Log.d().getState().triggerNotification(20486, 24580, 0);
            }
            continue; /* Loop/switch isn't completed */
_L29:
            boolean flag4 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Pause Operation Started");
            Log.d().getState().setOp(20489);
            if(Log.d() != null)
                flag4 = Log.d().pause();
            if(!flag4)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20489, 24580, 0, 1);
            continue; /* Loop/switch isn't completed */
_L30:
            boolean flag3 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Resume Operation Started");
            Log.d().getState().setOp(20490);
            if(Log.d() != null)
                flag3 = Log.d().resume();
            if(!flag3)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20490, 24580, 0, 1);
            continue; /* Loop/switch isn't completed */
_L31:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "TrcikMode Operation Started");
            Log.d().getState().setOp(20491);
            Log.d(MtvAppPlayerOneSeg.this, message.what, 0, message.arg1, message.arg2, 0, 0);
            continue; /* Loop/switch isn't completed */
_L32:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Reposition Operation Started repositionTimeStamp [").append(message.arg1).append("]").toString());
            Log.d().getState().setOp(20492);
            Log.d(MtvAppPlayerOneSeg.this, message.what, 0, 0, message.arg1, 0, 0);
            continue; /* Loop/switch isn't completed */
_L33:
            boolean flag2 = false;
            if(Log.d() == null) goto _L44; else goto _L43
_L43:
            message.arg1;
            JVM INSTR tableswitch 1 2: default 1572
        //                       1 1632
        //                       2 1670;
               goto _L45 _L46 _L47
_L45:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid command [").append(message.arg1).append("] in TVLink Operation. Failure in TVLInk Operation()").toString());
_L44:
            if(!flag2)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20493, 24580, 0, 1);
            continue; /* Loop/switch isn't completed */
_L46:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "TVLink Operation Started ");
            Log.d().getState().setOp(20493);
            flag2 = Log.d().startTVLink((MtvOneSegTVLink)message.obj);
            continue; /* Loop/switch isn't completed */
_L47:
            flag2 = Log.d().stopTVLink();
            if(true) goto _L44; else goto _L34
_L34:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Close Operation Started");
            Log.d().getState().setOp(20494);
            Log.d(MtvAppPlayerOneSeg.this, 1);
            continue; /* Loop/switch isn't completed */
_L35:
            boolean flag1;
            flag1 = false;
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Delete Operation Started");
            if(Log.d().getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && Log.d().getState().getOp() != 20485) goto _L49; else goto _L48
_L48:
            if(Log.d() != null)
            {
                Log.d(MtvAppPlayerOneSeg.this, 0, 20495, 0, 0, Log.d(), null, null);
                flag1 = Log.d(MtvAppPlayerOneSeg.this, 1);
            }
_L50:
            Log.d().getState().setOp(20495);
            if(!flag1)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully deleted the playback session ");
                Log.d(MtvAppPlayerOneSeg.this, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, 20495, 24581, 0, 1);
                Log.d(MtvAppPlayerOneSeg.this);
            }
            continue; /* Loop/switch isn't completed */
_L49:
            if(Log.d() != null)
            {
                Log.d(MtvAppPlayerOneSeg.this, 0, 20495, 0, 0, Log.d(), null, null);
                flag1 = Log.d().delete();
            }
            if(true) goto _L50; else goto _L5
_L5:
            Log.d(MtvAppPlayerOneSeg.this, 0);
            Log.d(MtvAppPlayerOneSeg.this, Log.d().getState().getOp());
            continue; /* Loop/switch isn't completed */
_L4:
            if(Log.d() != null)
                Log.d().deleteTVFile(message.arg1);
            continue; /* Loop/switch isn't completed */
_L6:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully created the playback session");
                Log.d().getState().setOp(20480);
                Log.d(MtvAppPlayerOneSeg.this, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED, 20481, 24581, 0, 1);
                if(Log.d() != null)
                {
                    Log.d().getAttribute().setSignalStatistics(Log.d().getSignalStatistics());
                    Log.d().getAttribute().setSignalLevel(Log.d().getSignalQuality());
                    Log.d(MtvAppPlayerOneSeg.this).run();
                }
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Failure in creating the playback session ");
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20481, 24580, 0, 1);
            }
            continue; /* Loop/switch isn't completed */
_L7:
            boolean flag = Log.d(MtvAppPlayerOneSeg.this, 20482);
            if(!flag)
                switch(message.arg1)
                {
                case 202: 
                default:
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("With OPEN command from backend.. Verify the commands once again!").toString());
                    break;

                case 201: 
                    Log.d(MtvAppPlayerOneSeg.this, 0);
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully opened the playback session ");
                    Log.d(MtvAppPlayerOneSeg.this, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED, 20482, 24581, 0, 1);
                    Log.d().getAttribute().setChannel((MtvOneSegChannel)message.obj);
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Play Operation Started");
                    Log.d(MtvAppPlayerOneSeg.this, 20484, 20484, 24577, 0, 1);
                    int l = Log.d(Log.d()).pbType();
                    if(l != 5 && l != 3)
                    {
                        if(Log.d() != null)
                            flag = Log.d().start((MtvOneSegChannel)message.obj);
                        if(!flag)
                        {
                            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in starting player.. Govinda Govinda !!");
                            Log.d(MtvAppPlayerOneSeg.this, 20480, 20484, 24580, 0, 1);
                        }
                    }
                    break;

                case 204: 
                case 205: 
                case 206: 
                    Log.d(MtvAppPlayerOneSeg.this, 20482, message.arg1, message.arg2);
                    break;

                case 203: 
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "Failure in opening the playback session ");
                    if(Log.d(Log.d()).pbType() == 2 || Log.d(Log.d()).pbType() == 4)
                        Log.d(MtvAppPlayerOneSeg.this, 1);
                    else
                        Log.d(MtvAppPlayerOneSeg.this, 20480, 20482, 24580, 0, 1);
                    break;
                }
            continue; /* Loop/switch isn't completed */
_L18:
            if(message.arg1 == 215)
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20484, 24593, 0, 1);
            else
            if(!Log.d(MtvAppPlayerOneSeg.this, 20484))
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
                    Log.d().getState().setOp(20480);
                    Log.d(MtvAppPlayerOneSeg.this, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, 20484, 24581, 0, 1);
                    break;

                case 204: 
                case 205: 
                case 206: 
                    Log.d(MtvAppPlayerOneSeg.this, 20484, message.arg1, message.arg2);
                    break;

                case 214: 
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", "End of file reached");
                    Log.d(MtvAppPlayerOneSeg.this, 20480, 20484, 24592, 0, 1);
                    break;

                case 203: 
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Failure in starting the playback session ");
                    Log.d(MtvAppPlayerOneSeg.this, 20480, 20484, 24580, 0, 1);
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
            JVM INSTR lookupswitch 3: default 2712
        //                       201: 2731
        //                       216: 2758
        //                       217: 2766;
               goto _L51 _L52 _L53 _L54
_L51:
            Log.d().getState().triggerNotification(20487, c4, k);
            continue; /* Loop/switch isn't completed */
_L52:
            Log.d().getComponents().getVideo().setCaptFrameName((String)message.obj);
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
            Log.d().getState().setOp(20480);
            Log.d().getState().triggerNotification(20487, c3, message.arg2);
            continue; /* Loop/switch isn't completed */
_L10:
            int j = Log.d().getState().getOp();
            MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Got the captured frame");
            if(j == 20488)
            {
                Log.d().getState().setOp(20480);
                if(message.arg1 == 201)
                {
                    Log.d().getComponents().getVideo().setCaptFrame((Bitmap)message.obj);
                    Log.d().getState().triggerNotification(j, 24581, 0);
                } else
                {
                    Log.d().getState().triggerNotification(j, 24580, 0);
                }
            } else
            if(j == 20487)
            {
                if(message.arg1 == 201)
                {
                    Log.d().getComponents().getVideo().setCaptFrame((Bitmap)message.obj);
                    Log.d().getState().triggerNotification(j, 24596, 0);
                }
            } else
            {
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid curCmd [").append(j).append("]. CaptureFrame CB Cannot be handled!").toString());
            }
            continue; /* Loop/switch isn't completed */
_L11:
            if(!Log.d(MtvAppPlayerOneSeg.this, 20485) && Log.d().getState().getOp() == 20485)
            {
                switch(message.arg1)
                {
                default:
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Status [").append(message.arg1).append("With SCAN command from backend.. Verify the commands once again!").toString());
                    break;

                case 202: 
                    char c2 = '\u6016';
                    Log.d().getAttribute().setLastScannedChannel(message.arg2);
                    if(message.obj != null)
                    {
                        Log.d().getAttribute().setChannel((MtvOneSegChannel)message.obj);
                        c2 = '\u6015';
                    }
                    Log.d().getState().triggerNotification(20485, c2, message.arg2);
                    break;

                case 201: 
                case 203: 
                    char c1 = '\u6004';
                    if(message.arg1 == 201)
                        c1 = '\u6005';
                    Log.d(MtvAppPlayerOneSeg.this, 20480, 20485, c1, 0, 1);
                    Log.d().getAttribute().reset();
                    break;
                }
                if(false)
                    ;
            }
            continue; /* Loop/switch isn't completed */
_L12:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CANCEL_SCAN from Service");
            Log.d(MtvAppPlayerOneSeg.this, 20480, 20486, 24581, 0, 1);
            Log.d().getAttribute().reset();
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
                if(Log.d().getState().getOp() != 20494)
                    Log.d().getAttribute().setProgram((android.broadcast.helper.types.MtvOneSegProgram[])(android.broadcast.helper.types.MtvOneSegProgram[])message.obj);
                break;

            case 209: 
                if(Log.d().getState().getOp() != 20494)
                    Log.d().getAttribute().setTot(((Long)message.obj).longValue());
                break;

            case 210: 
                if(Log.d(Log.d()) == 0)
                {
                    if(message.arg2 == 2)
                        Log.d(MtvAppPlayerOneSeg.this, 1);
                    else
                        Log.d(MtvAppPlayerOneSeg.this, 0);
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("As there are pending commands [").append(Log.d(Log.d())).append("] to be serviced, ignoring LOW_SIGNAL from Player").toString());
                }
                break;

            case 211: 
                int i = Log.d().getState().getOp();
                if(i != 20494 && i != 20491 && i != 20492)
                {
                    Log.d().getComponents().getCaption().setBuffer((SpannableStringBuilder)message.obj);
                    Log.d().getState().triggerNotification(20483, 24589, 0);
                }
                break;

            case 212: 
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Time Update:: New time is [").append(message.arg2).append("]").toString());
                Log.d().getState().triggerNotification(20483, 24590, message.arg2);
                break;

            case 213: 
                Log.d().getState().triggerNotification(20483, 24591, 0);
                break;

            case 218: 
                char c = '\u6004';
                if(message.arg2 == 201)
                    c = '\u6003';
                Log.d().getState().triggerNotification(20483, 24599, c);
                break;

            case 207: 
                Log.d().getState().triggerNotification(20483, 24585, 0);
                break;
            }
            continue; /* Loop/switch isn't completed */
_L13:
            Log.d(MtvAppPlayerOneSeg.this, 1, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PAUSED, 0);
            continue; /* Loop/switch isn't completed */
_L14:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Resume SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
            Log.d(MtvAppPlayerOneSeg.this, 256, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING, message.arg2);
            continue; /* Loop/switch isn't completed */
_L15:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Trickmode SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
                Log.d(MtvAppPlayerOneSeg.this, 16, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            } else
            if(message.arg1 == 202)
                Log.d(MtvAppPlayerOneSeg.this, 4096, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            continue; /* Loop/switch isn't completed */
_L16:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Got Reposition SUCCESS from Service: New time is: [").append(message.arg2).append("]").toString());
            Log.d(MtvAppPlayerOneSeg.this, 16, message.arg1, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, message.arg2);
            continue; /* Loop/switch isn't completed */
_L19:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CMD_TVLINK_START SUCCESS from Service:");
                Log.d().getState().triggerNotification(20493, 24578, 0);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Got CMD_TVLINK_START FAILURE!!! from Service:");
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20493, 24580, 0, 1);
            }
            continue; /* Loop/switch isn't completed */
_L20:
            if(message.arg1 == 201)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "CMD_TVLINK_STOP:TVLink Operation Completed ");
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20493, 24581, 0, 1);
            } else
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "CMD_TVLINK_STOP FAILURE!!!! ");
                Log.d(MtvAppPlayerOneSeg.this, 20480, 20493, 24580, 0, 1);
            }
            continue; /* Loop/switch isn't completed */
_L21:
            Log.d(MtvAppPlayerOneSeg.this, 0);
            continue; /* Loop/switch isn't completed */
_L22:
            MtvUtilDebug.High("MtvAppPlayerOneSeg", "Successfully deleted the playback session ");
            Log.d(MtvAppPlayerOneSeg.this, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE, 20495, 24581, 0, 1);
            Log.d(MtvAppPlayerOneSeg.this);
            continue; /* Loop/switch isn't completed */
_L2:
            MtvUtilDebug.Error("MtvAppPlayerOneSeg", "******Invalid Context. Cannot Handle commands in PlayerThread******");
            if(true) goto _L56; else goto _L55
_L55:
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$1;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRetryChnlSearch = new Runnable() {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$2;->run()V");
            if(Log.d() != null && Log.d().getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED && Log.d() != null)
            {
                MtvUtilDebug.Low("MtvAppPlayerOneSeg", "Posting OPEN from Retry Runnable ");
                Log.d(MtvAppPlayerOneSeg.this, 20482, 0, 0, Log.d(Log.d()));
            } else
            {
                MtvUtilDebug.Error("MtvAppPlayerOneSeg", (new StringBuilder()).append("Invalid Parameters mCurContext [").append(Log.d()).append(" ] mPendingParam [").append(Log.d()).append("] Cannot retry channel search").toString());
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$2;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRunnableLowSignal = new Runnable() {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$3;->run()V");
            if(Log.d(MtvAppPlayerOneSeg.this) != null && Log.d() != null)
            {
                MtvUtilDebug.High("MtvAppPlayerOneSeg", "Low- Signal:: Go to good signal area else TV will be stopped immediately!!!");
                if(Log.d().getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV)
                {
                    if(Log.d() != null && Log.d(Log.d()) != null)
                        Log.d().getState().triggerNotification(20483, 24588, Log.d(Log.d()).chnlNum());
                    else
                        MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid Pending Parameters.. May be nobody interested in receiving the notification !!");
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Live is not going on! How come low-signal now -- Anyways ignore it!!");
                }
                Log.d(MtvAppPlayerOneSeg.this, 0);
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$3;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };
    private final Runnable mRunnableUpdateSignal = new Runnable() {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$4;->run()V");
            com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State _tmp = com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE;
            if(Log.d(MtvAppPlayerOneSeg.this) != null && Log.d() != null)
            {
                com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state = Log.d().getState().getState();
                if(state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED || state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED || state == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                {
                    Log.d().getAttribute().setSignalStatistics(Log.d().getSignalStatistics());
                    int i = Log.d().getSignalQuality();
                    MtvUtilDebug.High("MtvAppPlayerOneSeg", (new StringBuilder()).append("Current SignalQuality [").append(i).append("]").toString());
                    Log.d().getAttribute().setSignalLevel(i);
                    if(i > 0);
                    if(Log.d() == 3)
                    {
                        MtvUtilDebug.High("MtvAppPlayerOneSeg", "Low- Signal:: Go to good signal area else TV will be stopped immediately!!!");
                        Log.d().getState().triggerNotification(20483, 24588, 0);
                        Log.d(MtvAppPlayerOneSeg.this, 0);
                    } else
                    {
                        Log.d(MtvAppPlayerOneSeg.this, 1);
                    }
                } else
                {
                    MtvUtilDebug.Error("MtvAppPlayerOneSeg", "Invalid State, not extracting signal quality!");
                }
            }
        }

        final MtvAppPlayerOneSeg this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$4;-><init>(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
                this$0 = MtvAppPlayerOneSeg.this;
                super();
            }
    };

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;-><clinit>()V");
    }


/*
    static void access$1100(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1100(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Z)V");
        mtvappplayeroneseg.handleSignalUpdate(flag);
        return;
    }

*/


/*
    static boolean access$1200(MtvAppPlayerOneSeg mtvappplayeroneseg, int i, int j, int k, int l, int i1, int j1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1200(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;IIIIII)Z");
        return mtvappplayeroneseg.handleSeek(i, j, k, l, i1, j1);
    }

*/


/*
    static boolean access$1300(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1300(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Z)Z");
        return mtvappplayeroneseg.handleClose(flag);
    }

*/


/*
    static boolean access$1400(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag, int i, int j, int k, MtvAppPlaybackContext mtvappplaybackcontext, Context context, MtvURI mtvuri)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1400(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;ZIIILcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Landroid/content/Context;Landroid/broadcast/helper/MtvURI;)Z");
        return mtvappplayeroneseg.setPendingOperation(flag, i, j, k, mtvappplaybackcontext, context, mtvuri);
    }

*/


/*
    static void access$1500(MtvAppPlayerOneSeg mtvappplayeroneseg, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, int i, int j, int k, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1500(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;IIIZ)V");
        mtvappplayeroneseg.setContextState(state, i, j, k, flag);
        return;
    }

*/


/*
    static void access$1600(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1600(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Z)V");
        mtvappplayeroneseg.handleRetryChnlSearch(flag);
        return;
    }

*/


/*
    static boolean access$1700(MtvAppPlayerOneSeg mtvappplayeroneseg, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1700(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;I)Z");
        return mtvappplayeroneseg.handlePendingCommand(i);
    }

*/


/*
    static Runnable access$1800(MtvAppPlayerOneSeg mtvappplayeroneseg)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1800(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)Ljava/lang/Runnable;");
        return mtvappplayeroneseg.mRunnableUpdateSignal;
    }

*/


/*
    static void access$1900(MtvAppPlayerOneSeg mtvappplayeroneseg, int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$1900(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;III)V");
        mtvappplayeroneseg.handleBuffering(i, j, k);
        return;
    }

*/


/*
    static MtvAppPlaybackContext access$200()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$200()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mCurContext;
    }

*/


/*
    static MtvAppPlaybackContext access$202(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$202(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        mCurContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/


/*
    static void access$2100(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$2100(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Z)V");
        mtvappplayeroneseg.handleLowSignal(flag);
        return;
    }

*/


/*
    static boolean access$2200(MtvAppPlayerOneSeg mtvappplayeroneseg, int i, int j, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$2200(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;IILcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;I)Z");
        return mtvappplayeroneseg.handleLocalPlayback(i, j, state, k);
    }

*/


/*
    static MtvAppPlayerPendingParams access$300()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$300()Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg$MtvAppPlayerPendingParams;");
        return mPendingParam;
    }

*/


/*
    static boolean access$3200(MtvAppPlayerOneSeg mtvappplayeroneseg, int i, int j, int k, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$3200(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;IIILjava/lang/Object;)Z");
        return mtvappplayeroneseg.sendPlayerThreadMsg(i, j, k, obj);
    }

*/


/*
    static Handler access$3300(MtvAppPlayerOneSeg mtvappplayeroneseg)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$3300(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)Landroid/os/Handler;");
        return mtvappplayeroneseg.mPlayerHdlr;
    }

*/


/*
    static int access$3400()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$3400()I");
        return mLowSigCnt;
    }

*/


/*
    static boolean access$500(MtvAppPlayerOneSeg mtvappplayeroneseg)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$500(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)Z");
        return mtvappplayeroneseg.getPendingOpStatus();
    }

*/


/*
    static void access$600(MtvAppPlayerOneSeg mtvappplayeroneseg, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$600(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;Z)V");
        mtvappplayeroneseg.setPendingOpStatus(flag);
        return;
    }

*/


/*
    static MtvOneSegService access$700()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$700()Landroid/broadcast/oneseg/MtvOneSegService;");
        return mOneSegServ;
    }

*/


/*
    static MtvOneSegService access$702(MtvOneSegService mtvonesegservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$702(Landroid/broadcast/oneseg/MtvOneSegService;)Landroid/broadcast/oneseg/MtvOneSegService;");
        mOneSegServ = mtvonesegservice;
        return mtvonesegservice;
    }

*/


/*
    static void access$800(MtvAppPlayerOneSeg mtvappplayeroneseg, int i, int j, int k, int l, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$800(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;IIIIZ)V");
        mtvappplayeroneseg.setContextParams(i, j, k, l, flag);
        return;
    }

*/


/*
    static void access$900(MtvAppPlayerOneSeg mtvappplayeroneseg)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;->access$900(Lcom/samsung/sec/mtv/app/player/MtvAppPlayerOneSeg;)V");
        mtvappplayeroneseg.resetApp();
        return;
    }

*/
}
