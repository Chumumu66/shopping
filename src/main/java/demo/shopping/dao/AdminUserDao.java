package demo.shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import demo.shopping.po.Buser;

@Repository("adminUserDao")
@Mapper
public interface AdminUserDao {
	List<Buser> userInfo();
	int deleteuserManager(Integer id);
}
