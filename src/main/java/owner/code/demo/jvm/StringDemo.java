package owner.code.demo.jvm;

/**
 * 常量池测试
 */
public class StringDemo {
    public static void main(String[] args) {
        StringDemo demo = new StringDemo();
        demo.stringMethod();
    }

    /**
     * s1 == s4：false,原因s4是由两个变量相加而成，编译器无法将s4理解成常量
     * s1 == s5：true,原因s5在编译器可以认为是常量
     */
    public void stringMethod() {
        String s1 = "Hello";
        String s2 = "He";
        String s3 = "llo";
        String s4 = s2 + s3;
        String s5 = "He" + "llo";
        String s6 = "He" + s3;
        String s7 = new String("Hello");
        System.out.println(s1 == s4);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false
        System.out.println(s1 == s7);//false
    }

    /**
     * 整型变量只有 -128~127 在常量池中
     */
    public void integerMethod() {
        Integer s1 = 126;
        Integer s2 = 126;
        Integer s3 = 129;
        Integer s4 = 129;
        System.out.println(s1 == s2);//true
        System.out.println(s3 == s4);//false
    }
}
