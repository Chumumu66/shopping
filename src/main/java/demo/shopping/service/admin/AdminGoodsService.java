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
	public Map<String, Integer> getPaginationQuery(Integer pageCur);
	public List<Goods> selectGoods(Integer pageCur);
	public Goods getAGood(Integer id);
	public boolean deleteGoods(Integer ids[], Model model);
	public boolean deleteAGoods(Integer id, Model model);
	public boolean addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) throws IOException;
}
