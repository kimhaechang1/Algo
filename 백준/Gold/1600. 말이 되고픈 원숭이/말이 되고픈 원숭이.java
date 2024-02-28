import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int W;
	static int H;
	static int [][] map;
	static StringTokenizer stk;
	static boolean [][][] v;
	static Queue<int []> queue;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] hy = {-1,-2,-2,-1,1,2,2,1};
	static int [] hx = {-2,-1,1,2,2,1,-1,-2};
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		map= new int[H][W];
		for(int i= 0;i<H;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=  0;j<W;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		cnt = 0;
		queue = new LinkedList<>();
		v = new boolean[H][W][K+1];
		queue.add(new int[] {0,0,K,0}); // y, x, K, cnt
		
		if(bfs()) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}
		
		// 격자판 0,0 에서 W-1 H-1 까지, 갈 수 없으면 -1, 말의 움직임을 사용할 수 있는 횟수 K
		// 그렇다면 몇번의 말의 움직임을 사용하여 갔는지에 대한 v[][][K]가 필요할 것 같다.
		// 아에 사용을 안했거나 1번 2번 ... K번 사용해서 도착했는지가 중요하다.
		// 동작수의 최솟값을 구하는것 즉 최단거리

	}
	static boolean bfs() {
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			// 지금 내가 말이동법을 사용 할 수 있으면서 사용 하는 경우
			if(now[0] == H-1 && now[1] == W-1) {
				cnt = now[3];
				return true;
			}
			int ny, nx;
			if(now[2] > 0) {
				for(int k = 0;k<8;k++) {
					ny = now[0] + hy[k];
					nx = now[1] + hx[k];
					int disK = now[2]-1;
					if(ny >= H || ny < 0 || nx>= W || nx<0 || v[ny][nx][K-disK] || map[ny][nx] == 1) continue;
					v[ny][nx][K-disK] = true;
					queue.add(new int[] {ny, nx, disK, now[3]+1});
				}
			}
			for(int k = 0;k<4;k++) {
				ny = now[0]+ dy[k];
				nx = now[1]+ dx[k];
				if(ny >= H || ny < 0 || nx>= W || nx<0 || v[ny][nx][K-now[2]] || map[ny][nx] == 1) continue;
				v[ny][nx][K-now[2]] = true;
				queue.add(new int[] {ny, nx, now[2],now[3]+1});
			}
		}
		return false;
	}

}
