import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n1, m1, n2, m2;
    static int[][] map1;
    static int[][] map2;
    static int answer;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	
    	// 퍼즐은 각각 정사각형 모양의 격자들로 이루어진 격자판 내에 이루어져있다.
    	// 퍼즐의 조각역시 정사각형 모양이다.
    	// 두 퍼즐의 퍼즐조각이 같은 격자에서 만나지 않도록 배치하여 하나의 액자에 담는 방법중에 가장 적은 비용
    	
    	// 하나를 고정시켜놓고 다른하나를 돌려가며 해도 시간초과가 나지않을것 같다.
    	// 문제는 돌릴 녀석이 직사각형이란것만 주의하면 된다.
    	
    	answer = Integer.MAX_VALUE;
    	
    	for(int k = 0; k < 4; k++) {
    		int[][] main = new int[n1 + n2 * 2][m1 + m2 * 2];
    		for(int i = 0; i < n1; i ++) { 
    			for(int j = 0; j < m1; j++) {
    				main[i + n2][j + m2] = map1[i][j];
    			}
    		}
    		for(int i = 0; i < main.length - n2; i++) {
    			for(int j = 0; j < main[i].length - m2; j++) {
    				go(i, j, main);
    			}
    		}
    		
        	rotate();
    	}
    	System.out.println(answer);
    }
    void rotate() {
    	// 돌리고나서 가로 길이 세로길이에 따라 map2 를 다시 매핑하고
    	// 값을 옮기는 식으로 해보자.
    	int[][] rotated = new int[m2][n2];
    	for(int i = 0; i < n2; i++) {
    		for(int j = 0; j < m2; j++) {
    			
        		rotated[m2 - j - 1][i] = map2[i][j];
    		}
    	}
    	int temp = n2;
    	n2 = m2;
    	m2 = temp;
    	map2 = new int[n2][m2];
    	for(int i = 0; i < n2; i++) {
    		for(int j = 0; j < m2; j++) {
    			map2[i][j] = rotated[i][j];
    		}
    	}
    	
    }
    
    void go(int sy, int sx, int[][] main) {
    	for(int i = sy; i < sy + n2; i++) {
    		for(int j = sx; j < sx + m2; j++) {
    			if (map2[i - sy][j - sx] == 1 && main[i][j] == 1) {
    				return;
    			}
    		}
    	}
    	
		int y1 = Math.min(sy, n2);
		int x1 = Math.min(sx, m2);
		int y2 = Math.max(n2 + n1, sy + n2);
		int x2 = Math.max(m2 + m1, sx + m2);
		
		answer = Math.min((y2 - y1) * (x2 - x1), answer);
    }
    
    void draw(int[][] main, int sy, int sx) {
    	int[][] copy = new int[main.length][main[0].length];
    	for(int i = 0; i < copy.length; i++) {
    		for(int j = 0; j < copy[i].length; j++) {
    			copy[i][j] = main[i][j];
    		}
    	}
    	
    	for(int i = sy; i < sy + n2; i++) {
    		for(int j = sx; j < sx + m2; j++) {
    			if (copy[i][j] == 1 && map2[i - sy][j - sx] == 0) {
    				
    			} else if (copy[i][j] == 0 && map2[i - sy][j - sx] == 1) {
    				copy[i][j] = 1;
    			}
    		}
    	}
    	
    	printBoard(copy);
    	
    }
    void printBoard(int[][] map) {
    	for(int i = 0; i < map.length;i ++) {
    		for(int j= 0; j < map[i].length; j++) {
    			System.out.print(map[i][j] +" ");
    		}
    		System.out.println();
    	}
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
    	stk = new StringTokenizer(bf.readLine());
    	n1 = Integer.parseInt(stk.nextToken());
    	m1 = Integer.parseInt(stk.nextToken());
    	map1 = new int[n1][m1];
    	for(int i = 0; i < n1; i++) {
    		String temp = bf.readLine();
    		for(int j = 0; j < m1; j++) {
    			map1[i][j] = temp.charAt(j) - '0';
    		}
    	}
    	
    	stk = new StringTokenizer(bf.readLine());
    	n2 = Integer.parseInt(stk.nextToken());
    	m2 = Integer.parseInt(stk.nextToken());
    	map2 = new int[n2][m2];    
    
    	for(int i = 0; i < n2; i++) {
    		String temp = bf.readLine();
    		for(int j = 0; j < m2; j++) {
    			map2[i][j] = temp.charAt(j) - '0';
    		}    	
		}
    }
}
