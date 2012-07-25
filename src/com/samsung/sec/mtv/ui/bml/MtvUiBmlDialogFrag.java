// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.app.AlertDialog;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import com.samsung.sec.mtv.app.bml.IMtvAppBmlDialogListener;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.ui.tvlink.MtvUiTvLinks;
import java.io.UnsupportedEncodingException;

public class MtvUiBmlDialogFrag extends MtvUiFrag
    implements IMtvAppBmlDialogListener
{
    private class InputDialogListener
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$InputDialogListener;->onClick(Landroid/content/DialogInterface;I)V");
            byte abyte1[] = edit.getText().toString().getBytes("UTF-8");
            byte abyte0[] = abyte1;
_L1:
            MtvAppBml mtvappbml = Log.d();
            UnsupportedEncodingException unsupportedencodingexception;
            boolean flag;
            if(i == -1)
                flag = true;
            else
                flag = false;
            mtvappbml.appExIMEEndPeer(flag, abyte0);
            mInputManager.toggleSoftInput(2, 0);
            Log.d(MtvUiBmlDialogFrag.this, null);
            return;
            unsupportedencodingexception;
            MtvUtilDebug.Low("MtvUiBmlDialog", "InputDialogListener - text input fail!!");
            abyte0 = null;
              goto _L1
        }

        private EditText edit;
        final MtvUiBmlDialogFrag this$0;

        public InputDialogListener(EditText edittext)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$InputDialogListener;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;Landroid/widget/EditText;)V");
            this$0 = MtvUiBmlDialogFrag.this;
            super();
            edit = edittext;
        }
    }


    public MtvUiBmlDialogFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;-><init>()V");
        super();
        cmd = 0;
        mbtnNum = 0;
        mdialogMessege = null;
        adb = null;
        mBMLdiag = null;
        isBMLDialogParameterSet = false;
        mInputManager = null;
        dialog = null;
        mContext = null;
    }

    public static MtvUiBmlDialogFrag getInstance()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->getInstance()Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;");
        if(BmlDialogFrag == null)
            BmlDialogFrag = new MtvUiBmlDialogFrag();
        return BmlDialogFrag;
    }

    public void IMEStartPeer(byte abyte0[], boolean flag, boolean flag1, int i, int j)
    {
        View view;
        EditText edittext;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->IMEStartPeer([BZZII)V");
        view = LayoutInflater.from(mContext).inflate(0x7f03000e, null);
        String s = MtvAppBml.byteArrayToString(abyte0);
        edittext = (EditText)view.findViewById(0x7f0a0047);
        edittext.setText(s, android.widget.TextView.BufferType.EDITABLE);
        MtvUtilDebug.Low("MtvUiBmlDialog", "BML_CB_appExIMEStartPeer");
        mInputManager = (InputMethodManager)mContext.getSystemService("input_method");
        mInputManager.toggleSoftInput(2, 0);
        if(i >= 5 && i <= 12)
        {
            InputFilter ainputfilter[] = new InputFilter[1];
            ainputfilter[0] = new android.text.InputFilter.LengthFilter(j);
            edittext.setFilters(ainputfilter);
        }
        i;
        JVM INSTR tableswitch 0 12: default 192
    //                   0 192
    //                   1 192
    //                   2 192
    //                   3 192
    //                   4 192
    //                   5 312
    //                   6 336
    //                   7 358
    //                   8 380
    //                   9 391
    //                   10 415
    //                   11 437
    //                   12 448;
           goto _L1 _L1 _L1 _L1 _L1 _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        if(!flag1)
            edittext.setSingleLine();
        if(flag)
            edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edittext.setSelectAllOnFocus(true);
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(mContext)).setView(view).setIcon(null).create();
        InputDialogListener inputdialoglistener = new InputDialogListener(edittext);
        alertdialog.setButton(-1, mContext.getString(0x7f070034), inputdialoglistener);
        alertdialog.setButton(-2, mContext.getString(0x7f070035), inputdialoglistener);
        alertdialog.setCancelable(false);
        alertdialog.show();
        dialog = alertdialog;
        return;
_L2:
        edittext.setInputType(4096);
        edittext.setKeyListener(new NumberKeyListener() {

            protected char[] getAcceptedChars()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$4;->getAcceptedChars()[C");
                char ac[] = new char[27];
                ac[0] = 'A';
                ac[1] = 'B';
                ac[2] = 'C';
                ac[3] = 'D';
                ac[4] = 'E';
                ac[5] = 'F';
                ac[6] = 'G';
                ac[7] = 'H';
                ac[8] = 'I';
                ac[9] = 'J';
                ac[10] = 'K';
                ac[11] = 'L';
                ac[12] = 'M';
                ac[13] = 'N';
                ac[14] = 'O';
                ac[15] = 'P';
                ac[16] = 'Q';
                ac[17] = 'R';
                ac[18] = 'S';
                ac[19] = 'T';
                ac[20] = 'U';
                ac[21] = 'V';
                ac[22] = 'W';
                ac[23] = 'X';
                ac[24] = 'Y';
                ac[25] = 'Z';
                ac[26] = '.';
                return ac;
            }

            public int getInputType()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$4;->getInputType()I");
                return 4096;
            }

            final MtvUiBmlDialogFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$4;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)V");
                this$0 = MtvUiBmlDialogFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L3:
        edittext.setInputType(1);
        edittext.setKeyListener(new NumberKeyListener() {

            protected char[] getAcceptedChars()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$5;->getAcceptedChars()[C");
                char ac[] = new char[27];
                ac[0] = 'a';
                ac[1] = 'b';
                ac[2] = 'c';
                ac[3] = 'd';
                ac[4] = 'e';
                ac[5] = 'f';
                ac[6] = 'g';
                ac[7] = 'h';
                ac[8] = 'i';
                ac[9] = 'j';
                ac[10] = 'k';
                ac[11] = 'l';
                ac[12] = 'm';
                ac[13] = 'n';
                ac[14] = 'o';
                ac[15] = 'p';
                ac[16] = 'q';
                ac[17] = 'r';
                ac[18] = 's';
                ac[19] = 't';
                ac[20] = 'u';
                ac[21] = 'v';
                ac[22] = 'w';
                ac[23] = 'x';
                ac[24] = 'y';
                ac[25] = 'z';
                ac[26] = '.';
                return ac;
            }

            public int getInputType()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$5;->getInputType()I");
                return 1;
            }

            final MtvUiBmlDialogFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$5;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)V");
                this$0 = MtvUiBmlDialogFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L4:
        edittext.setInputType(3);
        edittext.setKeyListener(new NumberKeyListener() {

            protected char[] getAcceptedChars()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$6;->getAcceptedChars()[C");
                char ac[] = new char[10];
                ac[0] = '0';
                ac[1] = '1';
                ac[2] = '2';
                ac[3] = '3';
                ac[4] = '4';
                ac[5] = '5';
                ac[6] = '6';
                ac[7] = '7';
                ac[8] = '8';
                ac[9] = '9';
                return ac;
            }

            public int getInputType()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$6;->getInputType()I");
                return 3;
            }

            final MtvUiBmlDialogFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$6;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)V");
                this$0 = MtvUiBmlDialogFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L5:
        edittext.setInputType(8194);
        continue; /* Loop/switch isn't completed */
_L6:
        edittext.setInputType(28673);
        edittext.setKeyListener(new NumberKeyListener() {

            protected char[] getAcceptedChars()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$7;->getAcceptedChars()[C");
                char ac[] = new char[37];
                ac[0] = '0';
                ac[1] = '1';
                ac[2] = '2';
                ac[3] = '3';
                ac[4] = '4';
                ac[5] = '5';
                ac[6] = '6';
                ac[7] = '7';
                ac[8] = '8';
                ac[9] = '9';
                ac[10] = 'A';
                ac[11] = 'B';
                ac[12] = 'C';
                ac[13] = 'D';
                ac[14] = 'E';
                ac[15] = 'F';
                ac[16] = 'G';
                ac[17] = 'H';
                ac[18] = 'I';
                ac[19] = 'J';
                ac[20] = 'K';
                ac[21] = 'L';
                ac[22] = 'M';
                ac[23] = 'N';
                ac[24] = 'O';
                ac[25] = 'P';
                ac[26] = 'Q';
                ac[27] = 'R';
                ac[28] = 'S';
                ac[29] = 'T';
                ac[30] = 'U';
                ac[31] = 'V';
                ac[32] = 'W';
                ac[33] = 'X';
                ac[34] = 'Y';
                ac[35] = 'Z';
                ac[36] = '.';
                return ac;
            }

            public int getInputType()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$7;->getInputType()I");
                return 28673;
            }

            final MtvUiBmlDialogFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$7;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)V");
                this$0 = MtvUiBmlDialogFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L7:
        edittext.setInputType(1);
        edittext.setKeyListener(new NumberKeyListener() {

            protected char[] getAcceptedChars()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$8;->getAcceptedChars()[C");
                char ac[] = new char[37];
                ac[0] = '0';
                ac[1] = '1';
                ac[2] = '2';
                ac[3] = '3';
                ac[4] = '4';
                ac[5] = '5';
                ac[6] = '6';
                ac[7] = '7';
                ac[8] = '8';
                ac[9] = '9';
                ac[10] = 'a';
                ac[11] = 'b';
                ac[12] = 'c';
                ac[13] = 'd';
                ac[14] = 'e';
                ac[15] = 'f';
                ac[16] = 'g';
                ac[17] = 'h';
                ac[18] = 'i';
                ac[19] = 'j';
                ac[20] = 'k';
                ac[21] = 'l';
                ac[22] = 'm';
                ac[23] = 'n';
                ac[24] = 'o';
                ac[25] = 'p';
                ac[26] = 'q';
                ac[27] = 'r';
                ac[28] = 's';
                ac[29] = 't';
                ac[30] = 'u';
                ac[31] = 'v';
                ac[32] = 'w';
                ac[33] = 'x';
                ac[34] = 'y';
                ac[35] = 'z';
                ac[36] = '.';
                return ac;
            }

            public int getInputType()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$8;->getInputType()I");
                return 1;
            }

            final MtvUiBmlDialogFrag this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$8;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)V");
                this$0 = MtvUiBmlDialogFrag.this;
                super();
            }
        });
        continue; /* Loop/switch isn't completed */
