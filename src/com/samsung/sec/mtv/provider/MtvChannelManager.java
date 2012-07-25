// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import com.samsung.sec.mtv.utility.MtvAreaStationInfo;
import com.samsung.sec.mtv.utility.MtvPreferences;
import java.util.HashMap;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvChannel, MtvArea, MtvAreaManager

public class MtvChannelManager
    implements BaseColumns
{

    private MtvChannelManager()
    {
    }

    public static MtvChannel builder(Cursor cursor)
    {
        return new MtvChannel(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(0), cursor.getInt(7), cursor.getInt(8));
    }

    public static void deleteDB(Context context, Uri uri)
    {
        if(uri == null)
            uri = CONTENT_URI;
        String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        context.getContentResolver().delete(uri, s, null);
    }

    public static MtvChannel findByNamePCh(Context context, String s, int i)
    {
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s1 = (new StringBuilder()).append("ch_name=? and ch_pch=").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        MtvChannel mtvchannel = null;
        ContentResolver contentresolver = context.getContentResolver();
        String as1[] = new String[1];
        as1[0] = s;
        Cursor cursor = contentresolver.query(uri, as, s1, as1, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
    }

    public static MtvChannel findByPChannel(Context context, int i)
    {
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s = (new StringBuilder()).append("ch_pch=").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        MtvChannel mtvchannel = null;
        Cursor cursor = context.getContentResolver().query(uri, as, s, null, "ch_vch ASC");
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
    }

    public static MtvChannel findByServiceId(Context context, int i)
    {
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s = (new StringBuilder()).append("ch_svcid1<=").append(i).append(" and ").append("ch_svcid2").append(">=").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        MtvChannel mtvchannel = null;
        Cursor cursor = context.getContentResolver().query(uri, as, s, null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
    }

    public static MtvChannel findByVChannel(Context context, int i)
    {
        Uri uri;
        String as[];
        String s;
        MtvChannel mtvchannel;
        uri = CONTENT_URI;
        as = PROJECTION;
        s = (new StringBuilder()).append("ch_vch=").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        mtvchannel = null;
        Cursor cursor1 = context.getContentResolver().query(uri, as, s, null, null);
        Cursor cursor = cursor1;
_L2:
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
        Exception exception;
        exception;
        exception.printStackTrace();
        cursor = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static MtvChannel[] getAllAvailableChannels(Context context)
    {
        String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, null);
        MtvChannel amtvchannel[] = new MtvChannel[cursor.getCount()];
        if(cursor != null)
        {
            for(int i = 0; i < cursor.getCount(); i++)
            {
                cursor.moveToNext();
                amtvchannel[i] = builder(cursor);
            }

            cursor.close();
        }
        return amtvchannel;
    }

    protected static ContentValues getContentValues(MtvChannel mtvchannel)
    {
        ContentValues contentvalues = new ContentValues();
        if(mtvchannel.mVirtualNum != -1)
            contentvalues.put("ch_vch", Integer.valueOf(mtvchannel.mVirtualNum));
        if(mtvchannel.mPhysicalNum != -1)
            contentvalues.put("ch_pch", Integer.valueOf(mtvchannel.mPhysicalNum));
        if(mtvchannel.mFavorite != -1)
            contentvalues.put("ch_fav", Integer.valueOf(mtvchannel.mFavorite));
        if(mtvchannel.mAvailable != -1)
            contentvalues.put("ch_avlb", Integer.valueOf(mtvchannel.mAvailable));
        if(mtvchannel.mChannelName != null)
            contentvalues.put("ch_name", mtvchannel.mChannelName);
        if(mtvchannel.mSlot != -1)
            contentvalues.put("ch_slot", Integer.valueOf(mtvchannel.mSlot));
        if(mtvchannel.mServiceID1 != -1)
            contentvalues.put("ch_svcid1", Integer.valueOf(mtvchannel.mServiceID1));
        if(mtvchannel.mServiceID2 != -1)
            contentvalues.put("ch_svcid2", Integer.valueOf(mtvchannel.mServiceID2));
        return contentvalues;
    }

    public static int getCount(Context context)
    {
        return getCount(context, null, null);
    }

    private static int getCount(Context context, String s, String as[])
    {
        Uri uri = Uri.withAppendedPath(CONTENT_URI, "count");
        int i = 0;
        String s1;
        String s2;
        Cursor cursor;
        if(s == null)
            s1 = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        else
            s1 = (new StringBuilder()).append(s).append(" and ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        s2 = (new StringBuilder()).append(s1).append(" and ch_pch<>-1").toString();
        cursor = context.getContentResolver().query(uri, null, s2, as, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                i = cursor.getInt(0);
            }
            cursor.close();
        }
        MtvUtilDebug.Low("MtvChannelManager", (new StringBuilder()).append("getCount sql: ").append(s2).append(" result=").append(i).toString());
        return i;
    }

    public static MtvChannel getFirst(Context context)
    {
        String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, "ch_vch ASC");
        MtvChannel mtvchannel = null;
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
    }

    public static MtvChannel getFirstOnAir(Context context)
    {
        String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        String s2 = (new StringBuilder()).append(s1).append(" and ch_avlb=1").toString();
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, s2, null, "ch_vch ASC");
        MtvChannel mtvchannel = null;
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        if(mtvchannel == null)
            mtvchannel = getFirst(context);
        return mtvchannel;
    }

    public static MtvChannel getLast(Context context)
    {
        String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, "ch_vch ASC");
        MtvChannel mtvchannel = null;
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToLast();
                mtvchannel = builder(cursor);
            }
            cursor.close();
        }
        return mtvchannel;
    }

    public static MtvChannel getNext(Context context, int i)
    {
        Cursor cursor;
        MtvChannel mtvchannel;
        String s = (new StringBuilder()).append("ch_vch>").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, null);
        mtvchannel = null;
        if(cursor == null) goto _L2; else goto _L1
