import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Collections;
import java.util.Comparator;

public class PartitionTable extends LinkedList{
	Partition <KV> [] partitions;
	int numPartitions;
	KV keyValue;
	KV head;
	KV next;

	public PartitionTable() {
		partitions = new Partition[numPartitions];

	}

	public void addToPartitionAt(int index, Object key, int value){

		partitions[index].add(key, value);

	}

	public void sortPartitionAt(int index){
		LinkedList curPar = partitions[index];
		Collections.sort(curPar);

		}

	}
	public boolean isSortedPartitionAt(int index){

		if (head == null)
			return true;

		// Traverse the list till last node and return false if a node is smaller than or equal its next.
		for (KV t = head; t.next != null; t = t.next)
			if (partitions[index].key.compareTo(partitions[index].key.next));
				return false;

		return true;


	}


}
