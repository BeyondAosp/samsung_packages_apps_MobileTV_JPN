// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.utility.MtvUtilAppService;

// Referenced classes of package com.samsung.sec.mtv.ui.bml:
//            IMtvUiBmlActivity

public class MtvUiBmlBasicControlFrag extends MtvUiFrag
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener, android.view.View.OnTouchListener
{

    public MtvUiBmlBasicControlFrag()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;-><init>()V");
        super();
        mHandler = new Handler();
        mBtnCntrlUp = null;
        mBtnCntrlDown = null;
        mBtnCntrlSelect = null;
        mBtnCntrlBack = null;
        mCntrlFragView = null;
        mListener = null;
        mRunnableBMLCtrlUp = new _cls1();
        mRunnableBMLCtrlDown = new _cls2();
    }

    public static void hide()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->hide()V");
        if(mFragHandler != null)
            mFragHandler.removeFrag(8);
    }

    public static void setAppcomponents(MtvAppBml mtvappbml, MtvUiFragHandler mtvuifraghandler)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->setAppcomponents(Lcom/samsung/sec/mtv/app/bml/MtvAppBml;Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;)V");
        mBmlApp = mtvappbml;
        mFragHandler = mtvuifraghandler;
    }

    public static void show()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->show()V");
        if(mFragHandler != null)
            mFragHandler.addFrag(8, -1L, false, new int[0]);
    }

    public void cancelBMLLongClick()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->cancelBMLLongClick()V");
        if(mHandler != null)
        {
            mHandler.removeCallbacks(mRunnableBMLCtrlUp);
            MtvUtilDebug.Mid("MtvUiBmlBasicControlFrag", "ACTION_UP. removeCallbacks Left");
            mHandler.removeCallbacks(mRunnableBMLCtrlDown);
        }
    }

    public void init()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->init()V");
        RelativeLayout relativelayout = (RelativeLayout)mCntrlFragView.findViewById(0x7f0a007b);
        if(relativelayout != null)
        {
            relativelayout.setOnTouchListener(this);
            relativelayout.setOnLongClickListener(this);
        }
        mBtnCntrlUp = (ImageButton)mCntrlFragView.findViewById(0x7f0a007c);
        if(mBtnCntrlUp != null)
        {
            mBtnCntrlUp.setOnTouchListener(this);
            mBtnCntrlUp.setOnLongClickListener(this);
        }
        mBtnCntrlDown = (ImageButton)mCntrlFragView.findViewById(0x7f0a007d);
        if(mBtnCntrlDown != null)
        {
            mBtnCntrlDown.setOnTouchListener(this);
            mBtnCntrlDown.setOnLongClickListener(this);
        }
        mBtnCntrlSelect = (Button)mCntrlFragView.findViewById(0x7f0a007e);
        if(mBtnCntrlSelect != null)
            mBtnCntrlSelect.setOnTouchListener(this);
        mBtnCntrlBack = (Button)mCntrlFragView.findViewById(0x7f0a007f);
        if(mBtnCntrlBack != null)
            mBtnCntrlBack.setOnTouchListener(this);
    }

    public void onAttach(Activity activity)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onAttach(Landroid/app/Activity;)V");
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onClick(Landroid/view/View;)V");
    }

    public void onCreate(Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onCreate(Landroid/os/Bundle;)V");
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onCreateOptionsMenu(Landroid/view/Menu;Landroid/view/MenuInflater;)V");
        super.onCreateOptionsMenu(menu, menuinflater);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;");
        mCntrlFragView = layoutinflater.inflate(0x7f030017, viewgroup, false);
        init();
        return mCntrlFragView;
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onDestroy()V");
        if(mHandler != null)
            mHandler = null;
        if(mBtnCntrlUp != null)
            mBtnCntrlUp = null;
        if(mBtnCntrlDown != null)
            mBtnCntrlDown = null;
        if(mBtnCntrlSelect != null)
            mBtnCntrlSelect = null;
        if(mBtnCntrlBack != null)
            mBtnCntrlBack = null;
        if(mCntrlFragView != null)
            mCntrlFragView = null;
        super.onDestroy();
    }

    public boolean onLongClick(View view)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onLongClick(Landroid/view/View;)Z");
        if(0x7f0a007c != view.getId()) goto _L2; else goto _L1
_L1:
        mHandler.post(mRunnableBMLCtrlUp);
        MtvUtilDebug.Mid("MtvUiBmlBasicControlFrag", "mRunnableBMLCtrlUp RUN. ");
_L4:
        return true;
