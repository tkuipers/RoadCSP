import java.util.*;
public class Runner{
	public static void main(String[] args){
		Graph graph = new Graph();
		// Truck truck1 = new Sander("truck1");
		// graph.getHead().sendTruck(truck1);
		// graph.addTruck(truck1);
		// graph.getHead().sendTruck(truck1);
		graph.setStatus(0);
		graph.setStatus(1);
		for(int i = 0; i < Constant.spawnScrape; i++){
			Truck truck1 = new Scraper("startScraper");
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);
			
		}
		for(int i = 0; i < Constant.spawnSand; i++){
			Truck truck1 = new Sander("startSander");
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);
			
		}
		for(int i = 0; i < Constant.spawnRemove; i++){
			Truck truck1 = new Remover("startRemover");
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);
			
		}


		for(int i = 0; i < Constant.itCount; i++){
			// System.out.println("\n\nIteration: " + i + "\n" + graph);
			// System.out.println("AT ITERATION " + i + " the bottom truck is " + )
			graph.increment();
		}
		// System.out.println("TRUCK PATH");
		System.out.println(graph.getPathString());
		// System.out.println("The ones with an odd number of connections");
	}
}