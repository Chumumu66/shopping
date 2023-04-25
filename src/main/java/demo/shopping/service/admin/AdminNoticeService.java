package demo.shopping.service.admin;

import org.springframework.ui.Model;

import demo.shopping.po.Notice;

import java.util.List;

public interface AdminNoticeService {
	public boolean addNotice(Notice notice);
	public List<Notice> deleteNoticeSelect();
	public Notice selectANotice(Integer id);
	public boolean deleteNotice(Integer id);
}
