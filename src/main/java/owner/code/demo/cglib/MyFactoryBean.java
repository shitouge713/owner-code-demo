package owner.code.demo.cglib;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("myFactoryBean")
public class MyFactoryBean implements FactoryBean {

    public Object getObject() throws Exception {
        return new SingService();
    }

    public Class<?> getObjectType() {
        return SingService.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
