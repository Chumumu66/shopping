package demo.shopping.service.admin;
import javax.servlet.http.HttpSession;
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

	@Autowired
	private AdminDao adminDao;

	@Override
	public Auser login(Auser auser) {
		if(adminDao.login(auser) != null && adminDao.login(auser).size() > 0) {
			return auser;
		}else{
			return null;
		}
	}

}
