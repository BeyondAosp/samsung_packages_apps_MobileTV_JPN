// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilAppService;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiStationData

public class MtvUiSettingsBroadcast extends Activity
{
    private class BroadCastSettingsAdapter extends BaseAdapter
        implements android.view.View.OnClickListener
    {

        public int getCount()
        {
            int i;
            if(listItems != null)
                i = listItems.length;
            else
                i = 0;
            return i;
        }

        public Object getItem(int i)
        {
            String s;
            if(listItems != null)
                s = listItems[i];
            else
                s = null;
            return s;
        }

        public long getItemId(int i)
        {
            long l;
            if(listItems != null)
                l = listItems[i].hashCode();
            else
                l = 0L;
            return l;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            LayoutInflater layoutinflater = getLayoutInflater();
            View view1;
            if(view == null)
                view1 = layoutinflater.inflate(0x7f03002e, null);
            else
                view1 = view;
            if(i == 4)
            {
                CheckBox checkbox = (CheckBox)view1.findViewById(0x7f0a0116);
                checkbox.setVisibility(0);
                checkbox.setTag(Integer.valueOf(i));
                checkbox.setOnClickListener(this);
                TextView textview;
                boolean flag;
                if(mPreference.getBroadcastDataManufactureMode() == 0)
                    flag = true;
                else
                    flag = false;
                checkbox.setChecked(flag);
            }
            textview = (TextView)view1.findViewById(0x7f0a0115);
            textview.setText(listItems[i]);
            view1.setTag(Integer.valueOf(i));
            view1.setOnClickListener(this);
            if(i == 0 || i == 1)
            {
                mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
                if(mMtvAppPlaybackContext != null && (mMtvAppPlaybackContext.getState().getOp() == 20487 || mMtvAppPlaybackContext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LOCALTV))
                {
                    textview.setTextColor(0xff888888);
                    view1.setEnabled(false);
                }
            }
            return view1;
        }

        public void onClick(View view)
        {
            ((Integer)view.getTag()).intValue();
            JVM INSTR tableswitch 0 5: default 48
        //                       0 49
        //                       1 72
        //                       2 95
        //                       3 118
        //                       4 141
        //                       5 256;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            return;
_L2:
            (new OptionsDialogFragment(0)).show(getFragmentManager(), "OptionsDialogFragment");
            continue; /* Loop/switch isn't completed */
_L3:
            (new OptionsDialogFragment(1)).show(getFragmentManager(), "OptionsDialogFragment");
            continue; /* Loop/switch isn't completed */
_L4:
            (new OptionsDialogFragment(2)).show(getFragmentManager(), "OptionsDialogFragment");
            continue; /* Loop/switch isn't completed */
_L5:
            (new OptionsDialogFragment(3)).show(getFragmentManager(), "OptionsDialogFragment");
            continue; /* Loop/switch isn't completed */
_L6:
            if(view instanceof RelativeLayout)
            {
                CheckBox checkbox = (CheckBox)view.findViewById(0x7f0a0116);
                boolean flag;
                if(!checkbox.isChecked())
                    flag = true;
                else
                    flag = false;
                checkbox.setChecked(flag);
            }
            if(mPreference == null)
                mPreference = new MtvPreferences(getApplicationContext());
            if(mPreference.getBroadcastDataManufactureMode() == 0)
                mPreference.setBroadcastDataManufactureMode(1);
            else
                mPreference.setBroadcastDataManufactureMode(0);
            continue; /* Loop/switch isn't completed */
_L7:
            Intent intent = new Intent(MtvUiSettingsBroadcast.this, com/samsung/sec/mtv/ui/common/MtvUiStationData);
            startActivityForResult(intent, 0);
            if(true) goto _L1; else goto _L8
_L8:
        }

        private String listItems[];
        final MtvUiSettingsBroadcast this$0;

        private BroadCastSettingsAdapter()
        {
            this$0 = MtvUiSettingsBroadcast.this;
            super();
            listItems = getResources().getStringArray(0x7f050002);
        }

    }

    public static class OptionsDialogFragment extends DialogFragment
    {

        private Dialog createDialogByType()
        {
            int i = 0;
            dialogType;
            JVM INSTR tableswitch 0 3: default 36
        //                       0 58
        //                       1 111
        //                       2 165
        //                       3 239;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            Object obj = null;
_L7:
            if(obj != null)
                ((AlertDialog) (obj)).getWindow().addFlags(1024);
            return ((Dialog) (obj));
_L2:
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    if(j1 == 0)
                        mPreference.setBroadcastSetRecordingMode(0);
                    else
                        mPreference.setBroadcastSetRecordingMode(1);
                    dialoginterface.dismiss();
                }

                final OptionsDialogFragment this$0;

                _cls1()
                {
                    this$0 = OptionsDialogFragment.this;
                    super();
                }
            }

            if(mPreference.getBroadcastSetRecordingMode() != 0)
                i = 1;
            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700b6).setSingleChoiceItems(0x7f050005, i, new _cls1()).create();
            continue; /* Loop/switch isn't completed */
