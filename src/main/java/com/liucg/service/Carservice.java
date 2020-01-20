package com.liucg.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liucg.pojo.Car;
import com.liucg.pojo.Menu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
public interface Carservice extends IService<Car> {
	//获取车辆list
	public List<Map<String,Object>> getList(String plate);
	//获取车票信息 从两个不同的数据库中获取
	public String getPlateMsg(String plate);

}
