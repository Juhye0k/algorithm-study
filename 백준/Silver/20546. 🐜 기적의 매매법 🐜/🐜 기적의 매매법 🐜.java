
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 주어진 현금
        int money = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] price = new int[14];
        for(int i=0; i<14; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        /*
        준현
        1. 한번 사면 절대 팔지 않음
        2. 살 수 있으면 최대한 많이 삼
        3. 살 수 있다면 가능한 만큼 즉시 매수
         */
        int count1 = 0;
        int money1 = money;
        /*
        성민
        1. 전량 매수 or 전량 매도
        2. 빚을 내지는 않음
        3. 3일 연속 가격이 전일 대비 상승 -> 다음날 무조건 하락 -> 3일 연속 상승하면, 전량매도
        4. 3일 연속 가격이 전일 대비 하락 -> 다음날 무조건 상승. 전량 매수
         */
        int count2 = 0;
        int money2 = money;
        int upCount = 0;
        int downCount = 0;
        for(int i=0; i<14; i++) {

            // 준현이의 거래
            if(money1>=price[i]) {
                count1 += money1/price[i];
                money1 %= price[i];
            }

            // 성민이의 거래
            if(i>0 && price[i]>price[i-1]) {
                downCount = 0;
                upCount++;
            }
            if(i>0 && price[i]<price[i-1]) {
                upCount = 0;
                downCount++;
            }
            if(i>0 && price[i]==price[i-1]) {
                upCount = 0;
                downCount = 0;
            }
            if(upCount>=3) {
                money2 += price[i] * count2;
                count2 = 0;
            }
            // 3일 연속 전일 대비 하락 -> 전량 매수
            if(downCount>=3 && money2>=price[i]) {
                count2 += money2/price[i];
                money2 %= price[i];
            }
        }
        int result1 = money1+ price[13] * count1;
        int result2 = money2 +price[13] * count2;
        if(result1>result2) {
            System.out.println("BNP");
        }
        else if(result1<result2) {
            System.out.println("TIMING");
        }
        else {
            System.out.println("SAMESAME");
        }
    }

}