// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegProgram;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.ui.common.MtvUiMemInfoFrag;
import com.samsung.sec.mtv.utility.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MtvUiChannelSchedule extends Activity
    implements android.view.View.OnClickListener, IMtvAppPlaybackStateListener, IMtvAppProgramAttributeListener
{
    private class ProgramAdapter extends BaseAdapter
    {

        private boolean isDatechanged(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->isDatechanged(I)Z");
            boolean flag;
            if(i == 0)
            {
                flag = true;
            } else
            {
                Date date = new Date(mtvPrograms[i - 1].mTimeStart);
                Date date1 = new Date(mtvPrograms[i].mTimeStart);
                if(date.getDay() != date1.getDay())
                    flag = true;
                else
                    flag = false;
            }
            return flag;
        }

        public int getCount()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->getCount()I");
            int i;
            if(mtvPrograms != null)
                i = mtvPrograms.length;
            else
                i = 0;
            return i;
        }

        public MtvProgram getItem(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->getItem(I)Lcom/samsung/sec/mtv/provider/MtvProgram;");
            return mtvPrograms[i];
        }

        public volatile Object getItem(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->getItem(I)Ljava/lang/Object;");
            return getItem(i);
        }

        public long getItemId(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->getItemId(I)J");
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;");
            MtvProgram mtvprogram = mtvPrograms[i];
            View view1 = mInflater.inflate(0x7f030011, viewgroup, false);
            if(mtvprogram != null)
            {
                view1.setTag(mtvprogram);
                TextView textview = (TextView)view1.findViewById(0x7f0a0051);
                String s;
                String s1;
                ImageView imageview;
                MtvReservation mtvreservation;
                int j;
                MtvProgram mtvprogram1;
                if(isDatechanged(i))
                    textview.setText((new SimpleDateFormat("yyyy.M.d(EEE)")).format(new Date(mtvprogram.mTimeStart)));
                else
                    textview.setVisibility(8);
                s = (new SimpleDateFormat("H:mm")).format(new Date(mtvprogram.mTimeStart));
                s1 = (new StringBuilder()).append(s).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(mtvprogram.mTimeEnd))).toString();
                imageview = (ImageView)view1.findViewById(0x7f0a0053);
                mtvreservation = MtvReservationManager.find(context, mtvprogram.mTimeStart, new boolean[0]);
                if(mtvreservation != null && mtvreservation.mPgmName.equals(mtvprogram.mPgmName))
                {
                    if(mtvreservation != null && mtvreservation.mPgmType == 0)
                        imageview.setImageDrawable(Log.d(MtvUiChannelSchedule.this)[2]);
                    else
                    if(mtvreservation != null && mtvreservation.mPgmType == 1)
                        imageview.setImageDrawable(Log.d(MtvUiChannelSchedule.this)[1]);
                    imageview.setAlpha(255);
                } else
                {
                    imageview.setImageDrawable(Log.d(MtvUiChannelSchedule.this)[0]);
                    imageview.setAlpha(0);
                }
                ((TextView)view1.findViewById(0x7f0a0054)).setText(s1);
                ((TextView)view1.findViewById(0x7f0a0055)).setText(mtvprogram.mPgmName);
                if(Log.d(MtvUiChannelSchedule.this) == null)
                    Log.d(MtvUiChannelSchedule.this, new MtvPreferences(getApplicationContext()));
                j = Log.d(MtvUiChannelSchedule.this).getLatestVChannel();
                mtvprogram1 = null;
                Log.d(MtvUiChannelSchedule.this, MtvAppPlaybackContextManager.getInstance().getCurrentContext());
                if(j < 0)
                {
                    MtvUtilDebug.Mid("MtvUiChannelSchedule", "....using PCH");
                    if(Log.d(MtvUiChannelSchedule.this) != null)
                        mtvprogram1 = MtvProgramManager.getCurrentProgramByPhCh(context, Log.d(MtvUiChannelSchedule.this).getLatestPChannelFromVChannel(), Log.d(MtvUiChannelSchedule.this).getAttribute().getTot());
                } else
                {
                    MtvChannel mtvchannel = MtvChannelManager.findByVChannel(context, j);
                    if(mtvchannel != null && Log.d(MtvUiChannelSchedule.this) != null)
                        mtvprogram1 = MtvProgramManager.getCurrentProgramByPhCh(context, mtvchannel.mPhysicalNum, Log.d(MtvUiChannelSchedule.this).getAttribute().getTot());
                }
                if(mtvprogram1 != null && mtvprogram.mTimeStart == mtvprogram1.mTimeStart && mtvprogram.mTimeEnd == mtvprogram1.mTimeEnd)
                {
                    MtvUtilDebug.Low("MtvUiChannelSchedule", "match");
                    ((ImageView)view1.findViewById(0x7f0a0056)).setVisibility(0);
                } else
                {
                    ((ImageView)view1.findViewById(0x7f0a0056)).setVisibility(4);
                }
            }
            return view1;
        }

        public void setSelectItemTextColor(int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;->setSelectItemTextColor(I)V");
            Log.d(MtvUiChannelSchedule.this).notifyDataSetChanged();
        }

        private Context context;
        private final LayoutInflater mInflater;
        private MtvProgram mtvPrograms[];
        final MtvUiChannelSchedule this$0;

        public ProgramAdapter(Context context1, MtvProgram amtvprogram[])
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Landroid/content/Context;[Lcom/samsung/sec/mtv/provider/MtvProgram;)V");
            this$0 = MtvUiChannelSchedule.this;
            super();
            mtvPrograms = amtvprogram;
            context = context1;
            mInflater = (LayoutInflater)context1.getSystemService("layout_inflater");
        }
    }


    public MtvUiChannelSchedule()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;-><init>()V");
        super();
        mProgramAdapter = null;
        mMtvAppPlaybackContext = null;
        mChannelScheduleUiMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$1;->handleMessage(Landroid/os/Message;)V");
                message.what;
                JVM INSTR tableswitch 320 320: default 32
            //                           320 33;
                   goto _L1 _L2
