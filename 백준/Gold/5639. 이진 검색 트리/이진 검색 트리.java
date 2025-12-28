
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class Main {

    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input=br.readLine())!=null && !input.isEmpty())
            list.add(Integer.parseInt(input));
        Preorder(0,list.size()-1);

    }
    public static void Preorder(int n, int end) {
        if(n>end) return;
        // 경계를 찾기
        int mid = n+1;
        while(mid<=end && list.get(mid)<list.get(n))
            mid++;
        Preorder(n+1,mid-1);
        Preorder(mid,end);
        System.out.println(list.get(n));
    }
}