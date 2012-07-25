// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;
import android.util.Log;
import java.io.*;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvUtilConfigSetting

public class MtvUtilConfigSettingControl
{

    public MtvUtilConfigSettingControl()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSettingControl;-><init>()V");
        super();
    }

    private int changeBooleanToInt(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSettingControl;->changeBooleanToInt(Z)I");
        int i = 1;
        if(flag != i)
            i = 0;
        return i;
    }

    private int getSettingValue(String s, String s1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSettingControl;->getSettingValue(Ljava/lang/String;Ljava/lang/String;)I");
        int i = s.indexOf(s1);
        int j;
        if(i >= 0)
        {
            char c = s.charAt(1 + (i + s1.length()));
            if(c == '1')
                j = 1;
            else
            if(c == '2')
                j = 2;
            else
                j = 0;
            MtvUtilDebug.High("MtvUtilConfigSettingControl", (new StringBuilder()).append("## ").append(s1).append("=[").append(j).append("]").toString());
        } else
        {
            MtvUtilDebug.Error("MtvUtilConfigSettingControl", (new StringBuilder()).append("## Not found ").append(s1).toString());
            j = -1;
        }
        return j;
    }

    public MtvUtilConfigSetting getConfigFromFile()
    {
        MtvUtilConfigSetting mtvutilconfigsetting;
        File file;
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSettingControl;->getConfigFromFile()Lcom/samsung/sec/mtv/utility/MtvUtilConfigSetting;");
        mtvutilconfigsetting = new MtvUtilConfigSetting();
        file = new File("/data/one-seg/", "OneSeg_Config.cfg");
        if(file.exists() && file.length() > 0L) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Error("MtvUtilConfigSettingControl", "Not found config file! [/data/one-seg/OneSeg_Config.cfg]");
_L8:
        return mtvutilconfigsetting;
_L2:
        byte abyte0[] = new byte[10 + (int)file.length()];
        String s;
        FileInputStream fileinputstream = new FileInputStream(file);
        int i = fileinputstream.read(abyte0);
        fileinputstream.close();
        s = new String(abyte0, 0, i);
        if(getSettingValue(s, "CONFIG_TSFILESIMUL") != 1) goto _L4; else goto _L3
_L3:
        mtvutilconfigsetting.iTsFileSimul = true;
_L6:
        FileNotFoundException filenotfoundexception;
        if(getSettingValue(s, "CONFIG_TSCAPTURE") == 1)
        {
            mtvutilconfigsetting.iTsCapture = true;
            continue; /* Loop/switch isn't completed */
        }
        break; /* Loop/switch isn't completed */
_L4:
        mtvutilconfigsetting.iTsFileSimul = false;
        IOException ioexception;
        if(true) goto _L6; else goto _L5
_L5:
        try
        {
            mtvutilconfigsetting.iTsCapture = false;
        }
        // Misplaced declaration of an exception variable
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
            MtvUtilDebug.Error("MtvUtilConfigSettingControl", "Not found config file! [/data/one-seg/OneSeg_Config.cfg]");
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            MtvUtilDebug.Error("MtvUtilConfigSettingControl", "IO Exceiption Error! [/data/one-seg/OneSeg_Config.cfg]");
            ioexception.printStackTrace();
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public boolean setConfigToFile(MtvUtilConfigSetting mtvutilconfigsetting)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilConfigSettingControl;->setConfigToFile(Lcom/samsung/sec/mtv/utility/MtvUtilConfigSetting;)Z");
        StringBuffer stringbuffer = new StringBuffer(400);
        stringbuffer.append((new StringBuilder()).append("CONFIG_TSFILESIMUL=").append(changeBooleanToInt(mtvutilconfigsetting.iTsFileSimul)).append("\n").toString());
        stringbuffer.append((new StringBuilder()).append("CONFIG_TSCAPTURE=").append(changeBooleanToInt(mtvutilconfigsetting.iTsCapture)).append("\n").toString());
        String s = stringbuffer.toString();
        MtvUtilDebug.Mid("MtvUtilConfigSettingControl", (new StringBuilder()).append("Save Content\n[").append(s).toString());
        File file = new File("/data/one-seg/", "OneSeg_Config.cfg");
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.flush();
            fileoutputstream.write(s.getBytes());
            fileoutputstream.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
            MtvUtilDebug.Error("MtvUtilConfigSettingControl", "File Not Found=[/data/one-seg/OneSeg_Config.cfg]");
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            MtvUtilDebug.Error("MtvUtilConfigSettingControl", "File Writng Fail!=[/data/one-seg/OneSeg_Config.cfg]");
        }
        return true;
    }
}