_L1:
                return;
_L2:
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        MtvOneSegProgram amtvonesegprogram[];
                        int i;
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$1$1;->run()V");
                        amtvonesegprogram = getProgram();
                        if(Log.d(_fld0) == null)
                            Log.d(_fld0, new MtvPreferences(getApplicationContext()));
                        i = Log.d(_fld0).getLatestVChannel();
                        MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("oneSegProgramList").append(amtvonesegprogram).toString());
                        if(amtvonesegprogram == null || amtvonesegprogram.length <= 0) goto _L2; else goto _L1
_L1:
                        int j;
                        MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("oneSegProgramList.length").append(amtvonesegprogram.length).toString());
                        j = 0;
_L6:
                        if(j < amtvonesegprogram.length && amtvonesegprogram[j] != null) goto _L4; else goto _L3
_L3:
                        Log.d(_fld0, Log.d(_fld0).getLatestPChannel());
_L2:
                        return;
_L4:
                        MtvProgram mtvprogram = new MtvProgram(amtvonesegprogram[j], i);
                        if(mtvprogram != null)
                            MtvProgramManager.updateOrInsert(getApplicationContext(), mtvprogram);
                        j++;
                        if(true) goto _L6; else goto _L5
_L5:
                    }

                    final _cls1 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$1$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$1;)V");
                            this$1 = _cls1.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
                if(true) goto _L1; else goto _L3
