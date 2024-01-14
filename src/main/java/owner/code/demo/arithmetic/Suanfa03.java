package owner.code.demo.arithmetic;

import java.util.Arrays;

/**
 * 冒泡排序  时间复杂度  O（n*n）   空间复杂度O（1）
 * 需要排序n-1趟，每趟需要排n-i次，i表示行数
 * 考虑用双重for循环设计，第一层表示所要排的趟数，第二层表示次数
 * 要注意的点：每趟要排序的起始元素是i+1，i表示元素下标
 * 比较判断的元素和基准数，如果比较成功，对换元素，每趟比较完成之后，会将最大值放入最后一位
 */
public class Suanfa03 {
    public static void main(String[] args) {
        int [] arr = new int []{12,4,432,11,3,67,45,89};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[j]<arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
