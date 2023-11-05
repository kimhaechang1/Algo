import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int N;
	static int K;
	static boolean [] r; // 로봇들이 살아있는가?
	static int [] b;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		// 로봇을 올리는 위치에서 올리거나(왼쪽 끝)이거나 로봇이 이동할 때 마다 칸의 내구도가 1 감소
		// 로봇이 해당 칸으로 이동할 수 없다면 가만히 있으며
		// 이동할 수 없는 조건은 
		// 벨트가 한칸씩 밀리면서 이동하는데, 시계방향으로 밀린다.
		// 내구도 0인 칸의 개수가 k개 이상이라면 진행을 멈춘다.
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		r = new boolean[N+1];
		b = new int [2*N+1];
		int cnt = 0;
		stk = new StringTokenizer(bf.readLine());
		for(int i= 1;i<=2*N;i++) {
			b[i] = Integer.parseInt(stk.nextToken());
		}
		// 로봇은 올리는 위치에서만 올릴 수 있으므로 최대 1~N번째 벨트 칸에 존재할 수 있다.
		while(chk()) {
			// 벨트 이동  // peekFirst 가 2N번째
			// F 1 2 1 2 1 2 B
			// N번째 칸의 로봇이 도달하면 즉시 없어진다.
			b[0] = b[2*N];
			for(int i = 2*N;i>1;i-- ){
				b[i] = b[i-1];
			}
			b[1] = b[0];
			// 벨트 이동에 따른 내구도 감소 없는 로봇이동
			for(int i= N; i>1;i--) {
				r[i] = r[i-1];
			}
			// N번째 로봇 내리기
			// 0번째는 새로운 벨트가 올라오기 때문에 무조건 비어있다.
			r[1] = false;
			r[N] = false;
			// 로봇 이동
			for(int i= N;i>1;i--) {
				if(r[i-1] && !r[i] && b[i] > 0) {
					r[i-1] = false;
					r[i] = true;
					b[i]--;
				}
			}
			// 올리는칸에 로봇 올리기
			if(b[1] > 0) {
				r[1] = true;
				b[1]--;
			}
			cnt++;
		}
		System.out.print(cnt);
	}
	static boolean chk() {
		int c = 0;
		for(int i= 1;i<=2*N;i++) {
			if(b[i] == 0) c++;
			if(c >= K) return false;
		}
		return true;
	}
}