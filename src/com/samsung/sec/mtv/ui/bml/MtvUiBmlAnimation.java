// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.sec.mtv.app.bml.IMtvAppBmlAnimationListener;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.utility.MtvPreferences;

class MtvUiBmlAnimation extends Animation
    implements IMtvAppBmlAnimationListener
{

    public MtvUiBmlAnimation(Context context, AttributeSet attributeset, MtvAppBml mtvappbml)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;Lcom/samsung/sec/mtv/app/bml/MtvAppBml;)V");
        super(context, attributeset);
        mMtvPreferences = null;
        mContext = context;
        mMtvPreferences = new MtvPreferences(context);
        init();
        mBmlApp = mtvappbml;
    }

    public void init()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->init()V");
        mbmlanimation = (AnimationDrawable)((Activity)mContext).findViewById(0x7f0a000d).getBackground();
        mbmlAnimTextView = (TextView)((Activity)mContext).findViewById(0x7f0a000e);
        bmlAnimLayout = (RelativeLayout)((Activity)mContext).findViewById(0x7f0a000b);
    }

    public boolean isRunning()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->isRunning()Z");
        boolean flag;
        if(mbmlanimation != null)
        {
            MtvUtilDebug.Low("MtvUiBmlAnimation", "isRunning:  BML animation is running ");
            flag = mbmlanimation.isRunning();
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void onResume()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->onResume()V");
        if(mBmlApp == null) goto _L2; else goto _L1
_L1:
        mBmlApp.registerBmlAnimationListener(this);
        if(mBmlApp.getCurrentUIEvt() != com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_START_ANIMATION) goto _L4; else goto _L3
_L3:
        startBmlAnimation();
_L2:
        return;
_L4:
        if(mBmlApp.getCurrentUIEvt() == com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.AppBmlUIEvents.MTV_APP_BML_UI_EVT_STOP_ANIMATION)
            stopBmlAnimation();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void setBmlAnimationText(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlAppAnimMessages bmlappanimmessages)
    {
        int i;
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->setBmlAnimationText(Lcom/samsung/sec/mtv/app/bml/MtvAppBmlConstants$BmlAppAnimMessages;)V");
        i = 0x7f07002f;
        if(mContext == null) goto _L2; else goto _L1
_L1:
        static class _cls1
        {

            static final int $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlAppAnimMessages[];

            static 
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation$1;-><clinit>()V");
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlAppAnimMessages = new int[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlAppAnimMessages.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlAppAnimMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlAppAnimMessages.MTV_APP_BML_RECEIVING.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlAppAnimMessages[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlAppAnimMessages.MTV_APP_BML_RETREIVING.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlAppAnimMessages[bmlappanimmessages.ordinal()];
        JVM INSTR tableswitch 1 2: default 48
    //                   1 59
    //                   2 65;
           goto _L2 _L3 _L4
_L2:
        animText = mContext.getString(i);
        return;
_L3:
        i = 0x7f07002f;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f070030;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void startBmlAnimation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->startBmlAnimation()V");
        if(mContext.getResources().getConfiguration().orientation != 2) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiBmlAnimation", "startBmlAnimation: landscape mode return ");
        stopBmlAnimation();
_L5:
        return;
_L2:
        if(mbmlAnimTextView != null)
        {
            mbmlAnimTextView.setText(animText);
            mbmlAnimTextView.setVisibility(0);
        }
        if(!mMtvPreferences.isCaptionEnabled()) goto _L4; else goto _L3
_L3:
        if(bmlAnimLayout != null)
            bmlAnimLayout.setPadding(0, 100, 0, 0);
_L6:
        if(mbmlanimation != null)
            if(!mbmlanimation.isRunning())
            {
                MtvUtilDebug.Low("MtvUiBmlAnimation", "startBmlAnimation:  starting BML animation 1 ");
                mbmlanimation.start();
                mbmlanimation.invalidateSelf();
            } else
            {
                MtvUtilDebug.Low("MtvUiBmlAnimation", "startBmlAnimation:  starting BML animation 2 ");
                mbmlanimation.stop();
                mbmlanimation.start();
            }
        if(bmlAnimLayout != null)
            bmlAnimLayout.setVisibility(0);
        if(true) goto _L5; else goto _L4
_L4:
        if(bmlAnimLayout != null)
            bmlAnimLayout.setPadding(0, 0, 0, 0);
          goto _L6
    }

    public void stopBmlAnimation()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;->stopBmlAnimation()V");
        if(mbmlanimation != null)
        {
            MtvUtilDebug.Low("MtvUiBmlAnimation", "stopBmlAnimation: stopping BML animation ");
            mbmlanimation.stop();
        }
        if(bmlAnimLayout != null)
            bmlAnimLayout.setVisibility(8);
        if(mbmlAnimTextView != null)
            mbmlAnimTextView.setVisibility(8);
    }

    private static String animText = null;
    private static RelativeLayout bmlAnimLayout = null;
    private static MtvAppBml mBmlApp = null;
    private static Context mContext = null;
    private static TextView mbmlAnimTextView = null;
    private static AnimationDrawable mbmlanimation = null;
    private MtvPreferences mMtvPreferences;

    static 
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlAnimation;-><clinit>()V");
    }
}
