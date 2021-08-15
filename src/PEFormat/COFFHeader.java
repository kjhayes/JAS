package PEFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashSet;

import StreamIO.IJSONWritable;
import StreamIO.IStreamReadable;
import StreamIO.IStreamWritable;

import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class COFFHeader implements IStreamReadable, IStreamWritable, IJSONWritable {
    //Inner Classes//
    public enum IMAGE_FILE_MACHINE implements IStreamWritable { 
        UNKNOWN (0x0, "Any"),
        AM33 (0x1d3, "Matsushita AM33"),
        AMD64 (0x8664, "x64"),
        ARM (0x1c0, "ARM little endian"),
        ARM64 (0xaa64, "ARM64 little endian"),
        ARMNT (0x1c4, "ARM Thumb-2 little endian"),
        EBC (0xebc, "EFI byte code"),
        I386 (0x14c, "Intel 386"),
        IA64 (0x200, "Intel Itanium"),
        M32R (0x9041, "Mitsubishi M32R little endian"),
        MIPS16 (0x266, "MIPS16"),
        MIPSFPU (0x366, "MIPS with FPU"),
        POWERPC (0x1f0, "Power PC little endian"),
        POWERPCFP (0x1f1, "PowerPC with FPU Support"),
        R4000 (0x166, "MIPS little endian"),
        RISCV32 (0x5032, "RISC-V 32-bit Address Space"),
        RISCV64 (0x5064, "RISC-V 64-bit Address Space"),
        RISCV128 (0x5128, "RISC-V 128-bit Address Space"),
        SH3 (0x1a2, "Hitachi SH3 DSP"),
        SH4 (0x1a6, "Hitachi SH4"),
        SH5 (0x1a8, "Hitachi SH5"),
        THUMB (0x1c2, "Thumb"),
        WCEMIPSV2 (0x169, "MIPS little endian WCE v2");

        short value;
        String desc_name;
        private IMAGE_FILE_MACHINE(int i, String s){
            value = (short)i;
            desc_name = s;
        }
        public static IMAGE_FILE_MACHINE ReadFromStream(FileInputStream istr) {
            switch(StreamReader.ReadLE16(istr)&0xFFFF){
                case 0x0:{return UNKNOWN;}
                case 0x1d3:{return AM33;}
                case 0x8664:{return AMD64;}
                case 0x1c0:{return ARM;}
                case 0xaa64:{return ARM64;}
                case 0x1c4:{return ARMNT;}
                case 0xebc:{return EBC;}
                case 0x14c:{return I386;}
                case 0x200:{return IA64;}
                case 0x9041:{return M32R;}
                case 0x266:{return MIPS16;}
                case 0x366:{return MIPSFPU;}
                case 0x1f0:{return POWERPC;}
                case 0x1f1:{return POWERPCFP;}
                case 0x166:{return R4000;}
                case 0x5032:{return RISCV32;}
                case 0x5064:{return RISCV64;}
                case 0x5128:{return RISCV128;}
                case 0x1a2:{return SH3;}
                case 0x1a6:{return SH4;}
                case 0x1a8:{return SH5;}
                case 0x1c2:{return THUMB;}
                case 0x169:{return WCEMIPSV2;}
            }
            return UNKNOWN;
        }
        @Override 
        public void WriteToStream(FileOutputStream ostr){
            StreamWriter.WriteLE16(ostr, (short)value);
        }
    }
    //end IMAGE_FILE_MACHINE
    public enum IMAGE_FILE_CHARACTERISTIC {            
        RELOCS_STRIPPED (0x0001, "RELOCS_STRIPPED"),
        EXECUTABLE_IMAGE (0x0002, "EXECUTABLE_IMAGE"),
        LINE_NUMS_STRIPPED (0x0004, "LINE_NUMS_STRIPPED"),
        LOCAL_SYMS_STRIPPED (0x0008, "LOCAL_SYMS_STRIPPED"),
        AGGRESSIVE_WS_TRIM (0x0010, "AGGRESSIVE_WS_TRIM"),
        LARGE_ADDRESS_AWARE (0x0020, "LARGE_ADDRESS_AWARE"),
        RESERVED (0x0040, "RESERVED_FOR_FUTURE_USE"),
        BYTES_REVERSED_LO (0x0080, "BYTES_REVERSED_LO"),
        _32BIT_MACHINE (0x0100, "32BIT_MACHINE"),
        DEBUG_STRIPPED (0x0200, "DEBUG_STRIPPED"),
        REMOVABLE_RUN_FROM_SWAP (0x0400, "REMOVABLE_RUN_FROM_SWAP"),
        NET_RUN_FROM_SWAP (0x0800, "NET_RUN_FROM_SWAP"),
        FILE_SYSTEM (0x1000, "FILE_SYSTEM"),
        FILE_DLL (0x2000, "FILE_DLL"),
        FILE_UP_SYSTEM_ONLY (0x4000, "FILE_UP_SYSTEM_ONLY"),
        FILE_BYTES_REVERSED_HI (0x8000, "FILE_BYTES_REVERSED_HO");


        short mask_val;
        String name;
        private IMAGE_FILE_CHARACTERISTIC(int i, String s){
            mask_val = (short)i;
            name = s;
        }

        public static HashSet<IMAGE_FILE_CHARACTERISTIC> ReadFromBitMask(short bitmask) {
            HashSet<IMAGE_FILE_CHARACTERISTIC> hash = new HashSet<IMAGE_FILE_CHARACTERISTIC>();
            int i = bitmask&0xFFFF;
            if((i&0x0001) != 0){hash.add(RELOCS_STRIPPED);}
            if((i&0x0002) != 0){hash.add(EXECUTABLE_IMAGE);}
            if((i&0x0004) != 0){hash.add(LINE_NUMS_STRIPPED);}
            if((i&0x0008) != 0){hash.add(LOCAL_SYMS_STRIPPED);}
            if((i&0x0010) != 0){hash.add(AGGRESSIVE_WS_TRIM);}
            if((i&0x0020) != 0){hash.add(LARGE_ADDRESS_AWARE);}
            if((i&0x0040) != 0){hash.add(RESERVED);}
            if((i&0x0080) != 0){hash.add(BYTES_REVERSED_LO);}
            if((i&0x0100) != 0){hash.add(_32BIT_MACHINE);}
            if((i&0x0200) != 0){hash.add(DEBUG_STRIPPED);}
            if((i&0x0400) != 0){hash.add(REMOVABLE_RUN_FROM_SWAP);}
            if((i&0x0800) != 0){hash.add(NET_RUN_FROM_SWAP);}
            if((i&0x1000) != 0){hash.add(FILE_SYSTEM);}
            if((i&0x2000) != 0){hash.add(FILE_DLL);}
            if((i&0x4000) != 0){hash.add(FILE_UP_SYSTEM_ONLY);}
            if((i&0x8000) != 0){hash.add(FILE_BYTES_REVERSED_HI);}
            return hash;
        }
    }


    //uint16
    IMAGE_FILE_MACHINE machine; //enum
    //uint16
    short number_of_sections;
    //uint32
    int time_date_stamp;
    //uint32
    int pointer_to_symbol_table = 0; //DEPRECATED: SHOULD BE ZERO
    //uint32
    int number_of_symbols = 0; //DEPRECATED: SHOULD BE ZERO
    //uint16
    short size_of_optional_header; //Not Required For Object Files 
    //uint16
    HashSet<IMAGE_FILE_CHARACTERISTIC> characteristics; //bitmask

    //Methods//

    @Override
    public void ReadFromStream(FileInputStream istr){
        machine = IMAGE_FILE_MACHINE.ReadFromStream(istr);
        number_of_sections = StreamReader.ReadLE16(istr);
        time_date_stamp = StreamReader.ReadLE32(istr);
        pointer_to_symbol_table = StreamReader.ReadLE32(istr);
        number_of_symbols = StreamReader.ReadLE32(istr);
        size_of_optional_header = StreamReader.ReadLE16(istr);
        characteristics = IMAGE_FILE_CHARACTERISTIC.ReadFromBitMask(StreamReader.ReadLE16(istr));
    }
    @Override
    public void WriteToStream(FileOutputStream ostr){
        StreamWriter.WriteLE16(ostr, machine.value);
        StreamWriter.WriteLE16(ostr, number_of_sections);
        StreamWriter.WriteLE32(ostr, time_date_stamp);
        StreamWriter.WriteLE32(ostr, pointer_to_symbol_table);
        StreamWriter.WriteLE32(ostr, number_of_symbols);
        StreamWriter.WriteLE16(ostr, size_of_optional_header);
        short characteristics_masked = 0;
        for (IMAGE_FILE_CHARACTERISTIC c : characteristics) {
            characteristics_masked |= c.mask_val;            
        }
        StreamWriter.WriteLE16(ostr, characteristics_masked);
    }
    @Override
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting) {
        IJSONWritable.WriteU16(ostr, "Machine", machine.value, formatting, false);
        IJSONWritable.WriteU16(ostr, "Number Of Sections", number_of_sections, formatting, false);
        IJSONWritable.WriteU32(ostr, "Time Date Stamp", time_date_stamp, formatting, false);
        IJSONWritable.WriteU32(ostr, "Pointer To Symbol Table", pointer_to_symbol_table, formatting, false);
        IJSONWritable.WriteU32(ostr, "Number Of Symbols", number_of_symbols, formatting, false);
        IJSONWritable.WriteU16(ostr, "Size Of Optional Header", size_of_optional_header, formatting, false);
        short characteristics_masked = 0;
        for (IMAGE_FILE_CHARACTERISTIC c : characteristics) {
            characteristics_masked |= c.mask_val;            
        }
        IJSONWritable.WriteU16(ostr, "Characteristics", characteristics_masked, formatting, true);
    }
}
