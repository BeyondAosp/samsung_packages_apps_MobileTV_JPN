// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.oneseg.MtvOneSegFactory;
import android.util.Log;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            MtvAppPlaybackContext, MtvAppProgramComponents

public class MtvAppPlaybackContextManager
{
    private static class SingletonHolder
    {

        private static final MtvAppPlaybackContextManager instance = new MtvAppPlaybackContextManager();

        static 
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager$SingletonHolder;-><clinit>()V");
        }


/*
        static MtvAppPlaybackContextManager access$100()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager$SingletonHolder;->access$100()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;");
            return instance;
        }

*/

        private SingletonHolder()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager$SingletonHolder;-><init>()V");
            super();
        }
    }


    private MtvAppPlaybackContextManager()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;-><init>()V");
        super();
        this;
        JVM INSTR monitorenter ;
        live = null;
        local = null;
        tvlink = null;
        scan = null;
        current = null;
        return;
    }

    MtvAppPlaybackContextManager(_cls1 _pcls1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;-><init>(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager$1;)V");
        this();
    }

    public static MtvAppPlaybackContextManager getInstance()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->getInstance()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;");
        return Log.d();
    }

    public MtvAppPlaybackContext getCurrentContext()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->getCurrentContext()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        this;
        JVM INSTR monitorenter ;
        MtvAppPlaybackContext mtvappplaybackcontext = current;
        this;
        JVM INSTR monitorexit ;
        return mtvappplaybackcontext;
        Exception exception;
        exception;
        throw exception;
    }

    public MtvAppPlaybackContext getLiveTV()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->getLiveTV()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        this;
        JVM INSTR monitorenter ;
        MtvAppPlaybackContext mtvappplaybackcontext;
        if(live == null)
            live = new MtvAppPlaybackContext(MtvAppPlaybackContext.Type.LIVETV);
        mtvappplaybackcontext = live;
        this;
        JVM INSTR monitorexit ;
        return mtvappplaybackcontext;
        Exception exception;
        exception;
        throw exception;
    }

    public MtvAppPlaybackContext getLocalTV()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->getLocalTV()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        this;
        JVM INSTR monitorenter ;
        MtvAppPlaybackContext mtvappplaybackcontext;
        if(local == null)
            local = new MtvAppPlaybackContext(MtvAppPlaybackContext.Type.LOCALTV);
        local.getComponents().getAudio().setControlInterface(MtvOneSegFactory.getAudioControl());
        local.getComponents().getVideo().setControlInterface(MtvOneSegFactory.getVideoControl());
        mtvappplaybackcontext = local;
        this;
        JVM INSTR monitorexit ;
        return mtvappplaybackcontext;
        Exception exception;
        exception;
        throw exception;
    }

    public MtvAppPlaybackContext getScanner()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->getScanner()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        this;
        JVM INSTR monitorenter ;
        MtvAppPlaybackContext mtvappplaybackcontext;
        if(scan == null)
            scan = new MtvAppPlaybackContext(MtvAppPlaybackContext.Type.SCANNER);
        scan.getComponents().getAudio().setControlInterface(MtvOneSegFactory.getAudioControl());
        scan.getComponents().getVideo().setControlInterface(MtvOneSegFactory.getVideoControl());
        mtvappplaybackcontext = scan;
        this;
        JVM INSTR monitorexit ;
        return mtvappplaybackcontext;
        Exception exception;
        exception;
        throw exception;
    }

    public void reset()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->reset()V");
        this;
        JVM INSTR monitorenter ;
        if(scan != null)
            scan.reset();
        if(tvlink != null)
            tvlink.reset();
        if(local != null)
            local.reset();
        if(live != null)
            live.reset();
        if(current != null)
            current.reset();
        live = null;
        local = null;
        tvlink = null;
        scan = null;
        current = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setCurrentContext(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContextManager;->setCurrentContext(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)V");
        MtvAppPlaybackContext mtvappplaybackcontext1 = current;
        if(mtvappplaybackcontext == null || mtvappplaybackcontext1 == mtvappplaybackcontext)
            break MISSING_BLOCK_LABEL_41;
        this;
        JVM INSTR monitorenter ;
        current = mtvappplaybackcontext;
    }

    private MtvAppPlaybackContext current;
    private MtvAppPlaybackContext live;
    private MtvAppPlaybackContext local;
    private MtvAppPlaybackContext scan;
    private MtvAppPlaybackContext tvlink;
}
