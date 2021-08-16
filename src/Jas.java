import java.io.File;

import ASM.Intel;

public class Jas {
    public static void main(String[] args){
        try{
            
            File src = new File(args[0]);
            Intel intel = new Intel();

            intel.Assemble(src);

        }catch(Exception e){System.err.println(e);}
    }    
}
