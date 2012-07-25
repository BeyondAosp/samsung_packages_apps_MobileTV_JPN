// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.utility.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MtvUiManualReservation extends Activity
    implements android.view.View.OnClickListener, MtvUiFrag.IMtvFragEventListener
{
    private class MtvTimePickerDialog extends TimePickerDialog
    {

        public void onTimeChanged(TimePicker timepicker, int i, int j)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$MtvTimePickerDialog;->onTimeChanged(Landroid/widget/TimePicker;II)V");
            Log.d(MtvUiManualReservation.this).set(11, i);
            Log.d(MtvUiManualReservation.this).set(12, j);
        }

        final MtvUiManualReservation this$0;

        public MtvTimePickerDialog(Context context, android.app.TimePickerDialog.OnTimeSetListener ontimesetlistener, int i, int j, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$MtvTimePickerDialog;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;Landroid/content/Context;Landroid/app/TimePickerDialog$OnTimeSetListener;IIZ)V");
            this$0 = MtvUiManualReservation.this;
            super(context, ontimesetlistener, i, j, flag);
            Log.d(MtvUiManualReservation.this).set(11, i);
            Log.d(MtvUiManualReservation.this).set(12, j);
        }
    }

    private static final class PopupType extends Enum
    {

        public static PopupType valueOf(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;->valueOf(Ljava/lang/String;)Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;");
            return (PopupType)Enum.valueOf(com/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType, s);
        }

        public static PopupType[] values()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;->values()[Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;");
            return (PopupType[])$VALUES.clone();
        }

        private static final PopupType $VALUES[];
        public static final PopupType CHANNEL_NUMBER;
        public static final PopupType END_DATE;
        public static final PopupType END_TIME;
        public static final PopupType NONE;
        public static final PopupType RESRV_TYPE;
        public static final PopupType START_DATE;
        public static final PopupType START_TIME;

        static 
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;-><clinit>()V");
            NONE = new PopupType("NONE", 0);
            RESRV_TYPE = new PopupType("RESRV_TYPE", 1);
            CHANNEL_NUMBER = new PopupType("CHANNEL_NUMBER", 2);
            START_DATE = new PopupType("START_DATE", 3);
            END_DATE = new PopupType("END_DATE", 4);
            START_TIME = new PopupType("START_TIME", 5);
            END_TIME = new PopupType("END_TIME", 6);
            PopupType apopuptype[] = new PopupType[7];
            apopuptype[0] = NONE;
            apopuptype[1] = RESRV_TYPE;
            apopuptype[2] = CHANNEL_NUMBER;
            apopuptype[3] = START_DATE;
            apopuptype[4] = END_DATE;
            apopuptype[5] = START_TIME;
            apopuptype[6] = END_TIME;
            $VALUES = apopuptype;
        }

        private PopupType(String s, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$PopupType;-><init>(Ljava/lang/String;I)V");
            super(s, i);
        }
    }


    public MtvUiManualReservation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;-><init>()V");
        super();
        mCurrentPopupType = PopupType.NONE;
        mIsReserveFromEPG = false;
        mPrevReservation = null;
        mChannelListIndex = -1;
        mMtvPreferences = null;
        mSavedBundle = null;
        reserve_save_menu = null;
        reserve_cancel_menu = null;
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$2;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low("MtvUiManualReservation", "onServiceConnected...");
                Log.d(MtvUiManualReservation.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiManualReservation.this).registerListener(Log.d(MtvUiManualReservation.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$2;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiManualReservation.this).unregisterListener(Log.d(MtvUiManualReservation.this));
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3$1;->run()V");
                        finish();
                    }

                    final _cls3 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3;)V");
                            this$1 = _cls3.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        mStartTimeSetListener = new android.app.TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timepicker, int i, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$7;->onTimeSet(Landroid/widget/TimePicker;II)V");
                if((new Date(mStartYear, -1 + mStartMonth, mStartDay)).getTime() != getDefaultDate()) goto _L2; else goto _L1
_L1:
                int k;
                int i1;
                long l = System.currentTimeMillis();
                k = (new Date(l)).getHours();
                i1 = (new Date(l)).getMinutes();
                if(k <= i) goto _L4; else goto _L3
_L3:
                startTimeShouldBeSetAfterCurrentTimeAlert();
_L6:
                return;
_L4:
                if(k == i && i1 >= j)
                {
                    startTimeShouldBeSetAfterCurrentTimeAlert();
                    continue; /* Loop/switch isn't completed */
                }
_L2:
                mStartHour = i;
                mStartMinute = j;
                Log.d(MtvUiManualReservation.this).setText(Log.d(MtvUiManualReservation.this, mStartHour, mStartMinute));
                if(true) goto _L6; else goto _L5
