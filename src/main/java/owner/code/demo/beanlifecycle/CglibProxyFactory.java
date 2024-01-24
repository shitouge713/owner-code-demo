package owner.code.demo.beanlifecycle;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description CGLIB动态代理
 * CGLIB(Code Generation Library)是一个开源项目
 * 是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与实现Java接口。
 * CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类
 * <p>
 * 可以为没有实现接口的类去做代理，也可以为实现接口的类去做代理。
 * <p>
 * spring采用的是哪一种动态机制:
 * 如果目标对象，有接口，优先使用jdk动态代理
 * 如果目标对象，无接口，使用cglib动态代理。
 * @Author sxl
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024-01-24
 */
public class CglibProxyFactory implements MethodInterceptor {
    //目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //创建代理对象
    public Object createProxy() {
        //1.创建Enhancer
        Enhancer enhancer = new Enhancer();
        //2.传递目标对象Class
        enhancer.setSuperclass(target.getClass());
        //3.设置回调操作（相当于InvocationHandler）
        enhancer.setCallback(this);

        return enhancer.create();
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //在调用目标对象方法前，统一做一些其他操作，即功能增强
        System.out.println("例如：日志操作......");
        return method.invoke(target, args);
    }
}
