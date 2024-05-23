import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 09:00 부터 n번 t분간격으로 m명씩 태운다.?????
        int [] times = new int[timetable.length];
        for(int i= 0;i<times.length;i++){
            String [] frags = timetable[i].split(":");
            times[i] = Integer.parseInt(frags[0]) * 60 + Integer.parseInt(frags[1]);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0;i<times.length;i++){
            pq.add(times[i]);
        }
        // 언제나 09:00 에서 시작하는건데 
        // 결국 가장 늦게 출발하고 싶다면 n번째 셔틀버스가 올때 타면된다.
        int ans = 0;
        int startTime = 540;
        int total = 0;
        int lastTime = 0;
        // 08:00 -> 09:00
        // 09:09, 09:10 -> 09:10
        for(int i = 0;i<n;i++){
            total = 0;
            while(!pq.isEmpty()){
                int select = pq.peek();
                if(select <= startTime && total < m){
                    pq.poll();
                    total++;
                }else break;
                
                lastTime = select-1;
            }
            startTime += t;
        }
        if(total < m) {
            lastTime = startTime - t;
        }
        
        String answer = translate(lastTime);
        return answer;
    }
    static String translate(int time){
        StringBuilder sb = new StringBuilder();
        int hour = 0;
        int minute = 0;
        if(time > 59){
            hour = time / 60;
        }
        time -= (60 * hour);
        minute = time;
        if(hour < 10){
            sb.append("0");
        }
        sb.append(hour).append(":");
        if(minute < 10){
            sb.append("0");
        }
        sb.append(minute);
        return sb.toString();
    }
}