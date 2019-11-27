import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestMemory {

	static final int SIZE = 10000;
	
	static final int TEST_SIZE = 65536;

	static final String SWAP = "swap.tmp";

	static byte fce(int adr) {
		return (byte) ((adr * 5 + 6) % 256 - 128);
	}

	static byte fce2(int adr) {
		return (byte) ((adr * 7 + 5) % 256 - 128);
	}

	static boolean test1(Memory m) {
		boolean result = true;
		for (int i = 0; i < TEST_SIZE; i++)
			m.write(i, fce(i));
		for (int i = 0; i < TEST_SIZE; i++)
			if (m.read(i) != fce(i))
				result = false;
		if (result) {
			System.out.println("Test1: PASS");
		} else {
			System.out.println("Test1: FAIL");
		}
		return result;
	}

	static boolean test2(Memory m) {
		boolean result = true;
		for (int i = 0; i < TEST_SIZE; i++)
			m.write(i, fce(i));
		for (int i = TEST_SIZE-1; i >= 0; i--)
			if (m.read(i) != fce(i))
				result = false;
		if (result) {
			System.out.println("Test2: PASS");
		} else {
			System.out.println("Test2: FAIL");
		}
		return result;
	}

	static boolean test3(Memory m) {
		boolean result = true;
		for (int i = TEST_SIZE-1; i >= 0; i--)
			m.write(i, fce(i));
		for (int posun = 0; posun < TEST_SIZE; posun += 100) {
			for (int i = 0; i < TEST_SIZE; i++) {
				int adr = (i+posun)%TEST_SIZE;
				if (m.read(adr) != fce(adr))
					result = false;
			}
		}
		int posun_zapis=55;
		for (int i = 0; i < TEST_SIZE; i++) {
			int adr = (i+posun_zapis)%TEST_SIZE;
			m.write(adr, fce2(adr));
		}
		for (int posun = 20; posun < TEST_SIZE; posun += 100) {
			for (int i = 0; i < TEST_SIZE; i++) {
				int adr = (i+posun)%TEST_SIZE;
				if (m.read(adr) != fce2(adr))
					result = false;
			}
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
					Constructor constr = c.getConstructor(new Class[] {
							int.class, String.class });
					Memory m = (Memory) constr.newInstance(new Object[] { SIZE,
							SWAP });
					boolean result = test1(m);
					result = test2(m) && result;
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
			System.out.println("Use TestMemory <class_name>");
		}
	}
}