_L5:
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$7;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        mEndTimeSetListener = new android.app.TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timepicker, int i, int j)
            {
                Date date;
                Date date1;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$8;->onTimeSet(Landroid/widget/TimePicker;II)V");
                date = new Date(mEndYear, -1 + mEndMonth, mEndDay);
                date1 = new Date(mStartYear, -1 + mStartMonth, mStartDay);
                if(date.getTime() != date1.getTime()) goto _L2; else goto _L1
_L1:
                int k;
                int l;
                k = mStartHour;
                l = mStartMinute;
                if(k <= i) goto _L4; else goto _L3
_L3:
                EndTimeShouldBeSetAfterStartTimeAlert();
_L6:
                return;
_L4:
                if(k == i && l >= j)
                {
                    EndTimeShouldBeSetAfterStartTimeAlert();
                    continue; /* Loop/switch isn't completed */
                }
_L2:
                mEndHour = i;
                mEndMinute = j;
                Log.d(MtvUiManualReservation.this).setText(Log.d(MtvUiManualReservation.this, mEndHour, mEndMinute));
                if(true) goto _L6; else goto _L5
_L5:
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$8;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        mStartDateSetListener = new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                Date date;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$9;->onDateSet(Landroid/widget/DatePicker;III)V");
                date = new Date(i, j, k);
                if(date.getTime() >= getDefaultDate()) goto _L2; else goto _L1
_L1:
                startTimeShouldBeSetAfterCurrentTimeAlert();
_L4:
                return;
_L2:
                if(date.getTime() == getDefaultDate())
                {
                    long l = System.currentTimeMillis();
                    int i1 = (new Date(l)).getHours();
                    int j1 = (new Date(l)).getMinutes();
                    if(i1 > mStartHour)
                    {
                        startTimeShouldBeSetAfterCurrentTimeAlert();
                        continue; /* Loop/switch isn't completed */
                    }
                    if(i1 == mStartHour && j1 >= mStartMinute)
                    {
                        startTimeShouldBeSetAfterCurrentTimeAlert();
                        continue; /* Loop/switch isn't completed */
                    }
                }
                if(date.getTime() - getDefaultDate() > 0x240c8400L)
                {
                    setStartTimeWithinOneWeekAlert();
                } else
                {
                    mStartYear = i;
                    mStartMonth = j + 1;
                    mStartDay = k;
                    Log.d(MtvUiManualReservation.this).setText(Log.d(MtvUiManualReservation.this, mStartYear, -1 + mStartMonth, mStartDay));
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$9;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        mEndDateSetListener = new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$10;->onDateSet(Landroid/widget/DatePicker;III)V");
                mEndYear = i;
                mEndMonth = j + 1;
                mEndDay = k;
                Log.d(MtvUiManualReservation.this).setText(Log.d(MtvUiManualReservation.this, mEndYear, -1 + mEndMonth, mEndDay));
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$10;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        mDismissListener = new android.content.DialogInterface.OnDismissListener() {

            public void onDismiss(DialogInterface dialoginterface)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$11;->onDismiss(Landroid/content/DialogInterface;)V");
                Log.d(MtvUiManualReservation.this, null);
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$11;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        popupSaveDate = Calendar.getInstance();
    }

    private MtvReservation convertEPGInfoToReserve(int i, String s, int j, String s1, String s2)
    {
        Date date1;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->convertEPGInfoToReserve(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)Lcom/samsung/sec/mtv/provider/MtvReservation;");
        int k = -1;
        long l = 0L;
        long l1 = 0L;
        Date date = null;
        String s3 = "";
        int i1;
        MtvChannel mtvchannel;
        SimpleDateFormat simpledateformat;
        Date date2;
        if(i == 1)
            i1 = 0;
        else
            i1 = 1;
        if(s != null)
            s3 = s;
        mtvchannel = MtvChannelManager.findByPChannel(this, j);
        if(mtvchannel != null)
            k = mtvchannel.mVirtualNum;
        simpledateformat = new SimpleDateFormat("yyyy/M/d H:mm");
        date = simpledateformat.parse(s1);
        date2 = simpledateformat.parse(s2);
        date1 = date2;
_L2:
        if(date != null)
            l = date.getTime();
        if(date1 != null)
            l1 = date1.getTime();
        return new MtvReservation(j, k, -1, l, l1, s3, "", i1, 0);
        ParseException parseexception;
        parseexception;
        MtvUtilDebug.Low("MtvUiManualReservation", "convertEPGInfoToReserve caught in date parsing");
        parseexception.printStackTrace();
        date1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private String formatDate(int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->formatDate(III)Ljava/lang/String;");
        SimpleDateFormat simpledateformat = (SimpleDateFormat)DateFormat.getDateFormat(getApplicationContext());
        simpledateformat.applyPattern((new StringBuilder()).append(simpledateformat.toPattern()).append(" (EEE)").toString());
        return simpledateformat.format(new Date(i - 1900, j, k));
    }

    private String formatTime(int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->formatTime(II)Ljava/lang/String;");
        SimpleDateFormat simpledateformat = new SimpleDateFormat("mm");
        Date date = new Date();
        date.setMinutes(j);
        String s = simpledateformat.format(date).toString();
        String s1;
        if(DateFormat.is24HourFormat(getApplicationContext()))
            s1 = (new StringBuilder()).append(i).append(" : ").append(s).toString();
        else
        if(i < 12)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Object obj;
            if(i == 0)
                obj = "12";
            else
                obj = Integer.valueOf(i);
            s1 = stringbuilder.append(obj).append(" : ").append(s).append(" ").append(DateUtils.getAMPMString(0).toUpperCase()).toString();
        } else
        {
            StringBuilder stringbuilder1 = new StringBuilder();
            Object obj1;
            if(i == 12)
                obj1 = "12";
            else
                obj1 = Integer.valueOf(i - 12);
            s1 = stringbuilder1.append(obj1).append(" : ").append(s).append(" ").append(DateUtils.getAMPMString(1).toUpperCase()).toString();
        }
        return s1;
    }

    private void initReservationVariables()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->initReservationVariables()V");
        mReservationType = -1;
        long l = System.currentTimeMillis();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy");
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("H");
        SimpleDateFormat simpledateformat2 = new SimpleDateFormat("M");
        SimpleDateFormat simpledateformat3 = new SimpleDateFormat("d");
        mStartYear = Integer.parseInt(simpledateformat.format(new Date(l)));
        mStartMonth = Integer.parseInt(simpledateformat2.format(new Date(l)));
        mStartDay = Integer.parseInt(simpledateformat3.format(new Date(l)));
        mStartHour = Integer.parseInt(simpledateformat1.format(new Date(l)));
        mStartMinute = 0;
        if(mStartHour < 23)
        {
            mStartHour = 1 + mStartHour;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(5, 1);
            long l1 = calendar.getTimeInMillis();
            mStartYear = Integer.parseInt(simpledateformat.format(new Date(l1)));
            mStartMonth = Integer.parseInt(simpledateformat2.format(new Date(l1)));
            mStartDay = Integer.parseInt(simpledateformat3.format(new Date(l1)));
            mStartHour = 0;
        }
        if(mIsReserveFromEPG)
        {
            Intent intent = getIntent();
            int i = intent.getIntExtra("EXTRA_RESERVATION_TYPE", -1);
            int j = intent.getIntExtra("EXTRA_RESERVATION_CHANNEL_NO", -1);
            String s = intent.getStringExtra("EXTRA_RESERVATION_START_DATE");
            String s1 = intent.getStringExtra("EXTRA_RESERVATION_END_DATE");
            setRemineReservation(convertEPGInfoToReserve(i, intent.getStringExtra("EXTRA_RESERVATION_PROGRAM_NAME"), j, s, s1));
        } else
        if(mPrevReservation != null)
            setRemineReservation(mPrevReservation);
        else
            setNewReserve();
    }

    private void initViewControl()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->initViewControl()V");
        mChannelNum = (Button)findViewById(0x7f0a0012);
        mDummyEditTextBox = (EditText)findViewById(0x7f0a0010);
        mProgramInputName = (EditText)findViewById(0x7f0a0017);
        mReserveStartDate = (Button)findViewById(0x7f0a0013);
        mReserveEndDate = (Button)findViewById(0x7f0a0015);
        mReserveStartTime = (Button)findViewById(0x7f0a0014);
        mReserveEndTime = (Button)findViewById(0x7f0a0016);
        mReserveType = (Button)findViewById(0x7f0a0011);
        mProgramInputName.setImeOptions(6);
        mChannelNum.setOnClickListener(this);
        mReserveStartDate.setOnClickListener(this);
        mReserveStartTime.setOnClickListener(this);
        mReserveEndDate.setOnClickListener(this);
        mReserveEndTime.setOnClickListener(this);
        mReserveType.setOnClickListener(this);
    }

    private void reloadReservationVariables(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->reloadReservationVariables(Landroid/os/Bundle;)V");
        mChannelListIndex = bundle.getInt("ChannelListIndex");
        mChannelNum.setText(getDisplayChannelText(mChannelListIndex));
        if(mChannelListIndex > -1)
        {
            mPch = mChannelsList[mChannelListIndex].mPhysicalNum;
            mVch = mChannelsList[mChannelListIndex].mVirtualNum;
        }
        Calendar calendar = (Calendar)bundle.getSerializable("mStartDate");
        mStartYear = calendar.get(1);
        mStartMonth = calendar.get(2);
        mStartDay = calendar.get(5);
        mStartHour = calendar.get(11);
        mStartMinute = calendar.get(12);
        mReserveStartDate.setText(formatDate(mStartYear, -1 + mStartMonth, mStartDay));
        mReserveStartTime.setText(formatTime(mStartHour, mStartMinute));
        Calendar calendar1 = (Calendar)bundle.getSerializable("mEndDate");
        mEndYear = calendar1.get(1);
        mEndMonth = calendar1.get(2);
        mEndDay = calendar1.get(5);
        mEndHour = calendar1.get(11);
        mEndMinute = calendar1.get(12);
        mReserveEndDate.setText(formatDate(mEndYear, -1 + mEndMonth, mEndDay));
        mReserveEndTime.setText(formatTime(mEndHour, mEndMinute));
        mReservationType = bundle.getInt("mReservationType");
        if(mReservationType == 1)
            mReserveType.setText(0x7f0702b3);
        else
            mReserveType.setText(0x7f07008a);
        mProgramInputName.setText(bundle.getString("mProgramInputName"));
    }

    private void reset()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->reset()V");
        mIsReserveFromEPG = false;
        mReserveDialog = null;
        mPrevReservation = null;
        mChannelsList = null;
        mChannelListIndex = -1;
        mMtvPreferences = null;
        mSavedBundle = null;
    }

    private void saveManualReserve()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->saveManualReserve()V");
        long l = (new Date(-1900 + mStartYear, -1 + mStartMonth, mStartDay, mStartHour, mStartMinute, 0)).getTime();
        long l1 = (new Date(-1900 + mEndYear, -1 + mEndMonth, mEndDay, mEndHour, mEndMinute, 0)).getTime();
        String s = ((EditText)findViewById(0x7f0a0017)).getText().toString();
        MtvReservation mtvreservation = new MtvReservation(mPch, mVch, -1, l, l1, s, "", mReservationType, 0);
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiManualReservation", (new StringBuilder()).append("saving manualReserve this.mPch = ").append(mPch).toString());
        Intent intent;
        if(mPrevReservation != null)
            MtvReservationManager.update(this, mtvreservation, mPrevReservation.mUriId);
        else
            MtvReservationManager.updateOrInsert(this, mtvreservation);
        MtvUtilSetReservationAlarm.setReservationAlarm(this, mtvreservation.mTimeStart, true, false);
        Toast.makeText(this, getString(0x7f0702ae), 0).show();
        intent = new Intent();
        intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.common.MtvUiPopUpActivity"));
        intent.putExtra("popup_type", 3);
        intent.putExtra("Alert_title", true);
        intent.setFlags(0x10000000);
        MtvUtilDebug.High("MtvUiManualReservation", "Mobile Tv Lanuch antenna");
        startActivity(intent);
        reset();
        finish();
    }

    private void setRemineReservation(MtvReservation mtvreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->setRemineReservation(Lcom/samsung/sec/mtv/provider/MtvReservation;)V");
        boolean flag = false;
        int i = 0;
        do
        {
label0:
            {
                if(i < mChannelsList.length)
                {
                    if(mChannelsList[i].mPhysicalNum != mtvreservation.mPch)
                        break label0;
                    mChannelNum.setText(getDisplayChannelText(i));
                    mPch = mChannelsList[i].mPhysicalNum;
                    mVch = mChannelsList[i].mVirtualNum;
                    mChannelListIndex = i;
                    flag = true;
                }
                if(!flag)
                {
                    mChannelNum.setText((new StringBuilder()).append(getString(0x7f0702ac)).append(mtvreservation.mPch).toString());
                    mPch = mtvreservation.mPch;
                    mVch = -1;
                }
                mProgramInputName.setText(mtvreservation.mPgmName);
                SimpleDateFormat simpledateformat = (SimpleDateFormat)DateFormat.getDateFormat(getApplicationContext());
                simpledateformat.applyPattern((new StringBuilder()).append(simpledateformat.toPattern()).append(" (EEE)").toString());
                mReserveStartDate.setText(simpledateformat.format(new Date(mtvreservation.mTimeStart)));
                mReserveEndDate.setText(simpledateformat.format(new Date(mtvreservation.mTimeEnd)));
                SimpleDateFormat simpledateformat1 = new SimpleDateFormat("H");
                SimpleDateFormat simpledateformat2 = new SimpleDateFormat("mm");
                SimpleDateFormat simpledateformat3;
                SimpleDateFormat simpledateformat4;
                SimpleDateFormat simpledateformat5;
                if(DateFormat.is24HourFormat(getApplicationContext()))
                {
                    mReserveStartTime.setText((new StringBuilder()).append(simpledateformat1.format(new Date(mtvreservation.mTimeStart))).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeStart))).toString());
                } else
                {
                    int j = Integer.parseInt(simpledateformat1.format(new Date(mtvreservation.mTimeStart)));
                    if(j < 12)
                    {
                        Button button3 = mReserveStartTime;
                        StringBuilder stringbuilder3 = new StringBuilder();
                        Object obj3;
                        if(j == 0)
                            obj3 = "12";
                        else
                            obj3 = Integer.valueOf(j);
                        button3.setText(stringbuilder3.append(obj3).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeStart))).append(" ").append(DateUtils.getAMPMString(0).toUpperCase()).toString());
                    } else
                    {
                        Button button = mReserveStartTime;
                        StringBuilder stringbuilder = new StringBuilder();
                        Object obj;
                        if(j == 12)
                            obj = "12";
                        else
                            obj = Integer.valueOf(j - 12);
                        button.setText(stringbuilder.append(obj).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeStart))).append(" ").append(DateUtils.getAMPMString(1).toUpperCase()).toString());
                    }
                }
                if(DateFormat.is24HourFormat(getApplicationContext()))
                {
                    mReserveEndTime.setText((new StringBuilder()).append(simpledateformat1.format(new Date(mtvreservation.mTimeEnd))).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeEnd))).toString());
                } else
                {
                    int k = Integer.parseInt(simpledateformat1.format(new Date(mtvreservation.mTimeEnd)));
                    if(k < 12)
                    {
                        Button button2 = mReserveEndTime;
                        StringBuilder stringbuilder2 = new StringBuilder();
                        Object obj2;
                        if(k == 0)
                            obj2 = "12";
                        else
                            obj2 = Integer.valueOf(k);
                        button2.setText(stringbuilder2.append(obj2).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeEnd))).append(" ").append(DateUtils.getAMPMString(0).toUpperCase()).toString());
                    } else
                    {
                        Button button1 = mReserveEndTime;
                        StringBuilder stringbuilder1 = new StringBuilder();
                        Object obj1;
                        if(k == 12)
                            obj1 = "12";
                        else
                            obj1 = Integer.valueOf(k - 12);
                        button1.setText(stringbuilder1.append(obj1).append(" : ").append(simpledateformat2.format(new Date(mtvreservation.mTimeEnd))).append(" ").append(DateUtils.getAMPMString(1).toUpperCase()).toString());
                    }
                }
                if(mtvreservation.mPgmType == 0)
                {
                    mReserveType.setText(0x7f07008a);
                    mReservationType = 0;
                } else
                {
                    mReserveType.setText(0x7f0702b3);
                    mReservationType = 1;
                }
                simpledateformat3 = new SimpleDateFormat("yyyy");
                simpledateformat4 = new SimpleDateFormat("M");
                simpledateformat5 = new SimpleDateFormat("d");
                mStartYear = Integer.parseInt(simpledateformat3.format(new Date(mtvreservation.mTimeStart)));
                mStartMonth = Integer.parseInt(simpledateformat4.format(new Date(mtvreservation.mTimeStart)));
                mStartDay = Integer.parseInt(simpledateformat5.format(new Date(mtvreservation.mTimeStart)));
                mStartHour = Integer.parseInt(simpledateformat1.format(new Date(mtvreservation.mTimeStart)));
                mStartMinute = Integer.parseInt(simpledateformat2.format(new Date(mtvreservation.mTimeStart)));
                mEndYear = Integer.parseInt(simpledateformat3.format(new Date(mtvreservation.mTimeEnd)));
                mEndMonth = Integer.parseInt(simpledateformat4.format(new Date(mtvreservation.mTimeEnd)));
                mEndDay = Integer.parseInt(simpledateformat5.format(new Date(mtvreservation.mTimeEnd)));
                mEndHour = Integer.parseInt(simpledateformat1.format(new Date(mtvreservation.mTimeEnd)));
                mEndMinute = Integer.parseInt(simpledateformat2.format(new Date(mtvreservation.mTimeEnd)));
                checkInputAllField(true);
                return;
            }
            i++;
        } while(true);
    }

    private void showMtvDialog(int i)
    {
        int j;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->showMtvDialog(I)V");
        j = 0;
        i;
        JVM INSTR tableswitch 0 5: default 52
    //                   0 53
    //                   1 304
    //                   2 363
    //                   3 160
    //                   4 423
    //                   5 482;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        return;
_L2:
        mCurrentPopupType = PopupType.RESRV_TYPE;
        android.app.AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f07029f);
        if(mReservationType != 0)
            j = 1;
        mReserveDialog = builder.setSingleChoiceItems(0x7f050010, j, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$5;->onClick(Landroid/content/DialogInterface;I)V");
                if(k == 0)
                {
                    mReservationType = 0;
                    ((Button)findViewById(0x7f0a0011)).setText(0x7f07008a);
                } else
                {
                    mReservationType = 1;
                    ((Button)findViewById(0x7f0a0011)).setText(0x7f0702b3);
                }
                dialoginterface.dismiss();
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$5;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$4;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        }).create();
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        mReserveDialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L5:
        mCurrentPopupType = PopupType.CHANNEL_NUMBER;
        final CharSequence channelNameList[] = new CharSequence[mChannelsList.length];
        for(; j < mChannelsList.length; j++)
        {
            channelNameList[j] = getDisplayChannelText(j);
            if(channelNameList[j].equals(mChannelNum.getText()))
                mChannelListIndex = j;
        }

        mReserveDialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702af).setSingleChoiceItems(channelNameList, mChannelListIndex, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$6;->onClick(Landroid/content/DialogInterface;I)V");
                String s = (String)channelNameList[k];
                Log.d(MtvUiManualReservation.this).setText(s);
                Log.d(MtvUiManualReservation.this, k);
                mPch = Log.d(MtvUiManualReservation.this)[Log.d(MtvUiManualReservation.this)].mPhysicalNum;
                mVch = Log.d(MtvUiManualReservation.this)[Log.d(MtvUiManualReservation.this)].mVirtualNum;
                checkInputAllField(true);
                dialoginterface.dismiss();
            }

            final MtvUiManualReservation this$0;
            final CharSequence val$channelNameList[];

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$6;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;[Ljava/lang/CharSequence;)V");
                this$0 = MtvUiManualReservation.this;
                channelNameList = acharsequence;
                super();
            }
        }).setNegativeButton(0x7f070035, null).create();
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        mReserveDialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L3:
        mCurrentPopupType = PopupType.START_DATE;
        mReserveDialog = new DatePickerDialog(this, mStartDateSetListener, mStartYear, -1 + mStartMonth, mStartDay);
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        continue; /* Loop/switch isn't completed */
_L4:
        mCurrentPopupType = PopupType.START_TIME;
        mReserveDialog = new MtvTimePickerDialog(this, mStartTimeSetListener, mStartHour, mStartMinute, DateFormat.is24HourFormat(getApplicationContext()));
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        continue; /* Loop/switch isn't completed */
_L6:
        mCurrentPopupType = PopupType.END_DATE;
        mReserveDialog = new DatePickerDialog(this, mEndDateSetListener, mEndYear, -1 + mEndMonth, mEndDay);
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        continue; /* Loop/switch isn't completed */
_L7:
        mCurrentPopupType = PopupType.END_TIME;
        mReserveDialog = new MtvTimePickerDialog(this, mEndTimeSetListener, mEndHour, mEndMinute, DateFormat.is24HourFormat(getApplicationContext()));
        mReserveDialog.setOnDismissListener(mDismissListener);
        mReserveDialog.show();
        if(true) goto _L1; else goto _L8
