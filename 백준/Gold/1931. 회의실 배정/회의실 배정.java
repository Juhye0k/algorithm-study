


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node implements Comparable<Node>{
    int x,y;
    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public int compareTo(Node o) {
        if(this.y==o.y)
            return this.x-o.x;
        return this.y-o.y;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Node> list = new ArrayList<>();

        // 회의의 수
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x,y));
        }
        Collections.sort(list);
        int result =1;
        int endTime = list.get(0).y;

        for(int i=1;i<N;i++){
            Node node =list.get(i);
            if(endTime<=node.x) {
                result ++;
                endTime = node.y;
            }
        }
        System.out.println(result);
    }
}