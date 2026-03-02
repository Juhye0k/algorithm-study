
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 학번
        int schoolNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();



        if(schoolNum%4763!=0){
            System.out.println(0);
            return;
        }
        long target= schoolNum/4763;
        ArrayList<String> result = new ArrayList<>();
        for(int i=0; i<=200; i++) {
            for(int j=0; j<=200; j++) {
                long case1 = (i*508)+(j*212);
                long case2 = (i*508)+(j*305);
                long case3 = (i*108)+(j*212);
                long case4 = (i*108)+(j*305);
                if(case1 == target || case2 == target || case3 == target || case4 == target)
                    result.add(i+" "+j);
            }
        }
        System.out.println(result.size());
        for(String s : result) {
            System.out.println(s);
        }
    }
}