package x86;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import StreamIO.IJSONWritable;
import StreamIO.IStreamReadable;
import StreamIO.IStreamWritable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public abstract class Instruction implements IStreamReadable, IStreamWritable, IJSONWritable {
    PrefixByte[] prefix_bytes = new PrefixByte[4];
    int number_of_prefix_bytes = 0;

    Opcode opcode = new Opcode();
    ModRegRM mod_reg_rm = null;
    OSIB optional_scaled_indexed_byte = null;
    Displacement displacement = null;
    Immediate immediate = null;

    @Override 
    public void ReadFromStream(FileInputStream istr){
        byte last_read;
        number_of_prefix_bytes = 0;
        do{
            last_read = StreamReader.Read8(istr);
            prefix_bytes[number_of_prefix_bytes] = null;
            prefix_bytes[number_of_prefix_bytes] = PrefixByte.GetPrefixByte(last_read);
            if(prefix_bytes[number_of_prefix_bytes] != null){number_of_prefix_bytes++;}
        }while(prefix_bytes[number_of_prefix_bytes] != null);
        opcode.ReadFromStream(istr);
    }
    @Override
    public void WriteToStream(FileOutputStream ostr){
        for(int i = 0; i < number_of_prefix_bytes; i++){
            StreamWriter.Write8(ostr, prefix_bytes[i].val);
        }
        opcode.WriteToStream(ostr);
    }
    @Override
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
        if(number_of_prefix_bytes > 0){
            IJSONWritable.BeginWriteArray(ostr, "Prefix Bytes", formatting);
            for(int i = 0; i < number_of_prefix_bytes; i++){
                IJSONWritable.WriteArrayString(ostr, prefix_bytes[i].desc, formatting, (i == (number_of_prefix_bytes-1)));
            }
            IJSONWritable.EndWriteArray(ostr, formatting, false);
        }
        if(!opcode.two_bytes){
            IJSONWritable.WriteHex8(ostr, "Opcode", opcode.first_byte, formatting, true);        
        }
        else{
            IJSONWritable.WriteHex8(ostr, "Opcode Two Byte Prefix", opcode.first_byte, formatting, false);
            IJSONWritable.WriteHex8(ostr, "Opcode", opcode.second_byte, formatting, true);
        }
    }
}
