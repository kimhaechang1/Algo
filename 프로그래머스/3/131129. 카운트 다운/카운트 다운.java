// 1 ~ 20에 싱글, 더블, 트리플이 있음
// 상관없이 50점
// 두 선수가 같은 라운드에 0점을 만들면 싱글 또는 불을 더 많이 던진 선수가 승리, 그마저도 같으면 선공인 선수가 승리
import java.util.*;

// 최선의 경우 던질 총 다트의수, 싱글 또는 불을 맞춘 횟수의 합
class Solution {
    public int[] solution(int target) {
        int[] answer = {};
        // 젤 큰 점수를 받는 방법은 20점짜리 과녁에 트리플을 맞춘 상황임
        // target 을 0으로 만드는데 최소한의 개수를 사용하는게 중요하다라
        // 1 ~ 20의 수로 나누어 떨어진다면, 
        // 만약에 60점이라면 사실상 20점짜리에 트리플을 맞춘게 젤 좋단 말이지
        // 1 ~ 20 까지는 싱글이 좋고,
        // 싱글씩 target에서부터 빼면
        return bfs(target);
    }
    static int[] bfs(int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{target, 0, 0}); // now[1]: 싱글 + 불, now[2]: 총 개수
        int[] v = new int[100_001];
        int[] sb = new int[100_001];
        
        Arrays.fill(v, 987654321);
        v[target] = 0;
        sb[target] = 987654321;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i = 20; i > 0 ; i--) {
                for(int j = 1;j<=3;j++) {
                    if (now[0] < i * j) continue;
                    if (v[now[0] % (i * j)] > (now[2] + (now[0] / (i * j)))) {
                        sb[now[0] % (i * j)] = (now[1] + (j == 1 ? now[0] / (i * j) : 0) );
                        v[now[0] % (i * j)] = (now[2] + (now[0] / (i * j)));
                        if (sb[0] == 0 && v[0] == 17) {
                            System.out.println("target: "+now[0]+" i: "+i+" j: "+j+" now[2]: "+now[2]);
                        }
                        queue.add(new int[]{now[0] % (i * j), sb[now[0] % (i * j)] ,v[now[0] % (i * j)]});
                    } else if (v[now[0] % (i * j)] == (now[2] + (now[0] / (i * j)))
                               && sb[now[0] % (i * j)] < (now[1] + (j == 1 ? now[0] / (i * j) : 0) )
                    ) {
                        sb[now[0] % (i * j)] = (now[1] + (j == 1 ? now[0] / (i * j) : 0) );
                        queue.add(new int[]{now[0] % (i * j), sb[now[0] % (i * j)] ,v[now[0] % (i * j)]});
                    }
                }
            }
            
            if (now[0] >= 50 && v[now[0] % 50] > (now[2] + (now[0] / 50))) {
                
                sb[now[0] % 50] = now[1] + (now[0] / 50);
                v[now[0] % 50] = now[2] + (now[0] / 50);
                queue.add(new int[]{now[0] % 50, sb[now[0] % 50], v[now[0] % 50]});
            } else if (now[0] >= 50 && v[now[0] % 50] == (now[2] + (now[0] / 50)) && sb[now[0] % 50] < (now[1] + (now[0] / 50))) {
                sb[now[0] % 50] = now[1] + (now[0] / 50);
                queue.add(new int[]{now[0] % 50, sb[now[0] % 50], v[now[0] % 50]});
            }
            
            
        }
        return new int[]{v[0], sb[0]};
    }
}