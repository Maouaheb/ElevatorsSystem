package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import Main.Actions;
import Main.PL_Interface_impl;

class ActionsTest {
	public static Environment env;
	public static Elevator e;
	public static PL_Interface_impl  impl=null;
	static int  spec;
	static int  var;
	static Actions a=null;
	@BeforeAll
	public static void setup() throws Throwable {
		impl=new PL_Interface_impl();
		env=new Environment(5);
		e=new Elevator(env, true, 4, false);
		spec=3;
		var=-1;
		impl.start(spec, var);
		impl.setVariation(-1);
		a=new Actions(env, e);
	}
	@Test
	void testSpec1() {
		
		impl.Specification1();
		assertNotNull(a.angelinaCall());
		assertNotNull(a.bigMacCall());

	}
	@Test
	void testSpec2() {
		impl.Specification2();
		assertNotNull(a.bigMacCall());

/*
	
		assertNotNull(a.chuckCall());
		assertNotNull(a.monicaCall());
		assertNotNull(a.handicapCall());*/


	}
	@Test
	void testSpec3() {
		impl.Specification3();
		assertNotNull(a.bobCall());
	}
	@Test
	void testSpec9() {
		impl.Specification9();
		assertNotNull(a.bigMacCall());

		
	}
	@Test
	void testSpec13() {
		impl.Specification13();
		assertNotNull(a.aliceCall());
		assertNotNull(a.bobCall());
		assertNotNull(a.handicapCall());
		
	}
	@Test
	void testSpec14() {
		impl.Specification14();
		assertNotNull(a.bigMacCall());
		assertNotNull(a.bobCall());
		assertNotNull(a.handicapCall());
		
	}
	
}
