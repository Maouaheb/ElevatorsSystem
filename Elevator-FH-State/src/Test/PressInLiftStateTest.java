package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import Main.PressInLiftState;

class PressInLiftStateTest {
	private static Environment	env =null;
	private static Elevator e =null;
	private static PressInLiftState press=null;
	
	@BeforeAll
	static void setUp() {
		env = new Environment(5);
		e = new Elevator(env, true,4,false);
		press=new PressInLiftState();
	}
	@Test
	void test() {
		assertTrue(press.getNumber()<=env.getFloors().length);
	}
	@AfterAll
	public static void testPress() {
		e.pressInLiftFloorButton(press.getNumber());
		assertTrue(e.buttonForFloorIsPressed(press.getNumber()));
	}
}
