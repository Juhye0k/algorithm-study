
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
    static List<List<Integer>> graph;
    static List<Integer> list1;
    static List<Integer> list2;
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 테스트 케이스 개수 T
        int T=Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            // 트리를 구성하는 노드의 수 N
            int N=Integer.parseInt(br.readLine());
            graph=new ArrayList<>(); // 그래프 생성
            for(int j=0;j<=N;j++){  // 그래프 초기화, 1부터니까 N까지 초기화하기
                graph.add(new ArrayList<>());
            }
            for(int j=0;j<N-1;j++){
                st = new StringTokenizer(br.readLine());
                int parent=Integer.parseInt(st.nextToken());
                int child=Integer.parseInt(st.nextToken());
                graph.get(child).add(parent);               // 간선 그래프에 입력
            }

            // 트리를 구성하는 간선 정보, A,B가 주어지는데 A가 B의 부모라는 뜻, 즉 A-->B, 번호 1 이상
            // 테스트 케이스 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드
            st=new StringTokenizer(br.readLine());
            int num1=Integer.parseInt(st.nextToken());
            int num2=Integer.parseInt(st.nextToken());
            visited=new boolean[N+1];
            list1=new ArrayList<>();
            list2=new ArrayList<>();
            dfs(num1,list1);
            visited=new boolean[N+1];
            dfs(num2,list2);
            for(int j=0;j<list1.size();j++){
                if(list2.contains(list1.get(j))){
                    bw.write(list1.get(j)+"\n");
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int start,List<Integer> list){
        visited[start]=true;
        list.add(start);
        for(int i:graph.get(start)){
            if(!visited[i]){
                dfs(i,list);
            }
        }
    }
}

