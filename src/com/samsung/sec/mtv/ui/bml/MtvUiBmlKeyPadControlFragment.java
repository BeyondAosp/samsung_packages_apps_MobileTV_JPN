// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.utility.MtvUtilAppService;

// Referenced classes of package com.samsung.sec.mtv.ui.bml:
//            MtvUiBmlNumKeyPadFragment, IMtvUiBmlActivity

public class MtvUiBmlKeyPadControlFragment extends MtvUiFrag
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener, android.view.View.OnTouchListener
{

    public MtvUiBmlKeyPadControlFragment()
    {
        mBtnCntrKeyPad = null;
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
        if(mFragHandler != null)
            mFragHandler.removeFrag(9);
    }

    public static void setAppcomponents(MtvAppBml mtvappbml, MtvUiFragHandler mtvuifraghandler)
    {
        mBmlApp = mtvappbml;
        mFragHandler = mtvuifraghandler;
    }

    public static void show()
    {
        if(mFragHandler != null)
            mFragHandler.addFrag(9, -1L, false, new int[0]);
    }

    public void cancelBMLLongClick()
    {
        if(mHandler != null)
        {
            mHandler.removeCallbacks(mRunnableBMLCtrlUp);
            MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "ACTION_UP. removeCallbacks Left");
            mHandler.removeCallbacks(mRunnableBMLCtrlDown);
        }
    }

    public void init()
    {
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
        mBtnCntrKeyPad = (ImageButton)mCntrlFragView.findViewById(0x7f0a0080);
        if(mBtnCntrKeyPad != null)
            mBtnCntrKeyPad.setOnClickListener(this);
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
        JVM INSTR tableswitch 2131361920 2131361920: default 24
    //                   2131361920 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        if(mFragHandler.isFragPresent(7))
            MtvUiBmlNumKeyPadFragment.hide();
        else
            MtvUiBmlNumKeyPadFragment.show();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        super.onCreateOptionsMenu(menu, menuinflater);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mCntrlFragView = layoutinflater.inflate(0x7f030019, viewgroup, false);
        init();
        return mCntrlFragView;
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    public boolean onLongClick(View view)
    {
        MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "onLongClick. ");
        if(0x7f0a007c != view.getId()) goto _L2; else goto _L1
_L1:
        mHandler.post(mRunnableBMLCtrlUp);
        MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "mRunnableBMLCtrlUp RUN. ");
_L4:
        return true;
_L2:
        if(0x7f0a007d == view.getId())
        {
            mHandler.post(mRunnableBMLCtrlDown);
            MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "mRunnableBMLCtrlDown RUN. ");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag;
        MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", (new StringBuilder()).append("onSelected item=").append(menuitem).toString());
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
        MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "onCreateOptionsMenu");
        if(MtvUtilAppService.getCurrentOrientation(getActivity().getApplicationContext()) == 0)
        {
            MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "onCreateOptionsMenu portrait");
            if(((IMtvUiBmlActivity)getActivity()).isBmlFullView() && mFragHandler != null && mFragHandler.getActivityType() == 0)
            {
                MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "onCreateOptionsMenu: isBmlFullView TRUE ");
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
        super.onSaveInstanceState(bundle);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag;
        int i;
        flag = true;
        i = view.getId();
        if(mFragHandler.isPhoneLocked() != flag) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiBmlKeyPadControlFragment", "onTouchEvent: Phone is locked return ");
_L9:
        return flag;
_L2:
        if((motionevent.getAction() == 3 || motionevent.getAction() == flag) && (0x7f0a007c == i || 0x7f0a007d == i))
            cancelBMLLongClick();
        i;
        JVM INSTR tableswitch 2131361916 2131361919: default 96
    //                   2131361916 101
    //                   2131361917 129
    //                   2131361918 184
    //                   2131361919 157;
           goto _L3 _L4 _L5 _L6 _L7
_L6:
        break MISSING_BLOCK_LABEL_184;
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

    private static MtvAppBml mBmlApp = null;
    private static MtvUiFragHandler mFragHandler = null;
    private static Handler mHandler = new Handler();
    private ImageButton mBtnCntrKeyPad;
    private Button mBtnCntrlBack;
    private ImageButton mBtnCntrlDown;
    private Button mBtnCntrlSelect;
    private ImageButton mBtnCntrlUp;
    private View mCntrlFragView;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;
    private Runnable mRunnableBMLCtrlDown;
    private Runnable mRunnableBMLCtrlUp;






    private class _cls1
        implements Runnable
    {

        public void run()
        {
            if(MtvUiBmlKeyPadControlFragment.mBmlApp != null)
                MtvUiBmlKeyPadControlFragment.mBmlApp.onKeyEvent(new KeyEvent(0, 19));
            if(MtvUiBmlKeyPadControlFragment.mHandler != null)
                MtvUiBmlKeyPadControlFragment.mHandler.postDelayed(mRunnableBMLCtrlUp, 200L);
        }

        final MtvUiBmlKeyPadControlFragment this$0;

        _cls1()
        {
            this$0 = MtvUiBmlKeyPadControlFragment.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
            if(MtvUiBmlKeyPadControlFragment.mBmlApp != null)
                MtvUiBmlKeyPadControlFragment.mBmlApp.onKeyEvent(new KeyEvent(0, 20));
            if(MtvUiBmlKeyPadControlFragment.mHandler != null)
                MtvUiBmlKeyPadControlFragment.mHandler.postDelayed(mRunnableBMLCtrlDown, 200L);
        }

        final MtvUiBmlKeyPadControlFragment this$0;

        _cls2()
        {
            this$0 = MtvUiBmlKeyPadControlFragment.this;
            super();
        }
    }

}
