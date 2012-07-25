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
import android.util.Log;
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
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$MtvSearchParam;->getSlotId()I");
            return slotId;
        }

        public int[] getSlotMap()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$MtvSearchParam;->getSlotMap()[I");
            return slotMap;
        }

        private final int slotId;
        private final int slotMap[];
        final MtvUiChannelGuide this$0;

        public MtvSearchParam(int i, int ai[])
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$MtvSearchParam;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I[I)V");
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$MyTabContentFactory;->createTabContent(Ljava/lang/String;)Landroid/view/View;");
                View view = new View(mContext);
                view.setMinimumWidth(0);
                view.setMinimumHeight(0);
                return view;
            }

            private final Activity mContext;
            final TabManager this$1;

            public MyTabContentFactory(Activity activity)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$MyTabContentFactory;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;Landroid/app/Activity;)V");
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
            static Fragment access$1000(TabInfo tabinfo)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;->access$1000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;)Landroid/app/Fragment;");
                return tabinfo.fragment;
            }

*/


/*
            static Fragment access$1002(TabInfo tabinfo, Fragment fragment1)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;->access$1002(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;Landroid/app/Fragment;)Landroid/app/Fragment;");
                tabinfo.fragment = fragment1;
                return fragment1;
            }

*/


/*
            static Class access$1100(TabInfo tabinfo)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;->access$1100(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;)Ljava/lang/Class;");
                return tabinfo.mClass;
            }

*/


/*
            static Bundle access$1200(TabInfo tabinfo)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;->access$1200(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;)Landroid/os/Bundle;");
                return tabinfo.args;
            }

*/


/*
            static String access$1300(TabInfo tabinfo)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;->access$1300(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;)Ljava/lang/String;");
                return tabinfo.tag;
            }

*/

            public TabInfo(String s, Class class1, Bundle bundle)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager$TabInfo;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;)V");
                this$1 = TabManager.this;
                super();
                tag = s;
                mClass = class1;
                args = bundle;
            }
        }


        public void addTab(android.widget.TabHost.TabSpec tabspec, Class class1, Bundle bundle)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;->addTab(Landroid/widget/TabHost$TabSpec;Ljava/lang/Class;Landroid/os/Bundle;)V");
            String s = tabspec.getTag();
            tabspec.setContent(new MyTabContentFactory(mActivity));
            TabInfo tabinfo = new TabInfo(s, class1, bundle);
            Log.d(tabinfo, mActivity.getFragmentManager().findFragmentByTag(s));
            if(Log.d(tabinfo) != null && !Log.d(tabinfo).isDetached())
            {
                FragmentTransaction fragmenttransaction = mActivity.getFragmentManager().beginTransaction();
                fragmenttransaction.detach(Log.d(tabinfo));
                fragmenttransaction.commit();
            }
            mTabs.put(s, tabinfo);
            mTabHost.addTab(tabspec);
        }

        public void onTabChanged(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;->onTabChanged(Ljava/lang/String;)V");
            TabInfo tabinfo = (TabInfo)mTabs.get(s);
            if(mLastTab != tabinfo)
            {
                FragmentTransaction fragmenttransaction = mActivity.getFragmentManager().beginTransaction();
                if(mLastTab != null && Log.d(mLastTab) != null)
                    fragmenttransaction.detach(Log.d(mLastTab));
                if(tabinfo != null && Log.d(tabinfo) != null)
                {
                    fragmenttransaction.attach(Log.d(tabinfo));
                } else
                {
                    Log.d(tabinfo, Fragment.instantiate(mActivity, Log.d(tabinfo).getName(), Log.d(tabinfo)));
                    fragmenttransaction.add(mContainerId, Log.d(tabinfo), Log.d(tabinfo));
                }
                fragmenttransaction.commit();
                mActivity.getFragmentManager().executePendingTransactions();
                if(mLastTab != null && Log.d(mLastTab) != "channelguidelist" && mTabHost.getCurrentTab() == 0 && MtvAreaManager.getCount(getApplicationContext()) == 0)
                {
                    Log.d(MtvUiChannelGuide.this).setLatestVChannel(-1);
                    if(noAreaSetAlertDialog == null || !noAreaSetAlertDialog.isShowing())
                        Log.d(MtvUiChannelGuide.this, 0);
                }
                mLastTab = tabinfo;
            }
        }

        public void onUpdate(int i, Object obj, String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;->onUpdate(ILjava/lang/Object;Ljava/lang/String;)V");
            MtvUiFrag mtvuifrag = (MtvUiFrag)mActivity.getFragmentManager().findFragmentByTag(s);
            if(mtvuifrag != null)
                mtvuifrag.onUpdate(i, obj);
            else
                MtvUtilDebug.Error(Log.d(), "Channel Guide Fragment invaild during scan");
        }

        private final Activity mActivity;
        private final int mContainerId;
        TabInfo mLastTab;
        private final TabHost mTabHost;
        private final HashMap mTabs = new HashMap();
        final MtvUiChannelGuide this$0;

        public TabManager(Activity activity, TabHost tabhost, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;Landroid/app/Activity;Landroid/widget/TabHost;I)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;-><init>()V");
        super();
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$1;->handleMessage(Landroid/os/Message;)V");
                message.what;
                JVM INSTR tableswitch 400 401: default 36
            //                           400 37
            //                           401 140;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                if(Log.d(MtvUiChannelGuide.this) != null && Log.d(MtvUiChannelGuide.this).getState().getState().ordinal() >= com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.INITIALIZED.ordinal())
                {
                    Log.d(MtvUiChannelGuide.this, message.arg1);
                    Log.d(MtvUiChannelGuide.this).removeCallbacks(Log.d(MtvUiChannelGuide.this));
                    Log.d(MtvUiChannelGuide.this).postDelayed(Log.d(MtvUiChannelGuide.this), calculateDelayForStartChannel());
                } else
                {
                    MtvUtilDebug.High(Log.d(), "MiddleWare Not initialized yet ! Cannot entertaing open of channels... ignoring... ");
                }
                continue; /* Loop/switch isn't completed */
