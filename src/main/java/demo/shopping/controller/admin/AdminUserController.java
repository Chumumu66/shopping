package demo.shopping.controller.admin;

import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.shopping.service.admin.AdminUserService;

import java.util.List;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController{

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/userInfo")
	public String userInfo(Model model) {
		List<Buser> BUserList = adminUserService.userInfo();
		model.addAttribute("userList", BUserList);
		return "admin/userManager";
	}

	@RequestMapping("/deleteuserManager")
	public String deleteuserManager(Integer id, Model model) {
		boolean flag = adminUserService.deleteuserManager(id, model);
		if(flag == true){
			model.addAttribute("msg", "删除成功！");
		}else{
			model.addAttribute("msg", "删除失败！");
		}
		return "forward:/adminUser/userInfo";
	}
}
