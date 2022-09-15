package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import Main.ActionsContext;
import StrategyPattern.stopRequestedInDirectionOverloaded;

class ElevatorTest {

	private static Elevator e=null;
	private static Environment env=null;
	
	@BeforeAll
	public static void setUp() {
		env=new Environment(5);
		e=new Elevator(env, true, 4, false);
	}
	@Test
	public void testElevatorDirection() {
		ActionsContext a=new ActionsContext(env, e);
		Handicap h=(Handicap)a.callStrategy("disabled",env);
		e.getHandicap().add(h);
		e.timeShift();
		assertEquals(stopRequestedInDirectionOverloaded.class, e.stoprequested.getClass());
	}
}
