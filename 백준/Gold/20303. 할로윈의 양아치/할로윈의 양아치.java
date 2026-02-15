
import org.w3c.dom.Node;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;



public class Main {

    static int N, M, K;
    static int[] candies;
    static int[] parent;
    static int[] personCount;
    static int[] candySum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 덩어리 하나사면 아래꺼 다 살 수 있는데..
        int [] children;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N+1];
        parent = new int[N+1];
        personCount = new int[N+1];
        candySum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());

            parent[i] = i;
            personCount[i] = 1;
            candySum[i] = candies[i];
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u,v);
        }

        ArrayList<Group> groups = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            if(parent[i]==i) {
                groups.add(new Group(personCount[i], candySum[i]));
            }
        }

        int[] dp = new int[K];

        for(Group g : groups) {
            for (int j = K - 1; j >= g.people; j--) {
                dp[j] = Math.max(dp[j], dp[j - g.people] + g.candies);
            }
        }
        System.out.println(dp[K-1]);




    }
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB) {
            parent[rootB] = rootA;

            personCount[rootA] += personCount[rootB];
            candySum[rootA] += candySum[rootB];
        }
    }
    static class Group {
        int people;
        int candies;

        public Group(int people, int candies) {
            this.people = people;
            this.candies = candies;
        }
    }
}