package pack1;
import java.io.*;

/**
 *
 * @author tchan
 */

public class MemAllo extends MemoryAllocation{
 
    
    //init the list of freeblocks
    LinkedList freeBlocks = new LinkedList (); 
    //init the list of reserved blocks
    LinkedList reservedBlocks = new LinkedList ();
    int availSize;

    
        public MemAllo(int mem_size){
            super(mem_size);
       
            availSize = mem_size - 1;
  
            // init state: one node in freeBlocks:  {.startAddr=1, size=size-1}
            freeBlocks.add(1, availSize);
        }

        public int alloc(int requested_size) {
        // find the first available block with size >= requested_size in freeblocks
        Block head = freeBlocks.head;
        int resAdd;
        int strAdd;
        int blkSize;
            
        
            if (head == null){ //is firstblock is nulls,
                 return 0;
            }
            else { // current block, including firsblock is not null
                while(true){
              
                    if(head.size > requested_size){
                        reservedBlocks.add(head.startAddress, requested_size);
                        head.size = head.size - requested_size;
                        head.startAddress = head.startAddress + requested_size;
                        freeBlocks.printList();
                        return head.startAddress;
                    }
                    if(head.size == requested_size){
                        reservedBlocks.add(head.startAddress, head.size);
                        freeBlocks.remove(head.startAddress);
                        freeBlocks.printList();
                        return head.startAddress;
                    }
                    else {
                        head = head.next;
                    }
                }
            }
        }
        public void free(int addr){
            Block removed = reservedBlocks.remove(addr);
            freeBlocks.add(addr, removed.size);
            freeBlocks.printList();
        }
    
        public int size(){
            int sum = 0;
            Block head = freeBlocks.head;
            while( head.next != null){
                head = head.next;
                sum = sum + head.size;
            }
            return sum;
        }
  
        public int max_size(){
            Block head = freeBlocks.head;
            if (head != null) {
                int maxSize = freeBlocks.head.size;
                while (head.next != null) {
                    head = head.next;
                    if (head.size > maxSize) {
                        maxSize = head.size;
                    }
                }
                return maxSize;
            } else {
                return 0;
        }
    }
}



  
    
    
 