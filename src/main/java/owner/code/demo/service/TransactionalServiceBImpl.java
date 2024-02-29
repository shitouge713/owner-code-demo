package owner.code.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.code.demo.dao.Cart1;
import owner.code.demo.dao.CartDomain1;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Slf4j
@Service
public class TransactionalServiceBImpl {

    @Autowired
    private CartDomain1 cartDomain1;

    @Transactional(rollbackFor = Exception.class,propagation = REQUIRES_NEW)
    public void asyncMethod() throws Exception {
        try {
            System.out.println("asyncMethod，线程名称：" + Thread.currentThread().getName());
            Cart1 c = new Cart1();
            c.setSkuId("12132");
            c.setQuantity(2);
            c.setUserId("11111");
            cartDomain1.save(c);
            //制造一个数组下标越界的异常.判断事务是否回滚
            /*String[] arr = new String[2];
            arr[4] = "5";*/
            System.out.println("B插入完成");
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new Exception(e);
        }
    }

}