_L8:
        edittext.setInputType(24577);
        continue; /* Loop/switch isn't completed */
_L9:
        edittext.setInputType(1);
        if(true) goto _L1; else goto _L10
_L10:
    }

    public void destroyBMLDialog()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->destroyBMLDialog()V");
        if(mFragHandler != null && mFragHandler.isFragPresent(16) && isAdded())
            mFragHandler.removeFrag(16);
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public int getBtnNum()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->getBtnNum()I");
        return mbtnNum;
    }

    public int getCmd()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->getCmd()I");
        return cmd;
    }

    public String getDialogMessege()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->getDialogMessege()Ljava/lang/String;");
        return mdialogMessege;
    }

    public void halt()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->halt()V");
    }

    public boolean isDialogSet()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->isDialogSet()Z");
        return isBMLDialogParameterSet;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        if(bundle != null)
        {
            savedDialogState = bundle;
            mDialogShowing = bundle.getBoolean("bml_dialog_popup");
            if(mDialogShowing)
                showBMLDialog(getCmd());
        }
        return super.onCreateView(layoutinflater, viewgroup, bundle);
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->onResume()V");
        super.onResume();
        if(mBmlApp != null)
            mBmlApp.registerBmlDialogListener(getInstance());
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("bml_dialog_popup", mDialogShowing);
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->onStop()V");
        super.onStop();
        if(mBmlApp != null)
            mBmlApp.registerBmlDialogListener(getInstance());
    }

    public void setAppcomponents(MtvAppBml mtvappbml, MtvUiFragHandler mtvuifraghandler, Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->setAppcomponents(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;Landroid/content/Context;)V");
        mBmlApp = mtvappbml;
        mFragHandler = mtvuifraghandler;
        mContext = context;
        if(mBmlApp != null)
            mBmlApp.registerBmlDialogListener(getInstance());
        if(savedDialogState != null)
        {
            mDialogShowing = savedDialogState.getBoolean("bml_dialog_popup");
            if(mDialogShowing)
                showBMLDialog(getCmd());
        }
    }

    public void setBtnNum(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->setBtnNum(I)V");
        mbtnNum = i;
    }

    public void setCmd(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->setCmd(I)V");
        cmd = i;
    }

    public void setDialogBtnNum(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->setDialogBtnNum(I)V");
        setBtnNum(i);
    }

    public void setDialogMessege(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages appbmldialogmessages)
    {
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->setDialogMessege(Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$AppBmlDialogMessages;)V");
        i = -1;
        static class _cls9
        {

            static final int $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[];

            static 
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$9;-><clinit>()V");
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages = new int[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.values().length];
                NoSuchFieldError nosuchfielderror65;
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_HTTP.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_SSL_BEGIN.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SETLOCATION_SSL_END.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SERVER_CERT_CONFIRM_DIALOG.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_SERVER_CERT_CONFIRM_DIALOG_QEUSTION.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_NOMEMORY.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_MIXEDSECURETYPE.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_BADURL.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_BADMIMETYPE.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_ERROR_TOTAL_SIZEOVER.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_TRANSMITTEXTDATA.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_transmitTextDataOverIP.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_writePersistentArray.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_saveImageToMemoryCard.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_saveHttpServerImageToMemoryCard.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeCproBM.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_mailTo.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_startResidentApp.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_phoneTo.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeSchInfo.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_writeAddressBookInfo.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getCurPos.ordinal()] = 22;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getIRDID.ordinal()] = 23;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_PERMITFUNCTION_X_DPA_getRcvCond.ordinal()] = 24;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK.ordinal()] = 25;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_OLDEST.ordinal()] = 26;
                }
                catch(NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEPERSISTENTARRAY.ordinal()] = 27;
                }
                catch(NoSuchFieldError nosuchfielderror26) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_OUTOFBASEURIDIRECTORY.ordinal()] = 28;
                }
                catch(NoSuchFieldError nosuchfielderror27) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_ILLEGALFUNCTION.ordinal()] = 29;
                }
                catch(NoSuchFieldError nosuchfielderror28) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_FAILUREACTION_INVALIDSUFFIX.ordinal()] = 30;
                }
                catch(NoSuchFieldError nosuchfielderror29) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_DRAWERROR_TOOMANY_NESTS.ordinal()] = 31;
                }
                catch(NoSuchFieldError nosuchfielderror30) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_DTV_DRAWERROR_TIMEDOUT.ordinal()] = 32;
                }
                catch(NoSuchFieldError nosuchfielderror31) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_OPEN.ordinal()] = 33;
                }
                catch(NoSuchFieldError nosuchfielderror32) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_READ.ordinal()] = 34;
                }
                catch(NoSuchFieldError nosuchfielderror33) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_ARIBDC_TIMEDOUT.ordinal()] = 35;
                }
                catch(NoSuchFieldError nosuchfielderror34) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_AUTH_FORMAT.ordinal()] = 36;
                }
                catch(NoSuchFieldError nosuchfielderror35) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_AUTH_UNKNOWN.ordinal()] = 37;
                }
                catch(NoSuchFieldError nosuchfielderror36) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_BROADCAST_TRANSITON.ordinal()] = 38;
                }
                catch(NoSuchFieldError nosuchfielderror37) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CACHE_EXPIRE.ordinal()] = 39;
                }
                catch(NoSuchFieldError nosuchfielderror38) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CACHE_NONE.ordinal()] = 40;
                }
                catch(NoSuchFieldError nosuchfielderror39) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_CROSSMEDIA.ordinal()] = 41;
                }
                catch(NoSuchFieldError nosuchfielderror40) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_INVAL.ordinal()] = 42;
                }
                catch(NoSuchFieldError nosuchfielderror41) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_INVAL_TVCALL.ordinal()] = 43;
                }
                catch(NoSuchFieldError nosuchfielderror42) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_NOSERVER.ordinal()] = 44;
                }
                catch(NoSuchFieldError nosuchfielderror43) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_NOTFOUND.ordinal()] = 45;
                }
                catch(NoSuchFieldError nosuchfielderror44) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DNS_TIMEOUT.ordinal()] = 46;
                }
                catch(NoSuchFieldError nosuchfielderror45) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_DOMAIN.ordinal()] = 47;
                }
                catch(NoSuchFieldError nosuchfielderror46) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_FILE_OPEN.ordinal()] = 48;
                }
                catch(NoSuchFieldError nosuchfielderror47) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_FILE_READ.ordinal()] = 49;
                }
                catch(NoSuchFieldError nosuchfielderror48) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_GENERIC.ordinal()] = 50;
                }
                catch(NoSuchFieldError nosuchfielderror49) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_NO_CONTENT.ordinal()] = 51;
                }
                catch(NoSuchFieldError nosuchfielderror50) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REDIRECT_CANCELED.ordinal()] = 52;
                }
                catch(NoSuchFieldError nosuchfielderror51) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REDIRECT_FORMAT.ordinal()] = 53;
                }
                catch(NoSuchFieldError nosuchfielderror52) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQ_BODY_SIZEOVER.ordinal()] = 54;
                }
                catch(NoSuchFieldError nosuchfielderror53) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQ_HEADER_SIZEOVER.ordinal()] = 55;
                }
                catch(NoSuchFieldError nosuchfielderror54) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_REQTIMEOUT.ordinal()] = 56;
                }
                catch(NoSuchFieldError nosuchfielderror55) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_RESPTIMEOUT.ordinal()] = 57;
                }
                catch(NoSuchFieldError nosuchfielderror56) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_SSL.ordinal()] = 58;
                }
                catch(NoSuchFieldError nosuchfielderror57) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TCP.ordinal()] = 59;
                }
                catch(NoSuchFieldError nosuchfielderror58) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TOO_MANY_REDIRECT.ordinal()] = 60;
                }
                catch(NoSuchFieldError nosuchfielderror59) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_TOOMANYAUTH.ordinal()] = 61;
                }
                catch(NoSuchFieldError nosuchfielderror60) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_HTTP_E_AUTH_NOHEADER.ordinal()] = 62;
                }
                catch(NoSuchFieldError nosuchfielderror61) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_HTTP_E_REQ_TOOMANYCONTINUE.ordinal()] = 63;
                }
                catch(NoSuchFieldError nosuchfielderror62) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_NOTIFY_CONTENT_ERROR_UNKNOWN.ordinal()] = 64;
                }
                catch(NoSuchFieldError nosuchfielderror63) { }
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_SYSTEMCONTINUE.ordinal()] = 65;
                }
                catch(NoSuchFieldError nosuchfielderror64) { }
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$AppBmlDialogMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages.MTV_APP_BML_QUERY_DTV_WRITEBOOKMARK_REPLACE_EXPIRED.ordinal()] = 66;
_L2:
                return;
                nosuchfielderror65;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls9..SwitchMap.com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlDialogMessages[appbmldialogmessages.ordinal()];
        JVM INSTR tableswitch 1 66: default 300
    //                   1 325
    //                   2 332
    //                   3 339
    //                   4 346
    //                   5 353
    //                   6 360
    //                   7 367
    //                   8 374
    //                   9 381
    //                   10 388
    //                   11 395
    //                   12 402
    //                   13 409
    //                   14 416
    //                   15 423
    //                   16 430
    //                   17 437
    //                   18 444
    //                   19 451
    //                   20 458
    //                   21 465
    //                   22 472
    //                   23 479
    //                   24 486
    //                   25 493
    //                   26 500
    //                   27 507
    //                   28 514
    //                   29 521
    //                   30 528
    //                   31 535
    //                   32 542
    //                   33 549
    //                   34 556
    //                   35 563
    //                   36 570
    //                   37 577
    //                   38 584
    //                   39 591
    //                   40 598
    //                   41 605
    //                   42 612
    //                   43 619
    //                   44 626
    //                   45 633
    //                   46 640
    //                   47 647
    //                   48 654
    //                   49 661
    //                   50 668
    //                   51 675
    //                   52 682
    //                   53 689
    //                   54 696
    //                   55 703
    //                   56 710
    //                   57 717
    //                   58 724
    //                   59 731
    //                   60 738
    //                   61 745
    //                   62 752
    //                   63 759
    //                   64 766
    //                   65 773
    //                   66 780;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67
