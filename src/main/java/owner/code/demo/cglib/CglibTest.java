package owner.code.demo.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Cglib 代理实例
 */
public class CglibTest {
    public static void main(String[] args) {
        Enhancer en = new Enhancer();
        en.setSuperclass(Target.class);
        en.setCallback(new MyMethodInteceptor());
        Target instance = (Target)en.create();
        instance.method1();
    }
}
