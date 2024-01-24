package owner.code.demo.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * InstantiationAwareBeanPostProcessor：bean执行构造器前后执行
 * postProcessBeforeInstantiation:
 * 实例化前执行，该方法可以返回一个该bean类型的对象，或对该bean的代理对象，如果这样，后续的bean的实例化和初始化将不再执行，只执行后续after方法
 * postProcessAfterInstantiation:
 * 实例化后执行，此时还没有装配属性，如果返回false，将跳过后续的属性装配动作postProcessProperties及bean的属性赋值操作
 * postProcessProperties:
 * 装配属性前，该方法传入装配所需要的PropertyValues和BeanName，该方法返回的PropertyValues将最终装配到bean对象中
 *
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("myBean")) {
            System.out.println("myBean ~ InstantiationAwareBeanPostProcessor ~ postProcessBeforeInstantiation");
        }
        return null;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myBean")) {
            System.out.println("myBean ~ InstantiationAwareBeanPostProcessor ~ postProcessAfterInstantiation");
        }
        return true;
    }

    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        if (beanName.equals("myBean")) {
            System.out.println("myBean ~ InstantiationAwareBeanPostProcessor ~ postProcessProperties");
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
            mutablePropertyValues.addPropertyValue("name", null);
            pvs = mutablePropertyValues;
        }
        return pvs;
    }
}
