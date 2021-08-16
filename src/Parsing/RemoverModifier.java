package Parsing;

import java.io.FileOutputStream;

public class RemoverModifier implements Pass.Modifier {
    int[] blacklist;
    public RemoverModifier(int[] blist){
        blacklist = blist;
    }
    @Override
    public void Pass(int read, FileOutputStream ostr){
        for(int i = 0; i < blacklist.length; i++){
            if(read == blacklist[i]){
                return;
            }
        }
        try{
        ostr.write(read);
        }catch(Exception e){
            System.err.println(e);
        }
    }

}
