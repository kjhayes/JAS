package PEFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import StreamIO.IStreamReadable;
import StreamIO.IJSONWritable;
import StreamIO.IStreamWritable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class SectionHeaderTable implements IStreamReadable, IStreamWritable, IJSONWritable {
 
    public class SectionHeader implements IStreamReadable, IStreamWritable, IJSONWritable {
        
        byte[] name = new byte[8];
        int virtual_size;
        int virtual_address;
        int size_of_raw_data;
        int pointer_to_raw_data;
        int pointer_to_relocations;
        int pointer_to_line_numbers;
        short number_of_relocations;
        short number_of_line_numbers;
        int characteristics;

        @Override 
        public void ReadFromStream(FileInputStream istr){
            for(int i = 0; i < 8; i++){
                name[i] = StreamReader.Read8(istr);
            }
            virtual_size = StreamReader.ReadLE32(istr);
            virtual_address = StreamReader.ReadLE32(istr);
            size_of_raw_data = StreamReader.ReadLE32(istr);
            pointer_to_raw_data = StreamReader.ReadLE32(istr);
            pointer_to_relocations = StreamReader.ReadLE32(istr);
            pointer_to_line_numbers = StreamReader.ReadLE32(istr);
            number_of_relocations = StreamReader.ReadLE16(istr);
            number_of_line_numbers = StreamReader.ReadLE16(istr);
            characteristics = StreamReader.ReadLE32(istr);
        }
        @Override
        public void WriteToStream(FileOutputStream ostr){
            for(int i = 0; i < 8; i++){
                StreamWriter.Write8(ostr, name[i]);
            }
            StreamWriter.WriteLE32(ostr, virtual_size);
            StreamWriter.WriteLE32(ostr, virtual_address);
            StreamWriter.WriteLE32(ostr, size_of_raw_data);
            StreamWriter.WriteLE32(ostr, pointer_to_raw_data);
            StreamWriter.WriteLE32(ostr, pointer_to_relocations);
            StreamWriter.WriteLE32(ostr, pointer_to_line_numbers);
            StreamWriter.WriteLE16(ostr, number_of_relocations);
            StreamWriter.WriteLE16(ostr, number_of_line_numbers);
            StreamWriter.WriteLE32(ostr, characteristics);
        }
        public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
            String charsetString = "UTF-8";
            try{
                short s_length = 0;
                for(int i = 0; i < 8; i++){
                    if(name[i] != 0x00){
                        s_length++;
                    }
                }
                String name_str = new String(name, charsetString);
                
                IJSONWritable.WriteString(ostr, "Section Name", name_str.substring(0, s_length), formatting, false);
                IJSONWritable.WriteHex32(ostr, "Virtual Size", virtual_size, formatting, false);
                IJSONWritable.WriteHex32(ostr, "Virtual Address", virtual_address, formatting, false);
                IJSONWritable.WriteHex32(ostr, "Size Of Raw Data", size_of_raw_data, formatting, false);
                IJSONWritable.WriteHex32(ostr, "Pointer To Raw Data", pointer_to_raw_data, formatting, false);
                IJSONWritable.WriteHex32(ostr, "Pointer To Relocations", pointer_to_relocations, formatting, false);
                IJSONWritable.WriteU16(ostr, "Number Of Relocations", number_of_relocations, formatting, false);
                IJSONWritable.WriteU16(ostr, "Number Of Line Numbers", number_of_line_numbers, formatting, false);
                IJSONWritable.WriteHex32(ostr, "Characteristics", characteristics, formatting, true);

            }catch(Exception e){
                System.err.println(e);
            }
        }
    }    

    int short_number_of_sections;
    SectionHeader[] section_headers;

    public SectionHeaderTable(short n_of_sec){
        short_number_of_sections = n_of_sec&(0xFFFF);
        section_headers = new SectionHeader[short_number_of_sections];
    }

    @Override 
    public void ReadFromStream(FileInputStream istr){
        for(int i = 0; i < short_number_of_sections; i++){
            section_headers[i] = new SectionHeader();
            section_headers[i].ReadFromStream(istr);
        }
    }
    @Override
    public void WriteToStream(FileOutputStream ostr){
        for(int i = 0; i < short_number_of_sections; i++){
            section_headers[i].WriteToStream(ostr);
        }
    }
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
        IJSONWritable.BeginWriteArray(ostr, "Section Headers", formatting);
        for(int i = 0; i < short_number_of_sections; i++){
            IJSONWritable.WriteArrayObject(ostr, section_headers[i], formatting, (i == (short_number_of_sections - 1)));
        }
        IJSONWritable.EndWriteArray(ostr, formatting, true);
    }
}
