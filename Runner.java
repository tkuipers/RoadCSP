import java.util.*;
//the run file for the entire program
public class Runner{
	public static void main(String[] args){
		// System.out.println(args[0]);
		int graphNum = -1;
		if(args[0] != null){
			graphNum = Integer.parseInt(args[0]);
		}
		else{
			System.out.println("You must enter which graph you are attempting to run the csp on.");
			System.exit(0);
		}
		System.out.println("Creating a graph of type " + graphNum);
		Graph graph = new Graph(graphNum);

		for(int i = 0; i < Constant.spawnScrape; i++){
			Truck truck1 = new Scraper("startScraper", 0);
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);
		}
		for(int i = 0; i < Constant.spawnSand; i++){
			Truck truck1 = new Sander("startSander", 0);
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);
		}
		for(int i = 0; i < Constant.spawnRemove; i++){
			Truck truck1 = new Remover("startRemover", 0);
			graph.getHead().sendTruck(truck1);
			graph.addTruck(truck1);	
		}


		graph.setStatus(1);
		for(int i = 0; i < Constant.itCount; i++){
			graph.increment(i);
		}

		graph.setStatus(0);
		for(int i = 0; i < Constant.itCount; i++){
			graph.increment(i);
		}

		graph.setStatus(2);
		for(int i = 0; i < Constant.itCount; i++){
			graph.increment(i);
		}
		System.out.println(graph.getPathString());
	}
}