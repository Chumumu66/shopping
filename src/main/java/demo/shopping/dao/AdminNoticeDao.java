package demo.shopping.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import demo.shopping.po.Notice;

@Repository("adminNoticeDao")
@Mapper
public interface AdminNoticeDao {
	int addNotice(Notice notice);

	List<Notice> deleteNoticeSelect();

	int deleteNotice(Integer id);

	Notice selectANotice(Integer id);

}
