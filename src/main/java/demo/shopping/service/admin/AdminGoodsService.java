package demo.shopping.service.admin;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import demo.shopping.po.Goods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdminGoodsService {
	Map<String, Integer> getPaginationQuery(Integer pageCur);
	List<Goods> selectGoods(Integer pageCur);
	Goods getAGood(Integer id);
	boolean deleteGoods(Integer ids[], Model model);
	boolean deleteAGoods(Integer id, Model model);
	boolean addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) throws IOException;
}
