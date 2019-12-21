import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Partition {

        int index;
        Object key;
        int value;

        public Partition(int index, Object key, int value){
            this.index = index;
            this.key =key;
            this.value = value;
        }


    }
	

