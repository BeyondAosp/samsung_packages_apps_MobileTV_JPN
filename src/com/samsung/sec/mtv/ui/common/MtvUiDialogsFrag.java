// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.channelguide.MtvUiChangeArea;
import com.samsung.sec.mtv.utility.*;

public class MtvUiDialogsFrag extends DialogFragment
{
    public static class EPGErrorDialogFragment extends DialogFragment
    {

        public static EPGErrorDialogFragment newInstance(int i, int j)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;->newInstance(II)Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;");
            EPGErrorDialogFragment epgerrordialogfragment = new EPGErrorDialogFragment();
            epgerrordialogfragment.myStringId = i;
            epgerrordialogfragment.myTitleID = j;
            return epgerrordialogfragment;
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
            if(bundle != null)
            {
                myStringId = bundle.getInt("myStringId");
                myTitleID = bundle.getInt("myTitleID");
            }
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment$1;->onClick(Landroid/content/DialogInterface;I)V");
                    dismiss();
                }

                final EPGErrorDialogFragment this$0;

                _cls1()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;)V");
                    this$0 = EPGErrorDialogFragment.this;
                    super();
                }
            }

            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setIcon(0x7f02008e).setTitle(getString(myTitleID)).setIcon(0x7f02008e).setMessage(getString(myStringId)).setPositiveButton(0x7f070034, new _cls1()).create();
            alertdialog.getWindow().addFlags(1024);
            return alertdialog;
        }

        public void onDismiss(DialogInterface dialoginterface)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;->onDismiss(Landroid/content/DialogInterface;)V");
            if(getActivity() instanceof MtvUiFrag.IMtvFragEventListener)
                ((MtvUiFrag.IMtvFragEventListener)getActivity()).onFragEvent(276, Boolean.valueOf(true));
            super.onDismiss(dialoginterface);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;->onSaveInstanceState(Landroid/os/Bundle;)V");
            bundle.putInt("myStringId", myStringId);
            bundle.putInt("myTitleID", myTitleID);
            super.onSaveInstanceState(bundle);
        }

        private int myStringId;
        private int myTitleID;

        public EPGErrorDialogFragment()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$EPGErrorDialogFragment;-><init>()V");
            super();
        }
    }


    public MtvUiDialogsFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;-><init>()V");
        super();
        mPreferences = null;
        mListener = null;
    }

    private AlertDialog createDialogByType(int i, Bundle bundle)
    {
        int j;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->createDialogByType(ILandroid/os/Bundle;)Landroid/app/AlertDialog;");
        j = 0;
        i;
        JVM INSTR tableswitch 1 14: default 84
    //                   1 90
    //                   2 254
    //                   3 356
    //                   4 412
    //                   5 502
    //                   6 570
    //                   7 670
    //                   8 746
    //                   9 924
    //                   10 992
    //                   11 1060
    //                   12 1132
    //                   13 84
    //                   14 1196;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L1 _L14
_L1:
        AlertDialog alertdialog = null;
_L16:
        return alertdialog;
_L2:
        final MtvArea area[] = MtvAreaManager.getAllAreas(getActivity());
        CharSequence acharsequence[] = new CharSequence[area.length];
        while(j < area.length) 
        {
            String s7;
            if(area[j].mAreaName.equals("Empty"))
                s7 = (new StringBuilder()).append(getString(0x7f07028f)).append(" ").append(j + 1).toString();
            else
                s7 = MtvAreaStationInfo.getStringByResourceName(getActivity(), area[j].mAreaName);
            acharsequence[j] = s7;
            j++;
        }
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f070290).setSingleChoiceItems(acharsequence, mPreferences.getCurrentSlot(), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$2;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUtilDebug.Mid("MtvUiDialogsFrag", (new StringBuilder()).append("which button = ").append(i1).append(" areaId=").append(area[i1].mAreaId).toString());
                if(area[i1].mAreaId == -1)
                {
                    Log.d(MtvUiDialogsFrag.this).onFragEvent(602, Integer.valueOf(i1));
                } else
                {
                    MtvUtilDebug.Mid("MtvUiDialogsFrag", (new StringBuilder()).append("changeArea SlotID=").append(i1).append(" - already updated").toString());
                    Log.d(MtvUiDialogsFrag.this).onFragEvent(603, Integer.valueOf(i1));
                    Log.d(MtvUiDialogsFrag.this, null);
                }
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;
            final MtvArea val$area[];

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;[Lcom/samsung/sec/mtv/provider/MtvArea;)V");
                this$0 = MtvUiDialogsFrag.this;
                area = amtvarea;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L3:
        final Intent intent = new Intent(getActivity(), com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
        intent.putExtra("slotId", bundle.getInt("SlotID"));
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setIcon(0x7f02008e).setTitle(0x7f070293).setMessage(0x7f070294).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$4;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
                Log.d(MtvUiDialogsFrag.this).onFragEvent(601, intent);
            }

            final MtvUiDialogsFrag this$0;
            final Intent val$intent;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;Landroid/content/Intent;)V");
                this$0 = MtvUiDialogsFrag.this;
                intent = intent1;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$3;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L4:
        String s6 = bundle.getString("title");
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(s6).setItems(0x7f050008, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$5;->onClick(Landroid/content/DialogInterface;I)V");
                i1;
                JVM INSTR tableswitch 0 2: default 36
            //                           0 37
            //                           1 56
            //                           2 75;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                Log.d(MtvUiDialogsFrag.this).onFragEvent(275, null);
                continue; /* Loop/switch isn't completed */
_L3:
                Log.d(MtvUiDialogsFrag.this).onFragEvent(271, null);
                continue; /* Loop/switch isn't completed */
_L4:
                Log.d(MtvUiDialogsFrag.this).onFragEvent(281, null);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$5;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L5:
        final String filePath = bundle.getString("filePath");
        final int fileIndex = bundle.getInt("index");
        final int fileType = bundle.getInt("fileType");
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028d).setMessage(0x7f0700e2).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$7;->onClick(Landroid/content/DialogInterface;I)V");
                if(fileType != 2)
                    Log.d(MtvUiDialogsFrag.this).onFragEvent(283, null);
                dialoginterface.dismiss();
                MtvFileManager.deleteTvFile(fileIndex, filePath, MtvAppPlayerOneSeg.getInstance());
                if(Log.d(MtvUiDialogsFrag.this).getSelectedFileIndex() != Log.d(MtvUiDialogsFrag.this).getLatestFileIndex()) goto _L2; else goto _L1
