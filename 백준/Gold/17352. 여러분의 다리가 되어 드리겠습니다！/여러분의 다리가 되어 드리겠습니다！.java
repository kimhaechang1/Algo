

import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[][] info;
    static int[] p;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 1 ~ N 까지의 섬에다가, N -1 개의 다리가 그 섬들을 잇고 있었으며,
    	// 원래는 어떤 두섬 사이든 다리로 왕복할 수 있었다.
    	// 이말은 즉, 어떠한 두섬사이라도 반드시 이동할 수 있는 길이 최소 1개라도 존재한단 뜻
    	
    	// 그러다가 하나를 짤랐고, 그 하나를 다시 찾아서 붙여야한다.
    	
    	// 즉, 다시 생각해보면 원래 하나의 그래프인데, 두개로 쪼개졌단 얘기니까
    	// 집합이 두개였던것으로 해석하자면, 분리집합 문제로 볼 수 있다.
    	init();
    	for(int i = 0; i < info.length; i++) {
    		union(info[i][0], info[i][1]);
    	}
    	for(int i = 1; i < n + 1; i++) {
    		find(i);
    	}
    	// System.out.println(Arrays.toString(p));
    	int a1 = p[1];
    	int a2 = p[1];
    	for(int i = 2 ; i < n + 1; i++) {
    		if (a1 != p[i]) {
    			a2 = p[i];
    			break;
    		}
    	}
    	System.out.print(a1+ " " + a2);
    }
    void init() {
    	p = new int[n + 1];
    	for(int i = 1; i < n + 1; i++) p[i] = i;
    }
    int find(int x) {
    	if (p[x] == x) return p[x];
    	else return p[x] = find(p[x]);
    }
    boolean union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	if (p[a] == p[b]) {
    		return false;
    	}
    	p[b] = a;
    	return true;
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
        info = new int[n - 2][2];
        for(int i = 0; i < n -2 ; i++) {
        	stk = new StringTokenizer(bf.readLine());
        	info[i][0] = Integer.parseInt(stk.nextToken());
        	info[i][1] = Integer.parseInt(stk.nextToken());
        }
    }
}
