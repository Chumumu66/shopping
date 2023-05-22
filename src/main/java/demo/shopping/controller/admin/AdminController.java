package demo.shopping.controller.admin;

import javax.servlet.http.HttpSession;
import demo.shopping.dao.AdminTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;
import org.springframework.web.server.ResponseStatusException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AdminController {

	Logger logger = Logger.getLogger("AdminController");

	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser, HttpSession session) {
		logger.log(Level.INFO,"获取登录页面");
		return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login(@ModelAttribute Auser auser, Model model, HttpSession session) {
		Auser auser1 =  adminService.findAUserByUserNameAndPassword(auser.getAname(), auser.getApwd());
		logger.log(Level.INFO,"登陆完成");
		if(auser1 != null){
			session.setAttribute("auser", auser);
			session.setAttribute("goodsType", adminService.selectGoodsType());
			return "admin/main";
		}else{
			model.addAttribute("msg", "登陆失败，用户名或密码错误！");
			return "admin/login";
		}

	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		logger.log(Level.INFO,"退出");
		return "admin/login";
	}


	@RequestMapping(value = "/mul")
	public int mulParam() {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "出现错误！请联系管理员说明情况！");
	}

}
