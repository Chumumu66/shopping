package demo.shopping.service.admin;

import demo.shopping.dao.AdminTypeDao;
import demo.shopping.po.GoodsType;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.shopping.dao.AdminDao;
import demo.shopping.po.Auser;

import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{

	Log logger = LogFactory.getLog(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminTypeDao adminTypeDao;

	@Override
	public Auser findAUserByUserNameAndPassword(String user_name, String password) {
		Auser auser = adminDao.findAUserByUserNameAndPassword(user_name);
		if(auser.getApwd().equals(password)) {
			logger.debug("登陆成功，跳转到后台管理主页面");
			return auser;
		}else{
			logger.debug("登陆失败，用户名或密码错误");
			return null;
		}
	}

	@Override
	public List<GoodsType> selectGoodsType() {
		return adminTypeDao.selectGoodsType();
	}

}
