import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int [] arr;
    static int [] brr;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        for(int i= 0;i<n;i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(arr);
        // 수열 내 특정원소가 있는지 검사하는것
        // 이분탐색으로 하면 되는데,
        // 우선 a + b + c = k 가 되는것 중에 k가 가장 큰 것을 구해야 한다.
        // 이걸 다른말로 하자면 a + b = k - c 가 된다.
        // 그러면 두 수의 합이 하나 선택한 값을 어떤 두 수의 차와 같다는 것을 의미
        // 모든 두 수의 합을 하나의 수열로 만들고 어떤 두 수의 차와 같은것이 존재하는지 이분탐색으로 찾으면 된다.
        // 수식을 먼저 생각하고 편하게 하는 방법으로 바꿔보자
        int max = 0;
        ArrayList<Integer> list =new ArrayList<>();

        for(int i= 0;i<n;i++){
            for(int j = 0;j<n;j++){
                list.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(list);
        for(int i = n-1;i>-1;i--){
            for(int j= i;j>-1;j--){
                if(search(list,arr[i] - arr[j])){
                    max = Math.max(arr[i], max);
                }
            }
        }
        System.out.print(max);
    }
    static boolean search(ArrayList<Integer> list, int find){
        int start = 0;
        int end = list.size()-1;
        while(start <= end){
            int mid = (start + end)/2;
            if(find > list.get(mid)){
                start = mid+1;
            }else if(find < list.get(mid)){
                end = mid-1;
            }else{
                return true;
            }
        }
        return false;
    }

}