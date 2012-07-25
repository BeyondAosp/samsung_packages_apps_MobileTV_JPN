// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.player;

import android.broadcast.helper.MtvURI;
import android.broadcast.helper.types.MtvOneSegTVLink;
import android.content.Context;
import com.samsung.sec.mtv.app.context.MtvAppPlaybackContext;

public interface IMtvAppPlayerOneSeg
{

    public abstract boolean cancelRecord(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean cancelScanChannels(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean captFrame(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean create(MtvAppPlaybackContext mtvappplaybackcontext, Context context);

    public abstract void delete(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean deleteTVFile(MtvAppPlaybackContext mtvappplaybackcontext, int i);

    public abstract boolean open(MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri);

    public abstract boolean open(MtvAppPlaybackContext mtvappplaybackcontext, MtvURI mtvuri, Context context);

    public abstract boolean pause(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean reposition(MtvAppPlaybackContext mtvappplaybackcontext, int i);

    public abstract boolean resume(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean scanChannels(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean startRecord(MtvAppPlaybackContext mtvappplaybackcontext, String s, int i);

    public abstract boolean startTVLink(MtvAppPlaybackContext mtvappplaybackcontext, MtvOneSegTVLink mtvonesegtvlink, Context context);

    public abstract boolean stopRecord(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean stopTVLink(MtvAppPlaybackContext mtvappplaybackcontext);

    public abstract boolean trickmode(MtvAppPlaybackContext mtvappplaybackcontext, int i, int j);
}
