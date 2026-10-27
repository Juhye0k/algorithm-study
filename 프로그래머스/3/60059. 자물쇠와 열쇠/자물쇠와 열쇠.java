class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        
        int m = key.length;
        int n = lock.length;
        
        boolean hasHole = false;
        
        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                if(lock[r][c] == 0)
                    hasHole = true;
            }
        }
        
        if(!hasHole) {
            return true;
        }
        
        for(int rotation = 0; rotation<4; rotation++) {
            for(int row = -(m-1); row <= n-1; row++) {
                for(int col = -(m-1); col<=n-1; col++) {
                    if(canOpen(key,lock,row,col))
                        return true;
                }
            }
            key =rotate(key);
        }
        
        
        
        return false;
        
        
    }
    private boolean canOpen(int[][] key, int[][] lock, int offsetRow, int offsetCol) {
        int m =key.length;
        int n = lock.length;
        
        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                
                int keyRow = r-offsetRow;
                int keyCol = c-offsetCol;
                
                int keyValue = 0;
                
                if(keyRow>=0 && keyRow<m && keyCol>=0 && keyCol<m) {
                    keyValue = key[keyRow][keyCol];
                }
                if(lock[r][c] +keyValue !=1)
                    return false;
            }
        }
        return true;
    }
    
    private int[][] rotate(int[][] key) {
        int m = key.length;
        int[][] rotated = new int[m][m];
        
        for(int r=0; r<m; r++) {
            for(int c=0; c<m; c++) {
                rotated[c][m-1-r] = key[r][c];
            }
        }
        return rotated;
    }
}