// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.utility.*;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiRecordFrag extends MtvUiFrag
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{

    public MtvUiRecordFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;-><init>()V");
        super();
        mVolumeButton = null;
        mVolumeMuteButton = null;
        mVolumeText = null;
        mRecDuration = null;
        mStopIcon = null;
        mRecScrDisp = null;
        mRecMainLayout = null;
        mMtvAudMgr = null;
        mRecMainLayoutVisibility = 0;
        mRecDurationVisibility = 0;
        mRecordHandler = new _cls1();
        mRunnableUpdateRecTime = new _cls2();
    }

    public MtvUiRecordFrag(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;-><init>(Z)V");
        this();
        if(flag)
            mRecTime = 0;
    }

    private void initializeUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->initializeUI()V");
        mRecMainLayout = (RelativeLayout)mLayoutView.findViewById(0x7f0a00b3);
        mRecDuration = (TextView)mLayoutView.findViewById(0x7f0a00b2);
        mVolumeButton = (ImageButton)mLayoutView.findViewById(0x7f0a00b6);
        mVolumeText = (TextView)mLayoutView.findViewById(0x7f0a0079);
        if(mVolumeButton != null)
        {
            mVolumeButton.setOnClickListener(this);
            mVolumeButton.setOnLongClickListener(this);
        } else
        {
            MtvUtilDebug.Low("MtvUiRecordFrag", "initializeUI:mRecVolume : null ");
        }
        mVolumeMuteButton = (ImageButton)mLayoutView.findViewById(0x7f0a00b7);
        if(mVolumeMuteButton != null)
        {
            mVolumeMuteButton.setOnClickListener(this);
            mVolumeMuteButton.setOnLongClickListener(this);
        } else
        {
            MtvUtilDebug.Low("MtvUiRecordFrag", "mRecVolumeMute : null ");
        }
        if(mVolumeText != null)
        {
            mVolumeText.setOnClickListener(this);
            mVolumeText.setOnLongClickListener(this);
        } else
        {
            MtvUtilDebug.Low("MtvUiRecordFrag", "initializeUI:mVolumeText : null ");
        }
        mStopIcon = (Button)mLayoutView.findViewById(0x7f0a00b5);
        if(mStopIcon != null)
            mStopIcon.setOnClickListener(this);
        else
            MtvUtilDebug.Low("MtvUiRecordFrag", "mStopIcon : null ");
        mRecScrDisp = (ImageButton)mLayoutView.findViewById(0x7f0a00b4);
        if(mRecScrDisp != null)
            mRecScrDisp.setOnClickListener(this);
        else
            MtvUtilDebug.Low("MtvUiRecordFrag", "mRecScrDisp : null ");
        if(1 == MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) && mRecScrDisp != null)
        {
            mRecScrDisp.setOnClickListener(this);
            if(mPreferences.getDisplaySize() == 0)
                mRecScrDisp.setImageResource(0x7f020036);
            else
                mRecScrDisp.setImageResource(0x7f020037);
        }
    }

    private void toggleVolumeButton()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->toggleVolumeButton()V");
        if(mMtvAudMgr.getVolumeLevel() == 0)
        {
            if(mVolumeMuteButton != null)
                mVolumeMuteButton.setVisibility(0);
            if(mVolumeButton != null)
                mVolumeButton.setVisibility(8);
        } else
        {
            if(mVolumeMuteButton != null)
                mVolumeMuteButton.setVisibility(8);
            if(mVolumeButton != null)
                mVolumeButton.setVisibility(0);
        }
        if(mVolumeText != null)
            if(mMtvAudMgr.getVolumeLevel() == 0)
                mVolumeText.setText(null);
            else
            if(mMtvAudMgr.getVolumeLevel() < 10)
                mVolumeText.setText((new StringBuilder()).append("0").append(mMtvAudMgr.getVolumeLevel()).toString());
            else
                mVolumeText.setText((new StringBuilder()).append("").append(mMtvAudMgr.getVolumeLevel()).toString());
    }

    private void updateRecordComponents(boolean flag, boolean flag1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->updateRecordComponents(ZZ)V");
        MtvUtilDebug.Low("MtvUiRecordFrag", (new StringBuilder()).append("updateRecordComponents: ").append(flag).toString());
        int i;
        int j;
        if(flag)
            i = 0;
        else
            i = 8;
        j = MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext());
        if(j == 1)
        {
            mRecMainLayout.setVisibility(i);
        } else
        {
            mRecMainLayout.setVisibility(0);
            mVolumeButton.setEnabled(flag);
            mVolumeMuteButton.setEnabled(flag);
            mVolumeText.setEnabled(false);
            mStopIcon.setEnabled(flag);
        }
        if(!flag1)
            mRecDuration.setVisibility(i);
        else
        if(!flag && j == 1)
            mRecDuration.setVisibility(8);
        else
            mRecDuration.setVisibility(0);
        mRecMainLayoutVisibility = mRecMainLayout.getVisibility();
        mRecDurationVisibility = mRecDuration.getVisibility();
        if(mRecMainLayoutVisibility == 0)
            mListener.onFragEvent(253, null);
    }

    private void updateRecordComponentsVisibility()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->updateRecordComponentsVisibility()V");
        mRecMainLayout.setVisibility(mRecMainLayoutVisibility);
        mRecDuration.setVisibility(mRecDurationVisibility);
        if(mRecMainLayoutVisibility == 0)
            mListener.onFragEvent(253, null);
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onClick(Landroid/view/View;)V");
        MtvUtilDebug.Low("MtvUiRecordFrag", "recScreen  onClick");
        view.getId();
        JVM INSTR lookupswitch 5: default 72
    //                   2131361913: 73
    //                   2131361972: 89
    //                   2131361973: 183
    //                   2131361974: 73
    //                   2131361975: 73;
           goto _L1 _L2 _L3 _L4 _L2 _L2
