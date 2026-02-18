

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x,y;
    int value;
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

}


public class Main {

    static int T;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // N개의 주차공간
        int N = Integer.parseInt(st.nextToken());
        // M대의 차량
        int M = Integer.parseInt(st.nextToken());
        // 주차 가격
        int [] price = new int[N];
        // 차 무게
        int[] car = new int[M+1];
        int[] parkSpace = new int[N];

        for(int i=0; i<N; i++) {
            price[i] = Integer.parseInt(br.readLine());
        }
        for(int i=1; i<=M; i++) {
            car[i] = Integer.parseInt(br.readLine());
        }
        int result = 0;
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<2*M; i++) {
            int newCar = Integer.parseInt(br.readLine());

            if(newCar>0){
                boolean parked = false;
                // 빈자리 있는지 탐색
                for(int j=0; j<N; j++) {
                    if(parkSpace[j]==0) {
                        parkSpace[j] = newCar;
                        result += price[j] * car[newCar];
                        parked = true;
                        break;
                    }
                }
                // 빈자리 없으면 대기열로 이동
                if(!parked) {
                    q.offer(newCar);
                }
            }
            else {
                int leaveCar = -newCar;

                for(int j=0; j<N; j++) {
                    if (parkSpace[j] == leaveCar) {
                        parkSpace[j] = 0;
                        if (!q.isEmpty()) {
                            int nextCar = q.poll();
                            parkSpace[j] = nextCar;
                            result += price[j] * car[nextCar];
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}