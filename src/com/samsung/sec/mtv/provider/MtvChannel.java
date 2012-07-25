// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.types.MtvOneSegChannel;

public class MtvChannel
{

    public MtvChannel(int i, int j, int k, int l, String s, int i1, int j1)
    {
        mUriId = -1;
        mServiceID1 = -1;
        mServiceID2 = -1;
        mVirtualNum = i;
        mPhysicalNum = j;
        mFavorite = k;
        mAvailable = l;
        mChannelName = s;
        mSlot = i1;
        mUriId = j1;
    }

    public MtvChannel(int i, int j, int k, int l, String s, int i1, int j1, 
            int k1)
    {
        mUriId = -1;
        mServiceID1 = -1;
        mServiceID2 = -1;
        mVirtualNum = i;
        mPhysicalNum = j;
        mFavorite = k;
        mAvailable = l;
        mChannelName = s;
        mSlot = i1;
        mServiceID1 = j1;
        mServiceID2 = k1;
    }

    public MtvChannel(int i, int j, int k, int l, String s, int i1, int j1, 
            int k1, int l1)
    {
        mUriId = -1;
        mServiceID1 = -1;
        mServiceID2 = -1;
        mVirtualNum = i;
        mPhysicalNum = j;
        mFavorite = k;
        mAvailable = l;
        mChannelName = s;
        mSlot = i1;
        mServiceID1 = k1;
        mServiceID2 = l1;
        mUriId = j1;
    }

    public MtvChannel(MtvOneSegChannel mtvonesegchannel, int i)
    {
        mUriId = -1;
        mServiceID1 = -1;
        mServiceID2 = -1;
        if(mtvonesegchannel != null)
        {
            mVirtualNum = mtvonesegchannel.getRemoteKeyID();
            mPhysicalNum = mtvonesegchannel.getServID();
            mFavorite = -1;
            mAvailable = i;
            mChannelName = mtvonesegchannel.getServName();
            mSlot = -1;
        } else
        {
            mVirtualNum = -1;
            mPhysicalNum = -1;
            mFavorite = -1;
            mAvailable = 0;
            mChannelName = "";
            mSlot = -1;
        }
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("MtvChannel").append("[virtual=").append(mVirtualNum).toString();
        String s1 = (new StringBuilder()).append(s).append(", physical=").append(mPhysicalNum).toString();
        String s2 = (new StringBuilder()).append(s1).append(", favorite=").append(mFavorite).toString();
        String s3 = (new StringBuilder()).append(s2).append(", available=").append(mAvailable).toString();
        String s4 = (new StringBuilder()).append(s3).append(", name=").append(mChannelName).toString();
        String s5 = (new StringBuilder()).append(s4).append(", slot=").append(mSlot).toString();
        return (new StringBuilder()).append(s5).append(", service ID=").append(mServiceID1).append("-").append(mServiceID2).append("]").toString();
    }

    public final int mAvailable;
    public final String mChannelName;
    public final int mFavorite;
    public final int mPhysicalNum;
    public int mServiceID1;
    public int mServiceID2;
    public final int mSlot;
    public int mUriId;
    public final int mVirtualNum;
}
