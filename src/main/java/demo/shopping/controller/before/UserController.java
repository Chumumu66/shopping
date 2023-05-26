package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Buser;
import demo.shopping.service.before.UserService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger("UserController");

	@Autowired
	private UserService userService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/register")
	public String register(@ModelAttribute Buser buser,Model model, HttpSession session, String code) throws IOException {
		logger.log(Level.INFO, toString("userController.getRegister"));
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("codeError", toString("userController.codeError"));
			return "before/register";
		}
		int n = userService.register(buser);
		if(n > 0) {
			return "before/login";
		}else {
			model.addAttribute("msg", toString("userController.loginError"));
			return "before/register";
		}
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute Buser buser,Model model, HttpSession session, String code) throws IOException {
		logger.log(Level.INFO, toString("userController.getLoginPage"));
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", toString("userController.codeError"));
			return "before/login";
		}
		Buser ruser = userService.login(buser);
		if(ruser != null) {
			session.setAttribute("bruser", ruser);
			model.addAttribute("bruser", ruser);
			System.out.println(ruser.getBemail());
			return "forward:/before";
		}else {
			model.addAttribute("msg", toString("userController.loginError"));
			return "before/login";
		}
	}

	@RequestMapping("/exit")
	public String exit(HttpSession session) throws IOException {
		logger.log(Level.INFO, toString("userController.exitPage"));
		session.invalidate();
		return "forward:/before";
	}
}
