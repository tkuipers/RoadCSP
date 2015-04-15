import java.util.*;
//The class to represent the map of the "world"
public class Graph{

	private ArrayList<Connection> allConns;
	private GraphNode head;
	private ArrayList<GraphNode> allNodes;
	private ArrayList<Truck> allTrucks;
	private int snowStatus;
	/* TYPE 1
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

		TYPE 2
		a1-------------------10--------------------a2
		|\										   /|
		| \										  / |
		|  \									 /	|
		|	\4									/4	|
		|	 \								   /	|
		|	  \								  /		|
		|	   \							 /		|
		|		b1-----------6--------------b2		|
		|		|							|		|
		10		6							6		10
		|		|							|		|
		|		|							|		|
		|		c2-------------6------------c2		|
		|	  /	 							 \		|
		|	 /								  \		|
		|	/								   \	|
		|  /4									\4	|
		| /										 \	|
		|/										  \	|
		d1------------------10---------------------d2

		TYPE 3

				a1
				/|\
			   /3|\	
			  /	 | \
			 / 	b2	\
		10	/	/\	 \ 10
		   / 6 /  \ 6 \
		  /   /    \   \
		 /   c1----c2   \
		/ 3/     6    \3 \ 
	   d1----------------d2
				10

		TYPE 4
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
		|   3        4        3	\
	   4|								\c4 to d2, 4
		|										  \
		d1-------------------10--------------------d2
		|\										   /|
		| \										  / |
		|  \									 /	|
		|	\4									/4	|
		|	 \								   /	|
		|	  \								  /		|
		|	   \							 /		|
		|		e1-----------6--------------e2		|
		|		|							|		|
		10		6							6		10
		|		|							|		|
		|		|							|		|
		|		f2-------------6------------f2		|
		|	  /	 							 \		|
		|	 /								  \		|
		|	/								   \	|
		|  /4									\4	|
		| /										 \	|
		|/										  \	|
		g1------------------10---------------------g2
		|									/
		|					/h1 to g2, weight of3
		|			/
		|		h1
		|		/|\
		|	   /3|\	
		|	  /	 | \
		|	 / 	i2	\
		| 10/	/\	 \ 10
		|  / 6 /  \ 6 \
		| /   /    \   \
		|/   j1----j2   \
		/ 3/     6    \3 \ 
	   k1----------------k2
				10
	*/
	//constructor
	public Graph(int type){
		allConns = new ArrayList<Connection>();

		allNodes = new ArrayList<GraphNode>();

		allTrucks = new ArrayList<Truck>();


		snowStatus = 0;
		if(type == 1){
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
		else if(type == 2){
			head = new GraphNode("a1", this);
			GraphNode a1 = head;
			GraphNode a2 = new GraphNode("a2", this);
			GraphNode b1 = new GraphNode("b1", this);
			GraphNode b2 = new GraphNode("b2", this);
			GraphNode c1 = new GraphNode("c1", this);
			GraphNode c2 = new GraphNode("c2", this);
			GraphNode d1 = new GraphNode("d1", this);
			GraphNode d2 = new GraphNode("d2", this);
	
			allNodes.add(a1);
			allNodes.add(a2);
			allNodes.add(b1);
			allNodes.add(b2);
			allNodes.add(c1);
			allNodes.add(c2);
			allNodes.add(d1);
			allNodes.add(d2);
	
			makeConn(a1, a2, 10, 1);
			makeConn(a1, d1, 10, 1);
			makeConn(a1, b1, 4, 1);
			makeConn(a2, b2, 4, 1);
			makeConn(a2, d2, 10, 1);
			makeConn(b1, c1, 6, 1);
			makeConn(b1, b2, 6, 1);
			makeConn(b2, c2, 3, 1);
			makeConn(c1, c2, 6, 1);
			makeConn(c1, d1, 4, 1);
			makeConn(c2, d2, 4, 1);
			makeConn(d1, d2, 10, 1);
		}
		else if(type == 3){
			head = new GraphNode("a1", this);
			GraphNode a1 = head;
			GraphNode b1 = new GraphNode("b1", this);
			GraphNode c1 = new GraphNode("c1", this);
			GraphNode c2 = new GraphNode("c2", this);
			GraphNode d1 = new GraphNode("d1", this);
			GraphNode d2 = new GraphNode("d2", this);
	
			allNodes.add(a1);
			allNodes.add(b1);
			allNodes.add(c1);
			allNodes.add(c2);
			allNodes.add(d1);
			allNodes.add(d2);
	
			makeConn(a1, d1, 10, 1);
			makeConn(a1, d2, 10, 1);
			makeConn(a1, b1, 3, 1);
			makeConn(b1, c1, 6, 1);
			makeConn(b1, c2, 6, 1);
			makeConn(c1, c2, 6, 1);
			makeConn(c1, d1, 3, 1);
			makeConn(c2, d2, 3, 1);
			makeConn(d1, d2, 10, 1);
		}
		else if(type == 4){
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

			a1 = new GraphNode("d1", this);
			a2 = new GraphNode("d2", this);
			makeConn(a1, c1, 4, 2);
			makeConn(a2, c4, 4, 2);
			b1 = new GraphNode("e1", this);
			b2 = new GraphNode("e2", this);
			c1 = new GraphNode("f1", this);
			c2 = new GraphNode("f2", this);
			GraphNode d1 = new GraphNode("g1", this);
			GraphNode d2 = new GraphNode("g2", this);
	
			allNodes.add(a1);
			allNodes.add(a2);
			allNodes.add(b1);
			allNodes.add(b2);
			allNodes.add(c1);
			allNodes.add(c2);
			allNodes.add(d1);
			allNodes.add(d2);
	
			makeConn(a1, a2, 10, 1);
			makeConn(a1, d1, 10, 1);
			makeConn(a1, b1, 4, 1);
			makeConn(a2, b2, 4, 1);
			makeConn(a2, d2, 10, 1);
			makeConn(b1, c1, 6, 1);
			makeConn(b1, b2, 6, 1);
			makeConn(b2, c2, 3, 1);
			makeConn(c1, c2, 6, 1);
			makeConn(c1, d1, 4, 1);
			makeConn(c2, d2, 4, 1);
			makeConn(d1, d2, 10, 1);

			a1 = new GraphNode("h1", this);
			makeConn(a1, d1, 3, 1);
			b1 = new GraphNode("i1", this);
			c1 = new GraphNode("j1", this);
			c2 = new GraphNode("j2", this);
			d1 = new GraphNode("k1", this);
			makeConn(d1, d2, 3, 1);
			d2 = new GraphNode("k2", this);
	
			allNodes.add(a1);
			allNodes.add(b1);
			allNodes.add(c1);
			allNodes.add(c2);
			allNodes.add(d1);
			allNodes.add(d2);
	
			makeConn(a1, d1, 10, 1);
			makeConn(a1, d2, 10, 1);
			makeConn(a1, b1, 3, 1);
			makeConn(b1, c1, 6, 1);
			makeConn(b1, c2, 6, 1);
			makeConn(c1, c2, 6, 1);
			makeConn(c1, d1, 3, 1);
			makeConn(c2, d2, 3, 1);
			makeConn(d1, d2, 10, 1);
		}
		else{
			System.out.println("You did not enter a valid choice");
			System.exit(0);
		}
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
		int truckCount = 0;
		for(int i = 0; i < allTrucks.size(); i++){
			Truck tempTruck = allTrucks.get(i);
			ArrayList<GraphNode> path;
			path = tempTruck.getPath();
			if(path.size() != 0){
				truckCount++;
				out += "" + tempTruck + "\n";
				int count = 0;
				for(int j = 0; j < path.size(); j++){
					// if(j >= 2 && path.get(j) != path.get(j-2)){
						// if(path.get(j) != path.get(j-4)){
							out += path.get(j) + "\n";
							count++;
						// }
					// }
					// if(j < 2){
						// out += path.get(j) + "\n";
						// count++;

					// }
				}
				out += count + " roads.\n";
			}
		}
		out +=  truckCount + " trucks.";
		return out;
	}
	//add a truck to the alltrucks ArrayList
	public void addTruck(Truck inTruck){
		allTrucks.add(inTruck);
		// spawnNode.sendTruck(inTruck);
	}
	//Increment a unit of time on the map.  Increments all of the nodes and all of the connections
	//If any road needs get higher than the set cut-off limit in the constant file, it will spawn a new machine at that location.
	public void increment(int itNum){
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
					System.out.println("ITERATION: " + itNum + " BUILDING A SCRAPER FOR NODE " + allConns.get(i).toShortString());
					firstScrapeSpawn = false;
					if(allConns.get(i).getSandNeeds() > Constant.cutOff){
						Truck newTruck = new Sander("varSander", itNum);
						allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
						allTrucks.add(newTruck);
					}
					else{
						Truck newTruck = new Scraper("varScraper", itNum);
						allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
						allTrucks.add(newTruck);
					}
					// System.out.println("\n\n\nPRINTING GRAPH");
					// System.out.println(this);
					// System.exit(0);
				}
				if(allConns.get(i).getSandNeeds() > Constant.cutOff && firstSandSpawn){
					System.out.println("ITERATION: " + itNum + " BUILDING A SANDER FOR NODE " + allConns.get(i).toShortString());
					firstSandSpawn = false;
					Truck newTruck = new Sander("varSander", itNum);
					allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
					allTrucks.add(newTruck);
					// System.exit(0);
				}
				if(allConns.get(i).getRemoveNeeds() > Constant.cutOff && firstRemoveSpawn){
					System.out.println("ITERATION: " + itNum + " BUILDING A REMOVER FOR NODE " + allConns.get(i).toShortString());
					firstRemoveSpawn = false;
					Truck newTruck = new Remover("varRemover", itNum);
					allConns.get(i).sendTruck(newTruck, allConns.get(i).getTo());
					allTrucks.add(newTruck);
					// System.exit(0);
				}
			}
		// }
	}
	// public void addConnection
	// public String getGraph(String)
}