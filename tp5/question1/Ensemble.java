package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    public boolean add(T t) {
        // à compléter pour la question1-
        if(table.contains(t)) {return false;}
        table.add(t);
        return true;
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
        for(int i=0;i<e.table.size();i++){
            this.add(e.table.get(i));
        }
        return this;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
      
        this. table.retainAll(e.table);
        return this;
    }

    public Ensemble<T> diff(Ensemble<T> e) {
        // à compléter pour la question1-2
        
        this.table.removeAll(e.table);
        return this;
    }

    Ensemble<T> diffSym(Ensemble<T> e) {

        Ensemble<T> a = new Ensemble<T>();
        Ensemble<T> b = new Ensemble<T>();
        
        a.table.addAll(this.table);
        b.table.addAll(e.table);

          Ensemble<T> inters = a.inter(b);
          
          Ensemble<T> union = this.union(e);
          
    
        
        return union.diff(inters);
    }
   
}