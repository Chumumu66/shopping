package demo.shopping.controller.admin;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser) {
		return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login(@Valid @ModelAttribute Auser auser, Model model, HttpSession session) {
		return adminService.login(auser, model, session);
	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) {
		session.invalidate();
		return "admin/login";
	}

	@ExceptionHandler
	public ModelAndView handleLoginError(Exception ex){
		return new ModelAndView("error/error");
	}
}
