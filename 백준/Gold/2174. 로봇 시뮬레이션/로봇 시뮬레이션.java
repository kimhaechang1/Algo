import java.util.*;
import java.io.*;

public class Main{
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [][] map;
	static int a;
	static int b;
	static int n;
	static int m;
	static StringTokenizer stk;
	static int [][] arr;
	static Queue<int []> queue;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk =new StringTokenizer(bf.readLine());
		a = Integer.parseInt(stk.nextToken());
		b = Integer.parseInt(stk.nextToken());
		map = new int[b][a];
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		int [][] init = new int[n][3];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			init[i][1] = Integer.parseInt(stk.nextToken())-1;
			init[i][0] = b-Integer.parseInt(stk.nextToken());
			init[i][2] = getDir(stk.nextToken());
			map[init[i][0]][init[i][1]] = i+1;
		}
		String [] commands = new String[m];
		for(int i = 0;i<m;i++) {
			commands[i] = bf.readLine();
		}
		/*for(int i = 0;i<b;i++) {
			for(int j = 0;j<a;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}*/
		String res = "OK";
		t : for(String command : commands) {
			stk = new StringTokenizer(command);
			int idx = Integer.parseInt(stk.nextToken())-1;
			String com = stk.nextToken();
			int step = Integer.parseInt(stk.nextToken());
			//System.out.println(command);
			int sy = init[idx][0];
			int sx = init[idx][1];
			
			map[init[idx][0]][init[idx][1]] = 0;
			for(int j = 0;j<step;j++) {
				int sd = init[idx][2];
				switch(com) {
				case "L":
					if(sd == 0) {
						init[idx][2] = 2;
					}else if(sd == 1) {
						init[idx][2] = 3;
					}else if(sd == 2) {
						init[idx][2] = 1;
					}else {
						init[idx][2] = 0;
					}
					break;
				case "R":
					if(sd == 0) {
						init[idx][2] = 3;
					}else if(sd == 1) {
						init[idx][2] = 2;
					}else if(sd == 2) {
						init[idx][2] = 0;
					}else {
						init[idx][2] = 1;
					}
					break;
				case "F":
					//System.out.println("before : sy : "+sy +" sx : "+sx);
					//System.out.println("before : sd : "+sd);
					sy += dy[sd];
					sx += dx[sd];
					//System.out.println("after : sy : "+sy +" sx : "+sx);
					//System.out.println(sy+" "+sx);
					if(sy >= b || sy < 0  || sx >= a || sx < 0) {
						StringBuilder sb =new StringBuilder();
						sb.append("Robot").append(" ").append(idx+1).append(" ").append("crashes").append(" ").append("into").append(" ").append("the").append(" ").append("wall");
						res = sb.toString();
						break t;
					}
					if(map[sy][sx] != 0) {
						//System.out.println("map[sy][sx] : "+map[sy][sx]);
						StringBuilder sb =new StringBuilder();
						sb.append("Robot").append(" ").append(idx+1).append(" ").append("crashes").append(" ").append("into").append(" ").append("robot").append(" ").append(map[sy][sx]);
						res = sb.toString();
						break t;
					}
					
					break;
				}
			}
			init[idx][0] = sy;
			init[idx][1] = sx;
			
			map[sy][sx] = idx+1;
			/*for(int i = 0;i<b;i++) {
				for(int j = 0;j<a;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
				
			}
			System.out.println("==================");*/
		}
		System.out.println(res);
	}
	static int getDir(String d) {
		switch(d) {
		case "N":
			return 0;
		case "S":
			return 1;
		case "W":
			return 2;
		default:
			return 3;
		}
	}
}