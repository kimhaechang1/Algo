import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static String [] buffer;
    public static void main(String[] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        buffer = bf.readLine().split(" ");
        int len = Integer.parseInt(buffer[0]);
        int l = Integer.parseInt(buffer[1]);
        buffer = bf.readLine().split(" ");
        ArrayDeque<Node> deque = new ArrayDeque<>();
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<len;i++){
            int now = Integer.parseInt(buffer[i]);
            while(!deque.isEmpty() && deque.getLast().value > now){
                deque.pollLast();
            }
            deque.addLast(new Node(i, now));
            if(deque.getFirst().index <= i-l){
                deque.pollFirst();
            }
            sb.append(deque.getFirst().value).append(" ");
        }
        System.out.println(sb);
    }

}
class Node{
    int value;
    int index;
    public Node( int index, int value){
        this.index = index;
        this.value = value;
    }
}