import java.util.*;
public class Evaluator{
	//CONDITIONS
	//All Roads must be travelled
	//More to come
	private Graph graph;
	ArrayList<GraphNode> out;
	public Evaluator(Graph inGraph){
		graph = inGraph;
		out = traverseAll(graph.getHead(), new ArrayList<GraphNode>());
		

	}
	public String toString(){
		String string = "";
		if(out == null){
			string += "PROBLEM";
		}
		else{
			for(int i = 0; i < out.size(); i++){
				string += out.get(i) + "\n";
			}
		}
		return string;

	}
	public ArrayList<GraphNode> traverseAll(GraphNode input, ArrayList<GraphNode> curList){
		ArrayList<Connection> adjacent = input.getAdjacent();
		if(curList.size()==0){
			//CHECK FOR NULL CASE
			if(adjacent.size() == 0){
				curList.add(input);
				return curList;
			}
			//START INITIAL LOOP
			else{
				curList.add(input);
				GraphNode nextNode = adjacent.get(0).getOther(input);
				adjacent.get(0).upCount();
				System.out.println("Travelling from " + input + " to " + nextNode);
				return traverseAll(nextNode, curList);
			}
		}
		else{
			System.out.println("CHecking if equal");
			//COMPARE IF THEY ARE EQUAL
			if(input == curList.get(0)){
			//IF TRUE, RETURN LIST
				return curList;
			}
			else{
				//CHECK FOR LOWEST TRAVEL COUNT ON A PATH.  TAKE THAT PATH.
				System.out.println("ELSE");
				curList.add(input);
				int lowest = Integer.MAX_VALUE;
				int address = Integer.MAX_VALUE;
				for(int i = 0; i < adjacent.size(); i++){

					//CHECK IF IT TRAVELS TO THE BEGINNING, IF IT DOES, MAKE SURE ALL OTHER PATHS HAVE BEEN TRAVELLED FIRST
					if(lowest > adjacent.get(i).getCount()){
						//CHECK IF OTHER IS BEGINNING
						boolean found = false;
						if(adjacent.get(i).getOther(input) == curList.get(0)){
							//TRY OTHER PATHS
							for(int j = 0; j < adjacent.size(); j++){
								if(graph.getUntravelled() != 0){
									// System.out.println("" + adjacent.get(j).getCount());
									lowest = adjacent.get(j).getCount();
									address = j;
									found = true;
									// break;
									// continue;
								}
							}
						}
						if(!found){
							//GO THE WAY TOWARD THE BEGINNING
							lowest = adjacent.get(i).getCount();
							address = i;
						}
					}
				}
				GraphNode nextNode = adjacent.get(address).getOther(input);
				adjacent.get(address).upCount();
				System.out.println("Travelling from " + input + " to " + nextNode);
				return traverseAll(nextNode, curList);

			}
		}

	}

}