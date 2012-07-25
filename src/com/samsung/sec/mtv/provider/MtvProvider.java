// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvAreaManager, MtvChannelManager, MtvProgramManager, MtvReservationManager, 
//            MtvArea

public class MtvProvider extends ContentProvider
{
    protected static class DatabaseHelper extends SQLiteOpenHelper
    {

        private void createDefaultContents(SQLiteDatabase sqlitedatabase)
        {
            for(int i = 0; i < 10; i++)
                sqlitedatabase.insert("areas", null, MtvAreaManager.getContentValues(new MtvArea(-1, "Empty")));

        }

        public static DatabaseHelper getInstance(Context context)
        {
            if(mDatabaseHelperInstance == null)
                mDatabaseHelperInstance = new DatabaseHelper(context);
            return mDatabaseHelperInstance;
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            MtvUtilDebug.Mid("DatabaseHelper", "onCreate");
            sqlitedatabase.execSQL("CREATE TABLE areas (_id INTEGER PRIMARY KEY,area_id INTEGER,area_name TEXT);");
            sqlitedatabase.execSQL("CREATE TABLE channels (_id INTEGER PRIMARY KEY,ch_vch INTEGER,ch_pch INTEGER DEFAULT -1,ch_fav INTEGER DEFAULT 0,ch_avlb INTEGER DEFAULT 0,ch_name TEXT,ch_slot INTEGER,ch_svcid1 INTEGER  DEFAULT -1,ch_svcid2 INTEGER  DEFAULT -1);");
            sqlitedatabase.execSQL("CREATE TABLE programs (_id INTEGER PRIMARY KEY,epg_vch INTEGER,epg_pch INTEGER,epg_plock INTEGER,epg_stime INTEGER,epg_etime INTEGER,epg_name TEXT,epg_detail TEXT);");
            sqlitedatabase.execSQL("CREATE TABLE reservations (_id INTEGER PRIMARY KEY,epg_vch INTEGER,epg_pch INTEGER,epg_plock INTEGER,epg_stime INTEGER,epg_etime INTEGER,epg_name TEXT,epg_detail TEXT,egp_type INTEGER,egp_status INTEGER);");
            createDefaultContents(sqlitedatabase);
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            MtvUtilDebug.Mid("DatabaseHelper", "onUpgrade");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS areas");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS channels");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS programs");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS reservations");
            onCreate(sqlitedatabase);
        }

        private static DatabaseHelper mDatabaseHelperInstance = null;


        private DatabaseHelper(Context context)
        {
            super(context, "mtv.db", null, 7);
        }
    }


    public MtvProvider()
    {
        mOpenHelper = null;
    }

    private Uri insert_area(Uri uri, ContentValues contentvalues)
    {
        ContentValues contentvalues1;
        long l;
        if(contentvalues != null)
            contentvalues1 = new ContentValues(contentvalues);
        else
            contentvalues1 = new ContentValues();
        l = mOpenHelper.getWritableDatabase().insert("areas", null, contentvalues1);
        if(l > 0L)
        {
            Uri uri1 = ContentUris.withAppendedId(MtvAreaManager.CONTENT_URI, l);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        } else
        {
            throw new SQLException((new StringBuilder()).append("Failed to insert row into ").append(uri).toString());
        }
    }

    private Uri insert_channel(Uri uri, ContentValues contentvalues)
    {
        ContentValues contentvalues1;
        long l;
        if(contentvalues != null)
            contentvalues1 = new ContentValues(contentvalues);
        else
            contentvalues1 = new ContentValues();
        l = mOpenHelper.getWritableDatabase().insert("channels", null, contentvalues1);
        if(l > 0L)
        {
            Uri uri1 = ContentUris.withAppendedId(MtvChannelManager.CONTENT_URI, l);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        } else
        {
            throw new SQLException((new StringBuilder()).append("Failed to insert row into ").append(uri).toString());
        }
    }

    private Uri insert_program(Uri uri, ContentValues contentvalues)
    {
        ContentValues contentvalues1;
        long l;
        if(contentvalues != null)
            contentvalues1 = new ContentValues(contentvalues);
        else
            contentvalues1 = new ContentValues();
        l = mOpenHelper.getWritableDatabase().insert("programs", null, contentvalues1);
        if(l > 0L)
        {
            Uri uri1 = ContentUris.withAppendedId(MtvProgramManager.CONTENT_URI, l);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        } else
        {
            throw new SQLException((new StringBuilder()).append("Failed to insert row into ").append(uri).toString());
        }
    }

    private Uri insert_reservation(Uri uri, ContentValues contentvalues)
    {
        ContentValues contentvalues1;
        long l;
        if(contentvalues != null)
            contentvalues1 = new ContentValues(contentvalues);
        else
            contentvalues1 = new ContentValues();
        l = mOpenHelper.getWritableDatabase().insert("reservations", null, contentvalues1);
        if(l > 0L)
        {
            Uri uri1 = ContentUris.withAppendedId(MtvReservationManager.CONTENT_URI, l);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        } else
        {
            throw new SQLException((new StringBuilder()).append("Failed to insert row into ").append(uri).toString());
        }
    }

    public int delete(Uri uri, String s, String as[])
    {
        SQLiteDatabase sqlitedatabase;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("delete ").append(uri.toString()).toString());
        sqlitedatabase = mOpenHelper.getWritableDatabase();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 104
    //                   1 149
    //                   2 163
    //                   3 104
    //                   4 256
    //                   5 270
    //                   6 104
    //                   7 363
    //                   8 377
    //                   9 104
    //                   10 131;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L1 _L6 _L7 _L1 _L8
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
_L8:
        int i = 0;
