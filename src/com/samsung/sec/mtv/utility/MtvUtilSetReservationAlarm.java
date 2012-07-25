// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.samsung.sec.mtv.provider.MtvReservation;
import com.samsung.sec.mtv.provider.MtvReservationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvPreferences

public class MtvUtilSetReservationAlarm
{

    public MtvUtilSetReservationAlarm()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilSetReservationAlarm;-><init>()V");
        super();
    }

    private static int calculateSetReserveTime(long l, long l1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilSetReservationAlarm;->calculateSetReserveTime(JJ)I");
        SimpleDateFormat simpledateformat = new SimpleDateFormat("d");
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("H");
        SimpleDateFormat simpledateformat2 = new SimpleDateFormat("mm");
        SimpleDateFormat simpledateformat3 = new SimpleDateFormat("ss");
        Calendar calendar = Calendar.getInstance();
        long l2 = l1 - l - (long)calendar.get(15) - (long)calendar.get(16);
        int i = Integer.parseInt(simpledateformat.format(new Date(l2)));
        int j = Integer.parseInt(simpledateformat1.format(new Date(l2)));
        int k = Integer.parseInt(simpledateformat2.format(new Date(l2)));
        int i1 = Integer.parseInt(simpledateformat3.format(new Date(l2)));
        if(i > 8)
            i = 1;
        return i1 + (24 * (60 * (60 * (i - 1))) + 60 * (j * 60) + k * 60);
    }

    public static void setReservationAlarm(Context context, long l, boolean flag, boolean flag1)
    {
        long l1;
        MtvReservation mtvreservation;
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilSetReservationAlarm;->setReservationAlarm(Landroid/content/Context;JZZ)V");
        l1 = System.currentTimeMillis();
        mtvreservation = MtvReservationManager.find(context, l, new boolean[0]);
        if(mtvreservation != null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Mid(TAG, "setReservationAlarm : invalid reservation request");
_L4:
        return;
_L2:
        int i = calculateSetReserveTime(l1, l);
        int j = calculateSetReserveTime(l1, mtvreservation.mTimeEnd);
        Intent intent = new Intent("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_START");
        Intent intent1 = new Intent("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_REMINDER");
        Intent intent2 = new Intent("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END");
        PendingIntent pendingintent = PendingIntent.getBroadcast(context, mtvreservation.mUriId, intent.putExtra("dbId", mtvreservation.mUriId), 0x8000000);
        PendingIntent pendingintent1 = PendingIntent.getBroadcast(context, mtvreservation.mUriId, intent1.putExtra("dbId", mtvreservation.mUriId), 0x8000000);
        PendingIntent pendingintent2 = PendingIntent.getBroadcast(context, mtvreservation.mUriId, intent2.putExtra("dbId", mtvreservation.mUriId), 0x8000000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(13, i - 5);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        calendar1.add(13, i - 20);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.add(13, j);
        if(flag)
        {
            AlarmManager alarmmanager1 = (AlarmManager)context.getSystemService("alarm");
            alarmmanager1.set(0, calendar.getTimeInMillis(), pendingintent);
            alarmmanager1.set(0, calendar1.getTimeInMillis(), pendingintent1);
            alarmmanager1.set(0, calendar2.getTimeInMillis(), pendingintent2);
            MtvUtilDebug.Mid(TAG, "setReservationAlarm : new alarm set ");
        } else
        {
            AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
            alarmmanager.cancel(pendingintent);
            alarmmanager.cancel(pendingintent1);
            alarmmanager.cancel(pendingintent2);
            if(flag1)
                MtvReservationManager.delete(context, l, null);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void setReservationOnBoot(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilSetReservationAlarm;->setReservationOnBoot(Landroid/content/Context;)V");
        MtvPreferences mtvpreferences = new MtvPreferences(context);
        if(mtvpreferences.getReservationAlertID() != -1)
            mtvpreferences.setReservationAlertID(-1);
        MtvReservation amtvreservation[] = MtvReservationManager.getAllReserves(context);
        long l = System.currentTimeMillis();
        if(amtvreservation != null && amtvreservation.length >= 1)
        {
            int i = 0;
            while(i < amtvreservation.length) 
            {
                if(amtvreservation[i].mTimeStart < l)
                {
                    MtvReservation mtvreservation = amtvreservation[i];
                    if(mtvreservation.mPgmStatus == 0 || mtvreservation.mPgmStatus == 6)
                        MtvReservationManager.UpdateStatus(context, mtvreservation, 3);
                } else
                {
                    setReservationAlarm(context, amtvreservation[i].mTimeStart, true, false);
                }
                i++;
            }
        }
    }

    private static String TAG = "MtvUtilSetReservationAlarm";

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/utility/MtvUtilSetReservationAlarm;-><clinit>()V");
    }
}
