// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilAudioManager;
import java.util.Locale;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiSettingsFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{
    public static class MemCardErrFragment extends DialogFragment
    {

        public void onCreate(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;->onCreate(Landroid/os/Bundle;)V");
            super.onCreate(bundle);
            if(bundle != null)
                messageStringId = bundle.getInt("messageStringId", -1);
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
            String s = "";
            if(messageStringId != -1)
                s = getString(messageStringId);
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment$1;->onClick(Landroid/content/DialogInterface;I)V");
                }

                final MemCardErrFragment this$0;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;)V");
                    this$0 = MemCardErrFragment.this;
                    super();
                }
            }

            return (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700c9).setMessage(s).setPositiveButton(0x7f070034, new _cls1()).create();
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;->onSaveInstanceState(Landroid/os/Bundle;)V");
            bundle.putInt("messageStringId", messageStringId);
            super.onSaveInstanceState(bundle);
        }

        private int messageStringId;

        public MemCardErrFragment()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;-><init>()V");
            super();
            messageStringId = -1;
        }

        public MemCardErrFragment(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$MemCardErrFragment;-><init>(I)V");
            super();
            messageStringId = -1;
            messageStringId = i;
        }
    }

    public static class SaveToDialogFragment extends DialogFragment
    {

        public void onCreate(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;->onCreate(Landroid/os/Bundle;)V");
            super.onCreate(bundle);
            mPreference = new MtvPreferences(getActivity());
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
            int i = 0;
            String as[] = new String[2];
            as[i] = getActivity().getString(0x7f0700b4);
            as[1] = getActivity().getString(0x7f0700b5);
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment$1;->onClick(Landroid/content/DialogInterface;I)V");
                    if(j == 0)
                        Log.d(SaveToDialogFragment.this).setSaveToStorage(1);
                    else
                    if(!MtvUtilMemoryStatus.isExternalMemoryAvailable())
                        (new MemCardErrFragment(0x7f0700ca)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                    if(MtvUtilMemoryStatus.getAvailableExternalMemorySize() < 0x204000L)
                        (new MemCardErrFragment(0x7f0700cb)).show(getFragmentManager(), "MemCardErrFragment");
                    else
                        Log.d(SaveToDialogFragment.this).setSaveToStorage(0);
                    dialoginterface.dismiss();
                }

                final SaveToDialogFragment this$0;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;)V");
                    this$0 = SaveToDialogFragment.this;
                    super();
                }
            }

            AlertDialog alertdialog;
            if(mPreference.getSaveToStorage() != 1)
                i = 1;
            alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0700b3).setSingleChoiceItems(as, i, new _cls1()).create();
            alertdialog.getWindow().addFlags(1024);
            return alertdialog;
        }

        private MtvPreferences mPreference;


