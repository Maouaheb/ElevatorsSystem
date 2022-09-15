package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;
import Main.Actions;
import Main.PL_Interface_impl;

class ElevatorTest {
	public static Environment env;
	public static Elevator e;
	public static PL_Interface_impl  impl=null;
	static Actions a=null;

	@BeforeAll
	public static void setup() {
		env=new Environment(5);
		e=new Elevator(env, true, 4, false);
		a=new Actions(env, e);
		impl=new PL_Interface_impl();

	}
	@Test
	void test() {
		assertEquals(5, e.floorButtons.length);
		impl.Specification13();
		e.timeShift();
		assertTrue(e.maximumWeight>e.getPersons().size());
	}

}
