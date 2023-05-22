package demo.shopping.controller.admin;

import javax.servlet.http.HttpSession;
import demo.shopping.dao.AdminTypeDao;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.po.Auser;
import demo.shopping.service.admin.AdminService;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AdminController {

	Logger logger = Logger.getLogger("AdminController");

	@Autowired
	private AdminService adminService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/admin")
	public String toLogin(@ModelAttribute Auser auser, HttpSession session) throws IOException {
		logger.log(Level.INFO,toString("adminController.login"));
		return "admin/login";
	}

	@RequestMapping("/admin/login")
	public String login(@ModelAttribute Auser auser, Model model, HttpSession session) throws IOException {
		Auser auser1 =  adminService.findAUserByUserNameAndPassword(auser.getAname(), auser.getApwd());
		logger.log(Level.INFO,toString("adminController.loginSuccess"));
		if(auser1 != null){
			session.setAttribute("auser", auser);
			session.setAttribute("goodsType", adminService.selectGoodsType());
			return "admin/main";
		}else{
			model.addAttribute("msg", toString("adminController.loginError"));
			return "admin/login";
		}

	}

	@RequestMapping("/exit")
	public String exit(@ModelAttribute Auser auser,HttpSession session) throws IOException {
		session.invalidate();
		logger.log(Level.INFO, toString("adminController.exit"));
		return "admin/login";
	}


	@RequestMapping(value = "/mul")
	public int mulParam() throws IOException {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, toString("adminController.mul"));
	}

}
