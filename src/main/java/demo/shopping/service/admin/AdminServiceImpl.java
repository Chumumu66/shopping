package demo.shopping.service.admin;
import javax.servlet.http.HttpSession;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminDao;
import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.Auser;
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{

	Log logger = LogFactory.getLog(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;

	@Override
	public Auser login(Auser auser) {
		if(adminDao.login(auser) != null && adminDao.login(auser).size() > 0) {
			logger.debug("登陆成功，跳转到后台管理主页面");
			return auser;
		}else{
			logger.debug("登陆失败，用户名或密码错误");
			return null;
		}
	}

}
