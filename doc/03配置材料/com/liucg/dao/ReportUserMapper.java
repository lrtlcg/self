package com.liucg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liucg.pojo.ReportUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liucg
 * @since 2019-12-19
 */
public interface ReportUserMapper extends BaseMapper<ReportUser> {
	/**
	 * 获取报表用户权限
	 * @param reportId 报表id
	 * @return
	 */
	@Select("select * from(select bus_reportUser.id,sys_user.name,sys_user.acc,sys_user.departmentName,sys_user.jobTitleName from bus_reportFile left join  bus_reportUser on bus_reportFile.id=bus_reportUser.reportId left join sys_user on bus_reportUser.userId=sys_user.id where bus_reportFile.id=#{reportId}) a where a.id is not null")
	public List<Map<String,Object>> getUserByReportId(Integer reportId);
}
