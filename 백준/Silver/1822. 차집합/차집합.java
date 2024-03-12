import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int m;
    static int [] arr;
    static int [] brr;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i =0;i<n;i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        brr = new int[m];
        stk = new StringTokenizer(bf.readLine());
        for(int i =0;i<m;i++){
            brr[i] = Integer.parseInt(stk.nextToken());
        }
        // 두 원소 집합의 크기가 다르며, 50만개가 있을 수 있으므로, 단순 N^2비교로 수열을 비교하면 안된다.
        // 원소의 순서는 중요치 않으므로 정렬을 하고 A집합 내 원소가 B집합내에 존재 할 수 있는지 이분탐색으로 찾으면
        // 시간복잡도는 약 50만 * log50만 이 된다.
        Arrays.sort(arr);
        Arrays.sort(brr);
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for(int A : arr){
            if(!search(brr,  A)){
                list.add(A);
            }
        }
        sb.append(list.size()).append("\n");
        for(int num : list){
            sb.append(num).append(" ");
        }

        System.out.print(sb);
    }
    static boolean search(int [] arr, int find){
        int s = 0;
        int e = m-1;
        while(s <= e){
            int mid = (s+e)/2;
            if(arr[mid] > find){
                e = mid-1;
            }else if(arr[mid] == find){
                return true;
            }else{
                s = mid+1;
            }
        }
        return false;
    }
}