// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.IMtvOneSegVideoControl;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegChannel;
import android.broadcast.helper.types.MtvOneSegProgram;
import android.content.*;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.common.MtvUiDialogsFrag;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.utility.*;
import com.sec.android.touchwiz.widget.TwProgressBar;
import java.util.Date;

// Referenced classes of package com.samsung.sec.mtv.ui.channelguide:
//            MtvUiChannelSchedule, MtvUiSetArea, MtvUiChannelGuide

public class MtvUiFragChannelList extends MtvUiFrag
    implements android.app.LoaderManager.LoaderCallbacks, android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
{
    private class ChannelAdapter extends CursorAdapter
    {

        public void bindView(View view, Context context, Cursor cursor)
        {
            MtvChannel mtvchannel = MtvChannelManager.builder(cursor);
            view.setTag(mtvchannel);
            MtvUtilDebug.Low(MtvUiFragChannelList.TAG, (new StringBuilder()).append("bindView channel=").append(mtvchannel.toString()).toString());
            String s = null;
            TextView textview;
            TextView textview1;
            int i;
            if(mtvchannel.mVirtualNum < 10)
                s = Integer.toString(mtvchannel.mVirtualNum);
            else
            if(mtvchannel.mVirtualNum == 10)
                s = "0";
            else
            if(mtvchannel.mVirtualNum == 11)
                s = "*";
            else
            if(mtvchannel.mVirtualNum == 12)
                s = "#";
            textview = (TextView)view.findViewById(0x7f0a004e);
            textview1 = (TextView)view.findViewById(0x7f0a004f);
            textview.setText(s);
            textview1.setText(mtvchannel.mChannelName);
            i = mMtvPreferences.getLatestVChannel();
            if(i != -1 && mtvchannel.mVirtualNum == i)
            {
                MtvUtilDebug.Mid(MtvUiFragChannelList.TAG, (new StringBuilder()).append("current: vch=").append(i).append(", select: pch=").append(mtvchannel.mPhysicalNum).append(" vch=").append(mtvchannel.mVirtualNum).toString());
                view.findViewById(0x7f0a004d).setBackgroundDrawable(mContext.getResources().getDrawable(0x7f020170));
            } else
            {
                view.findViewById(0x7f0a004d).setBackgroundDrawable(mContext.getResources().getDrawable(0x7f02002f));
            }
            if(mtvchannel.mPhysicalNum < 1)
            {
                view.findViewById(0x7f0a004d).setBackgroundDrawable(mContext.getResources().getDrawable(0x7f02016c));
                view.findViewById(0x7f0a004d).getBackground().setAlpha(127);
                textview1.setTextColor(Color.parseColor("#5b6872"));
                textview.setTextColor(Color.parseColor("#5b6872"));
            } else
            if(mtvchannel.mAvailable == 1 && !MtvUtilDebug.isReleaseMode())
            {
                textview.setTextColor(-256);
                textview1.setTextColor(-256);
            } else
            {
                textview.setTextColor(-1);
                textview1.setTextColor(-1);
            }
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1;
            if(i == 9)
                view1 = super.getView(10, view, viewgroup);
            else
            if(i == 10)
                view1 = super.getView(9, view, viewgroup);
            else
                view1 = super.getView(i, view, viewgroup);
            return view1;
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
        {
            return ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030010, viewgroup, false);
        }

        private final Context mContext;
        final MtvUiFragChannelList this$0;

        public ChannelAdapter(Context context, Cursor cursor)
        {
            this$0 = MtvUiFragChannelList.this;
            super(context, cursor);
            mContext = context;
        }
    }

    public static class ChannelListDialogFragment extends DialogFragment
    {

        private Dialog createDialog(int i)
        {
            Object obj = null;
            i;
            JVM INSTR tableswitch 0 1: default 24
        //                       0 26
        //                       1 72;
               goto _L1 _L2 _L3
_L1:
            return ((Dialog) (obj));
_L2:
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    j;
                    JVM INSTR tableswitch 0 0: default 20
                //                               0 21;
                       goto _L1 _L2
_L1:
                    return;
_L2:
                    ChannelListDialogFragment.removeDialog(getFragmentManager(), "ChannelListDialogFragment");
                    ChannelListDialogFragment.newInstance(1).show(getFragmentManager(), "ChannelListDialogFragment");
                    if(true) goto _L1; else goto _L3
_L3:
                }

                final ChannelListDialogFragment this$0;

                _cls1()
                {
                    this$0 = ChannelListDialogFragment.this;
                    super();
                }
            }

            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07029a).setItems(0x7f05000f, new _cls1()).create();
            ((AlertDialog) (obj)).getWindow().addFlags(1024);
            continue; /* Loop/switch isn't completed */
_L3:
            class _cls2
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    MtvPreferences mtvpreferences;
                    android.net.Uri uri = MtvChannelManager.getUri(MtvUiFragChannelList.sSelectedChannel.mUriId);
                    MtvChannelManager.update2Default(getActivity().getApplicationContext(), uri);
                    MtvProgramManager.deletePChannelPrograms(getActivity().getApplicationContext(), MtvUiFragChannelList.sSelectedChannel.mPhysicalNum);
                    mtvpreferences = new MtvPreferences(getActivity().getApplicationContext());
                    if(MtvUiFragChannelList.sSelectedChannel.mVirtualNum != mtvpreferences.getLatestVChannel()) goto _L2; else goto _L1
