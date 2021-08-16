package PEFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import StreamIO.IJSONWritable;
import StreamIO.IStreamReadable;
import StreamIO.IStreamWritable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class OptionalHeader implements IJSONWritable, IStreamReadable, IStreamWritable {
    
    public enum MagicNumber {
        PE32 (0x10b),
        PE32Plus (0x20b);

        public short val;
        private MagicNumber(int number){
            val = (short)number;
        }
    }
    
    public class StandardFields implements IJSONWritable, IStreamReadable, IStreamWritable {

        byte major_linker_ver;
        byte minor_linker_ver;
        int size_of_code;
        int size_of_initialized_data;
        int size_of_uninitialized_data;
        int address_of_entry_point;
        int base_of_code;

        @Override 
        public void ReadFromStream(FileInputStream istr){
            major_linker_ver = StreamReader.Read8(istr);
            minor_linker_ver = StreamReader.Read8(istr);
            size_of_code = StreamReader.ReadLE32(istr);
            size_of_initialized_data = StreamReader.ReadLE32(istr);
            size_of_uninitialized_data = StreamReader.ReadLE32(istr);
            address_of_entry_point = StreamReader.ReadLE32(istr);
            base_of_code = StreamReader.ReadLE32(istr);
        }
        @Override 
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.Write8(ostr, major_linker_ver);
            StreamWriter.Write8(ostr, minor_linker_ver);
            StreamWriter.WriteLE32(ostr, size_of_code);
            StreamWriter.WriteLE32(ostr, size_of_initialized_data);
            StreamWriter.WriteLE32(ostr, size_of_uninitialized_data);
            StreamWriter.WriteLE32(ostr, address_of_entry_point);
            StreamWriter.WriteLE32(ostr, base_of_code);
        }
        @Override 
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            IJSONWritable.WriteU8(ostr, "Major Linker Version", major_linker_ver, formatting, false);
            IJSONWritable.WriteU8(ostr, "Minor Linker Version", minor_linker_ver, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Code", size_of_code, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Initialized Data", size_of_initialized_data, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Uninitialized Data", size_of_uninitialized_data, formatting, false);
            IJSONWritable.WriteU32(ostr, "Address Of Entry Point", address_of_entry_point, formatting, false);
            IJSONWritable.WriteU32(ostr, "Base Of Code", base_of_code, formatting, true);
        }
    }

    public class PE32WindowsFields implements IJSONWritable, IStreamReadable, IStreamWritable {

        int base_of_data;
        int image_base;
        int section_alignment;
        int file_alignment;
        short major_operating_system_ver;
        short minor_operating_system_ver;
        short major_image_ver;
        short minor_image_ver;
        short major_subsystem_ver;
        short minor_subsystem_ver;
        int win32_version_value; //Reserved For Zero;
        int size_of_image;
        int size_of_headers;
        int check_sum;
        short subsystem;
        short dllcharacteristics;
        int size_of_stack_reserve;
        int size_of_stack_commit;
        int size_of_heap_reserve;
        int size_of_heap_commit;
        int loader_flags;
        
        @Override 
        public void ReadFromStream(FileInputStream istr){
            base_of_data = StreamReader.ReadLE32(istr);
            image_base = StreamReader.ReadLE32(istr);
            section_alignment = StreamReader.ReadLE32(istr);
            file_alignment = StreamReader.ReadLE32(istr);
            major_operating_system_ver = StreamReader.ReadLE16(istr);
            minor_operating_system_ver = StreamReader.ReadLE16(istr);
            major_image_ver = StreamReader.ReadLE16(istr);
            minor_image_ver = StreamReader.ReadLE16(istr);
            major_subsystem_ver = StreamReader.ReadLE16(istr);
            minor_subsystem_ver = StreamReader.ReadLE16(istr);
            win32_version_value = StreamReader.ReadLE32(istr);
            size_of_image = StreamReader.ReadLE32(istr);
            size_of_headers = StreamReader.ReadLE32(istr);
            check_sum = StreamReader.ReadLE32(istr);
            subsystem = StreamReader.ReadLE16(istr);
            dllcharacteristics = StreamReader.ReadLE16(istr);
            size_of_stack_reserve = StreamReader.ReadLE32(istr);
            size_of_stack_commit = StreamReader.ReadLE32(istr);
            size_of_heap_reserve = StreamReader.ReadLE32(istr);
            size_of_heap_commit = StreamReader.ReadLE32(istr);
            loader_flags = StreamReader.ReadLE32(istr);
        }
        @Override 
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.WriteLE32(ostr, base_of_data);
            StreamWriter.WriteLE32(ostr, image_base);
            StreamWriter.WriteLE32(ostr, section_alignment);
            StreamWriter.WriteLE32(ostr, file_alignment);
            StreamWriter.WriteLE16(ostr, major_operating_system_ver);
            StreamWriter.WriteLE16(ostr, minor_operating_system_ver);
            StreamWriter.WriteLE16(ostr, major_image_ver);
            StreamWriter.WriteLE16(ostr, minor_image_ver);
            StreamWriter.WriteLE16(ostr, major_subsystem_ver);
            StreamWriter.WriteLE16(ostr, minor_subsystem_ver);
            StreamWriter.WriteLE32(ostr, win32_version_value);
            StreamWriter.WriteLE32(ostr, size_of_image);
            StreamWriter.WriteLE32(ostr, size_of_headers);
            StreamWriter.WriteLE32(ostr, check_sum);
            StreamWriter.WriteLE16(ostr, subsystem);
            StreamWriter.WriteLE16(ostr, dllcharacteristics);
            StreamWriter.WriteLE32(ostr, size_of_stack_reserve);
            StreamWriter.WriteLE32(ostr, size_of_stack_commit);
            StreamWriter.WriteLE32(ostr, size_of_heap_reserve);
            StreamWriter.WriteLE32(ostr, size_of_heap_commit);
            StreamWriter.WriteLE32(ostr, loader_flags);
        }
        @Override 
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            IJSONWritable.WriteU32(ostr, "Base Of Data", base_of_data, formatting, false);
            IJSONWritable.WriteU32(ostr, "Image Base", image_base, formatting, false);
            IJSONWritable.WriteU32(ostr, "Section Alignment", section_alignment, formatting, false);
            IJSONWritable.WriteU32(ostr, "File Alignment", file_alignment, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major OS Version", major_operating_system_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor OS Version", minor_operating_system_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major Image Version", major_image_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor Image Version", minor_image_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major Subsystem Version", major_subsystem_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor Subsystem Version", minor_subsystem_ver, formatting, false);
            IJSONWritable.WriteU32(ostr, "Win32 Version Value", win32_version_value, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Image", size_of_image, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Headers", size_of_headers, formatting, false);
            IJSONWritable.WriteU32(ostr, "Check Sum", check_sum, formatting, false);
            IJSONWritable.WriteU16(ostr, "Subsystem", subsystem, formatting, false);
            IJSONWritable.WriteU16(ostr, "DLL Characteristics", dllcharacteristics, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Stack Reserve", size_of_stack_reserve, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Stack Commit", size_of_stack_commit, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Heap Reserve", size_of_heap_reserve, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Heap Commit", size_of_heap_commit, formatting, false);
            IJSONWritable.WriteU32(ostr, "Loader Flags", loader_flags, formatting, true);
        }
    }    
    public class PE32PlusWindowsFields implements IJSONWritable, IStreamReadable, IStreamWritable {    
        
        long image_base;
        int section_alignment;
        int file_alignment;
        short major_operating_system_ver;
        short minor_operating_system_ver;
        short major_image_ver;
        short minor_image_ver;
        short major_subsystem_ver;
        short minor_subsystem_ver;
        int win32_version_value; //Reserved For Zero;
        int size_of_image;
        int size_of_headers;
        int check_sum;
        short subsystem;
        short dllcharacteristics;
        long size_of_stack_reserve;
        long size_of_stack_commit;
        long size_of_heap_reserve;
        long size_of_heap_commit;
        int loader_flags;

        @Override 
        public void ReadFromStream(FileInputStream istr){
            image_base = StreamReader.ReadLE64(istr);
            section_alignment = StreamReader.ReadLE32(istr);
            file_alignment = StreamReader.ReadLE32(istr);
            major_operating_system_ver = StreamReader.ReadLE16(istr);
            minor_operating_system_ver = StreamReader.ReadLE16(istr);
            major_image_ver = StreamReader.ReadLE16(istr);
            minor_image_ver = StreamReader.ReadLE16(istr);
            major_subsystem_ver = StreamReader.ReadLE16(istr);
            minor_subsystem_ver = StreamReader.ReadLE16(istr);
            win32_version_value = StreamReader.ReadLE32(istr);
            size_of_image = StreamReader.ReadLE32(istr);
            size_of_headers = StreamReader.ReadLE32(istr);
            check_sum = StreamReader.ReadLE32(istr);
            subsystem = StreamReader.ReadLE16(istr);
            dllcharacteristics = StreamReader.ReadLE16(istr);
            size_of_stack_reserve = StreamReader.ReadLE64(istr);
            size_of_stack_commit = StreamReader.ReadLE64(istr);
            size_of_heap_reserve = StreamReader.ReadLE64(istr);
            size_of_heap_commit = StreamReader.ReadLE64(istr);
            loader_flags = StreamReader.ReadLE32(istr);
        }
        @Override 
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.WriteLE64(ostr, image_base);
            StreamWriter.WriteLE32(ostr, section_alignment);
            StreamWriter.WriteLE32(ostr, file_alignment);
            StreamWriter.WriteLE16(ostr, major_operating_system_ver);
            StreamWriter.WriteLE16(ostr, minor_operating_system_ver);
            StreamWriter.WriteLE16(ostr, major_image_ver);
            StreamWriter.WriteLE16(ostr, minor_image_ver);
            StreamWriter.WriteLE16(ostr, major_subsystem_ver);
            StreamWriter.WriteLE16(ostr, minor_subsystem_ver);
            StreamWriter.WriteLE32(ostr, win32_version_value);
            StreamWriter.WriteLE32(ostr, size_of_image);
            StreamWriter.WriteLE32(ostr, size_of_headers);
            StreamWriter.WriteLE32(ostr, check_sum);
            StreamWriter.WriteLE16(ostr, subsystem);
            StreamWriter.WriteLE16(ostr, dllcharacteristics);
            StreamWriter.WriteLE64(ostr, size_of_stack_reserve);
            StreamWriter.WriteLE64(ostr, size_of_stack_commit);
            StreamWriter.WriteLE64(ostr, size_of_heap_reserve);
            StreamWriter.WriteLE64(ostr, size_of_heap_commit);
            StreamWriter.WriteLE32(ostr, loader_flags);
        }
        @Override 
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            IJSONWritable.WriteU64(ostr, "Image Base", image_base, formatting, false);
            IJSONWritable.WriteU32(ostr, "Section Alignment", section_alignment, formatting, false);
            IJSONWritable.WriteU32(ostr, "File Alignment", file_alignment, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major OS Version", major_operating_system_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor OS Version", minor_operating_system_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major Image Version", major_image_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor Image Version", minor_image_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Major Subsystem Version", major_subsystem_ver, formatting, false);
            IJSONWritable.WriteU16(ostr, "Minor Subsystem Version", minor_subsystem_ver, formatting, false);
            IJSONWritable.WriteU32(ostr, "Win32 Version Value", win32_version_value, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Image", size_of_image, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size Of Headers", size_of_headers, formatting, false);
            IJSONWritable.WriteU32(ostr, "Check Sum", check_sum, formatting, false);
            IJSONWritable.WriteU16(ostr, "Subsystem", subsystem, formatting, false);
            IJSONWritable.WriteU16(ostr, "DLL Characteristics", dllcharacteristics, formatting, false);
            IJSONWritable.WriteU64(ostr, "Size Of Stack Reserve", size_of_stack_reserve, formatting, false);
            IJSONWritable.WriteU64(ostr, "Size Of Stack Commit", size_of_stack_commit, formatting, false);
            IJSONWritable.WriteU64(ostr, "Size Of Heap Reserve", size_of_heap_reserve, formatting, false);
            IJSONWritable.WriteU64(ostr, "Size Of Heap Commit", size_of_heap_commit, formatting, false);
            IJSONWritable.WriteU32(ostr, "Loader Flags", loader_flags, formatting, true);
        }
    }

    public class RVASizePair implements IStreamReadable, IStreamWritable, IJSONWritable {
        public int virtual_address;
        public int size;

        @Override
        public void ReadFromStream(FileInputStream istr){
            virtual_address = StreamReader.ReadLE32(istr);
            size = StreamReader.ReadLE32(istr);
        }
        @Override
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.WriteLE32(ostr, virtual_address);
            StreamWriter.WriteLE32(ostr, size);
        }
        @Override
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            IJSONWritable.WriteHex32(ostr, "Virtual Address", virtual_address, formatting, false);
            IJSONWritable.WriteU32(ostr, "Size", size, formatting, true);
        }
    }
    public class RVASizeTable implements IStreamReadable, IStreamWritable, IJSONWritable {
        public int number_of_rva_and_sizes;
        public RVASizePair[] rva_size_pairs;

        private static final String[] table_names_array = {
            "Export Table",
            "Import Table",
            "Resource Table",
            "Exception Table",
            "Certificate Table",
            "Base Relocation Table",
            "Debug",
            "Architecture", //Reserved Must Be Zero
            "Global Ptr",
            "TLS Table",
            "Load Config Table",
            "Bound Import",
            "IAT",
            "Delay Import Descriptor",
            "CLR Runtime Header",
            "Reserved"
        };

        @Override
        public void ReadFromStream(FileInputStream istr){
            number_of_rva_and_sizes = StreamReader.ReadLE32(istr);
            rva_size_pairs = new RVASizePair[number_of_rva_and_sizes];
            for(int i = 0; i < number_of_rva_and_sizes; i++){
                rva_size_pairs[i] = new RVASizePair();
                rva_size_pairs[i].ReadFromStream(istr);
            }
        }
        @Override
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.WriteLE32(ostr, number_of_rva_and_sizes);
            for(int i = 0; i < number_of_rva_and_sizes; i++){
                rva_size_pairs[i].WriteToStream(ostr);
            }
        }
        @Override
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            IJSONWritable.WriteU32(ostr, "Count", number_of_rva_and_sizes, formatting, false);
            for(int i = 0; i < number_of_rva_and_sizes; i++){
                IJSONWritable.WriteObject(ostr, table_names_array[i], rva_size_pairs[i], formatting, (i == (number_of_rva_and_sizes-1)));
            }
        }    
    }

    MagicNumber magic_number;
    StandardFields standard_fields = new StandardFields();

