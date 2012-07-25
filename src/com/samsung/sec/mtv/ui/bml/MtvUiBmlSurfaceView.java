// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.bml;

import android.app.Activity;
import android.broadcast.helper.MtvUtilDebug;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.access.bml.BMLNativeView;
import com.samsung.sec.mtv.app.bml.IMtvAppBmlSurfaceListener;
import com.samsung.sec.mtv.app.bml.MtvAppBml;
import com.samsung.sec.mtv.app.context.*;
import com.samsung.sec.mtv.ui.channelguide.MtvUiChannelGuide;
import com.samsung.sec.mtv.ui.common.MtvUiFragHandler;
import com.samsung.sec.mtv.utility.MtvPreferences;
import com.samsung.sec.mtv.utility.MtvUtilConfigSetting;

// Referenced classes of package com.samsung.sec.mtv.ui.bml:
//            MtvUiBmlDialogFrag, MtvUiBmlAnimation, MtvUiBmlBasicControlFrag, MtvUiBmlKeyPadControlFragment, 
//            MtvUiBmlNumKeyPadFragment, MtvUiBmlCaptionKeyPadControlFragment, MtvUiBmlCaptionBasicControlFrag

public class MtvUiBmlSurfaceView extends BMLNativeView
    implements IMtvAppBmlSurfaceListener
{
    class GestrueDetectorBML extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$GestrueDetectorBML;->onSingleTapConfirmed(Landroid/view/MotionEvent;)Z");
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", " BML onSingleTapConfirmed call...");
            return super.onSingleTapConfirmed(motionevent);
        }

        final MtvUiBmlSurfaceView this$0;

        GestrueDetectorBML()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$GestrueDetectorBML;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;)V");
            this$0 = MtvUiBmlSurfaceView.this;
            super();
        }
    }


    public MtvUiBmlSurfaceView(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;-><init>(Landroid/content/Context;)V");
        super(context);
        mBmlApp = null;
        mMtvAppPlaybackContext = null;
        mSurfaceHolder = null;
        mBmlAnimation = null;
        mbmlDialog = null;
        mbmlCntrlType = com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        mGestureDetector = null;
        mFragHandler = null;
        mMtvPreferences = null;
        initializeBMLDimension(context);
    }

    public MtvUiBmlSurfaceView(Context context, AttributeSet attributeset)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V");
        super(context, attributeset);
        mBmlApp = null;
        mMtvAppPlaybackContext = null;
        mSurfaceHolder = null;
        mBmlAnimation = null;
        mbmlDialog = null;
        mbmlCntrlType = com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        mGestureDetector = null;
        mFragHandler = null;
        mMtvPreferences = null;
        initializeBMLDimension(context);
    }

    public MtvUiBmlSurfaceView(Context context, AttributeSet attributeset, int i)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V");
        super(context, attributeset, i);
        mBmlApp = null;
        mMtvAppPlaybackContext = null;
        mSurfaceHolder = null;
        mBmlAnimation = null;
        mbmlDialog = null;
        mbmlCntrlType = com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC;
        mGestureDetector = null;
        mFragHandler = null;
        mMtvPreferences = null;
        initializeBMLDimension(context);
    }

    private void destroyBmlContrlFragment()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->destroyBmlContrlFragment()V");
    }

    private boolean getPhoneLockedState()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->getPhoneLockedState()Z");
        boolean flag;
        if(mFragHandler != null)
            flag = mFragHandler.isPhoneLocked();
        else
            flag = false;
        return flag;
    }

    private void initializeBMLDimension(Context context)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->initializeBMLDimension(Landroid/content/Context;)V");
        browser_width = (int)MtvUtilConfigSetting.convertDpToPixel(360F, context);
        browser_height = (int)MtvUtilConfigSetting.convertDpToPixel(640F, context);
        bmlbar_height = (int)MtvUtilConfigSetting.convertDpToPixel(72F, context);
        caption_height = (int)MtvUtilConfigSetting.convertDpToPixel(93F, context);
        browser_full_height = (int)MtvUtilConfigSetting.convertDpToPixel(510F, context);
        browser_normal_height = (int)MtvUtilConfigSetting.convertDpToPixel(307F, context);
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", (new StringBuilder()).append("initializeBMLDimension: browser_width:").append(browser_width).append("browser_height: ").append(browser_height).append(" bmlbar_height:").append(bmlbar_height).append(" caption_height:").append(caption_height).append("browser_full_height:").append(browser_full_height).append("browser_normal_height:").append(browser_normal_height).toString());
    }

    private void setBMLLandscapeMode()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->setBMLLandscapeMode()V");
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "setBMLLandscapeMode: entered ");
        if(mbmlDialog != null)
            mbmlDialog.destroyBMLDialog();
        if(mBmlAnimation != null && mBmlAnimation.isRunning())
        {
            mBmlAnimation.stopBmlAnimation();
            mBmlAnimation = null;
        }
        setVisibility(8);
    }

    public boolean IsBMLCaptionView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->IsBMLCaptionView()Z");
        boolean flag;
        if(mMtvPreferences != null && mMtvPreferences.isCaptionEnabled() && IsBMLTvView())
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "IsBMLCaptionView:  TRUE ");
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "IsBMLCaptionView:  FALSE ");
            flag = false;
        }
        return flag;
    }

    public boolean IsBMLFullView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->IsBMLFullView()Z");
        boolean flag = true;
        if(get_scr_height() != browser_full_height + bmlbar_height)
            if(get_scr_height() == browser_full_height)
            {
                MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "IsBMLFullView: TRUE ");
            } else
            {
                if(!MtvUtilDebug.isReleaseMode())
                    MtvUtilDebug.Mid("MtvUiBmlSurfaceView", (new StringBuilder()).append("IsBMLFullView: FALSE ").append(getWidth()).append("  ").append(getHeight()).toString());
                flag = false;
            }
        return flag;
    }

    public boolean IsBMLTvView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->IsBMLTvView()Z");
        boolean flag;
        if(getWidth() == browser_width && getHeight() == browser_normal_height || getWidth() == browser_width && getHeight() == browser_normal_height + bmlbar_height)
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "IsBMLTvView:  TRUE ");
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "IsBMLTvView:  FALSE ");
            flag = false;
        }
        return flag;
    }

    public void bmlControlTypeChanged()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->bmlControlTypeChanged()V");
        startBmlContrlFragment();
    }

    public void onCreate(MtvAppPlaybackContext mtvappplaybackcontext, MtvUiFragHandler mtvuifraghandler)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onCreate(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;)V");
        if(mtvappplaybackcontext != null)
            mMtvAppPlaybackContext = mtvappplaybackcontext;
        else
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        mBmlApp = MtvAppBml.getInstance(getContext());
        mMtvPreferences = new MtvPreferences(getContext());
        mFragHandler = mtvuifraghandler;
        mBmlApp.init(mMtvAppPlaybackContext);
        mBmlApp.registerBmlSurface(getContext(), this);
        mBmlApp.registerBmlSurfaceListener(this);
        setBrowserWidth(browser_width);
        setBrowserHeight(browser_height);
    }

    public void onDestroy()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onDestroy()V");
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "onDestroy:Entered");
        mGestureDetector = null;
        if(mSurfaceHolder != null)
        {
            if(mSurfaceHolderCallback != null)
                mSurfaceHolder.removeCallback(mSurfaceHolderCallback);
            mSurfaceHolderCallback = null;
            mSurfaceHolder = null;
        }
        if(mBmlAnimation != null)
        {
            mBmlAnimation.stopBmlAnimation();
            mBmlAnimation = null;
        }
        destroyBmlContrlFragment();
        if(mbmlDialog != null)
        {
            mbmlDialog.destroyBMLDialog();
            mbmlDialog = null;
        }
        if(mBmlApp != null)
        {
            mBmlApp.deInit();
            mBmlApp = null;
        }
        if(mFragHandler != null)
            mFragHandler = null;
        mMtvPreferences = null;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onKeyUp(ILandroid/view/KeyEvent;)Z");
        if(mbmlDialog != null)
        {
            mbmlDialog.destroyBMLDialog();
            if(mBmlApp != null)
                mBmlApp.sendDialogReply(com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlDialogReply.MTV_APP_BML_DIALOG_REPLAY_NO, mbmlDialog.getCmd());
        }
        return super.onKeyUp(i, keyevent);
    }

    public void onPause()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onPause()V");
    }

    public void onResume(MtvAppPlaybackContext mtvappplaybackcontext)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onResume(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;)V");
        MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "onResume:Entered");
        if(mtvappplaybackcontext != null)
            mMtvAppPlaybackContext = mtvappplaybackcontext;
        else
            mMtvAppPlaybackContext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mMtvPreferences == null)
            mMtvPreferences = new MtvPreferences(getContext());
        mBmlApp = MtvAppBml.getInstance(getContext());
        mBmlApp.init(mMtvAppPlaybackContext);
        mBmlApp.onResume(mMtvAppPlaybackContext);
        mBmlApp.registerBmlSurface(getContext(), this);
        mBmlApp.registerBmlSurfaceListener(this);
        if(mBmlAnimation == null)
            mBmlAnimation = new MtvUiBmlAnimation(getContext(), null, mBmlApp);
        mbmlDialog = MtvUiBmlDialogFrag.getInstance();
        if(mbmlDialog != null)
            mbmlDialog.setAppcomponents(mBmlApp, mFragHandler, getContext());
        MtvUiBmlBasicControlFrag.setAppcomponents(mBmlApp, mFragHandler);
        MtvUiBmlKeyPadControlFragment.setAppcomponents(mBmlApp, mFragHandler);
        MtvUiBmlNumKeyPadFragment.setAppcomponents(mBmlApp, mFragHandler);
        MtvUiBmlCaptionKeyPadControlFragment.setAppcomponents(mBmlApp, mFragHandler);
        MtvUiBmlCaptionBasicControlFrag.setAppcomponents(mBmlApp, mFragHandler);
        if(!checkCanvasValid())
            MtvUtilDebug.Error("MtvUiBmlSurfaceView", "onResume:Canvas inValid ");
        if(mBmlApp != null)
            mBmlApp.refreshBMLScreen();
        updateBMLSurfaceView();
        if(mbmlDialog != null)
            mbmlDialog.onResume();
        if(mBmlAnimation != null)
            mBmlAnimation.onResume();
    }

    public void onStart(MtvUiFragHandler mtvuifraghandler)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onStart(Lcom/samsung/sec/mtv/ui/common/MtvUiFragHandler;)V");
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "onStart: entered ");
        mFragHandler = mtvuifraghandler;
        mSurfaceHolder = getHolder();
        if(mSurfaceHolder != null)
            mSurfaceHolder.addCallback(mSurfaceHolderCallback);
        setVisibility(0);
        setFocusable(true);
        mGestureDetector = new GestureDetector(getContext(), new GestrueDetectorBML());
    }

    public void onStop()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onStop()V");
        if(mBmlApp != null)
            mBmlApp.registerBmlSurfaceListener(null);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->onTouchEvent(Landroid/view/MotionEvent;)Z");
        if(motionevent == null)
        {
            MtvUtilDebug.Error("MtvUiBmlSurfaceView", "onTouchEvent: MotionEvent NULL !! ");
        } else
        {
label0:
            {
                if(!getPhoneLockedState())
                    break label0;
                MtvUtilDebug.Low("MtvUiBmlSurfaceView", "onTouchEvent: Phone is locked return ");
            }
        }
