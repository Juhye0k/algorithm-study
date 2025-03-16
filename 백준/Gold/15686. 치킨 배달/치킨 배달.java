import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static List<Node> chickens;
    static int lastResult;
    static List<Node> selected;
    static int M;
    static List<Node> houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        lastResult=Integer.MAX_VALUE;
        int N=Integer.parseInt(st.nextToken()); // 크기가 N인 도시를 정의한다.
        M=Integer.parseInt(st.nextToken()); // 치킨칩은 최대 M개이다. 이후에 치킨집을 선택할 때 M개만큼 선택해야한다.
        selected=new LinkedList<>();        // M개만큼 선택을 해야할 때 사용하는 배열
        houses = new ArrayList<>();         // 집의 위치를 저장하는 배열
        chickens = new ArrayList<>();       // 치킨집의 배열을 저장하는 배열
        for(int i=1;i<=N;i++){              // 배열 정보 입력받기
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int num=Integer.parseInt(st.nextToken());
                if (num == 1) houses.add(new Node(i, j));              // 1이면 집
                else if (num == 2) chickens.add(new Node(i, j));       // 2면 치킨집
            }
        }
        backtracking(0,0);   // 백트래킹 시작
        System.out.println(lastResult);

    }
    public static void backtracking(int index, int count){
        if(count==M){
            int result=0;
            for(Node house:houses){
                int distance=Integer.MAX_VALUE;
                for(Node chicken:selected){
                    int i = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                    distance=Math.min(distance,i);
                }
                result+=distance;
            }
            lastResult=Math.min(result,lastResult);
            return;
        }
        if (index == chickens.size()) return; // 기저 조건 중요!! 더 이상 선택할 치킨집이 없으면 종료해야함

        selected.add(chickens.get(index));           // 치킨집을 하나씩 선택한다.
        backtracking(index+1,count+1);   // 재귀적으로 호출해서 인덱스를 늘려간다.
        selected.remove(selected.size()-1);    // 백트래킹을 통해 다음 인덱스 탐색
        backtracking(index+1,count);
    }
}

