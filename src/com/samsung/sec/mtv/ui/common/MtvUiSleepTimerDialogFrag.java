// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TimePicker;
import com.samsung.sec.mtv.utility.MtvPreferences;
import java.util.Calendar;

public class MtvUiSleepTimerDialogFrag extends DialogFragment
{

    public MtvUiSleepTimerDialogFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;-><init>()V");
        super();
        mPreferences = null;
        mListener = null;
    }

    private AlertDialog createDialogByType(int i)
    {
        AlertDialog alertdialog;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->createDialogByType(I)Landroid/app/AlertDialog;");
        alertdialog = null;
        i;
        JVM INSTR tableswitch 0 2: default 36
    //                   0 38
    //                   1 329
    //                   2 494;
           goto _L1 _L2 _L3 _L4
_L1:
        return alertdialog;
_L2:
        String as[];
        as = new String[6];
        int j = 0 + 1;
        as[0] = getString(0x7f070021);
        int k = j + 1;
        String s1 = getString(0x7f070023);
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(10);
        as[j] = String.format(s1, aobj);
        int l = k + 1;
        String s2 = getString(0x7f070023);
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(30);
        as[k] = String.format(s2, aobj1);
        int i1 = l + 1;
        as[l] = getString(0x7f070024);
        int j1 = i1 + 1;
        as[i1] = getString(0x7f070025);
        j1 + 1;
        as[j1] = getString(0x7f070026);
        mPreferences.getAutoPowerOffTime();
        JVM INSTR lookupswitch 5: default 248
    //                   0: 299
    //                   10: 305
    //                   30: 311
    //                   60: 317
    //                   120: 323;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        byte byte0 = 5;
_L11:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f070027).setSingleChoiceItems(as, byte0, new _cls1()).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 0;
        continue; /* Loop/switch isn't completed */
_L7:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L8:
        byte0 = 2;
        continue; /* Loop/switch isn't completed */
_L9:
        byte0 = 3;
        continue; /* Loop/switch isn't completed */
_L10:
        byte0 = 4;
        if(true) goto _L11; else goto _L3
_L3:
        View view = LayoutInflater.from(getActivity()).inflate(0x7f030033, null);
        final TimePicker timePicker = (TimePicker)view.findViewById(0x7f0a0131);
        timePicker.setIs24HourView(Boolean.valueOf(true));
        timePicker.setOnTimeChangedListener(new _cls2());
        if(mCurrentSleepTime == 0)
        {
            timePicker.setCurrentHour(Integer.valueOf(0));
            timePicker.setCurrentMinute(Integer.valueOf(0));
        } else
        {
            timePicker.setCurrentHour(Integer.valueOf(mCurrentSleepTime / 60));
            timePicker.setCurrentMinute(Integer.valueOf(mCurrentSleepTime % 60));
        }
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f070027).setView(view).setPositiveButton(0x7f070028, new _cls4()).setNegativeButton(0x7f070035, new _cls3()).create();
        continue; /* Loop/switch isn't completed */
_L4:
        String s = getString(0x7f07002a);
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f070027).setMessage(s).setPositiveButton(0x7f070034, new _cls6()).setNegativeButton(0x7f070035, new _cls5()).create();
        alertdialog.getWindow().addFlags(1024);
        if(true) goto _L1; else goto _L12
_L12:
    }

    private int getCustomSleepTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->getCustomSleepTime()I");
        return mNewCustomSleepTime;
    }

    public static MtvUiSleepTimerDialogFrag newInstance(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->newInstance(Landroid/os/Bundle;)Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;");
        MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag = new MtvUiSleepTimerDialogFrag();
        mtvuisleeptimerdialogfrag.setArguments(bundle);
        return mtvuisleeptimerdialogfrag;
    }

    public static void setAlarm(Context context, long l, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->setAlarm(Landroid/content/Context;JZ)V");
        MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", (new StringBuilder()).append("setAlarm aftertime_min ").append(l).toString());
        long l1 = l * 60L;
        if(l < 1L)
            flag = false;
        PendingIntent pendingintent = PendingIntent.getBroadcast(context, 1234, new Intent("com.samsung.sec.mtv.ACTION_MTV_SLEEP_TIMER_ALARM"), 0x8000000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(13, (int)l1);
        if(flag)
        {
            ((AlarmManager)context.getSystemService("alarm")).set(1, calendar.getTimeInMillis(), pendingintent);
            MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", (new StringBuilder()).append("auto-power off set : after min : ").append(l).toString());
        } else
        {
            ((AlarmManager)context.getSystemService("alarm")).cancel(pendingintent);
            MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", "cancelled auto-power off ");
        }
    }

    private void setAutoTimer(Context context, int i)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->setAutoTimer(Landroid/content/Context;I)V");
        flag = true;
        i;
        JVM INSTR tableswitch 0 4: default 48
    //                   0 89
    //                   1 100
    //                   2 109
    //                   3 118
    //                   4 127;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        mPreferences.setAutoPowerOffTime(mNewCustomSleepTime);
        setAlarm(context, mNewCustomSleepTime, flag);
        mListener.onFragEvent(230, null);
        mListener = null;
        return;
_L2:
        mNewCustomSleepTime = 0;
        flag = false;
        continue; /* Loop/switch isn't completed */
_L3:
        mNewCustomSleepTime = 10;
        continue; /* Loop/switch isn't completed */
_L4:
        mNewCustomSleepTime = 30;
        continue; /* Loop/switch isn't completed */
_L5:
        mNewCustomSleepTime = 60;
        continue; /* Loop/switch isn't completed */
_L6:
        mNewCustomSleepTime = 120;
        if(true) goto _L1; else goto _L7
_L7:
    }

    private void setCustomSleepTime(int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->setCustomSleepTime(II)V");
        mNewCustomSleepTime = j + i * 60;
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
        mPreferences = new MtvPreferences(getActivity().getApplicationContext());
        mListener = (MtvUiFrag.IMtvFragEventListener)getActivity();
        Bundle bundle1 = getArguments();
        AlertDialog alertdialog = null;
        if(bundle1 != null)
        {
            mCurrentSleepTime = bundle1.getInt("remainTime", 0);
            if(bundle1.getBoolean("expire", false))
                alertdialog = createDialogByType(2);
            else
                alertdialog = createDialogByType(0);
        }
        return alertdialog;
    }

    private Context mContext;
    private int mCurrentSleepTime;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private int mNewCustomSleepTime;
    private MtvPreferences mPreferences;


