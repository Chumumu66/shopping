package demo.shopping.service.admin;

import demo.shopping.po.Auser;
import demo.shopping.po.GoodsType;

import java.util.List;

public interface AdminService {

	Auser login(Auser auser);

	List<GoodsType> selectGoodsType();

}
