import java.util.*;
class Solution {
    static class Tuple{
        int [] values;
        public Tuple(int [] arr){
            values = arr;
        }
        public String toString(){
            return Arrays.toString(values);
        }
    }
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Tuple[] tuples = new Tuple[data.length];
        for(int i= 0;i<data.length;i++){
            tuples[i] = new Tuple(data[i]);   
        }
        Arrays.sort(tuples, (a, b)->{
            if(a.values[col-1] == b.values[col-1]){
                return b.values[0] - a.values[0];
            }
            return a.values[col-1] - b.values[col-1];
        });
        int total = 0;
        for(int row = row_begin-1;row<=row_end-1;row++){
            int sum = 0;
            for(int c = 0;c < tuples[row].values.length;c++){
                sum += (tuples[row].values[c] % (row+1));
            }
            total ^= sum;
        }
        return total;
    }
}