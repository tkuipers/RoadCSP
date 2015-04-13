import java.util.*;
//The class to represent the map of the "world"
public class Graph{

	private ArrayList<Connection> allConns;
	private GraphNode head;
	private ArrayList<GraphNode> allNodes;
	private ArrayList<Truck> allTrucks;
	private int snowStatus;
	/*
		a1---------10----------a2
		|						|
		|						|
	   5|						|5
		|						|
		|						|
		b1----5----b2-----5----b3
		|		   /\			|
		|		  /  \			|
	   5|		4/    \4		|5
		|		/      \		|
		|	   /	    \		|
		c1----c2---------c3----c4
		   3        4        3
	*/
	//constructor
	public Graph(){
		allConns = new ArrayList<Connection>();

		allNodes = new ArrayList<GraphNode>();

		allTrucks = new ArrayList<Truck>();


		snowStatus = 0;

		head = new GraphNode("a1", this);
		GraphNode a1 = head;
		GraphNode a2 = new GraphNode("a2", this);
		GraphNode b1 = new GraphNode("b1", this);
		GraphNode b2 = new GraphNode("b2", this);
		GraphNode b3 = new GraphNode("b3", this);
		GraphNode c1 = new GraphNode("c1", this);
		GraphNode c2 = new GraphNode("c2", this);
		GraphNode c3 = new GraphNode("c3", this);
		GraphNode c4 = new GraphNode("c4", this);

		allNodes.add(a1);
		allNodes.add(a2);
		allNodes.add(b1);
		allNodes.add(b2);
		allNodes.add(b3);
		allNodes.add(c1);
		allNodes.add(c2);
		allNodes.add(c3);
		allNodes.add(c4);

		makeConn(a1, a2, 10, 1);
		makeConn(a1, b1, 5, 4);
		makeConn(b1, b2, 5, 2);
		makeConn(b2, b3, 5, 1);
		makeConn(a2, b3, 5, 4);
		makeConn(b1, c1, 5, 2);
		makeConn(c1, c2, 3, 1);
		makeConn(c2, c3, 4, 1);
		makeConn(c3, c4, 3, 4);
		makeConn(c4, b3, 5, 1);
		makeConn(c2, b2, 4, 3);
		makeConn(c3, b2, 4, 4);
	}
	//make a connection between two intersections
	public void makeConn(GraphNode firstNode, GraphNode secondNode, int weight, int priority){
		Connection newConn = new Connection(weight, firstNode, secondNode, priority);
		allConns.add(newConn);
		firstNode.addConn(newConn);
		secondNode.addConn(newConn);
	}
	//return a list of all trucks on the map
	public ArrayList<Truck> getTruck(){
		return allTrucks;
	}
	//set the status of the snowfall.  Amount of snowfall depends on the numbers in the constant file
	// status 1 = less major
	// status 2 = more major
	public void setStatus(int status){
		snowStatus = status;
		for(int i = 0; i < allConns.size(); i++){
			allConns.get(i).setStatus(status);
		}
	}
	//tostring method.  Prints info on the graph
	public String toString(){
		String out = "";
		// out += getGraph(out);
		// out += head.printGraph();
		for(int i = 0; i < allNodes.size(); i++){
			out += allNodes.get(i).printGraph();
		}
		return out;
	}
	//reutnr the first node in the graph
	public GraphNode getHead(){
		return head;
	}
	//get a string containing all of the paths that all of the trucks took.
	public String getPathString(){
		String out = "";
		for(int i = 0; i < allTrucks.size(); i++){
			Truck tempTruck = allTrucks.get(i);
			ArrayList<GraphNode> path;
			path = tempTruck.getPath();
			if(path.size() != 0){
				out += "" + tempTruck + "\n";
				int count = 0;
				for(int j = 0; j < path.size(); j++){
					if(j >= 2 && path.get(j) != path.get(j-2)){
						out += path.get(j) + "\n";
						count++;
					}
					if(j < 2){
						out += path.get(j) + "\n";
						count++;

					}
				}
				out += count + " roads.\n";
			}
		}
		out +=  allTrucks.size() + " trucks.";
		return out;
	}
	//add a truck to the alltrucks ArrayList
	public void addTruck(Truck inTruck){
		allTrucks.add(inTruck);
		// spawnNode.sendTruck(inTruck);
	}
	//Increment a unit of time on the map.  Increments all of the nodes and all of the connections
	//If any road needs get higher than the set cut-off limit in the constant file, it will spawn a new machine at that location.
	public void increment(){
		// for(int i = 0; i < allNodes.size(); i++){
// 
			// System.out.println(allNodes.get(i).printGraph());
		// }
		boolean firstScrapeSpawn = true;
		boolean firstSandSpawn = true;
		boolean firstRemoveSpawn = true;
		for(int i = 0; i < allNodes.size(); i++){
			// allConns.
			allNodes.get(i).increment();
		}
		for(int i = 0; i < allConns.size(); i++){
			allConns.get(i).increment();
			// if(allTrucks.size() < (allConns.size() /4)){
				if(allConns.get(i).getScrapeNeeds() > Constant.cutOff && firstScrapeSpawn){
					firstScrapeSpawn = false;
					Truck newTruck = new Scraper("varScraper");
					allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
					allTrucks.add(newTruck);
				}
				if(allConns.get(i).getSandNeeds() > Constant.cutOff && firstSandSpawn){
					// System.out.println("BUILDING A SANDER");
					firstSandSpawn = false;
					Truck newTruck = new Scraper("varSander");
					allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
					allTrucks.add(newTruck);
				}
				if(allConns.get(i).getRemoveNeeds() > Constant.cutOff && firstRemoveSpawn){
					firstRemoveSpawn = false;
					Truck newTruck = new Scraper("varRemover");
					allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
					allTrucks.add(newTruck);
				}
			}
		// }
	}
	// public void addConnection
	// public String getGraph(String)
}