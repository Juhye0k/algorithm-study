class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int x=Integer.parseInt(a+""+b);
        int y=2*a*b;
        answer=x>y?x:y;
        return answer;
    }
}