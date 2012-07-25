// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.configration;

import android.app.Activity;
import android.app.Fragment;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.MtvUtilDebugSetting;
import android.os.Bundle;
import android.view.*;
import android.widget.CheckBox;
import android.widget.Toast;
import com.samsung.sec.mtv.utility.MtvUtilConfigSetting;
import com.samsung.sec.mtv.utility.MtvUtilConfigSettingControl;

public class MtvUiFragmentConfigration extends Fragment
    implements android.view.View.OnClickListener
{

    public MtvUiFragmentConfigration()
    {
    }

    private void initializeUI(View view)
    {
        mCheckBoxTSCapture = (CheckBox)view.findViewById(0x7f0a00f3);
        mCheckBoxFileSimulation = (CheckBox)view.findViewById(0x7f0a00f2);
        mCheckBoxDebugError = (CheckBox)view.findViewById(0x7f0a00f5);
        mCheckBoxDebugHigh = (CheckBox)view.findViewById(0x7f0a00f6);
        mCheckBoxDebugMid = (CheckBox)view.findViewById(0x7f0a00f7);
        mCheckBoxDebugLow = (CheckBox)view.findViewById(0x7f0a00f8);
        mCheckBoxDebugBML = (CheckBox)view.findViewById(0x7f0a0103);
        mCheckBoxDebugLive = (CheckBox)view.findViewById(0x7f0a00f9);
        mCheckBoxDebugLocal = (CheckBox)view.findViewById(0x7f0a00fa);
        mCheckBoxDebugChnGuide = (CheckBox)view.findViewById(0x7f0a00fb);
        mCheckBoxDebugTvLink = (CheckBox)view.findViewById(0x7f0a00fc);
        mCheckBoxDebugTestMode = (CheckBox)view.findViewById(0x7f0a00fd);
        mCheckBoxDebugFragments = (CheckBox)view.findViewById(0x7f0a00fe);
        mCheckBoxDebugCommon = (CheckBox)view.findViewById(0x7f0a00ff);
        mCheckBoxDebugUtility = (CheckBox)view.findViewById(0x7f0a0100);
        mCheckBoxDebugReservation = (CheckBox)view.findViewById(0x7f0a0101);
        mCheckBoxDebugHelper = (CheckBox)view.findViewById(0x7f0a0102);
        mCheckBoxDebugPBCtx = (CheckBox)view.findViewById(0x7f0a0104);
        mCheckBoxDebugAppBase = (CheckBox)view.findViewById(0x7f0a0105);
        mCheckBoxDebugPlayer = (CheckBox)view.findViewById(0x7f0a0106);
        mCheckBoxDebugFW = (CheckBox)view.findViewById(0x7f0a0107);
        mCheckBoxDebugFWUtility = (CheckBox)view.findViewById(0x7f0a0108);
        mConfigSettingControl = new MtvUtilConfigSettingControl();
        mConfigSetting = mConfigSettingControl.getConfigFromFile();
        if(mConfigSetting != null)
        {
            mCheckBoxFileSimulation.setChecked(mConfigSetting.iTsFileSimul);
            mCheckBoxTSCapture.setChecked(mConfigSetting.iTsCapture);
        }
        mCheckBoxFileSimulation.setOnClickListener(this);
        mCheckBoxTSCapture.setOnClickListener(this);
        mDebugSetting = MtvUtilDebugSetting.getInstance();
        mCheckBoxDebugError.setChecked(mDebugSetting.isAllowedDebugLevel(8));
        mCheckBoxDebugHigh.setChecked(mDebugSetting.isAllowedDebugLevel(4));
        mCheckBoxDebugMid.setChecked(mDebugSetting.isAllowedDebugLevel(2));
        mCheckBoxDebugLow.setChecked(mDebugSetting.isAllowedDebugLevel(1));
        mCheckBoxDebugError.setOnClickListener(this);
        mCheckBoxDebugHigh.setOnClickListener(this);
        mCheckBoxDebugMid.setOnClickListener(this);
        mCheckBoxDebugLow.setOnClickListener(this);
        mCheckBoxDebugBML.setChecked(mDebugSetting.isAllowedModuleForDebug(1));
        mCheckBoxDebugLive.setChecked(mDebugSetting.isAllowedModuleForDebug(2));
        mCheckBoxDebugLocal.setChecked(mDebugSetting.isAllowedModuleForDebug(4));
        mCheckBoxDebugChnGuide.setChecked(mDebugSetting.isAllowedModuleForDebug(8));
        mCheckBoxDebugTvLink.setChecked(mDebugSetting.isAllowedModuleForDebug(16));
        mCheckBoxDebugTestMode.setChecked(mDebugSetting.isAllowedModuleForDebug(32));
        mCheckBoxDebugFragments.setChecked(mDebugSetting.isAllowedModuleForDebug(64));
        mCheckBoxDebugCommon.setChecked(mDebugSetting.isAllowedModuleForDebug(128));
        mCheckBoxDebugUtility.setChecked(mDebugSetting.isAllowedModuleForDebug(256));
        mCheckBoxDebugReservation.setChecked(mDebugSetting.isAllowedModuleForDebug(512));
        mCheckBoxDebugHelper.setChecked(mDebugSetting.isAllowedModuleForDebug(1024));
        mCheckBoxDebugPBCtx.setChecked(mDebugSetting.isAllowedModuleForDebug(2048));
        mCheckBoxDebugAppBase.setChecked(mDebugSetting.isAllowedModuleForDebug(4096));
        mCheckBoxDebugPlayer.setChecked(mDebugSetting.isAllowedModuleForDebug(8192));
        mCheckBoxDebugFW.setChecked(mDebugSetting.isAllowedModuleForDebug(16384));
        mCheckBoxDebugFWUtility.setChecked(mDebugSetting.isAllowedModuleForDebug(32768));
        mCheckBoxDebugBML.setOnClickListener(this);
        mCheckBoxDebugLive.setOnClickListener(this);
        mCheckBoxDebugLocal.setOnClickListener(this);
        mCheckBoxDebugMid.setOnClickListener(this);
        mCheckBoxDebugChnGuide.setOnClickListener(this);
        mCheckBoxDebugTvLink.setOnClickListener(this);
        mCheckBoxDebugTestMode.setOnClickListener(this);
        mCheckBoxDebugFragments.setOnClickListener(this);
        mCheckBoxDebugCommon.setOnClickListener(this);
        mCheckBoxDebugUtility.setOnClickListener(this);
        mCheckBoxDebugReservation.setOnClickListener(this);
        mCheckBoxDebugHelper.setOnClickListener(this);
        mCheckBoxDebugPBCtx.setOnClickListener(this);
        mCheckBoxDebugAppBase.setOnClickListener(this);
        mCheckBoxDebugPlayer.setOnClickListener(this);
        mCheckBoxDebugFW.setOnClickListener(this);
        mCheckBoxDebugFWUtility.setOnClickListener(this);
    }

    public void onAttach(Activity activity)
    {
        MtvUtilDebug.High("TAG", "onAttach");
        super.onAttach(activity);
    }

    public void onClick(View view)
    {
        boolean flag = true;
        view.getId();
        JVM INSTR tableswitch 2131362034 2131362056: default 112
    //                   2131362034 150
    //                   2131362035 120
    //                   2131362036 112
    //                   2131362037 178
    //                   2131362038 250
    //                   2131362039 311
    //                   2131362040 371
    //                   2131362041 466
    //                   2131362042 501
    //                   2131362043 536
    //                   2131362044 574
    //                   2131362045 612
    //                   2131362046 650
    //                   2131362047 688
    //                   2131362048 729
    //                   2131362049 770
    //                   2131362050 811
    //                   2131362051 431
    //                   2131362052 852
    //                   2131362053 893
    //                   2131362054 934
    //                   2131362055 982
    //                   2131362056 1023;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23
_L1:
        MtvUtilDebug.Error("MtvUiFragmentConfigration", "Selected default checkbox");
_L25:
        return;
_L3:
        MtvUtilConfigSetting mtvutilconfigsetting1 = mConfigSetting;
        if(mConfigSetting.iTsCapture)
            flag = false;
        mtvutilconfigsetting1.iTsCapture = flag;
        continue; /* Loop/switch isn't completed */
_L2:
        MtvUtilConfigSetting mtvutilconfigsetting = mConfigSetting;
        if(mConfigSetting.iTsFileSimul)
            flag = false;
        mtvutilconfigsetting.iTsFileSimul = flag;
        continue; /* Loop/switch isn't completed */
_L4:
        if(mDebugSetting.isAllowedDebugLevel(8))
            mDebugSetting.setDebugValuesForLevel(8, 0);
        else
            mDebugSetting.setDebugValuesForLevel(8, flag);
        mDebugSetting.setDebugValuesForLevel(7, 0);
        mCheckBoxDebugHigh.setChecked(false);
        mCheckBoxDebugMid.setChecked(false);
        mCheckBoxDebugLow.setChecked(false);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mDebugSetting.isAllowedDebugLevel(4))
        {
            mDebugSetting.setDebugValuesForLevel(7, 0);
            mCheckBoxDebugMid.setChecked(false);
            mCheckBoxDebugLow.setChecked(false);
        } else
        {
            mDebugSetting.setDebugValuesForLevel(12, flag);
            mCheckBoxDebugError.setChecked(flag);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(mDebugSetting.isAllowedDebugLevel(2))
        {
            mDebugSetting.setDebugValuesForLevel(3, 0);
            mCheckBoxDebugLow.setChecked(false);
        } else
        {
            mDebugSetting.setDebugValuesForLevel(14, flag);
            mCheckBoxDebugError.setChecked(flag);
            mCheckBoxDebugHigh.setChecked(flag);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if(mDebugSetting.isAllowedDebugLevel(flag))
        {
            mDebugSetting.setDebugValuesForLevel(flag, 0);
        } else
        {
            mDebugSetting.setDebugValuesForLevel(15, flag);
            mCheckBoxDebugError.setChecked(flag);
            mCheckBoxDebugHigh.setChecked(flag);
            mCheckBoxDebugMid.setChecked(flag);
        }
        continue; /* Loop/switch isn't completed */
_L18:
        if(mDebugSetting.isAllowedModuleForDebug(flag))
            mDebugSetting.setDebugValuesOfModule(flag, 0);
        else
            mDebugSetting.setDebugValuesOfModule(flag, flag);
        continue; /* Loop/switch isn't completed */
_L8:
        if(mDebugSetting.isAllowedModuleForDebug(2))
            mDebugSetting.setDebugValuesOfModule(2, 0);
        else
            mDebugSetting.setDebugValuesOfModule(2, flag);
        continue; /* Loop/switch isn't completed */
_L9:
        if(mDebugSetting.isAllowedModuleForDebug(4))
            mDebugSetting.setDebugValuesOfModule(4, 0);
        else
            mDebugSetting.setDebugValuesOfModule(4, flag);
        continue; /* Loop/switch isn't completed */
_L10:
        if(mDebugSetting.isAllowedModuleForDebug(8))
            mDebugSetting.setDebugValuesOfModule(8, 0);
        else
            mDebugSetting.setDebugValuesOfModule(8, flag);
        continue; /* Loop/switch isn't completed */
_L11:
        if(mDebugSetting.isAllowedModuleForDebug(16))
            mDebugSetting.setDebugValuesOfModule(16, 0);
        else
            mDebugSetting.setDebugValuesOfModule(16, flag);
        continue; /* Loop/switch isn't completed */
_L12:
        if(mDebugSetting.isAllowedModuleForDebug(32))
            mDebugSetting.setDebugValuesOfModule(32, 0);
        else
            mDebugSetting.setDebugValuesOfModule(32, flag);
        continue; /* Loop/switch isn't completed */
_L13:
        if(mDebugSetting.isAllowedModuleForDebug(64))
            mDebugSetting.setDebugValuesOfModule(64, 0);
        else
            mDebugSetting.setDebugValuesOfModule(64, flag);
        continue; /* Loop/switch isn't completed */
_L14:
        if(mDebugSetting.isAllowedModuleForDebug(128))
            mDebugSetting.setDebugValuesOfModule(128, 0);
        else
            mDebugSetting.setDebugValuesOfModule(128, flag);
        continue; /* Loop/switch isn't completed */
_L15:
        if(mDebugSetting.isAllowedModuleForDebug(256))
            mDebugSetting.setDebugValuesOfModule(256, 0);
        else
            mDebugSetting.setDebugValuesOfModule(256, flag);
        continue; /* Loop/switch isn't completed */
_L16:
        if(mDebugSetting.isAllowedModuleForDebug(512))
            mDebugSetting.setDebugValuesOfModule(512, 0);
        else
            mDebugSetting.setDebugValuesOfModule(512, flag);
        continue; /* Loop/switch isn't completed */
_L17:
        if(mDebugSetting.isAllowedModuleForDebug(1024))
            mDebugSetting.setDebugValuesOfModule(1024, 0);
        else
            mDebugSetting.setDebugValuesOfModule(1024, flag);
        continue; /* Loop/switch isn't completed */
_L19:
        if(mDebugSetting.isAllowedModuleForDebug(2048))
            mDebugSetting.setDebugValuesOfModule(2048, 0);
        else
            mDebugSetting.setDebugValuesOfModule(2048, flag);
        continue; /* Loop/switch isn't completed */
_L20:
        if(mDebugSetting.isAllowedModuleForDebug(4096))
            mDebugSetting.setDebugValuesOfModule(4096, 0);
        else
            mDebugSetting.setDebugValuesOfModule(4096, flag);
        continue; /* Loop/switch isn't completed */
_L21:
        MtvUtilDebug.Low("MtvUiFragmentConfigration", "Selected CheckBox_Debug_Player");
        if(mDebugSetting.isAllowedModuleForDebug(8192))
            mDebugSetting.setDebugValuesOfModule(8192, 0);
        else
            mDebugSetting.setDebugValuesOfModule(8192, flag);
        continue; /* Loop/switch isn't completed */
_L22:
        if(mDebugSetting.isAllowedModuleForDebug(16384))
            mDebugSetting.setDebugValuesOfModule(16384, 0);
        else
            mDebugSetting.setDebugValuesOfModule(16384, flag);
        continue; /* Loop/switch isn't completed */
_L23:
        if(mDebugSetting.isAllowedModuleForDebug(32768))
            mDebugSetting.setDebugValuesOfModule(32768, 0);
        else
            mDebugSetting.setDebugValuesOfModule(32768, flag);
        if(true) goto _L25; else goto _L24
_L24:
    }

    public void onCreate(Bundle bundle)
    {
        MtvUtilDebug.High("MtvUiFragmentConfigration", "onCreate");
        setHasOptionsMenu(true);
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        MtvUtilDebug.High("MtvUiFragmentConfigration", "onCreateView");
        View view = layoutinflater.inflate(0x7f030029, viewgroup, false);
        initializeUI(view);
        return view;
    }

    public void onDestroyView()
    {
        MtvUtilDebug.High("MtvUiFragmentConfigration", "onDestroyView");
        super.onDestroyView();
    }

    public void onDetach()
    {
        MtvUtilDebug.High("MtvUiFragmentConfigration", "onDetach");
        super.onDetach();
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 1 1: default 24
    //                   1 30;
           goto _L1 _L2
_L1:
        return super.onOptionsItemSelected(menuitem);
_L2:
        if(!mConfigSettingControl.setConfigToFile(mConfigSetting))
            Toast.makeText(getActivity().getApplicationContext(), "Save Failed !!!", 1).show();
        else
            Toast.makeText(getActivity().getApplicationContext(), "Save Sucess !!!", 1).show();
        MtvUtilDebug.getDebugInstance().saveDbgInfoToFile();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        menu.add(0, 1, 0, "Save");
        super.onPrepareOptionsMenu(menu);
    }

    private CheckBox mCheckBoxDebugAppBase;
    private CheckBox mCheckBoxDebugBML;
    private CheckBox mCheckBoxDebugChnGuide;
    private CheckBox mCheckBoxDebugCommon;
    private CheckBox mCheckBoxDebugError;
    private CheckBox mCheckBoxDebugFW;
    private CheckBox mCheckBoxDebugFWUtility;
    private CheckBox mCheckBoxDebugFragments;
    private CheckBox mCheckBoxDebugHelper;
    private CheckBox mCheckBoxDebugHigh;
    private CheckBox mCheckBoxDebugLive;
    private CheckBox mCheckBoxDebugLocal;
    private CheckBox mCheckBoxDebugLow;
    private CheckBox mCheckBoxDebugMid;
    private CheckBox mCheckBoxDebugPBCtx;
    private CheckBox mCheckBoxDebugPlayer;
    private CheckBox mCheckBoxDebugReservation;
    private CheckBox mCheckBoxDebugTestMode;
    private CheckBox mCheckBoxDebugTvLink;
    private CheckBox mCheckBoxDebugUtility;
    private CheckBox mCheckBoxFileSimulation;
    private CheckBox mCheckBoxTSCapture;
    private MtvUtilConfigSetting mConfigSetting;
    private MtvUtilConfigSettingControl mConfigSettingControl;
    private MtvUtilDebugSetting mDebugSetting;
}
