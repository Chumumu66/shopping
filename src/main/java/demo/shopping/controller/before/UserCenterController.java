package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.dao.UserCenterDao;
import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserCenterController extends BaseBeforeController {

	Logger logger = Logger.getLogger("UserCenterController");

	@Autowired
	private UserCenterDao userCenterDao;

	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, Model model) {
		logger.log(Level.INFO,"获取用户中心");
		model.addAttribute("myOrder", userCenterDao.myOrder(MyUtil.getUserId(session)));
		model.addAttribute("myFocus", userCenterDao.myFocus(MyUtil.getUserId(session)));
		return "before/userCenter";
	}

	@RequestMapping("/orderDetail")
	public String orderDetail(Model model, Integer ordersn) {
		logger.log(Level.INFO,"获取细节");
		model.addAttribute("myOrderDetail", userCenterDao.orderDetail(ordersn));
		return "before/userOrderDetail";
	}
}
