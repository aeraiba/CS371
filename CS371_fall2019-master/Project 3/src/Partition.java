import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Partition<KV> extends LinkedList{


    private final int size;
    private final LinkedList<KV> list;
    int index;
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

    }
	

