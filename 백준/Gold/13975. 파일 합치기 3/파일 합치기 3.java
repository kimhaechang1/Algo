import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static long [] arr;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb =new StringBuilder();
		for(int t= 0;t<T;t++) {
			int k = Integer.parseInt(bf.readLine());
			arr = new long[k];
			stk = new StringTokenizer(bf.readLine());
			PriorityQueue<Long> pq =new PriorityQueue<>();
			for(int i= 0;i<k;i++) {
				arr[i] = Integer.parseInt(stk.nextToken());
				pq.add(arr[i]);
			}
			long cost = 0;
			while(!pq.isEmpty()) {
				// 두 가지 경우가 있다. 하나는 두개를 뽑아서 합치던가
				// 아니면 만들어져 있는 하나를 가지고 생 파일 하나랑 합치던가
				if(pq.size() == 1) {
					break;
				}
				long f1= pq.poll();
				long f2= pq.poll();
				pq.add(f1 + f2);
				cost += (f1 + f2);
			}
			sb.append(cost).append("\n");
		}
		System.out.print(sb);
	}
}