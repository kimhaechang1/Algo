import java.util.*;

class Solution {
    static class File implements Comparable<File>{
        int idx;
        String HEAD;
        int NUMBER;
        
        public int compareTo(File o){
            if(HEAD.equals(o.HEAD)){
                if(NUMBER == o.NUMBER){
                    return idx - o.idx;
                }
                return NUMBER - o.NUMBER;
            }
            return HEAD.compareTo(o.HEAD);
        }
        
    }
    public String[] solution(String[] files) {
        String[] answer = {};
        // HEAD: 문자로 구성되어있음
        // NUMBER: 최대 5자리 숫자가 올 수 있으며 맨앞에 0이 붙을수도 있음
        // TAIL: 숫자가 다시 나타날 수도 있음
        // 우선 HEAD를 기준으로 사전순 정렬, 이때 대소문자 구분 X
        // HEAD로 구분안될경우 -> NUMBER 숫자로 정렬
        // 이걸로도 구분이 안될 경우 원래 입력의 index로 구분
        File[] fs = new File[files.length];
        for(int i= 0;i<files.length;i++){
            fs[i] = new File();
            fs[i].idx = i;
            int HEADEndPoint = getHEAD(files[i]);
            fs[i].HEAD = files[i].substring(0, HEADEndPoint).toLowerCase();
            String sub = files[i].substring(HEADEndPoint, files[i].length());
            fs[i].NUMBER = getNUMBER(sub);
        }
        Arrays.sort(fs);
        answer = new String[files.length];
        for(int i= 0;i<fs.length;i++){
            answer[i] = files[fs[i].idx];
        }
        return answer;
    }
    static int getHEAD(String file){
        int start = 0;
        int end = 1;
        file = file.toLowerCase();
        while(end < file.length()){
            int ascii = file.charAt(end) - '0';
            if(ascii >= 0 && ascii < 10){
                break;
            }
            end++;
        }
        return end;
    }
    static int getNUMBER(String file){
        int start = 0;
        int end = 1;
        while(end < file.length()){
            int ascii = file.charAt(end) - '0';
            if(ascii >= 0 && ascii < 10){
                end++;
                continue;
            }
            break;
        }
        return Integer.parseInt(file.substring(0, end));
    }
}