import java.io.*;
import java.util.*;

class Person {
    int id;
    long productCont;  // 상품 개수는 큰 수일 수 있으므로 long
    int countId;
    long finishTime;   // 계산 종료시간도 long

    public Person(int id, long productCont) {
        this.id = id;
        this.productCont = productCont;
    }
}

class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        // 종료시간이 작은 순으로 정렬하고, 종료시간이 같으면 계산대 번호가 큰 순으로 정렬
        if (p1.finishTime == p2.finishTime) {
            return p2.countId - p1.countId;
        } else {
            return Long.compare(p1.finishTime, p2.finishTime);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out));

        // 사용 가능한 계산대 번호를 저장하는 우선순위 큐 (번호가 작은 계산대 우선)
        PriorityQueue<Integer> waitQueue = new PriorityQueue<>();
        // 계산 진행중인 고객을 종료시간 순으로 관리하는 우선순위 큐
        PriorityQueue<Person> processQueue = new PriorityQueue<>(new PersonComparator());
        // 아직 계산대에 배정되지 않은 고객들의 큐
        Queue<Person> personQueue = new LinkedList<>();

        long result = 0;
        List<Integer> resultList = new ArrayList<>();

        // N과 k 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 고객 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            long productCont = Long.parseLong(st.nextToken()); // long으로 입력
            Person person = new Person(id, productCont);
            personQueue.add(person);
        }

        // 계산대 번호 1~k를 waitQueue에 넣기
        for (int i = 0; i < k; i++) {
            waitQueue.add(i + 1);
        }

        // 각 계산대의 현재 누적 처리시간 (초기값 0), 계산대 번호는 1부터 k까지 사용하므로
        long[] regTime = new long[k + 1];

        // 고객 대기 또는 계산중인 고객이 남아있을 동안 반복
        while (!personQueue.isEmpty() || !processQueue.isEmpty()) {
            // 사용 가능한 계산대가 있으면 대기 고객을 할당
            while (!waitQueue.isEmpty() && !personQueue.isEmpty()) {
                int countId = waitQueue.poll();  // 사용 가능한 계산대 번호
                Person person = personQueue.poll(); // 대기 고객
                // 고객의 종료시간 계산: 현재 계산대 누적시간 + 고객의 물건 개수
                long finishTime = regTime[countId] + person.productCont;
                person.finishTime = finishTime;
                person.countId = countId;
                // 계산대의 누적시간 업데이트
                regTime[countId] = finishTime;
                processQueue.add(person);
            }
            if (!processQueue.isEmpty()) {
                // 계산이 끝나는 고객(가장 빠른 종료시간)을 꺼냄
                Person outPerson = processQueue.poll();
                resultList.add(outPerson.id);
                // 해당 계산대는 이제 사용 가능하므로 waitQueue에 다시 삽입
                waitQueue.add(outPerson.countId);
                // 동시에 계산을 마친 다른 고객들이 있다면 모두 처리
                while (!processQueue.isEmpty() && processQueue.peek().finishTime == outPerson.finishTime) {
                    Person sameTimePerson = processQueue.poll();
                    resultList.add(sameTimePerson.id);
                    waitQueue.add(sameTimePerson.countId);
                }
            }
        }

        // 최종 결과 계산: (순서) * (고객 id)의 합
        int index = 1;
        for (int id : resultList) {
            result += (long) index * id;
            index++;
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
