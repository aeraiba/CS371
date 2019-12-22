import java.util.Comparator;

public class PartitionTable {
	Partition [] partitions = new Partition[];
	int numPartitions;
	KV keyValue;
	KV head;

	public void addToPartitionAt(int index, Object key, int value){

		KV keyvalue = new KV(key, value);
		partitions[index].add(key, value);

	}

	public void sortPartitionAt(int index){




	}
	
public void isSortedPartitionAt(int index){
		if (head == null)
			return true;

		// Traverse the list till last node and return false if a node is smaller than or equal its next.
		for (KV t = head; t.next != null; t = t.next)
			if (t.key.compareTo(t.next.key);
				return false;

		return true;


	}

}