/*
        static MtvPreferences access$200(SaveToDialogFragment savetodialogfragment)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
            return savetodialogfragment.mPreference;
        }

*/

        public SaveToDialogFragment()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$SaveToDialogFragment;-><init>()V");
            super();
            mPreference = null;
        }
    }


    public MtvUiSettingsFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;-><init>()V");
        super();
        mView = null;
        mMtvAudioManager = null;
        settingsType = -1;
    }

    public MtvUiSettingsFrag(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;-><init>(I)V");
        super();
        mView = null;
        mMtvAudioManager = null;
        settingsType = -1;
    }

    public MtvUiSettingsFrag(int i, int j)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;-><init>(II)V");
        super();
        mView = null;
        mMtvAudioManager = null;
        settingsType = -1;
        settingsType = i;
    }

    private void changeBrightnessLevel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->changeBrightnessLevel()V");
        android.view.WindowManager.LayoutParams layoutparams = getActivity().getWindow().getAttributes();
        layoutparams.screenBrightness = mPreferences.getBrightnessValue();
        getActivity().getWindow().setAttributes(layoutparams);
    }

    public static boolean changeColorTone(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->changeColorTone(I)Z");
        return true;
    }

    public static boolean changeOutdoorVisibility(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->changeOutdoorVisibility(Z)Z");
        return true;
    }

    private void initializeAudioUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->initializeAudioUI()V");
        ((Button)mView.findViewById(0x7f0a00ba)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00bb)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00bc)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00bd)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00be)).setOnClickListener(this);
        updateAudioEffects(new int[0]);
        ((Button)mView.findViewById(0x7f0a00c0)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00c1)).setOnClickListener(this);
        Button button = (Button)mView.findViewById(0x7f0a00c2);
        if(!getActivity().getResources().getConfiguration().locale.equals(Locale.US))
            button.setTextSize(2, 17F);
        button.setOnClickListener(this);
        updateAudioLanguage(new int[0]);
        CheckBox checkbox = (CheckBox)mView.findViewById(0x7f0a00c4);
        TextView textview = (TextView)mView.findViewById(0x7f0a00c5);
        checkbox.setChecked(mPreferences.isAudio51Enabled());
        checkbox.setOnClickListener(this);
        textview.setOnClickListener(this);
    }

    private void initializeDisplayUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->initializeDisplayUI()V");
        mSeekBar = (SeekBar)mView.findViewById(0x7f0a00c8);
        mSeekBar.setMax(5);
        mSeekBar.setOnSeekBarChangeListener(brightnessSeekBarChangeListener);
        if(mPreferences.getBrightnessLevel() == -1)
            mPreferences.setBrightnessLevel(3);
        setBrightnessLevel(mPreferences.getBrightnessLevel(), true);
        CheckBox checkbox = (CheckBox)mView.findViewById(0x7f0a00ca);
        TextView textview = (TextView)mView.findViewById(0x7f0a00cb);
        checkbox.setChecked(mPreferences.isOutdoorVisibility());
        checkbox.setOnClickListener(this);
        textview.setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00cd)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00ce)).setOnClickListener(this);
        ((Button)mView.findViewById(0x7f0a00cf)).setOnClickListener(this);
        updateColorTone(new int[0]);
        CheckBox checkbox1 = (CheckBox)mView.findViewById(0x7f0a00d1);
        TextView textview1 = (TextView)mView.findViewById(0x7f0a00d2);
        checkbox1.setChecked(mPreferences.isframeIPEnabled());
        checkbox1.setOnClickListener(this);
        textview1.setOnClickListener(this);
        CheckBox checkbox2 = (CheckBox)mView.findViewById(0x7f0a00d4);
        TextView textview2 = (TextView)mView.findViewById(0x7f0a00d5);
        checkbox2.setChecked(mPreferences.isCaptionEnabled());
        checkbox2.setOnClickListener(this);
        textview2.setOnClickListener(this);
    }

    private void setBrightnessLevel(int i, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->setBrightnessLevel(IZ)V");
        mSeekBar.setProgress(i);
        if(flag)
            mPreferences.setBrightnessLevel(i);
    }

    private void toggleCaption()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->toggleCaption()V");
        boolean flag;
        if(mPreferences.isCaptionEnabled())
            flag = false;
        else
            flag = true;
        ((CheckBox)mView.findViewById(0x7f0a00d4)).setChecked(flag);
        if(mPreferences.isCaptionEnabled() != flag)
            mPreferences.setCaptionEnabled(flag);
        mListener.onFragEvent(202, null);
    }

    private void toggleFrameInterpolation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->toggleFrameInterpolation()V");
        int i = 1;
        int j;
        CheckBox checkbox;
        if(mPreferences.isframeIPEnabled())
            j = 0;
        else
            j = i;
        if(j == 0)
            i = 0;
        checkbox = (CheckBox)mView.findViewById(0x7f0a00d1);
        MtvAppPlaybackContextManager.getInstance().getCurrentContext().getComponents().getVideo().getControlInterface().setFrameRateChange(i);
        checkbox.setChecked(j);
        if(mPreferences.isframeIPEnabled() != j)
            mPreferences.setFrameIPEnabled(j);
    }

    private void toggleOutdoorVisibility()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->toggleOutdoorVisibility()V");
        boolean flag;
        CheckBox checkbox;
        if(mPreferences.isOutdoorVisibility())
            flag = false;
        else
            flag = true;
        checkbox = (CheckBox)mView.findViewById(0x7f0a00ca);
        if(changeOutdoorVisibility(flag))
        {
            checkbox.setChecked(flag);
            if(mPreferences.isOutdoorVisibility() != flag)
                mPreferences.setOutdoorVisibility(flag);
        }
    }

    private transient void toggle_5_1Channel(int ai[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->toggle_5_1Channel([I)V");
        boolean flag;
        CheckBox checkbox;
        if(mPreferences.isAudio51Enabled())
            flag = false;
        else
            flag = true;
        checkbox = (CheckBox)mView.findViewById(0x7f0a00c4);
        if(ai != null && ai.length > 0)
            if(ai[0] == 1)
                flag = true;
            else
                flag = false;
        if(!MtvUtilAudioManager.getInstance(mContext).isHeadsetConnected() && flag)
        {
            Toast.makeText(mContext, 0x7f0700c8, 0).show();
            if(checkbox != null)
                checkbox.setChecked(false);
        } else
        {
            mPreferences.setAudio51Enabled(flag);
            if(flag)
                mMtvAudioManager.setAudioEffect(6);
            else
                mMtvAudioManager.setAudioEffect(mPreferences.getAudioEffect());
            if(checkbox != null)
                checkbox.setChecked(flag);
            mListener.onFragEvent(203, Boolean.valueOf(true));
        }
    }

    private transient void updateAudioEffects(int ai[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->updateAudioEffects([I)V");
        int ai1[] = new int[5];
        ai1[0] = 0x7f0a00ba;
        ai1[1] = 0x7f0a00bb;
        ai1[2] = 0x7f0a00bc;
        ai1[3] = 0x7f0a00bd;
        ai1[4] = 0x7f0a00be;
        int i = mPreferences.getAudioEffect();
        if(ai != null && ai.length > 0)
        {
            mMtvAudioManager.setAudioEffect(ai[0]);
            mPreferences.setAudioEffect(ai[0]);
            i = ai[0];
        }
        int j = 0;
        while(j < ai1.length) 
        {
            boolean flag;
            if(j == i)
                flag = true;
            else
                flag = false;
            ((Button)mView.findViewById(ai1[j])).setSelected(flag);
            j++;
        }
    }

    private transient void updateAudioLanguage(int ai[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->updateAudioLanguage([I)V");
        int ai1[] = new int[3];
        ai1[0] = 0x7f0a00c2;
        ai1[1] = 0x7f0a00c0;
        ai1[2] = 0x7f0a00c1;
        int i = mPreferences.getAudioLanguage();
        if(ai != null && ai.length > 0)
        {
            mMtvAudioManager.setAudioMode(ai[0]);
            mPreferences.setAudioLanguage(ai[0]);
            i = ai[0];
        }
        int j = 0;
        while(j < ai1.length) 
        {
            boolean flag;
            if(j == i)
                flag = true;
            else
                flag = false;
            ((Button)mView.findViewById(ai1[j])).setSelected(flag);
            j++;
        }
    }

    private transient void updateColorTone(int ai[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->updateColorTone([I)V");
        int ai1[] = new int[4];
        ai1[0] = 0;
        ai1[1] = 0x7f0a00cd;
        ai1[2] = 0x7f0a00ce;
        ai1[3] = 0x7f0a00cf;
        int i = mPreferences.getColorTone();
        if(i < ai1.length && i >= 1)
        {
            if(ai != null && ai.length > 0 && changeColorTone(ai[0]))
            {
                mPreferences.setColorTone(ai[0]);
                i = ai[0];
            }
            int j = 1;
            while(j < ai1.length) 
            {
                boolean flag;
                if(j == i)
                    flag = true;
                else
                    flag = false;
                ((Button)mView.findViewById(ai1[j])).setSelected(flag);
                j++;
            }
        }
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onAttach(Landroid/app/Activity;)V");
        super.onAttach(activity);
        try
        {
            mListener = (MtvUiFrag.IMtvFragEventListener)activity;
            return;
        }
        catch(ClassCastException classcastexception)
        {
            throw new ClassCastException((new StringBuilder()).append(activity.toString()).append(" must implement IMtvFragEventListener").toString());
        }
    }

    public void onClick(View view)
    {
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onClick(Landroid/view/View;)V");
        i = view.getId();
        i;
        JVM INSTR tableswitch 2131361978 2131362005: default 140
    //                   2131361978 209
    //                   2131361979 228
    //                   2131361980 247
    //                   2131361981 266
    //                   2131361982 285
    //                   2131361983 140
    //                   2131361984 304
    //                   2131361985 323
    //                   2131361986 342
    //                   2131361987 140
    //                   2131361988 361
    //                   2131361989 361
    //                   2131361990 140
    //                   2131361991 140
    //                   2131361992 140
    //                   2131361993 140
    //                   2131361994 141
    //                   2131361995 141
    //                   2131361996 140
    //                   2131361997 148
    //                   2131361998 148
    //                   2131361999 148
    //                   2131362000 140
    //                   2131362001 195
    //                   2131362002 195
    //                   2131362003 140
    //                   2131362004 202
    //                   2131362005 202;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L9 _L1 _L10 _L10 _L1 _L1 _L1 _L1 _L11 _L11 _L1 _L12 _L12 _L12 _L1 _L13 _L13 _L1 _L14 _L14
_L1:
        return;
_L11:
        toggleOutdoorVisibility();
        continue; /* Loop/switch isn't completed */
_L12:
        byte byte0;
        int ai8[];
        if(i == 0x7f0a00ce)
            byte0 = 2;
        else
        if(i == 0x7f0a00cf)
            byte0 = 3;
        else
            byte0 = 1;
        ai8 = new int[1];
        ai8[0] = byte0;
        updateColorTone(ai8);
        continue; /* Loop/switch isn't completed */
_L13:
        toggleFrameInterpolation();
        continue; /* Loop/switch isn't completed */
_L14:
        toggleCaption();
        continue; /* Loop/switch isn't completed */
_L2:
        int ai7[] = new int[1];
        ai7[0] = 0;
        updateAudioEffects(ai7);
        continue; /* Loop/switch isn't completed */
_L3:
        int ai6[] = new int[1];
        ai6[0] = 1;
        updateAudioEffects(ai6);
        continue; /* Loop/switch isn't completed */
_L4:
        int ai5[] = new int[1];
        ai5[0] = 2;
        updateAudioEffects(ai5);
        continue; /* Loop/switch isn't completed */
_L5:
        int ai4[] = new int[1];
        ai4[0] = 3;
        updateAudioEffects(ai4);
        continue; /* Loop/switch isn't completed */
_L6:
        int ai3[] = new int[1];
        ai3[0] = 4;
        updateAudioEffects(ai3);
        continue; /* Loop/switch isn't completed */
_L7:
        int ai2[] = new int[1];
        ai2[0] = 1;
        updateAudioLanguage(ai2);
        continue; /* Loop/switch isn't completed */
_L8:
        int ai1[] = new int[1];
        ai1[0] = 2;
        updateAudioLanguage(ai1);
        continue; /* Loop/switch isn't completed */
_L9:
        int ai[] = new int[1];
        ai[0] = 0;
        updateAudioLanguage(ai);
        continue; /* Loop/switch isn't completed */
_L10:
        toggle_5_1Channel(new int[0]);
        if(true) goto _L1; else goto _L15
_L15:
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mContext = getActivity().getApplicationContext();
        mPreferences = new MtvPreferences(getActivity().getApplicationContext());
        mMtvAudioManager = MtvUtilAudioManager.getInstance(mContext);
        if(bundle != null)
            settingsType = bundle.getInt("settingsType");
        if(settingsType != 19) goto _L2; else goto _L1
_L1:
        mView = layoutinflater.inflate(0x7f030024, null);
        initializeDisplayUI();
_L4:
        return mView;
_L2:
        if(settingsType == 18)
        {
            mView = layoutinflater.inflate(0x7f030023, null);
            initializeAudioUI();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onDestroy()V");
        super.onDestroy();
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onResume()V");
        if(settingsType != 19) goto _L2; else goto _L1
_L1:
        initializeDisplayUI();
_L4:
        super.onResume();
        return;
_L2:
        if(settingsType == 18)
            initializeAudioUI();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        bundle.putInt("settingsType", settingsType);
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiSettingsFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR tableswitch 109 109: default 60
    //                   109 61;
           goto _L1 _L2
_L1:
        return;
_L2:
        if(obj != null)
        {
            int ai[] = new int[1];
            ai[0] = ((Integer)obj).intValue();
            toggle_5_1Channel(ai);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private android.widget.SeekBar.OnSeekBarChangeListener brightnessSeekBarChangeListener = new android.widget.SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$1;->onProgressChanged(Landroid/widget/SeekBar;IZ)V");
            MtvUtilDebug.Low("MtvUiSettingsFrag", (new StringBuilder()).append("onProgressChanged = ").append(i).toString());
            Log.d(MtvUiSettingsFrag.this).setBrightnessLevel(i);
            Log.d(MtvUiSettingsFrag.this);
        }

        public void onStartTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$1;->onStartTrackingTouch(Landroid/widget/SeekBar;)V");
        }

        public void onStopTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$1;->onStopTrackingTouch(Landroid/widget/SeekBar;)V");
        }

        final MtvUiSettingsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;)V");
                this$0 = MtvUiSettingsFrag.this;
                super();
            }
    };
    private Context mContext;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvUtilAudioManager mMtvAudioManager;
    private MtvPreferences mPreferences;
    private SeekBar mSeekBar;
    private View mView;
    private int settingsType;


/*
    static MtvPreferences access$000(MtvUiSettingsFrag mtvuisettingsfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuisettingsfrag.mPreferences;
    }

*/


/*
    static void access$100(MtvUiSettingsFrag mtvuisettingsfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiSettingsFrag;)V");
        mtvuisettingsfrag.changeBrightnessLevel();
        return;
    }

*/
}
