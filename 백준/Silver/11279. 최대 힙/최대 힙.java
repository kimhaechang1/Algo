import java.util.*;
import java.io.*;

public class Main{
    static class Heap{
        int [] arr;
        public int size;
        private final int INIT_SIZE = 1;
        public Heap(){
            size = 0;
            this.arr = new int[100001];
        }
        public int size(){
            return this.size;
        }
        public void sizeDown(){
            if(size == 0) return;
            this.size--;
        }
        public void sizeUp(){
            if(size == 100000) return;
            this.size++;
        }

        public void push(int x){
            sizeUp();
            int s = size();
            arr[s] = x;

            shiftUp();
            //printHeap();

        }
        public int pop(){
            int poped = 0;
            poped = arr[INIT_SIZE];
            arr[INIT_SIZE] = 0;
            int s = size();
            if(s > INIT_SIZE){
                arr[INIT_SIZE] = arr[s];
                arr[s] = 0;
            }
            sizeDown();
            shiftDown();
            //printHeap();

            return poped;
        }
        public void shiftUp(){
            int pointer = size();

            while(pointer > 1 && arr[pointer/2] < arr[pointer]){
                //System.out.println("함수타니?");
                //System.out.println("swap전 : ");printHeap();
                //System.out.println("swap 대상 "+arr[pointer/2]+" <=> "+arr[pointer]);
                swap(pointer/2, pointer);
                //System.out.println("swap후 : ");printHeap();
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
            //System.out.println("shiftDown 전 : ");printHeap();
            while(start*2 <= s && arr[start*2] > 0){
                //System.out.println("fdafaasfasfaw 돌기");
                int swapedIdx = -1;
                int swaped = -1;
                if(arr[start * 2] > arr[start]){
                    swaped = arr[start * 2];
                    swapedIdx = start * 2;
                }
                if(start * 2 + 1 <= s && arr[start * 2 + 1] > 0){
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
                //System.out.println("swap 후 start : "+start);
            }
        }
        private void swap(int a, int b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

    }
    public static void main(String [] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        Heap heap = new Heap();
        StringBuilder sb = new StringBuilder();
        while(n-- > 0){
            int order = Integer.parseInt(bf.readLine());
            if(order == 0){
                sb.append(heap.pop()).append("\n");
            }else{
                heap.push(order);
            }
        }
        System.out.print(sb);
    }
}