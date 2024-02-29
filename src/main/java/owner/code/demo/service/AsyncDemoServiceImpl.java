package owner.code.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.code.demo.dao.Cart;
import owner.code.demo.dao.CartDomain;

@Slf4j
@Service
public class AsyncDemoServiceImpl {

    @Autowired
    private CartDomain cartDomain;

    //@Async("myselfExecutor")
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void asyncMethod() throws Exception {
        try {
            System.out.println("asyncMethod，线程名称：" + Thread.currentThread().getName());
            Cart c = new Cart();
            c.setSkuId("12132");
            c.setQuantity(2);
            c.setUserId("2222222222222");
            cartDomain.save(c);
            //制造一个数组下标越界的异常.判断事务是否回滚
            String[] arr = new String[2];
            arr[4] = "5";
            System.out.println("插入完成");
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new Exception(e);
        }
    }

    @Async("myselfExecutor")
    public void asyncMethod2() {
        try {
            System.out.println("asyncMethod2，线程名称：" + Thread.currentThread().getName());
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
