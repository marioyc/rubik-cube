public class IDASolverReturn {
	int moves,exploredNodes;
	double duration;
	String solution;
	
	IDASolverReturn(){
	}
	
	public String toString(){
		return "Moves = " + moves + ", Explored nodes = " + exploredNodes + ", Time = " + duration + "\nSolution = " + solution;
	}
}
