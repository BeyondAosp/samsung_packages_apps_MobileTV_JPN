// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.text.format.DateFormat;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.provider.MtvFile;
import com.samsung.sec.mtv.provider.MtvFileManager;
import com.samsung.sec.mtv.ui.common.*;
import com.samsung.sec.mtv.ui.fileplayer.MtvUiFilePlayer;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilCrypto;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.samsung.sec.mtv.ui.channelguide:
//            MtvUiChannelGuide

public class MtvUiFragTVFilesList extends MtvUiFrag
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener
{
    private class MtvFileAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = view;
            if(view1 == null)
                view1 = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f030030, null);
            mPreImageView = (ImageView)view1.findViewById(0x7f0a0119);
            mPlayImageView = (ImageView)view1.findViewById(0x7f0a011a);
            MtvFile mtvfile = mFileList[i];
            if(mtvfile != null)
            {
                TextView textview = (TextView)view1.findViewById(0x7f0a011e);
                TextView textview1 = (TextView)view1.findViewById(0x7f0a0120);
                TextView textview2 = (TextView)view1.findViewById(0x7f0a0121);
                view1.findViewById(0x7f0a0048).setVisibility(8);
                String s;
                String s1;
                if(DateFormat.is24HourFormat(getActivity().getApplicationContext()))
                    s = (new SimpleDateFormat("M/d (EEE) H:mm")).format(mtvfile.getCreationTime());
                else
                    s = (new SimpleDateFormat("M/d (EEE) h:mm a")).format(mtvfile.getCreationTime());
                if(getResources().getConfiguration().orientation == 1)
                {
                    textview1.setText(s);
                    textview2.setText(mtvfile.getChannelName());
                } else
                {
                    textview1.setText((new StringBuilder()).append(s).append(" ").append(mtvfile.getChannelName()).toString());
                }
                if(mtvfile.getProgramName() == null || mtvfile.getProgramName().equals("null"))
                    s1 = getResources().getString(0x7f0700ce);
                else
                    s1 = mtvfile.getProgramName();
                textview.setText(s1);
                view1.setTag(mtvfile);
                setFileInfo(mtvfile);
            }
            return view1;
        }

        private MtvFile mFileList[];
        final MtvUiFragTVFilesList this$0;

        public MtvFileAdapter(Context context, int i)
        {
            this$0 = MtvUiFragTVFilesList.this;
            super(context, i);
            mFileList = null;
        }

        public MtvFileAdapter(Context context, int i, MtvFile amtvfile[])
        {
            this$0 = MtvUiFragTVFilesList.this;
            super(context, i, amtvfile);
            mFileList = null;
            mFileList = amtvfile;
        }
    }

    public static class ViewDetailsFragment extends DialogFragment
    {

        private Dialog buildDialog(ArrayList arraylist)
        {
            Activity activity = getActivity();
            String as[] = new String[2];
            as[0] = "title";
            as[1] = "value";
            int ai[] = new int[2];
            ai[0] = 0x7f0a0123;
            ai[1] = 0x7f0a0124;
            SimpleAdapter simpleadapter = new SimpleAdapter(activity, arraylist, 0x7f030031, as, ai);
            class _cls1
                implements android.content.DialogInterface.OnClickListener
            {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if(dialoginterface != null)
                        dialoginterface.dismiss();
                }

                final ViewDetailsFragment this$0;

                _cls1()
                {
                    this$0 = ViewDetailsFragment.this;
                    super();
                }
            }

            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(getActivity())).setTitle(0x7f0702bf).setAdapter(simpleadapter, null).setNegativeButton(0x7f070034, new _cls1()).create();
            alertdialog.requestWindowFeature(1);
            alertdialog.getWindow().setFlags(1024, 1024);
            return alertdialog;
        }

        private ArrayList constructDetails()
        {
            ArrayList arraylist = new ArrayList();
            String s = getString(0x7f0700d9);
            String s1 = getFileFormatString();
            HashMap hashmap = new HashMap();
            hashmap.put("title", s);
            hashmap.put("value", s1);
            arraylist.add(hashmap);
            String s2 = getString(0x7f0700dd);
            String s3 = getFileSizeString();
            HashMap hashmap1 = new HashMap();
            hashmap1.put("title", s2);
            hashmap1.put("value", s3);
            arraylist.add(hashmap1);
            String s4 = getString(0x7f0700de);
            String s5 = getFileLocationString();
            HashMap hashmap2 = new HashMap();
            hashmap2.put("title", s4);
            hashmap2.put("value", s5);
            arraylist.add(hashmap2);
            return arraylist;
        }

        private String getFileFormatString()
        {
            mMtvFile.getFileFormat();
            JVM INSTR tableswitch 0 2: default 32
        //                       0 61
        //                       1 51
        //                       2 41;
               goto _L1 _L2 _L3 _L4
_L1:
            String s = getString(0x7f0700dc);
_L6:
            return s;
_L4:
            s = getString(0x7f0700da);
            continue; /* Loop/switch isn't completed */
_L3:
            s = getString(0x7f0700db);
            continue; /* Loop/switch isn't completed */
_L2:
            s = getString(0x7f0700db);
            if(true) goto _L6; else goto _L5
_L5:
        }

        private String getFileLocationString()
        {
            String s;
            if(mMtvFile.getFilePath().contains("PhMem") || mMtvFile.getFilePath().contains("Phone") || mMtvFile.getFilePath().contains("/sdcard/"))
                s = getString(0x7f0700b4);
            else
                s = getString(0x7f0700b5);
            return s;
        }

        private String getFileSizeString()
        {
            DecimalFormat decimalformat = new DecimalFormat("###,###.##");
            String s;
            if(mMtvFile.getFileSize() < 0x100000L)
                s = (new StringBuilder()).append(decimalformat.format((double)mMtvFile.getFileSize() / 1024D)).append(getString(0x7f0700df)).toString();
            else
                s = (new StringBuilder()).append(decimalformat.format((double)mMtvFile.getFileSize() / 1048576D)).append(getString(0x7f0700e0)).toString();
            return s;
        }

        public Dialog onCreateDialog(Bundle bundle)
        {
            if(bundle != null)
                mMtvFile = (MtvFile)bundle.getSerializable("mMtvFile");
            return buildDialog(constructDetails());
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            bundle.putSerializable("mMtvFile", mMtvFile);
        }

        private MtvFile mMtvFile;

        public ViewDetailsFragment()
        {
        }

        public ViewDetailsFragment(MtvFile mtvfile)
        {
            this();
            mMtvFile = mtvfile;
            MtvUtilDebug.Low("ViewDetailsFragment", (new StringBuilder()).append("mMTvFile :\n").append(mtvfile).toString());
        }
    }


    public MtvUiFragTVFilesList()
    {
        mMtvFileList = null;
        adapter = null;
        mProgressDialog = null;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
            }

            final MtvUiFragTVFilesList this$0;

            
            {
                this$0 = MtvUiFragTVFilesList.this;
                super();
            }
        };
        mTimedRemoveDialog = new Runnable() {

            public void run()
            {
                MtvUtilDebug.Low(MtvUiFragTVFilesList.TAG, "Remove dialog progress");
                mHandler.removeCallbacks(mTimedRemoveDialog);
                if(mProgressDialog != null)
                {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
                populateTVFileList();
            }

            final MtvUiFragTVFilesList this$0;

            
            {
                this$0 = MtvUiFragTVFilesList.this;
                super();
            }
        };
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
        catch(IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        catch(NullPointerException nullpointerexception2)
        {
            nullpointerexception2.printStackTrace();
        }
_L1:
        return bitmap;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L4:
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
        filenotfoundexception;
        fileinputstream = fileinputstream1;
          goto _L4
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
        MtvUtilDebug.Low(TAG, (new StringBuilder()).append("getPreviewThumbnailPath = ").append(s1).append(s2).toString());
        return (new StringBuilder()).append(s1).append(s2).toString();
    }

    private void populateTVFileList()
    {
        MtvUtilDebug.Low(TAG, "populateTVFileList");
        mListView = (ListView)mLayoutView.findViewById(0x7f0a009c);
        mMtvFileList = MtvFileManager.getAvailableTVRecFilesEx();
        if(mMtvFileList != null && mMtvFileList.length != 0)
            adapter = new MtvFileAdapter(mActivity.getApplicationContext(), 0x7f030030, mMtvFileList);
        else
            adapter = new MtvFileAdapter(mActivity.getApplicationContext(), 0x7f030030);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        ((ImageView)mLayoutView.findViewById(0x7f0a009e)).setAlpha(90);
        mListView.setEmptyView(mLayoutView.findViewById(0x7f0a009d));
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
    //                   1 307
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
        Exception exception2;
        Exception exception3;
        FileNotFoundException filenotfoundexception1;
        IOException ioexception7;
        if(fileinputstream3 != null)
            try
            {
                fileinputstream3.close();
            }
            catch(IOException ioexception5)
            {
                ioexception5.printStackTrace();
            }
          goto _L4
        ioexception7;
        ioexception7.printStackTrace();
          goto _L8
        filenotfoundexception1;
        fileinputstream2 = fileinputstream3;
_L16:
        filenotfoundexception1.printStackTrace();
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
        exception3;
        exception3.printStackTrace();
          goto _L8
        exception2;
        fileinputstream2 = fileinputstream3;
_L15:
        if(fileinputstream2 != null)
            try
            {
                fileinputstream2.close();
            }
            catch(IOException ioexception4)
            {
                ioexception4.printStackTrace();
            }
        throw exception2;
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
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
          goto _L4
        IOException ioexception3;
        ioexception3;
        ioexception3.printStackTrace();
          goto _L11
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        fileinputstream = fileinputstream1;
_L14:
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
        Exception exception1;
        exception1;
        exception1.printStackTrace();
          goto _L11
        Exception exception;
        exception;
        fileinputstream = fileinputstream1;
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
        throw exception;
        exception;
        if(true) goto _L13; else goto _L12
_L12:
        filenotfoundexception;
          goto _L14
        exception2;
          goto _L15
        filenotfoundexception1;
          goto _L16
    }

    private void startFilePlayer(MtvFile mtvfile)
    {
        Intent intent = new Intent(mActivity.getApplicationContext(), com/samsung/sec/mtv/ui/fileplayer/MtvUiFilePlayer);
        MtvUtilDebug.Low(TAG, "startFilePlayer");
        intent.putExtra("MtvFile", mtvfile);
        startActivityForResult(intent, 0);
    }

    public void onClick(View view)
    {
        if(mMtvFileList != null && mMtvFileList.length > 0)
        {
            MtvFile mtvfile = (MtvFile)view.getTag();
            mListView.setOnItemClickListener(null);
            mPreImageView.setOnClickListener(null);
            if(mtvfile != null)
                startFilePlayer(mtvfile);
        }
    }

    public void onCreate(Bundle bundle)
    {
        MtvUtilDebug.Low(TAG, "onCreate called...");
        mPreferences = new MtvPreferences(getActivity().getApplicationContext());
        mActivity = getActivity();
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mLayoutView = layoutinflater.inflate(0x7f03001e, viewgroup, false);
        return mLayoutView;
    }

    public void onDestroy()
    {
        mPreferences = null;
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        adapter = null;
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        MtvUtilDebug.Low(TAG, "onItemClick");
        startFilePlayer((MtvFile)view.getTag());
        mPreferences.setLatestFileIndex(i);
        adapter.notifyDataSetChanged();
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        if(mMtvFileList != null && mMtvFileList.length > 0)
        {
            Bundle bundle = new Bundle();
            MtvUtilDebug.Low(TAG, "onItemLongClick");
            sSelectedFile = (MtvFile)view.getTag();
            mPreferences.setSelectedFileIndex(i);
            mPreferences.setLatestFileIndex(i);
            adapter.notifyDataSetChanged();
            String s;
            if(sSelectedFile.getProgramName() == null || sSelectedFile.getProgramName().equals("null"))
                s = getResources().getString(0x7f0700ce);
            else
                s = sSelectedFile.getProgramName();
            bundle.putInt("dialogType", 3);
            bundle.putString("title", (new StringBuilder()).append(sSelectedFile.getChannelName()).append(" ").append(s).toString());
            MtvUiDialogsFrag.newInstance(bundle).show(getActivity().getFragmentManager(), "dialog");
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 0 1: default 28
    //                   0 30
    //                   1 43;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        ((MtvUiChannelGuide)getActivity()).addMemInfoFrag();
        continue; /* Loop/switch isn't completed */
_L3:
        Intent intent = new Intent(mActivity, com/samsung/sec/mtv/ui/common/MtvUiRemoveList);
        intent.putExtra("Remove_List_Type", 103);
        startActivityForResult(intent, 0);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        menu.add(0, 0, 0, 0x7f07028b).setIcon(0x7f020101);
        if(mMtvFileList != null && mMtvFileList.length > 0)
            menu.add(0, 1, 0, 0x7f07028d).setIcon(0x7f020100);
    }

    public void onResume()
    {
        MtvUtilDebug.Low(TAG, "onResume called");
        populateTVFileList();
        super.onResume();
    }

    public void onStop()
    {
        super.onStop();
        adapter = null;
    }

    public void onUpdate(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   1: 67
    //                   2: 171
    //                   3: 148
    //                   4: 222
    //                   115: 178
    //                   127: 185;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        super.onUpdate(i, obj);
        return;
_L2:
        Bundle bundle = new Bundle();
        bundle.putInt("dialogType", 4);
        bundle.putString("filePath", sSelectedFile.getFilePath());
        bundle.putInt("index", sSelectedFile.getIndex());
        bundle.putInt("fileType", sSelectedFile.getFileFormat());
        MtvUiDialogsFrag.newInstance(bundle).show(getActivity().getFragmentManager(), "dialog");
        continue; /* Loop/switch isn't completed */
_L4:
        (new ViewDetailsFragment(sSelectedFile)).show(getFragmentManager(), "ViewDetailsFragment");
        continue; /* Loop/switch isn't completed */
_L3:
        populateTVFileList();
        continue; /* Loop/switch isn't completed */
_L6:
        populateTVFileList();
        continue; /* Loop/switch isn't completed */
_L7:
        if(mProgressDialog != null)
        {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mHandler.removeCallbacks(mTimedRemoveDialog);
        populateTVFileList();
        continue; /* Loop/switch isn't completed */
_L5:
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(0x7f07028d);
        mProgressDialog.setMessage(getString(0x7f07029d));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mHandler.removeCallbacks(mTimedRemoveDialog);
        mHandler.postDelayed(mTimedRemoveDialog, 5000L);
        if(true) goto _L1; else goto _L8
_L8:
    }

    private static String TAG = "MtvUiFragTVFilesList";
    private static MtvFile sSelectedFile = null;
    MtvFileAdapter adapter;
    private Activity mActivity;
    private Handler mHandler;
    private View mLayoutView;
    private ListView mListView;
    private MtvFile mMtvFileList[];
    private ImageView mPlayImageView;
    private ImageView mPreImageView;
    private MtvPreferences mPreferences;
    private ProgressDialog mProgressDialog;
    private Runnable mTimedRemoveDialog;







/*
    static ProgressDialog access$302(MtvUiFragTVFilesList mtvuifragtvfileslist, ProgressDialog progressdialog)
    {
        mtvuifragtvfileslist.mProgressDialog = progressdialog;
        return progressdialog;
    }

*/



/*
    static ImageView access$502(MtvUiFragTVFilesList mtvuifragtvfileslist, ImageView imageview)
    {
        mtvuifragtvfileslist.mPreImageView = imageview;
        return imageview;
    }

*/


/*
    static ImageView access$602(MtvUiFragTVFilesList mtvuifragtvfileslist, ImageView imageview)
    {
        mtvuifragtvfileslist.mPlayImageView = imageview;
        return imageview;
    }

*/

}
