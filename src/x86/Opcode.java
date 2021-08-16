package x86;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.stream.Stream;

import StreamIO.IJSONWritable;
import StreamIO.IStreamReadable;
import StreamIO.IStreamWritable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class Opcode implements IStreamReadable, IStreamWritable {
    boolean two_bytes = false;

    byte first_byte;
    byte second_byte;

    @Override
    public void ReadFromStream(FileInputStream istr){
        first_byte = StreamReader.Read8(istr);
        if(first_byte == 0x0f){
            two_bytes = true;
            second_byte = StreamReader.Read8(istr);
        }
    }
    @Override
    public void WriteToStream(FileOutputStream ostr){
        StreamWriter.Write8(ostr, first_byte);
        if(two_bytes){StreamWriter.Write8(ostr, second_byte);}
    }
}
