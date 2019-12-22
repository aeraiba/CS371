import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;

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

		partitions[index].add();

	}

	public void sortPartitionAt(int index){
		Partition <KV> curPar;
		curPar = partitions[index];
		List copy = new ArrayList((Collection) curPar);
		Collections.sort(copy);

	}
	public boolean isSortedPartitionAt(int index){

		Partition <KV> curPar;
		curPar = partitions[index];
		List copy = new ArrayList((Collection) curPar);
		Collections.sort(copy);

		return copy.equals(curPar);

	}


}
