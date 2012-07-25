// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;-><init>()V");
        super();
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->initializeUI()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->setChannelDetails(Ljava/lang/String;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->setMainFragChannelDetails(Ljava/lang/String;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->toggleVolumeButton()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->updateComponentsOnLock(Z)V");
        mChUpButton.setEnabled(flag);
        mChDownButton.setEnabled(flag);
        mChGuideButton.setEnabled(flag);
        mVolumeButton.setEnabled(flag);
        mVolumeMuteButton.setEnabled(flag);
        mVolumeText.setEnabled(flag);
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onClick(Landroid/view/View;)V");
        view.getId();
        JVM INSTR tableswitch 2131361908 2131361910: default 40
    //                   2131361908 57
    //                   2131361909 73
    //                   2131361910 41;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onResume()V");
        toggleVolumeButton();
        super.onResume();
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiMainFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR lookupswitch 6: default 100
    //                   101: 128
    //                   106: 121
    //                   107: 121
    //                   108: 121
    //                   112: 107
    //                   243: 100;
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


/*
    static void access$000(MtvUiMainFrag mtvuimainfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)V");
        mtvuimainfrag.toggleVolumeButton();
        return;
    }

*/


/*
    static MtvUiFrag.IMtvFragEventListener access$100(MtvUiMainFrag mtvuimainfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuimainfrag.mListener;
    }

*/


/*
    static MtvUtilAudioManager access$200(MtvUiMainFrag mtvuimainfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)Lcom/samsung/sec/mtv/utility/MtvUtilAudioManager;");
        return mtvuimainfrag.mMtvAudMgr;
    }

*/

    private class _cls2
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$2;->onClick(Landroid/view/View;)V");
            MtvUtilDebug.Low("MtvUiMainFrag", "Mute Volume onClick:...");
            Log.d(MtvUiMainFrag.this);
            Log.d(MtvUiMainFrag.this).onFragEvent(220, null);
        }

        final MtvUiMainFrag this$0;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)V");
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnLongClickListener
    {

        public boolean onLongClick(View view)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$3;->onLongClick(Landroid/view/View;)Z");
            Log.d(MtvUiMainFrag.this).volumeMute();
            Log.d(MtvUiMainFrag.this);
            Log.d(MtvUiMainFrag.this).onFragEvent(226, null);
            return true;
        }

        final MtvUiMainFrag this$0;

        _cls3()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)V");
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnTouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$1;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
            return true;
        }

        final MtvUiMainFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiMainFrag;)V");
            this$0 = MtvUiMainFrag.this;
            super();
        }
    }

}
