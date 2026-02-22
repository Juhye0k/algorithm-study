


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

class Node {
    int x,y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long H = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());

        int N = Integer.parseInt(st.nextToken());
        Map<Node, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int j=x-2; j<=x; j++) {
                for(int k=y-2; k<=y; k++) {
                    if(j>=1 && j+2<=H && k>=1 && k+2<=W) {
                        Node node = new Node(j,k);
                        map.put(node, map.getOrDefault(node, 0)+1);
                    }
                }
            }
        }
        long[] ans = new long[10];
        for(int count:map.values()) {
            ans[count]++;
        }
        long totalGrids = (H-2) * (W-2);
        ans[0] = totalGrids - map.size();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb);
    }
}