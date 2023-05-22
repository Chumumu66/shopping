package demo.shopping.service.before;

import demo.shopping.dao.UserDao;
import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    int register(Buser buser);

    Buser login(Buser buser);
}
