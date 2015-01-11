import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class IDA2 extends IDASolverInterface{
	int memo[][][][];
	int pos_dest[][][],pos[];
	
	IDA2(){
		memo = new int[54][54][54][54];
		
		for(int i = 0;i < 54;++i)
			for(int j = 0;j < 54;++j)
				for(int k = 0;k < 54;++k)
					for(int l = 0;l < 54;++l)
						memo[i][j][k][l] = -1;
		
		pos_dest = new int[54][6][3];
		
		for(int i = 0;i < 54;++i)
			for(int j = 0;j < 6;++j)
				pos_dest[i][j][0] = i;
		
		pos = new int[20];
		
		// Left
        pos[0] = 51; pos[1] = 48; pos[2] = 45;
        pos[3] = 15; pos[4] = 12; pos[5] = 9;
        pos[6] = 42; pos[7] = 39; pos[8] = 36;
        pos[9] = 29; pos[10] = 32; pos[11] = 35;
        pos[12] = 0; pos[13] = 1;
        pos[14] = 2; pos[15] = 5;
        pos[16] = 8; pos[17] = 7;
        pos[18] = 6; pos[19] = 3;
        
        precalculateFace(0);
        
        // Front
        pos[0] = 45; pos[1] = 46; pos[2] = 47;
        pos[3] = 24; pos[4] = 21; pos[5] = 18;
        pos[6] = 44; pos[7] = 43; pos[8] = 42;
        pos[9] = 2; pos[10] = 5; pos[11] = 8;
        pos[12] = 9; pos[13] = 10;
        pos[14] = 11; pos[15] = 14;
        pos[16] = 17; pos[17] = 16;
        pos[18] = 15; pos[19] = 12;
        
        precalculateFace(1);
        
        // Right
        pos[0] = 47; pos[1] = 50; pos[2] = 53;
        pos[3] = 33; pos[4] = 30; pos[5] = 27;
        pos[6] = 38; pos[7] = 41; pos[8] = 44;
        pos[9] = 11; pos[10] = 14; pos[11] = 17;
        pos[12] = 18; pos[13] = 19;
        pos[14] = 20; pos[15] = 23;
        pos[16] = 26; pos[17] = 25;
        pos[18] = 24; pos[19] = 21;
        
        precalculateFace(2);
        
        // Back
        pos[0] = 53; pos[1] = 52; pos[2] = 51;
        pos[3] = 6; pos[4] = 3; pos[5] = 0;
        pos[6] = 36; pos[7] = 37; pos[8] = 38;
        pos[9] = 20; pos[10] = 23; pos[11] = 26;
        pos[12] = 27; pos[13] = 28;
        pos[14] = 29; pos[15] = 32;
        pos[16] = 35; pos[17] = 34;
        pos[18] = 33; pos[19] = 30;
        
        precalculateFace(3);
        
        // Up
        pos[0] = 9; pos[1] = 10; pos[2] = 11;
        pos[3] = 18; pos[4] = 19; pos[5] = 20;
        pos[6] = 27; pos[7] = 28; pos[8] = 29;
        pos[9] = 0; pos[10] = 1; pos[11] = 2;
        pos[12] = 36; pos[13] = 37;
        pos[14] = 38; pos[15] = 41;
        pos[16] = 44; pos[17] = 43;
        pos[18] = 42; pos[19] = 39;
        
        precalculateFace(4);
        
        // Down
        pos[0] = 35; pos[1] = 34; pos[2] = 33;
        pos[3] = 26; pos[4] = 25; pos[5] = 24;
        pos[6] = 17; pos[7] = 16; pos[8] = 15;
        pos[9] = 8; pos[10] = 7; pos[11] = 6;
        pos[12] = 45; pos[13] = 46;
        pos[14] = 47; pos[15] = 50;
        pos[16] = 53; pos[17] = 52;
        pos[18] = 51; pos[19] = 48;
        
        precalculateFace(5);
	}
	
	void precalculateFace(int f){
		for(int i = 0;i < 12;++i)
			pos_dest[ pos[ (i + 9) % 12 ] ][f][0] = pos[i];
		
		for(int i = 0;i < 8;++i)
			pos_dest[ pos[ 12 + (i + 2) % 8 ] ][f][0] = pos[12 + i];

		for(int j = 1;j < 3;++j)
			for(int i = 0;i < 54;++i)
				pos_dest[i][f][j] = pos_dest[ pos_dest[i][f][0] ][f][j - 1];
	}
	
	int estimate(Cube C){
		int ret = 0;
		
		for(int i = 0;i < 12;++i){
			int x = Cube.edgePairs[i][0],y = Cube.edgePairs[i][1];
			ret = Math.max(ret, solveTwoPositions(x,y,C.origin[x],C.origin[y]));
		}
		
		for(int i = 0;i < 8;++i){
			int x = Cube.cornerTriples[i][0],y = Cube.cornerTriples[i][1];
			ret = Math.max(ret, solveTwoPositions(x,y,C.origin[x],C.origin[y]));
		}
		
		return ret;
	}
	
	int solveTwoPositions(int sx, int sy, int ex, int ey){
		if(memo[sx][sy][ex][ey] == -1)
			return memo[sx][sy][ex][ey];
		
		Queue<Node> Q = new LinkedList<Node>();
		Set<Node> S = new HashSet<Node>();
		
		Node aux = new Node(sx,sy,0);
		Q.add(aux);
		S.add(aux);
		
		while(!Q.isEmpty()){
			Node cur = Q.poll();
			
			if(cur.x == ex && cur.y == ey){
				memo[sx][sy][ex][ey] = cur.dist;
				return cur.dist;
			}
			
			for(int i = 0;i < 6;++i){
				for(int j = 0;j < 3;++j){
					aux = new Node(pos_dest[cur.x][i][j],pos_dest[cur.y][i][j],cur.dist + 1);
					
					if(!S.contains(aux)){
						Q.add(aux);
						S.add(aux);
					}
				}
			}
		}

		memo[sx][sy][ex][ey] = INF;
		return INF;
	}
}

class Node implements Comparable<Node>{
	int x,y,dist;
	
	Node(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	public int compareTo(Node o){
		if(x < o.x) return -1;
		if(x > o.x) return 1;
		if(y < o.y) return -1;
		if(y > o.y) return 1;
		return 0;
	}
}