/*
    static Context access$000(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)Landroid/content/Context;");
        return mtvuisleeptimerdialogfrag.mContext;
    }

*/


/*
    static Context access$002(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$002(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;Landroid/content/Context;)Landroid/content/Context;");
        mtvuisleeptimerdialogfrag.mContext = context;
        return context;
    }

*/


/*
    static AlertDialog access$100(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;I)Landroid/app/AlertDialog;");
        return mtvuisleeptimerdialogfrag.createDialogByType(i);
    }

*/


/*
    static void access$200(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, Context context, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;Landroid/content/Context;I)V");
        mtvuisleeptimerdialogfrag.setAutoTimer(context, i);
        return;
    }

*/


/*
    static void access$300(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$300(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;II)V");
        mtvuisleeptimerdialogfrag.setCustomSleepTime(i, j);
        return;
    }

*/


/*
    static int access$402(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$402(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;I)I");
        mtvuisleeptimerdialogfrag.mNewCustomSleepTime = i;
        return i;
    }

*/


/*
    static int access$500(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$500(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)I");
        return mtvuisleeptimerdialogfrag.getCustomSleepTime();
    }

*/


/*
    static MtvPreferences access$600(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$600(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuisleeptimerdialogfrag.mPreferences;
    }

*/


/*
    static MtvUiFrag.IMtvFragEventListener access$700(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$700(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuisleeptimerdialogfrag.mListener;
    }

*/


/*
    static MtvUiFrag.IMtvFragEventListener access$702(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, MtvUiFrag.IMtvFragEventListener imtvfrageventlistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;->access$702(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        mtvuisleeptimerdialogfrag.mListener = imtvfrageventlistener;
        return imtvfrageventlistener;
    }

*/

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$1;->onClick(Landroid/content/DialogInterface;I)V");
            MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", (new StringBuilder()).append("which button = ").append(String.valueOf(i)).toString());
            if(i == 5)
            {
                Log.d(MtvUiSleepTimerDialogFrag.this, getActivity().getApplicationContext());
                AlertDialog alertdialog = Log.d(MtvUiSleepTimerDialogFrag.this, 1);
                alertdialog.show();
                alertdialog.getWindow().addFlags(1024);
            } else
            {
                Log.d(MtvUiSleepTimerDialogFrag.this, getActivity(), i);
            }
            dialoginterface.dismiss();
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls2
        implements android.widget.TimePicker.OnTimeChangedListener
    {

        public void onTimeChanged(TimePicker timepicker, int i, int j)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$2;->onTimeChanged(Landroid/widget/TimePicker;II)V");
            if(i >= 12)
            {
                i %= 12;
                timePicker.setCurrentHour(Integer.valueOf(i));
            }
            timePicker.setCurrentHour(Integer.valueOf(i));
            Log.d(MtvUiSleepTimerDialogFrag.this, i, j);
        }

        final MtvUiSleepTimerDialogFrag this$0;
        final TimePicker val$timePicker;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;Landroid/widget/TimePicker;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            timePicker = timepicker;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$4;->onClick(Landroid/content/DialogInterface;I)V");
            timePicker.requestFocus();
            Log.d(MtvUiSleepTimerDialogFrag.this, Log.d(MtvUiSleepTimerDialogFrag.this));
            Log.d(MtvUiSleepTimerDialogFrag.this, Log.d(MtvUiSleepTimerDialogFrag.this), 5);
            if(Log.d(MtvUiSleepTimerDialogFrag.this) == null)
                MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", "CONTEXT NULL");
            Log.d(MtvUiSleepTimerDialogFrag.this, null);
        }

        final MtvUiSleepTimerDialogFrag this$0;
        final TimePicker val$timePicker;

        _cls4()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;Landroid/widget/TimePicker;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            timePicker = timepicker;
            super();
        }
    }


    private class _cls3
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$3;->onClick(Landroid/content/DialogInterface;I)V");
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls3()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls6
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$6;->onClick(Landroid/content/DialogInterface;I)V");
            Log.d(MtvUiSleepTimerDialogFrag.this).onFragEvent(231, null);
            Log.d(MtvUiSleepTimerDialogFrag.this, null);
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls6()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$6;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls5
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$5;->onClick(Landroid/content/DialogInterface;I)V");
            MtvUiSleepTimerDialogFrag.setAlarm(getActivity(), Log.d(MtvUiSleepTimerDialogFrag.this).getAutoPowerOffTime(), true);
            Log.d(MtvUiSleepTimerDialogFrag.this, null);
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls5()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag$5;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSleepTimerDialogFrag;)V");
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }

}
