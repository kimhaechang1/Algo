import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] b;

	public static void main(String[] args) throws Exception {
		// 만약에 빌딩이 자기보다 높은 빌딩들 사이에 있는경우 해당 빌딩의 관리자는 아무것도 볼 수 없다.
		// 일단 원소를 하나 스택에 넣어두고 다음 원소부터 검증하는데
		// 검증 할 때, 스택에 들어올 원소가 현재스택에 피크값 보다 크다면, 스택에서 팝한다. 왜냐하면 스택에 들어있는 순서는 내림차순이어야 하므로
		// 내림차순이어야 하는 이유는 내림차순으로 두면 피크값보다 작은 원소가 왔을 때 스택에 모든 원소들이 해당 작은원소보다 큰것들 뿐이므로 스택사이즈를 결과값에 더하면 된다.
		// 피크값이 더 크다면 해당 원소는 현재 스택에 있는 모든 빌딩들이 볼 수 있으므로 스택 사이즈만큼을 결과값에 더해야 한다.
		// 만약에 전부 팝해서 남은게 없다면 그냥 원소를 넣는다.
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		Stack<Integer> stack =new Stack<>();
		b = new int[N];
		for(int i = 0;i<N;i++) {
			b[i] = Integer.parseInt(bf.readLine());
		}
		long sum = 0;
		
		for(int i= 0;i<N;i++) {
			if(stack.isEmpty()) {
				stack.push(b[i]);
			}else {
				while(true) {
					if(stack.isEmpty()) {
						stack.push(b[i]);
						break;
					}
					if(stack.peek() > b[i]) {
						sum+=stack.size();
						stack.push(b[i]);
						break;
					}else {
						stack.pop();
					}
				}
			}
		}
		System.out.println(sum);		
	}
}