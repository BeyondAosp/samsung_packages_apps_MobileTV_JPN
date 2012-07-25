// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.helper.types.*;
import android.util.Log;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            IMtvAppProgramAttributeListener

public class MtvAppProgramAttributes
{

    MtvAppProgramAttributes()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;-><init>()V");
        super();
        init();
    }

    private void init()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->init()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getChannel()Landroid/broadcast/helper/types/MtvOneSegChannel;");
        return channel;
    }

    public int getLastScannedChannel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getLastScannedChannel()I");
        return lastScannedChannel;
    }

    public MtvOneSegProgram[] getProgram()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getProgram()[Landroid/broadcast/helper/types/MtvOneSegProgram;");
        return program;
    }

    public int getSignalLevel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getSignalLevel()I");
        return signalLevel;
    }

    public MtvOneSegSignal getSignalStatistics()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getSignalStatistics()Landroid/broadcast/helper/types/MtvOneSegSignal;");
        return signal;
    }

    public long getTot()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->getTot()J");
        return tot;
    }

    public void registerListener(IMtvAppProgramAttributeListener imtvappprogramattributelistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->registerListener(Lcom/samsung/sec/mtv/app/context/IMtvAppProgramAttributeListener;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->reset()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setChannel(Landroid/broadcast/helper/types/MtvOneSegChannel;)V");
        channel = mtvonesegchannel;
        if(cb != null)
            cb.onProgramAttributeUpdated(3);
    }

    public void setLastScannedChannel(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setLastScannedChannel(I)V");
        lastScannedChannel = i;
    }

    public void setProgram(MtvOneSegProgram amtvonesegprogram[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setProgram([Landroid/broadcast/helper/types/MtvOneSegProgram;)V");
        program = amtvonesegprogram;
        if(cb != null)
            cb.onProgramAttributeUpdated(2);
    }

    public void setSignalLevel(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setSignalLevel(I)V");
        signalLevel = i;
        if(cb != null)
            cb.onProgramAttributeUpdated(16);
    }

    public void setSignalStatistics(MtvOneSegSignal mtvonesegsignal)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setSignalStatistics(Landroid/broadcast/helper/types/MtvOneSegSignal;)V");
        signal = mtvonesegsignal;
    }

    public void setTot(long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;->setTot(J)V");
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
