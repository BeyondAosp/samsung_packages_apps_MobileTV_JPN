// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.ui.configration;

import android.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;

// Referenced classes of package com.samsung.sec.mtv.ui.configration:
//            MtvUiFragmentConfigration

public class MtvUiConfigration extends Activity
{

    public MtvUiConfigration()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        setContentView(getLayoutInflater().inflate(0x7f030002, null));
        super.onCreate(bundle);
        FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
        fragmenttransaction.add(0x7f0a000f, new MtvUiFragmentConfigration());
        fragmenttransaction.commit();
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.clear();
        return super.onPrepareOptionsMenu(menu);
    }
}
