import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [] arr;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		double w= 1;
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(arr);
		
		int idx = 0;	
		
		// 파라메트릭 서치인줄 알았으나
		// 그냥 그리디 문제인듯
		// 특정 로프를 시작점으로 두고 해당 로프번호에서 끝까지 사용했다고 했을 때
		// 로프에 가해지는 최소한의 하중(w/현재 선택가능한 최대 로프개수)보다 로프중 가장 작은 값이 작다면 
		// 그것은 무게를 더 늘려봐도 되는것이다.
		// 왜냐하면 해당 시작점 로프를 못쓰는 순간에 도달하지 못했기 때문이다.
		// 그 반대의 경우에는 해당 시작점에 대해서 더 고려할 필요가 없기 때문에 다음 시작점(idx)으로 포인터를 옮긴다.
		while(idx < N) {
			if((double)(w/(N-idx)) <= arr[idx]) {
				w++;
			}else {
				idx++;
			}
		}
			
			
		System.out.println((int)w-1);
		 
	}
	
}