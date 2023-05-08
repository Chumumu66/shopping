package demo.shopping.service.before;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import demo.shopping.po.Notice;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Map;

public interface IndexService {
	public List<GoodsType> getGoodsType();
	public List<Map<String, Object>> getSaleList();
	public List<Map<String, Object>> getFocusList();
	public List<Map<String, Object>> getNoticeList();
	public List<Map<String, Object>> getLastedList(Goods goods);
	public String toRegister(Model model);
	public String toLogin(Model model);
	public String goodsDetail(Model model,Integer id);
	public Notice selectANotice(Integer id);
	public String search(Model model,String mykey);
}
