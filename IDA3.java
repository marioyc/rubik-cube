import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class IDA3 extends IDASolverInterface{
	int dist[],edges_0_dist[],edges_1_dist[];
	
	public String getAlgorithm(){
		return "IDA3";
	}
	
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
		
		try {
			br = new BufferedReader(new FileReader("precalc/edges_0_in.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		edges_0_dist = new int[42577920];
		
		for(int i = 0;i < 42577920;++i){
			try {
				String line = br.readLine();
				if(line == null) break;
				StringTokenizer st = new StringTokenizer(line);
				
				int id = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				edges_0_dist[id] = d;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			br = new BufferedReader(new FileReader("precalc/edges_1_in.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		edges_1_dist = new int[42577920];
		
		for(int i = 0;i < 42577920;++i){
			try {
				String line = br.readLine();
				if(line == null) break;
				StringTokenizer st = new StringTokenizer(line);
				
				int id = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				edges_1_dist[id] = d;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	int estimate(Cube C) throws Exception{
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
		
		int ret = dist[aux.encode()];
		
		EdgesState aux2 = new EdgesState();
		
		for(int i = 0;i < 6;++i){
			for(int j = 0;j < 12;++j){
				for(int k = 0;k < 2;++k){
					for(int l = 0;l < 2;++l){
						if(C.origin[ Cube.edgePairs[j][l] ] == Cube.edgePairs[i][k]){
							aux2.pos[i][k] = Cube.edgePairs[j][l];
							break;
						}
					}
				}
			}
		}
		
		if(edges_0_dist[aux2.encode(0)] == -1)
			throw new Exception("edges_0_dist index not found for : " + C);
		ret = Math.max(ret, edges_0_dist[aux2.encode(0)]);
		
		EdgesState aux3 = new EdgesState();
		
		for(int i = 6;i < 12;++i){
			for(int j = 0;j < 12;++j){
				for(int k = 0;k < 2;++k){
					for(int l = 0;l < 2;++l){
						if(C.origin[ Cube.edgePairs[j][l] ] == Cube.edgePairs[i][k]){
							aux3.pos[i - 6][k] = Cube.edgePairs[j][l];
							break;
						}
					}
				}
			}
		}
		
		if(edges_1_dist[aux3.encode(0)] == -1)
			throw new Exception("edges_1_dist index not found for : " + C);
		ret = Math.max(ret, edges_1_dist[aux3.encode(0)]);
		
		return ret;
	}
}