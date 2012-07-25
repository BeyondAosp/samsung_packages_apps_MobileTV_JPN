// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.fileplayer;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageButton;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;

public class MtvUiFilePlayerImgFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{

    public MtvUiFilePlayerImgFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;-><init>()V");
        super();
        isLocked = false;
    }

    public MtvUiFilePlayerImgFrag(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;-><init>(I)V");
        boolean flag = true;
        this();
        if(i != flag)
            flag = false;
        isLocked = flag;
    }

    private void initializeUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->initializeUI()V");
        prevButton = (ImageButton)mLayoutView.findViewById(0x7f0a006f);
        prevButton.setOnClickListener(this);
        nextButton = (ImageButton)mLayoutView.findViewById(0x7f0a0070);
        nextButton.setOnClickListener(this);
    }

    private void updateFragmentsBasedOnLockState(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->updateFragmentsBasedOnLockState(Z)V");
        if(prevButton != null)
            prevButton.setEnabled(flag);
        if(nextButton != null)
            nextButton.setEnabled(flag);
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onClick(Landroid/view/View;)V");
        view.getId();
        JVM INSTR tableswitch 2131361903 2131361904: default 36
    //                   2131361903 37
    //                   2131361904 53;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mLayoutView = layoutinflater.inflate(0x7f030015, null);
        initializeUI();
        return mLayoutView;
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onResume()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayerImgFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiFilePlayerImgFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR tableswitch 112 112: default 56
    //                   112 63;
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