_L10:
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
_L2:
        i = sqlitedatabase.delete("channels", s, as);
        continue; /* Loop/switch isn't completed */
_L3:
        StringBuilder stringbuilder2 = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s3;
        if(!TextUtils.isEmpty(s))
            s3 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s3 = "";
        i = sqlitedatabase.delete("channels", stringbuilder2.append(s3).toString(), as);
        continue; /* Loop/switch isn't completed */
_L4:
        i = sqlitedatabase.delete("programs", s, as);
        continue; /* Loop/switch isn't completed */
_L5:
        StringBuilder stringbuilder1 = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s2;
        if(!TextUtils.isEmpty(s))
            s2 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s2 = "";
        i = sqlitedatabase.delete("programs", stringbuilder1.append(s2).toString(), as);
        continue; /* Loop/switch isn't completed */
_L6:
        i = sqlitedatabase.delete("reservations", s, as);
        continue; /* Loop/switch isn't completed */
_L7:
        StringBuilder stringbuilder = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s1;
        if(!TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s1 = "";
        i = sqlitedatabase.delete("reservations", stringbuilder.append(s1).toString(), as);
        if(true) goto _L10; else goto _L9
_L9:
    }

    public String getType(Uri uri)
    {
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("getType ").append(uri.toString()).toString());
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 96
    //                   1 128
    //                   2 134
    //                   3 96
    //                   4 140
    //                   5 146
    //                   6 96
    //                   7 152
    //                   8 158
    //                   9 96
    //                   10 123;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L1 _L6 _L7 _L1 _L8
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
_L8:
        String s = "vnd.android.cursor.dir/vnd.android.mtv.area";
_L10:
        return s;
_L2:
        s = "vnd.android.cursor.dir/vnd.android.mtv.channel";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "vnd.android.curosr.item/vnd.android.mtv.channel";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "vnd.android.cursor.dir/vnd.android.mtv.program";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "vnd.android.curosr.item/vnd.android.mtv.program";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "vnd.android.cursor.dir/vnd.android.mtv.reservation";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "vnd.android.curosr.item/vnd.android.mtv.reservation";
        if(true) goto _L10; else goto _L9
_L9:
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("insert ").append(uri.toString()).toString());
        mUriMatcher.match(uri);
        JVM INSTR lookupswitch 4: default 84
    //                   1: 120
    //                   4: 130
    //                   7: 140
    //                   10: 111;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
_L5:
        Uri uri1 = insert_area(uri, contentvalues);
_L7:
        return uri1;
