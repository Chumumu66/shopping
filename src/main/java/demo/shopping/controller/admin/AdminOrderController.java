package demo.shopping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.service.admin.AdminOrderService;
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
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/orderInfo")
	public String orderInfo(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminOrderController.getOrder"));
		List<Map<String,Object>> list =  adminOrderService.orderInfo(model);
		model.addAttribute("orderList", list);
		return "admin/orderManager";
	}

	@RequestMapping("/deleteorderManager")
	public String deleteorderManager(Model model, Integer id) throws IOException {
		logger.log(Level.INFO, toString("adminOrderController.getDelete"));
		boolean flag =  adminOrderService.deleteorderManager(id);
		if(flag == true){
			model.addAttribute(toString("page.deleteSuccess"));
		}else {
			model.addAttribute(toString("page.deleteError"));
		}
		return "forward:/adminOrder/orderInfo";
	}
}
