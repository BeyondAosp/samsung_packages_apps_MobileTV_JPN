// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.utility.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiDialogsFrag

public class MtvUiRemoveList extends Activity
    implements android.view.View.OnClickListener, MtvUiFrag.IMtvFragEventListener
{
    private class MtvFileAdapter extends ArrayAdapter
    {

        public int getCount()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;->getCount()I");
            int i;
            if(Log.d(MtvUiRemoveList.this) != null && Log.d(MtvUiRemoveList.this).length > 0)
                i = Log.d(MtvUiRemoveList.this).length;
            else
                i = 0;
            return i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f030030, null);
            Log.d(MtvUiRemoveList.this, (ImageView)view1.findViewById(0x7f0a0119));
            Log.d(MtvUiRemoveList.this, (ImageView)view1.findViewById(0x7f0a011a));
            MtvFile mtvfile = Log.d(MtvUiRemoveList.this)[i];
            if(mtvfile != null)
            {
                Log.d(MtvUiRemoveList.this).setVisibility(0);
                Log.d(MtvUiRemoveList.this).setVisibility(0);
                TextView textview = (TextView)view1.findViewById(0x7f0a011e);
                TextView textview1 = (TextView)view1.findViewById(0x7f0a0120);
                TextView textview2 = (TextView)view1.findViewById(0x7f0a0121);
                CheckBox checkbox = (CheckBox)view1.findViewById(0x7f0a0122);
                checkbox.setVisibility(0);
                checkbox.setChecked(Log.d(MtvUiRemoveList.this)[i]);
                checkbox.setFocusable(false);
                checkbox.setClickable(false);
                String s;
                String s1;
                if(mtvfile.getProgramName() == null || mtvfile.getProgramName().equals("null"))
                    s = getResources().getString(0x7f0700ce);
                else
                    s = mtvfile.getProgramName();
                textview.setText(s);
                if(DateFormat.is24HourFormat(getApplicationContext()))
                    s1 = (new SimpleDateFormat("M/d (EEE) H:mm")).format(mtvfile.getCreationTime());
                else
                    s1 = (new SimpleDateFormat("M/d (EEE) h:mm a")).format(mtvfile.getCreationTime());
                if(getResources().getConfiguration().orientation == 1)
                {
                    textview1.setText(s1);
                    textview2.setText(mtvfile.getChannelName());
                } else
                {
                    textview1.setText((new StringBuilder()).append(s1).append(" ").append(mtvfile.getChannelName()).toString());
                }
                view1.setTag(mtvfile);
                Log.d(MtvUiRemoveList.this, mtvfile);
            } else
            {
                Log.d(MtvUiRemoveList.this).setVisibility(4);
                Log.d(MtvUiRemoveList.this).setVisibility(4);
            }
            return view1;
        }

        public void setChecked(int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;->setChecked(IZ)V");
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        public void toggle(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;->toggle(I)V");
            boolean flag;
            if(Log.d(MtvUiRemoveList.this, i))
                flag = false;
            else
                flag = true;
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        final MtvUiRemoveList this$0;

        public MtvFileAdapter(Context context, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/content/Context;I)V");
            this$0 = MtvUiRemoveList.this;
            super(context, i);
        }
    }

    public class ReserveAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03002d, null);
            startTime = (Long)Log.d(MtvUiRemoveList.this).get(i);
            if(startTime != null)
            {
                Log.d(MtvUiRemoveList.this).setVisibility(0);
                Log.d(MtvUiRemoveList.this).setVisibility(0);
                MtvReservation mtvreservation = MtvReservationManager.find(mContext, startTime.longValue(), new boolean[0]);
                String s = (new SimpleDateFormat("M/d H:mm")).format(new Date(((MtvProgram) (mtvreservation)).mTimeStart));
                String s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(((MtvProgram) (mtvreservation)).mTimeEnd))).toString();
                ((TextView)view1.findViewById(0x7f0a0113)).setText((new StringBuilder()).append(s1).append(" [").append(getResources().getString(0x7f0702ac)).append(Integer.toString(((MtvProgram) (mtvreservation)).mVch)).append("]").toString());
                ((TextView)view1.findViewById(0x7f0a0112)).setText(((MtvProgram) (mtvreservation)).mPgmName);
                ((ImageView)view1.findViewById(0x7f0a0053)).setImageDrawable(mIcon[mtvreservation.mPgmType][selectStatusIconIndex(mtvreservation)]);
                check = (CheckBox)view1.findViewById(0x7f0a0048).findViewById(0x7f0a0114);
                check.setVisibility(0);
                check.setChecked(Log.d(MtvUiRemoveList.this)[i]);
                check.setFocusable(false);
                check.setClickable(false);
            } else
            {
                Log.d(MtvUiRemoveList.this).setVisibility(4);
                Log.d(MtvUiRemoveList.this).setVisibility(4);
            }
            return view1;
        }

        public int selectStatusIconIndex(MtvReservation mtvreservation)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;->selectStatusIconIndex(Lcom/samsung/sec/mtv/provider/MtvReservation;)I");
            int i;
            if(mtvreservation.mPgmStatus == 0 && ((MtvProgram) (mtvreservation)).mTimeStart < System.currentTimeMillis())
                i = 1;
            else
            if(mtvreservation.mPgmStatus == 0 || mtvreservation.mPgmStatus == 6 || mtvreservation.mPgmStatus == 1)
                i = 0;
            else
                i = 1;
            return i;
        }

        public void setChecked(int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;->setChecked(IZ)V");
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        public void toggle(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;->toggle(I)V");
            boolean flag;
            if(Log.d(MtvUiRemoveList.this, i))
                flag = false;
            else
                flag = true;
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        private CheckBox check;
        private Context mContext;
        private final Drawable mIcon[][];
        private Long startTime;
        final MtvUiRemoveList this$0;

        public ReserveAdapter(Context context, int i, ArrayList arraylist)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/content/Context;ILjava/util/ArrayList;)V");
            this$0 = MtvUiRemoveList.this;
            super(context, i, arraylist);
            int ai[] = new int[2];
            ai[0] = 2;
            ai[1] = 2;
            mIcon = (Drawable[][])Array.newInstance(android/graphics/drawable/Drawable, ai);
            mContext = context;
            mIcon[1][0] = context.getResources().getDrawable(0x7f0200c0);
            mIcon[0][0] = context.getResources().getDrawable(0x7f0200bd);
            mIcon[1][1] = context.getResources().getDrawable(0x7f0200c1);
            mIcon[0][1] = context.getResources().getDrawable(0x7f0200be);
        }
    }

    public class StationDataAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$StationDataAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000c, null);
            String s = mTitleName[i];
            mCount = MtvBMLManager.deleteStationData_GetNetworkItemCount(i);
            mNameText = (TextView)view1.findViewById(0x7f0a003c);
            mCountText = (TextView)view1.findViewById(0x7f0a003d);
            check = (CheckBox)view1.findViewById(0x7f0a003e);
            check.setVisibility(0);
            check.setChecked(Log.d(MtvUiRemoveList.this)[i]);
            mNameText.setSelected(Log.d(MtvUiRemoveList.this)[i]);
            mCountText.setSelected(Log.d(MtvUiRemoveList.this)[i]);
            check.setFocusable(false);
            check.setClickable(false);
            mNameText.setText(s);
            mCountText.setVisibility(8);
            mCountText.setText((new StringBuilder()).append(": ").append(mCount).toString());
            return view1;
        }

        public void setChecked(int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$StationDataAdapter;->setChecked(IZ)V");
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        public void toggle(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$StationDataAdapter;->toggle(I)V");
            boolean flag;
            if(Log.d(MtvUiRemoveList.this, i))
                flag = false;
            else
                flag = true;
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(Log.d(MtvUiRemoveList.this).length);
        }

        private CheckBox check;
        private int mCount;
        private TextView mCountText;
        private TextView mNameText;
        private String mTitleName[];
        final MtvUiRemoveList this$0;

        public StationDataAdapter(Context context, int i, String as[])
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$StationDataAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/content/Context;I[Ljava/lang/String;)V");
            this$0 = MtvUiRemoveList.this;
            super(context, i, as);
            mTitleName = as;
        }
    }

    private class TvLinkAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1;
            MtvCProBMInfo mtvcprobminfo;
            ImageView imageview;
            TextView textview;
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
            view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000f, null);
            mtvcprobminfo = mMtvLinks[i];
            if(mtvcprobminfo == null)
                break MISSING_BLOCK_LABEL_260;
            Log.d(MtvUiRemoveList.this).setVisibility(0);
            Log.d(MtvUiRemoveList.this).setVisibility(0);
            imageview = (ImageView)view1.findViewById(0x7f0a004a);
            textview = (TextView)view1.findViewById(0x7f0a004b);
            view1.findViewById(0x7f0a0048).setVisibility(0);
            check = (CheckBox)view1.findViewById(0x7f0a0049);
            check.setChecked(Log.d(MtvUiRemoveList.this)[i]);
            check.setFocusable(false);
            check.setClickable(false);
            mtvcprobminfo.getCproBMType();
            JVM INSTR tableswitch 0 4: default 192
        //                       0 230
        //                       1 240
        //                       2 192
        //                       3 250
        //                       4 250;
               goto _L1 _L2 _L3 _L1 _L4 _L4
