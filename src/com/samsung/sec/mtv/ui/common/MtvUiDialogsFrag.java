// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
            EPGErrorDialogFragment epgerrordialogfragment = new EPGErrorDialogFragment();
            epgerrordialogfragment.myStringId = i;
            epgerrordialogfragment.myTitleID = j;
            return epgerrordialogfragment;
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
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
                    dismiss();
                }

                final EPGErrorDialogFragment this$0;

                _cls1()
                {
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
            if(getActivity() instanceof MtvUiFrag.IMtvFragEventListener)
                ((MtvUiFrag.IMtvFragEventListener)getActivity()).onFragEvent(276, Boolean.valueOf(true));
            super.onDismiss(dialoginterface);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("myStringId", myStringId);
            bundle.putInt("myTitleID", myTitleID);
            super.onSaveInstanceState(bundle);
        }

        private int myStringId;
        private int myTitleID;

        public EPGErrorDialogFragment()
        {
        }
    }


    public MtvUiDialogsFrag()
    {
        mPreferences = null;
        mListener = null;
    }

    private AlertDialog createDialogByType(int i, Bundle bundle)
    {
        int j = 0;
        i;
        JVM INSTR tableswitch 1 14: default 72
    //                   1 78
    //                   2 237
    //                   3 339
    //                   4 395
    //                   5 485
    //                   6 551
    //                   7 649
    //                   8 725
    //                   9 902
    //                   10 970
    //                   11 1038
    //                   12 1110
    //                   13 72
    //                   14 1174;
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
                MtvUtilDebug.Mid("MtvUiDialogsFrag", (new StringBuilder()).append("which button = ").append(i1).append(" areaId=").append(area[i1].mAreaId).toString());
                if(area[i1].mAreaId == -1)
                {
                    mListener.onFragEvent(602, Integer.valueOf(i1));
                } else
                {
                    MtvUtilDebug.Mid("MtvUiDialogsFrag", (new StringBuilder()).append("changeArea SlotID=").append(i1).append(" - already updated").toString());
                    mListener.onFragEvent(603, Integer.valueOf(i1));
                    mListener = null;
                }
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;
            final MtvArea val$area[];

            
            {
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
                dialoginterface.dismiss();
                mListener.onFragEvent(601, intent);
            }

            final MtvUiDialogsFrag this$0;
            final Intent val$intent;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                intent = intent1;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                i1;
                JVM INSTR tableswitch 0 2: default 28
            //                           0 29
            //                           1 48
            //                           2 67;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                mListener.onFragEvent(275, null);
                continue; /* Loop/switch isn't completed */
_L3:
                mListener.onFragEvent(271, null);
                continue; /* Loop/switch isn't completed */
_L4:
                mListener.onFragEvent(281, null);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                if(fileType != 2)
                    mListener.onFragEvent(283, null);
                dialoginterface.dismiss();
                MtvFileManager.deleteTvFile(fileIndex, filePath, MtvAppPlayerOneSeg.getInstance());
                if(mPreferences.getSelectedFileIndex() != mPreferences.getLatestFileIndex()) goto _L2; else goto _L1
_L1:
                mPreferences.setLatestFileIndex(0);
_L4:
                mListener.onFragEvent(272, null);
                return;
_L2:
                if(mPreferences.getSelectedFileIndex() < mPreferences.getLatestFileIndex())
                    mPreferences.setLatestFileIndex(-1 + mPreferences.getLatestFileIndex());
                if(true) goto _L4; else goto _L3
_L3:
            }

            final MtvUiDialogsFrag this$0;
            final int val$fileIndex;
            final String val$filePath;
            final int val$fileType;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                fileType = i;
                fileIndex = j;
                filePath = s;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                i1;
                JVM INSTR tableswitch 0 0: default 20
            //                           0 21;
                   goto _L1 _L2
_L1:
                return;
_L2:
                mListener.onFragEvent(273, Integer.valueOf(id));
                if(true) goto _L1; else goto _L3
_L3:
            }

            final MtvUiDialogsFrag this$0;
            final int val$id;

            
            {
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
                    MtvUtilSetReservationAlarm.setReservationAlarm(getActivity(), ((MtvProgram) (reserve)).mTimeStart, false, true);
                }

                final MtvUiDialogsFrag this$0;
                final MtvReservation val$reserve;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                reserve = mtvreservation;
                super();
            }
            }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i1)
                {
                    dialoginterface.dismiss();
                }

                final MtvUiDialogsFrag this$0;

            
            {
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
                mListener.onFragEvent(274, getArguments());
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                mListener.onFragEvent(276, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                mListener.onFragEvent(277, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                MtvUtilDebug.Low("MtvUiDialogsFrag", (new StringBuilder()).append("MtvUiDialogsFrag isVisible?").append(isVisible()).toString());
                mListener.onFragEvent(278, null);
            }

            final MtvUiDialogsFrag this$0;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        MtvUiDialogsFrag.removeDialog(getFragmentManager(), "signal_alert_retry_exit");
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("dialogType", 12);
                        MtvUiDialogsFrag.newInstance(bundle1).show(getFragmentManager(), "signal_alert_terminate");
                    }

                    final _cls18 this$1;

                        _cls1()
                        {
                            this$1 = _cls18.this;
                            super();
                        }
                }

                getActivity().runOnUiThread(new _cls1());
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                mListener.onFragEvent(276, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
                mListener.onFragEvent(280, null);
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
                this$0 = MtvUiDialogsFrag.this;
                super();
            }
        }).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i1)
            {
                dialoginterface.dismiss();
            }

            final MtvUiDialogsFrag this$0;

            
            {
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
        MtvUiDialogsFrag mtvuidialogsfrag = new MtvUiDialogsFrag();
        mtvuidialogsfrag.setArguments(bundle);
        return mtvuidialogsfrag;
    }

    public static void removeDialog(FragmentManager fragmentmanager, String s)
    {
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
        super.onCancel(dialoginterface);
        getArguments().getInt("dialogType");
        JVM INSTR tableswitch 11 12: default 36
    //                   11 37
    //                   12 55;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        getActivity().runOnUiThread(new Runnable() {

            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putInt("dialogType", 12);
                MtvUiDialogsFrag.newInstance(bundle).show(getFragmentManager(), "signal_alert_terminate");
            }

            final MtvUiDialogsFrag this$0;

            
            {
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




/*
    static MtvUiFrag.IMtvFragEventListener access$002(MtvUiDialogsFrag mtvuidialogsfrag, MtvUiFrag.IMtvFragEventListener imtvfrageventlistener)
    {
        mtvuidialogsfrag.mListener = imtvfrageventlistener;
        return imtvfrageventlistener;
    }

*/

}
