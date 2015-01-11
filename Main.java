public class Main{
	// Question 4, <= 5 moves OK
	
	// BGOOGYBRRYYWRRWWBGBBRBBWRWGWWYOOGOYYRGGRWOGGOBYYBYROOW 5
	// OBYYGGYGOBROYROBOOBBWBBWWBWROGRORRORYWBRWYRGYWGGWYWGYG 6
	// GRRYGYWOOGGYOROWBBOOYWBWRRBBRYBOGWRGOGRYWBYWBGYWGYWRBO 6
	// RRGGGBRRBOBGRROOOGRBBGBBRGGOOBOOROGBYWWYWWYYWYYYWYWWYW 7
	// YWYYGGYBBGBWWRWWOBGGWBBYOOWOGGROBBOBRYGRWRORROGYOYYRWR 7
	// OYBRGRRGRYRWBRYBRYROOGBBGBYGGBWOWOYWWWWOWWOYBYGROYOGBG 7
	// GGWGGYYYOBRGORGYOWOBYOBYBRWBOWBOWGWRRWRRWROWYBBRGYYGBO 7
	static int testIDA(IDASolverInterface solver, int moves){
		Cube C = new Cube();
		C.mixCube(moves);
		System.out.println(C);
		
		int ret = solver.solve(C);
		return ret;
	}
	
	
	static void compareIDA(IDASolverInterface solver1, IDASolverInterface solver2, int moves){
		Cube C = new Cube();
		C.mixCube(moves);
		
		Cube C2 = new Cube();
		
		try{
			C2.initFromString(C.toString());
		}catch(Exception e){
			System.out.println(":(");
		}
		
		long startTime = System.nanoTime();
		int ret1 = solver1.solve(C);
		long endTime = System.nanoTime();
		double duration = (endTime - startTime) / 1000000.0;
		System.out.println("IDA1 : " + ret1);
		System.out.println("duration1 = " + duration + " milliseconds");
		
		startTime = System.nanoTime();
		int ret2 = solver2.solve(C2);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000.0;
		System.out.println("IDA2 : " + ret2);
		System.out.println("duration2 = " + duration + " milliseconds");
	}
	
    public static void main(String args[]){
    	IDA1 solver1 = new IDA1();
    	IDA2 solver2 = new IDA2();
    	
    	for(int i = 0;i < 15;++i)
	    	compareIDA(solver1,solver2,7);
    	
    	/*
		double total[] = new double[10];
		int cont[] = new int[10];
		
		for(int i = 0;i <= 8;++i) total[i] = 0;
		
		for(int i = 1;i <= 7;++i){
	    	for(int it = 0;it < 30;++it){
	    		long startTime = System.nanoTime();
	    		
	    		int ret = testIDA(solver1,i);
	    		
	    		System.out.println("it = " + it + " : " + ret);
	    		
	    		long endTime = System.nanoTime();
	    		double duration = (endTime - startTime) / 1000000.0;
	    		
	    		System.out.println("duration = " + duration + " milliseconds");
	
	    		total[ret] += duration;
	    		cont[ret] += 1;
	    	}
		}
    	
    	for(int i = 0;i <= 8;++i)
    		System.out.println("\ni = " + i + ", average duration = " + total[i] / cont[i] + " milliseconds\n");
    	*/
    }
    
    /*
		i = 1, average duration = 1.175077159090909 milliseconds
		
		i = 2, average duration = 2.9136991250000013 milliseconds
		
		i = 3, average duration = 12.997581391304347 milliseconds
		
		i = 4, average duration = 37.02824552777778 milliseconds
		
		i = 5, average duration = 652.3021389000002 milliseconds
		
		i = 6, average duration = 7947.991147769231 milliseconds
		
		i = 7, average duration = 167997.96886766667 milliseconds
      */
}
