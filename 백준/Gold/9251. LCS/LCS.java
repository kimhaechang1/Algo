import java.util.*;
import java.io.*;

public class Main{
	public static void main(String [] args)throws Exception{
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		char [] A =bf.readLine().toCharArray();
		char [] B = bf.readLine().toCharArray();
		int [][] dp = new int[A.length+1][B.length+1];
		// 두 문자가 매칭되는 경우
		// 각 문자의 이전 문자까지의 LCS값을 들고와서 지금문자를 더하여 길이를 추가하면됨
		// 매칭되지 않는 경우, A[i]가 매칭되고 B[j]는 매칭되지 않는경우와 A[i]는 매칭되지 않고 B[j]는 매칭되는 경우 중 더 큰 LCS를 가지는 쪽을 선택하면 된다.
		
		for(int i = 1;i<A.length+1;i++) {
			for(int j = 1;j<B.length+1;j++) {
				if(A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.print(dp[A.length][B.length]);
	}
}