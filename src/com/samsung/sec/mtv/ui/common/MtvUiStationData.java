// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.MtvBMLManager;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilAppService;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiRemoveList

public class MtvUiStationData extends Activity
    implements android.widget.AdapterView.OnItemClickListener
{
    protected class StationDataAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000c, null);
            String s = mTitleName[i];
            mCount = MtvBMLManager.deleteStationData_GetNetworkItemCount(i);
            mNameText = (TextView)view1.findViewById(0x7f0a003c);
            mCountText = (TextView)view1.findViewById(0x7f0a003d);
            mNameText.setText(s);
            mCountText.setText((new StringBuilder()).append(": ").append(mCount).toString());
            return view1;
        }

        private int mCount;
        private TextView mCountText;
        private TextView mNameText;
        private String mTitleName[];
        final MtvUiStationData this$0;

        public StationDataAdapter(Context context, int i, String as[])
        {
            this$0 = MtvUiStationData.this;
            super(context, i, as);
            mTitleName = as;
        }
    }


    public MtvUiStationData()
    {
        mAdapter = null;
        mDeleteStationData = null;
        mStationDataListView = null;
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low("MtvUiStationData", "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(listener);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mService.unregisterListener(listener);
            }

            final MtvUiStationData this$0;

            
            {
                this$0 = MtvUiStationData.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

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

            final MtvUiStationData this$0;

            
            {
                this$0 = MtvUiStationData.this;
                super();
            }
        };
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000b);
        getWindow().addFlags(128);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        mStationDataListView = (ListView)findViewById(0x7f0a003b);
        mDeleteStationData = getBaseContext().getResources().getStringArray(0x7f05000a);
        mAdapter = new StationDataAdapter(this, 0x7f03000c, mDeleteStationData);
        mStationDataListView.setAdapter(mAdapter);
        mStationDataListView.setOnItemClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, 0, 0, 0x7f070296).setIcon(0x7f020103);
        return super.onCreateOptionsMenu(menu);
    }

    public void onDestroy()
    {
        MtvUtilDebug.Low("MtvUiStationData", "onDestroy call...");
        super.onDestroy();
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        finishActivity(0);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        MtvUtilDebug.Low("MtvUiStationData", (new StringBuilder()).append("position is ").append(i).toString());
        Intent intent = new Intent(this, com/samsung/sec/mtv/ui/common/MtvUiRemoveList);
        intent.putExtra("Remove_List_Type", 100);
        intent.putExtra("delete_station_data_index", i);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 0 0: default 24
    //                   0 30;
           goto _L1 _L2
_L1:
        return super.onOptionsItemSelected(menuitem);
_L2:
        MtvBMLManager.deleteStationData_DeleteAllAffiliation();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onResume()
    {
        super.onResume();
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        MtvUtilDebug.High("MtvUiStationData", "onResume call..");
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        finish();
_L4:
        return;
_L2:
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mMtvPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        getWindow().setTitle(getString(0x7f0700be));
        if(mStationDataListView != null && mAdapter != null)
            mStationDataListView.setAdapter(mAdapter);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onStop()
    {
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    private onMtvAppAndroidServiceListener listener;
    private StationDataAdapter mAdapter;
    private ServiceConnection mConnection;
    private String mDeleteStationData[];
    private MtvPreferences mMtvPreferences;
    private MtvAppAndroidService mService;
    private ListView mStationDataListView;



/*
    static MtvAppAndroidService access$002(MtvUiStationData mtvuistationdata, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuistationdata.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/

}
