import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static int k;
	static long [] dmg;
	static long [][] boss;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		dmg = new long[n+1];
		for(int i = 1;i<n+1;i++) {
			dmg[i] = Long.parseLong(bf.readLine());
		}
		boss = new long[k+1][2];
		for(int i = 1;i<k+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			boss[i][0] = Long.parseLong(stk.nextToken());
			boss[i][1] = Long.parseLong(stk.nextToken());
		}
		PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->{
			return Long.compare(b, a);
		});
		for(int i = 1;i<n+1;i++) {
			long dg = dmg[i];
			long [][] dp = new long[k+1][901];
			for(int j = 1;j<k+1;j++) {
				for(int t = 1;t<901;t++) {
					dp[j][t] = dp[j-1][t];
					if(boss[j][0]  <= t*dg) {
						long time = boss[j][0]/dg;
						if(boss[j][0] % dg != 0) time++;
						dp[j][t] = Math.max(dp[j][t], dp[j-1][t -(int)time]+boss[j][1]);
					}
				}
			}
			pq.add(dp[k][900]);
			/*System.out.println(i + "번째 캐릭터의 보스돌이 dp");
			for(int j = 1;j<k+1;j++) {
				System.out.println(Arrays.toString(dp[j]));
			}
			System.out.println("========================");*/
		}
		long sum = 0;
		while(m-- >0) {
			sum += pq.poll();
		}
		System.out.print(sum);
	
	}
}