package ElevatorSystem; public  class Environment {
	 /*@model@*/ boolean[] calledAt_Spec1;
	 /*@model@*/ boolean[] calledAt_Spec2;
	Floor[] floors;
	/*@ 
	 ensures (\forall int i; 0 <= i && i < calledAt_Spec1.length; !calledAt_Spec1[i]);
	 ensures (\forall int i; 0 <= i && i < calledAt_Spec1.length; !calledAt_Spec2[i]);
	 assignable floors, floors[*]; @*/
	public Environment  (int numFloors) {
		//@ set calledAt_Spec1 = new boolean[numFloors]; 
		//@ set calledAt_Spec2 = new boolean[numFloors]; 
		floors = new Floor[numFloors];
		for (int i = 0; i < numFloors; i++) {
			floors[i] = new Floor(this, i);}
		floors = new Floor[numFloors];
		for (int i = 0; i < numFloors; i++) {
			floors[i] = new Floor(this, i);	}}
	public Floor getFloor  (int id) {
		return floors[id];}
	public Floor[] getFloors  () {
		return floors;}
	public boolean isTopFloor  (int id) {
		return id == floors.length-1;	}
	 /*@model@*/ boolean[] calledAt_Spec9;}
