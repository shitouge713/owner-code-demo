package owner.code.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.code.demo.dao.CartDomain;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDomain cartDomain;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void simulateDeadlockMethod1(Long id1, Long id2) {
        try {
            cartDomain.updateById(id1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cartDomain.searchById(id2);
        } catch (Exception e) {
            log.error("fatalError,simulateDeadlockMethod1,e:{}", e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void searchByIInShareMode(Long id1) {
        try {
            cartDomain.searchByIInShareMode(id1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cartDomain.updateById(id1);
        } catch (Exception e) {
            log.error("fatalError,simulateDeadlockMethod1,e:{}", e);
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void simulateDeadlockMethod2(Long id1, Long id2) {
        try {
            cartDomain.searchById(id2);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cartDomain.updateById(id1);
        } catch (Exception e) {
            log.error("fatalError,simulateDeadlockMethod2,e:{}", e);
        }
    }
}
