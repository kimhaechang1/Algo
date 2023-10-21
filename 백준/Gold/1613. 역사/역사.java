import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int K;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		int [][] map = new int[401][401];
		for(int i = 0;i<401;i++) {
			Arrays.fill(map[i], 987654321);
		}
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		for(int i = 0;i<K;i++ ) {
			stk = new StringTokenizer(bf.readLine());
			int prev = Integer.parseInt(stk.nextToken());
			int post = Integer.parseInt(stk.nextToken());
			map[prev][post] = 1;
		}
		
		for(int k = 1;k<401;k++) {
			for(int i = 1;i<401;i++) {
				if(i == k) continue;
				for(int j = 1;j<401;j++) {
					if(j == k)continue;
					if(i == j)continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		StringBuilder sb= new StringBuilder();
		int M = Integer.parseInt(bf.readLine());
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int v1 = Integer.parseInt(stk.nextToken());
			int v2 = Integer.parseInt(stk.nextToken());
			if(map[v1][v2] != 987654321) {
				sb.append(-1).append("\n");
				continue;
			}
			if(map[v2][v1]!=987654321) {
				sb.append(1).append("\n");
				continue;
			}
			sb.append(0).append("\n");
		}
		System.out.print(sb);
	}
}