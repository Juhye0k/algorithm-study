

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;


public class Main {

    static int[][] Gears = new int[4][8];
    static int[] head = {0,0,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for(int i=0; i<4; i++) {
            String s = br.readLine();
            for(int j=0; j<8; j++) {
                Gears[i][j] = s.charAt(j)-'0';
            }
        }
        // 회전 횟수 K
        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 회전시킨 톱니바퀴 번호
            int num = Integer.parseInt(st.nextToken())-1;
            // 방향 1: 시계방향 -1 : 반시계방향
            int dir = Integer.parseInt(st.nextToken());

            // 각 톱니바퀴가 이번 턴에 돌아갈 방향을 저장함
            int[] d = new int[4];
            d[num] = dir;

            // 왼쪽 전파
            for(int j = num; j>0; j--) {
                int rightGear = j;
                int leftGear = j-1;
                // 왼쪽 톱니와 오른쪽 톱니의 값을 비교
                int leftGear3 = getVal(leftGear, 2);
                int rightGear9 = getVal(rightGear, 6);
                if(leftGear3 != rightGear9) {
                    d[leftGear] = d[rightGear]*-1;
                } else {
                    break;
                }
            }
            // 오른쪽 전파
            for(int j = num; j<3; j++) {
                int leftGear = j;
                int rightGear = j+1;
                int leftGear3 = getVal(leftGear, 2);
                int rightGear9 = getVal(rightGear, 6);
                if(leftGear3 != rightGear9) {
                    d[rightGear] = d[leftGear]*-1;
                } else {
                    break;
                }
            }
            for(int j=0; j<4; j++) {
                if(d[j] == 0) continue;
                rotate(j, d[j]);
            }
        }
        int score = 0;
        if(getVal(0, 0) == 1) score+=1;
        if(getVal(1, 0) == 1) score+=2;
        if(getVal(2, 0) == 1) score+=4;
        if(getVal(3, 0) == 1) score+=8;
        System.out.println(score);

    }
    static int getVal(int gearNum, int loc) {
        int actualIndex = (head[gearNum] + loc) % 8;
        return Gears[gearNum][actualIndex];
    }
    static void rotate(int gearNum, int dir) {
        head[gearNum] = (head[gearNum] - dir+8) % 8;
    }
}