class Solution {
    public int solution(int[][] signals) {
        int answer = 0;
        int repeat = -1;
        
        int n = signals.length;
        int[] periods = new int[n];
        
        
        for(int i=0; i<n; i++) {
            periods[i] = signals[i][0] + signals[i][1] + signals[i][2];
            repeat = lcm(repeat, periods[i]);
        }
        
        for(int t=1; t<=repeat; t++) {
            boolean allYellow = true;
            
            for(int i=0; i<n; i++) {
                int g = signals[i][0];
                int y = signals[i][1];                
                int position = (t-1) % periods[i];
                
                if(position<g || position >= g+y) {
                    allYellow = false;
                    break;
                }
            }
            
            if(allYellow) return t;
        }
        return -1;
        
    }
    public static int gcd(int a, int b) {
        while (b!=0) {
            int remainder = a%b;
            a = b;
            b = remainder;
        }
        return a;
    }
    
    public static int lcm(int a, int b) {
        return a / gcd(a,b) * b;
    }
}