import java.util.*;
//A class to represent roads between intersections
public class Connection{
	private int weight;
	private GraphNode from;
	private GraphNode to;
	private int travelCount;
	private double needsScrape;
	private double needSand;
	private ArrayList<Truck> plowTruck;
	private GraphNode destination;
	private boolean counting;
	private ArrayList<Integer> count;
	private int priority;
	private int status;
	private double removeNeeds;
	private double snowAmount;
	//contructor takes in a weight, a from node, a to node, and a priority for how important th road is
	//priority 1 = freeways, arterial roadways, business districts, bus ways
	//priority 2 = collector/bus route roadways, transit park and ride access roads
	//priority 3 = local industrial roadways
	//priority 4 = residential roadways, alleys
	public Connection(int weight, GraphNode from, GraphNode to, int priority){
		plowTruck = new ArrayList<Truck>();
		this.weight = weight;
		this.to = to;
		this.from = from;
		counting = false;
		count = new ArrayList<Integer>();
		this.priority = priority;
		status = 0;
		needsScrape = 0.1;
		needSand = 0.1;
		count  = new ArrayList<Integer>();
	}
	//put a truck into the road, where it will be held for a certain amound of time based on the speed and will then be sent to a destination stored in the truck
	public void sendTruck(Truck inTruck, GraphNode fromNode){
		inTruck.setDestination(getOther(fromNode));
		plowTruck.add(inTruck);
		counting = true;
		count.add(0);
	}
	//return the length of the road
	public int getWeight(){
		return weight;
	}
	// set the length of the road
	public void setWeight(int weight){
		this.weight = weight;
	}
	// get one connection of the road
	public GraphNode getTo(){
		return to;
	}
	// status = 0: All clear, is not snowing
	// status = 1: snowing Constant.statOneStorm cm/min
	// status = 2: snowing Constant.statTwoStorm cm/min
	// if the storm status is on alert, set residential streets to being unneeded
	// if not snowing, set residential streets to a status of one.
	public void setStatus(int newStatus){
		if(newStatus == 0){
			status = newStatus;
			if(priority == 3 || priority == 4){
				needsScrape = 1;
				needSand = 1;
				removeNeeds = 0.9;
			}
		}
		else if(newStatus == 1 || newStatus == 2){
			status = newStatus;
			if(priority == 3 || priority == 4){
				
				needsScrape = 0.5;
				needSand = 0.5;
				removeNeeds = 0.1;
			}
		}
	}

	//Increment the value of the road
	//increment how badly it needs to be scraped, sanded, and how much snow has fallen on it
	public void incrementNeeds(){
		if(priority == 1 ){
			needsScrape += ((1.0/Constant.firstPriPlowRate) * status);
			needSand += ((1.0/Constant.firstPriPlowRate) * status);
		}
		if(priority == 2 ){
			needsScrape += ((1.0/Constant.secondPriPlowRate) * status);
			needSand += ((1.0/Constant.secondPriPlowRate) * status);
		}
		if(status == 1){
			snowAmount += Constant.statOneStorm;
		}
		if(status == 2){
			snowAmount += Constant.statTwoStorm;
		}
	}
	public double getScrapeNeeds(){
		return needsScrape;
	}
	public double getSandNeeds(){
		return needSand;
	}
	//if the status is 0, it will double remove needs of imortant streets, and set residential streets to have a need of 1
	public double getRemoveNeeds(){
		double tempNeeds = removeNeeds;
		if(priority == 1 || priority == 2){
			if(snowAmount > Constant.highPriSnowCut){
				tempNeeds =  0.9;
			}
			else{
				tempNeeds = 0.1;
			}
			// if(status == 0){
				// tempNeeds *= 2;
			// }
		}
		// if(priority == 3){
			// if(snowAmount > Constant.lowPriSnowCut){
				// tempNeeds =  0.9;
			// }
			// else{
				// tempNeeds = 0.1;
			// }
			// if(status == 0){
				// tempNeeds *= 1.5;
			// }
		// }
		// if(priority == 4){
			// if(status == 0){
				// tempNeeds =  0.9;
			// }
			// else{
				// tempNeeds = 0.1;
			// }
		// }
		removeNeeds = tempNeeds;
		return tempNeeds;
	}
	//set where road is heading to
	public void setTo(GraphNode to){
		this.to = to;
	}
	// return where road is heading from
	public GraphNode getFrom(){
		return from;
	}
	//set wehre road is coming from
	public void setFrom(GraphNode from){
		this.from = from;
	}
	//return the other end of the road when an intersection is inputted
	public GraphNode getOther(GraphNode cur){
		if(to == cur){
			return from;
		}
		return to;
	}
	//get a summarized version of the road
	public String toShortString(){
		String out = "";
		out += from + " to " + to + "\n\twith a need of " + needsScrape;
		return out;

	}
	//get an in depth string aof information on the road
	public String toString(){
		String out = "";
		out += from + " to " + to + "\n\twith a weight of " + weight;
		if(plowTruck.size() != 0){
			for(int i = 0; i < plowTruck.size(); i++){
				out += "\n\t\tcontains Truck:" + plowTruck.get(i);
				out += "\n\t\tdestination is: " + plowTruck.get(i).getDestination();
			}
		} 
		else{
			out += "\n\t\tContains No Trucks";
		}
		out += "\n\t\tScrapeNeed: " + needsScrape;
		out += "\n\t\tSandNeed: " + needSand;
		out += "\n\t\tRemovalNeed: " + removeNeeds;
		out += "\n\t\tSnowAmount: " + snowAmount;
		return out;
	}
	//incremenet snow values and if there is a truck, move the truck along until it gets to the point where you push it to the other intersection
	public void increment(){
		incrementNeeds();
		for(int i = 0; i < plowTruck.size(); i++){
			Truck curPlowTruck = plowTruck.get(i);
			count.set(i, count.get(i) + 1);
			if(count.get(i) >= (weight/curPlowTruck.getSpeed())){
				curPlowTruck.getDestination().sendTruck(curPlowTruck);
				if(curPlowTruck.canScrape()){
					needsScrape = 0.1;
				}
				if(curPlowTruck.canSand()){
					// needsScrape = 0.0;
					needSand = 0.1;
				}
				if(curPlowTruck.canRemove()){
					removeNeeds = 0.1;
					snowAmount = 0.0;
				}
				count.remove(((int) i));
				// counting = false;
				plowTruck.remove(curPlowTruck);
			}
		}

	}

}