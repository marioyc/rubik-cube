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
    
    void rotateUp(){
        
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    void rotateDown(){
        
        
        for(int i = 0;i < times;++i)
            rotate();
    }
    
    // colors of the cube's faces
    private int M[];
    // auxiliary arrays for rotations
    private int pos[],color[];
    
    Cube(){
        M = new int[54];
        pos = new int[20];
        color = new int[20];
        
        for(int i = 0;i < 6;++i)
            for(int j = 0;j < 3;++j)
                for(int k = 0;k < 3;++k)
                    M[9 * i + 3 * j + k] = i;
    }
    
    void print(){
        for(int i = 0;i < 3;++i)
            System.out.println("      " + M[36 + 3 * i] + " " + M[36 + 3 * i + 1] + " " + M[36 + 3 * i + 2]);
        
        for(int i = 0;i < 3;++i){
            for(int j = 0;j < 4;++j)
                System.out.print(M[9 * j + 3 * i] + " " + M[9 * j + 3 * i + 1] + " " + M[9 * j + 3 * i + 2] + " ");
            System.out.println();
        }
        
        for(int i = 0;i < 3;++i)
            System.out.println("      " + M[45 + 3 * i] + " " + M[45 + 3 * i + 1] + " " + M[45 + 3 * i + 2]);
    }
    
    /*
       8  7  6
    9  12 13 14 5
    10 19    15 4
    11 18 17 16 3
       0  1  2
    */
    
    void rotate(){
        for(int i = 0;i < 20;++i)
            color[i] = M[ pos[i] ];
        
        for(int i = 0;i < 12;++i)
            M[ pos[i] ] = color[ (pos[i] + 9) % 12 ];
        
        for(int i = 0;i < 9;++i)
            M[ pos[12 + i] ] = color[ pos[12 + (i + 2) % 9] ];
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
    
    void rotateRight(int times){
        pos[0] = 47; pos[1] = 50; pos[2] = 53;
        pos[3] = 33; pos[4] = 30; pos[5] = 27;
        pos[6] = 38; pos[7] = 41; pos[8] = 44;
        pos[7] = 11; pos[8] = 14; pos[9] = 17;
        pos[12] = 18; pos[13] = 19;
        pos[14] = 20; pos[15] = 23;
        pos[16] = 26; pos[17] = 25;
        pos[18] = 24; pos[19] = 21;
        
        for(int i = 0;i < times;++i)
            rotate();
    }
};
