
import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static char[][] map;
    static int INF = 987654321;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	// 지금보니까 결국 두 노드사이에 연결지점이 존재한다면 서로 노드끼리 2-친구 관계가 된다.
    	// 더웃긴건 두 사이의 2-친구 라는 뜻은, 두 노드사이에 하나의 중간노드만 존재해야 한단 뜻
    	int[][] g = new int[n][n];
    	for(int i = 0 ; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			g[i][j] = map[i][j] == 'Y' ? 1 : 987654321;
    		}
    	}
    		
    	for(int k = 0; k < n; k++) {
    		for(int i = 0; i < n; i++) {
    			if (k == i) continue;
    			for(int j = 0; j < n; j++) {
    				if (k == j) continue;
    				if (i == j) continue;
    				g[i][j] = Math.min(g[i][j], g[i][k]+g[k][j]);
    			}
    		}
    	}
    	// 그러면 플로이드워셜을 통해 연결가능성을 보고
    	// 어짜피 둘 사이 다이렉트 연결이라면 비용을 1로 두었을 텐데,
    	// 2-친구를 만족하려면 중간노드 하나까지만 낄 수 있으니까, 비용 2까지만 허락된다.
    	int answer = 0;
    	for(int i = 0 ;i < n; i++) {
    		int c = 0;
    		for(int j = 0; j < n; j++) {
    			if (g[i][j] != 987654321 && g[i][j] <= 2) {
    				c++;
    			}
    		}
    		answer = Math.max(answer, c);
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
    	map = new char[n][n];
    	for(int i = 0;  i < n; i++) {
    		map[i] = bf.readLine().toCharArray();
    	}
    
    }
}
