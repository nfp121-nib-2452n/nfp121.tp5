package question3;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

public class TreeSetFactory<Integer> implements Factory<Set>{
    public Set create(){
        return new TreeSet<Integer>();
    }
}