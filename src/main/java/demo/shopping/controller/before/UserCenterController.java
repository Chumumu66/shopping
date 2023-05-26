package demo.shopping.controller.before;

import javax.servlet.http.HttpSession;

import demo.shopping.service.before.UserCenterService;
import demo.shopping.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserCenterController extends BaseBeforeController {

	Logger logger = Logger.getLogger("UserCenterController");

	@Autowired
	private UserCenterService userCenterService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/userCenter")
	public String userCenter(HttpSession session, Model model) throws IOException {
		logger.log(Level.INFO, toString("userCenterController.getUserCenter"));
		model.addAttribute("myOrder", userCenterService.myOrder(MyUtil.getUserId(session)));
		model.addAttribute("myFocus", userCenterService.myFocus(MyUtil.getUserId(session)));
		return "before/userCenter";
	}

	@RequestMapping("/orderDetail")
	public String orderDetail(Model model, Integer ordersn) throws IOException {
		logger.log(Level.INFO, toString("userCenterController.getDetail"));
		model.addAttribute("myOrderDetail", userCenterService.orderDetail(ordersn));
		return "before/userOrderDetail";
	}
}
