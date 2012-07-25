// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.provider.MtvBMLManager;
import com.samsung.sec.mtv.provider.MtvCProBMInfo;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.ui.common.MtvUiRemoveList;
import com.samsung.sec.mtv.ui.tvlink.MtvUiTvLinks;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MtvUiFragTVLinkList extends MtvUiFrag
    implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
{
    public static class MTvlinkDetail extends DialogFragment
    {

        private CharSequence getLinkNumber()
        {
            int i = 1 + mLinkId;
            return (new StringBuilder()).append(getResources().getString(0x7f07027d)).append(" ").append(i).toString();
        }

        private String getProgramInfo()
        {
            return mlink.getOutline();
        }

        private String getTitleText()
        {
            return mlink.getTitle();
        }

        private String getValidityDate()
        {
            return (new SimpleDateFormat("yyyy/M/d H:mm")).format(mlink.getValidDate());
        }

        private Dialog initDetails(MtvCProBMInfo mtvcprobminfo)
        {
            makeLinkInfo();
            Activity activity = getActivity();
            ArrayList arraylist = mInfoList;
            String as[] = new String[2];
            as[0] = "MENU_TITLE";
            as[1] = "MENU_VALUE";
            int ai[] = new int[2];
            ai[0] = 0x7f0a0110;
            ai[1] = 0x7f0a0111;
            SimpleAdapter simpleadapter = new SimpleAdapter(activity, arraylist, 0x7f03002c, as, ai);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            builder.setTitle(getLinkNumber());
            builder.setAdapter(simpleadapter, null);
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if(mDialog != null)
                        mDialog.dismiss();
                }

                final MTvlinkDetail this$0;

                _cls1()
                {
                    this$0 = MTvlinkDetail.this;
                    super();
                }
            }

            builder.setPositiveButton(getString(0x7f070034), new _cls1());
            mDetailsDialog = builder.create();
            mDetailsDialog.setCanceledOnTouchOutside(false);
            mDetailsDialog.requestWindowFeature(1);
            mDetailsDialog.getWindow().setFlags(1024, 1024);
            mDetailsDialog.show();
            mDetailsDialog.setOnKeyListener(mKeyListener);
            mDialog = mDetailsDialog;
            return mDialog;
        }

        private void makeLinkInfo()
        {
            if(mlink == null)
            {
                MtvUtilDebug.Error(TAG, "Mlink null!!!");
            } else
            {
                makeTitleText();
                makeProgramInfo();
                makeValidityDate();
            }
        }

        private void makeProgramInfo()
        {
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f07027f));
            map.put("MENU_VALUE", getProgramInfo());
            mInfoList.add(map);
        }

        private void makeTitleText()
        {
            map = new HashMap();
            map.put("MENU_TITLE", getString(0x7f07027e));
            map.put("MENU_VALUE", getTitleText());
            mInfoList.add(map);
        }

        private void makeValidityDate()
        {
            if(mlink.getValidDate().getTime() != 0L)
            {
                map = new HashMap();
                map.put("MENU_TITLE", getString(0x7f07027f));
                map.put("MENU_VALUE", getValidityDate());
                mInfoList.add(map);
            }
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            if(bundle != null)
                mLinkId = bundle.getInt("MtvLinkId");
            try
            {
                mlink = MtvBMLManager.getAvailableCProBMInfo(mLinkId);
            }
            catch(SQLiteException sqliteexception)
            {
                sqliteexception.printStackTrace();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            return initDetails(mlink);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("MtvLinkId", mLinkId);
        }

        static String TAG = "MTvlinkDetail";
        AlertDialog mDetailsDialog;
        private AlertDialog mDialog;
        private ArrayList mInfoList;
        private android.content.DialogInterface.OnKeyListener mKeyListener;
        private int mLinkId;
        private HashMap map;
        private MtvCProBMInfo mlink;



        public MTvlinkDetail()
        {
            map = null;
            mInfoList = new ArrayList();
            mDialog = null;
            mDetailsDialog = null;
            class _cls2
                implements android.content.DialogInterface.OnKeyListener
            {

                public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                {
                    boolean flag = true;
                    i;
                    JVM INSTR tableswitch 4 4: default 24
                //                               4 30;
                       goto _L1 _L2
_L1:
                    flag = false;
_L4:
                    return flag;
_L2:
                    if(keyevent.getAction() == flag && mDialog != null)
                        mDialog.dismiss();
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final MTvlinkDetail this$0;

                _cls2()
                {
                    this$0 = MTvlinkDetail.this;
                    super();
                }
            }

            mKeyListener = new _cls2();
        }

        public MTvlinkDetail(int i)
        {
            map = null;
            mInfoList = new ArrayList();
            mDialog = null;
            mDetailsDialog = null;
            mKeyListener = new _cls2();
            mLinkId = i;
        }
    }

    private class TvLinkAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1;
            MtvCProBMInfo mtvcprobminfo;
            view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03000f, null);
            mtvcprobminfo = mMtvLinks[i];
            if(mtvcprobminfo == null) goto _L2; else goto _L1
