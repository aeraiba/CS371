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
	


}
