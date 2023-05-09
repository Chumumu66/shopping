package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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

	@Override
	public boolean deleteType(Integer id) {
		if(adminTypeDao.selectGoodsByType(id).size() > 0 || adminTypeDao.deleteType(id) > 0) {
			return true;
		}else{
			return false;
		}
	}
	
}
