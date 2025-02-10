import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
        static List<List<Integer>> list;
        static boolean[] visited;
        static List<Integer> apple;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        list=new ArrayList<>(); // 간선 정보를 담을 배열
        apple=new ArrayList<>(); // 사과 정보를 담을 배열
        // 정점의 수 n과 k가 공백으로 주어짐
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());   // 정점의 수
        int k=Integer.parseInt(st.nextToken());   // 거리
        visited=new boolean[n];                   // 방문했는지  판단하는  배열

        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
        // 간선의 정보
        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int v1=Integer.parseInt(st.nextToken());
            int v2=Integer.parseInt(st.nextToken());
            list.get(v1).add(v2);
            list.get(v2).add(v1);
        }

        // 사과의 정보
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            apple.add(Integer.parseInt(st.nextToken()));
        }
        // 루트 노드에서 DFS 탐색을 시작 --> 거리가 k이하인 노드를 찾아나가기, dfs의 리턴 값은 수확할 수 있는 사과의 개수로
        int result=DFS(0,0, k);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    public static int DFS(int start, int disc, int limit){
        // 해당 노드 방문을 표시
        visited[start]=true;
        // 사과의 개수를 체크
        int count= apple.get(start);
        // for문을 통해서, 해당 간선과 연결된 노드를 방문함
        if(disc+1<=limit){
            for(int i:list.get(start)){
                if(!visited[i]){
                    count+=DFS(i,disc+1,limit);
                }
            }
        }
        return count;
    }
}

