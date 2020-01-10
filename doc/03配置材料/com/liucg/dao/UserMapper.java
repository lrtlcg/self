package com.liucg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liucg.pojo.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liucg
 * @since 2019-12-12
 */
public interface UserMapper extends BaseMapper<User> {
	@Select("select max(acc) as acc from sys_user where createDate>=#{startDate} and createDate< #{endDate}")
	public String  getAccount(String startDate,String endDate);
	/**
	 * 根据条件来查询
	 * @param search
	 * @return
	 */
	@Select("select * from sys_user where  jobTitleName like concat('%',#{search},'%') or concat('%',#{search},'%') or name like concat('%',#{search},'%') or departmentName like concat('%',#{search},'%')")
	public List<User> ListByParmarm(String search);
}
