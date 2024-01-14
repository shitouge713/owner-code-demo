package owner.code.demo.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInteceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理前逻辑");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("代理后逻辑");
        return obj;
    }
}
