class Solution {
    static int size;
    static int n;
    static int m;
    static int answer;
    static int[][] info;
    static boolean[][][] visited;
    public int solution(int[][] info, int n, int m) {
        answer = Integer.MAX_VALUE;
        this.n = n;
        this.m = m;
        this.info = info;
        size = info.length;
        
        visited = new boolean[size+1][n][m];
        
        dfs(0,0,0);
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
    public static void dfs(int depth, int a, int b) {
        if(a>=n || b>=m) {
            return;
        }
        if(a>= answer) return;
        
        if(visited[depth][a][b]) return;
        visited[depth][a][b] = true;
        if(depth >= size) {
            answer = Math.min(answer, a);
            return;
        }

        // a를 선택한 경우
        dfs(depth+1, a + info[depth][0], b);
        // b를 선택한 경우
        dfs(depth+1, a, b + info[depth][1]);
    }
}