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
		// 캐릭터 별로 선택하고 보스를 이걸 잡냐 저걸 잡냐에 따라 달라짐
		// 보스에 선택은 잡을수도 있고 안잡을수도있으니 -> 부분집합
		// 각 캐릭터별로 15분가량의 시간이 주어져있음
		// 따라서 15분동안의 dp테이블을 채운 최종 최대이익들 중
		// 높은 순으로 m개 꺼내면 됨
		// dp 테이블은 현재 사용가능한 보스와 해당 보스를 잡는 시간대별, 그리고 원소값은 메소임
		// 결국 특정 보스의 체력을 뛰어넘는 데미지를 넣었을 경우 해당 보스에 대한 보상을 받게됨
		// 여기서 캐릭터는 자유롭지만 보스는 잡다 딴데 갔다가 돌아오는경우는 없으므로ㄴ
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
		}
		long sum = 0;
		while(m-- >0) {
			sum += pq.poll();
		}
		System.out.print(sum);
	
	}
}