#Snow Plow SCSP
This program is a simple Semi-Ring Constraint Satisfation problem that you can use to plan the snowplow routes in a weighted graph.

##Running the Program
To run the program, simply change the constants to the values that you want to use, compile the program, and run java Runner from your terminal

##Constant Class Values
-How often, in minutes, does a priority road need to be plowed
	-**public static final double** *firstPriPlowRate*
-How often, in minutes, does a secondary road need to be plowed
	-**public static final double** *secondPriPlowRate*
-How many minutes do you want to run the simulation for
	-**public static final int** *itCount* 
-How Many roads in advance do you want the tree search to look? 
	-**public static final int** *maxRec*
-How far over you priority do you want to go before you call in another truck
	-**public static final double** *cutOff*
-How many cm/min fall in a status 1 storm
	-**public static final double** *statOneStorm*
-How many cm/min fall in a status 2 storm
	-**public static final double** *statTwoStorm*
-How many cm's of snow do you want before you call in snow removal on a high priorit road
	-**public static final double** *highPriSnowCut*
-How many cm's of snow do you want before you call in snow removal on a low priorit road
	-**public static final double** *lowPriSnowCut*
-How many Scrapers do you want the SCSP to start with
	-**public static final int** *spawnScrape*
-How many Sanders do you want the SCSP to start with
	-**public static final int** *spawnSand*
-How many Removers do you want the SCSP to start with
	-**public static final int** *spawnRemove*