_L2:
        uri1 = insert_channel(uri, contentvalues);
        continue; /* Loop/switch isn't completed */
_L3:
        uri1 = insert_program(uri, contentvalues);
        continue; /* Loop/switch isn't completed */
_L4:
        uri1 = insert_reservation(uri, contentvalues);
        if(true) goto _L7; else goto _L6
_L6:
    }

    public boolean onCreate()
    {
        MtvUtilDebug.Mid("MtvProvider", "onCreate");
        mOpenHelper = DatabaseHelper.getInstance(getContext());
        return true;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        SQLiteQueryBuilder sqlitequerybuilder;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("query ").append(uri.toString()).append(" where: ").append(s).toString());
        sqlitequerybuilder = new SQLiteQueryBuilder();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 11: default 116
    //                   1 277
    //                   2 313
    //                   3 571
    //                   4 375
    //                   5 411
    //                   6 633
    //                   7 473
    //                   8 509
    //                   9 695
    //                   10 143
    //                   11 215;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L10:
        break MISSING_BLOCK_LABEL_695;
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
_L11:
        sqlitequerybuilder.setTables("areas");
        sqlitequerybuilder.setProjectionMap(MtvAreaManager.PROJECTION_MAP);
        if(as == null)
            as = MtvAreaManager.PROJECTION;
        if(s1 == null)
            s1 = "_id ASC";
_L14:
        Cursor cursor;
        cursor = sqlitequerybuilder.query(mOpenHelper.getReadableDatabase(), as, s, as1, null, null, s1);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
_L13:
        return cursor;
_L12:
        SQLiteDatabase sqlitedatabase3 = mOpenHelper.getReadableDatabase();
        String s5 = "SELECT count(*) FROM areas";
        if(!TextUtils.isEmpty(s))
            s5 = (new StringBuilder()).append(s5).append(" WHERE ").append(s).toString();
        cursor = sqlitedatabase3.rawQuery(s5, as1);
          goto _L13
_L2:
        sqlitequerybuilder.setTables("channels");
        sqlitequerybuilder.setProjectionMap(MtvChannelManager.PROJECTION_MAP);
        if(as == null)
            as = MtvChannelManager.PROJECTION;
        if(s1 == null)
            s1 = "ch_vch ASC";
          goto _L14
_L3:
        sqlitequerybuilder.setTables("channels");
        sqlitequerybuilder.setProjectionMap(MtvChannelManager.PROJECTION_MAP);
        sqlitequerybuilder.appendWhere((new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1)).toString());
        if(as == null)
            as = MtvChannelManager.PROJECTION;
          goto _L14
_L5:
        sqlitequerybuilder.setTables("programs");
        sqlitequerybuilder.setProjectionMap(MtvProgramManager.PROJECTION_MAP);
        if(as == null)
            as = MtvProgramManager.PROJECTION;
        if(s1 == null)
            s1 = "epg_stime ASC";
          goto _L14
_L6:
        sqlitequerybuilder.setTables("programs");
        sqlitequerybuilder.setProjectionMap(MtvProgramManager.PROJECTION_MAP);
        sqlitequerybuilder.appendWhere((new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1)).toString());
        if(as == null)
            as = MtvProgramManager.PROJECTION;
          goto _L14
_L8:
        sqlitequerybuilder.setTables("reservations");
        sqlitequerybuilder.setProjectionMap(MtvReservationManager.PROJECTION_MAP);
        if(as == null)
            as = MtvReservationManager.PROJECTION;
        if(s1 == null)
            s1 = "epg_stime ASC";
          goto _L14
_L9:
        sqlitequerybuilder.setTables("reservations");
        sqlitequerybuilder.setProjectionMap(MtvReservationManager.PROJECTION_MAP);
        sqlitequerybuilder.appendWhere((new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1)).toString());
        if(as == null)
            as = MtvReservationManager.PROJECTION;
          goto _L14
_L4:
        SQLiteDatabase sqlitedatabase2 = mOpenHelper.getReadableDatabase();
        String s4 = "SELECT count(*) FROM channels";
        if(!TextUtils.isEmpty(s))
            s4 = (new StringBuilder()).append(s4).append(" WHERE ").append(s).toString();
        cursor = sqlitedatabase2.rawQuery(s4, as1);
          goto _L13