_L8:
    }

    public void EndTimeShouldBeSetAfterStartTimeAlert()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->EndTimeShouldBeSetAfterStartTimeAlert()V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(0x7f0702ad).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    public void checkInputAllField(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->checkInputAllField(Z)V");
        if(reserve_save_menu != null)
            if(flag)
                reserve_save_menu.setEnabled(true);
            else
                reserve_save_menu.setEnabled(false);
    }

    public void durationWrongAlert()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->durationWrongAlert()V");
        int i;
        String s;
        Object aobj[];
        String s1;
        if(mReservationType == 0)
            i = 0x7f0702a9;
        else
            i = 0x7f0702aa;
        s = getString(i);
        aobj = new Object[1];
        aobj[0] = Integer.valueOf(4);
        s1 = String.format(s, aobj);
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(s1).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    public long getDefaultDate()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->getDefaultDate()J");
        long l = System.currentTimeMillis();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy");
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("M");
        SimpleDateFormat simpledateformat2 = new SimpleDateFormat("d");
        int i = Integer.parseInt(simpledateformat.format(new Date(l)));
        int j = Integer.parseInt(simpledateformat1.format(new Date(l)));
        int k = Integer.parseInt(simpledateformat2.format(new Date(l)));
        return (new Date(i, j - 1, k)).getTime();
    }

    public String getDisplayChannelText(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->getDisplayChannelText(I)Ljava/lang/String;");
        String s;
        if(mChannelsList != null && i >= 0 && i < mChannelsList.length)
        {
            s = (new StringBuilder()).append(getString(0x7f0702ac)).append(mChannelsList[i].mPhysicalNum).append(" ").append(mChannelsList[i].mChannelName).toString();
        } else
        {
            MtvUtilDebug.High("MtvUiManualReservation", "getDisplayChannelText() : invalid index passed returning default channel name...");
            s = getString(0x7f0702a8);
        }
        return s;
    }

    public boolean isReservationAlreadyTime()
    {
        MtvReservation amtvreservation[];
        long l;
        long l1;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->isReservationAlreadyTime()Z");
        amtvreservation = (MtvReservation[])MtvReservationManager.getAllReserves(this);
        Date date = new Date(-1900 + mStartYear, -1 + mStartMonth, mStartDay, mStartHour, mStartMinute, 0);
        Date date1 = new Date(-1900 + mEndYear, -1 + mEndMonth, mEndDay, mEndHour, mEndMinute, 0);
        l = date.getTime();
        l1 = date1.getTime();
        Arrays.sort(amtvreservation);
        if(true) goto _L2; else goto _L1
_L1:
        int j = 0;
_L9:
        if(j >= amtvreservation.length)
            break MISSING_BLOCK_LABEL_419;
        if(amtvreservation[j].mTimeEnd <= System.currentTimeMillis()) goto _L4; else goto _L3
_L3:
        if(l != amtvreservation[j].mTimeStart || l1 != amtvreservation[j].mTimeEnd) goto _L6; else goto _L5
_L5:
        boolean flag;
        if(mPrevReservation != null && amtvreservation[j].mUriId == mPrevReservation.mUriId)
            flag = true;
        else
            flag = false;
_L8:
        return flag;
_L6:
        if(l >= amtvreservation[j].mTimeEnd || mPrevReservation != null && amtvreservation[j].mUriId == mPrevReservation.mUriId) goto _L4; else goto _L7
_L7:
        if(l1 <= amtvreservation[j].mTimeStart)
            flag = true;
        else
            flag = false;
          goto _L8
_L4:
        j++;
          goto _L9
_L2:
        int i = 0;
_L12:
        if(i >= amtvreservation.length) goto _L11; else goto _L10
_L10:
        if(amtvreservation[i].mTimeStart < System.currentTimeMillis())
            continue; /* Loop/switch isn't completed */
        if(l == amtvreservation[i].mTimeStart && l1 == amtvreservation[i].mTimeEnd)
        {
            if(mPrevReservation != null && amtvreservation[i].mUriId == mPrevReservation.mUriId)
                flag = true;
            else
                flag = false;
        } else
        {
            if(l >= amtvreservation[i].mTimeEnd || mPrevReservation != null && amtvreservation[i].mUriId == mPrevReservation.mUriId)
                continue; /* Loop/switch isn't completed */
            if(l1 <= amtvreservation[i].mTimeStart)
                flag = true;
            else
                flag = false;
        }
          goto _L8
        i++;
          goto _L12
_L11:
        flag = true;
          goto _L8
    }

    public int isReservationDurationValid()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->isReservationDurationValid()I");
        GregorianCalendar gregoriancalendar = new GregorianCalendar(mStartYear, -1 + mStartMonth, mStartDay, mStartHour, mStartMinute);
        GregorianCalendar gregoriancalendar1 = new GregorianCalendar(mEndYear, -1 + mEndMonth, mEndDay, mEndHour, mEndMinute);
        int i;
        if(gregoriancalendar1.getTimeInMillis() - gregoriancalendar.getTimeInMillis() > 0xdbba00L)
            i = 0;
        else
        if(gregoriancalendar1.getTimeInMillis() <= gregoriancalendar.getTimeInMillis())
            i = 2;
        else
            i = 1;
        return i;
    }

    public boolean isStartTimeGreaterThanCurrentTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->isStartTimeGreaterThanCurrentTime()Z");
        boolean flag = false;
        int i;
        int j;
        if((new Date(mStartYear, -1 + mStartMonth, mStartDay)).getTime() == getDefaultDate())
        {
            long l = System.currentTimeMillis();
            i = (new Date(l)).getHours();
            j = (new Date(l)).getMinutes();
            break MISSING_BLOCK_LABEL_74;
        }
        do
        {
            flag = true;
            if(true)
                do
                    return flag;
                while(i > mStartHour || i == mStartHour && j >= mStartMinute);
        } while(true);
    }

    public void onClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onClick(Landroid/view/View;)V");
        view.getId();
        JVM INSTR tableswitch 2131361809 2131361814: default 52
    //                   2131361809 93
    //                   2131361810 53
    //                   2131361811 61
    //                   2131361812 69
    //                   2131361813 77
    //                   2131361814 85;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        return;
