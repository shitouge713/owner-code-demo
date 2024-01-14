package owner.code.demo.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找字符串中不含有重复字符的最长字串
 * 方案1：时间复杂度O（n）
 * 滑动窗口
 * hashMap中存放滑动窗口tail，key为实际字符，value为字符在字符串中的位置
 * 滑动窗口起始都是0，左闭右开
 * tail每移动一个位置
 *      1、判断新的位置的元素在前面的字符串中是否存在
 *      2、如果存在，将滑动窗口head移动到重复字符左边的下一位，即map中key为字符的value值
 *          1.2、结果长度+1,比较和原长度的大小，取最大值
 *          1.3、将元素放入到map中,key为char，value为char在字符串中的位置
 * @return
 */
public class String02 {
    public static void main(String[] args) {
        String a = "abacqada";
        method01(a);
    }
    public static void  method01(String s) {
        int ans = 0;
        int number = 0;
        Map<Character,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(hashMap.containsKey(ch)){
                number = hashMap.get(ch);
            }
            ans = Math.max(ans,i-number);
            hashMap.put(ch,i);
        }
        System.out.println(ans);
    }
}
