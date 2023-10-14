import java.util.*;
import java.io.*;

public class Main{
	static int H;
	static int W;
	static char [][] map;
	static boolean [][] v;
	static boolean [] keys;
	static ArrayList<int []> [] doorList;
	static StringTokenizer stk;
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t= 1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			map = new char[H+2][W+2];
			for(int i= 0;i<H;i++) {
				String str = bf.readLine();
				for(int j=0;j<W;j++) {
					map[i+1][j+1] = str.charAt(j);
				}
			}
			keys = new boolean[26];
			doorList = new ArrayList[26];
			for(int i= 0;i<26;i++) {
				doorList[i] = new ArrayList<>();
			}
			v = new boolean[H+2][W+2];
			
			for(int i = 0;i<H+2;i++) {
				map[i][0] = '.';
				map[i][W+1] = '.';
			}
			for(int i = 0;i<W+2;i++) {
				map[0][i] = '.';
				map[H+1][i] = '.';
			}
			char [] ks = bf.readLine().toCharArray();
			if(ks[0] - '0' != 0) {
				for(char k : ks) {
					keys[k-'a']=true;
				}
			}
			cnt = 0;
			bfs();
//			for(int i= 0;i<H+2;i++) {
//				for(int j= 0;j<W+2;j++) {
//					if(v[i][j]) System.out.print("1 ");
//					else System.out.print("0 ");
//				}
//				System.out.println();
//			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static void bfs() {
		Queue<int []> queue = new LinkedList<>();
		v[0][0] = true;
		queue.add(new int[] {0,0});
		
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= H+2 || ny < 0 || nx >= W+2 || nx < 0 || v[ny][nx] || map[ny][nx] == '*') continue;
				if(map[ny][nx] == '.') {
					v[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}else if(map[ny][nx] >= 65 && map[ny][nx] <= 90 ) {
					// 문의 경우
					// a~z = 65 ~ 90
					// A~Z = 97 ~ 122
					if(keys[(map[ny][nx] + 32)-'a']) {
						// 열쇠가 있으면 열고 들어가면 된다.
						v[ny][nx] = true;
						queue.add(new int[] {ny, nx});
					}else {
						// 현재 상태에서는 열쇠가 없다면, 들어갈 수 없으므로 나중에 열쇠가 생겼을 때 방문할 수 있도록 후보 리스트에 둔다.
						doorList[map[ny][nx]-'A'].add(new int[] {ny,nx});
					}
				}else if(map[ny][nx] >= 97 &&  map[ny][nx] <= 122) {
					// 열쇠의 경우
					// 해당 열쇠를 먹은것을 keys 배열에 처리해준다.
					v[ny][nx] = true;
					queue.add(new int[] {ny, nx});
					keys[map[ny][nx]-'a'] = true;
					// 그리고 지금 당장에 문을 딸 수 있는 문들을 따러갈 수 있게 큐에 다시 고려 해야 할 좌표를 넣는다.
					for( int [] yx : doorList[(map[ny][nx]-32)-'A']) {
						if(v[yx[0]][yx[1]]) continue;
						v[yx[0]][yx[1]] = true;
						queue.add(new int[] {yx[0],yx[1]});
					}
				}else if(map[ny][nx] == '$') {
					map[ny][nx] = '.';
					v[ny][nx] = true;
					cnt++;
					queue.add(new int[] {ny, nx});
				}
			}
		}
	}
}