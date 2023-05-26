package demo.shopping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.service.admin.AdminOrderService;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController extends BaseController {

	Logger logger = Logger.getLogger("AdminOrderController");

	@Autowired
	private AdminOrderService adminOrderService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/orderInfo")
	public String orderInfo(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminOrderController.getOrder"));
		List<Map<String,Object>> list =  adminOrderService.orderInfo(model);
		model.addAttribute("orderList", list);
		return "admin/orderManager";
	}

	//异常处理示例代码
	@RequestMapping("/deleteorderManager")
	public String deleteorderManager(Model model, Integer id) throws IOException {
		logger.log(Level.INFO, toString("adminOrderController.getDelete"));
		int count =  adminOrderService.deleteorderManager(id);
		switch (count){
			case 0:
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, toString("adminOrderController.deleteDetail"));
			case 1:
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, toString("adminOrderController.deleteOrder"));
			case 2:
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, toString("adminOrderController.rollBack"));
			default:
				model.addAttribute(toString("page.deleteSuccess"));
		}
		return "forward:/adminOrder/orderInfo";
	}
}
