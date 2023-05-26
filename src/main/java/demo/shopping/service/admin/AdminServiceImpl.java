package demo.shopping.service.admin;

import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.GoodsType;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.shopping.dao.AdminDao;
import demo.shopping.po.Auser;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{

	Log logger = LogFactory.getLog(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminTypeDao adminTypeDao;

	public String toString(String str) throws IOException {
		return StringUtils.substringAfter(StringUtils
				.substringBefore(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
						.substring(new String(Files.readAllBytes(new ClassPathResource("templates/i18n/messages.properties").getFile().toPath()))
								.indexOf(str)),"\r\n"), "= ");
	}

	@Override
	public Auser findAUserByUserNameAndPassword(String user_name, String password) throws IOException {
		Auser auser = adminDao.findAUserByUserNameAndPassword(user_name);
		if(auser.getApwd().equals(password)) {
			logger.debug(toString("adminServiceImpl.loginSuccess"));
			return auser;
		}else{
			logger.debug(toString("adminServiceImpl.loginError"));
			return null;
		}
	}

	@Override
	public List<GoodsType> selectGoodsType() {
		return adminTypeDao.selectGoodsType();
	}

}
