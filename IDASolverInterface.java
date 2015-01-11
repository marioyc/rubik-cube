public abstract class IDASolverInterface {
	final int MAX_DEPTH = 30;
	final int INF = 50;
	
	public int solve(Cube C){
		int i = 0;
		
		while(i <= MAX_DEPTH){
			//System.out.println("i = " + i);
			if(search(0,i,C))
				return i;
			++i;
		}
		
		return -1;
	}
	
	boolean search(int count, int maxMoves, Cube C){
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
	
	abstract int estimate(Cube C);
}
