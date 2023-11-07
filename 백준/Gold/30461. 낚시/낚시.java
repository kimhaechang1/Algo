import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [][] map;
	static int [][] dp;
	static int N;
	static int M;
	static int Q;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		Q = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		// 무게추 a인 무게추를 매달아 b만큼 낚싯대를 휘두르면 a,b 칸에 미끼가 존재하게 된다.
		// 1~a 인 모든 정수 i에 대하여  (i, b)에 존재하는 모든 물고기를 잡는다.
		// 낚싯줄을 감아올리면 왼쪽 위 대각선으로 이동한다. 미끼가 일감호의 공간을 벗어나면 즉시 회수
		// 누적합? 사용하면 좀 편할라나
		// 왼쪽 위 대각선 누적합을 사용하면 될듯
		int [][] suff1 = new int[N][M]; // 세로 누적합
		for(int i= 0;i<N;i++) {
			suff1[i] = map[i].clone();
		}
		
		// 점화식
		// 어떤 좌표 (a,b)에 대하여 수확한 물고기의 합은
		// 아래방향 누적합 구한거에 대각선 누적합 구한 결과의 suff1 배열의 (a, b)가 된다.
		for(int i= 0;i<M;i++) {
			for(int j= 1;j<N;j++) {
				suff1[j][i] = suff1[j-1][i]+suff1[j][i];
			}
		}
		
		int sy = 0;
		int sx = 0;
		while(true) {
			sy += 1;
			sx += 1;
			if(sy >= N || sy < 0 || sx >= M || sx < 0) break;
			suff1[sy][sx] = suff1[sy][sx] + suff1[sy-1][sx-1];
		}
		
		for(sy = 1;sy<N;sy++) {
			int ny = sy;
			int nx = 0;
			while(true) {
				ny += 1;
				nx += 1;
				if(ny >= N || ny < 0 || nx >= M || nx < 0) break;
				suff1[ny][nx] = suff1[ny][nx] + suff1[ny-1][nx-1];
			}
		}
		for(sx = 1;sx<M;sx++) {
			int ny = 0;
			int nx = sx;
			while(true) {
				ny += 1;
				nx += 1;
				if(ny >= N || ny < 0 || nx >= M || nx < 0) break;
				suff1[ny][nx] = suff1[ny][nx] + suff1[ny-1][nx-1];
			}
		}
		
		for(int i=0;i<Q;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(stk.nextToken())-1;
			int b = Integer.parseInt(stk.nextToken())-1;
			sb.append(suff1[a][b]).append("\n");
		}
		System.out.print(sb);
		
	}
}