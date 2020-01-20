package com.liucg.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liucg.dao.CarMapper;
import com.liucg.dao.MenuMapper;
import com.liucg.pojo.Car;
import com.liucg.service.Carservice;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
@Service
public class CarserviceImpl extends ServiceImpl<CarMapper, Car> implements Carservice {
	@Autowired
	private CarMapper carMapper;

	@Override
	public List<Map<String, Object>> getList(String plate) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list1=carMapper.getList(plate);
		List<Map<String,Object>> list2=carMapper.getList2(plate);
		List<Map<String,Object>> listAll=new ArrayList<Map<String,Object>>();
		listAll.addAll(list1);
		listAll.addAll(list2);
		listAll=new ArrayList<Map<String,Object>>(new LinkedHashSet<>(listAll)); //除重复
		return listAll;
	}

	@Override
	public String getPlateMsg(String plate) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=this.getList(plate);
		//log.info("list数据是,{}",list);
		String content="";
		for(Map map:list) {
			String  phone="手机: "+(String) map.get("Phone");
			String name="车主: "+(String) map.get("Name");
			String plateId="车牌: "+(String) map.get("PlateID");
			if(phone.isEmpty()) {
				phone="手机: "+"-";
				name="车主: "+"临时车辆";
			}
			content=name+"\n";
			content=content+plateId+"\n";
			content=content+phone+"\n";
			content=content+"\n";
		}
		return content;
	}
	

}
