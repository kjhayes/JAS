package StreamIO;

import java.io.FileWriter;

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
        void IncreaseDepth(){
            depth++;
        }
        void DecreaseDepth(){
            depth--;
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

    //Objects
    public static void WriteObject(FileWriter ostr, String name, IJSONWritable obj, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write("{\n");
            formatting.IncreaseDepth();
            obj.WriteJSON(ostr, formatting);
            formatting.DecreaseDepth();
            ostr.write("}");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }

    //Strings
    public static void WriteString(FileWriter ostr, String name, String s, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write("\""+s+"\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }

    //Unsigned Integers
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
    //Signed Integers
    public static void WriteS8(FileWriter ostr, String name, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write(Integer.toString(Byte.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteS16(FileWriter ostr, String name, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write(Integer.toString(Short.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteS32(FileWriter ostr, String name, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write(Integer.toString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteS64(FileWriter ostr, String name, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write(Long.toString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    //Hex Strings
    public static void WriteHex8(FileWriter ostr, String name, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(Byte.toUnsignedInt(value)));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteHex16(FileWriter ostr, String name, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);
        
        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(Short.toUnsignedInt(value)));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteHex32(FileWriter ostr, String name, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(value));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteHex64(FileWriter ostr, String name, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write("\"0x");
            ostr.write(Long.toHexString(value));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }

//Arrays
    public static void BeginWriteArray(FileWriter ostr, String name, Formatting formatting){
        formatting.Initial(ostr);
        WriteNameColon(ostr, name, formatting);

        try{
            ostr.write("[");
        }catch(Exception e){
            System.err.println(e);
        }
        formatting.Final(ostr);
    }
    public static void EndWriteArray(FileWriter ostr, Formatting formatting, boolean is_last){
        formatting.Initial(ostr);
        try{
            ostr.write("]");
        }catch(Exception e){
            System.err.println(e);
        }
        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void BeginWriteArrayArray(FileWriter ostr, Formatting formatting){
        formatting.Initial(ostr);
        try{
            ostr.write("[");
        }catch(Exception e){
            System.err.println(e);
        }
        formatting.Final(ostr);
    }

    //Objects
    public static void WriteArrayObject(FileWriter ostr, IJSONWritable obj, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("{\n");
            formatting.IncreaseDepth();
            obj.WriteJSON(ostr, formatting);
            formatting.DecreaseDepth();
            ostr.write("}");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }

    //Strings
    public static void WriteArrayString(FileWriter ostr, String s, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("\""+s+"\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }

    //Unsigned Integers
    public static void WriteArrayU8(FileWriter ostr, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toUnsignedString(Byte.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayU16(FileWriter ostr, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toUnsignedString(Short.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayU32(FileWriter ostr, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toUnsignedString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayU64(FileWriter ostr, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Long.toUnsignedString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    //Signed Integers
    public static void WriteArrayS8(FileWriter ostr, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toString(Byte.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayS16(FileWriter ostr, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toString(Short.toUnsignedInt(value)));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayS32(FileWriter ostr, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Integer.toString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayS64(FileWriter ostr, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write(Long.toString(value));
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    //Hex Strings
    public static void WriteArrayHex8(FileWriter ostr, byte value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(Byte.toUnsignedInt(value)));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayHex16(FileWriter ostr, short value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(Short.toUnsignedInt(value)));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayHex32(FileWriter ostr, int value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("\"0x");
            ostr.write(Integer.toHexString(value));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
    public static void WriteArrayHex64(FileWriter ostr, long value, Formatting formatting, boolean is_last) {
        formatting.Initial(ostr);
        try{
            ostr.write("\"0x");
            ostr.write(Long.toHexString(value));
            ostr.write("\"");
        }catch(Exception e){
            System.err.println(e);
        }

        HandleLast(ostr, is_last, formatting);
        formatting.Final(ostr);
    }
}