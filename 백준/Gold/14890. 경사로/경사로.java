import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int L;
	static int [][]map;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		map = new int[N][N];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 세로줄에 대해서 검사
		// 가로줄에 대해서 검사
		// 높이 차이가 1이 나는 곳을 만났다? 경사로 설치 가능여부 검사
		boolean [] v;
		int c =0;
		for(int i= 0;i<N;i++) {
			v = new boolean[N];
			int cnt= 1;
			int j = 1;
			t:for(j = 1;j<N;j++) {
				int cha = map[i][j] - map[i][j-1];
				if(cha == -1) {
					if(j+L <= N) {
						for(int k= j; k<j+L;k++) {
							if(v[k]) {
								break t;
							}else {
								v[k] = true;
							}
							
						}
					}else {
						break;
					}
					cnt = 1;
				}else if(cha == 1){
					if(cnt >= L) {
						for(int k= j-L; k<j;k++) {
							if(v[k]) break t;
							v[k] = true;
						}
					}else {
						break;
					}
					cnt = 1;
				}else if(cha == 0){
					if(v[j]) { 
						cnt = 1;
					}
					else cnt++;
				}else {
					break;
				}
			}
			if(j == N) {
				c++;
			}
		}
		
		for(int i= 0;i<N;i++) {
			v = new boolean[N];
			int cnt= 1;
			int j = 1;
			t:for(j = 1;j<N;j++) {
				int cha = map[j][i] - map[j-1][i];
				if(cha == -1) {
					if(j+L <= N) {
						for(int k= j; k<j+L;k++) {
							if(v[k]) {
								break t;
							}else {
								v[k] = true;
							}
						}
					}else {
						break;
					}
					cnt = 1;
				}else if(cha == 1){
					if(cnt >= L) {
						for(int k= j-L; k<j;k++) {
							if(v[k]) break t;
							v[k] = true;
						}
					}else {
						break;
					}
					cnt = 1;
				}else if(cha == 0){
					if(v[j]) { 
						cnt = 1;
					}
					else cnt++;
				}else {
					break;
				}
			}
			if(j == N) {
				c++;
			}
		}
		System.out.println(c);
	}
}