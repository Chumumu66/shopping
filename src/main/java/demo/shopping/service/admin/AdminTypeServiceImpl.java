package demo.shopping.service.admin;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.shopping.dao.AdminTypeDao;
import java.util.List;

@Service("adminTypeService")
@Transactional
public class AdminTypeServiceImpl implements AdminTypeService{

	@Autowired
	private AdminTypeDao adminTypeDao;

	@Override
	public List<GoodsType> toAddType() {
		List<GoodsType> goodsTypeList = adminTypeDao.selectGoodsType();
		return goodsTypeList;
	}

	@Override
	public int addType(String typename) {
		return adminTypeDao.addType(typename);
	}

	@Override
	public List<GoodsType> toDeleteType() {
		return adminTypeDao.selectGoodsType();
	}

	//异常处理示例
	@Override
	public int deleteType(Integer id) {
		List<Goods> goodsList = adminTypeDao.selectGoodsByType(id);
		if(goodsList.size() > 0){
			return 0;
		}
		int count = adminTypeDao.deleteType(id);
		if(count == 0){
			return 1;
		}else{
			return 2;
		}
	}

	@Override
	public List<GoodsType> selectGoodsType() {
		return adminTypeDao.selectGoodsType();
	}

}
