package demo.shopping.service.before;

import demo.shopping.dao.UserCenterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("userCenterService")
@Transactional
public class UserCenterServiceImpl implements UserCenterService{

    @Autowired
    private UserCenterDao userCenterDao;

    @Override
    public List<Map<String, Object>> myOrder(Integer bid) {
        return userCenterDao.myOrder(bid);
    }

    @Override
    public List<Map<String, Object>> myFocus(Integer bid) {
        return userCenterDao.myFocus(bid);
    }

    @Override
    public List<Map<String, Object>> orderDetail(Integer ordersn) {
        return userCenterDao.orderDetail(ordersn);
    }
}