_L1:
        if(cursor.getCount() <= 0) goto _L4; else goto _L3
_L3:
        if(cursor.moveToFirst())
            mtvchannel = builder(cursor);
_L6:
        cursor.close();
_L2:
        return mtvchannel;
_L4:
        if(getCount(context) > 1)
            mtvchannel = getFirst(context);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static MtvChannel getPrevious(Context context, int i)
    {
        Cursor cursor;
        MtvChannel mtvchannel;
        String s = (new StringBuilder()).append("ch_vch<").append(i).append(" and ").append("ch_slot").append("=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
        String s1 = (new StringBuilder()).append(s).append(" and ch_pch<>-1").toString();
        cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, null);
        mtvchannel = null;
        if(cursor == null) goto _L2; else goto _L1
_L1:
        if(cursor.getCount() <= 0) goto _L4; else goto _L3
_L3:
        if(cursor.moveToLast())
            mtvchannel = builder(cursor);
_L6:
        cursor.close();
_L2:
        return mtvchannel;
_L4:
        if(getCount(context) > 1)
            mtvchannel = getLast(context);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static Uri getUri(int i)
    {
        Uri uri;
        if(i != -1)
            uri = ContentUris.withAppendedId(CONTENT_URI, i);
        else
            uri = null;
        return uri;
    }

    public static void setDefaultAreaNChannel(Context context, int i, int j, String s)
    {
        if(i == -1)
            i = (new MtvPreferences(context)).getCurrentSlot();
        else
            (new MtvPreferences(context)).setCurrentSlot(i);
        if(j != -1 && s != null)
        {
            MtvArea mtvarea = new MtvArea(j, s);
            ContentValues contentvalues = MtvAreaManager.getContentValues(mtvarea);
            String s2 = (new StringBuilder()).append("area_id=").append(mtvarea.mAreaId).toString();
            context.getContentResolver().update(MtvAreaManager.CONTENT_URI, contentvalues, s2, null);
        }
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvChannelManager", (new StringBuilder()).append("localId=").append(j).toString());
        if(j >= 0)
        {
            SQLiteDatabase sqlitedatabase = MtvProvider.DatabaseHelper.getInstance(context).getWritableDatabase();
            for(int k = 0; k < MtvAreaStationInfo.AREA_STATION[j - 1].length / 5; k++)
            {
                int l = k + 1;
                int i1 = Integer.parseInt(MtvAreaStationInfo.AREA_STATION[j - 1][1 + k * 5]);
                String s1 = MtvAreaStationInfo.getStringByResourceName(context, MtvAreaStationInfo.AREA_STATION[j - 1][k * 5]);
                int j1 = Integer.parseInt(MtvAreaStationInfo.AREA_STATION[j - 1][3 + k * 5], 16);
                int k1 = Integer.parseInt(MtvAreaStationInfo.AREA_STATION[j - 1][4 + k * 5], 16);
                sqlitedatabase.insert("channels", null, getContentValues(new MtvChannel(l, i1, 0, 0, s1, i, j1, k1)));
            }

        }
    }

    public static void update2Default(Context context, Uri uri)
    {
        if(uri != null)
        {
            String s = (new StringBuilder()).append("ch_slot=").append((new MtvPreferences(context)).getCurrentSlot()).toString();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("ch_pch", "-1");
            contentvalues.put("ch_fav", "0");
            contentvalues.put("ch_avlb", "0");
            contentvalues.put("ch_name", (new StringBuilder()).append("<").append(context.getResources().getString(0x7f0700ed)).append(">").toString());
            context.getContentResolver().update(uri, contentvalues, s, null);
        }
    }

    public static void updateOrInsert(Context context, MtvChannel mtvchannel)
    {
        MtvChannel mtvchannel1;
        MtvChannel mtvchannel2;
        mtvchannel1 = new MtvChannel(mtvchannel.mVirtualNum, mtvchannel.mPhysicalNum, mtvchannel.mFavorite, mtvchannel.mAvailable, mtvchannel.mChannelName, (new MtvPreferences(context)).getCurrentSlot(), mtvchannel.mUriId);
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvChannelManager", (new StringBuilder()).append("Update or insert CHANNEL ").append(mtvchannel1.toString()).toString());
        mtvchannel2 = findByVChannel(context, mtvchannel1.mVirtualNum);
        if(mtvchannel2 != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvChannelManager", "Update or insert CHANNEL : not present ");
_L4:
        return;
_L2:
        if(mtvchannel2.mPhysicalNum == -1 || mtvchannel2.mAvailable == 0)
            context.getContentResolver().update(getUri(mtvchannel2.mUriId), getContentValues(mtvchannel1), null, null);
        else
        if(mtvchannel2.mPhysicalNum == mtvchannel1.mPhysicalNum && mtvchannel2.mChannelName.trim().equals(mtvchannel1.mChannelName.trim()))
            MtvUtilDebug.Low("MtvChannelManager", "Update or insert CHANNEL : same present ");
        else
        if(findByNamePCh(context, mtvchannel1.mChannelName, mtvchannel1.mPhysicalNum) == null)
        {
            MtvChannel mtvchannel3 = findByPChannel(context, -1);
            if(mtvchannel3 != null)
            {
                MtvChannel mtvchannel4 = new MtvChannel(mtvchannel3.mVirtualNum, mtvchannel1.mPhysicalNum, mtvchannel1.mFavorite, mtvchannel1.mAvailable, mtvchannel1.mChannelName, mtvchannel1.mSlot, mtvchannel1.mUriId);
                context.getContentResolver().update(getUri(mtvchannel3.mUriId), getContentValues(mtvchannel4), null, null);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.sec.mtv/channels");
    public static final String PROJECTION[];
    protected static HashMap PROJECTION_MAP;

    static 
    {
        String as[] = new String[9];
        as[0] = "_id";
        as[1] = "ch_vch";
        as[2] = "ch_pch";
        as[3] = "ch_fav";
        as[4] = "ch_avlb";
        as[5] = "ch_name";
        as[6] = "ch_slot";
        as[7] = "ch_svcid1";
        as[8] = "ch_svcid2";
        PROJECTION = as;
        PROJECTION_MAP = new HashMap();
        PROJECTION_MAP.put("_id", "_id");
        PROJECTION_MAP.put("ch_vch", "ch_vch");
        PROJECTION_MAP.put("ch_pch", "ch_pch");
        PROJECTION_MAP.put("ch_fav", "ch_fav");
        PROJECTION_MAP.put("ch_avlb", "ch_avlb");
        PROJECTION_MAP.put("ch_name", "ch_name");
        PROJECTION_MAP.put("ch_slot", "ch_slot");
        PROJECTION_MAP.put("ch_svcid1", "ch_svcid1");
        PROJECTION_MAP.put("ch_svcid2", "ch_svcid2");
    }
}
