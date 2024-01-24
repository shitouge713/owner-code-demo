package owner.code.demo.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * postProcessBeforeInitialization:
 * 属性注入后，初始化前执行的方法
 * postProcessAfterInitialization：
 * bean初始化后执行的方法
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myBean")) {
            System.out.println("myBean ~ BeanPostProcessor ~ postProcessBeforeInitialization");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myBean")) {
            System.out.println("myBean ~ BeanPostProcessor ~ postProcessAfterInitialization");
        }
        return bean;
    }
}
