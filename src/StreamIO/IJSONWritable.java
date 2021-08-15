package StreamIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.Normalizer.Form;

public interface IJSONWritable {
    public class Formatting {
        int depth = 0;
        boolean use_depth = true;
        boolean new_line_after_each_call = true;
        
        void Initial(FileWriter ostr) {
            if(use_depth){
                for(int i = 0; i<depth; i++){
                    try{
                        ostr.write("\t");
                    }catch(Exception e){
                        System.err.println(e);
                    }
                }
            }
        }
        void Final(FileWriter ostr) {
            if(new_line_after_each_call) {
                try{
                    ostr.write("\n");
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }
    public void WriteJSON(FileWriter ostr, Formatting formatting);

    private static void WriteNameColon(FileWriter ostr, String name, Formatting formatting){
        try{
            ostr.write("\""+name+"\" : ");
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
    private static void HandleLast(FileWriter ostr, boolean is_last, Formatting formatting){
        if(!is_last){
            try{
                ostr.write(", ");
            }catch(Exception e){
                System.err.println(e);
            }
        }
    }

    public static void WriteObject(FileWriter ostr, String name, IJSONWritable obj, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write("{");
            obj.WriteJSON(ostr, formatting);
            ostr.write("}");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteU8(FileWriter ostr, String name, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write(Integer.toUnsignedString(Byte.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteU16(FileWriter ostr, String name, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write(Integer.toUnsignedString(Short.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteU32(FileWriter ostr, String name, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write(Integer.toUnsignedString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteU64(FileWriter ostr, String name, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write(Long.toUnsignedString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
}
