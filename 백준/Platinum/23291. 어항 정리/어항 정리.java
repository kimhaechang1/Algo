import java.util.*;
import java.io.*;


public class Main{
	static StringTokenizer stk;
	static int n;
	static int k;
	static int [] arr;
	static int [][] temp;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n  = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		arr = new int[n];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0;i<n;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		// 물고기의 수가 가장 적은 어항에 물고기를 한마리 넣는다.
		// 그러한 어항이 여러개 라면 물고기의 수가 최소인 어항 모두에 넣는다.
		// 가장 왼쪽에 있는 어항을 그 오른쪽 어항위에 올린다.
		// 2개 이상 쌓여있는 어항을 모두 공중부양 시킨 다음 전체를 시계방향 90도 회전
		// 그 이후 공중 부양시킨 어항을 바닥에 있는 어항위에 올린다.
		// 바닥의 가장 왼쪽에 있는 어항 위에 공중 부양 시킨 어항 중 가장 왼쪽에 있는 어항이 있어야 한다.
		// 이 작업은 공중부양시킨 어항 중 가장 오른쪽에 있는 어항의 아래가 바닥에 있는 어항이 있을때 까지 반복
		int cnt = 0;
		/*for(int i = 0;i<temp.length;i++) {
			System.out.println(Arrays.toString(temp[i]));
		}*/
		while(true) {
			if(getCha() <= k) {
				break;
			}
			cnt++;
			init();
			while(go());
			move();
			moveToArr();
			rotateHalf();
			move();
			moveToArr();
		}
		System.out.println(cnt);
	}
	static int getCha() {
		int [] temp = arr.clone();
		Arrays.sort(temp);
		return temp[arr.length-1] - temp[0];
	}
	static void init() {
		ArrayList<Integer> list =new ArrayList<>();
		int min = 999999999;
		for(int i = 0;i<n;i++) {
			if(min > arr[i]) {
				min = arr[i];
				list.clear();
				list.add(i);
			}else if(min == arr[i]) {
				list.add(i);
			}
		}
		for(int k : list) arr[k]++;
		temp = new int[100][100];
		for(int i = 0;i<n;i++) {
			temp[99][i] = arr[i];
		}
		temp[98][1] =temp[99][0];
		temp[99][0] = 0;	
	}
	static boolean go() {
		int iy = 98;
		int ix = n;
		int cnt = 0;
		while(ix-- > -1) {
			if(temp[99][ix] == 0) break;
			int h = isTall(ix);
			if(h == 1)continue;
			if(h >n - (ix+1)) return false;
			int x = ix+1+cnt;
			int sx = ix;
			for(int i = 0;i<h;i++) {
				temp[iy][x++] = temp[99-i][sx];
				temp[99-i][sx] = 0;
			}
			iy--;
			cnt++;
		}
		return true;
	}
	static int isTall(int x) {
		int cnt = 1;
		int y = 99;
		while(true) {
			--y;
			if(temp[y][x] > 0) {
				cnt++;
			}else if(temp[y][x] == 0) {
				break;
			}
		}
		return cnt;
	}
	static void move() {
		int [] dy = {-1,1,0,0};
		int [] dx = {0,0,-1,1};
		int [][] tempMap =  new int[100][100];
		for(int i = 99;i>-1;i--) {
			for(int j = 0;j<100;j++) {
				if(temp[i][j] > 0) {
					int val = temp[i][j];
					for(int k = 0;k<4;k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if(OOB(ny, nx) || temp[ny][nx] == 0) continue;
						int cha = Math.abs(temp[ny][nx] - val);
						if(cha < 5) continue;
						if(val > temp[ny][nx]) {
							tempMap[i][j] -= (cha/5);
							tempMap[ny][nx] += (cha/5);
						}else {
							tempMap[ny][nx] -= (cha/5);
							tempMap[i][j] += (cha/5);
						}
					}
				}
			}
		}
		for(int i = 99; i>-1;i--) {
			for(int j = 0;j<100;j++){
				if(tempMap[i][j] == 0) continue;
				temp[i][j] += (tempMap[i][j]/2);
			}
		}
		
	}
	static boolean OOB(int y, int x) {
		return y >= 100 || y < 0 || x>=100 || x < 0;
	}
	static void moveToArr() {
		int idx = 0;
		for(int i = 0;i<100;i++) {
			if(temp[99][i] == 0) continue;
			int h = isTall(i);
			if(h == 1) {
				arr[idx++] = temp[99][i];
				temp[99][i] = 0;
			}else {
				for(int j = 0;j<h;j++) {
					arr[idx++] = temp[99-j][i];
					temp[99-j][i] = 0;
				}
			}
		}
	}
	static void rotateHalf() {
		for(int i = 0;i<n;i++) {
			temp[99][i] = arr[i];
		}
		int sy= 99;
		int ty = 98;
		int tx;
		for(int i = 0;i<100;i++) {
			if(temp[sy][i] == 0) continue;
			int sx = i;
			tx = sx + (n/2);
			for(int j= sx+(n/2-1);j>=sx;j--) {
				 temp[ty][tx++] = temp[sy][j];
				 temp[sy][j] = 0;
			}
			break;
		}
		
		ty--;
		sy--;
		for(int i = 0;i<100;i++) {
			if(temp[sy][i] == 0) continue;
			int sx = i;
			tx = sx + (n/4);
			for(int j = 98;j<100;j++) {
				for(int k = sx+(n/4-1);k>=sx;k--) {
					temp[ty][tx++] = temp[j][k];
					temp[j][k] = 0;
				}
				tx = sx + (n/4);
				ty--;
			}
			break;
		}
		
	}
}