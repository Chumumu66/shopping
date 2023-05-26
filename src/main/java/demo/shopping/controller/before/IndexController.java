package demo.shopping.controller.before;

import demo.shopping.po.Buser;
import demo.shopping.po.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Goods;
import demo.shopping.service.before.IndexService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {

	Logger logger = Logger.getLogger("IndexController");

	@Autowired
	private IndexService indexService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/before")
	public String before(Model model, Goods goods) throws IOException {
		logger.log(Level.INFO,toString("indexController.getPage"));
		model.addAttribute("goodsType", indexService.getGoodsType());
		model.addAttribute("salelist", indexService.getSaleList());
		model.addAttribute("focuslist", indexService.getFocusList());
		model.addAttribute("noticelist", indexService.getNoticeList());
		model.addAttribute("lastedlist", indexService.getLastedList(goods));
		return "before/index";
	}

	@RequestMapping("/toRegister")
	public String toRegister(Model model) throws IOException {
		logger.log(Level.INFO,toString("indexController.getRegisterPage"));
		model.addAttribute("rbuser", new Buser());
		return "before/register";
	}

	@RequestMapping("/toLogin")
	public String toLogin(Model model) throws IOException {
		logger.log(Level.INFO,toString("indexController.getLoginPage"));
		model.addAttribute("lbuser", new Buser());
		return "before/login";
	}

	@RequestMapping("/getGoodsDetail")
	public String goodsDetail(Model model,Integer id) throws IOException {
		logger.log(Level.INFO,toString("indexController.getGoodsDetailPage"));
		Goods goods = indexService.goodsDetail(id);
		model.addAttribute("goods", goods);
		return "before/goodsdetail";
	}

	@RequestMapping("/selectANotice")
	public String selectANotice(Model model,Integer id) throws IOException {
		Notice notice = indexService.selectANotice(id);
		model.addAttribute("notice", notice);
		logger.log(Level.INFO,toString("indexController.getGoodsDetailPage"));
		return "admin/noticeDetail";
	}

	@RequestMapping("/search")
	public String search(Model model,String mykey) throws IOException {
		logger.log(Level.INFO,toString("indexController.getSearchResultPage"));
		List<Goods> goodsList = indexService.search(mykey);
		model.addAttribute("searchlist", goodsList);
		return "before/searchResult";
	}

	@RequestMapping("/head")
	public String head() {
		return "before/head.html";
	}

}
