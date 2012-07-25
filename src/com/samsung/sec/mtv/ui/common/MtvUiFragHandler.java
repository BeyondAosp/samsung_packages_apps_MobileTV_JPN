// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.common;

import android.app.*;
import android.broadcast.helper.MtvUtilDebug;
import android.os.Bundle;
import android.os.Handler;
import com.samsung.sec.mtv.ui.bml.*;
import com.samsung.sec.mtv.ui.channelguide.MtvUiChannelNumFrag;
import com.samsung.sec.mtv.ui.fileplayer.MtvUiFilePlayerImgFrag;
import com.samsung.sec.mtv.ui.fileplayer.MtvUiFilePlayerVidFrag;
import java.util.*;

// Referenced classes of package com.samsung.sec.mtv.ui.common:
//            MtvUiMemInfoFrag, MtvUiProgInfoFrag, MtvUiMainFrag, MtvUiRecordFrag, 
//            MtvUiSettingsFrag, MtvUiCaptureFrag, MtvUiLockFrag, MtvUiVolumeControlBarFrag, 
//            MtvUiStatusBarFrag, MtvUiFrag

public class MtvUiFragHandler
{

    private MtvUiFragHandler()
    {
        String as[] = new String[17];
        as[0] = "MainFrag";
        as[1] = "RecordFrag";
        as[2] = "SettingsFrag";
        as[3] = "InfoFrag";
        as[4] = "CaptureFrag";
        as[5] = "TouchLockFrag";
        as[6] = "ChannelNumFrag";
        as[7] = "BMLNumInFrag";
        as[8] = "BMLBasicFrag";
        as[9] = "BMLMainFrag";
        as[10] = " BMLCapBasic";
        as[11] = "BMLCapKeyPad";
        as[12] = "VolumeFrag";
        as[13] = "FilePlayerImgFrag";
        as[14] = "FilePlayerVidFrag";
        as[15] = "StatusBarFrag";
        as[16] = "BmlDialogFrag";
        fragTags = as;
        fragHashMap = new LinkedHashMap();
        isEnabled = true;
        mHandler = new _cls1();
        mTimedMainFrag = new _cls2();
        mTimedStatusFrag = new _cls3();
        mTimedVolumeControlBarFrag = new _cls4();
        mTimedCaptureFrag = new _cls5();
        mTimedFPImgFrag = new _cls6();
        mTimedFPVidFrag = new _cls7();
    }

    public MtvUiFragHandler(FragmentManager fragmentmanager, int i, int j)
    {
        String as[] = new String[17];
        as[0] = "MainFrag";
        as[1] = "RecordFrag";
        as[2] = "SettingsFrag";
        as[3] = "InfoFrag";
        as[4] = "CaptureFrag";
        as[5] = "TouchLockFrag";
        as[6] = "ChannelNumFrag";
        as[7] = "BMLNumInFrag";
        as[8] = "BMLBasicFrag";
        as[9] = "BMLMainFrag";
        as[10] = " BMLCapBasic";
        as[11] = "BMLCapKeyPad";
        as[12] = "VolumeFrag";
        as[13] = "FilePlayerImgFrag";
        as[14] = "FilePlayerVidFrag";
        as[15] = "StatusBarFrag";
        as[16] = "BmlDialogFrag";
        fragTags = as;
        fragHashMap = new LinkedHashMap();
        isEnabled = true;
        mHandler = new _cls1();
        mTimedMainFrag = new _cls2();
        mTimedStatusFrag = new _cls3();
        mTimedVolumeControlBarFrag = new _cls4();
        mTimedCaptureFrag = new _cls5();
        mTimedFPImgFrag = new _cls6();
        mTimedFPVidFrag = new _cls7();
        fragMgr = fragmentmanager;
        activityType = i;
        viewGroup = j;
    }

