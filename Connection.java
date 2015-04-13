import java.util.*;
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
	public void sendTruck(Truck inTruck, GraphNode fromNode){
		inTruck.setDestination(getOther(fromNode));
		plowTruck.add(inTruck);
		counting = true;
		count.add(0);
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
	public double getRemoveNeeds(){
		if(priority == 1 || priority == 2){
			if(snowAmount > Constant.highPriSnowCut){
				removeNeeds =  1;
			}
		}
		if(priority == 3){
			if(snowAmount > Constant.lowPriSnowCut){
				removeNeeds =  1;
			}
		}
		//problem here, will start plowing residential before it finishes everywhere else
		if(priority == 4){
			if(status == 0){
				removeNeeds =  1;
			}
		}
		return removeNeeds;
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
		if(plowTruck.size() != 0){
			for(int i = 0; i < plowTruck.size(); i++){
				out += "\n\t\tcontains Truck:" + plowTruck.get(i);
				out += "\n\t\tdestination is: " + plowTruck.get(i).getDestination();
			}
		} 
		else{
			out += "\n\t\tContains No Trucks";
		}
		return out;
	}
	public void increment(){
		incrementNeeds();
		for(int i = 0; i < plowTruck.size(); i++){
			Truck curPlowTruck = plowTruck.get(i);
				count.set(i, count.get(i) + 1);
			if(count.get(i) >= (weight/curPlowTruck.getSpeed())){
				curPlowTruck.getDestination().sendTruck(curPlowTruck);
				if(curPlowTruck.canScrape()){
					needsScrape = 0.0;
				}
				if(curPlowTruck.canSand()){
					// needsScrape = 0.0;
					needSand = 0.0;
				}
				if(curPlowTruck.canRemove()){
					removeNeeds = 0.0;
				}
				count.remove(((int) i));
				// counting = false;
				plowTruck.remove(curPlowTruck);
			}
		}

	}

}