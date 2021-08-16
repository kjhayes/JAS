package x86;

public enum PrefixByte {
    ES_OVR (0x26, "ES Segment Override"),
    CS_OVR (0x2e, "CS Segment Override"),
    SS_OVR (0x36, "SS Segment Override"),
    DS_OVR (0x3e, "DS Segment Override"),
    FS_OVR (0x64, "FS Segment Override"),
    GS_OVR (0x65, "GS Segment Override"),
    OPR_SIZE_OVR (0x66, "Operand Size Override"),
    PRC_SIZE_OVR (0x67, "Precision Size Override"),
    ADR_SIZE_OVR (0x68, "Address Size Override"),
    WAIT (0x9b, "Wait"),
    LOCK (0xf0, "Lock"),
    REPNE (0xf2, "Repeat NE"),
    REP (0xf3, "Repeat");

    public static final int[] possible_prefixes = {
        0x26,
        0x2e,
        0x36,
        0x3e,
        0x64,
        0x65,
        0x66,
        0x67,
        0x68,
        0x9b,
        0xf0,
        0xf2,
        0xf3
    };

    byte val;
    String desc;
    private PrefixByte(int b, String description){
        val = (byte)b;
        desc = description;
    }

    public static PrefixByte GetPrefixByte(byte b){
        switch(b){
            case((byte)0x26):{return ES_OVR;}
            case((byte)0x2e):{return CS_OVR;}
            case((byte)0x36):{return SS_OVR;}
            case((byte)0x3e):{return DS_OVR;}
            case((byte)0x64):{return FS_OVR;}
            case((byte)0x65):{return GS_OVR;}
            case((byte)0x66):{return OPR_SIZE_OVR;}
            case((byte)0x67):{return PRC_SIZE_OVR;}
            case((byte)0x68):{return ADR_SIZE_OVR;}
            case((byte)0x9b):{return WAIT;}
            case((byte)0xf0):{return LOCK;}
            case((byte)0xf2):{return REPNE;}
            case((byte)0xf3):{return REP;}
        }
        return null;
    }
}