package demo.shopping.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import demo.shopping.po.Goods;

@Repository("adminGoodsDao")
@Mapper
public interface AdminGoodsDao {

	int getGoodsCount();

	List<Goods> selectGoods();

	List<Goods> selectAllGoodsByPage(Map<String, Object> map);

	int addGoods(Goods goods);

	Goods selectGoodsById(Integer id);

	int deleteGoods(List<Integer> ids);

	int deleteAGoods(Integer id);

	int updateGoodsById(Goods goods);

	List<Map<String, Object>> selectCartGoods(Integer id);

	List<Map<String, Object>> selectFocusGoods(Integer id);

	List<Map<String, Object>> selectOrderdetailGoods(Integer id);

}
