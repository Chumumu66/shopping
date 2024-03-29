package demo.shopping.service.admin;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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

	//异常处理示例
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteorderManager(Integer id) {
		try {
			int count = adminOrderDao.deleteOrderDetail(id);
			if(count == 0){
				return 0;
			}
			count = adminOrderDao.deleteOrderBase(id);
			if(count == 0){
				return 1;
			}
		}catch (Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 2;
		}
		return 3;
	}

}
