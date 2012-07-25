// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.HashMap;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvProgram

public class MtvProgramManager
    implements BaseColumns
{

    private MtvProgramManager()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;-><init>()V");
        super();
    }

    private static MtvProgram builder(Cursor cursor)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->builder(Landroid/database/Cursor;)Lcom/samsung/sec/mtv/provider/MtvProgram;");
        return new MtvProgram(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getLong(4), cursor.getLong(5), cursor.getString(6), cursor.getString(7), cursor.getInt(0));
    }

    public static void delete(Context context, Uri uri)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->delete(Landroid/content/Context;Landroid/net/Uri;)V");
        if(uri == null)
            uri = CONTENT_URI;
        context.getContentResolver().delete(uri, null, null);
    }

    public static void deletePChannelPrograms(Context context, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->deletePChannelPrograms(Landroid/content/Context;I)V");
        String s = (new StringBuilder()).append("epg_pch=").append(i).toString();
        context.getContentResolver().delete(CONTENT_URI, s, null);
    }

    public static MtvProgram findByPChannel(Context context, long l, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->findByPChannel(Landroid/content/Context;JI)Lcom/samsung/sec/mtv/provider/MtvProgram;");
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s = (new StringBuilder()).append("epg_stime=").append(l).toString();
        String s1 = (new StringBuilder()).append(s).append(" AND epg_pch=").append(i).toString();
        MtvProgram mtvprogram = null;
        Cursor cursor = context.getContentResolver().query(uri, as, s1, null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvprogram = builder(cursor);
            }
            cursor.close();
        }
        return mtvprogram;
    }

    public static MtvProgram[] findProgramsByPChannel(Context context, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->findProgramsByPChannel(Landroid/content/Context;I)[Lcom/samsung/sec/mtv/provider/MtvProgram;");
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s = (new StringBuilder()).append("epg_pch=").append(i).toString();
        MtvProgram amtvprogram[] = null;
        Cursor cursor = context.getContentResolver().query(uri, as, s, null, null);
        if(cursor != null)
        {
            int j = cursor.getCount();
            if(j > 0)
            {
                amtvprogram = new MtvProgram[j];
                cursor.moveToFirst();
                for(int k = 0; k < cursor.getCount(); k++)
                {
                    amtvprogram[k] = builder(cursor);
                    cursor.moveToNext();
                }

            }
            cursor.close();
        }
        return amtvprogram;
    }

    protected static ContentValues getContentValues(MtvProgram mtvprogram)
    {
        ContentValues contentvalues;
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->getContentValues(Lcom/samsung/sec/mtv/provider/MtvProgram;)Landroid/content/ContentValues;");
        contentvalues = new ContentValues();
        if(mtvprogram != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvProgramManager", "getContentValues : MtvArea is NULL");
_L4:
        return contentvalues;
_L2:
        if(mtvprogram.mPch != -1)
            contentvalues.put("epg_pch", Integer.valueOf(mtvprogram.mPch));
        if(mtvprogram.mVch != -1)
            contentvalues.put("epg_vch", Integer.valueOf(mtvprogram.mVch));
        if(mtvprogram.mLock != -1)
            contentvalues.put("epg_plock", Integer.valueOf(mtvprogram.mLock));
        if(mtvprogram.mTimeStart != -1L)
            contentvalues.put("epg_stime", Long.valueOf(mtvprogram.mTimeStart));
        if(mtvprogram.mTimeEnd != -1L)
            contentvalues.put("epg_etime", Long.valueOf(mtvprogram.mTimeEnd));
        if(mtvprogram.mPgmName != null)
            contentvalues.put("epg_name", mtvprogram.mPgmName);
        if(mtvprogram.mPgmDetail != null)
            contentvalues.put("epg_detail", mtvprogram.mPgmDetail);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static MtvProgram getCurrentProgramByPhCh(Context context, int i, long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->getCurrentProgramByPhCh(Landroid/content/Context;IJ)Lcom/samsung/sec/mtv/provider/MtvProgram;");
        String s = (new StringBuilder()).append("epg_pch=").append(i).toString();
        String s1 = (new StringBuilder()).append(s).append(" AND epg_stime<").append(l).toString();
        MtvProgram mtvprogram = null;
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToLast();
                mtvprogram = builder(cursor);
            }
            cursor.close();
        }
        return mtvprogram;
    }

    public static Uri getUri(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->getUri(I)Landroid/net/Uri;");
        Uri uri;
        if(i != -1)
            uri = ContentUris.withAppendedId(CONTENT_URI, i);
        else
            uri = null;
        return uri;
    }

    public static void updateOrInsert(Context context, MtvProgram mtvprogram)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;->updateOrInsert(Landroid/content/Context;Lcom/samsung/sec/mtv/provider/MtvProgram;)V");
        Uri uri = getUri(mtvprogram.mUriId);
        if(uri == null)
        {
            MtvProgram mtvprogram1 = findByPChannel(context, mtvprogram.mTimeStart, mtvprogram.mPch);
            if(mtvprogram1 != null)
                uri = getUri(mtvprogram1.mUriId);
        }
        if(uri == null)
        {
            MtvUtilDebug.Low("MtvProgramManager", "update: insert channel");
            ContentValues contentvalues1 = getContentValues(mtvprogram);
            context.getContentResolver().insert(CONTENT_URI, contentvalues1);
        } else
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvProgramManager", (new StringBuilder()).append("update: update channel uri=").append(uri.toString()).toString());
            ContentValues contentvalues = getContentValues(mtvprogram);
            context.getContentResolver().update(uri, contentvalues, null, null);
        }
    }

    public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.sec.mtv/programs");
    public static final String PROJECTION[];
    protected static HashMap PROJECTION_MAP;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProgramManager;-><clinit>()V");
        String as[] = new String[8];
        as[0] = "_id";
        as[1] = "epg_pch";
        as[2] = "epg_vch";
        as[3] = "epg_plock";
        as[4] = "epg_stime";
        as[5] = "epg_etime";
        as[6] = "epg_name";
        as[7] = "epg_detail";
        PROJECTION = as;
        PROJECTION_MAP = new HashMap();
        PROJECTION_MAP.put("_id", "_id");
        PROJECTION_MAP.put("epg_pch", "epg_pch");
        PROJECTION_MAP.put("epg_vch", "epg_vch");
        PROJECTION_MAP.put("epg_plock", "epg_plock");
        PROJECTION_MAP.put("epg_stime", "epg_stime");
        PROJECTION_MAP.put("epg_etime", "epg_etime");
        PROJECTION_MAP.put("epg_name", "epg_name");
        PROJECTION_MAP.put("epg_detail", "epg_detail");
    }
}
