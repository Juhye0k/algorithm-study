import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        HashSet<String> company = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String action = st.nextToken();
            
            if (action.equals("enter")) {
                company.add(name);
            } else {
                company.remove(name);
            }
        }
        
        ArrayList<String> list = new ArrayList<>(company);
        
        Collections.sort(list, Collections.reverseOrder());
        
        StringBuilder sb = new StringBuilder();
        for (String name : list) {
            sb.append(name).append("\n");
        }
        
        System.out.print(sb);
    }
}