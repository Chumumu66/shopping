package demo.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
@Repository("adminTypeDao")
@Mapper
public interface AdminTypeDao {
	List<GoodsType> selectGoodsType();
	int addType(String typename);
	int deleteType(Integer id);
	List<Goods> selectGoodsByType(Integer id);
}
