package owner.code.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.code.demo.dao.Cart;
import owner.code.demo.dao.CartDomain;

/**
 * 1. 默认遇到运行时异常RuntimeException才会回滚，对Checked Exception不回滚，可以通过指定异常类型来调整，如Exception
 * 2. 异常如果在方法内被try catch住，则无法回滚
 * 3. 该注解只能作用在public级别的方法上
 * 4. 调用关系不当引起的事务失效
 *     1、类内部之间调用不生效，AB方法均在一个类中，A没有标记，B标记了，A调用B，B中事务会生效么？【不会生效】
 *     2、AB方法均在一个类中，A有标记，B没有标记，A方法调用B方法，B中的事务会生效么？【会生效】
 *     3、AB方法均在一个类中，A有标记，B有标记，A方法调用B方法，B中的事务会生效么？【会生效，但使用的是A创建的事务】
 * 4. ServiceA类中的A方法，ServiceB类中的B方法，都标记了事务注解，B方法发生异常会影响A回滚么？A方法发生异常会影响B回滚么？
 *    如果B的异常抛到了A方法中，A也向外抛了该异常，则AB都会回滚
 *    如果只是A发生了异常，B没有发生异常，因为A,B属于同一个事务，所以都会回滚，如果B不想回滚，需要调整传播机制
 */
@Slf4j
@Service
public class TransactionalServiceAImpl {

    @Autowired
    private CartDomain cartDomain;

    @Autowired
    private TransactionalServiceBImpl transactionalServiceBImpl;

    @Transactional(rollbackFor=Exception.class)
    public void asyncMethod3() throws Exception {
        try {
            System.out.println("asyncMethod，线程名称：" + Thread.currentThread().getName());
            Cart c = new Cart();
            c.setSkuId("12132");
            c.setQuantity(2);
            c.setUserId("333333333333");
            cartDomain.save(c);
            transactionalServiceBImpl.asyncMethod();
            //制造一个数组下标越界的异常.判断事务是否回滚
            String[] arr = new String[2];
            arr[4] = "5";
            System.out.println("A插入完成");
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new Exception(e);
        }
    }

}
