import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static StringTokenizer stk;
	static String [][] datas;
	static String [] candi;
	static HashMap<String, Double> blood;
	static HashMap<String, ArrayList<String>> g;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		String dKing = bf.readLine();
		datas = new String[n][3];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<3;j++) {
				datas[i][j] = stk.nextToken();
			}
		}
		blood = new HashMap<>();
		candi = new String[m];
		for(int i = 0;i<m;i++) {
			candi[i] = bf.readLine();
			blood.put(candi[i], 0.0);
		}
		
		// 나라를 세운사람과 가장 가까운사람이 후보
		// 즉, 왕족의 피가 가장 많이 섞인 자식
		// 모든 객체는 어머니, 아버지 반씩 받음
		// 나라를 세운 놈의 자식은 반은 왕족이 됨, 해당 자식이 왕족이 아닌사람과 부모가 된 경우 그 자식은 1/4 왕족 피
		// 결국 부모에서부터 갈수있는 자식에 대해 단방향 간선을 그리고
		// 타고 내려가면서 몇번째 인지 체크하면 됨
		g = new HashMap<>();
		
		for(int i = 0;i<n;i++) {
			String [] info = datas[i];
			ArrayList<String> p1 = g.get(info[1]);
			ArrayList<String> p2 = g.get(info[2]);
			if(p1 == null) {
				p1 = new ArrayList<>();
			}
			if(p2 == null) {
				p2 = new ArrayList<>();
			}
			p1.add(info[0]);
			p2.add(info[0]);
			g.put(info[1], p1);
			g.put(info[2], p2);
		}
		bfs(dKing);
		double max = 0;
		String kingsChild = "";
		for(String c : candi) {
			if(blood.containsKey(c)) {
				double val = blood.get(c);
				if(val > max) {
					max = val;
					kingsChild = c;
				}
			}
		}
		System.out.print(kingsChild);
	}
	static void bfs(String start) {
		Queue<String> queue = new ArrayDeque<>();
		queue.add(start);
		double cnt = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			cnt /=2;
			for(int i = 0;i<size;i++) {
				String now = queue.poll();
				if(!g.containsKey(now)) continue;
				for(String child : g.get(now)) {
					for(int j = 0;j<m;j++) {
						if(child.equals(candi[j])) {
							double bl = blood.get(candi[j]);
							bl += cnt;
							blood.put(candi[j], bl);
						}
					}
					queue.add(child);
				}
			}
		}
	}
	
}