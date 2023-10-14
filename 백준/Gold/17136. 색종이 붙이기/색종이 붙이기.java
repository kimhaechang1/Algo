import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static int [] pcnt = {0, 5, 5, 5, 5, 5}; // 1칸짜리, 2*2칸짜리, 3*3칸짜리, 4*4, 5*5
	static StringTokenizer stk;
	static int min = 987654321;
	public static void main(String [] args) throws Exception{
		map = new int[10][10];
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int i= 0;i<10;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=0;j<10;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 완탐 돌려야 하고
		// 현재 칸에 1*1 부터 5*5까지 붙일수 있는 모든 경우를 따져봐야하고
		// 붙였다면 지도상에 1을 discount하고
		// 0,0부터 오른쪽으로 밀고 마지막 끝칸인 10,10까지 검사한다.
		// 해당 색종이를 붙일 수 있는지 여부를 chk메서드와
		// 붙이는 메서드가 필요할듯
		// 만약 10,10까지 한번도 도달할 수 없다면, 최솟값은 여전히 변동이 없을것
		
		dfs(0,0,0);
		if(min == 987654321) {
			min = -1;
		}
		System.out.println(min);
	}
	static void dfs(int y, int x, int cnt) {
		
		if(y == 10) {
			if(cnt < min) {
				min = cnt;
				
			}
			return;
		}
		if(y < 10 && x == 10) {
			dfs(y+1, 0,cnt);
			return;
		}
		
		
		if(map[y][x] == 1) {
			for(int i = 1;i<=5;i++) {
				if(pcnt[i] > 0 && chk(y,x,i)) {
					go(y,x,i,0);
					pcnt[i]--;
					dfs(y,x+1,cnt+1);
					pcnt[i]++;
					go(y,x,i,1);
				}
			}
		}else {
			dfs(y,x+1, cnt);
		}
	}
	static boolean chk(int sy, int sx, int pnum) {
		for(int i = sy; i<sy+pnum;i++) {
			for(int j= sx;j<sx+pnum;j++) {
				if(i >= 10 || j>= 10 || map[i][j] == 0) return false;
			}
		}
		return true;
	}
	static void go(int sy, int sx, int pnum, int val) {
		for(int i = sy;i<sy+pnum;i++) {
			for(int j= sx;j<sx+pnum;j++) {
				map[i][j] = val;
			}
		}
	}
}