// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
import java.text.DecimalFormat;

public class MtvUtilMemoryStatus
{

    public MtvUtilMemoryStatus()
    {
    }

    public static long ConvertByteToTime(long l)
    {
        long l2;
        if(l < 0x204000L)
        {
            MtvUtilDebug.Low("MtvUtilMemoryStatus", (new StringBuilder()).append("ConvertByteToTime: avail_byte is less :").append(l).toString());
            l2 = 0L;
        } else
        {
            long l1 = (l - 0x204000L) / 49152L;
            MtvUtilDebug.Low("MtvUtilMemoryStatus", (new StringBuilder()).append("ConvertByteToTime: avail_byte : ").append(l).append("  recordable_time: ").append(l1).toString());
            l2 = l1;
        }
        return l2;
    }

    public static String formatSize(long l)
    {
        double d = l;
        String s;
        if((double)l >= 1024D)
        {
            String s1 = "Kbytes";
            double d1 = d / 1024D;
            if((double)l >= 1024D)
            {
                s1 = "Mbytes";
                d1 /= 1024D;
                if((double)l >= 1024D)
                {
                    s1 = "Gbytes";
                    d1 /= 1024D;
                }
            }
            s = (new StringBuilder()).append((new DecimalFormat("###,###.##")).format(d1)).append(s1).toString();
        } else
        {
            s = new String("0.0Kbytes");
        }
        return s;
    }

    public static long getAvailableExternalMemorySize()
    {
        long l;
        if(isExternalMemoryAvailable())
        {
            StatFs statfs = new StatFs((new File(mExternalStorageSdPath)).getPath());
            l = (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
        } else
        {
            l = -1L;
        }
        return l;
    }

    public static long getAvailableInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    public static boolean isExternalMemoryAvailable()
    {
        boolean flag;
        mExternalStorageSdPath = new String();
        flag = false;
        if(mContext != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.High("MtvUtilMemoryStatus", "isExternalMemoryAvailable: mContext null returning false");
        flag = false;
_L4:
        return flag;
_L2:
        StorageManager storagemanager = (StorageManager)mContext.getSystemService("storage");
        StorageVolume astoragevolume[] = storagemanager.getVolumeList();
        int i = 0;
        do
        {
            if(i < astoragevolume.length)
            {
label0:
                {
                    StorageVolume storagevolume = astoragevolume[i];
                    if(!"sd".equals(storagevolume.getSubSystem()) || !storagevolume.isRemovable())
                        break label0;
                    mExternalStorageSdPath = storagevolume.getPath();
                    if(storagemanager.getVolumeState(mExternalStorageSdPath).equals("mounted"))
                        flag = true;
                    else
                        flag = false;
                }
            }
            if(true)
                continue;
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isInternalMemoryAvailable()
    {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void setAppComponents(Context context)
    {
        mContext = context;
    }

    private static Context mContext;
    private static String mExternalStorageSdPath;
}
