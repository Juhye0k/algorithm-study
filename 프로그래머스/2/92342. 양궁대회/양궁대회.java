class Solution {
    static int[] answer;
    static int[] info;
    static int maxDiff;
    public int[] solution(int n, int[] info) {
        this.info = info;
        /*
        K점 -> 점수를 한 번만 가져감
        - a == b인 경우, 어피치가 더 많은 점수를 가져감
        
        라이언이 이기기 위해 어떤 과녁 점수에 맞혀야 하는지
        n : 화살의 개수, 1<= n <= 10
        info : 어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 0~11
        
        가장 낮은 점수를 더 많이 맞힌 경우를 return
        
        우승할 방법이 없으면 -1
        
        1. 라이언이 맞힌 화살 개수를 설정한다.
        2. 어치피, 라이언의 점수를 계산한다.
        3. 점수 차가 큰 것을 정답으로 고르고, 만약 같다면 맞힌 화살 개수가 더 많은 것 선택
        */
        
        /*
        가장 큰 점수차로 이기려면
        - 우선, 높은 점수부터 맞춰야한다.
        - 어피치보다 더 많이 맞추게되면 그때부터 더 안맞춰도됨. 다음으로 넘어가기
        - 이때 후에 나오는게 정답임. 낮은 점수를 더 많이 맞추는게 정답이니
        - 그럼 난 여기서 어떻게 완전 탐색을 하지? 11중 for문은 에바고 dfs 백트래킹?
        */
        answer = new int[]{-1};
        maxDiff = 0;
        dfs(0,n,new int[11]);
        return answer;
    }
    public static void dfs(int index, int remaining, int[] lion) {
        if(index == 10) {
            lion[10] = remaining;
            int[] result = calculate(lion);
            int diff = result[1] - result[0];

            if(diff>0) {
                if(diff>maxDiff
                  || (diff == maxDiff && prefer(lion))) {
                    maxDiff = diff;
                    answer = lion.clone();
                }
            }
            lion[10] = 0;
            return;
        }
        
        int need = info[index] + 1;
        
        if(remaining >= need) {
            lion[index] = need;
            dfs(index+1, remaining-need, lion);
            lion[index] = 0;
        }
        dfs(index+1, remaining, lion);
    }
    public static int[] calculate(int[] lion) {
        int apeechScore =0, lionScore= 0;
        for(int i=0; i<=10; i++) {
            if(lion[i]>info[i]) {
                lionScore += 10-i;
            } else if(info[i]>0) {
                apeechScore += 10-i;
            }
           
        }
        return new int[]{apeechScore, lionScore};
    }
    // 점수 차이가 같은 두 배치 중에서 낮은 점수에 화살을 더 많이 쏜 배치를 고름
    public static boolean prefer(int[] lion) {
        for(int i=10; i>=0; i--) {
            if(lion[i] != answer[i]) {
                return lion[i] > answer[i];
            }
        }
        return false;
    }
}