import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N==1)  {
            ans = 1;
        }
        else choose(0);
        System.out.println(ans);
    }
    public static void choose(int curNum) {
        if(curNum == N ) {
            if(check()) {
                ans++;
            }
            return;
        }
        for(int i=1; i<=4; i++) {
            list.add(i);
            choose(curNum+1);
            list.remove(list.size()-1);
        }
    }
    public static boolean check() {
        int count = 1;
        int beforeNum = list.get(0);
        for(int i=1; i<list.size(); i++) {
            // 전과 같다
            if(beforeNum == list.get(i)) {
                count++;
            }
            // 전과 다르다
            else {
                if(count%beforeNum!=0) {
                    return false;
                } 
                else {
                    beforeNum = list.get(i);
                    count = 1;
                }
            }
        }
        if(count%beforeNum!=0){
            return false;
        } 
        return true;
    }
}
