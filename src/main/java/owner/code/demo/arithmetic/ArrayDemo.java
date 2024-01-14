package owner.code.demo.arithmetic;

import java.util.*;


public class ArrayDemo {
    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        array(arrays, 11);
    }

    public static void method12() {
        for (int i = 1; i < 10000000; i++) {
            if (Math.sqrt(i + 100) % 1 == 0) {
                if (Math.sqrt(i + 168) % 1 == 0) {
                    System.out.println(i);
                }
            }
        }
    }

    public void method11(int[] nums, int sum, List<Integer> list) {
        int count = 0;
        for (int i = 1; i < list.size() + 1; i++) {
            for (int j = 1; j < list.size() + 1; j++) {
                for (int k = 1; k < list.size() + 1; k++) {
                    if (i != j && j != k && i != k) {
                        count++;
                        System.out.println(100 * i + 10 * j + k);
                    }
                }
            }
        }
        System.out.println(count);
    }

    public boolean findSum(int[] nums, int sum) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            int n = sum - m;
            for (int j = i; j < nums.length - i; j++) {
                if (nums[j] == n) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 求得数组中两两相加等于某个数的全部记录
     */
    public static void array(int[] arrays, int target) {
        //List<Object> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arrays.length - 1; i++) {
            int a = target - arrays[i];
            if (map.containsKey(a)) {
                System.out.println(Arrays.toString(new int[]{i, map.get(a)}));
            } else {
                map.put(arrays[i], i);
            }
        }
    }

    public static int method1(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < array[mid]) {
                right = mid - 1;
            } else if (target > array[mid]) {
                left = mid + 1;
            } else {
                while ((mid - 1) >= 0 && array[mid - 1] == array[mid]) {
                    mid = mid - 1;
                }
                return mid;
            }
        }
        return -1;
    }

    public static int method2(Integer target) {
        List<Integer> list = new ArrayList<>();
        String result = "";
        if (target > 0) {
            while (target > 0) {
                list.add(target % 10);
                target = target / 10;
            }
        } else {
            while (target < 0) {
                list.add(-target % 10);
                target = target / 10;
            }
            result = "-";
        }
        for (int i = 0; i < list.size(); i++) {
            result += String.valueOf(list.get(i));
        }
        return Integer.valueOf(result);
    }
}