_L1:
        break; /* Loop/switch isn't completed */
_L67:
        break MISSING_BLOCK_LABEL_780;
_L68:
        if(mContext != null)
        {
            mdialogMessege = mContext.getString(i);
            isBMLDialogParameterSet = true;
        } else
        {
            isBMLDialogParameterSet = false;
        }
        return;
_L2:
        i = 0x7f070044;
          goto _L68
_L3:
        i = 0x7f070045;
          goto _L68
_L4:
        i = 0x7f070046;
          goto _L68
_L5:
        i = 0x7f070047;
          goto _L68
_L6:
        i = 0x7f070048;
          goto _L68
_L7:
        i = 0x7f070049;
          goto _L68
_L8:
        i = 0x7f07004a;
          goto _L68
_L9:
        i = 0x7f07004b;
          goto _L68
_L10:
        i = 0x7f07004c;
          goto _L68
_L11:
        i = 0x7f07004d;
          goto _L68
_L12:
        i = 0x7f07004e;
          goto _L68
_L13:
        i = 0x7f07004f;
          goto _L68
_L14:
        i = 0x7f070050;
          goto _L68
_L15:
        i = 0x7f070051;
          goto _L68
_L16:
        i = 0x7f070052;
          goto _L68
_L17:
        i = 0x7f070053;
          goto _L68
