package pack1;

import java.util.LinkedList;
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
    
    public class Block{ //CONSTRUCTOR
        int startAddress; //start adress of block
        int size; //size of block
        Block next; // init next Block
        int data;
        
        public Block (int startAddress, int size) //initiate block class to input starter address, and size of block inputted
        { 
            this.startAddress = startAddress;
            this.size = size;
        }
        public void display(){
            System.out.println("Block starting Address" + startAddress + "Block Size" + size);
        }
 
        public boolean search(Block head, int x){
        Block current = head;
        while (current != null){
            if (current.data == x)
                return true;
            current = current.next;
        }
        return false;
    }

    }

            
    
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
                       reservedBlocks.add(head.startAddress, head.size);
                         strAdd = head.startAddress + requested_size;
                         blkSize = head.size - requested_size;
                         freeBlocks.add(strAdd, blkSize);
                         return head.startAddress;
                     }
                     if(head.size == requested_size){
                         reservedBlocks.add(head.startAddress, head.size);
                         strAdd = head.startAddress + requested_size;
                         blkSize = head.size - requested_size;
                         head = null;
                         freeBlocks.add(strAdd, blkSize);
                         return head.startAddress;
                                 
                     }
                     else
                        head = head.next;
             }
            } 
         }
        public void free(int addr){
            
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
        int maxSize = 0;
        Block head = freeBlocks.head;
        Block newBlock = null;
        if (head.next != null) {
            while (head.next != null) {
                newBlock = head;
                head = head.next;

                if (head.size > newBlock.size) {
                    maxSize = head.size;
                }
            }
        }
        else
            return 0;
   return maxSize;
    }         
   
  

class LinkedList {
    
    Block head;
    Block temp;
    
    int newSize;
    
     public LinkedList (){
         
     }
    public void add(int startAddress, int size){
       Block newBlock = new Block(startAddress, size);
       newBlock.next = head;
       head = newBlock;
    }
    

    
    //public void remove(){  }  
}
}
  
    
    
 