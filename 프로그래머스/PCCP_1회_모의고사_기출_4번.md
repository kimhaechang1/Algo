<a href="https://school.programmers.co.kr/learn/courses/20847/lessons/255903#"> 문제 바로가기 </a>

초견에 풀었는가? : X

이해한 만큼

- 우선순위큐를 사용해야함을 인지함 (자료구조 문제임을 이해)
- 도착시각순으로 정렬된 자료구조와, 문제의 실행순서 조건을 고려하는 자료구조 총 2개가 필요함을 인지함
- 특정 프로그램이 실행중일때 대기큐에 들어가야하는 프로그램의 조건을 인지함

헷갈린 순간들

- 우선순위를 헷갈림 ( 우선순위값이 먼저 고려해야 하냐랑 도착시간을 먼저 고려해야하냐 )
- 실행 대기큐를 처리하는 방법을 잘못생각함 ( 한번에 하나만 처리가능함을 인지하지 못함 )

피드백

- 문제에서 보면 특정 시점에 콜된 프로그램들 중 우선순위가 높은 프로그램을 먼저 시작한다고 되어있다.
  - 여기서 알수 있듯, 콜된 시각이 중요하고 그 시각에 같이 콜된 프로그램들을 대기큐에 넣는것이 핵심이다.

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
        }); // 실행대기 큐
            
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b)-> {
            
            return Integer.compare(a[1], b[1]);
        }); // 타임라인 큐
        
      
        int len = programs.length;
        
        for(int i = 0;i<len;i++) {
            pq2.add(programs[i]);
        }
        
        // answer[0]: 모든 프로그램이 종료되는 시각
        // answer[n]: 프로그램점수가 n인 프로그램들의 대기시간의 합
        int time = 0;
        
        while(!pq2.isEmpty() || !pq.isEmpty()) {
            // 모든 프로그램을 처리해야 한다는 조건
            while(!pq2.isEmpty() && pq2.peek()[1] <= time) {
                // 현재 프로그램의 종료시각보다 더 빨리 콜된 프로그램들을 실행대기큐로 옮김
                pq.add(pq2.poll());
            }
            
            if(!pq.isEmpty()) {
                // 실행대기큐가 쌓여있다면 하나씩 실행대기큐에서 꺼내서 실행함
                int[] now = pq.poll();
                int idx = now[0];
                int call =now[1];
                int dura = now[2];
                
                answer[idx] += time - call;
                time += dura;
                // 이때 아래의 continue가 없으면, 시간이 잘못더해지는걸 방지할 수 있음
            } else {
                int[] peek = pq2.peek();
                // 간격이 동떨어진 프로그램의 경우, 실행큐로 들어가기 위한 처리를 한다.
                time = peek[1];
                pq.add(peek);
                pq2.poll();
            }
            // 실행대기큐가 쌓여있지 않다면 시간을 하나씩 더하면서 처리함
        }
    
        answer[0] = time;
        
        return answer;
    }
}
```
