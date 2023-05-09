package demo.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Order;

@Repository("orderDao")
@Mapper
public interface OrderDao {
	int addOrder(Order order);
	int addOrderDetail(Map<String, Object> map);
	List<Map<String, Object>> selectGoodsShop(Integer uid);
	int updateStore(Map<String, Object> map);
	int clear(Integer uid);
	int pay(Integer ordersn);
}
