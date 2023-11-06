import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static StringTokenizer stk;
	static int [] p;
	static int [] e;
	static ArrayList<Integer> [] g;
	public static void main(String[]  args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// a - b 적
		// 나의 건너편은 적, 건너건너는 아군
		// 두개의 집합을 나눈다.
		
		// 
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		p = new int[N+1];
		for(int i= 1;i<N+1;i++) {
			p[i] = i;
		}
		g = new ArrayList[N+1];
		for(int i= 1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i= 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			g[a].add(b);
			g[b].add(a);
		}
		boolean canGo = true;
		for(int i =1;i<N+1;i++) {
			if(p[i] == i) {
				p[i] = 1;
				if(!bfs(i)) {
					canGo = false;
					break;
				}
			}
		}
		System.out.print( (canGo ? "1" : "0"));
	}
	static boolean bfs(int startNode) {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {startNode, p[startNode]});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int flg = now[1];
			for(int node : g[now[0]]) {
				if(p[node] == flg ) {
					return false;
				}
				if(p[node] != node) continue;
				p[node] = flg * -1;
				queue.add(new int[] {node, p[node]});
			}
		}
		return true;
	}
}