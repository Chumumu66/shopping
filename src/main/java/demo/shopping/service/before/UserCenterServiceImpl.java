package demo.shopping.service.before;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userCenterService")
@Transactional
public class UserCenterServiceImpl implements UserCenterService{

}
