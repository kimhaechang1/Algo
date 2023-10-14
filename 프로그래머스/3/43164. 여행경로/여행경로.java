import java.util.*;
class Solution {
    static int N;
	static boolean isFind;
	static String res;
	static class Visited{
		String s;
		String e;
		boolean v;
		public Visited(String s, String e, boolean v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
	}
	static Visited[] vs;
	public String[] solution(String[][] tickets) {
        String[] answer = {};
        N = tickets.length;
        vs = new Visited[N];
        int idx = 0;
        for(String [] ticket : tickets) {
        	String s = ticket[0];
        	String e = ticket[1];
        	vs[idx++] = new Visited(s, e, false);
        }
        
        Arrays.sort(vs, (o1, o2)->{
        	return (o1.e).compareTo(o2.e);
        });
        res = "";
        isFind = false;
        dfs(0, "ICN", "ICN");
        answer = res.split(" ");
        return answer;
    }
	static void dfs(int cnt, String path, String pres) {
		if(isFind) return;
		if(cnt == N) {
			res = path;
			isFind = true;
			return;
		}		
		for(int i = 0;i<N;i++) {
            
			if(pres.equals(vs[i].s) && !vs[i].v) {
				vs[i].v = true;
				dfs(cnt+1, path+" "+vs[i].e, vs[i].e);
				vs[i].v = false;
			}
		}
	}
}