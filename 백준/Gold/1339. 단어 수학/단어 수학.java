import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int [] res;
	static boolean [] v;
	static String [] str;
	static int max;
	static int dMax;
	static int [] numberToInt;
	static HashMap<Character, Integer> map;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		str = new String[n];
		v = new boolean[26];
		for(int i= 0;i<n;i++) {
			str[i] = bf.readLine();
			char [] ch = str[i].toCharArray();
			for(char t : ch) v[t-'A'] = true;
		}
		for(int i = 0;i<26;i++) {
			if(v[i]) dMax++;
		}
		Arrays.sort(str, (a, b)->{
			return b.length() - a.length();
		});
		
		int idx = 0;
		PriorityQueue<int []> pq = new PriorityQueue<>((a,b)->{
			return b[1] - a[1];
		});
		res = new int[26];
		for(int i = 0;i<n;i++) {
			char [] t = str[i].toCharArray();
			for(int j = 0;j<t.length;j++) {
				res[t[j]-'A'] += Math.pow(10, t.length-1-j);
			}
		}
		for(int i = 0;i<26;i++) {
			if(res[i] == 0) continue;
			pq.add(new int[] {i, res[i]});
		}
		
		numberToInt = new int[26];
		int pres = 9;
		while(!pq.isEmpty()) {
			int [] now = pq.poll();
			numberToInt[now[0]] = pres;
			pres--;
		}
		max = 0;
		for(String s : str) {
			char [] alpas = s.toCharArray();
			int len = alpas.length;
			int number = 0;
			for(int j = 0;j<len;j++) {
				number += (numberToInt[alpas[j]-'A'] * Math.pow(10, len-j-1));
			}
			max += number;
		}
		System.out.print(max);
		
		// 9 8 7 6 5 4 3 2 1 0      
		// 98654
		//   783
		// 자릿수를 생각한다.
		// 같은 자리수라면 거꾸로 배치하면 되고
		// 두 알파벳 중 길이가 더 긴것부터 큰 숫자를 배정하면 된다.
		// 그렇게 하면 다음과 같은 반례가 발생한다.
		//  5
//			AB
//			BC
//			CD
//			DE
//			EF
		// 따라서 기댓값의 최소한을 기준으로 정렬하여 9부터 0까지 배정한다.
	}
}