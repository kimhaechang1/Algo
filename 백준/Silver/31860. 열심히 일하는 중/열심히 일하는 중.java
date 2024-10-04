import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int m;
    static int k;
    static int[] D;
    static class Data implements Comparable<Data>{
        int d;
        int idx;
        public Data(int d, int idx) {
            this.d = d;
            this.idx = idx;
        }

        public int compareTo(Data d) {
            return d.d - this.d;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        D = new int[n];
        for(int i = 0;i<n;i++) {
            D[i] = Integer.parseInt(bf.readLine());
        }
        // 하루에 하나의 일만 처리할 수 있으며, 중요도가 높은 일 부터 처리해야 한다. 그 일의 중요도는 M만큼 감소, K이하가 되면 완료한걸로 됨
        // 오늘의 만족감 계산법은 전날 만족감 절반에 + 오늘의 할일의 중요도가 된다.
        // 그러고 M만큼 오늘의 할일 중요도에서 빠진다.
        // 모든일을 끝낼 때 까지 몇일 걸리는지, 일별로 느낀 만족감
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Data> pq = new PriorityQueue<>();
        for(int i =0;i<n;i++) {
            pq.add(new Data(D[i], i));
        }
        int prev = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Data now = pq.poll();
            int present = 0;
            if (prev == 0) {
                present += (now.d);
            } else {
                present += (prev / 2) + now.d;
            }
            cnt++;
            sb.append(present).append("\n");
            prev = present;
            if (now.d - m > k) {
                pq.add(new Data(now.d - m, now.idx));
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(cnt).append("\n").append(sb.toString());
        System.out.print(result);
    }
}