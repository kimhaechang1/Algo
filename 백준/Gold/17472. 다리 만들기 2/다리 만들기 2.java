import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static StringTokenizer stk;
	static int N;
	static int M;
	static class Edge{
		int f;
		int t;
		int w;
		public Edge(int f, int t, int w) {
			this.f = f;
			this.t = t;
			this.w = w;
		}
	}
	static int cnt;
	static ArrayList<Edge> list;
	static int[] p;
	static void makeSet() {
		p = new int[cnt-2];
		for(int i= 1;i<p.length;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	
	static Map<Integer, ArrayList<int[]>> oc;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}				
			
		}
		// 다리길이는 2 이상이어야 한다., 한 방향으로 쭉 이어저야 한다.(y 축 or x 축)
		// 다리의 양끝이 연결되어있는 두 섬이 연결되어있다고 생각하는 것
		// 다리 옆에 섬이 있다고해서 해당 섬과 이어져있다고 할 ㅅ수 없다.
		oc = new HashMap<>();
		
		cnt = 3; // offset = 2
		for(int i = 0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1) {
					mark(i,j,cnt++);
				}
			}
		}
		list = new ArrayList<>(); // 0, 0 사용 안함
		
		// 최소 스패닝 트리가 아닐까 싶은데
		// 간선을 어떻게 구하지
		for(int i = 1;i<cnt-2;i++) {
			for(int j = 1;j<cnt-2;j++){
				if(i == j) continue;
				int gijunidx = i;
				int targetidx = j;
				// x 축으로 비교, y축으로 비교
				// 차가 1이면 간선비용으로 추가하지않는다.
				ArrayList<int[]> glist = oc.get(i);
				ArrayList<int[]> tlist = oc.get(j);
				//System.out.println(glist.size());
				//System.out.println(tlist.size());
				for(int g = 0;g<glist.size();g++) {
					for(int t = 0;t<tlist.size();t++) {
						int[] gyx = glist.get(g);
						int[] tyx = tlist.get(t);
						if(gyx[0] == tyx[0]) {
							// x 좌표 차
							
								if(gyx[1] > tyx[1]) {
									int gx = gyx[1]+dx[3];
									int tx = tyx[1]+dx[2];
									if(gx >= M || tx < 0) continue;
									if(map[gyx[0]][gx] == i+2 && map[tyx[0]][tx] == j+2) {
										if(gx - tx-1 == 1) continue;
										if(!go(gyx[0],gx, tyx[0],tx, 2, map[tyx[0]][tx]))continue;
										list.add(new Edge(gijunidx, targetidx, Math.abs(gx-tx)-1));
										list.add(new Edge(targetidx, gijunidx, Math.abs(gx-tx)-1));
									}
								}else {
									int gx = gyx[1]+dx[2];
									int tx = tyx[1]+dx[3];
									if(tx >= M || gx < 0) continue;
									if(map[gyx[0]][gx] == i+2 && map[tyx[0]][tx] == j+2) {
										if(tx - gx-1 == 1) continue;
										if(!go(tyx[0],tx,gyx[0],gx, 2, map[gyx[0]][gx])) continue;
										list.add(new Edge(gijunidx, targetidx, Math.abs(gx-tx)-1));
										list.add(new Edge(targetidx, gijunidx, Math.abs(gx-tx)-1));
									}
								}
						}else if(gyx[1] == tyx[1]) {
								if(gyx[0] > tyx[0]) {
									// gy 쪽이  더 아래에 있다.
									int gy = gyx[0]+dy[1];
									int ty = tyx[0]+dy[0];
									if(gy >= N || ty < 0) continue;
									if(map[gy][gyx[1]] == i+2 && map[ty][tyx[1]] == j+2) {
										if(gy - ty-1 == 1) continue;
										if(!go(gy,gyx[1],ty,tyx[1], 0,  map[ty][tyx[1]])) continue;;
										list.add(new Edge(gijunidx, targetidx, Math.abs(gy-ty)-1));
										list.add(new Edge(targetidx, gijunidx, Math.abs(gy-ty)-1));
									}
								}else {
									// ty 쪽이 더 아래에 있다.
									int gy = gyx[0]+dy[0];
									int ty = tyx[0]+dy[1];
									if(ty >= N || gy < 0) continue;
									if(map[gy][gyx[1]] == i+2 && map[ty][tyx[1]] == j+2) {
										if(ty - gy-1 == 1) continue;
										if(!go(ty,tyx[1],gy,gyx[1], 0, map[gy][gyx[1]])) continue;
										list.add(new Edge(gijunidx, targetidx, Math.abs(gy-ty)-1));
										list.add(new Edge(targetidx, gijunidx, Math.abs(gy-ty)-1));
									}
								}
						}
					}
				}
			}
		}
		makeSet();
		Collections.sort(list, (o1, o2)->{
			return o1.w - o2.w;
		});
		
		int res = 0;
		int c = 0;
		
		for(Edge edge : list) {
			if(union(edge.f, edge.t)) {
				res+= edge.w;
				if(++c == cnt-4) {
					break;
				}
			}
		}
		if(c != cnt-4) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	}
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static void mark(int y, int x, int mark) {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {y,x});
		map[y][x] = mark;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= N || ny < 0 || nx >= M || nx < 0)continue;
				if(map[ny][nx] == 0) {
					if(oc.containsKey(mark-2)) {
						if(oc.get(mark-2) == null) {
							oc.put(mark-2,new ArrayList<>());
							oc.get(mark-2).add(new int[] {ny, nx});
						}else {
							ArrayList<int []> list = oc.get(mark-2);
							list.add(new int [] {ny, nx});
							oc.put(mark-2, list);
						}
					}else {
						oc.put(mark-2,new ArrayList<>());
						oc.get(mark-2).add(new int[] {ny, nx});
					}
					
				}else if(map[ny][nx] == 1) {
					map[ny][nx] = mark;
					queue.add(new int[] {ny,nx});
				}
			}
		}
	}
	static boolean go(int sy, int sx, int ty, int tx, int d, int targetNumber) {
		while(true) {
			sy+=dy[d];
			sx+=dx[d];
			if(sy >=N || sy < 0 || sx >= M || sx < 0) return false;
			if(sy == ty && sx == tx) break;
			else if(map[sy][sx] > 0 && (sy != ty || sx != tx)) return false;
		}
		return true;
	}
}