_L3:
        showMtvDialog(3);
        continue; /* Loop/switch isn't completed */
_L4:
        showMtvDialog(1);
        continue; /* Loop/switch isn't completed */
_L5:
        showMtvDialog(2);
        continue; /* Loop/switch isn't completed */
_L6:
        showMtvDialog(4);
        continue; /* Loop/switch isn't completed */
_L7:
        showMtvDialog(5);
        continue; /* Loop/switch isn't completed */
_L2:
        showMtvDialog(0);
        if(true) goto _L1; else goto _L8
_L8:
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        mSavedBundle = bundle;
        setContentView(0x7f030003);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        mActionBar = getActionBar();
        mChannelsList = MtvChannelManager.getAllAvailableChannels(this);
        getWindow().setSoftInputMode(3);
        getWindow().addFlags(128);
        mMtvPreferences = new MtvPreferences(getApplicationContext());
        initViewControl();
        if(MtvUtilConfigSetting.EPGAPP_ENABLED)
        {
            Intent intent = getIntent();
            if(intent != null && intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_RESERVATION_DETAILS"))
                mIsReserveFromEPG = true;
        }
        if(!mIsReserveFromEPG)
        {
            Bundle bundle1 = getIntent().getExtras();
            MtvReservation mtvreservation;
            if(bundle1 != null)
                mtvreservation = MtvReservationManager.find(this, bundle1.getLong("MtvReservation"), new boolean[0]);
            else
                mtvreservation = null;
            if(mtvreservation != null)
                mPrevReservation = mtvreservation;
            else
                mPrevReservation = null;
        }
        if(bundle == null)
            initReservationVariables();
        else
            reloadReservationVariables(bundle);
        if(mChannelNum != null && mChannelsList != null && mChannelListIndex > -1)
            mChannelNum.setText(getDisplayChannelText(mChannelListIndex));
        else
            mChannelNum.setText(getString(0x7f0702a8));
        if(mChannelsList.length < 1)
            mChannelNum.setEnabled(false);
        else
            mChannelNum.setEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onCreateOptionsMenu(Landroid/view/Menu;)Z");
        boolean flag = false;
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(0x7f090001, menu);
        reserve_cancel_menu = menu.getItem(0);
        reserve_save_menu = menu.getItem(1);
        reserve_save_menu.setEnabled(false);
        if(mChannelNum != null)
        {
            String s = mChannelNum.getText().toString().trim();
            if(s.equals(getString(0x7f0702a8)) || s.equals(""))
                flag = true;
            if(!flag)
                reserve_save_menu.setEnabled(true);
        }
        android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener = new android.view.MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuitem)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$1;->onMenuItemClick(Landroid/view/MenuItem;)Z");
                menuitem.getItemId();
                JVM INSTR tableswitch 2131362100 2131362101: default 36
            //                           2131362100 130
            //                           2131362101 38;
                   goto _L1 _L2 _L3
