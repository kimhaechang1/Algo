import java.util.*;
import java.io.*;


public class Main{
	public static void main(String [] args) throws Exception{
		// 수직선 문제
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// G = x^2 - y^2
		// x : 현재 몸무게
		// y : 기억하고 있엇던 몸무게
		// x > y (x!=0 && y!=0)
		// 64 - 49 = 15
		// x - y > 0
		// 4 1 -> 16 - 1 = 15
		// 8 7 -> 64 - 49 = 15
		// 100000 -> 
		long G = Integer.parseInt(bf.readLine());
		int s = 1;
		int e = 2;
		ArrayList<Integer> list = new ArrayList<>();
		
		while(s < e) {
			long S = (long)(Math.pow(e, 2) - Math.pow(s, 2));
			if(S > G) {
				s++;
			}else if(S == G) {
				list.add(e);
				s++;
			}else {
				e++;
			}
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		if(list.size() == 0) {
			sb.append(-1);
		}else {
			for(int a : list) sb.append(a).append("\n");
		}

		System.out.print(sb);
	}
}