// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class MtvUtilConfigSetting
{

    public MtvUtilConfigSetting()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSetting;-><init>()V");
        super();
        iTsFileSimul = false;
        iTsCapture = false;
    }

    public static float convertDpToPixel(float f, Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSetting;->convertDpToPixel(FLandroid/content/Context;)F");
        return f * ((float)context.getResources().getDisplayMetrics().densityDpi / 160F);
    }

    public static boolean EPGAPP_ENABLED = true;
    public boolean iTsCapture;
    public boolean iTsFileSimul;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSetting;-><clinit>()V");
    }
}
