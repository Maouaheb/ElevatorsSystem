package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;
import Main.ActionsContext;
import Main.PressInLift0;
import Main.PressInLift1;
import Main.PressInLift2;
import Main.PressInLift3;
import Main.PressInLift4;
import StrategyActions.BobCall;

class ActionsContextTest {
	private static Elevator e=null;
	private static Environment env=null;
	static ActionsContext a=null;
	
	@BeforeAll
	public static void setUp() {
		env=new Environment(5);
		e=new Elevator(env, true, 4, false);
		a=new ActionsContext(env, e);
	}
	@Test
	void testCall() {
		Person person= a.callStrategy("Bob",env);
		assertNotNull(env);
		assertEquals(BobCall.class,a.CallStrategy.getClass());
		assertEquals("bob", person.getName());
		assertEquals(40, person.getWeight());
		assertEquals(4, person.getOrigin());
		assertEquals(0, person.getDestination());
		e.timeShift();
		assertTrue(e.getPersons().size()>0);	
		}
	@Test
	//integration tests
	public void textPress() {
		a.pressInLift(a.getNum(), e);
		assertTrue(a.getNum()>=0 && a.getNum()<5);
		if(a.getNum()==0) {
			assertEquals(PressInLift0.class,a.press.getClass());
		}
		if(a.getNum()==1) {
			assertEquals(PressInLift1.class,a.press.getClass());
		}
		if(a.getNum()==3) {
			assertEquals(PressInLift3.class,a.press.getClass());
		}
		if(a.getNum()==2) {
			assertEquals(PressInLift2.class,a.press.getClass());
		}
		if(a.getNum()==4) {
			assertEquals(PressInLift4.class,a.press.getClass());
		}
	}
}
