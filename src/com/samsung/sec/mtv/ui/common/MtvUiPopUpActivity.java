// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.os.*;
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
            if(mKeyguardManager.inKeyguardRestrictedInputMode())
                mKeyguardLock.reenableKeyguard();
        }

        private void checkAndDisableScreenLock()
        {
            mKeyguardManager = (KeyguardManager)mContext.getSystemService("keyguard");
            mKeyguardLock = mKeyguardManager.newKeyguardLock("keyguard");
            if(mKeyguardManager.inKeyguardRestrictedInputMode())
            {
                class _cls1
                    implements android.app.KeyguardManager.OnKeyguardExitResult
                {

                    public void onKeyguardExitResult(boolean flag)
                    {
                        if(flag)
                            MtvUtilDebug.High("MtvUiPopUpActivity", "onKeyguardExitResult: success");
                        else
                            MtvUtilDebug.High("MtvUiPopUpActivity", "onKeyguardExitResult: failed");
                    }

                    final MtvReservationAlertControl this$1;

                _cls1()
                {
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




        private MtvReservationAlertControl()
        {
            this$0 = MtvUiPopUpActivity.this;
            super();
            class _cls2
                implements Runnable
            {

                public void run()
                {
                    cheakAndReEnableScreenLock();
                }

                final MtvReservationAlertControl this$1;

                _cls2()
                {
                    this$1 = MtvReservationAlertControl.this;
                    super();
                }
            }

            runnableReenterKeyguard = new _cls2();
        }

    }


    public MtvUiPopUpActivity()
    {
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
                i = 1;
                s = intent.getAction();
                MtvUtilDebug.Low("MtvUiPopUpActivity", (new StringBuilder()).append("mIntentReceiver onReceive: action=").append(s).toString());
                break MISSING_BLOCK_LABEL_33;
                while(true) 
                {
                    do
                        return;
                    while(s == null || !s.equals("android.intent.action.BATTERY_CHANGED"));
                    if(intent.getIntExtra("status", i) != 2)
                        i = 0;
                    if(i != 0 && type == 0)
                    {
                        MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                        MtvUiPopUpActivity.isBatteryLowPopupAvailable = false;
                        finish();
                    }
                }
            }

            final MtvUiPopUpActivity this$0;

            
            {
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        };
        RunnablePopupExpire = new Runnable() {

            public void run()
            {
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                type;
                JVM INSTR tableswitch 0 3: default 52
            //                           0 53
            //                           1 70
            //                           2 87
            //                           3 104;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_LOW_BATTERY ");
                handleLowBatteryOK();
                continue; /* Loop/switch isn't completed */
_L3:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_SLEEP_TIMER_EXIT ");
                handleSleepTimerOK();
                continue; /* Loop/switch isn't completed */
_L4:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_RESERVATION_ALERT ");
                handleReservationOK();
                continue; /* Loop/switch isn't completed */
_L5:
                MtvUtilDebug.Low("MtvUiPopUpActivity", "RunnablePopupExpire POPUP_TYPE_LAUNCH_ANTENNA_ALERT ");
                handleLaunchAntennaOK();
                if(true) goto _L1; else goto _L6
_L6:
            }

            final MtvUiPopUpActivity this$0;

            
            {
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        };
    }

    private void createPopup(int i)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 40
    //                   1 116
    //                   2 221
    //                   3 437;
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
                    MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_LOW_BATTERY onClick OK ");
                    MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                    handleLowBatteryOK();
                }

                final MtvUiPopUpActivity this$0;

            
            {
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
                MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_SLEEP_TIMER_EXIT onClick OK ");
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                handleSleepTimerOK();
            }

            final MtvUiPopUpActivity this$0;

            
            {
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                MtvUiSleepTimerDialogFrag.setAlarm(getApplicationContext(), (new MtvPreferences(getApplicationContext())).getAutoPowerOffTime(), true);
                finish();
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
            }

            final MtvUiPopUpActivity this$0;

            
            {
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
        mMtvReservationAlertControl.checkAndDisableScreenLock();
        mDialogBuilder = new android.app.AlertDialog.Builder(this);
        mDialogBuilder.setTitle(0x7f0702b5);
        mDialogBuilder.setMessage(0x7f0702b6);
        mDialogBuilder.setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                handleReservationOK();
            }

            final MtvUiPopUpActivity this$0;

            
            {
                this$0 = MtvUiPopUpActivity.this;
                super();
            }
        });
        mDialogBuilder.setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                MtvReservation mtvreservation = MtvReservationManager.find(MtvUiPopUpActivity.this, mReservationId);
                if(mtvreservation != null && mtvreservation.mPgmStatus == 0)
                    MtvReservationManager.UpdateStatus(MtvUiPopUpActivity.this, mtvreservation, 7);
                if(mPreferences.getReserveAlertFrom() == 0)
                {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.samsung.sec.mtv.ACTION_MTV_RESERVATION_CANCEL_EXIT");
                    sendBroadcast(intent1);
                }
                mPreferences.setReservationAlertID(-1);
                mPreferences.setReservAlertFrom(-1);
                mReservationId = -1;
                finish();
            }

            final MtvUiPopUpActivity this$0;

            
            {
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
                MtvUtilDebug.Low("MtvUiPopUpActivity", "POPUP_TYPE_LAUNCH_ANTENNA_ALERT onClick OK ");
                MtvUiPopUpActivity.mPopupUiMsgHandler.removeCallbacks(RunnablePopupExpire);
                handleLaunchAntennaOK();
            }

            final MtvUiPopUpActivity this$0;

            
            {
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
        MtvUtilDebug.High("MtvUiPopUpActivity", "handleLaunchAntennaOK...");
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        mPreferences.setLaunchAntennaAlert(false);
        finish();
    }

    private void handleLowBatteryOK()
    {
        isBatteryLowPopupAvailable = false;
        MtvUtilAppService.setAppExiting(true);
        finish();
    }

    private void handleReservationOK()
    {
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
            mPopupUiMsgHandler.postDelayed(mMtvReservationAlertControl.runnableReenterKeyguard, 2000L);
        startActivity(intent);
        finish();
    }

    private void handleSleepTimerOK()
    {
        MtvUtilDebug.High("MtvUiPopUpActivity", "handleSleepTimerOK");
        MtvUtilAppService.setAppExiting(true);
        finish();
    }

    public static boolean isBatteryLowPopupAvailable()
    {
        return isBatteryLowPopupAvailable;
    }

    protected void onCreate(Bundle bundle)
    {
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
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("Alert_title", AnatenaAlertfrom);
    }

    private static IntentFilter INTENT_FILTER;
    private static boolean isBatteryLowPopupAvailable = false;
    private static Handler mPopupUiMsgHandler = new Handler() {

        public void handleMessage(Message message)
        {
            int _tmp = message.what;
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
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("android.intent.action.BATTERY_CHANGED");
    }








/*
    static boolean access$302(boolean flag)
    {
        isBatteryLowPopupAvailable = flag;
        return flag;
    }

*/






/*
    static int access$902(MtvUiPopUpActivity mtvuipopupactivity, int i)
    {
        mtvuipopupactivity.mReservationId = i;
        return i;
    }

*/
}
