import java.util.LinkedList;
import java.io.*;

/**
 *
 * @author tchan
 */
class MemAllo extends MemoryAllocation{
 
    int size;
    //init the list of freeblocks
    LinkedList <Block> freeBlock; 
    //init the list of returnblocks
    LinkedList <Block> returnBlock;
    
    
    static private class Block{ //CONSTRUCTOR
        int startAddress; //start adress of block
        int size; //size of block
        //initiate block class to input starter address, and size of block inputted
        public Block (int x, int y)
        { 
            x = startAddress;
            y = size;
        }
    
    } 
  public class LinkedList{
      Node head;
      static class Node{
          int data;
          Node next;
          Node (int n){
              data = n;
              next = null;
          }
      }
  }
    
    public MemAllo(int mem_size){
        super(mem_size);
        int blockSize;
        LinkedList <Block> freeBlock = new LinkedList <Block>();
        LinkedList <Block> returnBlock = new LinkedList <Block>();
        
        // init the list freeBlocks
        //init the list returnedBlocks
        while (size > 0)
            blockSize = size - 1;
            int block Address =  size + startAddress;
            
       // if size = 1000, then available mem is 1-999, 0 is reserved for failed case
      // init state: one node in freeBlocks:  {.startAddr=1, size=size-1} 
        freeBlock.add(new Block(1, size-1));
    }
            
    
        public int alloc(int requested_size) {
     // find the first available block with size >= requested_size.

         if (freeBlock > requested_size)
         //move this block from freeBlocks
          freeBlock.remove();
        //insert a new block into freeBlocks, with startAddr = removed_block.startAddr+size,  size= removed_block.size-size;
          freeblock.
     //before returning, add the removed_block to 
      //list returnedBlocks.
      //Return removed_block.startAddr; //@Anna, can we rename this to reserved_block or something?

    
    
    public void free(int addr){
         //use case: free(1);
      //question1: how do we know 1 is a legit address?
       //question2: how do we know the reserved block of 1 has size 10?
      //to answer the question 1&2, consult the returnedBlock list, find the size of the addr to be recycled.
      //we likely pay O(n) to do this query. Can we do better?
      //perhaps we can use a hashtable 

      // if returnedBlock list tells us the addr is legit.
      // do not forget to remove it from returnedBlock list
     
        // construct a block to free {.startAddr=addr, .size=size}
        //insert this block B to freeBlocks
        // suppose freeBlocks is sorted (either impl a sortedLinkedList or do list.sort(...))
        //if B is neither head nor tail then
              //mayMerge B with its successor
              //mayMerge B with its predecessor
              //can we do it the other way around? Merge it with predecessor then successor?
        //if B is head, only mayMerge with its successor
        //if B is tail, only mayMerge with its predecessor
      
}

    }
    public int size(){
        
    }
    public int max_size(){
        
    }
    
    
}
