import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int [] arr;
	static int K;
	static int [][] map;
	static StringTokenizer stk;
	public static void main(String [] args)throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk= new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[201][201];
		for(int i = 1;i<201;i++) {
			for(int j =1;j<201;j++) {
				map[i][j] = 987654321;
			}
		}
		for(int i =0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			map[s][e] = Integer.parseInt(stk.nextToken());
		}
		
		K = Integer.parseInt(bf.readLine());
		arr = new int[K];
		stk = new StringTokenizer(bf.readLine());
		for(int i=0;i<K;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		
		ArrayList<Integer> list =new ArrayList<>();
		for(int k= 1;k<201;k++) {
			for(int i =1;i<201;i++) {
				if(i == k) continue;
				for(int j = 1;j<201;j++) {
					if(j == k) continue;
					if(i == j) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		// 도시 X에 대한 친구들의 왕복비용들 중 최대가 되는 숫자가 있을것이고
		// 그 중 최소에 대한 도시 X를 구한다.
		// 도시 X에 대한 최소들이 같은게 여러개 있을 수 있다.
		
		int minValue = 987654321;
		for(int i = 1;i<N+1;i++) {
			int maxValue = -1;
			for(int j = 0;j<K;j++) {
				int fromTo = map[arr[j]][i];
				int toFrom = map[i][arr[j]];
				int sum = 0;
				if(fromTo!= 987654321 && toFrom != 987654321) {
					sum = fromTo + toFrom;
					if(sum > maxValue) maxValue = sum;
				}
			}
			if(maxValue != -1) {
				if(minValue > maxValue) {
					minValue = maxValue;
					list.clear();
					list.add(i);
				}else if(minValue == maxValue){
					list.add(i);
				}
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int t : list) sb.append(t).append(" ");
		System.out.print(sb);
	}
}