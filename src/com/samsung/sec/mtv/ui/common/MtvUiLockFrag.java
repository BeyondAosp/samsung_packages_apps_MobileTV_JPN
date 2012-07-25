// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiLockFrag extends MtvUiFrag
{

    public MtvUiLockFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;-><init>()V");
        super();
        lockImage = null;
    }

    private void initializeUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->initializeUI()V");
        lockImage = (ImageView)mLayoutView.findViewById(0x7f0a0025);
        lockImage.setOnClickListener(new _cls1());
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mLayoutView = layoutinflater.inflate(0x7f030026, null);
        initializeUI();
        return mLayoutView;
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onResume()V");
        super.onResume();
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiLockFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        super.onUpdate(i, obj);
    }

    private ImageView lockImage;
    private View mLayoutView;
    private MtvUiFrag.IMtvFragEventListener mListener;


/*
    static MtvUiFrag.IMtvFragEventListener access$000(MtvUiLockFrag mtvuilockfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuilockfrag.mListener;
    }

*/

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag$1;->onClick(Landroid/view/View;)V");
            Log.d(MtvUiLockFrag.this).onFragEvent(200, null);
        }

        final MtvUiLockFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiLockFrag;)V");
            this$0 = MtvUiLockFrag.this;
            super();
        }
    }

}
