package demo.shopping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.service.admin.AdminOrderService;
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

	@RequestMapping("/orderInfo")
	public String orderInfo(Model model) {
		logger.log(Level.INFO,"获取订单信息");
		List<Map<String,Object>> list =  adminOrderService.orderInfo(model);
		model.addAttribute("orderList", list);
		return "admin/orderManager";
	}

	@RequestMapping("/deleteorderManager")
	public String deleteorderManager(Model model, Integer id) {
		logger.log(Level.INFO,"删除订单信息");
		boolean flag =  adminOrderService.deleteorderManager(id);
		if(flag == true){
			model.addAttribute("删除成功");
		}else {
			model.addAttribute("删除失败");
		}
		return "forward:/adminOrder/orderInfo";
	}
}
