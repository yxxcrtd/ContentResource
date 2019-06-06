package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;
import cn.digitalpublishing.po.Latest;

/**
 * @name 最新活动
 * @Autor  CuiXian
 */
public interface LatestService extends BaseService {

	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(Latest obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
    public void insert(Latest obj) throws Exception;
    
    /**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取最新活动列表
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Latest> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 获取列表信息总数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据Id获取最新活动信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Latest getById(String id) throws Exception;
	
}