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
 * @since 2019-12-18
 */
@TableName("bus_reportFile")
public class ReportFile extends Model<ReportFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private String version;

    @TableField("devDate")
    private String devDate;

    @TableField("devHum")
    private String devHum;

    private String fb;

    private String mk;

    @TableField("urlSrc")
    private String urlSrc;


    
    public ReportFile(String name, String type, String version, String devDate, String devHum, String fb, String mk,
			String urlSrc) {
		super();
		this.name = name;
		this.type = type;
		this.version = version;
		this.devDate = devDate;
		this.devHum = devHum;
		this.fb = fb;
		this.mk = mk;
		this.urlSrc = urlSrc;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevDate() {
        return devDate;
    }

    public void setDevDate(String devDate) {
        this.devDate = devDate;
    }

    public String getDevHum() {
        return devHum;
    }

    public void setDevHum(String devHum) {
        this.devHum = devHum;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getUrlSrc() {
        return urlSrc;
    }

    public void setUrlSrc(String urlSrc) {
        this.urlSrc = urlSrc;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ReportFile{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", version=" + version +
        ", devDate=" + devDate +
        ", devHum=" + devHum +
        ", fb=" + fb +
        ", mk=" + mk +
        ", urlSrc=" + urlSrc +
        "}";
    }
}
