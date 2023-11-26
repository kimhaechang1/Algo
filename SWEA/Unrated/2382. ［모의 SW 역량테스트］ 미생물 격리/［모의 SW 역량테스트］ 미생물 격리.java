import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb;
	static StringTokenizer stk;
	static int N;
	static int M;
	static int K;
	static int [] dy= {0,-1,1,0,0}; // 상하좌우
	static int [] dx= {0,0,0,-1,1};
	static class Map{
		int size; // 미생물 개수
		int time; // 미생물 도착 시간
		int d; // 이동방향
		int idx;
		public Map(int size, int time, int d, int idx) {
			this.size = size;
			this.time = time;
			this.d = d;
			this.idx = idx;
		}
		@Override
		public String toString() {
			return "Map [size=" + size + ", time=" + time + ", d=" + d + ", idx=" + idx + "]";
		}
	}
	static Map [][] map;
	static class Data{
		int y;
		int x;
		int size;
		int time;
		int d;
		boolean alive;
		public Data(int y,int x, int size, int time, int d, boolean a) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.time = time;
			this.d = d;
			this.alive = a;
		}
		@Override
		public String toString() {
			return "Data [y=" + y + ", x=" + x + ", size=" + size + ", time=" + time + ", d=" + d + ", alive=" + alive
					+ "]";
		}
	}
	static Data [] dt;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb= new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int t= 1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			map = new Map[N][N];
			dt = new Data[K];
			for(int i= 0;i<K;i++) {
				stk = new StringTokenizer(bf.readLine());
				int y = Integer.parseInt(stk.nextToken());
				int x = Integer.parseInt(stk.nextToken());
				int size = Integer.parseInt(stk.nextToken());
				int d = Integer.parseInt(stk.nextToken());
				dt[i] = new Data(y,x,size, 0,d, true);
			}
			for(int i = 0;i<K;i++) {
				Data d = dt[i];
				map[d.y][d.x] = new Map(d.size, 0, d.d, i);
			}
			// i == 0 && j == 0 && i ==N && j==M 인 곳을 마주친다면
			// 군집 내 미생물의 절반(소수점 아래 절삭)이 죽는다. 만약 size가 1인놈이 가는 경우 군집이 사라짐
			// 이동방햐이 반대로 바뀐다.
			// 서로가 동일한 시간에 만난다면 미생물 수가 더 많은쪽으로 합쳐진다.
			// 미생물의 사이즈는 서로 무조건 달다.
			Arrays.sort(dt, (o1, o2)->{
				return o2.size - o1.size; 
			});
			while(M-- > 0) {
				// 각 미생물들 이동시키기
				
				for(int i= 0;i<K;i++) {
					if(dt[i].alive) {
						int ny = dt[i].y + dy[dt[i].d];
						int nx = dt[i].x + dx[dt[i].d];
						dt[i].time = dt[i].time+1;
						if(ny < 1 || ny >=N-1 || nx<1 || nx>=N-1) {
							if(dt[i].size == 1) {
								dt[i].alive = false;
							}
							else {
								dt[i].size = (int)dt[i].size/2;
								if(dt[i].d == 1) dt[i].d = 2;
								else if(dt[i].d == 2) dt[i].d=1;
								else if(dt[i].d == 3) dt[i].d=4;
								else if(dt[i].d == 4) dt[i].d=3;
								dt[i].y= ny;
								dt[i].x =nx;
								map[dt[i].y][dt[i].x] = new Map(dt[i].size, dt[i].time, dt[i].d, i);
							}
						}else {
							dt[i].y= ny;
							dt[i].x =nx;
							if(map[dt[i].y][dt[i].x] == null) {
								map[dt[i].y][dt[i].x] = new Map(dt[i].size, dt[i].time,dt[i].d, i);
							}else if(map[dt[i].y][dt[i].x].time == dt[i].time && map[dt[i].y][dt[i].x].size > dt[i].size) {
								dt[i].alive = false;
								dt[map[dt[i].y][dt[i].x].idx].size = map[dt[i].y][dt[i].x].size + dt[i].size;
								map[dt[i].y][dt[i].x].size = map[dt[i].y][dt[i].x].size + dt[i].size;
							}else if(map[dt[i].y][dt[i].x].time == dt[i].time && map[dt[i].y][dt[i].x].size < dt[i].size) {
								dt[map[dt[i].y][dt[i].x].idx].alive = false;
								int size = map[dt[i].y][dt[i].x].size + dt[i].size;
								dt[i].size = size;
								map[dt[i].y][dt[i].x] = new Map(size,dt[i].time, dt[i].d, i);
							}else if(map[dt[i].y][dt[i].x].time < dt[i].time) {
								map[dt[i].y][dt[i].x] = new Map(dt[i].size,dt[i].time, dt[i].d, i);
							}
						}
					}
				}
			}
			int sum = 0;
			for(Data d : dt) {
				if(d.alive) sum+=d.size;
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

}
