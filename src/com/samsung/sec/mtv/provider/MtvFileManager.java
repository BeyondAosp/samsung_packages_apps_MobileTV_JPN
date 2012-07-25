// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.broadcast.helper.MtvUtilDebug;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import com.samsung.sec.mtv.app.context.MtvAppPlaybackContextManager;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.utility.MtvFileDBException;
import com.samsung.sec.mtv.utility.MtvUtilCrypto;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.provider:
//            MtvFile

public class MtvFileManager
{
    private static class MtvFileComparator
        implements Comparator
    {

        public int compare(MtvFile mtvfile, MtvFile mtvfile1)
        {
            int i;
            if(mtvfile == null || mtvfile1 == null)
            {
                MtvUtilDebug.High("MtvFileManager", "compare - null handling not supported - SERIOUS ERROR");
                i = 0;
            } else
            {
                i = (int)(mtvfile1.getCreationTime().getTime() - mtvfile.getCreationTime().getTime());
            }
            return i;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((MtvFile)obj, (MtvFile)obj1);
        }

        private MtvFileComparator()
        {
        }

    }

    private static class MtvFileManagerTvFileBuilder
    {

        public void close()
        {
            if(mCursor != null)
            {
                mCursor.close();
                mCursor = null;
            }
        }

        public int getItemSize()
        {
            int i = 0;
            if(mCursor != null)
                i = mCursor.getCount();
            return i;
        }

