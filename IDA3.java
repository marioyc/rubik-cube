import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class IDA3 extends IDASolverInterface{
	int dist[];
	
	IDA3(){
		dist = new int[88179840];
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("precalc/corners_in.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0;i < 88179840;++i){
			try {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int id = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				dist[id] = d;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	int estimate(Cube C){
		CornersState aux = new CornersState();
		
		for(int i = 0;i < 7;++i){
			for(int j = 0;j < 8;++j){
				for(int k = 0;k < 3;++k){
					for(int l = 0;l < 3;++l){
						if(C.origin[ Cube.cornerTriples[j][l] ] == Cube.cornerTriples[i][k]){
							aux.pos[i][k] = Cube.cornerTriples[j][l];
							break;
						}
					}
				}
			}
		}
		
		return dist[aux.encode()];
	}
}