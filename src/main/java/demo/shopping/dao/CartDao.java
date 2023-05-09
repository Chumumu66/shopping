package demo.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("cartDao")
@Mapper
public interface CartDao {
	int focus(Map<String, Object> map);
	List<Map<String, Object>> isFocus(Map<String, Object> map);
	List<Map<String, Object>> isPutCart(Map<String, Object> map);
	int putCart (Map<String, Object> map);
	int updateCart (Map<String, Object> map);
	List<Map<String, Object>> selectCart(Integer id);
	int deleteAgoods (Map<String, Object> map);
	int clear(Integer id);
}
