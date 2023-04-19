package demo.shopping.service.before;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.UserDao;
import demo.shopping.po.Buser;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public String register(Buser buser, Model model, HttpSession session, String code) {
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

	@Override
	public String login(Buser buser, Model model, HttpSession session, String code) {
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

}
