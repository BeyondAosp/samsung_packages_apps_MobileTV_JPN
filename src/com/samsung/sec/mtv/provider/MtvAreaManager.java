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
//            MtvArea

public class MtvAreaManager
    implements BaseColumns
{

    private MtvAreaManager()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;-><init>()V");
        super();
    }

    private static MtvArea builder(Cursor cursor)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->builder(Landroid/database/Cursor;)Lcom/samsung/sec/mtv/provider/MtvArea;");
        return new MtvArea(cursor.getInt(1), cursor.getString(2), cursor.getInt(0));
    }

    public static MtvArea find(Context context, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->find(Landroid/content/Context;I)Lcom/samsung/sec/mtv/provider/MtvArea;");
        Uri uri = CONTENT_URI;
        String s = (new StringBuilder()).append("_id=").append(i + 1).toString();
        MtvArea mtvarea = null;
        Cursor cursor = context.getContentResolver().query(uri, null, s, null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvarea = builder(cursor);
            }
            cursor.close();
        }
        return mtvarea;
    }

    public static MtvArea[] getAllAreas(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->getAllAreas(Landroid/content/Context;)[Lcom/samsung/sec/mtv/provider/MtvArea;");
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        MtvArea amtvarea[] = new MtvArea[10];
        if(cursor != null)
        {
            for(int i = 0; i < cursor.getCount(); i++)
            {
                cursor.moveToNext();
                amtvarea[i] = builder(cursor);
            }

            cursor.close();
        }
        return amtvarea;
    }

    protected static ContentValues getContentValues(MtvArea mtvarea)
    {
        ContentValues contentvalues;
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->getContentValues(Lcom/samsung/sec/mtv/provider/MtvArea;)Landroid/content/ContentValues;");
        contentvalues = new ContentValues();
        if(mtvarea != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvAreaManager", "getContentValues : MtvArea is NULL");
_L4:
        return contentvalues;
_L2:
        contentvalues.put("area_id", Integer.valueOf(mtvarea.mAreaId));
        if(mtvarea.mAreaName != null)
            contentvalues.put("area_name", mtvarea.mAreaName);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getCount(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->getCount(Landroid/content/Context;)I");
        Uri uri = Uri.withAppendedPath(CONTENT_URI, "count");
        int i = 0;
        Cursor cursor = context.getContentResolver().query(uri, null, "area_id<>-1", null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                i = cursor.getInt(0);
            }
            cursor.close();
        }
        return i;
    }

    public static void update(Context context, int i, MtvArea mtvarea)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->update(Landroid/content/Context;ILcom/samsung/sec/mtv/provider/MtvArea;)V");
        context.getContentResolver().update(CONTENT_URI, getContentValues(mtvarea), (new StringBuilder()).append("_id=").append(i + 1).toString(), null);
    }

    public static void update2Default(Context context, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;->update2Default(Landroid/content/Context;I)V");
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("area_id", "-1");
        contentvalues.put("area_name", "Empty");
        context.getContentResolver().update(CONTENT_URI, contentvalues, (new StringBuilder()).append("_id=").append(i + 1).toString(), null);
    }

    public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.sec.mtv/areas");
    public static final String PROJECTION[];
    protected static HashMap PROJECTION_MAP;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvAreaManager;-><clinit>()V");
        String as[] = new String[3];
        as[0] = "_id";
        as[1] = "area_id";
        as[2] = "area_name";
        PROJECTION = as;
        PROJECTION_MAP = new HashMap();
        PROJECTION_MAP.put("_id", "_id");
        PROJECTION_MAP.put("area_id", "area_id");
        PROJECTION_MAP.put("area_name", "area_name");
    }
}
