public class Main{
	static boolean search(int count, int maxMoves, Cube C){
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
	
	// Question 4, <= 5 moves OK
	static int IDA1(int moves){
		Cube c = new Cube();
		c.mixCube(moves);
		
		int i = 0;
		
		while(i <= moves){
			if(search(0,i,c))
				return i;
			++i;
		}
		
		return -1;
	}
	
    public static void main(String args[]){
		double total[] = new double[10];
		int cont[] = new int[10];
		
		for(int i = 0;i <= 8;++i) total[i] = 0;
		
		for(int i = 1;i <= 7;++i){
	    	for(int it = 0;it < 30;++it){
	    		long startTime = System.nanoTime();
	    		
	    		int ret = IDA1(i);
	    		
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
