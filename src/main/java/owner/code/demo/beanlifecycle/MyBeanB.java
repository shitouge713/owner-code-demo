package owner.code.demo.beanlifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanB {

    @Autowired
    private MyBean myBean;


    public MyBeanB() {
        System.out.println("myBeanB ~ 构造方法");
    }

    public void setName(MyBean myBean) {
        System.out.println("myBeanB ~ 属性赋值");
        this.myBean = myBean;
    }

    public MyBean getName() {
        return myBean;
    }
}
