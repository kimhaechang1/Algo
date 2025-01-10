import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        int [] arr =new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0;i<n;i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int find = Integer.parseInt(bf.readLine());
        Arrays.sort(arr);
        int count = 0;
        int start= 0; int end = arr.length-1;
        while(start<end){
            int sum = arr[start]+arr[end];
            if(sum == find){
                count++;
                start++;
            }
            else if(sum < find) start++;
            else end--;
        }
        System.out.println(count);
    }
}