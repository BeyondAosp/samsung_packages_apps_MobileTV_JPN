// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.miniTV;

import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class MtvUiMiniModeSurface extends SurfaceView
{

    public MtvUiMiniModeSurface(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvUiMiniModeSurface;-><init>(Landroid/content/Context;)V");
        super(context);
        mVideoWidth = 0;
        mVideoHeight = 0;
    }

    public MtvUiMiniModeSurface(Context context, AttributeSet attributeset)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvUiMiniModeSurface;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V");
        super(context, attributeset);
        mVideoWidth = 0;
        mVideoHeight = 0;
    }

    public MtvUiMiniModeSurface(Context context, AttributeSet attributeset, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvUiMiniModeSurface;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V");
        super(context, attributeset, i);
        mVideoWidth = 0;
        mVideoHeight = 0;
    }

    protected void onMeasure(int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvUiMiniModeSurface;->onMeasure(II)V");
        int k = mVideoWidth;
        int l = mVideoHeight;
        int i1 = resolveAdjustedSize(k, i);
        int j1 = resolveAdjustedSize(l, j);
        if(k > 0 && l > 0)
        {
            i1 = getDefaultSize(mVideoWidth, i);
            j1 = getDefaultSize(mVideoHeight, j);
        }
        MtvUtilDebug.Low("MtvUiMiniModeSurface", (new StringBuilder()).append("setting size: ").append(i1).append('x').append(j1).toString());
        super.setMeasuredDimension(i1, j1);
    }

    public int resolveAdjustedSize(int i, int j)
    {
        int k;
        int l;
        int i1;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MtvUiMiniModeSurface;->resolveAdjustedSize(II)I");
        k = i;
        l = android.view.View.MeasureSpec.getMode(j);
        i1 = android.view.View.MeasureSpec.getSize(j);
        l;
        JVM INSTR lookupswitch 3: default 60
    //                   -2147483648: 69
    //                   0: 63
    //                   1073741824: 80;
           goto _L1 _L2 _L3 _L4
_L1:
        return k;
_L3:
        k = i;
        continue; /* Loop/switch isn't completed */
_L2:
        k = Math.min(i, i1);
        continue; /* Loop/switch isn't completed */
_L4:
        k = i1;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private int mVideoHeight;
    private int mVideoWidth;
}
