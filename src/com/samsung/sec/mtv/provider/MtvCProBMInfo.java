// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.util.Log;
import java.util.Date;

public class MtvCProBMInfo
{

    public MtvCProBMInfo()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;-><init>()V");
        super();
        id = -1;
        CproBMType = -1;
        title = null;
        dstURI = null;
        originalNetworkID = -1;
        transportStreamID = -1;
        serviceID = -1;
        affiliationID = new int[6];
        outline = null;
        reserved = null;
    }

    public int[] getAffiliationID()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getAffiliationID()[I");
        return affiliationID;
    }

    public int getCproBMType()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getCproBMType()I");
        return CproBMType;
    }

    public String getDstURI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getDstURI()Ljava/lang/String;");
        return dstURI;
    }

    public int getID()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getID()I");
        return id;
    }

    public int getOriginalNetworkID()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getOriginalNetworkID()I");
        return originalNetworkID;
    }

    public String getOutline()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getOutline()Ljava/lang/String;");
        return outline;
    }

    public int getServiceID()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getServiceID()I");
        return serviceID;
    }

    public String getTitle()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getTitle()Ljava/lang/String;");
        return title;
    }

    public int getTransportStreamID()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getTransportStreamID()I");
        return transportStreamID;
    }

    public Date getValidDate()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;->getValidDate()Ljava/util/Date;");
        return validdate;
    }

    public int CproBMType;
    public int affiliationID[];
    public String dstURI;
    public int id;
    public int index;
    public int originalNetworkID;
    public String outline;
    public String reserved;
    public int serviceID;
    public String title;
    public int transportStreamID;
    public Date validdate;
}
