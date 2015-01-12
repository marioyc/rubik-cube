public abstract class IDASolverInterface {
	final int MAX_DEPTH = 20;
	final int INF = 50;
	int exploredNodes;
	
	public abstract String getAlgorithm();
	
	public IDASolverReturn solve(Cube C)  throws Exception{
		IDASolverReturn ret = new IDASolverReturn();
		ret.moves = -1;
		
		int i = 0;
		exploredNodes = 0;
		
		long startTime = System.nanoTime();
		
		while(i <= MAX_DEPTH){
			//System.out.println("i = " + i);
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
		return ret;
	}
	
	boolean search(int count, int maxMoves, Cube C) throws Exception{
		++exploredNodes;
		if(count + estimate(C) > maxMoves) return false;
		if(count == maxMoves) return C.check();
		
		for(int i = 1;i <= 3;++i){
			C.rotateFront(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateFront(1);

		for(int i = 1;i <= 3;++i){
			C.rotateBack(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateBack(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateLeft(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateLeft(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateRight(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateRight(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateUp(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateUp(1);
		
		for(int i = 1;i <= 3;++i){
			C.rotateDown(1);
			
			if(search(count + 1,maxMoves,C))
				return true;
		}
		C.rotateDown(1);
		
		return false;
	}
	
	abstract int estimate(Cube C) throws Exception;
}
