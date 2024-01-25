package owner.code.demo.cglib;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<SingService> {

    public SingService getObject() throws Exception {
        return new SingService();
    }

    public Class<?> getObjectType() {
        return SingService.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
