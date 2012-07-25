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
import android.util.Log;
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;->getCount()I");
            int i;
            if(listItems != null)
                i = listItems.length;
            else
                i = 0;
            return i;
        }

        public Object getItem(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;->getItem(I)Ljava/lang/Object;");
            String s;
            if(listItems != null)
                s = listItems[i];
            else
                s = null;
            return s;
        }

        public long getItemId(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;->getItemId(I)J");
            long l;
            if(listItems != null)
                l = listItems[i].hashCode();
            else
                l = 0L;
            return l;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
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
                if(Log.d(MtvUiSettingsBroadcast.this).getBroadcastDataManufactureMode() == 0)
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
                Log.d(MtvUiSettingsBroadcast.this, MtvAppPlaybackContextManager.getInstance().getCurrentContext());
                if(Log.d(MtvUiSettingsBroadcast.this) != null && (Log.d(MtvUiSettingsBroadcast.this).getState().getOp() == 20487 || Log.d(MtvUiSettingsBroadcast.this).getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LOCALTV))
                {
                    textview.setTextColor(0xff888888);
                    view1.setEnabled(false);
                }
            }
            return view1;
        }

        public void onClick(View view)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;->onClick(Landroid/view/View;)V");
            ((Integer)view.getTag()).intValue();
            JVM INSTR tableswitch 0 5: default 56
        //                       0 57
        //                       1 80
        //                       2 103
        //                       3 126
        //                       4 149
        //                       5 264;
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
            if(Log.d(MtvUiSettingsBroadcast.this) == null)
                Log.d(MtvUiSettingsBroadcast.this, new MtvPreferences(getApplicationContext()));
            if(Log.d(MtvUiSettingsBroadcast.this).getBroadcastDataManufactureMode() == 0)
                Log.d(MtvUiSettingsBroadcast.this).setBroadcastDataManufactureMode(1);
            else
                Log.d(MtvUiSettingsBroadcast.this).setBroadcastDataManufactureMode(0);
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)V");
            this$0 = MtvUiSettingsBroadcast.this;
            super();
            listItems = getResources().getStringArray(0x7f050002);
        }

        BroadCastSettingsAdapter(_cls1 _pcls1)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$BroadCastSettingsAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$1;)V");
            this();
        }
    }

    public static class OptionsDialogFragment extends DialogFragment
    {

        private Dialog createDialogByType()
        {
            int i;
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;->createDialogByType()Landroid/app/Dialog;");
            i = 0;
            dialogType;
            JVM INSTR tableswitch 0 3: default 44
        //                       0 66
        //                       1 119
        //                       2 173
        //                       3 247;
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$1;->onClick(Landroid/content/DialogInterface;I)V");
                    if(j1 == 0)
                        Log.d(OptionsDialogFragment.this).setBroadcastSetRecordingMode(0);
                    else
                        Log.d(OptionsDialogFragment.this).setBroadcastSetRecordingMode(1);
                    dialoginterface.dismiss();
                }

                final OptionsDialogFragment this$0;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$2;->onClick(Landroid/content/DialogInterface;I)V");
                    if(j1 == 0)
                        Log.d(OptionsDialogFragment.this).setBroadcastImageLocationStorage(1);
                    else
                    if(!MtvUtilMemoryStatus.isExternalMemoryAvailable())
                        (new MtvUiSettingsFrag.MemCardErrFragment(0x7f0700ca)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                    if(MtvUtilMemoryStatus.getAvailableExternalMemorySize() < 0x204000L)
                        (new MtvUiSettingsFrag.MemCardErrFragment(0x7f0700cb)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                        Log.d(OptionsDialogFragment.this).setBroadcastImageLocationStorage(0);
                    dialoginterface.dismiss();
                }

                final OptionsDialogFragment this$0;

                _cls2()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$3;->onClick(Landroid/content/DialogInterface;I)V");
                    j1;
                    JVM INSTR tableswitch 0 2: default 36
                //                               0 43
                //                               1 57
                //                               2 71;
                       goto _L1 _L2 _L3 _L4
_L1:
                    dialoginterface.dismiss();
                    return;
_L2:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataNotifyMode(0);
                    continue; /* Loop/switch isn't completed */
_L3:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataNotifyMode(1);
                    continue; /* Loop/switch isn't completed */
_L4:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataNotifyMode(2);
                    if(true) goto _L1; else goto _L5
_L5:
                }

                final OptionsDialogFragment this$0;

                _cls3()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$4;->onClick(Landroid/content/DialogInterface;I)V");
                    j1;
                    JVM INSTR tableswitch 0 2: default 36
                //                               0 43
                //                               1 57
                //                               2 71;
                       goto _L1 _L2 _L3 _L4
_L1:
                    dialoginterface.dismiss();
                    return;
_L2:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataLocationMode(0);
                    continue; /* Loop/switch isn't completed */
_L3:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataLocationMode(1);
                    continue; /* Loop/switch isn't completed */
_L4:
                    Log.d(OptionsDialogFragment.this).setBroadcastDataLocationMode(2);
                    if(true) goto _L1; else goto _L5
_L5:
                }

                final OptionsDialogFragment this$0;

                _cls4()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;)V");
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;->onCreate(Landroid/os/Bundle;)V");
            super.onCreate(bundle);
            mPreference = new MtvPreferences(getActivity().getApplicationContext());
            if(bundle != null)
                dialogType = bundle.getInt("dialogType");
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
            return createDialogByType();
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;->onSaveInstanceState(Landroid/os/Bundle;)V");
            bundle.putInt("dialogType", dialogType);
            super.onSaveInstanceState(bundle);
        }

        private int dialogType;
        private MtvPreferences mPreference;


