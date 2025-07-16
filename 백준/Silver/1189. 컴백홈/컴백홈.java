import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int r, c, k;
    static char[][] map;
    static int answer;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	
    	// 한수는 왼쪽 아래점, 집은 오른쪽 위에
    	// 한번 지나친곳은 절대 다시 방문하지 않음
    	// K 거리로 도착하는 모든 경우의수를 구하라
    	answer = 0;
    	boolean[][] v = new boolean[r][c];
    	if (map[r - 1][0] == 'T' || map[0][c - 1] == 'T') {
    		System.out.print(0);
    		return;
    	}
    	
    	v[r - 1][0] = true;
    	dfs(r - 1,0,1,v);
    	System.out.print(answer);
    }
    static void dfs(int y, int x, int depth, boolean[][] v) {
    	if (depth == k) {
    		if (y == 0 && x == c - 1) {
    			answer++;
    		}
    		return;
    	}
    	
    	for(int dir = 0; dir < 4; dir++) {
    		int ny = y + dy[dir];
    		int nx = x + dx[dir];
    		if (ny >= r || ny < 0 || nx >= c || nx < 0 || v[ny][nx] || map[ny][nx] == 'T') continue;
    		v[ny][nx] = true;
    		depth = depth + 1;
    		dfs(ny, nx, depth, v);
    		depth = depth - 1;
    		v[ny][nx] = false;
    	}
    }
    
    void testCase() throws Exception {
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {
    	stk = new StringTokenizer(bf.readLine());
    	r = Integer.parseInt(stk.nextToken());
    	c = Integer.parseInt(stk.nextToken());
    	k = Integer.parseInt(stk.nextToken());
    	map = new char[r][c];
    	for(int i= 0; i < r; i ++) {
    		map[i] = bf.readLine().toCharArray();
    	}
    }
}
