package owner.code.demo.arithmetic;

import java.util.Arrays;

/**
 * 二分查找
 */
public class Suanfa05 {
    public static void main(String[] args) {
        int[] num = {2, 4, 3, 5, 6, 7, 8, 14, 23, 34};
        Arrays.sort(num);
        System.out.println(Arrays.toString(num));
        System.out.println(method(num,23));

    }

    public static int method(int[] num, int target) {
       int left = 0;
       int right = num.length -1;
       while(left<=right){
          int mid = left +(right-left)/2;
          if(num[mid]>target){
              right = mid -1;
          }else if(num[mid]<target){
              left = mid +1;
          }else{
              return num[mid];
          }
       }
       return -1;
    }
}
