package owner.code.demo.arithmetic;

import java.util.Arrays;

/**
 * 选择排序
 * 同一个数组中选择最大（小）的元素的下标，放在数组的起始位，
 * 然后从第二位开始选择最大（小）的放在数组的第二位
 */
public class Suanfa04 {
    public static void main(String[] args) {
        int [] arr = new int []{12,4,432,11,3,67,45,89};
        for (int i = 0; i <arr.length ; i++) {
            int find = i;
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[j]< arr[find]){
                    find = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[find];
            arr[find] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
