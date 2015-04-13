import java.util.*;
public class Evaluator{
	//CONDITIONS
	//All Roads must be travelled
	//More to come
	private Graph graph;
	ArrayList<GraphNode> out;
	public Evaluator(Graph inGraph){
		graph = inGraph;
		// out = traverseAll(graph.getHead(), new ArrayList<GraphNode>());
		

	}
	public String toString(){
		String string = "";
		// if(out == null){
			// string += "PROBLEM";
		// }
		// else{
			// for(int i = 0; i < out.size(); i++){
				// string += out.get(i) + "\n";
			// }
		// }
		return string;

	}
	

}