        public MtvFile getNextTvFile()
        {
            MtvFile mtvfile = null;
            if(mCursor == null) goto _L2; else goto _L1
_L1:
            boolean flag;
            if(!bMoveToFirstDone)
            {
                bMoveToFirstDone = true;
                flag = mCursor.moveToFirst();
            } else
            {
                flag = mCursor.moveToNext();
            }
            if(!flag) goto _L4; else goto _L3
_L3:
            mtvfile = new MtvFile();
            if(mtvfile == null) goto _L6; else goto _L5
_L5:
            if(mDbType != 1 && mDbType != 2) goto _L8; else goto _L7
_L7:
            mtvfile.index = mCursor.getInt(index);
            mtvfile.channelName = mCursor.getString(indexChannelName);
            mtvfile.programName = mCursor.getString(indexProgramName);
            mtvfile.filePath = mCursor.getString(indexFilePath);
            mtvfile.pidOfVideo = mCursor.getInt(indexVPID);
            mtvfile.pidOfAudio = mCursor.getInt(indexAPID);
            mtvfile.creationTime = new Date(mCursor.getLong(indexCreationTime));
            mtvfile.durationOfRecording = mCursor.getInt(indexDuration);
            mtvfile.fileFormat = mCursor.getInt(indexFileFormat);
            mtvfile.fileSize = mCursor.getInt(indexFileSize);
            mtvfile.isLATM = mCursor.getInt(indexIsLATM);
            if(mDbType == 2)
                mtvfile.index = -1 * mtvfile.index;
_L6:
            if(mtvfile == null)
                mtvfile = null;
            return mtvfile;
_L8:
            if(mDbType == 3)
            {
                mtvfile.index = mCursor.getInt(index);
                mtvfile.channelName = mCursor.getString(indexChannelName);
                mtvfile.programName = mCursor.getString(indexProgramName);
                mtvfile.filePath = mCursor.getString(indexFilePath);
                mtvfile.creationTime = new Date(mCursor.getLong(indexCreationTime));
                mtvfile.fileFormat = mCursor.getInt(indexFileFormat);
                mtvfile.fileSize = mCursor.getInt(indexFileSize);
                mtvfile.index = MtvFileManager.convertToImageDbIndex(mtvfile.index);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            mCursor.close();
            mCursor = null;
            continue; /* Loop/switch isn't completed */
_L2:
            MtvUtilDebug.High("MtvFileManager", "TvFileBuilder: cursor is null");
            if(true) goto _L6; else goto _L9
_L9:
        }

        private boolean bMoveToFirstDone;
        private int index;
        private int indexAPID;
        private int indexChannelName;
        private int indexCreationTime;
        private int indexDuration;
        private int indexFileFormat;
        private int indexFilePath;
        private int indexFileSize;
        private int indexIsLATM;
        private int indexProgramName;
        private int indexVPID;
        private Cursor mCursor;
        private int mDbType;

        public MtvFileManagerTvFileBuilder(SQLiteDatabase sqlitedatabase, int i)
        {
            String s;
            mCursor = null;
            mDbType = 1;
            bMoveToFirstDone = false;
            s = null;
            mDbType = i;
            if(i == 1 || i == 2)
                s = "select rowid, * from tvfiles_dbtable";
            else
            if(i == 3)
                s = "select rowid, * from tvimage_dbtable";
            else
                MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("unsupported dbtype ").append(i).toString());
            if(s != null)
                mCursor = sqlitedatabase.rawQuery(s, null);
            if(mCursor == null) goto _L2; else goto _L1
_L1:
            if(mCursor.getCount() != 0) goto _L4; else goto _L3
_L3:
            MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("TvFileBuilder: no entries in this DB !, dbType ").append(i).toString());
            mCursor.close();
            mCursor = null;
_L6:
            return;
_L4:
            if(i == 1 || i == 2)
            {
                index = mCursor.getColumnIndex("rowid");
                indexChannelName = mCursor.getColumnIndex("chName");
                indexProgramName = mCursor.getColumnIndex("proName");
                indexFilePath = mCursor.getColumnIndex("fullFilePath");
                indexVPID = mCursor.getColumnIndex("V_PID");
                indexAPID = mCursor.getColumnIndex("A_PID");
                indexCreationTime = mCursor.getColumnIndex("iCreateTime");
                indexDuration = mCursor.getColumnIndex("iDuration");
                indexFileFormat = mCursor.getColumnIndex("iFileFormat");
                indexFileSize = mCursor.getColumnIndex("iFileSize");
                indexIsLATM = mCursor.getColumnIndex("isLATM");
            } else
            if(i == 3)
            {
                index = mCursor.getColumnIndex("rowid");
                indexChannelName = mCursor.getColumnIndex("chName");
                indexProgramName = mCursor.getColumnIndex("proName");
                indexFilePath = mCursor.getColumnIndex("fullFilePath");
                indexCreationTime = mCursor.getColumnIndex("iCreateTime");
                indexFileFormat = mCursor.getColumnIndex("iFileFormat");
                indexFileSize = mCursor.getColumnIndex("iFileSize");
            }
            continue; /* Loop/switch isn't completed */
_L2:
            MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("TvFileBuider failed. Query [").append(s).append("], dbType ").append(i).toString());
            mCursor = null;
            if(true) goto _L6; else goto _L5
_L5:
        }
    }


    public MtvFileManager()
    {
    }

    private static int convertFromImageDbIndex(int i)
    {
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("[ImageDB]  input index: ").append(i).append(" hex: ").append(Integer.toHexString(i)).append(" converted to : hex: ").append(Integer.toHexString(i & 0xbfffffff)).append(" int: ").append(i & 0xbfffffff).toString());
        return i & 0xbfffffff;
    }

    private static int convertToImageDbIndex(int i)
    {
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("[ImageDB]  input index: ").append(i).append(" hex: ").append(Integer.toHexString(i)).append(" converted to :").append(Integer.toHexString(i | 0x40000000)).toString());
        return i | 0x40000000;
    }

    public static void deleteTvFile(int i, String s, IMtvAppPlayerOneSeg imtvappplayeroneseg)
        throws MtvFileDBException
    {
        boolean flag = true;
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("Entered deleteTvFile(), iFileIndex ").append(i).toString());
        if(imtvappplayeroneseg == null)
            throw new MtvFileDBException("Entered deleteTvFile() invalid player ");
        if(mIsImageDBEnabled && i > 0 && isImageDbIndex(i))
        {
            flag = false;
            com.samsung.sec.mtv.app.context.MtvAppPlaybackContext mtvappplaybackcontext;
            try
            {
                deleteTvImage(i, s, imtvappplayeroneseg);
            }
            catch(MtvFileDBException mtvfiledbexception)
            {
                mtvfiledbexception.printStackTrace();
            }
        }
        if(flag)
        {
            mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            if(mtvappplaybackcontext != null && imtvappplayeroneseg.deleteTVFile(mtvappplaybackcontext, i))
                MtvUtilDebug.Low("MtvFileManager", "Successfully deleted the requested TV File ");
            else
                MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("nativeDeleteTVRecFile() is failed, index ").append(i).toString());
        }
        MtvUtilDebug.Low("MtvFileManager", "Exiting deleteTvFile() ");
    }

    public static void deleteTvImage(int i, String s, IMtvAppPlayerOneSeg imtvappplayeroneseg)
        throws MtvFileDBException
    {
        SQLiteDatabase sqlitedatabase;
        boolean flag;
        sqlitedatabase = null;
        flag = false;
        if(imtvappplayeroneseg == null)
            throw new MtvFileDBException("Entered deleteTvFile() invalid player ");
        if(mIsImageDBEnabled && isImageDbIndex(i)) goto _L2; else goto _L1
_L1:
        deleteTvFile(i, s, imtvappplayeroneseg);
_L6:
        return;
_L2:
        int j;
        if(s == null)
        {
            MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("illegal param - filePath is null, index: ").append(i).toString());
            continue; /* Loop/switch isn't completed */
        }
        j = convertFromImageDbIndex(i);
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("deleteTvImage: file index: ").append(j).append("  filePath: ").append(s).toString());
        boolean flag1;
        sqlitedatabase = SQLiteDatabase.openDatabase("/sdcard/Android/data/one-seg/tvimages_database.db", null, 0);
        sqlitedatabase.execSQL((new StringBuilder()).append("DELETE FROM tvimage_dbtable WHERE ROWID == ").append(j).toString());
        File file = new File(s);
        if(!file.exists() || !file.canWrite())
            break MISSING_BLOCK_LABEL_179;
        flag1 = file.delete();
        flag = flag1;
        sqlitedatabase.close();
