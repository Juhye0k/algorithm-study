import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;




public class Main{

    static int [] parent;
    static int [] rank;

    public static void makeSet(int n){
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
    public static int find(int n){
        if(parent[n] != n){
            parent[n] =find(parent[n]);
        }
        return parent[n];
    }
    public static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot != bRoot){  // 같은 집합이 아닐 때만 union
            if(rank[aRoot] < rank[bRoot]){
                parent[aRoot] = bRoot;
            }
            else if(rank[aRoot] > rank[bRoot]){
                parent[bRoot] = aRoot;
            }
            else{
                parent[bRoot] = aRoot;
                rank[aRoot]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 사람의 수 N과 파티의 수 M이 주어짐
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        makeSet(N);
        // 이야기의 진실을 아는 사람의 수와 번호
        Set<Integer> answerPeople = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int answerNum = Integer.parseInt(st.nextToken());
        if(answerNum != 0){
            for(int i = 0; i < answerNum; i++){
                answerPeople.add(Integer.parseInt(st.nextToken()));
            }
        }
        List<List<Integer>> parties = new ArrayList<>();
        // M개의 줄에 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어짐
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());
            List<Integer> partyPeople = new ArrayList<>();
            for(int j=0; j<partyNum; j++){
                partyPeople.add(Integer.parseInt(st.nextToken()));
            }
            for(int j=0; j<partyPeople.size()-1; j++){
                union(partyPeople.get(j), partyPeople.get(j+1));
            }
            parties.add(partyPeople);
        }
        Set<Integer> connectedTruth = new HashSet<>();
        for(int truthPerson : answerPeople){
            int root = find(truthPerson);
            for(int i=1; i<=N; i++){
                if(find(i) == root){
                    connectedTruth.add(i);
                }
            }
        }
        int answer = 0;
        for(List<Integer> party : parties){
            boolean isTrue = true;
            for(int person : party){
                if(connectedTruth.contains(person)){
                    isTrue = false;
                    break;
                }
            }
            if(isTrue){
                answer++;
            }
        }
        System.out.println(answer);
    }
}
