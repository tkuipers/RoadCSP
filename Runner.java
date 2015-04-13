import java.util.*;
//the run file for the entire program
public class Runner{
	public static void main(String[] args){
		Graph graph = new Graph();
		// Truck truck1 = new Sander("truck1");
		// graph.getHead().sendTruck(truck1);
		// graph.addTruck(truck1);
		// graph.getHead().sendTruck(truck1);
		// graph.setStatus(0);
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
			// System.out.println("\n\n\n\n\n\nITEREATION " + i + ":\n" + graph);
			graph.increment();
		}
		System.out.println("STOPPED SNOWING");
		// graph.setStatus(0);
		// for(int i = 0; i < Constant.itCount; i++){
			// graph.increment();
		// }
		// graph.setStatus(1);
		// for(int i = 0; i < Constant.itCount; i++){
			// graph.increment();
		// }
		// System.out.println("TRUCK PATH");
		System.out.println(graph.getPathString());
		// System.out.println("The ones with an odd number of connections");
	}
}