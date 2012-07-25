// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.Window;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.provider.MtvReservation;
import com.samsung.sec.mtv.provider.MtvReservationManager;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilAppService;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiSleepTimerDialogFrag

public class MtvUiPopUpActivity extends Activity
{
    private class MtvReservationAlertControl
    {

        private void cheakAndReEnableScreenLock()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;->cheakAndReEnableScreenLock()V");
            if(mKeyguardManager.inKeyguardRestrictedInputMode())
                mKeyguardLock.reenableKeyguard();
        }

        private void checkAndDisableScreenLock()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;->checkAndDisableScreenLock()V");
            mKeyguardManager = (KeyguardManager)Log.d(MtvUiPopUpActivity.this).getSystemService("keyguard");
            mKeyguardLock = mKeyguardManager.newKeyguardLock("keyguard");
            if(mKeyguardManager.inKeyguardRestrictedInputMode())
            {
                class _cls1
                    implements android.app.KeyguardManager.OnKeyguardExitResult
                {

                    public void onKeyguardExitResult(boolean flag)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl$1;->onKeyguardExitResult(Z)V");
                        if(flag)
                            MtvUtilDebug.High("MtvUiPopUpActivity", "onKeyguardExitResult: success");
                        else
                            MtvUtilDebug.High("MtvUiPopUpActivity", "onKeyguardExitResult: failed");
                    }

                    final MtvReservationAlertControl this$1;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;)V");
                    this$1 = MtvReservationAlertControl.this;
                    super();
                }
                }

                mKeyguardManager.exitKeyguardSecurely(new _cls1());
                mKeyguardLock.disableKeyguard();
            }
        }

        private android.app.KeyguardManager.KeyguardLock mKeyguardLock;
        private KeyguardManager mKeyguardManager;
        private final Runnable runnableReenterKeyguard;
        final MtvUiPopUpActivity this$0;


/*
        static Runnable access$1200(MtvReservationAlertControl mtvreservationalertcontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;->access$1200(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;)Ljava/lang/Runnable;");
            return mtvreservationalertcontrol.runnableReenterKeyguard;
        }

*/


/*
        static void access$1400(MtvReservationAlertControl mtvreservationalertcontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;->access$1400(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;)V");
            mtvreservationalertcontrol.cheakAndReEnableScreenLock();
            return;
        }

*/


/*
        static void access$700(MtvReservationAlertControl mtvreservationalertcontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;->access$700(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;)V");
            mtvreservationalertcontrol.checkAndDisableScreenLock();
            return;
        }

*/

        private MtvReservationAlertControl()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
            this$0 = MtvUiPopUpActivity.this;
            super();
            class _cls2
                implements Runnable
            {

                public void run()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl$2;->run()V");
                    Log.d(MtvReservationAlertControl.this);
                }

                final MtvReservationAlertControl this$1;

                _cls2()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;)V");
                    this$1 = MtvReservationAlertControl.this;
                    super();
                }
            }

            runnableReenterKeyguard = new _cls2();
        }

        MtvReservationAlertControl(_cls1 _pcls1)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$MtvReservationAlertControl;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$1;)V");
            this();
        }
    }


    public MtvUiPopUpActivity()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;-><init>()V");
        super();
        mDialog = null;
        mDialogBuilder = null;
        type = -1;
        mContext = null;
        AfetrreservationOk = false;
        AnatenaAlertfrom = false;
        mMtvReservationAlertControl = null;
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                int i;
                String s;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$2;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V");
                i = 1;
                s = intent.getAction();
                MtvUtilDebug.Low("MtvUiPopUpActivity", (new StringBuilder()).append("mIntentReceiver onReceive: action=").append(s).toString());
                break MISSING_BLOCK_LABEL_42;
                while(true) 
                {
                    do
                        return;
                    while(s == null || !s.equals("android.intent.action.BATTERY_CHANGED"));
                    if(intent.getIntExtra("status", i) != 2)
                        i = 0;
                    if(i != 0 && Log.d(MtvUiPopUpActivity.this) == 0)
                    {
                        Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                        Log.d(0);
                        finish();
                    }
                }
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        };
        RunnablePopupExpire = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$9;->run()V");
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                Log.d(MtvUiPopUpActivity.this);
                JVM INSTR tableswitch 0 3: default 60
            //                           0 61
            //                           1 78
            //                           2 95
            //                           3 112;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_LOW_BATTERY ");
                Log.d(MtvUiPopUpActivity.this);
                continue; /* Loop/switch isn't completed */