_L4:
        if(!flag)
            MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("deleteTvImage: failed to delete file: ").append(s).toString());
        continue; /* Loop/switch isn't completed */
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("deleteTvImage failed(), file index: ").append(j).toString());
        sqliteexception.printStackTrace();
        sqlitedatabase.close();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        sqlitedatabase.close();
        throw exception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static String generateFileName(int i, Date date)
    {
        String s;
        String s1;
        s = null;
        s1 = null;
        if(i != 0 && i != 1) goto _L2; else goto _L1
_L1:
        s = "record";
        s1 = ".ts";
_L4:
        String s2 = (new SimpleDateFormat("yyyyMMddHHmmssS")).format(date);
        return (new StringBuilder()).append(s).append(s2).append(s1).toString();
_L2:
        if(i == 2)
        {
            s = "capture";
            s1 = ".jpg";
        } else
        if(i == 3)
        {
            s = "thumb";
            s1 = ".thm";
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static MtvFile[] getAvailableTVRecFiles()
    {
        int i;
        MtvFile amtvfile[];
        i = 0;
        amtvfile = null;
        if(!mIsImageDBEnabled) goto _L2; else goto _L1
_L1:
        MtvFile amtvfile1[] = getAvailableTVRecFilesEx();
_L7:
        return amtvfile1;
_L2:
        MtvUtilDebug.Low("MtvFileManager", " Entered getAvailableTVRecFiles() ");
        if(vAvailableTvFiles == null)
            break MISSING_BLOCK_LABEL_1055;
        if(vAvailableTvFiles.size() != 0)
            vAvailableTvFiles.clear();
        MtvUtilDebug.Low("MtvFileManager", "nativeUpdateTvFile() Success");
        SQLiteDatabase sqlitedatabase3 = SQLiteDatabase.openDatabase("/data/one-seg/tvfiles_database.db", null, 1);
        SQLiteDatabase sqlitedatabase = sqlitedatabase3;
_L8:
        if(sqlitedatabase == null) goto _L4; else goto _L3
_L3:
        SQLiteDatabase sqlitedatabase1;
        Cursor cursor = sqlitedatabase.rawQuery("select rowid, * from tvfiles_dbtable", null);
        int j = cursor.getColumnIndex("rowid");
        int k = cursor.getColumnIndex("chName");
        int l = cursor.getColumnIndex("proName");
        int i1 = cursor.getColumnIndex("fullFilePath");
        int j1 = cursor.getColumnIndex("V_PID");
        int k1 = cursor.getColumnIndex("A_PID");
        int l1 = cursor.getColumnIndex("iCreateTime");
        int i2 = cursor.getColumnIndex("iDuration");
        int j2 = cursor.getColumnIndex("iFileFormat");
        int k2 = cursor.getColumnIndex("iFileSize");
        int l2 = cursor.getColumnIndex("isLATM");
        SQLiteException sqliteexception;
        SQLiteDatabase sqlitedatabase2;
        if(cursor.moveToFirst())
        {
            i = cursor.getCount();
            MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("getAvailableTVRecFiles() iItemSize : ").append(i).toString());
            do
            {
                MtvFile mtvfile1 = new MtvFile();
                mtvfile1.index = cursor.getInt(j);
                mtvfile1.channelName = cursor.getString(k);
                mtvfile1.programName = cursor.getString(l);
                mtvfile1.filePath = cursor.getString(i1);
                mtvfile1.pidOfVideo = cursor.getInt(j1);
                mtvfile1.pidOfAudio = cursor.getInt(k1);
                mtvfile1.creationTime = new Date(cursor.getLong(l1));
                mtvfile1.durationOfRecording = cursor.getInt(i2);
                mtvfile1.fileFormat = cursor.getInt(j2);
                mtvfile1.fileSize = cursor.getInt(k2);
                mtvfile1.isLATM = cursor.getInt(l2);
                vAvailableTvFiles.add(mtvfile1);
            } while(cursor.moveToNext());
        } else
        {
            MtvUtilDebug.High("MtvFileManager", "getAvailableTvFiles() mCursor.moveToFirst() failed");
        }
        cursor.close();
        sqlitedatabase.close();
        sqlitedatabase2 = SQLiteDatabase.openDatabase("/mnt/extSdCard/external_tvfiles.db", null, 1);
        sqlitedatabase1 = sqlitedatabase2;
_L9:
        if(sqlitedatabase1 != null) goto _L6; else goto _L5
_L5:
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles() there is no TvFiles in sd card");
        amtvfile = new MtvFile[i];
        vAvailableTvFiles.copyInto(amtvfile);
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles() End");
_L13:
        MtvUtilDebug.Low("MtvFileManager", " Exiting getAvailableTVRecFiles() ");
        amtvfile1 = amtvfile;
          goto _L7
        sqliteexception;
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles() there is no TvFiles");
        sqlitedatabase = null;
          goto _L8
        SQLiteException sqliteexception1;
        sqliteexception1;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles(): openDatabase /mnt/extSdCard/external_tvfiles.db failed");
        sqlitedatabase1 = null;
          goto _L9
_L6:
        Cursor cursor1;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        int i6;
        cursor1 = sqlitedatabase1.rawQuery("select rowid, * from tvfiles_dbtable", null);
        i3 = cursor1.getColumnIndex("rowid");
        j3 = cursor1.getColumnIndex("chName");
        k3 = cursor1.getColumnIndex("proName");
        l3 = cursor1.getColumnIndex("fullFilePath");
        i4 = cursor1.getColumnIndex("V_PID");
        j4 = cursor1.getColumnIndex("A_PID");
        k4 = cursor1.getColumnIndex("iCreateTime");
        l4 = cursor1.getColumnIndex("iDuration");
        i5 = cursor1.getColumnIndex("iFileFormat");
        j5 = cursor1.getColumnIndex("iFileSize");
        k5 = cursor1.getColumnIndex("isLATM");
        l5 = 0;
        i6 = 0;
        if(!cursor1.moveToFirst()) goto _L11; else goto _L10
_L10:
        l5 = cursor1.getCount();
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("getAvailableTVRecFiles() iItemSize_ext : ").append(l5).toString());
_L12:
        MtvFile mtvfile;
        Date date;
        boolean flag;
        int j6;
        mtvfile = new MtvFile();
        mtvfile.index = -1 * cursor1.getInt(i3);
        mtvfile.channelName = cursor1.getString(j3);
        mtvfile.programName = cursor1.getString(k3);
        mtvfile.filePath = cursor1.getString(l3);
        mtvfile.pidOfVideo = cursor1.getInt(i4);
        mtvfile.pidOfAudio = cursor1.getInt(j4);
        date = new Date(cursor1.getLong(k4));
        mtvfile.creationTime = date;
        mtvfile.durationOfRecording = cursor1.getInt(l4);
        mtvfile.fileFormat = cursor1.getInt(i5);
        mtvfile.fileSize = cursor1.getInt(j5);
        mtvfile.isLATM = cursor1.getInt(k5);
        flag = false;
        j6 = i6;
_L14:
        if(j6 < vAvailableTvFiles.size())
        {
            if(((MtvFile)vAvailableTvFiles.get(j6)).getCreationTime().getTime() <= date.getTime())
                break MISSING_BLOCK_LABEL_1038;
            vAvailableTvFiles.add(j6, mtvfile);
            i6 = j6 + 1;
            flag = true;
        }
        if(!flag)
            vAvailableTvFiles.add(mtvfile);
        if(cursor1.moveToNext()) goto _L12; else goto _L11
_L11:
        cursor1.close();
        sqlitedatabase1.close();
        amtvfile = new MtvFile[i + l5];
        vAvailableTvFiles.copyInto(amtvfile);
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles() End");
          goto _L13
        j6++;
          goto _L14
_L4:
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFiles() there is no TvFiles");
          goto _L13
        MtvUtilDebug.Low("MtvFileManager", " vAvilableTVFiles is null.. Cannot get the updated files list ");
          goto _L13
    }

    public static MtvFile[] getAvailableTVRecFilesEx()
    {
        MtvFile amtvfile[];
        int i;
        amtvfile = null;
        i = 0;
        if(mIsImageDBEnabled) goto _L2; else goto _L1
_L1:
        amtvfile = getAvailableTVRecFiles();
_L10:
        return amtvfile;
_L2:
        SQLiteDatabase sqlitedatabase;
        MtvFileManagerTvFileBuilder mtvfilemanagertvfilebuilder;
        MtvUtilDebug.Low("MtvFileManager", " Entered getAvailableTVRecFilesEx() ");
        MtvFile mtvfile2;
        SQLiteDatabase sqlitedatabase5;
        if(vAvailableTvFiles != null)
        {
            if(vAvailableTvFiles.size() != 0)
                vAvailableTvFiles.clear();
        } else
        {
            vAvailableTvFiles = new Vector();
        }
        if(vAvailableTvFiles == null)
            break; /* Loop/switch isn't completed */
        sqlitedatabase5 = SQLiteDatabase.openDatabase("/data/one-seg/tvfiles_database.db", null, 1);
        sqlitedatabase = sqlitedatabase5;
_L4:
        if(sqlitedatabase == null)
            break MISSING_BLOCK_LABEL_180;
        mtvfilemanagertvfilebuilder = new MtvFileManagerTvFileBuilder(sqlitedatabase, 1);
        if(mtvfilemanagertvfilebuilder == null)
            break MISSING_BLOCK_LABEL_176;
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("getAvailableTVRecFilesEx() opened db: /data/one-seg/tvfiles_database.db ItemSize is: ").append(mtvfilemanagertvfilebuilder.getItemSize()).toString());
        if(mtvfilemanagertvfilebuilder.getItemSize() > 0)
            do
            {
                mtvfile2 = mtvfilemanagertvfilebuilder.getNextTvFile();
                if(mtvfile2 == null)
                    break;
                vAvailableTvFiles.add(mtvfile2);
            } while(true);
        break; /* Loop/switch isn't completed */
        SQLiteException sqliteexception;
        sqliteexception;
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFilesEx() open failed for db: /data/one-seg/tvfiles_database.db");
        sqlitedatabase = null;
        if(true) goto _L4; else goto _L3
