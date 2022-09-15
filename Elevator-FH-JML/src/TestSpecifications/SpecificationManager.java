package TestSpecifications; public class SpecificationManager {

	public static void setupSpecifications() {
	}


	public static boolean checkSpecification(int id) {
		if (singleSpecification == -2)
			return false;
		else if (singleSpecification == -1)
			return true;
		else 
			return singleSpecification == id;
	}

	private static int singleSpecification = -1;


	public static void checkOnlySpecification(int scenario) {
		singleSpecification  = scenario;
	}


}
