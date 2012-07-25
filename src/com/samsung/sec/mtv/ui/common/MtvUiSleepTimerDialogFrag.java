// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TimePicker;
import com.samsung.sec.mtv.utility.MtvPreferences;
import java.util.Calendar;

public class MtvUiSleepTimerDialogFrag extends DialogFragment
{

    public MtvUiSleepTimerDialogFrag()
    {
        mPreferences = null;
        mListener = null;
    }

    private AlertDialog createDialogByType(int i)
    {
        AlertDialog alertdialog = null;
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 30
    //                   1 321
    //                   2 486;
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
        JVM INSTR lookupswitch 5: default 240
    //                   0: 291
    //                   10: 297
    //                   30: 303
    //                   60: 309
    //                   120: 315;
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
        return mNewCustomSleepTime;
    }

    public static MtvUiSleepTimerDialogFrag newInstance(Bundle bundle)
    {
        MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag = new MtvUiSleepTimerDialogFrag();
        mtvuisleeptimerdialogfrag.setArguments(bundle);
        return mtvuisleeptimerdialogfrag;
    }

    public static void setAlarm(Context context, long l, boolean flag)
    {
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
        boolean flag = true;
        i;
        JVM INSTR tableswitch 0 4: default 36
    //                   0 76
    //                   1 86
    //                   2 95
    //                   3 104
    //                   4 113;
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
        mNewCustomSleepTime = j + i * 60;
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
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
    static Context access$002(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, Context context)
    {
        mtvuisleeptimerdialogfrag.mContext = context;
        return context;
    }

*/





/*
    static int access$402(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, int i)
    {
        mtvuisleeptimerdialogfrag.mNewCustomSleepTime = i;
        return i;
    }

*/





/*
    static MtvUiFrag.IMtvFragEventListener access$702(MtvUiSleepTimerDialogFrag mtvuisleeptimerdialogfrag, MtvUiFrag.IMtvFragEventListener imtvfrageventlistener)
    {
        mtvuisleeptimerdialogfrag.mListener = imtvfrageventlistener;
        return imtvfrageventlistener;
    }

*/

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", (new StringBuilder()).append("which button = ").append(String.valueOf(i)).toString());
            if(i == 5)
            {
                mContext = getActivity().getApplicationContext();
                AlertDialog alertdialog = createDialogByType(1);
                alertdialog.show();
                alertdialog.getWindow().addFlags(1024);
            } else
            {
                setAutoTimer(getActivity(), i);
            }
            dialoginterface.dismiss();
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls1()
        {
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls2
        implements android.widget.TimePicker.OnTimeChangedListener
    {

        public void onTimeChanged(TimePicker timepicker, int i, int j)
        {
            if(i >= 12)
            {
                i %= 12;
                timePicker.setCurrentHour(Integer.valueOf(i));
            }
            timePicker.setCurrentHour(Integer.valueOf(i));
            setCustomSleepTime(i, j);
        }

        final MtvUiSleepTimerDialogFrag this$0;
        final TimePicker val$timePicker;

        _cls2()
        {
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
            timePicker.requestFocus();
            mNewCustomSleepTime = getCustomSleepTime();
            setAutoTimer(mContext, 5);
            if(mContext == null)
                MtvUtilDebug.Low("MtvUiSleepTimerDialogFrag", "CONTEXT NULL");
            mContext = null;
        }

        final MtvUiSleepTimerDialogFrag this$0;
        final TimePicker val$timePicker;

        _cls4()
        {
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
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls3()
        {
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls6
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            mListener.onFragEvent(231, null);
            mListener = null;
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls6()
        {
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }


    private class _cls5
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            MtvUiSleepTimerDialogFrag.setAlarm(getActivity(), mPreferences.getAutoPowerOffTime(), true);
            mListener = null;
        }

        final MtvUiSleepTimerDialogFrag this$0;

        _cls5()
        {
            this$0 = MtvUiSleepTimerDialogFrag.this;
            super();
        }
    }

}
