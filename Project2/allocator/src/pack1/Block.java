package pack1;

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
        System.out.println("Block starting Address " + startAddress + "Block Size " + size);
    }
}
