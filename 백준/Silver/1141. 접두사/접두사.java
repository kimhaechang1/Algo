import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static String[] arr;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	// 접두사X 집합이란 집합의 어떠한 단어도 다른 단어의 접두어가 되지않는 집합이다.   	
    	// 최대한 접두어 집합들을 만들고 서로의 접두어 집합들 중에 원소가 1개인것들을 모으면 최대가 되지않을까?
    	// 다시 생각해보면 최초에는 모든 원소가 자신의 집합을 가질것이다.
    	
    	// 정렬을 했기 때문에 만약에 자기 뒷 원소가 현재원소를 포함하지 않는다면 그보다 더 뒤에있는 원소는 절대 포함되지 않는다.
    	// 무슨말이냐?
    	// Hella 라는 문자열과 Hellb hellab 이렇게 3개가 있을때 정렬하면
    	// Hella Hellab Hellb 가 된다. 무조건 자기 오른쪽만 보고 포함되는지 검사하고 포함되어버린다면
    	// 문제의 조건상 접두사X 집합에서 배제해야 한다.
    	// 즉, i 1 ~ N 에서 j 0 ~ i 까지 다 살펴볼 필요가 없다는것이다.
    	Arrays.sort(arr);
    	int answer = n;
    	for(int i = 0; i < n - 1; i++) {
    		if (arr[i + 1].startsWith(arr[i])) {
    			answer--;
    		}
    	}
    	System.out.print(answer);
    	
    	
    	
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
    	arr = new String[n];
    	for(int i = 0; i < n; i++) {
    		arr[i] = bf.readLine();
    	}
    
    }
}
