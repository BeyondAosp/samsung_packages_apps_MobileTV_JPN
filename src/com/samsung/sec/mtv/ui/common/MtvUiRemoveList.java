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
            int i;
            if(mMtvFiles != null && mMtvFiles.length > 0)
                i = mMtvFiles.length;
            else
                i = 0;
            return i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f030030, null);
            mPreImageView = (ImageView)view1.findViewById(0x7f0a0119);
            mPlayImageView = (ImageView)view1.findViewById(0x7f0a011a);
            MtvFile mtvfile = mMtvFiles[i];
            if(mtvfile != null)
            {
                mSelectAllText.setVisibility(0);
                mAllCheck.setVisibility(0);
                TextView textview = (TextView)view1.findViewById(0x7f0a011e);
                TextView textview1 = (TextView)view1.findViewById(0x7f0a0120);
                TextView textview2 = (TextView)view1.findViewById(0x7f0a0121);
                CheckBox checkbox = (CheckBox)view1.findViewById(0x7f0a0122);
                checkbox.setVisibility(0);
                checkbox.setChecked(mItemsCheckedState[i]);
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
                setFileInfo(mtvfile);
            } else
            {
                mSelectAllText.setVisibility(4);
                mAllCheck.setVisibility(4);
            }
            return view1;
        }

        public void setChecked(int i, boolean flag)
        {
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mMtvFiles.length);
        }

        public void toggle(int i)
        {
            boolean flag;
            if(getItemCheckedState(i))
                flag = false;
            else
                flag = true;
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mMtvFiles.length);
        }

        final MtvUiRemoveList this$0;

        public MtvFileAdapter(Context context, int i)
        {
            this$0 = MtvUiRemoveList.this;
            ArrayAdapter(context, i);
        }
    }

    public class ReserveAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03002d, null);
            startTime = (Long)mReserveList.get(i);
            if(startTime != null)
            {
                mSelectAllText.setVisibility(0);
                mAllCheck.setVisibility(0);
                MtvReservation mtvreservation = MtvReservationManager.find(mContext, startTime.longValue(), new boolean[0]);
                String s = (new SimpleDateFormat("M/d H:mm")).format(new Date(((MtvProgram) (mtvreservation)).mTimeStart));
                String s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(((MtvProgram) (mtvreservation)).mTimeEnd))).toString();
                ((TextView)view1.findViewById(0x7f0a0113)).setText((new StringBuilder()).append(s1).append(" [").append(getResources().getString(0x7f0702ac)).append(Integer.toString(((MtvProgram) (mtvreservation)).mVch)).append("]").toString());
                ((TextView)view1.findViewById(0x7f0a0112)).setText(((MtvProgram) (mtvreservation)).mPgmName);
                ((ImageView)view1.findViewById(0x7f0a0053)).setImageDrawable(mIcon[mtvreservation.mPgmType][selectStatusIconIndex(mtvreservation)]);
                check = (CheckBox)view1.findViewById(0x7f0a0048).findViewById(0x7f0a0114);
                check.setVisibility(0);
                check.setChecked(mItemsCheckedState[i]);
                check.setFocusable(false);
                check.setClickable(false);
            } else
            {
                mSelectAllText.setVisibility(4);
                mAllCheck.setVisibility(4);
            }
            return view1;
        }

        public int selectStatusIconIndex(MtvReservation mtvreservation)
        {
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
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mRemove_Reserve.length);
        }

        public void toggle(int i)
        {
            boolean flag;
            if(getItemCheckedState(i))
                flag = false;
            else
                flag = true;
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mRemove_Reserve.length);
        }

        private CheckBox check;
        private Context mContext;
        private final Drawable mIcon[][];
        private Long startTime;
        final MtvUiRemoveList this$0;

        public ReserveAdapter(Context context, int i, ArrayList arraylist)
        {
            this$0 = MtvUiRemoveList.this;
            ArrayAdapter(context, i, arraylist);
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
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000c, null);
            String s = mTitleName[i];
            mCount = MtvBMLManager.deleteStationData_GetNetworkItemCount(i);
            mNameText = (TextView)view1.findViewById(0x7f0a003c);
            mCountText = (TextView)view1.findViewById(0x7f0a003d);
            check = (CheckBox)view1.findViewById(0x7f0a003e);
            check.setVisibility(0);
            check.setChecked(mItemsCheckedState[i]);
            mNameText.setSelected(mItemsCheckedState[i]);
            mCountText.setSelected(mItemsCheckedState[i]);
            check.setFocusable(false);
            check.setClickable(false);
            mNameText.setText(s);
            mCountText.setVisibility(8);
            mCountText.setText((new StringBuilder()).append(": ").append(mCount).toString());
            return view1;
        }

        public void setChecked(int i, boolean flag)
        {
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mDeleteStationData.length);
        }

        public void toggle(int i)
        {
            boolean flag;
            if(getItemCheckedState(i))
                flag = false;
            else
                flag = true;
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mDeleteStationData.length);
        }

        private CheckBox check;
        private int mCount;
        private TextView mCountText;
        private TextView mNameText;
        private String mTitleName[];
        final MtvUiRemoveList this$0;

        public StationDataAdapter(Context context, int i, String as[])
        {
            this$0 = MtvUiRemoveList.this;
            ArrayAdapter(context, i, as);
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
            view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000f, null);
            mtvcprobminfo = mMtvLinks[i];
            if(mtvcprobminfo == null)
                break MISSING_BLOCK_LABEL_252;
            mSelectAllText.setVisibility(0);
            mAllCheck.setVisibility(0);
            imageview = (ImageView)view1.findViewById(0x7f0a004a);
            textview = (TextView)view1.findViewById(0x7f0a004b);
            view1.findViewById(0x7f0a0048).setVisibility(0);
            check = (CheckBox)view1.findViewById(0x7f0a0049);
            check.setChecked(mItemsCheckedState[i]);
            check.setFocusable(false);
            check.setClickable(false);
            mtvcprobminfo.getCproBMType();
            JVM INSTR tableswitch 0 4: default 184
        //                       0 222
        //                       1 232
        //                       2 184
        //                       3 242
        //                       4 242;
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
            mSelectAllText.setVisibility(4);
            mAllCheck.setVisibility(4);
              goto _L5
        }

        public void setChecked(int i, boolean flag)
        {
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mLinks.length);
        }

        public void toggle(int i)
        {
            boolean flag;
            if(getItemCheckedState(i))
                flag = false;
            else
                flag = true;
            setItemCheckedState(i, flag);
            mListView.setItemChecked(i, flag);
            notifyDataSetChanged();
            checkItemCount(mLinks.length);
        }

        private CheckBox check;
        private MtvCProBMInfo mMtvLinks[];
        final MtvUiRemoveList this$0;

        public TvLinkAdapter(Context context, int i)
        {
            this$0 = MtvUiRemoveList.this;
            ArrayAdapter(context, i);
        }

        public TvLinkAdapter(Context context, int i, MtvCProBMInfo amtvcprobminfo[])
        {
            this$0 = MtvUiRemoveList.this;
            ArrayAdapter(context, i, amtvcprobminfo);
            mMtvLinks = amtvcprobminfo;
        }
    }


    public MtvUiRemoveList()
    {
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
                MtvUtilDebug.Low("MtvUiRemoveList", (new StringBuilder()).append("mUiHandler.handleMessage what=").append(message.what).toString());
                message.what;
                JVM INSTR tableswitch 0 1: default 52
            //                           0 53
            //                           1 138;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                mProgressDialog = new ProgressDialog(MtvUiRemoveList.this);
                mProgressDialog.setTitle(0x7f07028d);
                mProgressDialog.setMessage(getString(0x7f07029d));
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                continue; /* Loop/switch isn't completed */
_L3:
                finish();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Handler();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low("MtvUiRemoveList", "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(listener);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mService.unregisterListener(listener);
            }

            final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        finish();
                    }

                    final _cls4 this$1;

                        _cls1()
                        {
                            this$1 = _cls4.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
            }

            final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
            }
        };
        mIntentReceiver = new BroadcastReceiver() ;
    }

    private void clearDeleteFileDialog()
    {
        MtvUtilDebug.Mid("MtvUiRemoveList", "clearDeleteFileDialog  called..");
        mUiHandler.sendEmptyMessage(1);
        requestSystemKeyEvent(3, false);
        requestSystemKeyEvent(4, false);
    }

    private void createTabList(int i)
    {
        i;
        JVM INSTR tableswitch 100 104: default 36
    //                   100 386
    //                   101 36
    //                   102 568
    //                   103 37
    //                   104 208;
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
                    mTvFileAdapter.toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
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
                    mLinkAdapter.toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
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
                if(((TextView)view.findViewById(0x7f0a003c)).getText().toString().equals(getString(0x7f070029)))
                    mListView.setItemChecked(l, false);
                else
                    mStationDataAdapter.toggle(l);
            }

            final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
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
                    mReserveAdapter.toggle(l);
                }

                final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
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
        boolean flag;
        if(i <= mItemsCheckedState.length)
            flag = mItemsCheckedState[i];
        else
            flag = false;
        return flag;
    }

    private String getPreviewThumbnailPath(MtvFile mtvfile)
    {
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
    }

    private void removeDeleteStationData()
    {
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
        (new Thread(new Runnable() )).start();
    }

    private void removeLink()
    {
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
        IWindowManager iwindowmanager = android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        boolean flag2 = iwindowmanager.requestSystemKeyEvent(i, getComponentName(), flag);
        boolean flag1 = flag2;
_L2:
        return flag1;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        flag1 = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void setFileInfo(MtvFile mtvfile)
    {
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
        JVM INSTR tableswitch 0 2: default 68
    //                   0 71
    //                   1 323
    //                   2 98;
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
        if(i <= mItemsCheckedState.length)
            mItemsCheckedState[i] = flag;
    }

    private void setSaveButtonEnabled(int i)
    {
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
        mUiHandler.sendEmptyMessage(0);
        mNotiMgr = (NotificationManager)getSystemService("notification");
        if(mNotiMgr != null)
            mNotiMgr.cancel(0x7f020113);
        requestSystemKeyEvent(3, true);
        requestSystemKeyEvent(4, true);
    }

    public void checkItemCount(int i)
    {
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
        view.getId();
        JVM INSTR tableswitch 2131361837 2131361837: default 24
    //                   2131361837 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        mRemoveListType;
        JVM INSTR tableswitch 100 104: default 64
    //                   100 290
    //                   101 72
    //                   102 365
    //                   103 140
    //                   104 215;
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
        onCreate(bundle);
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
        onCreateOptionsMenu(menu);
        getMenuInflater().inflate(0x7f090000, menu);
        doneLayout = menu.getItem(1);
        cancelLayout = menu.getItem(0);
        menu.getItem(1).setEnabled(false);
        android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener = new android.view.MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuitem)
            {
                menuitem.getItemId();
                JVM INSTR tableswitch 2131362098 2131362099: default 28
            //                           2131362098 233
            //                           2131362099 30;
                   goto _L1 _L2 _L3
_L1:
                return false;
_L3:
                mRemoveListType;
                JVM INSTR tableswitch 100 104: default 72
            //                           100 223
            //                           101 106
            //                           102 116
            //                           103 178
            //                           104 213;
                   goto _L4 _L5 _L6 _L7 _L8 _L9
_L4:
                if(mRemoveListType != 103 && mRemoveListType != 102)
                    finish();
                continue; /* Loop/switch isn't completed */
_L6:
                removeChannel();
                  goto _L4
_L7:
                class _cls2
                    implements android.content.DialogInterface.OnClickListener
                {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        removeReserve();
                        dialoginterface.dismiss();
                    }

                    final _cls2 this$1;

                        _cls2()
                        {
                            this$1 = _cls2.this;
                            Object();
                        }
                }

                class _cls1
                    implements android.content.DialogInterface.OnClickListener
                {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls2 this$1;

                        _cls1()
                        {
                            this$1 = _cls2.this;
                            Object();
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
                removeLink();
                  goto _L4
_L5:
                removeDeleteStationData();
                  goto _L4
_L2:
                finish();
                if(true) goto _L1; else goto _L10
_L10:
            }

            final MtvUiRemoveList this$0;

            
            {
                this$0 = MtvUiRemoveList.this;
                Object();
            }
        };
        cancelLayout.setOnMenuItemClickListener(onmenuitemclicklistener);
        doneLayout.setOnMenuItemClickListener(onmenuitemclicklistener);
        return true;
    }

    public void onDestroy()
    {
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
        i;
        JVM INSTR tableswitch 280 280: default 20
    //                   280 21;
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
        bundle.putBooleanArray("mItemsCheckedState", mItemsCheckedState);
        super.onSaveInstanceState(bundle);
    }

    public void onStop()
    {
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
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED");
    }



/*
    static ProgressDialog access$002(MtvUiRemoveList mtvuiremovelist, ProgressDialog progressdialog)
    {
        mtvuiremovelist.mProgressDialog = progressdialog;
        return progressdialog;
    }

*/








/*
    static int access$1410(MtvUiRemoveList mtvuiremovelist)
    {
        int i = mtvuiremovelist.mTVFilesCount;
        mtvuiremovelist.mTVFilesCount = i - 1;
        return i;
    }

*/












/*
    static ImageView access$2402(MtvUiRemoveList mtvuiremovelist, ImageView imageview)
    {
        mtvuiremovelist.mPreImageView = imageview;
        return imageview;
    }

*/


/*
    static ImageView access$2502(MtvUiRemoveList mtvuiremovelist, ImageView imageview)
    {
        mtvuiremovelist.mPlayImageView = imageview;
        return imageview;
    }

*/










/*
    static MtvAppAndroidService access$602(MtvUiRemoveList mtvuiremovelist, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuiremovelist.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/



}
