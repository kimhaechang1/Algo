import java.util.*;
import java.io.*;
import java.io.*;
public class Main{
	
	public static void main(String [] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1;t<=T;t++) {
			int N = Integer.parseInt(bf.readLine());
			
			TreeSet<Integer> tset = new TreeSet<>();
	        TreeMap<Integer, Integer> tmap = new TreeMap<>();
	        
	        for(int i = 0;i<N;i++){
	            String [] q = bf.readLine().split(" ");
	            String order = q[0];
	            char where = (q[1].toCharArray())[0];
	            if(order.equals("I")){
	            	int key = Integer.parseInt(q[1]);
	            	Integer cnt = tmap.get(key);
	                if(cnt == null) {
	                	tmap.put(key, 1);
	                }else {
	                	tmap.put(key, ++cnt);
	                }
	                
	            }else if(order.equals("D")){
	                
	                if(tmap.isEmpty()) continue;
	                if(where == '-'){
	                    Integer lower = tmap.firstKey();
	                    Integer cnt = tmap.get(lower);
	                    if(--cnt == 0) {
	                    	tmap.remove(lower);
	                    }else {
	                    	tmap.put(lower, cnt);
	                    }
	                }else{
	                	Integer higher = tmap.lastKey();
	                    Integer cnt = tmap.get(higher);
	                    if(--cnt == 0) {
	                    	tmap.remove(higher);
	                    }else {
	                    	tmap.put(higher, cnt);
	                    }
	                }
	            }
	        }
	        if(tmap.isEmpty()){
	            sb.append("EMPTY").append("\n");
	        }else{
	            sb.append(tmap.lastKey()).append(" ").append(tmap.firstKey()).append("\n");
	            
	        }
		}
		System.out.println(sb);
    }
}