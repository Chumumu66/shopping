package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminTypeDao;
@Service("adminTypeService")
@Transactional
public class AdminTypeServiceImpl implements AdminTypeService{
	@Autowired
	private AdminTypeDao adminTypeDao;

	@Override
	public String toAddType(Model model) {
		model.addAttribute("allTypes", adminTypeDao.selectGoodsType());
		return "admin/addType";
	}

	@Override
	public String addType(String typename, Model model, HttpSession session) {
		adminTypeDao.addType(typename);
		session.setAttribute("goodsType", adminTypeDao.selectGoodsType());
		return "forward:/adminType/toAddType";
	}

	@Override
	public String toDeleteType(Model model) {
		model.addAttribute("allTypes", adminTypeDao.selectGoodsType());
		return "admin/deleteType";
	}

	@Override
	public String deleteType(Integer id, Model model) {
		if(adminTypeDao.selectGoodsByType(id).size() > 0) {
			model.addAttribute("msg", "�����й�����������ɾ����");
			return "forward:/adminType/toDeleteType";
		}
		if(adminTypeDao.deleteType(id) > 0) 
			model.addAttribute("msg", "���ͳɹ�ɾ����");
		return "forward:/adminType/toDeleteType";
	}
	
}
