// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.util.Log;
import java.io.Serializable;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvProgram

public class MtvReservation extends MtvProgram
    implements Serializable, Comparable
{

    public MtvReservation(int i, int j, int k, long l, long l1, 
            String s, String s1, int i1, int j1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvReservation;-><init>(IIIJJLjava/lang/String;Ljava/lang/String;II)V");
        super(i, j, k, l, l1, s, s1);
        mPgmType = i1;
        mPgmStatus = j1;
    }

    public MtvReservation(int i, int j, int k, long l, long l1, 
            String s, String s1, int i1, int j1, int k1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvReservation;-><init>(IIIJJLjava/lang/String;Ljava/lang/String;III)V");
        super(i, j, k, l, l1, s, s1, k1);
        mPgmType = i1;
        mPgmStatus = j1;
    }

    public MtvReservation(MtvProgram mtvprogram, int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvReservation;-><init>(Lcom/samsung/sec/mtv/provider/MtvProgram;II)V");
        super(mtvprogram.mPch, mtvprogram.mVch, mtvprogram.mLock, mtvprogram.mTimeStart, mtvprogram.mTimeEnd, mtvprogram.mPgmName, mtvprogram.mPgmDetail);
        mPgmType = i;
        mPgmStatus = j;
    }

    public int compareTo(MtvReservation mtvreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvReservation;->compareTo(Lcom/samsung/sec/mtv/provider/MtvReservation;)I");
        byte byte0;
        if(super.mTimeStart < ((MtvProgram) (mtvreservation)).mTimeStart)
            byte0 = -1;
        else
        if(super.mTimeStart > ((MtvProgram) (mtvreservation)).mTimeStart)
            byte0 = 1;
        else
            byte0 = 0;
        return byte0;
    }

    public volatile int compareTo(Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvReservation;->compareTo(Ljava/lang/Object;)I");
        return compareTo((MtvReservation)obj);
    }

    public int mPgmStatus;
    public int mPgmType;
}