_L3:
                MtvOneSegProgram amtvonesegprogram[] = getProgram();
                if(Log.d(MtvUiChannelGuide.this) == null)
                    Log.d(MtvUiChannelGuide.this, new MtvPreferences(getApplicationContext()));
                int i = Log.d(MtvUiChannelGuide.this).getLatestVChannel();
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableShowNotification = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$2;->run()V");
                Log.d(MtvUiChannelGuide.this);
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$2;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableNextPreviousChannel = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$3;->run()V");
                MtvUtilDebug.Low(Log.d(), (new StringBuilder()).append("RunnableNextPreviousChannel: ").append(Log.d(MtvUiChannelGuide.this)).toString());
                if(Log.d(MtvUiChannelGuide.this) != null)
                {
                    MtvURI mtvuri = new MtvURI(2, Log.d(MtvUiChannelGuide.this));
                    Log.d(MtvUiChannelGuide.this).open(MtvAppPlaybackContextManager.getInstance().getLiveTV(), mtvuri);
                }
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$3;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        RunnableUpdateProgramChannelInfo = new Runnable() {

            public void run()
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$14;->run()V");
                if(Log.d() != null)
                    Log.d().onUpdate(123, null, "channelguidelist");
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$14;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        mService = null;
        mConnection = new ServiceConnection() {

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$15;->onServiceConnected(Landroid/content/ComponentName;Landroid/os/IBinder;)V");
                MtvUtilDebug.Low(Log.d(), "onServiceConnected...");
                Log.d(MtvUiChannelGuide.this, (MtvAppAndroidService)((MtvAppAndroidServiceBinder)ibinder).getService());
                Log.d(MtvUiChannelGuide.this).registerListener(Log.d(MtvUiChannelGuide.this));
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$15;->onServiceDisconnected(Landroid/content/ComponentName;)V");
                Log.d(MtvUiChannelGuide.this).unregisterListener(Log.d(MtvUiChannelGuide.this));
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$15;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        listener = new onMtvAppAndroidServiceListener() {

            public void onMtvAppAndroidServiceNotify(int i, Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16;->onMtvAppAndroidServiceNotify(ILjava/lang/Object;)V");
            }

            public void onMtvAppFinishNotify(Object obj)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16;->onMtvAppFinishNotify(Ljava/lang/Object;)V");
                class _cls1
                    implements Runnable
                {

                    public void run()
                    {
                        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16$1;->run()V");
                        finish();
                    }

                    final _cls16 this$1;

                        _cls1()
                        {
                            Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16$1;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16;)V");
                            this$1 = _cls16.this;
                            super();
                        }
                }

                Log.d(MtvUiChannelGuide.this).post(new _cls1());
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$16;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
        mIntentReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent)
            {
                String s;
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$17;->onReceive(Landroid/content/Context;Landroid/content/Intent;)V");
                s = intent.getAction();
                MtvUtilDebug.Low(Log.d(), (new StringBuilder()).append("mIntentReceiver onReceive: action=").append(s).toString());
                break MISSING_BLOCK_LABEL_40;
                if(s != null && s.equals("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED"))
                {
                    MtvUtilDebug.Low(Log.d(), "mIntentReceiver: onRecieve for confirmation of TV file delete");
                    Log.d().onUpdate(127, Integer.valueOf(mSlotId), "tvfiles");
                }
                return;
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$17;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        };
    }

    private void createDialog(int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->createDialog(I)V");
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("createDialog :called dialog type :").append(i).toString());
        i;
        JVM INSTR tableswitch 0 0: default 56
    //                   0 57;
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
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$4;->onClick(Landroid/content/DialogInterface;I)V");
                if(Log.d(MtvUiChannelGuide.this) != 0)
                    Log.d(MtvUiChannelGuide.this).setCurrentTab(0);
                Intent intent = new Intent(MtvUiChannelGuide.this, com/samsung/sec/mtv/ui/channelguide/MtvUiChangeArea);
                intent.putExtra("slotId", 0);
                startActivityForResult(intent, 0);
                dialoginterface.dismiss();
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$4;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
        });
        noAreaSetAlertDialog = builder.create();
        noAreaSetAlertDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$5;->onCancel(Landroid/content/DialogInterface;)V");
                setResult(-1, getIntent().putExtra("noAreaSet", MtvUiChannelGuide.noAreaSet));
                finish();
                dialoginterface.dismiss();
            }

            final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$5;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getCurrentChannelProgramTitle(ZZ)Ljava/lang/String;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->initPlayer()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->setActivityProperty()V");
        requestWindowFeature(1);
        setVolumeControlStream(3);
        getWindow().addFlags(128);
    }

    private void showNotification()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->showNotification()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->addMemInfoFrag()V");
        (new MtvUiMemInfoFrag()).show(getFragmentManager(), "MtvUiMemInfoFrag");
    }

    long calculateDelayForStartChannel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->calculateDelayForStartChannel()J");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getChannel()Landroid/broadcast/helper/types/MtvOneSegChannel;");
        MtvOneSegChannel mtvonesegchannel;
        if(mMtvAppPlaybackContext != null)
            mtvonesegchannel = mMtvAppPlaybackContext.getAttribute().getChannel();
        else
            mtvonesegchannel = null;
        return mtvonesegchannel;
    }

    public MtvOneSegProgram getCurrentProgramDetails(MtvOneSegProgram amtvonesegprogram[])
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getCurrentProgramDetails([Landroid/broadcast/helper/types/MtvOneSegProgram;)Landroid/broadcast/helper/types/MtvOneSegProgram;");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getHandler()Landroid/os/Handler;");
        return mChannelGuideUiMsgHandler;
    }

    public MtvOneSegProgram[] getProgram()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getProgram()[Landroid/broadcast/helper/types/MtvOneSegProgram;");
        MtvOneSegProgram amtvonesegprogram[];
        if(mMtvAppPlaybackContext != null)
            amtvonesegprogram = mMtvAppPlaybackContext.getAttribute().getProgram();
        else
            amtvonesegprogram = null;
        return amtvonesegprogram;
    }

    public long getStreamTime()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->getStreamTime()J");
        long l;
        if(mMtvAppPlaybackContext != null)
            l = mMtvAppPlaybackContext.getAttribute().getTot();
        else
            l = 0L;
        return l;
    }

    public boolean isMiniModeRunning()
    {
        Iterator iterator;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->isMiniModeRunning()Z");
        iterator = ((ActivityManager)getSystemService("activity")).getRunningServices(0x7fffffff).iterator();
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onActivityResult(IILandroid/content/Intent;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onBackPressed()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onCreate(Landroid/os/Bundle;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onDestroy()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onFragEvent(ILjava/lang/Object;)V");
        i;
        JVM INSTR lookupswitch 14: default 132
    //                   0: 133
    //                   1: 460
    //                   2: 140
    //                   271: 199
    //                   272: 300
    //                   273: 179
    //                   275: 245
    //                   281: 222
    //                   282: 496
    //                   283: 323
    //                   600: 418
    //                   601: 346
    //                   602: 367
    //                   603: 439;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onPlayerNotification(III)V");
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("Ch Guide onPlayerNotification command:").append(i).append(" status:").append(j).append(" extra:").append(extra).toString());
        mtvuifragchannellist = (MtvUiFragChannelList)getFragmentManager().findFragmentByTag("channelguidelist");
        i;
        JVM INSTR tableswitch 20481 20494: default 140
    //                   20481 141
    //                   20482 172
    //                   20483 935
    //                   20484 380
    //                   20485 653
    //                   20486 888
    //                   20487 140
    //                   20488 140
    //                   20489 140
    //                   20490 140
    //                   20491 140
    //                   20492 140
    //                   20493 140
    //                   20494 334;
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$6;->run()V");
                    Log.d(MtvUiChannelGuide.this).getTabWidget().setEnabled(true);
                    Fragment fragment = getFragmentManager().findFragmentByTag("channelguidelist");
                    if(Log.d() != null && fragment != null && fragment.isResumed())
                        Log.d().onUpdate(126, null, "channelguidelist");
                    openChannel();
                    Log.d(MtvUiChannelGuide.this).onResume(Log.d(MtvUiChannelGuide.this));
                }

                final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$6;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
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
        JVM INSTR tableswitch 24581 24581: default 380
    //                   24581 472;
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$7;->run()V");
                    if(Log.d() != null)
                        Log.d().onUpdate(111, Integer.valueOf(extra), "channelguidelist");
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$7;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$8;->run()V");
                    MtvUtilDebug.Low(Log.d(), "Ch Guide runOnUiThread execute: Scan Update...");
                    if(Log.d() != null)
                        Log.d().onUpdate(113, null, "channelguidelist");
                    else
                        MtvUtilDebug.Low(Log.d(), "Ch Guide runOnUiThread TAB mgr null");
                }

                final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$8;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
                this$0 = MtvUiChannelGuide.this;
                super();
            }
            });
            break;

        case 24598: 
            runOnUiThread(new Runnable() {

                public void run()
                {
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$9;->run()V");
                    MtvUtilDebug.Low(Log.d(), "Ch Guide runOnUiThread execute: Scan Update...");
                    if(Log.d() != null)
                        Log.d().onUpdate(113, null, "channelguidelist");
                    else
                        MtvUtilDebug.Low(Log.d(), "Ch Guide runOnUiThread TAB mgr null");
                }

                final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$9;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$10;->run()V");
                    if(Log.d() != null)
                        Log.d().onUpdate(112, Integer.valueOf(extra), "channelguidelist");
                    Log.d(MtvUiChannelGuide.this).getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$10;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$11;->run()V");
                    if(Log.d() != null)
                        Log.d().onUpdate(117, Integer.valueOf(extra), "channelguidelist");
                    Log.d(MtvUiChannelGuide.this).getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$11;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$12;->run()V");
                    if(Log.d() != null)
                        Log.d().onUpdate(115, Integer.valueOf(extra), "channelguidelist");
                    Log.d(MtvUiChannelGuide.this).getTabWidget().setEnabled(true);
                }

                final MtvUiChannelGuide this$0;
                final int val$extra;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$12;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)V");
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
                    Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$13;->run()V");
                    if(Log.d() != null)
                        MtvUtilDebug.Low(Log.d(), "No Need to post the update to UI");
                }

                final MtvUiChannelGuide this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$13;-><init>(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onProgramAttributeUpdated(I)V");
        i;
        JVM INSTR tableswitch 2 2: default 28
    //                   2 29;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onResume()V");
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
            break MISSING_BLOCK_LABEL_486;
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
        currentTabId = mTabHost.getCurrentTab();
        bundle.putInt("currenttabindex", currentTabId);
    }

    public void onStateChanged(com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state, com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State state1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onStateChanged(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState$State;)V");
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->onStop()V");
        MtvUtilAppService.resetMtvVisibiltySettings(getApplicationContext());
        super.onStop();
    }

    void openChannel()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->openChannel()V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;-><clinit>()V");
        INTENT_FILTER = new IntentFilter();
        INTENT_FILTER.addAction("com.samsung.sec.mtv.ACTION_MTV_TVFILE_DELETED");
    }


