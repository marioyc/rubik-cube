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
		
		String s = C.toString();
		
		Cube C2 = new Cube();
		C2.initFromString(s);
		
		IDASolverReturn ret1 = solver1.solve(C);
		System.out.println(solver1.getAlgorithm() + " : " + ret1);
		
		IDASolverReturn ret2 = solver2.solve(C2);
		System.out.println(solver2.getAlgorithm() + " : " + ret2);
		System.out.println("-------------------------------------------\n");
		
		if(ret1.moves == -1 || ret1.moves > moves)
			throw new Exception("Wrong answer : " + solver1.getAlgorithm() + "for " + s);
		
		if(ret2.moves == -1 || ret2.moves > moves)
			throw new Exception("Wrong answer : " + solver2.getAlgorithm() + "for " + s);
		
		if(ret1.moves != ret2.moves)
			throw new Exception("Different answers : " + solver1.getAlgorithm() + " and " + solver2.getAlgorithm() + " for " + s + ", " + moves + " random moves.");
	}
	
	static void compareIDA(IDASolverInterface solver1, IDASolverInterface solver2, String s) throws Exception{
		Cube C = new Cube();
		C.initFromString(s);
		
		Cube C2 = new Cube();
		C2.initFromString(s);
		
		IDASolverReturn ret1 = solver1.solve(C);
		System.out.println(solver1.getAlgorithm() + " : " + ret1);
		
		IDASolverReturn ret2 = solver2.solve(C2);
		System.out.println(solver2.getAlgorithm() + " : " + ret2);
		System.out.println("-------------------------------------------\n");
		
		if(ret1.moves != ret2.moves)
			throw new Exception("Different answers : " + solver1.getAlgorithm() + " and " + solver2.getAlgorithm() + " for " + s);
	}
	
    public static void main(String args[]) throws Exception{
    	// uncomment to generate pattern databases
    	
    	/*try {
    		HeuristicsGenerator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	
    	IDA1 solver1 = new IDA1();
    	IDA2 solver2 = new IDA2();
    	IDA3 solver3 = new IDA3();
    	
    	// uncomment and change to test a solver
    	/*for(int i = 0;i < 20;++i){
    		IDASolverReturn ret = testIDA(solver3,13);
    		System.out.println(ret);
    	}*/
    	
    	// uncommment and change to compare two solvers
    	
    	/*for(int moves = 1;moves <= 6;++moves)
	    	for(int i = 0;i < 200;++i)
		    	compareIDA(solver2,solver3,moves);
    	*/
    	
    	// uncomment and change to test for a particular cube
    	//compareIDA(solver2,solver3,"YGBYGBYGBRRRRRRRRRGBWGBWGBWOOOOOOOOOGGGWWWYYYWWWYYYBBB");
    	
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