_L1:
                    MtvChannel mtvchannel;
                    MtvUiFrag mtvuifrag;
                    mtvpreferences.setLatestVChannel(-1);
                    mtvpreferences.setLatestPChannel(-1);
                    mtvchannel = MtvChannelManager.getFirstOnAir(getActivity().getApplicationContext());
                    mtvuifrag = (MtvUiFrag)getFragmentManager().findFragmentByTag("channelguidelist");
                    if(mtvchannel == null) goto _L4; else goto _L3
_L3:
                    mtvpreferences.setLatestVChannel(mtvchannel.mVirtualNum);
                    mtvpreferences.setLatestPChannel(mtvchannel.mPhysicalNum);
                    if(mtvuifrag != null)
                        mtvuifrag.onUpdate(210, Integer.valueOf(mtvchannel.mPhysicalNum));
_L2:
                    return;
_L4:
                    Toast.makeText(getActivity(), 0x7f07028e, 500).show();
                    mtvpreferences.setLatestPChannel(13);
                    if(mtvuifrag != null)
                        mtvuifrag.onUpdate(210, Integer.valueOf(13));
                    if(true) goto _L2; else goto _L5
_L5:
                }

                final ChannelListDialogFragment this$0;

                _cls2()
                {
                    this$0 = ChannelListDialogFragment.this;
                    super();
                }
            }

            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028d).setMessage(0x7f07029b).setPositiveButton(0x7f070034, new _cls2()).create();
            ((AlertDialog) (obj)).getWindow().addFlags(1024);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public static ChannelListDialogFragment newInstance(int i)
        {
            com/samsung/sec/mtv/ui/channelguide/MtvUiFragChannelList$ChannelListDialogFragment;
            JVM INSTR monitorenter ;
            ChannelListDialogFragment channellistdialogfragment;
            channellistdialogfragment = new ChannelListDialogFragment();
            channellistdialogfragment.dialogId = i;
            com/samsung/sec/mtv/ui/channelguide/MtvUiFragChannelList$ChannelListDialogFragment;
            JVM INSTR monitorexit ;
            return channellistdialogfragment;
            Exception exception;
            exception;
            throw exception;
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

        public Dialog onCreateDialog(Bundle bundle)
        {
            if(bundle != null)
                dialogId = bundle.getInt("dialogId");
            return createDialog(dialogId);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("dialogId", dialogId);
        }

        private int dialogId;

        public ChannelListDialogFragment()
        {
        }
    }

    class ControlChGuideLoadingAnimationRunnable
        implements Runnable
    {

        private void controlAnimation()
        {
            mAnimationDrawable = (AnimationDrawable)mLoadingImageView.getDrawable();
            MtvUtilDebug.Low(MtvUiFragChannelList.TAG, (new StringBuilder()).append("controlAnimation: called :").append(isEnable).toString());
            if(isEnable)
            {
                mLoadingImageView.invalidate();
                mLoadingImageView.setVisibility(0);
                mTxtAnimation.setVisibility(0);
                if(!mAnimationDrawable.isRunning())
                    mAnimationDrawable.start();
                else
                    mAnimationDrawable.start();
                if(mStrAnimation != null)
                    mTxtAnimation.setText(mStrAnimation);
                else
                    mTxtAnimation.setText("");
            } else
            {
                if(mAnimationDrawable.isRunning())
                    mAnimationDrawable.stop();
                mLoadingImageView.setVisibility(4);
                mTxtAnimation.setText("");
                mTxtAnimation.setVisibility(4);
            }
        }

        public void postAnimationToRunInUIThread()
        {
            mLoadingImageView.removeCallbacks(this);
            mLoadingImageView.post(this);
        }

        public void run()
        {
            controlAnimation();
        }

        public void setAnimationEnable(boolean flag)
        {
            MtvUtilDebug.Low(MtvUiFragChannelList.TAG, (new StringBuilder()).append("ControlAnimationRunnable: setAnimationEnable :").append(flag).toString());
            isEnable = flag;
        }

        protected void setAnimationText(String s)
        {
            MtvUtilDebug.Low(MtvUiFragChannelList.TAG, (new StringBuilder()).append("ControlAnimationRunnable: setAnimationText :").append(s).toString());
            mStrAnimation = s;
        }

        private boolean isEnable;
        private AnimationDrawable mAnimationDrawable;
        private String mStrAnimation;
        final MtvUiFragChannelList this$0;

        public ControlChGuideLoadingAnimationRunnable()
        {
            this$0 = MtvUiFragChannelList.this;
            super();
            isEnable = false;
            MtvUtilDebug.Low(MtvUiFragChannelList.TAG, "ControlChGuideLoadingAnimationRunnable...");
        }
    }


    public MtvUiFragChannelList()
    {
        mStackLevel = 1;
        mMtvAppPlaybackContext = null;
        mSurfaceView = null;
        mLoadingImageView = null;
        mTxtAnimation = null;
        mhidden_surfaceview = null;
        mChannelNameText = null;
        mProgNameText = null;
        mGridView = null;
        mSlotID = -1;
        mSlotMap = null;
        mScanProgressDialog = null;
        mScanProgressBar = null;
        mScanProgressTitle = null;
        mScanProgressInfo = null;
        bScanProgress = false;
        mChangeAreaCompleteDialog = null;
    }

    private void StartScanProgessBar(Context context)
    {
        View view = LayoutInflater.from(context).inflate(0x7f030012, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        MtvUtilDebug.Low(TAG, "StartScanProgessBar: called...");
        builder.setIcon(0x7f02008e).setNegativeButton(0x7f070035, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                MtvUtilDebug.Low(MtvUiFragChannelList.TAG, "StartScanProgessBar: Cancel pressed issuing cancel scan");
                cancelScanChannel();
            }

            final MtvUiFragChannelList this$0;

            
            {
                this$0 = MtvUiFragChannelList.this;
                super();
            }
        }).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
            {
                boolean flag;
                if(j == 82)
                {
                    MtvUtilDebug.Low(MtvUiFragChannelList.TAG, "StartScanProgessBar:onKey KEYCODE_MENU is ignore");
                    flag = true;
                } else
                {
                    flag = false;
                }
                return flag;
            }

            final MtvUiFragChannelList this$0;

            
            {
                this$0 = MtvUiFragChannelList.this;
                super();
            }
        }).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                MtvUtilDebug.Low(MtvUiFragChannelList.TAG, "StartScanProgessBar onCancel: Progress Dialog cancelled");
                cancelScanChannel();
            }

            final MtvUiFragChannelList this$0;

            
            {
                this$0 = MtvUiFragChannelList.this;
                super();
            }
        });
        mScanProgressTitle = (TextView)view.findViewById(0x7f0a0057);
        mScanProgressInfo = (TextView)view.findViewById(0x7f0a005a);
        mScanProgressBar = (TwProgressBar)view.findViewById(0x7f0a0059);
        int i;
        MtvAppPlaybackContext mtvappplaybackcontext;
        if(mScanType == 1)
            mScanProgressTitle.setText(getString(0x7f070298));
        else
        if(mScanType == 2)
            mScanProgressTitle.setText(getString(0x7f070299));
        mScanProgressBar.setProgressDrawable(0x7f02010e);
        mScanProgressBar.setBackgroundDrawable(0x7f02010f);
        mScanProgressBar.setMax(100);
        i = getScanProgessPercentage();
        mScanProgressInfo.setText((new StringBuilder()).append(Integer.toString(i)).append(" %").toString());
        mScanProgressBar.setProgress(i);
        builder.setView(view);
        mScanProgressDialog = builder.create();
        mScanProgressDialog.show();
        mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mtvappplaybackcontext != null && mtvappplaybackcontext.getState().getOp() == 20485)
        {
            mScanProgressDialog.setCancelable(true);
            mScanProgressDialog.getButton(-2).setEnabled(true);
        } else
        {
            mScanProgressDialog.setCancelable(false);
            mScanProgressDialog.getButton(-2).setEnabled(false);
        }
        mScanProgressDialog.getWindow().addFlags(1024);
    }

    private void UpdateScanProgessBar()
    {
        if(mScanProgressBar != null)
        {
            int i = getScanProgessPercentage();
            mScanProgressBar.setProgress(i);
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("pg*** UpdateScanProgessBar- scanPercentage:").append(i).toString());
            mScanProgressInfo.setText((new StringBuilder()).append(Integer.toString(i)).append(" %").toString());
        }
    }

    private void cancelScanChannel()
    {
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        IMtvAppPlayerOneSeg imtvappplayeroneseg = MtvAppPlayerOneSeg.getInstance();
        if(mtvappplaybackcontext != null && imtvappplayeroneseg != null)
        {
            MtvUtilDebug.Low(TAG, "cancelScanChannel called");
            if(imtvappplayeroneseg.cancelScanChannels(mtvappplaybackcontext))
            {
                if(mScanProgressDialog != null)
                {
                    mScanProgressDialog.dismiss();
                    mScanProgressDialog = null;
                }
            } else
            {
                MtvUtilDebug.High(TAG, "cancelScanChannel : Cancel Update/Search failed ");
            }
        } else
        {
            MtvUtilDebug.Error(TAG, "cancelScanChannel : Cancel Update/Search failed ");
        }
    }

    private void changeArea(int i)
    {
        mMtvPreferences.setCurrentSlot(i);
        MtvProgramManager.delete(getActivity(), null);
        getLoaderManager().restartLoader(1, null, this);
        mGridView.setAdapter(mListAdapter);
    }

    private void changeAreaNStartTV(int i)
    {
        mMtvPreferences.setCurrentSlot(i);
        MtvProgramManager.delete(getActivity(), null);
        MtvChannel mtvchannel = MtvChannelManager.getFirstOnAir(getActivity());
        if(mtvchannel != null)
        {
            mMtvPreferences.setLatestPChannel(mtvchannel.mPhysicalNum);
            mMtvPreferences.setLatestVChannel(mtvchannel.mVirtualNum);
        }
        invalidateChannelInfo();
        getLoaderManager().restartLoader(1, null, this);
        mGridView.setAdapter(mListAdapter);
        IMtvAppPlayerOneSeg imtvappplayeroneseg = MtvAppPlayerOneSeg.getInstance();
        if(imtvappplayeroneseg != null && mtvchannel != null)
        {
            MtvURI mtvuri = new MtvURI(2, mtvchannel.mPhysicalNum);
            imtvappplayeroneseg.open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
        }
    }

    private MtvOneSegChannel getChannel()
    {
        MtvOneSegChannel mtvonesegchannel;
        if(mMtvAppPlaybackContext != null)
            mtvonesegchannel = mMtvAppPlaybackContext.getAttribute().getChannel();
        else
            mtvonesegchannel = null;
        return mtvonesegchannel;
    }

    private String getCurrentChannelName()
    {
        MtvOneSegChannel mtvonesegchannel = getChannel();
        String s;
        if(mtvonesegchannel != null && mtvonesegchannel.getServName() != null)
            s = mtvonesegchannel.getServName();
        else
            s = mMtvPreferences.getLatestChannelNameForDisplay(true);
        return s;
    }

    private MtvOneSegProgram getCurrentProgramDetails(MtvOneSegProgram amtvonesegprogram[])
    {
        MtvOneSegProgram mtvonesegprogram = null;
        long l = 0L;
        if(mMtvAppPlaybackContext != null)
            l = mMtvAppPlaybackContext.getAttribute().getTot();
        int i = amtvonesegprogram.length;
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    MtvOneSegProgram mtvonesegprogram1 = amtvonesegprogram[j];
                    if(mtvonesegprogram1 == null || l <= mtvonesegprogram1.getStartTime().getTime() || l >= mtvonesegprogram1.getEndTime().getTime())
                        break label0;
                    mtvonesegprogram = mtvonesegprogram1;
                }
                return mtvonesegprogram;
            }
            j++;
        } while(true);
    }

    private String getCurrentProgramName()
    {
        String s = "";
        MtvOneSegProgram amtvonesegprogram[] = getProgram();
        if(amtvonesegprogram != null)
        {
            MtvOneSegProgram mtvonesegprogram = getCurrentProgramDetails(amtvonesegprogram);
            if(mtvonesegprogram != null)
                s = mtvonesegprogram.getProgName();
        } else
        {
            s = "";
        }
        return s;
    }

    private MtvOneSegProgram[] getProgram()
    {
        MtvOneSegProgram amtvonesegprogram[];
        if(mMtvAppPlaybackContext != null)
            amtvonesegprogram = mMtvAppPlaybackContext.getAttribute().getProgram();
        else
            amtvonesegprogram = null;
        return amtvonesegprogram;
    }

    private int getScanProgessPercentage()
    {
        int i = 0;
        if(mMtvAppPlaybackContext == null || mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER)
        {
            MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            if(mtvappplaybackcontext != null && mtvappplaybackcontext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER)
                mMtvAppPlaybackContext = mtvappplaybackcontext;
        }
        int j;
        if(mMtvAppPlaybackContext != null)
        {
            j = mMtvAppPlaybackContext.getAttribute().getLastScannedChannel();
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("pg*** mMtvAppPlaybackContext  ").append(mMtvAppPlaybackContext.getType()).toString());
        } else
        {
            j = 0;
        }
        if(j > 12 && j < 63)
        {
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("pg*** % ***    ").append(j).toString());
            i = (100 * (j - 12)) / 50;
        }
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("pg*** % ***    ").append(i).append("  ").append(j).toString());
        return i;
    }

    private void initPlayer()
    {
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV)
            mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface().enableVideo();
        registerVideoSurfaceView(true);
        if(MtvAreaManager.getCount(getActivity().getApplicationContext()) > 0)
        {
            if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                showAnimationControl(false, null);
            else
            if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED)
                showAnimationControl(true, getString(0x7f070086));
            else
                showAnimationControl(true, getString(0x7f070087));
            mhidden_surfaceview.setVisibility(8);
            mSurfaceView.setVisibility(0);
            if(mListAdapter != null)
                mListAdapter.notifyDataSetChanged();
            showChannelNameProgramName(getCurrentChannelName(), getCurrentProgramName());
        } else
        {
            MtvUtilDebug.High(TAG, "noAreaSet... HiddenSurfaaceView Visible...");
            showAnimationControl(false, null);
            showChannelNameProgramName("", "");
            mhidden_surfaceview.setVisibility(0);
            mSurfaceView.setVisibility(8);
        }
    }

    private void initView(View view)
    {
        mSurfaceView = (SurfaceView)view.findViewById(0x7f0a008e);
        if(mSurfaceView != null)
            mSurfaceView.setOnClickListener(this);
        mLoadingImageView = (ImageView)view.findViewById(0x7f0a0091);
        mLoadingImageView.setImageDrawable(getResources().getDrawable(0x7f040002));
        mTxtAnimation = (TextView)view.findViewById(0x7f0a0023);
        mControlChGuideLoadingAnimationRunnable = new ControlChGuideLoadingAnimationRunnable();
        mhidden_surfaceview = (ImageView)view.findViewById(0x7f0a008f);
        mhidden_surfaceview.setImageDrawable(getResources().getDrawable(0x7f020099));
        mChannelNameText = (TextView)view.findViewById(0x7f0a0092);
        mProgNameText = (TextView)view.findViewById(0x7f0a0093);
        mCh_guide_schedule = (Button)view.findViewById(0x7f0a0095);
        if(mCh_guide_schedule != null)
            mCh_guide_schedule.setOnClickListener(this);
        mCh_guide_tvview_btn = (Button)view.findViewById(0x7f0a0096);
        if(mCh_guide_tvview_btn != null)
            mCh_guide_tvview_btn.setOnClickListener(this);
        mGridView = (GridView)view.findViewById(0x7f0a0094);
        mGridView.setVisibility(0);
        mGridView.setAdapter(mListAdapter);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener(this);
    }

    private void invalidateChannelInfo()
    {
        if(mChannelNameText != null)
            mChannelNameText.setText("");
        if(mProgNameText != null)
            mProgNameText.setText("");
        if(mSurfaceView != null)
        {
            mSurfaceView.setVisibility(4);
            mSurfaceView.setVisibility(0);
        }
    }

    private void registerVideoSurfaceView(boolean flag)
    {
        if(mMtvAppPlaybackContext != null)
        {
            mMtvAppPlaybackContext.getComponents().getVideo().enable();
            IMtvOneSegVideoControl imtvonesegvideocontrol = mMtvAppPlaybackContext.getComponents().getVideo().getControlInterface();
            if(imtvonesegvideocontrol != null)
                if(flag)
                    imtvonesegvideocontrol.registerSurface(mSurfaceView);
                else
                    imtvonesegvideocontrol.registerSurface(null);
        }
    }

    private void scanChannel()
    {
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getScanner();
        IMtvAppPlayerOneSeg imtvappplayeroneseg = MtvAppPlayerOneSeg.getInstance();
        if(mtvappplaybackcontext != null && imtvappplayeroneseg != null)
        {
            mMtvAppPlaybackContext = mtvappplaybackcontext;
            mMtvAppPlaybackContext.getState().registerListener((IMtvAppPlaybackStateListener)getActivity());
            mMtvAppPlaybackContext.getAttribute().registerListener((IMtvAppProgramAttributeListener)getActivity());
            imtvappplayeroneseg.scanChannels(mtvappplaybackcontext);
            MtvUtilDebug.Low(TAG, "scanChannel :Scan Started ...");
            StartScanProgessBar(getActivity());
        } else
        {
            MtvUtilDebug.Error(TAG, "ScanChannel : Update/Search failed ");
        }
    }

    private void scanChannel(int i, int ai[])
    {
        mMtvPreferences.setCurrentSlot(i);
        mMtvPreferences.setLatestVChannel(-1);
        mMtvPreferences.setLatestPChannel(-1);
        MtvChannelManager.deleteDB(getActivity(), null);
        if(ai != null)
        {
            mScanType = 1;
            String s = MtvAreaStationInfo.AREA_LOCAL[ai[0]][ai[1]][ai[2]];
            MtvAreaManager.update(getActivity(), i, new MtvArea(MtvAreaStationInfo.AREA_LOCAL_ID[ai[0]][ai[1]][ai[2]], s));
            MtvChannelManager.setDefaultAreaNChannel(getActivity(), i, MtvAreaStationInfo.AREA_LOCAL_ID[ai[0]][ai[1]][ai[2]], null);
        } else
        {
            MtvArea mtvarea = MtvAreaManager.find(getActivity(), mMtvPreferences.getCurrentSlot());
            if(mtvarea.mAreaId >= 0)
            {
                mScanType = 2;
                MtvUtilDebug.Low(TAG, (new StringBuilder()).append("update Channel current slotId=").append(i).append(" areaId=").append(mtvarea.mAreaId).append(" areaName=").append(mtvarea.mAreaName).toString());
                MtvChannelManager.setDefaultAreaNChannel(getActivity(), i, mtvarea.mAreaId, mtvarea.mAreaName);
            } else
            {
                MtvUtilDebug.Low(TAG, (new StringBuilder()).append("Scan channel Area is not set =").append(i).append(" areaId=").append(mtvarea.mAreaId).append(" areaName=").append(mtvarea.mAreaName).toString());
            }
        }
        mListAdapter.notifyDataSetChanged();
        scanChannel();
    }

    private void showChannelNameProgramName(String s, String s1)
    {
        if(mChannelNameText != null)
            mChannelNameText.setText(s);
        if(mProgNameText != null)
            mProgNameText.setText(s1);
    }

    private void startTvAfterScan()
    {
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
        MtvUtilAudioManager.getInstance(getActivity()).stopOtherSound();
        IMtvAppPlayerOneSeg imtvappplayeroneseg = MtvAppPlayerOneSeg.getInstance();
        if(mtvappplaybackcontext != null && imtvappplayeroneseg != null)
        {
            mMtvAppPlaybackContext = mtvappplaybackcontext;
            mMtvAppPlaybackContext.getState().registerListener((IMtvAppPlaybackStateListener)getActivity());
            mMtvAppPlaybackContext.getAttribute().registerListener((IMtvAppProgramAttributeListener)getActivity());
            MtvChannel mtvchannel;
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.PLAYING)
                showAnimationControl(false, null);
            else
            if(mMtvAppPlaybackContext.getState().getState() == com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.OPENED)
                showAnimationControl(true, getString(0x7f070086));
            else
                showAnimationControl(true, getString(0x7f070087));
            registerVideoSurfaceView(true);
            mtvchannel = MtvChannelManager.getFirstOnAir(getActivity().getApplicationContext());
            if(mtvchannel != null)
            {
                MtvURI mtvuri = new MtvURI(2, mtvchannel.mPhysicalNum);
                mMtvPreferences.setLatestPChannel(mtvchannel.mPhysicalNum);
                mMtvPreferences.setLatestVChannel(mtvchannel.mVirtualNum);
                mListAdapter.notifyDataSetChanged();
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Low(TAG, (new StringBuilder()).append("startTvAfterScan  which URI =").append(mtvuri).toString());
                imtvappplayeroneseg.open(mMtvAppPlaybackContext, mtvuri);
            }
        } else
        {
            MtvUtilDebug.Error(TAG, "Scan Failed");
        }
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
        JVM INSTR lookupswitch 3: default 40
    //                   2131361934: 99
    //                   2131361941: 41
    //                   2131361942: 99;
           goto _L1 _L2 _L3 _L2
