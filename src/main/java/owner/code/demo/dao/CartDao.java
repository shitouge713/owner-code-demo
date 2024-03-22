package owner.code.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface CartDao extends BaseMapper<Cart> {

    int reduceKu(@Param("param") UpdateCartParams param);

    @Select(value = "select * from o_cart where id = #{id} lock in share mode")
    Cart searchByIInShareMode(@Param("id") Long id);

    @Select(value = "select * from o_cart where id = #{id} for update")
    Cart searchById(@Param("id") Long id);

    @Update(value = "update o_cart set quantity = quantity - 1 where id = #{id} and quantity - 1 > 0")
    Integer updateById(@Param("id") Long id);

}
