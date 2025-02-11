import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static List<List<Integer>> graph;
    static boolean visited[];
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int start=0;
        // 트리의 노드의 개수 N
        int N=Integer.parseInt(br.readLine());
        // 각 노드의 부모가 주어짐, 부모가 없으면 -1
        StringTokenizer st=new StringTokenizer(br.readLine());
        graph=new ArrayList<>();
        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }
        visited=new boolean[N];
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(st.nextToken());  // i번 노드의 부모 입력
            if(num==-1)                                // 루트 노드일 때 --> DFS 시작점으로 설정
                start=i;
            else                                      // 아니다 --> 부모 추가
                graph.get(num).add(i);
        }
        // 지울 노드의 번호
        int num=Integer.parseInt(br.readLine());
        result=0;
        // DFS로 탐색을 하다가, 해당 노드의 graph에서 탐색할게 없다--> 리프 노드이다, 즉 count+=1;
        // 만약에 지우고자 하는 노드를 만났다?--> dfs 하면 안됨
        dfs(start,num);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    public static void dfs(int start, int delNum){
        if(start==delNum)
            return;
        visited[start]=true;
        int childCount=0;
        for(int i:graph.get(start)){               // 연결된 정점 탐색 ㄱㄱ
            if(i==delNum)
                continue;                        // 만약 해당 정점이 삭제될 정점이 아니다?
            childCount++;
            dfs(i,delNum);
        }
        if(childCount==0)
            result++;
    }
}

