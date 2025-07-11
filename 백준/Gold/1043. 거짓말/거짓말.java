import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;




public class Main{

    static int[] parent;
    static int[] rank;

    public static void makeSet(int n){
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i =1; i<=n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot != yRoot){
            if(rank[xRoot]<rank[yRoot]){
                parent[xRoot] = yRoot;
            }
            else if(rank[xRoot]>rank[yRoot]){
                parent[yRoot] = xRoot;
            }
            else{
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 파티의 수

        makeSet(N);

        // 진실을 아는 사람의 수와 번호
        st = new StringTokenizer(br.readLine());
        int answerNum = Integer.parseInt(st.nextToken());
        Set<Integer> answerPeople = new HashSet<>();

        for(int i = 0; i<answerNum; i++){
            answerPeople.add(Integer.parseInt(st.nextToken()));
        }

        List<List<Integer>> parties = new ArrayList<>();
        // 각 파티마다 오는 사람의 수와 번호
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            // 파티에 오는 사람의 수
            int num = Integer.parseInt(st.nextToken());
            // 사람의 번호
            List<Integer> party = new ArrayList<>();
            for(int j = 0; j<num; j++){
                // 해당 파티에 오는 사람들을 저장
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
            // 같은 파티에 참석한 사람들을 같은 파티로 연결

            for(int j=0; j<party.size()-1; j++){
                union(party.get(j), party.get(j+1));
            }
        }
        Set<Integer> connectedTruth = new HashSet<>();
        for(int truthPerson : answerPeople){
            int root = find(truthPerson);
            for(int i=1 ;i<=N; i++){
                if(find(i) == root){
                    connectedTruth.add(i);
                }
            }
        }

        int answer = 0;
        for(List<Integer> party : parties){
            boolean canLie = true;
            for(int person : party){
                if(connectedTruth.contains(person)){
                    canLie =false;
                    break;
                }
            }
            if(canLie){
                answer++;
            }
        }
        System.out.println(answer);
    }
}
