package demo.shopping.service.before;

import java.util.List;
import java.util.Map;

public interface UserCenterService {

    List<Map<String, Object>> myOrder(Integer bid);

    List<Map<String, Object>> myFocus(Integer bid);

    List<Map<String, Object>> orderDetail(Integer ordersn);

}
