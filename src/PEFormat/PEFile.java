package PEFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import StreamIO.IJSONWritable;
import StreamIO.IStreamWritable;
import StreamIO.IStreamReadable;
import StreamIO.StreamReader;
import StreamIO.StreamWriter;

public class PEFile implements IStreamReadable, IStreamWritable, IJSONWritable {
//Constants
    final static int expected_pe_signature = 0x00004550;

//Variables
    
    DOSHeader dosHeader = new DOSHeader();
    byte[] dosStubProgram;
    int pe_signature;
    COFFHeader coffHeader = new COFFHeader();
    OptionalHeader optionalHeader = new OptionalHeader();
    SectionHeaderTable sectionHeaderTable;

    //Methods//
    @Override
    public void ReadFromStream(FileInputStream istr){
        dosHeader.ReadFromStream(istr);

        dosStubProgram = new byte[dosHeader.new_exe_header_address - 0x40];
        for(int i = 0; i < dosStubProgram.length; i++){
            dosStubProgram[i] = StreamReader.Read8(istr);
        }

        pe_signature = StreamReader.ReadLE32(istr);
        coffHeader.ReadFromStream(istr);
        optionalHeader.ReadFromStream(istr);

        sectionHeaderTable = new SectionHeaderTable(coffHeader.number_of_sections);
        sectionHeaderTable.ReadFromStream(istr);
    }
    @Override 
    public void WriteToStream(FileOutputStream ostr){
        dosHeader.WriteToStream(ostr);
        for(int i = 0; i < dosStubProgram.length; i++){
            StreamWriter.Write8(ostr, dosStubProgram[i]);
        }
        StreamWriter.WriteLE32(ostr, pe_signature);
        coffHeader.WriteToStream(ostr);
        optionalHeader.WriteToStream(ostr);
        sectionHeaderTable.WriteToStream(ostr);
    }
    @Override
    public void WriteJSON(FileWriter ostr, IJSONWritable.Formatting formatting){
        IJSONWritable.WriteObject(ostr, "DOS Header", dosHeader, formatting, false);
        IJSONWritable.WriteU64(ostr, "PE Signature", pe_signature, formatting, false);
        IJSONWritable.WriteObject(ostr, "COFF Header", coffHeader, formatting, false);
        IJSONWritable.WriteObject(ostr, "Optional Header", optionalHeader, formatting, false);
        IJSONWritable.WriteObject(ostr, "Section Header Table", sectionHeaderTable, formatting, true);
    }
    //
}
