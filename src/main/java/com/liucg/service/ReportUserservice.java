package com.liucg.service;

import com.liucg.pojo.ReportUser;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucg
 * @since 2019-12-19
 */
public interface ReportUserservice extends IService<ReportUser> {
	//获取报表用户权限
	public List<Map<String,Object>> getUserByReportId(Integer reportId);
}
