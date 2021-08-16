package Parsing;

public class Section {
    int marker_begin;
    int marker_end;
    public Section(int marker){
        marker_begin = marker;
        marker_end = marker;
    }
    public Section(int begin, int end){
        marker_begin = begin;
        marker_end = end;
    }
}