_L3:
            class _cls2
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    if(j1 == 0)
                        mPreference.setBroadcastImageLocationStorage(1);
                    else
                    if(!MtvUtilMemoryStatus.isExternalMemoryAvailable())
                        (new MtvUiSettingsFrag.MemCardErrFragment(0x7f0700ca)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                    if(MtvUtilMemoryStatus.getAvailableExternalMemorySize() < 0x204000L)
                        (new MtvUiSettingsFrag.MemCardErrFragment(0x7f0700cb)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                        mPreference.setBroadcastImageLocationStorage(0);
                    dialoginterface.dismiss();
                }

                final OptionsDialogFragment this$0;

                _cls2()
                {
                    this$0 = OptionsDialogFragment.this;
                    super();
                }
            }

            if(mPreference.getBroadcastImageLocationStorage() != 1)
                i = 1;
            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700b9).setSingleChoiceItems(0x7f050006, i, new _cls2()).create();
            continue; /* Loop/switch isn't completed */
_L4:
            int l = mPreference.getBroadcastDataNotifyMode();
            class _cls3
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    j1;
                    JVM INSTR tableswitch 0 2: default 28
                //                               0 35
                //                               1 49
                //                               2 63;
                       goto _L1 _L2 _L3 _L4
_L1:
                    dialoginterface.dismiss();
                    return;
_L2:
                    mPreference.setBroadcastDataNotifyMode(0);
                    continue; /* Loop/switch isn't completed */
_L3:
                    mPreference.setBroadcastDataNotifyMode(1);
                    continue; /* Loop/switch isn't completed */
_L4:
                    mPreference.setBroadcastDataNotifyMode(2);
                    if(true) goto _L1; else goto _L5
_L5:
                }

                final OptionsDialogFragment this$0;

                _cls3()
                {
                    this$0 = OptionsDialogFragment.this;
                    super();
                }
            }

            int i1;
            if(l == 0)
                i1 = 0;
            else
            if(l == 1)
                i1 = 1;
            else
                i1 = 2;
            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700ba).setSingleChoiceItems(0x7f050004, i1, new _cls3()).create();
            continue; /* Loop/switch isn't completed */
_L5:
            int j;
            int k;
            j = mPreference.getBroadcastDataLocationMode();
            if(j != 0)
                break; /* Loop/switch isn't completed */
            k = 0;
_L8:
            class _cls4
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    j1;
                    JVM INSTR tableswitch 0 2: default 28
                //                               0 35
                //                               1 49
                //                               2 63;
                       goto _L1 _L2 _L3 _L4
_L1:
                    dialoginterface.dismiss();
                    return;
_L2:
                    mPreference.setBroadcastDataLocationMode(0);
                    continue; /* Loop/switch isn't completed */
_L3:
                    mPreference.setBroadcastDataLocationMode(1);
                    continue; /* Loop/switch isn't completed */
_L4:
                    mPreference.setBroadcastDataLocationMode(2);
                    if(true) goto _L1; else goto _L5
_L5:
                }

                final OptionsDialogFragment this$0;

                _cls4()
                {
                    this$0 = OptionsDialogFragment.this;
                    super();
                }
            }

            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700bc).setSingleChoiceItems(0x7f050004, k, new _cls4()).create();
            if(true) goto _L7; else goto _L6
_L6:
            if(j == 1)
                k = 1;
            else
                k = 2;
              goto _L8
            if(true) goto _L7; else goto _L9
_L9:
        }

        public void onCreate(Bundle bundle)
        {
            super.onCreate(bundle);
            mPreference = new MtvPreferences(getActivity().getApplicationContext());
            if(bundle != null)
                dialogType = bundle.getInt("dialogType");
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            return createDialogByType();
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("dialogType", dialogType);
            super.onSaveInstanceState(bundle);
        }

        private int dialogType;
        private MtvPreferences mPreference;


        public OptionsDialogFragment()
        {
        }

        public OptionsDialogFragment(int i)
        {
            dialogType = i;
        }
    }


    public MtvUiSettingsBroadcast()
    {
        mMtvAppPlaybackContext = null;
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low("MtvUiSettingsBroadcast", "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(listener);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mService.unregisterListener(listener);
            }

            final MtvUiSettingsBroadcast this$0;

            
            {
                this$0 = MtvUiSettingsBroadcast.this;
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

            final MtvUiSettingsBroadcast this$0;

            
            {
                this$0 = MtvUiSettingsBroadcast.this;
                super();
            }
        };
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setTitle(0x7f0700a3);
        setContentView(0x7f03000a);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        mPreference = new MtvPreferences(getApplicationContext());
        mListView = (ListView)findViewById(0x7f0a003a);
        mListView.setAdapter(new BroadCastSettingsAdapter());
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    public void onResume()
    {
        super.onResume();
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(MtvUtilAppService.isAppExiting())
        {
            finish();
        } else
        {
            MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
            MtvPreferences mtvpreferences = new MtvPreferences(getApplicationContext());
            android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
            layoutparams.screenBrightness = mtvpreferences.getBrightnessValue();
            getWindow().setAttributes(layoutparams);
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
    }

    public void onStop()
    {
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    private onMtvAppAndroidServiceListener listener;
    private ServiceConnection mConnection;
    private ListView mListView;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvPreferences mPreference;
    private MtvAppAndroidService mService;



/*
    static MtvPreferences access$102(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvPreferences mtvpreferences)
    {
        mtvuisettingsbroadcast.mPreference = mtvpreferences;
        return mtvpreferences;
    }

*/



/*
    static MtvAppPlaybackContext access$202(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvAppPlaybackContext mtvappplaybackcontext)
    {
        mtvuisettingsbroadcast.mMtvAppPlaybackContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/



/*
    static MtvAppAndroidService access$402(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuisettingsbroadcast.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/

}
