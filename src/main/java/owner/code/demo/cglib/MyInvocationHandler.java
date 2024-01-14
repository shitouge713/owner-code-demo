package owner.code.demo.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler<T> implements InvocationHandler {

    /**
     * 代理对象，通过泛型的方式设置，可以代理多个对象
     */
    private T t;

    public MyInvocationHandler(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前逻辑");
        Object invoke = method.invoke(t, args);
        System.out.println("代理后逻辑");
        return invoke;
    }
}