_L1:
        return true;
        if(mGestureDetector != null)
            mGestureDetector.onTouchEvent(motionevent);
        if(IsBMLCaptionView() && !IsBMLFullView())
            motionevent.offsetLocation(0.0F, -caption_height);
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 116
    //                   0 119
    //                   1 173
    //                   2 168;
           goto _L1 _L2 _L3 _L4
_L2:
        int i = 0;
_L5:
        MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "onTouchEvent: passing touch event to BML engine ");
        if(mBmlApp == null)
            mBmlApp = MtvAppBml.getInstance(getContext());
        mBmlApp.processMouseEvent((int)motionevent.getX(), (int)motionevent.getY(), i);
          goto _L1
_L4:
        i = 1;
          goto _L5
_L3:
        i = 2;
          goto _L5
    }

    public void openBMLHomePage()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->openBMLHomePage()V");
        if(mBmlApp != null)
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "setBMLHomePage: Setting BML home page");
            mBmlApp.openBMLHomePage();
        }
    }

    public void setBMLCaptionView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->setBMLCaptionView()V");
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "setBMLCaptionView: isPhoneLocked TRUE ");
        mBmlApp.setBMLViewSize(browser_width, browser_normal_height - caption_height, 0, caption_height);
    }

    public void setBMLFullView(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->setBMLFullView(Z)V");
        if(!flag) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "setBMLFullView: isPhoneLocked TRUE ");
        mBmlApp.setBMLViewSize(browser_width, browser_full_height, 0, 0);
