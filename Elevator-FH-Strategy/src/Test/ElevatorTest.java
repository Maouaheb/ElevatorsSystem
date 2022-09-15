package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.timeShiftStrategy1;
import ElevatorSystem.timeShiftStrategyHandicap;
import Main.ActionsContext;

class ElevatorTest {

	private static Environment	env =null;
	private static Elevator e =null;
	private static ActionsContext a =null;
	
	@BeforeAll
	static void setUp() {
		env = new Environment(5);
		e = new Elevator(env, true,4,false);
		a = new ActionsContext(env, e);
	}
	@Test
	void testIntegrationHandicap() {
		// test if we have handicap call we select the handicap lift strategy
		Handicap h=(Handicap) a.callStrategy("disabled", env);
		e.getHandicap().add(h);
		e.timeShift();
		if(e.getHandicap().size()>0) {
			assertEquals(timeShiftStrategyHandicap.class , e.shiftStrategy.getClass() );
		}
		if(e.getHandicap().size() == 0 && e.getPersons().size()>0) {
			assertEquals(timeShiftStrategy1.class , e.shiftStrategy.getClass() );
		}
	}

}
