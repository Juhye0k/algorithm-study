

import java.awt.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;





public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String B = br.readLine();
        String[] arr1 = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] arr2 = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        // 최대 100 -> 문자열이 길어봤자 4글자임. 알파벳 하나하나씩 재배치 한다고 하면 8!
        // 따라서, 브루트포스로 돌릴 수 있다
        for(int i = 1; i<=100; i++) {
            int ten = i / 10;
            int one = i % 10;
            String str = arr2[ten] + arr1[one];

            int[] st1 = check(str);
            int[] st2 = check(B);
            boolean isTrue = true;
            for(int j=0; j<26; j++) {
                if(st1[j]!=st2[j]){
                    isTrue = false;
                }
            }
            if(isTrue){
                System.out.println(str);
                break;
            }
        }
    }
    public static int[] check(String str) {
        int[] arr = new int[26];
        for(int i=0; i<str.length(); i++) {
            arr[str.charAt(i)-'A']++;
        }
        return arr;
    }
}