import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import pack1.MemoryAllocation;

public class TestMemoryAllocation {
	static final int SIZE = 10000;

	static final int TEST_SIZE_1 = 10;
	static final int TEST_SIZE_2 = 20;
        

	static boolean test1(MemoryAllocation m) {
		boolean result = true;
		int ptr[] = new int[SIZE];
		int p = 0;
		while (m.max_size() >= TEST_SIZE_1) {
			ptr[p] = m.alloc(TEST_SIZE_1);
			if (ptr[p] == 0) {
				result = false;
			}
			p++;
		}
		int max_p = p;
		if (max_p < 400) {
			result = false;
		}
		int l_limit = p / 3;
		int u_limit = 2 * p / 3;
		for (int i = l_limit; i < u_limit; i++) {
			m.free(ptr[i]);
			ptr[i] = 0;
		}
		// We know for sure by now that max_size() works if result is true
		if(m.max_size() != (u_limit-l_limit)*TEST_SIZE_1) {
			result = false;
		}
		p = l_limit;
		while (p < u_limit && m.max_size() >= TEST_SIZE_1) {
			ptr[p] = m.alloc(TEST_SIZE_1);
			if (ptr[p] == 0) {
				result = false;
			}
			p++;
		}
		for (int i = 0; i < max_p; i++) {
			if (ptr[i] > 0)
				m.free(ptr[i]);
			ptr[i] = 0;
		}
		if(m.size() != SIZE-1) {
			result = false;
		}
		if (result) {
			System.out.println("Test1: PASS " + max_p);
		} else {
			System.out.println("Test1: FAIL");
		}
		return result;
	}

	static boolean test2(MemoryAllocation m) {
		boolean result = true;
		int ptr[] = new int[SIZE];
		int p = 0;
		while (m.max_size() >= TEST_SIZE_1) {
			ptr[p] = m.alloc(TEST_SIZE_1);
			if (ptr[p] == 0) {
				result = false;
			}
			p++;
		}
		int max_p = p;
		for (int i = 0; i < max_p; i += 3) {
			m.free(ptr[i]);
			ptr[i] = 0;
		}
		p = 0;
		while (p < max_p && m.max_size() >= TEST_SIZE_1) {
			ptr[p] = m.alloc(TEST_SIZE_1);
			if (ptr[p] == 0) {
				result = false;
			}
			p += 3;
		}
		if (p < max_p / 2) {
			result = false;
		}
		for (int i = 0; i < max_p; i++) {
			if (ptr[i] > 0)
				m.free(ptr[i]);
			ptr[i] = 0;
		}
		if (result) {
			System.out.println("Test2: PASS " + max_p);
		} else {
			System.out.println("Test2: FAIL");
		}
		return result;
	}

	static boolean test3(MemoryAllocation m) {
		boolean result = true;
		int ptr[] = new int[SIZE];
		int p = 0;
		while (m.max_size() >= (2 * TEST_SIZE_2 + TEST_SIZE_1)) {
			if (m.max_size() >= TEST_SIZE_1) {
				ptr[p] = m.alloc(TEST_SIZE_1);
				if (ptr[p] == 0) {
					result = false;
				}
				p++;
			}
			if (m.max_size() >= TEST_SIZE_2) {
				ptr[p] = m.alloc(TEST_SIZE_2);
				if (ptr[p] == 0) {
					result = false;
				}
				p++;
			}
			if (m.max_size() >= TEST_SIZE_2) {
				ptr[p] = m.alloc(TEST_SIZE_2);
				if (ptr[p] == 0) {
					result = false;
				}
				p++;
			}
		}
		int max_p = p;
		if (max_p < 90) {
			result = false;
		}
		for (int i = 0; i < max_p - 2; i += 3) {
			m.free(ptr[i]);
			ptr[i] = 0;
			m.free(ptr[i + 1]);
			ptr[i + 1] = 0;
		}
		p = 0;
		while (m.max_size() >= TEST_SIZE_1) {
			if ((p >= max_p) || (ptr[p] == 0)) {
				ptr[p] = m.alloc(TEST_SIZE_1);
			}
			p++;
		}
		if (p < max_p-2) {
			result = false;
		}
		if (result) {
			System.out.println("Test3: PASS");
		} else {
			System.out.println("Test3: FAIL");
		}
		return result;
	}

	public static void main(String[] args) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class c;

		if (args.length > 0) {
			try {
				c = loader.loadClass(args[0]);
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot find class " + args[0] + " error:"
						+ e.toString());
				c = null;
			}
			if (c != null) {
				try {
					Constructor constr = c
							.getConstructor(new Class[] { int.class });
					MemoryAllocation m = (MemoryAllocation) constr
							.newInstance(new Object[] { SIZE });
					boolean result = test1(m);
					m = (MemoryAllocation) constr
							.newInstance(new Object[] { SIZE });
					result = test2(m) && result;
					m = (MemoryAllocation) constr
							.newInstance(new Object[] { SIZE });
					result = test3(m) && result;
					if (result) {
						System.out.println("Your class " + args[0] + " PASS all tests");
					} else {
						System.out.println("Class " + args[0] + " FAIL");
					}
				} catch (InstantiationException e) {
					System.out.println("Cannot create instance of class "
							+ args[0] + " error:" + e.toString());
				} catch (IllegalAccessException e) {
					System.out.println("Cannot use class " + args[0]
							+ " error:" + e.toString());
				} catch (NoSuchMethodException e) {
					System.out.println("Bad constructor of class " + args[0]
							+ " error:" + e.toString());
				} catch (InvocationTargetException e) {
					System.out.println("Error calling the constructor "
							+ args[0] + " error:" + e.toString());
				}
			}
		} else {
			System.out.println("Use TestMemoryAllocation <class_name>");
		}
	}

}