_L3:
        mtvfilemanagertvfilebuilder.close();
        sqlitedatabase.close();
        SQLiteDatabase sqlitedatabase4 = SQLiteDatabase.openDatabase("/mnt/extSdCard/external_tvfiles.db", null, 1);
        SQLiteDatabase sqlitedatabase1 = sqlitedatabase4;
_L6:
        MtvFileManagerTvFileBuilder mtvfilemanagertvfilebuilder1;
        if(sqlitedatabase1 == null)
            break MISSING_BLOCK_LABEL_303;
        mtvfilemanagertvfilebuilder1 = new MtvFileManagerTvFileBuilder(sqlitedatabase1, 2);
        if(mtvfilemanagertvfilebuilder1 == null)
            break MISSING_BLOCK_LABEL_298;
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("getAvailableTVRecFilesEx() opened db: /mnt/extSdCard/external_tvfiles.db ItemSize is: ").append(mtvfilemanagertvfilebuilder1.getItemSize()).toString());
        if(mtvfilemanagertvfilebuilder1.getItemSize() > 0)
            do
            {
                MtvFile mtvfile1 = mtvfilemanagertvfilebuilder1.getNextTvFile();
                if(mtvfile1 == null)
                    break;
                vAvailableTvFiles.add(mtvfile1);
            } while(true);
        break; /* Loop/switch isn't completed */
        SQLiteException sqliteexception1;
        sqliteexception1;
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFilesEx() open failed for db: /mnt/extSdCard/external_tvfiles.db");
        sqlitedatabase1 = null;
        if(true) goto _L6; else goto _L5