_L1:
        return;
_L3:
        if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
        {
            Intent intent = new Intent(getActivity().getApplicationContext(), com/samsung/sec/mtv/ui/channelguide/MtvUiChannelSchedule);
            intent.putExtra("view_program_details", true);
            startActivity(intent);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        mListener.onFragEvent(0, null);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, "onCreate called...");
        super.onCreate(bundle);
        mMtvPreferences = new MtvPreferences(getActivity());
        if(bundle != null)
        {
            mScanType = bundle.getInt("mScanType", 2);
            bScanProgress = bundle.getBoolean("scanprogress", false);
            mSlotID = bundle.getInt("mSlotID");
            mSlotMap = bundle.getIntArray("mSlotMap");
        }
        mListAdapter = new ChannelAdapter(getActivity(), null);
        getLoaderManager().initLoader(1, null, this);
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(bScanProgress && mtvappplaybackcontext != null && mtvappplaybackcontext.getState().getOp() == 20485)
            StartScanProgessBar(getActivity());
        setHasOptionsMenu(true);
    }

    public Loader onCreateLoader(int i, Bundle bundle)
    {
        String s = (new StringBuilder()).append("ch_slot=").append(mMtvPreferences.getCurrentSlot()).toString();
        return new CursorLoader(getActivity(), MtvChannelManager.CONTENT_URI, null, s, null, null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        MtvUtilDebug.Error(TAG, "onCreateOptionsMenu called... Not doing anything as it can be handled in onPreapareOptionsMenu() !!!");
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, "onCreateView called :");
        View view = layoutinflater.inflate(0x7f03001c, viewgroup, false);
        getLoaderManager().restartLoader(1, null, this);
        initView(view);
        return view;
    }

    public void onDestroy()
    {
        MtvUtilDebug.Low(TAG, "onDestroy called...");
        super.onDestroy();
        mMtvPreferences = null;
    }

    public void onDestroyView()
    {
        MtvUtilDebug.Low(TAG, "onDestroyView called...");
        super.onDestroyView();
        if(mScanProgressDialog != null && mScanProgressDialog.isShowing())
        {
            mScanProgressDialog.dismiss();
            mScanProgressDialog = null;
        }
        getLoaderManager().destroyLoader(1);
        mMtvAppPlaybackContext = null;
        registerVideoSurfaceView(false);
    }

    public void onDetach()
    {
        MtvUtilDebug.Low(TAG, "onDetach called...");
        super.onDetach();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        MtvChannel mtvchannel = (MtvChannel)view.getTag();
        if(mtvchannel != null)
            if(mtvchannel.mPhysicalNum < 1)
                Toast.makeText(getActivity().getApplicationContext(), 0x7f07028e, 0).show();
            else
            if(mtvchannel.mVirtualNum == mMtvPreferences.getLatestVChannel())
            {
                mMtvPreferences.setLatestVChannel(mtvchannel.mVirtualNum);
                mListener.onFragEvent(0, null);
            } else
            {
                mMtvPreferences.setLatestVChannel(mtvchannel.mVirtualNum);
                mMtvPreferences.setLatestPChannel(mtvchannel.mPhysicalNum);
                startTVChannel(mtvchannel.mPhysicalNum);
                mListAdapter.notifyDataSetChanged();
            }
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        Object obj = view.getTag();
        boolean flag;
        if(obj == null)
        {
            flag = false;
        } else
        {
            sSelectedChannel = (MtvChannel)obj;
            if(((MtvChannel)obj).mPhysicalNum < 1)
            {
                Toast.makeText(getActivity().getApplicationContext(), 0x7f07028e, 0).show();
                flag = false;
            } else
            {
                ChannelListDialogFragment.newInstance(0).show(getFragmentManager(), "ChannelListDialogFragment");
                flag = true;
            }
        }
        return flag;
    }

    public void onLoadFinished(Loader loader, Cursor cursor)
    {
        mListAdapter.swapCursor(cursor);
    }

    public volatile void onLoadFinished(Loader loader, Object obj)
    {
        onLoadFinished(loader, (Cursor)obj);
    }

    public void onLoaderReset(Loader loader)
    {
        mListAdapter.swapCursor(null);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 1 4: default 36
    //                   1 38
    //                   2 52
    //                   3 79
    //                   4 94;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        mListener.onFragEvent(1, null);
        continue; /* Loop/switch isn't completed */
_L3:
        Intent intent = new Intent(getActivity(), com/samsung/sec/mtv/ui/channelguide/MtvUiSetArea);
        getActivity().startActivityForResult(intent, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        scanChannel(mMtvPreferences.getCurrentSlot(), null);
        continue; /* Loop/switch isn't completed */
_L5:
        mListener.onFragEvent(2, null);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void onPause()
    {
        super.onPause();
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mtvappplaybackcontext != null && mtvappplaybackcontext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV)
        {
            IMtvOneSegVideoControl imtvonesegvideocontrol = mtvappplaybackcontext.getComponents().getVideo().getControlInterface();
            if(imtvonesegvideocontrol != null)
            {
                imtvonesegvideocontrol.disableVideo();
                imtvonesegvideocontrol.unregisterSurface();
            }
        }
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        MtvUtilDebug.Low(TAG, "onPrepareOptionsMenu called...");
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        menu.clear();
        if(mtvappplaybackcontext == null || mtvappplaybackcontext.getState().getState().ordinal() < com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED.ordinal())
        {
            MtvUtilDebug.High(TAG, "onPrepareOptionsMenu : MW not initialized.. returning without creating options ! ");
        } else
        {
            menu.add(0, 1, 0, 0x7f070284).setIcon(0x7f0200fd);
            menu.add(0, 2, 0, 0x7f070288).setIcon(0x7f020105);
            menu.add(0, 3, 0, 0x7f070286).setIcon(0x7f02010d);
            menu.add(0, 4, 0, 0x7f070285).setIcon(0x7f0200fe);
            if(mtvappplaybackcontext != null && mtvappplaybackcontext.getType() == com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER)
            {
                menu.findItem(3).setEnabled(false);
                MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPrepareOptionsMenu enable udpate").append(mtvappplaybackcontext.getType()).append(" :").append(mtvappplaybackcontext.getState().getOp()).toString());
            }
            super.onPrepareOptionsMenu(menu);
        }
    }

    public void onResume()
    {
        super.onResume();
        initPlayer();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("mScanType", mScanType);
        if(mScanProgressDialog != null)
            bundle.putBoolean("scanprogress", true);
        else
            bundle.putBoolean("scanprogress", false);
        bundle.putInt("mSlotID", mSlotID);
        bundle.putIntArray("mSlotMap", mSlotMap);
        super.onSaveInstanceState(bundle);
    }

    public void onUpdate(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 16: default 140
    //                   111: 280
    //                   112: 415
    //                   113: 332
    //                   115: 541
    //                   116: 230
    //                   117: 588
    //                   118: 320
    //                   119: 216
    //                   120: 202
    //                   121: 160
    //                   122: 181
    //                   123: 145
    //                   124: 635
    //                   125: 678
    //                   126: 141
    //                   210: 721;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
        return;
_L16:
        initPlayer();
_L13:
        showChannelNameProgramName(getCurrentChannelName(), getCurrentProgramName());
        continue; /* Loop/switch isn't completed */
_L11:
        showAnimationControl(((Boolean)obj).booleanValue(), getString(0x7f070086));
        continue; /* Loop/switch isn't completed */
_L12:
        showAnimationControl(((Boolean)obj).booleanValue(), getString(0x7f070087));
        continue; /* Loop/switch isn't completed */
_L10:
        changeAreaNStartTV(((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L9:
        changeArea(((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L6:
        mScanType = 1;
        if(obj != null)
        {
            MtvUiChannelGuide.MtvSearchParam mtvsearchparam = (MtvUiChannelGuide.MtvSearchParam)obj;
            mSlotID = mtvsearchparam.getSlotId();
            mSlotMap = mtvsearchparam.getSlotMap();
            scanChannel(mtvsearchparam.getSlotId(), mtvsearchparam.getSlotMap());
        }
        continue; /* Loop/switch isn't completed */
_L2:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_STARTED");
        if(mScanProgressDialog != null)
        {
            mScanProgressDialog.setCancelable(true);
            mScanProgressDialog.getButton(-2).setEnabled(true);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_CHECK_RESUME");
        continue; /* Loop/switch isn't completed */
_L4:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_CHNL_PROGRESS bScanCancelled");
        if(mScanProgressDialog == null)
        {
            StartScanProgessBar(getActivity());
            if(mScanProgressBar != null)
                mScanProgressBar.setProgress(0);
            MtvUtilDebug.Low(TAG, " onUpdate: :) :) still struggling");
        }
        if(mScanProgressDialog != null)
        {
            mScanProgressDialog.setCancelable(true);
            mScanProgressDialog.getButton(-2).setEnabled(true);
        }
        UpdateScanProgessBar();
        continue; /* Loop/switch isn't completed */
_L3:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_COMPLETED");
        if(mScanType == 1 && isResumed())
        {
            Bundle bundle = new Bundle();
            bundle.putInt("dialogType", 8);
            bundle.putInt("mSlotID", mSlotID);
            bundle.putIntArray("mSlotMap", mSlotMap);
            mChangeAreaCompleteDialog = MtvUiDialogsFrag.newInstance(bundle);
            mChangeAreaCompleteDialog.show(getActivity().getFragmentManager(), "change_area_complete");
        }
        if(mScanProgressDialog != null)
            mScanProgressDialog.dismiss();
        mScanProgressDialog = null;
        mScanType = 0;
        onUpdate(125, null);
        startTvAfterScan();
        continue; /* Loop/switch isn't completed */
_L5:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_CANCEL_COMPLETED");
        if(mScanProgressDialog != null)
            mScanProgressDialog.dismiss();
        mScanProgressDialog = null;
        mScanType = 0;
        onUpdate(125, null);
        startTvAfterScan();
        continue; /* Loop/switch isn't completed */
_L7:
        MtvUtilDebug.Low(TAG, "onUpdate: UPDATE_ANNOUNCE_SCAN_FAILURE");
        if(mScanProgressDialog != null)
            mScanProgressDialog.dismiss();
        mScanProgressDialog = null;
        mScanType = 0;
        onUpdate(125, null);
        startTvAfterScan();
        continue; /* Loop/switch isn't completed */
_L14:
        mCh_guide_schedule.setOnClickListener(null);
        mCh_guide_tvview_btn.setOnClickListener(null);
        mGridView.setOnItemClickListener(null);
        mGridView.setOnItemLongClickListener(null);
        mSurfaceView.setOnClickListener(null);
        continue; /* Loop/switch isn't completed */
_L15:
        mCh_guide_schedule.setOnClickListener(this);
        mCh_guide_tvview_btn.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener(this);
        mSurfaceView.setOnClickListener(this);
        continue; /* Loop/switch isn't completed */
_L17:
        startTVChannel(((Integer)obj).intValue());
        if(true) goto _L1; else goto _L18
_L18:
    }

    public void showAnimationControl(boolean flag, String s)
    {
        MtvUtilDebug.Low(TAG, "showAnimationControl...");
        if(mControlChGuideLoadingAnimationRunnable != null && mhidden_surfaceview.getVisibility() != 0)
        {
            if(flag)
            {
                mControlChGuideLoadingAnimationRunnable.setAnimationEnable(true);
                mControlChGuideLoadingAnimationRunnable.setAnimationText(s);
            } else
            {
                mControlChGuideLoadingAnimationRunnable.setAnimationEnable(false);
                mControlChGuideLoadingAnimationRunnable.setAnimationText(s);
            }
            mControlChGuideLoadingAnimationRunnable.postAnimationToRunInUIThread();
        }
    }

    void startTVChannel(int i)
    {
        Handler handler = ((MtvUiChannelGuide)getActivity()).getHandler();
        invalidateChannelInfo();
        if(handler != null)
            handler.sendMessage(handler.obtainMessage(400, i, -1));
        showAnimationControl(true, getString(0x7f070087));
    }

    private static String TAG = "MtvUiFragChannelList";
    private static MtvChannel sSelectedChannel = null;
    private boolean bScanProgress;
    private Button mCh_guide_schedule;
    private Button mCh_guide_tvview_btn;
    private DialogFragment mChangeAreaCompleteDialog;
    private TextView mChannelNameText;
    private ControlChGuideLoadingAnimationRunnable mControlChGuideLoadingAnimationRunnable;
    private GridView mGridView;
    private ChannelAdapter mListAdapter;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;
    private ImageView mLoadingImageView;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvPreferences mMtvPreferences;
    private TextView mProgNameText;
    private TwProgressBar mScanProgressBar;
    private AlertDialog mScanProgressDialog;
    private TextView mScanProgressInfo;
    private TextView mScanProgressTitle;
    private int mScanType;
    int mSlotID;
    int mSlotMap[];
    int mStackLevel;
    private SurfaceView mSurfaceView;
    private TextView mTxtAnimation;
    private ImageView mhidden_surfaceview;







}
