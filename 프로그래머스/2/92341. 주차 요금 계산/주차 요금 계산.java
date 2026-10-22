import java.util.*;
class Car {
    int time;
    String status;
    
    public Car(int time, String status) {
        this.time = time;
        this.status = status;
    }
}
class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 기본 시간
        int normalTime = fees[0];
        // 기본 요금
        int money = fees[1]; 
        // 단위 시간
        int dTime = fees[2];
        // 단위 요금
        int dMoney = fees[3];
        Map<Integer,List<Car>> map = new TreeMap<>();
        for(int i=0; i<records.length; i++) {
            String[] str = records[i].split(" ");
            // 시간
            int carTime = calculate(str[0]);
            // 번호
            int carNumber = Integer.parseInt(str[1]);
            // 상태
            String carStatus = str[2];
            List<Car> list = map.getOrDefault(carNumber, new ArrayList<>());
            list.add(new Car(carTime, carStatus));
            map.put(carNumber,list);
        }
        int[] answer = new int[map.size()];
        int index = 0;
        for(List<Car> list : map.values()) {
            int inTime = 0;
            int totalTime = 0;
            boolean parked = false;
            
            for(Car car : list) {
                if(car.status.equals("IN")) {
                    inTime = car.time;
                    parked = true;
                }
                else {
                    totalTime += car.time - inTime;
                    parked = false;
                }
            }
            int result = 0;
            if(parked) {
                totalTime += calculate("23:59")-inTime;
            }
            if(totalTime<=normalTime) {
                result = money;
            }
            else {
                int n1 = (totalTime-normalTime)/dTime;
                int n2 = (totalTime-normalTime)%dTime;
                if(n2!=0) n1++;
                result = money + n1 * dMoney;
            }
            answer[index++] = result;
        }
        
        return answer;
    }
    public static int calculate(String time) {
        String[] s = time.split(":");
        int times = Integer.parseInt(s[0]) * 60;
        int minute = Integer.parseInt(s[1]);
        
        return times+minute;
    }
}