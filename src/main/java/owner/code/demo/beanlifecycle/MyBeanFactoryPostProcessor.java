package owner.code.demo.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryPostProcessor：
 * 作用于 BeanDefinition 解析之后，Bean 实例化之前，我们可以在bean定义好之后做修改，从而影响bean的实例化
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor ~ BeanFactoryPostProcessor ~ postProcessBeanFactory");
    }
}
