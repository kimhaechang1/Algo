import java.util.*;
import java.io.*;

class Solution {
    static StringTokenizer stk;
    public int[] solution(String today, String[] terms, String[] p) {
        ArrayList<Integer> reslist = new ArrayList<>();
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        for(String t : terms ){
            String [] q = t.split(" ");
            map.put(q[0], Integer.parseInt(q[1]));
        }
        System.out.println(today);
        stk = new StringTokenizer(today, ".");
        
        //String [] frag = today.split(".");
        //System.out.println(Arrays.toString(frag));
        int ty= Integer.parseInt(stk.nextToken());
        int tm= Integer.parseInt(stk.nextToken());
        int td= Integer.parseInt(stk.nextToken());
        for(int i = 0;i<p.length;i++){
            String [] data = p[i].split(" ");
            String date = data[0];
            String type = data[1];
            stk = new StringTokenizer(date, ".");
            int y = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int am = map.get(type);
            int rd = d; 
            int rm = m;
            int ry = y;
            if(m + am >= 12){
                ry += ((m + am)/12);
                if((m + am) % 12 == 0){
                    rm = 12;
                    ry--;
                }else{
                    rm = (m + am)%12;   
                }
                   
            }else{
                rm = m + am;
            }
            System.out.println(ry +" "+rm +" "+rd);
            boolean isDie = false;
            if(ty > ry){
                isDie = true;
            }else if(ty == ry){
                if(tm > rm){
                    isDie = true;
                }else if(tm == rm){
                    if(td >= rd){
                        isDie = true;
                    }
                }
            }
            System.out.println(isDie);
            if(isDie){
                reslist.add(i+1);    
            }
            
        }
        answer = new int[reslist.size()];
        for(int i = 0;i<reslist.size();i++){
            answer[i] = reslist.get(i);
        }
        return answer;
    }
}