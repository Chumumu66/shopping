package demo.shopping.service.before;

import demo.shopping.dao.UserDao;
import demo.shopping.po.Buser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int register(Buser buser) {
        return userDao.register(buser);
    }

    @Override
    public List<Buser> login(Buser buser) {
        return userDao.login(buser);
    }
}
