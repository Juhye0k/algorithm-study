
import java.io.*;
import java.util.*;

class paper{
    int small;
    int big;
    public paper(int small, int big){
        this.small = small;
        this.big = big;
    }
}

class paperComparator implements Comparator<paper>{
    public int compare(paper p1, paper p2){
        if(p1.small==p2.small)
            return p1.big-p2.big;
        return p1.small-p2.small;
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<paper> papers = new ArrayList<>();
        // 색종이의 장수
        int n=Integer.parseInt(br.readLine());
        // 각 줄에 색종이의 두 변의 길이
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int big=Math.max(a,b);
            int small=Math.min(a,b);
            papers.add(new paper(small,big));
        }
        int ans=0;
        Collections.sort(papers,new paperComparator());
        int dp[]=new int[n];
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(papers.get(j).small<=papers.get(i).small && papers.get(j).big<=papers.get(i).big)
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
            ans=Math.max(ans,dp[i]);
        }
        System.out.println(ans);
    }
}
