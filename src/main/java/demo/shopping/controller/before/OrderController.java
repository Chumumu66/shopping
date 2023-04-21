package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.OrderService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseBeforeController{
	Logger logger=Logger.getLogger("OrderController");
	@Autowired
	private OrderService orderService;

	@RequestMapping("/orderSubmit")
	public String orderSubmit(Model model, HttpSession session,Double amount) {
		logger.log(Level.INFO,"获取请求提交");
		return orderService.orderSubmit(model, session, amount);
	}

	@RequestMapping("/pay")
	public String pay(Integer ordersn) {
		logger.log(Level.INFO,"获取付款");
		return orderService.pay(ordersn);
	}
}
