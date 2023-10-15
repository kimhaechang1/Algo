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
		while(idx < N) {
			/*System.out.println("w : "+w);
			System.out.println((double)(w/(N-idx)));
			System.out.println(idx +" arr[idx] : "+arr[idx]);
			System.out.println("-----------");*/
			if((double)(w/(N-idx)) <= arr[idx]) {
				w++;
			}else {
				idx++;
			}
		}
			
			
		System.out.println((int)w-1);
		 
	}
	
}