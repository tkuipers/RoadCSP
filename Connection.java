public class Connection{
	private int weight;
	private GraphNode from;
	private GraphNode to;
	private int travelCount;
	public Connection(int weight, GraphNode from, GraphNode to){
		this.weight = weight;
		this.to = to;
		this.from = from;
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
	public String toString(){
		return from + " to " + to;
	}

}