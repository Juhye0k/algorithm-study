class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] ar = new int[rows][columns];
        int index = 0;
        int value = 1;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                ar[r][c] = value++;
            }
        }
        for(int k=0; k<queries.length; k++) {
            int[][] tempArr = new int[rows][columns];
            
            for(int r=0; r<rows; r++)
                tempArr[r] = ar[r].clone();
            
            int startX = queries[k][0]-1;
            int startY = queries[k][1]-1;
            
            int endX = queries[k][2]-1;
            int endY = queries[k][3]-1;
            int result = Integer.MAX_VALUE;
            for(int i=startX; i<=endX; i++) {
                for(int j=startY; j<=endY; j++) {
                    if(i==startX || j==startY || i==endX|| j==endY) {
                        int[] temp = rotate(i,j,startX,startY,endX,endY);
                        tempArr[temp[0]][temp[1]] = ar[i][j];
                        result = Math.min(result, ar[i][j]);
                    }
                }
            }
            ar = tempArr;
            answer[index++] = result;
        }
        return answer;
    }
    public static int[] rotate(int x, int y, int startX, int startY, int endX, int endY) {
        // 시작과 열이 같음
        int dx=0, dy=0;
        if(y==startY) {
            if(x==startX) {
                dx = x;
                dy = y+1;
            }
            else  {
                dx = x-1;
                dy = y;
            }
        }
           
        // 시작과 행이 같음
        else if(x==startX){
            // 끝과 열이 같음
            if(endY == y) {
                dx=x+1;
                dy=y;
            }
            // 끝과 열이 다름
            else {
                dx = x;
                dy = y+1;
            }
        }
        // 끝과 열이 같음
        else if(endY == y) {
            // 끝과 행이 같음
            if(endX == x) {
                dx = x;
                dy = y-1;
            }
            // 끝과 행이 다름
            else {
                dx = x+1;
                dy = y;
            }
        }
        // 끝과 행이 같음
        else if(endX == x) {
            if(startY == y) {
                dx = x-1;
                dy = y;
            }
            else {
                dx = x;
                dy = y-1;
            }
        }
        return new int[]{dx,dy};
    }
}