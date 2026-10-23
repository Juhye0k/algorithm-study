class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        /*
        징검다리 일렬로, 밟을 때마다 1씩 줄어듬
        0이 되면 더 이상 밟을 수 없고, 한 번에 여러 칸을 건너 뛸 수 있음
        디딤돌이 여러 개인 경우 무조건 가장 가까운 디딤돌로만 건너뛸 수 있음
        */
        int left = 1;
        int right = 0;
        for(int stone : stones) {
            right = Math.max(right, stone);
        }
        while(left<=right) {
            int mid = left + (right-left) / 2;
            if(canCross(stones, k , mid)) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid-1;
            }
        }
        return answer;
       
    }
    static boolean canCross(int[] stones, int k, int people) {
        int count = 0;
        
        for(int stone : stones) {
            if(stone < people) {
                count ++;
            } else {
                count = 0;
            }
            if(count>=k) {
                return false;
            }
        }
        return true;
    }
}