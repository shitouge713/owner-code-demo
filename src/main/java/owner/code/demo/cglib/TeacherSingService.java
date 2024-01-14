package owner.code.demo.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TeacherSingService implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("代理内容");
        return methodProxy.invokeSuper(o,objects);
    }
}
