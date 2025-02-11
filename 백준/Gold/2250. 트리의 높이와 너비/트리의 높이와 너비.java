import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class resultNode{
    int num;
    int depth;
    public resultNode(int num, int depth){
        this.num = num;
        this.depth = depth;
    }
}
class AnswerNode{
    int level;
    int extent;
    public AnswerNode(int num, int extent){
        this.level = num;
        this.extent = extent;
    }
}
public class Main {
    static List<List<Integer>> graph;
    static resultNode map[];
    static List<Integer> depthList;
    static int mapIndex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 노드의 개수를 나타내는 정수 N
        int N=Integer.parseInt(br.readLine());
        // 노드 번호와 해당 노드의 왼쪽 자식, 오른쪽 자식 노드 번호
        // 노드 번호는 1부터 N, 자식이 없으면 -1
        graph=new ArrayList<>();
        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }
        mapIndex=0;
        map=new resultNode[N];                               // 격자 초기화
        depthList=new ArrayList<>();
        boolean root[]=new boolean[N];
        depthList.sort(Collections.reverseOrder());
        AnswerNode answerNode=new AnswerNode(0,0);
        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            int num=Integer.parseInt(st.nextToken());           // 노드 번호 업력
            int child1=Integer.parseInt(st.nextToken());        // 왼쪽 자식 입력
            int child2=Integer.parseInt(st.nextToken());        // 오른쪽 자식 입력
            if(child1!=-1)
                root[child1-1]=true;
            if(child2!=-1)
                root[child2-1]=true;
            graph.get(num).add(child1);
            graph.get(num).add(child2);
        }                                       // 격자의 크기는 노드의 크기인 N만큼. 격자의 순서? --> 중위 탐색으로 DFS 돌려서 리스트에 넣으면 될거 같음
        int start=0;
        for(int i=0;i<N;i++){
            if(root[i]==false)
                start=i+1;
        }
        inOrder(start,1);
        for(int i=depthList.size();i>0;i--){           // 높이 1부터 ~ 탐색
            int min=N+1;
            int max=0;
            for(int j=0;j<map.length;j++){
                if(map[j].depth==i&& j<min)
                    min=j;
                if(map[j].depth==i&&j>max)
                    max=j;
            }
            int result=max-min+1;
            if(answerNode.extent<=result)
                answerNode=new AnswerNode(i,result);
        }
        bw.write(answerNode.level+" "+answerNode.extent);
        bw.flush();
        bw.close();
    }
    public static void inOrder(int num,int depth){     // 중위 순회를 돌리는 이유 --> 가장 왼쪽에 있는 것부터 탐색 가능하므로, 리스트에 올바르게 추가 가능
        if(!depthList.contains(depth))
            depthList.add(depth);
        if(graph.get(num).get(0)!=-1)        // 왼쪽 자식이 있을 경우에만
            inOrder(graph.get(num).get(0),depth+1);  //  탐색
        map[mapIndex++]=(new resultNode(num,depth));                        // 해당 노드 리스트에 추가
        if(graph.get(num).get(1)!=-1)        // 오른쪽 자식이 있을때만
            inOrder(graph.get(num).get(1),depth+1);  // 탐색
    }
}

