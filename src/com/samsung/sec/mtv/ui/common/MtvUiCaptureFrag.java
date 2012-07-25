// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegChannel;
import android.broadcast.helper.types.MtvOneSegProgram;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.provider.MtvFile;
import com.samsung.sec.mtv.provider.MtvFileManager;
import com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilMemoryStatus;
import java.util.Date;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag

public class MtvUiCaptureFrag extends MtvUiFrag
{

    public MtvUiCaptureFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;-><init>()V");
        super();
    }

    private void captureAutoSave()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->captureAutoSave()V");
        if(MtvUtilMemoryStatus.isExternalMemoryAvailable() || MtvUtilMemoryStatus.isInternalMemoryAvailable()) goto _L2; else goto _L1
_L1:
        saved_file_toast = Toast.makeText(mContext, 0x7f0700cf, 0);
        saved_file_toast.show();
_L4:
        return;
_L2:
        boolean flag = false;
        Bitmap bitmap = mReceivedImage.getBitmap();
        if(bitmap != null)
            if(!saveFile(bitmap))
                MtvUtilDebug.Error("MtvUiCaptureFrag", "Failed to save the file");
            else
                flag = true;
        if(flag)
        {
            saved_file_toast = Toast.makeText(mContext, 0x7f0700d0, 0);
            saved_file_toast.show();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initializeUI()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->initializeUI()V");
        MtvUtilDebug.Low("MtvUiCaptureFrag", "initializeUI");
        mPreferences = new MtvPreferences(getActivity().getApplicationContext());
        mCaptureImgView = (ImageView)mLayoutView.findViewById(0x7f0a005c);
        mCaptureImgText = (TextView)mLayoutView.findViewById(0x7f0a005e);
        mCaptureImgText.setVisibility(8);
        mCaptureImgView.setBackgroundDrawable(null);
        mCaptureImgView.setImageDrawable(null);
    }

    private boolean saveFile(Bitmap bitmap)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->saveFile(Landroid/graphics/Bitmap;)Z");
        boolean flag = false;
        MtvUiLivePlayer mtvuiliveplayer = (MtvUiLivePlayer)getActivity();
        MtvUtilDebug.Low("MtvUiCaptureFrag", "saveFile");
        if(bitmap != null)
        {
            MtvFile mtvfile = new MtvFile();
            int i = mPreferences.getSaveToStorage();
            if(i == 0 && !MtvUtilMemoryStatus.isExternalMemoryAvailable())
                i = 1;
            MtvOneSegChannel mtvonesegchannel = mtvuiliveplayer.getChannel();
            if(mtvonesegchannel != null)
                mtvfile.setChannelName(mtvonesegchannel.getServName());
            MtvOneSegProgram amtvonesegprogram[] = mtvuiliveplayer.getProgram();
            if(amtvonesegprogram != null)
            {
                MtvOneSegProgram mtvonesegprogram = mtvuiliveplayer.getCurrentProgramDetails(amtvonesegprogram);
                if(mtvonesegprogram != null)
                    mtvfile.setProgramName(mtvonesegprogram.getProgName());
            }
            mtvfile.setFileFormat(2);
            mtvfile.setCreationTime(new Date());
            MtvFileManager.saveFile(i, bitmap, mtvfile);
            mContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse((new StringBuilder()).append("file://").append(mtvfile.getFilePath()).toString())));
            flag = true;
        }
        return flag;
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mContext = getActivity().getApplicationContext();
        mLayoutView = layoutinflater.inflate(0x7f030013, null);
        initializeUI();
        return mLayoutView;
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onPause()V");
        super.onPause();
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onResume()V");
        super.onResume();
    }

    public void onStart()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onStart()V");
        super.onStart();
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onStop()V");
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;->onUpdate(ILjava/lang/Object;)V");
        MtvUtilDebug.Low("MtvUiCaptureFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        i;
        JVM INSTR tableswitch 110 110: default 60
    //                   110 61;
           goto _L1 _L2
_L1:
        return;
_L2:
        this;
        JVM INSTR monitorenter ;
        MtvUtilDebug.Low("MtvUiCaptureFrag", "onUpdate: inside synchronized block");
        mReceivedImage = new BitmapDrawable(getResources(), (Bitmap)obj);
        mCaptureImgView.setBackgroundDrawable(mReceivedImage);
        mCaptureImgText.setVisibility(0);
        if(obj == null) goto _L4; else goto _L3
_L3:
        captureAutoSave();
_L5:
        this;
        JVM INSTR monitorexit ;
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L4:
        mListener.onFragEvent(251, null);
        saved_file_toast = Toast.makeText(mContext, 0x7f0700d1, 0);
        saved_file_toast.show();
          goto _L5
    }

    public static Toast saved_file_toast = null;
    private TextView mCaptureImgText;
    private ImageView mCaptureImgView;
    private Context mContext;
    private View mLayoutView;
    private MtvUiFrag.IMtvFragEventListener mListener;
    private MtvPreferences mPreferences;
    BitmapDrawable mReceivedImage;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiCaptureFrag;-><clinit>()V");
    }
}
