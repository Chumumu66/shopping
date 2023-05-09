package demo.shopping.service.admin;

import javax.servlet.http.HttpSession;

import demo.shopping.po.GoodsType;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminTypeService {
	List<GoodsType> toAddType();
	int addType(String typename);
	List<GoodsType> toDeleteType();
	boolean deleteType(Integer id);
}
