package Main;
import java.util.List; public interface PL_Interface {

	public List<String> getExecutedActions();


	public void start(int specification, int variation) throws Throwable;




	public boolean isAbortedRun();


}
