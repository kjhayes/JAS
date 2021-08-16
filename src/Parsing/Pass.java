package Parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Pass {
    
    public interface Modifier {
        public void Pass(int read_char, FileOutputStream ostr);
    }

    private Modifier modifier;

    private Section[] unaffected_sections;

    public Pass(Modifier mod, Section[] unaffected_sections_){
        unaffected_sections = unaffected_sections_;
        modifier = mod;
    }

    public File TempPassedFile(File src){
        try{
            ArrayList<Section> active_sections = new ArrayList<Section>();
            File temp = File.createTempFile("PASS_FILE", null);
            FileOutputStream t_ostr = new FileOutputStream(temp);
            FileInputStream s_istr = new FileInputStream(src);

            int read = 0;
            while(read != -1){
                read = s_istr.read();
                
                if(active_sections.size() == 0){
                    modifier.Pass(read, t_ostr);
                }
            
                for(int i = 0; i < unaffected_sections.length; i++){
                    if((char)read == unaffected_sections[i].marker_begin){
                        active_sections.add(unaffected_sections[i]);
                    }
                }
                for(int i = 0; i < active_sections.size(); i++){
                    if((char)read == active_sections.get(i).marker_end){
                        active_sections.remove(active_sections.get(i));
                    }
                }
            }
            t_ostr.close();
            s_istr.close();

            return temp;
        }catch(Exception e){
            System.err.println(e);
        }
        return null;
    }
}
