// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class MtvUtilConfigSetting
{

    public MtvUtilConfigSetting()
    {
        iTsFileSimul = false;
        iTsCapture = false;
    }

    public static float convertDpToPixel(float f, Context context)
    {
        return f * ((float)context.getResources().getDisplayMetrics().densityDpi / 160F);
    }

    public static boolean EPGAPP_ENABLED = true;
    public boolean iTsCapture;
    public boolean iTsFileSimul;

}
