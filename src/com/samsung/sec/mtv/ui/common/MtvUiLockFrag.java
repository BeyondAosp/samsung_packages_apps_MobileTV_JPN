// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiLockFrag extends MtvUiFrag
{

    public MtvUiLockFrag()
    {
        lockImage = null;
    }

    private void initializeUI()
    {
        lockImage = (ImageView)mLayoutView.findViewById(0x7f0a0025);
        lockImage.setOnClickListener(new _cls1());
    }

    public void onAttach(Activity activity)
    {
        onAttach(activity);
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
        onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mLayoutView = layoutinflater.inflate(0x7f030026, null);
        initializeUI();
        return mLayoutView;
    }

    public void onPause()
    {
        onPause();
    }

    public void onResume()
    {
        onResume();
    }

    public void onStart()
    {
        onStart();
    }

    public void onStop()
    {
        onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        MtvUtilDebug.Low("MtvUiLockFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        onUpdate(i, obj);
    }

    private ImageView lockImage;
    private View mLayoutView;
    private MtvUiFrag.IMtvFragEventListener mListener;


    private class _cls1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            mListener.onFragEvent(200, null);
        }

        final MtvUiLockFrag this$0;

        _cls1()
        {
            this$0 = MtvUiLockFrag.this;
            Object();
        }
    }

}
