import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
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
        // 생각해봤을 때 한사람씩 온 순서대로 버스에 채워넣으면 된다.
        // 그럴려면 정렬이 빼낼때 수행되는 pq를 사용하는것이 젤 좋다
        // pq에서 빼냈을 때, 현재 버스의 도착시간과 같거나 적은 애들은 즉각 태우면 된다.
        // 단, m명이 되기까지만 태워야 한다.
        // 그러면서 마지막 탔었던 순서가 콘이 되면 최적의 해가 된다.
        // 그런데 어떤상황에서도 같은 시간이면 콘이 뒤로 밀려난다고 조건에 적혀있으므로
        // 콘의 시간은 해당 버스를 마지막에 탔엇던 사람을 밀어내야 하는 시간인 "마지막사람-1"이어야 한다.
        // 그렇게 콘의 시간을 계속 갱신한다.
        // 마지막 버스가 이제 중요한데
        // 마지막 버스에 인원이 다 타지못했다면, startTime에 타면 된다.
        // 주의해야 할 점은, 반복문에 의해 마지막에 한번 t만큼 더하므로, startTime이 마지막보다 더 다음의 버스 도착시간이 된다.
        // 따라서 인원이 풀이 아니라면 t만큼 빼준것이 최적의 해가 된다.
        // 그게 아니라면 마지막 탔던 인원을 밀어내야 하므로 lastTime -1이 정답이 된다.
        // 이 문제에서의 핵심은, 시간:분 으로 되어있는 요소를 하나의 단위로 매핑해주는 작업이 중요하고
        // 다음으로는 최대한 압축해서 버스에 태우는 작업과
        // 마지막에 막차버스를 어떻게 태울것인가에 대한 작업 이 세 가지가 중요하다
        // 유형은 그리디
        int ans = 0;
        int startTime = 540;
        int total = 0;
        int lastTime = 0;
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