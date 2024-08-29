import java.util.*;

// 09:48

class Solution {
    static class Data {
        String n;
        int s;
        int d;
        int stop;
        public Data(String name, int start, int duration) {
            n = name;
            s = start;
            d = duration;
        }
        public void setStop(int time) {
            this.stop = time;
        }
        
        public String toString() {
            return "[ name: "+n+", start: "+s+", duration: "+d+", stop: "+stop+"]";
        }
    }
    static int n;
    public String[] solution(String[][] plans) {
        String[] answer = {};
        // 끝나자 마자 새롭게 시작하는 과제가 있는경우는, 보관소로 이동하지 않음
        // 끝나자 마자 보관소의 원소와 new가 둘다 있는경우에는, new를 우선시함
        // 보관소도 가장 최근에 멈춘 과제부터 시작함
        
        // 진행중이었던 과제를 도중에 멈출경우 wait으로 들어가는데, 이때 현재시각 업데이트를 진행한만큼만 업데이트해야하는데, 
        // 새로 시작하는 과제의 시작시각으로 업데이트함
        // 그럴경우 나중에 해당 과제의 남은시각 관리하기가 힘들어짐
        
        n = plans.length;
        Data[] drr = new Data[n];
        for(int i =0;i<n;i++){
            drr[i] = new Data(plans[i][0], convert(plans[i][1]), Integer.parseInt(plans[i][2]));
        }
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b)-> {
            return Integer.compare(a.s, b.s);
        });
        PriorityQueue<Data> wait = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b.stop, a.stop);
        });
        
        for(Data data: drr) {
            pq.add(data);
        }
        
        Data go = pq.poll();
        int presentTime = go.s;
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty() || !wait.isEmpty()) {
            if(!pq.isEmpty()) {
                Data newData = pq.peek();
                if(newData.s <= presentTime + go.d) {
                    boolean isSame = false;
                    if(newData.s == presentTime + go.d) {
                        isSame = true;
                    }
                    if(!isSame) {
                        go.setStop(newData.s);
                        go.d -= (newData.s - presentTime);
                        presentTime += (newData.s - presentTime);
                        wait.add(go);
                    } else {
                        result.add(go.n);
                        presentTime += go.d;
                    }
                    go = newData;
                    pq.poll();
                } else if(newData.s > presentTime + go.d) {
                    boolean useWait = false;
                    result.add(go.n);
                    if(!wait.isEmpty()) {
                        Data w = wait.poll();
                        presentTime += go.d;
                        go = w;
                        useWait = true;
                    } else {
                        go = newData;
                        presentTime = go.s;
                    }
                    
                    if(!useWait) {
                        pq.poll();
                    }
                }
                continue;
            }
            if(!wait.isEmpty()) {
                Data getWait = wait.poll();
                result.add(go.n);
                go = getWait;
                presentTime = getWait.s;
            }
        }
        result.add(go.n);
        answer =new String[result.size()];
        for(int i = 0;i<result.size();i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public int convert(String time) {
        String[] ts = time.split(":");
        return Integer.parseInt(ts[0]) * 60 + Integer.parseInt(ts[1]);
    }
    
}