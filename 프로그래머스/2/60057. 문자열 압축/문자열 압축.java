class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int length = s.length();
        for(int i=1; i<=length; i++) {
            // i 단위로 자른다. 이전 문자열을 기록해둔다
            String prev ="";
            int count = 0;
            String result = "";
            for(int j=0; j<length; j+=i) {
               
                String m = s.substring(j,Math.min(j+i,length));
                if(prev.equals(m)) count++;
                else {
                    if(count>1) {
                        result += count;
                    }
                    result+=prev;
                    prev = m;
                    count = 1;
                }
            }
            if(count>1) {
                result+=count;
            }
            result+=prev;
                    answer = Math.min(answer,result.length());

        }
        return answer;
    }      

}