_L1:
                return true;
_L3:
                if(isStartTimeGreaterThanCurrentTime())
                {
                    int i = isReservationDurationValid();
                    if(i == 1)
                    {
                        if(isReservationAlreadyTime())
                            Log.d(MtvUiManualReservation.this);
                        else
                            showAlreadyReserveDlg();
                    } else
                    if(i == 0)
                        durationWrongAlert();
                    else
                    if(i == 2)
                        EndTimeShouldBeSetAfterStartTimeAlert();
                } else
                {
                    startTimeShouldBeSetAfterCurrentTimeAlert();
                }
                continue; /* Loop/switch isn't completed */
_L2:
                Log.d(MtvUiManualReservation.this);
                finish();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvUiManualReservation this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
                this$0 = MtvUiManualReservation.this;
                super();
            }
        };
        reserve_save_menu.setOnMenuItemClickListener(onmenuitemclicklistener);
        reserve_cancel_menu.setOnMenuItemClickListener(onmenuitemclicklistener);
        return true;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onDestroy()V");
        if(mReserveDialog != null && mReserveDialog.isShowing())
        {
            mReserveDialog.dismiss();
            mReserveDialog = null;
        }
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        super.onDestroy();
    }

    public void onFragEvent(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onFragEvent(ILjava/lang/Object;)V");
        if(i == 276)
            finish();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onResume()V");
        super.onResume();
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        finish();
_L4:
        return;
_L2:
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        static class _cls12
        {

            static final int $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[];

            static 
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation$12;-><clinit>()V");
                $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType = new int[PopupType.values().length];
                NoSuchFieldError nosuchfielderror6;
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.RESRV_TYPE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.CHANNEL_NUMBER.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.START_DATE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.START_TIME.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.END_DATE.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.END_TIME.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                $SwitchMap$com$samsung$sec$mtv$ui$common$MtvUiManualReservation$PopupType[PopupType.NONE.ordinal()] = 7;
_L2:
                return;
                nosuchfielderror6;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        android.view.WindowManager.LayoutParams layoutparams;
        if(mIsReserveFromEPG)
            getWindow().setTitle(getString(0x7f0702a2));
        else
        if(mPrevReservation != null)
            getWindow().setTitle(getString(0x7f0702a3));
        else
            getWindow().setTitle(getString(0x7f0702a2));
        layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mMtvPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        if(MtvAreaManager.getCount(getApplicationContext()) == 0)
        {
            if(reserve_save_menu != null)
                reserve_save_menu.setEnabled(false);
            MtvUiDialogsFrag.EPGErrorDialogFragment.newInstance(0x7f0702d2, 0x7f070293).show(getFragmentManager(), "EPGErrorDialogFragment");
        }
        mDummyEditTextBox.requestFocus();
        if(mSavedBundle == null) goto _L4; else goto _L3
_L3:
        popupSaveDate = (Calendar)mSavedBundle.getSerializable("popupArgs");
        int i = popupSaveDate.get(1);
        int j = popupSaveDate.get(2);
        int k = popupSaveDate.get(5);
        int l = popupSaveDate.get(11);
        int i1 = popupSaveDate.get(12);
        switch(_cls12..SwitchMap.com.samsung.sec.mtv.ui.common.MtvUiManualReservation.PopupType[PopupType.valueOf(mSavedBundle.getString("popupType")).ordinal()])
        {
        case 1: // '\001'
            showMtvDialog(0);
            break;

        case 2: // '\002'
            showMtvDialog(3);
            break;

        case 3: // '\003'
            mCurrentPopupType = PopupType.START_DATE;
            mReserveDialog = new DatePickerDialog(this, mStartDateSetListener, i, j, k);
            mReserveDialog.setOnDismissListener(mDismissListener);
            mReserveDialog.show();
            break;

        case 4: // '\004'
            mCurrentPopupType = PopupType.START_TIME;
            mReserveDialog = new MtvTimePickerDialog(this, mStartTimeSetListener, l, i1, DateFormat.is24HourFormat(getApplicationContext()));
            mReserveDialog.setOnDismissListener(mDismissListener);
            mReserveDialog.show();
            break;

        case 5: // '\005'
            mCurrentPopupType = PopupType.END_DATE;
            mReserveDialog = new DatePickerDialog(this, mEndDateSetListener, i, j, k);
            mReserveDialog.setOnDismissListener(mDismissListener);
            mReserveDialog.show();
            break;

        case 6: // '\006'
            mCurrentPopupType = PopupType.END_TIME;
            mReserveDialog = new MtvTimePickerDialog(this, mEndTimeSetListener, l, i1, DateFormat.is24HourFormat(getApplicationContext()));
            mReserveDialog.setOnDismissListener(mDismissListener);
            mReserveDialog.show();
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        MtvUtilDebug.Low("MtvUiManualReservation", "onSaveInstanceState()");
        bundle.putInt("ChannelListIndex", mChannelListIndex);
        Calendar calendar = Calendar.getInstance();
        calendar.set(mStartYear, mStartMonth, mStartDay, mStartHour, mStartMinute);
        bundle.putSerializable("mStartDate", calendar);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute);
        bundle.putSerializable("mEndDate", calendar1);
        bundle.putInt("mReservationType", mReservationType);
        bundle.putString("mProgramInputName", mProgramInputName.getText().toString());
        if(mReserveDialog == null)
            mCurrentPopupType = PopupType.NONE;
        if(mReserveDialog instanceof DatePickerDialog)
        {
            DatePickerDialog datepickerdialog = (DatePickerDialog)mReserveDialog;
            popupSaveDate.set(1, datepickerdialog.getDatePicker().getYear());
            popupSaveDate.set(2, datepickerdialog.getDatePicker().getMonth());
            popupSaveDate.set(5, datepickerdialog.getDatePicker().getDayOfMonth());
        }
        bundle.putString("popupType", mCurrentPopupType.name());
        bundle.putSerializable("popupArgs", popupSaveDate);
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->onStop()V");
        MtvUtilAppService.resetMtvVisibiltySettings(this);
        super.onStop();
    }

    public void setNewReserve()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->setNewReserve()V");
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy/M/d");
        simpledateformat.applyPattern((new StringBuilder()).append(simpledateformat.toPattern()).append(" (EEE)").toString());
        mEndYear = mStartYear;
        mEndMonth = mStartMonth;
        mEndDay = mStartDay;
        mEndMinute = mStartMinute;
        if(mStartHour < 23)
        {
            mEndHour = 1 + mStartHour;
        } else
        {
            mEndHour = 0;
            SimpleDateFormat simpledateformat1 = new SimpleDateFormat("yyyy");
            SimpleDateFormat simpledateformat2 = new SimpleDateFormat("M");
            SimpleDateFormat simpledateformat3 = new SimpleDateFormat("d");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(5, 1);
            Long long1 = Long.valueOf(calendar.getTimeInMillis());
            mEndYear = Integer.parseInt(simpledateformat1.format(new Date(long1.longValue())));
            mEndMonth = Integer.parseInt(simpledateformat2.format(new Date(long1.longValue())));
            mEndDay = Integer.parseInt(simpledateformat3.format(new Date(long1.longValue())));
        }
        mReserveType.setText(getString(0x7f07008a));
        mReservationType = 0;
        mChannelNum.setText(getString(0x7f0702a8));
        mReserveStartDate.setText(simpledateformat.format(new Date(-1900 + mStartYear, -1 + mStartMonth, mStartDay)));
        mReserveEndDate.setText(simpledateformat.format(new Date(-1900 + mEndYear, -1 + mEndMonth, mEndDay)));
        mReserveStartTime.setText(formatTime(mStartHour, mStartMinute));
        mReserveEndTime.setText(formatTime(mEndHour, mEndMinute));
    }

    public void setStartTimeWithinOneWeekAlert()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->setStartTimeWithinOneWeekAlert()V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(0x7f0702b1).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    public void showAlreadyReserveDlg()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->showAlreadyReserveDlg()V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(0x7f0702b2).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    public void startTimeShouldBeSetAfterCurrentTimeAlert()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->startTimeShouldBeSetAfterCurrentTimeAlert()V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(0x7f0702b0).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    private onMtvAppAndroidServiceListener listener;
    public ActionBar mActionBar;
    private int mChannelListIndex;
    private Button mChannelNum;
    private MtvChannel mChannelsList[];
    private ServiceConnection mConnection;
    private PopupType mCurrentPopupType;
    private android.content.DialogInterface.OnDismissListener mDismissListener;
    private EditText mDummyEditTextBox;
    private android.app.DatePickerDialog.OnDateSetListener mEndDateSetListener;
    int mEndDay;
    int mEndHour;
    int mEndMinute;
    int mEndMonth;
    private android.app.TimePickerDialog.OnTimeSetListener mEndTimeSetListener;
    int mEndYear;
    boolean mIsReserveFromEPG;
    private MtvPreferences mMtvPreferences;
    int mPch;
    private MtvReservation mPrevReservation;
    private EditText mProgramInputName;
    int mReservationType;
    private AlertDialog mReserveDialog;
    private Button mReserveEndDate;
    private Button mReserveEndTime;
    private Button mReserveStartDate;
    private Button mReserveStartTime;
    private Button mReserveType;
    private Bundle mSavedBundle;
    private MtvAppAndroidService mService;
    private android.app.DatePickerDialog.OnDateSetListener mStartDateSetListener;
    int mStartDay;
    int mStartHour;
    int mStartMinute;
    int mStartMonth;
    private android.app.TimePickerDialog.OnTimeSetListener mStartTimeSetListener;
    int mStartYear;
    int mVch;
    private Calendar popupSaveDate;
    MenuItem reserve_cancel_menu;
    MenuItem reserve_save_menu;


