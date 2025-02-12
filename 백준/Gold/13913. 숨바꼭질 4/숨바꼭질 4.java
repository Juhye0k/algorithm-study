import java.io.*;
import java.util.*;

class multiverse{
    int xPos;
    int time;

    public multiverse(int xPos, int time){
        this.xPos = xPos;
        this.time = time;
    }
}
public class Main {
    static Queue<multiverse> q;
    static boolean visited[];
    static int N,K;
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        q=new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 수빈이가 있는 위치 N
        N = Integer.parseInt(st.nextToken());
        // 동생이 있는 위치 K
        K = Integer.parseInt(st.nextToken());
        List<Integer> path=new ArrayList<>();
        visited=new boolean[100001];
        parent=new int[100001];
        int result=bfs(N);
        bw.write(result+"\n");
        for(int i=K; i!=N; i=parent[i]){
            path.add(i);
        }
        path.add(N);
        Collections.reverse(path);
        for(int i:path)
            bw.write(i+" ");
        bw.flush();
        bw.close();
    }
    public static int bfs(int num){
        q.add(new multiverse(num,0));
        visited[num]=true;

        while(!q.isEmpty()){
            multiverse temp=q.poll();
            if(temp.xPos==K){
                return temp.time;
            }
            insert(temp);
        }
        return -1;
    }
    public static boolean check(int num){
        return (num>=0 && num<=100000)&&!visited[num];
    }
    public static void insert(multiverse x){
        if(check(x.xPos-1)){
            visited[x.xPos-1]=true;
            parent[x.xPos-1]=x.xPos;
            q.add(new multiverse(x.xPos-1,x.time+1));
        }
        if(check(x.xPos+1)) {
            visited[x.xPos+1]=true;
            parent[x.xPos+1]=x.xPos;
            q.add(new multiverse(x.xPos+1,x.time+1));
        }

        if(check(x.xPos*2)){
            visited[x.xPos*2]=true;
            parent[x.xPos*2]=x.xPos;
            q.add(new multiverse(x.xPos*2,x.time+1));
        }
    }
}

