package demo.shopping.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminNoticeDao;
import demo.shopping.po.Notice;

import java.util.List;

@Service("adminNoticeService")
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService{

	@Autowired
	private AdminNoticeDao adminNoticeDao;

	@CacheEvict(value = "noticeList", allEntries = true)
	@Override
	public Notice addNotice(Notice notice) {
		adminNoticeDao.addNotice(notice);
		adminNoticeDao.selectANotice(notice.getId());
		return notice;
	}
	@Override
	public List<Notice> deleteNoticeSelect() {
		List<Notice> noticeList = adminNoticeDao.deleteNoticeSelect();
		return noticeList;
	}
	@Override
	public Notice selectANotice(Integer id) {
		Notice notice = adminNoticeDao.selectANotice(id);
		return notice;
	}

	@Caching(evict = {@CacheEvict(value = "notices"), @CacheEvict(value = "noticeList", allEntries = true)})
	@Override
	public void deleteNotice(Integer id) {
		adminNoticeDao.deleteNotice(id);
	}

}
