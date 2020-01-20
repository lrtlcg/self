package com.liucg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liucg.pojo.Car;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */

public interface CarMapper extends BaseMapper<Car> {
	/**
	 * 根据车牌号,获取车主信息
	 * @param plate
	 * @return
	 */
	@DS("car1")
	@Select("select PlateID,Name,Phone from(select control_voucher.VoucherNo as PlateID,control_person.PersonName as Name,control_person.RFullPath as Address,control_person.Mobile as Phone from  control_person  left join control_voucher ON control_person.PGUID= control_voucher.PGUID) a where PlateID like concat('%',#{plate},'%') ")
	public List<Map<String,Object>> getList(String plate);
	@DS("car2")
	@Select("select PlateID,Name,Phone from Car  where PlateID like '%'+#{plate}+'%' ")
	public List<Map<String,Object>> getList2(String plate);

}
