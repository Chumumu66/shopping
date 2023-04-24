package demo.shopping.controller.admin;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.admin.AdminGoodsService;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/adminGoods")
public class AdminGoodsController extends BaseController{

	@Autowired
	private AdminGoodsService adminGoodsService;

	@RequestMapping("/selectGoods")
	public String selectGoods(Model model, Integer pageCur, String act) {
		int temp = adminGoodsService.getGoodsCount();
		model.addAttribute("totalCount", temp);
		int totalPage = adminGoodsService.getTotalPage(temp);
		model.addAttribute("totalPage", totalPage);
		int pageCur1 = adminGoodsService.getPageCur(temp, pageCur);
		model.addAttribute("pageCur", pageCur1);
		List<Goods> allGoods = adminGoodsService.selectGoods(pageCur1);
		model.addAttribute("allGoods", allGoods);
		if("deleteSelect".equals(act)){
			return "admin/deleteSelectGoods";
		}else if("updateSelect".equals(act)){
			return "admin/updateSelectGoods";
		}else{
			return "admin/selectGoods";
		}
	}

	@RequestMapping("/selectAGoods")
	public String selectAGoods(Model model, Integer id, String act, HttpServletRequest request){
		Goods aGood = adminGoodsService.getAGood(id);
		model.addAttribute("goods", aGood);
		model.addAttribute("goodsType", request.getSession().getAttribute("goodsType"));
		if("updateAgoods".equals(act)){
			return "admin/updateAgoods";
		}
		return "admin/goodsDetail";
	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(Integer ids[], Model model) {
		if(adminGoodsService.deleteGoods(ids, model) == true){
			model.addAttribute("msg", "删除成功！");
		}else{
			model.addAttribute("msg", "删除失败！");
		}
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@RequestMapping("/deleteAGoods")
	public String deleteAGoods(Integer id, Model model) {
		if(adminGoodsService.deleteAGoods(id, model) == true){
			model.addAttribute("msg", "删除成功！");
		}else{
			model.addAttribute("msg", "删除失败！");
		}
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@RequestMapping("/toAddGoods")
	public String toAddGoods(Model model, HttpServletRequest request){
		model.addAttribute("goods", new Goods());
		model.addAttribute("goodsType", request.getSession().getAttribute("goodsType"));
		return "admin/addGoods";
	}

	@RequestMapping("/addGoods")
	public String addGoods(@ModelAttribute Goods goods, HttpServletRequest request, String updateAct){
		return adminGoodsService.addOrUpdateGoods(goods, request, updateAct);
	}

}
