package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 内容处理的接口
 * @title ContentService.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh 
 * @version 1.0
 */
public interface ContentService {
	/**
	 * 插入内容表
	 * @param content
	 * @return
	 */
	public TaotaoResult saveContent(TbContent content);
	
	/**
	 * 根据内容分类的id 查询其下的内容列表
	 * @param id
	 * @return
	 */
	public List<TbContent> getContentListByCatId(Long categoryId);
	
	/**
	 * 删除内容
	 * @param contentId
	 * @return
	 */
	public TaotaoResult deleteContent(List<Long> ids);
}
