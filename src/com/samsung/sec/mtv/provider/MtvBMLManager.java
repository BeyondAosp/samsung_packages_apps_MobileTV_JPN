// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.io.*;
import java.util.Date;
import java.util.Vector;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvCProBMInfo

public class MtvBMLManager
{

    public MtvBMLManager()
    {
    }

    public static void deleteCProBMInfo(int i)
        throws SQLiteException
    {
        MtvUtilDebug.Low("MtvBMLManager", (new StringBuilder()).append("deleteCProBMInfo() : called. listIndex = ").append(i).toString());
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        mDb.execSQL((new StringBuilder()).append("DELETE FROM dtvcprobminfo WHERE  ID== ").append(i).toString());
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L2:
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.Error("MtvBMLManager", "deleteCProBMInfo() : CProBMInfo \tdata open failed");
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
    }

    public static void deleteCProBMInfoAll()
        throws SQLiteException
    {
        MtvUtilDebug.Low("MtvBMLManager", "deleteCProBMInfoAll() : called.");
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        mDb.execSQL("DELETE FROM dtvcprobminfo");
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L2:
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.Error("MtvBMLManager", "deleteCProBMInfoAll() : CProBMInfo \tdata open failed");
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
    }

    public static void deleteStationData_DeleteAllAffiliation()
        throws SQLiteException
    {
        MtvUtilDebug.Low("MtvBMLManager", "deleteStationData_DeleteAllAffiliation() : called.");
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        mDb.execSQL("DELETE FROM dtvaffiliationblock");
        mDb.execSQL("DELETE FROM dtvaffiliationbroadcaster");
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L2:
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
    }

    public static void deleteStationData_DeleteNetwork(int i, int j)
        throws SQLiteException
    {
        int k;
        k = -1;
        MtvUtilDebug.Low("MtvBMLManager", "deleteStationData_DeleteNetwork() : called.");
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        SQLiteDatabase sqlitedatabase = mDb;
        String s = (new StringBuilder()).append("select originalNetworkID from dtvaffiliationbroadcaster where affiliationID=? order by originalNetworkID limit ").append(j).append(",1").toString();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append(i).append("").toString();
        Cursor cursor = sqlitedatabase.rawQuery(s, as);
        if(cursor.moveToFirst())
            k = cursor.getInt(0);
        cursor.close();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        if(k >= 0) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("deleteStationData_DeleteNetwork() : Error in retrieving originalNetworkID.").append(k).toString());
_L6:
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L3:
        return;
_L2:
        Cursor cursor1;
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        SQLiteDatabase sqlitedatabase1 = mDb;
        String as1[] = new String[2];
        as1[0] = (new StringBuilder()).append(i).append("").toString();
        as1[1] = (new StringBuilder()).append(k).append("").toString();
        cursor1 = sqlitedatabase1.rawQuery("select count(*) from dtvaffiliationblock where affiliationID=? and originalNetworkID=?", as1);
        if(cursor1.moveToFirst())
        {
            if(cursor1.getCount() <= 0)
                break MISSING_BLOCK_LABEL_470;
            SQLiteDatabase sqlitedatabase3 = mDb;
            String as3[] = new String[2];
            as3[0] = (new StringBuilder()).append(i).append("").toString();
            as3[1] = (new StringBuilder()).append(k).append("").toString();
            sqlitedatabase3.execSQL("delete from dtvaffiliationblock where affiliationID=? and originalNetworkID=?", as3);
        }
_L4:
        cursor1.close();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 0);
        SQLiteDatabase sqlitedatabase2 = mDb;
        String as2[] = new String[2];
        as2[0] = (new StringBuilder()).append(i).append("").toString();
        as2[1] = (new StringBuilder()).append(k).append("").toString();
        sqlitedatabase2.execSQL("delete from dtvaffiliationbroadcaster where affiliationID=? and originalNetworkID=?", as2);
        continue; /* Loop/switch isn't completed */
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
          goto _L3
        MtvUtilDebug.Error("MtvBMLManager", (new StringBuilder()).append("deleteStationData_DeleteNetwork() : Count").append(cursor1.getCount()).toString());
          goto _L4
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static int deleteStationData_GetNetworkItemCount(int i)
    {
        int j;
        j = 0;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("deleteStationData_GetNetworkItemCount() : \taffiliation_ID = ").append(i).toString());
        Cursor cursor;
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 1);
        SQLiteDatabase sqlitedatabase = mDb;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append(i).append("").toString();
        cursor = sqlitedatabase.rawQuery("select count(*)\tfrom dtvaffiliationbroadcaster where affiliationID=?", as);
        if(!cursor.moveToFirst()) goto _L2; else goto _L1
