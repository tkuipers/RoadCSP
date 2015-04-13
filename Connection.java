public class Connection{
	private int weight;
	private GraphNode from;
	private GraphNode to;
	private int travelCount;
	private double needsTruck;
	private Truck plowTruck;
	private GraphNode destination;
	private boolean counting;
	private int count;
	public Connection(int weight, GraphNode from, GraphNode to){
		this.weight = weight;
		this.to = to;
		this.from = from;
		counting = false;
		count = -1;
	}
	public void sendTruck(Truck inTruck, GraphNode fromNode){
		inTruck.setDestination(getOther(fromNode));
		plowTruck = inTruck;
		counting = true;
		count = 0;
	}
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public GraphNode getTo(){
		return to;
	}
	public void incrementNeeds(){
		needsTruck += (1.0/Constant.plowRate); //30 minutes is how often the road needs to be plowed.
	}
	public double getNeeds(){
		return needsTruck;
	}
	public int getCount(){
		return travelCount;
	}
	public void upCount(){
		travelCount++;
	}
	public void setTo(GraphNode to){
		this.to = to;
	}
	public GraphNode getFrom(){
		return from;
	}
	public void setFrom(GraphNode from){
		this.from = from;
	}
	public GraphNode getOther(GraphNode cur){
		if(to == cur){
			return from;
		}
		return to;
	}
	public String toShortString(){
		String out = "";
		out += from + " to " + to + "\n\twith a need of " + needsTruck;
		return out;

	}
	public String toString(){
		String out = "";
		out += from + " to " + to + "\n\twith a weight of " + weight;
		if(plowTruck != null){
			out += "\n\t\tcontains Truck:" + plowTruck;
			out += "\n\t\tdestination is: " + plowTruck.getDestination();
		} 
		else{
			out += "\n\t\tContains No Trucks";
		}
		return out;
	}
	public void increment(){
		incrementNeeds();
		if(counting){
			needsTruck = -1.0;
			count++;
		}
		if(count >= (weight/2)){
			plowTruck.getDestination().sendTruck(plowTruck);
			plowTruck = null;
			needsTruck = 0.0;
			count = -1;
			counting = false;
		}

	}

}