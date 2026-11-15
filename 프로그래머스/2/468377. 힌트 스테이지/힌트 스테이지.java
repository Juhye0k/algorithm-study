class Solution {
    static int answer;
    static int size;
    public int solution(int[][] cost, int[][] hint) {
        answer = Integer.MAX_VALUE;
        size = cost.length;
        // i번 힌트 권 -> i번 스테이지에서만 사용
        
        // 각 스테이지에서 힌트를 최대 1개 구매 가능 
        
        // 힌트 번들에는 힌트권이 총 k장, 여러 장 포함 가능
        int[] hintList = new int[size];
        dfs(0,0,hintList, cost, hint);
        // 모든 스테이지를 해결하는 데 필요한 최소 비용 
        return answer;
    }
    public static void dfs(int stage, int money, int[] hintList, int[][] cost, int[][] hint) {
        
        // stage 끝에 도달 -> answer 갱신
        if(stage == size) {
            answer = Math.min(answer, money);
            return;
        }
        // 각 스테이지에서 쿠폰을 구매할지, 말지 결정해야함
        
        // 힌트가 있으면 -> 쓰기
        int hintCount = Math.min(hintList[stage], size-1);
        money += cost[stage][hintCount];
        if (stage == size - 1) {
            dfs(stage + 1, money, hintList, cost, hint);
            return;
        }
        // 쿠폰 쓰는 경우
        for(int i=1; i<hint[stage].length; i++) {
            hintList[hint[stage][i]-1]+=1;
        }
        dfs(stage+1, money + hint[stage][0], hintList, cost, hint);
        for(int i=1; i<hint[stage].length; i++) {
            hintList[hint[stage][i]-1]-=1;
        }
        // 쿠폰 안쓰는 경우
        dfs(stage+1, money, hintList, cost, hint);
    }
}