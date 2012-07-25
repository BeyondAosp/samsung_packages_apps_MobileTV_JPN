// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.types.MtvOneSegProgram;
import android.util.Log;
import java.util.Date;

public class MtvProgram
{

    public MtvProgram(int i, int j, int k, long l, long l1, 
            String s, String s1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgram;-><init>(IIIJJLjava/lang/String;Ljava/lang/String;)V");
        super();
        mUriId = -1;
        mPch = i;
        mVch = j;
        mLock = k;
        mTimeStart = l;
        mTimeEnd = l1;
        mPgmName = s;
        mPgmDetail = s1;
    }

    public MtvProgram(int i, int j, int k, long l, long l1, 
            String s, String s1, int i1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgram;-><init>(IIIJJLjava/lang/String;Ljava/lang/String;I)V");
        super();
        mUriId = -1;
        mPch = i;
        mVch = j;
        mLock = k;
        mTimeStart = l;
        mTimeEnd = l1;
        mPgmName = s;
        mPgmDetail = s1;
        mUriId = i1;
    }

    public MtvProgram(MtvOneSegProgram mtvonesegprogram, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgram;-><init>(Landroid/broadcast/helper/types/MtvOneSegProgram;I)V");
        super();
        mUriId = -1;
        mPch = mtvonesegprogram.getServiceID();
        mVch = i;
        mLock = -1;
        mTimeStart = mtvonesegprogram.getStartTime().getTime();
        mTimeEnd = mtvonesegprogram.getEndTime().getTime();
        mPgmName = mtvonesegprogram.getProgName();
        mPgmDetail = mtvonesegprogram.getProgDesc();
    }

    public String toString()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgram;->toString()Ljava/lang/String;");
        String s = (new StringBuilder()).append("MtvProgram").append("[virtual=").append(mVch).toString();
        String s1 = (new StringBuilder()).append(s).append(", physical=").append(mPch).toString();
        String s2 = (new StringBuilder()).append(s1).append(", pl=").append(mLock).toString();
        String s3 = (new StringBuilder()).append(s2).append(", start=").append(mTimeStart).toString();
        String s4 = (new StringBuilder()).append(s3).append(", end=").append(mTimeEnd).toString();
        return (new StringBuilder()).append(s4).append(", name=").append(mPgmName).append("]").toString();
    }

    public final int mLock;
    public final int mPch;
    public final String mPgmDetail;
    public final String mPgmName;
    public final long mTimeEnd;
    public final long mTimeStart;
    public int mUriId;
    public final int mVch;
}
