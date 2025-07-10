import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[] arr;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	// 사람들의 키는 1 ~ N 이며 자신보다 큰사람이 왼쪽에 몇명있엇는지만 기억한다.
    	
    	// 자신보다 왼쪽에 큰사람이 전부 없는경우에는
    	// 0 0 0 0 0 ... 이런식으로 출력될 것이다.
    
    	// 그리고 맨 마지막숫자인 N 은 어디에 위치하더라도 항상 0이다.
    	
    	// 항상 결정되는것은 1의 위치다.
    	// 왜냐하면 나머지 숫자들은 모두 1보다 크기 때문에 1앞에 몇개의 숫자가 있엇느냐에 따라 달라진다.
    	
    	
    	int[] result = new int[n + 1];
    	result[arr[1] + 1] = 1;
    	for(int i = 2; i < n + 1; i++) {
    		int gt = 0;
    		for(int j = 1; j < n + 1; j++) {
				if (gt == arr[i] && result[j] == 0) {
					result[j] = i;
					break;
				}
				if (result[j] == 0) gt++;
			}
//    		System.out.println(Arrays.toString(result));
    	}
    	StringBuilder sb = new StringBuilder();
    	for(int i = 1; i < n + 1; i++) {
    		sb.append(result[i]).append(" ");
    	}
    	
    	System.out.println(sb);
    	
    }
    
    void testCase() throws Exception {

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {
    	n = Integer.parseInt(bf.readLine());
    	arr = new int[n + 1];
    	stk = new StringTokenizer(bf.readLine());
    	for(int i = 1; i < n + 1; i++) {
    		arr[i] = Integer.parseInt(stk.nextToken());
    	}
    
    }
}
