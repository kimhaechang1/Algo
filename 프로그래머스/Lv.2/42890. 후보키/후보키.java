import java.util.*;
import java.io.*;
// 2024 01 13 14:20 start
// 2024 01 13
class Solution {
    static boolean [] sel;
    static int max;
    static int n;
    static int m;
    static boolean [] isPK;
    static boolean [] candiK;
    static boolean [] nums;
    static HashSet<String> cset;
    // 2 3 X
    static public int solution(String[][] relation) {
        // 유일하게 식별
        // 서브셋 검사
        // 실수한 부분, 후보키 개수를 구하는거였노
        n = relation.length; // rows;
        m = relation[0].length; // cols;
        max = 1;
        int answer = 0;
        cset = new HashSet<>();
        for(int i = 0;i<relation[0].length;i++) {
        	nums = new boolean[m];
        	dfs(0, 0, i, relation, "");
        }
        answer = Math.max(max, cset.size());
       
        return answer;
    }
    static void dfs(int depth, int start, int end, String [][] relation, String present) {
    	if(depth == end) {
    		if(!isUnique(present, relation)) {
    			return;
    		}
    		if(!isMin(present)) {
    			return;
    		}
    		cset.add(present);
    		return;
    	}
    	
    	for(int i= start;i<m;i++) {
    		if(nums[i]) continue;
    		nums[i] = true;
    		dfs(depth+1, i, end, relation, present+String.valueOf(i));
    		nums[i] = false;
    	}
    }
    static boolean isUnique(String pres, String [][] relation) {
    	int [] arr = new int[pres.length()];
    	for(int i = 0;i<pres.length();i++) {
    		arr[i] = pres.charAt(i)-'0';
    	}
    	HashSet<String> set = new HashSet<>();
    	for(int i= 0;i<n;i++) {
    		String elem = "";
    		for(int j = 0;j<arr.length;j++) {
    			elem += relation[i][arr[j]];
    		}
    		if(set.contains(elem)) {
    			return false;
    		}else {
    			set.add(elem);
    		}
    	}
    	return true;
    }
    static boolean isMin(String pres) {
    	int [] arr = new int[pres.length()];
    	HashSet<Integer> sett = new HashSet<>();
    	for(int i = 0;i<pres.length();i++) {
    		arr[i] = pres.charAt(i)-'0';
    		sett.add(arr[i]);
    	}
    	Iterator<String> iter = cset.iterator();
    	while(iter.hasNext()) {
    		String p = iter.next();
    		int [] candi = new int[p.length()];
    		int c = 0;
    		for(int j = 0;j<candi.length;j++) {
    			candi[j] = p.charAt(j)-'0';
    			if(sett.contains(candi[j])) {
    				c++;
    			}
    		}
    		if(c == candi.length) return false;
    		
    	}
    	return true;
    }
    static void print() {
    	Iterator<String> iter = cset.iterator();
    	while(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    }
}