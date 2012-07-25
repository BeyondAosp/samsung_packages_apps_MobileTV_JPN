// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.channelguide;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageButton;
import com.samsung.sec.mtv.ui.common.MtvUiFrag;
import java.util.HashMap;

public class MtvUiChannelNumFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{

    public MtvUiChannelNumFrag()
    {
    }

    private void initializeUI()
    {
        for(int i = 0; i < RSRC_BUTTON_KEY.length; i++)
            mKeyMap.put(Integer.valueOf(RSRC_BUTTON_KEY[i]), Integer.valueOf(i));

        mInputEditText = (EditText)mLayoutView.findViewById(0x7f0a0060);
        mInputEditText.setInputType(0);
        mInputEditText.setCursorVisible(true);
        for(int j = 0; j < RSRC_BUTTON_KEY.length; j++)
            ((ImageButton)mLayoutView.findViewById(RSRC_BUTTON_KEY[j])).setOnClickListener(this);

        for(int k = 0; k < RSRC_BUTTON.length; k++)
            ((ImageButton)mLayoutView.findViewById(RSRC_BUTTON[k])).setOnClickListener(this);

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
        MtvUtilDebug.Low("MtvUiChannelNumFrag", (new StringBuilder()).append("OnClick id=").append(view.getId()).toString());
        view.getId();
        JVM INSTR tableswitch 2131361889 2131361900: default 92
    //                   2131361889 93
    //                   2131361890 93
    //                   2131361891 93
    //                   2131361892 93
    //                   2131361893 93
    //                   2131361894 93
    //                   2131361895 93
    //                   2131361896 93
    //                   2131361897 93
    //                   2131361898 214
    //                   2131361899 93
    //                   2131361900 261;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L2 _L4
_L1:
        return;
_L2:
        if(mInputEditText.length() < 3)
        {
            Integer integer = (Integer)mKeyMap.get(Integer.valueOf(view.getId()));
            mInputEditText.setText(mInputEditText.getText().toString().trim());
            if(mInputEditText.length() < 2)
            {
                mInputEditText.setText((new StringBuilder()).append(mInputEditText.getText()).append(integer.toString()).append(" ").toString());
                mInputEditText.setSelection(mInputEditText.getText().length());
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!TextUtils.isEmpty(mInputEditText.getText()))
            mListener.onFragEvent(261, Integer.valueOf(Integer.parseInt(mInputEditText.getText().toString().trim())));
        continue; /* Loop/switch isn't completed */
_L4:
        if(mInputEditText.length() > 0)
        {
            mInputEditText.setText(mInputEditText.getText().toString().trim());
            String s = mInputEditText.getText().toString();
            mInputEditText.setText(s.substring(0, -1 + s.length()));
            mInputEditText.setSelection(mInputEditText.getText().length());
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mLayoutView = layoutinflater.inflate(0x7f030014, null);
        initializeUI();
        return mLayoutView;
    }

    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }

    public void onStart()
    {
        super.onStart();
    }

    public void onStop()
    {
        super.onStop();
    }

    public void onUpdate(int i, Object obj)
    {
        MtvUtilDebug.Low("MtvUiChannelNumFrag", (new StringBuilder()).append("onUpdate: what[").append(i).append("]").toString());
        super.onUpdate(i, obj);
    }

    private static final int RSRC_BUTTON[];
    private static final int RSRC_BUTTON_KEY[];
    private EditText mInputEditText;
    private final HashMap mKeyMap = new HashMap();
    private View mLayoutView;
    private com.samsung.sec.mtv.ui.common.MtvUiFrag.IMtvFragEventListener mListener;

    static 
    {
        int ai[] = new int[10];
        ai[0] = 0x7f0a006b;
        ai[1] = 0x7f0a0061;
        ai[2] = 0x7f0a0062;
        ai[3] = 0x7f0a0063;
        ai[4] = 0x7f0a0064;
        ai[5] = 0x7f0a0065;
        ai[6] = 0x7f0a0066;
        ai[7] = 0x7f0a0067;
        ai[8] = 0x7f0a0068;
        ai[9] = 0x7f0a0069;
        RSRC_BUTTON_KEY = ai;
        int ai1[] = new int[2];
        ai1[0] = 0x7f0a006a;
        ai1[1] = 0x7f0a006c;
        RSRC_BUTTON = ai1;
    }
}
