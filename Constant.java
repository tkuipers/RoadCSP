//A Class containing all of the contants used in the program
public class Constant{
	//How often, in minutes, does a priority road need to be plowed
	public static final double firstPriPlowRate = 240; 
	//How often, in minutes, does a secondary road need to be plowed
	public static final double secondPriPlowRate = 480; 
	//How many minutes do you want to run the simulation for
	public static final int itCount = 150; 
	//How Many roads in advance do you want the tree search to look? 
	public static final int maxRec = 2; 
	//How far over you priority do you want to go before you call in another truck
	public static final double cutOff = 01.5; 
	//How many cm/min fall in a status 1 storm
	public static final double statOneStorm = 0.1;
	//How many cm/min fall in a status 2 storm
	public static final double statTwoStorm = 0.2;
	//How many cm's of snow do you want before you call in snow removal on a high priority road
	public static final double highPriSnowCut = 50.0;
	//How many cm's of snow do you want before you call in snow removal on a low priority road
	public static final double lowPriSnowCut = 100.0;
	//How many Scrapers do you want the SCSP to start with
	public static final int spawnScrape = 1;
	//How many Sanders do you want the SCSP to start with
	public static final int spawnSand = 1;
	//How many Removers do you want the SCSP to start with
	public static final int spawnRemove = 1;
}