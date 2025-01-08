import java.io.*;
import java.util.*;

class Problem {
    int number;
    int difficult;

    public Problem(int number, int difficult){
        this.number = number;
        this.difficult = difficult;
    }
}

class DifficultComparator implements Comparator<Problem> {
    @Override
    public int compare(Problem p1, Problem p2) {
        if(p1.difficult==p2.difficult){
            return p2.number - p1.number;
        }
        else
            return p2.difficult - p1.difficult;
    }
}

class EasyComparator implements Comparator<Problem> {

    @Override
    public int compare(Problem p1, Problem p2) {
        if(p1.difficult==p2.difficult){
            return p1.number - p2.number;
        }
        else
            return p1.difficult - p2.difficult;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        TreeSet<Problem> difficultSet = new TreeSet<>((p1, p2) -> {
            if (p1.difficult == p2.difficult) {
                return p2.number - p1.number; // 난이도가 같으면 문제 번호 내림차순
            }
            return p2.difficult - p1.difficult; // 난이도 내림차순
        });

        TreeSet<Problem> easySet = new TreeSet<>((p1, p2) -> {
            if (p1.difficult == p2.difficult) {
                return p1.number - p2.number; // 난이도가 같으면 문제 번호 오름차순
            }
            return p1.difficult - p2.difficult; // 난이도 오름차순
        });

        HashMap<Integer,Problem> map=new HashMap<>();
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int number=Integer.parseInt(st.nextToken());
            int difficult=Integer.parseInt(st.nextToken());
            Problem problem=new Problem(number,difficult);

            difficultSet.add(problem);
            easySet.add(problem);
            map.put(number,problem);
        }

        int M= Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            // 연산자 입력
            String calculator=st.nextToken();

            // add 일 때
            if(calculator.equals("add")){
                int number=Integer.parseInt(st.nextToken());
                int difficulty=Integer.parseInt(st.nextToken());
                Problem problem=new Problem(number,difficulty);
                difficultSet.add(problem);
                easySet.add(problem);
                map.put(number,problem);
            }

            // recommend 일 때
            else if(calculator.equals("recommend")){
                String type=st.nextToken();
                if(type.equals("1")){
                    int number=difficultSet.first().number;
                    bw.write(number+"\n");
                }
                else if(type.equals("-1")){
                    int number=easySet.first().number;
                    bw.write(number+"\n");
                }
            }

            // solved 일 때
            else if(calculator.equals("solved")){
                int number = Integer.parseInt(st.nextToken());
                Problem problem = map.get(number);

                if (problem != null) {
                    difficultSet.remove(problem);
                    easySet.remove(problem);
                    map.remove(number);
                }

            }
        }
        bw.flush();
    }
}