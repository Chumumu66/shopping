package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.CartService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseBeforeController{
	Logger logger=Logger.getLogger("CartController");
	@Autowired
	private CartService cartService;

	@RequestMapping("/focus")
	public String focus(Model model,Integer id, HttpSession session) {
		logger.log(Level.INFO,"获取商品细节页面");
		return cartService.focus(model, id, session);
	}

	@RequestMapping("/putCart")
	public String putCart(Model model,Integer shoppingnum, Integer id, HttpSession session) {
		logger.log(Level.INFO,"加入购物车成功请求");
		return cartService.putCart(model, shoppingnum, id, session);
	}

	@RequestMapping("/selectCart")
	public String selectCart(Model model, HttpSession session) {
		logger.log(Level.INFO,"获取所有商品");
		return cartService.selectCart(model, session);
	}

	@RequestMapping("/deleteAgoods")
	public String deleteAgoods(Integer id,HttpSession session) {
		logger.log(Level.INFO,"删除对应商品");
		return cartService.deleteAgoods(id, session);
	}

	@RequestMapping("/clear")
	public String clear(HttpSession session) {
		logger.log(Level.INFO,"全部取消");
		return cartService.clear(session);
	}

	@RequestMapping("/orderConfirm")
	public String orderConfirm(Model model, HttpSession session) {
		logger.log(Level.INFO,"获取搜索");
		return cartService.orderConfirm(model, session);
	}
}
