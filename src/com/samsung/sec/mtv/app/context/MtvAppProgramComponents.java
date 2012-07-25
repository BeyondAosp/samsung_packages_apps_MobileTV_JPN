// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.*;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.util.Log;

public class MtvAppProgramComponents
{
    public static class MtvAppPrgAudioComponent
    {

        private void init()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->init()V");
            isEnabled = false;
            iface = null;
        }

        public void disable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->disable()V");
            isEnabled = false;
            if(iface != null)
                iface.disableAudio();
        }

        public void enable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->enable()V");
            isEnabled = true;
            if(iface != null)
                iface.enableAudio();
        }

        public IMtvOneSegAudioControl getControlInterface()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->getControlInterface()Landroid/broadcast/IMtvOneSegAudioControl;");
            IMtvOneSegAudioControl imtvonesegaudiocontrol;
            if(isEnabled)
                imtvonesegaudiocontrol = iface;
            else
                imtvonesegaudiocontrol = null;
            return imtvonesegaudiocontrol;
        }

        public void reset()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->reset()V");
            init();
        }

        public void setControlInterface(IMtvOneSegAudioControl imtvonesegaudiocontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;->setControlInterface(Landroid/broadcast/IMtvOneSegAudioControl;)V");
            iface = imtvonesegaudiocontrol;
        }

        private IMtvOneSegAudioControl iface;
        private boolean isEnabled;

        MtvAppPrgAudioComponent()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;-><init>()V");
            super();
            init();
        }
    }

    public static class MtvAppPrgBmlComponent
    {

        private void init()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;->init()V");
            isEnabled = false;
            iface = null;
        }

        public void enable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;->enable()V");
            isEnabled = true;
            if(iface != null)
                iface.enableBml();
        }

        public IMtvOneSegBmlViewControl getControlInterface()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;->getControlInterface()Landroid/broadcast/IMtvOneSegBmlViewControl;");
            this;
            JVM INSTR monitorenter ;
            if(!isEnabled) goto _L2; else goto _L1
_L1:
            IMtvOneSegBmlViewControl imtvonesegbmlviewcontrol = iface;
_L4:
            this;
            JVM INSTR monitorexit ;
            return imtvonesegbmlviewcontrol;
_L2:
            imtvonesegbmlviewcontrol = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void reset()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;->reset()V");
            init();
        }

        public void setControlInterface(IMtvOneSegBmlViewControl imtvonesegbmlviewcontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;->setControlInterface(Landroid/broadcast/IMtvOneSegBmlViewControl;)V");
            this;
            JVM INSTR monitorenter ;
            iface = imtvonesegbmlviewcontrol;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private IMtvOneSegBmlViewControl iface;
        private boolean isEnabled;

        MtvAppPrgBmlComponent()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;-><init>()V");
            super();
            init();
        }
    }

    public static class MtvAppPrgCaptionComponent
    {

        private void init()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->init()V");
            isEnabled = false;
            iface = null;
            captBuffer = null;
        }

        public void enable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->enable()V");
            isEnabled = true;
            if(iface != null)
                iface.enableCaption();
        }

        public SpannableStringBuilder getBuffer()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->getBuffer()Landroid/text/SpannableStringBuilder;");
            this;
            JVM INSTR monitorenter ;
            if(!isEnabled) goto _L2; else goto _L1
_L1:
            SpannableStringBuilder spannablestringbuilder = captBuffer;
_L4:
            this;
            JVM INSTR monitorexit ;
            return spannablestringbuilder;
