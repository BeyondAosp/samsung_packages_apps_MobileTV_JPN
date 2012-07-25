// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.helper.MtvURI;
import android.broadcast.helper.MtvUtilDebug;
import android.broadcast.helper.types.MtvOneSegChannel;
import android.broadcast.helper.types.MtvOneSegProgram;
import android.content.*;
import android.os.*;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.app.player.IMtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.player.MtvAppPlayerOneSeg;
import com.samsung.sec.mtv.app.service.*;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.bml.MtvUiBmlSurfaceView;
import com.samsung.sec.mtv.ui.common.*;
import com.samsung.sec.mtv.ui.fileplayer.MtvUiFilePlayer;
import com.samsung.sec.mtv.utility.*;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.ui.channelguide:
//            MtvUiChangeArea, MtvUiFragChannelList, MtvUiFragReservationList, MtvUiFragTVFilesList, 
//            MtvUiFragTVLinkList

public class MtvUiChannelGuide extends Activity
    implements IMtvAppPlaybackStateListener, IMtvAppProgramAttributeListener, com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener
{
    public final class MtvSearchParam
    {

        public int getSlotId()
        {
            return slotId;
        }

        public int[] getSlotMap()
        {
            return slotMap;
        }

        private final int slotId;
        private final int slotMap[];
        final MtvUiChannelGuide this$0;

        public MtvSearchParam(int i, int ai[])
        {
            this$0 = MtvUiChannelGuide.this;
            super();
            slotId = i;
            slotMap = ai;
        }
    }

    private class TabManager
        implements android.widget.TabHost.OnTabChangeListener
    {
        final class MyTabContentFactory
            implements android.widget.TabHost.TabContentFactory
        {

            public View createTabContent(String s)
            {
                View view = new View(mContext);
                view.setMinimumWidth(0);
                view.setMinimumHeight(0);
                return view;
            }

            private final Activity mContext;
            final TabManager this$1;

            public MyTabContentFactory(Activity activity)
            {
                this$1 = TabManager.this;
                super();
                mContext = activity;
            }
        }

        final class TabInfo
        {

            private final Bundle args;
            private Fragment fragment;
            private final Class mClass;
            private final String tag;
            final TabManager this$1;



/*
            static Fragment access$1002(TabInfo tabinfo, Fragment fragment1)
            {
                tabinfo.fragment = fragment1;
                return fragment1;
            }

*/




            public TabInfo(String s, Class class1, Bundle bundle)
            {
                this$1 = TabManager.this;
                super();
                tag = s;
                mClass = class1;
                args = bundle;
            }
        }


        public void addTab(android.widget.TabHost.TabSpec tabspec, Class class1, Bundle bundle)
        {
            String s = tabspec.getTag();
            tabspec.setContent(new MyTabContentFactory(mActivity));
            TabInfo tabinfo = new TabInfo(s, class1, bundle);
            tabinfo.fragment = mActivity.getFragmentManager().findFragmentByTag(s);
            if(tabinfo.fragment != null && !tabinfo.fragment.isDetached())
            {
                FragmentTransaction fragmenttransaction = mActivity.getFragmentManager().beginTransaction();
                fragmenttransaction.detach(tabinfo.fragment);
                fragmenttransaction.commit();
            }
            mTabs.put(s, tabinfo);
            mTabHost.addTab(tabspec);
        }

        public void onTabChanged(String s)
        {
            TabInfo tabinfo = (TabInfo)mTabs.get(s);
            if(mLastTab != tabinfo)
            {
                FragmentTransaction fragmenttransaction = mActivity.getFragmentManager().beginTransaction();
                if(mLastTab != null && mLastTab.fragment != null)
                    fragmenttransaction.detach(mLastTab.fragment);
                if(tabinfo != null && tabinfo.fragment != null)
                {
                    fragmenttransaction.attach(tabinfo.fragment);
                } else
                {
                    tabinfo.fragment = Fragment.instantiate(mActivity, tabinfo.mClass.getName(), tabinfo.args);
                    fragmenttransaction.add(mContainerId, tabinfo.fragment, tabinfo.tag);
                }
                fragmenttransaction.commit();
                mActivity.getFragmentManager().executePendingTransactions();
                if(mLastTab != null && mLastTab.tag != "channelguidelist" && mTabHost.getCurrentTab() == 0 && MtvAreaManager.getCount(getApplicationContext()) == 0)
                {
                    mMtvPreferences.setLatestVChannel(-1);
                    if(noAreaSetAlertDialog == null || !noAreaSetAlertDialog.isShowing())
                        createDialog(0);
                }
                mLastTab = tabinfo;
            }
        }

        public void onUpdate(int i, Object obj, String s)
        {
            MtvUiFrag mtvuifrag = (MtvUiFrag)mActivity.getFragmentManager().findFragmentByTag(s);
            if(mtvuifrag != null)
                mtvuifrag.onUpdate(i, obj);
            else
                MtvUtilDebug.Error(MtvUiChannelGuide.TAG, "Channel Guide Fragment invaild during scan");
        }

        private final Activity mActivity;
        private final int mContainerId;
        TabInfo mLastTab;
        private final TabHost mTabHost;
        private final HashMap mTabs = new HashMap();
        final MtvUiChannelGuide this$0;

        public TabManager(Activity activity, TabHost tabhost, int i)
        {
            this$0 = MtvUiChannelGuide.this;
            super();
            mActivity = activity;
            mTabHost = tabhost;
            mContainerId = i;
            tabhost.setOnTabChangedListener(this);
        }
    }


    public MtvUiChannelGuide()
    {
        mMtvPlayerOneSeg = null;
        mChannelNumber = 21;
        mSelected = null;
        noAreaSetAlertDialog = null;
        currentTabId = 0;
        mMtvAppPlaybackContext = null;
        mMtvAudMgr = null;
        mChannelGuideUiMsgHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 400 401: default 28
            //                           400 29
            //                           401 132;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                if(mMtvAppPlaybackContext != null && mMtvAppPlaybackContext.getState().getState().ordinal() >= com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED.ordinal())
                {
                    mChannelNumber = message.arg1;
                    mChannelGuideUiMsgHandler.removeCallbacks(RunnableNextPreviousChannel);
                    mChannelGuideUiMsgHandler.postDelayed(RunnableNextPreviousChannel, calculateDelayForStartChannel());
                } else
                {
                    MtvUtilDebug.High(MtvUiChannelGuide.TAG, "MiddleWare Not initialized yet ! Cannot entertaing open of channels... ignoring... ");
                }
                continue; /* Loop/switch isn't completed */
_L3:
                MtvOneSegProgram amtvonesegprogram[] = getProgram();
                if(mMtvPreferences == null)
                    mMtvPreferences = new MtvPreferences(getApplicationContext());
                int i = mMtvPreferences.getLatestVChannel();
                MtvProgramManager.delete(MtvUiChannelGuide.this, null);
                if(amtvonesegprogram != null && amtvonesegprogram.length > 0)
                {
                    int j = 0;
                    while(j < amtvonesegprogram.length && amtvonesegprogram[j] != null) 
                    {
                        MtvProgram mtvprogram = new MtvProgram(amtvonesegprogram[j], i);
                        if(mtvprogram != null)
                            MtvProgramManager.updateOrInsert(MtvUiChannelGuide.this, mtvprogram);
                        j++;
                    }
                }
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableShowNotification = new Runnable() {

            public void run()
            {
                showNotification();
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableNextPreviousChannel = new Runnable() {

            public void run()
            {
                MtvUtilDebug.Low(MtvUiChannelGuide.TAG, (new StringBuilder()).append("RunnableNextPreviousChannel: ").append(mChannelNumber).toString());
                if(mMtvPlayerOneSeg != null)
                {
                    MtvURI mtvuri = new MtvURI(2, mChannelNumber);
                    mMtvPlayerOneSeg.open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
                }
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableUpdateProgramChannelInfo = new Runnable() {

            public void run()
            {
                if(MtvUiChannelGuide.mTabManager != null)
                    MtvUiChannelGuide.mTabManager.onUpdate(123, null, "channelguidelist");
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "onServiceConnected...");
                mService = (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService();
                mService.registerListener(listener);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mService.unregisterListener(listener);
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
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

                    final _cls16 this$1;

                        _cls1()
                        {
                            this$1 = _cls16.this;
                            super();
                        }
                }

                mChannelGuideUiMsgHandler.post(new _cls1());
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                String s;
                s = intent.getAction();
                MtvUtilDebug.Low(MtvUiChannelGuide.TAG, (new StringBuilder()).append("mIntentReceiver onReceive: action=").append(s).toString());
                break MISSING_BLOCK_LABEL_30;
                if(s != null && s.equals("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED"))
                {
                    MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "mIntentReceiver: onRecieve for confirmation of TV file delete");
                    MtvUiChannelGuide.mTabManager.onUpdate(127, Integer.valueOf(mSlotId), "tvfiles");
                }
                return;
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
    }

    private void createDialog(int i)
    {
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("createDialog :called dialog type :").append(i).toString());
        i;
        JVM INSTR tableswitch 0 0: default 44
    //                   0 45;
           goto _L1 _L2
_L1:
        return;
_L2:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(0x7f02008e);
        builder.setTitle(0x7f070293);
        builder.setMessage(0x7f070294);
        builder.setPositiveButton(0x7f070034, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                if(currentTabId != 0)
                    mTabHost.setCurrentTab(0);
                Intent intent = new Intent(MtvUiChannelGuide.this, com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
                intent.putExtra("slotId", 0);
                startActivityForResult(intent, 0);
                dialoginterface.dismiss();
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        });
        noAreaSetAlertDialog = builder.create();
        noAreaSetAlertDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                setResult(-1, getIntent().putExtra("noAreaSet", MtvUiChannelGuide.noAreaSet));
                finish();
                dialoginterface.dismiss();
            }

            final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        });
        noAreaSetAlertDialog.getWindow().addFlags(1024);
        noAreaSetAlertDialog.show();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private String getCurrentChannelProgramTitle(boolean flag, boolean flag1)
    {
        MtvOneSegChannel mtvonesegchannel = null;
        String s = "";
        MtvOneSegProgram amtvonesegprogram[];
        String s1;
        if(!flag)
        {
            MtvOneSegChannel mtvonesegchannel1 = getChannel();
            MtvOneSegProgram amtvonesegprogram1[] = getProgram();
            mtvonesegchannel = mtvonesegchannel1;
            amtvonesegprogram = amtvonesegprogram1;
        } else
        {
            amtvonesegprogram = null;
        }
        if(mtvonesegchannel != null)
            s1 = mtvonesegchannel.getServName();
        else
            s1 = mMtvPreferences.getLatestChannelNameForDisplay(flag1);
        if(amtvonesegprogram != null)
        {
            MtvOneSegProgram mtvonesegprogram = getCurrentProgramDetails(amtvonesegprogram);
            if(mtvonesegprogram != null)
                s = mtvonesegprogram.getProgName();
        } else
        {
            s = "";
        }
        return (new StringBuilder()).append("").append(s1).append("\n").append(s).toString();
    }

    private void initPlayer()
    {
        boolean flag;
        Intent intent = getIntent();
        if(intent != null && intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_RESERVATION_LIST"))
            flag = true;
        else
            flag = false;
        mDummyBmlSurface = (MtvUiBmlSurfaceView)findViewById(0x7f0a000a);
        mMtvPlayerOneSeg = MtvAppPlayerOneSeg.getInstance();
        mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvAppPlaybackContext == null) goto _L2; else goto _L1
_L1:
        if(mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.LIVETV && mMtvAppPlaybackContext.getType() != com.samsung.sec.mtv.app.context.MtvAppPlaybackContext.Type.SCANNER)
        {
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            openChannel();
        } else
        {
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
        }
_L4:
        return;
_L2:
        if(!flag)
        {
            MtvUtilDebug.Error(TAG, "Current Context is null... issuing create again...");
            mTabHost.getTabWidget().setEnabled(false);
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getLiveTV();
            mDummyBmlSurface = (MtvUiBmlSurfaceView)findViewById(0x7f0a000a);
            mMtvAppPlaybackContext.getState().registerListener(this);
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
            mMtvPlayerOneSeg.create(mMtvAppPlaybackContext, this);
            mMtvAppPlaybackContext.getComponents().getBml().enable();
            if(mMtvAppPlaybackContext.getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE)
            {
                mDummyBmlSurface.onResume(mMtvAppPlaybackContext);
            } else
            {
                MtvUiFragHandler mtvuifraghandler = new MtvUiFragHandler(getFragmentManager(), -1, 0x1020011);
                mtvuifraghandler.setEnabled(false);
                mDummyBmlSurface.onCreate(mMtvAppPlaybackContext, mtvuifraghandler);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setActivityProperty()
    {
        requestWindowFeature(1);
        setVolumeControlStream(3);
        getWindow().addFlags(128);
    }

    private void showNotification()
    {
        MtvUtilDebug.Low(TAG, "showNotification...");
        Notification notification = new Notification();
        notification.icon = 0x7f020113;
        notification.when = 0L;
        notification.flags = 0x22 | notification.flags;
        Intent intent = new Intent();
        intent.setFlags(0x100000);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName("com.samsung.sec.mtv", "com.samsung.sec.mtv.ui.liveplayer.MtvUiLivePlayer"));
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, getString(0x7f07009b), getCurrentChannelProgramTitle(false, true), pendingintent);
        if(mNotificationManager != null)
            mNotificationManager.notify(0x7f020113, notification);
        else
            MtvUtilDebug.Error(TAG, "mNotificationManager is null");
    }

    public void addMemInfoFrag()
    {
        (new MtvUiMemInfoFrag()).show(getFragmentManager(), "MtvUiMemInfoFrag");
    }

    long calculateDelayForStartChannel()
    {
        long l = System.currentTimeMillis();
        long l1 = l - mPreviousChannelChangeTime;
        long l2;
        if(l1 > 2500L)
            l2 = 0L;
        else
            l2 = 2500L - l1;
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("DelayForStart:").append(l2).append(": prev:").append(mPreviousChannelChangeTime).append(": current:").append(mPreviousChannelChangeTime).toString());
        mPreviousChannelChangeTime = l;
        return l2;
    }

    public MtvOneSegChannel getChannel()
    {
        MtvOneSegChannel mtvonesegchannel;
        if(mMtvAppPlaybackContext != null)
            mtvonesegchannel = mMtvAppPlaybackContext.getAttribute().getChannel();
        else
            mtvonesegchannel = null;
        return mtvonesegchannel;
    }

    public MtvOneSegProgram getCurrentProgramDetails(MtvOneSegProgram amtvonesegprogram[])
    {
        MtvOneSegProgram mtvonesegprogram = null;
        long l = getStreamTime();
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

    protected Handler getHandler()
    {
        return mChannelGuideUiMsgHandler;
    }

    public MtvOneSegProgram[] getProgram()
    {
        MtvOneSegProgram amtvonesegprogram[];
        if(mMtvAppPlaybackContext != null)
            amtvonesegprogram = mMtvAppPlaybackContext.getAttribute().getProgram();
        else
            amtvonesegprogram = null;
        return amtvonesegprogram;
    }

    public long getStreamTime()
    {
        long l;
        if(mMtvAppPlaybackContext != null)
            l = mMtvAppPlaybackContext.getAttribute().getTot();
        else
            l = 0L;
        return l;
    }

    public boolean isMiniModeRunning()
    {
        Iterator iterator = ((ActivityManager)getSystemService("activity")).getRunningServices(0x7fffffff).iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!((android.app.ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName().equals("com.samsung.sec.mtv.ui.miniTV.MtvMiniModeService")) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("MtvUiChannelGuide onActivityResult: requestCode=").append(i).append(", resultCode=").append(j).toString());
        if(j == -1)
            if(intent.getBooleanExtra("setarea", true))
            {
                if(intent.hasExtra("noAreaSet"))
                {
                    MtvUtilDebug.Low(TAG, "noAreaSet! -user didnot select Area");
                    noAreaSet = true;
                } else
                {
                    MtvUtilDebug.Low(TAG, "Launching Change Area from SET AREA Class!");
                    int k = intent.getIntExtra("slotId", 0);
                    Intent intent1 = new Intent(this, com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
                    intent1.putExtra("slotId", k);
                    startActivityForResult(intent1, 0);
                }
            } else
            {
                mTabHost.getTabWidget().setEnabled(false);
                if(mTabManager != null && currentTabId == 0)
                    mTabManager.onUpdate(124, null, "channelguidelist");
                noAreaSet = false;
                mSlotId = intent.getIntExtra("slotId", 0);
                mSelected = intent.getIntArrayExtra("localId");
            }
        super.onActivityResult(i, j, intent);
    }

    public void onBackPressed()
    {
        if(!MtvUiFragHandler.removeUnManagedFrag("ProgInfoFrag", this))
        {
            Fragment fragment = getFragmentManager().findFragmentByTag("TvLinkInfo");
            if(fragment != null)
            {
                FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
                fragmenttransaction.remove(fragment);
                fragmenttransaction.commit();
                mTabHost.getTabWidget().setEnabled(true);
            } else
            {
                finish();
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onCreate called..Time:").append(System.currentTimeMillis()).toString());
        super.onCreate(bundle);
        bindService(new Intent(getApplicationContext(), com/samsung/sec/mtv/app/service/MtvAppAndroidService), mConnection, 1);
        mMtvPreferences = new MtvPreferences(getApplicationContext());
        setActivityProperty();
        setContentView(0x7f030001);
        mTabHost = (TabHost)findViewById(0x7f0a0007);
        mTabHost.setup();
        mTabManager = new TabManager(this, mTabHost, 0x1010000);
        mTabManager.addTab(mTabHost.newTabSpec("channelguidelist").setIndicator(getString(0x7f07028c)), com/samsung/sec/mtv/ui/channelguide/MtvUiFragChannelList, null);
        mTabManager.addTab(mTabHost.newTabSpec("schedule").setIndicator(getString(0x7f07008d)), com/samsung/sec/mtv/ui/channelguide/MtvUiFragReservationList, null);
        mTabManager.addTab(mTabHost.newTabSpec("tvfiles").setIndicator(getString(0x7f070094)), com/samsung/sec/mtv/ui/channelguide/MtvUiFragTVFilesList, null);
        mTabManager.addTab(mTabHost.newTabSpec("tvlink").setIndicator(getString(0x7f070095)), com/samsung/sec/mtv/ui/channelguide/MtvUiFragTVLinkList, null);
        mNotificationManager = (NotificationManager)getSystemService("notification");
        if(bundle == null) goto _L2; else goto _L1
_L1:
        currentTabId = bundle.getInt("currenttabindex", -1);
_L4:
        getWindow().addFlags(8192);
        if(currentTabId != mTabHost.getCurrentTab())
            mTabHost.setCurrentTab(currentTabId);
        mMtvAudMgr = MtvUtilAudioManager.getInstance(getApplicationContext());
        registerReceiver(mIntentReceiver, INTENT_FILTER);
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onCreate END..Time:").append(System.currentTimeMillis()).toString());
        return;
_L2:
        if(getIntent().hasExtra("tab"))
            currentTabId = getIntent().getIntExtra("tab", 0);
        else
        if(MtvUtilConfigSetting.EPGAPP_ENABLED)
        {
            Intent intent = getIntent();
            if(intent != null && intent.getAction() != null && intent.getAction().equalsIgnoreCase("ACTION_DTV_RESERVATION_LIST"))
            {
                currentTabId = 1;
                mTabHost.getTabWidget().getChildTabViewAt(3).setEnabled(false);
                mTabHost.getTabWidget().getChildTabViewAt(2).setEnabled(false);
                mTabHost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onDestroy()
    {
        MtvUtilDebug.Low(TAG, "onDestroy called");
        if(mService != null)
        {
            mService.unregisterListener(listener);
            unbindService(mConnection);
            mService = null;
        }
        super.onDestroy();
        MtvUtilAppService.unbindDrawables(findViewById(0x7f0a0008));
        System.gc();
        mNotificationManager = null;
        unregisterReceiver(mIntentReceiver);
    }

    public void onFragEvent(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 14: default 124
    //                   0: 125
    //                   1: 452
    //                   2: 132
    //                   271: 191
    //                   272: 292
    //                   273: 171
    //                   275: 237
    //                   281: 214
    //                   282: 485
    //                   283: 315
    //                   600: 410
    //                   601: 338
    //                   602: 359
    //                   603: 431;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L1:
        return;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L4:
        Intent intent2 = getIntent();
        intent2.putExtra("channelinput", true);
        setResult(-1, intent2);
        mMtvPreferences.setLatestFileIndex(0);
        finish();
        continue; /* Loop/switch isn't completed */
_L7:
        if(mTabManager != null)
            mTabManager.onUpdate(1, obj, "schedule");
        continue; /* Loop/switch isn't completed */
_L5:
        if(mTabManager != null)
            mTabManager.onUpdate(1, Boolean.valueOf(false), "tvfiles");
        continue; /* Loop/switch isn't completed */
_L9:
        if(mTabManager != null)
            mTabManager.onUpdate(3, Boolean.valueOf(false), "tvfiles");
        continue; /* Loop/switch isn't completed */
_L8:
        Intent intent1 = new Intent(getApplicationContext(), com/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer);
        MtvUtilDebug.Low(TAG, "startFilePlayer MTV_UPDATE_FRAG_CMD_DIALOGS_LAUNCH_FILE_PLAYER");
        intent1.putExtra("MtvFile", MtvFileManager.getAvailableTVRecFilesEx()[mMtvPreferences.getSelectedFileIndex()]);
        startActivityForResult(intent1, 0);
        continue; /* Loop/switch isn't completed */
_L6:
        if(mTabManager != null)
            mTabManager.onUpdate(2, Boolean.valueOf(false), "tvfiles");
        continue; /* Loop/switch isn't completed */
_L11:
        if(mTabManager != null)
            mTabManager.onUpdate(4, Boolean.valueOf(false), "tvfiles");
        continue; /* Loop/switch isn't completed */
_L13:
        Intent intent = (Intent)obj;
        if(intent != null)
            startActivityForResult(intent, 0);
        continue; /* Loop/switch isn't completed */
_L14:
        Bundle bundle1 = new Bundle();
        bundle1.putInt("dialogType", 2);
        bundle1.putInt("SlotID", ((Integer)obj).intValue());
        MtvUiDialogsFrag.newInstance(bundle1).show(getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L12:
        if(mTabManager != null)
            mTabManager.onUpdate(119, obj, "channelguidelist");
        continue; /* Loop/switch isn't completed */
_L15:
        if(mTabManager != null)
            mTabManager.onUpdate(120, obj, "channelguidelist");
        continue; /* Loop/switch isn't completed */
_L3:
        Bundle bundle = new Bundle();
        bundle.putInt("dialogType", 1);
        MtvUiDialogsFrag.newInstance(bundle).show(getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L10:
        if(mTabManager != null)
            mTabManager.onUpdate(2, Boolean.valueOf(false), "schedule");
        if(true) goto _L1; else goto _L16
_L16:
    }

    public void onPlayerNotification(int i, int j, final int extra)
    {
        MtvUiFragChannelList mtvuifragchannellist;
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("Ch Guide onPlayerNotification command:").append(i).append(" status:").append(j).append(" extra:").append(extra).toString());
        mtvuifragchannellist = (MtvUiFragChannelList)getFragmentManager().findFragmentByTag("channelguidelist");
        i;
        JVM INSTR tableswitch 20481 20494: default 132
    //                   20481 133
    //                   20482 164
    //                   20483 927
    //                   20484 372
    //                   20485 645
    //                   20486 880
    //                   20487 132
    //                   20488 132
    //                   20489 132
    //                   20490 132
    //                   20491 132
    //                   20492 132
    //                   20493 132
    //                   20494 326;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L8
_L11:
        return;
_L2:
        if(j == 24581)
        {
            MtvUtilDebug.Low(TAG, "IMtvAppPlayerOneSeg.CMD_CREATE IMtvAppPlayerOneSeg.STAT_COMPLETED...");
            runOnUiThread(new Runnable() {

                public void run()
                {
                    mTabHost.getTabWidget().setEnabled(true);
                    Fragment fragment = getFragmentManager().findFragmentByTag("channelguidelist");
                    if(MtvUiChannelGuide.mTabManager != null && fragment != null && fragment.isResumed())
                        MtvUiChannelGuide.mTabManager.onUpdate(126, null, "channelguidelist");
                    openChannel();
                    mDummyBmlSurface.onResume(mMtvAppPlaybackContext);
                }

                final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
            });
        }
        continue; /* Loop/switch isn't completed */
_L3:
        switch(j)
        {
        case 24577: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070087));
            runOnUiThread(RunnableShowNotification);
            break;

        case 24582: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070086));
            break;

        case 24584: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(false, null);
            break;

        case 24583: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070086));
            break;

        case 24580: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070087));
            openChannel();
            break;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPlayerNotification ...:CMD_CLOSE: Status-").append(j).toString());
        j;
        JVM INSTR tableswitch 24581 24581: default 372
    //                   24581 464;
           goto _L5 _L9
_L5:
        switch(j)
        {
        case 24577: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070086));
            break;

        case 24581: 
            MtvUtilAudioManager.getInstance(getApplicationContext()).updateCurrentAudioEffectAndMode();
            break;

        case 24582: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070086));
            break;

        case 24583: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070086));
            break;

        case 24584: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(false, null);
            break;

        case 24580: 
            if(mtvuifragchannellist != null)
                mtvuifragchannellist.showAnimationControl(true, getString(0x7f070087));
            openChannel();
            break;

        case 24593: 
            MtvUtilDebug.Error(TAG, "onPlayerNotification ...CMD_PLAY:STAT_UNKNOWN:: Something severely screwed -- Happy Debugging !!!");
            if(!MtvUtilDebug.isReleaseMode())
            {
                Toast toast = Toast.makeText(this, "\n\n\n       [OneSeg]   F A T A L    E R R O R !\n\n\n OneSeg middleware crashed, cannot continue MTV \n\n  - Use *#9900# to take log after termination - ", 1);
                toast.setGravity(119, 0, 0);
                toast.show();
            }
            sendBroadcast(new Intent("com.samsung.sec.mtv.ACTION_MTV_APP_FINISH_ACTIVITIES_ALONE"));
            break;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        runOnUiThread(RunnableUpdateProgramChannelInfo);
        if(mMtvAppPlaybackContext != null)
            mMtvAppPlaybackContext.getAttribute().registerListener(this);
          goto _L5
