package demo.shopping.service.before;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.OrderDao;
import demo.shopping.po.Order;
import demo.shopping.util.MyUtil;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order orderSubmit(Integer id, Double amount) {
		Order order = new Order();
		order.setAmount(amount);
		order.setBusertable_id(id);

		int n=orderDao.addOrder(order);
		System.out.println("成功与否="+n);
		Map<String, Object> map = new HashMap<>();
		map.put("ordersn", order.getId());
		map.put("uid", id);
		orderDao.addOrderDetail(map);
		List<Map<String, Object>> list = orderDao.selectGoodsShop(id);
		for (Map<String, Object> map2 : list) {
			orderDao.updateStore(map2);
		}
		orderDao.clear(id);
		return order;
	}

	@Override
	public int pay(Integer ordersn) {
		int flag = orderDao.pay(ordersn);
		return flag;
	}

}
