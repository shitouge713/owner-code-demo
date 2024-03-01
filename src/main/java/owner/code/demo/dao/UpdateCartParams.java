package owner.code.demo.dao;

import lombok.Data;

/**
 * 测试高并发场景下的库存扣减sql及性能
 */
@Data
public class UpdateCartParams {
    private Long id;
    private Integer kcSize;
}
