package demo.shopping.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminOrderDao;

@Service("adminOrderService")
@Transactional
public class AdminOrderServiceImpl implements AdminOrderService{
	@Autowired
	private AdminOrderDao adminOrderDao;
	@Override
	public List<Map<String,Object>> orderInfo(Model model) {
		List<Map<String,Object>> list = adminOrderDao.orderInfo();
		return list;
	}
	@Override
	public boolean deleteorderManager(Integer id) {
		if(adminOrderDao.deleteOrderDetail(id) != 0 && adminOrderDao.deleteOrderBase(id) != 0){
			return true;
		}else{
			return false;
		}
	}

}
