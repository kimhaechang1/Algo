import java.util.*;

class Solution {
    static class Data implements Comparable<Data>{
        int value;
        int idx;
        public Data(int value, int idx){
            this.value = value;
            this.idx = idx;
        }
        public int compareTo(Data o){
            return this.value - o.value;
        }
    }
    public int solution(int[] people, int limit) {
        // 압축형 greedy 문제
        int answer = 0;
        Arrays.sort(people);
        int s = 0;
        int e = people.length -1;
        while(s <= e){
            if(s == e){
                answer++;
                break;
            }
            // 가장 최소인 s가 가리키는 값과 더했는데도 크면 걘 혼자타야함
            if(people[s] + people[e] > limit){
                answer++;
                e--;
            }else{
                answer++;
                s++;
                e--;
            }
        }
      
        

        return answer;
    }
}