package demo.shopping.service.before;

import java.util.List;
import java.util.Map;

public interface CartService {

	Map<String, Object> focus(Integer id, Integer uId);

	Map<String, Object> putCart(Integer shoppingnum, Integer id, Integer uId);

	List<Map<String, Object>> selectCart(Integer id);

	int deleteAgoods(Integer id,Integer uId);

	int clear(Integer id);

	List<Map<String, Object>> orderConfirm(Integer id);

	List<Map<String, Object>> isFocus(Map<String, Object> map);

	List<Map<String, Object>> isPutCart(Map<String, Object> map);

	int putCart (Map<String, Object> map);

	int updateCart (Map<String, Object> map);

	int focus(Map<String, Object> map);

}