_L1:
            textview.setText((new StringBuilder()).append(" ").append(mtvcprobminfo.getTitle()).toString());
            view1.setTag(mtvcprobminfo);
_L5:
            return view1;
_L2:
            imageview.setImageResource(0x7f0200bb);
              goto _L1
_L3:
            imageview.setImageResource(0x7f0200bc);
              goto _L1
_L4:
            imageview.setImageResource(0x7f0200c2);
              goto _L1
            Log.d(MtvUiRemoveList.this).setVisibility(4);
            Log.d(MtvUiRemoveList.this).setVisibility(4);
              goto _L5
        }

        public void setChecked(int i, boolean flag)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;->setChecked(IZ)V");
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mLinks.length);
        }

        public void toggle(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;->toggle(I)V");
            boolean flag;
            if(Log.d(MtvUiRemoveList.this, i))
                flag = false;
            else
                flag = true;
            Log.d(MtvUiRemoveList.this, i, flag);
            Log.d(MtvUiRemoveList.this).setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mLinks.length);
        }

        private CheckBox check;
        private MtvCProBMInfo mMtvLinks[];
        final MtvUiRemoveList this$0;

        public TvLinkAdapter(Context context, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/content/Context;I)V");
            this$0 = MtvUiRemoveList.this;
            super(context, i);
        }

        public TvLinkAdapter(Context context, int i, MtvCProBMInfo amtvcprobminfo[])
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/content/Context;I[Lcom/samsung/sec/mtv/provider/MtvCProBMInfo;)V");
            this$0 = MtvUiRemoveList.this;
            super(context, i, amtvcprobminfo);
            mMtvLinks = amtvcprobminfo;
        }
    }


    public MtvUiRemoveList()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;-><init>()V");
        super();
        mReserveList = new ArrayList();
        mMtvFiles = null;
        mLinks = null;
        mListView = null;
        mAllCheck = null;
        mProgressDialog = null;
        mDeleteStationData = new String[8];
        mStationDataAdapter = null;
        mDeleteStationDataListUp = null;
        mNotiMgr = null;
        mTVFilesCount = 0;
        cancelLayout = null;
        doneLayout = null;
        actionBarView = null;
        mUiHandler = new Handler() {

            public void handleMessage(Message message)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$1;->handleMessage(Landroid/os/Message;)V");
                MtvUtilDebug.Low("MtvUiRemoveList", (new StringBuilder()).append("mUiHandler.handleMessage what=").append(message.what).toString());
                message.what;
                JVM INSTR tableswitch 0 1: default 60
            //                           0 61
            //                           1 146;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                Log.d(MtvUiRemoveList.this, new ProgressDialog(MtvUiRemoveList.this));
                Log.d(MtvUiRemoveList.this).setTitle(0x7f07028d);
                Log.d(MtvUiRemoveList.this).setMessage(getString(0x7f07029d));
                Log.d(MtvUiRemoveList.this).setIndeterminate(true);
                Log.d(MtvUiRemoveList.this).setCancelable(false);
                Log.d(MtvUiRemoveList.this).show();
                continue; /* Loop/switch isn't completed */
_L3:
                finish();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$3;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low("MtvUiRemoveList", "onServiceConnected...");
                Log.d(MtvUiRemoveList.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiRemoveList.this).registerListener(Log.d(MtvUiRemoveList.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$3;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiRemoveList.this).unregisterListener(Log.d(MtvUiRemoveList.this));
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$3;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4$1;->run()V");
                        finish();
                    }

                    final _cls4 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4;)V");
                            this$1 = _cls4.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$4;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        };
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                String s;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$10;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V");
                s = intent.getAction();
                MtvUtilDebug.Low("MtvUiRemoveList", (new StringBuilder()).append("mIntentReceiver onReceive: action=").append(s).toString());
                break MISSING_BLOCK_LABEL_39;
                if(s != null && s.equals("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED"))
                {
                    Log.d(MtvUiRemoveList.this);
                    MtvUtilDebug.Low("MtvUiRemoveList", (new StringBuilder()).append("mIntentReceiver:onRecieve for confirmation of TV files delete. Current count: ").append(Log.d(MtvUiRemoveList.this)).toString());
                    if(Log.d(MtvUiRemoveList.this) <= 0)
                    {
                        if(Log.d(MtvUiRemoveList.this) != null)
                        {
                            MtvUiRemoveList.sIsDeleting = false;
                            Log.d(MtvUiRemoveList.this).dismiss();
                            Log.d(MtvUiRemoveList.this, null);
                        }
                        Log.d(MtvUiRemoveList.this, 3, 0);
                        Log.d(MtvUiRemoveList.this, 4, 0);
                        finish();
                    } else
                    {
                        Log.d(MtvUiRemoveList.this);
                        MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("mIntentReceiver:onRecieve for confirmation of TV files delete. Current count:").append(Log.d(MtvUiRemoveList.this)).toString());
                    }
                }
                return;
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$10;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        };
    }

    private void clearDeleteFileDialog()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->clearDeleteFileDialog()V");
        MtvUtilDebug.Mid("MtvUiRemoveList", "clearDeleteFileDialog  called..");
        mUiHandler.sendEmptyMessage(1);
        requestSystemKeyEvent(3, false);
        requestSystemKeyEvent(4, false);
    }

    private void createTabList(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->createTabList(I)V");
        i;
        JVM INSTR tableswitch 100 104: default 44
    //                   100 394
    //                   101 44
    //                   102 576
    //                   103 45
    //                   104 216;
           goto _L1 _L2 _L1 _L3 _L4 _L5
_L1:
        return;
_L4:
        if(mMtvFiles == null && !sIsDeleting)
            mMtvFiles = MtvFileManager.getAvailableTVRecFilesEx();
        if(mMtvFiles != null && mMtvFiles.length != 0)
        {
            mListView.setVisibility(0);
            if(mItemsCheckedState == null)
                mItemsCheckedState = new boolean[mMtvFiles.length];
            mTvFileAdapter = new MtvFileAdapter(this, 0x7f030030);
            mListView.setAdapter(mTvFileAdapter);
            mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$5;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                    Log.d(MtvUiRemoveList.this).toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$5;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
            });
            checkItemCount(mMtvFiles.length);
        } else
        {
            mTvFileAdapter = new MtvFileAdapter(this, 0x7f030030);
            mListView.setAdapter(mTvFileAdapter);
            mAllCheck.setClickable(false);
            if(doneLayout != null)
                doneLayout.setEnabled(false);
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(mLinks == null)
            mLinks = MtvBMLManager.getAvailableCProBMInfoAll();
        if(mLinks != null && mLinks.length != 0)
        {
            MtvUtilDebug.Mid("MtvUiRemoveList", "mLinks is not null");
            mListView.setVisibility(0);
            if(mItemsCheckedState == null)
                mItemsCheckedState = new boolean[mLinks.length];
            mLinkAdapter = new TvLinkAdapter(this, 0x7f03000f, mLinks);
            mListView.setAdapter(mLinkAdapter);
            mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$6;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                    Log.d(MtvUiRemoveList.this).toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$6;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
            });
        } else
        {
            MtvUtilDebug.Mid("MtvUiRemoveList", "mLinks is null");
            mLinkAdapter = new TvLinkAdapter(this, 0x7f03000f);
            mListView.setAdapter(mLinkAdapter);
            mAllCheck.setClickable(false);
            if(doneLayout != null)
                doneLayout.setEnabled(false);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        mAffiliation_id = getIntent().getIntExtra("delete_station_data_index", 0);
        mAllCheck.setClickable(false);
        int k = 0;
        while(k < 8) 
        {
            if(MtvBMLManager.deleteStationData_GetNetworkName(mAffiliation_id, k) != null)
            {
                mDeleteStationData[k] = MtvBMLManager.deleteStationData_GetNetworkName(mAffiliation_id, k);
                mAllCheck.setClickable(true);
            } else
            {
                mDeleteStationData[k] = getString(0x7f070029);
            }
            k++;
        }
        if(mItemsCheckedState == null)
            mItemsCheckedState = new boolean[8];
        mStationDataAdapter = new StationDataAdapter(this, 0x7f03000c, mDeleteStationData);
        mListView.setAdapter(mStationDataAdapter);
        mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int l, long l1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$7;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                if(((TextView)view.findViewById(0x7f0a003c)).getText().toString().equals(getString(0x7f070029)))
                    Log.d(MtvUiRemoveList.this).setItemChecked(l, false);
                else
                    Log.d(MtvUiRemoveList.this).toggle(l);
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$7;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        });
        mAllCheck.setClickable(false);
        if(doneLayout != null)
            doneLayout.setEnabled(false);
        continue; /* Loop/switch isn't completed */