_L6:
        switch(j)
        {
        case 24577: 
            MtvUtilDebug.Low(TAG, "onPlayerNotification :CMD_SCAN : STAT_STARTED");
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(111, Integer.valueOf(extra), "channelguidelist");
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                this$0 = MtvUiChannelGuide.this;
                extra = i;
                super();
            }
            });
            break;

        case 24597: 
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPlayerNotification :CMD_SCAN : STAT_CHNL_DETECTED extra:").append(extra).toString());
            MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
            Intent intent;
            MtvOneSegChannel mtvonesegchannel;
            if(mtvappplaybackcontext != null)
                mtvonesegchannel = mtvappplaybackcontext.getAttribute().getChannel();
            else
                mtvonesegchannel = null;
            if(mtvonesegchannel != null)
                MtvChannelManager.updateOrInsert(this, new MtvChannel(mtvonesegchannel, 1));
            runOnUiThread(new Runnable() {

                public void run()
                {
                    MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "Ch Guide runOnUiThread execute: Scan Update...");
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(113, null, "channelguidelist");
                    else
                        MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "Ch Guide runOnUiThread TAB mgr null");
                }

                final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
            });
            break;

        case 24598: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "Ch Guide runOnUiThread execute: Scan Update...");
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(113, null, "channelguidelist");
                    else
                        MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "Ch Guide runOnUiThread TAB mgr null");
                }

                final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
            });
            break;

        case 24581: 
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPlayerNotification :CMD_SCAN : STAT_COMPLETED extra:").append(extra).toString());
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(112, Integer.valueOf(extra), "channelguidelist");
                    mTabHost.getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                this$0 = MtvUiChannelGuide.this;
                extra = i;
                super();
            }
            });
            break;

        case 24580: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(117, Integer.valueOf(extra), "channelguidelist");
                    mTabHost.getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                this$0 = MtvUiChannelGuide.this;
                extra = i;
                super();
            }
            });
            break;
        }
