class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] diff = new int[n + 1][m + 1];

        // skill
        for(int i=0; i<skill.length; i++) {
            // type이 1일 때 -> 공격
            // type이 2일 때 -> 회복
            int type = skill[i][0];
            
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            
            int damage = type==1?-skill[i][5]:skill[i][5];
            
            diff[r1][c1] +=damage;
            diff[r1][c2+1] -=damage;
            diff[r2+1][c1] -=damage;
            diff[r2+1][c2+1] += damage;
            
        }
        for(int r=0; r<board.length; r++) {
            for(int c=1; c<board[0].length; c++) {
                diff[r][c] +=diff[r][c-1];
            }
        }
        for (int row = 1; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                diff[row][col] += diff[row - 1][col];
            }
        }
        for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[0].length; j++) {
                    if(board[i][j] + diff[i][j]>=1) answer++;
                }
            }
        return answer;
    }
}