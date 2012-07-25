// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.utility;

import android.app.ActivityManager;
import android.broadcast.helper.MtvUtilDebug;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.PowerManager;
import android.view.*;
import java.util.List;

// Referenced classes of package com.samsung.sec.mtv.utility:
//            MtvUtilTvOut

public class MtvUtilAppService
{

    private MtvUtilAppService()
    {
    }

    public static boolean forceresetMtvVisibiltySettings()
    {
        MtvUtilTvOut.resumeTvOut();
        return true;
    }

    public static int getCurrentOrientation(Context context)
    {
        int i = ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRotation();
        int j;
        if(i == 1 || i == 3)
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUtilAppService", "Lanscape...");
            j = 1;
        } else
        {
            if(!MtvUtilDebug.isReleaseMode())
                MtvUtilDebug.Low("MtvUtilAppService", "Portrait...");
            j = 0;
        }
        return j;
    }

    public static int getRotation(Context context)
    {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static boolean isAbnormalExit()
    {
        return isAbnormalExit;
    }

    public static boolean isAppExiting()
    {
        return isAppExiting;
    }

    public static boolean isIntentAvailable(Context context, Intent intent)
    {
        boolean flag;
        if(context.getPackageManager().queryIntentActivities(intent, 0x10000).size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isScreenOn(Context context)
    {
        return ((PowerManager)context.getSystemService("power")).isScreenOn();
    }

    public static void releaseCPUPartialWakeLock()
    {
        if(mCpuWakeLock != null && mCpuWakeLock.isHeld())
            mCpuWakeLock.release();
    }

    public static boolean resetMtvVisibiltySettings(Context context)
    {
        String s;
        s = null;
        ActivityManager activitymanager = (ActivityManager)context.getSystemService("activity");
        if(activitymanager != null)
            s = ((android.app.ActivityManager.RunningTaskInfo)activitymanager.getRunningTasks(1).get(0)).topActivity.getClassName();
        if(s == null) goto _L2; else goto _L1
_L1:
        if(!MtvUtilDebug.isReleaseMode())
            MtvUtilDebug.Low("MtvUtilAppService", (new StringBuilder()).append("resetMtvVisibiltySettings() :topActivityName = ").append(s).toString());
        if(s.contains("com.samsung.sec.mtv")) goto _L2; else goto _L3
_L3:
        boolean flag;
        MtvUtilDebug.Low("MtvUtilAppService", "resetMtvVisibiltySettings: Not Top Activity.. resetting ");
        MtvUtilTvOut.resumeTvOut();
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static void setAbnormalExit(boolean flag)
    {
        isAbnormalExit = flag;
    }

    public static void setAppExiting(boolean flag)
    {
        isAppExiting = flag;
    }

    public static boolean setMtvVisibiltySettings(Context context)
    {
        MtvUtilDebug.Low("MtvUtilAppService", "setMtvVisibiltySettings()");
        MtvUtilTvOut.suspendTvOut();
        return true;
    }

    public static void unbindDrawables(View view)
    {
        if(view != null && view.getBackground() != null)
            view.getBackground().setCallback(null);
        if(view instanceof ViewGroup)
        {
            for(int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
                unbindDrawables(((ViewGroup)view).getChildAt(i));

            ((ViewGroup)view).removeAllViews();
        }
    }

    private static boolean isAbnormalExit = false;
    private static boolean isAppExiting = false;
    private static android.os.PowerManager.WakeLock mCpuWakeLock;

}