_L2:
        if(0x7f0a007d == view.getId())
        {
            mHandler.post(mRunnableBMLCtrlDown);
            MtvUtilDebug.Mid("MtvUiBmlBasicControlFrag", "mRunnableBMLCtrlDown RUN. ");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onOptionsItemSelected(Landroid/view/MenuItem;)Z");
        MtvUtilDebug.Low("MtvUiBmlBasicControlFrag", (new StringBuilder()).append("onSelected item=").append(menuitem).toString());
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
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onPrepareOptionsMenu(Landroid/view/Menu;)V");
        MtvUtilDebug.Low("MtvUiBmlBasicControlFrag", "onCreateOptionsMenu");
        if(MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) == 0)
        {
            MtvUtilDebug.Low("MtvUiBmlBasicControlFrag", "onCreateOptionsMenu portrait");
            if(((IMtvUiBmlActivity)getActivity()).isBmlFullView() && mFragHandler != null && mFragHandler.getActivityType() == 0)
            {
                MtvUtilDebug.Low("MtvUiBmlBasicControlFrag", "onCreateOptionsMenu: isBmlFullView TRUE ");
                menu.clear();
                menu.add(0, 14, 0, getActivity().getApplicationContext().getString(0x7f070037)).setIcon(0x7f0200fc);
                menu.add(0, 15, 0, getActivity().getApplicationContext().getString(0x7f070088)).setIcon(0x7f02010b);
                menu.add(0, 13, 0, getActivity().getApplicationContext().getString(0x7f070097)).setIcon(0x7f020107);
            }
        }
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag;
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onTouch(Landroid/view/View;Landroid/view/MotionEvent;)Z");
        flag = true;
        i = view.getId();
        if(mFragHandler.isPhoneLocked() != flag) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiBmlBasicControlFrag", "onTouchEvent: Phone is locked return ");
_L9:
        return flag;
_L2:
        if((motionevent.getAction() == 3 || motionevent.getAction() == flag) && (0x7f0a007c == i || 0x7f0a007d == i))
            cancelBMLLongClick();
        i;
        JVM INSTR tableswitch 2131361916 2131361919: default 108
    //                   2131361916 114
    //                   2131361917 142
    //                   2131361918 197
    //                   2131361919 170;
           goto _L3 _L4 _L5 _L6 _L7
_L6:
        break MISSING_BLOCK_LABEL_197;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L10:
        flag = false;
        if(true) goto _L9; else goto _L8
_L8:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 19));
          goto _L10
_L5:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 20));
          goto _L10
_L7:
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 4));
          goto _L10
        if(mBmlApp != null)
            mBmlApp.onKeyEvent(new KeyEvent(motionevent.getAction(), 23));
          goto _L10
    }

    public void onUpdate(int i, Object obj)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->onUpdate(ILjava/lang/Object;)V");
        super.onUpdate(i, obj);
    }

    private static MtvAppBml mBmlApp = null;
    private static MtvUiFragHandler mFragHandler = null;
    private Button mBtnCntrlBack;
    private ImageButton mBtnCntrlDown;
    private Button mBtnCntrlSelect;
    private ImageButton mBtnCntrlUp;
    private View mCntrlFragView;
    private Handler mHandler;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;
    private Runnable mRunnableBMLCtrlDown;
    private Runnable mRunnableBMLCtrlUp;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;-><clinit>()V");
    }


/*
    static MtvAppBml access$000()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->access$000()Lcom/samsung/sec/mtv/app/bml/MtvAppBml;");
        return mBmlApp;
    }

*/


/*
    static Handler access$100(MtvUiBmlBasicControlFrag mtvuibmlbasiccontrolfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->access$100(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;)Landroid/os/Handler;");
        return mtvuibmlbasiccontrolfrag.mHandler;
    }

*/


/*
    static Runnable access$200(MtvUiBmlBasicControlFrag mtvuibmlbasiccontrolfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->access$200(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;)Ljava/lang/Runnable;");
        return mtvuibmlbasiccontrolfrag.mRunnableBMLCtrlUp;
    }

*/


/*
    static Runnable access$300(MtvUiBmlBasicControlFrag mtvuibmlbasiccontrolfrag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;->access$300(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;)Ljava/lang/Runnable;");
        return mtvuibmlbasiccontrolfrag.mRunnableBMLCtrlDown;
    }

*/

    private class _cls1
        implements Runnable
    {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag$1;->run()V");
            if(Log.d() != null)
                Log.d().onKeyEvent(new KeyEvent(0, 19));
            if(Log.d(MtvUiBmlBasicControlFrag.this) != null)
                Log.d(MtvUiBmlBasicControlFrag.this).postDelayed(Log.d(MtvUiBmlBasicControlFrag.this), 200L);
        }

        final MtvUiBmlBasicControlFrag this$0;

        _cls1()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag$1;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;)V");
            this$0 = MtvUiBmlBasicControlFrag.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag$2;->run()V");
            if(Log.d() != null)
                Log.d().onKeyEvent(new KeyEvent(0, 20));
            if(Log.d(MtvUiBmlBasicControlFrag.this) != null)
                Log.d(MtvUiBmlBasicControlFrag.this).postDelayed(Log.d(MtvUiBmlBasicControlFrag.this), 200L);
        }

        final MtvUiBmlBasicControlFrag this$0;

        _cls2()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag$2;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlBasicControlFrag;)V");
            this$0 = MtvUiBmlBasicControlFrag.this;
            super();
        }
    }

}
