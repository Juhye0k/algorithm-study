


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class Card implements Comparable<Card>{
    int suit;
    int number;
    public Card(int suit, int number) {
        this.suit = suit;
        this.number = number;
    }
    @Override
    public int compareTo(Card o) {
        return this.number - o.number;
    }
}



public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 포커 카드의 무늬  0: 클로버, 1:다이아몬드, 2:하트, 3:스페이드
        // 포커 카드의 숫자 1:에이스 2~10 숫자, 11:잭, 12:퀸, 13:킹
        int [][] ar = new int[4][14];
        List<Card> list = new ArrayList<>();
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int suit = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            Card card = new Card(suit, num);
            list.add(card);
            ar[suit][num]++;

        }
        // 무늬가 같은지, 숫자가 같은 카드가 몇개인지, 연속적인 카드가 몇개인지
        // 집합을 사용해서 카운트하는건 어떨까

        // 이때까지 최댓값
        int max = 0;
        int maxSuit = 0;
        int maxNum = 0;

        for(int i=0; i<4; i++) {
            for(int j=1; j<=13; j++) {
                if(ar[i][j]==1) continue;

                List<Card> temp = new ArrayList<>(list);
                temp.add(new Card(i,j));
                int result = evaluate(temp);
                if(max < result) {
                    max = result;
                    maxSuit = i;
                    maxNum = j;
                }
            }
        }
        System.out.println(maxSuit+" "+maxNum);
    }
    public static int evaluate(List<Card> list) {
        // flush인지
        boolean isFlush = flush(list);
        // 스트레이트인지
        boolean isStraight = straight(list);

        // straight flush
        if(isStraight && isFlush) return 8;



        int [] counts = new int[14];
        for(Card card : list) counts[card.number]++;

        int maxCount = 0;
        int pairCount = 0;
        boolean three = false;
        boolean two = false;

        for(int i=0; i<14; i++) {
            maxCount = Math.max(maxCount, counts[i]);
            if(counts[i] == 2) two = true;
            if(counts[i] == 3) three = true;
            if(counts[i] >= 2) pairCount++;
        }

        // quadruple
        if(maxCount == 4) return 7;
        // full house
        if(three && two) return 6;
        // flush
        if(isFlush) return 5;
        // straight
        if(isStraight) return 4;
        // triple
        if(maxCount == 3) return 3;
        // two pair
        if(pairCount == 2) return 2; // [수정] 1이 아니라 2 리턴

        // pair
        if(pairCount == 1) return 1;
        return 0;
    }
    public static boolean flush(List<Card> list) {
        boolean isFlush = true;
        int suit = list.get(0).suit;
        for(Card card : list) {
            if(card.suit != suit) {
                isFlush = false;
                break;
            }
        }
        return isFlush;
    }
    public static boolean straight(List<Card> list) {
        Collections.sort(list);
        boolean isStraight = true;
        for(int k=0; k<4; k++) {
            if(list.get(k+1).number - list.get(k).number != 1) {
                isStraight = false;
                break;
            }
        }
        return isStraight;
    }
}