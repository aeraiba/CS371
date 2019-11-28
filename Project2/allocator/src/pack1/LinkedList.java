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
        insertionSort();
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
                insertionSort();
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

    private boolean mayMerge(Block one, Block two) {
        if(one.startAddress + one.size == two.startAddress) {
            one.size = one.size + two.size;
            one.next = two.next;
            two.next = null;
            return true;
        }
        return false;
    }

    public void insertionSort() {
        sorted = null;
        Block current = head;
        while (current != null) {
            sortedInsert(current);
            current = current.next;
        }
        // after we sort it, we need to compare every adjacent block pair
        current = head;
        while(current.next != null){
            if(!mayMerge(current, current.next)) {
                current = current.next;
            }
        }
        // to test for merges with mayMerge. In this process, it is
        // important to remember that when you merge a pair of nodes, this
        // may change weather or not you have already tested cursor with cursor.next

        head = sorted;

    }
    public void sortedInsert(Block newBlock) {
        if (sorted == null){
            sorted = newBlock;
        } else {
            Block cursor = sorted;
            if (cursor.startAddress > newBlock.startAddress){
                newBlock.next = sorted;
                sorted = newBlock;
                return;
            }
            while (cursor.next != null && cursor.next.startAddress <= newBlock.startAddress){
                cursor = cursor.next;
            }
            newBlock.next =  cursor.next;
            cursor.next = newBlock;
        }
    }

    public void printList(){
        Block cursor = head;
        cursor.display();
        while (cursor.next != null){
            cursor = cursor.next;
            cursor.display();
        }
        System.out.println();
    }


}
