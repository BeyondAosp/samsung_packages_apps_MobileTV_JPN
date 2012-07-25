// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.util.Log;

public class MtvFileDBException extends RuntimeException
{

    public MtvFileDBException()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvFileDBException;-><init>()V");
        super();
    }

    public MtvFileDBException(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvFileDBException;-><init>(Ljava/lang/String;)V");
        super(s);
    }

    private static final long serialVersionUID = 1L;
}