_L3:
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$2;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low("MtvUiChannelSchedule", "onServiceConnected...");
                Log.d(MtvUiChannelSchedule.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiChannelSchedule.this).registerListener(Log.d(MtvUiChannelSchedule.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$2;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiChannelSchedule.this).unregisterListener(Log.d(MtvUiChannelSchedule.this));
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$2;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3$1;->run()V");
                        finish();
                    }

                    final _cls3 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3;)V");
                            this$1 = _cls3.this;
                            super();
                        }
                }

                runOnUiThread(new _cls1());
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$3;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
        mScheduleListListener = new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$5;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                MtvUtilDebug.Low("MtvUiChannelSchedule", "mScheduleListListener");
                if(getFragmentManager().findFragmentByTag("ProgInfoFrag") == null)
                {
                    MtvProgram mtvprogram = (MtvProgram)view.getTag();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pgmPch", mtvprogram.mPch);
                    MtvChannel mtvchannel = MtvChannelManager.findByPChannel(getApplicationContext(), mtvprogram.mPch);
                    if(mtvchannel != null)
                        bundle.putString("channelName", mtvchannel.mChannelName);
                    bundle.putLong("startTime", mtvprogram.mTimeStart);
                    bundle.putLong("endtime", mtvprogram.mTimeEnd);
                    bundle.putString("programName", mtvprogram.mPgmName);
                    MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append(" pgm.mPgmDetail :").append(mtvprogram.mPgmDetail).toString());
                    bundle.putString("programDesc", mtvprogram.mPgmDetail);
                    bundle.putInt("reservationType", checkForReservationType(mtvprogram));
                    MtvUiFragHandler.addUnManagedFrag("ProgInfoFrag", bundle, 0x7f0a0000, MtvUiChannelSchedule.this);
                    Log.d(MtvUiChannelSchedule.this).setSelectItemTextColor(i);
                }
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$5;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
        mScheduleListLongListener = new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$6;->onItemLongClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)Z");
                ((ListView)findViewById(0x7f0a0005)).setOnItemClickListener(null);
                MtvUtilDebug.Low("MtvUiChannelSchedule", "mScheduleListLongListener");
                MtvProgram mtvprogram = (MtvProgram)view.getTag();
                boolean flag = Log.d(MtvUiChannelSchedule.this, mtvprogram, view);
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$6$1;->run()V");
                        ((ListView)findViewById(0x7f0a0005)).setOnItemClickListener(mScheduleListListener);
                    }

                    final _cls6 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$6$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$6;)V");
                            this$1 = _cls6.this;
                            super();
                        }
                }

                Log.d(MtvUiChannelSchedule.this).postDelayed(new _cls1(), 1000L);
                return flag;
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$6;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
        mScheduleListClickListener = new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$7;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
                MtvUtilDebug.Low("MtvUiChannelSchedule", "mScheduleListClickListener");
                MtvProgram mtvprogram = (MtvProgram)view.getTag();
                Log.d(MtvUiChannelSchedule.this, mtvprogram, view);
            }

            final MtvUiChannelSchedule this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$7;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)V");
                this$0 = MtvUiChannelSchedule.this;
                super();
            }
        };
    }

    private CharSequence getSpinnerTitle(MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->getSpinnerTitle(Lcom/samsung/sec/mtv/provider/MtvChannel;)Ljava/lang/CharSequence;");
        return (new StringBuilder()).append("CH ").append(mtvchannel.mPhysicalNum).append(" ").append(mtvchannel.mChannelName).toString();
    }

    private boolean isReservationAlreadyTime(MtvProgram mtvprogram)
    {
        boolean flag;
        MtvReservation amtvreservation[];
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->isReservationAlreadyTime(Lcom/samsung/sec/mtv/provider/MtvProgram;)Z");
        flag = false;
        amtvreservation = (MtvReservation[])MtvReservationManager.getAllReserves(this);
        if(amtvreservation != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        Arrays.sort(amtvreservation);
        for(int i = 0; i < amtvreservation.length; i++)
        {
            if(amtvreservation[i].mTimeStart > System.currentTimeMillis())
                continue;
            if(mtvprogram.mTimeStart == amtvreservation[i].mTimeStart && mtvprogram.mTimeEnd == amtvreservation[i].mTimeEnd)
            {
                if(mtvprogram != null && amtvreservation[i].mUriId == mtvprogram.mUriId)
                    flag = true;
            } else
            {
                if(mtvprogram.mTimeStart >= amtvreservation[i].mTimeEnd || mtvprogram != null && amtvreservation[i].mUriId == mtvprogram.mUriId)
                    continue;
                if(mtvprogram.mTimeEnd <= amtvreservation[i].mTimeStart)
                    flag = true;
            }
            continue; /* Loop/switch isn't completed */
        }

        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void mStartNewChannel(MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->mStartNewChannel(Lcom/samsung/sec/mtv/provider/MtvChannel;)V");
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
        IMtvAppPlayerOneSeg imtvappplayeroneseg = MtvAppPlayerOneSeg.getInstance();
        if(mtvappplaybackcontext != null && imtvappplayeroneseg != null)
        {
            mMtvAppPlaybackContext = mtvappplaybackcontext;
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            if(mtvchannel != null)
            {
                MtvURI mtvuri = new MtvURI(2, mtvchannel.mPhysicalNum);
                MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("startTvAfterScan  which URI =").append(mtvuri).toString());
                imtvappplayeroneseg.open(mMtvAppPlaybackContext, mtvuri);
            }
        }
    }

    private boolean processItemClick(MtvProgram mtvprogram, View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->processItemClick(Lcom/samsung/sec/mtv/provider/MtvProgram;Landroid/view/View;)Z");
        MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("pgm :").append(mtvprogram).toString());
        if(!isReservationAlreadyTime(mtvprogram))
        {
            showAlreadyReserveDlg();
        } else
        {
            String s = ((TextView)view.findViewById(0x7f0a0054)).getText().toString();
            String s1 = (new StringBuilder()).append(s).append(" ").append(((TextView)view.findViewById(0x7f0a0055)).getText()).toString();
            int i = 0;
            MtvReservation mtvreservation = MtvReservationManager.find(getApplicationContext(), mtvprogram.mTimeStart, new boolean[0]);
            if(mtvreservation != null && mtvreservation.mPgmName.equals(mtvprogram.mPgmName))
            {
                if(mtvreservation != null && mtvreservation.mPgmType == 0)
                    i = 0x7f050012;
                else
                if(mtvreservation != null && mtvreservation.mPgmType == 1)
                    i = 0x7f050013;
            } else
            {
                i = 0x7f050011;
            }
            if(mtvprogram.mTimeStart >= System.currentTimeMillis())
            {
                reserveContextMenuDialog(s1, i, mtvprogram);
                mProgramAdapter.notifyDataSetChanged();
            }
        }
        return false;
    }

    private void setListViewItemByPCh(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->setListViewItemByPCh(I)V");
        MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("nPCh ").append(i).toString());
        MtvProgram amtvprogram[] = MtvProgramManager.findProgramsByPChannel(getApplicationContext(), i);
        if(amtvprogram != null)
        {
            for(int j = 0; j < amtvprogram.length; j++)
                MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("mtvPrograms ").append(amtvprogram[j].mPgmName).toString());

        }
        ListView listview = (ListView)findViewById(0x7f0a0005);
        mProgramAdapter = new ProgramAdapter(this, amtvprogram);
        listview.setAdapter(mProgramAdapter);
        listview.setEmptyView(findViewById(0x7f0a0006));
        if(getIntent().getBooleanExtra("view_program_details", false))
        {
            listview.setOnItemClickListener(mScheduleListListener);
            listview.setOnItemLongClickListener(mScheduleListLongListener);
        } else
        {
            listview.setOnItemClickListener(mScheduleListClickListener);
        }
    }

    private void setSpinnerButtonText(CharSequence charsequence, MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->setSpinnerButtonText(Ljava/lang/CharSequence;Lcom/samsung/sec/mtv/provider/MtvChannel;)V");
        mSpinnerButton.setText(charsequence);
    }

    private void showChannelList()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->showChannelList()V");
        final MtvChannel channels[] = MtvChannelManager.getAllAvailableChannels(this);
        int i = 0;
        CharSequence acharsequence[] = new CharSequence[channels.length];
        for(int j = 0; j < channels.length; j++)
        {
            acharsequence[j] = getSpinnerTitle(channels[j]);
            if(acharsequence[j].equals(mSpinnerButton.getText()))
                i = j;
        }

        if(showChannelListAlertDialog != null && showChannelListAlertDialog.isShowing())
            showChannelListAlertDialog.dismiss();
        showChannelListAlertDialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702af).setSingleChoiceItems(acharsequence, i, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$4;->onClick(Landroid/content/DialogInterface;I)V");
                MtvUtilDebug.Mid("MtvUiChannelSchedule", (new StringBuilder()).append("showChannelList which").append(k).toString());
                MtvChannel mtvchannel = channels[k];
                MtvUtilDebug.Mid("MtvUiChannelSchedule", (new StringBuilder()).append("channel ").append(mtvchannel.mPhysicalNum).toString());
                MtvProgramManager.delete(MtvUiChannelSchedule.this, null);
                Log.d(MtvUiChannelSchedule.this).setLatestPChannel(mtvchannel.mPhysicalNum);
                Log.d(MtvUiChannelSchedule.this).setLatestVChannel(mtvchannel.mVirtualNum);
                Log.d(MtvUiChannelSchedule.this, mtvchannel);
                Log.d(MtvUiChannelSchedule.this, Log.d(MtvUiChannelSchedule.this, mtvchannel), mtvchannel);
                Log.d(MtvUiChannelSchedule.this, mtvchannel.mPhysicalNum);
                dialoginterface.dismiss();
            }

            final MtvUiChannelSchedule this$0;
            final MtvChannel val$channels[];

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$4;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;[Lcom/samsung/sec/mtv/provider/MtvChannel;)V");
                this$0 = MtvUiChannelSchedule.this;
                channels = amtvchannel;
                super();
            }
        }).setNegativeButton(0x7f070035, null).show();
        showChannelListAlertDialog.getWindow().addFlags(1024);
    }

    public void CancelReservationDialog(final long mStartTime)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->CancelReservationDialog(J)V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702be).setMessage(0x7f0702b4).setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$9;->onClick(Landroid/content/DialogInterface;I)V");
                MtvReservationManager.delete(getApplicationContext(), mStartTime, null);
                Log.d(MtvUiChannelSchedule.this).notifyDataSetChanged();
            }

            final MtvUiChannelSchedule this$0;
            final long val$mStartTime;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$9;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;J)V");
                this$0 = MtvUiChannelSchedule.this;
                mStartTime = l;
                super();
            }
        }).setNegativeButton(0x7f070035, null).show().getWindow().addFlags(1024);
        mProgramAdapter.notifyDataSetChanged();
    }

    public void ReserveProgramStarted(String s, MtvProgram mtvprogram)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->ReserveProgramStarted(Ljava/lang/String;Lcom/samsung/sec/mtv/provider/MtvProgram;)V");
        if(!isReservationAlreadyTime(mtvprogram))
        {
            showAlreadyReserveDlg();
        } else
        {
            MtvReservation mtvreservation = new MtvReservation(new MtvProgram(mtvprogram.mPch, mtvprogram.mVch, mtvprogram.mLock, 2000L + System.currentTimeMillis(), mtvprogram.mTimeEnd, mtvprogram.mPgmName, mtvprogram.mPgmDetail), 0, 0);
            MtvReservationManager.updateOrInsert(getApplicationContext(), mtvreservation);
            MtvUtilSetReservationAlarm.setReservationAlarm(getApplicationContext(), mtvreservation.mTimeStart, true, false);
        }
    }

    public int checkForReservationType(MtvProgram mtvprogram)
    {
        byte byte0;
        MtvReservation mtvreservation;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->checkForReservationType(Lcom/samsung/sec/mtv/provider/MtvProgram;)I");
        byte0 = 0;
        mtvreservation = MtvReservationManager.find(getApplicationContext(), mtvprogram.mTimeStart, new boolean[0]);
        if(mtvreservation == null || !mtvreservation.mPgmName.equals(mtvprogram.mPgmName)) goto _L2; else goto _L1
_L1:
        if(mtvreservation == null || mtvreservation.mPgmType != 0) goto _L4; else goto _L3
_L3:
        byte0 = 2;
_L6:
        return byte0;
_L4:
        if(mtvreservation != null && mtvreservation.mPgmType == 1)
            byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L2:
        byte0 = 0;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public MtvOneSegProgram[] getProgram()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->getProgram()[Landroid/broadcast/helper/types/MtvOneSegProgram;");
        MtvOneSegProgram amtvonesegprogram[];
        if(mMtvAppPlaybackContext != null)
            amtvonesegprogram = mMtvAppPlaybackContext.getAttribute().getProgram();
        else
            amtvonesegprogram = null;
        return amtvonesegprogram;
    }

    public void onBackPressed()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onBackPressed()V");
        if(!MtvUiFragHandler.removeUnManagedFrag("ProgInfoFrag", this))
            finish();
    }

    public void onClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onClick(Landroid/view/View;)V");
        view.getId();
        JVM INSTR tableswitch 2131361794 2131361794: default 32
    //                   2131361794 33;
           goto _L1 _L2
