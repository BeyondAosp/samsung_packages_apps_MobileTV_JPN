// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.oneseg.MtvOneSegFactory;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            MtvAppPlaybackContext, MtvAppProgramComponents

public class MtvAppPlaybackContextManager
{
    private static class SingletonHolder
    {

        private static final MtvAppPlaybackContextManager instance = new MtvAppPlaybackContextManager();



        private SingletonHolder()
        {
        }
    }


    private MtvAppPlaybackContextManager()
    {
        this;
        JVM INSTR monitorenter ;
        live = null;
        local = null;
        tvlink = null;
        scan = null;
        current = null;
        return;
    }


    public static MtvAppPlaybackContextManager getInstance()
    {
        return SingletonHolder.instance;
    }

    public MtvAppPlaybackContext getCurrentContext()
    {
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
        MtvAppPlaybackContext mtvappplaybackcontext1 = current;
        if(mtvappplaybackcontext == null || mtvappplaybackcontext1 == mtvappplaybackcontext)
            break MISSING_BLOCK_LABEL_31;
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
