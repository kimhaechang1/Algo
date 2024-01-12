import java.util.*;
import java.io.*;

public class Main{
    public static void main(String [] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] targets = bf.readLine().toCharArray();
        String find = bf.readLine();
        char [] fchs = find.toCharArray();
        int flen = find.length();
        for(int i = 0;i< targets.length;i++){
            st.push(targets[i]);
            if(st.size() >= flen){
                int idx = flen;
                char [] tch = new char[flen];
                boolean isSame = true;
                while(!st.isEmpty() && idx-- > 0){
                    tch[idx] = st.pop();
                    if(tch[idx] != fchs[idx]){
                        isSame = false;
                        break;
                    }
                }
                while(!isSame && idx < flen){
                    st.push(tch[idx++]);
                }
            }
        }
        Stack<Character> temp = new Stack<>();
        while(!st.isEmpty()) temp.push(st.pop());
        while(!temp.isEmpty()) sb.append(temp.pop());
        System.out.print("".equals(sb.toString()) ? "FRULA" : sb);
    }
}
