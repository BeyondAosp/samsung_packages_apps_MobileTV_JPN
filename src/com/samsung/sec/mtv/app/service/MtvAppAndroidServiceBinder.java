// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.service;

import android.os.Binder;
import java.lang.ref.WeakReference;

public class MtvAppAndroidServiceBinder extends Binder
{

    public MtvAppAndroidServiceBinder(Object obj)
    {
        mService = new WeakReference(obj);
    }

    public Object getService()
    {
        return mService.get();
    }

    private WeakReference mService;
}
