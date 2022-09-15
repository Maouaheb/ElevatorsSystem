package AspectPattern;

public aspect Overloaded {
	// inject the aspect in time shift elevator
	pointcut overloaded() : execution(* ElevatorSystem.Elevator.timeShift());
	after() returning() : overloaded() {
		System.out.println("aspect overloaded elevator");
	}

}
