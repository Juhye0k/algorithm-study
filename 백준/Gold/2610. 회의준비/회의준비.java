import java.io.*;
import java.util.*;


public class Main {
    static int[] parent;
    static final int INF = 1_000_000;

    static int find(int person) {
        if(parent[person] == person) {
            return person;
        }
        parent[person] = find(parent[person]);
        return parent[person];
    }
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        int[][] dist = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            parent[i] = i;
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }


        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 두 사람의 루트를 연결
            union(a, b);

            if(a!=b) {
                dist[a][b] = dist[b][a] = 1;
            }
        }
        for(int via=1; via<=n; via++) {
            for (int from =1; from<=n; from++) {
                for (int to =1; to<=n; to++) {
                    dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                }
            }
        }

        // 위원회
        Map<Integer, List<Integer>> committees = new HashMap<>();

        // 부모를 찾아 위원회에 넣기
        for (int person = 1; person <=n; person++) {
            int root = find(person);

            if(!committees.containsKey(root)) {
                committees.put(root, new ArrayList<>());
            }
            committees.get(root).add(person);
        }
        List<Integer> representatives = new ArrayList<>();

        for (List<Integer> members : committees.values()) {
            int representative = -1;
            int bestDistance = INF;

            // candidate : 이번에 대표락 가정한 사람
            for (int  candidate : members) {
                int fartest = 0;

                for (int member : members) {
                    fartest = Math.max(fartest, dist[candidate][member]);
                }
                if(fartest<bestDistance) {
                    bestDistance = fartest;
                    representative = candidate;
                }
            }
            representatives.add(representative);
        }
        Collections.sort(representatives);

        StringBuilder out = new StringBuilder();
        out.append(representatives.size()).append('\n'); 

        for (int representative : representatives) {
            out.append(representative).append("\n");
        }
        System.out.println(out);
    }


}