_L4:
        return;
_L2:
        if(mMtvPreferences != null)
            if(mMtvPreferences.isCaptionEnabled())
            {
                MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView:  setBMLCaptionView  ");
                setBMLCaptionView();
            } else
            if(IsBMLTvView())
            {
                MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView:  setBMLTvView  ");
                setBMLTvView();
            } else
            {
                MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView: else setBMLTvView  ");
                setBMLTvView();
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setBMLTvView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->setBMLTvView()V");
        if(mBmlApp != null)
        {
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "setBMLTvView: isPhoneLocked TRUE ");
            mBmlApp.setBMLViewSize(browser_width, browser_normal_height, 0, 0);
        }
    }

    public void setPhoneLockedState(boolean flag)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->setPhoneLockedState(Z)V");
        updateBMLSurfaceView();
    }

    public void startBmlContrlFragment()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->startBmlContrlFragment()V");
        mbmlCntrlType = mBmlApp.getBmlControlType();
        if(getContext().getResources().getConfiguration().orientation != 2) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "startBmlOsd: landscape mode return ");
        setBMLLandscapeMode();
_L4:
        return;
_L2:
        if(mFragHandler == null || 1 != mFragHandler.getActivityType() || !mMtvPreferences.getIsFileFormatImage())
            break; /* Loop/switch isn't completed */
        stopBmlContrlFragment();
        if(true) goto _L4; else goto _L3
