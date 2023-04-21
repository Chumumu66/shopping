package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Buser;
import demo.shopping.service.before.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger=Logger.getLogger("UserController");
	@Autowired
	private UserService userService;
	@RequestMapping("/register")
	public String register(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		logger.log(Level.INFO,"获取注册");
		return userService.register(buser, model, session, code);
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) {
		logger.log(Level.INFO,"获取登陆页面");
		return userService.login(buser, model, session, code);
	}
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		logger.log(Level.INFO,"退出页面");
		session.invalidate();
		return "forward:/before";
	}
}
