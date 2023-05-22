package demo.shopping.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import demo.shopping.po.Auser;

@Repository("adminDao")
@Mapper
public interface AdminDao {

	Auser findAUserByUserNameAndPassword(@Param("user_name") String userName);

}
