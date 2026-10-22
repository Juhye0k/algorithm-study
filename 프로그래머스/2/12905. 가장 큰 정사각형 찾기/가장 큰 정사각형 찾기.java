class Solution
{
    public int solution(int [][]board)
    {
        int answer = 1234;
        int size = board.length;
        /*
        가장 단순한 방법 : 각 위치에서 크기가 1,2,3... 인 정사각형을 잡고 전부 1인지 확인
        -> 이미 확인한 칸을 큰 정사각형에서 다시 확인함
        -> 작은 정사각형을 확인한 결과를 저장해서, 큰 정사각형을 판별하는데 사용할 수 없을까?
        
        무엇을 저장하지? 
        - 단순히 지금까지 찾은 가장 큰 정사각형의 길이만 저장하면 위치를 잃어버림
        - 다른 곳에서 찾은 정사각형은 현재 칸 주변에 큰 정사각형이 있는지 판단하는데 도움이 되지 않음
        - 따라서, 오른쪽 아래를 고정하여 이 칸에서 끝나는 가장 큰 정사각형의 길이를 구함
        - 왼쪽 위부터 계산할 때 필요한 이웃의 결과가 이미 계산되어 있기 때문
        */
        int row = board.length;
        int col = board[0].length;
        int[][] dp = new int[row+1][col+1];
        int maxSide = 0;
    
        
        for(int i=1; i<=row; i++) {
            for(int j=1; j<=col; j++) {
                if(board[i-1][j-1] == 1)
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]))+1;
                
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        
        
        return maxSide * maxSide;
    }
}