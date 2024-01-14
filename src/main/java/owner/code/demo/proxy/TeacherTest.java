package owner.code.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TeacherTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Person t = new Teacher();
        InvocationHandler h = new MyInvocationHandler(t);
        Person p = (Person)Proxy.newProxyInstance(t.getClass().getClassLoader(),new Class[]{Person.class},h);
        p.sing();
    }
}