_L1:
            ImageView imageview;
            TextView textview;
            imageview = (ImageView)view1.findViewById(0x7f0a004a);
            textview = (TextView)view1.findViewById(0x7f0a004b);
            mtvcprobminfo.getCproBMType();
            JVM INSTR tableswitch 0 4: default 104
        //                       0 142
        //                       1 152
        //                       2 104
        //                       3 162
        //                       4 162;
               goto _L3 _L4 _L5 _L3 _L6 _L6
_L3:
            textview.setText((new StringBuilder()).append(" ").append(mtvcprobminfo.getTitle()).toString());
            view1.setTag(mtvcprobminfo);
_L2:
            return view1;
_L4:
            imageview.setImageResource(0x7f0200bb);
            continue; /* Loop/switch isn't completed */
_L5:
            imageview.setImageResource(0x7f0200bc);
            continue; /* Loop/switch isn't completed */
_L6:
            imageview.setImageResource(0x7f0200c2);
            if(true) goto _L3; else goto _L7
_L7:
        }

        private MtvCProBMInfo mMtvLinks[];
        final MtvUiFragTVLinkList this$0;

        public TvLinkAdapter(Context context, int i, MtvCProBMInfo amtvcprobminfo[])
        {
            this$0 = MtvUiFragTVLinkList.this;
            super(context, i, amtvcprobminfo);
            mMtvLinks = amtvcprobminfo;
        }
    }

    public static class TvLinkDialogFragment extends DialogFragment
    {

        public static TvLinkDialogFragment newInstance(int i, int j, int k)
        {
            com/samsung/sec/mtv/ui/channelguide/MtvUiFragTVLinkList$TvLinkDialogFragment;
            JVM INSTR monitorenter ;
            TvLinkDialogFragment tvlinkdialogfragment;
            tvlinkdialogfragment = new TvLinkDialogFragment();
            tvlinkdialogfragment.dialogId = i;
            tvlinkdialogfragment.positionOrLinkIndex = j;
            tvlinkdialogfragment.mLinkId = k;
            com/samsung/sec/mtv/ui/channelguide/MtvUiFragTVLinkList$TvLinkDialogFragment;
            JVM INSTR monitorexit ;
            return tvlinkdialogfragment;
            Exception exception;
            exception;
            throw exception;
        }

        public Dialog createDialog(int i, int j, MtvCProBMInfo mtvcprobminfo)
        {
            Object obj = null;
            i;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 35
        //                       2 105
        //                       3 209;
               goto _L1 _L2 _L3 _L4
_L1:
            return ((Dialog) (obj));
_L2:
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    k;
                    JVM INSTR tableswitch 0 1: default 24
                //                               0 25
                //                               1 58;
                       goto _L1 _L2 _L3
_L1:
                    return;
_L2:
                    (new MTvlinkDetail(mLink.getID())).show(getFragmentManager(), MTvlinkDetail.TAG);
                    continue; /* Loop/switch isn't completed */
_L3:
                    TvLinkDialogFragment.newInstance(3, -1, mLinkId).show(getFragmentManager(), "TvLinkDialogFragment");
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final TvLinkDialogFragment this$0;

                _cls1()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle((new StringBuilder()).append("Link").append(1 + positionOrLinkIndex).toString()).setItems(0x7f050014, new _cls1()).create();
            ((Dialog) (obj)).getWindow().addFlags(1024);
            continue; /* Loop/switch isn't completed */
_L3:
            class _cls2
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

                final TvLinkDialogFragment this$0;

                _cls2()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            class _cls4
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                    if(positionOrLinkIndex == 2)
                    {
                        Uri uri = Uri.parse(mLink.getDstURI());
                        if(uri != null)
                        {
                            Intent intent = new Intent("android.intent.action.VIEW", uri);
                            if(MtvUtilAppService.isIntentAvailable(getActivity(), intent))
                                startActivity(intent);
                            else
                                MtvUtilDebug.Error("TvLinkDialogFragment", "Activity Not Found !!!");
                        }
                    }
                }

                final TvLinkDialogFragment this$0;

                _cls4()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            class _cls3
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

                final TvLinkDialogFragment this$0;

                _cls3()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            if(positionOrLinkIndex == -1)
                obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(getString(0x7f070281)).setPositiveButton(0x7f070034, new _cls2()).create();
            else
                obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(getString(0x7f070044)).setPositiveButton(0x7f070034, new _cls4()).setNegativeButton(0x7f070035, new _cls3()).create();
            continue; /* Loop/switch isn't completed */
_L4:
            class _cls6
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    MtvBMLManager.deleteCProBMInfo(mLink.getID());
                    MtvUiFrag mtvuifrag = (MtvUiFrag)getFragmentManager().findFragmentByTag("tvlink");
                    if(mtvuifrag != null)
                        mtvuifrag.onUpdate(1, null);
                }

                final TvLinkDialogFragment this$0;

                _cls6()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            class _cls5
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

                final TvLinkDialogFragment this$0;

                _cls5()
                {
                    this$0 = TvLinkDialogFragment.this;
                    super();
                }
            }

            obj = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f07028d).setIcon(0x7f02008e).setMessage(0x7f07029b).setPositiveButton(0x7f070034, new _cls6()).setNegativeButton(0x7f070035, new _cls5()).create();
            ((Dialog) (obj)).getWindow().addFlags(1024);
            if(true) goto _L1; else goto _L5
