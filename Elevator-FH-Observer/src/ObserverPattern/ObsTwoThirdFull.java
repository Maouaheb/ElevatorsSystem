package ObserverPattern;

import java.util.Observable;
import java.util.Observer;

import ElevatorSystem.Elevator;

public class ObsTwoThirdFull implements Observer {
// observer for two thirds weight
	@Override
	public void update(Observable o, Object arg) {
		
		//  a warning is displayed if the total weight in the elevator is almost two third to forbidden any new request
		// TODO Auto-generated method stub
System.out.println("Two thirds of elevator is full ");		
	}

}
