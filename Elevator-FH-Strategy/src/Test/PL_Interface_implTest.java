package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Main.PL_Interface_impl;
import Main.Specification1;
import Main.Specification13;
import Main.Specification14;
import Main.Specification2;
import Main.Specification9;

class PL_Interface_implTest {
	private static PL_Interface_impl impl =null;
	
	@BeforeAll
	static void setUp() {
		 impl=new PL_Interface_impl();

	}
	@Test
	void test() {
		impl.test(PL_Interface_impl.specification, PL_Interface_impl.variation);
		if(PL_Interface_impl.specification==1) {
			assertEquals(Specification1.class, impl.spec.getClass());
		}
		if(PL_Interface_impl.specification==2) {
			assertEquals(Specification2.class, impl.spec.getClass());
		}
		if(PL_Interface_impl.specification==9) {
			assertEquals(Specification9.class, impl.spec.getClass());
		}
		if(PL_Interface_impl.specification==13) {
			assertEquals(Specification13.class, impl.spec.getClass());
		}
		if(PL_Interface_impl.specification==14) {
			assertEquals(Specification14.class, impl.spec.getClass());
		}
	}
	@Test
	void TestUnits() {
		// the specification value
		System.out.print(PL_Interface_impl.specification);
		assertTrue((PL_Interface_impl.specification == 1) || (PL_Interface_impl.specification == 2) || (PL_Interface_impl.specification == 3 )||(PL_Interface_impl.specification ==9) || (PL_Interface_impl.specification == 13) || (PL_Interface_impl.specification == 14));
		assertTrue(PL_Interface_impl.cleanupTimeShifts ==24);
		assertTrue( Integer.class.isInstance(PL_Interface_impl.getIntegerMinMax(0, 7)));
		assertTrue(PL_Interface_impl.NUM_FLOORS ==5);
	}

}