/*
    static void access$000(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
        mtvuimanualreservation.saveManualReserve();
        return;
    }

*/


/*
    static void access$100(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)V");
        mtvuimanualreservation.reset();
        return;
    }

*/


/*
    static String access$1000(MtvUiManualReservation mtvuimanualreservation, int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$1000(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;III)Ljava/lang/String;");
        return mtvuimanualreservation.formatDate(i, j, k);
    }

*/


/*
    static Button access$1100(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$1100(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Landroid/widget/Button;");
        return mtvuimanualreservation.mReserveStartDate;
    }

*/


/*
    static Button access$1200(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$1200(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Landroid/widget/Button;");
        return mtvuimanualreservation.mReserveEndDate;
    }

*/


/*
    static AlertDialog access$1302(MtvUiManualReservation mtvuimanualreservation, AlertDialog alertdialog)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$1302(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;Landroid/app/AlertDialog;)Landroid/app/AlertDialog;");
        mtvuimanualreservation.mReserveDialog = alertdialog;
        return alertdialog;
    }

*/


/*
    static Calendar access$1400(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$1400(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Ljava/util/Calendar;");
        return mtvuimanualreservation.popupSaveDate;
    }

*/


/*
    static MtvAppAndroidService access$200(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuimanualreservation.mService;
    }

*/


