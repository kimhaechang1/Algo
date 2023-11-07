import java.util.*;
import java.io.*;


public class Main{
	static int N;
	static int W;
	static int L;
	static int [] trucks;
	static int [] wait;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		W = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		wait = new int[W];
		trucks = new int[N];
		stk =new StringTokenizer(bf.readLine());
		for(int i= 0;i<N;i++) {
			trucks[i] = Integer.parseInt(stk.nextToken());
		}
		int out = 0;
		int sum = 0; // 다리위 트럭의 누적합
		int t= 0;
        while(out != N){
        	t++;
            // 다리위 움직임, 0번째 차량은 항상 빠져나감
            if(wait[0] != 0){
                sum -= wait[0];    
                wait[0] = 0;
                out++;
            }
            
            for(int i = 0; i<wait.length-1;i++){
                wait[i] = wait[i+1];
            }
            // 마지막 차도 땡겨지므로 항상 비게됨
            wait[wait.length-1] = 0;
            // 대기 트럭에서 넣을 수 있는지 누적합 값을 통해 검사
            if(sum+trucks[0] <= L){
                // 대기트럭에서 다리를 건너는 배열로 이동
                wait[wait.length-1] = trucks[0];
                sum += wait[wait.length-1];
                trucks[0] = 0;
                // 대기트럭 라인도 땡겨짐
                for(int i = 0;i<N-1;i++){
                	trucks[i] = trucks[i+1];
                }
            }
        }
        System.out.print(t);
		
	}
}