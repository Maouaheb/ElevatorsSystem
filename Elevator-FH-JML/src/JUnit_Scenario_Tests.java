import org.junit.Before; import org.junit.Test; import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.Person; import TestSpecifications.SpecificationException; import TestSpecifications.SpecificationManager; public  class JUnit_Scenario_Tests {
	private static final int cleanupTimeShifts = 12;


	@Before
	public void setup() {

	}

	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec1.length; !env.calledAt_Spec1[i]);
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec2.length; !env.calledAt_Spec2[i]);
	 assignable \nothing; @*/
	 private void  testFinished__wrappee__Base  (Environment env) {

	}

	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]);
	 assignable \nothing; @*/
	public void testFinished(Environment env) {
		testFinished__wrappee__Base(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification1() {
		SpecificationManager.checkOnlySpecification(1);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		a.angelinaCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification2() {
		SpecificationManager.checkOnlySpecification(2);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification3() {
		SpecificationManager.checkOnlySpecification(3);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false, 4, false);
		Actions a = new Actions(env, e);

		Person bob = a.bobCall();
		while (env.getFloor(bob.getOrigin()).hasCall())
			e.timeShift();
		
		
		e.timeShift();
		
		
		
		
		a.bobCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification9() {
		SpecificationManager.checkOnlySpecification(9);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification13() {
		SpecificationManager.checkOnlySpecification(13);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.aliceCall();
		Person angelina = a.angelinaCall();
		while (env.getFloor(angelina.getOrigin()).hasCall()) {
			e.timeShift();
		}
		a.bobCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


	@Test(expected = SpecificationException.class)
	public void Specification14() {
		SpecificationManager.checkOnlySpecification(14);
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		Person bm = a.bigMacCall();

		while (env.getFloor(bm.getOrigin()).hasCall()) {
			e.timeShift();
		}
		a.bobCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		testFinished(env);
	}


}
