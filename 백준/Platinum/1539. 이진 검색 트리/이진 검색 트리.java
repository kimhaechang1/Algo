import java.util.*;
import java.io.*;

public class Main{
	static int [] node;
	static class Node{
		Node left;
		Node right;
		int value;
		public Node(int value) {
			this.left = null;
			this.right = null;
			this.value = value;
		}
		public String toString() {
			return "node value : "+this.value+" left child : "+(this.left == null ? null : this.left.value)+" right node : "+(this.right == null ? null : this.right.value);
		}
	}
	static class Tree{
		Node root;
		long sum;
		public Tree(){
			this.root = null;
			this.sum = 0;
		}
		void insert(int x) {
			if(this.root == null) {
				root = new Node(x);
				sum += 1;
			}else {
				Node node = root;
				int cnt = 1;
				while(true) {
					cnt++;
					if(node.value < x) {
						if(node.right == null) {
							node.right = new Node(x);
							this.sum += cnt;
							break;
						}else {
							node = node.right;
						}
						
					}else {
						if(node.left == null) {
							node.left = new Node(x);
							this.sum += cnt;
							break;
						}else {
							node = node.left;
						}
					}
				}				
			}
		}
	}
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		Tree tree = new Tree();
		// 단순 BST를 직접 구현하면 TLE
		TreeSet<Integer> treeSet = new TreeSet<>();
		long sum = 0;
		int root = Integer.parseInt(bf.readLine());
		sum += 1;
		treeSet.add(root);
		int [] h = new int[250001];
		h[root] = 1;
		for(int i = 1;i<n;i++) {
			int nodeVal = Integer.parseInt(bf.readLine());
			treeSet.add(nodeVal);
			Integer lower = treeSet.lower(nodeVal);
			Integer higher = treeSet.higher(nodeVal);
			if(lower == null && higher != null) {
				h[nodeVal] = h[higher]+1;
			}else if(lower != null && higher == null) {
				h[nodeVal] = h[lower]+1;
			}else {
				if(h[higher] < h[lower]) {
					h[nodeVal] = h[lower] +1;
				}else {
					h[nodeVal] = h[higher] + 1;
				}
			}
		}
		for(int i = 0;i<n;i++) {
			if(i == root) continue;
			if(h[i] == 0) continue;
			sum += h[i];
		}
		System.out.print(sum);
		// 루트노드에서 부터 + 1
		// 1 + 2 + 3 + 4 + 5 + 4 + 5 + 6 + 7 + 3
		// 10 + 14 + 16 = 40
		// 해당 노드가 정착하는순간 트리의 높이는 정해진다.
	}
}