_L5:
        mtvfilemanagertvfilebuilder1.close();
        sqlitedatabase1.close();
        SQLiteDatabase sqlitedatabase3 = SQLiteDatabase.openDatabase("/sdcard/Android/data/one-seg/tvimages_database.db", null, 1);
        SQLiteDatabase sqlitedatabase2 = sqlitedatabase3;
_L8:
        MtvFileManagerTvFileBuilder mtvfilemanagertvfilebuilder2;
        if(sqlitedatabase2 == null)
            break MISSING_BLOCK_LABEL_425;
        mtvfilemanagertvfilebuilder2 = new MtvFileManagerTvFileBuilder(sqlitedatabase2, 3);
        if(mtvfilemanagertvfilebuilder2 == null)
            break MISSING_BLOCK_LABEL_420;
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("getAvailableTVRecFilesEx() opened db: /sdcard/Android/data/one-seg/tvimages_database.db ItemSize is: ").append(mtvfilemanagertvfilebuilder2.getItemSize()).toString());
        if(mtvfilemanagertvfilebuilder2.getItemSize() > 0)
            do
            {
                MtvFile mtvfile = mtvfilemanagertvfilebuilder2.getNextTvFile();
                if(mtvfile == null)
                    break;
                vAvailableTvFiles.add(mtvfile);
            } while(true);
        break; /* Loop/switch isn't completed */
        SQLiteException sqliteexception2;
        sqliteexception2;
        MtvUtilDebug.Low("MtvFileManager", "getAvailableTVRecFilesEx() open failed for db: /sdcard/Android/data/one-seg/tvimages_database.db");
        sqlitedatabase2 = null;
        if(true) goto _L8; else goto _L7
_L7:
        mtvfilemanagertvfilebuilder2.close();
        sqlitedatabase2.close();
        i = vAvailableTvFiles.size();
        if(i > 0)
        {
            Collections.sort(vAvailableTvFiles, new MtvFileComparator());
            if(i > 0)
            {
                amtvfile = new MtvFile[i];
                if(amtvfile != null)
                    vAvailableTvFiles.copyInto(amtvfile);
            }
        } else
        {
            MtvUtilDebug.High("MtvFileManager", "getAvailableTVRecFilesEx(): total file count from all DBs = 0 (NO FILES FOUND)");
        }
_L11:
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append(" Exiting getAvailableTVRecFilesEx(), totalCount read: ").append(i).toString());
        if(true) goto _L10; else goto _L9
_L9:
        MtvUtilDebug.Low("MtvFileManager", " vAvilableTVFiles is null.. Cannot get the updated files list ");
          goto _L11
        if(true) goto _L10; else goto _L12
