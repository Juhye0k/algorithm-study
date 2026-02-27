class Solution {
    static int answer;
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        answer = -1;
        visited = new boolean[dungeons.length];
        dfs(k,dungeons,1);
        return answer;
    }
    public static void dfs(int k, int[][] dungeons,int depth) {
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && k>= dungeons[i][0]) {
                k-=dungeons[i][1];
                answer = Math.max(answer,depth);
                visited[i] = true;
                dfs(k,dungeons,depth+1);
                visited[i] = false;
                k+=dungeons[i][1];
            }
        }
    }
}