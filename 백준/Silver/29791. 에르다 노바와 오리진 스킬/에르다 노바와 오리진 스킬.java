import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer stk;
	static int N;
	static int M;
	static int [] nrr;
	static int [] mrr;
	static class Time{
		boolean pressA;
		boolean pressO;
		public Time(boolean pA, boolean pO) {
			this.pressA = pA;
			this.pressO = pO;
		}
	}
	static Time [] times;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		nrr = new int[N];
		mrr = new int[M];
		times = new Time[1000001];
		for(int i= 0;i<N;i++) {
			nrr[i] = Integer.parseInt(stk.nextToken());
			times[nrr[i]] = new Time(true, false);
		}
		stk = new StringTokenizer(bf.readLine());
		for(int i= 0;i<M;i++) {
			mrr[i] = Integer.parseInt(stk.nextToken());
			if(times[mrr[i]]!= null) {
				times[mrr[i]].pressO = true;
			}else {
				times[mrr[i]] =  new Time(false, true);
			}
			
		}
		// 에르다 노바 -> 몬스터에게 행동불가, 100초
		// 오리진 -> 절대 행동 불가, 360 초
		// 에르다 노바건 오리진이건 각각의 스킬에 90초동안 면역상태가 된다
		// 에르다노바와 오리진을 동시에 발동시킨다면 가각 몬스터에게 걸림
		// 각 스킬의 단축키를 눌렀을 때 재사용 대기시간이거나 몬스터가 면역상태라면 
		// 아무런 스킬이 적용되지 않는다.
		// 몬스터가 행동불가 와 절대행동 불가가 된 횟수를 구하자
		
		// 동시간대에 발사한건 각각 걸리게 되고
		// 몬스터의 상태와 시간대별 상태를 골라보자
		
		int curTime = 0;
		int cc = 0;
		int cc2 = 0;
		int cool1 = 0;
		int cool2 = 0;
		int mcool1 = 0;
		int mcool2 = 0;
		for(int i = 1;i<times.length;i++) {
			if(times[i]!=null) {
//				System.out.println(i +" "+times[i].pressA+" "+times[i].pressO);
				if(times[i].pressA) {
					if(cool1 <= i) {
						cool1 = (100+i);
						if(mcool1 <= i) {
							cc++;
							mcool1 = (90+i);
						}
					}
				}
				if(times[i].pressO) {
					if(cool2 <= i) {
						cool2 = (360+i);
						if(mcool2 <= i) {
							cc2++;
							mcool2 = (90+i);
						}
					}
				}
			}
		}
		System.out.println(cc +" "+cc2);
		
	}

}
