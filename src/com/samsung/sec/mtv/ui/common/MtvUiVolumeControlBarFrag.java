// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.utility.MtvUtilAudioManager;
import com.sec.android.touchwiz.widget.TwSeekBar;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiVolumeControlBarFrag extends MtvUiFrag
{

    public MtvUiVolumeControlBarFrag()
    {
        mVolumeControlBar = null;
        mVolumeSeekBar = null;
        mVolumeTextView = null;
        mMtvAudMgr = null;
        mVolumeSeekBarChangeListener = new _cls1();
    }

    private void initializeUI(View view)
    {
        mVolumeSeekBar = (TwSeekBar)((LinearLayout)view.findViewById(0x7f0a00ef)).findViewById(0x7f0a00f0);
        mVolumeSeekBar.setOnTwSeekBarChangeListener(mVolumeSeekBarChangeListener);
        mVolumeTextView = (TextView)((RelativeLayout)view.findViewById(0x7f0a00ed)).findViewById(0x7f0a00ee);
        updateVolumePopupBar();
    }

    private void updateVolumePopupBar()
    {
        int i = mMtvAudMgr.getVolumeLevel();
        mVolumeSeekBar.setProgress(i);
        mVolumeTextView.setText((new StringBuilder()).append(i).append("").toString());
    }

    public void onAttach(Activity activity)
    {
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
        MtvUtilDebug.Low("VolumeFrag", "onCreate");
        super.onCreate(bundle);
        setRetainInstance(true);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getActivity().getApplicationContext());
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        MtvUtilDebug.Low("VolumeFrag", "onCreateView");
        mVolumeControlBar = layoutinflater.inflate(0x7f030028, viewgroup, false);
        initializeUI(mVolumeControlBar);
        return mVolumeControlBar;
    }

    public void onDestroyView()
    {
        MtvUtilDebug.Low("VolumeFrag", "onDestroyView");
        super.onDestroyView();
        mVolumeControlBar = null;
    }

    public void onDetach()
    {
        MtvUtilDebug.Low("VolumeFrag", "onDetach");
        super.onDetach();
    }

    public void onResume()
    {
        MtvUtilDebug.Low("VolumeFrag", "onResume");
        super.onResume();
        mVolumeSeekBar.setProgress(mMtvAudMgr.getVolumeLevel());
    }

    public void onUpdate(int i, Object obj)
    {
        MtvUtilDebug.Low("VolumeFrag", (new StringBuilder()).append("onUpdate : what = ").append(i).toString());
        i;
        JVM INSTR tableswitch 106 108: default 52
    //                   106 53
    //                   107 53
    //                   108 104;
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
    private TwSeekBar mVolumeSeekBar;
    private com.sec.android.touchwiz.widget.TwSeekBar.OnTwSeekBarChangeListener mVolumeSeekBarChangeListener;
    private TextView mVolumeTextView;




    private class _cls1
        implements com.sec.android.touchwiz.widget.TwSeekBar.OnTwSeekBarChangeListener
    {

        public void onProgressChanged(TwSeekBar twseekbar, int i, boolean flag)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("VolumeFrag", (new StringBuilder()).append("mVolumeSeekBarChangeListener:onProgressChanged( progress=").append(i).append(",fromTouch=").append(flag).toString());
            mMtvAudMgr.setVolumeLevel(i);
            mVolumeTextView.setText((new StringBuilder()).append(i).append("").toString());
            mListener.onFragEvent(224, null);
        }

        public void onStartTrackingTouch(TwSeekBar twseekbar)
        {
            MtvUtilDebug.Low("VolumeFrag", "onStartTrackingTouch() is called");
            mMtvAudMgr.setAudioMute(false);
            if(notifytouchtoactivity)
            {
                MtvUtilDebug.Low("VolumeFrag", "Notifing to activity");
                notifytouchtoactivity = false;
                mListener.onFragEvent(223, null);
            }
        }

        public void onStopTrackingTouch(TwSeekBar twseekbar)
        {
            MtvUtilDebug.Low("VolumeFrag", "onStopTrackingTouch() is called");
            notifytouchtoactivity = true;
            MtvUtilDebug.Low("VolumeFrag", "Notifing to activity touch ends...");
            mListener.onFragEvent(225, null);
        }

        private boolean notifytouchtoactivity;
        final MtvUiVolumeControlBarFrag this$0;

        _cls1()
        {
            this$0 = MtvUiVolumeControlBarFrag.this;
            super();
            notifytouchtoactivity = true;
        }
    }

}
