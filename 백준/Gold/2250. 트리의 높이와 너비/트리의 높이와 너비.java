import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] info;
    static StringTokenizer stk;
    static BufferedReader bf;

    static int cnt;

    static class Node {

        Node left;
        Node right;
        int val;
        int x;

    }
    static int ansLevel;
    static int ans;

    static HashMap<Integer, Integer> parent;

    static int[][] coordi;
    static int W, H;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void input() throws Exception{

        n = Integer.parseInt(bf.readLine());

        info = new int[n][3];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            info[i][2] = Integer.parseInt(stk.nextToken());
        }

    }

    void solve() {
        // 이진 트리에서 같은 레벨에 있는 노드는 같은 행에 위치한다.
        // 어떤 노드에서 l substree 에 있는 노드들은 항상 해당 노드보다 왼쪽에 위치해야 한다.
        // 오른쪽은 그 반대
        // 노드가 배치된 가장 왼쪽 열과 오른쪽 열 사이엔 아무 노드도 없이 비어있는 열은 없다.
        // 가장 넓은 레벨과 그 레벨의 너비, 만약 너비가 같은 레벨이 있다면 더작은 번호를 리턴

        // root 노드를 찾고
        // 트리를 구성하기
        // 그리고 각 노드별 왼쪽 서브트리
        int root = findRoot();

        Node[] nodes = new Node[10001];
        for(int i = 1;i<=10000;i++) {
            nodes[i] = new Node();
            nodes[i].val = i;
        }

        for(int[] inf: info) {
            int parent = inf[0];
            int left = inf[1];
            int right = inf[2];

            if (left != -1) {
                nodes[parent].left = nodes[left];
            }
            if (right != -1) {
                nodes[parent].right = nodes[right];
            }
        }
        coordi = new int[n + 1][2];

        cnt = 1;
        inOrder(nodes[root]);
        ansLevel = 1;
        ans = 1;
        preOrder(nodes[root], 1);

        int[][] nodeMap = new int[H + 1][W + 1];

        for(int i = 1;i<coordi.length;i++) {
            int node = i;
            int x = coordi[node][0];
            int y = coordi[node][1];

            nodeMap[y][x] = node;
        }

        for(int i = 1;i<H + 1;i++) {
            int start = 1;
            int end = W;
            for(int j = 0;j<W + 1;j++) {
                if (nodeMap[i][j] != 0) {
                    start = j;
                    break;
                }
            }
            for(int j = W;j > 0;j--) {
                if (nodeMap[i][j] != 0) {
                    end = j;
                    break;
                }
            }
            int width = end - start + 1;
            if (ans < width) {
                ans = width;
                ansLevel = i;
            }
        }

        /*for(int i = 1;i<H + 1;i++) {
            for(int j = 1;j<W + 1;j++) {
                System.out.print(nodeMap[i][j]+"\t");
            }
            System.out.println();
        }*/

        System.out.println(ansLevel+" "+ans);
    }
    static int findRoot() {
        parent = new HashMap<>();
        for(int i = 0;i < n;i++) {
            int node = info[i][0];
            int l = info[i][1];
            int r = info[i][2];
            if (!parent.containsKey(l)) {
                parent.put(l, node);
            }
            if (!parent.containsKey(r)) {
                parent.put(r, node);
            }
        }
        int root = -1;
        for(int i = 0;i<info.length;i++) {
            if (!parent.containsKey(info[i][0])) {
                root = info[i][0];
            }
        }
        return root;
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        coordi[node.val][0] = cnt;
        node.x = cnt;
        W = Math.max(W, cnt);
        cnt++;
        inOrder(node.right);
    }
    static void preOrder(Node node, int height) {
        if (node == null) {
            return;
        }
        coordi[node.val][1] = height;
        H = Math.max(H, height);
        preOrder(node.left, height + 1);
        preOrder(node.right, height + 1);
    }
}