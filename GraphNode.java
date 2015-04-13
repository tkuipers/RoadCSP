import java.util.*;
public class GraphNode{
	private ArrayList<Connection> adjacent;
	private String id;
	// private boolean hasTruck;
	private ArrayList<Truck> truck;
	// private double needsPlow;
	// private ArrayList<Integer> weights;
	private boolean visited;
	public GraphNode(String id){
		adjacent = new ArrayList<Connection>();
		truck = new ArrayList<Truck>();
		// weights = new ArrayList<Integer>();
		this.id = id;
		visited = false;
	}
	public void addConn(Connection input){
		adjacent.add(input);
		// weights.add(weight);
	}
	// public ArrayList<Connection> getAdjacent(){
		// return adjacent;
	// }
	public void sendTruck(Truck inTruck){
		inTruck.addToPath(this);
		truck.add(inTruck);
	}
	public int getConnNum(){
		return adjacent.size();
	}
	public String toString(){
		String out = "";
		out += id;
		return out;
	}
	public void addTruck(Truck inTruck){
		truck.add(inTruck);
	}
	public boolean hasTruck(){
		if(truck.size() == 0){
			return true;
		}
		return false;
	}
	public void removeTruck(Truck inTruck){
		truck.remove(inTruck);
	}
	public ArrayList<Connection> getAdjacent(){
		return adjacent;
	}
	// 
	public boolean getVisited(){
		return visited;
	}
	public void setVisited(boolean input){
		visited = input;
	}
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
	public void increment(){
		if(!truck.isEmpty()){
			weighOptions();
			truck.remove(0);
		}
	}
	public void weighOptions(){
		double bestOp = -0.5;
		int outAddress = 0;
		for(int i = 0; i < adjacent.size(); i++){
			// System.out.println("DEBATING \n\t" + adjacent.get(i).toShortString() + "\n\twith a value of " + recSearch(adjacent.get(i).getOther(this), 1, 0, Constant.maxRec));
			if(recSearch(adjacent.get(i).getOther(this), adjacent.get(i).getNeeds(), 0, Constant.maxRec) > bestOp){
				bestOp = recSearch(adjacent.get(i).getOther(this), adjacent.get(i).getNeeds(), 0, Constant.maxRec);
				outAddress = i;
			}
		}
		// System.out.println("THE BEST PATH IS TO TAKE \n\t" + adjacent.get(outAddress).toShortString() + "\n\twith a value of " + bestOp);
		if(outAddress != -1){
			adjacent.get(outAddress).sendTruck(truck.get(0), this);
		}
	}
	public double recSearch(GraphNode parent, double prevVal, int curDepth, int maxDepth){
		double best = -1.0;
		int address = -1;
		for(int i = 0; i < parent.getAdjacent().size(); i++){
			double poss = prevVal * parent.getAdjacent().get(i).getNeeds();
			if(poss > best){
				best = poss;
				address = i;
			}
		}
		if(curDepth == maxDepth){
			return best;
		}
		return recSearch(parent.getAdjacent().get(address).getOther(parent), best, curDepth + 1, maxDepth);
	}
}