// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.SharedPreferences;
import com.samsung.sec.mtv.provider.MtvChannel;
import com.samsung.sec.mtv.provider.MtvChannelManager;
import java.util.Observable;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvUtilMemoryStatus

public final class MtvPreferences extends Observable
{

    public MtvPreferences(Context context)
    {
        float af[] = new float[6];
        af[0] = 0.12F;
        af[1] = 0.16F;
        af[2] = 0.35F;
        af[3] = 0.47F;
        af[4] = 0.75F;
        af[5] = 0.98F;
        mBrightnessTable = af;
        mContext = context;
        mPreferences = context.getSharedPreferences("com.samsung.sec.mtv.utility", 0);
    }

    public int getAudioEffect()
    {
        return mPreferences.getInt("pref_audio_effect", 0);
    }

    public int getAudioLanguage()
    {
        return mPreferences.getInt("pref_audio_language", 1);
    }

    public int getAutoPowerOffTime()
    {
        return mPreferences.getInt("pref_auto_power_off_time", 0);
    }

    public int getBrightnessLevel()
    {
        return mPreferences.getInt("pref_brightness_level", 3);
    }

    public float getBrightnessValue()
    {
        return mBrightnessTable[getBrightnessLevel()];
    }

    public int getBroadcastDataLocationMode()
    {
        return mPreferences.getInt("pref_broadcast_location", 0);
    }

    public int getBroadcastDataManufactureMode()
    {
        return mPreferences.getInt("pref_broadcast_manufacture", 0);
    }

    public int getBroadcastDataNotifyMode()
    {
        return mPreferences.getInt("pref_broadcast_notify", 0);
    }

    public int getBroadcastImageLocationStorage()
    {
        if(!MtvUtilMemoryStatus.isExternalMemoryAvailable())
            setBroadcastImageLocationStorage(1);
        return mPreferences.getInt("pref_image_location_storage", 1);
    }

    public int getBroadcastSetRecordingMode()
    {
        return mPreferences.getInt("pref_broadcast_set_recording", 0);
    }

    public int getColorTone()
    {
        return mPreferences.getInt("pref_screen_colortone", 1);
    }

    public int getComingReservationID()
    {
        return mPreferences.getInt("pref_coming_reserveration_id", -1);
    }

    public int getCurrentSlot()
    {
        return mPreferences.getInt("pref_current_slot", 0);
    }

    public int getDisplaySize()
    {
        return mPreferences.getInt("pref_display_size", 0);
    }

    public boolean getIsDtvStartedByReminderAlert()
    {
        return mPreferences.getBoolean("pref_reserve_dtv_started", false);
    }

    public boolean getIsFileFormatImage()
    {
        return mPreferences.getBoolean("pref_file_format", false);
    }

    public String getLatestChannelNameForDisplay(boolean flag)
    {
        MtvChannel mtvchannel = null;
        int i = getLatestVChannel();
        String s;
        if(i > 0 && i < 13)
            mtvchannel = MtvChannelManager.findByVChannel(mContext, i);
        else
        if(i > -1)
        {
            int j = getLatestPChannel();
            mtvchannel = MtvChannelManager.findByPChannel(mContext, j);
        }
        if(mtvchannel != null)
        {
            s = mtvchannel.mChannelName;
        } else
        {
            int k = getLatestPChannel();
            if(flag && k > -1)
                s = (new StringBuilder()).append("Ch: ").append(k).toString();
            else
                s = "";
        }
        return s;
    }

    public int getLatestChannelNumberForDisplay()
    {
        int i = getLatestVChannel();
        if(i <= 0 || i >= 13)
            i = getLatestPChannel();
        return i;
    }

    public int getLatestFileIndex()
    {
        return mPreferences.getInt("pref_latest_file_index", 0);
    }

    public int getLatestPChannel()
    {
        return mPreferences.getInt("pref_latest_pchannel", -1);
    }

    public int getLatestPChannelFromVChannel()
    {
        int i = getLatestVChannel();
        if(i > 0 && i < 13)
        {
            MtvChannel mtvchannel = MtvChannelManager.findByVChannel(mContext, i);
            if(mtvchannel != null)
                i = mtvchannel.mPhysicalNum;
        } else
        {
            i = getLatestPChannel();
        }
        return i;
    }

    public int getLatestVChannel()
    {
        return mPreferences.getInt("pref_latest_vchannel", -1);
    }

    public boolean getLaunchAntennaAlert()
    {
        return mPreferences.getBoolean("pref_launch_antenna", false);
    }

