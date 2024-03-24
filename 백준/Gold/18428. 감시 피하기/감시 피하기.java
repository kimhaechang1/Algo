import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int [][] map;
	static StringTokenizer stk;
	static ArrayList<int[]> list;
	static ArrayList<int[]> Tlist;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		Tlist = new ArrayList<>();
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=0;j<N;j++) {
				String data = stk.nextToken();
				if(data.equals("S")) {
					map[i][j] = 1;
				}else if(data.equals("T")) {
					Tlist.add(new int[] {i,j});
					map[i][j] = 2;
				}else if(data.equals("X")) {
					list.add(new int[] {i, j});
				}
			}
		}
		boolean flag = true;
		// 2 : T, S : 1, 맵크기 커봤자 6*6, 선생님수 5이하
		// 상 하 좌 우 4가지 방향으로 감시를 할 수 있으며 장애물 뒤 학생은알 수 없음
		// 36! = 36 35 34 / 6
		// 3! * 33!
		int [] comb = new int[list.size()];
		Arrays.fill(comb, comb.length-3,comb.length,1);
		do {
			flag = true;
//			System.out.println(Arrays.toString(comb));
			int [][] t = new int[3][2];
			int idx = 0;
			for(int i= 0;i<comb.length;i++) {
				if(comb[i] == 1) {
					t[idx][0] = list.get(i)[0];
					t[idx++][1] = list.get(i)[1];
				}
			}
			for(int i= 0;i<3;i++) {
				map[t[i][0]][t[i][1]] = 7;
			}
			
			L :for(int [] yx : Tlist) {
				for(int k = 0;k<4;k++) {
					if(!go(yx[0], yx[1],k)) {
						flag = false;
						break L;
					}
				}
			}
			if(flag) {
				break;
			}
			for(int i= 0;i<3;i++) {
				map[t[i][0]][t[i][1]] = 0;
			}
			
		}while(np(comb));
		if(flag) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}
	static boolean np(int [] arr) {
		int i = arr.length-1;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		if(i == 0) return false;
		int j = arr.length-1;
		while(arr[i-1] >= arr[j]) j--;
		int t = arr[j];
		arr[j] = arr[i-1];
		arr[i-1] =t;
		int k = arr.length-1;
		while(i<k) {
			t = arr[k];
			arr[k] = arr[i];
			arr[i] = t;
			i++;
			k--;
		}
		return true;		
	}
	static boolean go(int sy, int sx,int d) {
		int ny = sy;
		int nx = sx;
		boolean res = true;
		while(true) {
			ny+=dy[d];
			nx+=dx[d];
			
			if(ny >=N || ny < 0 || nx>=N || nx<0) break;
			if(map[ny][nx] == 1) {
				res = false;
				break;
			}
			else if(map[ny][nx] == 7) {
				res = true;
				break;
			}
		}
		return res;
		
	}

}
