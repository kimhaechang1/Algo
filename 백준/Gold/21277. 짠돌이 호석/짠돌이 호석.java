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
    	
    	// 두 격자모두 최대 50*50으로 올 수 있음
    	
    	// 그러면 하나를 고정시켜놓고 다른하나를 쭉 돌면서 격자칸에 겹치는게 없을때 마다 가로 세로 길이 계산하면 되지 않을까
    	// 돌리기 편할라면 정사각형인게 좋으니까
    	// 어짜피 배열자체도 별로안크고, 그러면 돌릴 녀석과 고정시킬 녀석을 나눠봤을 때,
    	
    	// 고정시킬녀석은 돌릴녀석이 최대 50*50이니까 서로 아에 딱붙이는 경우만 생각한다 하면 150*150 에 가운데에 고정시킬 녀석을 배치해야한다.
    	
    	answer = Integer.MAX_VALUE;
    	
//    	 100 * 100 * 50 * 50 = 2500 0000
    	for(int k = 0; k < 4; k++) {
    		int[][] main = new int[n1 + n2 * 2][m1 + m2 * 2];
    		for(int i = 0; i < n1; i ++) { 
    			for(int j = 0; j < m1; j++) {
    				main[i + n2][j + m2] = map1[i][j];
    			}
    		}
//    		printBoard(main);
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
//    	printBoard(rotated);
    	map2 = new int[n2][m2];
//    	System.out.println("n2: "+n2+ " m2: "+m2);
    	for(int i = 0; i < n2; i++) {
    		for(int j = 0; j < m2; j++) {
    			map2[i][j] = rotated[i][j];
    		}
    	}
    	
    }
    
    void go(int sy, int sx, int[][] main) {
//    	printBoard(map2);
    	for(int i = sy; i < sy + n2; i++) {
    		for(int j = sx; j < sx + m2; j++) {
//    			System.out.println("ry: "+(i - sy) +" rx: "+ (j - sx) +" y: "+i+" x: "+j);
    			if (map2[i - sy][j - sx] == 1 && main[i][j] == 1) {
    				return;
    			}
    		}
    	}
    	//draw(main, sy, sx);
    	
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
