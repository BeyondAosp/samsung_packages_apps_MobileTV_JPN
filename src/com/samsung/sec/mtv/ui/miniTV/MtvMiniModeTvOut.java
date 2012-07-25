// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.miniTV;

import android.os.*;
import android.util.Log;

public class MtvMiniModeTvOut
{

    public MtvMiniModeTvOut()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeTvOut;-><init>()V");
        super();
    }

    public static void resumeTvOut()
    {
        ITvoutService itvoutservice;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeTvOut;->resumeTvOut()V");
        Log.d("MtvUtilTvOut", "resumeTvOut() - Tv OUT Resume!!! ");
        itvoutservice = android.os.ITvoutService.Stub.asInterface(ServiceManager.getService("tvoutservice"));
        if(itvoutservice != null) goto _L2; else goto _L1
_L1:
        Log.e("MtvUtilTvOut", "resumeTvOut() - TvOutService Not running");
_L4:
        return;
_L2:
        try
        {
            Log.i("MtvUtilTvOut", (new StringBuilder()).append("TvoutGetStatus = ").append(itvoutservice.TvoutGetStatus()).toString());
            Log.i("MtvUtilTvOut", (new StringBuilder()).append("TvoutGetSuspendStatus = ").append(itvoutservice.TvoutGetSuspendStatus()).toString());
            if(itvoutservice.TvoutGetSuspendStatus())
            {
                Log.i("MtvUtilTvOut", "TvoutGetSuspendStatus = true, making it false");
                itvoutservice.TvoutSetSuspendStatus(false);
            }
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MtvUtilTvOut", "resumeTvOut() - Tv OUT Resume fail!!! ", remoteexception);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void suspendTvOut()
    {
        ITvoutService itvoutservice;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvMiniModeTvOut;->suspendTvOut()V");
        Log.d("MtvUtilTvOut", "suspendTvOut() - Tv OUT Suspend!!! ");
        itvoutservice = android.os.ITvoutService.Stub.asInterface(ServiceManager.getService("tvoutservice"));
        if(itvoutservice != null) goto _L2; else goto _L1
_L1:
        Log.e("MtvUtilTvOut", "suspendTvOut() - TvOutService Not running");
_L3:
        return;
_L2:
        int i = 1;
_L4:
        if(i > 5)
            break MISSING_BLOCK_LABEL_63;
        if(!itvoutservice.TvoutGetStatus())
            break MISSING_BLOCK_LABEL_203;
        Log.e("MtvUtilTvOut", "EnableHDMISubtitleOnTV() - break!!");
        Log.i("MtvUtilTvOut", (new StringBuilder()).append("TvoutGetStatus = ").append(itvoutservice.TvoutGetStatus()).toString());
        Log.i("MtvUtilTvOut", (new StringBuilder()).append("TvoutGetSuspendStatus = ").append(itvoutservice.TvoutGetSuspendStatus()).toString());
        if(itvoutservice.TvoutGetStatus() && !itvoutservice.TvoutGetSuspendStatus())
        {
            Log.i("MtvUtilTvOut", "TvoutGetSuspendStatus = false, making it true");
            itvoutservice.TvoutSetSuspendStatus(true);
        }
        if(itvoutservice.TvoutGetSuspendStatus())
        {
            Log.i("MtvUtilTvOut", "TvoutPostSuspend, posting a string");
            itvoutservice.TvoutPostSuspend("HDMI not available while application is running. Application will display on phone only");
        }
          goto _L3
        RemoteException remoteexception;
        remoteexception;
        Log.e("MtvUtilTvOut", "suspendTvOut() - Tv OUT Suspend fail!!! ", remoteexception);
          goto _L3
        Log.e("MtvUtilTvOut", "EnableHDMISubtitleOnTV() - wait");
        Thread.sleep(100L);
_L5:
        i++;
          goto _L4
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L5
    }
}
