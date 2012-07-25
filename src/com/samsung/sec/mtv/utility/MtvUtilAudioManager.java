// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.bluetooth.*;
import android.broadcast.IMtvOneSegAudioControl;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.samsung.sec.mtv.app.context.*;
import java.util.List;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvPreferences

public class MtvUtilAudioManager
{
    private class CallStateListener extends PhoneStateListener
    {

        public void onCallStateChanged(int i, String s)
        {
            MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("onCallStateChanged(state=").append(i).append(") is called").toString());
            i;
            JVM INSTR tableswitch 1 2: default 52
        //                       1 59
        //                       2 59;
               goto _L1 _L2 _L2
_L1:
            super.onCallStateChanged(i, s);
            return;
_L2:
            setAudioMute(true);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final MtvUtilAudioManager this$0;

        private CallStateListener()
        {
            this$0 = MtvUtilAudioManager.this;
            super();
        }

    }


    private MtvUtilAudioManager()
    {
        mAudioManager = null;
        mContext = null;
        mBtA2DP = null;
        mBtAdapter = null;
        mIsMuted = false;
        mLastRetainedVolumeLevel = 0;
        mMtvAppPlaybackContextManager = null;
        mMtvPreferences = null;
        mCallStateListener = null;
    }

    private MtvUtilAudioManager(Context context)
    {
        mAudioManager = null;
        mContext = null;
        mBtA2DP = null;
        mBtAdapter = null;
        mIsMuted = false;
        mLastRetainedVolumeLevel = 0;
        mMtvAppPlaybackContextManager = null;
        mMtvPreferences = null;
        mCallStateListener = null;
        mContext = context;
        mMtvAppPlaybackContextManager = MtvAppPlaybackContextManager.getInstance();
        mMtvPreferences = new MtvPreferences(mContext);
        mAudioManager = (AudioManager)mContext.getSystemService("audio");
        mCallStateListener = new CallStateListener();
        ((TelephonyManager)mContext.getSystemService("phone")).listen(mCallStateListener, 32);
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, serviceListener, 2);
    }

    public static MtvUtilAudioManager getInstance(Context context)
    {
        if(mMtvUtilAudioManager == null)
            mMtvUtilAudioManager = new MtvUtilAudioManager(context);
        return mMtvUtilAudioManager;
    }

    public boolean checkIsCalling()
    {
        boolean flag = true;
        int i = ((TelephonyManager)mContext.getSystemService("phone")).getCallState();
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("checkIsCalling() - tm.getCallState(): ").append(i).toString());
        if(i == 2 || i == flag)
            MtvUtilDebug.Low("MtvUtilAudioManager", "checkIsCalling() - in call ");
        else
            flag = false;
        return flag;
    }

    public int getVolumeLevel()
    {
        int i = mAudioManager.getStreamVolume(3);
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("getVolumeLevel() : volume= ").append(i).toString());
        return i;
    }

    public boolean isBTConnected()
    {
        boolean flag;
        if(mBtA2DP.getConnectedDevices().size() != 0 && mAudioManager.isBluetoothA2dpOn())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isHeadsetConnected()
    {
        boolean flag;
        if(mAudioManager.isWiredHeadsetOn())
        {
            MtvUtilDebug.Low("MtvUtilAudioManager", "isHeadsetConnected(): connected");
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUtilAudioManager", "isHeadsetConnected(): not - connected");
            flag = false;
        }
        return flag;
    }

    public void removeCallStateListener()
    {
        ((TelephonyManager)mContext.getSystemService("phone")).listen(mCallStateListener, 0);
    }

    public int setAudioEffect(int i)
    {
        int j = 1;
        boolean flag = isHeadsetConnected();
        MtvUtilDebug.Low("MtvUtilAudioManager", "inside setAudioEffect...");
        if(i != 6 && mMtvPreferences.isAudio51Enabled() == j && isHeadsetConnected() == j)
            i = 6;
        MtvAppPlaybackContext mtvappplaybackcontext = mMtvAppPlaybackContextManager.getCurrentContext();
        if(mtvappplaybackcontext != null)
        {
            IMtvOneSegAudioControl imtvonesegaudiocontrol = mtvappplaybackcontext.getComponents().getAudio().getControlInterface();
            if(imtvonesegaudiocontrol != null)
            {
                MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("AudioControlInterface setSoundEffect").append(i).toString());
                boolean flag1;
                if(!flag)
                    j = 0;
                flag1 = imtvonesegaudiocontrol.setSoundEffect(j, i);
                MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("AudioControlInterface setAudioMode retVal:").append(flag1).toString());
            }
        }
        return 0;
    }

    public int setAudioEnable(boolean flag)
    {
        int i = 1;
        if(checkIsCalling() == i && flag == i)
        {
            MtvUtilDebug.Low("MtvUtilAudioManager", "setAudioEnable() request ignored while call");
            i = 0;
        } else
        {
            MtvAppPlaybackContext mtvappplaybackcontext = mMtvAppPlaybackContextManager.getCurrentContext();
            if(mtvappplaybackcontext != null)
            {
                if(flag)
                    mtvappplaybackcontext.getComponents().getAudio().enable();
                else
                    mtvappplaybackcontext.getComponents().getAudio().disable();
            } else
            {
                i = -1;
            }
        }
        return i;
    }

    public int setAudioMode(int i)
    {
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("inside setAudioMode... playerAudioMode = ").append(i).toString());
        MtvAppPlaybackContext mtvappplaybackcontext = mMtvAppPlaybackContextManager.getCurrentContext();
        if(mtvappplaybackcontext != null)
        {
            IMtvOneSegAudioControl imtvonesegaudiocontrol = mtvappplaybackcontext.getComponents().getAudio().getControlInterface();
            if(imtvonesegaudiocontrol != null)
            {
                MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("AudioControlInterface setAudioMode").append(i).toString());
                boolean flag = imtvonesegaudiocontrol.setAudioMode(i);
                MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("AudioControlInterface setAudioMode retVal:").append(flag).toString());
            }
        }
        return 0;
    }

    public void setAudioMute(boolean flag)
    {
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("inside setAudioMute : mute: ").append(flag).toString());
        if(!checkIsCalling() || flag) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUtilAudioManager", "setAudioMute() unmute request ignored while call");
