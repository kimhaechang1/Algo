import java.util.*;
import java.io.*;

// start 19:24
// end
public class Main{
	static int n;
	static int [][] infos;
	static StringTokenizer stk;
	static int [] dx = {1,0,-1,0};
	static int [] dy = {0,-1,0,1};
	static boolean [][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		infos = new int[n][4];
		for(int i= 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			infos[i][0] = Integer.parseInt(stk.nextToken());
			infos[i][1] = Integer.parseInt(stk.nextToken()); // 
			infos[i][2] = Integer.parseInt(stk.nextToken()); // 방향
			infos[i][3] = Integer.parseInt(stk.nextToken()); // 세대
		}
		map = new boolean[101][101];
		// 0: 우, 1: 상, 2: 좌, 3: 하
		// -1 세대의 드래곤커브 끝점을 기준으로 90도 회전시킨다음 이전 세대의 끝점에 붙인것
		// 드래곤 커브를 잘 그리는 것이 중요할듯
		// 왔던 순서에 역순으로 반시계방향을 돌리는 방향대로 이동해야함 1만큼
		// 0세대때 오른쪽으로 이동했다면 반시계방향이니까 위로 이동해야함
		// 쌓인 스택 [오른, 위]
		// 1세대때 스택에 꺼내면서 위니까 반시계는 왼, 오른이니까 반시계는 위
		// 스택에 넣고 [오른, 위, 왼, 위]
		// 다음 세대때에는
		// 위였으니까 왼, 왼이었으니까 아래, 위였으니까 반시계면 왼,
		int idx = 0;
		while(idx < n) {
			go(idx);
			idx++;
		}
		
		// 한번 오른쪽, 그다음 아래, 그다음 대각선 아래
		int [] dy = {0, 1, 1};
		int [] dx = {1, 0, 1};
		int cnt = 0;
		for(int i= 0;i<100;i++) {
			for(int j= 0;j<100;j++) {
				if(map[i][j]) {
					boolean isSquare = true;
					for(int dir = 0;dir<3;dir++) {
						int ny = i + dy[dir];
						int nx = j + dx[dir];
						if(!map[ny][nx]) {
							isSquare = false;
							break;
						}
					}
					if(isSquare) {
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
		
	}
	static void go(int idx) {
		int sx = infos[idx][0];
		int sy = infos[idx][1];
		int sd = infos[idx][2];
		int g = infos[idx][3];
		int sg = 0;
		List<Integer> list = new ArrayList<>();
		map[sx][sy] = true;
		int nx = sx + dx[sd];
		int ny = sy + dy[sd];
		map[nx][ny] = true;
		sg++;
		list.add(sd);
		int lx = nx;
		int ly = ny;
		while(sg <= g) {
			int dirIdx = list.size()-1;
			while(dirIdx >= 0) {
				int dir = list.get(dirIdx);
				int nd = (dir + 1) % 4;
				lx = lx + dx[nd];
				ly = ly + dy[nd];
				map[lx][ly] = true;
				list.add(nd);
				dirIdx--;
			}
//			for(int i= 0;i<6;i++) {
//				for(int j= 0;j<6;j++) {
//					System.out.print(map[j][i] ? "1 " : "0 ");
//				}
//				System.out.println();
//			}
//			System.out.println("#######pg : "+sg +" idx : "+idx);
			sg++;
		}
	}
}