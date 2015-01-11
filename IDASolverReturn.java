
public class IDASolverReturn {
	int moves,exploredNodes;
	double duration;
	
	IDASolverReturn(){
	}
	
	public String toString(){
		return "Moves = " + moves + ", Explored nodes = " + exploredNodes + ", Time = " + duration;
	}
}
