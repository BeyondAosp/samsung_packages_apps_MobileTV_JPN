// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.helper.MtvUtilDebug;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            IMtvAppPlaybackStateListener

public final class MtvAppPlaybackState
{
    private static class Notification
    {

        final int command;
        final int extra;
        final int status;

        Notification(int i, int j, int k)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$Notification;-><init>(III)V");
            super();
            command = i;
            status = j;
            extra = k;
        }
    }

    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;->valueOf(Ljava/lang/String;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;");
            return (State)Enum.valueOf(com/samsung/sec/mtv/app/context/MtvAppPlaybackState$State, s);
        }

        public static State[] values()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;->values()[Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;");
            return (State[])$VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State INITIALIZED;
        public static final State NONE;
        public static final State OPENED;
        public static final State PAUSED;
        public static final State PLAYING;

        static 
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;-><clinit>()V");
            NONE = new State("NONE", 0);
            INITIALIZED = new State("INITIALIZED", 1);
            OPENED = new State("OPENED", 2);
            PLAYING = new State("PLAYING", 3);
            PAUSED = new State("PAUSED", 4);
            State astate[] = new State[5];
            astate[0] = NONE;
            astate[1] = INITIALIZED;
            astate[2] = OPENED;
            astate[3] = PLAYING;
            astate[4] = PAUSED;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;-><init>(Ljava/lang/String;I)V");
            super(s, i);
        }
    }


    MtvAppPlaybackState()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;-><init>()V");
        super();
        this;
        JVM INSTR monitorenter ;
        msgQ = new LinkedList();
        init();
        return;
    }

    private void init()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->init()V");
        state = State.NONE;
        op = 0;
        cb = null;
        if(!msgQ.isEmpty())
            msgQ.clear();
    }

    public int getOp()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->getOp()I");
        this;
        JVM INSTR monitorenter ;
        int i = op;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public State getState()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->getState()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;");
        this;
        JVM INSTR monitorenter ;
        State state1 = state;
        this;
        JVM INSTR monitorexit ;
        return state1;
        Exception exception;
        exception;
        throw exception;
    }

    public void registerListener(IMtvAppPlaybackStateListener imtvappplaybackstatelistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->registerListener(Lcom/samsung/sec/mtv/app/context/IMtvAppPlaybackStateListener;)V");
        this;
        JVM INSTR monitorenter ;
        cb = imtvappplaybackstatelistener;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void reset()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->reset()V");
        this;
        JVM INSTR monitorenter ;
        init();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOp(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->setOp(I)V");
        this;
        JVM INSTR monitorenter ;
        op = i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setState(State state1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->setState(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;)V");
        this;
        JVM INSTR monitorenter ;
        State state2;
        IMtvAppPlaybackStateListener imtvappplaybackstatelistener;
        state2 = state;
        state = state1;
        imtvappplaybackstatelistener = cb;
        this;
        JVM INSTR monitorexit ;
        if(imtvappplaybackstatelistener != null)
            imtvappplaybackstatelistener.onStateChanged(state2, state1);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void triggerNotification(int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->triggerNotification(III)V");
        this;
        JVM INSTR monitorenter ;
        IMtvAppPlaybackStateListener imtvappplaybackstatelistener = cb;
        this;
        JVM INSTR monitorexit ;
        if(imtvappplaybackstatelistener != null)
            imtvappplaybackstatelistener.onPlayerNotification(i, j, k);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void triggerNotification(int i, int j, int k, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;->triggerNotification(IIIZ)V");
        if(flag) goto _L2; else goto _L1
_L1:
        triggerNotification(i, j, k);
_L9:
        return;
_L2:
        Notification notification = new Notification(i, j, k);
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        IMtvAppPlaybackStateListener imtvappplaybackstatelistener;
        imtvappplaybackstatelistener = cb;
        if(imtvappplaybackstatelistener == null || !msgQ.isEmpty())
        {
            flag1 = true;
            if(!msgQ.offer(notification))
            {
                MtvUtilDebug.Error("MtvAppPlaybackState", (new StringBuilder()).append("SERIOUS ERROR: Couldnt add cmd [").append(i).append("] to msgQ").toString());
                flag1 = false;
            }
        }
        this;
        JVM INSTR monitorexit ;
_L10:
        if(imtvappplaybackstatelistener == null) goto _L4; else goto _L3
_L3:
        if(!flag1)
            break MISSING_BLOCK_LABEL_250;
        this;
        JVM INSTR monitorenter ;
        notification = (Notification)msgQ.peek();
        imtvappplaybackstatelistener = cb;
        this;
        JVM INSTR monitorexit ;
        if(imtvappplaybackstatelistener == null || notification == null) goto _L6; else goto _L5
_L5:
        imtvappplaybackstatelistener.onPlayerNotification(notification.command, notification.status, notification.extra);
        this;
        JVM INSTR monitorenter ;
        msgQ.poll();
        if(msgQ.isEmpty()) goto _L8; else goto _L7
_L7:
        flag1 = true;
_L11:
        this;
        JVM INSTR monitorexit ;
_L4:
        if(flag1) goto _L10; else goto _L9
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        this;
        JVM INSTR monitorexit ;
        throw exception1;
_L8:
        flag1 = false;
          goto _L11
        Exception exception2;
        exception2;
        this;
        JVM INSTR monitorexit ;
        throw exception2;
_L6:
        flag1 = false;
          goto _L4
        imtvappplaybackstatelistener.onPlayerNotification(notification.command, notification.status, notification.extra);
          goto _L4
    }

    private IMtvAppPlaybackStateListener cb;
    private Queue msgQ;
    private int op;
    private State state;
}
