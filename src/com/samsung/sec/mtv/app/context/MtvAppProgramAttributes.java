// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.helper.types.*;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            IMtvAppProgramAttributeListener

public class MtvAppProgramAttributes
{

    MtvAppProgramAttributes()
    {
        init();
    }

    private void init()
    {
        cb = null;
        prgNumber = 0;
        program = null;
        channel = null;
        tot = 0L;
        signalLevel = 0;
        lastScannedChannel = 0;
    }

    public MtvOneSegChannel getChannel()
    {
        return channel;
    }

    public int getLastScannedChannel()
    {
        return lastScannedChannel;
    }

    public MtvOneSegProgram[] getProgram()
    {
        return program;
    }

    public int getSignalLevel()
    {
        return signalLevel;
    }

    public MtvOneSegSignal getSignalStatistics()
    {
        return signal;
    }

    public long getTot()
    {
        return tot;
    }

    public void registerListener(IMtvAppProgramAttributeListener imtvappprogramattributelistener)
    {
        this;
        JVM INSTR monitorenter ;
        cb = imtvappprogramattributelistener;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void reset()
    {
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

    public void setChannel(MtvOneSegChannel mtvonesegchannel)
    {
        channel = mtvonesegchannel;
        if(cb != null)
            cb.onProgramAttributeUpdated(3);
    }

    public void setLastScannedChannel(int i)
    {
        lastScannedChannel = i;
    }

    public void setProgram(MtvOneSegProgram amtvonesegprogram[])
    {
        program = amtvonesegprogram;
        if(cb != null)
            cb.onProgramAttributeUpdated(2);
    }

    public void setSignalLevel(int i)
    {
        signalLevel = i;
        if(cb != null)
            cb.onProgramAttributeUpdated(16);
    }

    public void setSignalStatistics(MtvOneSegSignal mtvonesegsignal)
    {
        signal = mtvonesegsignal;
    }

    public void setTot(long l)
    {
        tot = l;
    }

    private IMtvAppProgramAttributeListener cb;
    private MtvOneSegChannel channel;
    private int lastScannedChannel;
    private int prgNumber;
    private MtvOneSegProgram program[];
    private MtvOneSegSignal signal;
    private int signalLevel;
    private long tot;
}
