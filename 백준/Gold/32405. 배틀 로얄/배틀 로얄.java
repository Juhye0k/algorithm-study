import java.io.*;
import java.util.*;

class Player {
    int index;
    int power;
    int hp;
    int contribution;
    public Player(int index) {
        this.index = index;
        this.contribution=0;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<Player> list=new ArrayList<>();
        Deque<Player> deque=new ArrayDeque<>();
        // 정수 N 입력
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            Player p=new Player(i+1);
            list.add(p);
        }
        // 플레이어 공격력 입력
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            list.get(i).power=Integer.parseInt(st.nextToken());
        }
        // 플레이어 체력 입력
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            list.get(i).hp=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            deque.addFirst(list.get(i));
        }
        long allPower=0;
        while(deque.size()!=1){
            // 턴 플레이어 선정
            Player turnPlayer=deque.pollLast();

            long realAllPower=allPower-turnPlayer.power*turnPlayer.contribution;
            if(turnPlayer.hp-realAllPower<=0)
                continue;

            allPower+=turnPlayer.power;
            turnPlayer.contribution+=1;

            deque.addFirst(turnPlayer);


        }
        System.out.println(deque.remove().index);
    }
}
