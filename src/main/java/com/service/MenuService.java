package com.service;

import com.dao.MenuDao;
import com.google.gson.Gson;
import com.models.entity.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: MenuService
 * @Date: 13/11/2021 12:51 上午
 * @Author: garlam
 * @Description:
 */

@Service
public class MenuService {
	private static final Logger log = LogManager.getLogger(MenuService.class.getName());

	private MenuDao menuDao;
	private List<Menu> menuList = new ArrayList<Menu>();

	public MenuService(List<Menu> menuList) {
		this.menuList = menuList;
	}


	@Autowired
	public MenuService(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public List getMenuList() {
		List<Menu> menuList = menuDao.findAll();

		return menuList;
	}

	public List getMenuTree() {
		List<Menu> menuList = menuDao.findAll();

		MenuService menuService = new MenuService(menuList);
		List<Menu> menuTree = menuService.buildTree();

		return menuTree;
	}


	//建立树形结构
	private List<Menu> buildTree() {
		List<Menu> treeMenus = new ArrayList<Menu>();
		List<Menu> rootNode = getRootNode();
		for (Menu menuNode : rootNode) {
			menuNode = buildChildTree(menuNode);
			treeMenus.add(menuNode);
		}
		return treeMenus;
	}

	//递归，建立子树形结构
	private Menu buildChildTree(Menu pNode) {
		List<Menu> childMenus = new ArrayList<Menu>();
		for (Menu menuNode : menuList) {
			if (menuNode.getParentId().equals(pNode.getMenuId())) {
				childMenus.add(buildChildTree(menuNode));
			}
		}
		pNode.setChildren(childMenus);
		return pNode;
	}

	//获取根节点
	private List<Menu> getRootNode() {
		List<Menu> rootMenuLists = new ArrayList<Menu>();
		for (Menu menuNode : menuList) {
			if (menuNode.getParentId() == 0l) {
				rootMenuLists.add(menuNode);
			}
		}
		return rootMenuLists;
	}


	private static void main(String[] args) {

		Menu m1 = new Menu();
		m1.setMenuId(1l);
		m1.setMenu("系统管理");
		m1.setParentId(0l);
		m1.setParentMenu("0");

		Menu m2 = new Menu();
		m2.setMenuId(2l);
		m2.setMenu("权限管理");
		m2.setParentId(1l);
		m2.setParentMenu("系统管理");

		Menu m3 = new Menu();
		m3.setMenuId(3l);
		m3.setMenu("密码修改");
		m3.setParentId(2l);
		m3.setParentMenu("权限管理");


		Menu m4 = new Menu();
		m4.setMenuId(4l);
		m4.setMenu("权限管理");
		m4.setParentId(2l);
		m4.setParentMenu("系统管理");


		Menu m5 = new Menu();
		m5.setMenuId(5l);
		m5.setMenu("系统监控");
		m5.setParentId(1l);
		m5.setParentMenu("系统管理");


		Menu m6 = new Menu();
		m6.setMenuId(6l);
		m6.setMenu("在线用户");
		m6.setParentId(5l);
		m6.setParentMenu("系统监控");


		Menu m7 = new Menu();
		m7.setMenuId(7l);
		m7.setMenu("订阅区");
		m7.setParentId(0l);
		m7.setParentMenu("0");


		Menu m8 = new Menu();
		m8.setMenuId(8l);
		m8.setMenu("未知领域");
		m8.setParentId(0l);
		m8.setParentMenu("0");


		List<Menu> menuList = new ArrayList<Menu>();
		/*插入一些数据*/
		menuList.add(m1);
		menuList.add(m2);
		menuList.add(m3);
		menuList.add(m4);
		menuList.add(m5);
		menuList.add(m6);
		menuList.add(m7);
		menuList.add(m8);


		/*创建菜单树*/
		MenuService menuTree = new MenuService(menuList);
		menuList = menuTree.buildTree();
		/*转为json看看效果*/
		Gson gson = new Gson();
		String jsonOutput = gson.toJson(menuList);
		System.out.println(jsonOutput);
	}
}

