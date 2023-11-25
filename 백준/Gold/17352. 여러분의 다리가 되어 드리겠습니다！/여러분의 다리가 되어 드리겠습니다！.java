import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [] p;
	static void makeSet() {
		p = new int[N+1];
		for(int i = 1;i<N+1;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		// disjoin set
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		makeSet();
		for(int i=0;i<N-2;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a1 = Integer.parseInt(stk.nextToken());
			int b1 = Integer.parseInt(stk.nextToken());
			union(a1, b1);
			union(b1, a1);
		}
		
		int a = -1;
		for(int i = 1;i<N+1;i++) {
			p[i] = find(i);
		}
		//System.out.println(Arrays.toString(p));
		int prev = p[1];
		int prevIdx = 1;
		for(int i= 2;i<N+1;i++) {
			if(prev!= p[i]) {
				System.out.println(prev +" "+p[i]);
				break;
			}
		}
		//System.out.println(prevIdx + " "+ a);
	}
}