_L3:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_SLEEP_TIMER_EXIT ");
                Log.d(MtvUiPopUpActivity.this);
                continue; /* Loop/switch isn't completed */
_L4:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_RESERVATION_ALERT ");
                Log.d(MtvUiPopUpActivity.this);
                continue; /* Loop/switch isn't completed */
_L5:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_LAUNCH_ANTENNA_ALERT ");
                Log.d(MtvUiPopUpActivity.this);
                if(true) goto _L1; else goto _L6
_L6:
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$9;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        };
    }

    private void createPopup(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->createPopup(I)V");
        i;
        JVM INSTR tableswitch 0 3: default 40
    //                   0 48
    //                   1 124
    //                   2 229
    //                   3 448;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        MtvUtilDebug.Low("MtvUiPopUpActivity", "INVALID POPUP TYPE");
_L7:
        return;
_L2:
        if(!isBatteryLowPopupAvailable)
        {
            isBatteryLowPopupAvailable = true;
            mDialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0700a1).setMessage(0x7f07009f).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$3;->onClick(Landroid/content/DialogInterface;I)V");
                    MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_LOW_BATTERY onClick OK ");
                    Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                    Log.d(MtvUiPopUpActivity.this);
                }

                final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
            }).setCancelable(false).create();
            mPopupUiMsgHandler.postDelayed(RunnablePopupExpire, 5000L);
        } else
        {
            finish();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        String s1;
        if(MtvAppPlaybackContextManager.getInstance().getCurrentContext().getState().getOp() == 20487)
            s1 = getString(0x7f07002b);
        else
            s1 = getString(0x7f07002a);
        mDialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070027).setMessage(s1).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$5;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_SLEEP_TIMER_EXIT onClick OK ");
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                Log.d(MtvUiPopUpActivity.this);
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$5;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$4;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUiSleepTimerDialogFrag.setAlarm(getApplicationContext(), (new MtvPreferences(getApplicationContext())).getAutoPowerOffTime(), true);
                finish();
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        }).create();
        mPopupUiMsgHandler.postDelayed(RunnablePopupExpire, 5000L);
        continue; /* Loop/switch isn't completed */
_L4:
        mReservationId = getIntent().getExtras().getInt("dbId");
        mPreferences = new MtvPreferences(getApplicationContext());
        if(mMtvReservationAlertControl == null)
            mMtvReservationAlertControl = new MtvReservationAlertControl();
        Log.d(mMtvReservationAlertControl);
        mDialogBuilder = new android.app.AlertDialog.Builder(this);
        mDialogBuilder.setTitle(0x7f0702b5);
        mDialogBuilder.setMessage(0x7f0702b6);
        mDialogBuilder.setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$6;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                Log.d(MtvUiPopUpActivity.this);
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$6;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        });
        mDialogBuilder.setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$7;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                MtvReservation mtvreservation = MtvReservationManager.find(MtvUiPopUpActivity.this, Log.d(MtvUiPopUpActivity.this));
                if(mtvreservation != null && mtvreservation.mPgmStatus == 0)
                    MtvReservationManager.UpdateStatus(MtvUiPopUpActivity.this, mtvreservation, 7);
                if(Log.d(MtvUiPopUpActivity.this).getReserveAlertFrom() == 0)
                {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_CANCEL_EXIT");
                    sendBroadcast(intent1);
                }
                Log.d(MtvUiPopUpActivity.this).setReservationAlertID(-1);
                Log.d(MtvUiPopUpActivity.this).setReservAlertFrom(-1);
                Log.d(MtvUiPopUpActivity.this, -1);
                finish();
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$7;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        });
        mDialogBuilder.setCancelable(false);
        Intent intent = new Intent();
        intent.setAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_END_DIALOG_CLOSE");
        sendBroadcast(intent);
        mDialog = mDialogBuilder.create();
        if(mPreferences.getReservationAlertID() != -1)
            mPopupUiMsgHandler.postDelayed(RunnablePopupExpire, 5000L);
        else
            mPopupUiMsgHandler.postDelayed(RunnablePopupExpire, 4000L);
        continue; /* Loop/switch isn't completed */
