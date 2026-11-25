import java.io.*;
import java.util.*;


public class Main {
    static int[] parent;
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a!=b) {
            parent[a] = b;
            return true;
        }
        return false;
    }
    static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        int answer = 1;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a,b)) {
                System.out.println(answer);
                return;
            }
            answer++;

        }
        System.out.println(0);
    }


}