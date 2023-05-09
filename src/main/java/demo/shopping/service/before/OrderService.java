package demo.shopping.service.before;
import javax.servlet.http.HttpSession;

import demo.shopping.po.Order;
import org.springframework.ui.Model;
public interface OrderService {
	Order orderSubmit(Integer id, Double amount);
	int pay(Integer ordersn);
}