//This is where a union would be useful, but sadly Java doesn't have them.
    PE32WindowsFields pe32 = null;
    PE32PlusWindowsFields pe32plus = null;
//
    RVASizeTable rva_size_table = new RVASizeTable();

    @Override
    public void ReadFromStream(FileInputStream istr){
        short mn = StreamReader.ReadLE16(istr);
        standard_fields.ReadFromStream(istr);
        if(mn == 0x10b){
            magic_number = MagicNumber.PE32;
            pe32plus = null;
            pe32 = new PE32WindowsFields();
            pe32.ReadFromStream(istr);
        }
        else if(mn == 0x20b){
            magic_number = MagicNumber.PE32Plus;
            pe32 = null;
            pe32plus = new PE32PlusWindowsFields();
            pe32plus.ReadFromStream(istr);
        }
        rva_size_table.ReadFromStream(istr);
    }
    @Override
    public void WriteToStream(FileOutputStream ostr){
        StreamWriter.WriteLE16(ostr, magic_number.val);
        standard_fields.WriteToStream(ostr);
        if((pe32 != null) && (pe32plus == null)){
                pe32.WriteToStream(ostr);
        }
        else if((pe32plus != null) && (pe32 == null)){
            pe32plus.WriteToStream(ostr);
        }
        else{
            System.err.println("ERROR: Both PE32 And PE32+ Are Defined Within A Single Optional Header!");
        }
        rva_size_table.WriteToStream(ostr);
    }
    @Override
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting) {
        IJSONWritable.WriteU16(ostr, "Magic Number", magic_number.val, formatting, false);
        IJSONWritable.WriteObject(ostr, "Standard Fields", standard_fields, formatting, false);
        if((pe32 != null) && (pe32plus == null)){
            IJSONWritable.WriteObject(ostr, "PE32 Fields", pe32, formatting, false);
        }
        else if((pe32plus != null) && (pe32 == null)){
            IJSONWritable.WriteObject(ostr, "PE32+ Fields", pe32plus, formatting, false);
        }
        else{
            System.err.println("ERROR: Both PE32 And PE32+ Are Defined Within A Single Optional Header!");
        }
        IJSONWritable.WriteObject(ostr, "RVA And Sizes Table", rva_size_table, formatting, true);
    }
}
