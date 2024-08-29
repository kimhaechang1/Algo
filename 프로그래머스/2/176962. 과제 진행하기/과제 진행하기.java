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
                //System.out.println("진행중이던: "+go+"현재 시각: "+presentTime);
                //System.out.println("새로 시작하려는: "+newData);
                if(newData.s <= presentTime + go.d) {
                    boolean isSame = false;
                    if(newData.s == presentTime + go.d) {
                        isSame = true;
                    }
                    if(!isSame) {
                        go.setStop(newData.s);
                        go.d -= (newData.s - presentTime);
                        //System.out.println("중단과제 큐에 넣음: "+go);
                        presentTime += (newData.s - presentTime);
                        wait.add(go);
                    } else {
                        //System.out.println("곧바로 시작하면 되서 기존에 껄 종료: "+go);
                        result.add(go.n);
                        presentTime += go.d;
                    }
                    go = newData;
                    pq.poll();
                } else if(newData.s > presentTime + go.d) {
                    boolean useWait = false;
                    //System.out.println("걍 시작하면됨: "+newData);
                    //System.out.println("기존의 진행중이던거 끝냄: "+go);
                    result.add(go.n);
                    if(!wait.isEmpty()) {
                        Data w = wait.poll();
                        //System.out.println("wait에서 꺼냄: "+newData);
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