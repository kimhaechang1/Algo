import java.util.*;
import java.io.*;

public class Main{
	static char[][] map;
	static int [] dy = {0,-1,1,0,0,-1,1,-1,1};
	static int [] dx = {0,0,0,-1,1,1,-1,-1,1};
	static int N = 8;
	static Queue<int []> queue;
	static int ans = 0;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][N];
		Queue<int []> w = new ArrayDeque<>();
		for(int i= 0;i<N;i++) {
			String str = bf.readLine();
			for(int j = 0;j<N;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		queue = new ArrayDeque<>();
		for(int i = N-1;i>-1;i--) {
			for(int j = 0;j<N;j++) {
				if(map[i][j] == '#') {
					w.add(new int[] {i,j});
				}
			}
		}
		map[N-1][0] = 'P';
		Queue<int []> p = new ArrayDeque<>();
		while(true) {
			if(!chk()) {
				break;
			}
			for(int i= 7;i>-1;i--) {
				for(int j= 0;j<8;j++) {
					if(map[i][j]=='P') {
						p.add(new int[] {i, j});
					}
				}
			}
			int psize = p.size();
			for(int i= 0;i<psize;i++) {
				int [] now = p.poll();
				for(int k = 0;k<9;k++) {
					int ny = now[0] + dy[k];
					int nx = now[1] + dx[k];
					if(ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] != '.') continue;
					map[ny][nx] = 'P';
				}
			}
			
			int wsize = w.size();
			for(int i= 0;i<wsize;i++) {
				int [] now = w.poll();
				int ny = now[0] + dy[2];
				
				if(ny >= N) {
					map[now[0]][now[1]] = '.';
					continue;
				}
				map[now[0]][now[1]] = '.';
				map[ny][now[1]] = '#';
				w.add(new int[] {ny, now[1]});
			}
		}
		System.out.println(ans);
	}
	static boolean chk() {
		// 남은 사람이 있는지 검사
		int minP = -1;
		t :for(int i= 0;i<8;i++) {
			for(int j= 0;j<8;j++) {
				if(map[i][j] == 'P') {
					minP = i;
					break t;
				}
			}
		}
		if(minP == -1) {
			return false;
		}
		// 벽보다 위에 있는 사람이 존재하는지 검사.
		int minW = -1;
		t :for(int i= 0;i<8;i++) {
			for(int j= 0;j<8;j++) {
				if(map[i][j] == '#') {
					minW = i;
					break t;
				}
			}
		}
		if(minW == -1) {
			ans = 1;
			return false;
		}
		
		if(minW > minP) {
			ans = 1;
			return false;
		}
		return true;
	}
}