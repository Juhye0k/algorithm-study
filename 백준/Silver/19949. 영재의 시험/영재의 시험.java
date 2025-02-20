import java.io.*;
import java.util.*;
public class Main {
    static BufferedWriter bw;
    static List<Integer> answer;
    static int result;
    static int duplication;
    static int ar[];
    static int point;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        duplication=1;
        result=0;
        answer=new ArrayList<>();
        for(int i=0;i<10;i++){
            answer.add(Integer.parseInt(st.nextToken()));
        }
        ar=new int[11];
        check(0,0,1);
        bw.write(point+"\n");
        bw.flush();
        bw.close();
    }
    public static void check(int depth,int preNum,int count){
        // 선택한 문제의 개수= depth가 10개 --> return
        if(depth==10){
            int result=0;
            for(int i=0;i<10;i++){
                if(answer.get(i)==ar[i])
                    result++;
            }
            if(result>=5)
                point++;
            return;
        }
        for(int i=1;i<=5;i++){
            if(i==preNum) { // 이전 숫자와 같다--> count를 증가시켜줘야함
                if (count == 2)    // 만약 기존에 2번 반복되었는데 또 같은 숫자를 넣으면 안되므로, continue
                    continue;
                ar[depth] = i;
                check(depth + 1, i, count + 1);
            }
            else{              // 이전 숫자와 다를 경우 --> count 초기화
                ar[depth]=i;
                check(depth+1,i,1);
            }
        }
    }
}
