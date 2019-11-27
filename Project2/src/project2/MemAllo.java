import java.util.LinkedList;
import java.io.*;

/**
 *
 * @author tchan
 */


class MemAllo extends MemoryAllocation{
 
    int size;
    int startAddress;
    Node head;
    static public Block firstBlock = null;
    static public Block lastBlock = null;
    //init the list of freeblocks
    LinkedList <> freeBlock = new LinkedList <>(); 
    //init the list of reserved blocks
    LinkedList <> reservedBlock = new LinkedList <>();
        
    
    static public class Block{ //CONSTRUCTOR
        int startAddress; //start adress of block
        int size; //size of block
        Block next; // init next Block
        
        public Block (int startAddress, int size) //initiate block class to input starter address, and size of block inputted
        { 
            this.startAddress = startAddress;
            this.size = size;
        }
        public void display(){
            System.out.println("Block starting Address" + startAddress + "Block Size" + size);
        }
       
    }
     public int returnSize(){
            return this.size;
        
        }
    public int returnStrtAdd(){
        return this.startAddress;        
         
     }
    
    public MemAllo(int mem_size){
        super(mem_size);
        int blockSize;
        int availSize;
        
        if (mem_size > 0)
            availSize = mem_size - 1;
            
       // if size = 1000, then available mem is 1-999, 0 is reserved for failed case
      // init state: one node in freeBlocks:  {.startAddr=1, size=size-1} 
        freeBlock.add(new Block(1, size = size-1));
        freeBlock.add(new Block (3,2));
        freeBlock.add(new Block(5,2));
        

    }
            
    
        public int alloc(int requested_size) {
     // find the first available block with size >= requested_size in freeblocks
         Block blockToFind =  null;
         int reqSize = requested_size;
         
         
       
              if (freeBlock == null){ //is firstblock is nulls, there are noblocks
                 return 0;
             }
             else { // current block, including firsblock is not null
                 while(freeBlock != null){
                     freeBlock.search(this.startAddress, this.size);
                
                     if (freeBlock.size >= reqSize){ //if block is greater or equal roo
                     size =  freeBlock.size - size;
                     startAddress = freeBlock.startAddress + size;
                     freeBlock.remove();
                     reservedBlock.add(new Block(startAddress, size));
                     break;
                 }
                     else
                        freeBlock = freeBlock.next;

             }
            } 
           return startAddress;
         }
        public void free(int addr){
      
      while(reservedBlock.search(reservedBlock.startAddress, addr)){
          if (startAddress == addr){
              freeBlock.add(new Block(addr, size));
              reservedBlock.remove();
             
          }
          else{
              System.out.println("Not legit address");
          }
          
      }
            
    }

    
  public int size(){  
   int sum = 0;
   for (Block eachBlock : freeBlock){
       freeBlock.search(freeBlock.startAddress, freeBlock.size);
     sum = sum + freeBlock.size;
   } 
   return sum;
  }
  
    public int max_size(){
        int maxSize = 0;
        int currentSize;
        int nextSize = 0;
        
        currentSize = freeBlock.size;
        
        for (Block eachBlock : freeBlock){
            nextSize = eachBlock.size;
            if(nextSize > currentSize){
                currentSize = nextSize;
            } 
        }
        maxSize = nextSize;
        return maxSize;
    }         
    
  private void mayMerge(Node one, Node two){
     
}

class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }
class LinkedList{
    
    Node head;
    
    public boolean search(Node head, int x){
        Node current = head;
        while (current != null){
            if (current.data == x)
                return true;
            current = current.next;
        }
        return false;
    } 

}   
    
        
  
    
    
 