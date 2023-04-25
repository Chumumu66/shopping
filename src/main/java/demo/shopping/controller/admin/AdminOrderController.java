package demo.shopping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminOrderService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController extends BaseController{

	@Autowired
	private AdminOrderService adminOrderService;

	@RequestMapping("/orderInfo")
	public String orderInfo(Model model) {
		List<Map<String,Object>> list =  adminOrderService.orderInfo(model);
		model.addAttribute("orderList", list);
		return "admin/orderManager";
	}

	@RequestMapping("/deleteorderManager")
	public String deleteorderManager(Model model, Integer id) {
		boolean flag =  adminOrderService.deleteorderManager(id);
		if(flag == true){
			model.addAttribute("删除成功");
		}else {
			model.addAttribute("删除失败");
		}
		return "forward:/adminOrder/orderInfo";
	}
}