_L5:
        String s;
        if(AnatenaAlertfrom)
            s = getString(0x7f0702ab);
        else
            s = getString(0x7f0702b5);
        mDialog = (new android.app.AlertDialog.Builder(this)).setTitle(s).setMessage(0x7f0702cf).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$8;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_LAUNCH_ANTENNA_ALERT onClick OK ");
                Log.d().removeCallbacks(Log.d(MtvUiPopUpActivity.this));
                Log.d(MtvUiPopUpActivity.this);
            }

            final MtvUiPopUpActivity this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$8;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        }).create();
        mPopupUiMsgHandler.postDelayed(RunnablePopupExpire, 5000L);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void handleLaunchAntennaOK()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->handleLaunchAntennaOK()V");
        MtvUtilDebug.High("MtvUiPopUpActivity", "handleLaunchAntennaOK...");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        mPreferences.setLaunchAntennaAlert(false);
        finish();
    }

    private void handleLowBatteryOK()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->handleLowBatteryOK()V");
        isBatteryLowPopupAvailable = false;
        MtvUtilAppService.setAppExiting(true);
        finish();
    }

    private void handleReservationOK()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->handleReservationOK()V");
        MtvUtilDebug.High("MtvUiPopUpActivity", "handleReservationOK...");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        AfetrreservationOk = true;
        Intent intent = new Intent();
        intent.putExtra("dbId", mReservationId);
        intent.putExtra("youNeedToShowAlert", false);
        intent.setFlags(0x4000000);
        intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"));
        if(mMtvReservationAlertControl != null)
            mPopupUiMsgHandler.postDelayed(Log.d(mMtvReservationAlertControl), 2000L);
        startActivity(intent);
        finish();
    }

    private void handleSleepTimerOK()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->handleSleepTimerOK()V");
        MtvUtilDebug.High("MtvUiPopUpActivity", "handleSleepTimerOK");
        MtvUtilAppService.setAppExiting(true);
        finish();
    }

    public static boolean isBatteryLowPopupAvailable()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->isBatteryLowPopupAvailable()Z");
        return isBatteryLowPopupAvailable;
    }

    protected void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        MtvUtilDebug.Low("MtvUiPopUpActivity", "OnCreate");
        requestWindowFeature(1);
        getWindow().addFlags(0x680400);
        mContext = this;
        setContentView(0x7f030005);
        Intent intent = getIntent();
        type = intent.getIntExtra("popup_type", 0);
        AnatenaAlertfrom = intent.getBooleanExtra("Alert_title", false);
        MtvUtilDebug.Low("MtvUiPopUpActivity", (new StringBuilder()).append("OnCreate -type").append(type).toString());
        registerReceiver(mIntentReceiver, INTENT_FILTER);
        createPopup(type);
    }

    protected void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->onDestroy()V");
        MtvUtilDebug.Low("MtvUiPopUpActivity", "OnDestroy");
        isBatteryLowPopupAvailable = false;
        mContext = null;
        if(mDialog != null && mDialog.isShowing())
        {
            mDialog.dismiss();
            mDialog = null;
        }
        super.onDestroy();
        unregisterReceiver(mIntentReceiver);
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->onPause()V");
        MtvUtilDebug.Low("MtvUiPopUpActivity", "OnPause");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        MtvUtilDebug.High("MtvUiPopUpActivity", (new StringBuilder()).append("mPreferences.getReservationAlertID()").append(mPreferences.getReservationAlertID()).toString());
        if(mReservationId != -1 && AfetrreservationOk)
        {
            MtvUtilDebug.High("MtvUiPopUpActivity", (new StringBuilder()).append("mPreferences.getReservationAlertID()").append(mPreferences.getReservationAlertID()).toString());
            AfetrreservationOk = false;
            Intent intent = new Intent();
            intent.putExtra("dbId", mReservationId);
            intent.putExtra("youNeedToShowAlert", false);
            intent.setFlags(0x4000000);
            intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"));
            startActivity(intent);
        }
        MtvUtilDebug.High("MtvUiPopUpActivity", " onpause startActivity");
        super.onPause();
        if(!isFinishing());
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->onResume()V");
        MtvUtilDebug.Low("MtvUiPopUpActivity", "OnResume");
        super.onResume();
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        if(!isFinishing())
            finish();
