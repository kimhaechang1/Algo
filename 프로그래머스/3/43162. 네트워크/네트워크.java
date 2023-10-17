import java.util.*;
class Solution {
    static int [] p;
	static int N;
	static void makeSet() {
		p =  new int[N];
		for(int i = 0;i<N;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	 public int solution(int n, int[][] computers) {
		 int answer = 0;
		 // union find
		 N = n;
		 makeSet();
		 for(int i= 0;i<N;i++) {
			 for(int j= 0;j<N;j++) {
				 if(i == j) continue;
				 if(computers[i][j] == 0) continue;
				 union(i, j);
			 }
		 }
		 for(int i=0;i<N;i++) {
			 if(p[i] == i) {
				 answer++;
			 }
		 }
		 return answer;
	 }
}