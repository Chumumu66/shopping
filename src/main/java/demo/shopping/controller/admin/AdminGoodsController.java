package demo.shopping.controller.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.admin.AdminGoodsService;
@Controller
@RequestMapping("/adminGoods")
public class AdminGoodsController extends BaseController{
	@Autowired
	private AdminGoodsService adminGoodsService;
	@RequestMapping("/selectGoods")
	public String selectGoods(Model model, Integer pageCur, String act) {
		return adminGoodsService.selectGoods(model, pageCur, act);
	}

	@RequestMapping("/toAddGoods")
	public String toAddGoods(Model model){
		model.addAttribute("goods", new Goods());
		return "admin/addGoods";
	}

	@RequestMapping("/addGoods")
	public String addGoods(@ModelAttribute Goods goods, HttpServletRequest request, String updateAct){
		return adminGoodsService.addOrUpdateGoods(goods, request, updateAct);
	}

	@RequestMapping("/selectAGoods")
	public String selectAGoods(Model model, Integer id, String act){
		return adminGoodsService.selectAGoods(model, id, act);
	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(Integer ids[], Model model) {
		return adminGoodsService.deleteGoods(ids, model);
	}

	@RequestMapping("/deleteAGoods")
	public String deleteAGoods(Integer id, Model model) {
		return adminGoodsService.deleteAGoods(id, model);
	}
}
