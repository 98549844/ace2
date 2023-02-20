package com.models.entity;

import com.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname: Menus
 * @Date: 12/11/2021 11:48 下午
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "menu")
@Entity
public class Menu extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(strategy = "identity", name = "id")
	@Column
	private Long menuId;
	//private String menuId;

	@Column
	private String menu;

	@Column
	private Long parentId;
	//private String parentId;
	@Column
	private String parentMenu;

	//@transient 就是在给javabean上需要添加个属性, 这个属性你又不希望给存到数据库中去，仅仅是做个非静态的临时变量
	@Transient
	private List<Menu> children;


	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}

