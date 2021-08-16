package ASM;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import Parsing.Remover;
import Parsing.Section;

import x86.Assembler;

public class Intel implements Assembler {
    static Section string_quotes = new Section(0x22);
    static Section[] whitespace_ignore = {string_quotes};
    static int[] whitespace_blacklist = {0x20, 0x9, 0x10, 0x13};
    static Remover whitespace = new Remover(whitespace_ignore, whitespace_blacklist);
    @Override
    public byte[] Assemble(File src){
        byte[] bytes = null;
        File temp = whitespace.TempPassedFile(src);
        try{
            FileOutputStream o = new FileOutputStream(new File("TEMPFILE"));
            
            Files.copy(Path.of(temp.getPath()), o);

        }catch(Exception e){
            System.err.println(e);
        }
        return bytes;
    }
}
