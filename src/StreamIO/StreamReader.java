package StreamIO;

import java.io.FileInputStream;

//static
public final class StreamReader {
    public static byte Read8(FileInputStream istr) {
        byte b = 0x00;
        try{
            b = (byte)(istr.read()&0xFF);
        }catch(Exception e){
            System.err.println(e);
        }
        return b;
    }

    //Little Endian
    public static short ReadLE16(FileInputStream istr) {
        short s = (short)(Read8(istr)&0xFF);
        s |= (short)((Read8(istr)&0xFF)<<8);
        return s;
    }
    public static int ReadLE32(FileInputStream istr) {
        int i = ReadLE16(istr)&0xFFFF;
        i |= ((ReadLE16(istr)&0xFFFF)<<16);
        return i;
    }
    public static long ReadLE64(FileInputStream istr) {
        long l = ReadLE32(istr)&0xFFFFFFFFL;
        l |= ((ReadLE32(istr)&0xFFFFFFFFL)<<32);
        return l;
    }
}
