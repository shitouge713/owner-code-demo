package owner.code.demo.cglib;

import java.lang.reflect.Proxy;

public class ProxyTest2 {
    public static void main(String[] args) {
        Target target = new Target();
        TargetInterface newProxyInstance = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    //反射知识点
                    Object invoke = method.invoke(target, args1);
                    return invoke;
                });
        String method1 = newProxyInstance.method1();
        newProxyInstance.method2();
        int method3 = newProxyInstance.method3(100);
        System.out.println(method1);
        System.out.println(method3);
    }
}