    private void addFragInternal(int i, long l)
    {
        if(i >= 0 && i <= 17)
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("addFragInternal!!! FragType:").append(i).toString());
            fragHashMap.put(Integer.valueOf(i), Long.valueOf(l));
        } else
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("addFragInternal!!! Invalid FragType:").append(i).toString());
        }
    }

    public static void addUnManagedFrag(String s, Bundle bundle, int i, Activity activity)
    {
        if(activity == null || s == null) goto _L2; else goto _L1
_L1:
        if(!s.equals("MemInfoFrag")) goto _L4; else goto _L3
_L3:
        FragmentTransaction fragmenttransaction1 = activity.getFragmentManager().beginTransaction();
        fragmenttransaction1.add(i, new MtvUiMemInfoFrag(), s);
        fragmenttransaction1.commit();
_L2:
        return;
_L4:
        if(s.equals("ProgInfoFrag"))
        {
            FragmentTransaction fragmenttransaction = activity.getFragmentManager().beginTransaction();
            fragmenttransaction.add(i, new MtvUiProgInfoFrag(bundle), s);
            fragmenttransaction.commit();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private String getFragTag(int i)
    {
        String s;
        if(i >= 0 && i <= 17)
            s = fragTags[i];
        else
            s = "InvalidFrag";
        return s;
    }

    private transient Fragment getNewFrag(int i, int ai[])
    {
        Object obj = null;
        i;
        JVM INSTR tableswitch 0 16: default 84
    //                   0 86
    //                   1 97
    //                   2 109
    //                   3 84
    //                   4 152
    //                   5 163
    //                   6 174
    //                   7 185
    //                   8 196
    //                   9 207
    //                   10 229
    //                   11 218
    //                   12 247
    //                   13 289
    //                   14 324
    //                   15 258
    //                   16 240;
           goto _L1 _L2 _L3 _L4 _L1 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L1:
        return ((Fragment) (obj));
_L2:
        obj = new MtvUiMainFrag();
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new MtvUiRecordFrag(true);
        continue; /* Loop/switch isn't completed */
_L4:
        if(ai != null && ai.length >= 1)
            obj = new MtvUiSettingsFrag(ai[1], peekUiFragment());
        else
            obj = new MtvUiSettingsFrag(peekUiFragment());
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new MtvUiCaptureFrag();
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new MtvUiLockFrag();
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new MtvUiChannelNumFrag();
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new MtvUiBmlNumKeyPadFragment();
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new MtvUiBmlBasicControlFrag();
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new MtvUiBmlKeyPadControlFragment();
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new MtvUiBmlCaptionBasicControlFrag();
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new MtvUiBmlCaptionKeyPadControlFragment();
        continue; /* Loop/switch isn't completed */
_L17:
        obj = MtvUiBmlDialogFrag.getInstance();
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new MtvUiVolumeControlBarFrag();
        continue; /* Loop/switch isn't completed */
_L16:
        if(activityType == 0)
            obj = new MtvUiStatusBarFrag(0);
        else
            obj = new MtvUiStatusBarFrag(1);
        continue; /* Loop/switch isn't completed */
_L14:
        if(ai != null && ai.length >= 1)
            obj = new MtvUiFilePlayerImgFrag(ai[1]);
        else
            obj = new MtvUiFilePlayerImgFrag();
        continue; /* Loop/switch isn't completed */
_L15:
        if(ai != null && ai.length >= 1)
            obj = new MtvUiFilePlayerVidFrag(ai[1]);
        else
            obj = new MtvUiFilePlayerVidFrag();
        if(true) goto _L1; else goto _L18
_L18:
    }

    private int getTopFragType()
    {
        int i = -1;
        MtvUtilDebug.Mid("MtvUiFragHandler", (new StringBuilder()).append("getTopFragType : fragHandler Enabled?: ").append(isEnabled).toString());
        if(isEnabled)
        {
            Set set = fragHashMap.keySet();
            MtvUtilDebug.Low("MtvUiFragHandler", set.toString());
            Object aobj[] = set.toArray();
            if(aobj.length > 0)
                i = ((Integer)aobj[-1 + aobj.length]).intValue();
        }
        return i;
    }

    private void hideAllFragments()
    {
        MtvUtilDebug.Mid("MtvUiFragHandler", (new StringBuilder()).append("hideAllFragments : fragHandler Enabled?:").append(isEnabled).toString());
        if(isEnabled)
        {
            Iterator iterator = fragHashMap.keySet().iterator();
            FragmentTransaction fragmenttransaction = null;
            if(fragMgr != null)
                fragmenttransaction = fragMgr.beginTransaction();
            do
            {
                if(!iterator.hasNext())
                    break;
                int i = ((Integer)iterator.next()).intValue();
                String s = getFragTag(i);
                if(fragMgr != null)
                {
                    Fragment fragment = fragMgr.findFragmentByTag(s);
                    if(fragment != null && !fragment.isHidden() && (i == 2 || i == 6))
                        fragmenttransaction.hide(fragment);
                }
            } while(true);
            fragmenttransaction.commit();
        }
    }

    private void remFragInternal(int i)
    {
        if(i >= 0 && i <= 17)
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("remFragInternal!!! FragType:").append(i).toString());
            fragHashMap.remove(Integer.valueOf(i));
        } else
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("remFragInternal!!! Invalid FragType:").append(i).toString());
        }
    }

    public static boolean removeUnManagedFrag(String s, Activity activity)
    {
        boolean flag;
        FragmentManager fragmentmanager;
        flag = false;
        if(activity == null || s == null)
            break MISSING_BLOCK_LABEL_49;
        fragmentmanager = activity.getFragmentManager();
        Fragment fragment = fragmentmanager.findFragmentByTag(s);
        if(fragment == null)
            break MISSING_BLOCK_LABEL_49;
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.remove(fragment);
        fragmenttransaction.commit();
        flag = true;
_L2:
        return flag;
        IllegalStateException illegalstateexception;
        illegalstateexception;
        flag = false;
        MtvUtilDebug.High("MtvUiFragHandler", (new StringBuilder()).append(s).append(":").append(illegalstateexception.getMessage()).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void startTimer(int i, long l)
    {
        Runnable runnable = null;
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("startTimer: fragType[").append(i).append("] timeOut[").append(l).append("]").toString());
        if(i == 0)
            runnable = mTimedMainFrag;
        else
        if(i == 15)
            runnable = mTimedStatusFrag;
        else
        if(i == 12)
            runnable = mTimedVolumeControlBarFrag;
        else
        if(i == 4)
            runnable = mTimedCaptureFrag;
        else
        if(i == 13)
            runnable = mTimedFPImgFrag;
        else
        if(i == 14)
            runnable = mTimedFPVidFrag;
        if(l == -1L)
            mHandler.removeCallbacks(runnable);
        else
        if(l >= 0L)
        {
            if(runnable != null)
            {
                mHandler.removeCallbacks(runnable);
                mHandler.postDelayed(runnable, l);
            }
        } else
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "startTimer: intvalid timer value");
        }
    }

    private void stopTimer(int i)
    {
        Runnable runnable;
        runnable = null;
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("stopTimer: fragType[").append(i).append("]").toString());
        if(i != 0) goto _L2; else goto _L1
