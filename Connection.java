public class Connection{
	private int weight;
	private GraphNode from;
	private GraphNode to;
	private int travelCount;
	private double needsScrape;
	private double needSand;
	private Truck plowTruck;
	private GraphNode destination;
	private boolean counting;
	private int count;
	private int priority;
	private int status;
	public Connection(int weight, GraphNode from, GraphNode to, int priority){
		this.weight = weight;
		this.to = to;
		this.from = from;
		counting = false;
		count = -1;
		this.priority = priority;
		status = 0;
		needsScrape = 0.1;
		needSand = 0.1;
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
	//if the storm status is on alert, set residential streets to being unneeded
	public void setStatus(int newStatus){
		if(newStatus == 0){
			status = newStatus;
			if(priority == 3 || priority == 4){
				needsScrape = 1;
			}
		}
		else if(newStatus == 1 || newStatus == 2){
			status = newStatus;
			if(priority == 3 || priority == 4){
				needsScrape = 0.1;
			}
		}
	}

	//priority 1 = freeways, arterial roadways, business districts, bus ways
	//priority 2 = collector/bus route roadways, transit park and ride access roads
	//priority 3 = local industrial roadways
	//priority 4 = residential roadways, alleys
	public void incrementNeeds(){
		if(priority == 1 ){
			needsScrape += ((1.0/Constant.firstPriPlowRate) * status);
			needSand += ((1.0/Constant.firstPriPlowRate) * status);
		}
		if(priority == 2 ){
			needsScrape += ((1.0/Constant.secondPriPlowRate) * status);
			needSand += ((1.0/Constant.secondPriPlowRate) * status);
		}
	}
	public double getScrapeNeeds(){
		return needsScrape;
	}
	public double getSandNeeds(){
		return needsScrape * needSand;
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
		out += from + " to " + to + "\n\twith a need of " + needsScrape;
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
		if(plowTruck != null){
			if(counting){
				needsScrape = -1.0;
				count++;
			}
			if(count >= (weight/plowTruck.getSpeed())){
				plowTruck.getDestination().sendTruck(plowTruck);
				plowTruck = null;
				needsScrape = 0.0;
				count = -1;
				counting = false;
			}
		}

	}

}