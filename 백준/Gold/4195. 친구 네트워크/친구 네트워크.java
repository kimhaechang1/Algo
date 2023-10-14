import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static String [][] map;
	static StringTokenizer stk;
	static Set<String> set;
	static HashMap<String, Integer> hashmap;
	static int pN;
	static int [] p;
	static int [] cnt;
	
	
	// 랭크관리 union-find 문제
	static void makeSet() {
		p = new int[pN];
		cnt = new int[pN];
		for(int i= 0;i<pN;i++) {
			p[i] = i;
			cnt[i] = 1;
		}
		
	}
	static int find(int a) {
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return cnt[aRoot];
		int sum = 0;
		if (cnt[aRoot] >= cnt[bRoot]) {
			p[bRoot] = aRoot;
			cnt[aRoot] += cnt[bRoot];
			
			sum = cnt[aRoot];
		}else {
			p[aRoot] = bRoot;
			cnt[bRoot] +=cnt[aRoot];
			sum = cnt[bRoot];
		}
		return sum;
	}
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t= 1;t<=T;t++) {
			N = Integer.parseInt(bf.readLine());
			map = new String[N][2];
			set = new HashSet<>();
			for(int i = 0;i<N;i++) {
				stk = new StringTokenizer(bf.readLine());				
				String p1 = stk.nextToken();
				String p2 = stk.nextToken();
				set.add(p1);set.add(p2);
				map[i][0] = p1;
				map[i][1] = p2;
			}
			pN = set.size();
			makeSet();
			Iterator<String> iter = set.iterator();
			hashmap = new HashMap<>();
			int idx= 0;
			while(iter.hasNext()) {
				String p = iter.next();
				hashmap.put(p, idx++);
			}
			for(int i=  0;i<N;i++) {
				int idx1 = hashmap.get(map[i][0]);
				int idx2 = hashmap.get(map[i][1]);
				sb.append(union(idx1, idx2)).append("\n");
			}
		}
		System.out.print(sb);
	}
}