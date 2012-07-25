// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.utility.MtvUtilAppService;
import java.util.HashMap;

// Referenced classes of package com.samsung.sec.mtv.ui.bml:
//            IMtvUiBmlActivity

public class MtvUiBmlNumKeyPadFragment extends MtvUiFrag
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener, android.view.View.OnTouchListener
{

    public MtvUiBmlNumKeyPadFragment()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;-><init>()V");
        super();
        mCntrlFragView = null;
        mListener = null;
    }

    public static void hide()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->hide()V");
        if(mFragHandler != null)
            mFragHandler.removeFrag(7);
    }

    public static void setAppcomponents(MtvAppBml mtvappbml, MtvUiFragHandler mtvuifraghandler)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->setAppcomponents(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;)V");
        mBmlApp = mtvappbml;
        mFragHandler = mtvuifraghandler;
    }

    public static void show()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->show()V");
        if(mFragHandler != null)
            mFragHandler.addFrag(7, -1L, false, new int[0]);
    }

    public void init()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->init()V");
        MtvUtilDebug.Low("MtvUiBmlNumKeyPadFragment", "init: ");
        for(int i = 0; i < RSRC_NUMERIC_BUTTON_KEY.length; i++)
            mKeyMap.put(Integer.valueOf(RSRC_NUMERIC_BUTTON_KEY[i]), Integer.valueOf(i));

        for(int j = 0; j < RSRC_NUMERIC_BUTTON_KEY.length; j++)
            mCntrlFragView.findViewById(RSRC_NUMERIC_BUTTON_KEY[j]).setOnClickListener(this);

        for(int k = 0; k < RSRC_NUMERIC_BUTTON_KEY.length; k++)
            mCntrlFragView.findViewById(RSRC_NUMERIC_BUTTON_KEY[k]).setOnTouchListener(this);

    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onClick(Landroid/view/View;)V");
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onCreateOptionsMenu(Landroid/view/Menu;Landroid/view/MenuInflater;)V");
        super.onCreateOptionsMenu(menu, menuinflater);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mCntrlFragView = layoutinflater.inflate(0x7f03001b, viewgroup, false);
        init();
        return mCntrlFragView;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onDestroy()V");
        super.onDestroy();
    }

    public boolean onLongClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onLongClick(Landroid/view/View;)Z");
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onOptionsItemSelected(Landroid/view/MenuItem;)Z");
        MtvUtilDebug.Low("MtvUiBmlNumKeyPadFragment", (new StringBuilder()).append("onSelected item=").append(menuitem).toString());
        char c;
        if(menuitem.getItemId() == 13)
            c = '\323';
        else
        if(menuitem.getItemId() == 14)
        {
            c = '\324';
        } else
        {
label0:
            {
                if(menuitem.getItemId() != 15)
                    break label0;
                c = '\325';
            }
        }
        mListener.onFragEvent(c, null);
        flag = true;
_L2:
        return flag;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onPrepareOptionsMenu(Landroid/view/Menu;)V");
        MtvUtilDebug.Low("MtvUiBmlNumKeyPadFragment", "onCreateOptionsMenu");
        if(MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) == 0)
        {
            MtvUtilDebug.Low("MtvUiBmlNumKeyPadFragment", "onCreateOptionsMenu portrait");
            if(((IMtvUiBmlActivity)getActivity()).isBmlFullView() && mFragHandler != null && mFragHandler.getActivityType() == 0)
            {
                MtvUtilDebug.Low("MtvUiBmlNumKeyPadFragment", "onCreateOptionsMenu: isBmlFullView TRUE ");
                menu.clear();
                menu.add(0, 14, 0, getActivity().getApplicationContext().getString(0x7f070037)).setIcon(0x7f0200fc);
                menu.add(0, 15, 0, getActivity().getApplicationContext().getString(0x7f070088)).setIcon(0x7f02010b);
                menu.add(0, 13, 0, getActivity().getApplicationContext().getString(0x7f070097)).setIcon(0x7f020107);
            }
        }
        super.onPrepareOptionsMenu(menu);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onSaveInstanceState(Landroid/os/Bundle;)V");
        super.onSaveInstanceState(bundle);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
        view.getId();
        JVM INSTR tableswitch 2131361921 2131361932: default 76
    //                   2131361921 106
    //                   2131361922 134
    //                   2131361923 162
    //                   2131361924 190
    //                   2131361925 218
    //                   2131361926 246
    //                   2131361927 274
    //                   2131361928 302
    //                   2131361929 330
    //                   2131361930 358
    //                   2131361931 78
    //                   2131361932 386;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        return false;
_L12:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 7));
        continue; /* Loop/switch isn't completed */
_L2:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 8));
        continue; /* Loop/switch isn't completed */
_L3:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 9));
        continue; /* Loop/switch isn't completed */
_L4:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 10));
        continue; /* Loop/switch isn't completed */
_L5:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 11));
        continue; /* Loop/switch isn't completed */
_L6:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 12));
        continue; /* Loop/switch isn't completed */
_L7:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 13));
        continue; /* Loop/switch isn't completed */
_L8:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 14));
        continue; /* Loop/switch isn't completed */
_L9:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 15));
        continue; /* Loop/switch isn't completed */
_L10:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 16));
        continue; /* Loop/switch isn't completed */
_L11:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 17));
        continue; /* Loop/switch isn't completed */
_L13:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 18));
        if(true) goto _L1; else goto _L14
_L14:
    }

    private static final int RSRC_NUMERIC_BUTTON_KEY[];
    private static MtvAppBml mBmlApp = null;
    private static MtvUiFragHandler mFragHandler = null;
    private View mCntrlFragView;
    private final HashMap mKeyMap = new HashMap();
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlNumKeyPadFragment;-><clinit>()V");
        int ai[] = new int[12];
        ai[0] = 0x7f0a008b;
        ai[1] = 0x7f0a0081;
        ai[2] = 0x7f0a0082;
        ai[3] = 0x7f0a0083;
        ai[4] = 0x7f0a0084;
        ai[5] = 0x7f0a0085;
        ai[6] = 0x7f0a0086;
        ai[7] = 0x7f0a0087;
        ai[8] = 0x7f0a0088;
        ai[9] = 0x7f0a0089;
        ai[10] = 0x7f0a008a;
        ai[11] = 0x7f0a008c;
        RSRC_NUMERIC_BUTTON_KEY = ai;
    }
}
