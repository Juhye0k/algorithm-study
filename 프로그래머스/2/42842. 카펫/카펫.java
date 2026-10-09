class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int total = brown + yellow;
        // 가로 * 세로 = 총 칸수
        for(int height = 3; height*height<=total; height ++ ) {
            if(total%height!=0) continue;
            int width = total / height;
            if((width-2)*(height-2)==yellow)  {
                return new int[]{width, height};
            }
        }
        return answer;
    }
}