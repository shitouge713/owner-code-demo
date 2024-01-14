package owner.code.demo.arithmetic;

/**
 * 实现字符串的反转
 * 方案1：StringBuffer的reverse方法
 * 方案2：转换成char数组，for循环倒序加入到StringBuffer中
 */
public class String01 {
    public static void main(String[] args) {
        method02();
    }
    public static void method01(){
        String str = "abcdef";
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = chars.length; i >0 ; i--) {
            sb.append(chars[i]);
        }
        System.out.println(sb.toString());
    }

    public static void method02(){
        String str = "abcdef";
        StringBuilder builder = new StringBuilder(str);
        System.out.println(builder.reverse().toString());
    }

}
