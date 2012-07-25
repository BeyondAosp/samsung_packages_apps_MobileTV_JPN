// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilSetReservationAlarm;
import java.util.HashMap;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvReservation

public class MtvReservationManager
    implements BaseColumns
{

    private MtvReservationManager()
    {
    }

    public static void UpdateStatus(Context context, MtvReservation mtvreservation, int i)
    {
        if(mtvreservation != null && i >= 0 && i <= 9) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.High("MtvReservationManager", "invalid newStatuspassed or Reservation null value passed !");
_L4:
        return;
_L2:
        int j = mtvreservation.mUriId;
        MtvPreferences mtvpreferences = new MtvPreferences(context);
        mtvreservation.mPgmStatus = i;
        mtvreservation.mUriId = -1;
        updateOrInsert(context, mtvreservation);
        if(i == 6)
        {
            MtvUtilDebug.Low("MtvReservationManager", "UpdateStatus():: reservation started");
            mtvpreferences.setReservationAlertID(j);
            mtvpreferences.setIsReservationProgram(true);
        } else
        if(i == 1)
        {
            MtvUtilDebug.Low("MtvReservationManager", "UpdateStatus():: reservation success");
            MtvUtilSetReservationAlarm.setReservationAlarm(context, mtvreservation.mTimeStart, false, false);
            mtvpreferences.setReservationAlertID(-1);
            mtvpreferences.setIsReservationProgram(false);
        } else
        if(i != 0)
        {
            MtvUtilDebug.Low("MtvReservationManager", "UpdateStatus():: reservation failure");
            if(i != 7)
                MtvUtilDebug.Low("MtvReservationManager", "newStatus != STATUS_CANCELLED");
            MtvUtilSetReservationAlarm.setReservationAlarm(context, mtvreservation.mTimeStart, false, false);
            mtvpreferences.setReservationAlertID(-1);
            mtvpreferences.setIsReservationProgram(false);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static MtvReservation builder(Cursor cursor)
    {
        return new MtvReservation(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getLong(4), cursor.getLong(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(0));
    }

    public static void delete(Context context, long l)
    {
        Uri uri = ContentUris.withAppendedId(CONTENT_URI, l);
        context.getContentResolver().delete(uri, null, null);
    }

    public static void delete(Context context, long l, Uri uri)
    {
        String s = (new StringBuilder()).append("epg_stime=").append(l).toString();
        context.getContentResolver().delete(CONTENT_URI, s, null);
    }

    public static MtvReservation find(Context context, int i)
    {
        MtvReservation mtvreservation = null;
        if(i >= 0)
        {
            Uri uri = CONTENT_URI;
            String as[] = PROJECTION;
            String s = (new StringBuilder()).append("_id=").append(i).toString();
            MtvReservation mtvreservation1 = null;
            Cursor cursor = context.getContentResolver().query(uri, as, s, null, null);
            if(cursor != null)
            {
                if(cursor.getCount() > 0)
                {
                    cursor.moveToFirst();
                    mtvreservation1 = builder(cursor);
                }
                cursor.close();
            }
            mtvreservation = mtvreservation1;
        }
        return mtvreservation;
    }

    public static transient MtvReservation find(Context context, long l, boolean aflag[])
    {
        Uri uri = CONTENT_URI;
        String as[] = PROJECTION;
        String s;
        MtvReservation mtvreservation;
        Cursor cursor;
        if(aflag != null && aflag.length > 0 && aflag[0])
            s = (new StringBuilder()).append("epg_etime=").append(l).toString();
        else
            s = (new StringBuilder()).append("epg_stime=").append(l).toString();
        mtvreservation = null;
        cursor = context.getContentResolver().query(uri, as, s, null, null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                mtvreservation = builder(cursor);
            }
            cursor.close();
        }
        return mtvreservation;
    }

    public static MtvReservation[] getAllReserves(Context context)
    {
        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        MtvReservation amtvreservation[] = null;
        if(cursor != null)
        {
            amtvreservation = new MtvReservation[cursor.getCount()];
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++)
            {
                amtvreservation[i] = builder(cursor);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return amtvreservation;
    }

    protected static ContentValues getContentValues(MtvReservation mtvreservation)
    {
        ContentValues contentvalues = new ContentValues();
        if(mtvreservation != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvReservationManager", "getContentValues : MtvArea is NULL");
_L4:
        return contentvalues;
_L2:
        if(mtvreservation.mPch != -1)
            contentvalues.put("epg_pch", Integer.valueOf(mtvreservation.mPch));
        if(mtvreservation.mVch != -1)
            contentvalues.put("epg_vch", Integer.valueOf(mtvreservation.mVch));
        if(mtvreservation.mLock != -1)
            contentvalues.put("epg_plock", Integer.valueOf(mtvreservation.mLock));
        if(mtvreservation.mTimeStart != -1L)
            contentvalues.put("epg_stime", Long.valueOf(mtvreservation.mTimeStart));
        if(mtvreservation.mTimeEnd != -1L)
            contentvalues.put("epg_etime", Long.valueOf(mtvreservation.mTimeEnd));
        if(mtvreservation.mPgmName != null)
            contentvalues.put("epg_name", mtvreservation.mPgmName);
        if(mtvreservation.mPgmDetail != null)
            contentvalues.put("epg_detail", mtvreservation.mPgmDetail);
        if(mtvreservation.mPgmType != -1)
            contentvalues.put("egp_type", Integer.valueOf(mtvreservation.mPgmType));
        if(mtvreservation.mPgmStatus != -1)
            contentvalues.put("egp_status", Integer.valueOf(mtvreservation.mPgmStatus));
        if(true) goto _L4; else goto _L3
_L3:
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

    public static boolean isImmediateReservation(Context context, long l)
    {
        Cursor cursor;
        String s = (new StringBuilder()).append("epg_stime>=").append(l).toString();
        String s1 = (new StringBuilder()).append(s).append(" AND epg_stime<").append(10000L + l).toString();
        cursor = context.getContentResolver().query(CONTENT_URI, null, s1, null, null);
        if(cursor == null) goto _L2; else goto _L1
_L1:
        if(cursor.getCount() <= 0) goto _L4; else goto _L3
_L3:
        boolean flag;
        MtvReservation amtvreservation[] = new MtvReservation[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            amtvreservation[i] = builder(cursor);
            MtvUtilDebug.Low("MtvReservationManager", amtvreservation[i].toString());
            cursor.moveToNext();
        }

        cursor.close();
        flag = true;
_L6:
        return flag;
_L4:
        cursor.close();
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void update(Context context, MtvReservation mtvreservation, int i)
    {
        Uri uri = null;
        MtvReservation mtvreservation1 = find(context, i);
        if(mtvreservation1 != null)
            uri = getUri(mtvreservation1.mUriId);
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvReservationManager", (new StringBuilder()).append("update: update reserve uri=").append(uri.toString()).toString());
        ContentValues contentvalues = getContentValues(mtvreservation);
        context.getContentResolver().update(uri, contentvalues, null, null);
    }

    public static void updateOrInsert(Context context, MtvReservation mtvreservation)
    {
        Uri uri = getUri(mtvreservation.mUriId);
        if(uri == null)
        {
            MtvReservation mtvreservation1 = find(context, mtvreservation.mTimeStart, new boolean[0]);
            if(mtvreservation1 != null)
                uri = getUri(mtvreservation1.mUriId);
        }
        if(uri == null)
        {
            MtvUtilDebug.Low("MtvReservationManager", "update: insert reserve");
            ContentValues contentvalues1 = getContentValues(mtvreservation);
            context.getContentResolver().insert(CONTENT_URI, contentvalues1);
        } else
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvReservationManager", (new StringBuilder()).append("update: update reserve uri=").append(uri.toString()).toString());
            ContentValues contentvalues = getContentValues(mtvreservation);
            context.getContentResolver().update(uri, contentvalues, null, null);
        }
    }

    public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.sec.mtv/reservations");
    public static final String PROJECTION[];
    protected static HashMap PROJECTION_MAP;

    static 
    {
        String as[] = new String[10];
        as[0] = "_id";
        as[1] = "epg_pch";
        as[2] = "epg_vch";
        as[3] = "epg_plock";
        as[4] = "epg_stime";
        as[5] = "epg_etime";
        as[6] = "epg_name";
        as[7] = "epg_detail";
        as[8] = "egp_type";
        as[9] = "egp_status";
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
        PROJECTION_MAP.put("egp_type", "egp_type");
        PROJECTION_MAP.put("egp_status", "egp_status");
    }
}
