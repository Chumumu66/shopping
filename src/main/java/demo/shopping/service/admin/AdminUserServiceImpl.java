package demo.shopping.service.admin;

import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import demo.shopping.dao.AdminUserDao;
import demo.shopping.dao.CartDao;
import demo.shopping.dao.UserCenterDao;
import java.util.List;
import java.util.Map;

@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserCenterDao userCenterDao;

	@Override
	public List<Buser> userInfo() {
		List<Buser> bUserList = adminUserDao.userInfo();
		return bUserList;
	}

	//异常处理示例
	@Override
	public int deleteuserManager(Integer id, Model model) {
		List<Map<String, Object>> cartList = cartDao.selectCart(id);
		if(cartList.size() > 0){
			return 0;
		}
		List<Map<String, Object>> focusList = userCenterDao.myFocus(id);
		if(focusList.size() > 0){
			return 1;
		}
		List<Map<String, Object>> orderList = userCenterDao.myOrder(id);
		if(orderList.size() > 0){
			return 2;
		}
		int count = adminUserDao.deleteuserManager(id);
		if(count == 0){
			return 3;
		}
		return 4;
	}

	@Override
	public int deleteUser(Integer id, Model model) {
		return adminUserDao.deleteuserManager(id);
	}

}
