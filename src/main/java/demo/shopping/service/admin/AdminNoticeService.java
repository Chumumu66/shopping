package demo.shopping.service.admin;

import demo.shopping.po.Notice;
import java.util.List;

public interface AdminNoticeService {
	Notice addNotice(Notice notice);
	List<Notice> deleteNoticeSelect();
	Notice selectANotice(Integer id);
	void deleteNotice(Integer id);
}
