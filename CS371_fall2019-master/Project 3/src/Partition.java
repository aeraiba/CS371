import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Partition<E> {


        int index;
        Object key;
        int value;
        KV next;
        KV head;
        public Partition(int index, Object key, int value) {
            LinkedList<KV> partitions = new LinkedList();
        }


        public void add(Object key, int value){
            KV keyvalue = new KV(key, value);
            keyvalue.next = head;
            head = keyvalue;
        }
        public Object getKey() {
            return this.key;
        }
        public int getValue(){
            return this.value;
        }


        public void sort(KV k1, KV k2){


        }


        public void is_sorted(){

        }
        public int compareTo(KV k1, KV k2) {
        return k1.this.key.compareTo(k2.getClass());
     }
}


    }
	

