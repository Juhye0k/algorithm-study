import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
    int start,end;
    public Point(int start, int end) {
        this.start=start;
        this.end=end;
    }

    @Override
    public int compareTo(Point o) {
        return this.end-o.end;
    }
}

public class Main {
    static List<Point> list;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Point(start,end));
        }
        int ans = 0;
        Collections.sort(list);
        int before = 0;
        for(int i=0; i<list.size(); i++) {
            Point temp = list.get(i);
            if(temp.start<=before) continue;
            else before = temp.end;
            ans++;
        }
        System.out.println(ans);
    }
}