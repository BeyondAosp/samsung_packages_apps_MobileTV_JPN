// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.samsung.sec.mtv.provider.*;
import com.samsung.sec.mtv.ui.channelguide.MtvUiChannelSchedule;
import java.sql.Date;
import java.text.SimpleDateFormat;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiFrag, MtvUiFragHandler

public class MtvUiProgInfoFrag extends MtvUiFrag
    implements android.view.View.OnClickListener
{

    private MtvUiProgInfoFrag()
    {
        RESERVATION_NONE = 0;
    }

    public MtvUiProgInfoFrag(Bundle bundle)
    {
        this();
        mProgInfo = bundle;
    }

    private void initializeUI()
    {
        MtvUtilDebug.Low("MtvUiProgInfoFrag", "initializeUI");
        mPCh = mProgInfo.getInt("pgmPch");
        startTime = mProgInfo.getLong("startTime");
        TextView textview = (TextView)mLayoutView.findViewById(0x7f0a00a5);
        TextView textview1 = (TextView)mLayoutView.findViewById(0x7f0a00a7);
        TextView textview2 = (TextView)mLayoutView.findViewById(0x7f0a00a9);
        ((Button)mLayoutView.findViewById(0x7f0a00ac)).setOnClickListener(this);
        Button button = (Button)mLayoutView.findViewById(0x7f0a00ae);
        if(button != null)
            button.setOnClickListener(this);
        mProgramSchedule = (Button)mLayoutView.findViewById(0x7f0a00ab);
        MtvChannel mtvchannel = MtvChannelManager.findByPChannel(getActivity(), mPCh);
        String s = null;
        String s1;
        String s2;
        if(mProgInfo.getString("channelName") == null && mtvchannel == null)
            s = getResources().getString(0x7f07029c);
        else
        if(mtvchannel != null)
        {
            if(mtvchannel.mChannelName != null)
                s = mtvchannel.mChannelName;
        } else
        {
            s = mProgInfo.getString("channelName");
        }
        textview.setText((new StringBuilder()).append("CH").append(mPCh).append(" ").append(s).toString());
        mtvProgram = MtvProgramManager.findByPChannel(getActivity(), startTime, mPCh);
        s1 = (new SimpleDateFormat("H:mm")).format(new Date(mtvProgram.mTimeStart));
        s2 = (new StringBuilder()).append(s1).append(" - ").append((new SimpleDateFormat("H:mm")).format(new Date(mProgInfo.getLong("endtime")))).toString();
        mProgTitle = mProgInfo.getString("programName");
        textview1.setText((new StringBuilder()).append(s2).append(" ").append(mProgTitle).toString());
        textview2.setText(mProgInfo.getString("programDesc"));
        mReservationType = mProgInfo.getInt("reservationType");
        if(getActivity().getLocalClassName().contains("MtvUiChannelSchedule"))
        {
            if(mtvProgram.mTimeEnd > System.currentTimeMillis())
            {
                if(mReservationType == 0)
                {
                    mProgramSchedule.setText(0x7f07008d);
                    longClickIndex = 0x7f050011;
                } else
                {
                    mProgramSchedule.setText(0x7f0702be);
                }
                mProgramSchedule.setOnClickListener(this);
            } else
            {
                mProgramSchedule.setVisibility(8);
            }
        } else
        {
            mLayoutView.findViewById(0x7f0a00aa).setVisibility(8);
            mLayoutView.findViewById(0x7f0a00ad).setVisibility(0);
        }
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131361963 2131361966: default 36
    //                   2131361963 50
    //                   2131361964 37
    //                   2131361965 36
    //                   2131361966 37;
           goto _L1 _L2 _L3 _L1 _L3
_L1:
        return;
_L3:
        MtvUiFragHandler.removeUnManagedFrag("ProgInfoFrag", getActivity());
        continue; /* Loop/switch isn't completed */
_L2:
        if(getActivity() != null)
        {
            MtvUiFragHandler.removeUnManagedFrag("ProgInfoFrag", getActivity());
            if(mReservationType == 0)
            {
                if(mtvProgram.mTimeStart < System.currentTimeMillis() && mtvProgram.mTimeEnd > System.currentTimeMillis())
                    ((MtvUiChannelSchedule)getActivity()).ReserveProgramStarted(mProgTitle, mtvProgram);
                else
                    ((MtvUiChannelSchedule)getActivity()).reserveContextMenuDialog(mProgTitle, longClickIndex, mtvProgram);
            } else
            {
                ((MtvUiChannelSchedule)getActivity()).CancelReservationDialog(startTime);
            }
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(false);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mLayoutView = layoutinflater.inflate(0x7f030021, null);
        initializeUI();
        return mLayoutView;
    }

    private final int RESERVATION_NONE;
    private int longClickIndex;
    private View mLayoutView;
    private int mPCh;
    private Bundle mProgInfo;
    private String mProgTitle;
    private Button mProgramSchedule;
    private int mReservationType;
    private MtvProgram mtvProgram;
    private long startTime;
}