_L1:
        if(true) goto _L11; else goto _L10
_L10:
_L7:
        switch(j)
        {
        case 24581: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUiChannelGuide.mTabManager.onUpdate(115, Integer.valueOf(extra), "channelguidelist");
                    mTabHost.getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                this$0 = MtvUiChannelGuide.this;
                extra = i;
                super();
            }
            });
            break;
        }
        if(true) goto _L11; else goto _L12
_L12:
_L4:
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPlayerNotification ...:CMD_STATUS_UPDT Status-").append(j).toString());
        switch(j)
        {
        case 24599: 
            intent = new Intent();
            MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onPlayerNotification: STAT_DEL_FILE -> result: ").append(extra).toString());
            intent.putExtra("deleteTVFileConfirmation", true);
            intent.setAction("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED");
            sendBroadcast(intent);
            runOnUiThread(new Runnable() {

                public void run()
                {
                    if(MtvUiChannelGuide.mTabManager != null)
                        MtvUtilDebug.Low(MtvUiChannelGuide.TAG, "No Need to post the update to UI");
                }

                final MtvUiChannelGuide this$0;

            
            {
                this$0 = MtvUiChannelGuide.this;
                super();
            }
            });
            break;
        }
        if(true) goto _L11; else goto _L13
_L13:
    }

    public void onProgramAttributeUpdated(int i)
    {
        i;
        JVM INSTR tableswitch 2 2: default 20
    //                   2 21;
           goto _L1 _L2
_L1:
        return;
_L2:
        mChannelGuideUiMsgHandler.sendMessage(mChannelGuideUiMsgHandler.obtainMessage(401));
        runOnUiThread(RunnableShowNotification);
        runOnUiThread(RunnableUpdateProgramChannelInfo);
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onResume()
    {
        super.onResume();
        sendBroadcast(new Intent("intent.stop.app-in-app"));
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getApplicationContext());
        if(MtvUtilAppService.isAppExiting())
        {
            MtvUtilDebug.Low(TAG, "isExiting...");
            finish();
        } else
        {
label0:
            {
                if(!isMiniModeRunning())
                    break label0;
                finish();
            }
        }
_L3:
        return;
        MtvArea mtvarea;
        MtvArea mtvarea1;
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.screenBrightness = mMtvPreferences.getBrightnessValue();
        getWindow().setAttributes(layoutparams);
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onResume Start..Time:").append(System.currentTimeMillis()).toString());
        initPlayer();
        MtvUtilAppService.setMtvVisibiltySettings(getApplicationContext());
        mtvarea = null;
        mtvarea1 = MtvAreaManager.find(this, mMtvPreferences.getCurrentSlot());
        if(MtvAreaManager.getCount(getApplicationContext()) == 0)
            noAreaSet = true;
        if(mSelected == null) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low(TAG, "selected another area... will change the slot and scan again...");
        MtvUtilDebug.Low(TAG, "Stopping other sound now...");
        mMtvAudMgr.setAvStreaming(true);
        mTabManager.onUpdate(119, Integer.valueOf(mSlotId), "channelguidelist");
        mTabManager.onUpdate(116, new MtvSearchParam(mSlotId, mSelected), "channelguidelist");
        mSelected = null;
_L10:
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("onResume END..Time:").append(System.currentTimeMillis()).toString());
          goto _L3
