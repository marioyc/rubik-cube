import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Cube{
    /*
          Up
          36 37 38
          39 40 41
          42 43 44
          
    Left  Front    Right    Back
    0 1 2 9  10 11 18 19 20 27 28 29
    3 4 5 12 13 14 21 22 23 30 31 32
    6 7 8 15 16 17 24 25 26 33 34 35
    
          Down
          45 46 47
          48 49 50
          51 52 53
    */
    
    // colors of the cube's faces
    public int M[];
    // original positions
    public int origin[];
    // auxiliary arrays for rotations
    private int pos[],tmp1[],tmp2[];
    // encoding and decoding original input
    Map<Character, Integer> encode;
    Map<Integer, Character> decode;
    
    final static int edgePairs[][] = {
    		{1,39}, {3,32}, {5,12}, {7,48}, {10,43}, {14,21},
    		{16,46}, {19,41}, {23,30}, {25,50}, {28,37}, {34,52}
    };
    
    final static int cornerTriples[][] = {
    		{42,2,9}, {44,11,18}, {38,20,27}, {36,29,0}, // upper face corners
    		{45,15,8}, {47,24,17}, {53,33,26}, {51,6,35} // lower face corners
    };
    
    Cube(){
        M = new int[54];
        origin = new int[54];
        
        pos = new int[20];
        tmp1 = new int[20];
        tmp2 = new int[20];
        
        encode = new HashMap<Character, Integer>();
        decode = new HashMap<Integer, Character>();
        
        encode.put('G', 0); decode.put(0, 'G');
        encode.put('R', 1); decode.put(1, 'R');
        encode.put('B', 2); decode.put(2, 'B');
        encode.put('O', 3); decode.put(3, 'O');
        encode.put('W', 4); decode.put(4, 'W');
        encode.put('Y', 5); decode.put(5, 'Y');
        
        for(int i = 0;i < 6;++i)
            for(int j = 0;j < 3;++j)
                for(int k = 0;k < 3;++k){
                    M[9 * i + 3 * j + k] = i;
                    origin[9 * i + 3 * j + k] = 9 * i + 3 * j + k;
                }
    }
    
    void read() throws Exception{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Up Face:");
        String uface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	uface = uface + line;
        }
        
        System.out.println("Left Face:");
        String lface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	lface = lface + line;
        }
        
        System.out.println("Front Face:");
        String fface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	fface = fface + line;
        }
        
        System.out.println("Right Face:");
        String rface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	rface = rface + line;
        }
        
        System.out.println("Back Face:");
        String bface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	bface = bface + line;
        }
        
        System.out.println("Down Face:");
        String dface = new String();
        
        for(int i = 0;i < 3;++i){
        	String line = sc.nextLine();
        	dface = dface + line;
        }
        
        sc.close();
        
        String cube = lface + fface + rface + bface + uface + dface;
        initFromString(cube);
    }
    
    void initFromString(String cube) throws Exception{
        encode.clear();
        decode.clear();

        for(int i = 0;i < 6;++i){
        	if(encode.containsKey(cube.charAt(9 * i + 4)))
        		throw new Exception("Invalid cube: Repeated cube center");
        	else
    			encode.put(cube.charAt(9 * i + 4), i);
        	
        	decode.put(i, cube.charAt(9 * i + 4));
        }
        
        for(int i = 0;i < 54;++i){
        	if(encode.get(cube.charAt(i)) == null)
        		throw new Exception("Invalid cube: has no center");
        	else
        		M[i] = encode.get(cube.charAt(i));
        }
        
        for(int i = 0;i < 6;++i)
    		origin[9 * i + 4] = 9 * i + 4;
        
        for(int i = 0;i < 12;++i){
        	int x = edgePairs[i][0],y = edgePairs[i][1];
        	int c1 = M[x],c2 = M[y];
        	boolean found = false;
        	
        	for(int j = 0;j < 12;++j){
        		int c3 = edgePairs[j][0] / 9,c4 = edgePairs[j][1] / 9;
        		
        		if(c1 == c3 && c2 == c4){
        			origin[x] = edgePairs[j][0];
        			origin[y] = edgePairs[j][1];
        			found = true;
        		}
        		
        		if(c1 == c4 && c2 == c3){
        			origin[x] = edgePairs[j][1];
        			origin[y] = edgePairs[j][0];
        			found = true;
        		}
        	}
        	
        	if(!found)
        		throw new Exception("Invalid cube: No valid origin position");
        }
        
        for(int i = 0;i < 8;++i){
        	int x = cornerTriples[i][0],y = cornerTriples[i][1],z = cornerTriples[i][2];
        	int c1 = M[x],c2 = M[y],c3 = M[z];
        	boolean found = false;
        	
        	for(int j = 0;j < 8;++j){
        		int c4 = cornerTriples[j][0] / 9,c5 = cornerTriples[j][1] / 9,c6 = cornerTriples[j][2] / 9;
        		
        		if(c1 == c4 && c2 == c5 && c3 == c6){
        			origin[x] = cornerTriples[j][0];
        			origin[y] = cornerTriples[j][1];
        			origin[z] = cornerTriples[j][2];
        			found = true;
        		}
        		
        		if(c1 == c4 && c2 == c6 && c3 == c5){
        			origin[x] = cornerTriples[j][0];
        			origin[y] = cornerTriples[j][2];
        			origin[z] = cornerTriples[j][1];
        			found = true;
        		}
        		
        		if(c1 == c5 && c2 == c4 && c3 == c6){
        			origin[x] = cornerTriples[j][1];
        			origin[y] = cornerTriples[j][0];
        			origin[z] = cornerTriples[j][2];
        			found = true;
        		}
        		
        		if(c1 == c5 && c2 == c6 && c3 == c4){
        			origin[x] = cornerTriples[j][1];
        			origin[y] = cornerTriples[j][2];
        			origin[z] = cornerTriples[j][0];
        			found = true;
        		}
        		
        		if(c1 == c6 && c2 == c4 && c3 == c5){
        			origin[x] = cornerTriples[j][2];
        			origin[y] = cornerTriples[j][0];
        			origin[z] = cornerTriples[j][1];
        			found = true;
        		}
        		
        		if(c1 == c6 && c2 == c5 && c3 == c4){
        			origin[x] = cornerTriples[j][2];
        			origin[y] = cornerTriples[j][1];
        			origin[z] = cornerTriples[j][0];
        			found = true;
        		}
        	}
        	
        	if(!found)
        		throw new Exception("Invalid cube: No valid origin position");
        }
    }
    
    public String toString(){
    	String ret = new String();
    	
    	for(int i = 0;i < 54;++i)
    		ret = ret + decode.get(M[i]);
    	
    	return ret;
    }
    
    void print(){
        for(int i = 0;i < 3;++i)
            System.out.println("      " + decode.get(M[36 + 3 * i]) + " " + decode.get(M[36 + 3 * i + 1]) + " " + decode.get(M[36 + 3 * i + 2]));
        
        for(int i = 0;i < 3;++i){
            for(int j = 0;j < 4;++j)
                System.out.print(decode.get(M[9 * j + 3 * i]) + " " + decode.get(M[9 * j + 3 * i + 1]) + " " + decode.get(M[9 * j + 3 * i + 2]) + " ");
            System.out.println();
        }
        
        for(int i = 0;i < 3;++i)
            System.out.println("      " + decode.get(M[45 + 3 * i]) + " " + decode.get(M[45 + 3 * i + 1]) + " " + decode.get(M[45 + 3 * i + 2]));
        
        System.out.println();
    }
    
    /*
       8  7  6
    9  12 13 14 5
    10 19    15 4
    11 18 17 16 3
       0  1  2
    */
    
    void rotate(){
        for(int i = 0;i < 20;++i){
            tmp1[i] = M[ pos[i] ];
            tmp2[i] = origin[ pos[i] ];
        }
        
        for(int i = 0;i < 12;++i){
            M[ pos[i] ] = tmp1[ (i + 9) % 12 ];
            origin[ pos[i] ] = tmp2[ (i + 9) % 12 ];
        }
        
        for(int i = 0;i < 8;++i){
            M[ pos[12 + i] ] = tmp1[ 12 + (i + 2) % 8 ];
            origin[ pos[12 + i] ] = tmp2[ 12 + (i + 2) % 8 ];
        }
    }
    
    void rotateLeft(int times){
        pos[0] = 51; pos[1] = 48; pos[2] = 45;
        pos[3] = 15; pos[4] = 12; pos[5] = 9;
        pos[6] = 42; pos[7] = 39; pos[8] = 36;
        pos[9] = 29; pos[10] = 32; pos[11] = 35;
        pos[12] = 0; pos[13] = 1;
        pos[14] = 2; pos[15] = 5;
        pos[16] = 8; pos[17] = 7;
        pos[18] = 6; pos[19] = 3;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateFront(int times){
        pos[0] = 45; pos[1] = 46; pos[2] = 47;
        pos[3] = 24; pos[4] = 21; pos[5] = 18;
        pos[6] = 44; pos[7] = 43; pos[8] = 42;
        pos[9] = 2; pos[10] = 5; pos[11] = 8;
        pos[12] = 9; pos[13] = 10;
        pos[14] = 11; pos[15] = 14;
        pos[16] = 17; pos[17] = 16;
        pos[18] = 15; pos[19] = 12;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateRight(int times){
        pos[0] = 47; pos[1] = 50; pos[2] = 53;
        pos[3] = 33; pos[4] = 30; pos[5] = 27;
        pos[6] = 38; pos[7] = 41; pos[8] = 44;
        pos[9] = 11; pos[10] = 14; pos[11] = 17;
        pos[12] = 18; pos[13] = 19;
        pos[14] = 20; pos[15] = 23;
        pos[16] = 26; pos[17] = 25;
        pos[18] = 24; pos[19] = 21;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateBack(int times){
        pos[0] = 53; pos[1] = 52; pos[2] = 51;
        pos[3] = 6; pos[4] = 3; pos[5] = 0;
        pos[6] = 36; pos[7] = 37; pos[8] = 38;
        pos[9] = 20; pos[10] = 23; pos[11] = 26;
        pos[12] = 27; pos[13] = 28;
        pos[14] = 29; pos[15] = 32;
        pos[16] = 35; pos[17] = 34;
        pos[18] = 33; pos[19] = 30;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateUp(int times){
        pos[0] = 9; pos[1] = 10; pos[2] = 11;
        pos[3] = 18; pos[4] = 19; pos[5] = 20;
        pos[6] = 27; pos[7] = 28; pos[8] = 29;
        pos[9] = 0; pos[10] = 1; pos[11] = 2;
        pos[12] = 36; pos[13] = 37;
        pos[14] = 38; pos[15] = 41;
        pos[16] = 44; pos[17] = 43;
        pos[18] = 42; pos[19] = 39;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateDown(int times){
        pos[0] = 35; pos[1] = 34; pos[2] = 33;
        pos[3] = 26; pos[4] = 25; pos[5] = 24;
        pos[6] = 17; pos[7] = 16; pos[8] = 15;
        pos[9] = 8; pos[10] = 7; pos[11] = 6;
        pos[12] = 45; pos[13] = 46;
        pos[14] = 47; pos[15] = 50;
        pos[16] = 53; pos[17] = 52;
        pos[18] = 51; pos[19] = 48;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void randomMove(){
    	double r1 = Math.random() * 3,r2 = Math.random() * 6;
    	int times = 0;
    	
    	if(r1 < 1) times = 1;
    	else if(r1 < 2) times = 2;
    	else times = 3;
    	
    	if(r2 < 1) rotateFront(times);
    	else if(r2 < 2) rotateBack(times);
    	else if(r2 < 3) rotateLeft(times);
    	else if(r2 < 4) rotateRight(times);
    	else if(r2 < 5) rotateUp(times);
    	else rotateDown(times);
    }
    
    void mixCube(int moves){
    	for(int i = 0;i < moves;++i)
    		randomMove();
    }
    
    boolean check(){
    	for(int i = 0;i < 6;++i)
    		for(int j = 0;j < 9;++j)
    			if(M[9 * i + j] != M[9 * i + 4])
    				return false;
    	
    	return true;
    }
};
