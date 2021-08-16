import java.io.*;

import PEFormat.PEFile;
import StreamIO.IJSONWritable;

class JasRead {
    public static void main(String[] args){
        PEFile pe_file = new PEFile();

//Source File
        String src_file_dir = args[0];
        File src_file = new File(src_file_dir);
        FileInputStream src_file_istr = null;
        try{
            src_file_istr = new FileInputStream(src_file);
        
            //Src Reading
            pe_file.ReadFromStream(src_file_istr);

        //Src Close
        }catch(Exception e){
                System.err.println(e);
        }finally{
            if(src_file_istr != null){
                try{
                    src_file_istr.close();
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        }
        

//Output
        String output_file_dir = "a.out";
        String json_file_dir = "a.json";
        if(args.length > 1){
            output_file_dir = args[1];
        }
        if(args.length > 2){
            json_file_dir = args[2];
        }
        File output_file = new File(output_file_dir);
        FileOutputStream output_file_ostr = null;
        File json_file = new File(json_file_dir);
        FileWriter json_file_ostr = null;
        try{
            output_file_ostr = new FileOutputStream(output_file);
            json_file_ostr = new FileWriter(json_file); 
            
            IJSONWritable.Formatting formatting = new IJSONWritable.Formatting();

            //Output Write
            
            pe_file.WriteToStream(output_file_ostr);
            
            json_file_ostr.write("{\n");
            pe_file.WriteJSON(json_file_ostr, formatting);
            json_file_ostr.write("\n}");
        
        //Output Close
        }catch(Exception e){
            System.err.println(e);
        }finally{
            if(output_file_ostr != null){
                try{
                    output_file_ostr.close();
                }catch(Exception e){
                    System.err.println(e);
                }
            }
            if(json_file_ostr != null){
                try{
                    json_file_ostr.close();
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }
}