package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.dao.CartDao;
import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.before.CartService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
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

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/focus")
	public String focus(Model model,Integer id, HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("cartController.getGoodsDetail"));
		Map<String, Object> map = cartService.focus(id, MyUtil.getUserId(session));
		List<Map<String, Object>> mapList = cartService.isFocus(map);

		if(mapList.size() > 0) {
			model.addAttribute("msg", toString("cartController.haveFocus"));
		}else {
			int n = cartService.focus(map);
			if(n > 0)
				model.addAttribute("msg", toString("cartController.focusSuccess"));
			else
				model.addAttribute("msg", toString("cartController.focusError"));
		}
		return "forward:/goodsDetail?id=" + id;
	}

	@RequestMapping("/putCart")
	public String putCart(Model model,Integer shoppingnum, Integer id, HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("cartController.addCart"));
		Map<String, Object> map = cartService.putCart(shoppingnum, id, MyUtil.getUserId(session));
		List<Map<String, Object>> list = cartService.isPutCart(map);
		if(list.size() > 0){
			cartService.updateCart(map);
		} else{
			cartService.putCart(map);
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/selectCart")
	public String selectCart(Model model, HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("cartController.getAllGoods"));
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
	public String deleteAgoods(Integer id, HttpSession session, Model model) throws IOException {
		logger.log(Level.INFO, toString("cartController.deleteGoods"));
		//System.out.println(111);
		int flag = cartService.deleteAgoods(id, MyUtil.getUserId(session));
		if(flag == 0){
			model.addAttribute("msg", toString("page.deleteError"));
		}else{
			model.addAttribute("msg", toString("page.deleteSuccess"));
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/clear")
	public String clear(HttpSession session, Model model) throws IOException {
		logger.log(Level.INFO, toString("cartController.cancelAll"));
		int flag = cartService.clear(MyUtil.getUserId(session));
		if(flag == 0){
			model.addAttribute("msg", toString("cartController.clearError"));
		}else{
			model.addAttribute("msg", toString("cartController.clearSuccess"));
		}
		return "forward:/cart/selectCart";
	}

	@RequestMapping("/orderConfirm")
	public String orderConfirm(Model model, HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("cartController.getSearch"));
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
