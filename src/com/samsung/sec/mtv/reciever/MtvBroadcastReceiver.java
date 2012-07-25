// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.reciever;

import android.app.ActivityManager;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.view.KeyEvent;
import com.samsung.sec.mtv.provider.MtvReservation;
import com.samsung.sec.mtv.provider.MtvReservationManager;
import com.samsung.sec.mtv.ui.TestMode.MtvUiTestMode;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilSetReservationAlarm;
import java.util.List;

public class MtvBroadcastReceiver extends BroadcastReceiver
{

    public MtvBroadcastReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
_L1:
        return;
        if(keyevent == null || keyevent.getAction() != 0);
          goto _L1
        {
            mContext = context;
            String s = intent.getAction();
            Bundle bundle = intent.getExtras();
            boolean flag = s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_REMINDER");
            int i;
            MtvReservation mtvreservation;
            List list;
            int j;
            boolean flag1;
            MtvPreferences mtvpreferences;
            Intent intent1;
            MtvPreferences mtvpreferences1;
            Intent intent2;
            Context context1;
            long l;
            boolean aflag[];
            boolean flag2;
            Context context2;
            long l1;
            boolean aflag1[];
            boolean flag3;
            int k;
            KeyEvent keyevent;
            if(bundle != null)
                i = bundle.getInt("dbId", -1);
            else
                i = -1;
            mtvreservation = MtvReservationManager.find(mContext, i);
            MtvUtilDebug.Low("MtvBroadcastReciever", (new StringBuilder()).append("onReceive() Action : ").append(s).toString());
            if("android.intent.action.MEDIA_BUTTON".equals(intent.getAction()))
            {
                keyevent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                break MISSING_BLOCK_LABEL_98;
            }
            if(s.equals("android.provider.Telephony.SECRET_CODE"))
            {
                String s1 = intent.getData().getHost();
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low("MtvBroadcastReciever", (new StringBuilder()).append("host is ").append(s1).toString());
                if(s1.equals("4732"))
                {
                    Intent intent3 = new Intent();
                    intent3.setClass(context, com/samsung/sec/mtv/ui/TestMode/MtvUiTestMode);
                    intent3.setFlags(0x10000000);
                    MtvUtilDebug.Low("MtvBroadcastReciever", "Start Activity");
                    context.startActivity(intent3);
                }
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_REMINDER") || s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_START"))
            {
                if(mtvreservation == null)
                    MtvUtilDebug.Low("MtvBroadcastReciever", "onReceive() : invalid reservation ID");
                else
                if(flag)
                {
                    if(mtvreservation.mTimeStart - 20000L >= System.currentTimeMillis() - 5000L && mtvreservation.mPgmStatus == 0)
                        break MISSING_BLOCK_LABEL_351;
                    MtvUtilDebug.Low("MtvBroadcastReciever", "onReceive() : ACTION_MTV_RESERVATION_REMINDER-reservation start times already expired /most probably user changed system time");
                } else
                {
                    if(mtvreservation.mTimeStart >= System.currentTimeMillis() - 5000L && mtvreservation.mPgmStatus == 0)
                        break MISSING_BLOCK_LABEL_351;
                    MtvUtilDebug.Low("MtvBroadcastReciever", "onReceive() : ACTION_MTV_RESERVATION_START-reservation start times already expired /most probably user changed system time");
                    if(mtvreservation.mPgmStatus == 0 || mtvreservation.mPgmStatus == 6)
                        MtvReservationManager.UpdateStatus(context, mtvreservation, 2);
                }
            } else
            if(s.equals("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END"))
                MtvUtilDebug.Low("MtvBroadcastReciever", "onReceive() reservation end ,not handling it here- let service reciver service this");
            else
            if(s.equals("android.intent.action.BOOT_COMPLETED"))
            {
                MtvUtilDebug.Low("MtvBroadcastReciever", "BOOT COMPLETED Update the status for reservation");
                MtvUtilSetReservationAlarm.setReservationOnBoot(mContext);
            }
        }
          goto _L1
        MtvUtilDebug.High("MtvBroadcastReciever", "OnRecieve: Power lock release");
        mCpuWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(0x10000006, "MobileTV");
        mCpuWakeLock.acquire();
        mHandler = new Handler();
        mHandler.postDelayed(mRunnableReleaseLock, 1000L);
        list = ((ActivityManager)context.getSystemService("activity")).getRecentTasks(50, 0x800000);
        j = 0;
