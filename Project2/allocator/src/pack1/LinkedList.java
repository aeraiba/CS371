package pack1;

class LinkedList {

    Block head;
    Block temp;
    Block sorted;
    Block next;

    int newSize;

    public LinkedList (){

    }

    public void add(int startAddress, int size){
        Block newBlock = new Block(startAddress, size);
        newBlock.next = head;
        head = newBlock;
    }

    public Block remove(int startAddress){
        Block cursor= head;
        if (cursor != null){
            while (cursor.next != null && cursor.next.startAddress != startAddress){
                cursor = cursor.next;
            }
            if (cursor.next != null){      // found it
                Block to_remove = cursor.next;
                cursor.next = cursor.next.next;
                to_remove.next = null;
                return to_remove;
            } else {        // didnt find it
                return new Block(-1, -1);
            }
        }
        else
            return new Block(-1, - 1);
    }

    public Block search(int startAddress){
        Block current = head;
        while (current != null){
            if (current.startAddress == startAddress)
                return current;
            current = current.next;
        }
        return current;
    }

    private void mayMerge(Block one, Block two) {
        if(one.startAddress + one.size == two.startAddress) {
            one.size = one.size + two.size;
            two = null;
            two.next = null;
            add(one.startAddress, one.size);
        }
    }

    public void sortedInsert(Block newBlock) {
        if (sorted == null){
            sorted = newBlock;
        } else {
            Block cursor = sorted;
            if (cursor.startAddress < newBlock.startAddress){
                newBlock.next = sorted;
                sorted = newBlock;
                return;
            }
            while (cursor.next != null && cursor.next.startAddress >= newBlock.startAddress){
                cursor = cursor.next;
            }
            newBlock.next =  cursor.next;
            cursor.next = newBlock;
        }
    }


}
