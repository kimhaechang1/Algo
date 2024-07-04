import java.util.*;
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int t1= 0;
        int t2= 0;
        int [] arr = new int[m+n];
        int p = 0;
        while(t1 < m && t2 < n){
            if(nums1[t1] < nums2[t2]){
                arr[p++] = nums1[t1++];
            }else if(nums1[t1] > nums2[t2]){
                arr[p++] = nums2[t2++];
            }else{
                arr[p++] = nums1[t1++];
                arr[p++] = nums2[t2++];
            }
        }   

        while(t1 < m){
            arr[p++] = nums1[t1++];
        }

        while(t2 < n){
            arr[p++] = nums2[t2++];
        }
        
        for(int i = 0;i<nums1.length;i++){
            nums1[i] = arr[i];
        }
    }
}