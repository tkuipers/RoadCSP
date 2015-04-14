import java.util.*;
// The truck class that different machines extend from
public class Truck{
	private String id;
	private GraphNode dest;
	private ArrayList<GraphNode> path;
	private double speed;
	private boolean canScrape;
	private boolean canSand;
	private boolean canRemove;
	private int type;
	private int itSpawned;
	public Truck(String id, int type, double upm, boolean scrape, boolean sand, boolean remove, int spawned){ //units per minute
		path = new ArrayList<GraphNode>();
		this.id = id;
		speed = upm;
		canScrape = scrape;
		canSand = sand;
		canRemove = remove;
		this.type = type;
		itSpawned = spawned;
	}
	public int getType(){
		return type;
	}
	public String toString(){
		String out = "";
		out +=  id + " spawned at " + itSpawned;
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
	//check to see if the node 4 spots back is the same, if so, check to see if the node 2 spots back is the same, if they are, we need to remove
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