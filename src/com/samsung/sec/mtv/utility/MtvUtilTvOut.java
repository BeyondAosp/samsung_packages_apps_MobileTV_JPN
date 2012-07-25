// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;
import android.os.*;
import android.util.Log;

public class MtvUtilTvOut
{

    public MtvUtilTvOut()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilTvOut;-><init>()V");
        super();
    }

    public static void resumeTvOut()
    {
        ITvoutService itvoutservice;
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilTvOut;->resumeTvOut()V");
        MtvUtilDebug.Low("MtvUtilTvOut", "resumeTvOut() called");
        itvoutservice = android.os.ITvoutService.Stub.asInterface(ServiceManager.getService("tvoutservice"));
        if(itvoutservice != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Error("MtvUtilTvOut", "resumeTvOut() - TvOutService Not running");
_L4:
        return;
_L2:
        try
        {
            if(itvoutservice.TvoutGetSuspendStatus())
            {
                MtvUtilDebug.Low("MtvUtilTvOut", "TvoutGetSuspendStatus = true, making it false");
                itvoutservice.TvoutSetSuspendStatus(false);
            }
        }
        catch(RemoteException remoteexception)
        {
            MtvUtilDebug.Error("MtvUtilTvOut", (new StringBuilder()).append("resumeTvOut() - Resume failed ").append(remoteexception).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void suspendTvOut()
    {
        ITvoutService itvoutservice;
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilTvOut;->suspendTvOut()V");
        MtvUtilDebug.Low("MtvUtilTvOut", "suspendTvOut() called");
        itvoutservice = android.os.ITvoutService.Stub.asInterface(ServiceManager.getService("tvoutservice"));
        if(itvoutservice != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Error("MtvUtilTvOut", "suspendTvOut() - TvOutService Not running");
_L3:
        return;
_L2:
        int i = 1;
_L4:
        if(i > 5)
            break MISSING_BLOCK_LABEL_60;
        if(!itvoutservice.TvoutGetStatus())
            break MISSING_BLOCK_LABEL_151;
        MtvUtilDebug.Low("MtvUtilTvOut", "TvoutGetStatus - enabled");
        if(itvoutservice.TvoutGetStatus() && !itvoutservice.TvoutGetSuspendStatus())
        {
            MtvUtilDebug.Low("MtvUtilTvOut", "TvoutGetSuspendStatus = false, making it true");
            itvoutservice.TvoutSetSuspendStatus(true);
            if(itvoutservice.TvoutGetSuspendStatus())
            {
                MtvUtilDebug.Low("MtvUtilTvOut", "TvoutPostSuspend, posting a string");
                itvoutservice.TvoutPostSuspend("HDMI not available while application is running. Application will display on phone only");
            }
        }
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        MtvUtilDebug.Error("MtvUtilTvOut", (new StringBuilder()).append("suspendTvOut() - Suspend failed ").append(remoteexception).toString());
          goto _L3
        MtvUtilDebug.Low("MtvUtilTvOut", "TvoutGetStatus - waiting");
        Thread.sleep(100L);
_L5:
        i++;
          goto _L4
        InterruptedException interruptedexception;
        interruptedexception;
        MtvUtilDebug.Error("MtvUtilTvOut", (new StringBuilder()).append("Thread interrupted ").append(interruptedexception).toString());
          goto _L5
    }
}
