public abstract class IDASolverInterface {
	final int MAX_DEPTH = 20;
	final int INF = 50;
	int exploredNodes;
	String solution;
	
	public abstract String getAlgorithm();
	
	public IDASolverReturn solve(Cube C)  throws Exception{
		IDASolverReturn ret = new IDASolverReturn();
		ret.moves = -1;
		
		int i = 0;
		exploredNodes = 0;
		solution = new String();
		
		long startTime = System.nanoTime();
		
		while(i <= MAX_DEPTH){
			if(search(0,i,C)){
				ret.moves = i;
				break;
			}
			++i;
		}
		
		long endTime = System.nanoTime();
		double duration = (endTime - startTime) / 1000000.0;
		
		ret.exploredNodes = exploredNodes;
		ret.duration = duration;
		ret.solution = solution;
		return ret;
	}
	
	boolean search(int count, int maxMoves, Cube C) throws Exception{
		++exploredNodes;
		if(count + estimate(C) > maxMoves) return false;
		if(count == maxMoves) return C.check();
		
		for(int i = 1;i <= 3;++i){
			C.rotateFront(1);
			solution = solution + i + "F  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateFront(1);

		for(int i = 1;i <= 3;++i){
			C.rotateBack(1);
			solution = solution + i + "B  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateBack(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateLeft(1);
			solution = solution + i + "L  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateLeft(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateRight(1);
			solution = solution + i + "R  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateRight(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateUp(1);
			solution = solution + i + "U  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateUp(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateDown(1);
			solution = solution + i + "D  ";
			
			if(search(count + 1,maxMoves,C))
				return true;
			
			solution = solution.substring(0, solution.length() - 4);
		}
		C.rotateDown(1);
		
		return false;
	}
	
	abstract int estimate(Cube C) throws Exception;
}
