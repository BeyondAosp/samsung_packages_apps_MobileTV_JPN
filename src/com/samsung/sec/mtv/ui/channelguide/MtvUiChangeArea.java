// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.ListActivity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
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
        mCurrentPhase;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 123
    //                   1 133
    //                   2 150;
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
        if(j == -1)
        {
            setResult(-1, intent);
            finish();
        }
        super.onActivityResult(i, j, intent);
    }

    public void onBackPressed()
    {
        setResult(0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle)
    {
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
        super.onResume();
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        finish();
_L8:
        return;
_L2:
        mCurrentPhase;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 91
    //                   1 108
    //                   2 125;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_125;
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
    static MtvAppAndroidService access$002(MtvUiChangeArea mtvuichangearea, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuichangearea.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


    private class _cls1
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            MtvUtilDebug.Low("MtvUiChangeArea", "onServiceConnected...");
            mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
            mService.registerListener(listener);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            mService.unregisterListener(listener);
        }

        final MtvUiChangeArea this$0;

        _cls1()
        {
            this$0 = MtvUiChangeArea.this;
            super();
        }
    }


    private class _cls2
        implements onMtvAppAndroidServiceListener
    {

        public void onMtvAppAndroidServiceNotify(int i, Object obj)
        {
        }

        public void onMtvAppFinishNotify(Object obj)
        {
            class _cls1
                implements Runnable
            {

                public void run()
                {
                    finish();
                }

                final _cls2 this$1;

                _cls1()
                {
                    this$1 = _cls2.this;
                    super();
                }
            }

            runOnUiThread(new _cls1());
        }

        final MtvUiChangeArea this$0;

        _cls2()
        {
            this$0 = MtvUiChangeArea.this;
            super();
        }
    }

}
