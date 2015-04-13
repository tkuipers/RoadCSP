public class Runner{
	public static void main(String[] args){
		Graph graph = new Graph();
		Truck truck1 = new Sander("truck1");
		// graph.getHead().sendTruck(truck1);
		graph.addTruck(truck1);
		graph.getHead().sendTruck(truck1);
		graph.setStatus(0);
		graph.setStatus(1);
		for(int i = 0; i < Constant.itCount; i++){
			// System.out.println("\n\nIteration: " + i + "\n" + graph);
			graph.increment();
		}
		// System.out.println("TRUCK PATH");
		System.out.println(graph.getPathString());
		// System.out.println("The ones with an odd number of connections");
	}
}