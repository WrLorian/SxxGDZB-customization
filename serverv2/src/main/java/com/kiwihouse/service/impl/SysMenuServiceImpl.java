package com.kiwihouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.dao.entity.SysMenu;
import com.kiwihouse.dao.mapper.SysMenuMapper;
import com.kiwihouse.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	SysMenuMapper sysMenuMapper;
	
	@Override
	public List<SysMenu> getAuthMenuList(Integer uid) {
		return sysMenuMapper.getAuthMenuList(uid);
	}

	@Override
	public boolean updateMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		 int num = sysMenuMapper.updateMenu(sysMenu);
	     return num == 1? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean updateBatchMenuByIds(Long[] ids) {
		// TODO Auto-generated method stub
		int num = sysMenuMapper.updateBatchMenuByIds(ids);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
	}

}
