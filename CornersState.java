class CornersState{
	int pos[][];
	
	CornersState(){
		pos = new int[7][3];
	}
	
	public String toString(){
		String s = new String();
		
		for(int i = 0;i < 7;++i)
			for(int j = 0;j < 3;++j)
				s = s + pos[i][j] + " ";
		
		return s;
	}
	
	public int encode(){
		int aux1[] = new int[8];
		int aux2 = 0;
		
		for(int i = 0;i < 7;++i){
			int x = pos[i][0],y = pos[i][1],z = pos[i][2];
			
			for(int j = 0;j < 8;++j){
				int a = Cube.cornerTriples[j][0];
				
				if(x == a){
					aux1[i] = j;
					aux2 = aux2 * 3;
					break;
				}
				
				if(y == a){
					aux1[i] = j;
					aux2 = aux2 * 3 + 1;
					break;
				}
				
				if(z == a){
					aux1[i] = j;
					aux2 = aux2 * 3 + 2;
					break;
				}
			}
		}
		
		boolean used[] = new boolean[8];
		int ret = 0;
		
		for(int i = 0;i < 7;++i){
			int cont = 0;
			
			for(int j = 0;j < aux1[i];++j)
				if(!used[j])
					++cont;
			
			used[ aux1[i] ] = true;
			aux1[i] = cont;
		}
		
		int fact = 1;
		
		for(int i = 6;i >= 0;--i){
			fact *= 7 - i;
			ret += fact * aux1[i];
		}
		
		for(int i = 0;i < 7;++i)
			ret *= 3;
		
		ret += aux2;
		
		return ret;
	}
	
	static CornersState decode(int coded){
		int aux1[] = new int[7];
		int aux2[] = new int[7];
		
		for(int i = 6;i >= 0;--i){
			aux2[i] = coded % 3;
			coded /= 3;
		}
		
		boolean used[] = new boolean[8];
		int fact = 1;
		
		for(int i = 0;i < 8;++i)
			fact = fact * (1 + i);
		
		for(int i = 0;i < 7;++i){
			fact /= 8 - i;
			int pos = coded / fact;
			coded %= fact;
			
			int x = 0;
			
			for(int j = 0;j < 8 && pos >= 0;++j)
				if(!used[j]){
					x = j;
					--pos;
				}
			
			aux1[i] = x;
			used[x] = true;
		}
		
		CornersState ret = new CornersState();
		
		for(int i = 0;i < 7;++i){
			if(aux2[i] == 0){
				ret.pos[i][0] = Cube.cornerTriples[ aux1[i] ][0];
				ret.pos[i][1] = Cube.cornerTriples[ aux1[i] ][1];
				ret.pos[i][2] = Cube.cornerTriples[ aux1[i] ][2];
			}
			
			if(aux2[i] == 1){
				ret.pos[i][1] = Cube.cornerTriples[ aux1[i] ][0];
				ret.pos[i][2] = Cube.cornerTriples[ aux1[i] ][1];
				ret.pos[i][0] = Cube.cornerTriples[ aux1[i] ][2];
			}
			
			if(aux2[i] == 2){
				ret.pos[i][2] = Cube.cornerTriples[ aux1[i] ][0];
				ret.pos[i][0] = Cube.cornerTriples[ aux1[i] ][1];
				ret.pos[i][1] = Cube.cornerTriples[ aux1[i] ][2];
			}
		}
		
		return ret;
	}
}
