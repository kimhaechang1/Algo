
import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[] crains;
    static int m;
    static int[] ms;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
    	// 모든 크레인은 동시에 움직인다.
    	// 크레인별로 무게제한이 있다.
    	// 모든 박스를 배로 옮기는데 필요한 최솟값
    	// 모든 크레인은 1분에 박스 1개 배에 실을 수 있다.    	
    	
    	// 크래인 최대 50개, 박스 개수 최대 10000개
    	// 
    	
    	// 박스들 중에 최대값이 크레인들의 최대 한계치보다 큰게 있으면 옮길 수 없다.
    	// 가장 단순하게 접근해보자.
    	// 결국 모든 박스에 대해서 모든 크레인이 놀지않고 일을 최대한 할때가 시간이 최소로 발생한다.
    	// 그럼 각 크레인들이 연속해서 오름차순 정렬된 박스를 가져가는건 어떨까?
    	// 이것은 좋지않은 방법이다.
    	// 왜 그렇냐면, 자신이 할수있는 최대치를 처리하지못해 효율이 떨어지기 때문이다.
    	// 예를들어 아래와 같은 상황일 때
    	// 4
    	// 1 2 3 4
    	// 8
    	// 1 1 2 2 3 3 4 4 
    	
    	// 그냥 오름차순 정렬순서대로 밀어넣으면 1 1 2 2 를 처리하게 되고 3 3 4 4 로 인해 나중에 1, 2 크레인은 놀게된다.
    	// 하지만 자신이 처리할수있는 최대치를 잡고 일한다는 마인드로 들어가면
    	// 동시에 움직인다는 특징으로인해 시간대비 효율이 최대로 나올수 밖에 없다.
    	// 왜냐하면 동시성을 최대한 활용해야 같은시간 1분을 소모할때 더많은 박스를 옮길 수 있다.
    	Arrays.sort(crains);
    	TreeMap<Integer, Integer> boxMap = new TreeMap<>();
    	for(int i = 0; i < m; i++) {
    		boxMap.put(ms[i], boxMap.getOrDefault(ms[i], 0)+ 1);
    	}
    	if (crains[n - 1] < boxMap.lastKey()) {
    		System.out.print(-1);
    		return;
    	}
    	int cnt = 0;
    	int answer = 0;
    	while(cnt < m) {
    		for(int i = 0; i < n; i++) {
    			if (cnt >= m) break;
    			Map.Entry<Integer, Integer> entry = boxMap.floorEntry(crains[i]);
    			if (entry == null) continue;
    			if (entry.getValue() - 1 == 0) {
    				boxMap.remove(entry.getKey());
    			} else {
    				boxMap.put(entry.getKey(), entry.getValue() - 1);
    			}
    			cnt++;
    		}
    		answer++;
    	}
    	System.out.print(answer);
    	
    	

    	
    	
    	
    }
    
    void testCase() throws Exception {

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {
    	n = Integer.parseInt(bf.readLine());
    	crains = new int[n];
    	stk = new StringTokenizer(bf.readLine());
    	for(int i = 0;i < n; i++) {
    		crains[i] = Integer.parseInt(stk.nextToken());
    	}
    	m = Integer.parseInt(bf.readLine());
    	ms = new int[m];
    	stk = new StringTokenizer(bf.readLine());
    	for(int i = 0;i < m; i++) {
    		ms[i] = Integer.parseInt(stk.nextToken());
    	}
    
    }
}
