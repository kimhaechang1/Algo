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
    	HashMap<String, Integer> map = new HashMap<>();
    	Arrays.sort(arr);
//    	System.out.println(Arrays.toString(arr));
    	map.put(arr[0], 1);
    	for(int i = 1; i < n; i++) {
    		for(int j = 0; j < i; j++) {
    			if (arr[i].equals(arr[j])) continue;
    			if (arr[i].startsWith(arr[j])) {
    				map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
    			}
    		}
    		if (!map.containsKey(arr[i])) {
    			map.put(arr[i], 1);
    		}
    	}
//    	System.out.println(map);
    	int answer = 0;
    	for(Map.Entry<String, Integer> entry: map.entrySet()) {
    		if (entry.getValue() == 1) answer++;
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
