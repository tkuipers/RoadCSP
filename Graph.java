import java.util.*;
public class Graph{
	private ArrayList<Connection> allConns;
	private GraphNode head;
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
	public Graph(){
		allConns = new ArrayList<Connection>();
		head = new GraphNode("a1");
		GraphNode a1 = head;
		GraphNode a2 = new GraphNode("a2");
		GraphNode b1 = new GraphNode("b1");
		GraphNode b2 = new GraphNode("b2");
		GraphNode b3 = new GraphNode("b3");
		GraphNode c1 = new GraphNode("c1");
		GraphNode c2 = new GraphNode("c2");
		GraphNode c3 = new GraphNode("c3");
		GraphNode c4 = new GraphNode("c4");
		makeConn(a1, a2, 10);
		makeConn(a1, b1, 5);
		makeConn(b1, b2, 5);
		makeConn(b2, b3, 5);
		makeConn(a2, b3, 5);
		makeConn(b1, c1, 5);
		makeConn(c1, c2, 3);
		makeConn(c2, c3, 4);
		makeConn(c3, c4, 3);
		makeConn(c4, b3, 5);
		makeConn(c2, b2, 4);
		makeConn(c3, b2, 4);
	}
	public void makeConn(GraphNode firstNode, GraphNode secondNode, int weight){
		Connection newConn = new Connection(weight, firstNode, secondNode);
		allConns.add(newConn);
		firstNode.addConn(newConn);
		secondNode.addConn(newConn);
	}
	public String toString(){
		String out = "";
		// out += getGraph(out);
		out += head.printGraph();
		return out;
	}
	public int getUntravelled(){
		int count = 0;
		for(int i = 0; i < allConns.size(); i++){
			System.out.println("Checking " + allConns.get(i));
			if(allConns.get(i).getCount() == 0){
					System.out.println("incrementing");
					count++;
			}
		}
		return count;
	}
	public GraphNode getHead(){
		return head;
	}
	// public void addConnection
	// public String getGraph(String)
}