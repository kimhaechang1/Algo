import java.util.*;
import java.io.*;

public class Main{
	static int [][] arr;
	static int n;
	static int k;
	static int [] knap;
	static StringTokenizer stk;
	public static void main(String [] args ) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		arr = new int[n][2];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			arr[i][0] = m;
			arr[i][1] = v;
		}
		knap = new int[k];
		
		for(int i = 0;i<k;i++) {
			knap[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(knap);
		int maxCap = knap[k-1];
		PriorityQueue<int []> pq = new PriorityQueue<>((a,b)->{
			return b[1] - a[1];
		});
		Arrays.sort(arr, (a,b)->{
			if(a[0] == b[0]) {
				return b[1] - a[1];
			}
			return a[0] - b[0];
		});
		
		long max = 0;
		int idx = 0;
		for(int i = 0; i<k;i++) {
			while(idx < n && arr[idx][0] <= knap[i]) {
				pq.add(new int[] {arr[idx][0], arr[idx][1]});
				idx++;
			}
			if(!pq.isEmpty()) {
				max += pq.poll()[1];
			}
			
		}		
		System.out.println(max);
		
	}
}