_L1:
        if(cursor.getCount() <= 0) goto _L4; else goto _L3
_L3:
        j = cursor.getInt(0);
_L2:
        cursor.close();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L5:
        MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("deleteStationData_GetNetworkItemCount() : \tcount = ").append(j).toString());
        return j;
_L4:
        MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("deleteStationData_GetNetworkItemCount() : mCursor.getCount() = ").append(cursor.getCount()).toString());
          goto _L2
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.Error("MtvBMLManager", "deleteStationData_GetNetworkItemCount()  db file open failed");
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
          goto _L5
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
          goto _L2
    }

    public static String deleteStationData_GetNetworkName(int i, int j)
    {
        int k;
        String s;
        k = 0;
        s = null;
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 1);
        SQLiteDatabase sqlitedatabase = mDb;
        String s1 = (new StringBuilder()).append("select originalNetworkID from dtvaffiliationbroadcaster where affiliationID=? \torder by originalNetworkID limit ").append(j).append(",1").toString();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append(i).append("").toString();
        Cursor cursor = sqlitedatabase.rawQuery(s1, as);
        if(cursor.moveToFirst())
            k = cursor.getInt(0);
        cursor.close();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 1);
        SQLiteDatabase sqlitedatabase1 = mDb;
        String as1[] = new String[2];
        as1[0] = (new StringBuilder()).append(i).append("").toString();
        as1[1] = (new StringBuilder()).append(k).append("").toString();
        Cursor cursor1 = sqlitedatabase1.rawQuery("select SI  from dtvaffiliationbroadcaster where affiliationID=? and originalNetworkID=?", as1);
        if(cursor1.moveToFirst())
            s = cursor1.getString(0);
        cursor1.close();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
_L2:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("deleteStationData_GetNetworkName() : networkName").append(s).toString());
        return s;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(mDb != null)
        {
            mDb.close();
            mDb = null;
        }
        throw exception;
    }

    public static MtvCProBMInfo getAvailableCProBMInfo(int i)
        throws SQLiteException, IOException
    {
        MtvCProBMInfo mtvcprobminfo;
        mtvcprobminfo = null;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfo() : called. id = ").append(i).toString());
        mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 1);
        if(mDb != null) goto _L2; else goto _L1
_L1:
        MtvCProBMInfo mtvcprobminfo1;
        MtvUtilDebug.Error("MtvBMLManager", "getAvailableCProBMInfo() : there is no \tCProBMInfo");
        mDb.close();
        mtvcprobminfo1 = null;
_L3:
        return mtvcprobminfo1;
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.Error("MtvBMLManager", "getAvailableCProBMInfo() : CProBMInfo \tdata open failed");
        mtvcprobminfo1 = null;
          goto _L3
_L2:
        Cursor cursor;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        SQLiteDatabase sqlitedatabase = mDb;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append(i).append("").toString();
        cursor = sqlitedatabase.rawQuery("select rowid, * from dtvcprobminfo where id=?", as);
        j = cursor.getColumnIndex("rowid");
        k = cursor.getColumnIndex("id");
        l = cursor.getColumnIndex("CproBMType");
        i1 = cursor.getColumnIndex("title");
        j1 = cursor.getColumnIndex("dstURI");
        k1 = cursor.getColumnIndex("originalNetworkID");
        l1 = cursor.getColumnIndex("transportStreamID");
        i2 = cursor.getColumnIndex("serviceID");
        j2 = cursor.getColumnIndex("affiliationID0");
        k2 = cursor.getColumnIndex("affiliationID1");
        l2 = cursor.getColumnIndex("affiliationID2");
        i3 = cursor.getColumnIndex("affiliationID3");
        j3 = cursor.getColumnIndex("affiliationID4");
        k3 = cursor.getColumnIndex("affiliationID5");
        l3 = cursor.getColumnIndex("outline");
        i4 = cursor.getColumnIndex("reserved");
        if(!cursor.moveToFirst()) goto _L5; else goto _L4