_L3:
        static class _cls2
        {

            static final int $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlControlType[];

            static 
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$2;-><clinit>()V");
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlControlType = new int[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlControlType[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_BASIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$samsung$sec$mtv$app$bml$MtvAppBmlConstants$BmlControlType[com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType.MTV_APP_BML_CONTROL_KEYPAD.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls2..SwitchMap.com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType[mbmlCntrlType.ordinal()])
        {
        case 1: // '\001'
            stopBmlContrlFragment();
            if(mMtvPreferences != null && mMtvPreferences.isCaptionEnabled())
                MtvUiBmlCaptionBasicControlFrag.show();
            else
                MtvUiBmlBasicControlFrag.show();
            break;

        case 2: // '\002'
            stopBmlContrlFragment();
            if(mMtvPreferences != null && mMtvPreferences.isCaptionEnabled())
                MtvUiBmlCaptionKeyPadControlFragment.show();
            else
                MtvUiBmlKeyPadControlFragment.show();
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public void startTvLinkTab()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->startTvLinkTab()V");
        MtvAppPlaybackContext mtvappplaybackcontext = MtvAppPlaybackContextManager.getInstance().getCurrentContext();
        if(mtvappplaybackcontext != null && mtvappplaybackcontext.getState().getOp() != 20487)
        {
            Intent intent = new Intent(getContext(), com/samsung/sec/mtv/ui/channelguide/MtvUiChannelGuide);
            intent.putExtra("initialize", true);
            intent.putExtra("tab", 3);
            (new Bundle()).putParcelable("intent", intent);
            ((Activity)getContext()).startActivityForResult(intent, 0);
        } else
        {
            MtvUtilDebug.High("MtvUiBmlSurfaceView", "Going to TVLinks Tab not entertained while recording...");
        }
    }

    public void stopBmlContrlFragment()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->stopBmlContrlFragment()V");
        MtvUiBmlBasicControlFrag.hide();
        MtvUiBmlKeyPadControlFragment.hide();
        MtvUiBmlNumKeyPadFragment.hide();
        MtvUiBmlCaptionKeyPadControlFragment.hide();
        MtvUiBmlCaptionBasicControlFrag.hide();
    }

    public void updateBMLSurfaceView()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->updateBMLSurfaceView()V");
        if(getContext().getResources().getConfiguration().orientation != 2) goto _L2; else goto _L1
_L1:
        MtvUtilDebug.Low("MtvUiBmlSurfaceView", "startBmlOsd: landscape mode return ");
        setBMLLandscapeMode();
_L4:
        return;
_L2:
        if(!IsBMLFullView())
            break; /* Loop/switch isn't completed */
        MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView:  setBMLFullView  ");
        setBMLFullView(true);
_L5:
        startBmlContrlFragment();
        if(true) goto _L4; else goto _L3
_L3:
        if(mMtvPreferences != null && mMtvPreferences.isCaptionEnabled())
        {
            MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView:  setBMLCaptionView  ");
            setBMLCaptionView();
        } else
        if(IsBMLTvView())
        {
            MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView:  setBMLTvView  ");
            setBMLTvView();
        } else
        {
            MtvUtilDebug.Mid("MtvUiBmlSurfaceView", "updateBMLSurfaceView: else setBMLTvView  ");
            setBMLTvView();
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private int bmlbar_height;
    private int browser_full_height;
    private int browser_height;
    private int browser_normal_height;
    private int browser_width;
    private int caption_height;
    private MtvUiBmlAnimation mBmlAnimation;
    private MtvAppBml mBmlApp;
    private MtvUiFragHandler mFragHandler;
    private GestureDetector mGestureDetector;
    private MtvAppPlaybackContext mMtvAppPlaybackContext;
    private MtvPreferences mMtvPreferences;
    private SurfaceHolder mSurfaceHolder;
    private android.view.SurfaceHolder.Callback mSurfaceHolderCallback = new android.view.SurfaceHolder.Callback() {

        public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$1;->surfaceChanged(Landroid/view/SurfaceHolder;III)V");
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUiBmlSurfaceView", (new StringBuilder()).append("surfaceChanged format=").append(i).append(", width=").append(j).append(", height=").append(k).toString());
            if(!checkCanvasValid())
                MtvUtilDebug.Error("MtvUiBmlSurfaceView", "surfaceChanged:Canvas inValid ");
            if(Log.d(MtvUiBmlSurfaceView.this) != null && Log.d(MtvUiBmlSurfaceView.this).getState().getState() != com.samsung.sec.mtv.app.context.MtvAppPlaybackState.State.NONE && Log.d(MtvUiBmlSurfaceView.this) != null)
                Log.d(MtvUiBmlSurfaceView.this).refreshBMLScreen();
        }

        public void surfaceCreated(SurfaceHolder surfaceholder)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$1;->surfaceCreated(Landroid/view/SurfaceHolder;)V");
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "surfaceCreated");
        }

        public void surfaceDestroyed(SurfaceHolder surfaceholder)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$1;->surfaceDestroyed(Landroid/view/SurfaceHolder;)V");
            MtvUtilDebug.Low("MtvUiBmlSurfaceView", "surfaceDestroyed");
        }

        final MtvUiBmlSurfaceView this$0;

            
            {
                Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView$1;-><init>(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;)V");
                this$0 = MtvUiBmlSurfaceView.this;
                super();
            }
    };
    private com.samsung.sec.mtv.app.bml.MtvAppBmlConstants.BmlControlType mbmlCntrlType;
    private MtvUiBmlDialogFrag mbmlDialog;


/*
    static MtvAppPlaybackContext access$000(MtvUiBmlSurfaceView mtvuibmlsurfaceview)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->access$000(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;");
        return mtvuibmlsurfaceview.mMtvAppPlaybackContext;
    }

*/


/*
    static MtvAppBml access$100(MtvUiBmlSurfaceView mtvuibmlsurfaceview)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;->access$100(Lcom/samsung/sec/mtv/ui/bml/MtvUiBmlSurfaceView;)Lcom/samsung/sec/mtv/app/bml/MtvAppBml;");
        return mtvuibmlsurfaceview.mBmlApp;
    }

*/
}
