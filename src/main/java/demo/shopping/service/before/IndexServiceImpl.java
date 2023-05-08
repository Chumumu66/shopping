package demo.shopping.service.before;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import demo.shopping.po.GoodsType;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminNoticeDao;
import demo.shopping.dao.AdminTypeDao;
import demo.shopping.dao.IndexDao;
import demo.shopping.po.Buser;
import demo.shopping.po.Goods;
import demo.shopping.po.Notice;

@Service("indexService")
@Transactional
public class IndexServiceImpl implements IndexService{

	@Autowired
	private IndexDao indexDao;

	@Autowired
	private AdminTypeDao adminTypeDao;

	@Autowired
	private AdminNoticeDao adminNoticeDao;

	@Override
	public List<GoodsType> getGoodsType() {
		return adminTypeDao.selectGoodsType();
	}

	@Override
	public List<Map<String, Object>> getSaleList() {
		return indexDao.getSaleOrder();
	}

	@Override
	public List<Map<String, Object>> getFocusList() {
		return indexDao.getFocusOrder();
	}

	@Cacheable(value =  "noticeList")
	@Override
	public List<Map<String, Object>> getNoticeList() {
		return indexDao.selectNotice();
	}

	@Override
	public List<Map<String, Object>> getLastedList(Goods goods) {
		return indexDao.getLastedGoods(goods);
	}

	@Override
	public String toRegister(Model model) {
		model.addAttribute("rbuser", new Buser());
		return "before/register";
	}

	@Override
	public String toLogin(Model model) {

		model.addAttribute("lbuser", new Buser());

		return "before/login.html";

	}

	@Override
	public String goodsDetail(Model model, Integer id) {
		Goods goods = indexDao.selectGoodsById(id);
		model.addAttribute("goods", goods);
		return "before/goodsdetail";
	}

	@Cacheable(value = "notices", key = "#id")
	@Override
	public Notice selectANotice(Integer id) {
		Notice notice = adminNoticeDao.selectANotice(id);
		return notice;
	}

	@Override
	public String search(Model model, String mykey) {
		List<Goods> list = indexDao.search(mykey);
		model.addAttribute("searchlist", list);
		return "before/searchResult";
	}
	
}
