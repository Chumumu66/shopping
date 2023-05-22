package demo.shopping.controller.admin;

import javax.servlet.http.HttpSession;
import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.service.admin.AdminTypeService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/adminType")
public class AdminTypeController extends BaseController {

	Logger logger = Logger.getLogger("AdminTypeController");

	@Autowired
	private AdminTypeService adminTypeService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/toAddType")
	public String toAddType(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminTypeController.getAddType"));
		List<GoodsType> goodsTypeList = adminTypeService.toAddType();
		model.addAttribute("allTypes", goodsTypeList);
		return "admin/addType";
	}

	@RequestMapping("/addType")
	public String addType(String typename,Model model,HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("adminTypeController.addType"));
		int flag = adminTypeService.addType(typename);
		if(flag != 0){
			session.setAttribute("goodsType", adminTypeService.selectGoodsType());
		}else{
			model.addAttribute("msg", toString("page.addSuccess"));
		}
		return "forward:/adminType/toAddType";
	}

	@RequestMapping("/toDeleteType")
	public String toDeleteType(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminTypeController.getDeleteType"));
		List<GoodsType> goodsTypeList = adminTypeService.toDeleteType();
		model.addAttribute("allTypes", goodsTypeList);
		return "admin/deleteType";
	}

	@RequestMapping("/deleteType")
	public String deleteType(Integer id,Model model) throws IOException {
		logger.log(Level.INFO, toString("adminTypeController.deleteType"));
		boolean flag = adminTypeService.deleteType(id);
		if(flag == true){
			model.addAttribute("msg", toString("page.deleteSuccess"));
		}else{
			model.addAttribute("msg", toString("page.deleteError"));
		}
		return "forward:/adminType/toDeleteType";
	}
	
}
