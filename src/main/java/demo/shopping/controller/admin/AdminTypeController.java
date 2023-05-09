package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;

import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminTypeService;

import java.util.List;

@Controller
@RequestMapping("/adminType")
public class AdminTypeController extends BaseController{

	@Autowired
	private AdminTypeService adminTypeService;

	@Autowired
	private AdminTypeDao adminTypeDao;

	@RequestMapping("/toAddType")
	public String toAddType(Model model) {
		List<GoodsType> goodsTypeList = adminTypeService.toAddType();
		model.addAttribute("allTypes", goodsTypeList);
		return "admin/addType";
	}

	@RequestMapping("/addType")
	public String addType(String typename,Model model,HttpSession session) {
		int flag = adminTypeService.addType(typename);
		if(flag != 0){
			session.setAttribute("goodsType", adminTypeDao.selectGoodsType());
		}else{
			model.addAttribute("msg", "添加失败！");
		}
		return "forward:/adminType/toAddType";
	}

	@RequestMapping("/toDeleteType")
	public String toDeleteType(Model model) {
		List<GoodsType> goodsTypeList = adminTypeService.toDeleteType();
		model.addAttribute("allTypes", goodsTypeList);
		return "admin/deleteType";
	}

	@RequestMapping("/deleteType")
	public String deleteType(Integer id,Model model) {
		boolean flag = adminTypeService.deleteType(id);
		if(flag == true){
			model.addAttribute("msg", "删除成功！");
		}else{
			model.addAttribute("msg", "删除失败！");
		}
		return "forward:/adminType/toDeleteType";
	}
	
}
