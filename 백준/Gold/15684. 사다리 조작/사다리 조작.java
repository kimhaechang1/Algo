import java.util.*;
import java.io.*;


public class Main{
	static StringTokenizer stk;
	static int [][] map;
	static int m;
	static int n;
	static int h;
	static int min;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		h = Integer.parseInt(stk.nextToken());
		map = new int[h][n];
		min = 987654321;
		for(int i = 0;i<h;i++) {
			for(int j = 0;j<n;j++) {
				map[i][j] = -1;
			}
		}
		for(int i = 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken())-1;
			int h = Integer.parseInt(stk.nextToken())-1;
			map[s][h] = h+1;
			map[s][h+1] = h;
		}
		if(go()) {
			System.out.println(0);
		}else {
			dfs(0,0,0,0);
			if(min == 987654321) {
				System.out.println(-1);
			}else{
				System.out.println(min);
			}
			
		}
		
	}
	static boolean go() {
		for(int i = 0;i<n;i++) {
			int sy = 0;
			int sx = i;
			while(true) {
				if(sy == h) {
					break;
				}
				if(map[sy][sx] > -1) {
					sx = map[sy][sx];
				}
				sy+=1;
			}
			if(sx != i) {
				return false; 
			}
		}
		return true;
	}
	static void dfs(int depth, int sy, int sx, int cnt) {
        
		if(cnt > 3) {
			return;
		}
        if(cnt > min){
            return;
        }
		if(depth == n) {
			if(min > cnt) {
				min = Math.min(cnt, min);
			}
			return;
		}
		if(depth < n && sy == h) {
			if(sx == depth) {
				depth += 1;
				dfs(depth, 0, depth, cnt);
				depth -= 1;
			}
			return;
		}else if(depth < n && sy < h) {
			
			if(map[sy][sx] == -1 && cnt <= 3) {
				if(sx-1 > -1 && map[sy][sx-1] == -1 && cnt < 3) {
					map[sy][sx-1] = sx;
					map[sy][sx] = sx-1;
					cnt+=1;
					sy += 1;
					sx -=1;
					dfs(depth, sy, sx, cnt);
					sx += 1;
					sy -= 1;
					cnt-=1;
					map[sy][sx] = -1;
					map[sy][sx-1] = -1;
				}
				if(sx + 1 <= n-1 && map[sy][sx+1] == -1 && cnt < 3) {
					map[sy][sx+1] = sx;
					map[sy][sx] = sx+1;
					cnt +=1;
					sy += 1;
					sx += 1;
					dfs(depth, sy, sx, cnt);
					sy -= 1;
					sx -= 1;
					cnt -= 1;
					map[sy][sx+1] = -1;
					map[sy][sx] = -1;
				}
				sy += 1;
				dfs(depth, sy, sx, cnt);
				sy -= 1;
				return;
			}else if(map[sy][sx]!=-1){
				int temp = sx;
				sx = map[sy][sx];
				sy += 1;
				dfs(depth, sy, sx, cnt);
				sy -= 1;
				sx = temp;
				return;
			}
		}
	}
}