_L1:
                Log.d(MtvUiDialogsFrag.this).setLatestFileIndex(0);
_L4:
                Log.d(MtvUiDialogsFrag.this).onFragEvent(272, null);
                return;
_L2:
                if(Log.d(MtvUiDialogsFrag.this).getSelectedFileIndex() < Log.d(MtvUiDialogsFrag.this).getLatestFileIndex())
                    Log.d(MtvUiDialogsFrag.this).setLatestFileIndex(-1 + Log.d(MtvUiDialogsFrag.this).getLatestFileIndex());
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MtvUiDialogsFrag this$0;
            final int val$fileIndex;
            final String val$filePath;
            final int val$fileType;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$7;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;IILjava/lang/String;)V");
                this$0 = MtvUiDialogsFrag.this;
                fileType = i;
                fileIndex = j;
                filePath = s;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$6;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$6;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.show();
        continue; /* Loop/switch isn't completed */
_L6:
        String s5 = bundle.getString("title");
        final int id = bundle.getInt("db_id");
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(s5).setItems(0x7f050009, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$8;->onClick(Landroid/content/DialogInterface;I)V");
                i1;
                JVM INSTR tableswitch 0 0: default 28
            //                           0 29;
                   goto _L1 _L2
_L1:
                return;
_L2:
                Log.d(MtvUiDialogsFrag.this).onFragEvent(273, Integer.valueOf(id));
                if(true) goto _L1; else goto _L3
_L3:
            }

            final MtvUiDialogsFrag this$0;
            final int val$id;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$8;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;I)V");
                this$0 = MtvUiDialogsFrag.this;
                id = i;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L7:
        int l = bundle.getInt("db_id");
        final MtvReservation reserve = MtvReservationManager.find(getActivity(), l);
        if(reserve == null)
        {
            alertdialog = null;
        } else
        {
            alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028d).setMessage(0x7f0702b4).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$10;->onClick(Landroid/content/DialogInterface;I)V");
                    MtvUtilSetReservationAlarm.setReservationAlarm(getActivity(), ((MtvProgram) (reserve)).mTimeStart, false, true);
                }

                final MtvUiDialogsFrag this$0;
                final MtvReservation val$reserve;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$10;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;Lcom/samsung/sec/mtv/provider/MtvReservation;)V");
                this$0 = MtvUiDialogsFrag.this;
                reserve = mtvreservation;
                super();
            }
            }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$9;->onClick(Landroid/content/DialogInterface;I)V");
                    dialoginterface.dismiss();
                }

                final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$9;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
            }).create();
            alertdialog.getWindow().addFlags(1024);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        String s4 = bundle.getString("dialog_msg");
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0702ab).setMessage(s4).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$12;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d(MtvUiDialogsFrag.this).onFragEvent(274, getArguments());
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$12;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$11;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$11;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L9:
        int k = bundle.getInt("mSlotID");
        int ai[] = bundle.getIntArray("mSlotMap");
        String s;
        if(ai != null)
        {
            String s1 = MtvAreaStationInfo.getStringByResourceName(getActivity(), MtvAreaStationInfo.AREA_LOCAL[ai[0]][ai[1]][ai[2]]);
            String s2 = (new StringBuilder()).append(getString(0x7f07028f)).append(" ").append(k + 1).toString();
            String s3 = getString(0x7f070295);
            Object aobj[] = new Object[2];
            aobj[j] = s2;
            aobj[1] = s1;
            s = String.format(s3, aobj);
        } else
        {
            s = "";
        }
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setIcon(0x7f02008e).setTitle(0x7f070293).setMessage(s).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$13;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$13;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L10:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07009f).setMessage(0x7f07009a).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$15;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d(MtvUiDialogsFrag.this).onFragEvent(276, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$15;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$14;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$14;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L11:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07009f).setMessage(0x7f0700d3).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$17;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d(MtvUiDialogsFrag.this).onFragEvent(277, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$17;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$16;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$16;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L12:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07001d).setMessage(getString(0x7f07001e)).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$19;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUtilDebug.Low("MtvUiDialogsFrag", (new StringBuilder()).append("MtvUiDialogsFrag isVisible?").append(isVisible()).toString());
                Log.d(MtvUiDialogsFrag.this).onFragEvent(278, null);
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$19;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$18;->onClick(Landroid/content/DialogInterface;I)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$18$1;->run()V");
                        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_retry_exit");
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("dialogType", 12);
                        MtvUiDialogsFrag.newInstance(bundle1).show(getFragmentManager(), "signal_alert_terminate");
                    }

                    final _cls18 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$18$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$18;)V");
                            this$1 = _cls18.this;
                            super();
                        }
                }

                getActivity().runOnUiThread(new _cls1());
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$18;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L13:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f070020).setMessage(getString(0x7f07001f)).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$20;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d(MtvUiDialogsFrag.this).onFragEvent(276, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$20;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.show();
        alertdialog.getWindow().addFlags(1024);
        continue; /* Loop/switch isn't completed */
_L14:
        alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028d).setMessage(0x7f0700e2).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$22;->onClick(Landroid/content/DialogInterface;I)V");
                Log.d(MtvUiDialogsFrag.this).onFragEvent(280, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$22;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$21;->onClick(Landroid/content/DialogInterface;I)V");
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$21;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).create();
        alertdialog.show();
        alertdialog.getWindow().addFlags(1024);
        if(true) goto _L16; else goto _L15
