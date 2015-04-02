public class Graph{
	private GraphNode head;
	public Graph(){
		head = new GraphNode("a1");
		GraphNode tail = new GraphNode("a2");
		GraphNode other = new GraphNode("b1");
		GraphNode othero = new GraphNode("b3");
		makeConn(head, tail, 3);
		makeConn(head, other, 4);
		makeConn(tail, other, 8);
		makeConn(tail, othero, 8);


	}
	public void makeConn(GraphNode firstNode, GraphNode secondNode, int weight){
		firstNode.addConn(secondNode, weight);
		secondNode.addConn(firstNode, weight);
	}
	public String toString(){
		String out = "";
		// out += getGraph(out);
		out += head.printGraph();
		return out;
	}
	// public String getGraph(String)
}