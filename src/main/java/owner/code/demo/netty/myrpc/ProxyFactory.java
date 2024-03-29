package owner.code.demo.netty.myrpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private static InvocationHandler handler = new InvocationHandler() {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            NetModel netModel = new NetModel();

            Class<?>[] classes = proxy.getClass().getInterfaces();
            String className = classes[0].getName();

            netModel.setInterfaceName(className);
            netModel.setArgs(args);
            netModel.setMethodName(method.getName());

            String [] types = null;
            if(args!=null) {
                types = new String [args.length];
                for (int i = 0; i < types.length; i++) {
                    types[i] = args[i].getClass().getName();
                }
            }
            netModel.setType(types);

            byte[] byteArray = SerializeUtils.serializer(netModel);
            Object send = RPCClient.send(byteArray);
            return send;
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, handler );
    }
}
