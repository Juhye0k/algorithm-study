import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int weightSum = 0;
        int sCount = 0;
        int[] sAccumul = new int[n];

        int maxLen = -1;

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);

            if (c == 'S') {
                weightSum += 2;
                sCount++;
            } else if (c == 'K') {
                weightSum -= 1;
            }

            sAccumul[i] = sCount; // 현재 위치까지 S가 몇 개 나왔는지 저장

            if (map.containsKey(weightSum)) {
                int prevIdx = map.get(weightSum);
                int prevSCount = (prevIdx == -1) ? 0 : sAccumul[prevIdx];
                if (sCount > prevSCount) {
                    maxLen = Math.max(maxLen, i - prevIdx);
                }
            } else {
                map.put(weightSum, i);
            }
        }

        System.out.println(maxLen);
    }
}