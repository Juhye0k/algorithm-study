class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int answerLength = Integer.MAX_VALUE;
        int answerLeft = 0;
        int answerRight = 0;
        int sum = 0;
        for(int right = 0; right <sequence.length; right++) {
            sum+=sequence[right];
            
            while(sum>k) {
                sum-=sequence[left];
                left++;
            }
            if(sum==k) {
                int length = right-left + 1;
                
                if(length<answerLength) {
                    answerLength = length;
                    answerLeft = left;
                    answerRight = right;
                }
            }
        }
        return new int[] {answerLeft,answerRight};
    }
}