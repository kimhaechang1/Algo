import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 1;
        int [][] times = new int[book_time.length][2];
        for(int i= 0;i<book_time.length;i++){
            times[i] = getTime(book_time[i]);
        }
        // 시작시간이 빠른순으로 먼저 조회하되, 끝나는 시간도 빠른순으로 갱신
        // pq로 방관리하면, 특정 방의 새로운 끝나는 시각 갱신을 하기 힘들다.
        // 왜냐하면 정렬순으로 데이터를 빼내고, 새로운 방을 추가하는 시점을 찾을 순 있지만, 해당 방의 갱신시점은 못찾기 때문
        // 따라서 방관리는 pq로 하는것이 아닌 이미 정렬된 상태임을 가정하고 Random Access가 필요한 다른 자료구조를 채택하는것이 좋다.
        Arrays.sort(times, (a, b)->{
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        ArrayList<Integer> rooms = new ArrayList<>();
        rooms.add(times[0][1]);
        for(int i= 1;i<times.length;i++){
            boolean flag = false;
            for(int j = 0;j<rooms.size();j++){
                if(rooms.get(j)+10 <= times[i][0]){
                    rooms.set(j, times[i][1]);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                rooms.add(times[i][1]);
            }
        }
        return rooms.size();
    }
    static int [] getTime(String [] infos){
        int [] arr = new int[2];
        for(int i = 0;i<2;i++){
            String [] frags = infos[i].split(":");
            arr[i] += (Integer.parseInt(frags[0]) * 60 + Integer.parseInt(frags[1]));
        }
        return arr;
    }
    
}