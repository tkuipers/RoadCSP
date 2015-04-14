import java.util.*;
//the run file for the entire program
public class Runner{
	public static void main(String[] args){
		Graph graph = new Graph(1);

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
			// System.out.println("FIRST ROUND.\nITERATION: " + i + "\n" + graph);
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