package owner.code.demo.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 海容查看订单信息
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "SimulateDeadlockMethodDto", description = "模拟mysql死锁入参")
public class SimulateDeadlockMethodDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id1非空")
    private Long id1;

    @NotNull(message = "id2非空")
    Long id2;


}
