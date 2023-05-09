package demo.shopping.service.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminGoodsDao;
import demo.shopping.po.Goods;
import demo.shopping.util.MyUtil;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

@Service("adminGoodsService")
@Transactional
public class AdminGoodsServiceImpl implements AdminGoodsService{
	@Autowired
	private AdminGoodsDao adminGoodsDao;

	@Override
	public Map<String, Integer> getPaginationQuery(Integer pageCur) {
		Map<String, Integer> map = new HashMap<>();
		int temp = adminGoodsDao.getGoodsCount();
		map.put("totalCount", temp);
		int totalPage = 0;
		if (temp == 0) {
			totalPage = 0;
		} else {
			totalPage = (int) Math.ceil((double) temp / 10);
		}
		map.put("totalPage", totalPage);
		if (pageCur == null) {
			pageCur = 1;
		}
		if ((pageCur - 1) * 10 > temp) {
			pageCur = pageCur - 1;
		}
		map.put("pageCur", pageCur);
		return map;
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
	public boolean addOrUpdateGoods(Goods goods, HttpServletRequest request, String flag) throws IOException {
		String newFileName = "";
		String fileName = goods.getLogoImage().getOriginalFilename();

		if(fileName.length() > 0){
			File file = new File("");
			String realpath = file.getCanonicalPath() + "\\src\\main\\resources\\static\\images\\admin\\product";
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			newFileName = MyUtil.getStringID() + fileType;
			goods.setGpicture(newFileName);
			File targetFile = new File(realpath, newFileName);
			if(!targetFile.exists()){
				targetFile.mkdir();
			}
			try {
				goods.getLogoImage().transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag.equals("update")){
			if(adminGoodsDao.updateGoodsById(goods) > 0){
				return true;
			}else{
				return false;
			}
		}else{
			if(adminGoodsDao.addGoods(goods) > 0){
				return true;
			}else{
				return false;
			}
		}
	}
}
