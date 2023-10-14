import java.util.*;
import java.io.*;

public class Main{
	// MST 문제, 비용은 모두 양수
	// 먼저 모든 행성들 사이의 간선을 구해야 하고
	static int N;
	static Node [] arr;
	static StringTokenizer stk;
	static int [] cost;
	static boolean [] v;
	static int [] parent;
	
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
	static ArrayList<Edge> edgeList;
	static class Node{
		int idx;
		int x;
		int y;
		int z;
		public Node(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static void makeSet() {
		parent = new int[N];
		for(int i= 0;i<N;i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parent[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new Node [N];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y= Integer.parseInt(stk.nextToken());
			int z = Integer.parseInt(stk.nextToken());
			arr[i] =  new Node(i, x, y, z);
		}
		makeSet();
		
		// 결국 각 행성이 다음으로 선택할 행성을 선택하는 기준은 x좌표 혹은 y좌표 혹은 z좌표중에 가장 가까운 행성을 찾아가야 한다.
		// 그러면 행성 1~N에 대한 x좌표 따로 y좌표 따로 z좌표 따로 리스트를 만들고 오름차순 정렬을 한다고 하면
		// 절대 같은 위치에 중복되는 행성은 없다고 했으니 행성들간의 최소차는 자기 바로 앞 행성이 된다.
		// 따라서 간선의 비용을 구하는데 걸리는 시간복잡도는 300000이 되고 크루스칼로 MST를 구성하면 정답
		
		// x좌표 기준 오름차순 정렬 후 각 정점별 최단 거리를 구할 수 있다.
		// 간선의 개수를 구하기 귀찮다면 그냥 리스트를 쓰자
		edgeList = new ArrayList<>();
		Arrays.sort(arr, (o1,o2)->{
			return o1.x - o2.x;
		});
		for(int i= 1;i<N;i++) {
			edgeList.add(new Edge(arr[i-1].idx, arr[i].idx, Math.abs(arr[i-1].x- arr[i].x)));
		}
		
		Arrays.sort(arr, (o1,o2)->{
			return o1.y - o2.y;
		});
		for(int i= 1;i<N;i++) {
			edgeList.add(new Edge(arr[i-1].idx, arr[i].idx, Math.abs(arr[i-1].y- arr[i].y)));
		}
		
		Arrays.sort(arr, (o1,o2)->{
			return o1.z - o2.z;
		});
		for(int i= 1;i<N;i++) {
			edgeList.add(new Edge(arr[i-1].idx, arr[i].idx, Math.abs(arr[i-1].z- arr[i].z)));
		}
		
		Collections.sort(edgeList, (o1, o2)->{
			return o1.w - o2.w;
		});
		
		int res= 0;
		int cnt = 0;
		for(Edge edge : edgeList) {
			if(union(edge.f, edge.t)) {
				res+=edge.w;
				if(++cnt == N-1) break;
			}
		}
		System.out.println(res);
	}
}