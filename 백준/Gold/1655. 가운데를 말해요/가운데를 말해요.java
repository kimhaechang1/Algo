import java.util.*;
import java.io.*;

public class Main{
	static class MaxHeap{
        int [] arr;
        public int size;
        private final int INIT_SIZE = 1;
        private final int INIT_NUM;
        public MaxHeap(int init){
            size = 0;
            this.INIT_NUM = init;
            this.arr = new int[100002];
            Arrays.fill(arr, INIT_NUM);
        }
        public int size(){
            return this.size;
        }
        public void sizeDown(){
            if(size == 0) return;
            this.size--;
        }
        public void sizeUp(){
            if(size == 100001) return;
            this.size++;
        }

        public void push(int x){
            sizeUp();
            int s = size();
            arr[s] = x;
            shiftUp();
        }
        public int pop(){
            int poped = 0;
            poped = arr[INIT_SIZE];
            arr[INIT_SIZE] = INIT_NUM;
            int s = size();
            if(s > INIT_SIZE){
                arr[INIT_SIZE] = arr[s];
                arr[s] = INIT_NUM;
            }
            sizeDown();
            shiftDown();
            return poped;
        }
        public int peek() {
			return arr[INIT_SIZE];
		}
        public void shiftUp(){
            int pointer = size();

            while(pointer > 1 && arr[pointer/2] < arr[pointer]){
                swap(pointer/2, pointer);
                pointer /=2;
            }
        }
        private void printHeap(){
            int s = size();
            System.out.print("heap status : ");
            for(int i = 1;i<=s;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
        public void shiftDown(){
            int start = INIT_SIZE;
            int s = size();
            while(start*2 <= s && arr[start*2] != INIT_NUM){
                int swapedIdx = -1;
                int swaped = -1;
                if(arr[start * 2] > arr[start]){
                    swaped = arr[start * 2];
                    swapedIdx = start * 2;
                }
                if(start * 2 + 1 <= s && arr[start * 2 + 1] != INIT_NUM){
                    if(swaped == -1){
                       if(arr[start] < arr[start * 2 + 1]){
                           swaped = arr[start * 2 + 1];
                           swapedIdx = start * 2 + 1;
                       }
                    }else{
                        if(swaped < arr[start * 2 + 1]){
                           swaped = arr[start * 2 + 1];
                           swapedIdx = start * 2 + 1;
                        }
                    }
                }
                if(swapedIdx == -1){
                    break;
                }
                swap(start, swapedIdx);
                start = swapedIdx;
            }
        }
        private void swap(int a, int b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

    }
	static class MinHeap{
		private int full = 10;
		private int size = 0;
		
		public int INIT_NUM;
		int [] heap = new int[full];
		
		public MinHeap(int initNumber) {
			this.INIT_NUM = initNumber;
		}
		
		public void init() {
			Arrays.fill(heap, INIT_NUM);
		}
		
		public int pop() {
			if(size == 0) return -1;
			int res = heap[1];
			heap[1] = INIT_NUM;
			if(size - 1 == 0) {
				--size;
				return res;
			}
			heap[1] = heap[size];
			heap[size] = INIT_NUM;
			--size;
			shiftDown();
			return res;
		}
		
		public void push(int element) {
			if(size+1 == full) {
				int [] temp = new int[full*2];
				Arrays.fill(temp, INIT_NUM);
				for(int i= 1;i<=size;i++) {
					temp[i] = heap[i];
				}
				full=full*2;
				heap = temp;
			}
			heap[++size] = element;
			shiftUp();
		}
		public int peek() {
			return heap[1];
		}
		
		private	void shiftUp() {
			int start = size;
			while(start > 1 && heap[start/2] > heap[start]) {
				swap(start/2, start);
				start = start/2;
			}
		} 
		
		private void shiftDown() {
			int start = 1;
			// 왼쪽 차일드가 남아있을 때 까지 찾아야 한다.
			// 왜냐하면 오른쪽 차일드가 없을 순 있지만, 왼쪽 차일드는 없다면 오른쪽 차일드는 당연히 없기 때문이다.
			while(start*2 <= size && heap[start * 2] != INIT_NUM) {
				int min = heap[start * 2];
				int minIdx = start * 2;
				int next = start * 2;
				if((start*2)+1 <=size && heap[(start *2)+1] !=INIT_NUM && min > heap[(start * 2) + 1]) {
					min = heap[(start*2)+1];
					minIdx = start * 2 + 1;
					next = start * 2 + 1;
				}
				if(heap[start] <= min) {
					break;
				}
				swap(start, minIdx);
				start = next;
			}
		}
		
		private void swap(int idx1, int idx2) {
			int temp = heap[idx1];
			heap[idx1] = heap[idx2];
			heap[idx2] = temp;
		}
		
		public boolean isEmpty() {
			return size == 0 ? true : false;
		}
		
		public int size() {
			return size;
		}
		public void printHeap() {
			System.out.println(Arrays.toString(heap));
		}
	}
	
	
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for(int i = 0;i<n;i++) {
			int value = Integer.parseInt(bf.readLine());
			if(left.size() == right.size()) left.add(value);
			else right.add(value);
			
			if(left.size()!=0 && right.size()!=0) {
				if(right.peek() < left.peek()) {
					int l = left.poll();
					int r = right.poll();
					left.add(r);
					right.add(l);
				}
			}
			sb.append(left.peek()).append("\n");
			
		}
		System.out.print(sb);
	}
}