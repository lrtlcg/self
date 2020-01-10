package com.liucg.service.impl;

import com.liucg.pojo.ReportUser;
import com.liucg.dao.ReportUserMapper;
import com.liucg.service.ReportUserservice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucg
 * @since 2019-12-19
 */
@Service
public class ReportUserserviceImpl extends ServiceImpl<ReportUserMapper, ReportUser> implements ReportUserservice {
	@Autowired
	ReportUserMapper reportUserMapper;
	@Override
	//获取报表用户权限
	public List<Map<String, Object>> getUserByReportId(Integer reportId) {
		// TODO Auto-generated method stub
		return reportUserMapper.getUserByReportId(reportId);
	}

}
