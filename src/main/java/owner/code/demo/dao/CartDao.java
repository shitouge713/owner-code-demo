package owner.code.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CartDao extends BaseMapper<Cart> {

    int reduceKu(@Param("param") UpdateCartParams param);
}
