import java.util.*;
import java.io.*;

public class Main{
	
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for(int i = 0;i<n;i++) {
			int value = Integer.parseInt(bf.readLine());
			if(left.size() == right.size()) left.add(value);
			else right.add(value);
			
			if(left.size()!=0 && right.size()!=0) {
				if(right.peek() < left.peek()) {
					int l = left.poll();
					int r = right.poll();
					left.add(r);
					right.add(l);
				}
			}
			sb.append(left.peek()).append("\n");
			
		}
		System.out.print(sb);
	}
}