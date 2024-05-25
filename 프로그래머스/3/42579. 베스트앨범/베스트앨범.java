import java.util.*;

class Solution {
    static HashMap<String, Data> map;
    static class Data implements Comparable<Data>{
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int totalPlay;
        public int compareTo(Data o){
            return o.totalPlay - this.totalPlay;
        }
    }
    static PriorityQueue<Data> pq;
    static class Node implements Comparable<Node>{
        int idx;
        int play;
        public Node(int idx, int play){
            this.idx = idx;
            this.play = play;
        }
        
        public int compareTo(Node o){
            if(o.play == this.play){
                return this.idx - o.idx;
            }
            return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        // 속한 노래가 많이 재생된 장르를 먼저 수록
        // 장르 내에서 많이 재생된 노래를 수록
        // 장르 내에서 재생횟수가 같은 노래중에서는 고유번호가 낮은 노래를 먼저
        // 일단 장르별로 재생 횟수 합을 구해야함
        // 그다음, 장르 내에서 재생수 높은거 먼저 그리고 고유번호가 낮은거 
        // 여기서 두개까지만 뽑아야함
        map = new HashMap<>();
        for(int i= 0;i<plays.length;i++){
            if(map.containsKey(genres[i])){
                Data current = map.get(genres[i]);
                current.totalPlay += plays[i];
                current.pq.add(new Node(i, plays[i]));
            }else{
                map.put(genres[i], new Data());
                Data current = map.get(genres[i]);
                current.totalPlay += plays[i];
                current.pq.add(new Node(i, plays[i]));
            }
        }
        pq = new PriorityQueue<>();
        for(Map.Entry<String, Data> entry: map.entrySet()){
            pq.add(entry.getValue());
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()){
            int cnt = 0;
            Data current = pq.poll();
            while(!current.pq.isEmpty() && cnt < 2){
                Node node = current.pq.poll();
                result.add(node.idx);
                cnt++;
            }
        }
        answer = new int[result.size()];
        for(int i= 0;i<answer.length;i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}