package demo.shopping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.shopping.po.Notice;
import demo.shopping.service.admin.AdminNoticeService;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/adminNotice")
public class AdminNoticeController extends BaseController {

	Logger logger = Logger.getLogger("AdminNoticeController");

	@Autowired
	private AdminNoticeService adminNoticeService;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@RequestMapping("/toAddNotice")
	public String toAddNotice(Model model) throws IOException {
		logger.log(Level.INFO, toString("adminNoticeController.getAddNotice"));
		model.addAttribute("notice", new Notice());
		return "admin/addNotice";
	}

	@RequestMapping("/addNotice")
	public String addNotice(@ModelAttribute Notice notice, Model model) throws IOException {
		logger.log(Level.INFO,toString("adminNoticeController.addNotice"));
		Notice notice1 = adminNoticeService.addNotice(notice);
		return "forward:/adminNotice/deleteNoticeSelect";
	}

	@RequestMapping("/deleteNoticeSelect")
	public String deleteNoticeSelect(Model model) throws IOException {
		logger.log(Level.INFO,toString("adminNoticeController.addNotice"));
		List<Notice> noticeList = adminNoticeService.deleteNoticeSelect();
		model.addAttribute("allNotices", noticeList);
		return "admin/deleteNoticeSelect";
	}

	@RequestMapping("/selectANotice")
	public String selectANotice(Model model, Integer id) throws IOException {
		logger.log(Level.INFO,toString("adminNoticeController.getNotice"));
		Notice notice = adminNoticeService.selectANotice(id);
		model.addAttribute("notice", notice);
		return "admin/noticeDetail";
	}

	@RequestMapping("/deleteNotice")
	public String deleteNotice(Integer id) throws IOException {
		logger.log(Level.INFO,toString("adminNoticeController.deleteNotice"));
		adminNoticeService.deleteNotice(id);
		return "forward:/adminNotice/deleteNoticeSelect";
	}
}
