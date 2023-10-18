import java.util.*;
class Solution {
   static boolean [] prime;
	static String [] arr;
	static HashSet<Integer> set;
	static boolean [] v;
	static int N;
	static boolean isPrime(int num) {
		return prime[num];
	}
	static void makePrime() {
		for(int i = 2;i<=(int)(Math.sqrt(10000000));i++) {
			if(!prime[i]) {
				for(int j= i+i;j<10000000;j=j+i) {
					if(!prime[j]) prime[j] = true;
				}
			}
		}
	}
	static void dfs(int depth, String res, int p) {
		if(depth == p) {
			if(res.equals("")) return;
			int r = Integer.parseInt(res);
			if(!isPrime(r)) {
				set.add(r);
			}
			return;
		}
		for(int i = 0;i<N;i++) {
			if(!v[i]) {
				v[i] = true;
				dfs(depth+1, res+arr[i], p);
				v[i] = false;
			}
		}
	}
	public int solution(String numbers) {
		prime = new boolean[10000000];
		// false : 소수
		prime[0] = true;
		prime[1] = true;
		makePrime();
		N = numbers.length();
		arr = numbers.split("");
		set = new HashSet<>();
		v = new boolean[N];
		for(int i = 1;i<=N;i++) {
			dfs(0, "", i);
		}
        int answer = set.size();
        return answer;
    }
}