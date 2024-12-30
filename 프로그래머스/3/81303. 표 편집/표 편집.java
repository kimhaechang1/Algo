// U X: 현재 선택된 행에서 x칸 위에 있는 행을 선택한다.
// D X: 현재 선택된 행에서 X칸 아래에 있는 행을 선택한다.
// C: 현재 선택된 행을 삭제한 후, 바로 아래의 행을 선택합니다. 
// 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
// Z: 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.

// 표의 범위를 벗어나는 이동은 입력으로 주어지지 않음

// 커서 이동 문제고 뒤로도 이동하고, 앞으로도 이동할 수 있어야 한다.
// 링크를 바꿔주는게 유리하니까 양방향 연결리스트로 추정된다.
// 삭제의 경우 가장 먼저 삭제된것이 나중에 나와야 하니까, stack으로 관리하면 될듯?
// 원래대로 중간삽입 하려면, 자기 이전노드와 자기 앞 노드가 뭔지 알아야 할듯
// [0]: 이전, [1]: 본인 노드번호, [2]: next 

import java.util.*;

class Solution {
    static class Node {
        Node prev;
        Node next;
        int idx;
    }
    static Node[] nodes;
    static Stack<int[]> remove;
    static char[] result;
    public String solution(int n, int k, String[] cmd) {
        remove = new Stack<>(); 
        nodes = new Node[n];
        nodes[0] = new Node();
        nodes[0].idx = 0;
        nodes[n - 1] = new Node();
        nodes[n - 1].idx = n - 1;
        for(int i = 1;i < n; i++) {
            nodes[i] = new Node();
            nodes[i].idx = i;
            nodes[i - 1].next = nodes[i];
            nodes[i].prev = nodes[i - 1];
        }
        result = new char[n];
        Arrays.fill(result, 'O');
        Node cursor = nodes[k];
        int cnt;
        int cc = 0;
        for(String c: cmd) {
            if (c.startsWith("U")) {
                cnt = Integer.parseInt(c.split(" ")[1]);
                while(cnt-- > 0) {
                    cursor = cursor.prev;
                }
            } else if (c.startsWith("D")) {
                cnt = Integer.parseInt(c.split(" ")[1]);
                while(cnt-- > 0) {
                    cursor = cursor.next;
                }
            } else if (c.startsWith("C")) {
                Node prev = cursor.prev;
                Node next = cursor.next;
                result[cursor.idx] = 'X';
                remove.push(new int[]{prev == null ? -1 : prev.idx, cursor.idx, next == null ? -1 : next.idx});
                if (prev != null) {
                    prev.next = next;    
                }
                if (next != null) {
                    next.prev = prev;    
                }
                
                cursor = cursor.next == null ? cursor.prev : cursor.next;
                
            } else {
                int[] info = remove.pop();
                Node removed = nodes[info[1]];
                result[info[1]] = 'O';
                if (info[0] != -1) {
                    nodes[info[0]].next = removed;    
                }
                if (info[2] != -1) {
                    removed.next = nodes[info[2]];    
                }
                if (info[2] != -1) {
                    nodes[info[2]].prev = removed;    
                }
                if (info[0] != -1) {
                    removed.prev = nodes[info[0]];    
                }
            }
        }
        
        StringBuilder ans = new StringBuilder();
        for(char r: result) ans.append(r);
        return ans.toString();
    }
}