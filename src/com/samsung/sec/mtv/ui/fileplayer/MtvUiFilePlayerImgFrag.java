// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.fileplayer;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageButton;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;

public class MtvUiFilePlayerImgFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{

    public MtvUiFilePlayerImgFrag()
    {
        isLocked = false;
    }

    public MtvUiFilePlayerImgFrag(int i)
    {
        boolean flag = true;
        this();
        if(i != flag)
            flag = false;
        isLocked = flag;
    }

    private void initializeUI()
    {
        prevButton = (ImageButton)mLayoutView.findViewById(0x7f0a006f);
        prevButton.setOnClickListener(this);
        nextButton = (ImageButton)mLayoutView.findViewById(0x7f0a0070);
        nextButton.setOnClickListener(this);
    }

    private void updateFragmentsBasedOnLockState(boolean flag)
    {
        if(prevButton != null)
            prevButton.setEnabled(flag);
        if(nextButton != null)
            nextButton.setEnabled(flag);
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener)activity;
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
        JVM INSTR tableswitch 2131361903 2131361904: default 28
    //                   2131361903 29
    //                   2131361904 45;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        mListener.onFragEvent(282, null);
        continue; /* Loop/switch isn't completed */
_L3:
        mListener.onFragEvent(281, null);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mLayoutView = layoutinflater.inflate(0x7f030015, null);
        initializeUI();
        return mLayoutView;
    }

    public void onResume()
    {
        super.onResume();
        boolean flag;
        if(!isLocked)
            flag = true;
        else
            flag = false;
        updateFragmentsBasedOnLockState(flag);
    }

    public void onUpdate(int i, Object obj)
    {
        MtvUtilDebug.Low("MtvUiFilePlayerImgFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR tableswitch 112 112: default 48
    //                   112 55;
           goto _L1 _L2
_L1:
        super.onUpdate(i, obj);
        return;
_L2:
        if(obj != null)
        {
            boolean flag;
            if(!((Boolean)obj).booleanValue())
                flag = true;
            else
                flag = false;
            isLocked = flag;
            updateFragmentsBasedOnLockState(((Boolean)obj).booleanValue());
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private boolean isLocked;
    private View mLayoutView;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;
    private ImageButton nextButton;
    private ImageButton prevButton;
}