_L1:
        return;
_L2:
        showChannelList();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext != null)
        {
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            mMtvAppPlaybackContext.getState().registerListener(this);
        }
        requestWindowFeature(1);
        setContentView(0x7f030000);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        getWindow().addFlags(1152);
        String s;
        ListView listview;
        MtvProgram amtvprogram[];
        if(getResources().getConfiguration().orientation == 1)
            getWindow().clearFlags(1024);
        else
            getWindow().addFlags(1024);
        mPreferences = new MtvPreferences(getApplicationContext());
        mIcon[1] = getResources().getDrawable(0x7f0200c0);
        mIcon[2] = getResources().getDrawable(0x7f0200bd);
        if(mPreferences.getLatestPChannelFromVChannel() != -1)
        {
            String s1 = (new StringBuilder()).append("CH ").append(mPreferences.getLatestPChannelFromVChannel()).toString();
            MtvChannel mtvchannel = MtvChannelManager.findByPChannel(getApplicationContext(), mPreferences.getLatestPChannelFromVChannel());
            if(mtvchannel != null && mtvchannel.mChannelName != null)
                s1 = (new StringBuilder()).append("CH ").append(mPreferences.getLatestPChannelFromVChannel()).append(" ").append(mtvchannel.mChannelName).toString();
            s = s1;
        } else
        {
            s = getResources().getString(0x7f0700dc);
        }
        mSpinnerButton = (Button)findViewById(0x7f0a0002);
        if(MtvChannelManager.getAllAvailableChannels(this).length < 1)
            mSpinnerButton.setEnabled(false);
        else
            mSpinnerButton.setEnabled(true);
        mSpinnerButton.setText(s);
        listview = (ListView)findViewById(0x7f0a0005);
        amtvprogram = MtvProgramManager.findProgramsByPChannel(getApplicationContext(), mPreferences.getLatestPChannelFromVChannel());
        MtvUtilDebug.Low("MtvUiChannelSchedule", (new StringBuilder()).append("mtvPrograms  :").append(amtvprogram).toString());
        mProgramAdapter = new ProgramAdapter(getApplicationContext(), amtvprogram);
        listview.setAdapter(mProgramAdapter);
        listview.setEmptyView(findViewById(0x7f0a0006));
        if(getIntent().getBooleanExtra("view_program_details", false))
        {
            listview.setOnItemClickListener(mScheduleListListener);
            listview.setOnItemLongClickListener(mScheduleListLongListener);
        } else
        {
            listview.setOnItemClickListener(mScheduleListClickListener);
        }
        mSpinnerButton.setOnClickListener(this);
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onDestroy()V");
        super.onDestroy();
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        mMtvPreferences = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onOptionsItemSelected(Landroid/view/MenuItem;)Z");
        menuitem.getItemId();
        JVM INSTR tableswitch 0 0: default 32
    //                   0 34;
           goto _L1 _L2
