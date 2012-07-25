// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.broadcast.*;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;

public class MtvAppProgramComponents
{
    public static class MtvAppPrgAudioComponent
    {

        private void init()
        {
            isEnabled = false;
            iface = null;
        }

        public void disable()
        {
            isEnabled = false;
            if(iface != null)
                iface.disableAudio();
        }

        public void enable()
        {
            isEnabled = true;
            if(iface != null)
                iface.enableAudio();
        }

        public IMtvOneSegAudioControl getControlInterface()
        {
            IMtvOneSegAudioControl imtvonesegaudiocontrol;
            if(isEnabled)
                imtvonesegaudiocontrol = iface;
            else
                imtvonesegaudiocontrol = null;
            return imtvonesegaudiocontrol;
        }

        public void reset()
        {
            init();
        }

        public void setControlInterface(IMtvOneSegAudioControl imtvonesegaudiocontrol)
        {
            iface = imtvonesegaudiocontrol;
        }

        private IMtvOneSegAudioControl iface;
        private boolean isEnabled;

        MtvAppPrgAudioComponent()
        {
            init();
        }
    }

    public static class MtvAppPrgBmlComponent
    {

        private void init()
        {
            isEnabled = false;
            iface = null;
        }

        public void enable()
        {
            isEnabled = true;
            if(iface != null)
                iface.enableBml();
        }

        public IMtvOneSegBmlViewControl getControlInterface()
        {
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
            init();
        }

        public void setControlInterface(IMtvOneSegBmlViewControl imtvonesegbmlviewcontrol)
        {
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
            init();
        }
    }

    public static class MtvAppPrgCaptionComponent
    {

        private void init()
        {
            isEnabled = false;
            iface = null;
            captBuffer = null;
        }

        public void enable()
        {
            isEnabled = true;
            if(iface != null)
                iface.enableCaption();
        }

        public SpannableStringBuilder getBuffer()
        {
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
            init();
        }

        public void setBuffer(SpannableStringBuilder spannablestringbuilder)
        {
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
            init();
        }
    }

    public static class MtvAppPrgVideoComponent
    {

        private void init()
        {
            isEnabled = true;
            iface = null;
            captFrame = null;
            captFrameName = null;
        }

        public void disable()
        {
            isEnabled = false;
            if(iface != null)
                iface.disableVideo();
        }

        public void enable()
        {
            isEnabled = true;
            if(iface != null)
                iface.enableVideo();
        }

        public Bitmap getCaptFrame()
        {
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
            init();
        }

        public void setCaptFrame(Bitmap bitmap)
        {
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
            init();
        }
    }


    MtvAppProgramComponents()
    {
        audio = new MtvAppPrgAudioComponent();
        video = new MtvAppPrgVideoComponent();
        caption = new MtvAppPrgCaptionComponent();
        bml = new MtvAppPrgBmlComponent();
        availableComp = 0;
    }

    public MtvAppPrgAudioComponent getAudio()
    {
        return audio;
    }

    public MtvAppPrgBmlComponent getBml()
    {
        return bml;
    }

    public MtvAppPrgCaptionComponent getCaption()
    {
        return caption;
    }

    public MtvAppPrgVideoComponent getVideo()
    {
        return video;
    }

    public void reset()
    {
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
