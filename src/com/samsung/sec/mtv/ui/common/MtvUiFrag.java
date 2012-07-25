// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Fragment;
import android.util.Log;

public abstract class MtvUiFrag extends Fragment
{
    public static interface IMtvFragEventListener
    {

        public abstract void onFragEvent(int i, Object obj);
    }


    public MtvUiFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiFrag;-><init>()V");
        super();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiFrag;->onUpdate(ILjava/lang/Object;)V");
    }
}
