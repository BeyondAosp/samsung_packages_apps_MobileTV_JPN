// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;

import android.util.Log;

// Referenced classes of package com.samsung.sec.mtv.app.context:
//            MtvAppPlaybackState, MtvAppProgramAttributes, MtvAppProgramComponents

public final class MtvAppPlaybackContext
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;->valueOf(Ljava/lang/String;)Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;");
            return (Type)Enum.valueOf(com/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type, s);
        }

        public static Type[] values()
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;->values()[Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;");
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type LIVETV;
        public static final Type LOCALTV;
        public static final Type SCANNER;
        public static final Type TVLINK;

        static 
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;-><clinit>()V");
            LIVETV = new Type("LIVETV", 0);
            LOCALTV = new Type("LOCALTV", 1);
            TVLINK = new Type("TVLINK", 2);
            SCANNER = new Type("SCANNER", 3);
            Type atype[] = new Type[4];
            atype[0] = LIVETV;
            atype[1] = LOCALTV;
            atype[2] = TVLINK;
            atype[3] = SCANNER;
            $VALUES = atype;
        }

        private Type(String s, int i)
        {
            Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;-><init>(Ljava/lang/String;I)V");
            super(s, i);
        }
    }


    private MtvAppPlaybackContext()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;-><init>()V");
        super();
    }

    MtvAppPlaybackContext(Type type1)
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;-><init>(Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;)V");
        super();
        type = type1;
        state = new MtvAppPlaybackState();
        attrib = new MtvAppProgramAttributes();
        comps = new MtvAppProgramComponents();
    }

    public MtvAppProgramAttributes getAttribute()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;->getAttribute()Lcom/samsung/sec/mtv/app/context/MtvAppProgramAttributes;");
        return attrib;
    }

    public MtvAppProgramComponents getComponents()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;->getComponents()Lcom/samsung/sec/mtv/app/context/MtvAppProgramComponents;");
        return comps;
    }

    public MtvAppPlaybackState getState()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;->getState()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackState;");
        return state;
    }

    public Type getType()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;->getType()Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type;");
        return type;
    }

    public void reset()
    {
        Log.d("smali", "Lcom/samsung/sec/mtv/app/context/MtvAppPlaybackContext;->reset()V");
        comps.reset();
        attrib.reset();
        state.reset();
    }

    private MtvAppProgramAttributes attrib;
    private MtvAppProgramComponents comps;
    private MtvAppPlaybackState state;
    private Type type;
}
