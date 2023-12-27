import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main{
	static char [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static StringTokenizer stk;
	static int h,w;
	static class Person{
		ArrayList<int []> open;
		int y;
		int x;
		public Person(int y, int x, ArrayList<int []> open) {
			this.y = y;
			this.x = x;
			this.open = open;
		}
		public String toString() {
			return "y : "+y+" x : "+x+" size : "+open.size();
		}
	}
	static int [][] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// * : 지날수 없는 벽
		// # : 문
		// $ : 죄수 위치
		int T = Integer.parseInt(bf.readLine());
		for(int t= 1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			h = Integer.parseInt(stk.nextToken());
			w = Integer.parseInt(stk.nextToken());
			map = new char[h+2][w+2];
			Person [] ps = new Person[2];
			int idx = 0;
			for(int i = 1;i<=h;i++) {
				char [] frag = bf.readLine().toCharArray();
				for(int j = 1;j<=w;j++) {
					map[i][j] = frag[j-1];
					if(map[i][j] == '$') {
						ps[idx++] = new Person(i, j, new ArrayList<>());
					}
				}
			}
			
			// init
			for(int i = 0;i<h+2;i++) {
				map[i][0] = '.';
				map[i][w+1] = '.';
			}
			for(int i = 0;i<w+2;i++) {
				map[0][i] = '.';
				map[h+1][i] = '.';
			}
			// 각 죄수별로 최소의 문을 열고 나가는 경우들을 쭉 찍는다.
			// 그리고 외부인이 각 죄수들에게 도착하는 경우의수를 찍는다.
			v = new int[h+2][w+2];
			int [][] sum1 = new int [h+2][w+2];
			bfs(ps[0].y, ps[0].x, map, true);
			for(int i = 0;i<h+2;i++) {
				sum1[i] = v[i].clone();
			}
			
			int [][] sum2 = new int [h+2][w+2];
			bfs(ps[1].y, ps[1].x, map, true);
			for(int i = 0;i<h+2;i++) {
				sum2[i] = v[i].clone();
			}	
			int [][] sum3 = new int [h+2][w+2];
			
			bfs(0, 0, map, false);
			for(int i = 0;i<h+2;i++) {
				sum3[i] = v[i].clone();
			}	
			int min = 987654321;
			for(int i = 1;i<=h;i++) {
				for(int j = 1;j<=w;j++) {
					int s = 0;
					if(sum1[i][j] == Integer.MAX_VALUE || sum2[i][j] == Integer.MAX_VALUE || sum3[i][j] == Integer.MAX_VALUE) continue;
					if(map[i][j] == '*') continue;
                    // 문은 한번 열어두면 계속 열려있는 상태이므로
					s = (sum1[i][j] + sum2[i][j] + sum3[i][j]);
					if(map[i][j] == '#') s-=2;
					if(min > s) {
						min = s;
					}
				}
			}
			System.out.println(min);
			
		}
	}
	static void bfs(int sy, int sx, char[][] map, boolean isPrison) {
		PriorityQueue<int []> q = new PriorityQueue<>((a, b)->{
			return a[2] - b[2];
		});
		q.add(new int[] {sy, sx, 0});
		for(int i = 0;i<h+2;i++) {
			Arrays.fill(v[i], Integer.MAX_VALUE);
		}
		v[sy][sx] = 0;
		boolean [][] visit = new boolean[h+2][w+2];
		visit[sy][sx] = true;
		while(!q.isEmpty()) {
			int [] now = q.poll();
			for(int k = 0;k<4;k++) {
				int ny = now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(!OOB2(ny, nx) && !visit[ny][nx] && map[ny][nx] != '*') {
					visit[ny][nx] = true;
					
					if(map[ny][nx] == '#') {
						v[ny][nx] = now[2]+1;
						q.add(new int[] {ny, nx, now[2]+1});
					}else{
						v[ny][nx] = now[2];
						q.add(new int[] {ny, nx, now[2]});
					}
				}
			}
		}
		return;
	}
	static char[][] copy() {
		char [][] temp = new char[h+2][w+2];
		for(int i = 0;i<h+2;i++) {
			temp[i] = map[i].clone();
		}
		
		return temp;
	}
	static boolean OOB2(int y, int x) {
		return y >= h+2 || y < 0 || x >= w+2 || x < 0;
	}
	static void printMap(char [][] map) {
		for(int i = 0;i<h+2;i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	static void print(int [][] map) {
		for(int i = 0;i<h+2;i++) {
			for(int j = 0;j<w+2;j++) {
				if(map[i][j] == Integer.MAX_VALUE) {
					System.out.print("I ");
				}else {
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}
}