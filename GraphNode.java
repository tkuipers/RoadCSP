import java.util.*;
//A Class to represent intersection
//Used to point the truck in it's new direction after entering the intersection
public class GraphNode{
	private ArrayList<Connection> adjacent;
	private String id;
	private ArrayList<Truck> truck;
	private boolean visited;
	private Graph parent;
	public GraphNode(String id, Graph parent){
		adjacent = new ArrayList<Connection>();
		truck = new ArrayList<Truck>();
		this.id = id;
		visited = false;
		this.parent = parent;
	}
	//add a connection(road) to another intersection
	public void addConn(Connection input){
		adjacent.add(input);
		// weights.add(weight);
	}
	//Send a truck down a road to another intersection
	public void sendTruck(Truck inTruck){
		inTruck.addToPath(this);
		truck.add(inTruck);
	}
	//toString method.  returns the id of the intersection
	public String toString(){
		String out = "";
		out += id;
		return out;
	}
	//add a truck to the intersection
	public void addTruck(Truck inTruck){
		truck.add(inTruck);
	}
	//check if the intersection has a truck in it
	public boolean hasTruck(){
		if(truck.size() == 0){
			return true;
		}
		return false;
	}
	//remove a truck from the intersection
	public void removeTruck(Truck inTruck){
		truck.remove(inTruck);
	}
	//get all connections running into the intersection
	public ArrayList<Connection> getAdjacent(){
		return adjacent;
	}
	public Graph getGraph(){
		return parent;
	}
	//print detailed information about the intersection... all connections and the amount of trucks in it.
	public String printGraph(){
		// System.out.println("hey");
		// visited = true;
		String out = "";
		// out += id;
		if(truck.isEmpty()){
			out += "\n" +this + " has no truck ";
		}
		else{
			out += "\n" +this + " contains the Trucks: ";
			for(int i = 0; i < truck.size(); i++){
				out += "\n\t" +  truck.get(i); 
			}
		}
		out += "\n" +this + " is connected to:";
		for(int  j = 0; j < adjacent.size(); j++){
			// out += adjacent.get(j).getOther(this) + " weight: " + adjacent.get(j).getWeight() + "\n\t";
			out +=  "\n\t" + adjacent.get(j);
		} 
		// for(int i = 0; i < adjacent.size(); i++){
			// if(!adjacent.get(i).getOther(this).getVisited()){
				// adjacent.get(i).setVisited(true);
				// out += adjacent.get(i).getOther(this).printGraph();
			// }
		// }
		return out;
	}
	//go through a unit of time.  if the intersection contains a truck. it will send the truck out.
	public void increment(){
		if(!truck.isEmpty()){
			// weighOptions();
			// truck.remove(0);
			for(int i = 0; i < truck.size(); i++){
				weighOptions(truck.get(i));
			}
			truck = new ArrayList<Truck>();
		}
	}
	//recursively fin the best bath for the truck.  Looks x roads in advance based on the number set in the constants file.
	public void weighOptions(Truck inTruck){
		double bestOp = -0.5;
		int outAddress = 0;
		// if(inTruck.getType() == 1){
		for(int i = 0; i < adjacent.size(); i++){
			double needs = 1.0;
			if(inTruck.canScrape()){
				needs *= adjacent.get(i).getScrapeNeeds();
			}
			if(inTruck.canSand()){
				needs *= adjacent.get(i).getSandNeeds();
			}
			if(inTruck.canRemove()){
				needs *= adjacent.get(i).getRemoveNeeds();
			}
			// System.out.println("NEEDS IS " + needs);
			double tempNum = recSearch(adjacent.get(i).getOther(this), needs, 0, Constant.maxRec, 1, inTruck);
			// System.out.println("TRUCK "+ inTruck+" IS DEBATING \n\t" + adjacent.get(i).toShortString() + "\n\twith a value of " + tempNum);
			if(tempNum > bestOp){
				bestOp = tempNum;
				outAddress = i;
			}
		}
		// }
		// System.out.println("THE BEST PATH IS TO TAKE \n\t" + adjacent.get(outAddress).toShortString() + "\n\twith a value of " + bestOp);
		if(outAddress != -1){
			if(inTruck.canRemove()){
				// System.out.println(this.getGraph() + "\nTHE REMOVAL TRUCK BEST OPTION IS AT " + this + " AND SHOULD GO " + adjacent.get(outAddress).toShortString() + " WITH A VALUE OF " + bestOp + "\n\t" + "the other value is " + recSearch(adjacent.get(outAddress).getOther(this), 1.0, 0, Constant.maxRec, 1, inTruck) + "\n\tRemovalNeed" + adjacent.get(outAddress).getRemoveNeeds());	
			}	
			adjacent.get(outAddress).sendTruck(inTruck, this);
		}
	}
	//recursively search through the tree
	public double recSearch(GraphNode parent, double prevVal, int curDepth, int maxDepth, int type, Truck inTruck){
		double best = -1.0;
		int address = -1;
		// if(type == 1){
		for(int i = 0; i < parent.getAdjacent().size(); i++){
			double poss = prevVal;
			if(inTruck.canScrape()){
				poss *= parent.getAdjacent().get(i).getScrapeNeeds();
			}
			if(inTruck.canSand()){
				poss *= parent.getAdjacent().get(i).getSandNeeds();
			}
			if(inTruck.canRemove()){
				poss *= parent.getAdjacent().get(i).getRemoveNeeds();
			}
			if(poss > best){
				best = poss;
				address = i;
			}
		}
		if(curDepth == maxDepth){
			return best;
		}
		return best * recSearch(parent.getAdjacent().get(address).getOther(parent), best, curDepth + 1, maxDepth, type, inTruck);
	}
}