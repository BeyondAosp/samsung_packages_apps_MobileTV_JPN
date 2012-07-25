// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;


public interface IMtvAppPlaybackStateListener
{

    public abstract void onPlayerNotification(int i, int j, int k);

    public abstract void onStateChanged(MtvAppPlaybackState.State state, MtvAppPlaybackState.State state1);
}