_L1:
        return true;
_L2:
        if(getFragmentManager().findFragmentByTag("ProgInfoFrag") == null)
            (new MtvUiMemInfoFrag()).show(getFragmentManager(), "MtvUiMemInfoFrag");
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onPlayerNotification(int i, int j, int k)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onPlayerNotification(III)V");
        switch(i)
        {
        case 20494: 
            MtvUtilDebug.Low("MtvUiChannelSchedule", "onPlayerNotification CMD_CLOSE");
            switch(j)
            {
            case 24581: 
                if(mMtvAppPlaybackContext != null)
                    mMtvAppPlaybackContext.getAttribute().registerListener(this);
                break;
            }
            break;

        case 20484: 
            MtvUtilDebug.Low("MtvUiChannelSchedule", "onPlayerNotification CMD_PLAY");
            switch(j)
            {
            default:
                break;

            case 24581: 
                MtvUtilAudioManager.getInstance(getApplicationContext()).updateCurrentAudioEffectAndMode();
                break;

            case 24593: 
                MtvUtilDebug.Error("MtvUiChannelSchedule", "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
                if(!MtvUtilDebug.isReleaseMode())
                {
                    Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
                    toast.setGravity(119, 0, 0);
                    toast.show();
                }
                sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"));
                break;
            }
            break;
        }
        while(true) 
            return;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onPrepareOptionsMenu(Landroid/view/Menu;)Z");
        boolean flag = false;
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        if(getFragmentManager().findFragmentByTag("ProgInfoFrag") == null)
        {
            menu.add(0, 0, 0, 0x7f07028b).setIcon(0x7f020101);
            flag = true;
        }
        return flag;
    }

    public void onProgramAttributeUpdated(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onProgramAttributeUpdated(I)V");
        i;
        JVM INSTR tableswitch 2 2: default 28
    //                   2 29;
           goto _L1 _L2
_L1:
        return;
_L2:
        MtvUtilDebug.Low("MtvUiChannelSchedule", "ATTRIB_PROGRAM");
        mChannelScheduleUiMsgHandler.sendMessage(mChannelScheduleUiMsgHandler.obtainMessage(320));
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onResume()V");
        super.onResume();
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(mPreferences == null)
            mPreferences = new MtvPreferences(getApplicationContext());
        if(MtvUtilAppService.isAppExiting())
        {
            finish();
        } else
        {
            android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
            mMtvPreferences = new MtvPreferences(getApplicationContext());
            layoutparams.screenBrightness = mMtvPreferences.getBrightnessValue();
            getWindow().setAttributes(layoutparams);
        }
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->onStateChanged(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;)V");
    }

    public void reserveContextMenuDialog(String s, int i, final MtvProgram program)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->reserveContextMenuDialog(Ljava/lang/String;ILcom/samsung/sec/mtv/provider/MtvProgram;)V");
        (new android.app.AlertDialog.Builder(this)).setTitle(s).setItems(i, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$8;->onClick(Landroid/content/DialogInterface;I)V");
                j;
                JVM INSTR tableswitch 0 1: default 32
            //                           0 33
            //                           1 180;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                MtvReservation mtvreservation1;
                if(checkForReservationType(program) == 2)
                {
                    mtvreservation1 = new MtvReservation(program, 1, 0);
                    MtvReservationManager.updateOrInsert(getApplicationContext(), mtvreservation1);
                } else
                if(checkForReservationType(program) == 1)
                {
                    mtvreservation1 = new MtvReservation(program, 0, 0);
                    MtvReservationManager.updateOrInsert(getApplicationContext(), mtvreservation1);
                } else
                {
                    mtvreservation1 = new MtvReservation(program, 0, 0);
                    MtvReservationManager.updateOrInsert(getApplicationContext(), mtvreservation1);
                }
                MtvUtilSetReservationAlarm.setReservationAlarm(getApplicationContext(), ((MtvProgram) (mtvreservation1)).mTimeStart, true, false);
                Log.d(MtvUiChannelSchedule.this).notifyDataSetChanged();
                continue; /* Loop/switch isn't completed */
_L3:
                class _cls1
                    implements android.content.DialogInterface.OnClickListener
                {

                    public void onClick(DialogInterface dialoginterface1, int k)
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$8$1;->onClick(Landroid/content/DialogInterface;I)V");
                        MtvReservationManager.delete(getApplicationContext(), program.mTimeStart, null);
                        MtvUtilSetReservationAlarm.setReservationAlarm(getApplicationContext(), program.mTimeStart, false, true);
                        Log.d(_fld0).notifyDataSetChanged();
                    }

                    final _cls8 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$8$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$8;)V");
                            this$1 = _cls8.this;
                            super();
                        }
                }

                if(checkForReservationType(program) == 0)
                {
                    MtvReservation mtvreservation = new MtvReservation(program, 1, 0);
                    MtvReservationManager.updateOrInsert(getApplicationContext(), mtvreservation);
                    MtvUtilSetReservationAlarm.setReservationAlarm(getApplicationContext(), ((MtvProgram) (mtvreservation)).mTimeStart, true, false);
                    Log.d(MtvUiChannelSchedule.this).notifyDataSetChanged();
                } else
                {
                    (new android.app.AlertDialog.Builder(MtvUiChannelSchedule.this)).setTitle(0x7f0702be).setMessage(0x7f0702b4).setPositiveButton(0x7f070034, new _cls1()).setNegativeButton(0x7f070035, null).show().getWindow().addFlags(1024);
                }
                Log.d(MtvUiChannelSchedule.this).notifyDataSetChanged();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvUiChannelSchedule this$0;
            final MtvProgram val$program;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$8;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/provider/MtvProgram;)V");
                this$0 = MtvUiChannelSchedule.this;
                program = mtvprogram;
                super();
            }
        }).show().getWindow().addFlags(1024);
        mProgramAdapter.notifyDataSetChanged();
    }

    public void showAlreadyReserveDlg()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->showAlreadyReserveDlg()V");
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0702ab).setMessage(0x7f0702b2).setPositiveButton(0x7f070034, null).show().getWindow().addFlags(1024);
    }

    private final String TAG = "MtvUiChannelSchedule";
    private onMtvAppAndroidServiceListener listener;
    private Handler mChannelScheduleUiMsgHandler;
    private ServiceConnection mConnection;
    private final Drawable mIcon[] = new Drawable[3];
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvPreferences mMtvPreferences;
    private MtvPreferences mPreferences;
    private ProgramAdapter mProgramAdapter;
    android.widget.AdapterView.OnItemClickListener mScheduleListClickListener;
    android.widget.AdapterView.OnItemClickListener mScheduleListListener;
    android.widget.AdapterView.OnItemLongClickListener mScheduleListLongListener;
    private MtvAppAndroidService mService;
    private Button mSpinnerButton;
    private AlertDialog showChannelListAlertDialog;


