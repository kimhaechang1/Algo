
/*

12:45 ~ 12:56
 * */
import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			int n = Integer.parseInt(bf.readLine());
			sb.append("Case #").append(t).append(": ").append(testCase(n)).append("\n");
		}
		System.out.print(sb);
	}
	static String testCase(int n) {
		if (n == 0) return "INSOMNIA";
		boolean[] visit = new boolean[10];
		int sum = n;
		insert(sum, visit);
		while(keepGoing(visit)) {
			sum += n;
			insert(sum, visit);
		}
		return String.valueOf(sum);
	}
	static boolean keepGoing(boolean[] v) {
		for(boolean b: v) if (!b) return true;
		return false;
	}
	static void insert(int number, boolean[] v) {
		for (char f: String.valueOf(number).toCharArray()) v[f - '0'] = true;
	}
	
}
