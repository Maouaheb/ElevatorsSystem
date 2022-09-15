package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import Main.Actions;
import Main.PL_Interface_impl;

class PL_Interface_implTest {
	static Environment env=null;
	static Elevator e=null;
	static Actions a=null;
	@BeforeAll
	public static void setup() {
		env=new Environment(5);
		e=new Elevator(env, true, 4, false);
		a=new Actions(env, e);
		
	}
	@Test
	public void IntegrationTest() {
		
	}
	@Test
	void testSpecification() {
		PL_Interface_impl impl=new PL_Interface_impl();
		impl.setSpecification(2);
		assertTrue(impl.getSpecification()>=1 && impl.getSpecification() <= 14, "specficiation incorrect must be between 1 and 14");
		impl.setVariation(-1);
		assertTrue(impl.getVariation()>=-1, "variation incorrect");
	}

}
