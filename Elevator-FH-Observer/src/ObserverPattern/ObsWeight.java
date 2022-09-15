package ObserverPattern;

import java.util.Observable;
import java.util.Observer;

public class ObsWeight implements Observer {
// observer for the weight
	@Override
	public void update(Observable o, Object arg) {
		// a warning that is displayed when the elevator exceeds the maximum weight
		// TODO Auto-generated method stub
		System.out.println("I am observer of overloaded weight");
		
	}

}
