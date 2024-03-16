import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder all = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        for(int i = 0;i<n;i++){
            String tc = bf.readLine();
            StringBuilder sb = new StringBuilder();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iterator = list.listIterator();
            for(char k : tc.toCharArray()){
                if(k == '-'){
                    if(iterator.hasPrevious()){
                        iterator.previous();
                        iterator.remove();
                    }
                }else if(k == '<'){
                    if(iterator.hasPrevious()){
                        iterator.previous();
                    }
                }
                else if(k == '>'){
                    if(iterator.hasNext()){
                        iterator.next();
                    }
                }
                else {
                   iterator.add(k);
                }
            }
            for(char t : list){
                sb.append(t);
            }
            all.append(sb).append("\n");
        }
        System.out.println(all);
    }
}