_L3:
        MtvReservation amtvreservation[] = MtvReservationManager.getAllReserves(this);
        if(amtvreservation != null && amtvreservation.length > 0)
        {
            mRemove_Reserve = new Long[amtvreservation.length];
            for(int j = 0; j < amtvreservation.length; j++)
            {
                mReserveList.add(Long.valueOf(amtvreservation[j].mTimeStart));
                mRemove_Reserve[j] = Long.valueOf(amtvreservation[j].mTimeStart);
            }

            if(mReserveList.size() <= 0)
            {
                mAllCheck.setClickable(false);
                if(doneLayout != null)
                    doneLayout.setEnabled(false);
            }
            if(mItemsCheckedState == null)
                mItemsCheckedState = new boolean[amtvreservation.length];
            mReserveAdapter = new ReserveAdapter(this, 0x7f03002d, mReserveList);
            mListView.setAdapter(mReserveAdapter);
            mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$8;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                    Log.d(MtvUiRemoveList.this).toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$8;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
            });
        }
        if(true) goto _L1; else goto _L6
_L6:
    }

    private Bitmap decodeBitmap(String s)
    {
        FileInputStream fileinputstream;
        Bitmap bitmap;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->decodeBitmap(Ljava/lang/String;)Landroid/graphics/Bitmap;");
        fileinputstream = null;
        bitmap = null;
        FileInputStream fileinputstream1 = new FileInputStream(s);
        Bitmap bitmap1 = BitmapFactory.decodeStream(fileinputstream1);
        bitmap = bitmap1;
        try
        {
            fileinputstream1.close();
        }
        catch(IOException ioexception3)
        {
            ioexception3.printStackTrace();
        }
        catch(NullPointerException nullpointerexception3)
        {
            nullpointerexception3.printStackTrace();
        }
_L1:
        return bitmap;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L5:
        filenotfoundexception.printStackTrace();
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        catch(NullPointerException nullpointerexception1)
        {
            nullpointerexception1.printStackTrace();
        }
          goto _L1
        Exception exception1;
        exception1;
_L4:
        exception1.printStackTrace();
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        catch(NullPointerException nullpointerexception2)
        {
            nullpointerexception2.printStackTrace();
        }
          goto _L1
        Exception exception;
        exception;
_L3:
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        catch(NullPointerException nullpointerexception)
        {
            nullpointerexception.printStackTrace();
        }
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        if(true) goto _L3; else goto _L2
_L2:
        exception1;
        fileinputstream = fileinputstream1;
          goto _L4
        filenotfoundexception;
        fileinputstream = fileinputstream1;
          goto _L5
    }

    private int getCheckedItemCount()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->getCheckedItemCount()I");
        int i = 0;
        if(mItemsCheckedState != null && mItemsCheckedState.length > 0)
        {
            for(int j = 0; j < mItemsCheckedState.length; j++)
                if(mItemsCheckedState[j])
                    i++;

        }
        return i;
    }

    private boolean getItemCheckedState(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->getItemCheckedState(I)Z");
        boolean flag;
        if(i <= mItemsCheckedState.length)
            flag = mItemsCheckedState[i];
        else
            flag = false;
        return flag;
    }

    private String getPreviewThumbnailPath(MtvFile mtvfile)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->getPreviewThumbnailPath(Lcom/samsung/sec/mtv/provider/MtvFile;)Ljava/lang/String;");
        String s = mtvfile.getFilePath();
        String s1;
        String s2;
        if(mtvfile.getFileFormat() == 0)
        {
            int k = s.indexOf("record");
            s1 = (new StringBuilder()).append(s.substring(0, k)).append("thumbnails/").toString();
            s2 = (new StringBuilder()).append("thumb").append(s.substring(k + 6, -4 + s.length())).append(".thm").toString();
        } else
        {
            int i = s.indexOf("/");
            int j;
            StringBuilder stringbuilder;
            Object aobj[];
            if(s.substring(0, i).equals("Phone") || s.substring(0, i).equals("PhMem"))
                s1 = "/sdcard/video/MyTvFiles/thumbnails/";
            else
                s1 = "/mnt/extSdCard/video/MyTvFiles/thumbnails/";
            j = Integer.parseInt(s.substring(-3 + s.length(), s.length()), 16);
            stringbuilder = new StringBuilder();
            aobj = new Object[1];
            aobj[0] = Integer.valueOf(j);
            s2 = stringbuilder.append(String.format("%03X", aobj)).append(".thm").toString();
        }
        MtvUtilDebug.Low("MtvUiRemoveList", (new StringBuilder()).append("getPreviewThumbnailPath = ").append(s1).append(s2).toString());
        return (new StringBuilder()).append(s1).append(s2).toString();
    }

    private void removeChannel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->removeChannel()V");
    }

    private void removeDeleteStationData()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->removeDeleteStationData()V");
        if(mDeleteStationData != null)
        {
            sIsDeleting = true;
            for(int i = 0; i < mDeleteStationData.length; i++)
                if(mItemsCheckedState[i])
                {
                    MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("mMtvPlayer.deleteStationData(").append(i).append(") call..").toString());
                    MtvBMLManager.deleteStationData_DeleteNetwork(mAffiliation_id, i);
                }

            finish();
        }
    }

    private void removeFile()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->removeFile()V");
        (new Thread(new Runnable() {

            public void run()
            {
                int i;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$9;->run()V");
                MtvUiRemoveList.sIsDeleting = true;
                i = 0;
                if(Log.d(MtvUiRemoveList.this) == null) goto _L2; else goto _L1
_L1:
                int j;
                MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("removeFile:mMtvFiles.length: ").append(Log.d(MtvUiRemoveList.this).length).append("mTVFilesCount :").append(Log.d(MtvUiRemoveList.this)).toString());
                j = 0;
_L12:
                if(j >= Log.d(MtvUiRemoveList.this).length) goto _L4; else goto _L3
_L3:
                if(!Log.d(MtvUiRemoveList.this)[j]) goto _L6; else goto _L5
_L5:
                MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("removeFile:mItemsCheckedState item:").append(j).append("mTVFilesCount").append(Log.d(MtvUiRemoveList.this)).toString());
                MtvFileManager.deleteTvFile(Log.d(MtvUiRemoveList.this)[j].getIndex(), Log.d(MtvUiRemoveList.this)[j].getFilePath(), MtvAppPlayerOneSeg.getInstance());
                if(j != Log.d(MtvUiRemoveList.this).getLatestFileIndex()) goto _L8; else goto _L7
_L7:
                Log.d(MtvUiRemoveList.this).setLatestFileIndex(0);
_L10:
                Log.d(MtvUiRemoveList.this)[j] = false;
                if(1 != Log.d(MtvUiRemoveList.this)[j].getFileFormat() && 1 != Log.d(MtvUiRemoveList.this)[j].getFileFormat())
                    break; /* Loop/switch isn't completed */
                MtvUtilDebug.Mid("MtvUiRemoveList", "removeFile:mItemsCheckedState break");
_L4:
                if(Log.d(MtvUiRemoveList.this).getLatestFileIndex() >= i)
                    Log.d(MtvUiRemoveList.this).setLatestFileIndex(Log.d(MtvUiRemoveList.this).getLatestFileIndex() - i);
                if(Log.d(MtvUiRemoveList.this) <= 0)
                    Log.d(MtvUiRemoveList.this);
_L2:
                return;
_L8:
                if(Log.d(MtvUiRemoveList.this).getLatestFileIndex() != 0 && j < Log.d(MtvUiRemoveList.this).getLatestFileIndex())
                    i++;
                if(true) goto _L10; else goto _L9
_L9:
                MtvUtilDebug.Mid("MtvUiRemoveList", "removeFile:mItemsCheckedState continue");
                Log.d(MtvUiRemoveList.this);
_L6:
                j++;
                if(true) goto _L12; else goto _L11
_L11:
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$9;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        })).start();
    }

    private void removeLink()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->removeLink()V");
        if(mLinks != null)
        {
            sIsDeleting = true;
            if(getCheckedItemCount() == mLinks.length)
            {
                MtvUtilDebug.Mid("MtvUiRemoveList", "MtvBMLManager.deleteCProBMInfoAll(All) call..");
                MtvBMLManager.deleteCProBMInfoAll();
            } else
            {
                int i = 0;
                while(i < mLinks.length) 
                {
                    if(mItemsCheckedState[i])
                    {
                        MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("MtvBMLManager.deleteCProBMInfo(").append(i).append(") call..").toString());
                        MtvBMLManager.deleteCProBMInfo(mLinks[i].getID());
                    }
                    i++;
                }
            }
            finish();
        }
    }

    private void removeReserve()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->removeReserve()V");
        for(int i = 0; i < mRemove_Reserve.length; i++)
        {
            Long long1 = mRemove_Reserve[i];
            if(mItemsCheckedState[i])
                MtvUtilSetReservationAlarm.setReservationAlarm(this, MtvReservationManager.find(this, long1.longValue(), new boolean[0]).mTimeStart, false, true);
        }

        finish();
    }

    private boolean requestSystemKeyEvent(int i, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->requestSystemKeyEvent(IZ)Z");
        android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        try
        {
            getComponentName();
            return true;
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        return false;
    }

    private void setFileInfo(MtvFile mtvfile)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->setFileInfo(Lcom/samsung/sec/mtv/provider/MtvFile;)V");
        if(mtvfile != null) goto _L2; else goto _L1
_L1:
        mPreImageView.setTag(null);
_L4:
        return;
_L2:
        mPreImageView.setTag(mtvfile);
        mPlayImageView.setVisibility(8);
        if(mtvfile.getFilePath() == null) goto _L4; else goto _L3
_L3:
        mtvfile.getFileFormat();
        JVM INSTR tableswitch 0 2: default 76
    //                   0 79
    //                   1 331
    //                   2 106;
           goto _L4 _L5 _L6 _L7
_L5:
        mPreImageView.setImageBitmap(decodeBitmap(getPreviewThumbnailPath(mtvfile)));
        mPlayImageView.setVisibility(0);
          goto _L4
_L7:
        FileInputStream fileinputstream2 = null;
        FileInputStream fileinputstream3 = new FileInputStream(mtvfile.getFilePath());
        MtvUtilCrypto mtvutilcrypto1 = new MtvUtilCrypto(2);
        ByteBuffer bytebuffer2 = ByteBuffer.allocate(mtvutilcrypto1.getOutputSize(2, (int)fileinputstream3.getChannel().size()));
        ByteBuffer bytebuffer3 = ByteBuffer.allocate((int)fileinputstream3.getChannel().size());
        fileinputstream3.read(bytebuffer3.array());
        mtvutilcrypto1.decryptData(bytebuffer3, bytebuffer2);
        mPreImageView.setImageBitmap(BitmapFactory.decodeByteArray(bytebuffer2.array(), 0, bytebuffer2.array().length));
_L8:
        FileNotFoundException filenotfoundexception1;
        IOException ioexception8;
        if(fileinputstream3 != null)
            try
            {
                fileinputstream3.close();
            }
            catch(IOException ioexception9)
            {
                ioexception9.printStackTrace();
            }
          goto _L4
        ioexception8;
        ioexception8.printStackTrace();
          goto _L8
        filenotfoundexception1;
        fileinputstream2 = fileinputstream3;
_L18:
        filenotfoundexception1.printStackTrace();
        if(fileinputstream2 != null)
            try
            {
                fileinputstream2.close();
            }
            catch(IOException ioexception7)
            {
                ioexception7.printStackTrace();
            }
          goto _L4
        Exception exception2;
        exception2;
_L17:
        exception2.printStackTrace();
        if(fileinputstream2 != null)
            try
            {
                fileinputstream2.close();
            }
            catch(IOException ioexception6)
            {
                ioexception6.printStackTrace();
            }
          goto _L4
        Exception exception3;
        exception3;
_L16:
        if(fileinputstream2 != null)
            try
            {
                fileinputstream2.close();
            }
            catch(IOException ioexception5)
            {
                ioexception5.printStackTrace();
            }
        throw exception3;
_L6:
        FileInputStream fileinputstream;
        String s;
        fileinputstream = null;
        s = getPreviewThumbnailPath(mtvfile);
        if(s != null) goto _L10; else goto _L9
_L9:
        mPreImageView.setVisibility(4);
        mPlayImageView.setVisibility(0);
          goto _L4
_L10:
        mPlayImageView.setVisibility(0);
        mPreImageView.setImageBitmap(null);
        FileInputStream fileinputstream1 = new FileInputStream(s);
        MtvUtilCrypto mtvutilcrypto = new MtvUtilCrypto(2);
        ByteBuffer bytebuffer = ByteBuffer.allocate(mtvutilcrypto.getOutputSize(2, (int)fileinputstream1.getChannel().size()));
        ByteBuffer bytebuffer1 = ByteBuffer.allocate((int)fileinputstream1.getChannel().size());
        fileinputstream1.read(bytebuffer1.array());
        mtvutilcrypto.decryptData(bytebuffer1, bytebuffer);
        mPreImageView.setImageBitmap(BitmapFactory.decodeByteArray(bytebuffer.array(), 0, bytebuffer.array().length));
_L11:
        if(fileinputstream1 != null)
            try
            {
                fileinputstream1.close();
            }
            catch(IOException ioexception4)
            {
                ioexception4.printStackTrace();
            }
          goto _L4
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
          goto _L11
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        fileinputstream = fileinputstream1;
_L15:
        filenotfoundexception.printStackTrace();
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
          goto _L4
        Exception exception;
        exception;
_L14:
        exception.printStackTrace();
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
          goto _L4
        Exception exception1;
        exception1;
_L13:
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception1;
        exception1;
        fileinputstream = fileinputstream1;
        if(true) goto _L13; else goto _L12
_L12:
        exception;
        fileinputstream = fileinputstream1;
          goto _L14
        filenotfoundexception;
          goto _L15
        exception3;
        fileinputstream2 = fileinputstream3;
          goto _L16
        exception2;
        fileinputstream2 = fileinputstream3;
          goto _L17
        filenotfoundexception1;
          goto _L18
    }

    private void setItemCheckedState(int i, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->setItemCheckedState(IZ)V");
        if(i <= mItemsCheckedState.length)
            mItemsCheckedState[i] = flag;
    }

    private void setSaveButtonEnabled(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->setSaveButtonEnabled(I)V");
        if(i <= 0 && !mAllCheck.isChecked()) goto _L2; else goto _L1
_L1:
        if(doneLayout != null)
            doneLayout.setEnabled(true);
_L4:
        return;
_L2:
        if(doneLayout != null)
            doneLayout.setEnabled(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void startDeleteFileDialog()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->startDeleteFileDialog()V");
        mUiHandler.sendEmptyMessage(0);
        mNotiMgr = (NotificationManager)getSystemService("notification");
        if(mNotiMgr != null)
            mNotiMgr.cancel(0x7f020113);
        requestSystemKeyEvent(3, true);
        requestSystemKeyEvent(4, true);
    }

    public void checkItemCount(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->checkItemCount(I)V");
        int j = 0;
        int k = 0;
        while(k < i) 
        {
            if(mItemsCheckedState[k])
                j++;
            if(i == j)
                mAllCheck.setChecked(true);
            else
                mAllCheck.setChecked(false);
            k++;
        }
        setSaveButtonEnabled(j);
    }

    public void onClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onClick(Landroid/view/View;)V");
        view.getId();
        JVM INSTR tableswitch 2131361837 2131361837: default 32
    //                   2131361837 33;
           goto _L1 _L2
_L1:
        return;
_L2:
        mRemoveListType;
        JVM INSTR tableswitch 100 104: default 72
    //                   100 298
    //                   101 80
    //                   102 373
    //                   103 148
    //                   104 223;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        break; /* Loop/switch isn't completed */
_L5:
        break; /* Loop/switch isn't completed */
_L10:
        setSaveButtonEnabled(0);
        if(true) goto _L1; else goto _L9
_L9:
        if(mAllCheck.isChecked())
        {
            int j2 = 0;
            while(j2 < mRemove_Channel.length) 
            {
                mListView.setItemChecked(j2, true);
                j2++;
            }
        } else
        {
            int i2 = 0;
            while(i2 < mRemove_Channel.length) 
            {
                mListView.setItemChecked(i2, false);
                i2++;
            }
        }
          goto _L10
_L7:
        if(mMtvFiles != null)
            if(mAllCheck.isChecked())
            {
                int l1 = 0;
                while(l1 < mMtvFiles.length) 
                {
                    mTvFileAdapter.setChecked(l1, true);
                    l1++;
                }
            } else
            {
                int k1 = 0;
                while(k1 < mMtvFiles.length) 
                {
                    mTvFileAdapter.setChecked(k1, false);
                    k1++;
                }
            }
          goto _L10
_L8:
        if(mLinks != null)
            if(mAllCheck.isChecked())
            {
                int j1 = 0;
                while(j1 < mLinks.length) 
                {
                    mLinkAdapter.setChecked(j1, true);
                    j1++;
                }
            } else
            {
                int i1 = 0;
                while(i1 < mLinks.length) 
                {
                    mLinkAdapter.setChecked(i1, false);
                    i1++;
                }
            }
          goto _L10
_L4:
        if(mDeleteStationData != null)
            if(mAllCheck.isChecked())
            {
                int l = 0;
                while(l < mDeleteStationData.length) 
                {
                    mStationDataAdapter.setChecked(l, true);
                    l++;
                }
            } else
            {
                int k = 0;
                while(k < mDeleteStationData.length) 
                {
                    mStationDataAdapter.setChecked(k, false);
                    k++;
                }
            }
          goto _L10
_L6:
        if(mAllCheck.isChecked())
        {
            int j = 0;
            while(j < mRemove_Reserve.length) 
            {
                mReserveAdapter.setChecked(j, true);
                j++;
            }
        } else
        {
            int i = 0;
            while(i < mRemove_Reserve.length) 
            {
                mReserveAdapter.setChecked(i, false);
                i++;
            }
        }
          goto _L10
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        getActionBar();
        setContentView(0x7f030006);
        getWindow().addFlags(128);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
            mRemoveListType = bundle1.getInt("Remove_List_Type");
        if(bundle != null)
            mItemsCheckedState = bundle.getBooleanArray("mItemsCheckedState");
        mListView = (ListView)findViewById(0x7f0a002f);
        mListView.setEmptyView(findViewById(0x7f0a0030));
        mListView.setChoiceMode(2);
        mAllCheck = (CheckBox)findViewById(0x7f0a002d);
        mAllCheck.setOnClickListener(this);
        mSelectAllText = (TextView)findViewById(0x7f0a002c);
        if(100 == mRemoveListType)
        {
            mSelectAllText.setVisibility(8);
            findViewById(0x7f0a002b).setVisibility(0);
        }
        mPreferences = new MtvPreferences(getApplicationContext());
        registerReceiver(mIntentReceiver, INTENT_FILTER);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onCreateOptionsMenu(Landroid/view/Menu;)Z");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(0x7f090000, menu);
        doneLayout = menu.getItem(1);
        cancelLayout = menu.getItem(0);
        menu.getItem(1).setEnabled(false);
        android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener = new android.view.MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuitem)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2;->onMenuItemClick(Landroid/view/MenuItem;)Z");
                menuitem.getItemId();
                JVM INSTR tableswitch 2131362098 2131362099: default 36
            //                           2131362098 241
            //                           2131362099 38;
                   goto _L1 _L2 _L3