_L4:
        return;
_L2:
        MtvAppPlaybackContext mtvappplaybackcontext = mMtvAppPlaybackContextManager.getCurrentContext();
        if(mtvappplaybackcontext != null)
            if(!flag)
            {
                mtvappplaybackcontext.getComponents().getAudio().enable();
                IMtvOneSegAudioControl imtvonesegaudiocontrol1 = mtvappplaybackcontext.getComponents().getAudio().getControlInterface();
                if(imtvonesegaudiocontrol1 != null)
                    imtvonesegaudiocontrol1.unmuteAudio();
            } else
            {
                mtvappplaybackcontext.getComponents().getAudio().enable();
                IMtvOneSegAudioControl imtvonesegaudiocontrol = mtvappplaybackcontext.getComponents().getAudio().getControlInterface();
                if(imtvonesegaudiocontrol != null)
                    imtvonesegaudiocontrol.muteAudio();
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAvStreaming(boolean flag)
    {
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        MtvUtilDebug.Low("MtvUtilAudioManager", "transferToBT(): setAvStreaming True ");
        mBtAdapter.setAvStreaming(flag);
    }

    public void setVolumeLevel(int i)
    {
        if(i <= 0)
            mIsMuted = true;
        else
            mIsMuted = false;
        mLastRetainedVolumeLevel = 0;
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("setVolumeLevel() : volume= ").append(i).toString());
        mAudioManager.setStreamVolume(3, i, 0);
    }

    public void stopOtherSound()
    {
        if(!checkIsCalling())
        {
            if(mAudioManager != null)
            {
                mAudioManager.requestAudioFocus(mAudioFocusChangeListner, 3, 1);
                setAudioMute(false);
            }
        } else
        {
            MtvUtilDebug.High("MtvUtilAudioManager", "stopOtherSound called while on Call... Cannot proceed...");
        }
    }

    public void transferToBT()
    {
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        MtvUtilDebug.Low("MtvUtilAudioManager", "transferToBT(): setAvStreaming True ");
        mBtAdapter.setAvStreaming(true);
        if(mBtA2DP.getConnectedDevices().size() != 0)
        {
            mAudioManager.setBluetoothA2dpForceOn(true);
        } else
        {
            Intent intent = new Intent("android.bluetooth.devicepicker.action.LAUNCH");
            intent.putExtra("android.bluetooth.devicepicker.extra.NEED_AUTH", true);
            intent.putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 6);
            intent.putExtra("android.bluetooth.FromHeadsetActivity", true);
            intent.setFlags(0x10000000);
            mContext.startActivity(intent);
        }
    }

    public void transferToPhone()
    {
        MtvUtilDebug.Low("MtvUtilAudioManager", "transferToPhone()");
        mAudioManager.setBluetoothA2dpForceOn(false);
    }

    public boolean updateCurrentAudioEffectAndMode()
    {
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("updateCurrentAudioEffectAndMode... preferenceValues :AudioEffect=").append(mMtvPreferences.getAudioEffect()).append(" AudioLanguage=").append(mMtvPreferences.getAudioLanguage()).toString());
        if(mMtvPreferences.isAudio51Enabled())
            setAudioEffect(6);
        else
            setAudioEffect(mMtvPreferences.getAudioEffect());
        setAudioMode(mMtvPreferences.getAudioLanguage());
        return true;
    }

    public void volumeDown()
    {
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeDown() start: isMuted =").append(mIsMuted).append("  lastRetainedVolume =").append(mLastRetainedVolumeLevel).append(getVolumeLevel()).toString());
        if(mIsMuted)
        {
            mIsMuted = false;
            if(mAudioManager.getStreamVolume(3) == 0)
                mAudioManager.setStreamVolume(3, 1 + mLastRetainedVolumeLevel, 0);
        }
        mLastRetainedVolumeLevel = 0;
        mAudioManager.adjustStreamVolume(3, -1, 0);
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeDown() end : volume ").append(getVolumeLevel()).toString());
    }

    public void volumeMute()
    {
        if(mIsMuted)
        {
            MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeMute() : unmuted ,mLastRetainedVolumeLevel= ").append(mLastRetainedVolumeLevel).toString());
            mIsMuted = false;
            mAudioManager.setStreamVolume(3, mLastRetainedVolumeLevel, 0);
            mLastRetainedVolumeLevel = 0;
        } else
        {
            mIsMuted = true;
            mLastRetainedVolumeLevel = mAudioManager.getStreamVolume(3);
            mAudioManager.setStreamVolume(3, 0, 0);
            MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeMute() : muted ,mLastRetainedVolumeLevel= ").append(mLastRetainedVolumeLevel).toString());
        }
    }

    public void volumeUp()
    {
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeUp() start: isMuted =").append(mIsMuted).append("  lastRetainedVolume =").append(mLastRetainedVolumeLevel).append(getVolumeLevel()).toString());
        if(mIsMuted)
        {
            mIsMuted = false;
            if(mAudioManager.getStreamVolume(3) == 0)
                mAudioManager.setStreamVolume(3, -1 + mLastRetainedVolumeLevel, 0);
        }
        mLastRetainedVolumeLevel = 0;
        mAudioManager.adjustStreamVolume(3, 1, 0);
        MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("volumeUp() end : volume ").append(getVolumeLevel()).toString());
    }

    private static MtvUtilAudioManager mMtvUtilAudioManager = null;
    private android.media.AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListner = new android.media.AudioManager.OnAudioFocusChangeListener() {

        public void onAudioFocusChange(int i)
        {
            i;
            JVM INSTR tableswitch -2 1: default 32
        //                       -2 88
        //                       -1 88
        //                       0 32
        //                       1 33;
               goto _L1 _L2 _L2 _L1 _L3
_L1:
            return;
_L3:
            MtvUtilDebug.Low("MtvUtilAudioManager", "OnAudioFocusChangeListener() : AUDIOFOCUS_GAIN");
            setAudioMute(false);
            if(mBtA2DP.getConnectedDevices().size() != 0)
            {
                MtvUtilDebug.Low("MtvUtilAudioManager", "OnAudioFocusChangeListener: AUDIOFOCUS_GAIN :setAvStreaming() ");
                mBtAdapter.setAvStreaming(true);
            }
            continue; /* Loop/switch isn't completed */
_L2:
            setAudioMute(true);
            MtvUtilDebug.Low("MtvUtilAudioManager", (new StringBuilder()).append("OnAudioFocusChangeListener() : AUDIOFOCUS_LOSS_ ").append(i).toString());
            if(mBtA2DP.getConnectedDevices().size() != 0)
            {
                mBtAdapter = BluetoothAdapter.getDefaultAdapter();
                MtvUtilDebug.Low("MtvUtilAudioManager", "OnAudioFocusChangeListener: AUDIOFOCUS_GAIN :setAvStreaming() ");
                mBtAdapter.setAvStreaming(false);
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        final MtvUtilAudioManager this$0;

            
            {
                this$0 = MtvUtilAudioManager.this;
                super();
            }
    };
    private AudioManager mAudioManager;
    private BluetoothA2dp mBtA2DP;
    private BluetoothAdapter mBtAdapter;
    private CallStateListener mCallStateListener;
    private Context mContext;
    private boolean mIsMuted;
    private int mLastRetainedVolumeLevel;
    private MtvAppPlaybackContextManager mMtvAppPlaybackContextManager;
    private MtvPreferences mMtvPreferences;
    private android.bluetooth.BluetoothProfile.ServiceListener serviceListener = new android.bluetooth.BluetoothProfile.ServiceListener() {

        public void onServiceConnected(int i, BluetoothProfile bluetoothprofile)
        {
            mBtA2DP = (BluetoothA2dp)bluetoothprofile;
        }

        public void onServiceDisconnected(int i)
        {
            mBtA2DP = null;
        }

        final MtvUtilAudioManager this$0;

            
            {
                this$0 = MtvUtilAudioManager.this;
                super();
            }
    };




/*
    static BluetoothA2dp access$102(MtvUtilAudioManager mtvutilaudiomanager, BluetoothA2dp bluetootha2dp)
    {
        mtvutilaudiomanager.mBtA2DP = bluetootha2dp;
        return bluetootha2dp;
    }

*/



/*
    static BluetoothAdapter access$202(MtvUtilAudioManager mtvutilaudiomanager, BluetoothAdapter bluetoothadapter)
    {
        mtvutilaudiomanager.mBtAdapter = bluetoothadapter;
        return bluetoothadapter;
    }

*/
}
