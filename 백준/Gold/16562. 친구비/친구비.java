import java.util.*;
import java.io.*;

public class Main {
	static int parents[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int friendMoney[] = new int[N+1];
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			friendMoney[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b, friendMoney);
		}
		int sum = 0;
		for(int i=1; i<=N; i++) {
			if(parents[i] == i) {
				sum += friendMoney[i];
			}
		}
		if(k - sum < 0) {
			System.out.println("Oh no");
		}
		else {
			System.out.println(sum);
		}
		
	}

	private static void union(int a, int b, int[] friendMoney) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) {
			if(friendMoney[rootA] > friendMoney[rootB]) {
				parents[rootA] = rootB; 
			}
			else {
				parents[rootB] = rootA;
			}
		}
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
}