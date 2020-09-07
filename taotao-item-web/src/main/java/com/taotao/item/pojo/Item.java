package com.taotao.item.pojo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.taotao.pojo.TbItem;
/**
 * 这个pojo没有方在common中是因为common本身是需要依赖taotao-manager-pojo而Item这个类是需要依赖TbItem来注入的会形成循环依赖。
 * @author 602
 *
 */
public class Item extends TbItem {
	
	public Item(TbItem item){
		BeanUtils.copyProperties(item, this);//讲原来数据有的属性的值拷贝到item有的属性中
	}
	
	public String[] getImages(){
		if(StringUtils.isNotBlank(super.getImage())){
			return super.getImage().split(",");
		}
		StringUtils.isNotBlank("");
		return null;
	}
}
