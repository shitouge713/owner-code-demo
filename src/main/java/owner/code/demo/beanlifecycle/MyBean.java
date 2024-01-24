package owner.code.demo.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * bean的生命周期
 * BeanNameAware：bean实例化后，属性填充后，能够获取bean的名称
 * BeanFactoryAware：在bean实例化后，将BeanFactory实例注入到bean中，从而可以获取其他bean的实例
 * @PostConstruct：允许开发人员在Bean被构造并且依赖被注入后执行初始化逻辑，初始化时执行的方法
 * InitializingBean：初始化时执行的方法
 * DisposableBean: 销毁时执行
 * bean的实例化：调用bean的构造方法，填充bean属性的过程
 * bean的初始化：对象被创建后，spring做一些额外的操作来准备bean对象
 *
 */
@Component("myBean")
public class MyBean implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    @Autowired
    private MyBeanB name;


    public void setName(MyBeanB name) {
        System.out.println("myBean ~ 属性赋值");
        this.name = name;
    }

    public MyBeanB getName() {
        return name;
    }

    public MyBean() {
        System.out.println("myBean ~ 构造方法");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("myBean ~ BeanNameAware ~ setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("myBean ~ BeanFactoryAware ~ setBeanFactory");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("myBean ~ InitializingBean ~ afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("myBean ~ @PostConstruct");
    }

    @Override
    public void destroy() {
        System.out.println("myBean ~ DisposableBean ~ destroy");
    }

}
