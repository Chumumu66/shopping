package demo.shopping.service.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminGoodsDao;
import demo.shopping.po.Goods;
import demo.shopping.util.MyUtil;
@Service("adminGoodsService")
@Transactional
public class AdminGoodsServiceImpl implements AdminGoodsService{
	@Autowired
	private AdminGoodsDao adminGoodsDao;

	@Override
	public int getGoodsCount() {
		int temp = adminGoodsDao.getGoodsCount();
		return temp;
	}

	@Override
	public int getTotalPage(int temp) {
		int totalPage = 0;
		if (temp == 0) {
			totalPage = 0;
		} else {
			totalPage = (int) Math.ceil((double) temp / 10);
		}
		return totalPage;
	}

	@Override
	public int getPageCur(int temp, Integer pageCur) {
		if (pageCur == null) {
			pageCur = 1;
		}
		if ((pageCur - 1) * 10 > temp) {
			pageCur = pageCur - 1;
		}
		return pageCur;
	}

	@Override
	public List<Goods> selectGoods(Integer pageCur) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startIndex", (pageCur - 1) * 10);
		map.put("perPageSize", 10);
		List<Goods> allGoods = adminGoodsDao.selectAllGoodsByPage(map);
		return allGoods;
	}

	@Override
	public Goods getAGood(Integer id) {
		Goods aGood = adminGoodsDao.selectGoodsById(id);
		return aGood;
	}

	@Override
	public boolean deleteGoods(Integer[] ids, Model model) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			if(adminGoodsDao.selectCartGoods(ids[i]).size() > 0 ||
					adminGoodsDao.selectFocusGoods(ids[i]).size() > 0 || 
					adminGoodsDao.selectOrderdetailGoods(ids[i]).size() > 0) {
				return false;
			}
			list.add(ids[i]);
		}
		adminGoodsDao.deleteGoods(list);
		return true;
	}

	@Override
	public boolean deleteAGoods(Integer id, Model model) {
		if(adminGoodsDao.selectCartGoods(id).size() > 0 ||
				adminGoodsDao.selectFocusGoods(id).size() > 0 || 
				adminGoodsDao.selectOrderdetailGoods(id).size() > 0) {
			return false;
		}
		adminGoodsDao.deleteAGoods(id);
		return true;
	}

	@Override
	public String addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) {
		String newFileName = "";
		String fileName = goods.getLogoImage().getOriginalFilename();

		if(fileName.length() > 0){
			String realpath = "D:\\javaeeFile\\shopping\\src\\main\\resources\\static\\images\\admin\\product";
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			newFileName = MyUtil.getStringID() + fileType;
			goods.setGpicture(newFileName);
			File targetFile = new File(realpath, newFileName);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			try {
				goods.getLogoImage().transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if("update".equals(updateAct)){
			if(adminGoodsDao.updateGoodsById(goods) > 0){
				return "forward:/adminGoods/selectGoods?act=updateSelect";
			}else{
				return "/adminGoods/updateAgoods";
			}
		}else{
			if(adminGoodsDao.addGoods(goods) > 0){
				return "forward:/adminGoods/selectGoods";
			}else{
				return "card/addCard";
			}
		}
	}
}