/*
    static MtvAppPlaybackContext access$000(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$000(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvuichannelguide.mMtvAppPlaybackContext;
    }

*/


/*
    static int access$100(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$100(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)I");
        return mtvuichannelguide.mChannelNumber;
    }

*/


/*
    static int access$102(MtvUiChannelGuide mtvuichannelguide, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$102(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)I");
        mtvuichannelguide.mChannelNumber = i;
        return i;
    }

*/


/*
    static void access$1400(MtvUiChannelGuide mtvuichannelguide, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1400(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;I)V");
        mtvuichannelguide.createDialog(i);
        return;
    }

*/


/*
    static TabManager access$1500()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1500()Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide$TabManager;");
        return mTabManager;
    }

*/


/*
    static MtvUiBmlSurfaceView access$1600(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1600(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;");
        return mtvuichannelguide.mDummyBmlSurface;
    }

*/


/*
    static MtvAppAndroidService access$1700(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1700(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        return mtvuichannelguide.mService;
    }

*/


/*
    static MtvAppAndroidService access$1702(MtvUiChannelGuide mtvuichannelguide, MtvAppAndroidService mtvappandroidservice)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1702(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;)Lcom/samsung/sec/mtv/app/service/MtvAppAndroidService;");
        mtvuichannelguide.mService = mtvappandroidservice;
        return mtvappandroidservice;
    }

*/


