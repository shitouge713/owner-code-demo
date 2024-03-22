package owner.code.demo.deadlock;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import owner.code.demo.dto.SimulateDeadlockMethodDto;
import owner.code.demo.service.CartService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/cart/deadlock")
public class DeadLockController {

    @Autowired
    private CartService cartService;

    /**
     * 异常信息：
     * Deadlock found when trying to get lock; try restarting transaction
     * @param dto
     * @return
     */
    @PostMapping("/simulateDeadlockMethod1")
    @ApiOperation("模拟死锁方法1")
    public String simulateDeadlockMethod1(@RequestBody @Valid SimulateDeadlockMethodDto dto) {
        cartService.simulateDeadlockMethod1(dto.getId1(), dto.getId2());
        return "success";
    }

    @PostMapping("/simulateDeadlockMethod2")
    @ApiOperation("模拟死锁方法2")
    public String simulateDeadlockMethod2(@RequestBody @Valid SimulateDeadlockMethodDto dto) {
        cartService.simulateDeadlockMethod2(dto.getId1(), dto.getId2());
        return "success";
    }

    /**
     * 这种死锁，会导致两个线程一直在等待，直到获取锁超时（默认50s）
     * 异常信息：
     * Lock wait timeout exceeded; try restarting transaction
     * @param dto
     * @return
     */
    @PostMapping("/searchByIInShareMode")
    @ApiOperation("模拟死锁方法3")
    public String searchByIInShareMode(@RequestBody @Valid SimulateDeadlockMethodDto dto) {
        cartService.searchByIInShareMode(dto.getId1());
        return "success";
    }


}