_L2:
            spannablestringbuilder = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void reset()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->reset()V");
            init();
        }

        public void setBuffer(SpannableStringBuilder spannablestringbuilder)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->setBuffer(Landroid/text/SpannableStringBuilder;)V");
            this;
            JVM INSTR monitorenter ;
            captBuffer = spannablestringbuilder;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setControlInterface(IMtvOneSegCaptionControl imtvonesegcaptioncontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;->setControlInterface(Landroid/broadcast/IMtvOneSegCaptionControl;)V");
            this;
            JVM INSTR monitorenter ;
            iface = imtvonesegcaptioncontrol;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private SpannableStringBuilder captBuffer;
        private IMtvOneSegCaptionControl iface;
        private boolean isEnabled;

        MtvAppPrgCaptionComponent()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;-><init>()V");
            super();
            init();
        }
    }

    public static class MtvAppPrgVideoComponent
    {

        private void init()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->init()V");
            isEnabled = true;
            iface = null;
            captFrame = null;
            captFrameName = null;
        }

        public void disable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->disable()V");
            isEnabled = false;
            if(iface != null)
                iface.disableVideo();
        }

        public void enable()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->enable()V");
            isEnabled = true;
            if(iface != null)
                iface.enableVideo();
        }

        public Bitmap getCaptFrame()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->getCaptFrame()Landroid/graphics/Bitmap;");
            this;
            JVM INSTR monitorenter ;
            if(!isEnabled) goto _L2; else goto _L1
_L1:
            Bitmap bitmap = captFrame;
_L4:
            this;
            JVM INSTR monitorexit ;
            return bitmap;
_L2:
            bitmap = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public String getCaptFrameName()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->getCaptFrameName()Ljava/lang/String;");
            this;
            JVM INSTR monitorenter ;
            if(!isEnabled) goto _L2; else goto _L1
_L1:
            String s = captFrameName;
_L4:
            this;
            JVM INSTR monitorexit ;
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public IMtvOneSegVideoControl getControlInterface()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->getControlInterface()Landroid/broadcast/IMtvOneSegVideoControl;");
            this;
            JVM INSTR monitorenter ;
            if(!isEnabled) goto _L2; else goto _L1
_L1:
            IMtvOneSegVideoControl imtvonesegvideocontrol = iface;
_L4:
            this;
            JVM INSTR monitorexit ;
            return imtvonesegvideocontrol;
_L2:
            imtvonesegvideocontrol = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public void reset()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->reset()V");
            init();
        }

        public void setCaptFrame(Bitmap bitmap)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->setCaptFrame(Landroid/graphics/Bitmap;)V");
            this;
            JVM INSTR monitorenter ;
            if(isEnabled)
                captFrame = bitmap;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setCaptFrameName(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->setCaptFrameName(Ljava/lang/String;)V");
            this;
            JVM INSTR monitorenter ;
            if(isEnabled)
                captFrameName = s;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setControlInterface(IMtvOneSegVideoControl imtvonesegvideocontrol)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;->setControlInterface(Landroid/broadcast/IMtvOneSegVideoControl;)V");
            this;
            JVM INSTR monitorenter ;
            iface = imtvonesegvideocontrol;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private Bitmap captFrame;
        private String captFrameName;
        private IMtvOneSegVideoControl iface;
        private boolean isEnabled;

        MtvAppPrgVideoComponent()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;-><init>()V");
            super();
            init();
        }
    }


    MtvAppProgramComponents()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;-><init>()V");
        super();
        audio = new MtvAppPrgAudioComponent();
        video = new MtvAppPrgVideoComponent();
        caption = new MtvAppPrgCaptionComponent();
        bml = new MtvAppPrgBmlComponent();
        availableComp = 0;
    }

    public MtvAppPrgAudioComponent getAudio()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;->getAudio()Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgAudioComponent;");
        return audio;
    }

    public MtvAppPrgBmlComponent getBml()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;->getBml()Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgBmlComponent;");
        return bml;
    }

    public MtvAppPrgCaptionComponent getCaption()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;->getCaption()Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgCaptionComponent;");
        return caption;
    }

    public MtvAppPrgVideoComponent getVideo()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;->getVideo()Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents$MtvAppPrgVideoComponent;");
        return video;
    }

    public void reset()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;->reset()V");
        availableComp = 0;
        audio.reset();
        video.reset();
        caption.reset();
        bml.reset();
    }

    private MtvAppPrgAudioComponent audio;
    private int availableComp;
    private MtvAppPrgBmlComponent bml;
    private MtvAppPrgCaptionComponent caption;
    private MtvAppPrgVideoComponent video;
}
