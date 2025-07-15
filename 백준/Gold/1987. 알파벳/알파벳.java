import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;




public class Main{

    static char [][] ar;
    static int result;
    static int visited[];
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int R,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 사람의 수 N과 파티의 수 M이 주어짐

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ar = new char[R][C];
        visited = new  int[26];
        for(int i = 0; i < R; i++){
            String line = br.readLine();
            for(int j = 0; j < C; j++){
                ar[i][j] = line.charAt(j);
            }
        }
        for(int i = 0; i < 26; i++){
            visited[i] = -1;
        }
        dfs(0,0,1);
        System.out.println(result);

    }
    public static void dfs(int i, int j,int answer){
        visited[ar[i][j]-'A'] = 1;
        result = Math.max(result, answer);
        // 행열의 범위를 벗어나면 아웃
        // 상하좌우로 탐색해야함
        for(int k = 0; k < 4; k++){
            int nx = i + dx[k];
            int ny = j + dy[k];
            if(nx>=0 && nx<R && ny>=0 && ny<C &&
                    visited[ar[nx][ny]-'A']==-1){  // 방문하지 않은 곳이고, 이전 값과 다르면
                dfs(nx, ny,answer+1);
            }
        }
        visited[ar[i][j]-'A'] = -1;

    }
}