/*
    static MtvAppAndroidService access$202(MtvUiManualReservation mtvuimanualreservation, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$202(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuimanualreservation.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$300(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$300(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuimanualreservation.listener;
    }

*/


/*
    static Button access$400(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$400(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Landroid/widget/Button;");
        return mtvuimanualreservation.mChannelNum;
    }

*/


/*
    static int access$500(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$500(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)I");
        return mtvuimanualreservation.mChannelListIndex;
    }

*/


/*
    static int access$502(MtvUiManualReservation mtvuimanualreservation, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$502(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;I)I");
        mtvuimanualreservation.mChannelListIndex = i;
        return i;
    }

*/


/*
    static MtvChannel[] access$600(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$600(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)[Lcom/samsung/sec/mtv/provider/MtvChannel;");
        return mtvuimanualreservation.mChannelsList;
    }

*/


/*
    static String access$700(MtvUiManualReservation mtvuimanualreservation, int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$700(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;II)Ljava/lang/String;");
        return mtvuimanualreservation.formatTime(i, j);
    }

*/


/*
    static Button access$800(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$800(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Landroid/widget/Button;");
        return mtvuimanualreservation.mReserveStartTime;
    }

*/


/*
    static Button access$900(MtvUiManualReservation mtvuimanualreservation)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;->access$900(Lcom/samsung/sec/mtv/ui/common/MtvUiManualReservation;)Landroid/widget/Button;");
        return mtvuimanualreservation.mReserveEndTime;
    }

*/
}
