package Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.LiftSystem;
import ElevatorSystem.LiftSystemHandicap;
import Main.Actions;
import Main.HandicapCall;

class ElevatorTest {
	private static Environment	env =null;
	private static Elevator e =null;
	private static Actions a =null;
	
	@BeforeAll
	static void setUp() {
		env = new Environment(5);
		e = new Elevator(env, true,4,false);
		a = new Actions(env, e);
	}

	@Test
	void testIntegrationHandicap() {
		// test if we have handicap call we select the handicap lift strategy
		Handicap h=(Handicap) a.Call(new HandicapCall());
		e.getHandicap().add(h);
		e.timeShift();
		if(e.getHandicap().size()>0) {
			assertEquals(LiftSystemHandicap.class,e.lift.getClass());
		}
		if(e.getHandicap().size()==0 && e.getPersons().size()>0) {
			assertEquals(LiftSystem.class,e.lift.getClass());
		}
	}

	@Test
	void test() {
		assertTrue(e.maximumWeight>e.getPersons().size());
	}


}
