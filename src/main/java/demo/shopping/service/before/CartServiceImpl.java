package demo.shopping.service.before;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.shopping.dao.CartDao;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;

	@Override
	public Map<String, Object> focus(Integer id, Integer uId) {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uId);
		map.put("gid", id);
		return map;
	}

	@Override
	public Map<String, Object> putCart(Integer shoppingnum, Integer id, Integer uId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uId);
		map.put("gid", id);
		map.put("shoppingnum", shoppingnum);
		return map;
	}

	@Override
	public List<Map<String, Object>> selectCart(Integer id) {
		List<Map<String, Object>> mapList = cartDao.selectCart(id);
		return mapList;
	}

	@Override
	public int deleteAgoods(Integer id, Integer uId) {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uId);
		map.put("gid", id);
		int flag = cartDao.deleteAgoods(map);
		return flag;
	}

	@Override
	public int clear(Integer id) {
		int flag = cartDao.clear(id);
		return flag;
	}

	@Override
	public List<Map<String, Object>> orderConfirm(Integer id) {
		List<Map<String, Object>> mapList = cartDao.selectCart(id);
		return mapList;
	}

}
