package com.liucg.service.impl;

import com.liucg.pojo.Menu;
import com.liucg.dao.MenuMapper;
import com.liucg.service.Menuservice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
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
 * @since 2019-12-15
 */
@Service
public class MenuserviceImpl extends ServiceImpl<MenuMapper, Menu> implements Menuservice {
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public List<Map<String, Object>> getMenuByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return menuMapper.getMenuByUserId(roleId);
	}
	/*递归方法建树
	 * */
	@Override
	public List<Menu> getTreeMenu(String acc) {
		// TODO Auto-generated method stub
		List<Menu> treeNodes=menuMapper.getMenuTree(acc);
		List<Menu> trees=new ArrayList<Menu>();
		for(Menu treeNode:treeNodes) {
			if("1".equals(treeNode.getPid().toString())){
				//System.out.println("oooooooooooooo");
				trees.add(findChildren(treeNode,treeNodes));
			}
		}
		return trees;
	}
	/**
	 * 递归查找子节点
	 * @param treeNode
	 * @param treeNodes
	 * @return
	 */
	private Menu findChildren(Menu treeNode, List<Menu> treeNodes) {
		// TODO Auto-generated method stub
		for(Menu it:treeNodes) {
			if(treeNode.getId().equals(it.getPid())) {
				if(treeNode.getChildren()==null) {
					treeNode.setChildren(new ArrayList<Menu>());
				}
				treeNode.getChildren().add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}

}
