//A class to represent a sanding truck
public class Sander extends Truck{
	public Sander(String input, int spawn){
		// super(input, 1, 20, false, true, false);
		super(input, 1, 2, true, true, false, spawn);
	}
}