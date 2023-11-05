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
		// 문제 해석본
		// 1~2N번째의 벨트가 있다.
		// 1초동안 일어나는 시뮬레이션 순서
		// 1. 벨트를 2N번째부터 옆으로 민다. 이 때 2N번째는 1번째로 벨트칸이 이동한다.
		// 2. 벨트의 이동으로 인해 벨트위의 로봇도 자연스럽게 이동된다.
		// 3. 내리는 칸(N번째 칸)에 도달한 로봇은 무조건 떨어진다.
		// 3-1. 동시에 생각해보면 1번째 칸은 2N번째 칸이 올라오기 때문에 항상 로봇이 없는 벨트가 올라온다.
		// 4. 벨트위의 로봇들이 움직인다.
		// 5. 움직일 때, N번째 칸으로 이동한 로봇들은 즉시 또 내려간다.
		// 6. 1번째 칸에 내구도가 있다면 새 로봇을 올려보낸다.
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
					r[N] = false;
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