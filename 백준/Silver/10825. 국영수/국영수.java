import java.util.*;
import java.io.*;

public class Main{
	static int [][] scores;
	static String [] names;
	static int N;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		scores = new int[N][3];
		names = new String[N];
		HashMap<String, int []> map = new HashMap<>();
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			names[i] = stk.nextToken();
			scores[i][0] = Integer.parseInt(stk.nextToken());
			scores[i][1] = Integer.parseInt(stk.nextToken());
			scores[i][2] = Integer.parseInt(stk.nextToken());
		}
		for(int i = 0;i<N;i++) {
			map.put(names[i], scores[i]);
		}
		List<String> list = new ArrayList<>(Arrays.asList(names));
		Collections.sort(list, (o1, o2)->{
			int [] score1 = map.get(o1);
			int [] score2 = map.get(o2);
			if(score1[0] == score2[0]) {
				if(score1[1] == score2[1]) {
					if(score1[2] == score2[2]) {
						return o1.compareTo(o2);
					}else {
						return score2[2] - score1[2];
					}
				}else {
					return score1[1] - score2[1];
				}
			}else {
				return score2[0] - score1[0];
			}
		});
		StringBuilder sb =new StringBuilder();
		for(String s : list) {
			sb.append(s).append("\n");
		}
		System.out.print(sb);
	}
}