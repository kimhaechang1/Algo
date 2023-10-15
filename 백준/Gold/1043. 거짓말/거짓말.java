import java.io.*;
import java.util.*;

public class Main{
	static int N; // 전체 사람수
	static int K; // 진실을 아는 사람 수
	static int [] arr; // 진실을 아는사람 번호
	static int M; // 파티의 수
	static ArrayList<Integer> [] g; // 파티별 오는 사람번호
	static StringTokenizer stk;
	static int [] p;
	static void makeSet() {
		p = new int[N+1];
		for(int i = 1;i<N+1;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == -1) return -1;
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		if(bRoot == -1) {
			p[aRoot] = bRoot;
		}else if(aRoot == -1) {
			p[bRoot] = aRoot;
		}else {
			p[bRoot] = aRoot;
		}
		
		return true;
	}
	public static void main(String []args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk  = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		K = Integer.parseInt(stk.nextToken());
		arr = new int[K];
		makeSet();
		for(int i= 0;i<K;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			p[arr[i]] = -1;
		}
		g = new ArrayList[M+1];
		for(int i = 1;i<M+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i=1;i<=M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(stk.nextToken());
			for(int j = 0;j<num;j++) {
				g[i].add(Integer.parseInt(stk.nextToken()));
			}
		}
		
		int cnt = 0;
		for(int i = 1;i<=M;i++) {
			boolean flg = true;
			for(int j = 0;j<g[i].size()-1;j++) {
				//System.out.println("union : "+ g[i].get(j) +" - "+g[i].get(j+1));
				union(g[i].get(j),g[i].get(j+1));
				//System.out.println(Arrays.toString(p));
			}
		}
		for(int i =1;i<=N;i++) {
			find(i);
		}
		
		for(int i = 1;i<=M;i++) {
			boolean flg = true;
			for(int j = 0;j<g[i].size();j++) {
				if(p[g[i].get(j)]==-1) {
					flg = false;
					break;
				}
			}
			if(flg) cnt++;
		}
		// 만약 그 어떠한 사람도 진실을 모른다고 하면 파티의 개수가 최대가 된다.
		// 유니온 파인드로 진실을 알고있는 사람과 모르는사람을 나누는게 좋을듯
		// 진실을 알고있는 쪽을 p배열 값 -1로 두고 시작
		// max값의 초기값은 0으로 두고 시작 
		System.out.println(cnt);
	}
}