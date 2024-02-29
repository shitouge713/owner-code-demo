package owner.code.demo.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title CartDomain
 * @Copyright: Copyright (c) 2021
 * @Description:
 * @Auther: zgd
 * @Created on 2022/7/08
 */
@Slf4j
@Component
public class CartDomain extends ServiceImpl<CartDao, Cart> implements IService<Cart> {
    @Autowired
    private CartDao cartDao;


}
