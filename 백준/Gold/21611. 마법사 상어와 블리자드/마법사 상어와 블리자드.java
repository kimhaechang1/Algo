import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int n;
	static int m;
	static int [][] map;
	static int [][] command;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] score;
	static int sy;
	static int sx;
	static int [][] mark;
	static HashMap<Integer, int[]> hashmap;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new int[n][n];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<n;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		command = new int[m][2];
		for(int i = 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			command[i][0] = Integer.parseInt(stk.nextToken());
			command[i][1] = Integer.parseInt(stk.nextToken());
		}
		sy = (n-1)/2;
		sx = (n-1)/2;
		score = new int[4];
		mark = new int[n][n];
		hashmap = new HashMap<>();
		mark();
		for(int [] comm : command) {
			int d = comm[0]-1;
			int s = comm[1];
			attack(d, s);
			go();
			while(findAttack());
			farm();
		}
		int sum = 0;
		for(int i = 1;i<4;i++) {
			sum += (i * score[i]);
		}
		System.out.print(sum);
	}
	static void mark() {
		int y = sy;
		int x = sx;
		int sd = 2;
		int len = 1;
		int cnt = 0;
		int dirTurn = 0;
		int val = 0;
		mark[y][x] = val;
		val++;
		while(true) {
			y += dy[sd];
			x += dx[sd];
			cnt++;
			if(cnt == len) {
				if(sd == 2) {
					sd = 1;
				}else if(sd == 1) {
					sd = 3;
				}else if(sd == 3) {
					sd = 0;
				}else {
					sd = 2;
				}
				cnt=0;
				dirTurn++;
			}
			if(dirTurn == 2) {
				dirTurn = 0;
				len++;
			}
			if(OOB(y, x)) {
				break;
			}
			mark[y][x] = val;
			hashmap.put(val, new int[] {y, x});
			val++;
		}
	}
	static void attack(int dir, int s) {
		int y = sy;
		int x = sx;
		while(s-- > 0) {
			y += dy[dir];
			x += dx[dir];
			if(OOB(y, x)) break;
			map[y][x] = 0;
		}
	}
	static void go() {
		int [][] temp = new int[n][n];
		int temy = sy;
		int temx = sx;
		int y = sy;
		int x = sx;
		int sd = 2;
		int tsd = 2;
		int len = 1;
		int tlen = 1;
		int cnt = 0;
		int tcnt = 0;
		int dirTurn = 0;
		int tempDirTurn = 1;
		temy += dy[tsd];
		temx += dx[tsd];
		tsd = 1;
		while(true) {
			y += dy[sd];
			x += dx[sd];
			cnt++;
			if(cnt == len) {
				if(sd == 2) {
					sd = 1;
				}else if(sd == 1) {
					sd = 3;
				}else if(sd == 3) {
					sd = 0;
				}else {
					sd = 2;
				}
				cnt=0;
				dirTurn++;
			}
			if(dirTurn == 2) {
				dirTurn = 0;
				len++;
			}
			if(OOB(y, x)) {
				break;
			}
			if(map[y][x] != 0) {
				temp[temy][temx] = map[y][x];
				temy += dy[tsd];
				temx += dx[tsd];
				tcnt++;
				if(tcnt == tlen) {
					if(tsd == 2) {
						tsd = 1;
					}else if(tsd == 1) {
						tsd = 3;
					}else if(tsd == 3) {
						tsd = 0;
					}else {
						tsd = 2;
					}
					tcnt=0;
					tempDirTurn++;
				}
				if(tempDirTurn == 2) {
					tempDirTurn = 0;
					tlen++;
				}
			}
		}
		for(int i =0;i<n;i++) {
			map[i] = temp[i].clone();
		}
	}
	static boolean findAttack() {
		int prev = 0;
		int cnt = 0;
		int start = 0;
		int end = 0;
		boolean flg = false;
		for(Map.Entry<Integer, int[]> entry : hashmap.entrySet()) {
			int idx = entry.getKey();
			int [] pos = entry.getValue();
			int y = pos[0];
			int x = pos[1];
			if(map[y][x] == 0) break;
			if(prev == map[y][x]) {
				end = idx;
				cnt++;
			}else {
				if(cnt >= 4) {
					flg = true;
					for(int i = start;i<=end;i++) {
						int [] p = hashmap.get(i);
						score[map[p[0]][p[1]]]++;
						map[p[0]][p[1]] = 0;
					}
				}
				start = idx;
				end = idx;
				cnt = 1;
				prev = map[y][x];
			}
			
		}
		if(cnt >= 4) {
			flg = true;
			for(int i = start;i<=end;i++) {
				int [] p = hashmap.get(i);
				score[map[p[0]][p[1]]]++;
				map[p[0]][p[1]] = 0;
			}
		}
		go();
		return flg;
	}
	static void farm() {
		int prev = 0;
		int cnt = 0;
		int [][] temp = new int[n][n];
		int tempidx = 1;
		for(Map.Entry<Integer, int[]> entry : hashmap.entrySet()) {
			int [] pos = entry.getValue();
			int y = pos[0];
			int x = pos[1];
			if(map[y][x] == 0) break;
			if(prev == map[y][x]) {
				cnt++;
			}else {
				if(prev == 0) {
					prev = map[y][x];
					cnt = 1;
				}
				else{
					if(!hashmap.containsKey(tempidx)) break;
					int [] po = hashmap.get(tempidx);
					temp[po[0]][po[1]] = cnt;
					tempidx++;
					if(!hashmap.containsKey(tempidx)) break;
					po = hashmap.get(tempidx);
					temp[po[0]][po[1]] = prev;
					tempidx++;
					cnt = 1;
					prev = map[y][x];
				}
			}
		}
		if(hashmap.containsKey(tempidx)) {
			int[] po = hashmap.get(tempidx);
			temp[po[0]][po[1]] = cnt;
			tempidx++;
			if(hashmap.containsKey(tempidx)) {
				po = hashmap.get(tempidx);
				temp[po[0]][po[1]] = prev;
			}
		}
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				map[i][j] = temp[i][j];
			}
		}
	}
	static boolean OOB(int y, int x) {
		if(y >=n || y < 0 || x>=n || x<0) return true;
		return false;
	}
	static void print(int [][] map) {
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("==============");
	}
}