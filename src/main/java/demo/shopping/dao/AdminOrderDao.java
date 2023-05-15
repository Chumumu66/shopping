package demo.shopping.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("adminOrderDao")
@Mapper
public interface AdminOrderDao {

	List<Map<String,Object>> orderInfo();

	int deleteOrderDetail(Integer id);

	int deleteOrderBase(Integer id);

}
