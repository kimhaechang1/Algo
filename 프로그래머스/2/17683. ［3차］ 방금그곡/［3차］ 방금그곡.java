import java.util.*;

class Solution {
    static class Data implements Comparable<Data>{
        String music;
        int idx;
        int playTime;
        String title;
        public Data(String m, int i, int pt, String t){
            music = m;
            idx = i;
            playTime = pt;
            title = t;
        }
        public int compareTo(Data o){
            if(playTime == o.playTime){
                return idx - o.idx;
            }
            return o.playTime - playTime;
        }
    }
    static HashMap<String, String> map;
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        map = new HashMap<>();
        map.put("C#", "1");
        map.put("D#", "2");
        map.put("F#", "3");
        map.put("G#", "4");
        map.put("A#", "5");
        map.put("B#", "6");
        map.put("E#", "7");
        // 파싱이후 문자열을 재생시간만큼 이어붙여 완성시키고
        // 문자열이 포함되어잇는지 검사
        PriorityQueue<Data> pq = new PriorityQueue<>();
        m = parsing(m);
        for(int i= 0;i<musicinfos.length;i++){
            String [] musicinfo = musicinfos[i].split(",");
            int pt = getTime(musicinfo[0], musicinfo[1]);
            String music = getInfo(musicinfo[3], pt);
            pq.add(new Data(music, i, pt, musicinfo[2]));
        }
        String find = null;
        while(!pq.isEmpty()){
            Data now = pq.poll();
            if(now.music.contains(m)){
                find = now.title;
                break;
            }
        }
        if(find == null){
            return "(None)";
        }
        return find;
    }
    static int getTime(String start, String end){
        int s = 0;
        int e = 0;
        String [] frags = start.split(":");
        s += (Integer.parseInt(frags[0]) * 60 + Integer.parseInt(frags[1]));
        frags = end.split(":");
        e += (Integer.parseInt(frags[0]) * 60 + Integer.parseInt(frags[1]));
        return e-s;
    }
    static String getInfo(String melody, int pt){
        melody = parsing(melody);
        char [] mds = melody.toCharArray();
        int idx = 0;
        int cnt = 0;
        StringBuilder sb =new StringBuilder();
        while(cnt < pt){
            sb.append(mds[idx]);
            cnt++;
            idx++;
            idx = idx % mds.length;
        }
        return sb.toString();
    }
    static String parsing(String str){
        for(Map.Entry<String, String> entry: map.entrySet()){
            str = str.replaceAll(entry.getKey(), entry.getValue());
        }
        return str;
    }
}