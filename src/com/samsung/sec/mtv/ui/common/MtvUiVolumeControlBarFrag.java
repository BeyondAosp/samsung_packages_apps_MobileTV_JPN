// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.utility.MtvUtilAudioManager;
import com.sec.android.widget.SeekBar;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiVolumeControlBarFrag extends MtvUiFrag
{

    public MtvUiVolumeControlBarFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;-><init>()V");
        super();
        mVolumeControlBar = null;
        mVolumeSeekBar = null;
        mVolumeTextView = null;
        mMtvAudMgr = null;
        mVolumeSeekBarChangeListener = new _cls1();
    }

    private void initializeUI(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->initializeUI(Landroid/view/View;)V");
        mVolumeSeekBar = (SeekBar)((LinearLayout)view.findViewById(0x7f0a00ef)).findViewById(0x7f0a00f0);
        mVolumeSeekBar.setOnSeekBarChangeListener(mVolumeSeekBarChangeListener);
        mVolumeTextView = (TextView)((RelativeLayout)view.findViewById(0x7f0a00ed)).findViewById(0x7f0a00ee);
        updateVolumePopupBar();
    }

    private void updateVolumePopupBar()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->updateVolumePopupBar()V");
        int i = mMtvAudMgr.getVolumeLevel();
        mVolumeSeekBar.setProgress(i);
        mVolumeTextView.setText((new StringBuilder()).append(i).append("").toString());
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onAttach(Landroid/app/Activity;)V");
        MtvUtilDebug.Low("VolumeFrag", "onAttach");
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

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onCreate(Landroid/os/Bundle;)V");
        MtvUtilDebug.Low("VolumeFrag", "onCreate");
        super.onCreate(bundle);
        setRetainInstance(true);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getActivity().getApplicationContext());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        MtvUtilDebug.Low("VolumeFrag", "onCreateView");
        mVolumeControlBar = layoutinflater.inflate(0x7f030028, viewgroup, false);
        initializeUI(mVolumeControlBar);
        return mVolumeControlBar;
    }

    public void onDestroyView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onDestroyView()V");
        MtvUtilDebug.Low("VolumeFrag", "onDestroyView");
        super.onDestroyView();
        mVolumeControlBar = null;
    }

    public void onDetach()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onDetach()V");
        MtvUtilDebug.Low("VolumeFrag", "onDetach");
        super.onDetach();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onResume()V");
        MtvUtilDebug.Low("VolumeFrag", "onResume");
        super.onResume();
        mVolumeSeekBar.setProgress(mMtvAudMgr.getVolumeLevel());
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("VolumeFrag", (new StringBuilder()).append("onUpdate : what = ").append(i).toString());
        i;
        JVM INSTR tableswitch 106 108: default 60
    //                   106 61
    //                   107 61
    //                   108 112;
           goto _L1 _L2 _L2 _L3
_L1:
        return;
_L2:
        if(i == 106)
            mMtvAudMgr.volumeUp();
        else
            mMtvAudMgr.volumeDown();
        mVolumeSeekBar.setProgress(mMtvAudMgr.getVolumeLevel());
        mVolumeSeekBar.invalidate();
        updateVolumePopupBar();
        continue; /* Loop/switch isn't completed */
_L3:
        updateVolumePopupBar();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvUtilAudioManager mMtvAudMgr;
    private View mVolumeControlBar;
    private SeekBar mVolumeSeekBar;
    private com.sec.android.widget.SeekBar.OnSeekBarChangeListener mVolumeSeekBarChangeListener;
    private TextView mVolumeTextView;


/*
    static MtvUtilAudioManager access$000(MtvUiVolumeControlBarFrag mtvuivolumecontrolbarfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;)Lcom/samsung/sec/mtv/utility/MtvUtilAudioManager;");
        return mtvuivolumecontrolbarfrag.mMtvAudMgr;
    }

*/


/*
    static TextView access$100(MtvUiVolumeControlBarFrag mtvuivolumecontrolbarfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;)Landroid/widget/TextView;");
        return mtvuivolumecontrolbarfrag.mVolumeTextView;
    }

*/


/*
    static MtvUiFrag.IMtvFragEventListener access$200(MtvUiVolumeControlBarFrag mtvuivolumecontrolbarfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuivolumecontrolbarfrag.mListener;
    }

*/

    private class _cls1
        implements com.sec.android.widget.SeekBar.OnSeekBarChangeListener
    {

        public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag$1;->onProgressChanged(Lcom/sec/android/widget/SeekBar;IZ)V");
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("VolumeFrag", (new StringBuilder()).append("mVolumeSeekBarChangeListener:onProgressChanged( progress=").append(i).append(",fromTouch=").append(flag).toString());
            Log.d(MtvUiVolumeControlBarFrag.this).setVolumeLevel(i);
            Log.d(MtvUiVolumeControlBarFrag.this).setText((new StringBuilder()).append(i).append("").toString());
            Log.d(MtvUiVolumeControlBarFrag.this).onFragEvent(224, null);
        }

        public void onStartTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag$1;->onStartTrackingTouch(Lcom/sec/android/widget/SeekBar;)V");
            MtvUtilDebug.Low("VolumeFrag", "onStartTrackingTouch() is called");
            Log.d(MtvUiVolumeControlBarFrag.this).setAudioMute(false);
            if(notifytouchtoactivity)
            {
                MtvUtilDebug.Low("VolumeFrag", "Notifing to activity");
                notifytouchtoactivity = false;
                Log.d(MtvUiVolumeControlBarFrag.this).onFragEvent(223, null);
            }
        }

        public void onStopTrackingTouch(SeekBar seekbar)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag$1;->onStopTrackingTouch(Lcom/sec/android/widget/SeekBar;)V");
            MtvUtilDebug.Low("VolumeFrag", "onStopTrackingTouch() is called");
            notifytouchtoactivity = true;
            MtvUtilDebug.Low("VolumeFrag", "Notifing to activity touch ends...");
            Log.d(MtvUiVolumeControlBarFrag.this).onFragEvent(225, null);
        }

        private boolean notifytouchtoactivity;
        final MtvUiVolumeControlBarFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiVolumeControlBarFrag;)V");
            this$0 = MtvUiVolumeControlBarFrag.this;
            super();
            notifytouchtoactivity = true;
        }
    }

}
