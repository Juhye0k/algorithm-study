


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node{
    int id;
    int time;
    public Node(int id, int time) {
        this.id = id;
        this.time = time;
    }
}
class LatePeople implements Comparable<LatePeople>{
    int id;
    int time;
    int arrive;
    public LatePeople(int id, int time, int arrive) {
        this.id = id;
        this.time = time;
        this.arrive = arrive;
    }
    @Override
    public int compareTo(LatePeople o) {
        return this.arrive - o.arrive;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Node> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // 오픈 시 대기하는 손님
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int lateIdx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            q.add(new Node(id, time));
        }
        List<LatePeople> latePeople = new ArrayList<>();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            latePeople.add(new LatePeople(id, time, arrive));
        }
        Collections.sort(latePeople);
        int time = 0;
        while (time < W) {
            // 맨 앞 손님 받기
            Node current = q.poll();
            int useTime = Math.min(current.time, T);
            for (int i = 0; i < useTime; i++) {
                if (time >= W) break;
                sb.append(current.id).append("\n");
                time++;
            }
            if (time >= W) break;
            while (lateIdx < M && latePeople.get(lateIdx).arrive <= time) {
                LatePeople lp = latePeople.get(lateIdx++);
                q.add(new Node(lp.id, lp.time));
            }
            current.time -= useTime;
            if (current.time > 0) {
                q.add(current);
                // 은행 직원이 처리하고 있는 고객 id -> 큐의 맨앞
            }
        }
        System.out.println(sb);

    }
}