package demo.shopping.service.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import demo.shopping.po.Goods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AdminGoodsService {
	public int getGoodsCount();
	public int getTotalPage(int temp);
	public int getPageCur(int temp, Integer pageCur);
	public List<Goods> selectGoods(Integer pageCur);
	public Goods getAGood(Integer id);
	public boolean deleteGoods(Integer ids[], Model model);
	public boolean deleteAGoods(Integer id, Model model);
	public int addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) throws IOException;
}
