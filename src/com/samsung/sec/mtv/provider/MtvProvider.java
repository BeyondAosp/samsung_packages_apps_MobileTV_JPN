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
import android.util.Log;
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
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;->createDefaultContents(Landroid/database/sqlite/SQLiteDatabase;)V");
            for(int i = 0; i < 10; i++)
                sqlitedatabase.insert("areas", null, MtvAreaManager.getContentValues(new MtvArea(-1, "Empty")));

        }

        public static DatabaseHelper getInstance(Context context)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;->getInstance(Landroid/content/Context;)Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;");
            if(mDatabaseHelperInstance == null)
                mDatabaseHelperInstance = new DatabaseHelper(context);
            return mDatabaseHelperInstance;
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;->onCreate(Landroid/database/sqlite/SQLiteDatabase;)V");
            MtvUtilDebug.Mid("DatabaseHelper", "onCreate");
            sqlitedatabase.execSQL("CREATE TABLE areas (_id INTEGER PRIMARY KEY,area_id INTEGER,area_name TEXT);");
            sqlitedatabase.execSQL("CREATE TABLE channels (_id INTEGER PRIMARY KEY,ch_vch INTEGER,ch_pch INTEGER DEFAULT -1,ch_fav INTEGER DEFAULT 0,ch_avlb INTEGER DEFAULT 0,ch_name TEXT,ch_slot INTEGER,ch_svcid1 INTEGER  DEFAULT -1,ch_svcid2 INTEGER  DEFAULT -1);");
            sqlitedatabase.execSQL("CREATE TABLE programs (_id INTEGER PRIMARY KEY,epg_vch INTEGER,epg_pch INTEGER,epg_plock INTEGER,epg_stime INTEGER,epg_etime INTEGER,epg_name TEXT,epg_detail TEXT);");
            sqlitedatabase.execSQL("CREATE TABLE reservations (_id INTEGER PRIMARY KEY,epg_vch INTEGER,epg_pch INTEGER,epg_plock INTEGER,epg_stime INTEGER,epg_etime INTEGER,epg_name TEXT,epg_detail TEXT,egp_type INTEGER,egp_status INTEGER);");
            createDefaultContents(sqlitedatabase);
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;->onUpgrade(Landroid/database/sqlite/SQLiteDatabase;II)V");
            MtvUtilDebug.Mid("DatabaseHelper", "onUpgrade");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS areas");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS channels");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS programs");
            sqlitedatabase.execSQL("DROP TABLE IF EXISTS reservations");
            onCreate(sqlitedatabase);
        }

        private static DatabaseHelper mDatabaseHelperInstance = null;

        static 
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;-><clinit>()V");
        }

        private DatabaseHelper(Context context)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider$DatabaseHelper;-><init>(Landroid/content/Context;)V");
            super(context, "mtv.db", null, 7);
        }
    }


    public MtvProvider()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;-><init>()V");
        super();
        mOpenHelper = null;
    }

    private Uri insert_area(Uri uri, ContentValues contentvalues)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->insert_area(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->insert_channel(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->insert_program(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->insert_reservation(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("delete ").append(uri.toString()).toString());
        sqlitedatabase = mOpenHelper.getWritableDatabase();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 112
    //                   1 157
    //                   2 171
    //                   3 112
    //                   4 264
    //                   5 278
    //                   6 112
    //                   7 371
    //                   8 385
    //                   9 112
    //                   10 139;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->getType(Landroid/net/Uri;)Ljava/lang/String;");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("getType ").append(uri.toString()).toString());
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 104
    //                   1 136
    //                   2 142
    //                   3 104
    //                   4 148
    //                   5 154
    //                   6 104
    //                   7 160
    //                   8 166
    //                   9 104
    //                   10 131;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("insert ").append(uri.toString()).toString());
        mUriMatcher.match(uri);
        JVM INSTR lookupswitch 4: default 92
    //                   1: 130
    //                   4: 141
    //                   7: 152
    //                   10: 119;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->onCreate()Z");
        MtvUtilDebug.Mid("MtvProvider", "onCreate");
        mOpenHelper = DatabaseHelper.getInstance(getContext());
        return true;
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        SQLiteQueryBuilder sqlitequerybuilder;
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("query ").append(uri.toString()).append(" where: ").append(s).toString());
        sqlitequerybuilder = new SQLiteQueryBuilder();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 11: default 128
    //                   1 290
    //                   2 326
    //                   3 584
    //                   4 388
    //                   5 424
    //                   6 646
    //                   7 486
    //                   8 522
    //                   9 708
    //                   10 155
    //                   11 228;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L10:
        break MISSING_BLOCK_LABEL_708;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;->update(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I");
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvProvider", (new StringBuilder()).append("update uri=").append(uri).toString());
        sqlitedatabase = mOpenHelper.getWritableDatabase();
        mUriMatcher.match(uri);
        JVM INSTR tableswitch 1 10: default 112
    //                   1 167
    //                   2 183
    //                   3 112
    //                   4 278
    //                   5 294
    //                   6 112
    //                   7 389
    //                   8 405
    //                   9 112
    //                   10 139;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvProvider;-><clinit>()V");
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
