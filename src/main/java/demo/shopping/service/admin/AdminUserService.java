package demo.shopping.service.admin;

import demo.shopping.po.Buser;
import org.springframework.ui.Model;
import java.util.List;

public interface AdminUserService {

	List<Buser> userInfo();

	boolean deleteuserManager(Integer id, Model model);

}
