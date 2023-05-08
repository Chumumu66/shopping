package demo.shopping.controller.before;
import javax.servlet.http.HttpSession;

import demo.shopping.po.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.before.IndexService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {

	Logger logger = Logger.getLogger("IndexController");

	@Autowired
	private IndexService indexService;

	@RequestMapping("/before")
	public String before(Model model, Goods goods) {
		model.addAttribute("goodsType", indexService.getGoodsType());
		model.addAttribute("salelist", indexService.getSaleList());
		model.addAttribute("focuslist", indexService.getFocusList());
		model.addAttribute("noticelist", indexService.getNoticeList());
		model.addAttribute("lastedlist", indexService.getLastedList(goods));
		logger.log(Level.INFO,"获取前端页面");
		return "before/index";
	}

	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		logger.log(Level.INFO,"获取注册页面");
		return indexService.toRegister(model);
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		logger.log(Level.INFO,"获取登录页面");
		return indexService.toLogin(model);
	}

	@RequestMapping("/goodsDetail")
	public String goodsDetail(Model model,Integer id) {
		logger.log(Level.INFO,"获取商品细节页面");
		return indexService.goodsDetail(model, id);
	}

	@RequestMapping("/selectANotice")
	public String selectANotice(Model model,Integer id) {
		Notice notice = indexService.selectANotice(id);
		model.addAttribute("notice", notice);
		logger.log(Level.INFO,"获取商品细节页面");
		return "admin/noticeDetail";
	}

	@RequestMapping("/search")
	public String search(Model model,String mykey) {
		logger.log(Level.INFO,"获取搜索结果页面");
		return indexService.search(model, mykey);
	}

	@RequestMapping("/head")
	public String head() {
		return "before/head.html";
	}

}
