package com.taotao.content.service.impI;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	@Autowired
	private TbContentMapper mapper;
	
	@Override
	public TaotaoResult saveContent(TbContent content) {
		//1.注入mapper
		
		//2.补全其他的属性
		content.setCreated(new Date());
		content.setUpdated(content.getCreated());
		//3.插入内容表中
		mapper.insertSelective(content);
		//缓存同步
		try {
			jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok(); 
	}

	@Override
	public List<TbContent> getContentListByCatId(Long categoryId) {
		//检查缓存中是否存在数据 使用try catch包裹不影响正常业务
		try {
			String jsonStr = jedisClient.hget(CONTENT_KEY, categoryId+"");
			if(StringUtils.isNotBlank(jsonStr)){
				return JsonUtils.jsonToList(jsonStr, TbContent.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//1.注入mapper
		//2.创建Example
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);//相当于select * from tbcontent where id == category_Id
		//3.设置查询条件
		List<TbContent> list = mapper.selectByExample(example);
		//4.执行查询
		//返回
		try {
			jedisClient.hset(CONTENT_KEY, categoryId+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TaotaoResult deleteContent(List<Long> ids) {
		int flag = 0;
		for(Long id : ids){
			TbContent content = mapper.selectByPrimaryKey(id);
			Long tbCategoryId = content.getCategoryId();
			flag = mapper.deleteByPrimaryKey(id);
			if(flag == 0) return TaotaoResult.ok(0);
			jedisClient.hdel(CONTENT_KEY, tbCategoryId+"");
		}
		return TaotaoResult.ok(1);
	}
	
}