_L5:
        }

        public void onCreate(Bundle bundle)
        {
            super.onCreate(bundle);
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            if(bundle != null)
            {
                dialogId = bundle.getInt("dialogId");
                positionOrLinkIndex = bundle.getInt("positionOrLinkIndex");
                mLinkId = bundle.getInt("mLinkId");
            }
            try
            {
                mLink = MtvBMLManager.getAvailableCProBMInfo(mLinkId);
            }
            catch(SQLiteException sqliteexception)
            {
                MtvUtilDebug.Error("TvLinkDialogFragment", "SQLiteException while getAvailableCProBMInfo invoded!");
            }
            catch(IOException ioexception)
            {
                MtvUtilDebug.Error("TvLinkDialogFragment", "IOException while getAvailableCProBMInfo invoded!");
            }
            return createDialog(dialogId, positionOrLinkIndex, mLink);
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putInt("dialogId", dialogId);
            bundle.putInt("positionOrLinkIndex", positionOrLinkIndex);
            bundle.putInt("mLinkId", mLinkId);
        }

        private int dialogId;
        private MtvCProBMInfo mLink;
        private int mLinkId;
        private int positionOrLinkIndex;




        public TvLinkDialogFragment()
        {
        }
    }


    public MtvUiFragTVLinkList()
    {
        mLinks = null;
        mListView = null;
        mUriString = null;
        mLinkIndex = -1;
        mTvLinkDetail = null;
        mLink = null;
        mLinkSelectedIndex = 0;
    }

    private void populateTVLinkList()
    {
        MtvUtilDebug.Low(TAG, "populateTVLinkList");
        mListView.setVisibility(0);
        mListView.bringToFront();
        mListView.setBackgroundColor(0xff000000);
        mLinks = MtvBMLManager.getAvailableCProBMInfoAll();
        if(mLinks != null && mLinks.length != 0)
            mLinkAdapter = new TvLinkAdapter(mActivity.getApplicationContext(), 0x7f03000f, mLinks);
        else
            mLinkAdapter = null;
        mListView.setAdapter(mLinkAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        populateTVLinkList();
        super.onActivityResult(i, j, intent);
    }

    public void onCreate(Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, "onCreate called...");
        super.onCreate(bundle);
        mActivity = getActivity();
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        MtvUtilDebug.Low(TAG, "onCreateOptionsMenu called...");
        menu.clear();
        if(mLinks != null && mLinks.length > 0)
            menu.add(0, 0, 0, 0x7f07028d).setIcon(0x7f020100);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, "onCreateView called :");
        mLayoutView = layoutinflater.inflate(0x7f030020, viewgroup, false);
        mListView = (ListView)mLayoutView.findViewById(0x7f0a00a0);
        ((ImageView)mLayoutView.findViewById(0x7f0a00a2)).setAlpha(90);
        LinearLayout linearlayout = (LinearLayout)mLayoutView.findViewById(0x7f0a00a1);
        mListView.setEmptyView(linearlayout);
        populateTVLinkList();
        return mLayoutView;
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        mTvLinkDetail = (MtvCProBMInfo)view.getTag();
        mLink = (MtvCProBMInfo)view.getTag();
        mLinkIndex = -1;
        if(mLink.getValidDate().getTime() >= System.currentTimeMillis()) goto _L2; else goto _L1
