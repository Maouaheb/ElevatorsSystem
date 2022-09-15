package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;
import Main.Actions;
import Main.BigMacCallState;
import Main.BobCallState;

class ActionsTest {
	private static Environment env=null;
	private static Elevator e=null;
	private static Actions a=null;
	@BeforeAll
	public static void setUp() {
		env=new Environment(5);
		e=new Elevator(env, true,4,false);
		a=new Actions(env,e);
	}
	@Test
	void testBobCall() {
		Person person= a.Call(new BobCallState());
		assertNotNull(env);
		assertEquals("bob", person.getName());
		assertEquals(40, person.getWeight());
		assertEquals(4, person.getOrigin());
		assertEquals(0, person.getDestination());
		e.getPersons().add(person);
		e.timeShift();
		assertTrue(e.getPersons().size()>0);
	}
	@Test
	void testBigMacCall() {
		Person person= a.Call(new BigMacCallState());
		assertNotNull(env);
		assertEquals("BigMac", person.getName());
		assertEquals(150, person.getWeight());
		assertEquals(1, person.getOrigin());
		assertEquals(3, person.getDestination());
		e.getPersons().add(person);
		e.timeShift();
		assertTrue(e.getPersons().size()>0);
		System.out.println(e.maximumWeight+"  "+person.getWeight()+" "+e.getWeight());
		e.enterElevator(person);
		if(e.maximumWeight<= person.getWeight()) {
			assertTrue(e.isBlocked());

		}
	}
}
