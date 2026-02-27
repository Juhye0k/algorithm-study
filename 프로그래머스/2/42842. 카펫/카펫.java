class Solution {
    public int[] solution(int brown, int yellow) {
        int total=brown+yellow;
        int[] answer = new int[2];
        for(int ansY=3; ansY<=Math.sqrt(total); ansY++) {
            if(total%ansY!=0) continue;
            int ansX=total/ansY;
            if((ansX-2)*(ansY-2)==yellow) {
                answer[0]=ansX;
                answer[1]=ansY;
                break;
            }
        }
        return answer;

    }
    
}