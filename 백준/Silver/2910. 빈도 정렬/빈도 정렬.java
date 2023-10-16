import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int C;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		ArrayList<Integer> list =new ArrayList<>();
		for(int i = 0 ;i<N;i++) {
			list.add(Integer.parseInt(stk.nextToken()));
		}
		HashMap<Integer, int []> map = new HashMap<>();
		for(int i = 0;i<list.size();i++) {
			int key = list.get(i);
			if(map.containsKey(key)) {
				int [] d = map.get(key);
				++d[0];
				map.put(key, d);
				
			}else {
				map.put(key, new int[] {0, i});
			}
		}
		Collections.sort(list, (o1, o2)->{
			int [] val1 = map.get(o1);
			int [] val2 = map.get(o2);
			if(val1[0] == val2[0]) {
				return val1[1] - val2[1];
			}
			return val2[0] - val1[0];
		});
		StringBuilder sb = new StringBuilder();
		for(int a : list) {
			sb.append(a).append(" ");
		}
		System.out.print(sb);
		
	}
}