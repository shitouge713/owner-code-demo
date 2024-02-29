package owner.code.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.code.demo.dao.Cart;
import owner.code.demo.dao.CartDomain;

@Service
public class AsyncDemoServiceImpl {

    @Autowired
    private CartDomain cartDomain;

    @Async("myselfExecutor")
    @Transactional
    public void asyncMethod() {
        System.out.println("asyncMethod，线程名称：" + Thread.currentThread().getName());
        Cart c = new Cart();
        c.setSkuId("12132");
        c.setQuantity(2);
        c.setUserId("2222222222222");
        cartDomain.save(c);
        Integer a = 10;
        Integer b = 0;
        Object result = a / b;
    }

    @Async("myselfExecutor")
    public void asyncMethod2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("asyncMethod2，线程名称：" + Thread.currentThread().getName());
    }


}
