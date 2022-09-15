package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import Main.ActionsContext;
import Main.PL_Interface_SuperType_impl;
import Main.PL_Interface_impl;

class PL_Interface_implTest {

	private static Environment env=null;
	private static ActionsContext a=null;
	private static PL_Interface_impl impl;
	@BeforeAll
	public static void setUp() {
		env=new Environment(5);
		impl=new PL_Interface_impl();
		a=new ActionsContext(env, impl.e );

	}
	@Test
	public void testMain() {
		impl.Specification();
		assertTrue(impl.e.getHandicap().size()>0);
	}
}