_L18:
        i = 0x7f070054;
          goto _L68
_L19:
        i = 0x7f070055;
          goto _L68
_L20:
        i = 0x7f070056;
          goto _L68
_L21:
        i = 0x7f070057;
          goto _L68
_L22:
        i = 0x7f070058;
          goto _L68
_L23:
        i = 0x7f070059;
          goto _L68
_L24:
        i = 0x7f07005a;
          goto _L68
_L25:
        i = 0x7f07005b;
          goto _L68
_L26:
        i = 0x7f07005c;
          goto _L68
_L27:
        i = 0x7f07005e;
          goto _L68
_L28:
        i = 0x7f07005f;
          goto _L68
_L29:
        i = 0x7f070060;
          goto _L68
_L30:
        i = 0x7f070061;
          goto _L68
_L31:
        i = 0x7f070062;
          goto _L68
_L32:
        i = 0x7f070063;
          goto _L68
_L33:
        i = 0x7f070064;
          goto _L68
_L34:
        i = 0x7f070065;
          goto _L68
_L35:
        i = 0x7f070066;
          goto _L68
_L36:
        i = 0x7f070067;
          goto _L68
_L37:
        i = 0x7f070068;
          goto _L68
_L38:
        i = 0x7f070069;
          goto _L68
_L39:
        i = 0x7f07006a;
          goto _L68
