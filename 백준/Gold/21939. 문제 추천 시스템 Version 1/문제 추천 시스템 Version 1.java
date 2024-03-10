import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static StringTokenizer stk;
	static class Data implements Comparable<Data>{
		int p;
		int l;
		public Data(int p, int l) {
			this.p = p;
			this.l = l;
		}
		public int compareTo(Data o) {
			if(this.l == o.l) {
				return o.p - this.p;
			}
			return o.l - this.l;
		}
	}
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Data> tset = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		N = Integer.parseInt(bf.readLine());
		for(int i = 0;i<N;i++) {
			stk =new StringTokenizer(bf.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int l = Integer.parseInt(stk.nextToken());
			tset.add(new Data(p, l));
			map.put(p, l);
		}
		
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(bf.readLine());
		while(M-- > 0) {
			stk = new StringTokenizer(bf.readLine());
			String cmd = stk.nextToken();
			int p;
			int l;
			int res;
			switch(cmd) {
				case "add":
					p = Integer.parseInt(stk.nextToken());
					l = Integer.parseInt(stk.nextToken());
					tset.add(new Data(p, l));
					map.put(p, l);
					break;
				case "recommend":
					int opt =Integer.parseInt(stk.nextToken());
					Data info;
					
					if(opt == 1) {
						info = tset.first();
					}else {
						info = tset.last();
					}
					sb.append(info.p).append("\n");
					break;
				case "solved":
					p = Integer.parseInt(stk.nextToken());
					Data rm = tset.ceiling(new Data(p, map.get(p)));
					tset.remove(rm);
					break;
			}
		}
		System.out.print(sb);
		
	}
}