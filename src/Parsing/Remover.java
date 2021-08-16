package Parsing;

public class Remover extends Pass {
    public Remover(Section[] ignore, int[] blacklist){
        super(new RemoverModifier(blacklist), ignore);
    }
}