_L1:
        return;
_L2:
        mListener.onFragEvent(227, null);
        continue; /* Loop/switch isn't completed */
_L3:
        MtvUtilDebug.Low("MtvUiRecordFrag", "recScreenDisp");
        if(mPreferences.getDisplaySize() == 0)
        {
            mListener.onFragEvent(201, Integer.valueOf(1));
            mPreferences.setDisplaySize(1);
            onUpdate(243, Integer.valueOf(1));
        } else
        {
            mListener.onFragEvent(201, Integer.valueOf(0));
            mPreferences.setDisplaySize(0);
            onUpdate(243, Integer.valueOf(0));
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mRecTime >= 3)
            mListener.onFragEvent(252, null);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        mContext = getActivity().getApplicationContext();
        mPreferences = new MtvPreferences(mContext);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(mContext);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        MtvUtilDebug.Low("MtvUiRecordFrag", "onCreateView...");
        mLayoutView = layoutinflater.inflate(0x7f030022, null);
        initializeUI();
        updateRecordComponentsVisibility();
        mRecordHandler.removeCallbacks(mRunnableUpdateRecTime);
        mRecordHandler.postDelayed(mRunnableUpdateRecTime, 0L);
        toggleVolumeButton();
        return mLayoutView;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onDestroy()V");
        mPreferences = null;
        mRecordHandler.removeCallbacks(mRunnableUpdateRecTime);
        super.onDestroy();
    }

    public boolean onLongClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onLongClick(Landroid/view/View;)Z");
        view.getId();
        JVM INSTR lookupswitch 3: default 48
    //                   2131361913: 52
    //                   2131361974: 52
    //                   2131361975: 52;
           goto _L1 _L2 _L2 _L2
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        mMtvAudMgr.volumeMute();
        toggleVolumeButton();
        mListener.onFragEvent(226, null);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onResume()V");
        super.onResume();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        if(!getActivity().isFinishing())
        {
            bundle.putInt("recMainLayoutVisibility", mRecMainLayoutVisibility);
            bundle.putInt("recDurationVisibility", mRecDurationVisibility);
        }
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiRecordFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR lookupswitch 8: default 116
    //                   106: 123
    //                   107: 123
    //                   108: 123
    //                   111: 130
    //                   112: 145
    //                   116: 218
    //                   243: 160
    //                   327: 241;
           goto _L1 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        super.onUpdate(i, obj);
        return;
