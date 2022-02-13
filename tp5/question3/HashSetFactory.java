package question3;

import java.util.Set;
import java.util.HashSet;

public class HashSetFactory<Integer> implements Factory<Set>{
public Set create(){
    return new HashSet<Integer>();
}
}