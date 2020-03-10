package com.liucg.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucg
 * @since 2020-03-08
 */
@TableName("bus_job")
public class Job extends Model<Job> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("companyName")
    private String companyName;

    @TableField("companyAddr")
    private String companyAddr;

    @TableField("companyInfo")
    private String companyInfo;

    @TableField("jobName")
    private String jobName;

    @TableField("jobAddr")
    private String jobAddr;

    @TableField("jobInfo")
    private String jobInfo;

    @TableField("webUrl")
    private String webUrl;

    @TableField("salaryMax")
    private String salaryMax;

    @TableField("salaryMin")
    private String salaryMin;

    @TableField("jobTime")
    private String jobTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Job{" +
        "id=" + id +
        ", companyName=" + companyName +
        ", companyAddr=" + companyAddr +
        ", companyInfo=" + companyInfo +
        ", jobName=" + jobName +
        ", jobAddr=" + jobAddr +
        ", jobInfo=" + jobInfo +
        ", webUrl=" + webUrl +
        ", salaryMax=" + salaryMax +
        ", salaryMin=" + salaryMin +
        ", jobTime=" + jobTime +
        "}";
    }
}
