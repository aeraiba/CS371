import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Partition {
//partitition consturctor
    class Partition(Object list, int n){

        //get the size of the list
        int size = key.size();

        //calculate the number of partions m of size n each
        int m = size / n;
        if (size % n != 0)
            m++;

        //create m empty list
        LinkedList <> [] partition = new LinkedList[m];
        for (int i = 0; 1 < m; i++) {  // creates m linked list
            partition [i] = new LinkedList();
            for (int i = 0; i < size; i++){ // inserts items in list into partition
                int index = i/n;
                partition[index].add(list.get(i));
            }
        }


        return partition;
    }
	
}
