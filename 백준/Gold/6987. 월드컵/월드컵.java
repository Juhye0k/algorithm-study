import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;





public class Main {
    // 대진표
    static int game[][];
    // 결과
    static int world[][];
    static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        world = new int[6][3];
        game = new int[15][2];
        int idx = 0;
        for(int i=0; i<5; i++) {
            for(int j=i+1; j<6; j++) {
                game[idx][0] = i;
                game[idx][1] = j;
                idx++;
            }
        }
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            isPossible = false;
            int totalSum = 0;
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    world[j][k] = Integer.parseInt(st.nextToken());
                    totalSum += world[j][k];
                }
            }
            if (totalSum != 30) {
                sb.append(0).append(" ");
                continue;
            }
            dfs(0);
            if(isPossible) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }
    public static void dfs(int cur) {

        if(isPossible) return;
        if(cur==15){
            isPossible = true;
            return;
        }
        int team1 = game[cur][0];
        int team2 = game[cur][1];

        if(world[team1][0]>0 && world[team2][2]>0){
            world[team1][0]--;
            world[team2][2]--;
            dfs(cur+1);
            world[team1][0]++;
            world[team2][2]++;

        }
        if(world[team1][2]>0 && world[team2][0]>0){
            world[team1][2]--;
            world[team2][0]--;
            dfs(cur+1);
            world[team1][2]++;
            world[team2][0]++;
        }
        if(world[team1][1]>0 && world[team2][1]>0){
            world[team1][1]--;
            world[team2][1]--;
            dfs(cur+1);
            world[team1][1]++;
            world[team2][1]++;
        }
    }
}
