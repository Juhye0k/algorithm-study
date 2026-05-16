class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int size = money.length;
        int[] dp1 = new int[size];
        int[] dp2 = new int[size];
        // 첫 번째 집 턴 경우
        dp1[0] = money[0];
        dp1[1] = money[0];
        for(int i=2; i<size-1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]); 
        }
        dp2[0] = 0;
        dp2[1] = money[1];
        // 첫 번째 집 안 턴 경우
        for(int i=2; i<size; i++) {
            dp2[i] = Math.max(dp2[i-1],dp2[i-2]+money[i]);
        }
        answer = Math.max(dp1[size-2],dp2[size-1]);
        return answer;
    }
}