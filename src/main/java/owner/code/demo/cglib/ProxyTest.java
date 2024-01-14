package owner.code.demo.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        //invoke 代表的是执行代理对象的方法
        //method：代表目标对象的方法字节码对象
        //args:代表目标对象的响应的方法的参数
        Target target = new Target();
        InvocationHandler handler = new MyInvocationHandler<>(target);
        AnimalInterface newProxyInstance = (AnimalInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces()
                ,handler);
        //method1调用的实际是代理对象$Proxy的method1方法，$Proxy的method1的方法中会调用invocationHandler的invoke方法
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //或者 启动参数中配置：-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
        //String method1 = newProxyInstance.method1();
        //System.out.println(method1);
        newProxyInstance.run();

    }
}