    public int getReservationAlertID()
    {
        MtvUtilDebug.High("MtvPreferences", "getReservationAlertID()");
        return mPreferences.getInt("pref_reserve_alert_id", -1);
    }

    public int getReserveAlertFrom()
    {
        MtvUtilDebug.High("MtvPreferences", "getReserveAlertFrom()");
        return mPreferences.getInt("pref_reserve_alert_from", -1);
    }

    public int getSaveToStorage()
    {
        if(!MtvUtilMemoryStatus.isExternalMemoryAvailable())
            setSaveToStorage(1);
        return mPreferences.getInt("pref_save_to_storage", 1);
    }

    public int getSelectedFileIndex()
    {
        return mPreferences.getInt("pref_select_file_index", 0);
    }

    public boolean isAudio51Enabled()
    {
        return mPreferences.getBoolean("pref_audio_51", false);
    }

    public boolean isCaptionEnabled()
    {
        return mPreferences.getBoolean("pref_caption", false);
    }

    public boolean isOutdoorVisibility()
    {
        boolean flag = true;
        if(mPreferences.getInt("pref_outdoor_visibility", 0) != flag)
            flag = false;
        return flag;
    }

    public boolean isReservationProgram()
    {
        return mPreferences.getBoolean("pref_while_scheduled_program", false);
    }

    public boolean isframeIPEnabled()
    {
        return mPreferences.getBoolean("pref_frameip", false);
    }

    public void setAudio51Enabled(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_audio_51", flag);
        editor.commit();
    }

    public void setAudioEffect(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_audio_effect", i);
        editor.commit();
        setChanged();
        notifyObservers("pref_audio_effect");
    }

    public void setAudioLanguage(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_audio_language", i);
        editor.commit();
    }

    public void setAutoPowerOffTime(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_auto_power_off_time", i);
        editor.commit();
    }

    public void setBrightnessLevel(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_brightness_level", i);
        editor.commit();
    }

    public void setBroadcastDataLocationMode(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_broadcast_location", i);
        editor.commit();
    }

    public void setBroadcastDataManufactureMode(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_broadcast_manufacture", i);
        editor.commit();
    }

    public void setBroadcastDataNotifyMode(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_broadcast_notify", i);
        editor.commit();
    }

    public void setBroadcastImageLocationStorage(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_image_location_storage", i);
        editor.commit();
        setChanged();
        notifyObservers("pref_image_location_storage");
    }

    public void setBroadcastSetRecordingMode(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_broadcast_set_recording", i);
        editor.commit();
    }

    public void setCaptionEnabled(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_caption", flag);
        editor.commit();
    }

    public void setColorTone(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_screen_colortone", i);
        editor.commit();
    }

    public void setComingReservationID(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_coming_reserveration_id", i);
        editor.commit();
    }

    public void setCurrentSlot(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_current_slot", i);
        editor.commit();
    }

    public void setDisplaySize(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_display_size", i);
        editor.commit();
    }

    public void setFrameIPEnabled(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_frameip", flag);
        editor.commit();
    }

    public void setIsDtvStartedByReminderAlert(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_reserve_dtv_started", flag);
        editor.commit();
    }

    public void setIsFileFormatImage(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_file_format", flag);
        editor.commit();
    }

    public void setIsReservationProgram(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_while_scheduled_program", flag);
        editor.commit();
    }

    public void setLatestFileIndex(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_latest_file_index", i);
        editor.commit();
    }

    public void setLatestPChannel(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_latest_pchannel", i);
        editor.commit();
    }

    public void setLatestVChannel(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_latest_vchannel", i);
        editor.commit();
    }

    public void setLaunchAntennaAlert(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("pref_launch_antenna", flag);
        editor.commit();
    }

    public void setOutdoorVisibility(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        editor.putInt("pref_outdoor_visibility", i);
        editor.commit();
    }

    public void setReservAlertFrom(int i)
    {
        MtvUtilDebug.High("MtvPreferences", (new StringBuilder()).append("setReservAlertFrom() :VALUE ").append(i).toString());
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_reserve_alert_from", i);
        editor.commit();
    }

    public void setReservationAlertID(int i)
    {
        MtvUtilDebug.Low("MtvPreferences", (new StringBuilder()).append(" setReservationAlertID() : reerve Id :").append(i).toString());
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_reserve_alert_id", i);
        editor.commit();
    }

    public void setSaveToStorage(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_save_to_storage", i);
        editor.commit();
    }

    public void setSelectedFileIndex(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("pref_select_file_index", i);
        editor.commit();
    }

    private float mBrightnessTable[];
    private final Context mContext;
    private final SharedPreferences mPreferences;
}