_L4:
        int j4 = cursor.getCount();
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfo() : iItemSize = ").append(j4).toString());
_L8:
        int k4;
        k4 = cursor.getInt(k);
        MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfo() : data id = ").append(k4).toString());
        if(k4 != i) goto _L7; else goto _L6
_L6:
        MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfo() : matching \tdata found. id = ").append(k4).toString());
        mtvcprobminfo = new MtvCProBMInfo();
        mtvcprobminfo.index = cursor.getInt(j);
        mtvcprobminfo.id = k4;
        mtvcprobminfo.CproBMType = cursor.getInt(l);
        mtvcprobminfo.title = cursor.getString(i1);
        mtvcprobminfo.dstURI = cursor.getString(j1);
        mtvcprobminfo.originalNetworkID = cursor.getInt(k1);
        mtvcprobminfo.transportStreamID = cursor.getInt(l1);
        mtvcprobminfo.serviceID = cursor.getInt(i2);
        mtvcprobminfo.affiliationID[0] = cursor.getInt(j2);
        mtvcprobminfo.affiliationID[1] = cursor.getInt(k2);
        mtvcprobminfo.affiliationID[2] = cursor.getInt(l2);
        mtvcprobminfo.affiliationID[3] = cursor.getInt(i3);
        mtvcprobminfo.affiliationID[4] = cursor.getInt(j3);
        mtvcprobminfo.affiliationID[5] = cursor.getInt(k3);
        mtvcprobminfo.outline = cursor.getString(l3);
        mtvcprobminfo.reserved = cursor.getString(i4);
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfoAll()\tCrop ID  \t: ").append(mtvcprobminfo.id).toString());
        mtvcprobminfo.validdate = getValidDateCProBMInfo(mtvcprobminfo.id);
_L5:
        cursor.close();
        mDb.close();
        MtvUtilDebug.Mid("MtvBMLManager", "getAvailableCProBMInfo() : End");
        mtvcprobminfo1 = mtvcprobminfo;
          goto _L3
