// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.ListActivity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.samsung.sec.mtv.app.service.MtvAppAndroidService;
import com.samsung.sec.mtv.app.service.onMtvAppAndroidServiceListener;
import com.samsung.sec.mtv.utility.*;

public class MtvUiChangeArea extends ListActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public MtvUiChangeArea()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;-><init>()V");
        super();
        mSlotId = 0;
        mCurrentPhase = 0;
        mArrayId = null;
        mArrayName = null;
        mService = null;
        mConnection = new _cls1();
        listener = new _cls2();
    }

    private void initialize()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->initialize()V");
        mCurrentPhase;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 131
    //                   1 141
    //                   2 158;
           goto _L1 _L2 _L3 _L4
_L1:
        mArrayName = new String[mArrayId.length];
        for(int i = 0; i < mArrayId.length; i++)
        {
            mArrayName[i] = MtvAreaStationInfo.getStringByResourceName(this, mArrayId[i]);
            MtvUtilDebug.Low("MtvUiChangeArea", (new StringBuilder()).append("selected=").append(mSelected).append("mArrayName[").append(mArrayName[i]).append("]").toString());
        }

        break; /* Loop/switch isn't completed */
_L2:
        mArrayId = MtvAreaStationInfo.AREA_REGION;
        continue; /* Loop/switch isn't completed */
_L3:
        mArrayId = MtvAreaStationInfo.AREA_PROVINCES[mSelected[0]];
        continue; /* Loop/switch isn't completed */
_L4:
        mArrayId = MtvAreaStationInfo.AREA_LOCAL[mSelected[0]][mSelected[1]];
        if(true) goto _L1; else goto _L5
_L5:
        MtvUtilDebug.Low("MtvUiChangeArea", (new StringBuilder()).append("Phase = ").append(mCurrentPhase).toString());
        setListAdapter(new ArrayAdapter(this, 0x109000f, mArrayName));
        ListView listview = getListView();
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(1);
        listview.setOnItemClickListener(this);
        return;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onActivityResult(IILandroid/content/Intent;)V");
        if(j == -1)
        {
            setResult(-1, intent);
            finish();
        }
        super.onActivityResult(i, j, intent);
    }

    public void onBackPressed()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onBackPressed()V");
        setResult(0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        getWindow().addFlags(128);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        mSlotId = getIntent().getIntExtra("slotId", 0);
        mSelected = getIntent().getIntArrayExtra("selected");
        if(mSelected == null)
            mSelected = new int[3];
        mCurrentPhase = getIntent().getIntExtra("phase", 0);
        MtvUtilDebug.Low("MtvUiChangeArea", (new StringBuilder()).append("onCreate mSlotId=").append(mSlotId).append(" mSelected=").append(mSelected).toString());
        initialize();
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onDestroy()V");
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        super.onDestroy();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
        MtvUtilDebug.Low("MtvUiChangeArea", (new StringBuilder()).append("onItemClick arg0=").append(adapterview).append(" view=").append(view).append(" position=").append(i).append(" id=").append(l).toString());
        mSelected[mCurrentPhase] = i;
        if(mCurrentPhase != 2)
        {
            Intent intent = new Intent(this, com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
            intent.putExtra("slotId", mSlotId);
            intent.putExtra("selected", mSelected);
            intent.putExtra("phase", 1 + mCurrentPhase);
            startActivityForResult(intent, 0);
        } else
        {
            MtvUtilDebug.Low("MtvUiChangeArea", (new StringBuilder()).append("onItemClick localId=").append(MtvAreaStationInfo.AREA_LOCAL_ID[mSelected[0]][mSelected[1]][i]).toString());
            Intent intent1 = getIntent();
            intent1.putExtra("slotId", mSlotId);
            intent1.putExtra("setarea", false);
            intent1.putExtra("localId", mSelected);
            setResult(-1, intent1);
            finish();
        }
    }

    protected void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onResume()V");
        super.onResume();
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        finish();
_L8:
        return;
_L2:
        mCurrentPhase;
        JVM INSTR tableswitch 0 2: default 56
    //                   0 106
    //                   1 123
    //                   2 140;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_140;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L9:
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        MtvPreferences mtvpreferences = new MtvPreferences(getApplicationContext());
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mtvpreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        if(true) goto _L8; else goto _L7
_L7:
        getWindow().setTitle(getString(0x7f070290));
          goto _L9
_L5:
        getWindow().setTitle(getString(0x7f070291));
          goto _L9
        getWindow().setTitle(getString(0x7f070292));
          goto _L9
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->onStop()V");
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    private final int CHANGE_LOCAL_AREA = 2;
    private final int CHANGE_PROVINCES = 1;
    private final int CHANGE_REGIONS = 0;
    private onMtvAppAndroidServiceListener listener;
    private String mArrayId[];
    private String mArrayName[];
    private ServiceConnection mConnection;
    private int mCurrentPhase;
    private int mSelected[];
    private MtvAppAndroidService mService;
    private int mSlotId;


/*
    static MtvAppAndroidService access$000(MtvUiChangeArea mtvuichangearea)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->access$000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuichangearea.mService;
    }

*/


/*
    static MtvAppAndroidService access$002(MtvUiChangeArea mtvuichangearea, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->access$002(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuichangearea.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$100(MtvUiChangeArea mtvuichangearea)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;->access$100(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuichangearea.listener;
    }

*/

    private class _cls1
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$1;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
            MtvUtilDebug.Low("MtvUiChangeArea", "onServiceConnected...");
            Log.d(MtvUiChangeArea.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
            Log.d(MtvUiChangeArea.this).registerListener(Log.d(MtvUiChangeArea.this));
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$1;->onServiceDisconnected(Landroid/content/ComponentName;)V");
            Log.d(MtvUiChangeArea.this).unregisterListener(Log.d(MtvUiChangeArea.this));
        }

        final MtvUiChangeArea this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;)V");
            this$0 = MtvUiChangeArea.this;
            super();
        }
    }


    private class _cls2
        implements onMtvAppAndroidServiceListener
    {

        public void onMtvAppAndroidServiceNotify(int i, Object obj)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
        }

        public void onMtvAppFinishNotify(Object obj)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
            class _cls1
                implements Runnable
            {

                public void run()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2$1;->run()V");
                    finish();
                }

                final _cls2 this$1;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2;)V");
                    this$1 = _cls2.this;
                    super();
                }
            }

            runOnUiThread(new _cls1());
        }

        final MtvUiChangeArea this$0;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea$2;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea;)V");
            this$0 = MtvUiChangeArea.this;
            super();
        }
    }

}
