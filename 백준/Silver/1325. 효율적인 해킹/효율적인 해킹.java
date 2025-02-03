
import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] arr;
    static BufferedReader bf;
    static StringTokenizer stk;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void testCase() throws Exception {

    }

    void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[m][2];
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(stk.nextToken());
            arr[i][1] = Integer.parseInt(stk.nextToken());
        }
    }

    int TC() {
        return 0;
    }

    void solve() {
        // A 신뢰 B 는
        // B -> A : B를 해킹하면 A도 해킹된다는 것

        // graph init
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            g[arr[i][1]].add(arr[i][0]);
        }

        // 모든 정점에서 BFS로 찾아나가는것이 좋은데,
        // BFS를 돌때마다 visited 배열을 초기화 시켜주는건 비용이 엄청 크다.

        // 또한 추가적으로 생각했을 때, 어짜피 1 ~ N번까지의 노드밖에 없고,
        // 1... N 순서로 시작노드로 잡고 탐색 해 갔을 때,
        // 시작 노드 A 에서 BFS를 통해 여러 노드를 거쳐 특정 노드 Any 가 갑자기 시작노드보다 작다면,
        // 2번을 통해 탐색한 모든 노드들을 방문체크 해도 된다.
        // 왜냐하면 BFS를 통한 탐색은 시작노드 A에서 부터 모든 노드의 탐색을 이미 해놓은 상태기 때문에,
        // 그러한 방문의 결과를 Any 노드에 대해서 저장해놓고 있다면 문제가 없어진다.

        BitSet[] bitSets = new BitSet[n + 1];
        for(int i = 1; i < n + 1; i++) bitSets[i] = new BitSet(n + 1);
        for(int i = 1; i <= n; i++) {
            bfs(i, bitSets, g);
        }
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n + 1; i++) {
            int trueCount = bitSets[i].cardinality();
            if (trueCount == max) {
                sb.append(i).append(" ");
            } else if (trueCount > max) {
                max = trueCount;
                sb.setLength(0);
                sb.append(i).append(" ");
            }
        }
        System.out.print(sb);
    }
    static void bfs(int startNode, BitSet[] vSets, ArrayList<Integer>[] g) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);
        vSets[startNode].set(startNode);
        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next: g[now]) {
                if (vSets[startNode].get(next)) continue;
                if (next < startNode) {
                    // 이미 해당 노드를 시작점으로 bfs를 돌렸을 것이다.
                    // 그 결과를 그대로 덮어씌우자
                    vSets[startNode].or(vSets[next]);
                } else {
                    vSets[startNode].set(next);
                    queue.add(next);
                }
            }
        }
    }
}