/*
    static onMtvAppAndroidServiceListener access$1800(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$1800(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/app/service/onMtvAppAndroidServiceListener;");
        return mtvuichannelguide.listener;
    }

*/


/*
    static Runnable access$200(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$200(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Ljava/lang/Runnable;");
        return mtvuichannelguide.RunnableNextPreviousChannel;
    }

*/


/*
    static Handler access$300(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$300(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Landroid/os/Handler;");
        return mtvuichannelguide.mChannelGuideUiMsgHandler;
    }

*/


/*
    static String access$400()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$400()Ljava/lang/String;");
        return TAG;
    }

*/


/*
    static MtvPreferences access$500(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$500(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        return mtvuichannelguide.mMtvPreferences;
    }

*/


/*
    static MtvPreferences access$502(MtvUiChannelGuide mtvuichannelguide, MtvPreferences mtvpreferences)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$502(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;Lcom/samsung/sec/mtv/utility/MtvPreferences;)Lcom/samsung/sec/mtv/utility/MtvPreferences;");
        mtvuichannelguide.mMtvPreferences = mtvpreferences;
        return mtvpreferences;
    }

*/


/*
    static void access$600(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$600(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)V");
        mtvuichannelguide.showNotification();
        return;
    }

*/


/*
    static IMtvAppPlayerOneSeg access$700(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$700(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Lcom/samsung/sec/mtv/app/player/IMtvAppPlayerOneSeg;");
        return mtvuichannelguide.mMtvPlayerOneSeg;
    }

*/


/*
    static int access$800(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$800(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)I");
        return mtvuichannelguide.currentTabId;
    }

*/


/*
    static TabHost access$900(MtvUiChannelGuide mtvuichannelguide)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;->access$900(Lcom/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide;)Landroid/widget/TabHost;");
        return mtvuichannelguide.mTabHost;
    }

*/
}
