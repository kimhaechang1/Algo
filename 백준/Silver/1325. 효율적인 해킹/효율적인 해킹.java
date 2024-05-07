import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] g;
    static StringTokenizer stk;
    static int n;
    static int m;
    static int [] memo;
    static Queue<Integer> queue;
    static boolean [] v;
    public static void main(String[] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        g = new ArrayList[n+1];
        for(int i=1;i<n+1;i++){
            g[i] = new ArrayList<>();
        }
        // 가장 신뢰받는 컴퓨터 즉, 가장 화살표를 많이 받는 컴퓨터가 가장 효율적인 컴퓨터이다.
        // 방문 체크되지 않은 노드들만 개수를 유심히 셈하면 된다.???
        //
        for(int i = 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            int a  = Integer.parseInt(stk.nextToken());
            int b= Integer.parseInt(stk.nextToken());
            g[a].add(b);
        }
        memo = new int[n+1];
        int max = -1;
        queue = new ArrayDeque<>();
        for(int i = 1;i<n+1;i++){
            memo[i]++;
            v = new boolean[n+1];
            v[i] = true;
            queue.add(i);
            bfs();
        }
        for(int i = 1;i<n+1;i++){
            max = Math.max(max, memo[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<n+1;i++){
            if(max == memo[i]){
                sb.append(i).append(" ");
            }
        }
        System.out.print(sb);
    }
    static void bfs(){
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next: g[now]){
                if(v[next]) continue;
                v[next] = true;
                memo[next]++;
                queue.add(next);
            }
        }
    }
}