_L7:
        SQLiteDatabase sqlitedatabase1 = mOpenHelper.getReadableDatabase();
        String s3 = "SELECT count(*) FROM programs";
        if(!TextUtils.isEmpty(s))
            s3 = (new StringBuilder()).append(s3).append(" WHERE ").append(s).toString();
        cursor = sqlitedatabase1.rawQuery(s3, as1);
          goto _L13
        SQLiteDatabase sqlitedatabase = mOpenHelper.getReadableDatabase();
        String s2 = "SELECT count(*) FROM reservations";
        if(!TextUtils.isEmpty(s))
            s2 = (new StringBuilder()).append(s2).append(" WHERE ").append(s).toString();
        cursor = sqlitedatabase.rawQuery(s2, as1);
          goto _L13
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        SQLiteDatabase sqlitedatabase;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("update uri=").append(uri).toString());
        sqlitedatabase = mOpenHelper.getWritableDatabase();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 100
    //                   1 155
    //                   2 171
    //                   3 100
    //                   4 266
    //                   5 282
    //                   6 100
    //                   7 377
    //                   8 393
    //                   9 100
    //                   10 127;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L1 _L6 _L7 _L1 _L8
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown URI ").append(uri).toString());
_L8:
        int i = sqlitedatabase.update("areas", contentvalues, s, as);
_L10:
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
_L2:
        i = sqlitedatabase.update("channels", contentvalues, s, as);
        continue; /* Loop/switch isn't completed */
_L3:
        StringBuilder stringbuilder2 = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s3;
        if(!TextUtils.isEmpty(s))
            s3 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s3 = "";
        i = sqlitedatabase.update("channels", contentvalues, stringbuilder2.append(s3).toString(), as);
        continue; /* Loop/switch isn't completed */
_L4:
        i = sqlitedatabase.update("programs", contentvalues, s, as);
        continue; /* Loop/switch isn't completed */
_L5:
        StringBuilder stringbuilder1 = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s2;
        if(!TextUtils.isEmpty(s))
            s2 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s2 = "";
        i = sqlitedatabase.update("programs", contentvalues, stringbuilder1.append(s2).toString(), as);
        continue; /* Loop/switch isn't completed */
_L6:
        i = sqlitedatabase.update("reservations", contentvalues, s, as);
        continue; /* Loop/switch isn't completed */
_L7:
        StringBuilder stringbuilder = (new StringBuilder()).append("_id=").append((String)uri.getPathSegments().get(1));
        String s1;
        if(!TextUtils.isEmpty(s))
            s1 = (new StringBuilder()).append(" AND (").append(s).append(')').toString();
        else
            s1 = "";
        i = sqlitedatabase.update("reservations", contentvalues, stringbuilder.append(s1).toString(), as);
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final UriMatcher mUriMatcher;
    private DatabaseHelper mOpenHelper;

    static 
    {
        mUriMatcher = new UriMatcher(-1);
        mUriMatcher.addURI("com.samsung.sec.mtv", "areas", 10);
        mUriMatcher.addURI("com.samsung.sec.mtv", "areas/count", 11);
        mUriMatcher.addURI("com.samsung.sec.mtv", "channels", 1);
        mUriMatcher.addURI("com.samsung.sec.mtv", "channels/#", 2);
        mUriMatcher.addURI("com.samsung.sec.mtv", "channels/count", 3);
        mUriMatcher.addURI("com.samsung.sec.mtv", "programs", 4);
        mUriMatcher.addURI("com.samsung.sec.mtv", "programs/#", 5);
        mUriMatcher.addURI("com.samsung.sec.mtv", "programs/count", 6);
        mUriMatcher.addURI("com.samsung.sec.mtv", "reservations", 7);
        mUriMatcher.addURI("com.samsung.sec.mtv", "reservations/#", 8);
        mUriMatcher.addURI("com.samsung.sec.mtv", "reservations/count", 9);
    }
}
