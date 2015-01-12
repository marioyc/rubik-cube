public class Main{
	static IDASolverReturn testIDA(IDASolverInterface solver, int moves)  throws Exception{
		Cube C = new Cube();
		C.mixCube(moves);
		System.out.println(C);
		
		return solver.solve(C);
	}
	
	static void compareIDA(IDASolverInterface solver1, IDASolverInterface solver2, int moves)  throws Exception{
		Cube C = new Cube();
		C.mixCube(moves);
		
		Cube C2 = new Cube();
		
		C2.initFromString(C.toString());
		
		IDASolverReturn ret1 = solver1.solve(C);
		System.out.println(solver1.getAlgorithm() + " : " + ret1);
		
		IDASolverReturn ret2 = solver2.solve(C2);
		System.out.println(solver2.getAlgorithm() + " : " + ret2);
		System.out.println("-------------------------------------------\n");
		
		if(ret1.moves != ret2.moves)
			throw new Exception("Different answers : " + solver1.getAlgorithm() + " and " + solver2.getAlgorithm() + " for " + C);
	}
	
    public static void main(String args[]) throws Exception{
    	// uncomment to generate pattern databases
    	/*HeuristicsGenerator G = new HeuristicsGenerator();
    	
    	try {
			G.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	IDA1 solver1 = new IDA1();
    	IDA2 solver2 = new IDA2();
    	IDA3 solver3 = new IDA3();
    	
    	// uncomment and change to test a solver
    	/*for(int i = 0;i < 100;++i){
    		IDASolverReturn ret = testIDA(solver3,9);
    		System.out.println(ret);
    	}*/
    	
    	// uncommment and change to compare two solvers
    	/*
    	for(int i = 0;i < 100;++i)
	    	compareIDA(solver2,solver3,3);
    	*/
    	
    	// measure average time
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
}
