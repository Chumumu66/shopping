package demo.shopping.service.admin;

import demo.shopping.po.GoodsType;
import java.util.List;

public interface AdminTypeService {

	List<GoodsType> toAddType();

	int addType(String typename);

	List<GoodsType> toDeleteType();

	boolean deleteType(Integer id);

}
