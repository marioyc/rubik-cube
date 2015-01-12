
public class EdgesState {
	int pos[][];
	
	EdgesState(){
		pos = new int[6][2];
	}
	
	public String toString(){
		String ret = new String();
		
		for(int i = 0;i < 6;++i)
			for(int j = 0;j < 2;++j)
				ret = ret + pos[i][j] + " ";
		
		return ret;
	}
	
	public int encode(int group){
		int aux1[] = new int[6];
		int aux2 = 0;
		
		for(int i = 0;i < 6;++i){
			int x = pos[i][0],y = pos[i][1];
			
			for(int j = 0;j < 12;++j){
				int a = Cube.edgePairs[j][0];
				
				if(x == a){
					aux1[i] = j;
					aux2 = aux2 * 2;
				}else if(y == a){
					aux1[i] = j;
					aux2 = aux2 * 2 + 1;
				}
			}
		}
		
		boolean used[] = new boolean[12];
		int ret = 0;
		
		for(int i = 0;i < 6;++i){
			int cont = 0;
			
			for(int j = 0;j < aux1[i];++j)
				if(!used[j])
					++cont;
			
			used[ aux1[i] ] = true;
			aux1[i] = cont;
		}
		
		int count = 1;
		
		for(int i = 1;i <= 12;++i) count *= i;
		for(int i = 1;i <= 6;++i) count /= i;
		
		for(int i = 0;i < 6;++i){
			count = count / (12 - i);
			
			ret += count * aux1[i];
		}
		
		for(int i = 0;i < 6;++i)
			ret *= 2;
		ret += aux2;
		
		return ret;
	}
	
	static EdgesState decode(int coded){
		int aux1[] = new int[6];
		int aux2[] = new int[6];
		
		for(int i = 5;i >= 0;--i){
			aux2[i] = coded % 2;
			coded /= 2;
		}
		
		boolean used[] = new boolean[12];
		int count = 1;
		
		for(int i = 1;i <= 12;++i) count *= i;
		for(int i = 1;i <= 6;++i) count /= i;
		
		for(int i = 0;i < 6;++i){
			count = count / (12 - i);
			
			int pos = coded / count;
			coded %= count;
			
			int x = 0;
			
			for(int j = 0;j < 12 && pos >= 0;++j)
				if(!used[j]){
					x = j;
					--pos;
				}
			
			aux1[i] = x;
			used[x] = true;
		}
		
		EdgesState ret = new EdgesState();
		
		for(int i = 0;i < 6;++i){
			if(aux2[i] == 0){
				ret.pos[i][0] = Cube.edgePairs[ aux1[i] ][0];
				ret.pos[i][1] = Cube.edgePairs[ aux1[i] ][1];
			}
			
			if(aux2[i] == 1){
				ret.pos[i][1] = Cube.edgePairs[ aux1[i] ][0];
				ret.pos[i][0] = Cube.edgePairs[ aux1[i] ][1];
			}
		}
		
		return ret;
	}
}
