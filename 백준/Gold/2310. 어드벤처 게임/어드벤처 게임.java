import java.util.*;
import java.io.*;

public class Main{
	static boolean flg;
	static int N;
	static class Room {
		String type;
		int coin;
		ArrayList<Integer> list = new ArrayList<>();
		public Room(String type, int coin) {
			this.type = type;
			this.coin = coin;
		}
		public String toString() {
			return "type" +type+" coin "+coin+" roomNumbers : "+(Arrays.toString(list.toArray()));
		}
	}
	
	static boolean [] v;
	static StringTokenizer stk;
	static Room [] rooms;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			flg = false;
			N = Integer.parseInt(bf.readLine());
			if(N == 0) break;
			rooms = new Room[N+1];
			for(int i = 1;i<N+1;i++){
				stk =new StringTokenizer(bf.readLine());
				String type = stk.nextToken();
				int coin = Integer.parseInt(stk.nextToken());
				if(type.equals("T")) coin *=-1;
				rooms[i] = new Room(type,coin);
				while(true) {
					int Rnumber = Integer.parseInt(stk.nextToken());
					if(Rnumber == 0) break;
					rooms[i].list.add(Rnumber);
				}
			}
			v = new boolean[N+1];
			int have = 0;
			// pre 
			if(rooms[1].type.equals("L") || rooms[1].type.equals("T")) {
				have += rooms[1].coin;
			}
			v[1] = true;
			go(1,have);
			if(flg) {
				sb.append("Yes").append("\n");
			}else {
				sb.append("No").append("\n");
			}
		}
		System.out.print(sb);
	}
	static void go(int present, int have) {
		
		if(flg) {
			return;
		}
		if(have < 0) {
			return;
		}
		if(have >= 0 && present == N) {
			flg = true;
			return;
		}
		
		for(int roomNumber: rooms[present].list) {
			if(flg) break;
			if(v[roomNumber]) continue;
			v[roomNumber] = true;
			if(rooms[roomNumber].type.equals("T") && have + rooms[roomNumber].coin >= 0) {
				have += (rooms[roomNumber].coin);
				go(roomNumber, have);
				have -= (rooms[roomNumber].coin);
			}
			else if(rooms[roomNumber].type.equals("L")) {
				if(rooms[roomNumber].coin <= have) {
					go(roomNumber, have);
				}else {
					have += (rooms[roomNumber].coin-have);
					go(roomNumber, have);
					have -= (rooms[roomNumber].coin-have);
				}
			}
			v[roomNumber] = false;
		}
	}
}