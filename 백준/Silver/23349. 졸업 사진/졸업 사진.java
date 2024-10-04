import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;

    static class Data {
        String name;
        String place;

        int st;
        int ed;

        public Data(String name, String place, int st, int ed) {
            this.name = name;
            this.place = place;
            this.st = st;
            this.ed = ed;
        }
    }
    static class Line implements Comparable<Line>{
        String place;
        int st;
        int ed;
        int cnt;
        public Line(String place, int st, int ed, int cnt) {
            this.place = place;
            this.st = st;
            this.ed = ed;
            this.cnt = cnt;
        }
        public int compareTo(Line o){
            if(this.cnt  == o.cnt) {
                if(this.place.compareTo(o.place) == 0) {
                    return this.st - o.st;
                }
                return this.place.compareTo(o.place);
            }
            return o.cnt - this.cnt;
        }
        public String toString() {
            return "[ place: "+place+" st: "+st+" ed: "+ed+" cnt: "+cnt+" ]";
        }
    }
    static Data[] drr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        drr = new Data[n];
        HashMap<String, Integer> nameMap = new HashMap<>();
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            drr[i] = new Data(stk.nextToken(), stk.nextToken(), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            if (nameMap.containsKey(drr[i].name)) continue;
            nameMap.put(drr[i].name, i);
        }
        PriorityQueue<Line> pq = new PriorityQueue<>();
        HashMap<String, TreeMap<Integer, Integer>> placeMap = new HashMap<>();
        for(Map.Entry<String, Integer> entry: nameMap.entrySet()) {
            int idx = entry.getValue();
            String place = drr[idx].place;
            int st = drr[idx].st;
            int ed = drr[idx].ed;
            pq.add(new Line(place, st, ed, 1));
            TreeMap<Integer, Integer> pointMap = placeMap.get(place);
            if (pointMap == null) pointMap = new TreeMap<>();
            pointMap.put(st, pointMap.get(st) == null ? 1 : pointMap.get(st) + 1);
            pointMap.put(ed, pointMap.get(ed ) == null ? -1 : pointMap.get(ed) - 1);
            placeMap.put(place, pointMap);
        }

        for(Map.Entry<String, TreeMap<Integer, Integer>> entry: placeMap.entrySet()) {
            String place = entry.getKey();
            TreeMap<Integer, Integer> pointMap = entry.getValue();
            TreeMap<Integer, Integer> lineMap = new TreeMap<>();
            int cnt = 0;
            for(Map.Entry<Integer, Integer> treeMapEntry: pointMap.entrySet()) {
                cnt += treeMapEntry.getValue();
                lineMap.put(treeMapEntry.getKey(), lineMap.get(treeMapEntry.getKey()) == null ? cnt : lineMap.get(treeMapEntry.getKey()) + cnt);
            }
            placeMap.put(place, lineMap);
        }

        for(Map.Entry<String, TreeMap<Integer, Integer>> entry: placeMap.entrySet()) {
            String place = entry.getKey();
            TreeMap<Integer, Integer> lineMap = entry.getValue();
            int prevValue = -1;
            int prev = -1;
            Queue<Line> queue = new ArrayDeque<>();
            for(Map.Entry<Integer, Integer> lineEntry: lineMap.entrySet()) {
                if (prev == -1 && lineEntry.getValue() > 0) {
                    prevValue = lineEntry.getValue();
                    prev= lineEntry.getKey();
                } else if (prev != -1 && lineEntry.getValue() == prevValue) {
                    continue;
                } else if (prev != -1 && lineEntry.getValue() != prevValue) {
                    pq.add(new Line(place, prev, lineEntry.getKey(), prevValue));
                    prevValue = lineEntry.getValue();
                    prev = lineEntry.getKey();
                }
            }
            int lastPoint = lineMap.lastKey();
            pq.add(new Line(place, prev, lastPoint, lineMap.get(lastPoint)));
        }
        Line first = pq.poll();
        System.out.print(first.place+" "+first.st+" "+first.ed);
    }
}