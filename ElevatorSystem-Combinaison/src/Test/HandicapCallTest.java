package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import AspectPattern.HandicapCall;
import ElevatorSystem.ContinueInDirection;
import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import Main.ActionsContext;

class HandicapCallTest {
    private static HandicapCall h= null;
	private static Elevator e=null;
	private static Environment env;
	@BeforeAll
	public static void set() {
	h= new HandicapCall();
	 env=new Environment(5);
		e=new Elevator(env, true, 4, false);
		
	}
	@Test
	void test() {
		
		ActionsContext a=new ActionsContext(env, e);
		e.getHandicap().add((Handicap) a.callStrategy("disabled",env));
		assertTrue(e.getHandicap().size()>0);
		e.timeShift();
		//assertEquals(ContinueInDirection.class, h.c.getClass());
		
	}

}
