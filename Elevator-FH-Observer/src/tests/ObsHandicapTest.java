package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import Main.PL_Interface_impl;
import ObserverPattern.ObsHandicap;

class ObsHandicapTest {

	static Elevator e=null;
	static ObsHandicap observer=null;
	static PL_Interface_impl impl=null;
	static Environment env=null;
	@BeforeAll
	public static void setUp() {
		
		observer=new ObsHandicap();
		e=observer.elevator;
		env=new Environment(5);
	}
	@Test
	public void testHandicap() {
		e.getHandicap().add(new Handicap("disabled", 40, 3, 0, env));
		e.timeShift();
		assertTrue(e.getHandicap().size()>0);
	}
	

}
