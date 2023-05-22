package demo.shopping.controller.admin;

import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.service.admin.AdminUserService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController {

	Logger logger = Logger.getLogger("AdminUserController");

	@Autowired
	private AdminUserService adminUserService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/userInfo")
	public String userInfo(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminUserController.getUser"));
		List<Buser> BUserList = adminUserService.userInfo();
		model.addAttribute("userList", BUserList);
		return "admin/userManager";
	}

	@RequestMapping("/deleteuserManager")
	public String deleteuserManager(Integer id, Model model) throws IOException {
		logger.log(Level.INFO, toString("adminUserController.deleteUser "));
		boolean flag = adminUserService.deleteuserManager(id, model);
		if(flag){
			model.addAttribute("msg", toString("page.deleteSuccess"));
		}else{
			model.addAttribute("msg", toString("page.deleteError"));
		}
		return "forward:/adminUser/userInfo";
	}
}
