import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static ArrayList<Integer> [] g;
	static int [] table;
	static int min;
	static StringTokenizer stk;
	static boolean [] v;
	static int a1;
	static int a2;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 치킨집으로 뽑을 2가지 번호를 뽑는다.
		// 각 치킨집으로 부터 모든 다른 정점까지의 최단시간을 구한다.
		// 이 때 최단시간 테이블에서 다른 치킨집에서 해당정점까지가 더 싸다고 하면 싼 결과를 만든다.
		// 그리고 합을 구하고 현재까지의 합과 동일하다면 더 작은 치킨집이 더 작은곳
		// 작은곳 까티지 같다면 큰번호또한 더 작은쪽의 조합을 선택한다.
		min = Integer.MAX_VALUE;
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		g = new ArrayList[N+1];
		v = new boolean[N+1];
		for(int i= 1;i<=N;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int t= Integer.parseInt(stk.nextToken());
			int f= Integer.parseInt(stk.nextToken());
			g[t].add(f);
			g[f].add(t);
		}
		
		for(int i = 1;i<N;i++) {
			for(int j = i+1;j<=N;j++) {
				table= new int[N+1];
				v = new boolean[N+1];
				table[i] = 0;
				table[j] = 0;
				v[i] = true;
				v[j] = true;
				bfs(i);
				bfs(j);
				//System.out.println(Arrays.toString(table));
				int sum = 0;
				for(int k = 1;k<=N;k++) {
					sum+= (table[k]*2);
				}
				if(sum < min) {
					min = sum;
					a1 = i;
					a2 = j;
				}else if(sum == min) {
					if(a1 > i) {
						a1 = i;
						a2 = j;
					}else if(a1 == i) {
						if(a2 > j) {
							a1 = i;
							a2 = j;
						}
					}
				}
			}
		}
		System.out.println(a1 +" "+a2+" "+min);
		
	}
	static void bfs(int startNode) {
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int [] {startNode, table[startNode]});
		while(!queue.isEmpty()) {
			int []now = queue.poll();
			v[now[0]] = true;
			for(int node : g[now[0]]) {
				if(!v[node]) {
					v[node] = true;
					table[node] = now[1]+1;
					queue.add(new int[] {node, now[1]+1});
				}else {
					if(table[node] > now[1]+1) {
						table[node] = now[1]+1;
						queue.add(new int[] {node, now[1]+1});
					}
				}
			}
		}
	}
}