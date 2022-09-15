package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ElevatorSystem.Environment;

class EnvironmentTest {

	@Test
	void testNumberFloors() {
		Environment env=new Environment(5);
	assertEquals(5, env.getFloors().length) ;
	 assertTrue(env.getFloors().length>1);
	}

}
