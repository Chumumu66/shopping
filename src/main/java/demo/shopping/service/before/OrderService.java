package demo.shopping.service.before;

import demo.shopping.po.Order;

public interface OrderService {

	Order orderSubmit(Integer id, Double amount);

	int pay(Integer ordersn);

}
