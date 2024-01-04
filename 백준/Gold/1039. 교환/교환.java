import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int n;
	static int k;
	static char [] nums;
	static int [] v;
	static int len;
	static int max;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		String num = stk.nextToken();
		nums = num.toCharArray();
		len = nums.length;
		n = Integer.parseInt(num);k = Integer.parseInt(stk.nextToken());
		v = new int[10000000];
		// 각 인덱스에 존재하는 자리수가 특정위치에 온적 있다면 true
		max = Integer.MIN_VALUE;
		v[n] = 0;
		bfs();
		System.out.println(max == Integer.MIN_VALUE ? -1 : max);
	}
	static void bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int [] {n, 0});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(now[1] == k) {
				max = Math.max(max, now[0]);
				continue;
			}
			char [] nums = String.valueOf(now[0]).toCharArray();
			for(int i = 0;i<nums.length;i++) {
				for(int j = i+1;j<nums.length;j++) {
					if(i == j) continue;
					char [] p = swap(i, j, nums);
					if(p[0]-'0' == 0) continue;
					int a = makeNumber(p);
					
					if(v[a] == now[1]+1) continue;
					v[a] = now[1]+1; 
					queue.add(new int[] {a, now[1]+1});
				}
			}
			
		}
	}
	static char [] swap(int a, int b, char [] nums) {
		char [] temp = nums.clone();
		char t = temp[a];
		temp[a] = temp[b];
		temp[b] = t;
		return temp;
	}
	static int makeNumber(char [] nums) {
		int n = 0;
		for(int i = 0;i<nums.length;i++) {
			 n += ((nums[i]-'0')*Math.pow(10, nums.length-i-1));
		}
		return n;
	}
}