_L1:
        TvLinkDialogFragment.newInstance(2, -1, mLink.getID()).show(getFragmentManager(), "TvLinkDialogFragment");
_L8:
        return;
_L2:
        if(mLink.getCproBMType() != 1) goto _L4; else goto _L3
_L3:
        mUriString = mLink.getDstURI();
        mLinkIndex = 1;
_L6:
        mLinkSelectedIndex = i;
        if(mLink.getCproBMType() != 1)
            break; /* Loop/switch isn't completed */
        Intent intent = new Intent(getActivity(), com/samsung/sec/mtv/ui/tvlink/MtvUiTvLinks);
        intent.putExtra("MtvSelectLink", mLinkSelectedIndex);
        startActivity(intent);
        continue; /* Loop/switch isn't completed */
_L4:
        if(mLink.getCproBMType() == 4 || mLink.getCproBMType() == 3)
        {
            mUriString = mLink.getDstURI();
            mLinkIndex = 2;
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(mLinkIndex == 2)
            TvLinkDialogFragment.newInstance(2, mLinkIndex, mLink.getID()).show(getFragmentManager(), "TvLinkDialogFragment");
        else
        if(mLink.getCproBMType() != 0)
        {
            Intent intent1 = new Intent(getActivity(), com/samsung/sec/mtv/ui/tvlink/MtvUiTvLinks);
            intent1.putExtra("MtvSelectLink", mLinkSelectedIndex);
            startActivity(intent1);
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        mTvLinkDetail = (MtvCProBMInfo)view.getTag();
        if(mTvLinkDetail == null)
            MtvUtilDebug.Error(TAG, "TabLink :onItemLongClick invalid tag");
        else
            TvLinkDialogFragment.newInstance(1, i, mTvLinkDetail.getID()).show(getFragmentManager(), "TvLinkDialogFragment");
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 0 0: default 24
    //                   0 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        Intent intent = new Intent(mActivity, com/samsung/sec/mtv/ui/common/MtvUiRemoveList);
        intent.putExtra("Remove_List_Type", 104);
        startActivityForResult(intent, 0);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onUpdate(int i, Object obj)
    {
        i;
        JVM INSTR tableswitch 1 1: default 20
    //                   1 21;
           goto _L1 _L2
_L1:
        return;
_L2:
        populateTVLinkList();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static String TAG = "MtvUiFragTVLinkList";
    private Activity mActivity;
    private View mLayoutView;
    protected MtvCProBMInfo mLink;
    private TvLinkAdapter mLinkAdapter;
    protected int mLinkIndex;
    private int mLinkSelectedIndex;
    protected MtvCProBMInfo mLinks[];
    private ListView mListView;
    protected MtvCProBMInfo mTvLinkDetail;
    protected String mUriString;

}
