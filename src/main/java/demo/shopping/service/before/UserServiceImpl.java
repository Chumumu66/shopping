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
    public Buser login(Buser buser) {
        Buser ruser = null;
        List<Buser> list = userDao.login(buser);
        if(list.size() > 0) {
            ruser = list.get(0);
        }
        if(buser.getBpwd().equals(ruser.getBpwd())){
            return ruser;
        }else{
            return null;
        }
    }
}
