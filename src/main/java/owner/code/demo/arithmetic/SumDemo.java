package owner.code.demo.arithmetic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数
 * 1、返回他们的数组下标。
 * 2、返回boolean
 */
public class SumDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        System.out.println(Arrays.toString(method2(nums, 18)));
    }

    //时间复杂度O(n)
    public static int[] method2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (hashMap.containsKey(find)) {
                return new int[]{i, hashMap.get(find)};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }

    /**
     * 时间复杂度： O(n*n)
     *
     * @return
     */
    public static int[] method1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int n = nums[j];
                if (m + n == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