_L1:
                return false;
_L3:
                Log.d(MtvUiRemoveList.this);
                JVM INSTR tableswitch 100 104: default 80
            //                           100 231
            //                           101 114
            //                           102 124
            //                           103 186
            //                           104 221;
                   goto _L4 _L5 _L6 _L7 _L8 _L9
_L4:
                if(Log.d(MtvUiRemoveList.this) != 103 && Log.d(MtvUiRemoveList.this) != 102)
                    finish();
                continue; /* Loop/switch isn't completed */
_L6:
                Log.d(MtvUiRemoveList.this);
                  goto _L4
_L7:
                class _cls2
                    implements android.content.DialogInterface.OnClickListener
                {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2$2;->onClick(Landroid/content/DialogInterface;I)V");
                        Log.d(_fld0);
                        dialoginterface.dismiss();
                    }

                    final _cls2 this$1;

                        _cls2()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2;)V");
                            this$1 = _cls2.this;
                            super();
                        }
                }

                class _cls1
                    implements android.content.DialogInterface.OnClickListener
                {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2$1;->onClick(Landroid/content/DialogInterface;I)V");
                        dialoginterface.dismiss();
                    }

                    final _cls2 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2$1;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2;)V");
                            this$1 = _cls2.this;
                            super();
                        }
                }

                (new android.app.AlertDialog.Builder(MtvUiRemoveList.this)).setTitle(0x7f07028d).setMessage(0x7f0702b4).setPositiveButton(0x7f070034, new _cls2()).setNegativeButton(0x7f070035, new _cls1()).show().getWindow().addFlags(1024);
                  goto _L4
