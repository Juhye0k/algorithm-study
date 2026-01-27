

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
    int u;
    int v;
    Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [] degree = new int[N+1];
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());


            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u,v));
            degree[u]++;
            degree[v]++;
        }
        long dCount = 0;
        for(Edge e : edges) {
            int u = e.u;
            int v = e.v;
            dCount += (long)(degree[u]-1)*(degree[v]-1);
        }
        long gCount = 0;
        for(int i = 1; i<=N; i++) {
            long n = degree[i];
            if(n>=3) {
                gCount += (n*(n-1)*(n-2))/6;
            }
        }
        if(dCount>gCount*3) {
            System.out.println("D");
        }
        else if(dCount<gCount*3) {
            System.out.println("G");
        }
        else {
            System.out.println("DUDUDUNGA");
        }
    }
}