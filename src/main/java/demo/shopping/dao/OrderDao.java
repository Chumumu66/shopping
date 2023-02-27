package demo.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Order;

@Repository("orderDao")
@Mapper
public interface OrderDao {
	public int addOrder(Order order);
	public int addOrderDetail(Map<String, Object> map);
	public List<Map<String, Object>> selectGoodsShop(Integer uid);
	public int updateStore(Map<String, Object> map);
	public int clear(Integer uid);
	public int pay(Integer ordersn);
}
