import java.util.*;
import java.io.*;

public class Main{
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> treeSet = new TreeSet<>();
		int n = Integer.parseInt(bf.readLine());
		long c = 0;
		int [] h = new int[300001];
		StringBuilder sb = new StringBuilder();
		int val = Integer.parseInt(bf.readLine());
		treeSet.add(val);
		h[val] = 0;
		sb.append(h[val]).append("\n");
		
		for(int i = 1;i<n;i++) {
			val = Integer.parseInt(bf.readLine());
			treeSet.add(val);
			Integer lower = treeSet.lower(val);
			Integer higher = treeSet.higher(val);
			if(lower == null && higher != null) {
				h[val] = h[higher]+1;
				c +=  h[val];
				sb.append(c).append("\n");
			}else if(higher == null && lower != null) {
				h[val] = h[lower]+1;
				c +=  h[val];
				sb.append(c).append("\n");
			}else {
				
				if(h[higher] < h[lower]) {
					h[val] = h[lower]+1;
					c +=  h[val];
					sb.append(c).append("\n");
				}else {
					h[val] = h[higher]+1;
					
					c +=  h[val];
					sb.append(c).append("\n");
				}
			}
		}
		
		System.out.print(sb);
	}
}