package owner.code.demo.arithmetic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * 给定一个正整数,找到由相同数字组成的下一个刚好比该数字大的数字
 */
public class String05 {
        public static int getNextBigger(int num) {

            int numLength = Integer.toString(num).length();
            //如果是负数或者数字位数为1，那么直接返回-1，代表不存在需要找的数字
            if (num <= 0 || numLength <= 1) {
                return -1;
            }
            int[] nums = new int[numLength];
            for (int i = numLength - 1; i >= 0; i--) {
                int tmp = num % 10;
                nums[i] = tmp;
                num = num / 10;
            }
            //找到给定数字中，从右至左没有按从大到小顺序排列的一段数字。假如给定数字式76985432，那么没有按顺序排列的一段数字就是，6985432。
            //对找到的这段数字重新排列。先把给定数字变成两个列表，[7]与[6,9,8,5,4,3,2]，
            // 然后对后面列表中的这些数字，取比6大一位的数字，即8。最后，把除8以外的数字，按从小到大得顺序重新排列。
            List<Integer> list = new ArrayList<>();
            //transition用来保存出现转折的数字，假如给定数字式76985432，没有按顺序排列的一段数字就是，6985432，转折的数字为6
            int transition = -1;
            for (int i = numLength - 1; i >= 0; i--) {
                if (i == 0) {
                    return -1;
                }
                list.add(nums[i]);
                if (nums[i] > nums[i - 1]) {
                    transition = nums[i - 1];
                    break;
                }
            }
            list.sort(Comparator.comparing(Integer::intValue));
            String resultStr = "";
            //将转折数字之前的数先整合起来
            for (int i = 0; i < numLength - list.size() - 1; i++) {
                resultStr += nums[i];
            }
            //找出比转折数大一位的数字，整合进resultStr
            for (int i = 0; i < list.size(); i++) {
                if (transition < list.get(i)) {
                    resultStr += list.get(i);
                    list.remove(i);
                    break;
                }
            }
            //将转折数字加入list并进行排序，然后整合进resultStr
            list.add(transition);
            list.sort(Comparator.comparing(Integer::intValue));
            for (int i = 0; i < list.size(); i++) {
                resultStr += list.get(i);
            }
            return Integer.parseInt(resultStr);
        }
        public static void main(String[] args) {
            System.out.println(getNextBigger(76985432));
            System.out.println(getNextBigger(213));
            System.out.println(getNextBigger(321));
            System.out.println(getNextBigger(786871));
        }
    }

