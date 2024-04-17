// start 09:00
// end
import java.util.*;
class Solution {
    class Data{
        int idx;
        int x;
        int y;
        public Data(int idx, int x, int y){
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "[ idx: "+idx+", x: "+x+" y: "+y+" ]";
        }
    }
    static int n;
    class Node{
        Node left;
        Node right;
        int val;
        int x;
        public Node(int val, int x){
            this.left = null;
            this.right = null;
            this.val = val;
            this.x = x;
        }
    }
    class Tree{
        Node root;
        public Tree(Node root){
            this.root = root;
        }
        public void preorder(Node now, ArrayList<Integer> list){
            if(now == null) return;
            list.add(now.val);
            preorder(now.left, list);
            preorder(now.right, list);
        }
        public void postorder(Node now, ArrayList<Integer> list){
            if(now == null) return;
            postorder(now.left, list);
            postorder(now.right, list);
            list.add(now.val);
        }
    }
    
    static Data [] datas;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        datas = new Data[nodeinfo.length];
        n = nodeinfo.length;
        for(int i= 0;i<nodeinfo.length;i++){
            datas[i] = new Data(i, nodeinfo[i][0], nodeinfo[i][1]);
        }
        Arrays.sort(datas, (a, b)->{
            if(a.y == b.y){
                return a.x - b.x;
            }
           return b.y - a.y;
        });
        //System.out.println(Arrays.toString(datas));
        Node root = new Node(datas[0].idx, datas[0].x);
        int idx = 1;
        while(idx < n){
            Data now = datas[idx];
            Node nxt = root;
            while(nxt != null){
                if(nxt.x < now.x){
                    if(nxt.right == null){
                        nxt.right = new Node(datas[idx].idx, datas[idx].x);
                        break;
                    }else{
                        nxt = nxt.right;
                    }
                }else{
                    if(nxt.left == null){
                        nxt.left = new Node(datas[idx].idx, datas[idx].x);
                        break;
                    }else{
                        nxt = nxt.left;
                    }
                }
            }
            idx++;
        }
        Tree tree = new Tree(root);
        ArrayList<Integer> result = new ArrayList<>();
        tree.preorder(root, result);
        tree.postorder(root, result);
        
        int cnt = 0;
        for(int val :result){
            answer[cnt/n][cnt%n] = val+1;
            cnt++;
        }
        return answer;
    }
}