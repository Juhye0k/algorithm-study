import java.awt.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



class Node implements Comparable<Node>{
    int weight;
    int price;

    public Node(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
    @Override
    public int compareTo(Node o) {
        if(this.price == o.price)
            return o.weight - this.weight;
        return this.price-o.price;
    }
}

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 덩어리 하나사면 아래꺼 다 살 수 있는데..
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;
        boolean isPossible = false;
        int curWeight = 0;
        int curPrice =0;

        Node[] ar = new Node[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            ar[i] = new Node(weight, price);
        }
        Arrays.sort(ar);
        for(int i=0; i<N; i++) {
            curWeight+=ar[i].weight;

            // 다음 무게가 같을 때
            if(i>0 && ar[i-1].price == ar[i].price) {
                curPrice+=ar[i].price;
            }
            // 다음 무게가 다를 때
            else {
                curPrice = ar[i].price;
            }
            if (curWeight >= M) {
                isPossible = true;
                answer = Math.min(answer, curPrice);

            }
        }
        if(!isPossible) answer = -1;
        System.out.println(answer);
    }
}