_L15:
    }

    public static MtvUiDialogsFrag newInstance(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->newInstance(Landroid/os/Bundle;)Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;");
        MtvUiDialogsFrag mtvuidialogsfrag = new MtvUiDialogsFrag();
        mtvuidialogsfrag.setArguments(bundle);
        return mtvuidialogsfrag;
    }

    public static void removeDialog(FragmentManager fragmentmanager, String s)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->removeDialog(Landroid/app/FragmentManager;Ljava/lang/String;)V");
        if(s != null && fragmentmanager != null)
        {
            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            android.app.Fragment fragment = fragmentmanager.findFragmentByTag(s);
            if(fragment != null)
            {
                fragmenttransaction.remove(fragment);
                fragmenttransaction.commit();
            }
        }
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->onAttach(Landroid/app/Activity;)V");
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

    public void onCancel(DialogInterface dialoginterface)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->onCancel(Landroid/content/DialogInterface;)V");
        super.onCancel(dialoginterface);
        getArguments().getInt("dialogType");
        JVM INSTR tableswitch 11 12: default 48
    //                   11 49
    //                   12 67;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        getActivity().runOnUiThread(new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$1;->run()V");
                Bundle bundle = new Bundle();
                bundle.putInt("dialogType", 12);
                MtvUiDialogsFrag.newInstance(bundle).show(getFragmentManager(), "signal_alert_terminate");
            }

            final MtvUiDialogsFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)V");
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L3:
        mListener.onFragEvent(276, null);
        dialoginterface.dismiss();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;");
        mPreferences = new MtvPreferences(getActivity().getApplicationContext());
        mListener = (MtvUiFrag.IMtvFragEventListener)getActivity();
        Bundle bundle1 = getArguments();
        AlertDialog alertdialog = null;
        if(bundle1 != null)
            alertdialog = createDialogByType(bundle1.getInt("dialogType"), bundle1);
        return alertdialog;
    }

    public static int MTV_UI_DIALOG_TYPE_RESET = 0;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvPreferences mPreferences;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;-><clinit>()V");
    }


/*
    static MtvUiFrag.IMtvFragEventListener access$000(MtvUiDialogsFrag mtvuidialogsfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        return mtvuidialogsfrag.mListener;
    }

*/


/*
    static MtvUiFrag.IMtvFragEventListener access$002(MtvUiDialogsFrag mtvuidialogsfrag, MtvUiFrag.IMtvFragEventListener imtvfrageventlistener)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->access$002(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;)Lcom/samsung/sec/mtv/ui/common/MtvUiFrag$IMtvFragEventListener;");
        mtvuidialogsfrag.mListener = imtvfrageventlistener;
        return imtvfrageventlistener;
    }

*/


/*
    static MtvPreferences access$100(MtvUiDialogsFrag mtvuidialogsfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiDialogsFrag;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuidialogsfrag.mPreferences;
    }

*/
}
