import javax.swing.*;
import java.util.*;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Partition <KV> {

    static Lock lock;
    static Condition full;
    static Condition empty;

    private final int size;
    private final LinkedList<KV> list;
    Object key;
    int value;
    KV next;
    KV head;

    public Partition(LinkedList <KV> list, int size) {
        lock.lock();
        this.list = list;
        this.size = size;
        lock.unlock();
    }

    public Partition<KV> createPar(Object list, int size) {
        lock.lock();
        if (list == null)
            throw new NullPointerException( "File must not be null");
        if(!(size >0)){
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        lock.unlock();
        return new Partition((LinkedList) list, size);
    }

    public void sort(LinkedList list){
        lock.unlock();
        List copy = new ArrayList(list);
        Collections.sort(list);
        lock.unlock();

    }
    public boolean is_sorted(LinkedList List){

        lock.lock();
        List copy = new ArrayList(list);
        Collections.sort(copy);

        lock.unlock();
        return copy.equals(list);

    }
    public KV getNext(){
        lock.lock();
        if(list.iterator().hasNext());
        lock.unlock();
            return list.iterator().next();

    }


}
