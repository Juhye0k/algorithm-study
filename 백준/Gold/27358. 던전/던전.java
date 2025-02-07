import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {  // 백준 27358
    static final long CAP = (long) 1e18;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> monsterList;
        List<String> operatorList;
        List<Integer> textList;

        int T=Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) {    // 10까지 가능
            int N=Integer.parseInt(br.readLine());  // 첫 줄에 방의 개수 N이 주어짐 - 10^5
            monsterList=new ArrayList<>();
            operatorList=new ArrayList<>();
            textList=new ArrayList<>();
            // N개의 D, 즉 몬스터의 공격력이 공백으로 주어짐 10^8
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                monsterList.add(Integer.parseInt(st.nextToken()));
            }
            st=new StringTokenizer(br.readLine(),"*+",true);
            for(int j=0; j<N; j++){                                // 길이가 N인 문자열 S, 즉 주문서의 보상 종류가 나타남. * 또는 +
                operatorList.add(st.nextToken());
            }
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                textList.add(Integer.parseInt(st.nextToken()));           // R1,...Rn이 공백으로 주어짐  10^8
            }
            long left=1;
            long right=Long.MAX_VALUE;
            long ans=12;
            while(left<=right){
                long mid=left+(right-left)/2;
                long pivot=mid;
                // 각 방을 방문해서 체력을 구해야하는데, 어떻게 효율적으로 가져오지? 함수를 어떻게 세워야할까 일단 순차적으로 방문할 수 밖에 없음
                for(int j=0; j<N; j++){   // 근데 이렇게 while문 안에 반복문 넣으면 터질거같은데
                    int monsterAttack=monsterList.get(j);
                    pivot-=monsterAttack;
                    if(pivot<=0){
                        break;
                    }
                    String operator=operatorList.get(j);
                    int text=textList.get(j);
                    if(operator.equals("+")){
                        if(pivot>CAP-text)
                            pivot=CAP;
                        else
                            pivot+=text;
                    }
                    else {
                        if(text!=0 && pivot>CAP/text){
                            pivot=CAP;
                        }
                        else{
                            pivot*=text;
                        }
                    }
                }
                if(pivot<=0){
                    left=mid+1;
                }
                else{
                    ans=mid;
                    right=mid-1;
                }
            }
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
    }
}