_L2:
        toggleVolumeButton();
        continue; /* Loop/switch isn't completed */
_L3:
        updateRecordComponents(((Boolean)obj).booleanValue(), false);
        continue; /* Loop/switch isn't completed */
_L4:
        updateRecordComponents(((Boolean)obj).booleanValue(), true);
        continue; /* Loop/switch isn't completed */
_L6:
        int j;
        ImageButton imagebutton;
        if(obj != null)
            j = ((Integer)obj).intValue();
        else
            j = 0;
        imagebutton = (ImageButton)mLayoutView.findViewById(0x7f0a00b4);
        if(j == 0)
            imagebutton.setImageResource(0x7f020036);
        else
            imagebutton.setImageResource(0x7f020037);
        continue; /* Loop/switch isn't completed */
_L5:
        if(mRecTime >= 3)
            mListener.onFragEvent(252, null);
        continue; /* Loop/switch isn't completed */
_L7:
        if(mLayoutView != null)
            mLayoutView.setVisibility(8);
        else
            MtvUtilDebug.Low("MtvUiRecordFrag", "mLayoutView is null");
        if(true) goto _L1; else goto _L8
_L8:
    }

    private static int mRecTime = 0;
    private Context mContext;
    private View mLayoutView;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvUtilAudioManager mMtvAudMgr;
    private MtvPreferences mPreferences;
    private TextView mRecDuration;
    private int mRecDurationVisibility;
    private RelativeLayout mRecMainLayout;
    private int mRecMainLayoutVisibility;
    private ImageButton mRecScrDisp;
    private Handler mRecordHandler;
    private Runnable mRunnableUpdateRecTime;
    private Button mStopIcon;
    private ImageButton mVolumeButton;
    private ImageButton mVolumeMuteButton;
    private TextView mVolumeText;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;-><clinit>()V");
    }


/*
    static int access$000()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->access$000()I");
        return mRecTime;
    }

*/


/*
    static int access$008()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->access$008()I");
        int i = mRecTime;
        mRecTime = i + 1;
        return i;
    }

*/


/*
    static TextView access$100(MtvUiRecordFrag mtvuirecordfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;)Landroid/widget/TextView;");
        return mtvuirecordfrag.mRecDuration;
    }

*/


/*
    static Handler access$200(MtvUiRecordFrag mtvuirecordfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;)Landroid/os/Handler;");
        return mtvuirecordfrag.mRecordHandler;
    }

*/

    private class _cls1 extends Handler
    {

        final MtvUiRecordFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;)V");
            this$0 = MtvUiRecordFrag.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag$2;->run()V");
            if(Log.d() < 3600)
            {
                TextView textview1 = Log.d(MtvUiRecordFrag.this);
                Object aobj1[] = new Object[3];
                aobj1[0] = Integer.valueOf(Log.d() / 3600);
                aobj1[1] = Integer.valueOf(Log.d() / 60);
                aobj1[2] = Integer.valueOf(Log.d() % 60);
                textview1.setText(String.format("%02d:%02d:%02d", aobj1));
            } else
            {
                int i = Log.d() / 60;
                TextView textview = Log.d(MtvUiRecordFrag.this);
                Object aobj[] = new Object[3];
                aobj[0] = Integer.valueOf(Log.d() / 3600);
                aobj[1] = Integer.valueOf(i % 60);
                aobj[2] = Integer.valueOf(Log.d() % 60);
                textview.setText(String.format("%02d:%02d:%02d", aobj));
            }
            Log.d();
            Log.d(MtvUiRecordFrag.this).postDelayed(this, 1000L);
        }

        final MtvUiRecordFrag this$0;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRecordFrag;)V");
            this$0 = MtvUiRecordFrag.this;
            super();
        }
    }

}