_L12:
    }

    private static String getCaptureFilePath(int i)
    {
        String s;
        if(i == 1)
            s = "/sdcard/image/MyTvFiles/";
        else
            s = "/mnt/extSdCard/image/MyTvFiles/";
        return s;
    }

    private static String getRecThumbPath(int i)
    {
        String s;
        if(i == 1)
            s = "/sdcard/video/MyTvFiles/thumbnails/";
        else
            s = "/mnt/extSdCard/video/MyTvFiles/thumbnails/";
        return s;
    }

    private static void insertOneSegTvFile(MtvFile mtvfile)
        throws MtvFileDBException, SQLiteException
    {
        if(!mIsImageDBEnabled)
            break MISSING_BLOCK_LABEL_30;
        MtvUtilDebug.Low("MtvFileManager", "separate DB for captured images is enabled.../sdcard/Android/data/one-seg/tvimages_database.db");
        insertOneSegTvImage(mtvfile);
_L1:
        return;
        MtvFileDBException mtvfiledbexception;
        mtvfiledbexception;
        mtvfiledbexception.printStackTrace();
          goto _L1
        if(mtvfile != null)
        {
            if(mtvfile.getFilePath().regionMatches(false, 0, "Phone", 0, "Phone".length()) || mtvfile.getFilePath().regionMatches(false, 0, "/sdcard/image/MyTvFiles/", 0, "/sdcard/image/MyTvFiles/".length()) || mtvfile.getFilePath().regionMatches(false, 0, "/sdcard/video/MyTvFiles/", 0, "/sdcard/video/MyTvFiles/".length()))
            {
                SQLiteDatabase sqlitedatabase;
                try
                {
                    sqlitedatabase = SQLiteDatabase.openOrCreateDatabase("/data/one-seg/tvfiles_database.db", null);
                }
                catch(SQLiteException sqliteexception)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() fail to create db");
                    throw new MtvFileDBException("insertOneSegTvFile() fail to create db");
                }
                if(sqlitedatabase == null)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() fail to create db");
                    throw new MtvFileDBException("insertOneSegTvFile() fail to create db");
                }
                sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS tvfiles_dbtable (chName TEXT, proName TEXT, fullFilePath TEXT, V_PID INTEGER, A_PID INTEGER, iCreateTime INTEGER, iDuration INTEGER, iFileFormat INTEGER, iFileSize INTEGER, isLATM INTEGER);");
                Date date = mtvfile.getCreationTime();
                if(mtvfile.getChannelName() == null || mtvfile.getProgramName() == null || mtvfile.getFilePath() == null || date == null || mtvfile.getFileFormat() == -1 || mtvfile.getFileSize() == -1L)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() the parameter is invalid");
                    sqlitedatabase.close();
                    throw new MtvFileDBException("insertOneSegTvFile() the parameter is invalid");
                }
                if(mtvfile.getFileFormat() == 2)
                    sqlitedatabase.execSQL((new StringBuilder()).append("INSERT INTO tvfiles_dbtable VALUES (\"").append(mtvfile.getChannelName()).append("\",\"").append(mtvfile.getProgramName()).append("\",\"").append(mtvfile.getFilePath()).append("\", -1, -1, ").append(date.getTime()).append(", -1, ").append(mtvfile.getFileFormat()).append(",").append(mtvfile.getFileSize()).append(", 0);").toString());
                else
                    sqlitedatabase.execSQL((new StringBuilder()).append("INSERT INTO tvfiles_dbtable VALUES (\"").append(mtvfile.getChannelName()).append("\",\"").append(mtvfile.getProgramName()).append("\",\"").append(mtvfile.getFilePath()).append("\",").append(mtvfile.getPidOfVideo()).append(",").append(mtvfile.getPidOfAudio()).append(",").append(date.getTime()).append(",").append(mtvfile.getDurationOfRecording()).append(",").append(mtvfile.getFileFormat()).append(",").append(mtvfile.getFileSize()).append(");").toString());
                sqlitedatabase.close();
            } else
            if(mtvfile.getFilePath().regionMatches(false, 0, "SDCard", 0, "SDCard".length()) || mtvfile.getFilePath().regionMatches(false, 0, "/mnt/extSdCard/image/MyTvFiles/", 0, "/mnt/extSdCard/image/MyTvFiles/".length()) || mtvfile.getFilePath().regionMatches(false, 0, "/mnt/extSdCard/video/MyTvFiles/", 0, "/mnt/extSdCard/video/MyTvFiles/".length()))
            {
                SQLiteDatabase sqlitedatabase1;
                try
                {
                    sqlitedatabase1 = SQLiteDatabase.openOrCreateDatabase("/mnt/extSdCard/external_tvfiles.db", null);
                }
                catch(SQLiteException sqliteexception1)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() fail to create db in Ext memory");
                    throw new MtvFileDBException("insertOneSegTvFile() fail to create db in Ext memory");
                }
                if(sqlitedatabase1 == null)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() fail to create db in Ext memory");
                    throw new MtvFileDBException("insertOneSegTvFile() fail to create db in Ext memory");
                }
                sqlitedatabase1.execSQL("CREATE TABLE IF NOT EXISTS tvfiles_dbtable (chName TEXT, proName TEXT, fullFilePath TEXT, V_PID INTEGER, A_PID INTEGER, iCreateTime INTEGER, iDuration INTEGER, iFileFormat INTEGER, iFileSize INTEGER, isLATM INTEGER);");
                Date date1 = mtvfile.getCreationTime();
                if(mtvfile.getChannelName() == null || mtvfile.getProgramName() == null || mtvfile.getFilePath() == null || date1 == null || mtvfile.getFileFormat() == -1 || mtvfile.getFileSize() == -1L)
                {
                    MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() the parameter is invalid");
                    sqlitedatabase1.close();
                    throw new MtvFileDBException("insertOneSegTvFile() the parameter is invalid");
                }
                if(mtvfile.getFileFormat() == 2)
                    sqlitedatabase1.execSQL((new StringBuilder()).append("INSERT INTO tvfiles_dbtable VALUES (\"").append(mtvfile.getChannelName()).append("\",\"").append(mtvfile.getProgramName()).append("\",\"").append(mtvfile.getFilePath()).append("\", -1, -1, ").append(date1.getTime()).append(", -1, ").append(mtvfile.getFileFormat()).append(",").append(mtvfile.getFileSize()).append(", 0);").toString());
                else
                    sqlitedatabase1.execSQL((new StringBuilder()).append("INSERT INTO tvfiles_dbtable VALUES (\"").append(mtvfile.getChannelName()).append("\",\"").append(mtvfile.getProgramName()).append("\",\"").append(mtvfile.getFilePath()).append("\",").append(mtvfile.getPidOfVideo()).append(",").append(mtvfile.getPidOfAudio()).append(",").append(date1.getTime()).append(",").append(mtvfile.getDurationOfRecording()).append(",").append(mtvfile.getFileFormat()).append(",").append(mtvfile.getFileSize()).append(");").toString());
                sqlitedatabase1.close();
            }
        } else
        {
            throw new MtvFileDBException("The parameter is null");
        }
          goto _L1
    }

    public static void insertOneSegTvImage(MtvFile mtvfile)
        throws MtvFileDBException, SQLiteException
    {
        if(mIsImageDBEnabled) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.High("MtvFileManager", "separate DB for captured images is NOT enabled... use insertOneSegTvFile directly!");
        insertOneSegTvFile(mtvfile);
_L4:
        return;
_L2:
        SQLiteDatabase sqlitedatabase;
        if(mtvfile == null)
        {
            MtvUtilDebug.High("MtvFileManager", "input param is null, nothing to save!");
            continue; /* Loop/switch isn't completed */
        }
        if(mtvfile.getFileFormat() != 2)
        {
            MtvUtilDebug.High("MtvFileManager", (new StringBuilder()).append("unsupported format ").append(mtvfile.getFileFormat()).toString());
            throw new MtvFileDBException("unsupported file format for db storage");
        }
        if(mtvfile.getChannelName() == null || mtvfile.getFilePath() == null || mtvfile.getFileSize() == -1L || mtvfile.getCreationTime() == null)
        {
            MtvUtilDebug.High("MtvFileManager", "insertOneSegTvImage() the parameter is invalid");
            throw new MtvFileDBException("insertOneSegTvImage() the parameter is invalid");
        }
        try
        {
            sqlitedatabase = SQLiteDatabase.openOrCreateDatabase("/sdcard/Android/data/one-seg/tvimages_database.db", null);
        }
        catch(SQLiteException sqliteexception)
        {
            MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvImage() fail to create db");
            throw new MtvFileDBException("insertOneSegTvImage() fail to create db");
        }
        if(sqlitedatabase == null)
        {
            MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvFile() fail to create db");
            throw new MtvFileDBException("insertOneSegTvFile() fail to create db");
        }
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS tvimage_dbtable (chName TEXT, proName TEXT, fullFilePath TEXT, iCreateTime INTEGER, iFileFormat INTEGER, iFileSize INTEGER);");
        String s = new String((new StringBuilder()).append("INSERT INTO tvimage_dbtable VALUES (\"").append(mtvfile.getChannelName()).append("\",\"").append(mtvfile.getProgramName()).append("\",\"").append(mtvfile.getFilePath()).append("\", ").append(mtvfile.getCreationTime().getTime()).append(",").append(mtvfile.getFileFormat()).append(",").append(mtvfile.getFileSize()).append(");").toString());
        MtvUtilDebug.Low("MtvFileManager", s);
        sqlitedatabase.execSQL(s);
        if(sqlitedatabase != null)
            sqlitedatabase.close();
        continue; /* Loop/switch isn't completed */
        SQLiteException sqliteexception1;
        sqliteexception1;
        MtvUtilDebug.Low("MtvFileManager", "insertOneSegTvImage() failed");
        if(sqlitedatabase != null)
            sqlitedatabase.close();
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        if(sqlitedatabase != null)
            sqlitedatabase.close();
        throw exception;
    }

    private static boolean isImageDbIndex(int i)
    {
        boolean flag;
        if((i & 0x40000000) == 0x40000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void saveFile(int i, Bitmap bitmap, MtvFile mtvfile)
    {
        String s;
        File file;
        String s1;
        FileOutputStream fileoutputstream;
        FileOutputStream fileoutputstream1;
        s = null;
        file = null;
        int j;
        File file1;
        boolean flag;
        MtvUtilCrypto mtvutilcrypto;
        int k;
        ByteBuffer bytebuffer;
        ByteBuffer bytebuffer1;
        FileInputStream fileinputstream;
        if(mtvfile == null)
            j = 2;
        else
            j = mtvfile.fileFormat;
        j;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 40
    //                   1 536
    //                   2 581;
           goto _L1 _L1 _L2 _L3
_L1:
        s1 = (new StringBuilder()).append(file).append(s).toString();
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile: filePath=").append(file).append(" fileName=").append(s).toString());
        if(!file.exists())
            file.mkdirs();
        file1 = new File(file, s);
        try
        {
            if(file1.exists())
                file1.delete();
            file1.createNewFile();
        }
        catch(IOException ioexception)
        {
            MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile File creation fail [").append(ioexception).append("]").toString());
            ioexception.printStackTrace();
        }
        fileoutputstream = null;
        MtvUtilDebug.Low("TAG", "saveFile: create new fileoutputstream");
        fileoutputstream1 = new FileOutputStream(file1);
        flag = bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileoutputstream1);
        fileoutputstream1.close();
        if(!flag) goto _L5; else goto _L4
_L4:
        mtvutilcrypto = new MtvUtilCrypto(1);
        k = mtvutilcrypto.getOutputSize(1, (int)file1.length());
        bytebuffer = ByteBuffer.allocate(k);
        bytebuffer1 = ByteBuffer.allocate((int)file1.length());
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile:  file length = ").append(file1.length()).toString());
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile:  outSize = ").append(k).toString());
        fileinputstream = new FileInputStream(file1);
        fileinputstream.read(bytebuffer1.array());
        fileinputstream.close();
_L6:
        MtvUtilDebug.Low("TAG", "saveFile:  Encrypt ");
        mtvutilcrypto.encryptData(bytebuffer1, bytebuffer);
        fileoutputstream = null;
        fileoutputstream1 = new FileOutputStream(file1);
        fileoutputstream1.write(bytebuffer.array());
        fileoutputstream = fileoutputstream1;
_L8:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("TAG", (new StringBuilder()).append("saveFile:  encrypted length").append(bytebuffer.array().length).toString());
        MtvUtilDebug.Low("TAG", "saveFile:  Encrypt Success ");
        if(mtvfile == null || mtvfile.fileFormat != 2)
            break MISSING_BLOCK_LABEL_490;
        MtvUtilDebug.Low("MtvFileManager", "saveFile: file.mFormat == FILE_FORMAT_JPEG ");
        mtvfile.filePath = s1;
        FileNotFoundException filenotfoundexception;
        IOException ioexception5;
        IOException ioexception6;
        try
        {
            mtvfile.fileSize = (int)fileoutputstream.getChannel().size();
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile: FILE SIZE = ").append(mtvfile.fileSize).toString());
        }
        catch(IOException ioexception8)
        {
            ioexception8.printStackTrace();
        }
        insertOneSegTvImage(mtvfile);
_L13:
        IOException ioexception2;
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception7)
            {
                MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception7).append("]").toString());
                ioexception7.printStackTrace();
            }
