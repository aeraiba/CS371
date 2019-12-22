import java.util.*;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Partition <KV> {

    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    private final int size;
    private final LinkedList<KV> list;
    Object key;
    int value;
    KV next;
    KV head;

    public Partition(LinkedList <KV> list, int size) {
        this.list = list;
        this.size = size;

    }

    public Partition<KV> createPar(Object list, int size) {
        if (list == null)
            throw new NullPointerException( "File must not be null");
        if(!(size >0)){
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        return new Partition((LinkedList) list, size);
    }

    public void sort(LinkedList list){
        List copy = new ArrayList(list);
        Collections.sort(list);

    }
    public boolean is_sorted(LinkedList List){

        List copy = new ArrayList(list);
        Collections.sort(copy);
        return copy.equals(list);

    }
    public KV getNext(){

        if(list.iterator().hasNext());
            return list.iterator().next();

    }


}
