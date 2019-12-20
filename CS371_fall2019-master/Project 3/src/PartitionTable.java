import java.util.Comparator;

public class PartitionTable {
	Partition [] partitions = new Partition[] (int index, key, int value);
	int numPartitions;
	KV keyValue;

	public void addToPartitionAt(int index, Object key, int value){

		KV keyvalue = new KV(key, value);
		partitions[index].add(keyvalue);

	}
	 void sortPartitionAt(int index){



	}
	public void isSortedPartitionAt(){

	}


}