/*
    static MtvPreferences access$000(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuichannelschedule.mPreferences;
    }

*/


/*
    static MtvPreferences access$002(MtvUiChannelSchedule mtvuichannelschedule, MtvPreferences mtvpreferences)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$002(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/utility/MtvPreferences;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        mtvuichannelschedule.mPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/


/*
    static void access$100(MtvUiChannelSchedule mtvuichannelschedule, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$100(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;I)V");
        mtvuichannelschedule.setListViewItemByPCh(i);
        return;
    }

*/


/*
    static boolean access$1000(MtvUiChannelSchedule mtvuichannelschedule, MtvProgram mtvprogram, View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$1000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/provider/MtvProgram;Landroid/view/View;)Z");
        return mtvuichannelschedule.processItemClick(mtvprogram, view);
    }

*/


/*
    static Handler access$1100(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$1100(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Landroid/os/Handler;");
        return mtvuichannelschedule.mChannelScheduleUiMsgHandler;
    }

*/


/*
    static MtvAppAndroidService access$200(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$200(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuichannelschedule.mService;
    }

*/


/*
    static MtvAppAndroidService access$202(MtvUiChannelSchedule mtvuichannelschedule, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$202(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuichannelschedule.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$300(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$300(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuichannelschedule.listener;
    }

*/


