package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Buser;
import demo.shopping.service.before.UserService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger("UserController");

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	@RequestMapping("/register")
	public String register(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		logger.log(Level.INFO,"获取注册");
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("codeError", "验证码错误");
			return "before/register";
		}
		int n = userDao.register(buser);
		if(n > 0) {
			return "before/login";
		}else {
			model.addAttribute("msg", "登录错误");
			return "before/register";
		}
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		logger.log(Level.INFO,"获取登陆页面");
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "验证码错误");
			return "before/login";
		}
		Buser ruser = null;
		List<Buser> list = userDao.login(buser);
		if(list.size() > 0) {
			ruser = list.get(0);
		}
		if(ruser != null) {
			session.setAttribute("bruser", ruser);
			model.addAttribute("bruser", ruser);
			System.out.println(ruser.getBemail());
			return "forward:/before";
		}else {
			model.addAttribute("msg", "登录错误");
			return "before/login";
		}
	}

	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		logger.log(Level.INFO,"退出页面");
		session.invalidate();
		return "forward:/before";
	}
}
