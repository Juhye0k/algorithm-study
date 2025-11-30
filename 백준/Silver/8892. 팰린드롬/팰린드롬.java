import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스의 개수 T
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            boolean answer = false;
            List<String> list = new ArrayList<>();

            // 입력 단어의 개수
            int N = Integer.parseInt(br.readLine());
            for(int j=0; j<N; j++){
                String word = br.readLine();
                list.add(word);
            }
            String str="";

            // 문자열 하나씩 꺼내서 조합하기
            searchLoop:
            for(int j=0; j<list.size(); j++){
                for(int k=0; k<list.size(); k++) {
                    if(j==k) continue;
                    // 조합한 문자열
                    str = list.get(j) + list.get(k);

                    if(isPalindrome(str)) {
                        sb.append(str).append("\n");
                        answer = true;
                        break searchLoop;
                    }
                }
            }
            if(!answer) sb.append(0+"\n");
        }
        System.out.println(sb);
    }
    public static boolean isPalindrome(String str){
        int ptr1 = 0;
        int ptr2 = str.length()-1;
        while(ptr1 < ptr2){
            if(str.charAt(ptr1) != str.charAt(ptr2)){
                return false;
            }
            ptr1++;
            ptr2--;
        }
        return true;
    }
}
