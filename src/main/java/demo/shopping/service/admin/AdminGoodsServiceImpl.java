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
	public String addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) {
		String newFileName = "";
		String fileName = goods.getLogoImage().getOriginalFilename(); 

		if(fileName.length() > 0){
			String realpath = request.getServletContext().getRealPath("logos");
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

	@Override
	public String selectGoods(Model model, Integer pageCur, String act) {
		List<Goods> allGoods = adminGoodsDao.selectGoods();
		int temp = allGoods.size();
		model.addAttribute("totalCount", temp);
		int totalPage = 0;
		if (temp == 0) {
			totalPage = 0;
		} else {
			totalPage = (int) Math.ceil((double) temp / 10);
		}
		if (pageCur == null) {
			pageCur = 1;
		}
		if ((pageCur - 1) * 10 > temp) {
			pageCur = pageCur - 1;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startIndex", (pageCur - 1) * 10);
		map.put("perPageSize", 10);
		allGoods = adminGoodsDao.selectAllGoodsByPage(map);
		model.addAttribute("allGoods", allGoods);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageCur", pageCur);
		if("deleteSelect".equals(act)){
			return "admin/deleteSelectGoods";
		}else if("updateSelect".equals(act)){
			return "admin/updateSelectGoods";
		}else{
			return "admin/selectGoods";
		}
	}

	@Override
	public String selectAGoods(Model model, Integer id, String act) {
		Goods agoods = adminGoodsDao.selectGoodsById(id);
		model.addAttribute("goods", agoods);
		if("updateAgoods".equals(act)){
			return "admin/updateAgoods";
		}
		return "admin/goodsDetail";
	}

	@Override
	public String deleteGoods(Integer[] ids, Model model) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			if(adminGoodsDao.selectCartGoods(ids[i]).size() > 0 ||
					adminGoodsDao.selectFocusGoods(ids[i]).size() > 0 || 
					adminGoodsDao.selectOrderdetailGoods(ids[i]).size() > 0) {
				model.addAttribute("msg", "��Ʒ�й�����������ɾ����");
				return "forward:/adminGoods/selectGoods?act=deleteSelect";
			}
			list.add(ids[i]);
		}
		adminGoodsDao.deleteGoods(list);
		model.addAttribute("msg", "�ɹ�ɾ����Ʒ��");
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@Override
	public String deleteAGoods(Integer id, Model model) {
		if(adminGoodsDao.selectCartGoods(id).size() > 0 ||
				adminGoodsDao.selectFocusGoods(id).size() > 0 || 
				adminGoodsDao.selectOrderdetailGoods(id).size() > 0) {
			model.addAttribute("msg", "��Ʒ�й�����������ɾ����");
			return "forward:/adminGoods/selectGoods?act=deleteSelect";
		}
		adminGoodsDao.deleteAGoods(id);
		model.addAttribute("msg", "�ɹ�ɾ����Ʒ��");
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}
}
