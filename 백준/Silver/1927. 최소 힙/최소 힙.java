import java.util.*;
import java.io.*;
import java.io.*;
public class Main{
	static class Heap{
		private int full = 10;
		private int size = 0;
		
		public int INIT_NUM;
		int [] heap = new int[full];
		
		public Heap(int initNumber) {
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
				/*System.out.println("바뀐 현재 루트의 값 : "+heap[1]);
				System.out.println("바뀐 현재 start 값 : "+heap[start]);
				System.out.println("start : "+start);
				System.out.println("현재 size 값 : "+size);*/
				int min = heap[start * 2];
				int minIdx = start * 2;
				int next = start * 2;
				if((start*2)+1 <=size && heap[(start *2)+1] !=INIT_NUM && min > heap[(start * 2) + 1]) {
					min = heap[(start*2)+1];
					minIdx = start * 2 + 1;
					next = start * 2 + 1;
				}
				if(heap[start] <= min) {
					//System.out.println(heap[start]+" "+min);
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
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Heap heap  = new Heap(-1);
		heap.init();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			int q = Integer.parseInt(bf.readLine());
			if(q == 0) {
				if(heap.isEmpty()) {
					sb.append("0").append("\n");
				}else {
					sb.append(heap.pop()).append("\n");
					//heap.printHeap();
				}
			}else {
				heap.push(q);
				//heap.printHeap();
			}
			
		}
		/*heap.printHeap();
		System.out.println(heap.size());*/
		System.out.print(sb);
		
	}
}