_L2:
        MtvArea amtvarea[];
        int i;
        if(mtvarea1.mAreaId != -1 || !noAreaSet)
            break MISSING_BLOCK_LABEL_473;
        MtvUtilDebug.Low(TAG, "reset current area and came back without selecting any other area...");
        MtvUtilDebug.Low(TAG, "will select the next available area in the list now...");
        amtvarea = MtvAreaManager.getAllAreas(this);
        i = 0;
_L11:
        if(i >= amtvarea.length) goto _L5; else goto _L4
_L4:
        if(amtvarea[i].mAreaId == -1) goto _L7; else goto _L6
_L6:
        mtvarea = amtvarea[i];
        mMtvPreferences.setCurrentSlot(i);
_L5:
        if(mtvarea == null || mtvarea.mAreaId == -1) goto _L9; else goto _L8
_L8:
        MtvUtilDebug.Low(TAG, "found next available area starting Tv...");
        mTabManager.onUpdate(120, Integer.valueOf(mMtvPreferences.getCurrentSlot()), "channelguidelist");
          goto _L10
_L7:
        i++;
          goto _L11
_L9:
        if(isFinishing()) goto _L10; else goto _L12
_L12:
        MtvUtilDebug.Low(TAG, "onResume : No AREA SET - Select Area Prompt");
        MtvChannelManager.deleteDB(this, null);
        MtvUtilAudioManager.getInstance(getApplicationContext()).setAudioMute(true);
        if(currentTabId == 0)
        {
            mMtvPreferences.setLatestVChannel(-1);
            createDialog(0);
        }
          goto _L3
        if(mMtvAudMgr != null)
        {
            MtvUtilDebug.Low(TAG, "Either launched from LivePlayer or came back from setArea without doing anything... ");
            MtvUtilDebug.Low(TAG, "Stopping other sound now...");
            mMtvAudMgr.stopOtherSound();
            mMtvAudMgr.setAvStreaming(true);
        }
          goto _L10
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        currentTabId = mTabHost.getCurrentTab();
        bundle.putInt("currenttabindex", currentTabId);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
    }

    public void onStop()
    {
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    void openChannel()
    {
        MtvURI mtvuri = new MtvURI(2, mMtvPreferences.getLatestPChannelFromVChannel());
        mMtvPlayerOneSeg.open(mMtvAppPlaybackContext, mtvuri);
    }

    private static IntentFilter INTENT_FILTER;
    private static String TAG = "MtvUiChannelGuide";
    private static NotificationManager mNotificationManager = null;
    private static TabManager mTabManager;
    public static boolean noAreaSet = false;
    private Runnable RunnableNextPreviousChannel;
    private Runnable RunnableShowNotification;
    private Runnable RunnableUpdateProgramChannelInfo;
    private int currentTabId;
    private onMtvAppAndroidServiceListener listener;
    private Handler mChannelGuideUiMsgHandler;
    private int mChannelNumber;
    private ServiceConnection mConnection;
    private MtvUiBmlSurfaceView mDummyBmlSurface;
    private BroadcastReceiver mIntentReceiver;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvUtilAudioManager mMtvAudMgr;
    private IMtvAppPlayerOneSeg mMtvPlayerOneSeg;
    private MtvPreferences mMtvPreferences;
    private long mPreviousChannelChangeTime;
    int mSelected[];
    private MtvAppAndroidService mService;
    int mSlotId;
    private TabHost mTabHost;
    AlertDialog noAreaSetAlertDialog;

    static 
    {
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED");
    }




/*
    static int access$102(MtvUiChannelGuide mtvuichannelguide, int i)
    {
        mtvuichannelguide.mChannelNumber = i;
        return i;
    }

*/






/*
    static MtvAppAndroidService access$1702(MtvUiChannelGuide mtvuichannelguide, MtvAppAndroidService mtvappandroidservice)
    {
        mtvuichannelguide.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/







/*
    static MtvPreferences access$502(MtvUiChannelGuide mtvuichannelguide, MtvPreferences mtvpreferences)
    {
        mtvuichannelguide.mMtvPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/




}