/*
        static MtvPreferences access$300(OptionsDialogFragment optionsdialogfragment)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;->access$300(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
            return optionsdialogfragment.mPreference;
        }

*/

        public OptionsDialogFragment()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;-><init>()V");
            super();
        }

        public OptionsDialogFragment(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$OptionsDialogFragment;-><init>(I)V");
            super();
            dialogType = i;
        }
    }


    public MtvUiSettingsBroadcast()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;-><init>()V");
        super();
        mMtvAppPlaybackContext = null;
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$1;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low("MtvUiSettingsBroadcast", "onServiceConnected...");
                Log.d(MtvUiSettingsBroadcast.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiSettingsBroadcast.this).registerListener(Log.d(MtvUiSettingsBroadcast.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$1;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiSettingsBroadcast.this).unregisterListener(Log.d(MtvUiSettingsBroadcast.this));
            }

            final MtvUiSettingsBroadcast this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)V");
                this$0 = MtvUiSettingsBroadcast.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2$1;->run()V");
                        finish();
                    }

                    final _cls2 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2;)V");
                            this$1 = _cls2.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
            }

            final MtvUiSettingsBroadcast this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)V");
                this$0 = MtvUiSettingsBroadcast.this;
                super();
            }
        };
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->onCreate(Landroid/os/Bundle;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->onDestroy()V");
        super.onDestroy();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->onResume()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->onStop()V");
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
    static MtvPreferences access$100(MtvUiSettingsBroadcast mtvuisettingsbroadcast)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuisettingsbroadcast.mPreference;
    }

*/


/*
    static MtvPreferences access$102(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvPreferences mtvpreferences)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$102(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;Lcom/samsung/sec/mtv/utility/MtvPreferences;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        mtvuisettingsbroadcast.mPreference = mtvpreferences;
        return mtvpreferences;
    }

*/


/*
    static MtvAppPlaybackContext access$200(MtvUiSettingsBroadcast mtvuisettingsbroadcast)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvuisettingsbroadcast.mMtvAppPlaybackContext;
    }

*/


/*
    static MtvAppPlaybackContext access$202(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$202(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        mtvuisettingsbroadcast.mMtvAppPlaybackContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/


/*
    static MtvAppAndroidService access$400(MtvUiSettingsBroadcast mtvuisettingsbroadcast)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$400(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuisettingsbroadcast.mService;
    }

*/


/*
    static MtvAppAndroidService access$402(MtvUiSettingsBroadcast mtvuisettingsbroadcast, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$402(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuisettingsbroadcast.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$500(MtvUiSettingsBroadcast mtvuisettingsbroadcast)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;->access$500(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsBroadcast;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuisettingsbroadcast.listener;
    }

*/
}
