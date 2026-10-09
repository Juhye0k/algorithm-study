class Solution {
    static int answer;
    static boolean[] visited;
    static int size;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        size = dungeons.length;
        visited = new boolean[size];
        dfs(k,dungeons,0);
        return answer;
            
    }
    public static void dfs(int health,int[][] dungeons,int count) {
        answer = Math.max(answer, count);
        for(int i=0; i<size; i++) {
            if(!visited[i] && health>=dungeons[i][0]) {
                visited[i] = true;
                dfs(health-dungeons[i][1], dungeons,count+1);
                visited[i] = false;
            }
        }
    }
}