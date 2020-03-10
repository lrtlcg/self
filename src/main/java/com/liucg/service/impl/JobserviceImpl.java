package com.liucg.service.impl;

import com.liucg.pojo.Job;
import com.liucg.dao.JobMapper;
import com.liucg.service.Jobservice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucg
 * @since 2020-03-08
 */
@Service
public class JobserviceImpl extends ServiceImpl<JobMapper, Job> implements Jobservice {

}
