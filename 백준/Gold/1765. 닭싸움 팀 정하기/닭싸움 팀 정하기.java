import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static ArrayList<Integer> [] eList; 
	static ArrayList<Integer> [] fList;
	static StringTokenizer stk;
	static boolean [] v;
	static void dfs(int idx) {
		// 먼저 친구들을 전부 묶는다.
		for(int f : fList[idx]) {
			if(v[f]) continue;
			v[f] = true;
			dfs(f);
		}
		
		// 현재 해당 사람의 적의 적 또한 현재 사람의 친구다.
		for(int e1 : eList[idx]) {
			for(int e : eList[e1]) {
				if(v[e]) continue;
				v[e] = true;
				dfs(e);
			}
		}
	}
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		m = Integer.parseInt(bf.readLine());
		eList = new ArrayList[n+1];
		fList = new ArrayList[n+1];	
		for(int i= 1;i<n+1;i++) {
			eList[i] = new ArrayList<>();
			fList[i] = new ArrayList<>();
		}
		
		for(int i= 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			String q = stk.nextToken();
			int v1 = Integer.parseInt(stk.nextToken());
			int v2 = Integer.parseInt(stk.nextToken());
			if("E".equals(q)) {
				eList[v1].add(v2);
				eList[v2].add(v1);
			}else {
				fList[v1].add(v2);
				fList[v2].add(v1);
			}
		}
		v = new boolean[n+1];
		// 친구들끼리 잘 엮어주면된다.
		// 본인기준 친구의 친구들은 다 친구이며
		// 적의 적 또한 지금 맺을 수 있는 친구다.
		// 이렇게 그룹을 묶으면 된다.
		int cnt=0;
		for(int i = 1;i<n+1;i++) {
			if(v[i]) continue;
			v[i] = true;
			dfs(i);
			cnt++;
		}
		System.out.println(cnt);
		
	}
}