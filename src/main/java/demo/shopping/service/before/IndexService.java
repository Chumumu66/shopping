package demo.shopping.service.before;

import demo.shopping.po.Goods;
import demo.shopping.po.GoodsType;
import demo.shopping.po.Notice;
import java.util.List;
import java.util.Map;

public interface IndexService {

	List<GoodsType> getGoodsType();

	List<Map<String, Object>> getSaleList();

	List<Map<String, Object>> getFocusList();

	List<Map<String, Object>> getNoticeList();

	List<Map<String, Object>> getLastedList(Goods goods);

	Goods goodsDetail(Integer id);

	Notice selectANotice(Integer id);

	List<Goods> search(String mykey);

}