_L8:
                Bundle bundle = new Bundle();
                bundle.putInt("dialogType", 14);
                MtvUiDialogsFrag.newInstance(bundle).show(getFragmentManager(), "tv_files_delete_ok_cancel");
                  goto _L4
_L9:
                Log.d(MtvUiRemoveList.this);
                  goto _L4
_L5:
                Log.d(MtvUiRemoveList.this);
                  goto _L4
_L2:
                finish();
                if(true) goto _L1; else goto _L10
_L10:
            }

            final MtvUiRemoveList this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$2;-><init>(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
                this$0 = MtvUiRemoveList.this;
                super();
            }
        };
        cancelLayout.setOnMenuItemClickListener(onmenuitemclicklistener);
        doneLayout.setOnMenuItemClickListener(onmenuitemclicklistener);
        return true;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onDestroy()V");
        MtvUtilDebug.Mid("MtvUiRemoveList", "onDestroy call...");
        sIsDeleting = false;
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        unregisterReceiver(mIntentReceiver);
        super.onDestroy();
    }

    public void onFragEvent(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onFragEvent(ILjava/lang/Object;)V");
        i;
        JVM INSTR tableswitch 280 280: default 28
    //                   280 29;
           goto _L1 _L2
_L1:
        return;
_L2:
        MtvUtilDebug.Mid("MtvUiRemoveList", "onFragEvent MTV_UPDATE_FRAG_CMD_DIALOGS_TV_FILES_DELETE_OK_CANCEL...");
        if(MtvUtilAppService.getRotation(getApplicationContext()) == 1)
            setRequestedOrientation(0);
        else
        if(MtvUtilAppService.getRotation(getApplicationContext()) == 3)
            setRequestedOrientation(8);
        else
            setRequestedOrientation(1);
        startDeleteFileDialog();
        for(int j = 0; j < mMtvFiles.length; j++)
            if(mItemsCheckedState[j])
                mTVFilesCount = 1 + mTVFilesCount;

        MtvUtilDebug.Mid("MtvUiRemoveList", (new StringBuilder()).append("onFragEvent MTV_UPDATE_FRAG_CMD_DIALOGS_TV_FILES_DELETE_OK_CANCEL:TV filecount:").append(mTVFilesCount).toString());
        removeFile();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onResume()V");
        super.onResume();
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        if(!MtvUtilAppService.isAppExiting()) goto _L2; else goto _L1
_L1:
        finish();
_L4:
        return;
_L2:
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        MtvUtilDebug.Low("MtvUiRemoveList", "onResume call..");
        createTabList(mRemoveListType);
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        layoutparams.screenBrightness = mPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        if(mRemoveListType == 100)
        {
            int i = getIntent().getIntExtra("delete_station_data_index", 0);
            mDeleteStationDataListUp = getBaseContext().getResources().getStringArray(0x7f05000a);
            getWindow().setTitle((new StringBuilder()).append(getString(0x7f07028d)).append(" : ").append(mDeleteStationDataListUp[i]).toString());
        } else
        {
            getWindow().setTitle(getString(0x7f07028d));
        }
        switch(mRemoveListType)
        {
        case 100: // 'd'
            if(mDeleteStationData != null && mDeleteStationData.length > 0 && !sIsDeleting)
                checkItemCount(mDeleteStationData.length);
            else
                finish();
            break;

        case 101: // 'e'
            checkItemCount(mRemove_Channel.length);
            break;

        case 102: // 'f'
            checkItemCount(mRemove_Reserve.length);
            break;

        case 103: // 'g'
            if(mMtvFiles != null && mMtvFiles.length > 0 && !sIsDeleting)
                checkItemCount(mMtvFiles.length);
            else
                finish();
            break;

        case 104: // 'h'
            if(mLinks != null && mLinks.length > 0 && !sIsDeleting)
                checkItemCount(mLinks.length);
            else
                finish();
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onSaveInstanceState(Landroid/os/Bundle;)V");
        bundle.putBooleanArray("mItemsCheckedState", mItemsCheckedState);
        super.onSaveInstanceState(bundle);
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->onStop()V");
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    private static IntentFilter INTENT_FILTER;
    static boolean sIsDeleting = false;
    View actionBarView;
    MenuItem cancelLayout;
    MenuItem doneLayout;
    private onMtvAppAndroidServiceListener listener;
    private int mAffiliation_id;
    private CheckBox mAllCheck;
    private ServiceConnection mConnection;
    private String mDeleteStationData[];
    private String mDeleteStationDataListUp[];
    private BroadcastReceiver mIntentReceiver;
    private boolean mItemsCheckedState[];
    private TvLinkAdapter mLinkAdapter;
    protected MtvCProBMInfo mLinks[];
    private ListView mListView;
    private MtvFile mMtvFiles[];
    private NotificationManager mNotiMgr;
    private ImageView mPlayImageView;
    private ImageView mPreImageView;
    private MtvPreferences mPreferences;
    private ProgressDialog mProgressDialog;
    private int mRemoveListType;
    private String mRemove_Channel[];
    private Long mRemove_Reserve[];
    private ReserveAdapter mReserveAdapter;
    private ArrayList mReserveList;
    private TextView mSelectAllText;
    private MtvAppAndroidService mService;
    private StationDataAdapter mStationDataAdapter;
    private int mTVFilesCount;
    private MtvFileAdapter mTvFileAdapter;
    private Handler mUiHandler;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;-><clinit>()V");
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED");
    }


/*
    static ProgressDialog access$000(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$000(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Landroid/app/ProgressDialog;");
        return mtvuiremovelist.mProgressDialog;
    }

*/


/*
    static ProgressDialog access$002(MtvUiRemoveList mtvuiremovelist, ProgressDialog progressdialog)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$002(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/app/ProgressDialog;)Landroid/app/ProgressDialog;");
        mtvuiremovelist.mProgressDialog = progressdialog;
        return progressdialog;
    }

*/


/*
    static int access$100(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$100(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)I");
        return mtvuiremovelist.mRemoveListType;
    }

*/


/*
    static ListView access$1000(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1000(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Landroid/widget/ListView;");
        return mtvuiremovelist.mListView;
    }

*/


/*
    static StationDataAdapter access$1100(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1100(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$StationDataAdapter;");
        return mtvuiremovelist.mStationDataAdapter;
    }

*/


/*
    static ReserveAdapter access$1200(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1200(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$ReserveAdapter;");
        return mtvuiremovelist.mReserveAdapter;
    }

*/


/*
    static MtvFile[] access$1300(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1300(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)[Lcom/samsung/sec/mtv/provider/MtvFile;");
        return mtvuiremovelist.mMtvFiles;
    }

*/


/*
    static int access$1400(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1400(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)I");
        return mtvuiremovelist.mTVFilesCount;
    }

*/


/*
    static int access$1410(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1410(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)I");
        int i = mtvuiremovelist.mTVFilesCount;
        mtvuiremovelist.mTVFilesCount = i - 1;
        return i;
    }

*/


/*
    static boolean[] access$1500(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1500(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)[Z");
        return mtvuiremovelist.mItemsCheckedState;
    }

*/


/*
    static MtvPreferences access$1600(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1600(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuiremovelist.mPreferences;
    }

*/


/*
    static void access$1700(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1700(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.clearDeleteFileDialog();
        return;
    }

*/


/*
    static ArrayList access$1800(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1800(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Ljava/util/ArrayList;");
        return mtvuiremovelist.mReserveList;
    }

*/


/*
    static TextView access$1900(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$1900(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Landroid/widget/TextView;");
        return mtvuiremovelist.mSelectAllText;
    }

*/


/*
    static void access$200(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$200(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.removeChannel();
        return;
    }

*/


/*
    static CheckBox access$2000(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2000(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Landroid/widget/CheckBox;");
        return mtvuiremovelist.mAllCheck;
    }

*/


/*
    static boolean access$2100(MtvUiRemoveList mtvuiremovelist, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2100(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;I)Z");
        return mtvuiremovelist.getItemCheckedState(i);
    }

*/


/*
    static void access$2200(MtvUiRemoveList mtvuiremovelist, int i, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2200(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;IZ)V");
        mtvuiremovelist.setItemCheckedState(i, flag);
        return;
    }

*/


/*
    static Long[] access$2300(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2300(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)[Ljava/lang/Long;");
        return mtvuiremovelist.mRemove_Reserve;
    }

*/


/*
    static ImageView access$2402(MtvUiRemoveList mtvuiremovelist, ImageView imageview)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2402(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/widget/ImageView;)Landroid/widget/ImageView;");
        mtvuiremovelist.mPreImageView = imageview;
        return imageview;
    }

*/


/*
    static ImageView access$2502(MtvUiRemoveList mtvuiremovelist, ImageView imageview)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2502(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Landroid/widget/ImageView;)Landroid/widget/ImageView;");
        mtvuiremovelist.mPlayImageView = imageview;
        return imageview;
    }

*/


/*
    static void access$2600(MtvUiRemoveList mtvuiremovelist, MtvFile mtvfile)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2600(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Lcom/samsung/sec/mtv/provider/MtvFile;)V");
        mtvuiremovelist.setFileInfo(mtvfile);
        return;
    }

*/


/*
    static String[] access$2700(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2700(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)[Ljava/lang/String;");
        return mtvuiremovelist.mDeleteStationData;
    }

*/


/*
    static boolean access$2800(MtvUiRemoveList mtvuiremovelist, int i, boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2800(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;IZ)Z");
        return mtvuiremovelist.requestSystemKeyEvent(i, flag);
    }

*/


/*
    static void access$2900(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$2900(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.removeFile();
        return;
    }

*/


/*
    static void access$300(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$300(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.removeReserve();
        return;
    }

*/


/*
    static void access$400(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$400(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.removeLink();
        return;
    }

*/


/*
    static void access$500(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$500(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)V");
        mtvuiremovelist.removeDeleteStationData();
        return;
    }

*/


/*
    static MtvAppAndroidService access$600(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$600(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuiremovelist.mService;
    }

*/


/*
    static MtvAppAndroidService access$602(MtvUiRemoveList mtvuiremovelist, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$602(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuiremovelist.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$700(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$700(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuiremovelist.listener;
    }

*/


/*
    static MtvFileAdapter access$800(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$800(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$MtvFileAdapter;");
        return mtvuiremovelist.mTvFileAdapter;
    }

*/


/*
    static TvLinkAdapter access$900(MtvUiRemoveList mtvuiremovelist)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;->access$900(Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList;)Lcom/samsung/sec/mtv/ui/common/MtvUiRemoveList$TvLinkAdapter;");
        return mtvuiremovelist.mLinkAdapter;
    }

*/
}
