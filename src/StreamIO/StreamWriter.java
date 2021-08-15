package StreamIO;

import java.io.FileOutputStream;

//static
public class StreamWriter {
    // These Functions Are Completely Pointless Because Narrowing Conversion Of Integral Primitives is Already Done In Raw Bits
    /*
    public static byte LSShortByteToSignedByteRaw(short s) {
        s &= 0xFF;
        if(s > 127){
            s = (short)(256 - s);
            s *= -1;
        }
        return (byte)s;
    }
    public static byte LSIntByteToSignedByteRaw(int i) {
        i &= 0xFF;
        if(i > 127){
            i = 256 - i;
            i *= -1;
        }
        return (byte)i;
    }
    public static byte LSLongByteToSignedByteRaw(long l) {
        l &= 0xFF;
        if(l > 127L){
            l = 256L - l;
            l *= -1L;
        }
        return (byte)l;
    }
    */

    public static void Write8(FileOutputStream ostr, byte b) {
        try{
            ostr.write(b&0xFF);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    //Little Endian
    public static void WriteLE16(FileOutputStream ostr, short s) {
        Write8(ostr, (byte)s);
        Write8(ostr, (byte)(s>>8));
    }
    public static void WriteLE32(FileOutputStream ostr, int i) {
        WriteLE16(ostr, (short)i);
        WriteLE16(ostr, (short)(i>>16));
    }
    public static void WriteLE64(FileOutputStream ostr, long l) {
        WriteLE32(ostr, (int)l);
        WriteLE32(ostr, (int)(l>>32));
    }
}
