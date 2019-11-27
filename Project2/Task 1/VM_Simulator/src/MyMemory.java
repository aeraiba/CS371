import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MyMemory {
    byte [] data; // stores data, index is PA
    HashTable<Integer, PTE> pageTable; 
    String swap_file;
    File f;
    int memSize;
    int PAGE_SIZE = 64;
    int TABLE_SIZE = 1024*6; // num entries * (size of each entry + extra 4 bytes per entry)
    
    /* This class represents a PTE in the page table. */
    private static class PTE {
        int vpn;
        int pfn;
        
        /* Constructor */
        private PTE(int vpn, int pfn) {
            this.vpn = vpn;
            this.pfn = pfn;
        }
    }
    
    /* This class represents a node of chains. */
    class HashNode<K, V> {
        K key;
        V value;
        
        /* Reference to the next node. */
        HashNode<K, V> next;
        
        /* Constructor */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    /* This class represents the hashed inverted page table and its methods. */
    class HashTable<K, V> {
        /* Array of chains. */
        private ArrayList<HashNode<K, V>> chains;
        
        /* Capacity of array list. */
        private final int numChains;
        
        /* Size of array list. */
        private int size;
        
        /* Constructor */
        public HashTable() {
            chains = new ArrayList<>();
            numChains = 1024;
            size = 0;
            
            for(int i = 0; i < numChains; i++) {
                chains.add(null);
            }
        }
        
        /* Hash function to obtain an entry in the hash table by hashing the vpn. */
        public int getChainIndex (K key) {
            int hashCode = key.hashCode();
            int index = hashCode%TABLE_SIZE;
            return index;
        }
        
        /* Returns value for a key. */
        public V get(int va, K key) throws FileNotFoundException, IOException {
            
            /* Find the head of chain for a given key. */
            int chainIndex = getChainIndex(key);
            HashNode<K, V> head = chains.get(chainIndex);
            
            /* Search key in chain. */
            while (head != null) {
                if (head.key.equals(key)) {
                    return head.value;
                }
                
                head = head.next;
            }
           
            /* If all entries in the chain are checked without a match, this
            means a miss or a page fault. */
            pageFaultHandler(va);
            return null;
        }
        
        /* Adds a key value pair to the page table. */
        public void add(K key, V value) {
            
            /* Find the head of chain for a given key. */
            int chainIndex = getChainIndex(key);
            HashNode<K, V> head = chains.get(chainIndex);
            
            /* Check if the key is already present. */
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                
                head = head.next;
            }
            
            /* Insert key into the chain. */
            size++;
            head = chains.get(chainIndex);
            HashNode<K, V> newNode = new HashNode<>(key, value);
            newNode.next = head;
            chains.set(chainIndex, newNode);
        }
    }
    
    /* Constructor */
    public MyMemory (int size) {
        memSize = size;
        data = new byte[memSize];
        
        /* Initialize pageTable. */
        pageTable = new HashTable<>();
        
        /* Setup swap file. Perhaps create the swap file and write 65536 all-zero bytes. */
        swap_file = "swap_file.txt";
        f = new File(swap_file);
    }
    
    /* How to implement addrTranslate? */
    public int addrTranslate(int va) throws Exception {
        int vpn = getVPN(va);
        int offset = getOffset(va);
        int pfn;
        PTE value;
        
        /* Check your pageTable, if vpn is in?
           Find the PTE the vpn maps with, find the pfn the vpn maps to, 
           and construct PA to return. Else, raise NotMappedException.
        */
        try {
            value = pageTable.get(va, vpn);
            int paddr = constructPA(value.pfn, offset);
            return paddr;
        } catch (Exception e) {
            throw new Exception("Raised NotMappedException. ");
        }
    }
    
    /* Helper function to extract vpn from a virtual address. */
    public int getVPN(int va) {
        int vpn = va/PAGE_SIZE;
        return vpn;
    }
    
    /* Helper function to extract offset from a virtual address. */
    public int getOffset(int va) {
        int offset = va%PAGE_SIZE;
        return offset;
    }
    
    /* Helper function to construct physical address based on pfn and offset. */
    public int constructPA(int pfn, int offset) {
        int pa = (pfn*PAGE_SIZE)+offset;
        return pa;
    }
    
    /* How to implement pageFaultHandler? pageFaultHandler is only invoked when a 
       NotMappedException is thrown out. Here, addr is the virtual address. */
    public void pageFaultHandler(int addr) throws FileNotFoundException, IOException {
        byte [] buffer = new byte[64]; // buffer to hold a page worth of data from swap file
        int vpn = getVPN(addr);
        int offset = getOffset(addr);
        int pfn = 0;
        
        FileChannel channel = new RandomAccessFile(f, "r").getChannel();
        ByteBuffer rbuf = channel.map(FileChannel.MapMode.READ_ONLY, offset, PAGE_SIZE);
        rbuf.get(buffer); // this loads 64 bytes from file into buffer
        
        /* Map vpn to a pfn, we need to create a new PTE(vpn, pfn) and add it to the pageTable. 
           What if no pfn is available? What if all slots in data array are mapped? Eviction is
           needed. How? */
        PTE pte = new PTE(vpn,pfn);
        pageTable.add(vpn, pte);
        
        /* Load this page in. */
        for(int i = 0; i < PAGE_SIZE; i++) {
            /* Copy data from buffer to data.
               Corner case: the last page may not contain 64 bytes, such as 10,000%64. */
            data[(pfn*PAGE_SIZE)+i] = buffer[i];   
        }
    }
    
    /* Writes specified value into memory at specified position (address). The simplest way 
       to implement it is to write-through: write into the memory and write back to the swap file. 
       It must solve the case when the requested page is not in memory. */
    public void write(int addr, byte value) throws Exception {
        int paddr;
        try {
            paddr = addrTranslate(addr);
        } catch (Exception e) {
            pageFaultHandler(addr);
            paddr = addrTranslate(addr);
        }
        
        data[paddr] = value;
        
        /* Write back to the swap file using buffer. */
    }
    
    /* Returns value that was stored at address addr, which is a virtual address. Similar to the write
       function, it must solve the situation if the page is not in the memory. The simplest way to
       implement it is to load the page from the swap file into memory first. */
    public byte read(int addr) throws Exception {
        int paddr;
        try {
            paddr = addrTranslate(addr);
        } catch (Exception e) {
            pageFaultHandler(addr);
            paddr = addrTranslate(addr);
        }
        
        byte val = data[paddr];
        return val;
    }
}
