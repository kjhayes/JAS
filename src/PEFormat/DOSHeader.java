package PEFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import StreamIO.IJSONWritable;
import StreamIO.IStreamReadable;
import StreamIO.IStreamWritable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class DOSHeader implements IStreamReadable, IStreamWritable, IJSONWritable {
//Constants
    final static short expected_magic_number = (short)(0x5A4D);
    
//Variables

    short magic_number;
    short bytes_on_last_page_of_file;
    short pages_in_file;
    short relocations;
    short size_of_header_in_paragraphs;
    short minimum_extra_paragraphs_needed;
    short maximum_extra_paragraphs_needed;
    short initial_relative_ss;
    short initial_sp;
    short check_sum;
    short initial_ip;
    short initial_relative_cs;
    short relocation_table_address;
    short overlay_number;
    short[] reserved_words = new short[4];
    short oem_identifier;
    short oem_info;
    short[] reserved_words_2 = new short[10];
    int new_exe_header_address;

    @Override
    public void ReadFromStream(FileInputStream istr) {
        magic_number = StreamReader.ReadLE16(istr);
        bytes_on_last_page_of_file = StreamReader.ReadLE16(istr);
        pages_in_file = StreamReader.ReadLE16(istr);
        relocations = StreamReader.ReadLE16(istr);
        size_of_header_in_paragraphs = StreamReader.ReadLE16(istr);
        minimum_extra_paragraphs_needed = StreamReader.ReadLE16(istr);
        maximum_extra_paragraphs_needed = StreamReader.ReadLE16(istr);
        initial_relative_ss = StreamReader.ReadLE16(istr);
        initial_sp = StreamReader.ReadLE16(istr);
        check_sum = StreamReader.ReadLE16(istr);
        initial_ip = StreamReader.ReadLE16(istr);
        initial_relative_cs = StreamReader.ReadLE16(istr);
        relocation_table_address = StreamReader.ReadLE16(istr);
        overlay_number = StreamReader.ReadLE16(istr);
        for(int i = 0; i < 4; i++){
            reserved_words[i] = StreamReader.ReadLE16(istr);
        }
        oem_identifier = StreamReader.ReadLE16(istr);
        oem_info = StreamReader.ReadLE16(istr);
        for(int i = 0; i < 10; i++){
            reserved_words_2[i] = StreamReader.ReadLE16(istr);
        }
        new_exe_header_address = StreamReader.ReadLE32(istr);
    }
    @Override
    public void WriteToStream(FileOutputStream ostr) {
        StreamWriter.WriteLE16(ostr, magic_number);
        StreamWriter.WriteLE16(ostr, bytes_on_last_page_of_file);
        StreamWriter.WriteLE16(ostr, pages_in_file);
        StreamWriter.WriteLE16(ostr, relocations);
        StreamWriter.WriteLE16(ostr, size_of_header_in_paragraphs);
        StreamWriter.WriteLE16(ostr, minimum_extra_paragraphs_needed);
        StreamWriter.WriteLE16(ostr, maximum_extra_paragraphs_needed);
        StreamWriter.WriteLE16(ostr, initial_relative_ss);
        StreamWriter.WriteLE16(ostr, initial_sp);
        StreamWriter.WriteLE16(ostr, check_sum);
        StreamWriter.WriteLE16(ostr, initial_ip);
        StreamWriter.WriteLE16(ostr, initial_relative_cs);
        StreamWriter.WriteLE16(ostr, relocation_table_address);
        StreamWriter.WriteLE16(ostr, overlay_number);
        for(int i = 0; i < 4; i++){
            StreamWriter.WriteLE16(ostr, reserved_words[i]);
        }
        StreamWriter.WriteLE16(ostr, oem_identifier);
        StreamWriter.WriteLE16(ostr, oem_info);
        for(int i = 0; i < 10; i++){
            StreamWriter.WriteLE16(ostr, reserved_words_2[i]);
        }
        StreamWriter.WriteLE32(ostr, new_exe_header_address);
    }
    @Override
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting) {
        IJSONWritable.WriteU16(ostr, "Magic Number", magic_number, formatting, false);
        IJSONWritable.WriteU16(ostr, "Bytes On Last Page Of File", bytes_on_last_page_of_file, formatting, false);
        IJSONWritable.WriteU16(ostr, "Pages In File", pages_in_file, formatting, false);
        IJSONWritable.WriteU16(ostr, "Relocations", relocations, formatting, false);
        IJSONWritable.WriteU16(ostr, "Size Of Header In Paragraphs", size_of_header_in_paragraphs, formatting, false);
        IJSONWritable.WriteU16(ostr, "Minimum Extra Paragraphs Needed", minimum_extra_paragraphs_needed, formatting, false);
        IJSONWritable.WriteU16(ostr, "Maximum Extra Paragraphs Needed ", maximum_extra_paragraphs_needed, formatting, false);
        IJSONWritable.WriteU16(ostr, "Initial Relative SS", initial_relative_ss, formatting, false);
        IJSONWritable.WriteU16(ostr, "Initial SP", initial_sp, formatting, false);
        IJSONWritable.WriteU16(ostr, "Checksum", check_sum, formatting, false);
        IJSONWritable.WriteU16(ostr, "Initial IP", initial_ip, formatting, false);
        IJSONWritable.WriteU16(ostr, "Initial Relative CS", initial_relative_cs, formatting, false);
        IJSONWritable.WriteU16(ostr, "File Address Of Relocation Table", relocation_table_address, formatting, false);
        IJSONWritable.WriteU16(ostr, "Overlay Number", overlay_number, formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words [0]", reserved_words[0], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words [1]", reserved_words[1], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words [2]", reserved_words[2], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words [3]", reserved_words[3], formatting, false);
        IJSONWritable.WriteU16(ostr, "OEM Identifier", oem_identifier, formatting, false);
        IJSONWritable.WriteU16(ostr, "OEM Information", oem_info, formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [0]", reserved_words_2[0], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [1]", reserved_words_2[1], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [2]", reserved_words_2[2], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [3]", reserved_words_2[3], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [4]", reserved_words_2[4], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [5]", reserved_words_2[5], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [6]", reserved_words_2[6], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [7]", reserved_words_2[7], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [8]", reserved_words_2[8], formatting, false);
        IJSONWritable.WriteU16(ostr, "Reserved Words 2 [9]", reserved_words_2[9], formatting, false);
        IJSONWritable.WriteU64(ostr, "File Address Of New Exe Header", new_exe_header_address, formatting, true);
    }
}
