class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        
        int h = n / w;
        if(n%w!=0) h+=1;
        
        int[][] ar = new int[h][w];
        
        int number = 1;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(number>n) break;
                if(i%2 !=0) {
                    ar[h-i-1][j] = number++;
                }
                else {
                    ar[h-i-1][w-j-1] = number++;
                }
            }
        }
        int x=0, y=0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(ar[i][j]==num) {
                    x = i;
                    y = j;
                }
            }
        }
        for(int i=x; i>=0; i--) {
            if(ar[i][y]!=0) answer++;
        }
        
        return answer;
    }
}