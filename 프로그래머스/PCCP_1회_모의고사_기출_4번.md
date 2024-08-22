<a href="https://school.programmers.co.kr/learn/courses/20847/lessons/255903#"> 문제 바로가기 </a>

초견에 풀었는가? : X

이해한 만큼

- 우선순위큐를 사용해야함을 인지함 (자료구조 문제임을 이해)
- 도착시각순으로 정렬된 자료구조와, 문제의 실행순서 조건을 고려하는 자료구조 총 2개가 필요함을 인지함
- 특정 프로그램이 실행중일때 대기큐에 들어가야하는 프로그램의 조건을 인지함

헷갈린 순간들

- 우선순위를 헷갈림 ( 우선순위값이 먼저 고려해야 하냐랑 도착시간을 먼저 고려해야하냐 )
- 실행 대기큐를 처리하는 방법을 잘못생각함 ( 한번에 하나만 처리가능함을 인지하지 못함 )

CODE
```java
import java.util.*;

class Solution {
    public long[] solution(int[][] programs) {
        long[] answer = new long[11];
        // 프로그램의 우선순위와 호출된 시각에 따라 실행순서를 결정
        // 점수가 낮을수록 우선순위가 높음
        // 프로그램이 호출되면 대기상태에 있다가 자기 순서가 되면 실행시간동안 실행된뒤 종료
        // 우선순위가 같은 프로그램은 먼저 호출된것이 먼저 실행됨
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> {
            if(a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });


        Arrays.sort(programs, (a, b) -> {
            if(a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int len = programs.length;


        // answer[0]: 모든 프로그램이 종료되는 시각
        // answer[n]: 프로그램점수가 n인 프로그램들의 대기시간의 합

        int ptr = 0;
        long time = programs[ptr][1] + programs[ptr][2];
        ptr++;
        while(true) {
            while(ptr < len && programs[ptr][1] <= time) {
                // 다른 프로그램 실행중일때 콜이 발동된 프로그램들은 대기큐로
                pq.add(programs[ptr++]);
            }
            if(ptr < len && pq.isEmpty()) {
                // 위에서 못채웠단 의미는 동떨어진 프로그램이 있는 경우다.
                time = programs[ptr][1] + programs[ptr][2];
                ptr++;
            } else if (!pq.isEmpty()){
                // 대기큐에 남아있으면 처리해야 한다.
                int[] p = pq.poll();
                answer[p[0]] += time - p[1];
                time += p[2];
            } else {
                // 더이상 고려할 프로그램도 없고 대기큐도 비었다는것은 종료각
                break;
            }
        }
        answer[0] = time;
        
        return answer;
    }
}
```