_L7:
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile:returning filefull path = ").append(s1).toString());
        return;
_L2:
        s = (new StringBuilder()).append("/").append(mtvfile.filePath).append(".thm").toString();
        file = new File(getRecThumbPath(i));
          goto _L1
_L3:
        s = (new StringBuilder()).append("/").append(generateFileName(2, new Date())).toString().replace("jpg", "img");
        file = new File(getCaptureFilePath(i));
          goto _L1
        ioexception5;
        ioexception5.printStackTrace();
          goto _L6
        filenotfoundexception;
        fileoutputstream = fileoutputstream1;
_L14:
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile FileNotFoundException [").append(filenotfoundexception).append("]").toString());
        filenotfoundexception.printStackTrace();
        s1 = null;
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception4)
            {
                MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception4).append("]").toString());
                ioexception4.printStackTrace();
            }
          goto _L7
        ioexception6;
_L12:
        ioexception6.printStackTrace();
          goto _L8
        ioexception2;
_L11:
        MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile IOException [").append(ioexception2).append("] in the outermost block").toString());
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception3)
            {
                MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception3).append("]").toString());
                ioexception3.printStackTrace();
            }
          goto _L7
        Exception exception;
        exception;
_L10:
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception1)
            {
                MtvUtilDebug.Low("MtvFileManager", (new StringBuilder()).append("saveFile FileOutputStream.close() IOException [").append(ioexception1).append("]").toString());
                ioexception1.printStackTrace();
            }
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L10; else goto _L9
_L9:
        ioexception2;
        fileoutputstream = fileoutputstream1;
          goto _L11
        ioexception6;
        fileoutputstream = fileoutputstream1;
          goto _L12
_L5:
        fileoutputstream = fileoutputstream1;
          goto _L13
        filenotfoundexception;
          goto _L14
    }

    private static boolean mIsImageDBEnabled = true;
    private static Vector vAvailableTvFiles = null;

    static 
    {
        vAvailableTvFiles = new Vector();
    }

}
