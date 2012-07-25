// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.miniTV;

import android.app.Activity;
import android.app.AlertDialog;
import android.broadcast.helper.MtvUtilDebug;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class MiniMtvExitDialog extends Activity
{

    public MiniMtvExitDialog()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;-><init>()V");
        super();
        mKeyListener = new _cls5();
        mReceiver = new _cls6();
    }

    private void register()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->register()V");
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("intent.finished.app-in-app");
        registerReceiver(mReceiver, intentfilter);
    }

    private void showPopup()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->showPopup()V");
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).setMessage(0x7f07009a).setTitle(0x7f07009f).setOnCancelListener(new _cls3()).setPositiveButton(0x7f070034, new _cls2()).setNegativeButton(0x7f070035, new _cls1()).create();
        alertdialog.setOnDismissListener(new _cls4());
        alertdialog.setOnKeyListener(mKeyListener);
        alertdialog.show();
    }

    private void unregister()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->unregister()V");
        if(mReceiver != null)
            unregisterReceiver(mReceiver);
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        MtvUtilDebug.Low("MtvMiniModeService", "oncreate");
        RelativeLayout relativelayout = new RelativeLayout(this);
        relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -1));
        setContentView(relativelayout);
        showPopup();
    }

    protected void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->onPause()V");
        super.onPause();
        MtvUtilDebug.Low(TAG, "onPause()....");
        overridePendingTransition(0, 0);
    }

    protected void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->onResume()V");
        super.onResume();
        register();
    }

    protected void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->onStop()V");
        super.onStop();
        unregister();
    }

    private static String TAG = "MiniMtvExitDialog";
    private android.content.DialogInterface.OnKeyListener mKeyListener;
    private BroadcastReceiver mReceiver;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;-><clinit>()V");
    }


/*
    static String access$000()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;->access$000()Ljava/lang/String;");
        return TAG;
    }

*/

    private class _cls5
        implements android.content.DialogInterface.OnKeyListener
    {

        public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
        {
            boolean flag;
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$5;->onKey(Landroid/content/DialogInterface;ILandroid/view/KeyEvent;)Z");
            flag = true;
            i;
            JVM INSTR lookupswitch 6: default 72
        //                       4: 78
        //                       19: 100
        //                       20: 100
        //                       23: 100
        //                       62: 100
        //                       66: 100;
               goto _L1 _L2 _L3 _L3 _L3 _L3 _L3
_L1:
            flag = false;
_L5:
            return flag;
_L2:
            if(keyevent.getAction() == flag && dialoginterface != null)
                dialoginterface.dismiss();
            continue; /* Loop/switch isn't completed */
_L3:
            flag = false;
            if(true) goto _L5; else goto _L4
_L4:
        }

        final MiniMtvExitDialog this$0;

        _cls5()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$5;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }


    private class _cls6 extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$6;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V");
            MtvUtilDebug.Low(Log.d(), (new StringBuilder()).append("on receive ..").append(intent.getAction()).toString());
            if("intent.finished.app-in-app".equals(intent.getAction()))
                finish();
        }

        final MiniMtvExitDialog this$0;

        _cls6()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$6;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }


    private class _cls3
        implements android.content.DialogInterface.OnCancelListener
    {

        public void onCancel(DialogInterface dialoginterface)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$3;->onCancel(Landroid/content/DialogInterface;)V");
            finish();
        }

        final MiniMtvExitDialog this$0;

        _cls3()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$3;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }


    private class _cls2
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$2;->onClick(Landroid/content/DialogInterface;I)V");
            Intent intent = new Intent("intent.stop.app-in-app");
            sendBroadcast(intent);
            finish();
        }

        final MiniMtvExitDialog this$0;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$2;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$1;->onClick(Landroid/content/DialogInterface;I)V");
            finish();
        }

        final MiniMtvExitDialog this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$1;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnDismissListener
    {

        public void onDismiss(DialogInterface dialoginterface)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$4;->onDismiss(Landroid/content/DialogInterface;)V");
            finish();
        }

        final MiniMtvExitDialog this$0;

        _cls4()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog$4;-><init>(Lcom/samsung/sec/mtv/ui/miniTV/MiniMtvExitDialog;)V");
            this$0 = MiniMtvExitDialog.this;
            super();
        }
    }

}
