import java.util.*;

class Solution {
    static int[] gNum;
    public int solution(int[] cards) {
        int answer = 0;
        // 100장의 카드더미속에는 1~ 100씩 하나씩 적혀있음
        // 2 ~ 100 자연수를 하나정해 그 수보다 작거나 같은 숫자카드들을 준비함
        ArrayList<Integer> results = new ArrayList<>();
        gNum = new int[cards.length];
        Arrays.fill(gNum, -1);
        int cnt = 1;
        for(int i = 0;i<cards.length;i++) {
            if (gNum[i] == -1) {
                gNum[i] = cnt;
                results.add(bfs(i, cnt, cards));
                cnt++;
            }
        }
        if(results.size() <= 1 && results.get(0) == cards.length) return 0;
        Collections.sort(results, Collections.reverseOrder());
        answer += (results.get(0) * results.get(1));
        return answer;
    }
    static int bfs(int startIdx, int m, int[] c) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startIdx);
        int cnt = 1;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            if (gNum[c[now]-1] != -1) {
                break;
            } else {
                gNum[c[now]-1] = m;
                queue.add(c[now]-1);
                cnt++;
            }
        }
        return cnt;
    }
}
