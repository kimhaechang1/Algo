import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static ArrayList<Integer> primes;
	static boolean [] isPrime;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		/*
		 * 수직선 상의 수 -> BFS, 누적합, 투포인터, 슬라이딩 윈도우
		 * 400만까지의 소수들 리스트를 미리 만들어놓고
		 * 투포인터로 양쪽끝 포인터를 두고 교차하는 순간 리턴시키면 가능할듯
		 * */
		isPrime = new boolean[4000001];
		isPrime[0] = true;
		isPrime[1] = true;
		// true : 소수가 아님, false : 소수
		// 에라토스테네스의 체
		for(int i= 2;i<((int)Math.sqrt(4000000))+1;i++) {
			if(!isPrime[i]) {
				for(int j= i+i ;j<4000001;j+=i) {
					if(!isPrime[j]) isPrime[j] = true;
				}
			}
		}
		primes = new ArrayList<>();
		for(int i= 1;i<4000001;i++) {
			if(isPrime[i]) continue;
			primes.add(i);
		}
		int len = primes.size();
		int s = 0;
		int e = 0;
		int cnt = 0;
//		if(!isPrime[N]) cnt++;
		
//		System.out.println(cnt);
		//
		/*
		 * 2 3  5  7  11  13  17  19  23  31  37  41  43 
		 * 2 5 10 17  28  41  58  77  100 131 168 209 252
		 * 
		 * */
		
		long st = primes.get(s);
		while(s < len) {
			if(N < st) {
				st -= primes.get(s);
				s++;
				if(s >= len) break;
			}else if(N > st) {
				e++;
                if(e >=len) break;
                st+=primes.get(e);
			}else {
                cnt++;
				st -= primes.get(s);
				s++;
			}
		}
		
		System.out.println(cnt);
	}
}