package com.taotao.service.impl;

import java.util.List;

import com.taotao.service.BaseService;

public class BaseServiceImpI<T> implements BaseService<T>{
	
	
	@Override
	public T queryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryCountByWhere(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryByPage(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T queryOne(T T) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSelective(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateById(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByIdSelective(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(List<Object> ids) {
		// TODO Auto-generated method stub
		
	}

}
