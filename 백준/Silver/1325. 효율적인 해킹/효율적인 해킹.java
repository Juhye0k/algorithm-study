import java.io.*;
import java.util.*;

class Node{
    int number;
    int count;
    public Node(int number, int count){
        this.number = number;
        this.count = count;
    }
}

class NodeComparator implements Comparator<Node>{
    public int compare(Node n1, Node n2){
        return n1.number - n2.number;
    }
}

public class Main {
    static List<List<Integer>> graph;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N과 M이 들어온다.
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        graph=new ArrayList<>();
        List<Node> result=new ArrayList<>(); // 가장 큰 결과를 저장할 배열, 여러개 가능하니까
        result.add(new Node(0,0));
        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }
        boolean visited[];
        // M개의 줄에 신뢰하는 관계가 A,B
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }
        for(int i=1;i<=N;i++){
            count=0;
            visited=new boolean[N+1];
            dfs(i,visited);
            if(result.get(0).count==count){   // 만약 최댓값이 같으면
                Node node=new Node(i,count);
                result.add(node);        // 해당 정점 그냥 넣기
            }
            else if(result.get(0).count<count){   // 만약 결과값에 있는 값보다 방금 탐색한게 크다
                result.clear();             // 리스트 싹다 비우고 추가
                Node node=new Node(i,count);
                result.add(node);
            }
        }
        Collections.sort(result,new NodeComparator());
        for(Node i:result){
            bw.write(i.number+" ");
        }
        bw.flush();
        bw.close();

    }
    public static void dfs(int start, boolean[] visited) {
        visited[start] = true;
        count++;
        for (int i : graph.get(start)) {
            if (!visited[i]) {
                dfs(i, visited);
            }
        }
    }
}

