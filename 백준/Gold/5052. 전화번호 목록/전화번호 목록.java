import java.util.*;
import java.io.*;

public class Main{
    static int T;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            int n = Integer.parseInt(bf.readLine());
            String [] strs = new String[n];
            for(int i = 0;i<strs.length;i++){
                strs[i] = bf.readLine();
            }
            Arrays.sort(strs);
            String ans = "YES";
            for(int i = 0;i<n-1;i++){
                int target;
                String sub;
                if(strs[i].length() < strs[i+1].length()){
                    target = strs[i].length();
                    sub = strs[i+1].substring(0, target);
                }else if(strs[i].length() > strs[i+1].length()){
                    target = strs[i+1].length();
                    sub = strs[i].substring(0, target);
                }else{
                    continue;
                }
                if(strs[i].equals(sub)){
                    ans = "NO";
                    break;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}