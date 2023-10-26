import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int [][] map;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N+1][N+1];
		for(int i = 1;i<N+1;i++) {
			Arrays.fill(map[i], 987654321);
		}
		for(int i = 0;i<M;i++) {
			stk =new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			map[e][s] = 1;
		}
		// floyd
		// s > e
		// e -> s
		for(int k = 1;k<N+1;k++) {
			for(int i= 1;i<N+1;i++) {
				if(i == k) continue;
				for(int j = 1;j<N+1;j++) {
					if(j == k) continue;
					if(i == j) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		/*for(int i= 1;i<N+1;i++) {
			for(int j= 1;j<N+1;j++) {
				if(map[i][j] == 987654321) System.out.print("0 ");
				else System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}*/
		int c = 0;
		for(int i= 1;i<N+1;i++) {
			int lc = 0;
			for(int j= 1;j<N+1;j++) {
				if(map[i][j] != 987654321) {
					lc++;
					if(lc > N/2) {
						c++;
						break;
					}
				}	
			}
			if(lc > N/2) continue;
			int gc = 0;
			for(int j=1;j<N+1;j++) {
				if(map[j][i] != 987654321) {
					gc++;
					if(gc > N/2) {
						c++;
						break;
					}
				}
			}
		}
		System.out.println(c);
	}
}