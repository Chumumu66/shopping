package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.po.Order;
import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.OrderService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseBeforeController {

	Logger logger = Logger.getLogger("OrderController");

	@Autowired
	private OrderService orderService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/orderSubmit")
	public String orderSubmit(Model model, HttpSession session,Double amount) throws IOException {
		logger.log(Level.INFO,toString("orderController.getPostSubmit"));

		Order order = orderService.orderSubmit(MyUtil.getUserId(session), amount);
		model.addAttribute("ordersn", order.getId());
		return "before/orderdone";
	}

	@RequestMapping("/pay")
	public String pay(Integer ordersn) throws IOException {
		logger.log(Level.INFO,toString("orderController.getPay"));
		int flag = orderService.pay(ordersn);
		return "before/paydone";
	}
}
