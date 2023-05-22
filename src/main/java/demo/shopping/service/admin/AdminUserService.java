package demo.shopping.service.admin;

import demo.shopping.po.Buser;
import org.springframework.ui.Model;
import java.util.List;

public interface AdminUserService {

	List<Buser> userInfo();

	int deleteuserManager(Integer id, Model model);

	int deleteUser(Integer id, Model model);

}
