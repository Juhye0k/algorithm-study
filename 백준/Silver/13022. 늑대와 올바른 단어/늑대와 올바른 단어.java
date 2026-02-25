
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int i = 0;
        int len = str.length();
        while(i<len){
            // 첫번째 단어는 w여야 한다
            if(str.charAt(i)!='w'){
                System.out.println(0);
                return;
            }

            // 해당 단어와 똑같을 때까지 길이를 센다 -> w의 길이 저장
            int wCount = 0;
            while(i<len && str.charAt(i)=='w') {
                wCount++;
                i++;
            }
            // 세팅하면서, w의 길이와 다르면 바로 0리턴
            int oCount = 0;
            while(i<len && str.charAt(i)=='o') {
                oCount++;
                i++;
            }
            if(wCount!=oCount){
                System.out.println(0);
                return;
            }
            // o의 길이 세팅
            int lCount = 0;
            while(i<len && str.charAt(i)=='l') {
                lCount++;
                i++;
            }
            if(lCount!=oCount){
                System.out.println(0);
                return;
            }
            // l의 길이 세팅
            int fCount = 0;
            while(i<len && str.charAt(i)=='f') {
                fCount++;
                i++;
            }
            // f의 길이 세팅
            if(fCount!=wCount){
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}