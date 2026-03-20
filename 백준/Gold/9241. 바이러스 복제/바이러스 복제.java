import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String str1 = br.readLine();
        String str2 = br.readLine();

        int left1 = 0, left2 = 0;
        int right1 = str1.length()-1, right2 = str2.length()-1;

        // 왼쪽 ㄱㄱ
        while(left1<str1.length() && left2<str2.length()) {
            if(str1.charAt(left1) == str2.charAt(left2)) {
                left1++;
                left2++;
            }
            else {
                break;
            }
        }
        // 오른쪽 ㄱㄱ
        while(right1>=left1 && right2>=left2) {
            if(str1.charAt(right1) == str2.charAt(right2)) {
                right1--;
                right2--;
            }
            else {
                break;
            }
        }
        System.out.println(right2 - left2 + 1);
    }
}