_L4:
        return;
_L2:
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        if(!mDialog.isShowing())
            mDialog.show();
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("Alert_title", AnatenaAlertfrom);
    }

    private static IntentFilter INTENT_FILTER;
    private static boolean isBatteryLowPopupAvailable = false;
    private static Handler mPopupUiMsgHandler = new Handler() {

        public void handleMessage(Message message)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$1;->handleMessage(Landroid/os/Message;)V");
            int _tmp = message.what;
        }

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity$1;-><init>()V");
                super();
            }
    };
    private boolean AfetrreservationOk;
    private boolean AnatenaAlertfrom;
    private Runnable RunnablePopupExpire;
    private Context mContext;
    private AlertDialog mDialog;
    private android.app.AlertDialog.Builder mDialogBuilder;
    private BroadcastReceiver mIntentReceiver;
    private MtvReservationAlertControl mMtvReservationAlertControl;
    private MtvPreferences mPreferences;
    private int mReservationId;
    private int type;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;-><clinit>()V");
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("android.intent.action.BATTERY_CHANGED");
    }


/*
    static int access$000(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)I");
        return mtvuipopupactivity.type;
    }

*/


/*
    static Runnable access$100(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)Ljava/lang/Runnable;");
        return mtvuipopupactivity.RunnablePopupExpire;
    }

*/


/*
    static MtvPreferences access$1000(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$1000(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuipopupactivity.mPreferences;
    }

*/


/*
    static void access$1100(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$1100(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
        mtvuipopupactivity.handleLaunchAntennaOK();
        return;
    }

*/


/*
    static Context access$1300(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$1300(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)Landroid/content/Context;");
        return mtvuipopupactivity.mContext;
    }

*/


/*
    static Handler access$200()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$200()Landroid/os/Handler;");
        return mPopupUiMsgHandler;
    }

*/


/*
    static boolean access$302(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$302(Z)Z");
        isBatteryLowPopupAvailable = flag;
        return flag;
    }

*/


/*
    static void access$400(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$400(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
        mtvuipopupactivity.handleLowBatteryOK();
        return;
    }

*/


/*
    static void access$500(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$500(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
        mtvuipopupactivity.handleSleepTimerOK();
        return;
    }

*/


/*
    static void access$800(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$800(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)V");
        mtvuipopupactivity.handleReservationOK();
        return;
    }

*/


/*
    static int access$900(MtvUiPopUpActivity mtvuipopupactivity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$900(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;)I");
        return mtvuipopupactivity.mReservationId;
    }

*/


/*
    static int access$902(MtvUiPopUpActivity mtvuipopupactivity, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;->access$902(Lcom/samsung/sec/mtv/ui/common/MtvUiPopUpActivity;I)I");
        mtvuipopupactivity.mReservationId = i;
        return i;
    }

*/
}
