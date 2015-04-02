import java.util.*;
public class GraphNode{
	private ArrayList<GraphNode> adjacent;
	private String id;
	private ArrayList<Integer> weights;
	private boolean visited;
	public GraphNode(String id){
		adjacent = new ArrayList<GraphNode>();
		weights = new ArrayList<Integer>();
		this.id = id;
		visited = false;
	}
	public void addConn(GraphNode neighbor, int weight){
		adjacent.add(neighbor);
		weights.add(weight);
	}
	public String toString(){
		String out = "";
		out += id;
		return out;
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
		visited = true;
		String out = "";
		// out += id;
		out += "\n" +this + " is connected to: \n\t";
		for(int  j = 0; j < adjacent.size(); j++){
			out += adjacent.get(j) + " weight: " + weights.get(j) + "\n\t";
		} 
		for(int i = 0; i < adjacent.size(); i++){
			if(!adjacent.get(i).getVisited()){
				// adjacent.get(i).setVisited(true);
				out += adjacent.get(i).printGraph();
			}
		}
		return out;
	}
}