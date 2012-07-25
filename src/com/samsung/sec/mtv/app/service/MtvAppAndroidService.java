// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.service;

import android.app.ActivityManager;
import android.app.Service;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.os.IBinder;
import com.samsung.sec.mtv.utility.MtvUtilAppService;
import com.samsung.sec.mtv.utility.MtvUtilTvOut;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.app.service:
//            MtvAppAndroidServiceBinder, onMtvAppAndroidServiceListener

public class MtvAppAndroidService extends Service
{
    private class MtvBroadcastReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if(s != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND") || s.equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", (new StringBuilder()).append("ACTION_MTV_APP_FINISH: ").append(s).toString());
                if(s.equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"))
                    MtvUtilAppService.setAbnormalExit(true);
                finishAllListners(intent);
            } else
            if(s.equals("android.intent.action.TIME_TICK"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_TIME_TICK recivied");
                notifyBaseListener(1, intent);
            } else
            if(s.equals("android.intent.action.BATTERY_CHANGED"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_BATTERY_CHANGED recevied");
                mLastBatteryIntent = intent;
                notifyBaseListener(2, intent);
            } else
            if(s.equals("android.intent.action.HEADSET_PLUG"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_HEADSET_PLUG received");
                notifyAllListener(9, intent);
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_FOREGROUND"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_MTV_APP_FINISH_FOREGROUND received");
                notifyBaseListener(8, null);
            } else
            if(s.equals("android.intent.action.BATTERY_LOW"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_BATTERY_LOW received");
                if(isAppOnForeground(getApplicationContext()))
                {
                    Intent intent2 = new Intent();
                    intent2.setAction("com.samsung.sec.mtv.ACTION_MTV_POP_UP");
                    intent2.putExtra("popup_type", 0);
                    notifyBaseListener(3, intent2);
                } else
                {
                    MtvUtilDebug.Low("MtvAppAndroidService", "com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND");
                    sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND"));
                }
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_SLEEP_TIMER_ALARM"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_MTV_SLEEP_TIMER_ALARM received");
                if(isAppOnForeground(getApplicationContext()))
                {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.samsung.sec.mtv.ACTION_MTV_POP_UP");
                    intent1.putExtra("popup_type", 1);
                    notifyBaseListener(7, intent1);
                } else
                {
                    MtvUtilDebug.Low("MtvAppAndroidService", "com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND");
                    sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND"));
                }
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_MTV_RESERVATION_END received");
                notifyBaseListener(10, intent);
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_CANCEL_EXIT"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_MTV_RESERVATION_CANCEL_EXIT received");
                notifyBaseListener(10, intent);
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END_DIALOG_CLOSE"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "ACTION_MTV_RESERVATION_END_DIALOG_CLOSE received");
                notifyBaseListener(12, intent);
            } else
            if(s.equals("android.intent.action.HDMI_PLUGGED"))
            {
                boolean flag = intent.getBooleanExtra("state", false);
                MtvUtilDebug.Low("MtvAppAndroidService", (new StringBuilder()).append("TvoutService HDMI : ").append(s).append(" status : ").append(flag).toString());
                String s1 = null;
                ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
                if(activitymanager != null)
                    s1 = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
                if(s1 != null)
                {
                    if(s1.contains("com.samsung.sec.mtv"))
                    {
                        if(flag)
                        {
                            MtvUtilDebug.Low("MtvAppAndroidService", "Calling HDMI suspend");
                            MtvUtilTvOut.suspendTvOut();
                        } else
                        {
                            MtvUtilDebug.Low("MtvAppAndroidService", "Calling HDMI resume");
                            MtvUtilTvOut.resumeTvOut();
                        }
                    } else
                    {
                        MtvUtilDebug.Low("MtvAppAndroidService", "DTV is not on top");
                    }
                } else
                {
                    MtvUtilDebug.Low("MtvAppAndroidService", "No top activity!! Is it even possible??");
                }
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        final MtvAppAndroidService this$0;

        private MtvBroadcastReceiver()
        {
            this$0 = MtvAppAndroidService.this;
            super();
        }

    }

    private class MtvMediaBroadcastReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if(s != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(s.equals("android.intent.action.MEDIA_EJECT"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "MtvMediaBroadcastReceiver: ACTION_MEDIA_EJECT received");
                notifyBaseListener(4, intent);
            } else
            if(s.equals("android.intent.action.MEDIA_UNMOUNTED"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "MtvMediaBroadcastReceiver: ACTION_MEDIA_UNMOUNTED received");
                notifyBaseListener(5, intent);
            } else
            if(s.equals("android.intent.action.MEDIA_MOUNTED"))
            {
                MtvUtilDebug.Low("MtvAppAndroidService", "MtvMediaBroadcastReceiver: ACTION_MEDIA_MOUNTED received");
                mLastBatteryIntent = intent;
                notifyBaseListener(6, intent);
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        final MtvAppAndroidService this$0;

        private MtvMediaBroadcastReceiver()
        {
            this$0 = MtvAppAndroidService.this;
            super();
        }

    }


    public MtvAppAndroidService()
    {
        mListeners = new ArrayList();
        mLastBatteryIntent = null;
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvAppAndroidService", "MtvAppAndroidService created...");
        mySingletonInstance = this;
    }

    private final void attach(onMtvAppAndroidServiceListener onmtvappandroidservicelistener)
    {
        this;
        JVM INSTR monitorenter ;
        mListeners.add(onmtvappandroidservicelistener);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final void detach(onMtvAppAndroidServiceListener onmtvappandroidservicelistener)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(iterator.next() != onmtvappandroidservicelistener)
                continue;
            iterator.remove();
            break;
        } while(true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean isAppOnForeground(Context context)
    {
        String s = ((android.app.ActivityManager.RunningTaskInfo)((ActivityManager)getApplicationContext().getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
        boolean flag;
        if(getApplicationContext().getPackageName().equals(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void finishAllListners(Intent intent)
    {
        onMtvAppAndroidServiceListener aonmtvappandroidservicelistener[];
        int i;
        aonmtvappandroidservicelistener = getArrayFromIterator();
        i = -1 + aonmtvappandroidservicelistener.length;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        MtvUtilDebug.Low("finish ServiceListener", (new StringBuilder()).append("").append(aonmtvappandroidservicelistener[i]).toString());
        aonmtvappandroidservicelistener[i].onMtvAppFinishNotify(intent);
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        nullpointerexception.printStackTrace();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    protected final onMtvAppAndroidServiceListener[] getArrayFromIterator()
    {
        this;
        JVM INSTR monitorenter ;
        onMtvAppAndroidServiceListener aonmtvappandroidservicelistener[];
        aonmtvappandroidservicelistener = new onMtvAppAndroidServiceListener[mListeners.size()];
        mListeners.toArray(aonmtvappandroidservicelistener);
        this;
        JVM INSTR monitorexit ;
        return aonmtvappandroidservicelistener;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyAllListener(int i, Intent intent)
    {
        onMtvAppAndroidServiceListener aonmtvappandroidservicelistener[];
        aonmtvappandroidservicelistener = getArrayFromIterator();
        MtvUtilDebug.Low("MtvAppAndroidService", (new StringBuilder()).append("notifyAllListener..:what: ").append(i).toString());
        int j;
        int k;
        j = aonmtvappandroidservicelistener.length;
        k = 0;
_L1:
        if(k >= j)
            break MISSING_BLOCK_LABEL_60;
        aonmtvappandroidservicelistener[k].onMtvAppAndroidServiceNotify(i, intent);
        k++;
          goto _L1
_L3:
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        nullpointerexception.printStackTrace();
        if(true) goto _L3; else goto _L2
_L2:
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyBaseListener(int i, Intent intent)
    {
        if(mListeners.size() >= 1)
            ((onMtvAppAndroidServiceListener)mListeners.get(0)).onMtvAppAndroidServiceNotify(i, intent);
_L1:
        return;
        NullPointerException nullpointerexception;
        nullpointerexception;
        nullpointerexception.printStackTrace();
          goto _L1
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onCreate()
    {
        mBroadcastReceiver = new MtvBroadcastReceiver();
        mMediaBroadcastReceiver = new MtvMediaBroadcastReceiver();
        getApplicationContext().registerReceiver(mBroadcastReceiver, broadcastIntentFilters);
        getApplicationContext().registerReceiver(mMediaBroadcastReceiver, broadcastMediaIntentFilters);
        super.onCreate();
    }

    public void onDestroy()
    {
        getApplicationContext().unregisterReceiver(mBroadcastReceiver);
        getApplicationContext().unregisterReceiver(mMediaBroadcastReceiver);
        mLastBatteryIntent = null;
        mBroadcastReceiver = null;
        mMediaBroadcastReceiver = null;
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        return 1;
    }

    public void registerListener(onMtvAppAndroidServiceListener onmtvappandroidservicelistener)
    {
        if(mListeners.size() > 0)
        {
            int i = -1 + mListeners.size();
            if(((onMtvAppAndroidServiceListener)mListeners.get(i)).getClass().equals(onmtvappandroidservicelistener.getClass()))
            {
                mListeners.remove(i);
                MtvUtilDebug.Low("MtvAppAndroidService", "removed last duplicate listener... ");
            }
        }
        attach(onmtvappandroidservicelistener);
        MtvUtilDebug.Low("MtvAppAndroidService", (new StringBuilder()).append("registerListener mListeners.size()= ").append(mListeners.size()).toString());
        notifyBaseListener(2, mLastBatteryIntent);
    }

    public void unregisterListener(onMtvAppAndroidServiceListener onmtvappandroidservicelistener)
    {
        detach(onmtvappandroidservicelistener);
        MtvUtilDebug.Low("MtvAppAndroidService", (new StringBuilder()).append("unregisterListener mListeners.size()= ").append(mListeners.size()).toString());
    }

    private static IntentFilter broadcastIntentFilters;
    private static IntentFilter broadcastMediaIntentFilters;
    private static MtvAppAndroidService mySingletonInstance = null;
    private final IBinder mBinder = new MtvAppAndroidServiceBinder(this);
    private MtvBroadcastReceiver mBroadcastReceiver;
    private Intent mLastBatteryIntent;
    protected volatile ArrayList mListeners;
    private MtvMediaBroadcastReceiver mMediaBroadcastReceiver;

    static 
    {
        broadcastIntentFilters = new IntentFilter();
        broadcastIntentFilters.addAction("android.intent.action.BATTERY_CHANGED");
        broadcastIntentFilters.addAction("android.intent.action.BATTERY_LOW");
        broadcastIntentFilters.addAction("android.intent.action.TIME_TICK");
        broadcastIntentFilters.addAction("android.intent.action.HEADSET_PLUG");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_LOW_BATTERY");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_SLEEP_TIMER_ALARM");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_SLEEP_TIMER_EXIT");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_CANCEL_EXIT");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END_DIALOG_CLOSE");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_BACKGROUND");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_FOREGROUND");
        broadcastIntentFilters.addAction("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE");
        broadcastIntentFilters.addAction("android.intent.action.HDMI_PLUGGED");
        broadcastMediaIntentFilters = new IntentFilter();
        broadcastMediaIntentFilters.addAction("android.intent.action.MEDIA_UNMOUNTED");
        broadcastMediaIntentFilters.addAction("android.intent.action.MEDIA_EJECT");
        broadcastMediaIntentFilters.addAction("android.intent.action.MEDIA_MOUNTED");
    }


/*
    static Intent access$202(MtvAppAndroidService mtvappandroidservice, Intent intent)
    {
        mtvappandroidservice.mLastBatteryIntent = intent;
        return intent;
    }

*/

}