_L40:
        i = 0x7f07006b;
          goto _L68
_L41:
        i = 0x7f07006c;
          goto _L68
_L42:
        i = 0x7f07006d;
          goto _L68
_L43:
        i = 0x7f07006e;
          goto _L68
_L44:
        i = 0x7f07006f;
          goto _L68
_L45:
        i = 0x7f070070;
          goto _L68
_L46:
        i = 0x7f070071;
          goto _L68
_L47:
        i = 0x7f070058;
          goto _L68
_L48:
        i = 0x7f070058;
          goto _L68
_L49:
        i = 0x7f070058;
          goto _L68
_L50:
        i = 0x7f070058;
          goto _L68
_L51:
        i = 0x7f070058;
          goto _L68
_L52:
        i = 0x7f070058;
          goto _L68
_L53:
        i = 0x7f070058;
          goto _L68
_L54:
        i = 0x7f070079;
          goto _L68
_L55:
        i = 0x7f07007a;
          goto _L68
_L56:
        i = 0x7f07007b;
          goto _L68
_L57:
        i = 0x7f07007c;
          goto _L68
_L58:
        i = 0x7f07007d;
          goto _L68
_L59:
        i = 0x7f07007e;
          goto _L68
_L60:
        i = 0x7f07007f;
          goto _L68
