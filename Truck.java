import java.util.*;
public class Truck{
	private String id;
	private GraphNode dest;
	private ArrayList<GraphNode> path;
	public Truck(String id){
		path = new ArrayList<GraphNode>();
		this.id = id;
	}
	public String toString(){
		String out = "";
		out +=  id;
		return out;
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
}