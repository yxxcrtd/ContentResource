package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;
import cn.digitalpublishing.po.Authorization;

/**
 * @name 授权信息
 * @Autor  CuiXian
 */
public interface AuthorizationService extends BaseService {

	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(Authorization obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
    public void insert(Authorization obj) throws Exception;
    
    /**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取授权信息列表
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Authorization> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 获取列表信息总数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据Id获取授权信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Authorization getById(String id) throws Exception;
	
}