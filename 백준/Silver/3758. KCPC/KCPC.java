import java.io.*;
import java.util.*;

class Team {
    int teamId;
    int resultPoint;
    int submitCount;
    int count; // 마지막 제출 시간

    public Team(int id) {
        teamId = id;
        resultPoint = 0;
        submitCount = 0;
        count = 0;
    }

    public void set(int resultPoint, int submitCount, int count) {
        this.resultPoint = resultPoint;
        this.submitCount = submitCount;
        this.count = count;
    }
}

class TeamComparator implements Comparator<Team> {
    public int compare(Team t1, Team t2) {
        if (t1.resultPoint == t2.resultPoint) {
            if (t1.submitCount == t2.submitCount) {
                return t1.count - t2.count; // 제출 시간이 빠른 순
            }
            return t1.submitCount - t2.submitCount; // 제출 횟수가 적은 순
        }
        return t2.resultPoint - t1.resultPoint; // 총 점수가 높은 순
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀 개수
            int k = Integer.parseInt(st.nextToken()); // 문제 개수
            int t = Integer.parseInt(st.nextToken()); // 조회할 팀 ID
            int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 개수

         
            int[][] teamScore = new int[n + 1][k + 1];
            List<Team> list = new ArrayList<>();
         
            for (int j = 1; j <= n; j++) {
                list.add(new Team(j));
            }

            int count = 0; // 제출 순서 기록
            for (int j = 0; j < m; j++) {
                count++;
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 팀 번호
                int b = Integer.parseInt(st.nextToken()); // 문제 번호
                int s = Integer.parseInt(st.nextToken()); // 획득한 점수

                Team currentTeam = list.get(a - 1);
             
                if (teamScore[a][b] < s) {
                    int newScore = currentTeam.resultPoint - teamScore[a][b] + s;
                    currentTeam.set(newScore, currentTeam.submitCount + 1, count);
                    teamScore[a][b] = s;
                } else {
                  
                    currentTeam.set(currentTeam.resultPoint, currentTeam.submitCount + 1, count);
                }
            }

            Collections.sort(list, new TeamComparator());
          
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).teamId == t) {
                    bw.write((j + 1) + "\n");
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
