// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.utility.*;
import java.util.StringTokenizer;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiMainFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{

    public MtvUiMainFrag()
    {
        mMtvAudMgr = null;
        mChUpButton = null;
        mChDownButton = null;
        mChGuideButton = null;
        mVolumeButton = null;
        mVolumeMuteButton = null;
        mVolumeText = null;
        mControlLayout = null;
        mProgramChannelDetail = "";
        mChannelName = null;
        volumeOnClickListener = new _cls2();
        volumeOnLongClickListener = new _cls3();
    }

    private void initializeUI()
    {
        int i = MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext());
        mControlLayout = (RelativeLayout)mLayoutView.findViewById(0x7f0a0073);
        mControlLayout.setBackgroundColor(0x7f060008);
        mControlLayout.setOnTouchListener(new _cls1());
        mChUpButton = (ImageButton)mLayoutView.findViewById(0x7f0a0076);
        mChDownButton = (ImageButton)mLayoutView.findViewById(0x7f0a0074);
        mChGuideButton = (Button)mLayoutView.findViewById(0x7f0a0075);
        mVolumeButton = (ImageButton)mLayoutView.findViewById(0x7f0a0077);
        mVolumeMuteButton = (ImageButton)mLayoutView.findViewById(0x7f0a0078);
        mVolumeText = (TextView)mLayoutView.findViewById(0x7f0a0079);
        mChUpButton.setOnClickListener(this);
        mChDownButton.setOnClickListener(this);
        mChGuideButton.setOnClickListener(this);
        mVolumeButton.setOnClickListener(volumeOnClickListener);
        mVolumeButton.setOnLongClickListener(volumeOnLongClickListener);
        mVolumeMuteButton.setOnClickListener(volumeOnClickListener);
        mVolumeMuteButton.setOnLongClickListener(volumeOnLongClickListener);
        mVolumeText.setOnClickListener(volumeOnClickListener);
        mVolumeText.setOnLongClickListener(volumeOnLongClickListener);
        if(i == 0)
        {
            int j = mPreferences.getLatestChannelNumberForDisplay();
            if(j < 0)
                mChGuideButton.setText("");
            else
                mChGuideButton.setText(Integer.toString(j));
        } else
        {
            setChannelDetails(mProgramChannelDetail);
        }
        mListener.onFragEvent(201, Integer.valueOf(mPreferences.getDisplaySize()));
    }

    private void setChannelDetails(String s)
    {
        int i = mPreferences.getLatestChannelNumberForDisplay();
        String s1;
        if(i < 0)
            s1 = "";
        else
            s1 = Integer.toString(i);
        if(s != null)
        {
            String s2 = new String(s);
            s2.concat("\n");
            StringTokenizer stringtokenizer = new StringTokenizer(s2, "\n");
            if(stringtokenizer.hasMoreTokens())
                s1 = (new StringBuilder()).append(s1).append(" ").append(stringtokenizer.nextToken()).toString();
            setMainFragChannelDetails(s1);
        } else
        {
            setMainFragChannelDetails(null);
        }
    }

    private void setMainFragChannelDetails(String s)
    {
        if(s != null)
        {
            mChannelName = s;
        } else
        {
            int i = mPreferences.getLatestChannelNumberForDisplay();
            if(i < 0)
                mChannelName = "";
            else
                mChannelName = Integer.toString(i);
        }
        if(mChGuideButton != null)
            mChGuideButton.setText(mChannelName);
    }

    private void toggleVolumeButton()
    {
        int i = mMtvAudMgr.getVolumeLevel();
        if(i == 0)
        {
            if(mVolumeMuteButton != null)
                mVolumeMuteButton.setVisibility(0);
            if(mVolumeButton != null)
                mVolumeButton.setVisibility(8);
        } else
        {
            mVolumeMuteButton.setVisibility(8);
            mVolumeButton.setVisibility(0);
        }
        if(mVolumeText != null)
            if(i == 0)
                mVolumeText.setText(null);
            else
            if(i < 10)
                mVolumeText.setText((new StringBuilder()).append("0").append(mMtvAudMgr.getVolumeLevel()).toString());
            else
                mVolumeText.setText((new StringBuilder()).append("").append(mMtvAudMgr.getVolumeLevel()).toString());
    }

    private void updateComponentsOnLock(boolean flag)
    {
        mChUpButton.setEnabled(flag);
        mChDownButton.setEnabled(flag);
        mChGuideButton.setEnabled(flag);
        mVolumeButton.setEnabled(flag);
        mVolumeMuteButton.setEnabled(flag);
        mVolumeText.setEnabled(flag);
    }

    public void onAttach(Activity activity)
    {
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
        view.getId();
        JVM INSTR tableswitch 2131361908 2131361910: default 32
    //                   2131361908 49
    //                   2131361909 65
    //                   2131361910 33;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L4:
        mListener.onFragEvent(241, null);
        continue; /* Loop/switch isn't completed */
_L2:
        mListener.onFragEvent(242, null);
        continue; /* Loop/switch isn't completed */
_L3:
        mListener.onFragEvent(244, null);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        MtvUtilDebug.Low("MtvUiMainFrag", "onCreateView");
        mContext = getActivity().getApplicationContext();
        mLayoutView = layoutinflater.inflate(0x7f030016, null);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(mContext);
        mPreferences = new MtvPreferences(mContext);
        initializeUI();
        toggleVolumeButton();
        return mLayoutView;
    }

    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        toggleVolumeButton();
        super.onResume();
    }

    public void onStart()
    {
        super.onStart();
    }

    public void onStop()
    {
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        MtvUtilDebug.Low("MtvUiMainFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR lookupswitch 6: default 92
    //                   101: 120
    //                   106: 113
    //                   107: 113
    //                   108: 113
    //                   112: 99
    //                   243: 92;
           goto _L1 _L2 _L3 _L3 _L3 _L4 _L1
_L1:
        super.onUpdate(i, obj);
        return;
_L4:
        updateComponentsOnLock(((Boolean)obj).booleanValue());
        continue; /* Loop/switch isn't completed */
_L3:
        toggleVolumeButton();
        continue; /* Loop/switch isn't completed */
_L2:
        if(obj != null)
            mProgramChannelDetail = (String)obj;
        else
            mProgramChannelDetail = "";
        MtvUtilDebug.Low("MtvUiMainFrag", (new StringBuilder()).append("onUpdate: UPDATE_NOW_PROGRAM : mProgramChannelDetail= ").append(mProgramChannelDetail).toString());
        if(MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) == 0)
        {
            int j = mPreferences.getLatestChannelNumberForDisplay();
            if(j < 0)
                mChGuideButton.setText("");
            else
                mChGuideButton.setText(Integer.toString(j));
        } else
        {
            setChannelDetails(mProgramChannelDetail);
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    private ImageButton mChDownButton;
    private Button mChGuideButton;
    private ImageButton mChUpButton;
    private String mChannelName;
    private Context mContext;
    private RelativeLayout mControlLayout;
    private View mLayoutView;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvUtilAudioManager mMtvAudMgr;
    private MtvPreferences mPreferences;
    private String mProgramChannelDetail;
    private ImageButton mVolumeButton;
    private ImageButton mVolumeMuteButton;
    private TextView mVolumeText;
    android.view.View.OnClickListener volumeOnClickListener;
    android.view.View.OnLongClickListener volumeOnLongClickListener;




    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            MtvUtilDebug.Low("MtvUiMainFrag", "Mute Volume onClick:...");
            toggleVolumeButton();
            mListener.onFragEvent(220, null);
        }

        final MtvUiMainFrag this$0;

        _cls2()
        {
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            mMtvAudMgr.volumeMute();
            toggleVolumeButton();
            mListener.onFragEvent(226, null);
            return true;
        }

        final MtvUiMainFrag this$0;

        _cls3()
        {
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnTouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            return true;
        }

        final MtvUiMainFrag this$0;

        _cls1()
        {
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }

}
