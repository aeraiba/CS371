public abstract class Memory {
	/*
	 * The constructor creates memory with specified size mem_size and prepare swap file with defined file name swap_name.
	 */
	public Memory(int mem_size, String swap_name) {
	}

	/*
	 * writes specified value into memory at specified position - addr
	 */
	abstract public void write(int addr, byte value);

	/*
	 * returns value that was stored at address addr
	 */
	abstract public byte read(int addr);
}