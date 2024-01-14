package owner.code.demo.arithmetic;


/**
 * 寻找字符串中的最大回文
 *
 * 寻找字符串中的最大回文思路
 * 方案1：暴力分析法，遍历所有的字符串，找到最大的字符串
 * 1、滑动窗口[i,j）从0,len-1开始，如果有符合要求的将字符串赋给临时回文，跳出，没有符合要求的j向前近一位
 * 2、下一次循环中，i向前近一位，判断新获取到的临时回文和取到的最大回文比较，选择长度长的回文
 * 时间复杂度：两次for循环+每个回文逻辑判断 = O（n*n*n）
 * @return
 */
public class String04 {
    public static void main(String[] args) {
        System.out.println(method02("aaaababacababaereraaa"));
    }

    /**
     *方案二 时间复杂度  n*n
     * 从第1个元素（下标0）开始，一直比较到最后一个元素，共需要比较n+n-1 = 2n-1次
     * 1、依次比较本元素i，i+1
     *            i-1，i+2
     *            i-n >=0 0,i+n+1，存在不相等不再继续比较,取出最大值
     * 2、依次比较本元素i-1,i+1
     *                  i-2,i+2
     *                   i-n=0,i+n<len 存在不相等不再继续比较,取出最大值
     * 等1和2都不相等时，取出1和2中最大值字符串
     * 待所有元素都查询时，取出
     *
     */
    public static String method01(String s) {
        int len = s.length();
        String str = "";
        for (int i = 0; i <len ; i++) {
            int n = 1;
            String mid = "";
            String str1 = "";
            String str2 = "";
            Boolean  flag1 = true;
            Boolean  flag2 = true;
            while(i-n>=0&&i+n<len){
                if(flag1){
                    if(compare(s.charAt(i-n),s.charAt(i+n))){
                        str1 = s.substring(i-n,i+n+1);
                        flag1 = true;
                    }else{
                        flag1 = false;
                    }
                }
                if(flag2){
                    if(compare(s.charAt(i-n+1),s.charAt(i+n))){
                        flag2 = true;
                        str2 = s.substring(i-n+1,i+n+1);
                    }else{
                        flag2 = false;
                    }
                }
                if(!flag1&&!flag2){
                    mid = str1.length()>str2.length() ?str1:str2;
                    break;
                }
                n++;
            }
            str = mid.length()>str.length() ?mid:str;
        }
        return str;
    }
    public static boolean compare(Character a,Character b){
        return a==b;
    }

    public static String method02(String str){
        int len = str.length();
        String msg = "";
        String mid = "";
        int l = 0;
        boolean flag = false;
        for (int i = 0; i <len-1 ; i++) {
            for (int j = len-1; j >i+1 ; j--) {
                String m = str.substring(i,j+1);
                if(method(m)){
                    mid = m;
                    break;
                }
            }
            msg = mid.length()>msg.length() ? mid:msg;
        }
        return msg;
    }
    /**
     * 方案1：从两边向中间扫
     *      1、如果存在不同的跳出
     *      2、当head的位置大于等于tail的位置时跳出
     *      3、满足条件，head++，tail--
     * 判断字符串是否为空
     * 判断字符串长度>不小于1
     * @param str
     * @return
     */
    public static boolean method(String str){
        char [] c = str.toCharArray();
        int head = 0;
        int tail = str.length() -1;
        if(str.length()<1){
            return false;
        }
        boolean flag = false;
        while(head<tail){
            if(c[head++]!=c[tail--]){
               flag = false;
               break;
            }
            flag = true;
        }
        return flag;
    }

}
