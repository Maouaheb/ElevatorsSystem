package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;
import Main.ActionsType1;

class ActionsType1Test {
private static	Environment env=null;
private static ElevatorType1 e=null;
private static ActionsType1 a=null;
	@BeforeAll
	public static void setup() {
		env=new Environment(5);
		e=new ElevatorType1(env, true, 4, false);
		a=new ActionsType1(env, e);
	}
	
	@Test
	void testIntegrationHandicap() {
		Person person=a.personCall(a.getName(), a.getWeight(), a.getO(), a.getDest(), env, e);
		if(a.getName().equals("disabled")) {
			assertEquals(Handicap.class, person.getClass());
		}
	}

}
