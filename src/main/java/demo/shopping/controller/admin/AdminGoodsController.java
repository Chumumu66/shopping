package demo.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.po.Goods;
import demo.shopping.service.admin.AdminGoodsService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/adminGoods")
public class AdminGoodsController extends BaseController {

	Logger logger = Logger.getLogger("GoodsController");

	@Autowired
	private AdminGoodsService adminGoodsService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/selectGoods")
	public String selectGoods(Model model, Integer pageCur, String act) throws IOException {

		logger.log(Level.INFO, toString("adminGoodsController.getProduct"));
		Map<String, Integer> map = adminGoodsService.getPaginationQuery(pageCur);
		model.addAttribute("totalCount", map.get("totalCount"));
		model.addAttribute("totalPage", map.get("totalPage"));
		model.addAttribute("pageCur", map.get("pageCur"));
		List<Goods> allGoods = adminGoodsService.selectGoods(map.get("pageCur"));
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
	public String selectAGoods(Model model, Integer id, String act, HttpServletRequest request) throws IOException {
		logger.log(Level.INFO,toString("adminGoodsController.getProduct"));
		Goods aGood = adminGoodsService.getAGood(id);
		model.addAttribute("goods", aGood);
		model.addAttribute("goodsType", request.getSession().getAttribute("goodsType"));
		if("updateAgoods".equals(act)){
			return "admin/updateAgoods";
		}
		return "admin/goodsDetail";
	}

	@RequestMapping("/deleteGoods")
	public String deleteGoods(Integer ids[], Model model) throws IOException {
		logger.log(Level.INFO,toString("adminGoodsController.deleteProduct"));
		if(adminGoodsService.deleteGoods(ids, model) == true){
			model.addAttribute("msg", toString("page.deleteSuccess"));
		}else{
			model.addAttribute("msg", toString("page.deleteError"));
		}
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@RequestMapping("/deleteAGoods")
	public String deleteAGoods(Integer id, Model model) throws IOException {
		logger.log(Level.INFO,toString("adminGoodsController.deleteProduct"));
		if(adminGoodsService.deleteAGoods(id, model) == true){
			model.addAttribute("msg", toString("page.deleteSuccess"));
		}else{
			model.addAttribute("msg", toString("page.deleteError"));
		}
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@RequestMapping("/toAddGoods")
	public String toAddGoods(Model model, HttpServletRequest request) throws IOException {
		logger.log(Level.INFO,toString("adminGoodsController.getAddProduct"));
		model.addAttribute("goods", new Goods());
		model.addAttribute("goodsType", request.getSession().getAttribute("goodsType"));
		return "admin/addGoods";
	}

	@RequestMapping("/addGoods")
	public String addGoods(@ModelAttribute Goods goods, HttpServletRequest request, String updateAct) throws IOException {
		logger.log(Level.INFO,toString("adminGoodsController.addProduct"));
		if("update".equals(updateAct)){
			String flag = "update";
			if(adminGoodsService.addOrUpdateGoods(goods, request, flag) == true){
				return "forward:/adminGoods/selectGoods?act=updateSelect";
			}else{
				return "/adminGoods/updateAgoods";
			}
		}else{
			String flag = "add";
			if(adminGoodsService.addOrUpdateGoods(goods, request, flag) == true){
				return "forward:/adminGoods/selectGoods";
			}else{
				return "card/addCard";
			}
		}
	}
}
