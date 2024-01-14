package owner.code.demo.netty;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class YxMain {
    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);

        Car person = extensionLoader.getExtension("bus");

        person.driver();

        System.out.println(person);
    }
}
