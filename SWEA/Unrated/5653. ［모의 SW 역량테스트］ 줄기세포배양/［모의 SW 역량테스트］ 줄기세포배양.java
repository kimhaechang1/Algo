import java.util.*;
import java.io.*;

public class Solution {
	static PriorityQueue<int []> pq;
	static int N;
	static int M;
	static int K;
	static int [][] map;
	static int [][] map2;
	static StringTokenizer stk;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// 세포의 상태는 X시간동안 비활성화 -> X시간동안 활성화 -> 그시간 지나면 죽음
		// 여기서말하는 X는 해당 세포의 생명력을 뜻함
		// 생명력이 높은 녀석들 순서로 큐에서 꺼내야함
		// 옆으로 번식할 경우 자기와 같은 시간과 생명력을 가진 번식이 이뤄짐
		// 예를들어 생명력이 2인 세포는
		// time = 2 아무런 일 일어나지않음
		// time = 1 아무런 일 일어나지않음
		// 그리고 먼저 번식했던 곳으로 겹쳐서 번식하지 않게 visited[][] 편성
		// time = 0 활성화되고 
		// time = -1 번식하고
		// time = -2 가 되면 죽어버림
		// 즉 time을 2로 설정하여 -1씩 하다가 -1이 된 순간 주변에 4방향으로 퍼뜨리고 생명력과 더했을때 0이 된 순간 죽어서 큐에 들어가지 않으면 됨
		// 각 세포별로 현재 시간에 맞춰서 행동을 한다.
		// 죽은 세포는 다시 큐에 넣지 않는다.
		// 만약에 50,50에서 최대시간만큼 증식한다고 하면 2초에 하나씩 증가하기 때문에 150씩 더했을 때 아래로 300 오른쪽으로 300이 되고
		// 만약에 0,0에서 최대시간만큼 증식한다고 하면 왼쪽으로 -150 위로 -150이 된다. 따라서 맵의 크기를 여유롭게 지어준 다음에 150을 더한만큼에 실제 맵에 찍어주면 된다.
		// 만약에 동일한 시간대에 하나의 셀에 두 세포가 번식하려 든다면 더 큰 세포가 우위를 가져야한다
		// 따라서 애초에 큐에서 꺼낼때부터 생명력이 강한걸 먼저 꺼내면 된다.
		
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int t= 1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			map = new int[N][M];
			for(int i= 0;i<N;i++) {
				stk = new StringTokenizer(bf.readLine());
				for(int j= 0;j<M;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			// 실제 맵으로 옮기기
			// 옮기면서 pq에 배양된 세포들 담기
			// y, x, time, val(생명력)
			map2 = new int[400][400];
			v = new boolean[400][400];
			pq = new PriorityQueue<>((o1, o2)->{return o2[3] - o1[3];}); 
			for(int i= 0;i<N;i++) {
				for(int j= 0;j<M;j++) {
					map2[150+i][150+j] = map[i][j];
					if(map2[150+i][150+j] >0) {
						v[150+i][150+j] = true;
						pq.add(new int[] {150+i, 150+j, map2[150+i][150+j], map2[150+i][150+j]});
					}
				}
			}
			bfs();
			/*for(int i = 0;i<map2.length;i++) {
				for(int j= 0;j<map2[i].length;j++) {
					System.out.print(map2[i][j]+" ");
				}
				System.out.println();
			}*/
			System.out.println("#"+t+" "+pq.size());
		}
	}
	static void bfs() {
		Queue<int []> queue = new LinkedList<>();
		
		while(K-- >0) {
			while(!pq.isEmpty()) {
				int [] now =  pq.poll();
				now[2]--; // 해당 세포의 시간 지내기
				if(now[2] == -1) { // 활성에 따른 번식 시작
					for(int k = 0;k<4;k++) {
						int ny = now[0] + dy[k];
						int nx = now[1] + dx[k];
						if(v[ny][nx]) continue;
						v[ny][nx] = true;
						map2[ny][nx] = now[3];
						queue.add(new int[] {ny, nx, now[3], now[3]}); // 주변에 비활성화된 세포를 퍼뜨림
					}
				}
				if(now[2] + now[3] == 0) continue; // 죽었단 것을 의미 
				queue.add(now); // 죽지만 않으면 비활성 아니면 활성이므로 임시큐에 넣음
			}
			while(!queue.isEmpty()) pq.add(queue.poll());
		}
	}
}