_L1:
        runnable = mTimedMainFrag;
_L4:
        if(runnable != null)
            mHandler.removeCallbacks(runnable);
        return;
_L2:
        if(i == 15)
            runnable = mTimedStatusFrag;
        else
        if(i == 12)
            runnable = mTimedVolumeControlBarFrag;
        else
        if(i == 4)
            runnable = mTimedCaptureFrag;
        else
        if(i == 13)
            runnable = mTimedFPImgFrag;
        else
        if(i == 14)
            runnable = mTimedFPVidFrag;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void unhideAllFragments()
    {
        MtvUtilDebug.Mid("MtvUiFragHandler", (new StringBuilder()).append("unhideAllFragments : fragHandler Enabled?:").append(isEnabled).toString());
        if(isEnabled)
        {
            Iterator iterator = fragHashMap.keySet().iterator();
            FragmentTransaction fragmenttransaction = null;
            if(fragMgr != null)
                fragmenttransaction = fragMgr.beginTransaction();
            do
            {
                if(!iterator.hasNext())
                    break;
                int i = ((Integer)iterator.next()).intValue();
                String s = getFragTag(i);
                if(fragMgr != null)
                {
                    Fragment fragment = fragMgr.findFragmentByTag(s);
                    if(fragment != null && fragment.isHidden() && (i == 2 || i == 6))
                        fragmenttransaction.show(fragment);
                }
            } while(true);
            fragmenttransaction.commit();
        }
    }

    public transient void addFrag(int i, long l, boolean flag, int ai[])
    {
        Fragment fragment;
        String s;
        fragment = null;
        s = getFragTag(i);
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("addFrag: fragType[").append(i).append("] timeOut[").append(l).append("] addToBackStack[").append(flag).append("]").toString());
        if(isValidFragment(i)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(isFragPresent(i))
            removeFrag(i);
        if(5 == i)
            hideAllFragments();
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("addFrag : fragHandler Enabled?:").append(isEnabled).toString());
        if(!isEnabled)
            continue; /* Loop/switch isn't completed */
        if(5 != i && fragMgr != null)
        {
            FragmentTransaction fragmenttransaction = fragMgr.beginTransaction();
            int j;
            if(true)
                if(ai != null && ai.length > 1)
                    fragment = getNewFrag(i, ai);
                else
                    fragment = getNewFrag(i, new int[0]);
            if(fragment.isAdded() || fragment.isVisible())
                continue; /* Loop/switch isn't completed */
            j = viewGroup;
            if(ai != null && ai.length > 0)
                j = ai[0];
            if(flag)
            {
                fragmenttransaction.add(j, fragment, s);
                fragmenttransaction.addToBackStack(null);
            } else
            {
                fragmenttransaction.add(j, fragment, s);
            }
            fragmenttransaction.commit();
        }
        if(l > 0L)
            startTimer(i, l);
        addFragInternal(i, l);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean areFragmentsHidden()
    {
        int i = peekUiFragment();
        boolean flag;
        if(2 == i || 5 == i || 6 == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void fillFragHandlerData(Bundle bundle)
    {
        int[] _tmp = new int[17];
        int ai[] = bundle.getIntArray("MtvUiFragHandler");
        if(ai != null)
        {
            for(int i = 0; i < ai.length; i++)
            {
                if(ai[i] == -1)
                    continue;
                long l = bundle.getLong(fragTags[ai[i]], 0L);
                if(0L == l)
                    continue;
                MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("fillFragHandlerData: adding fragType[").append(fragTags[ai[i]]).append("] timeOut[").append(l).append("]").toString());
                fragHashMap.put(Integer.valueOf(ai[i]), Long.valueOf(l));
                if(-1L != l)
                    startTimer(ai[i], l);
            }

        }
    }

    public int getActivityType()
    {
        return activityType;
    }

    public boolean isFragPresent(int i)
    {
        boolean flag = false;
        MtvUtilDebug.Mid("MtvUiFragHandler", (new StringBuilder()).append("isFragpresent : fragType:").append(i).toString());
        if(isEnabled && fragHashMap != null)
            flag = fragHashMap.containsKey(Integer.valueOf(i));
        return flag;
    }

    public boolean isPhoneLocked()
    {
        return isFragPresent(5);
    }

    public boolean isValidFragment(int i)
    {
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("isValidFragment!! fragType:").append(i).toString());
        boolean flag;
        if(i >= 0 && i <= 17)
        {
            flag = true;
        } else
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("isValidFragment!! Invalid Fragment !!fragType:").append(i).toString());
            flag = false;
        }
        return flag;
    }

    public void onUpdate(int i, Object obj, int j)
    {
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("onUpdate: fragType[").append(j).append("] what[").append(i).append("]").toString());
        MtvUtilDebug.Mid("MtvUiFragHandler", (new StringBuilder()).append("onUpdate : fragHandler Enabled?:").append(isEnabled).toString());
        if(fragMgr != null)
            if(j >= 0 && 17 != j)
            {
                String s1 = getFragTag(j);
                Fragment fragment1 = fragMgr.findFragmentByTag(s1);
                if(fragment1 != null)
                    ((MtvUiFrag)fragment1).onUpdate(i, obj);
                else
                    MtvUtilDebug.High("MtvUiFragHandler", (new StringBuilder()).append("onUpdate failed as ").append(s1).append(" not present").toString());
            } else
            if(17 == j)
            {
                Iterator iterator = fragHashMap.keySet().iterator();
                while(iterator.hasNext()) 
                {
                    String s = getFragTag(((Integer)iterator.next()).intValue());
                    Fragment fragment = fragMgr.findFragmentByTag(s);
                    if(fragment != null)
                        ((MtvUiFrag)fragment).onUpdate(i, obj);
                }
            }
    }

    public int peekUiFragment()
    {
        return getTopFragType();
    }

    public void putFragHandlerData(Bundle bundle)
    {
        Iterator iterator = fragHashMap.keySet().iterator();
        int ai[] = new int[17];
        int i = 0;
        Arrays.fill(ai, -1);
        MtvUtilDebug.Low("MtvUiFragHandler", "putFragHandlerData");
        while(iterator.hasNext()) 
        {
            int j = ((Integer)iterator.next()).intValue();
            bundle.putLong(fragTags[j], ((Long)fragHashMap.get(Integer.valueOf(j))).longValue());
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("putFragHandlerData: saving fragType[").append(fragTags[j]).append("] timeOut[").append(fragHashMap.get(Integer.valueOf(j))).append("]").toString());
            int k = i + 1;
            ai[i] = j;
            stopTimer(j);
            i = k;
        }
        bundle.putIntArray("MtvUiFragHandler", ai);
    }

    public void removeFrag(int i)
    {
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("removeFrag: fragType[").append(i).append("]").toString());
        if(isValidFragment(i)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s = getFragTag(i);
        if(fragMgr != null)
        {
            MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("removeFrag : fragHandler Enabled?:").append(isEnabled).toString());
            if(isEnabled)
            {
                if(5 != i)
                {
                    Fragment fragment = fragMgr.findFragmentByTag(s);
                    if(fragment != null && (fragment.isAdded() || fragment.isVisible()))
                    {
                        FragmentTransaction fragmenttransaction = fragMgr.beginTransaction();
                        fragmenttransaction.remove(fragment);
                        fragmenttransaction.commit();
                    }
                }
                remFragInternal(i);
            }
        }
        stopTimer(i);
        if(i == 5)
            unhideAllFragments();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void removeTimers()
    {
        MtvUtilDebug.Low("MtvUiFragHandler", "removeTimers called...");
        if(mTimedMainFrag != null)
            mHandler.removeCallbacks(mTimedMainFrag);
        if(mTimedStatusFrag != null)
            mHandler.removeCallbacks(mTimedStatusFrag);
        if(mTimedVolumeControlBarFrag != null)
            mHandler.removeCallbacks(mTimedVolumeControlBarFrag);
        if(mTimedCaptureFrag != null)
            mHandler.removeCallbacks(mTimedCaptureFrag);
        if(mTimedCaptureFrag != null)
            mHandler.removeCallbacks(mTimedFPImgFrag);
        if(mTimedCaptureFrag != null)
            mHandler.removeCallbacks(mTimedFPVidFrag);
    }

    public void reset()
    {
        removeTimers();
        fragHashMap.clear();
        mHandler.removeCallbacksAndMessages(null);
        fragHashMap = null;
        fragMgr = null;
    }

    public void resetTimer(int i, long l)
    {
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("resetTimer: fragType[").append(i).append("] timeOut[").append(l).append("]").toString());
        startTimer(i, l);
    }

    public void setEnabled(boolean flag)
    {
        isEnabled = flag;
        MtvUtilDebug.Low("MtvUiFragHandler", (new StringBuilder()).append("setEnabled : fragHandler isEnabled?:").append(isEnabled).toString());
    }

    private int activityType;
    private Map fragHashMap;
    private FragmentManager fragMgr;
    String fragTags[];
    private boolean isEnabled;
    private Handler mHandler;
    private Runnable mTimedCaptureFrag;
    private Runnable mTimedFPImgFrag;
    private Runnable mTimedFPVidFrag;
    private Runnable mTimedMainFrag;
    private Runnable mTimedStatusFrag;
    private Runnable mTimedVolumeControlBarFrag;
    private int viewGroup;

    private class _cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            int _tmp = message.what;
        }

        final MtvUiFragHandler this$0;

        _cls1()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "MainFrag time out, destroying it!!");
            removeFrag(0);
        }

        final MtvUiFragHandler this$0;

        _cls2()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "StatusFrag time out, destroying it!!");
            removeFrag(15);
        }

        final MtvUiFragHandler this$0;

        _cls3()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "mTimedVolumeControlBarFrag time out, destroying it!!");
            removeFrag(12);
        }

        final MtvUiFragHandler this$0;

        _cls4()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "mTimedCaptureFrag time out, destroying it!!");
            removeFrag(4);
        }

        final MtvUiFragHandler this$0;

        _cls5()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "mTimedFPImgFrag time out, destroying it!!");
            removeFrag(13);
        }

        final MtvUiFragHandler this$0;

        _cls6()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }


    private class _cls7
        implements Runnable
    {

        public void run()
        {
            MtvUtilDebug.Low("MtvUiFragHandler", "mTimedFPVidFrag time out, destroying it!!");
            removeFrag(14);
        }

        final MtvUiFragHandler this$0;

        _cls7()
        {
            this$0 = MtvUiFragHandler.this;
            super();
        }
    }

}
