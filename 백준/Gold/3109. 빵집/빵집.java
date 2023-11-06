import java.util.*;
import java.io.*;

public class Main{
	static char [][] map;
	static int R;
	static int C;
	static StringTokenizer stk;
	static int [] dy = {-1,0,1};
	static int [] dx = {1,1,1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		for(int i= 0;i<R;i++) {
			char []ch = bf.readLine().toCharArray();
			for(int j= 0;j<C;j++) {
				map[i][j] = ch[j];
			}
		}
		// 그리디하게 사고하자
		// 이 문제를 그리디하게 풀 수 있는 이유는 실을 그려보면 알 수 있다.
		// 이미 탐색에 실패한 칸에 대해서는 더이상 고려하지 않는다.
		// 왜냐하면 다른 출발점에서 실패한 영역을 탐색해봤자 똑같이 실패하기 때문이다.
		// 따라서 경우의수 복원이 없다.
		// 최대한 많이 연결하는 방법은 최대한 오른쪽 위로 탐색해서 다른선과 간섭을 줄이면 된다.
		// 움직이는 방향을 오른쪽위 정면 오른쪽 아래 순으로 탐색하게 만든다.
		// 매 칸마다 3가지 방향으로 이동할 수 있다.
		
		int cnt = 0;
		for(int i= 0;i<R;i++) {
			if(chk(i, 0)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	static boolean chk(int sy, int sx) {
		map[sy][sx] = 'x';
		
		if(sx == C-1) {
			return true;
		}
		
		if(sy-1 > -1 && sx+1 < C && map[sy-1][sx+1] == '.') {
			if(chk(sy-1, sx+1)) {
				return true;
			}
		}
		if(sx+1 < C && map[sy][sx+1] == '.') {
			if(chk(sy, sx+1)) {
				return true;
			}
		}
		if(sy+1 < R && sx+1 < C && map[sy+1][sx+1] == '.') {
			if(chk(sy+1, sx+1)) {
				return true;
			}
		}
		return false;
	}
}