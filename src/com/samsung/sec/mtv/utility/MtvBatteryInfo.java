// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;

public class MtvBatteryInfo
{

    public MtvBatteryInfo()
    {
    }

    public static boolean getBatteryChargeStatus()
    {
        return mBatteryChargeStatus;
    }

    public static int getBatteryDrawableResourceID()
    {
        int ai[];
        int i;
        if(getBatteryChargeStatus())
            ai = STATUS_BAR_RSRC_BATTERY_LEVEL_CHARGING;
        else
            ai = STATUS_BAR_RSRC_BATTERY_LEVEL_NORMAL;
        if(mBatteryLevel < 0)
            mBatteryLevel = 0;
        if(mBatteryLevel <= 4)
            i = ai[0];
        else
        if(mBatteryLevel > 4 && mBatteryLevel <= 15)
            i = ai[1];
        else
        if(mBatteryLevel > 15 && mBatteryLevel <= 35)
            i = ai[2];
        else
        if(mBatteryLevel > 35 && mBatteryLevel <= 49)
            i = ai[3];
        else
        if(mBatteryLevel > 49 && mBatteryLevel <= 60)
            i = ai[4];
        else
        if(mBatteryLevel > 60 && mBatteryLevel <= 75)
            i = ai[5];
        else
        if(mBatteryLevel > 75 && mBatteryLevel <= 90)
            i = ai[6];
        else
        if(mBatteryLevel > 90 && mBatteryLevel <= 99)
            i = ai[7];
        else
        if(mBatteryLevel >= 100)
            i = ai[8];
        else
            i = mBatteryLevel;
        return i;
    }

    public static void setBatteryChargeStatus(boolean flag)
    {
        MtvUtilDebug.Low("MtvBatteryInfo", (new StringBuilder()).append("setmBatteryChargeStatus:").append(flag).toString());
        mBatteryChargeStatus = flag;
    }

    private static void setBatteryLevel(int i)
    {
        mBatteryLevel = i;
    }

    public static void updateBatteryLevel(int i, int j)
    {
        MtvUtilDebug.Low("MtvBatteryInfo", (new StringBuilder()).append("updateBatteryLevel: Level:").append(i).append("Scale: ").append(j).toString());
        setBatteryLevel((i * 100) / j);
    }

    private static final int STATUS_BAR_RSRC_BATTERY_LEVEL_CHARGING[];
    private static final int STATUS_BAR_RSRC_BATTERY_LEVEL_NORMAL[];
    private static MtvBatteryInfo latestBatteryInfo = null;
    private static boolean mBatteryChargeStatus = false;
    private static int mBatteryLevel = 0;

    static 
    {
        int ai[] = new int[9];
        ai[0] = 0x7f020114;
        ai[1] = 0x7f020116;
        ai[2] = 0x7f020117;
        ai[3] = 0x7f020118;
        ai[4] = 0x7f020119;
        ai[5] = 0x7f02011a;
        ai[6] = 0x7f02011b;
        ai[7] = 0x7f020115;
        ai[8] = 0x7f020115;
        STATUS_BAR_RSRC_BATTERY_LEVEL_NORMAL = ai;
        int ai1[] = new int[9];
        ai1[0] = 0x7f02011c;
        ai1[1] = 0x7f02011e;
        ai1[2] = 0x7f02011f;
        ai1[3] = 0x7f020120;
        ai1[4] = 0x7f020121;
        ai1[5] = 0x7f020122;
        ai1[6] = 0x7f020123;
        ai1[7] = 0x7f02011d;
        ai1[8] = 0x7f02011d;
        STATUS_BAR_RSRC_BATTERY_LEVEL_CHARGING = ai1;
    }
}
