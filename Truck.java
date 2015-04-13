import java.util.*;
public class Truck{
	private String id;
	private GraphNode dest;
	private ArrayList<GraphNode> path;
	private double speed;
	private boolean canScrape;
	private boolean canSand;
	private boolean canRemove;
	private int type;
	//type1 = scarper
	//type2 = sander
	//type3 = snow remover
	// private int type;
	public Truck(String id, int type, double upm, boolean scrape, boolean sand, boolean remove){ //units per minute
		path = new ArrayList<GraphNode>();
		this.id = id;
		speed = upm;
		canScrape = scrape;
		canSand = sand;
		canRemove = remove;
		this.type = type;
	}
	public int getType(){
		return type;
	}
	public String toString(){
		String out = "";
		out +=  id;
		return out;
	}
	public boolean canScrape(){
		return canScrape;
	}
	public boolean canRemove(){
		return canRemove;
	}
	public boolean canSand(){
		return canSand;
	}
	public void addToPath(GraphNode in){
		// System.out.println("ADDING " + in + "  THE TRUCK PATH");
		path.add(in);
	}
	public ArrayList<GraphNode> getPath(){
		return path;
	}
	public void setDestination(GraphNode destination){
		this.dest = destination;
	}
	public GraphNode getDestination(){
		return dest;
	}
	public void setSpeed(double inSpeed){
		speed = inSpeed;
	}
	public double getSpeed(){
		return speed;
	}
}