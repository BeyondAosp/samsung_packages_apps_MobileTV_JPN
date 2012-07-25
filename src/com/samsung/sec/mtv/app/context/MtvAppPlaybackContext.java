// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package com.samsung.sec.mtv.app.context;


// Referenced classes of package com.samsung.sec.mtv.app.context:
//            MtvAppPlaybackState, MtvAppProgramAttributes, MtvAppProgramComponents

public final class MtvAppPlaybackContext
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(com/samsung/sec/mtv/app/context/MtvAppPlaybackContext$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type LIVETV;
        public static final Type LOCALTV;
        public static final Type SCANNER;
        public static final Type TVLINK;

        static 
        {
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
            super(s, i);
        }
    }


    private MtvAppPlaybackContext()
    {
    }

    MtvAppPlaybackContext(Type type1)
    {
        type = type1;
        state = new MtvAppPlaybackState();
        attrib = new MtvAppProgramAttributes();
        comps = new MtvAppProgramComponents();
    }

    public MtvAppProgramAttributes getAttribute()
    {
        return attrib;
    }

    public MtvAppProgramComponents getComponents()
    {
        return comps;
    }

    public MtvAppPlaybackState getState()
    {
        return state;
    }

    public Type getType()
    {
        return type;
    }

    public void reset()
    {
        comps.reset();
        attrib.reset();
        state.reset();
    }

    private MtvAppProgramAttributes attrib;
    private MtvAppProgramComponents comps;
    private MtvAppPlaybackState state;
    private Type type;
}
