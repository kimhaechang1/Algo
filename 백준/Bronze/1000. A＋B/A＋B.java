import java.io.*;
import java.util.StringTokenizer;
public class Main {

  public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String num = br.readLine();
      StringTokenizer stk = new StringTokenizer(num);
      int A = Integer.parseInt(stk.nextToken());
      int B = Integer.parseInt(stk.nextToken());
      System.out.println(A+B);      
  }
}
