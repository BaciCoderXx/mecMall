package com.taotao.service;

import java.util.List;

public interface BaseService<T> {
	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Long id);
	
	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> queryAll();
	
	/**
	 * 根据条件查询数据
	 * 
	 * @param t
	 * @return
	 */
	public Integer queryCountByWhere(T t); 
	
	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> queryByPage(Integer page, Integer rows);
	
	/**
	 * 根据条件查询一条数据
	 * @param T
	 * @return
	 */
	public T queryOne(T T);
	
	/**
	 * 新增
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 新增，忽略空参数+
	 * @param t
	 * @return
	 */
	public void saveSelective(T t);
	
	/**
	 * 根据主键更新
	 * 
	 * @param t
	 */
	public void updateById(T t);
	
	/**
	 * 根据主键更新，忽略空参数
	 * 
	 * @param t
	 */
	public void updateByIdSelective(T t);
	
	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * 根据ids批量删除数据
	 * 
	 * @param ids
	 */
	public void deleteByIds(List<Object> ids);
}