/*
    static void access$400(MtvUiChannelSchedule mtvuichannelschedule, MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$400(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/provider/MtvChannel;)V");
        mtvuichannelschedule.mStartNewChannel(mtvchannel);
        return;
    }

*/


/*
    static CharSequence access$500(MtvUiChannelSchedule mtvuichannelschedule, MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$500(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/provider/MtvChannel;)Ljava/lang/CharSequence;");
        return mtvuichannelschedule.getSpinnerTitle(mtvchannel);
    }

*/


/*
    static void access$600(MtvUiChannelSchedule mtvuichannelschedule, CharSequence charsequence, MtvChannel mtvchannel)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$600(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Ljava/lang/CharSequence;Lcom/samsung/sec/mtv/provider/MtvChannel;)V");
        mtvuichannelschedule.setSpinnerButtonText(charsequence, mtvchannel);
        return;
    }

*/


/*
    static ProgramAdapter access$700(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$700(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule$ProgramAdapter;");
        return mtvuichannelschedule.mProgramAdapter;
    }

*/


/*
    static Drawable[] access$800(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$800(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)[Landroid/graphics/drawable/Drawable;");
        return mtvuichannelschedule.mIcon;
    }

*/


/*
    static MtvAppPlaybackContext access$900(MtvUiChannelSchedule mtvuichannelschedule)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$900(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvuichannelschedule.mMtvAppPlaybackContext;
    }

*/


/*
    static MtvAppPlaybackContext access$902(MtvUiChannelSchedule mtvuichannelschedule, MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;->access$902(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        mtvuichannelschedule.mMtvAppPlaybackContext = mtvappplaybackcontext;
        return mtvappplaybackcontext;
    }

*/
}
