import java.util.*;
import java.io.*;

public class Solution{
	static int M;
	static int A;
	static class BC{
		@Override
		public String toString() {
			return "BC [y=" + y + ", x=" + x + ", c=" + c + ", p=" + p + "]";
		}
		int y;
		int x;
		int c;
		int p;
		public BC(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	}
	static BC [] bcs;
	static int [][] arr;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t= 1;t<=T;t++) {
			int res = 0;
			stk = new StringTokenizer(bf.readLine());
			M = Integer.parseInt(stk.nextToken());
			A = Integer.parseInt(stk.nextToken());
			arr = new int[M+1][2];
			stk = new StringTokenizer(bf.readLine());
			for(int i = 1;i<=M;i++) {
				arr[i][0] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(bf.readLine());
			for(int i = 1;i<=M;i++) {
				arr[i][1] = Integer.parseInt(stk.nextToken());
			}
			
			bcs = new BC[A];
			for(int i = 0;i<A;i++) {
				stk = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(stk.nextToken());
				int y = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
				int p = Integer.parseInt(stk.nextToken());
				bcs[i] = new BC(y, x, c, p);
			}
			int sy = 1;
			int sx = 1;
			int py = 10;
			int px = 10;
			int [] dy = {0,-1,0,1,0};
			int [] dx = {0,0,1,0,-1};
			Arrays.sort(bcs, (o1, o2)->{
				return o2.p - o1.p;
			});
			for(int i = 0;i<M+1;i++) {
				int ds = arr[i][0];
				int dp = arr[i][1];
				sy += dy[ds];
				sx += dx[ds];
				py += dy[dp];
				px += dx[dp];
				ArrayList<BC> slist = new ArrayList<>();
				ArrayList<BC> plist = new ArrayList<>();
				for(BC temp : bcs) {
//					System.out.println(temp);
					int tc = Math.abs(temp.y - sy) + Math.abs(temp.x- sx);
					if(tc <= temp.c) {
						slist.add(temp);
					}
					tc = Math.abs(temp.y - py) + Math.abs(temp.x- px);
//					System.out.println("위치 py : "+py +" px : "+px);
//					System.out.println("p위치와의 차 : "+tc);
					if(tc <= temp.c) {
						plist.add(temp);
					}
				}
//				System.out.println("sy : "+sy +" sx : "+sx + " list size : "+slist.size());
//				System.out.println("py : "+py +" px : "+px + " list size : "+plist.size());
				if(slist.size() > 0 || plist.size() > 0) {
					if(slist.size() > 0 && plist.size()> 0) {
						
						if(slist.get(0) == plist.get(0)) {
							int max = slist.get(0).p;
							// 한쪽에서 양보하는데 있어서 한쪽만 2개인 경우엔 깔끔하게 양보하지만
							// 둘다 2개 이상인 경우 두번째놈들끼리 비교해야한다.
							if(slist.size() >= 2) {
								max = Math.max(max, slist.get(0).p + slist.get(1).p);
							}
							if(plist.size() >= 2) {
								max = Math.max(max, plist.get(0).p + plist.get(1).p);
							}
							res += max;
						}else {
							res += (slist.get(0).p+plist.get(0).p);
						}
					}
					else if(slist.size() > 0 && plist.size() == 0) {
						res += slist.get(0).p;
					}else if(plist.size() > 0 && slist.size() == 0) {
						res += plist.get(0).p;
					}
				}
//				System.out.println("시간 : "+i +" res : "+res);
			}
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.print(sb);
	}
}