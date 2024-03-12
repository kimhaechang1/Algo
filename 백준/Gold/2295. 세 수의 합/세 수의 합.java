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
        // 최대 1000 개의 원소가 있기 때문에 N^3 알고리즘은 무조건 터진다.
        // 이걸 N^2으로 줄이기 위하여 a + b = k - c 라는 식으로 볼 수 있다.
        // 말로 해석하면, 어떤 두 수의 합은 어떤 수에서 어떤수를 뺀것과 같다고 볼 수 있다.
        // 그러면 모든 두 수의 합에 대한 list는 우리가 시간복잡도 내에 만들 수 있다.
        // 그러면 어떤 수에서 어떤 수를 뺀 값이 두수의 합이 되는 경우를 찾는다 즉, list내에 어떤수 - 어떤수를 한 값이 있는지 찾는다.
        // list내에 순서는 중요치 않으므로 정렬 후 이분탐색으로 탐색시간을 log로 줄이는 것
        // 결국 시간복잡도는 N^2 + (N(N+1)/2)log(N(N+1)/2)
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