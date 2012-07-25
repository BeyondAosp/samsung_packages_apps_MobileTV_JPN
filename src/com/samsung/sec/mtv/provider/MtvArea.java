// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;


public class MtvArea
{

    public MtvArea(int i, String s)
    {
        mUriId = -1;
        mAreaId = i;
        if(s != null)
            mAreaName = s;
        else
            mAreaName = "";
    }

    public MtvArea(int i, String s, int j)
    {
        mUriId = -1;
        mAreaId = i;
        mUriId = j;
        if(s != null)
            mAreaName = s;
        else
            mAreaName = "";
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("MtvArea").append("[areaId=").append(mAreaId).toString();
        return (new StringBuilder()).append(s).append(", area=").append(mAreaName).append("]").toString();
    }

    public final int mAreaId;
    public final String mAreaName;
    public int mUriId;
}
