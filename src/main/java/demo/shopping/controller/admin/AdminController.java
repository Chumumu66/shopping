package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;

import demo.shopping.dao.AdminTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminTypeDao adminTypeDao;

	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser, HttpSession session) {
		return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login(@ModelAttribute Auser auser, Model model, HttpSession session) {
		Auser auser1 =  adminService.login(auser);
		if(auser1 != null){
			session.setAttribute("auser", auser);
			session.setAttribute("goodsType", adminTypeDao.selectGoodsType());
			return "admin/main";
		}else{
			model.addAttribute("msg", "登录失败！");
			return "admin/login";
		}

	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}


	@RequestMapping(value = "/mul")
	public int mulParam(int param) {
		return 9/param;
	}

}
