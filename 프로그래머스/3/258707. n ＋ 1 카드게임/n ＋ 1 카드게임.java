class Solution {
    static boolean[] myCard;
    static boolean[] candidate;
    static int size;
    public int solution(int coin, int[] cards) {
        int answer = 1;
        size = cards.length;
        myCard = new boolean[cards.length+1];
        candidate = new boolean[size+1];
        // 1. 맨 처음에 카드 뭉치에서 n/3장을 뽑아 가짐
        for (int i=0; i<size/3; i++) {
            myCard[cards[i]] = true;
        }        
        int index = size/3;
        
        // 카드 뭉치에 남은 카드가 없을 때까지 진행
        while(index < size) {
            candidate[cards[index++]] = true;
            candidate[cards[index++]] = true;
            // 라운드 시작 때 2장을 뽑음
            if(userPair(myCard,myCard)) {
                answer++;
            }
            else if(coin>=1 && userPair(myCard, candidate)) {
                coin -= 1;
                answer++;
            }
            else if(coin>=2 && userPair(candidate, candidate)) {
                coin -= 2;
                answer++;
            }
            else {
                break;
            }
        }
        
        return answer;
    }
    static boolean userPair(boolean[] left, boolean[] right) {
        for(int card = 1; card<=size; card++) {
            int partner = size + 1 - card;
            
            if(left[card] && right[partner]) {
                left[card] = false;
                right[partner] = false;
                return true;
            }
        }
        return false;
    }
  
}