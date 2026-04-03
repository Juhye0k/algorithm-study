class Solution {
    public int solution(int[][] beginning, int[][] target) {
        
        int n = beginning.length;
        int m = beginning[0].length;
        int answer = Integer.MAX_VALUE;
        
        for(int bit = 0; bit< (1<<n); bit++) {
            int[] rowFlip = new int[n];
            int rowCount = 0;
            
            for(int i=0; i<n; i++) {
                if((bit&(1<<i))!=0) {
                    rowFlip[i] = 1;
                    rowCount++;
                }
            }
            int[] colFlip = new int[m];
            int colCount = 0;
            for(int j=0; j<m; j++) {
                if((beginning[0][j]^rowFlip[0])!=target[0][j]) {
                    colFlip[j] = 1;
                    colCount++;
                }
            }
            boolean possible = true;
            for(int i=0; i<n;i++) {
                for(int j=0; j<m; j++) {
                    int current = beginning[i][j]^rowFlip[i]^colFlip[j];
                    if(current!=target[i][j]) {
                        possible = false;
                        break;
                    }
                }
                if(!possible) break;
            }
            if(possible) {
                answer = Math.min(answer, rowCount+colCount);
            }
        }
        
        return answer == Integer.MAX_VALUE?-1:answer;
    }
}