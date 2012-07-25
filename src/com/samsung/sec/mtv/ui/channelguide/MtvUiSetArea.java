// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.AlertDialog;
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
import com.samsung.sec.mtv.provider.MtvArea;
import com.samsung.sec.mtv.provider.MtvAreaManager;
import com.samsung.sec.mtv.utility.*;

// Referenced classes of package com.samsung.sec.mtv.ui.channelguide:
//            MtvUiChangeArea

public class MtvUiSetArea extends ListActivity
    implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
{

    public MtvUiSetArea()
    {
        mService = null;
        mConnection = new _cls2();
        listener = new _cls3();
    }

    private void initialize()
    {
        MtvUtilDebug.Mid("MtvUiSetArea", "initialize");
        MtvArea amtvarea[] = MtvAreaManager.getAllAreas(this);
        String as[] = new String[amtvarea.length];
        int i = 0;
        while(i < amtvarea.length) 
        {
            String s;
            if(amtvarea[i].mAreaName.equals("Empty"))
                s = (new StringBuilder()).append(getString(0x7f07028f)).append(" ").append(i + 1).toString();
            else
                s = MtvAreaStationInfo.getStringByResourceName(this, amtvarea[i].mAreaName);
            as[i] = s;
            i++;
        }
        setListAdapter(new ArrayAdapter(this, 0x1090003, as));
        ListView listview = getListView();
        listview.setItemsCanFocus(false);
        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j != -1) goto _L2; else goto _L1
_L1:
        setResult(-1, intent);
        finish();
_L4:
        super.onActivityResult(i, j, intent);
        return;
_L2:
        if(j == 0)
            finish();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        MtvUtilDebug.Mid("MtvUiSetArea", "onCreate MtvUiSetArea");
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        initialize();
    }

    public void onDestroy()
    {
        MtvUtilDebug.Mid("MtvUiSetArea", "onDestroy");
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
        MtvUtilDebug.Mid("MtvUiSetArea", (new StringBuilder()).append("onItemClick arg0=").append(adapterview).append(" arg1=").append(view).append(" arg2=").append(i).append(" arg3=").append(l).toString());
        Intent intent = new Intent(this, com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
        intent.putExtra("slotId", i);
        startActivityForResult(intent, 0);
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, final int arg2, long l)
    {
        MtvUtilDebug.Mid("MtvUiSetArea", (new StringBuilder()).append("onItemLongClick arg0=").append(adapterview).append(" arg1=").append(view).append(" arg2=").append(arg2).append(" arg3=").append(l).toString());
        sArrayRsrc = 0x7f050007;
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070297).setItems(sArrayRsrc, new _cls1()).create().show();
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        MtvPreferences mtvpreferences = new MtvPreferences(getApplicationContext());
        if(MtvUtilAppService.isAppExiting())
        {
            finish();
        } else
        {
            MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
            android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
            layoutparams.screenBrightness = mtvpreferences.getBrightnessValue();
            getWindow().setAttributes(layoutparams);
        }
    }

    public void onStop()
    {
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    private static int sArrayRsrc = 0;
    private onMtvAppAndroidServiceListener listener;
    private ServiceConnection mConnection;
    private MtvAppAndroidService mService;





/*
    static MtvAppAndroidService access$102(MtvUiSetArea mtvuisetarea, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuisetarea.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


    private class _cls2
        implements ServiceConnection
    {

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            MtvUtilDebug.Low("MtvUiSetArea", "onServiceConnected...");
            mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
            mService.registerListener(listener);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            mService.unregisterListener(listener);
        }

        final MtvUiSetArea this$0;

        _cls2()
        {
            this$0 = MtvUiSetArea.this;
            super();
        }
    }


    private class _cls3
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

                final _cls3 this$1;

                _cls1()
                {
                    this$1 = _cls3.this;
                    super();
                }
            }

            runOnUiThread(new _cls1());
        }

        final MtvUiSetArea this$0;

        _cls3()
        {
            this$0 = MtvUiSetArea.this;
            super();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            i;
            JVM INSTR tableswitch 0 0: default 20
        //                       0 21;
               goto _L1 _L2
_L1:
            return;
_L2:
            MtvUtilDebug.Mid("MtvUiSetArea", "Reset the vaue");
            MtvAreaManager.update2Default(MtvUiSetArea.this, arg2);
            initialize();
            Intent intent = getIntent();
            intent.putExtra("noAreaSet", true);
            intent.putExtra("setarea", true);
            setResult(-1, intent);
            if(MtvAreaManager.getCount(MtvUiSetArea.this) == 0)
                finish();
            if(true) goto _L1; else goto _L3
_L3:
        }

        final MtvUiSetArea this$0;
        final int val$arg2;

        _cls1()
        {
            this$0 = MtvUiSetArea.this;
            arg2 = i;
            super();
        }
    }

}