_L4:
        if(j >= list.size())
            break MISSING_BLOCK_LABEL_1160;
        k = ((android.app.ActivityManager.RecentTaskInfo)list.get(j)).baseIntent.toString().indexOf("cmp=");
        if(!((android.app.ActivityManager.RecentTaskInfo)list.get(j)).baseIntent.toString().substring(k, k + 24).equals("cmp=com.samsung.sec.mtv/") || ((android.app.ActivityManager.RecentTaskInfo)list.get(j)).id == -1) goto _L3; else goto _L2
_L2:
        MtvUtilDebug.High("MtvBroadcastReciever", "Mobile Tv Already running!!!");
        flag1 = true;
_L5:
        mtvpreferences = new MtvPreferences(context);
        if(flag && !flag1)
        {
            intent1 = new Intent();
            intent1.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"));
            intent1.setAction("android.intent.action.MAIN");
            intent1.addCategory("android.intent.category.LAUNCHER");
            intent1.setFlags(0x10000004);
            mtvpreferences.setComingReservationID(i);
            mtvpreferences.setLatestPChannel(mtvreservation.mPch);
            mtvpreferences.setLatestVChannel(mtvreservation.mVch);
            mtvpreferences.setIsDtvStartedByReminderAlert(true);
            mtvpreferences.setLaunchAntennaAlert(true);
            MtvUtilDebug.High("MtvBroadcastReciever", "Mobile Tv - Reminder -App Start dispatch");
        } else
        if(flag && flag1)
        {
            mtvpreferences.setIsDtvStartedByReminderAlert(false);
            mtvpreferences.setLaunchAntennaAlert(false);
            context2 = mContext;
            l1 = mtvreservation.mTimeStart;
            aflag1 = new boolean[1];
            aflag1[0] = true;
            if(MtvReservationManager.find(context2, l1, aflag1) != null && mtvpreferences.getReserveAlertFrom() == 0)
                flag3 = true;
            else
                flag3 = false;
            if(flag3)
                mtvpreferences.setComingReservationID(i);
            intent1 = null;
        } else
        if(!flag && flag1)
        {
            intent2 = new Intent();
            intent2.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiPopUpActivity"));
            intent2.putExtra("dbId", i);
            intent2.putExtra("popup_type", 2);
            intent2.setFlags(0x10000004);
            context1 = mContext;
            l = mtvreservation.mTimeStart;
            aflag = new boolean[1];
            aflag[0] = true;
            if(MtvReservationManager.find(context1, l, aflag) != null && mtvpreferences.getReserveAlertFrom() == 0)
                flag2 = true;
            else
                flag2 = false;
            if(mtvpreferences.getIsDtvStartedByReminderAlert() || flag2)
            {
                MtvUtilDebug.Mid("MtvBroadcastReciever", (new StringBuilder()).append("isBacktoBackOutofAppReserve? :").append(flag2).toString());
                mtvpreferences.setReservAlertFrom(0);
            } else
            {
                mtvpreferences.setReservAlertFrom(1);
            }
            mtvpreferences.setIsDtvStartedByReminderAlert(false);
            mtvpreferences.setLaunchAntennaAlert(false);
            MtvUtilDebug.High("MtvBroadcastReciever", "Mobile Tv - Reminder -user Alert");
            intent1 = intent2;
        } else
        if(!flag && !flag1)
        {
            intent1 = new Intent();
            intent1.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"));
            intent1.setAction("android.intent.action.MAIN");
            intent1.addCategory("android.intent.category.LAUNCHER");
            intent1.setFlags(0x10000004);
            intent1.putExtra("dbId", i);
            intent1.putExtra("youNeedToShowAlert", true);
            mtvpreferences1 = new MtvPreferences(context);
            mtvpreferences1.setLatestPChannel(mtvreservation.mPch);
            mtvpreferences1.setLatestVChannel(mtvreservation.mVch);
            mtvpreferences1.setIsDtvStartedByReminderAlert(false);
            mtvpreferences1.setLaunchAntennaAlert(false);
            mtvpreferences1.setReservAlertFrom(0);
        } else
        {
            intent1 = null;
        }
        if(intent1 != null)
            context.startActivity(intent1);
          goto _L1
_L3:
        j++;
          goto _L4
        flag1 = false;
          goto _L5
    }

    private Context mContext;
    private android.os.PowerManager.WakeLock mCpuWakeLock;
    private Handler mHandler;
    private final Runnable mRunnableReleaseLock = new _cls1();




    private class _cls1
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.High("MtvBroadcastReciever", "Wakelock release");
            mHandler.removeCallbacks(mRunnableReleaseLock);
            if(mCpuWakeLock.isHeld())
                mCpuWakeLock.release();
        }

        final MtvBroadcastReceiver this$0;

        _cls1()
        {
            this$0 = MtvBroadcastReceiver.this;
            super();
        }
    }

}