_L61:
        i = 0x7f070080;
          goto _L68
_L62:
        i = 0x7f070081;
          goto _L68
_L63:
        i = 0x7f070082;
          goto _L68
_L64:
        i = 0x7f070083;
          goto _L68
_L65:
        i = 0x7f070084;
          goto _L68
_L66:
        i = 0x7f070085;
          goto _L68
        i = 0x7f07005d;
          goto _L68
    }

    public boolean showBMLDialog(final int cmd)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->showBMLDialog(I)Z");
        flag = false;
        MtvUtilDebug.Low("MtvUiBmlDialog", (new StringBuilder()).append("showDialogForBML : Entered cmd").append(cmd).toString());
        setCmd(cmd);
        if(mContext != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(mContext.getResources().getConfiguration().orientation == 2)
        {
            MtvUtilDebug.Low("MtvUiBmlDialog", "showDialogForBML: Landscapemode no need to make dialog");
        } else
        {
            if(mFragHandler != null)
                mFragHandler.addFrag(16, -1L, false, new int[0]);
            if(isDialogSet())
            {
                adb = new android.app.AlertDialog.Builder(mContext);
                adb.setMessage(getDialogMessege());
                if(cmd == 6)
                {
                    View view = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x7f03000d, null);
                    MtvUtilDebug.Low("MtvUiBmlDialog", "SLIM_BRCOMMAND_QUERY_AUTH_DIALOG");
                    adb.setView(view);
                    if(mBmlApp.isPrevUserNameNedded())
                    {
                        String s = new String(mBmlApp.getPrevUserName());
                        ((EditText)((Activity)mContext).findViewById(0x7f0a0042)).setText(s);
                    }
                    if(mBmlApp.isPrevUserPasswdNedded())
                        ((EditText)((Activity)mContext).findViewById(0x7f0a0044)).setText(mBmlApp.getPrevUserPassWd());
                }
                adb.setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$1;->onClick(Landroid/content/DialogInterface;I)V");
                        if(cmd == 6)
                        {
                            Log.d().setUserName(((EditText)((Activity)Log.d(MtvUiBmlDialogFrag.this)).findViewById(0x7f0a0042)).getText().toString().getBytes());
                            Log.d().setPrevUserPassWd(((EditText)((Activity)Log.d(MtvUiBmlDialogFrag.this)).findViewById(0x7f0a0044)).getText().toString());
                            if(((CheckBox)((Activity)Log.d(MtvUiBmlDialogFrag.this)).findViewById(0x7f0a0045)).isChecked())
                            {
                                Log.d().storeUserName(true);
                                Log.d().storeUserPasswd(true);
                            }
                        }
                        dialoginterface.dismiss();
                        MtvUiBmlDialogFrag.mDialogShowing = false;
                        Log.d(MtvUiBmlDialogFrag.this, 0);
                        Log.d(MtvUiBmlDialogFrag.this, null);
                        MtvUtilDebug.Low("MtvUiBmlDialog", "showDialogForBML: Yes is selected");
                        if(Log.d() != null)
                            Log.d().sendDialogReply(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_YES, cmd);
                        if(Log.d() != null && Log.d().isFragPresent(16) && isAdded())
                            Log.d().removeFrag(16);
                    }

                    final MtvUiBmlDialogFrag this$0;
                    final int val$cmd;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;I)V");
                this$0 = MtvUiBmlDialogFrag.this;
                cmd = i;
                super();
            }
                });
                if(getBtnNum() == 2)
                    adb.setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$2;->onClick(Landroid/content/DialogInterface;I)V");
                            dialoginterface.dismiss();
                            MtvUiBmlDialogFrag.mDialogShowing = false;
                            Log.d(MtvUiBmlDialogFrag.this, 0);
                            if(Log.d() != null && Log.d().isFragPresent(16) && isAdded())
                                Log.d().removeFrag(16);
                            Log.d(MtvUiBmlDialogFrag.this, null);
                            MtvUtilDebug.Low("MtvUiBmlDialog", "showDialogForBML: Cancel is selected");
                            Log.d().sendDialogReply(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_NO, cmd);
                            if(Log.d(MtvUiBmlDialogFrag.this) instanceof MtvUiTvLinks)
                                ((Activity)Log.d(MtvUiBmlDialogFrag.this)).finish();
                        }

                        final MtvUiBmlDialogFrag this$0;
                        final int val$cmd;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;I)V");
                this$0 = MtvUiBmlDialogFrag.this;
                cmd = i;
                super();
            }
                    });
                adb.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                    public void onCancel(DialogInterface dialoginterface)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$3;->onCancel(Landroid/content/DialogInterface;)V");
                        dialoginterface.dismiss();
                        MtvUiBmlDialogFrag.mDialogShowing = false;
                        Log.d(MtvUiBmlDialogFrag.this, 0);
                        Log.d(MtvUiBmlDialogFrag.this, null);
                        MtvUtilDebug.Low("MtvUiBmlDialog", "showDialogForBML: Back is selected");
                        if(Log.d() != null && Log.d().isFragPresent(16) && isAdded())
                            Log.d().removeFrag(16);
                        Log.d().sendDialogReply(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_NO, cmd);
                    }

                    final MtvUiBmlDialogFrag this$0;
                    final int val$cmd;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag$3;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;I)V");
                this$0 = MtvUiBmlDialogFrag.this;
                cmd = i;
                super();
            }
                });
                mBMLdiag = adb.create();
                mBMLdiag.show();
                mDialogShowing = true;
                flag = true;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static MtvUiBmlDialogFrag BmlDialogFrag = null;
    private static MtvAppBml mBmlApp = null;
    public static boolean mDialogShowing = false;
    private static MtvUiFragHandler mFragHandler = null;
    private static Bundle savedDialogState = null;
    private android.app.AlertDialog.Builder adb;
    private int cmd;
    private AlertDialog dialog;
    private boolean isBMLDialogParameterSet;
    private AlertDialog mBMLdiag;
    private Context mContext;
    InputMethodManager mInputManager;
    private int mbtnNum;
    private String mdialogMessege;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;-><clinit>()V");
    }


/*
    static Context access$000(MtvUiBmlDialogFrag mtvuibmldialogfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->access$000(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;)Landroid/content/Context;");
        return mtvuibmldialogfrag.mContext;
    }

*/


/*
    static MtvAppBml access$100()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->access$100()Lcom/samsung/sec/mtv/app/bml/MtvAppBml;");
        return mBmlApp;
    }

*/


/*
    static boolean access$202(MtvUiBmlDialogFrag mtvuibmldialogfrag, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->access$202(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;Z)Z");
        mtvuibmldialogfrag.isBMLDialogParameterSet = flag;
        return flag;
    }

*/


/*
    static AlertDialog access$302(MtvUiBmlDialogFrag mtvuibmldialogfrag, AlertDialog alertdialog)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->access$302(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;Landroid/app/AlertDialog;)Landroid/app/AlertDialog;");
        mtvuibmldialogfrag.mBMLdiag = alertdialog;
        return alertdialog;
    }

*/


/*
    static MtvUiFragHandler access$400()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlDialogFrag;->access$400()Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;");
        return mFragHandler;
    }

*/
}
