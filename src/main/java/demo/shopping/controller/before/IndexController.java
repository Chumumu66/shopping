package demo.shopping.controller.before;

import demo.shopping.po.Buser;
import demo.shopping.po.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.before.IndexService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {

	Logger logger = Logger.getLogger("IndexController");

	@Autowired
	private IndexService indexService;

	@RequestMapping("/before")
	public String before(Model model, Goods goods) {
		logger.log(Level.INFO,"获取前端页面");
		model.addAttribute("goodsType", indexService.getGoodsType());
		model.addAttribute("salelist", indexService.getSaleList());
		model.addAttribute("focuslist", indexService.getFocusList());
		model.addAttribute("noticelist", indexService.getNoticeList());
		model.addAttribute("lastedlist", indexService.getLastedList(goods));
		return "before/index";
	}

	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		logger.log(Level.INFO,"获取注册页面");
		model.addAttribute("rbuser", new Buser());
		return "before/register";
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		logger.log(Level.INFO,"获取登录页面");
		model.addAttribute("lbuser", new Buser());
		return "before/login.html";
	}

	@RequestMapping("/goodsDetail")
	public String goodsDetail(Model model,Integer id) {
		logger.log(Level.INFO,"获取商品细节页面");
		Goods goods = indexService.goodsDetail(id);
		model.addAttribute("goods", goods);
		return "before/goodsdetail";
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
		List<Goods> goodsList = indexService.search(mykey);
		model.addAttribute("searchlist", goodsList);
		return "before/searchResult";
	}

	@RequestMapping("/head")
	public String head() {
		return "before/head.html";
	}

}
