package com.liucg.pojo;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucg
 * @since 2019-12-16
 */
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String urlsrc;

    private String urlico;

    private String urllevel;

    private String status;
    @TableField(exist =  false)
    private List<Menu> children;

    private Integer pid;


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

    public String getUrlsrc() {
        return urlsrc;
    }

    public void setUrlsrc(String urlsrc) {
        this.urlsrc = urlsrc;
    }

    public String getUrlico() {
        return urlico;
    }

    public void setUrlico(String urlico) {
        this.urlico = urlico;
    }

    public String getUrllevel() {
        return urllevel;
    }

    public void setUrllevel(String urllevel) {
        this.urllevel = urllevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    

    public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", name=" + name +
        ", urlsrc=" + urlsrc +
        ", urlico=" + urlico +
        ", urllevel=" + urllevel +
        ", status=" + status +
        ", pid=" + pid +
        "}";
    }
}
