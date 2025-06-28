import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n, t, w, m;
    static int[][] init;
    static int[][] clients;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 영업시작때 이미 대기한 손님이 N 명
        // 그들은 어짜피 영업시작시간에 도착했기 때문에 Cx 는 없다.

        // 그 이후 M 명의 손님들이 오는데 그들은 영업 시작시각으로부터 Cx 시간만큼 떨어진 순간에 온다는뜻이고 당연히 대기큐 맨뒤에 진입하게 된다.

        // 알고리즘은 다음과 같다.
        // 대기큐 맨 앞에 있는 고객이 x번 손닌ㅁ이라고 하면, 창구에 있는 직원은
        // 해당 고객이 필요한 시간인 tx가 T보다 크다면 tx - T 만큼을 그 손님이 tx 로 갖게 되며
        // 그렇지 않은 경우 tx 가 0 이 된다.
        // 그러고나서 tx 가 0인 손님은 은행 바깥으로 나가게 되고, 그렇지 않은경우 큐의 맨 뒤로 이동하게 된다.
        // 만약 시간이 지난후에 도착한 손님과 동시진입이 걸린경우 도착한 손님보다 뒤에 진입힌다.
        //대기큐에 고객이 남아있다면 다시 맨앞 고객부터 처리한다.
        // 0 ~ W - 1 초까지 어떤 고객의 업무를 처리하는지 알려달라.

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,  o2) -> o1[2] - o2[2]);

        for(int i = 0; i < m; i++) {
            pq.add(clients[i]);
        }

        StringBuilder sb = new StringBuilder();
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n;i ++) q.add(new int[]{init[i][0], init[i][1]});
        int wCnt = 0;
        int until = 0;
        // 큐에 미리 0초대에 사람들을 집어넣는다.
        // tx > T 라면 T 만큼의 시간이, 그게 아니라면 tx 만큼의 시간이 흐르고 그것대로 시뮬레이션 해야한다.
        while(wCnt < w) {
            int[] client = q.poll();
            int cId = client[0];
            int remain = client[1];
            int cnt = 0;
            if (remain > t) {
                cnt = wCnt + t >= w ? w - wCnt : t;
                wCnt += t;
                int interval = remain - t;
                accumulate(sb, cnt, cId);
                while(!pq.isEmpty() && pq.peek()[2] <= wCnt ) {
                    int[] arrived = pq.poll();
                    q.add(new int[]{arrived[0], arrived[1]});
                }
                q.add(new int[]{cId, interval});
            } else {
                cnt = wCnt + remain >= w ? w - wCnt : remain;
                wCnt += remain;
                accumulate(sb, cnt, cId);
                while(!pq.isEmpty() && pq.peek()[2] <= wCnt ) {
                    int[] arrived = pq.poll();
                    q.add(new int[]{arrived[0], arrived[1]});
                }
            }
        }
        System.out.print(sb);
    }
    void accumulate(StringBuilder sb, int cnt, int cId) {
        while(cnt-- > 0) sb.append(cId).append("\n");
    }

    void testCase() throws Exception {

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        t = Integer.parseInt(stk.nextToken());
        w = Integer.parseInt(stk.nextToken());

        init = new int[n][2];
        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            init[i][0] = Integer.parseInt(stk.nextToken()); // px: 고객 id
            init[i][1] = Integer.parseInt(stk.nextToken()); // tx: 필요한 시간 tx
        }

        m = Integer.parseInt(bf.readLine());
        clients = new int[m][3];
        for(int i = 0; i < m; i++ ) {
            stk = new StringTokenizer(bf.readLine());
            clients[i][0] = Integer.parseInt(stk.nextToken());
            clients[i][1] = Integer.parseInt(stk.nextToken());
            clients[i][2] = Integer.parseInt(stk.nextToken());
        }
    }
}