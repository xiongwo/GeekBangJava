package witcher.cirilla.jdbc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderCartMapper {

    @Select("select * from order_cart")
    List<Map<String, Object>> getOrderCartFromMaster();

    @Datasource(name = "slave_1")
    @Select("select * from order_cart")
    List<Map<String, Object>> getOrderCartFromSlave();

}
