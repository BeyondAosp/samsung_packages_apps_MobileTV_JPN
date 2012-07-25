// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.sec.mtv.utility.MtvUtilMemoryStatus;

public class MtvUiMemInfoFrag extends DialogFragment
{

    public MtvUiMemInfoFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;-><init>()V");
        super();
    }

    private Dialog buildDialog(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;->buildDialog(Landroid/view/View;)Landroid/app/Dialog;");
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028b).setView(view).setNegativeButton(0x7f070034, new _cls1()).create();
        alertdialog.requestWindowFeature(1);
        alertdialog.getWindow().setFlags(1024, 1024);
        return alertdialog;
    }

    private View constructDetails()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;->constructDetails()Landroid/view/View;");
        LinearLayout linearlayout = (LinearLayout)getActivity().getLayoutInflater().inflate(0x7f030032, null);
        ((TextView)linearlayout.findViewById(0x7f0a0126)).setText((new StringBuilder()).append(getString(0x7f0700b4)).append(" :").toString());
        ((TextView)linearlayout.findViewById(0x7f0a0127)).setText(getString(0x7f0700e9));
        long l = MtvUtilMemoryStatus.getAvailableInternalMemorySize();
        ((TextView)linearlayout.findViewById(0x7f0a0128)).setText(MtvUtilMemoryStatus.formatSize(l));
        ((TextView)linearlayout.findViewById(0x7f0a0129)).setText(getString(0x7f0700e5));
        ((TextView)linearlayout.findViewById(0x7f0a012a)).setText(formatRemainTime(MtvUtilMemoryStatus.ConvertByteToTime(l)));
        long l1 = MtvUtilMemoryStatus.getAvailableExternalMemorySize();
        if(l1 >= 0L)
        {
            linearlayout.findViewById(0x7f0a012b).setVisibility(0);
            ((TextView)linearlayout.findViewById(0x7f0a012c)).setText((new StringBuilder()).append(getString(0x7f0700b5)).append(" :").toString());
            ((TextView)linearlayout.findViewById(0x7f0a012d)).setText(getString(0x7f0700e9));
            ((TextView)linearlayout.findViewById(0x7f0a012e)).setText(MtvUtilMemoryStatus.formatSize(l1));
            ((TextView)linearlayout.findViewById(0x7f0a012f)).setText(getString(0x7f0700e5));
            ((TextView)linearlayout.findViewById(0x7f0a0130)).setText(formatRemainTime(MtvUtilMemoryStatus.ConvertByteToTime(l1)));
        }
        return linearlayout;
    }

    private String formatRemainTime(long l)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;->formatRemainTime(J)Ljava/lang/String;");
        String s1;
        if(l < 1L)
        {
            String s5 = getString(0x7f0700e7);
            Object aobj4[] = new Object[1];
            aobj4[0] = Integer.valueOf(0);
            s1 = String.format(s5, aobj4);
        } else
        {
            long l1 = l / 60L;
            long l2 = l1 / 60L;
            long l3 = l1 % 60L;
            if(l2 > 1L && l3 > 1L)
            {
                String s4 = getString(0x7f0700e8);
                Object aobj3[] = new Object[2];
                aobj3[0] = Long.valueOf(l2);
                aobj3[1] = Long.valueOf(l3);
                s1 = String.format(s4, aobj3);
            } else
            if(l3 > 1L)
            {
                String s3 = getString(0x7f0700e7);
                Object aobj2[] = new Object[1];
                aobj2[0] = Long.valueOf(l3);
                s1 = String.format(s3, aobj2);
            } else
            if(l2 > 1L)
            {
                String s2 = getString(0x7f0700e6);
                Object aobj1[] = new Object[1];
                aobj1[0] = Long.valueOf(l2);
                s1 = String.format(s2, aobj1);
            } else
            {
                String s = getString(0x7f0700e7);
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(0);
                s1 = String.format(s, aobj);
            }
        }
        return s1;
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
        return buildDialog(constructDetails());
    }

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag$1;->onClick(Landroid/content/DialogInterface;I)V");
            dismiss();
        }

        final MtvUiMemInfoFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiMemInfoFrag;)V");
            this$0 = MtvUiMemInfoFrag.this;
            super();
        }
    }

}
