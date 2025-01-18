import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 집합 정의
        Set<String> set=new HashSet<>();
        // 연산의 수 M을 입력
        int M=Integer.parseInt(br.readLine());
        // M만큼 반복문을 돌리면서 연산 수행
        for(int i=0;i<M;i++){
            String str[]=br.readLine().split(" ");
            String command=str[0];
            switch(command){
                case "add":
                    if(set.contains(str[1])){
                        break;
                    }
                    set.add(str[1]);
                    break;
                case "check":
                    if(set.contains(str[1])){
                        bw.append(1+"\n");
                    }
                    else {
                        bw.append(0+"\n");
                    }
                    break;
                case "remove":
                    if(set.contains(str[1])){
                        set.remove(str[1]);
                    }
                    break;
                case "toggle":
                    if(set.contains(str[1])){
                        set.remove(str[1]);
                    }
                    else{
                        set.add(str[1]);
                    }
                    break;
                case "all":
                    set.clear();
                    for(int j=1;j<=20;j++){
                        set.add(String.valueOf(j));
                    }
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