_L7:
        if(cursor.moveToNext()) goto _L8; else goto _L5
    }

    public static MtvCProBMInfo[] getAvailableCProBMInfoAll()
        throws SQLiteException
    {
label0:
        {
            int i = 0;
            MtvUtilDebug.Low("MtvBMLManager", "getAvailableCProBMInfoAll() : called.");
            if(vCProBMInfo.size() != 0)
                vCProBMInfo.clear();
            MtvCProBMInfo amtvcprobminfo[];
            try
            {
                mDb = SQLiteDatabase.openDatabase("/data/one-seg/bml.db", null, 1);
            }
            catch(SQLiteException sqliteexception)
            {
                MtvUtilDebug.Error("MtvBMLManager", "getAvailableCProBMInfoAll() : CProBMInfo data open failed");
                amtvcprobminfo = null;
                if(false)
                    ;
                else
                    break label0;
            }
            if(mDb == null)
            {
                MtvUtilDebug.Error("MtvBMLManager", "getAvailableCProBMInfoAll() : there is no CProBMInfo");
                mDb.close();
                amtvcprobminfo = null;
            } else
            {
                Cursor cursor = mDb.rawQuery("select rowid, * from dtvcprobminfo", null);
                int j = cursor.getColumnIndex("id");
                int k = cursor.getColumnIndex("CproBMType");
                int l = cursor.getColumnIndex("title");
                int i1 = cursor.getColumnIndex("dstURI");
                int j1 = cursor.getColumnIndex("originalNetworkID");
                int k1 = cursor.getColumnIndex("transportStreamID");
                int l1 = cursor.getColumnIndex("serviceID");
                int i2 = cursor.getColumnIndex("affiliationID0");
                int j2 = cursor.getColumnIndex("affiliationID1");
                int k2 = cursor.getColumnIndex("affiliationID2");
                int l2 = cursor.getColumnIndex("affiliationID3");
                int i3 = cursor.getColumnIndex("affiliationID4");
                int j3 = cursor.getColumnIndex("affiliationID5");
                int k3 = cursor.getColumnIndex("outline");
                int l3 = cursor.getColumnIndex("reserved");
                if(cursor.moveToFirst())
                {
                    i = cursor.getCount();
                    MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfoAll() : iItemSize = ").append(i).toString());
                    Log.e("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfoAll() : iItemSize = ").append(i).toString());
                    do
                    {
                        MtvCProBMInfo mtvcprobminfo = new MtvCProBMInfo();
                        mtvcprobminfo.id = cursor.getInt(j);
                        mtvcprobminfo.CproBMType = cursor.getInt(k);
                        mtvcprobminfo.title = cursor.getString(l);
                        mtvcprobminfo.dstURI = cursor.getString(i1);
                        mtvcprobminfo.originalNetworkID = cursor.getInt(j1);
                        mtvcprobminfo.transportStreamID = cursor.getInt(k1);
                        mtvcprobminfo.serviceID = cursor.getInt(l1);
                        mtvcprobminfo.affiliationID[0] = cursor.getInt(i2);
                        mtvcprobminfo.affiliationID[1] = cursor.getInt(j2);
                        mtvcprobminfo.affiliationID[2] = cursor.getInt(k2);
                        mtvcprobminfo.affiliationID[3] = cursor.getInt(l2);
                        mtvcprobminfo.affiliationID[4] = cursor.getInt(i3);
                        mtvcprobminfo.affiliationID[5] = cursor.getInt(j3);
                        mtvcprobminfo.outline = cursor.getString(k3);
                        mtvcprobminfo.reserved = cursor.getString(l3);
                        if(!MtvUtilDebug.isReleaseMode())
                            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getAvailableCProBMInfoAll()   Crop ID  : ").append(mtvcprobminfo.id).toString());
                        mtvcprobminfo.validdate = getValidDateCProBMInfo(mtvcprobminfo.id);
                        vCProBMInfo.add(mtvcprobminfo);
                    } while(cursor.moveToNext());
                }
                MtvCProBMInfo amtvcprobminfo1[] = new MtvCProBMInfo[i];
                if(!vCProBMInfo.isEmpty())
                {
                    MtvUtilDebug.Mid("MtvBMLManager", "getAvailableCProBMInfoAll() : vCProBMInfo is not empty! so proceed copyInto().");
                    vCProBMInfo.copyInto(amtvcprobminfo1);
                } else
                {
                    MtvUtilDebug.Mid("MtvBMLManager", "getAvailableCProBMInfoAll() : vCProBMInfo is empty!");
                }
                cursor.close();
                mDb.close();
                MtvUtilDebug.Low("MtvBMLManager", "getAvailableCProBMInfoAll() : End");
                amtvcprobminfo = amtvcprobminfo1;
            }
        }
        return amtvcprobminfo;
    }

    private static Date getValidDateCProBMInfo(int i)
    {
        Date date = new Date();
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Mid("MtvBMLManager", (new StringBuilder()).append("getValidDateCProBMInfo() : index ").append(i).toString());
        try
        {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream("/data/one-seg/CproBmMng.dat"));
            for(long l = 0L; l < (long)i; l++)
            {
                datainputstream.readInt();
                datainputstream.readInt();
            }

            datainputstream.readInt();
            long l1 = 60 * datainputstream.readInt();
            if(l1 < 0L)
                date.setTime(0L);
            else
                date.setTime(l1 * 1000L);
            datainputstream.close();
        }
        catch(Exception exception)
        {
            MtvUtilDebug.Error("MtvBMLManager", (new StringBuilder()).append("getValidDateCProBMInfo() : \tException : ").append(exception).toString());
        }
        return date;
    }

    private static SQLiteDatabase mDb;
    private static Vector vCProBMInfo = new Vector();

}
