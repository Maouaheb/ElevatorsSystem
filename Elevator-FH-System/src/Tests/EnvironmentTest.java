package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ElevatorSystem.Environment;

class EnvironmentTest {
	int num=5;

	@Test
	void testNumberFloors() {
		Environment env=new Environment(5);
	assertEquals(num, env.getFloors().length) ;
	 assertTrue(env.getFloors().length>1);
	}
	

}
