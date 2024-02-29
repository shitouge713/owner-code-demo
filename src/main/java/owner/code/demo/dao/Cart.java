package owner.code.demo.dao;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author zgd
 * @version V1.0
 * @date 2022-07-08
 */
@Data
@EqualsAndHashCode
@TableName("o_cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 业务线 1：到柜 2：到家， 3，x平台
     */
    private Integer bizType;

    /**
     * 商家ID
     */
    private String merchantCode;

    /**
     * shopId， 目前x平台在用
     */
    private String shopId;

    /**
     * skuID
     */
    private String skuId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否有效标识 1：有效 0：无效
     */
    private Boolean valid;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModify;

}
