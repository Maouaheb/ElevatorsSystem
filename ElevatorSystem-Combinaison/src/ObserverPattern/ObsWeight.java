package ObserverPattern;

import java.util.Observable;
import java.util.Observer;

public class ObsWeight implements Observer {
// observe the weight
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("I am observer of overloaded weight");
		
	}

}
