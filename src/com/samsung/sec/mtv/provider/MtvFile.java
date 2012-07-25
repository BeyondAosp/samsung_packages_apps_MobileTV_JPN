// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.provider;

import android.util.Log;
import java.io.Serializable;
import java.util.Date;

public class MtvFile
    implements Serializable
{

    public MtvFile()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;-><init>()V");
        super();
        index = -1;
        channelName = null;
        programName = null;
        creationTime = null;
        durationOfRecording = -1;
        fileFormat = -1;
        fileSize = -1L;
        filePath = null;
        pidOfVideo = -1;
        pidOfAudio = -1;
        isLATM = 0;
    }

    public boolean equals(Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->equals(Ljava/lang/Object;)Z");
        boolean flag;
        if((obj instanceof MtvFile) && ((MtvFile)obj).filePath == filePath)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getChannelName()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getChannelName()Ljava/lang/String;");
        return channelName;
    }

    public Date getCreationTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getCreationTime()Ljava/util/Date;");
        return creationTime;
    }

    public int getDurationOfRecording()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getDurationOfRecording()I");
        return durationOfRecording;
    }

    public int getFileFormat()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getFileFormat()I");
        return fileFormat;
    }

    public String getFilePath()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getFilePath()Ljava/lang/String;");
        return filePath;
    }

    public long getFileSize()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getFileSize()J");
        return fileSize;
    }

    public int getIndex()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getIndex()I");
        return index;
    }

    public int getPidOfAudio()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getPidOfAudio()I");
        return pidOfAudio;
    }

    public int getPidOfVideo()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getPidOfVideo()I");
        return pidOfVideo;
    }

    public String getProgramName()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->getProgramName()Ljava/lang/String;");
        return programName;
    }

    public void setChannelName(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->setChannelName(Ljava/lang/String;)V");
        channelName = s;
    }

    public void setCreationTime(Date date)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->setCreationTime(Ljava/util/Date;)V");
        creationTime = date;
    }

    public void setFileFormat(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->setFileFormat(I)V");
        fileFormat = i;
    }

    public void setFilePath(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->setFilePath(Ljava/lang/String;)V");
        filePath = s;
    }

    public void setProgramName(String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->setProgramName(Ljava/lang/String;)V");
        programName = s;
    }

    public String toString()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/provider/MtvFile;->toString()Ljava/lang/String;");
        String s = (new StringBuilder()).append("MtvFile").append("[channelName=").append(channelName).toString();
        String s1 = (new StringBuilder()).append(s).append(", programName=").append(programName).toString();
        String s2 = (new StringBuilder()).append(s1).append(", creationTime=").append(creationTime).toString();
        String s3 = (new StringBuilder()).append(s2).append(", durationOfRecording=").append(durationOfRecording).toString();
        String s4 = (new StringBuilder()).append(s3).append(", fileFormat=").append(fileFormat).toString();
        String s5 = (new StringBuilder()).append(s4).append(", fileSize=").append(fileSize).toString();
        String s6 = (new StringBuilder()).append(s5).append(", filePath=").append(filePath).toString();
        String s7 = (new StringBuilder()).append(s6).append(", pidOfVideo=").append(pidOfVideo).toString();
        String s8 = (new StringBuilder()).append(s7).append(", pidOfAudio=").append(pidOfAudio).toString();
        return (new StringBuilder()).append(s8).append(", isLATM=").append(isLATM).append("]").toString();
    }

    private static final long serialVersionUID = 1L;
    protected String channelName;
    protected Date creationTime;
    protected int durationOfRecording;
    protected int fileFormat;
    protected String filePath;
    protected long fileSize;
    protected int index;
    protected int isLATM;
    protected int pidOfAudio;
    protected int pidOfVideo;
    protected String programName;
}
