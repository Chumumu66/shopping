package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.dao.CartDao;
import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.CartService;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseBeforeController {

	Logger logger = Logger.getLogger("CartController");

	@Autowired
	private CartService cartService;

	@Autowired
	private CartDao cartDao;

	@RequestMapping("/focus")
	public String focus(Model model,Integer id, HttpSession session) {
		logger.log(Level.INFO,"获取商品细节页面");
		Map<String, Object> map = cartService.focus(id, MyUtil.getUserId(session));
		List<Map<String, Object>> mapList = cartDao.isFocus(map);

		if(mapList.size() > 0) {
			model.addAttribute("msg", "已关注！");
		}else {
			int n = cartDao.focus(map);
			if(n > 0)
				model.addAttribute("msg", "关注成功！");
			else
				model.addAttribute("msg", "关注失败！");
		}
		return "forward:/goodsDetail?id=" + id;
	}

	@RequestMapping("/putCart")
	public String putCart(Model model,Integer shoppingnum, Integer id, HttpSession session) {
		logger.log(Level.INFO,"加入购物车成功请求");
		Map<String, Object> map = cartService.putCart(shoppingnum, id, MyUtil.getUserId(session));
		List<Map<String, Object>> list = cartDao.isPutCart(map);
		if(list.size() > 0){
			cartDao.updateCart(map);
		} else{
			cartDao.putCart(map);
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/selectCart")
	public String selectCart(Model model, HttpSession session) {
		logger.log(Level.INFO,"获取所有商品");
		List<Map<String, Object>> mapList = cartService.selectCart(MyUtil.getUserId(session));
		double sum = 0;
		for (Map<String, Object> map : mapList) {
			sum = sum + (Double)map.get("smallsum");
		}
		model.addAttribute("total", sum);
		model.addAttribute("cartlist", mapList);
		return "before/cart";
	}

	@RequestMapping("/deleteAgoods")
	public String deleteAgoods(Integer id, HttpSession session, Model model) {
		logger.log(Level.INFO,"删除对应商品");
		//System.out.println(111);
		int flag = cartService.deleteAgoods(id, MyUtil.getUserId(session));
		if(flag == 0){
			model.addAttribute("msg", "删除失败！");
		}else{
			model.addAttribute("msg", "删除成功！");
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/clear")
	public String clear(HttpSession session, Model model) {
		logger.log(Level.INFO,"全部取消");
		int flag = cartService.clear(MyUtil.getUserId(session));
		if(flag == 0){
			model.addAttribute("msg", "清空失败！！");
		}else{
			model.addAttribute("msg", "清空成功！");
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/orderConfirm")
	public String orderConfirm(Model model, HttpSession session) {
		logger.log(Level.INFO,"获取搜索");
		List<Map<String, Object>> mapList = cartService.orderConfirm(MyUtil.getUserId(session));
		double sum = 0;
		for (Map<String, Object> map : mapList) {
			sum = sum + (Double)map.get("smallsum");
		}
		model.addAttribute("total", sum);
		model.addAttribute("cartlist", mapList);
		return "before/orderconfirm";
	}
}
